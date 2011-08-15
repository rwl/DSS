package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.shared.HashList;
import org.apache.commons.math.complex.Complex;
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
 * Unit for processing the AutoAdd solution functions.
 *
 * Note: Make sure this class in instantiated after EnergyMeter class.
 *
 * There is one of these per circuit.
 *
 */
public class AutoAddImpl implements AutoAdd {

	private Generator generatorClass;
	private Capacitor capacitorClass;

	private int[] busIdxList;
	private int busIdxListSize;
	private boolean busIdxListCreated;
	private int lastAddedGenerator;
	private int lastAddedCapacitor;

	private int busIndex;
	private int phases;

	private double Ycap;
	private Complex GenVA;

	private double kWLosses, baseLosses, puLossImprovement;
	private double kWEEN , baseEEN, puEENImprovement;

	private PrintStream log;  // log file

	private int progressCount;


	/* AutoAdd mode variables */
	protected double genKW, genPF, genKVAr, capKVAr;
	protected int addType;

	protected boolean modeChanged;

	private static double sumSelectedRegisters(EnergyMeterObj mtr, int[] regs, int count) {
		double result = 0.0;
		for (int i = 0; i < count; i++) {
			result += mtr.getRegisters()[regs[i]] * mtr.getTotalsMask()[regs[i]];
		}
		return result;
	}

	public AutoAddImpl() {
		DSSGlobals globals = DSSGlobals.getInstance();

		busIdxListCreated = false;
		generatorClass = (Generator) globals.getDSSClassList().get(globals.getClassNames().find("generator"));
		capacitorClass = (Capacitor) globals.getDSSClassList().get(globals.getClassNames().find("capacitor"));

		// autoAdd defaults
		genKW   = 1000.0;
		genPF   = 1.0;
		capKVAr = 600.0;
		addType = DSSGlobals.GENADD;
		lastAddedGenerator = 0;
		lastAddedCapacitor = 0;

		modeChanged = true;
	}

	/**
	 * Make a list of unique bus names.
	 * If autoAddBusList in activeCircuit is not nil, use this list.
	 * Else, use the element lists in energy meters.
	 * If no energy meters, use all the buses in the active circuit.
	 */
	public void makeBusList() {
		int retval;
		String bName;
		PDElement PDElem;
		HashList busList;
		boolean busListCreatedHere;

		if (busIdxListCreated)
			busIdxList = new int[0];

		busListCreatedHere = false;
		busIdxListCreated = false;

		DSSGlobals globals = DSSGlobals.getInstance();

		// autoAddBusList exists in active circuit, use it (see set autoBusList)
		if (globals.getActiveCircuit().getAutoAddBusList().listSize() > 0) {
			busList = globals.getActiveCircuit().getAutoAddBusList();
		} else  {
			if (globals.getActiveCircuit().getEnergyMeters().size() == 0) {
				// no energy meters in circuit
				// include all buses in the circuit
				busIdxListSize = globals.getActiveCircuit().getBusList().listSize();
				busIdxList = (int[]) Utilities.resizeArray(busIdxList, busIdxListSize);

				for (int i = 0; i < busIdxListSize; i++)
					busIdxList[i] = i;

				busIdxListCreated = true;
				return;
			} else {
				/* Construct bus list from energy meters zone lists */
				// include only buses in energy meter lists
				// consider all meters
				busListCreatedHere = true;
				busList = new HashListImpl(globals.getActiveCircuit().getNumBuses());
				for (EnergyMeterObj pMeter : globals.getActiveCircuit().getEnergyMeters()) {
					if (pMeter.getBranchList() != null) {
						PDElem = (PDElement) pMeter.getBranchList().getFirst();
						while (PDElem != null) {  // add only unique bus names
							for (int i = 0; i < PDElem.getNTerms(); i++) {
								bName = Utilities.stripExtension(PDElem.getBus(i));
								retval = busList.find(bName);
								if (retval == -1) {  // TODO Check zero based indexing
									busList.add(bName);  // return value is index of bus
								}
							}
							PDElem = (PDElement) pMeter.getBranchList().goForward();
						}
					}
				}
			}
		}

		// make busIdxList from busList
		busIdxListSize = busList.listSize();
		busIdxList = (int[]) Utilities.resizeArray(busIdxList, busIdxListSize);

		for (int i = 0; i < busIdxListSize; i++) {
			busIdxList[i] = globals.getActiveCircuit().getBusList().find(busList.get(i));
		}

		if (busListCreatedHere)
			busList = null;
		busIdxListCreated = true;
	}

