package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.HashListImpl;

import com.epri.dss.common.AutoAdd;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.conversion.Generator;
import com.epri.dss.delivery.Capacitor;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.executive.Executive;
import com.epri.dss.executive.impl.DSSExecutive;

/**
 * Unit for processing the AutoAdd Solution functions.
 *
 * Note: Make sure this class in instantiated after energymeter class.
 *
 * There is one of these per circuit.
 *
 */
public class AutoAddImpl implements AutoAdd {

	private Generator GeneratorClass;
	private Capacitor CapacitorClass;

	private int[] BusIdxList;
	private int BusIdxListSize;
	private boolean BusIdxListCreated;
	private int LastAddedGenerator;
	private int LastAddedCapacitor;

	private int BusIndex;
	private int Phases;

	private double Ycap;
	private Complex GenVA;

	private double kWLosses, BaseLosses, puLossImprovement;
	private double kWEEN , BaseEEN, puEENImprovement;

	private PrintStream Log;  // Log File

	private int ProgressCount;


	/* Autoadd mode Variables */
	protected double GenkW, GenPF, Genkvar, Capkvar;
	protected int AddType;

	protected boolean ModeChanged;

	private static double sumSelectedRegisters(EnergyMeterObj Mtr, int[] Regs, int count) {
		double Result = 0.0;
		for (int i = 0; i < count; i++) {
			Result += Mtr.getRegisters()[Regs[i]] * Mtr.getTotalsMask()[Regs[i]];
		}
		return Result;
	}

	public AutoAddImpl() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		BusIdxListCreated = false;
		GeneratorClass = (Generator) Globals.getDSSClassList().get(Globals.getClassNames().find("generator"));
		CapacitorClass = (Capacitor) Globals.getDSSClassList().get(Globals.getClassNames().find("capacitor"));

		// AutoAdd defaults
		GenkW   = 1000.0;
		GenPF   = 1.0;
		Capkvar = 600.0;
		AddType = DSSGlobals.GENADD;
		LastAddedGenerator = 0;
		LastAddedCapacitor = 0;

