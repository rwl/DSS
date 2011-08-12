package com.epri.dss.common.impl;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Bus;
import com.epri.dss.common.impl.DSSBus.NodeBus;
import com.epri.dss.common.AutoAdd;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.CktElementClass;
import com.epri.dss.common.ControlQueue;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.FeederObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.control.CapControlObj;
import com.epri.dss.control.ControlElem;
import com.epri.dss.control.RegControlObj;
import com.epri.dss.control.SwtControlObj;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.conversion.PVSystemObj;
import com.epri.dss.conversion.StorageObj;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.PriceShapeObj;
import com.epri.dss.general.impl.NamedObjectImpl;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.meter.MeterElement;
import com.epri.dss.meter.MonitorObj;
import com.epri.dss.meter.SensorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.impl.CktTreeImpl;
import com.epri.dss.shared.impl.HashListImpl;

public class DSSCircuit extends NamedObjectImpl implements Circuit {

	public enum ReductionStrategyType {
		rsDefault,
		rsStubs,
		rsTapEnds,
		rsMergeParallel,
		rsBreakLoop,
		rsDangling,
		rsSwitches
	}

	public class CktElementDef {
		public int CktElementClass;
		public int devHandle;
	}

	private int[] NodeBuffer;
	private int NodeBufferMax;
	private boolean BusNameRedefined;
	private CktElement ActiveCktElement;

	// temp arrays for when the bus swap takes place
	private Bus[] SavedBuses;
	private String[] SavedBusNames;
	private int SavedNumBuses;

	/* Global multiplier for every load */
	private double LoadMultiplier;

	private boolean AbortBusProcess;

	/* Topology from the first source, lazy evaluation */
	private CktTree Branch_List;
	/* Bus adjacency lists of PD and PC elements */
	private List<PCElement>[] BusAdjPC;
	private List<PDElement>[] BusAdjPD;

	private String CaseName;

	protected int ActiveBusIndex;
	/* Fundamental and default base frequency */
	protected double Fundamental;

	/* Flag for use by control elements to detect redefinition of buses */
	protected boolean Control_BusNameRedefined;

	protected HashList BusList;
	protected HashList AutoAddBusList;
	protected HashList DeviceList;
	protected CktElementDef[] DeviceRef;  // type and handle of device

	// lists of pointers to different elements by class
	protected ArrayList<FaultObj> Faults;
	protected ArrayList<CktElement> CktElements;
	protected ArrayList<PDElement> PDElements;
	protected ArrayList<PCElement> PCElements;
	protected ArrayList<ControlElem> DSSControls;
	protected ArrayList<PCElement> Sources;
	protected ArrayList<MeterElement> MeterElements;
	protected ArrayList<SensorObj> Sensors;
	protected ArrayList<MonitorObj> Monitors;
	protected ArrayList<EnergyMeterObj> EnergyMeters;
	protected ArrayList<GeneratorObj> Generators;
	protected ArrayList<StorageObj> StorageElements;
	protected ArrayList<PVSystemObj> PVSystems;
	protected ArrayList<DSSObject> Substations;
	protected ArrayList<TransformerObj> Transformers;
	protected ArrayList<CapControlObj> CapControls;
	protected ArrayList<RegControlObj> RegControls;
	protected ArrayList<LineObj> Lines;
	protected ArrayList<LoadObj> Loads;
	protected ArrayList<CapacitorObj> ShuntCapacitors;
	protected ArrayList<FeederObj> Feeders;
	protected ArrayList<SwtControlObj> SwtControls;

	protected ControlQueue ControlQueue;

	protected SolutionObj Solution;
	protected AutoAdd AutoAddObj;

	// for AutoAdd stuff
	protected double UEWeight, LossWeight;

	protected int NumUERegs, NumLossRegs;
	protected int[] UERegs, LossRegs;

	protected double CapacityStart, CapacityIncrement;

	protected boolean TrapezoidalIntegration;  // flag for trapezoidal integration
	protected boolean LogEvents;

	protected String LoadDurCurve;
	protected LoadShapeObj LoadDurCurveObj;
	protected String PriceCurve;
	protected PriceShapeObj PriceCurveObj;

	protected int NumDevices, NumBuses, NumNodes;
	protected int MaxDevices, MaxBuses, MaxNodes;
	protected int IncDevices, IncBuses, IncNodes;

	// bus and node stuff
	protected Bus[] Buses;
	protected NodeBus[] MapNodeToBus;

	// flags
	protected boolean IsSolved;
	protected boolean DuplicatesAllowed;
	protected boolean ZonesLocked;
	protected boolean MeterZonesComputed;
	protected boolean PositiveSequence;  // model is to be interpreted as pos seq

	// voltage limits
	/* Per unit voltage restraints for this circuit */
	protected double NormalMinVolts, NormalMaxVolts, EmergMaxVolts, EmergMinVolts;
	protected double[] LegalVoltageBases;

	// global circuit multipliers
	protected double GeneratorDispatchReference,
	DefaultGrowthFactor,
	DefaultGrowthRate,
	GenMultiplier,  // global multiplier for every generator
	HarmMult;
	protected Complex DefaultHourMult;

	protected double PriceSignal;  // price signal for entire circuit

	// energy meter totals
	protected double[] RegisterTotals;

	protected LoadShapeObj DefaultDailyShapeObj, DefaultYearlyShapeObj;

	protected String CurrentDirectory;

	protected ReductionStrategyType ReductionStrategy;
	protected double ReductionMaxAngle, ReductionZmag;
	protected String ReductionStrategyString;

	protected double PctNormalFactor;

	/* Plot markers */
	protected int NodeMarkerCode, NodeMarkerWidth, SwitchMarkerCode;
	protected int TransMarkerSize, TransMarkerCode;

	protected boolean MarkSwitches;
	protected boolean MarkTransformers;

	protected int ActiveLoadShapeClass;

