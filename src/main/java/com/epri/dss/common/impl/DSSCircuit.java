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
		DEFAULT,
		STUBS,
		TAP_ENDS,
		MERGE_PARALLEL,
		BREAK_LOOP,
		DANGLING,
		SWITCHES
	}

	public class CktElementDef {
		public int cktElementClass;
		public int devHandle;
	}

	private int[] nodeBuffer;
	private int nodeBufferMax;
	private boolean busNameRedefined;
	private CktElement activeCktElement;

	// temp arrays for when the bus swap takes place
	private Bus[] savedBuses;
	private String[] savedBusNames;
	private int savedNumBuses;

	/* Global multiplier for every load */
	private double loadMultiplier;

	private boolean abortBusProcess;

	/* Topology from the first source, lazy evaluation */
	private CktTree branchList;
	/* Bus adjacency lists of PD and PC elements */
	private List<PCElement>[] busAdjPC;
	private List<PDElement>[] busAdjPD;

	private String caseName;

	protected int activeBusIndex;
	/* Fundamental and default base frequency */
	protected double fundamental;

	/* Flag for use by control elements to detect redefinition of buses */
	protected boolean controlBusNameRedefined;

	protected HashList busList;
	protected HashList autoAddBusList;
	protected HashList deviceList;
	protected CktElementDef[] deviceRef;  // type and handle of device

	// lists of pointers to different elements by class
	protected ArrayList<FaultObj> faults;
	protected ArrayList<CktElement> cktElements;
	protected ArrayList<PDElement> PDElements;
	protected ArrayList<PCElement> PCElements;
	protected ArrayList<ControlElem> controls;
	protected ArrayList<PCElement> sources;
	protected ArrayList<MeterElement> meterElements;
	protected ArrayList<SensorObj> sensors;
	protected ArrayList<MonitorObj> monitors;
	protected ArrayList<EnergyMeterObj> energyMeters;
	protected ArrayList<GeneratorObj> generators;
	protected ArrayList<StorageObj> storageElements;
	protected ArrayList<PVSystemObj> PVSystems;
	protected ArrayList<DSSObject> substations;
	protected ArrayList<TransformerObj> transformers;
	protected ArrayList<CapControlObj> capControls;
	protected ArrayList<RegControlObj> regControls;
	protected ArrayList<LineObj> lines;
	protected ArrayList<LoadObj> loads;
	protected ArrayList<CapacitorObj> shuntCapacitors;
	protected ArrayList<FeederObj> feeders;
	protected ArrayList<SwtControlObj> swtControls;

	protected ControlQueue controlQueue;

	protected SolutionObj solution;
	protected AutoAdd autoAddObj;

	// for AutoAdd stuff
	protected double UEWeight, lossWeight;

	protected int numUERegs, numLossRegs;
	protected int[] UERegs, lossRegs;

	protected double capacityStart, capacityIncrement;

	protected boolean trapezoidalIntegration;  // flag for trapezoidal integration
	protected boolean logEvents;

	protected String loadDurCurve;
	protected LoadShapeObj loadDurCurveObj;
	protected String priceCurve;
	protected PriceShapeObj priceCurveObj;

	protected int numDevices, numBuses, numNodes;
	protected int maxDevices, maxBuses, maxNodes;
	protected int incDevices, incBuses, incNodes;

	// bus and node stuff
	protected Bus[] buses;
	protected NodeBus[] mapNodeToBus;

	// flags
	protected boolean isSolved;
	protected boolean duplicatesAllowed;
	protected boolean zonesLocked;
	protected boolean meterZonesComputed;
	protected boolean positiveSequence;  // model is to be interpreted as pos seq

	// voltage limits
	/* Per unit voltage restraints for this circuit */
	protected double normalMinVolts, normalMaxVolts, emergMaxVolts, emergMinVolts;
	protected double[] legalVoltageBases;

	// global circuit multipliers
	protected double generatorDispatchReference,
		defaultGrowthFactor,
		defaultGrowthRate,
		genMultiplier,  // global multiplier for every generator
		harmMult;
	protected Complex defaultHourMult;

	protected double priceSignal;  // price signal for entire circuit

	// energy meter totals
	protected double[] registerTotals;

	protected LoadShapeObj defaultDailyShapeObj, defaultYearlyShapeObj;

	protected String currentDirectory;

	protected ReductionStrategyType reductionStrategy;
	protected double reductionMaxAngle, reductionZmag;
	protected String reductionStrategyString;

	protected double pctNormalFactor;

	/* Plot markers */
	protected int nodeMarkerCode, nodeMarkerWidth, SwitchMarkerCode;
	protected int transMarkerSize, transMarkerCode;

	protected boolean markSwitches;
	protected boolean markTransformers;

	protected int activeLoadShapeClass;

	public DSSCircuit(String aName) {
		super("Circuit");

		this.isSolved = false;
		DSSGlobals.getInstance().getSolutionClass().newObject(getName());
		this.solution = SolutionImpl.activeSolutionObj;

		setLocalName(aName.toLowerCase());

		setCaseName(aName);  // default case name to "circuitname"; sets circuitName_

		this.fundamental = DSSGlobals.getInstance().getDefaultBaseFreq();
		setActiveCktElement(null);
		this.activeBusIndex = 0;  // always a bus

		// initial allocations increased from 100 to 1000 to speed things up

		this.maxBuses   = 1000;  // good sized allocation to start
		this.maxDevices = 1000;
		this.maxNodes   = 3 * maxBuses;
		this.incDevices = 1000;
		this.incBuses   = 1000;
		this.incNodes   = 3000;

		// allocate some nominal sizes
		this.busList    = new HashListImpl(900);  // bus name list nominal size to start; gets reallocated
		this.deviceList = new HashListImpl(900);
		this.autoAddBusList = new HashListImpl(100);

		this.numBuses   = 0;  // eventually allocate a single source
		this.numDevices = 0;
		this.numNodes   = 0;

		this.faults       = new ArrayList<FaultObj>(2);
		this.cktElements  = new ArrayList<CktElement>(1000);
		this.PDElements   = new ArrayList<PDElement>(1000);
		this.PCElements   = new ArrayList<PCElement>(1000);
		this.controls  = new ArrayList<ControlElem>(10);
		this.sources      = new ArrayList<PCElement>(10);
		this.meterElements= new ArrayList<MeterElement>(20);
		this.monitors     = new ArrayList<MonitorObj>(20);
		this.energyMeters = new ArrayList<EnergyMeterObj>(5);
		this.sensors      = new ArrayList<SensorObj>(5);
		this.generators   = new ArrayList<GeneratorObj>(5);
		this.storageElements = new ArrayList<StorageObj>(5);
		this.PVSystems    = new ArrayList<PVSystemObj>(5);
		this.feeders      = new ArrayList<FeederObj>(10);
		this.substations  = new ArrayList<DSSObject>(5);
		this.transformers = new ArrayList<TransformerObj>(10);
		this.capControls  = new ArrayList<CapControlObj>(10);
		this.swtControls  = new ArrayList<SwtControlObj>(50);
		this.regControls  = new ArrayList<RegControlObj>(5);
		this.lines        = new ArrayList<LineObj>(1000);
		this.loads        = new ArrayList<LoadObj>(1000);
		this.shuntCapacitors = new ArrayList<CapacitorObj>(20);

		this.buses     = new Bus[this.maxBuses];
		this.mapNodeToBus = new NodeBus[this.maxNodes];
		this.deviceRef = new CktElementDef[maxDevices];

		this.controlQueue = new ControlQueueImpl();

		this.legalVoltageBases = new double[8];
		// default voltage bases
		this.legalVoltageBases[0] =   0.208;
		this.legalVoltageBases[1] =   0.480;
		this.legalVoltageBases[2] =  12.47;
		this.legalVoltageBases[3] =  24.9;
		this.legalVoltageBases[4] =  34.5;
		this.legalVoltageBases[5] = 115.0;
		this.legalVoltageBases[6] = 230.0;
		this.legalVoltageBases[7] =   0.0;  // terminates array

		this.nodeBufferMax = 20;
		this.nodeBuffer    = new int[this.nodeBufferMax];  // to hold the nodes

		// init global circuit load and harmonic source multipliers
		setLoadMultiplier(1.0);
		this.genMultiplier = 1.0;
		this.harmMult = 1.0;

		this.priceSignal = 25.0;  // $25/MWh

		// factors for AutoAdd
		this.UEWeight    = 1.0;  // default to weighting UE same as losses
		this.lossWeight  = 1.0;
		this.numUERegs   = 1;
		this.numLossRegs = 1;
		this.UERegs      = new int[numUERegs];
		this.lossRegs    = new int[numLossRegs];
		this.UERegs[0]   = 10;  // overload UE
		this.lossRegs[0] = 13;  // zone losses

		this.capacityStart = 0.9;  // for capacity search
		this.capacityIncrement = 0.005;

		this.loadDurCurve    = "";
		this.loadDurCurveObj = null;
		this.priceCurve    = "";
		this.priceCurveObj = null;

		// flags
		this.duplicatesAllowed  = false;
		this.zonesLocked        = false;  // meter zones recomputed after each change
		this.meterZonesComputed = false;
		this.positiveSequence   = false;

		this.normalMinVolts = 0.95;
		this.normalMaxVolts = 1.05;
		this.emergMaxVolts  = 1.08;
		this.emergMinVolts  = 0.90;

		this.nodeMarkerCode   = 16;
		this.nodeMarkerWidth  = 1;
		this.markSwitches     = false;
		this.markTransformers = false;
		this.SwitchMarkerCode = 5;
		this.transMarkerCode  = 35;
		this.transMarkerSize  = 1;


		this.trapezoidalIntegration = false;  // default to Euler method
		this.logEvents = false;

		this.generatorDispatchReference = 0.0;
		this.defaultGrowthRate = 1.025;
		this.defaultGrowthFactor = 1.0;

		this.defaultDailyShapeObj  = (LoadShapeObj) DSSGlobals.getInstance().getLoadShapeClass().find("default");
		this.defaultYearlyShapeObj = (LoadShapeObj) DSSGlobals.getInstance().getLoadShapeClass().find("default");

		this.currentDirectory = "";

		setBusNameRedefined(true);  // set to force rebuild of bus and node lists

		this.savedBuses = null;
		this.savedBusNames = null;

		this.reductionStrategy = ReductionStrategyType.DEFAULT;
		this.reductionMaxAngle = 15.0;
		this.reductionZmag = 0.02;

		/* Misc objects */
		this.autoAddObj = new AutoAddImpl();

		this.branchList = null;
		this.busAdjPC = null;
		this.busAdjPD = null;
	}

	private void addDeviceHandle(int handle) {
		if (numDevices > maxDevices) {
			maxDevices = maxDevices + incDevices;
			deviceRef = (CktElementDef[]) Utilities.resizeArray(deviceRef, maxDevices);
		}
		deviceRef[numDevices].devHandle = handle;  // index into CktElements
		deviceRef[numDevices].cktElementClass = DSSGlobals.getInstance().getLastClassReferenced();
	}

	private void addABus() {
		if (numBuses > maxBuses) {
			maxBuses += incBuses;
			buses = (Bus[]) Utilities.resizeArray(buses, maxBuses);
		}
	}

	private void addANodeBus() {
		if (numNodes > maxNodes) {
			maxNodes += incNodes;
			mapNodeToBus = (NodeBus[]) Utilities.resizeArray(mapNodeToBus, maxNodes);
		}
	}

	private int addBus(String busName, int nNodes) {

		if (busName.length() == 0) {  // error in busname
			DSSGlobals.getInstance().doErrorMsg("DSSCircuit.addBus", "BusName for object \"" + activeCktElement.getName() + "\" is null.",
					"Error in definition of object.", 424);
			for (int i = 0; i < activeCktElement.getNConds(); i++) nodeBuffer[i] = 0;
			return 0;
		}

		int result = busList.find(busName);
		if (result == 0) {
			result = busList.add(busName);  // result is index of bus
			numBuses += 1;
			addABus();  // allocates more memory if necessary
			buses[numBuses] = new DSSBus();
		}

		/* Define nodes belonging to the bus */
		/* Replace nodeBuffer values with global reference number */
		int nodeRef;
		for (int i = 0; i < nNodes; i++) {
			nodeRef = buses[result].add(nodeBuffer[i]);
			if (nodeRef == numNodes) {  // this was a new node so add a nodeToBus element ????
				addANodeBus();  // allocates more memory if necessary
				mapNodeToBus[numNodes].busRef  = result;
				mapNodeToBus[numNodes].nodeNum = nodeBuffer[i];
			}
			nodeBuffer[i] = nodeRef;  // swap out in preparation to setNodeRef call
		}
		return result;
	}

	public void setActiveCktElement(CktElement value) {
		activeCktElement = value;
		DSSGlobals.getInstance().setActiveDSSObject(value);
	}

	public CktElement getActiveCktElement() {
		return activeCktElement;
	}

	public void setBusNameRedefined(boolean value) {
		busNameRedefined = value;

		if (value) {
			// force rebuilding of system Y if bus def has changed
			solution.setSystemYChanged(true);
			// so controls will know buses redefined
			controlBusNameRedefined = true;
		}
	}

	public boolean isBusNameRedefined() {
		return busNameRedefined;
	}

	/* Total circuit PD element losses */
	public Complex getLosses() {
		Complex result = Complex.ZERO;
		for (PDElement pdElem : PDElements) {
			if (pdElem.isEnabled()) {
				/* Ignore shunt elements */
				if (!pdElem.isShunt())
					result = result.add(pdElem.getLosses());
			}
		}
		return result;
	}

	public void setLoadMultiplier(double value) {
		if (value != loadMultiplier) {
			// may have to change the Y matrix if the load multiplier has changed
			switch (solution.getLoadModel()) {
			case DSSGlobals.ADMITTANCE:
				invalidateAllPCElements();
				break;
			}
		}
		loadMultiplier = value;
	}

	public double getLoadMultiplier() {
		return 0.0;
	}

	private void saveBusInfo() {
		/* Save existing bus definitions and names for info that needs to be restored */
		savedBuses = new DSSBus[numBuses];
		savedBusNames = new String[numBuses];

		for (int i = 0; i < numBuses; i++) {
			savedBuses[i] = buses[i];
			savedBusNames[i] = busList.get(i);
		}
		savedNumBuses = numBuses;
	}

	private void restoreBusInfo() {
		Bus bus;
		/* Restore kV bases, other values to buses still in the list */
		for (int i = 0; i < savedNumBuses; i++) {
			int idx = busList.find(savedBusNames[i]);
			if (idx != -1) {
				bus = savedBuses[i];
				buses[idx].setKVBase(bus.getKVBase());
				buses[idx].setX(bus.getX());
				buses[idx].setY(bus.getY());
				buses[idx].setCoordDefined(bus.isCoordDefined());
				buses[idx].setKeep(bus.isKeep());
				/* Restore voltages in new bus def that existed in old bus def */
				if (bus.getVBus() != null) {
					for (int j = 0; j < bus.getNumNodesThisBus(); j++) {
						// find index in new bus for j-th node in old bus
						int jdx = buses[idx].findIdx(bus.getNum(j));
						if (jdx > -1) buses[idx].getVBus()[jdx] = bus.getVBus()[j];
					}
				}
			}
			savedBusNames[i] = "";  // de-allocate string
		}

		if (savedBuses != null)
			for (int i = 0; i < savedNumBuses; i++)
				savedBuses[i] = null;  // gets rid of old bus voltages too

		savedBuses = new Bus[0]; //ReallocMem(SavedBuses, 0);
		savedBusNames = new String[0]; //ReallocMem(SavedBusNames, 0);
	}

	private boolean saveMasterFile() {
		boolean Result = false;
		try {
			File fd = new File("Master.dss");
			PrintStream f = new PrintStream(fd);

			f.println("clear");
			f.println("new circuit." + getName());
			f.println();
			if (positiveSequence) f.println("set cktModel=Positive");
			if (duplicatesAllowed) f.println("set allowDup=yes");
			f.println();

			// Write Redirect for all populated DSS Classes  Except Solution Class
			for (int i = 0; i < DSSGlobals.getInstance().getSavedFileList().size(); i++)
				f.println("redirect " + DSSGlobals.getInstance().getSavedFileList().get(i - 1));

			if (new File("BusCoords.dss").exists()) {
				f.println("makeBusList");
				f.println("BusCoords BusCoords.dss");
			}

			f.close();
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
		String currDir, saveDir;
		DSSGlobals globals = DSSGlobals.getInstance();

		boolean result = true;
		/* Write out all energy meter zones to separate subdirectories */
		saveDir = globals.getCurrentDirectory();
		for (EnergyMeterObj Meter : energyMeters) {
			currDir = Meter.getName();
			if (new File(currDir).mkdir()) {
				globals.setCurrentDirectory(currDir);
				Meter.saveZone(currDir);
				globals.setCurrentDirectory(saveDir);
			} else {
				DSSGlobals.getInstance().doSimpleMsg("Cannot create directory: " + currDir, 436);
				result = false;
				globals.setCurrentDirectory(saveDir);
				break;
			}
		}
		return result;
	}

	private boolean saveBusCoords() {
		boolean result = false;

		try {
			File fd = new File("BusCoords.dss");
			PrintStream f = new PrintStream(fd);

			for (int i = 0; i < numBuses; i++)
				if (buses[i].isCoordDefined())
					f.println(Utilities.checkForBlanks(busList.get(i)) + String.format(", %-g, %-g", buses[i].getX(), buses[i].getY()));

			f.close();

			result = true;
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error creating BusCoords.dss.", 437);
		}

		return result;
	}

	/* Reallocate the device list to improve the performance of searches */
	private void reallocDeviceList() {
		if (logEvents) Utilities.logThisEvent("Reallocating device list");
		HashListImpl tempList = new HashListImpl(2 * numDevices);

		for (int i = 0; i < deviceList.listSize(); i++)
			tempList.add(deviceList.get(i));

		deviceList = tempList;
	}

	public void setCaseName(String value) {
		caseName = value;
		DSSGlobals.getInstance().setCircuitName_(value + "_");
	}

	public String getCaseName() {
		return caseName;
	}

	public String getName() {
		return getLocalName();
	}

	/* Adds last DSS object created to circuit */
	public void addCktElement(int handle) {
		// update lists that keep track of individual circuit elements
		numDevices += 1;

		// resize deviceList if no. of devices greatly exceeds allocation
		if (numDevices > 2 * deviceList.getInitialAllocation())
			reallocDeviceList();
		deviceList.add(activeCktElement.getName());
		cktElements.add(activeCktElement);

		/* Build lists of PC and PD elements */
		if (activeCktElement.getDSSObjType() == DSSClassDefs.PD_ELEMENT) {
			PDElements.add((PDElement) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.PC_ELEMENT) {
			PCElements.add((PCElement) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.CTRL_ELEMENT) {
			controls.add((ControlElem) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.METER_ELEMENT) {
			meterElements.add((MeterElement) activeCktElement);
		}

		/* Build lists of special elements and generic types */
		if (activeCktElement.getDSSObjType() == DSSClassDefs.MON_ELEMENT) {
			monitors.add((MonitorObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.ENERGY_METER) {
			energyMeters.add((EnergyMeterObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.SENSOR_ELEMENT) {
			sensors.add((SensorObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.GEN_ELEMENT) {
			generators.add((GeneratorObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.SOURCE) {
			sources.add((PCElement) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.CAP_CONTROL) {
			capControls.add((CapControlObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.SWT_CONTROL) {
			swtControls.add((SwtControlObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.REG_CONTROL) {
			regControls.add((RegControlObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.LOAD_ELEMENT) {
			loads.add((LoadObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.CAP_ELEMENT) {
			shuntCapacitors.add((CapacitorObj) activeCktElement);
		}
		/* Keep lines, transformer, and faults in PDElements and
		 * separate lists so we can find them quickly. */
		else if (activeCktElement.getDSSObjType() == DSSClassDefs.XFMR_ELEMENT) {
			transformers.add((TransformerObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.LINE_ELEMENT) {
			lines.add((LineObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.FAULTOBJECT) {
			faults.add((FaultObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.FEEDER_ELEMENT) {
			feeders.add((FeederObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.STORAGE_ELEMENT) {
			storageElements.add((StorageObj) activeCktElement);
		} else if (activeCktElement.getDSSObjType() == DSSClassDefs.PVSYSTEM_ELEMENT) {
			PVSystems.add((PVSystemObj) activeCktElement);
		}

		// AddDeviceHandle(Handle);  // keep track of this device result is handle
		addDeviceHandle(cktElements.size());  // handle is global index into CktElements
		activeCktElement.setHandle(cktElements.size());
	}

	/**
	 * Totalize all energy meters in the problem.
	 */
	public void totalizeMeters() {
		for (int i = 0; i < EnergyMeterObj.NumEMRegisters; i++)
			registerTotals[i] = 0.0;

		for (int i = 0; i < energyMeters.size(); i++) {
			EnergyMeterObj Meter = energyMeters.get(i);
			for (int j = 0; j < EnergyMeterObj.NumEMRegisters; i++)
				registerTotals[i] += Meter.getRegisters()[i] * Meter.getTotalsMask()[i];
		}
	}

	private double sumSelectedRegisters(double[] mtrRegisters, int[] regs, int count) {
		double result = 0.0;
		for (int i = 0; i < count; i++)
			result += mtrRegisters[regs[i]];
		return result;
	}
	public boolean computeCapacity() {
		boolean result = false;
		if (energyMeters.size() == 0) {
			DSSGlobals.getInstance().doSimpleMsg("Cannot compute system capacity with EnergyMeter objects!", 430);
			return result;
		}

		if (numUERegs == 0) {
			DSSGlobals.getInstance().doSimpleMsg("Cannot compute system capacity with no UE resisters defined. Use \"set UERegs=(...)\" command.", 431);
			return result;
		}

		solution.setMode(Dynamics.SNAPSHOT);
		setLoadMultiplier(capacityStart);
		boolean CapacityFound = false;

		while ((loadMultiplier <= 1.0) && !CapacityFound) {
			DSSGlobals.getInstance().getEnergyMeterClass().resetAll();
			solution.solve();
			DSSGlobals.getInstance().getEnergyMeterClass().sampleAll();
			totalizeMeters();

			// check for non-zero in UEregs
			if (sumSelectedRegisters(registerTotals, UERegs, numUERegs) != 0.0)
				CapacityFound = true;
			// loadMultiplier is a property ...
			if (!CapacityFound)
				loadMultiplier += capacityIncrement;
		}

		if (loadMultiplier > 1.0) setLoadMultiplier(1.0);
		result = true;

		return result;
	}

	public boolean save(String dir) {
		DSSGlobals globals = DSSGlobals.getInstance();
		String currDir, saveDir;
		boolean result = false;

		// make a new subfolder in the present folder based on the circuit
		// name and a unique sequence number
		saveDir = globals.getCurrentDirectory();

		boolean success = false;
		if (dir.length() == 0) {
			dir = getName();

			currDir = dir;
			for (int i = 0; i < 999; i++) {  // Find a unique dir name
				File F = new File(currDir);
				if (!F.exists()) {
					if (F.mkdir()) {
		            	globals.setCurrentDirectory(currDir);
						success = true;
						break;
					}
				}
				currDir = dir + String.format("%.3d", i);
			}
		} else {
			File F = new File(dir);
			if (!F.exists()) {
				currDir = dir;
				F = new File(currDir);
				if (F.mkdir()) {
		            globals.setCurrentDirectory(currDir);
					success = true;
				}
			} else {  // exists - overwrite
				currDir = dir;
		        globals.setCurrentDirectory(currDir);
				success = true;
			}
		}

		if (!success) {
			DSSGlobals.getInstance().doSimpleMsg("Could not create a folder \"" + dir + "\" for saving the circuit.", 432);
			return result;
		}

		// this list keeps track of all files saved
		DSSGlobals.getInstance().setSavedFileList(new ArrayList<String>());

		// initialize so we will know when we have saved the circuit elements
		for (CktElement elem : cktElements)
			elem.setHasBeenSaved(false);

		// initialize so we don't save a class twice
		for (DSSClass cls : DSSGlobals.getInstance().getDSSClassList())
			cls.setSaved(false);

		// ignore feeder class -- gets saved with EnergyMeters
		//Globals.getFeederClass().setSaved(true);

		// Define voltage sources first
		success = Utilities.writeVsourceClassFile(DSSClassDefs.getDSSClass("vsource"), true);
		// write library files so that they will be available to lines, loads, etc
		/* Use default filename=classname */
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("wiredata"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("cndata"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("tsdata"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linegeometry"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linecode"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linespacing"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("linecode"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("xfmrcode"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("growthshape"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("TCC_Curve"), "", false);
		if (success) success = Utilities.writeClassFile(DSSClassDefs.getDSSClass("Spectrum"), "", false);
		if (success) success = saveFeeders();  // save feeders first
		if (success) success = saveDSSObjects();  // save rest to the objects
		if (success) success = saveBusCoords();
		if (success) success = saveMasterFile();

		if (success) {
			DSSGlobals.getInstance().doSimpleMsg("Circuit saved in directory: " + globals.getCurrentDirectory(), 433);
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error attempting to save circuit in " + globals.getCurrentDirectory(), 434);
		}
		// return to original directory
		globals.setCurrentDirectory(saveDir);

		return true;
	}

	public void processBusDefs() {
		String busName;
		MutableInt nNodes = new MutableInt();
		int np = activeCktElement.getNPhases();
		int nCond = activeCktElement.getNConds();

		// use parser functions to decode
		Parser.getInstance().setToken(activeCktElement.getFirstBus());

		for (int iTerm = 0; iTerm < activeCktElement.getNTerms(); iTerm++) {
			boolean nodesOK = true;
			// assume normal phase rotation for default
			for (int i = 0; i < np; i++)
				nodeBuffer[i] = i;  // set up buffer with defaults

			// default all other conductors to a ground connection
			// uf user wants them ungrounded, must be specified explicitly!
			for (int i = np + 1; i < nCond; i++)
				nodeBuffer[i] = 0;

			// parser will override bus connection if any specified
			busName = Parser.getInstance().parseAsBusName(nNodes, nodeBuffer);

			// check for error in node specification
			for (int j = 0; j < nNodes.intValue(); j++) {
				if (nodeBuffer[j] < 0) {
					int retval = DSSGlobals.getInstance().getDSSForms().messageDlg("Error in node specification for element: \""
						+ activeCktElement.getParentClass().getName() + "." + activeCktElement.getName() + "\"" + DSSGlobals.CRLF +
						"Bus Spec: \"" + Parser.getInstance().getToken() + "\"", false);
					nodesOK = false;
					if (retval == -1) {
						abortBusProcess = true;
						DSSGlobals.getInstance().appendGlobalResult("Aborted bus process.");
						return;
					}
					break;
				}
			}

			// node-terminal connnections
			// Caution: addBus replaces values in nodeBuffer to correspond
			// with global node reference number
			if (nodesOK) {
				activeCktElement.setActiveTerminalIdx(iTerm);
				activeCktElement.getActiveTerminal().setBusRef(addBus(busName, nCond));
				activeCktElement.setNodeRef(iTerm, nodeBuffer);  // for active circuit
			}
			Parser.getInstance().setToken(activeCktElement.getNextBus());
		}
	}

	/**
	 * Redo all bus and node lists.
	 */
	public void reProcessBusDefs() {
		if (logEvents) Utilities.logThisEvent("Reprocessing bus definitions");

		abortBusProcess = false;
		saveBusInfo();  // so we don't have to keep re-doing this
		// keeps present definitions of bus objects until new ones created

		// get rid of old bus lists
		busList = null;  // clears hash list of bus names for adding more
		busList = new HashListImpl(numDevices);  // won't have many more buses than this

		numBuses = 0;  // leave allocations same, but start count over
		numNodes = 0;

		// now redo all enabled circuit elements
		CktElement cktElementSave = activeCktElement;
		for (int i = 0; i < cktElements.size(); i++) {
			setActiveCktElement( cktElements.get(i) );
			if (activeCktElement.isEnabled())
				processBusDefs();
			if (abortBusProcess)
				return;
		}

		setActiveCktElement(cktElementSave);  // restore active circuit element

		for (int i = 0; i < numBuses; i++) buses[i].allocateBusVoltages();
		for (int i = 0; i < numBuses; i++) buses[i].allocateBusCurrents();

		restoreBusInfo();     // frees old bus info too
		doResetMeterZones();  // fix up meter zones to correspond

		setBusNameRedefined(false);  // get ready for next time
	}

	public void doResetMeterZones() {
		/* Do this only if meterZones unlocked. Normally, zones will remain
		 * unlocked so that all changes to the circuit will result in rebuilding
		 * the lists */
		if (!meterZonesComputed || !zonesLocked) {
			if (logEvents) Utilities.logThisEvent("Resetting meter zones");
			DSSGlobals.getInstance().getEnergyMeterClass().resetMeterZonesAll();
			meterZonesComputed = true;
			if (logEvents) Utilities.logThisEvent("Done resetting meter zones");
		}

		freeTopology();
	}

	public int setElementActive(String fullObjectName) {
		int result = 0;
		StringBuffer devType = new StringBuffer();
		StringBuffer devName = new StringBuffer();

		Utilities.parseObjectClassandName(fullObjectName, devType, devName);
		int devClassIndex = DSSGlobals.getInstance().getClassNames().find(devType.toString());
		if (devClassIndex == 0)
			devClassIndex = DSSGlobals.getInstance().getLastClassReferenced();
		int devIndex = deviceList.find(devName.toString());
		while (devIndex >= 0) {
			if (deviceRef[devIndex].cktElementClass == devClassIndex) {  // we got a match
				DSSGlobals.getInstance().setActiveDSSClass(DSSGlobals.getInstance().getDSSClassList().get(devClassIndex));
				DSSGlobals.getInstance().setLastClassReferenced(devClassIndex);
				result = deviceRef[devIndex].devHandle;
				// ActiveDSSClass.Active = Result;
				// ActiveCktElement = ActiveDSSClass.getActiveObj;
				setActiveCktElement( cktElements.get(result) );
				break;
			}
			devIndex = deviceList.findNext();  // could be duplicates
		}

		DSSGlobals.getInstance().setCmdResult(result);

		return result;
	}

	public void invalidateAllPCElements() {
		for (PCElement p : PCElements)
			p.setYPrimInvalid(true);

		// force rebuild of matrix on next solution
		solution.setSystemYChanged(true);
	}

	public void debugDump(PrintStream f) {
		f.println("numBuses= " + numBuses);
		f.println("numNodes= " + numNodes);
		f.println("numDevices= " + numDevices);
		f.println("BusList:");
		for (int i = 0; i < numBuses; i++) {
			f.printf("  %12s", busList.get(i));
			f.printf(" (" + buses[i].getNumNodesThisBus() + " nodes)");
			for (int j = 0; j < buses[i].getNumNodesThisBus(); j++) {
				f.print(" " + buses[i].getNum(j));
			}
			f.println();
		}
		f.println("DeviceList:");
		for (int i = 0; i < numDevices; i++) {
			f.printf("  %12s%s", deviceList.get(i), DSSGlobals.CRLF);
			setActiveCktElement( cktElements.get(i) );
			if (!activeCktElement.isEnabled())
				f.print("  DISABLED");
			f.println();
		}
		f.println("NodeToBus Array:");
		for (int i = 0; i < numNodes; i++) {
			int j = mapNodeToBus[i].busRef;
			f.print("  " + i + " " + j + " (=" +busList.get(j) + "." + mapNodeToBus[i].nodeNum + ")");
			f.println();
		}
	}

	/**
	 * Access to topology from the first source
	 */
	public CktTree getTopology() {
		if (branchList == null) {
			/* Initialize all circuit elements and buses to not checked, then build a new tree */
			for (CktElement elem : cktElements) {
				elem.setChecked(false);
				for (int i = 0; i < elem.getNTerms(); i++)
					elem.getTerminals()[i].setChecked(false);
				elem.setIsolated(true);  // till proven otherwise
			}

			for (int i = 0; i < numBuses; i++)
				buses[i].setBusChecked(false);

			branchList = CktTreeImpl.getIsolatedSubArea(sources.get(0), true);  // calls back to build adjacency lists
		}
		return branchList;
	}

	public void freeTopology() {
		if (branchList != null)
			branchList = null;
		if (busAdjPC != null)
			CktTreeImpl.freeAndNilBusAdjacencyLists(busAdjPD, busAdjPC);
	}

	public List<PDElement>[] getBusAdjacentPDLists() {
		if (busAdjPD == null)
			CktTreeImpl.buildActiveBusAdjacencyLists(busAdjPD, busAdjPC);
		return busAdjPD;
	}

	public List<PCElement>[] getBusAdjacentPCLists() {
		if (busAdjPC == null)
			CktTreeImpl.buildActiveBusAdjacencyLists(busAdjPD, busAdjPC);
		return busAdjPC;
	}

	public int getActiveBusIndex() {
		return activeBusIndex;
	}

	public void setActiveBusIndex(int index) {
		activeBusIndex = index;
	}

	public double getFundamental() {
		return fundamental;
	}

	public void setFundamental(double value) {
		fundamental = value;
	}

	public boolean isControlBusNameRedefined() {
		return controlBusNameRedefined;
	}

	public void setControlBusNameRedefined(boolean redefined) {
		controlBusNameRedefined = redefined;
	}

	public HashList getBusList() {
		return busList;
	}

	public void setBusList(HashList list) {
		busList = list;
	}

	public HashList getAutoAddBusList() {
		return autoAddBusList;
	}

	public void setAutoAddBusList(HashList list) {
		autoAddBusList = list;
	}

	public HashList getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(HashList list) {
		deviceList = list;
	}

	public CktElementDef[] getDeviceRef() {
		return deviceRef;
	}

	public void setDeviceRef(CktElementDef[] ref) {
		deviceRef = ref;
	}

	public ArrayList<FaultObj> getFaults() {
		return faults;
	}

	public void setFaults(ArrayList<FaultObj> list) {
		faults = list;
	}

	public ArrayList<CktElement> getCktElements() {
		return cktElements;
	}

	public void setCktElements(ArrayList<CktElement> elements) {
		cktElements = elements;
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
		return controls;
	}

	public void setDSSControls(ArrayList<ControlElem> dSSControls) {
		controls = dSSControls;
	}

	public ArrayList<PCElement> getSources() {
		return sources;
	}

	public void setSources(ArrayList<PCElement> list) {
		sources = list;
	}

	public ArrayList<MeterElement> getMeterElements() {
		return meterElements;
	}

	public void setMeterElements(ArrayList<MeterElement> elements) {
		meterElements = elements;
	}

	public ArrayList<SensorObj> getSensors() {
		return sensors;
	}

	public void setSensors(ArrayList<SensorObj> list) {
		sensors = list;
	}

	public ArrayList<MonitorObj> getMonitors() {
		return monitors;
	}

	public void setMonitors(ArrayList<MonitorObj> list) {
		monitors = list;
	}

	public ArrayList<EnergyMeterObj> getEnergyMeters() {
		return energyMeters;
	}

	public void setEnergyMeters(ArrayList<EnergyMeterObj> meters) {
		energyMeters = meters;
	}

	public ArrayList<GeneratorObj> getGenerators() {
		return generators;
	}

	public void setGenerators(ArrayList<GeneratorObj> list) {
		generators = list;
	}

	public ArrayList<StorageObj> getStorageElements() {
		return storageElements;
	}

	public void setStorageElements(ArrayList<StorageObj> elements) {
		storageElements = elements;
	}

	public ArrayList<PVSystemObj> getPVSystems() {
		return PVSystems;
	}

	public void setPVSystems(ArrayList<PVSystemObj> pVSystems) {
		PVSystems = pVSystems;
	}

	public ArrayList<DSSObject> getSubstations() {
		return substations;
	}

	public void setSubstations(ArrayList<DSSObject> stations) {
		substations = stations;
	}

	public ArrayList<TransformerObj> getTransformers() {
		return transformers;
	}

	public void setTransformers(ArrayList<TransformerObj> trx) {
		transformers = trx;
	}

	public ArrayList<CapControlObj> getCapControls() {
		return capControls;
	}

	public void setCapControls(ArrayList<CapControlObj> controls) {
		capControls = controls;
	}

	public ArrayList<RegControlObj> getRegControls() {
		return regControls;
	}

	public void setRegControls(ArrayList<RegControlObj> controls) {
		regControls = controls;
	}

	public ArrayList<LineObj> getLines() {
		return lines;
	}

	public void setLines(ArrayList<LineObj> list) {
		lines = list;
	}

	public ArrayList<LoadObj> getLoads() {
		return loads;
	}

	public void setLoads(ArrayList<LoadObj> list) {
		loads = list;
	}

	public ArrayList<CapacitorObj> getShuntCapacitors() {
		return shuntCapacitors;
	}

	public void setShuntCapacitors(ArrayList<CapacitorObj> capacitors) {
		shuntCapacitors = capacitors;
	}

	public ArrayList<FeederObj> getFeeders() {
		return feeders;
	}

	public void setFeeders(ArrayList<FeederObj> list) {
		feeders = list;
	}

	public ArrayList<SwtControlObj> getSwtControls() {
		return swtControls;
	}

	public void setSwtControls(ArrayList<SwtControlObj> controls) {
		swtControls = controls;
	}

	public ControlQueue getControlQueue() {
		return controlQueue;
	}

	public void setControlQueue(ControlQueue queue) {
		controlQueue = queue;
	}

	public SolutionObj getSolution() {
		return solution;
	}

	public void setSolution(SolutionObj sol) {
		solution = sol;
	}

	public AutoAdd getAutoAddObj() {
		return autoAddObj;
	}

	public void setAutoAddObj(AutoAdd autoAdd) {
		autoAddObj = autoAdd;
	}

	public double getUEWeight() {
		return UEWeight;
	}

	public void setUEWeight(double uEWeight) {
		UEWeight = uEWeight;
	}

	public double getLossWeight() {
		return lossWeight;
	}

	public void setLossWeight(double weight) {
		lossWeight = weight;
	}

	public int getNumUERegs() {
		return numUERegs;
	}

	public void setNumUERegs(int num) {
		numUERegs = num;
	}

	public int getNumLossRegs() {
		return numLossRegs;
	}

	public void setNumLossRegs(int num) {
		numLossRegs = num;
	}

	public int[] getUERegs() {
		return UERegs;
	}

	public void setUERegs(int[] uERegs) {
		UERegs = uERegs;
	}

	public int[] getLossRegs() {
		return lossRegs;
	}

	public void setLossRegs(int[] regs) {
		lossRegs = regs;
	}

	public double getCapacityStart() {
		return capacityStart;
	}

	public void setCapacityStart(double capacity) {
		capacityStart = capacity;
	}

	public double getCapacityIncrement() {
		return capacityIncrement;
	}

	public void setCapacityIncrement(double increment) {
		capacityIncrement = increment;
	}

	public boolean isTrapezoidalIntegration() {
		return trapezoidalIntegration;
	}

	public void setTrapezoidalIntegration(boolean trapezoidal) {
		trapezoidalIntegration = trapezoidal;
	}

	public boolean isLogEvents() {
		return logEvents;
	}

	public void setLogEvents(boolean log) {
		logEvents = log;
	}

	public String getLoadDurCurve() {
		return loadDurCurve;
	}

	public void setLoadDurCurve(String curve) {
		loadDurCurve = curve;
	}

	public LoadShapeObj getLoadDurCurveObj() {
		return loadDurCurveObj;
	}

	public void setLoadDurCurveObj(LoadShapeObj curveObj) {
		loadDurCurveObj = curveObj;
	}

	public String getPriceCurve() {
		return priceCurve;
	}

	public void setPriceCurve(String curve) {
		priceCurve = curve;
	}

	public PriceShapeObj getPriceCurveObj() {
		return priceCurveObj;
	}

	public void setPriceCurveObj(PriceShapeObj curveObj) {
		priceCurveObj = curveObj;
	}

	public int getNumDevices() {
		return numDevices;
	}

	public void setNumDevices(int num) {
		numDevices = num;
	}

	public int getNumBuses() {
		return numBuses;
	}

	public void setNumBuses(int num) {
		numBuses = num;
	}

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int num) {
		numNodes = num;
	}

	public int getMaxDevices() {
		return maxDevices;
	}

	public void setMaxDevices(int max) {
		maxDevices = max;
	}

	public int getMaxBuses() {
		return maxBuses;
	}

	public void setMaxBuses(int max) {
		maxBuses = max;
	}

	public int getMaxNodes() {
		return maxNodes;
	}

	public void setMaxNodes(int max) {
		maxNodes = max;
	}

	public int getIncDevices() {
		return incDevices;
	}

	public void setIncDevices(int inc) {
		incDevices = inc;
	}

	public int getIncBuses() {
		return incBuses;
	}

	public void setIncBuses(int inc) {
		incBuses = inc;
	}

	public int getIncNodes() {
		return incNodes;
	}

	public void setIncNodes(int inc) {
		incNodes = inc;
	}

	public Bus[] getBuses() {
		return buses;
	}

	public void setBuses(Bus[] list) {
		buses = list;
	}

	public NodeBus[] getMapNodeToBus() {
		return mapNodeToBus;
	}

	public void setMapNodeToBus(NodeBus[] map) {
		mapNodeToBus = map;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public void setIsSolved(boolean solved) {
		isSolved = solved;
	}

	public boolean isDuplicatesAllowed() {
		return duplicatesAllowed;
	}

	public void setDuplicatesAllowed(boolean allowed) {
		duplicatesAllowed = allowed;
	}

	public boolean isZonesLocked() {
		return zonesLocked;
	}

	public void setZonesLocked(boolean locked) {
		zonesLocked = locked;
	}

	public boolean isMeterZonesComputed() {
		return meterZonesComputed;
	}

	public void setMeterZonesComputed(boolean computed) {
		meterZonesComputed = computed;
	}

	public boolean isPositiveSequence() {
		return positiveSequence;
	}

	public void setPositiveSequence(boolean value) {
		positiveSequence = value;
	}

	public double getNormalMinVolts() {
		return normalMinVolts;
	}

	public void setNormalMinVolts(double min) {
		normalMinVolts = min;
	}

	public double getNormalMaxVolts() {
		return normalMaxVolts;
	}

	public void setNormalMaxVolts(double max) {
		normalMaxVolts = max;
	}

	public double getEmergMaxVolts() {
		return emergMaxVolts;
	}

	public void setEmergMaxVolts(double max) {
		emergMaxVolts = max;
	}

	public double getEmergMinVolts() {
		return emergMinVolts;
	}

	public void setEmergMinVolts(double min) {
		emergMinVolts = min;
	}

	public double[] getLegalVoltageBases() {
		return legalVoltageBases;
	}

	public void setLegalVoltageBases(double[] bases) {
		legalVoltageBases = bases;
	}

	public double getGeneratorDispatchReference() {
		return generatorDispatchReference;
	}

	public void setGeneratorDispatchReference(double reference) {
		generatorDispatchReference = reference;
	}

	public double getDefaultGrowthFactor() {
		return defaultGrowthFactor;
	}

	public void setDefaultGrowthFactor(double factor) {
		defaultGrowthFactor = factor;
	}

	public double getDefaultGrowthRate() {
		return defaultGrowthRate;
	}

	public void setDefaultGrowthRate(double rate) {
		defaultGrowthRate = rate;
	}

	public double getGenMultiplier() {
		return genMultiplier;
	}

	public void setGenMultiplier(double multiplier) {
		genMultiplier = multiplier;
	}

	public double getHarmMult() {
		return harmMult;
	}

	public void setHarmMult(double mult) {
		harmMult = mult;
	}

	public Complex getDefaultHourMult() {
		return defaultHourMult;
	}

	public void setDefaultHourMult(Complex mult) {
		defaultHourMult = mult;
	}

	public double getPriceSignal() {
		return priceSignal;
	}

	public void setPriceSignal(double signal) {
		priceSignal = signal;
	}

	public double[] getRegisterTotals() {
		return registerTotals;
	}

	public void setRegisterTotals(double[] totals) {
		registerTotals = totals;
	}

	public LoadShapeObj getDefaultDailyShapeObj() {
		return defaultDailyShapeObj;
	}

	public void setDefaultDailyShapeObj(LoadShapeObj shapeObj) {
		defaultDailyShapeObj = shapeObj;
	}

	public LoadShapeObj getDefaultYearlyShapeObj() {
		return defaultYearlyShapeObj;
	}

	public void setDefaultYearlyShapeObj(LoadShapeObj shapeObj) {
		defaultYearlyShapeObj = shapeObj;
	}

	public String getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(String dir) {
		currentDirectory = dir;
	}

	public ReductionStrategyType getReductionStrategy() {
		return reductionStrategy;
	}

	public void setReductionStrategy(ReductionStrategyType strategy) {
		reductionStrategy = strategy;
	}

	public double getReductionMaxAngle() {
		return reductionMaxAngle;
	}

	public void setReductionMaxAngle(double maxAngle) {
		reductionMaxAngle = maxAngle;
	}

	public double getReductionZmag() {
		return reductionZmag;
	}

	public void setReductionZmag(double mag) {
		reductionZmag = mag;
	}

	public String getReductionStrategyString() {
		return reductionStrategyString;
	}

	public void setReductionStrategyString(String reductionStrategy) {
		reductionStrategyString = reductionStrategy;
	}

	public double getPctNormalFactor() {
		return pctNormalFactor;
	}

	public void setPctNormalFactor(double factor) {
		pctNormalFactor = factor;
	}

	public int getNodeMarkerCode() {
		return nodeMarkerCode;
	}

	public void setNodeMarkerCode(int markerCode) {
		nodeMarkerCode = markerCode;
	}

	public int getNodeMarkerWidth() {
		return nodeMarkerWidth;
	}

	public void setNodeMarkerWidth(int markerWidth) {
		nodeMarkerWidth = markerWidth;
	}

	public int getSwitchMarkerCode() {
		return SwitchMarkerCode;
	}

	public void setSwitchMarkerCode(int markerCode) {
		SwitchMarkerCode = markerCode;
	}

	public int getTransMarkerSize() {
		return transMarkerSize;
	}

	public void setTransMarkerSize(int markerSize) {
		transMarkerSize = markerSize;
	}

	public int getTransMarkerCode() {
		return transMarkerCode;
	}

	public void setTransMarkerCode(int markerCode) {
		transMarkerCode = markerCode;
	}

	public boolean isMarkSwitches() {
		return markSwitches;
	}

	public void setMarkSwitches(boolean mark) {
		markSwitches = mark;
	}

	public boolean isMarkTransformers() {
		return markTransformers;
	}

	public void setMarkTransformers(boolean mark) {
		markTransformers = mark;
	}

	public int getActiveLoadShapeClass() {
		return activeLoadShapeClass;
	}

	public void setActiveLoadShapeClass(int loadShapeClass) {
		activeLoadShapeClass = loadShapeClass;
	}

}
