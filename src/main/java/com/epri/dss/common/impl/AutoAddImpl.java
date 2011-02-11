package com.epri.dss.common.impl;

import java.io.PrintStream;

import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.HashListImpl;

import com.epri.dss.common.AutoAdd;
import com.epri.dss.common.Circuit;
import com.epri.dss.conversion.Generator;
import com.epri.dss.delivery.Capacitor;
import com.epri.dss.delivery.PDElement;

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
			if (Globals.getActiveCircuit().getEnergyMeters().listSize() = 0) {
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
						PDElem = pMeter.BranchList.getFirst();
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
	private double getWeightedLosses() {
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

	}

	public void addCurrents(int SolveType) {

	}

	private void ComputekWLosses_EEN() {

	}

	private void setBaseLosses() {

	}

	private String getUniqueGenName() {
		return null;
	}

	private String getUniqueCapName() {
		return null;
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