	/**
	 * Returns losses in metered part of circuit + weighted EEN values.
	 *
	 * If no meters, returns just total losses in circuit.
	 *
	 * Base everything on gen kW.
	 */
	public double getWeightedLosses() {
		double result;

		ComputekWLosses_EEN();

		if (DSSGlobals.getInstance().getActiveCircuit().getEnergyMeters().size() == 0) {
			// no energymeters in circuit
			// just go by total system losses
			puLossImprovement = (baseLosses - kWLosses) / genKW;
			puEENImprovement = 0.0;
			result = puLossImprovement;
		} else {
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			puLossImprovement = (baseLosses - kWLosses) / genKW;
			puEENImprovement = (baseEEN - kWEEN)/genKW;
			result = ckt.getLossWeight() * puLossImprovement + ckt.getUEWeight() * puEENImprovement;
		}
		return result;
	}

	public void appendToFile(String whichFile, String s) {
		// FIXME Implement this method.
		throw new UnsupportedOperationException();
	}

	private String getUniqueGenName() {
		String trialName = "";
		boolean done = false;

		while (!done) {
			done = true;
			lastAddedGenerator += 1;
			trialName = "Gadd" + String.valueOf(lastAddedGenerator);
			if (generatorClass.find(trialName) != null)
				done = false;
		}

		return trialName;
	}

	private String getUniqueCapName() {
		String trialName = "";
		boolean done = false;

		while (!done) {
			done = true;
			lastAddedCapacitor += 1;
			trialName = "Cadd" + String.valueOf(lastAddedCapacitor);
			if (capacitorClass.find(trialName) != null)
				done = false;
		}

		return trialName;
	}

	/**
	 * Automatically add caps or generators.
	 *
	 * Automatically add a specified size of generator or capacitor at the location
	 * that results in the lowest losses in either metered part of circuit or
	 * total circuit, if no meters.
	 *
	 * If metered, EEN is also added in with a selected weighting factor (see
	 * set ueweight= ... command).
	 *
	 * Thus, this algorithm placed generators and capacitors to minimize losses and
	 * potential unserved energy.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public int solve() throws SolverError, ControlProblem, Esolv32Problem {
		double lossImproveFactor, maxLossImproveFactor;
		int minLossBus, minBusPhases;
		String testBus;

		String commandString;

		double kVrat, testGenKW, testCapKVAr;
		int progressMax;

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
		int result = 0;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (sol.getLoadModel() == DSSGlobals.ADMITTANCE) {
			sol.setLoadModel(DSSGlobals.POWERFLOW);
			sol.setSystemYChanged(true);  // force rebuild of system Y without loads
		}

		/* Do a preliminary snapshot solution to force definition of meter zones
		 * and set bus lists
		 */
		globals.getEnergyMeterClass().resetAll();
		if (sol.isSystemYChanged() || ckt.isBusNameRedefined()) {
			sol.solveSnap();
			modeChanged = true;
		}

		globals.getEnergyMeterClass().sampleAll();

		/* Check to see if bus base voltages have been defined. */
		if (ckt.getBuses()[ckt.getNumBuses()].getKVBase() == 0.0)
			sol.setVoltageBases();

		if (modeChanged) {
			makeBusList();  // make list of buses to check
			modeChanged = false;  /* Keep same busIdxList if no changes */
		}

		sol.setIntervalHrs(1.0);