		ModeChanged = true;
	}

	/**
	 * Make a list of unique bus names.
	 * If AutoAddBusList in ActiveCircuit is not nil, use this list.
	 * Else, Use the element lists in Energy Meters.
	 * If no Energy Meters, use all the buses in the active circuit.
	 */
	public void makeBusList() {
		int retval;
		String Bname;
		PDElement PDElem;
		HashList BusList;
		boolean BusListCreatedHere;

		if (BusIdxListCreated)
			BusIdxList = new int[0];

		BusListCreatedHere = false;
		BusIdxListCreated = false;

		DSSGlobals Globals = DSSGlobals.getInstance();

		// Autoaddbuslist exists in Active Circuit, use it  (see set Autobuslist=)
		if (Globals.getActiveCircuit().getAutoAddBusList().listSize() > 0) {
			BusList = Globals.getActiveCircuit().getAutoAddBusList();
		} else  {
			if (Globals.getActiveCircuit().getEnergyMeters().size() == 0) {
				// No energymeters in circuit
				// Include all buses in the circuit
				BusIdxListSize = Globals.getActiveCircuit().getBusList().listSize();
				BusIdxList = (int[]) Utilities.resizeArray(BusIdxList, BusIdxListSize);

				for (int i = 0; i < BusIdxListSize; i++)
					BusIdxList[i] = i;

				BusIdxListCreated = true;
				return;
			} else {
				/* Construct Bus List from Energy Meters Zone Lists */
				// Include only buses in EnergyMeter lists
				// Consider all meters
				BusListCreatedHere = true;
				BusList = new HashListImpl(Globals.getActiveCircuit().getNumBuses());
				for (EnergyMeterObj pMeter : Globals.getActiveCircuit().getEnergyMeters()) {
					if (pMeter.getBranchList() != null) {
						PDElem = (PDElement) pMeter.getBranchList().getFirst();
						while (PDElem != null) {  // add only unique bus names
							for (int i = 0; i < PDElem.getNTerms(); i++) {
								Bname = Utilities.stripExtension(PDElem.getBus(i));
								retval = BusList.find(Bname);
								if (retval == -1) {  // TODO Check zero based indexing
									BusList.add(Bname);  // return value is index of bus
								}
							}
							PDElem = (PDElement) pMeter.getBranchList().GoForward();
						}
					}
				}
			}
		}

		// Make busIdxList from BusList
		BusIdxListSize = BusList.listSize();
		BusIdxList = (int[]) Utilities.resizeArray(BusIdxList, BusIdxListSize);

		for (int i = 0; i < BusIdxListSize; i++) {
			BusIdxList[i] = Globals.getActiveCircuit().getBusList().find(BusList.get(i));
		}

		if (BusListCreatedHere)
			BusList = null;
		BusIdxListCreated = true;
	}

	/**
	 * Returns losses in metered part of circuit + weighted EEN values.
	 *
	 * If no meters, returns just total losses in circuit.
	 *
	 * Base everything on gen kW.
	 */
	public double getWeightedLosses() {
		double Result;

		ComputekWLosses_EEN();

		if (DSSGlobals.getInstance().getActiveCircuit().getEnergyMeters().size() == 0) {
			// No energymeters in circuit
			// Just go by total system losses
			puLossImprovement = (BaseLosses - kWLosses) / GenkW;
			puEENImprovement = 0.0;
			Result = puLossImprovement;
		} else {
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			puLossImprovement = (BaseLosses - kWLosses) / GenkW;
			puEENImprovement = (BaseEEN - kWEEN)/GenkW;
			Result = ckt.getLossWeight() * puLossImprovement + ckt.getUEWeight() * puEENImprovement;
		}
		return Result;
	}

	public void appendToFile(String WhichFile, String S) {
		// FIXME Implement this method.
		throw new UnsupportedOperationException();
	}

	private String getUniqueGenName() {
		String TrialName = "";
		boolean Done = false;

		while (!Done) {
			Done = true;
			LastAddedGenerator += 1;
			TrialName = "Gadd" + String.valueOf(LastAddedGenerator);
			if (GeneratorClass.find(TrialName) != null)
				Done = false;
		}

		return TrialName;
	}

	private String getUniqueCapName() {
		String TrialName = "";
		boolean Done = false;

		while (!Done) {
			Done = true;
			LastAddedCapacitor += 1;
			TrialName = "Cadd" + String.valueOf(LastAddedCapacitor);
			if (CapacitorClass.find(TrialName) != null)
				Done = false;
		}

		return TrialName;
	}

	/**
	 * Automatically add caps or generators.
	 *
	 * Automatically add a specified size of generator or capacitor at the location
	 * that results in the lowest losses in either metered part of circuit or
	 * total circuit, if no meters.
	 *
	 * If metered, EEN is also added in WITH a selected weighting factor (see
	 * set ueweight= ... command).
	 *
	 * Thus, this algorithm placed generators and capacitors to minimize losses and
	 * potential unserved energy.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public int solve() throws SolverError, ControlProblem, Esolv32Problem {
		double LossImproveFactor, MaxLossImproveFactor;
		int MinLossBus, MinBusPhases;
		String TestBus;

		String CommandString;

		double kVrat, TestGenkW, TestCapkvar;
		int ProgressMax;

		/* Algorithm:
		 *     1) makes a list of buses to check, either
		 *         a. Previously defined list
		 *         b. Meter zone lists
		 *         c. All buses, if neither of the above
		 *     2) Inject a current corresponding to the generator
		 *     3) Check test criteria
		 *     4) Save result
		 *     5) Add generator/capacitor to circuit
		 */
		int Result = 0;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (sol.getLoadModel() == DSSGlobals.ADMITTANCE) {
			sol.setLoadModel(DSSGlobals.POWERFLOW);
			sol.setSystemYChanged(true);  // Force rebuild of System Y without Loads
		}

		/* Do a preliminary snapshot solution to Force definition of meter zones
		 * and set bus lists}
		 */
		Globals.getEnergyMeterClass().resetAll();
		if (sol.isSystemYChanged() || ckt.isBusNameRedefined()) {
			sol.solveSnap();
			ModeChanged = true;
		}

		Globals.getEnergyMeterClass().sampleAll();

		/* Check to see if bus base voltages have been defined. */
		if (ckt.getBuses()[ckt.getNumBuses()].getkVBase() == 0.0)
			sol.setVoltageBases();

		if (ModeChanged) {
			makeBusList();  // Make list of buses to check
			ModeChanged = false;  /* Keep same BusIdxList if no changes */
		}

		sol.setIntervalHrs(1.0);

		/* Start up Log File */
		File F = new File(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "AutoAddLog.csv");
		FileWriter FStream;
		try {
			FStream = new FileWriter(F, false);
			BufferedWriter FLog = new BufferedWriter(FStream);
			FLog.write("\"Bus\", \"Base kV\", \"kW Losses\", \"% Improvement\", \"kW UE\", \"% Improvement\", \"Weighted Total\", \"Iterations\"");
			FLog.newLine();
			FLog.close();  // Close it now after clearing it out
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		// for this solution mode, only the peak load condition is taken into account
		// load is adjusted for growth by year.
		sol.setGeneratorDispRef();

		/* Turn regulators and caps off while we are searching */
		sol.setControlMode(DSSGlobals.CONTROLSOFF);

		setBaseLosses();  /* Establish base values */

		switch (AddType) {
		case DSSGlobals.GENADD:
			if (ckt.isPositiveSequence()) {
				TestGenkW = GenkW / 3.0;
			} else {
				TestGenkW = GenkW;
			}

			if (GenPF != 0.0) {
				Genkvar = TestGenkW * Math.sqrt(1.0 / Math.pow(GenPF, 2) - 1.0);
				if (GenPF < 0.0)
					Genkvar = -Genkvar;
			} else {  // Someone goofed and specified 0.0 PF
				GenPF = 1.0;
				Genkvar = 0.0;
			}

			MinLossBus = 0;   // null string
			MaxLossImproveFactor = -1.0e50;  // Some very large neg number
			MinBusPhases = 3;

			/* Progress meter */
			DSSForms.progressCaption("AutoAdding Generators");
			ProgressMax = BusIdxListSize;
			ProgressCount = 0;

			DSSForms.progressFormCaption(String.format("Testing %d buses. Please Wait... ", BusIdxListSize));
			DSSForms.showPctProgress(0);

			for (int i = 0; i < BusIdxListSize; i++) {

				ProgressCount += 1;

				BusIndex = BusIdxList[i];

				if (BusIndex > 0) {  // TODO Check zero based indexing

					TestBus = ckt.getBusList().get(BusIndex);
					//DSSForms.progressFormCaption("Testing bus" + TestBus);
					if ((ProgressCount % 20 == 0) || (i == BusIdxListSize)) {
						DSSForms.progressFormCaption(String.format("Testing bus %d/%d. ", i, BusIdxListSize));
						DSSForms.showPctProgress((100 * ProgressCount) / ProgressMax);
					}

					Globals.getEnergyMeterClass().resetAll();

					/* Get the Number of Phases at this bus and the Node Ref and add into the Aux Current Array */

					/* Assume either a 3-phase or 1-phase generator */
					if (ckt.getBuses()[BusIndex].getNumNodesThisBus() < 3) {
						Phases = 1;
					} else {
						Phases = 3;
					}

					GenVA = new Complex(1000.0 * TestGenkW/Phases, 1000.0 * Genkvar/Phases) ;

					/* - - - - - - - - - Solution - - - - - - - - - - - - - - - */
					ckt.setIsSolved(false);

					sol.setUseAuxCurrents(true);   // Calls InjCurrents on callback
					sol.solveSnap();

					if (ckt.isSolved()) {
						/* Only do this if solution converged ELSE something might break
						 * in meter sampling}
						 */
						Globals.getEnergyMeterClass().sampleAll();

						LossImproveFactor = getWeightedLosses();

						try {
							FStream = new FileWriter(F, true);  // append
							BufferedWriter FLog = new BufferedWriter(FStream);
							FLog.write(String.format("\"%s\", %-g", TestBus, ckt.getBuses()[BusIndex].getkVBase()*DSSGlobals.SQRT3));
							FLog.write(String.format(", %-g, %-g", kWLosses, puLossImprovement * 100.0));
							FLog.write(String.format(", %-g, %-g", kWEEN, puEENImprovement * 100.0));
							FLog.write(String.format(", %-g, %d", LossImproveFactor, sol.getIteration()));
							FLog.newLine();
							FLog.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (LossImproveFactor > MaxLossImproveFactor) {
							MaxLossImproveFactor = LossImproveFactor;
							MinLossBus = BusIndex;
							MinBusPhases = Phases;
						}

					}
				}
				if (Globals.isSolutionAbort())
					break;
			}

			/* Put Control mode back to default before inserting Generator for real */
			sol.setControlMode(DSSGlobals.CTRLSTATIC);
			sol.setUseAuxCurrents(false);

			if (MinLossBus > 0) {
				Executive exec = DSSExecutive.getInstance();

				if (MinBusPhases >= 3) {
					kVrat = ckt.getBuses()[MinLossBus].getkVBase() * DSSGlobals.SQRT3;
				} else {
					kVrat = ckt.getBuses()[MinLossBus].getkVBase();
				}
				CommandString = "New, generator." + getUniqueGenName() +
						", bus1=\"" + ckt.getBusList().get(MinLossBus) +
						"\", phases=" + String.valueOf(MinBusPhases) +
						", kv="+ String.format("%-g", kVrat) +
						", kw=" + String.format("%-g", TestGenkW) +
						", " + String.format("%5.2f", GenPF) +
						String.format("! Factor =  %-g (%-.3g, %-.3g)", MaxLossImproveFactor, ckt.getLossWeight(), ckt.getUEWeight());
				exec.setCommand(CommandString);    // Defines Generator

				// AppEnd this command to '...AutoAddedGenerators.txt'
				appendToFile("Generators", CommandString);

				sol.solveSnap();  // Force rebuilding of lists

			}
			// Return location of added generator so that it can
			// be picked up through the result string of the COM interface
			Globals.setGlobalResult(ckt.getBusList().get(MinLossBus) +
					String.format(", %-g", MaxLossImproveFactor));

			DSSForms.progressHide();

			// note that the command that added the generator can be
			// picked up from the Command property of the COM interface.

		case DSSGlobals.CAPADD:
			MinLossBus = 0;  // null string
			MaxLossImproveFactor = -1.0e50;  // Some very large number
			MinBusPhases = 3;

			if (ckt.isPositiveSequence()) {
				TestCapkvar = Capkvar / 3.0;
			} else {
				TestCapkvar = Capkvar;
			}

			/* Progress meter */
			DSSForms.progressCaption("AutoAdding Capacitors");
			ProgressMax = BusIdxListSize;
			ProgressCount = 0;

			for (int i = 0; i < BusIdxListSize; i++) {
				ProgressCount += 1;
				/* Make sure testbus is actually in the circuit */
				BusIndex = BusIdxList[i];
				if (BusIndex > 0) {
					TestBus = ckt.getBusList().get(BusIndex);
					DSSForms.progressFormCaption("Testing bus " + TestBus);
					DSSForms.showPctProgress((100 * ProgressCount) / ProgressMax);

					Globals.getEnergyMeterClass().resetAll();

					/* Get the number of phases at this bus and the node ref and add into the aux current array */

					/* Assume either a 3-phase or 1-phase capacitor */
					if (ckt.getBuses()[BusIndex].getNumNodesThisBus() < 3) {
						Phases = 1;
					} else {
						Phases = 3;
					}

					// Apply the capacitor at the bus rating

					kVrat = ckt.getBuses()[BusIndex].getkVBase();  // L-N Base kV
					Ycap = (TestCapkvar * 0.001 / Phases ) / (kVrat * kVrat) ;


					/* - - - - - - - - - Solution - - - - - - - - - - - - - - - */
					ckt.setIsSolved(false);

					sol.setUseAuxCurrents(true);    // Calls InjCurrents on callback
					sol.solveSnap();

					if (ckt.isSolved()) {
						/* Only do this if solution converged ELSE something might break in meter sampling */

						Globals.getEnergyMeterClass().sampleAll();

						LossImproveFactor = getWeightedLosses();

						try {
							FStream = new FileWriter(F, true);  // append
							BufferedWriter FLog = new BufferedWriter(FStream);
							FLog.write(String.format("\"%s\", %-g", TestBus, ckt.getBuses()[BusIndex].getkVBase() * DSSGlobals.SQRT3));
							FLog.write(String.format(", %-g, %-g", kWLosses, puLossImprovement * 100.0));
							FLog.write(String.format(", %-g, %-g", kWEEN, puEENImprovement * 100.0));
							FLog.write(String.format(", %-g, %d", LossImproveFactor, sol.getIteration()));
							FLog.newLine();
							FLog.close();
						} catch (IOException e) {
							// TODO: handle exception
						}

						if (LossImproveFactor > MaxLossImproveFactor) {
							MaxLossImproveFactor = LossImproveFactor;
							MinLossBus = BusIndex;
							MinBusPhases = Phases;
						}
					}
				}
				if (Globals.isSolutionAbort())
					break;
			}


			/* Put Control mode back to default before inserting Capacitor for real */
			sol.setControlMode(DSSGlobals.CTRLSTATIC);
			sol.setUseAuxCurrents(false);

			if (MinLossBus > 0) {
				Executive exec = DSSExecutive.getInstance();

				if (MinBusPhases >= 3) {
					kVrat = ckt.getBuses()[MinLossBus].getkVBase() * DSSGlobals.SQRT3;
				} else {
					kVrat = ckt.getBuses()[MinLossBus].getkVBase();
				}

				CommandString = "New, Capacitor." + getUniqueCapName() +
						", bus1=\"" + ckt.getBusList().get(MinLossBus) +
						"\", phases=" + String.valueOf(MinBusPhases) +
						", kvar=" + String.format("%-g", TestCapkvar) +
						", kv=" + String.format("%-g", kVrat);
				exec.setCommand(CommandString);  // Defines capacitor

				// AppEnd this command to 'DSSAutoAddedCapacitors.txt'
				appendToFile("Capacitors", CommandString);


				sol.solveSnap();  // for rebuilding of lists, etc.

			}
			// Return location of added generator so that it can
			// be picked up through the result string of the COM interface
			Globals.setGlobalResult(ckt.getBusList().get(MinLossBus));

			// note that the command that added the generator can be
			// picked up from the Command property of the COM interface.
		}

		return Result;
	}

	/**
	 * Compute injection currents for generator or capacitor and add into
	 * system currents array.
	 */
	public void addCurrents(int SolveType) {
		Complex BusV;
		int NRef;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		switch (AddType) {
		case DSSGlobals.GENADD:
			/* For buses with voltage <> 0, add into aux current array */
			for (int i = 0; i < Phases; i++) {
				NRef = ckt.getBuses()[BusIndex].getRef(i);
				if (NRef > 0) {  // add in only non-ground currents  TODO Check zero indexing
					BusV = sol.getNodeV()[NRef];
					if ((BusV.getReal() != 0.0) || (BusV.getImaginary() != 0.0)) {
						/* Current  INTO the system network */
						switch (SolveType) {
						case Solution.NEWTONSOLVE:
							// FIXME Implement complex accumulate
							sol.getCurrents()[NRef] = sol.getCurrents()[NRef].add( GenVA.divide(BusV).conjugate().negate() );  // Terminal current
						case Solution.NORMALSOLVE:
							sol.getCurrents()[NRef] = sol.getCurrents()[NRef].add( GenVA.divide(BusV).conjugate() );   // Injection Current
						}
					}
				}
			}

		case DSSGlobals.CAPADD:
			/* For buses with voltage <> 0, add into aux current array */
			for (int i = 0; i < Phases; i++) {
				NRef = ckt.getBuses()[BusIndex].getRef(i);
				if (NRef > 0) {
					BusV = sol.getNodeV()[NRef];
					if ((BusV.getReal() != 0.0) || (BusV.getImaginary() != 0.0)) {
						/* Current  INTO the system network */
						switch (SolveType) {
						case Solution.NEWTONSOLVE:
							sol.getCurrents()[NRef] = sol.getCurrents()[NRef].add( new Complex(0.0, Ycap).multiply(BusV) ); // Terminal Current
						case Solution.NORMALSOLVE:
							sol.getCurrents()[NRef] = sol.getCurrents()[NRef].add( new Complex(0.0, -Ycap).multiply(BusV) );  // Injection Current
						}
					}  // Constant Y model
				}
			}
		}
	}

	private void ComputekWLosses_EEN() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.getEnergyMeters().size() == 0) {

			// No energymeters in circuit
			// Just go by total system losses
			kWLosses = ckt.getLosses().getReal() * 0.001;
			kWEEN = 0.0;

		} else {  // Sum losses in energy meters and add EEN
			kWLosses = 0.0;
			kWEEN = 0.0;

			for (EnergyMeterObj pMeter : ckt.getEnergyMeters()) {
				kWLosses = kWLosses + sumSelectedRegisters(pMeter, ckt.getLossRegs(), ckt.getNumLossRegs());
				kWEEN = kWEEN + sumSelectedRegisters(pMeter, ckt.getUERegs(), ckt.getNumUERegs());
			}
		}
	}

	private void setBaseLosses() {
		ComputekWLosses_EEN();
		BaseLosses = kWLosses;
		BaseEEN = kWEEN;
	}

	public double getGenkW() {
		return GenkW;
	}

	public void setGenkW(double genkW) {
		GenkW = genkW;
	}

	public double getGenPF() {
		return GenPF;
	}

	public void setGenPF(double genPF) {
		GenPF = genPF;
	}

	public double getGenkvar() {
		return Genkvar;
	}

	public void setGenkvar(double genkvar) {
		Genkvar = genkvar;
	}

	public double getCapkvar() {
		return Capkvar;
	}

	public void setCapkvar(double capkvar) {
		Capkvar = capkvar;
	}

	public int getAddType() {
		return AddType;
	}

	public void setAddType(int addType) {
		AddType = addType;
	}

	public boolean isModeChanged() {
		return ModeChanged;
	}

	public void setModeChanged(boolean modeChanged) {
		ModeChanged = modeChanged;
	}

}
