package com.ncond.dss.common.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.AutoAdd;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.Solution;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.conversion.Generator;
import com.ncond.dss.delivery.Capacitor;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.executive.impl.DSSExecutive;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.shared.HashList;
import com.ncond.dss.shared.impl.HashListImpl;

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
	private Complex genVA;

	private double kWLosses, baseLosses, puLossImprovement;
	private double kWEEN , baseEEN, puEENImprovement;

	private File log;  // log file

	private int progressCount;


	/* AutoAdd mode variables */
	protected double genKW, genPF, genKVAr, capKVAr;
	protected int addType;

	protected boolean modeChanged;

	public AutoAddImpl() {

		busIdxListCreated = false;
		generatorClass = (Generator) DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find("generator"));
		capacitorClass = (Capacitor) DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find("capacitor"));

		// autoAdd defaults
		genKW   = 1000.0;
		genPF   = 1.0;
		capKVAr = 600.0;
		addType = DSSGlobals.GENADD;
		lastAddedGenerator = 0;
		lastAddedCapacitor = 0;

		modeChanged = true;
	}

	private static double sumSelectedRegisters(EnergyMeterObj mtr, int[] regs, int count) {
		double result = 0.0;
		for (int i = 0; i < count; i++)
			result += mtr.getRegister(regs[i]) * mtr.getTotalsMask(regs[i]);
		return result;
	}

	public void makeBusList() {
		int retval;
		String bName;
		PDElement PDElem;
		HashList busList;

		Circuit ckt = DSSGlobals.activeCircuit;

		if (busIdxListCreated) busIdxList = new int[0];

		busIdxListCreated = false;

		// autoAddBusList exists in active circuit, use it (see set autoBusList)
		if (ckt.getAutoAddBusList().listSize() > 0) {
			busList = ckt.getAutoAddBusList();
		} else  {
			if (ckt.getEnergyMeters().size() == 0) {
				// no energy meters in circuit
				// include all buses in the circuit
				busIdxListSize = ckt.getBusList().listSize();
				busIdxList = Utilities.resizeArray(busIdxList, busIdxListSize);

				for (int i = 0; i < busIdxListSize; i++)
					busIdxList[i] = i;

				busIdxListCreated = true;
				return;
			} else {
				/* Construct bus list from energy meters zone lists */

				// include only buses in energy meter lists
				// consider all meters
				busList = new HashListImpl(ckt.getNumBuses());

				for (EnergyMeterObj pMeter : ckt.getEnergyMeters()) {
					if (pMeter.getBranchList() != null) {
						PDElem = (PDElement) pMeter.getBranchList().getFirst();  // FIXME: generic tree
						while (PDElem != null) {  // add only unique bus names
							for (int i = 0; i < PDElem.getNTerms(); i++) {
								bName = Utilities.stripExtension(PDElem.getBus(i));

								retval = busList.find(bName);
								if (retval == -1)
									busList.add(bName);  // return value is index of bus
							}
							PDElem = (PDElement) pMeter.getBranchList().goForward();
						}
					}
				}
			}
		}

		// make busIdxList from busList
		busIdxListSize = busList.listSize();
		busIdxList = Utilities.resizeArray(busIdxList, busIdxListSize);

		for (int i = 0; i < busIdxListSize; i++)
			busIdxList[i] = ckt.getBusList().find(busList.get(i));

		busIdxListCreated = true;
	}

	public double getWeightedLosses() {
		double losses;
		Circuit ckt = DSSGlobals.activeCircuit;

		computekWLosses_EEN();

		if (ckt.getEnergyMeters().size() == 0) {
			// no energymeters in circuit
			// just go by total system losses
			puLossImprovement = (baseLosses - kWLosses) / genKW;
			puEENImprovement = 0.0;
			losses = puLossImprovement;
		} else {
			puLossImprovement = (baseLosses - kWLosses) / genKW;
			puEENImprovement = (baseEEN - kWEEN) / genKW;
			losses = ckt.getLossWeight() * puLossImprovement + ckt.getUEWeight() * puEENImprovement;
		}
		return losses;
	}

	public void appendToFile(String fileName, String s) {
		String fName = DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + "AutoAdded" + fileName + ".txt";

		try {
			FileWriter fw = new FileWriter(fName, true);
			PrintWriter pw = new PrintWriter(fw);

			pw.println(s);

			fw.close();
			pw.close();
		} catch (IOException e) {
			DSSGlobals.doSimpleMsg("Error trying to append to " + fName +
					DSSGlobals.CRLF + e.getMessage(), 438);
		}
	}

	private String getUniqueGenName() {
		String trialName = "";
		boolean done = false;

		while (!done) {
			done = true;
			lastAddedGenerator += 1;
			trialName = "Gadd" + String.valueOf(lastAddedGenerator);
			if (generatorClass.find(trialName) != null) done = false;
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
			if (capacitorClass.find(trialName) != null) done = false;
		}

		return trialName;
	}

	public int solve() throws SolverError, ControlProblem, Esolv32Problem {

		double lossImproveFactor, maxLossImproveFactor;
		int minLossBus, minBusPhases;
		String testBus;

		String commandString;

		double kVrat, testGenKW, testCapKVAr;
		int progressMax;

		Executive exec;
		FileWriter fw;
		PrintWriter pw;

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

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (sol.getLoadModel() == DSSGlobals.ADMITTANCE) {
			sol.setLoadModel(DSSGlobals.POWERFLOW);
			sol.setSystemYChanged(true);  // force rebuild of system Y without loads
		}

		/* Do a preliminary snapshot solution to force definition of meter zones
		 * and set bus lists
		 */
		DSSGlobals.energyMeterClass.resetAll();
		if (sol.isSystemYChanged() || ckt.isBusNameRedefined()) {
			sol.solveSnap();
			modeChanged = true;
		}

		DSSGlobals.energyMeterClass.sampleAll();

		/* Check to see if bus base voltages have been defined. */
		if (ckt.getBus(ckt.getNumBuses()).getKVBase() == 0.0)
			sol.setVoltageBases();

		if (modeChanged) {
			makeBusList();  // make list of buses to check
			modeChanged = false;  /* Keep same busIdxList if no changes */
		}

		sol.setIntervalHrs(1.0);

		/* Start up Log File */
		log = new File(DSSGlobals.DSSDataDirectory + DSSGlobals.circuitName_ + "AutoAddLog.csv");
		try {
			fw = new FileWriter(log, false);
			pw = new PrintWriter(fw);
			pw.println("\"Bus\", \"Base kV\", \"kW Losses\", \"% Improvement\", \"kW UE\", \"% Improvement\", \"Weighted Total\", \"Iterations\"");
			fw.close();  // close it now after clearing it out
			pw.close();
		} catch (IOException e1) {
			DSSGlobals.doSimpleMsg("Error writing AutoAdd log file: " + e1.getMessage(), 0);
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
			DSSGlobals.forms.progressCaption("AutoAdding Generators");
			progressMax = busIdxListSize;
			progressCount = 0;

			DSSGlobals.forms.progressFormCaption(String.format("Testing %d buses. Please Wait... ", busIdxListSize));
			DSSGlobals.forms.showPctProgress(0);

			for (int i = 0; i < busIdxListSize; i++) {
				progressCount += 1;
				busIndex = busIdxList[i];

				if (busIndex >= 0) {
					testBus = ckt.getBusList().get(busIndex);

					//DSSGlobals.forms.progressFormCaption("Testing bus" + TestBus);
					if ((progressCount % 20 == 0) || (i == busIdxListSize)) {
						DSSGlobals.forms.progressFormCaption(String.format("Testing bus %d/%d. ", i, busIdxListSize));
						DSSGlobals.forms.showPctProgress((100 * progressCount) / progressMax);
					}

					DSSGlobals.energyMeterClass.resetAll();

					/* Get the number of phases at this bus and the node ref and add into the aux current array */

					/* Assume either a 3-phase or 1-phase generator */
					phases = (ckt.getBus(busIndex).getNumNodesThisBus() < 3) ? 1 : 3;

					genVA = new Complex(1000.0 * testGenKW/phases, 1000.0 * genKVAr/phases) ;

					/* - - - - - - - - - Solution - - - - - - - - - - - - - - - */
					ckt.setIsSolved(false);

					sol.setUseAuxCurrents(true);  // calls injCurrents on callback
					sol.solveSnap();

					if (ckt.isSolved()) {
						/* Only do this if solution converged else something might break
						 * in meter sampling.
						 */
						DSSGlobals.energyMeterClass.sampleAll();

						lossImproveFactor = getWeightedLosses();

						try {
							fw = new FileWriter(log, true);  // append
							pw = new PrintWriter(fw);

							pw.printf("\"%s\", %-g", testBus, ckt.getBus(busIndex).getKVBase() * DSSGlobals.SQRT3);
							pw.printf(", %-g, %-g", kWLosses, puLossImprovement * 100.0);
							pw.printf(", %-g, %-g", kWEEN, puEENImprovement * 100.0);
							pw.printf(", %-g, %d", lossImproveFactor, sol.getIteration());
							pw.println();

							fw.close();
							pw.close();
						} catch (IOException e2) {
							DSSGlobals.doSimpleMsg("Error writing AutoAdd log file: " + e2.getMessage(), 0);
						}

						if (lossImproveFactor > maxLossImproveFactor) {
							maxLossImproveFactor = lossImproveFactor;
							minLossBus = busIndex;
							minBusPhases = phases;
						}
					}
				}

				if (DSSGlobals.solutionAbort) break;
			}

			/* Put control mode back to default before inserting generator for real */
			sol.setControlMode(DSSGlobals.CTRLSTATIC);
			sol.setUseAuxCurrents(false);

			if (minLossBus > 0) {
				exec = DSSExecutive.getInstance();

				if (minBusPhases >= 3) {
					kVrat = ckt.getBus(minLossBus).getKVBase() * DSSGlobals.SQRT3;
				} else {
					kVrat = ckt.getBus(minLossBus).getKVBase();
				}

				commandString = "new, generator." + getUniqueGenName() +
						", bus1=\"" + ckt.getBusList().get(minLossBus) +
						"\", phases=" + String.valueOf(minBusPhases) +
						", kv="+ String.format("%-g", kVrat) +
						", kw=" + String.format("%-g", testGenKW) +
						", " + String.format("%5.2f", genPF) +
						String.format("! Factor =  %-g (%-.3g, %-.3g)",
								maxLossImproveFactor,
								ckt.getLossWeight(),
								ckt.getUEWeight());
				exec.setCommand(commandString);  // defines generator

				// append this command to '...AutoAddedGenerators.txt'
				appendToFile("Generators", commandString);

				sol.solveSnap();  // force rebuilding of lists
			}

			// return location of added generator
			DSSGlobals.globalResult = ckt.getBusList().get(minLossBus) +
					String.format(", %-g", maxLossImproveFactor);

			DSSGlobals.forms.progressHide();

			break;
		case DSSGlobals.CAPADD:
			minLossBus = 0;  // null string
			maxLossImproveFactor = -1.0e50;  // very large negative number
			minBusPhases = 3;

			testCapKVAr = ckt.isPositiveSequence() ? capKVAr / 3.0 : capKVAr;

			/* Progress meter */
			DSSGlobals.forms.progressCaption("AutoAdding Capacitors");
			progressMax = busIdxListSize;
			progressCount = 0;

			for (int i = 0; i < busIdxListSize; i++) {
				progressCount += 1;
				/* Make sure test bus is actually in the circuit */
				busIndex = busIdxList[i];
				if (busIndex >= 0) {
					testBus = ckt.getBusList().get(busIndex);
					DSSGlobals.forms.progressFormCaption("Testing bus " + testBus);
					DSSGlobals.forms.showPctProgress((100 * progressCount) / progressMax);

					DSSGlobals.energyMeterClass.resetAll();

					/* Get the number of phases at this bus and the node ref and add
					 * into the aux current array */

					/* Assume either a 3-phase or 1-phase capacitor */
					phases = (ckt.getBus(busIndex).getNumNodesThisBus() < 3) ? 1 : 3;

					// apply the capacitor at the bus rating

					kVrat = ckt.getBus(busIndex).getKVBase();  // L-N Base kV
					Ycap = (testCapKVAr * 0.001 / phases ) / (kVrat * kVrat) ;


					/* - - - - - - - - - Solution - - - - - - - - - - - - - - - */
					ckt.setIsSolved(false);

					sol.setUseAuxCurrents(true);  // calls injCurrents on callback
					sol.solveSnap();

					if (ckt.isSolved()) {
						/* Only do this if solution converged else something might
						 * break in meter sampling */

						DSSGlobals.energyMeterClass.sampleAll();

						lossImproveFactor = getWeightedLosses();

						try {
							fw = new FileWriter(log, true);  // append
							pw = new PrintWriter(fw);
							pw.printf("\"%s\", %-g", testBus, ckt.getBus(busIndex).getKVBase() * DSSGlobals.SQRT3);
							pw.printf(", %-g, %-g", kWLosses, puLossImprovement * 100.0);
							pw.printf(", %-g, %-g", kWEEN, puEENImprovement * 100.0);
							pw.printf(", %-g, %d", lossImproveFactor, sol.getIteration());
							pw.println();
							fw.close();
							pw.close();
						} catch (IOException e3) {
							DSSGlobals.doSimpleMsg("Error writing AutoAdd log file: " + e3.getMessage(), 0);
						}

						if (lossImproveFactor > maxLossImproveFactor) {
							maxLossImproveFactor = lossImproveFactor;
							minLossBus = busIndex;
							minBusPhases = phases;
						}
					}
				}

				if (DSSGlobals.solutionAbort) break;
			}

			/* Put control mode back to default before inserting capacitor for real */
			sol.setControlMode(DSSGlobals.CTRLSTATIC);
			sol.setUseAuxCurrents(false);

			if (minLossBus > 0) {
				exec = DSSExecutive.getInstance();

				if (minBusPhases >= 3) {
					kVrat = ckt.getBus(minLossBus).getKVBase() * DSSGlobals.SQRT3;
				} else {
					kVrat = ckt.getBus(minLossBus).getKVBase();
				}

				commandString = "new, capacitor." + getUniqueCapName() +
						", bus1=\"" + ckt.getBusList().get(minLossBus) +
						"\", phases=" + String.valueOf(minBusPhases) +
						", kvar=" + String.format("%-g", testCapKVAr) +
						", kv=" + String.format("%-g", kVrat);
				exec.setCommand(commandString);  // defines capacitor

				// append this command to 'DSSAutoAddedCapacitors.txt'
				appendToFile("Capacitors", commandString);

				sol.solveSnap();  // for rebuilding of lists, etc.
			}
			// return location of added generator
			DSSGlobals.globalResult = ckt.getBusList().get(minLossBus);

			break;
		}

		return result;
	}

	public void addCurrents(int solveType) {
		Complex busV;
		int nRef;

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		switch (addType) {
		case DSSGlobals.GENADD:
			/* For buses with voltage != 0, add into aux current array */
			for (int i = 0; i < phases; i++) {
				nRef = ckt.getBus(busIndex).getRef(i);
				if (nRef > 0) {  // add in only non-ground currents  TODO Check zero indexing
					busV = sol.getNodeV(nRef);
					if ((busV.getReal() != 0.0) || (busV.getImaginary() != 0.0)) {
						/* Current into the system network */
						switch (solveType) {
						case Solution.NEWTONSOLVE:
							// FIXME Implement complex accumulate
							sol.setCurrent(nRef, sol.getCurrent(nRef).add( genVA.divide(busV).conjugate().negate() ));  // terminal current
							break;
						case Solution.NORMALSOLVE:
							sol.setCurrent(nRef, sol.getCurrent(nRef).add( genVA.divide(busV).conjugate() ));  // injection Current
							break;
						}
					}
				}
			}

			break;
		case DSSGlobals.CAPADD:
			/* For buses with voltage != 0, add into aux current array */
			for (int i = 0; i < phases; i++) {
				nRef = ckt.getBus(busIndex).getRef(i);
				if (nRef >= 0) {
					busV = sol.getNodeV(nRef);
					if (busV.getReal() != 0.0 || busV.getImaginary() != 0.0) {
						/* Current into the system network */
						switch (solveType) {
						case Solution.NEWTONSOLVE:
							sol.setCurrent(nRef, sol.getCurrent(nRef).add( new Complex(0.0, Ycap).multiply(busV) ));  // terminal current
							break;
						case Solution.NORMALSOLVE:
							sol.setCurrent(nRef, sol.getCurrent(nRef).add( new Complex(0.0, -Ycap).multiply(busV) ));  // injection current
							break;
						}
					}  // constant Y model
				}
			}
			break;
		}
	}

	private void computekWLosses_EEN() {
		Circuit ckt = DSSGlobals.activeCircuit;

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
		computekWLosses_EEN();
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