		/* Start up Log File */
		File F = new File(globals.getDSSDataDirectory() + globals.getCircuitName_() + "AutoAddLog.csv");
		FileWriter FStream;
		try {
			FStream = new FileWriter(F, false);
			BufferedWriter FLog = new BufferedWriter(FStream);
			FLog.write("\"Bus\", \"Base kV\", \"kW Losses\", \"% Improvement\", \"kW UE\", \"% Improvement\", \"Weighted Total\", \"Iterations\"");
			FLog.newLine();
			FLog.close();  // close it now after clearing it out
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

		switch (addType) {
		case DSSGlobals.GENADD:
			if (ckt.isPositiveSequence()) {
				testGenKW = genKW / 3.0;
			} else {
				testGenKW = genKW;
			}

			if (genPF != 0.0) {
				genKVAr = testGenKW * Math.sqrt(1.0 / Math.pow(genPF, 2) - 1.0);
				if (genPF < 0.0)
					genKVAr = -genKVAr;
			} else {  // Someone specified 0.0 PF
				genPF = 1.0;
				genKVAr = 0.0;
			}

			minLossBus = 0;  // null string
			maxLossImproveFactor = -1.0e50;  // very large negative number
			minBusPhases = 3;

			/* Progress meter */
			globals.getDSSForms().progressCaption("AutoAdding Generators");
			progressMax = busIdxListSize;
			progressCount = 0;

			globals.getDSSForms().progressFormCaption(String.format("Testing %d buses. Please Wait... ", busIdxListSize));
			globals.getDSSForms().showPctProgress(0);

			for (int i = 0; i < busIdxListSize; i++) {

				progressCount += 1;

				busIndex = busIdxList[i];

				if (busIndex > 0) {  // TODO Check zero based indexing

					testBus = ckt.getBusList().get(busIndex);
					//DSSForms.progressFormCaption("Testing bus" + TestBus);
					if ((progressCount % 20 == 0) || (i == busIdxListSize)) {
						globals.getDSSForms().progressFormCaption(String.format("Testing bus %d/%d. ", i, busIdxListSize));
						globals.getDSSForms().showPctProgress((100 * progressCount) / progressMax);
					}

					globals.getEnergyMeterClass().resetAll();

					/* Get the number of phases at this bus and the node ref and add into the aux current array */

					/* Assume either a 3-phase or 1-phase generator */
					if (ckt.getBuses()[busIndex].getNumNodesThisBus() < 3) {
						phases = 1;
					} else {
						phases = 3;
					}

					GenVA = new Complex(1000.0 * testGenKW/phases, 1000.0 * genKVAr/phases) ;

					/* - - - - - - - - - Solution - - - - - - - - - - - - - - - */
					ckt.setIsSolved(false);

					sol.setUseAuxCurrents(true);  // calls injCurrents on callback
					sol.solveSnap();

					if (ckt.isSolved()) {
						/* Only do this if solution converged else something might break
						 * in meter sampling.
						 */
						globals.getEnergyMeterClass().sampleAll();

						lossImproveFactor = getWeightedLosses();

						try {
							FStream = new FileWriter(F, true);  // append
							BufferedWriter FLog = new BufferedWriter(FStream);
							FLog.write(String.format("\"%s\", %-g", testBus, ckt.getBuses()[busIndex].getKVBase()*DSSGlobals.SQRT3));
							FLog.write(String.format(", %-g, %-g", kWLosses, puLossImprovement * 100.0));
							FLog.write(String.format(", %-g, %-g", kWEEN, puEENImprovement * 100.0));
							FLog.write(String.format(", %-g, %d", lossImproveFactor, sol.getIteration()));
							FLog.newLine();
							FLog.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (lossImproveFactor > maxLossImproveFactor) {
							maxLossImproveFactor = lossImproveFactor;
							minLossBus = busIndex;
							minBusPhases = phases;
						}

					}
				}
				if (globals.isSolutionAbort())
					break;
			}

			/* Put control mode back to default before inserting generator for real */
			sol.setControlMode(DSSGlobals.CTRLSTATIC);
			sol.setUseAuxCurrents(false);

			if (minLossBus > 0) {
				Executive exec = DSSExecutive.getInstance();

				if (minBusPhases >= 3) {
					kVrat = ckt.getBuses()[minLossBus].getKVBase() * DSSGlobals.SQRT3;
				} else {
					kVrat = ckt.getBuses()[minLossBus].getKVBase();
				}
				commandString = "New, generator." + getUniqueGenName() +
						", bus1=\"" + ckt.getBusList().get(minLossBus) +
						"\", phases=" + String.valueOf(minBusPhases) +
						", kv="+ String.format("%-g", kVrat) +
						", kw=" + String.format("%-g", testGenKW) +
						", " + String.format("%5.2f", genPF) +
						String.format("! Factor =  %-g (%-.3g, %-.3g)", maxLossImproveFactor, ckt.getLossWeight(), ckt.getUEWeight());
				exec.setCommand(commandString);  // defines generator

				// append this command to '...AutoAddedGenerators.txt'
				appendToFile("Generators", commandString);

				sol.solveSnap();  // force rebuilding of lists
			}
			// return location of added generator
			globals.setGlobalResult(ckt.getBusList().get(minLossBus) +
					String.format(", %-g", maxLossImproveFactor));

			globals.getDSSForms().progressHide();

			break;
		case DSSGlobals.CAPADD:
			minLossBus = 0;  // null string
			maxLossImproveFactor = -1.0e50;  // very large negative number
			minBusPhases = 3;

			if (ckt.isPositiveSequence()) {
				testCapKVAr = capKVAr / 3.0;
			} else {
				testCapKVAr = capKVAr;
			}

			/* Progress meter */
			globals.getDSSForms().progressCaption("AutoAdding Capacitors");
			progressMax = busIdxListSize;
			progressCount = 0;

			for (int i = 0; i < busIdxListSize; i++) {
				progressCount += 1;
				/* Make sure test bus is actually in the circuit */
				busIndex = busIdxList[i];
				if (busIndex > 0) {
					testBus = ckt.getBusList().get(busIndex);
					globals.getDSSForms().progressFormCaption("Testing bus " + testBus);
					globals.getDSSForms().showPctProgress((100 * progressCount) / progressMax);

					globals.getEnergyMeterClass().resetAll();

					/* Get the number of phases at this bus and the node ref and add into the aux current array */

					/* Assume either a 3-phase or 1-phase capacitor */
					if (ckt.getBuses()[busIndex].getNumNodesThisBus() < 3) {
						phases = 1;
					} else {
						phases = 3;
					}

					// apply the capacitor at the bus rating

					kVrat = ckt.getBuses()[busIndex].getKVBase();  // L-N Base kV
					Ycap = (testCapKVAr * 0.001 / phases ) / (kVrat * kVrat) ;


					/* - - - - - - - - - Solution - - - - - - - - - - - - - - - */
					ckt.setIsSolved(false);

					sol.setUseAuxCurrents(true);  // calls injCurrents on callback
					sol.solveSnap();

					if (ckt.isSolved()) {
						/* Only do this if solution converged else something might break in meter sampling */

						globals.getEnergyMeterClass().sampleAll();

						lossImproveFactor = getWeightedLosses();

						try {
							FStream = new FileWriter(F, true);  // append
							BufferedWriter FLog = new BufferedWriter(FStream);
							FLog.write(String.format("\"%s\", %-g", testBus, ckt.getBuses()[busIndex].getKVBase() * DSSGlobals.SQRT3));
							FLog.write(String.format(", %-g, %-g", kWLosses, puLossImprovement * 100.0));
							FLog.write(String.format(", %-g, %-g", kWEEN, puEENImprovement * 100.0));
							FLog.write(String.format(", %-g, %d", lossImproveFactor, sol.getIteration()));
							FLog.newLine();
							FLog.close();
						} catch (IOException e) {
							// TODO: handle exception
						}

						if (lossImproveFactor > maxLossImproveFactor) {
							maxLossImproveFactor = lossImproveFactor;
							minLossBus = busIndex;
							minBusPhases = phases;
						}
					}
				}
				if (globals.isSolutionAbort())
					break;
			}

			/* Put control mode back to default before inserting capacitor for real */
			sol.setControlMode(DSSGlobals.CTRLSTATIC);
			sol.setUseAuxCurrents(false);

			if (minLossBus > 0) {
				Executive exec = DSSExecutive.getInstance();

				if (minBusPhases >= 3) {
					kVrat = ckt.getBuses()[minLossBus].getKVBase() * DSSGlobals.SQRT3;
				} else {
					kVrat = ckt.getBuses()[minLossBus].getKVBase();
				}

				commandString = "New, Capacitor." + getUniqueCapName() +
						", bus1=\"" + ckt.getBusList().get(minLossBus) +
						"\", phases=" + String.valueOf(minBusPhases) +
						", kvar=" + String.format("%-g", testCapKVAr) +
						", kv=" + String.format("%-g", kVrat);
				exec.setCommand(commandString);  // Defines capacitor

				// append this command to 'DSSAutoAddedCapacitors.txt'
				appendToFile("Capacitors", commandString);

				sol.solveSnap();  // for rebuilding of lists, etc.
			}
			// return location of added generator
			globals.setGlobalResult(ckt.getBusList().get(minLossBus));

			break;
		}

		return result;
	}

	/**
	 * Compute injection currents for generator or capacitor and add into
	 * system currents array.
	 */
	public void addCurrents(int solveType) {
		Complex busV;
		int nRef;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		switch (addType) {
		case DSSGlobals.GENADD:
			/* For buses with voltage != 0, add into aux current array */
			for (int i = 0; i < phases; i++) {
				nRef = ckt.getBuses()[busIndex].getRef(i);
				if (nRef > 0) {  // add in only non-ground currents  TODO Check zero indexing
					busV = sol.getNodeV()[nRef];
					if ((busV.getReal() != 0.0) || (busV.getImaginary() != 0.0)) {
						/* Current into the system network */
						switch (solveType) {
						case Solution.NEWTONSOLVE:
							// FIXME Implement complex accumulate
							sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add( GenVA.divide(busV).conjugate().negate() );  // terminal current
							break;
						case Solution.NORMALSOLVE:
							sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add( GenVA.divide(busV).conjugate() );  // injection Current
							break;
						}
					}
				}
			}

			break;
		case DSSGlobals.CAPADD:
			/* For buses with voltage != 0, add into aux current array */
			for (int i = 0; i < phases; i++) {
				nRef = ckt.getBuses()[busIndex].getRef(i);
				if (nRef > 0) {
					busV = sol.getNodeV()[nRef];
					if ((busV.getReal() != 0.0) || (busV.getImaginary() != 0.0)) {
						/* Current into the system network */
						switch (solveType) {
						case Solution.NEWTONSOLVE:
							sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add( new Complex(0.0, Ycap).multiply(busV) );  // terminal current
							break;
						case Solution.NORMALSOLVE:
							sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add( new Complex(0.0, -Ycap).multiply(busV) );  // injection current
							break;
						}
					}  // constant Y model
				}
			}
			break;
		}
	}

	private void ComputekWLosses_EEN() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.getEnergyMeters().size() == 0) {

			// no energymeters in circuit
			// just go by total system losses
			kWLosses = ckt.getLosses().getReal() * 0.001;
			kWEEN = 0.0;

		} else {  // sum losses in energy meters and add EEN
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
		baseLosses = kWLosses;
		baseEEN = kWEEN;
	}

	public double getGenKW() {
		return genKW;
	}

	public void setGenKW(double genkW) {
		genKW = genkW;
	}

	public double getGenPF() {
		return genPF;
	}

	public void setGenPF(double genpf) {
		genPF = genpf;
	}

	public double getGenKVAr() {
		return genKVAr;
	}

	public void setGenKVAr(double genkvar) {
		genKVAr = genkvar;
	}

	public double getCapKVAr() {
		return capKVAr;
	}

	public void setCapKVAr(double capkvar) {
		capKVAr = capkvar;
	}

	public int getAddType() {
		return addType;
	}

	public void setAddType(int addtype) {
		addType = addtype;
	}

	public boolean isModeChanged() {
		return modeChanged;
	}

	public void setModeChanged(boolean modeChanged) {
		this.modeChanged = modeChanged;
	}

}