	public DSSCircuit(String aName) {
		super("Circuit");

		this.IsSolved = false;
		DSSGlobals.getInstance().getSolutionClass().newObject(getName());
		this.Solution = SolutionImpl.getActiveSolutionObj();

		setLocalName(aName.toLowerCase());

		setCaseName(aName);  // default case name to "circuitname"; sets circuitName_

		this.Fundamental = DSSGlobals.getInstance().getDefaultBaseFreq();
		setActiveCktElement(null);
		this.ActiveBusIndex = 0;  // always a bus

		// initial allocations increased from 100 to 1000 to speed things up

		this.MaxBuses   = 1000;  // good sized allocation to start
		this.MaxDevices = 1000;
		this.MaxNodes   = 3 * MaxBuses;
		this.IncDevices = 1000;
		this.IncBuses   = 1000;
		this.IncNodes   = 3000;

		// allocate some nominal sizes
		this.BusList    = new HashListImpl(900);  // bus name list nominal size to start; gets reallocated
		this.DeviceList = new HashListImpl(900);
		this.AutoAddBusList = new HashListImpl(100);

		this.NumBuses   = 0;  // eventually allocate a single source
		this.NumDevices = 0;
		this.NumNodes   = 0;

		this.Faults       = new ArrayList<FaultObj>(2);
		this.CktElements  = new ArrayList<CktElement>(1000);
		this.PDElements   = new ArrayList<PDElement>(1000);
		this.PCElements   = new ArrayList<PCElement>(1000);
		this.DSSControls  = new ArrayList<ControlElem>(10);
		this.Sources      = new ArrayList<PCElement>(10);
		this.MeterElements= new ArrayList<MeterElement>(20);
		this.Monitors     = new ArrayList<MonitorObj>(20);
		this.EnergyMeters = new ArrayList<EnergyMeterObj>(5);
		this.Sensors      = new ArrayList<SensorObj>(5);
		this.Generators   = new ArrayList<GeneratorObj>(5);
		this.StorageElements = new ArrayList<StorageObj>(5);
		this.PVSystems    = new ArrayList<PVSystemObj>(5);
		this.Feeders      = new ArrayList<FeederObj>(10);
		this.Substations  = new ArrayList<DSSObject>(5);
		this.Transformers = new ArrayList<TransformerObj>(10);
		this.CapControls  = new ArrayList<CapControlObj>(10);
		this.SwtControls  = new ArrayList<SwtControlObj>(50);
		this.RegControls  = new ArrayList<RegControlObj>(5);
		this.Lines        = new ArrayList<LineObj>(1000);
		this.Loads        = new ArrayList<LoadObj>(1000);
		this.ShuntCapacitors = new ArrayList<CapacitorObj>(20);

		this.Buses     = new Bus[this.MaxBuses];
		this.MapNodeToBus = new NodeBus[this.MaxNodes];
		this.DeviceRef = new CktElementDef[MaxDevices];

		this.ControlQueue = new ControlQueueImpl();

		this.LegalVoltageBases = new double[8];
		// default voltage bases
		this.LegalVoltageBases[0] =   0.208;
		this.LegalVoltageBases[1] =   0.480;
		this.LegalVoltageBases[2] =  12.47;
		this.LegalVoltageBases[3] =  24.9;
		this.LegalVoltageBases[4] =  34.5;
		this.LegalVoltageBases[5] = 115.0;
		this.LegalVoltageBases[6] = 230.0;
		this.LegalVoltageBases[7] =   0.0;  // terminates array

		this.NodeBufferMax = 20;
		this.NodeBuffer    = new int[this.NodeBufferMax];  // to hold the nodes

		// init global circuit load and harmonic source multipliers
		setLoadMultiplier(1.0);
		this.GenMultiplier = 1.0;
		this.HarmMult = 1.0;

		this.PriceSignal = 25.0;  // $25/MWh

		// factors for AutoAdd
		this.UEWeight    = 1.0;  // default to weighting UE same as losses
		this.LossWeight  = 1.0;
		this.NumUERegs   = 1;
		this.NumLossRegs = 1;
		this.UERegs      = new int[NumUERegs];
		this.LossRegs    = new int[NumLossRegs];
		this.UERegs[0]   = 10;  // overload UE
		this.LossRegs[0] = 13;  // zone losses

		this.CapacityStart = 0.9;  // for capacity search
		this.CapacityIncrement = 0.005;

		this.LoadDurCurve    = "";
		this.LoadDurCurveObj = null;
		this.PriceCurve    = "";
		this.PriceCurveObj = null;

		// flags
		this.DuplicatesAllowed  = false;
		this.ZonesLocked        = false;  // meter zones recomputed after each change
		this.MeterZonesComputed = false;
		this.PositiveSequence   = false;

		this.NormalMinVolts = 0.95;
		this.NormalMaxVolts = 1.05;
		this.EmergMaxVolts  = 1.08;
		this.EmergMinVolts  = 0.90;

		this.NodeMarkerCode   = 16;
		this.NodeMarkerWidth  = 1;
		this.MarkSwitches     = false;
		this.MarkTransformers = false;
		this.SwitchMarkerCode = 5;
		this.TransMarkerCode  = 35;
		this.TransMarkerSize  = 1;


		this.TrapezoidalIntegration = false;  // default to Euler method
		this.LogEvents = false;

		this.GeneratorDispatchReference = 0.0;
		this.DefaultGrowthRate = 1.025;
		this.DefaultGrowthFactor = 1.0;

		this.DefaultDailyShapeObj  = (LoadShapeObj) DSSGlobals.getInstance().getLoadShapeClass().find("default");
		this.DefaultYearlyShapeObj = (LoadShapeObj) DSSGlobals.getInstance().getLoadShapeClass().find("default");

		this.CurrentDirectory = "";

		setBusNameRedefined(true);  // set to force rebuild of bus and node lists

		this.SavedBuses = null;
		this.SavedBusNames = null;

		this.ReductionStrategy = ReductionStrategyType.rsDefault;
		this.ReductionMaxAngle = 15.0;
		this.ReductionZmag = 0.02;

		/* Misc objects */
		this.AutoAddObj = new AutoAddImpl();

		this.Branch_List = null;
		this.BusAdjPC = null;
		this.BusAdjPD = null;
	}

	private void addDeviceHandle(int Handle) {
		if (NumDevices > MaxDevices) {
			MaxDevices = MaxDevices + IncDevices;
			DeviceRef = (CktElementDef[]) Utilities.resizeArray(DeviceRef, MaxDevices);
		}
		DeviceRef[NumDevices].devHandle = Handle;  // index into CktElements
		DeviceRef[NumDevices].CktElementClass = DSSGlobals.getInstance().getLastClassReferenced();
	}

	private void addABus() {
		if (NumBuses > MaxBuses) {
			MaxBuses += IncBuses;
			Buses = (Bus[]) Utilities.resizeArray(Buses, MaxBuses);
		}
	}

	private void addANodeBus() {
		if (NumNodes > MaxNodes) {
			MaxNodes += IncNodes;
			MapNodeToBus = (NodeBus[]) Utilities.resizeArray(MapNodeToBus, MaxNodes);
		}
	}

	private int addBus(final String BusName, int NNodes) {

		if (BusName.length() == 0) {  // error in busname
			DSSGlobals.getInstance().doErrorMsg("DSSCircuit.addBus", "BusName for object \"" + ActiveCktElement.getName() + "\" is null.",
					"Error in definition of object.", 424);
			for (int i = 0; i < ActiveCktElement.getNConds(); i++) NodeBuffer[i] = 0;
			return 0;
		}

		int Result = BusList.find(BusName);
		if (Result == 0) {
			Result = BusList.add(BusName);  // result is index of bus
			NumBuses += 1;
			addABus();  // allocates more memory if necessary
			Buses[NumBuses] = new DSSBus();
		}

		/* Define nodes belonging to the bus */
		/* Replace nodeBuffer values with global reference number */
		int NodeRef;
		for (int i = 0; i < NNodes; i++) {
			NodeRef = Buses[Result].add(NodeBuffer[i]);
			if (NodeRef == NumNodes) {  // this was a new node so add a nodeToBus element ????
				addANodeBus();  // allocates more memory if necessary
				MapNodeToBus[NumNodes].busRef  = Result;
				MapNodeToBus[NumNodes].nodeNum = NodeBuffer[i];
			}
			NodeBuffer[i] = NodeRef;  // swap out in preparation to setNodeRef call
		}
		return Result;
	}

	public void setActiveCktElement(CktElement Value) {
		ActiveCktElement = Value;
		DSSGlobals.getInstance().setActiveDSSObject(Value);
	}

	public CktElement getActiveCktElement() {
		return ActiveCktElement;
	}

	public void setBusNameRedefined(boolean Value) {
		BusNameRedefined = Value;

		if (Value) {
			// force rebuilding of system Y if bus def has changed
			Solution.setSystemYChanged(true);
			// so controls will know buses redefined
			Control_BusNameRedefined = true;
		}
	}

	public boolean isBusNameRedefined() {
		return BusNameRedefined;
	}

	/* Total circuit PD element losses */
	public Complex getLosses() {
		Complex Result = Complex.ZERO;
		for (PDElement pdElem : PDElements) {
			if (pdElem.isEnabled()) {
				/* Ignore shunt elements */
				if (!pdElem.isShunt())
					Result = Result.add(pdElem.getLosses());
			}
		}
		return Result;
	}

	public void setLoadMultiplier(double Value) {
		if (Value != LoadMultiplier) {
			// may have to change the Y matrix if the load multiplier has changed
			switch (Solution.getLoadModel()) {
			case DSSGlobals.ADMITTANCE:
				invalidateAllPCElements();
				break;
			}
		}
		LoadMultiplier = Value;
	}

	public double getLoadMultiplier() {
		return 0.0;
	}

	private void saveBusInfo() {
		/* Save existing bus definitions and names for info that needs to be restored */
		SavedBuses = new DSSBus[NumBuses];
		SavedBusNames = new String[NumBuses];

		for (int i = 0; i < NumBuses; i++) {
			SavedBuses[i] = Buses[i];
			SavedBusNames[i] = BusList.get(i);
		}
		SavedNumBuses = NumBuses;
	}

	private void restoreBusInfo() {
		Bus bus;
		/* Restore kV bases, other values to buses still in the list */
		for (int i = 0; i < SavedNumBuses; i++) {
			int idx = BusList.find(SavedBusNames[i]);
			if (idx != -1) {
				bus = SavedBuses[i];
				Buses[idx].setKVBase(bus.getKVBase());
				Buses[idx].setX(bus.getX());
				Buses[idx].setY(bus.getY());
				Buses[idx].setCoordDefined(bus.isCoordDefined());
				Buses[idx].setKeep(bus.isKeep());
				/* Restore voltages in new bus def that existed in old bus def */
				if (bus.getVBus() != null) {
					for (int j = 0; j < bus.getNumNodesThisBus(); j++) {
						// find index in new bus for j-th node in old bus
						int jdx = Buses[idx].findIdx(bus.getNum(j));
						if (jdx > -1) Buses[idx].getVBus()[jdx] = bus.getVBus()[j];
					}
				}
			}
			SavedBusNames[i] = "";  // de-allocate string
		}

		if (SavedBuses != null)
			for (int i = 0; i < SavedNumBuses; i++)
				SavedBuses[i] = null;  // gets rid of old bus voltages too

		SavedBuses = new Bus[0]; //ReallocMem(SavedBuses, 0);
		SavedBusNames = new String[0]; //ReallocMem(SavedBusNames, 0);
	}

	private boolean saveMasterFile() {
		boolean Result = false;
		try {
			File FD = new File("Master.dss");
			PrintStream F = new PrintStream(FD);

			F.println("clear");
			F.println("new circuit." + getName());
			F.println();
			if (PositiveSequence) F.println("set cktModel=Positive");
			if (DuplicatesAllowed) F.println("set allowDup=yes");
			F.println();

			// Write Redirect for all populated DSS Classes  Except Solution Class
			for (int i = 0; i < DSSGlobals.getInstance().getSavedFileList().size(); i++)
				F.println("redirect " + DSSGlobals.getInstance().getSavedFileList().get(i - 1));

			if (new File("BusCoords.dss").exists()) {
				F.println("makeBusList");
				F.println("BusCoords BusCoords.dss");
			}

			F.close();
			Result = true;
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error saving master file: " + e.getMessage(), 435);
		}

		return Result;
	}

	private boolean saveDSSObjects() {
		// write files for all populated DSS classes except solution class
		for (DSSClass DSS_Class : DSSGlobals.getInstance().getDSSClassList()) {
			if ((DSS_Class == DSSGlobals.getInstance().getSolutionClass()) || DSS_Class.isSaved())
				continue;  // cycle to next
			/* use default filename=classname */
			if (!Utilities.writeClassFile(DSS_Class, "", (DSS_Class instanceof CktElementClass) ))
				return false;
			DSS_Class.setSaved(true);
		}
		return true;
	}

	private boolean saveFeeders() {
		String CurrDir, SaveDir;
		DSSGlobals Globals = DSSGlobals.getInstance();

		boolean Result = true;
		/* Write out all energy meter zones to separate subdirectories */
		SaveDir = Globals.getCurrentDirectory();
		for (EnergyMeterObj Meter : EnergyMeters) {
			CurrDir = Meter.getName();
			if (new File(CurrDir).mkdir()) {
				Globals.setCurrentDirectory(CurrDir);
				Meter.saveZone(CurrDir);
				Globals.setCurrentDirectory(SaveDir);
			} else {
				DSSGlobals.getInstance().doSimpleMsg("Cannot create directory: " + CurrDir, 436);
				Result = false;
				Globals.setCurrentDirectory(SaveDir);
				break;
			}
		}
		return Result;
	}

	private boolean saveBusCoords() {
		boolean Result = false;

		try {
			File FD = new File("BusCoords.dss");
			PrintStream F = new PrintStream(FD);

			for (int i = 0; i < NumBuses; i++)
				if (Buses[i].isCoordDefined())
					F.println(Utilities.checkForBlanks(BusList.get(i)) + String.format(", %-g, %-g", Buses[i].getX(), Buses[i].getY()));

			F.close();

			Result = true;
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error creating BusCoords.dss.", 437);
		}

		return Result;
	}

	/* Reallocate the device list to improve the performance of searches */
	private void reallocDeviceList() {
		if (LogEvents) Utilities.logThisEvent("Reallocating device list");
		HashListImpl TempList = new HashListImpl(2 * NumDevices);

		for (int i = 0; i < DeviceList.listSize(); i++)
			TempList.add(DeviceList.get(i));

		DeviceList = TempList;
	}

	public void setCaseName(final String Value) {
		CaseName = Value;
		DSSGlobals.getInstance().setCircuitName_(Value + "_");
	}

	public String getCaseName() {
		return CaseName;
	}

	public String getName() {
		return getLocalName();
	}

	/* Adds last DSS object created to circuit */
	public void addCktElement(int Handle) {
		// update lists that keep track of individual circuit elements
		NumDevices += 1;

		// resize deviceList if no. of devices greatly exceeds allocation
		if (NumDevices > 2 * DeviceList.getInitialAllocation())
			reallocDeviceList();
		DeviceList.add(ActiveCktElement.getName());
		CktElements.add(ActiveCktElement);

		/* Build lists of PC and PD elements */
		if (ActiveCktElement.getDSSObjType() == DSSClassDefs.PD_ELEMENT) {
			PDElements.add((PDElement) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.PC_ELEMENT) {
			PCElements.add((PCElement) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.CTRL_ELEMENT) {
			DSSControls.add((ControlElem) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.METER_ELEMENT) {
			MeterElements.add((MeterElement) ActiveCktElement);
		}

		/* Build lists of special elements and generic types */
		if (ActiveCktElement.getDSSObjType() == DSSClassDefs.MON_ELEMENT) {
			Monitors.add((MonitorObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.ENERGY_METER) {
			EnergyMeters.add((EnergyMeterObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.SENSOR_ELEMENT) {
			Sensors.add((SensorObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.GEN_ELEMENT) {
			Generators.add((GeneratorObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.SOURCE) {
			Sources.add((PCElement) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.CAP_CONTROL) {
			CapControls.add((CapControlObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.SWT_CONTROL) {
			SwtControls.add((SwtControlObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.REG_CONTROL) {
			RegControls.add((RegControlObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.LOAD_ELEMENT) {
			Loads.add((LoadObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.CAP_ELEMENT) {
			ShuntCapacitors.add((CapacitorObj) ActiveCktElement);
		}
		/* Keep lines, transformer, and faults in PDElements and
		 * separate lists so we can find them quickly. */
		else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.XFMR_ELEMENT) {
			Transformers.add((TransformerObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.LINE_ELEMENT) {
			Lines.add((LineObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.FAULTOBJECT) {
			Faults.add((FaultObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.FEEDER_ELEMENT) {
			Feeders.add((FeederObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.STORAGE_ELEMENT) {
			StorageElements.add((StorageObj) ActiveCktElement);
		} else if (ActiveCktElement.getDSSObjType() == DSSClassDefs.PVSYSTEM_ELEMENT) {
			PVSystems.add((PVSystemObj) ActiveCktElement);
		}

		// AddDeviceHandle(Handle);  // keep track of this device result is handle
		addDeviceHandle(CktElements.size());  // handle is global index into CktElements
		ActiveCktElement.setHandle(CktElements.size());
	}

	/**
	 * Totalize all energy meters in the problem.
	 */
	public void totalizeMeters() {
		for (int i = 0; i < EnergyMeterObj.NumEMRegisters; i++)
			RegisterTotals[i] = 0.0;

		for (int i = 0; i < EnergyMeters.size(); i++) {
			EnergyMeterObj Meter = EnergyMeters.get(i);
			for (int j = 0; j < EnergyMeterObj.NumEMRegisters; i++)
				RegisterTotals[i] += Meter.getRegisters()[i] * Meter.getTotalsMask()[i];
		}
	}

	private double sumSelectedRegisters(double[] mtrRegisters, int[] Regs, int count) {
		double Result = 0.0;
		for (int i = 0; i < count; i++)
			Result += mtrRegisters[Regs[i]];
		return Result;
	}
	public boolean computeCapacity() {
		boolean Result = false;
		if (EnergyMeters.size() == 0) {
			DSSGlobals.getInstance().doSimpleMsg("Cannot compute system capacity with EnergyMeter objects!", 430);
			return Result;
		}

		if (NumUERegs == 0) {
			DSSGlobals.getInstance().doSimpleMsg("Cannot compute system capacity with no UE resisters defined. Use \"set UERegs=(...)\" command.", 431);
			return Result;
		}

		Solution.setMode(Dynamics.SNAPSHOT);
		setLoadMultiplier(CapacityStart);
		boolean CapacityFound = false;

		while ((LoadMultiplier <= 1.0) && !CapacityFound) {
			DSSGlobals.getInstance().getEnergyMeterClass().resetAll();
			Solution.solve();
			DSSGlobals.getInstance().getEnergyMeterClass().sampleAll();
			totalizeMeters();

			// check for non-zero in UEregs
			if (sumSelectedRegisters(RegisterTotals, UERegs, NumUERegs) != 0.0)
				CapacityFound = true;
			// loadMultiplier is a property ...
			if (!CapacityFound)
				LoadMultiplier += CapacityIncrement;
		}

		if (LoadMultiplier > 1.0) setLoadMultiplier(1.0);
		Result = true;

		return Result;
	}

	public boolean save(String Dir) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		String CurrDir, SaveDir;
		boolean Result = false;

		// make a new subfolder in the present folder based on the circuit
		// name and a unique sequence number
		SaveDir = Globals.getCurrentDirectory();

		boolean Success = false;
		if (Dir.length() == 0) {
			Dir = getName();

			CurrDir = Dir;
			for (int i = 0; i < 999; i++) {  // Find a unique dir name
				File F = new File(CurrDir);
				if (!F.exists()) {
					if (F.mkdir()) {
		            	Globals.setCurrentDirectory(CurrDir);
						Success = true;
						break;
					}
				}
				CurrDir = Dir + String.format("%.3d", i);
			}
		} else {
			File F = new File(Dir);
			if (!F.exists()) {
				CurrDir = Dir;
				F = new File(CurrDir);
				if (F.mkdir()) {
		            Globals.setCurrentDirectory(CurrDir);
					Success = true;
				}
			} else {  // exists - overwrite
				CurrDir = Dir;
		        Globals.setCurrentDirectory(CurrDir);
				Success = true;
			}
		}

		if (!Success) {
			DSSGlobals.getInstance().doSimpleMsg("Could not create a folder \"" + Dir + "\" for saving the circuit.", 432);
			return Result;
		}

		// this list keeps track of all files saved
		DSSGlobals.getInstance().setSavedFileList(new ArrayList<String>());

		// initialize so we will know when we have saved the circuit elements
		for (CktElement elem : CktElements)
			elem.setHasBeenSaved(false);

		// initialize so we don't save a class twice
		for (DSSClass cls : DSSGlobals.getInstance().getDSSClassList())
			cls.setSaved(false);

		// ignore feeder class -- gets saved with EnergyMeters
		//Globals.getFeederClass().setSaved(true);

		// Define voltage sources first
		Success = Utilities.writeVsourceClassFile(DSSClassDefs.getDSSClass("vsource"), true);
		// write library files so that they will be available to lines, loads, etc
		/* Use default filename=classname */
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("wiredata"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("cndata"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("tsdata"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linegeometry"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linecode"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linespacing"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linecode"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("xfmrcode"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("growthshape"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("TCC_Curve"), "", false);
		if (Success) Success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("Spectrum"), "", false);
		if (Success) Success = saveFeeders();  // save feeders first
		if (Success) Success = saveDSSObjects();  // save rest to the objects
		if (Success) Success = saveBusCoords();
		if (Success) Success = saveMasterFile();

		if (Success) {
			DSSGlobals.getInstance().doSimpleMsg("Circuit saved in directory: " + Globals.getCurrentDirectory(), 433);
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error attempting to save circuit in " + Globals.getCurrentDirectory(), 434);
		}
		// return to original directory
		Globals.setCurrentDirectory(SaveDir);

		return true;
	}

	public void processBusDefs() {
		String BusName;
		MutableInt NNodes = new MutableInt();
		int np = ActiveCktElement.getNPhases();
		int NCond = ActiveCktElement.getNConds();

		// use parser functions to decode
		Parser.getInstance().setToken(ActiveCktElement.getFirstBus());

		for (int iTerm = 0; iTerm < ActiveCktElement.getNTerms(); iTerm++) {
			boolean NodesOK = true;
			// assume normal phase rotation for default
			for (int i = 0; i < np; i++)
				NodeBuffer[i] = i;  // set up buffer with defaults

			// default all other conductors to a ground connection
			// uf user wants them ungrounded, must be specified explicitly!
			for (int i = np + 1; i < NCond; i++)
				NodeBuffer[i] = 0;

			// parser will override bus connection if any specified
			BusName = Parser.getInstance().parseAsBusName(NNodes, NodeBuffer);

			// check for error in node specification
			for (int j = 0; j < NNodes.intValue(); j++) {
				if (NodeBuffer[j] < 0) {
					int retval = DSSGlobals.getInstance().getDSSForms().messageDlg("Error in node specification for element: \""
						+ ActiveCktElement.getParentClass().getName() + "." + ActiveCktElement.getName() + "\"" + DSSGlobals.CRLF +
						"Bus Spec: \"" + Parser.getInstance().getToken() + "\"", false);
					NodesOK = false;
					if (retval == -1) {
						AbortBusProcess = true;
						DSSGlobals.getInstance().appendGlobalResult("Aborted bus process.");
						return;
					}
					break;
				}
			}

			// node-terminal connnections
			// Caution: addBus replaces values in nodeBuffer to correspond
			// with global node reference number
			if (NodesOK) {
				ActiveCktElement.setActiveTerminalIdx(iTerm);
				ActiveCktElement.getActiveTerminal().setBusRef(addBus(BusName, NCond));
				ActiveCktElement.setNodeRef(iTerm, NodeBuffer);  // for active circuit
			}
			Parser.getInstance().setToken(ActiveCktElement.getNextBus());
		}
	}

	/**
	 * Redo all bus and node lists.
	 */
	public void reProcessBusDefs() {
		if (LogEvents) Utilities.logThisEvent("Reprocessing bus definitions");

		AbortBusProcess = false;
		saveBusInfo();  // so we don't have to keep re-doing this
		// keeps present definitions of bus objects until new ones created

		// get rid of old bus lists
		BusList = null;  // clears hash list of bus names for adding more
		BusList = new HashListImpl(NumDevices);  // won't have many more buses than this

		NumBuses = 0;  // leave allocations same, but start count over
		NumNodes = 0;

		// now redo all enabled circuit elements
		CktElement CktElementSave = ActiveCktElement;
		for (int i = 0; i < CktElements.size(); i++) {
			setActiveCktElement( CktElements.get(i) );
			if (ActiveCktElement.isEnabled())
				processBusDefs();
			if (AbortBusProcess)
				return;
		}

		setActiveCktElement(CktElementSave);  // restore active circuit element

		for (int i = 0; i < NumBuses; i++) Buses[i].allocateBusVoltages();
		for (int i = 0; i < NumBuses; i++) Buses[i].allocateBusCurrents();

		restoreBusInfo();     // frees old bus info too
		doResetMeterZones();  // fix up meter zones to correspond

		setBusNameRedefined(false);  // get ready for next time
	}

	public void doResetMeterZones() {
		/* Do this only if meterZones unlocked. Normally, zones will remain
		 * unlocked so that all changes to the circuit will result in rebuilding
		 * the lists */
		if (!MeterZonesComputed || !ZonesLocked) {
			if (LogEvents) Utilities.logThisEvent("Resetting meter zones");
			DSSGlobals.getInstance().getEnergyMeterClass().resetMeterZonesAll();
			MeterZonesComputed = true;
			if (LogEvents) Utilities.logThisEvent("Done resetting meter zones");
		}

		freeTopology();
	}

	public int setElementActive(String FullObjectName) {
		int Result = 0;
		StringBuffer DevType = new StringBuffer();
		StringBuffer DevName = new StringBuffer();

		Utilities.parseObjectClassandName(FullObjectName, DevType, DevName);
		int DevClassIndex = DSSGlobals.getInstance().getClassNames().find(DevType.toString());
		if (DevClassIndex == 0)
			DevClassIndex = DSSGlobals.getInstance().getLastClassReferenced();
		int DevIndex = DeviceList.find(DevName.toString());
		while (DevIndex >= 0) {
			if (DeviceRef[DevIndex].CktElementClass == DevClassIndex) {  // we got a match
				DSSGlobals.getInstance().setActiveDSSClass(DSSGlobals.getInstance().getDSSClassList().get(DevClassIndex));
				DSSGlobals.getInstance().setLastClassReferenced(DevClassIndex);
				Result = DeviceRef[DevIndex].devHandle;
				// ActiveDSSClass.Active = Result;
				// ActiveCktElement = ActiveDSSClass.getActiveObj;
				setActiveCktElement( CktElements.get(Result) );
				break;
			}
			DevIndex = DeviceList.findNext();  // could be duplicates
		}

		DSSGlobals.getInstance().setCmdResult(Result);

		return Result;
	}

	public void invalidateAllPCElements() {
		for (PCElement p : PCElements)
			p.setYprimInvalid(true);

		// force rebuild of matrix on next solution
		Solution.setSystemYChanged(true);
	}

	public void debugDump(PrintStream F) {
		F.println("numBuses= " + NumBuses);
		F.println("numNodes= " + NumNodes);
		F.println("numDevices= " + NumDevices);
		F.println("BusList:");
		for (int i = 0; i < NumBuses; i++) {
			F.printf("  %12s", BusList.get(i));
			F.printf(" (" + Buses[i].getNumNodesThisBus() + " nodes)");
			for (int j = 0; j < Buses[i].getNumNodesThisBus(); j++) {
				F.print(" " + Buses[i].getNum(j));
			}
			F.println();
		}
		F.println("DeviceList:");
		for (int i = 0; i < NumDevices; i++) {
			F.printf("  %12s%s", DeviceList.get(i), DSSGlobals.CRLF);
			setActiveCktElement( CktElements.get(i) );
			if (!ActiveCktElement.isEnabled())
				F.print("  DISABLED");
			F.println();
		}
		F.println("NodeToBus Array:");
		for (int i = 0; i < NumNodes; i++) {
			int j = MapNodeToBus[i].busRef;
			F.print("  " + i + " " + j + " (=" +BusList.get(j) + "." + MapNodeToBus[i].nodeNum + ")");
			F.println();
		}
	}

	/**
	 * Access to topology from the first source
	 */
	public CktTree getTopology() {
		if (Branch_List == null) {
			/* Initialize all circuit elements and buses to not checked, then build a new tree */
			for (CktElement elem : CktElements) {
				elem.setChecked(false);
				for (int i = 0; i < elem.getNTerms(); i++)
					elem.getTerminals()[i].setChecked(false);
				elem.setIsIsolated(true);  // till proven otherwise
			}

			for (int i = 0; i < NumBuses; i++)
				Buses[i].setBusChecked(false);

			Branch_List = CktTreeImpl.getIsolatedSubArea(Sources.get(0), true);  // calls back to build adjacency lists
		}
		return Branch_List;
	}

	public void freeTopology() {
		if (Branch_List != null)
			Branch_List = null;
		if (BusAdjPC != null)
			CktTreeImpl.freeAndNilBusAdjacencyLists(BusAdjPD, BusAdjPC);
	}

	public List<PDElement>[] getBusAdjacentPDLists() {
		if (BusAdjPD == null)
			CktTreeImpl.buildActiveBusAdjacencyLists(BusAdjPD, BusAdjPC);
		return BusAdjPD;
	}

	public List<PCElement>[] getBusAdjacentPCLists() {
		if (BusAdjPC == null)
			CktTreeImpl.buildActiveBusAdjacencyLists(BusAdjPD, BusAdjPC);
		return BusAdjPC;
	}

	public int getActiveBusIndex() {
		return ActiveBusIndex;
	}

	public void setActiveBusIndex(int activeBusIndex) {
		ActiveBusIndex = activeBusIndex;
	}

	public double getFundamental() {
		return Fundamental;
	}

	public void setFundamental(double fundamental) {
		Fundamental = fundamental;
	}

	public boolean isControl_BusNameRedefined() {
		return Control_BusNameRedefined;
	}

	public void setControl_BusNameRedefined(boolean control_BusNameRedefined) {
		Control_BusNameRedefined = control_BusNameRedefined;
	}

	public HashList getBusList() {
		return BusList;
	}

	public void setBusList(HashList busList) {
		BusList = busList;
	}

	public HashList getAutoAddBusList() {
		return AutoAddBusList;
	}

	public void setAutoAddBusList(HashList autoAddBusList) {
		AutoAddBusList = autoAddBusList;
	}

	public HashList getDeviceList() {
		return DeviceList;
	}

	public void setDeviceList(HashList deviceList) {
		DeviceList = deviceList;
	}

	public CktElementDef[] getDeviceRef() {
		return DeviceRef;
	}

	public void setDeviceRef(CktElementDef[] deviceRef) {
		DeviceRef = deviceRef;
	}

	public ArrayList<FaultObj> getFaults() {
		return Faults;
	}

	public void setFaults(ArrayList<FaultObj> faults) {
		Faults = faults;
	}

	public ArrayList<CktElement> getCktElements() {
		return CktElements;
	}

	public void setCktElements(ArrayList<CktElement> cktElements) {
		CktElements = cktElements;
	}

	public ArrayList<PDElement> getPDElements() {
		return PDElements;
	}

	public void setPDElements(ArrayList<PDElement> pDElements) {
		PDElements = pDElements;
	}

	public ArrayList<PCElement> getPCElements() {
		return PCElements;
	}

	public void setPCElements(ArrayList<PCElement> pCElements) {
		PCElements = pCElements;
	}

	public ArrayList<ControlElem> getDSSControls() {
		return DSSControls;
	}

	public void setDSSControls(ArrayList<ControlElem> dSSControls) {
		DSSControls = dSSControls;
	}

	public ArrayList<PCElement> getSources() {
		return Sources;
	}

	public void setSources(ArrayList<PCElement> sources) {
		Sources = sources;
	}

	public ArrayList<MeterElement> getMeterElements() {
		return MeterElements;
	}

	public void setMeterElements(ArrayList<MeterElement> meterElements) {
		MeterElements = meterElements;
	}

	public ArrayList<SensorObj> getSensors() {
		return Sensors;
	}

	public void setSensors(ArrayList<SensorObj> sensors) {
		Sensors = sensors;
	}

	public ArrayList<MonitorObj> getMonitors() {
		return Monitors;
	}

	public void setMonitors(ArrayList<MonitorObj> monitors) {
		Monitors = monitors;
	}

	public ArrayList<EnergyMeterObj> getEnergyMeters() {
		return EnergyMeters;
	}

	public void setEnergyMeters(ArrayList<EnergyMeterObj> energyMeters) {
		EnergyMeters = energyMeters;
	}

	public ArrayList<GeneratorObj> getGenerators() {
		return Generators;
	}

	public void setGenerators(ArrayList<GeneratorObj> generators) {
		Generators = generators;
	}

	public ArrayList<StorageObj> getStorageElements() {
		return StorageElements;
	}

	public void setStorageElements(ArrayList<StorageObj> storageElements) {
		StorageElements = storageElements;
	}

	public ArrayList<PVSystemObj> getPVSystems() {
		return PVSystems;
	}

	public void setPVSystems(ArrayList<PVSystemObj> pVSystems) {
		PVSystems = pVSystems;
	}

	public ArrayList<DSSObject> getSubstations() {
		return Substations;
	}

	public void setSubstations(ArrayList<DSSObject> substations) {
		Substations = substations;
	}

	public ArrayList<TransformerObj> getTransformers() {
		return Transformers;
	}

	public void setTransformers(ArrayList<TransformerObj> transformers) {
		Transformers = transformers;
	}

	public ArrayList<CapControlObj> getCapControls() {
		return CapControls;
	}

	public void setCapControls(ArrayList<CapControlObj> capControls) {
		CapControls = capControls;
	}

	public ArrayList<RegControlObj> getRegControls() {
		return RegControls;
	}

	public void setRegControls(ArrayList<RegControlObj> regControls) {
		RegControls = regControls;
	}

	public ArrayList<LineObj> getLines() {
		return Lines;
	}

	public void setLines(ArrayList<LineObj> lines) {
		Lines = lines;
	}

	public ArrayList<LoadObj> getLoads() {
		return Loads;
	}

	public void setLoads(ArrayList<LoadObj> loads) {
		Loads = loads;
	}

	public ArrayList<CapacitorObj> getShuntCapacitors() {
		return ShuntCapacitors;
	}

	public void setShuntCapacitors(ArrayList<CapacitorObj> shuntCapacitors) {
		ShuntCapacitors = shuntCapacitors;
	}

	public ArrayList<FeederObj> getFeeders() {
		return Feeders;
	}

	public void setFeeders(ArrayList<FeederObj> feeders) {
		Feeders = feeders;
	}

	public ArrayList<SwtControlObj> getSwtControls() {
		return SwtControls;
	}

	public void setSwtControls(ArrayList<SwtControlObj> swtControls) {
		SwtControls = swtControls;
	}

	public ControlQueue getControlQueue() {
		return ControlQueue;
	}

	public void setControlQueue(ControlQueue controlQueue) {
		ControlQueue = controlQueue;
	}

	public SolutionObj getSolution() {
		return Solution;
	}

	public void setSolution(SolutionObj solution) {
		Solution = solution;
	}

	public AutoAdd getAutoAddObj() {
		return AutoAddObj;
	}

	public void setAutoAddObj(AutoAdd autoAddObj) {
		AutoAddObj = autoAddObj;
	}

	public double getUEWeight() {
		return UEWeight;
	}

	public void setUEWeight(double uEWeight) {
		UEWeight = uEWeight;
	}

	public double getLossWeight() {
		return LossWeight;
	}

	public void setLossWeight(double lossWeight) {
		LossWeight = lossWeight;
	}

	public int getNumUERegs() {
		return NumUERegs;
	}

	public void setNumUERegs(int numUERegs) {
		NumUERegs = numUERegs;
	}

	public int getNumLossRegs() {
		return NumLossRegs;
	}

	public void setNumLossRegs(int numLossRegs) {
		NumLossRegs = numLossRegs;
	}

	public int[] getUERegs() {
		return UERegs;
	}

	public void setUERegs(int[] uERegs) {
		UERegs = uERegs;
	}

	public int[] getLossRegs() {
		return LossRegs;
	}

	public void setLossRegs(int[] lossRegs) {
		LossRegs = lossRegs;
	}

	public double getCapacityStart() {
		return CapacityStart;
	}

	public void setCapacityStart(double capacityStart) {
		CapacityStart = capacityStart;
	}

	public double getCapacityIncrement() {
		return CapacityIncrement;
	}

	public void setCapacityIncrement(double capacityIncrement) {
		CapacityIncrement = capacityIncrement;
	}

	public boolean isTrapezoidalIntegration() {
		return TrapezoidalIntegration;
	}

	public void setTrapezoidalIntegration(boolean trapezoidalIntegration) {
		TrapezoidalIntegration = trapezoidalIntegration;
	}

	public boolean isLogEvents() {
		return LogEvents;
	}

	public void setLogEvents(boolean logEvents) {
		LogEvents = logEvents;
	}

	public String getLoadDurCurve() {
		return LoadDurCurve;
	}

	public void setLoadDurCurve(String loadDurCurve) {
		LoadDurCurve = loadDurCurve;
	}

	public LoadShapeObj getLoadDurCurveObj() {
		return LoadDurCurveObj;
	}

	public void setLoadDurCurveObj(LoadShapeObj loadDurCurveObj) {
		LoadDurCurveObj = loadDurCurveObj;
	}

	public String getPriceCurve() {
		return PriceCurve;
	}

	public void setPriceCurve(String priceCurve) {
		PriceCurve = priceCurve;
	}

	public PriceShapeObj getPriceCurveObj() {
		return PriceCurveObj;
	}

	public void setPriceCurveObj(PriceShapeObj priceCurveObj) {
		PriceCurveObj = priceCurveObj;
	}

	public int getNumDevices() {
		return NumDevices;
	}

	public void setNumDevices(int numDevices) {
		NumDevices = numDevices;
	}

	public int getNumBuses() {
		return NumBuses;
	}

	public void setNumBuses(int numBuses) {
		NumBuses = numBuses;
	}

	public int getNumNodes() {
		return NumNodes;
	}

	public void setNumNodes(int numNodes) {
		NumNodes = numNodes;
	}

	public int getMaxDevices() {
		return MaxDevices;
	}

	public void setMaxDevices(int maxDevices) {
		MaxDevices = maxDevices;
	}

	public int getMaxBuses() {
		return MaxBuses;
	}

	public void setMaxBuses(int maxBuses) {
		MaxBuses = maxBuses;
	}

	public int getMaxNodes() {
		return MaxNodes;
	}

	public void setMaxNodes(int maxNodes) {
		MaxNodes = maxNodes;
	}

	public int getIncDevices() {
		return IncDevices;
	}

	public void setIncDevices(int incDevices) {
		IncDevices = incDevices;
	}

	public int getIncBuses() {
		return IncBuses;
	}

	public void setIncBuses(int incBuses) {
		IncBuses = incBuses;
	}

	public int getIncNodes() {
		return IncNodes;
	}

	public void setIncNodes(int incNodes) {
		IncNodes = incNodes;
	}

	public Bus[] getBuses() {
		return Buses;
	}

	public void setBuses(Bus[] buses) {
		Buses = buses;
	}

	public NodeBus[] getMapNodeToBus() {
		return MapNodeToBus;
	}

	public void setMapNodeToBus(NodeBus[] mapNodeToBus) {
		MapNodeToBus = mapNodeToBus;
	}

	public boolean isSolved() {
		return IsSolved;
	}

	public void setIsSolved(boolean isSolved) {
		IsSolved = isSolved;
	}

	public boolean isDuplicatesAllowed() {
		return DuplicatesAllowed;
	}

	public void setDuplicatesAllowed(boolean duplicatesAllowed) {
		DuplicatesAllowed = duplicatesAllowed;
	}

	public boolean isZonesLocked() {
		return ZonesLocked;
	}

	public void setZonesLocked(boolean zonesLocked) {
		ZonesLocked = zonesLocked;
	}

	public boolean isMeterZonesComputed() {
		return MeterZonesComputed;
	}

	public void setMeterZonesComputed(boolean meterZonesComputed) {
		MeterZonesComputed = meterZonesComputed;
	}

	public boolean isPositiveSequence() {
		return PositiveSequence;
	}

	public void setPositiveSequence(boolean positiveSequence) {
		PositiveSequence = positiveSequence;
	}

	public double getNormalMinVolts() {
		return NormalMinVolts;
	}

	public void setNormalMinVolts(double normalMinVolts) {
		NormalMinVolts = normalMinVolts;
	}

	public double getNormalMaxVolts() {
		return NormalMaxVolts;
	}

	public void setNormalMaxVolts(double normalMaxVolts) {
		NormalMaxVolts = normalMaxVolts;
	}

	public double getEmergMaxVolts() {
		return EmergMaxVolts;
	}

	public void setEmergMaxVolts(double emergMaxVolts) {
		EmergMaxVolts = emergMaxVolts;
	}

	public double getEmergMinVolts() {
		return EmergMinVolts;
	}

	public void setEmergMinVolts(double emergMinVolts) {
		EmergMinVolts = emergMinVolts;
	}

	public double[] getLegalVoltageBases() {
		return LegalVoltageBases;
	}

	public void setLegalVoltageBases(double[] legalVoltageBases) {
		LegalVoltageBases = legalVoltageBases;
	}

	public double getGeneratorDispatchReference() {
		return GeneratorDispatchReference;
	}

	public void setGeneratorDispatchReference(double generatorDispatchReference) {
		GeneratorDispatchReference = generatorDispatchReference;
	}

	public double getDefaultGrowthFactor() {
		return DefaultGrowthFactor;
	}

	public void setDefaultGrowthFactor(double defaultGrowthFactor) {
		DefaultGrowthFactor = defaultGrowthFactor;
	}

	public double getDefaultGrowthRate() {
		return DefaultGrowthRate;
	}

	public void setDefaultGrowthRate(double defaultGrowthRate) {
		DefaultGrowthRate = defaultGrowthRate;
	}

	public double getGenMultiplier() {
		return GenMultiplier;
	}

	public void setGenMultiplier(double genMultiplier) {
		GenMultiplier = genMultiplier;
	}

	public double getHarmMult() {
		return HarmMult;
	}

	public void setHarmMult(double harmMult) {
		HarmMult = harmMult;
	}

	public Complex getDefaultHourMult() {
		return DefaultHourMult;
	}

	public void setDefaultHourMult(Complex defaultHourMult) {
		DefaultHourMult = defaultHourMult;
	}

	public double getPriceSignal() {
		return PriceSignal;
	}

	public void setPriceSignal(double priceSignal) {
		PriceSignal = priceSignal;
	}

	public double[] getRegisterTotals() {
		return RegisterTotals;
	}

	public void setRegisterTotals(double[] registerTotals) {
		RegisterTotals = registerTotals;
	}

	public LoadShapeObj getDefaultDailyShapeObj() {
		return DefaultDailyShapeObj;
	}

	public void setDefaultDailyShapeObj(LoadShapeObj defaultDailyShapeObj) {
		DefaultDailyShapeObj = defaultDailyShapeObj;
	}

	public LoadShapeObj getDefaultYearlyShapeObj() {
		return DefaultYearlyShapeObj;
	}

	public void setDefaultYearlyShapeObj(LoadShapeObj defaultYearlyShapeObj) {
		DefaultYearlyShapeObj = defaultYearlyShapeObj;
	}

	public String getCurrentDirectory() {
		return CurrentDirectory;
	}

	public void setCurrentDirectory(String currentDirectory) {
		CurrentDirectory = currentDirectory;
	}

	public ReductionStrategyType getReductionStrategy() {
		return ReductionStrategy;
	}

	public void setReductionStrategy(ReductionStrategyType reductionStrategy) {
		ReductionStrategy = reductionStrategy;
	}

	public double getReductionMaxAngle() {
		return ReductionMaxAngle;
	}

	public void setReductionMaxAngle(double reductionMaxAngle) {
		ReductionMaxAngle = reductionMaxAngle;
	}

	public double getReductionZmag() {
		return ReductionZmag;
	}

	public void setReductionZmag(double reductionZmag) {
		ReductionZmag = reductionZmag;
	}

	public String getReductionStrategyString() {
		return ReductionStrategyString;
	}

	public void setReductionStrategyString(String reductionStrategyString) {
		ReductionStrategyString = reductionStrategyString;
	}

	public double getPctNormalFactor() {
		return PctNormalFactor;
	}

	public void setPctNormalFactor(double pctNormalFactor) {
		PctNormalFactor = pctNormalFactor;
	}

	public int getNodeMarkerCode() {
		return NodeMarkerCode;
	}

	public void setNodeMarkerCode(int nodeMarkerCode) {
		NodeMarkerCode = nodeMarkerCode;
	}

	public int getNodeMarkerWidth() {
		return NodeMarkerWidth;
	}

	public void setNodeMarkerWidth(int nodeMarkerWidth) {
		NodeMarkerWidth = nodeMarkerWidth;
	}

	public int getSwitchMarkerCode() {
		return SwitchMarkerCode;
	}

	public void setSwitchMarkerCode(int switchMarkerCode) {
		SwitchMarkerCode = switchMarkerCode;
	}

	public int getTransMarkerSize() {
		return TransMarkerSize;
	}

	public void setTransMarkerSize(int transMarkerSize) {
		TransMarkerSize = transMarkerSize;
	}

	public int getTransMarkerCode() {
		return TransMarkerCode;
	}

	public void setTransMarkerCode(int transMarkerCode) {
		TransMarkerCode = transMarkerCode;
	}

	public boolean isMarkSwitches() {
		return MarkSwitches;
	}

	public void setMarkSwitches(boolean markSwitches) {
		MarkSwitches = markSwitches;
	}

	public boolean isMarkTransformers() {
		return MarkTransformers;
	}

	public void setMarkTransformers(boolean markTransformers) {
		MarkTransformers = markTransformers;
	}

	public int getActiveLoadShapeClass() {
		return ActiveLoadShapeClass;
	}

	public void setActiveLoadShapeClass(int activeLoadShapeClass) {
		ActiveLoadShapeClass = activeLoadShapeClass;
	}

}
