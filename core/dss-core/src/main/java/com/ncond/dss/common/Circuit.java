package com.ncond.dss.common;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus.NodeBus;
import com.ncond.dss.control.CapControlObj;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.control.RegControlObj;
import com.ncond.dss.control.SwtControlObj;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.conversion.PVSystemObj;
import com.ncond.dss.conversion.StorageObj;
import com.ncond.dss.delivery.CapacitorObj;
import com.ncond.dss.delivery.FaultObj;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.delivery.TransformerObj;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.NamedObject;
import com.ncond.dss.general.PriceShapeObj;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.meter.MeterElement;
import com.ncond.dss.meter.MonitorObj;
import com.ncond.dss.meter.SensorObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.Dynamics;
import com.ncond.dss.shared.HashList;

@Data
@EqualsAndHashCode(callSuper=true)
public class Circuit extends NamedObject {

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

	private int[] nodeBuffer;  // node numbers
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
	protected List<FaultObj> faults;
	protected List<CktElement> cktElements;
	protected List<PDElement> PDElements;
	protected List<PCElement> PCElements;
	protected List<ControlElem> controls;
	protected List<PCElement> sources;
	protected List<MeterElement> meterElements;
	protected List<SensorObj> sensors;
	protected List<MonitorObj> monitors;
	protected List<EnergyMeterObj> energyMeters;
	protected List<GeneratorObj> generators;
	protected List<StorageObj> storageElements;
	protected List<PVSystemObj> PVSystems;
	protected List<DSSObject> substations;
	protected List<TransformerObj> transformers;
	protected List<CapControlObj> capControls;
	protected List<RegControlObj> regControls;
	protected List<LineObj> lines;
	protected List<LoadObj> loads;
	protected List<CapacitorObj> shuntCapacitors;
	protected List<FeederObj> feeders;
	protected List<SwtControlObj> swtControls;

	protected ControlQueue controlQueue;

	protected SolutionObj solution;
	protected AutoAdd autoAddObj;

	protected double UEWeight, lossWeight;  // for AutoAdd

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

	// buses and nodes
	protected Bus[] buses;
	protected NodeBus[] mapNodeToBus;

	// flags
	protected boolean solved;
	protected boolean duplicatesAllowed;
	protected boolean zonesLocked;
	protected boolean meterZonesComputed;
	protected boolean positiveSequence;  // model is to be interpreted as pos seq

	// per unit voltage restraints for this circuit
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

	// plot markers
	protected int nodeMarkerCode, nodeMarkerWidth, switchMarkerCode;
	protected int transMarkerSize, transMarkerCode;

	protected boolean markSwitches;
	protected boolean markTransformers;

	protected int activeLoadShapeClass;

	public Circuit(String aName) {
		super("Circuit");

		solved = false;
		DSS.solutionClass.newObject(getName());
		solution = Solution.activeSolutionObj;

		setLocalName(aName.toLowerCase());

		setCaseName(aName);  // default case name to "circuitname"; sets circuitName_

		fundamental = DSS.defaultBaseFreq;
		setActiveCktElement(null);
		activeBusIndex = 0;  // always a bus

		// initial allocations increased from 100 to 1000 to speed things up

		maxBuses   = 1000;  // good sized allocation to start
		maxDevices = 1000;
		maxNodes   = 3 * maxBuses;
		incDevices = 1000;
		incBuses   = 1000;
		incNodes   = 3000;

		// allocate some nominal sizes
		busList    = new HashList(900);  // bus name list nominal size to start; gets reallocated
		deviceList = new HashList(900);
		autoAddBusList = new HashList(100);

		numBuses   = 0;  // eventually allocate a single source
		numDevices = 0;
		numNodes   = 0;

		faults       = new ArrayList<FaultObj>(2);
		cktElements  = new ArrayList<CktElement>(1000);
		PDElements   = new ArrayList<PDElement>(1000);
		PCElements   = new ArrayList<PCElement>(1000);
		controls     = new ArrayList<ControlElem>(10);
		sources      = new ArrayList<PCElement>(10);
		meterElements= new ArrayList<MeterElement>(20);
		monitors     = new ArrayList<MonitorObj>(20);
		energyMeters = new ArrayList<EnergyMeterObj>(5);
		sensors      = new ArrayList<SensorObj>(5);
		generators   = new ArrayList<GeneratorObj>(5);
		storageElements = new ArrayList<StorageObj>(5);
		PVSystems    = new ArrayList<PVSystemObj>(5);
		feeders      = new ArrayList<FeederObj>(10);
		substations  = new ArrayList<DSSObject>(5);
		transformers = new ArrayList<TransformerObj>(10);
		capControls  = new ArrayList<CapControlObj>(10);
		swtControls  = new ArrayList<SwtControlObj>(50);
		regControls  = new ArrayList<RegControlObj>(5);
		lines        = new ArrayList<LineObj>(1000);
		loads        = new ArrayList<LoadObj>(1000);
		shuntCapacitors = new ArrayList<CapacitorObj>(20);

		buses     = new Bus[maxBuses];
		mapNodeToBus = new NodeBus[maxNodes];
		deviceRef = new CktElementDef[maxDevices];

		controlQueue = new ControlQueue();

		legalVoltageBases = new double[8];
		// default voltage bases
		legalVoltageBases[0] =   0.208;
		legalVoltageBases[1] =   0.480;
		legalVoltageBases[2] =  12.47;
		legalVoltageBases[3] =  24.9;
		legalVoltageBases[4] =  34.5;
		legalVoltageBases[5] = 115.0;
		legalVoltageBases[6] = 230.0;
		legalVoltageBases[7] =   0.0;  // terminates array

		nodeBufferMax = 20;
		nodeBuffer    = new int[nodeBufferMax];  // to hold the nodes

		// init global circuit load and harmonic source multipliers
		setLoadMultiplier(1.0);
		genMultiplier = 1.0;
		harmMult = 1.0;

		priceSignal = 25.0;  // $25/MWh

		// factors for AutoAdd
		UEWeight    = 1.0;  // default to weighting UE same as losses
		lossWeight  = 1.0;
		numUERegs   = 1;
		numLossRegs = 1;
		UERegs      = new int[numUERegs];
		lossRegs    = new int[numLossRegs];
		UERegs[0]   = 10;  // overload UE
		lossRegs[0] = 13;  // zone losses

		capacityStart = 0.9;  // for capacity search
		capacityIncrement = 0.005;

		loadDurCurve    = "";
		loadDurCurveObj = null;
		priceCurve    = "";
		priceCurveObj = null;

		// flags
		duplicatesAllowed  = false;
		zonesLocked        = false;  // meter zones recomputed after each change
		meterZonesComputed = false;
		positiveSequence   = false;

		normalMinVolts = 0.95;
		normalMaxVolts = 1.05;
		emergMaxVolts  = 1.08;
		emergMinVolts  = 0.90;

		nodeMarkerCode   = 16;
		nodeMarkerWidth  = 1;
		markSwitches     = false;
		markTransformers = false;
		switchMarkerCode = 5;
		transMarkerCode  = 35;
		transMarkerSize  = 1;

		trapezoidalIntegration = false;  // default to Euler method
		logEvents = false;

		generatorDispatchReference = 0.0;
		defaultGrowthRate = 1.025;
		defaultGrowthFactor = 1.0;

		defaultDailyShapeObj  = (LoadShapeObj) DSS.loadShapeClass.find("default");
		defaultYearlyShapeObj = (LoadShapeObj) DSS.loadShapeClass.find("default");

		currentDirectory = "";

		setBusNameRedefined(true);  // set to force rebuild of bus and node lists

		savedBuses = null;
		savedBusNames = null;

		reductionStrategy = ReductionStrategyType.DEFAULT;
		reductionMaxAngle = 15.0;
		reductionZmag = 0.02;

		// misc objects
		autoAddObj = new AutoAdd();

		branchList = null;
		busAdjPC = null;
		busAdjPD = null;
	}

	private void addDeviceHandle(int handle) {
		if (numDevices > maxDevices) {
			maxDevices = maxDevices + incDevices;
			deviceRef = Util.resizeArray(deviceRef, maxDevices);
		}
		if (deviceRef[numDevices - 1] == null)
			deviceRef[numDevices - 1] = new CktElementDef();
		deviceRef[numDevices - 1].devHandle = handle;  // index into CktElements
		deviceRef[numDevices - 1].cktElementClass = DSS.lastClassReferenced;
	}

	private void addABus() {
		if (numBuses > maxBuses) {
			maxBuses += incBuses;
			buses = Util.resizeArray(buses, maxBuses);
		}
	}

	private void addANodeBus() {
		if (numNodes > maxNodes) {
			maxNodes += incNodes;
			mapNodeToBus = Util.resizeArray(mapNodeToBus, maxNodes);
		}
	}

	/**
	 * @return bus ref (one based)
	 */
	private int addBus(String busName, int nNodes) {
		int ref, nodeRef;

		if (busName.length() == 0) {  // error in busname
			DSS.doErrorMsg("DSSCircuit.addBus", "BusName for object \"" + activeCktElement.getName() + "\" is null.",
					"Error in definition of object.", 424);
			for (int i = 0; i < activeCktElement.getNumConds(); i++)
				nodeBuffer[i] = 0;
			ref = 0;
			return ref;
		}

		ref = busList.find(busName) + 1;
		if (ref == 0) {
			ref = busList.add(busName) + 1;  // result is one based index of bus
			numBuses += 1;
			addABus();  // allocates more memory if necessary
			buses[numBuses - 1] = new Bus();
		}

		/*
		 * Define nodes belonging to the bus.
		 * Replace nodeBuffer values with global reference number.
		 */
		for (int i = 0; i < nNodes; i++) {
			nodeRef = buses[ref - 1].add(nodeBuffer[i]);
			if (nodeRef == numNodes) {  // this was a new node so add a nodeToBus element ????
				addANodeBus();  // allocates more memory if necessary
				if (mapNodeToBus[numNodes - 1] == null)
					mapNodeToBus[numNodes - 1] = new NodeBus();
				mapNodeToBus[numNodes - 1].busRef  = ref;
				mapNodeToBus[numNodes - 1].nodeNum = nodeBuffer[i];
			}
			nodeBuffer[i] = nodeRef;  // swap out in preparation to setNodeRef call
		}
		return ref;
	}

	public void setActiveCktElement(CktElement value) {
		activeCktElement = value;
		DSS.activeDSSObject = value;
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

	/**
	 * Total circuit PD element losses.
	 */
	public Complex getLosses() {
		Complex losses = Complex.ZERO;
		for (PDElement pdElem : PDElements) {
			if (pdElem.isEnabled()) {
				if (!pdElem.isShunt())  // ignore shunt elements
					losses = losses.add(pdElem.getLosses());
			}
		}
		return losses;
	}

	public void setLoadMultiplier(double value) {
		if (value != loadMultiplier) {
			// may have to change the Y matrix if the load multiplier has changed
			switch (solution.getLoadModel()) {
			case DSS.ADMITTANCE:
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
		savedBuses = new Bus[numBuses];
		savedBusNames = new String[numBuses];

		for (int i = 0; i < numBuses; i++) {
			savedBuses[i] = buses[i];
			savedBusNames[i] = busList.get(i);
		}
		savedNumBuses = numBuses;
	}

	private void restoreBusInfo() {
		int i, j, idx, jdx;
		Bus bus;

		/* Restore kV bases, other values to buses still in the list */
		for (i = 0; i < savedNumBuses; i++) {
			idx = busList.find(savedBusNames[i]);
			if (idx != -1) {
				bus = savedBuses[i];
				buses[idx].setKVBase(bus.getKVBase());
				buses[idx].setX(bus.getX());
				buses[idx].setY(bus.getY());
				buses[idx].setCoordDefined(bus.isCoordDefined());
				buses[idx].setKeep(bus.isKeep());
				/* Restore voltages in new bus def that existed in old bus def */
				if (bus.getVBus() != null) {
					for (j = 0; j < bus.getNumNodesThisBus(); j++) {
						// find index in new bus for j-th node in old bus
						jdx = buses[idx].findIdx(bus.getNum(j));
						if (jdx > -1)
							buses[idx].getVBus()[jdx] = bus.getVBus()[j];
					}
				}
			}
			savedBusNames[i] = null;  // de-allocate string
		}

		savedBuses = null;
		savedBusNames = null;
	}

	private boolean saveMasterFile() {
		boolean success = false;
		try {
			PrintWriter pw = new PrintWriter("Master.dss");

			pw.println("clear");
			pw.println("new circuit." + getName());
			pw.println();
			if (positiveSequence)
				pw.println("set cktModel=Positive");
			if (duplicatesAllowed)
				pw.println("set allowDup=yes");
			pw.println();

			// write redirect for all populated DSS classes except solution class
			for (int i = 0; i < DSS.savedFileList.size(); i++)
				pw.println("redirect " + DSS.savedFileList.get(i));

			if (new File("BusCoords.dss").exists()) {
				pw.println("makeBusList");
				pw.println("busCoords BusCoords.dss");
			}

			pw.close();
			success = true;
		} catch (Exception e) {
			DSS.doSimpleMsg("Error saving master file: " + e.getMessage(), 435);
		}
		return success;
	}

	private boolean saveDSSObjects() {
		// write files for all populated DSS classes except solution class
		for (DSSClass cls : DSS.DSSClassList) {
			if ((cls == DSS.solutionClass) || cls.isSaved())
				continue;  // cycle to next
			/* use default filename=classname */
			if (!Util.writeClassFile(cls, "", cls instanceof CktElementClass))
				return false;
			cls.setSaved(true);
		}
		return true;
	}

	private boolean saveFeeders() {
		String currDir, saveDir;
		boolean success = true;

		/* Write out all energy meter zones to separate subdirectories */
		saveDir = DSS.currentDirectory;
		for (EnergyMeterObj mtr : energyMeters) {
			currDir = mtr.getName();
			if (new File(currDir).mkdir()) {
				DSS.currentDirectory = currDir;
				mtr.saveZone(currDir);
				DSS.currentDirectory = saveDir;
			} else {
				DSS.doSimpleMsg("Cannot create directory: " + currDir, 436);
				success = false;
				DSS.currentDirectory = saveDir;
				break;
			}
		}
		return success;
	}

	private boolean saveBusCoords() {
		boolean success = false;
		try {
			PrintWriter pw = new PrintWriter("BusCoords.dss");

			for (int i = 0; i < numBuses; i++) {
				if (buses[i].isCoordDefined()) {
					pw.printf("%s, %-g, %-g",
						Util.checkForBlanks(busList.get(i)),
						buses[i].getX(), buses[i].getY());
					pw.println();
				}
			}
			pw.close();
			success = true;
		} catch (Exception e) {
			DSS.doSimpleMsg("Error creating BusCoords.dss.", 437);
		}
		return success;
	}

	/* Reallocate the device list to improve the performance of searches */
	private void reallocDeviceList() {
		if (logEvents)
			Util.logThisEvent("Reallocating device list");
		HashList tempList = new HashList(2 * numDevices);

		for (int i = 0; i < deviceList.listSize(); i++)
			tempList.add(deviceList.get(i));

		deviceList = tempList;
	}

	public void setCaseName(String value) {
		caseName = value;
		DSS.circuitName_ = value + "_";
	}

	public String getCaseName() {
		return caseName;
	}

	public String getName() {
		return getLocalName();
	}

	/**
	 * Adds last DSS object created to circuit.
	 */
	public void addCktElement(int handle) {
		// update lists that keep track of individual circuit elements
		numDevices += 1;

		// resize deviceList if no. of devices greatly exceeds allocation
		if (numDevices > (2 * deviceList.getInitialAllocation())) {
			reallocDeviceList();
		}
		deviceList.add(activeCktElement.getName());
		cktElements.add(activeCktElement);

		/* Build lists of PC and PD elements */
		switch (activeCktElement.getObjType() & DSSClassDefs.BASECLASSMASK) {
		case DSSClassDefs.PD_ELEMENT:
			PDElements.add((PDElement) activeCktElement);
			break;
		case DSSClassDefs.PC_ELEMENT:
			PCElements.add((PCElement) activeCktElement);
			break;
		case DSSClassDefs.CTRL_ELEMENT:
			controls.add((ControlElem) activeCktElement);
			break;
		case DSSClassDefs.METER_ELEMENT:
			meterElements.add((MeterElement) activeCktElement);
			break;
		}

		/* Build lists of special elements and generic types */
		switch (activeCktElement.getObjType() & DSSClassDefs.BASECLASSMASK) {
		case DSSClassDefs.MON_ELEMENT:
			monitors.add((MonitorObj) activeCktElement);
			break;
		case DSSClassDefs.ENERGY_METER:
			energyMeters.add((EnergyMeterObj) activeCktElement);
			break;
		case DSSClassDefs.SENSOR_ELEMENT:
			sensors.add((SensorObj) activeCktElement);
			break;
		case DSSClassDefs.GEN_ELEMENT:
			generators.add((GeneratorObj) activeCktElement);
			break;
		case DSSClassDefs.SOURCE:
			sources.add((PCElement) activeCktElement);
			break;
		case DSSClassDefs.CAP_CONTROL:
			capControls.add((CapControlObj) activeCktElement);
			break;
		case DSSClassDefs.SWT_CONTROL:
			swtControls.add((SwtControlObj) activeCktElement);
			break;
		case DSSClassDefs.REG_CONTROL:
			regControls.add((RegControlObj) activeCktElement);
			break;
		case DSSClassDefs.LOAD_ELEMENT:
			loads.add((LoadObj) activeCktElement);
			break;
		case DSSClassDefs.CAP_ELEMENT:
			shuntCapacitors.add((CapacitorObj) activeCktElement);
			break;
		/* Keep lines, transformer, and faults in PDElements and
		 * separate lists so we can find them quickly. */
		case DSSClassDefs.XFMR_ELEMENT:
			transformers.add((TransformerObj) activeCktElement);
			break;
		case DSSClassDefs.LINE_ELEMENT:
			lines.add((LineObj) activeCktElement);
			break;
		case DSSClassDefs.FAULTOBJECT:
			faults.add((FaultObj) activeCktElement);
			break;
		case DSSClassDefs.FEEDER_ELEMENT:
			feeders.add((FeederObj) activeCktElement);
			break;
		case DSSClassDefs.STORAGE_ELEMENT:
			storageElements.add((StorageObj) activeCktElement);
			break;
		case DSSClassDefs.PVSYSTEM_ELEMENT:
			PVSystems.add((PVSystemObj) activeCktElement);
			break;
		}

		// addDeviceHandle(Handle);  // keep track of this device result is handle
		addDeviceHandle(cktElements.size() - 1);  // handle is global index into CktElements
		activeCktElement.setHandle(cktElements.size() - 1);
	}

	/**
	 * Totalize all energy meters in the problem.
	 */
	public void totalizeMeters() {
		int i, j;
		EnergyMeterObj meter;

		for (i = 0; i < EnergyMeterObj.NumEMRegisters; i++)
			registerTotals[i] = 0.0;

		for (i = 0; i < energyMeters.size(); i++) {
			meter = energyMeters.get(i);
			for (j = 0; j < EnergyMeterObj.NumEMRegisters; i++)
				registerTotals[i] += meter.getRegister(i) * meter.getTotalsMask(i);
		}
	}

	public boolean computeCapacity() {
		boolean success = false;
		boolean capacityFound;

		if (energyMeters.size() == 0) {
			DSS.doSimpleMsg("Cannot compute system capacity with no EnergyMeter objects!", 430);
			return success;
		}

		if (numUERegs == 0) {
			DSS.doSimpleMsg("Cannot compute system capacity with no UE resisters defined. " +
					"Use \"set UERegs=(...)\" command.", 431);
			return success;
		}

		solution.setMode(Dynamics.SNAPSHOT);
		setLoadMultiplier(capacityStart);
		capacityFound = false;

		while ((loadMultiplier <= 1.0) && !capacityFound) {
			DSS.energyMeterClass.resetAll();
			solution.solve();
			DSS.energyMeterClass.sampleAll();
			totalizeMeters();

			// check for non-zero in UEregs
			if (sumSelectedRegisters(registerTotals, UERegs, numUERegs) != 0.0)
				capacityFound = true;

			if (!capacityFound)
				setLoadMultiplier(loadMultiplier + capacityIncrement);
		}

		if (loadMultiplier > 1.0)
			setLoadMultiplier(1.0);

		success = true;
		return success;
	}

	private double sumSelectedRegisters(double[] mtrRegisters, int[] regs, int count) {
		double sum = 0.0;
		for (int i = 0; i < count; i++)
			sum += mtrRegisters[regs[i]];
		return sum;
	}

	public boolean save(String dir) {
		String currDir, saveDir;
		boolean result = false;

		// make a new subfolder in the present folder based on the circuit
		// name and a unique sequence number
		saveDir = DSS.currentDirectory;

		boolean success = false;
		if (dir.length() == 0) {
			dir = getName();

			currDir = dir;
			for (int i = 0; i < 999; i++) {  // find a unique dir name
				File F = new File(currDir);
				if (!F.exists()) {
					if (F.mkdir()) {
						DSS.currentDirectory = currDir;
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
					DSS.currentDirectory = currDir;
					success = true;
				}
			} else {  // exists - overwrite
				currDir = dir;
				DSS.currentDirectory = currDir;
				success = true;
			}
		}

		if (!success) {
			DSS.doSimpleMsg("Could not create a folder \"" + dir +
					"\" for saving the circuit.", 432);
			return result;
		}

		// this list keeps track of all files saved
		DSS.savedFileList = new ArrayList<String>();

		// initialize so we will know when we have saved the circuit elements
		for (CktElement elem : cktElements)
			elem.setHasBeenSaved(false);

		// initialize so we don't save a class twice
		for (DSSClass cls : DSS.DSSClassList)
			cls.setSaved(false);

		// ignore feeder class -- gets saved with EnergyMeters
		//Globals.getFeederClass().setSaved(true);

		// Define voltage sources first
		success = Util.writeVSourceClassFile(DSSClassDefs.getDSSClass("vsource"), true);
		// write library files so that they will be available to lines, loads, etc
		/* Use default filename=classname */
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("wiredata"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("cndata"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("tsdata"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("linegeometry"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("linecode"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("linespacing"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("linecode"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("xfmrcode"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("growthshape"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("TCC_Curve"), "", false);
		if (success) success = Util.writeClassFile(DSSClassDefs.getDSSClass("Spectrum"), "", false);
		if (success) success = saveFeeders();  // save feeders first
		if (success) success = saveDSSObjects();  // save rest to the objects
		if (success) success = saveBusCoords();
		if (success) success = saveMasterFile();

		if (success) {
			DSS.doSimpleMsg("Circuit saved in directory: " + DSS.currentDirectory, 433);
		} else {
			DSS.doSimpleMsg("Error attempting to save circuit in " + DSS.currentDirectory, 434);
		}

		// return to original directory
		DSS.currentDirectory = saveDir;

		return true;
	}

	public void processBusDefs() {
		int i, j, iTerm, rc;
		String busName;
		int[] nNodes = new int[1];
		int np = activeCktElement.getNumPhases();
		int ncond = activeCktElement.getNumConds();

		Parser parser = Parser.getInstance();

		// use parser functions to decode
		parser.setToken(activeCktElement.getFirstBus());

		for (iTerm = 0; iTerm < activeCktElement.getNumTerms(); iTerm++) {
			boolean nodesOK = true;
			// assume normal phase rotation for default
			for (i = 0; i < np; i++)
				nodeBuffer[i] = i + 1;  // set up buffer with defaults

			// default all other conductors to a ground connection
			// uf user wants them ungrounded, must be specified explicitly!
			for (i = np; i < ncond; i++)
				nodeBuffer[i] = 0;

			// parser will override bus connection if any specified
			busName = parser.parseAsBusName(nNodes, nodeBuffer);

			// check for error in node specification
			for (j = 0; j < nNodes[0]; j++) {
				if (nodeBuffer[j] < 0) {
					rc = DSS.forms.messageDlg("Error in node specification for element: \""
						+ activeCktElement.getParentClass().getClassName() + "." + activeCktElement.getName() + "\"" + DSS.CRLF +
						"Bus Spec: \"" + parser.getToken() + "\"", false);
					nodesOK = false;
					if (rc == -1) {
						abortBusProcess = true;
						DSS.appendGlobalResult("Aborted bus process.");
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
				activeCktElement.getActiveTerminal().setBusRef(addBus(busName, ncond));
				activeCktElement.setNodeRef(iTerm, nodeBuffer);  // for active circuit
			}
			parser.setToken(activeCktElement.getNextBus());
		}
	}

	/**
	 * Redo all bus lists and node lists.
	 */
	public void reProcessBusDefs() {
		if (logEvents)
			Util.logThisEvent("Reprocessing bus definitions");

		abortBusProcess = false;
		saveBusInfo();  // so we don't have to keep re-doing this
		// keeps present definitions of bus objects until new ones created

		// get rid of old bus lists
		busList = null;  // clears hash list of bus names for adding more
		busList = new HashList(numDevices);  // won't have many more buses than this

		numBuses = 0;  // leave allocations same, but start count over
		numNodes = 0;

		// now redo all enabled circuit elements
		CktElement cktElementSave = activeCktElement;
		for (int i = 0; i < cktElements.size(); i++) {
			setActiveCktElement( cktElements.get(i) );
			if (activeCktElement.isEnabled()) processBusDefs();
			if (abortBusProcess) return;
		}

		setActiveCktElement(cktElementSave);  // restore active circuit element

		for (int i = 0; i < numBuses; i++)
			buses[i].allocateBusVoltages();
		for (int i = 0; i < numBuses; i++)
			buses[i].allocateBusCurrents();

		restoreBusInfo();     // frees old bus info too
		doResetMeterZones();  // fix up meter zones to correspond

		setBusNameRedefined(false);  // get ready for next time
	}

	public void doResetMeterZones() {
		/* Do this only if meterZones unlocked. Normally, zones will remain
		 * unlocked so that all changes to the circuit will result in rebuilding
		 * the lists */
		if (!meterZonesComputed || !zonesLocked) {
			if (logEvents) Util.logThisEvent("Resetting meter zones");
			DSS.energyMeterClass.resetMeterZonesAll();
			meterZonesComputed = true;
			if (logEvents) Util.logThisEvent("Done resetting meter zones");
		}
		freeTopology();
	}

	public int setElementActive(String fullObjectName) {
		int devClassIndex, devIndex, handle = 0;
		String[] devType = new String[1];
		String[] devName = new String[1];

		Util.parseObjectClassandName(fullObjectName, devType, devName);
		devClassIndex = DSS.classNames.find(devType[0]);
		if (devClassIndex == -1)
			devClassIndex = DSS.lastClassReferenced;
		devIndex = deviceList.find(devName[0]);
		while (devIndex >= 0) {
			if (deviceRef[devIndex].cktElementClass == devClassIndex) {  // we got a match
				DSS.activeDSSClass = DSS.DSSClassList.get(devClassIndex);
				DSS.lastClassReferenced = devClassIndex;
				handle = deviceRef[devIndex].devHandle;
				// activeDSSClass.active = result;
				// activeCktElement = activeDSSClass.getActiveObj;
				setActiveCktElement( cktElements.get(handle) );
				break;
			}
			devIndex = deviceList.findNext();  // could be duplicates
		}
		DSS.cmdResult = handle;
		return handle;
	}

	public void invalidateAllPCElements() {
		for (PCElement p : PCElements) p.setYPrimInvalid(true);

		// force rebuild of matrix on next solution
		solution.setSystemYChanged(true);
	}

	public void debugDump(OutputStream out) {
		int i, j;
		PrintWriter pw = new PrintWriter(out);

		pw.println("numBuses= " + numBuses);
		pw.println("numNodes= " + numNodes);
		pw.println("numDevices= " + numDevices);
		pw.println("BusList:");
		for (i = 0; i < numBuses; i++) {
			pw.printf("  %12s", busList.get(i));
			pw.printf(" (" + buses[i].getNumNodesThisBus() + " nodes)");
			for (j = 0; j < buses[i].getNumNodesThisBus(); j++) {
				pw.print(" " + buses[i].getNum(j));
			}
			pw.println();
		}
		pw.println("DeviceList:");
		for (i = 0; i < numDevices; i++) {
			pw.printf("  %12s%s", deviceList.get(i), DSS.CRLF);
			setActiveCktElement( cktElements.get(i) );
			if (!activeCktElement.isEnabled())
				pw.print("  DISABLED");
			pw.println();
		}
		pw.println("NodeToBus Array:");
		for (i = 0; i < numNodes; i++) {
			j = mapNodeToBus[i].busRef;
			pw.print("  " + i + " " + j +
				" (=" +busList.get(j) + "." + mapNodeToBus[i].nodeNum + ")");
			pw.println();
		}
		pw.close();
	}

	/**
	 * Access to topology from the first source.
	 */
	public CktTree getTopology() {
		int i;
		if (branchList == null) {
			/* Initialize all circuit elements and buses to not checked, then build a new tree */
			for (CktElement elem : cktElements) {
				elem.setChecked(false);
				for (i = 0; i < elem.getNumTerms(); i++)
					elem.getTerminal(i).setChecked(false);
				elem.setIsolated(true);  // till proven otherwise
			}

			for (i = 0; i < numBuses; i++)
				buses[i].setBusChecked(false);

			// calls back to build adjacency lists
			branchList = CktTree.getIsolatedSubArea(sources.get(0), true);
		}
		return branchList;
	}

	public void freeTopology() {
		if (branchList != null)
			branchList = null;
		if (busAdjPC != null)
			CktTree.freeAndNilBusAdjacencyLists(busAdjPD, busAdjPC);
	}

	public List<PDElement>[] getBusAdjacentPDLists() {
		if (busAdjPD == null)
			CktTree.buildActiveBusAdjacencyLists(busAdjPD, busAdjPC);
		return busAdjPD;
	}

	public List<PCElement>[] getBusAdjacentPCLists() {
		if (busAdjPC == null)
			CktTree.buildActiveBusAdjacencyLists(busAdjPD, busAdjPC);
		return busAdjPC;
	}

	public Bus getBus(int idx) {
		return buses[idx];
	}

	/**
	 * @param nref one based node reference
	 * @return node bus mapping
	 */
	public NodeBus getMapNodeToBus(int nref) {
		return mapNodeToBus[nref - 1];
	}

	public NodeBus[] getMapNodeToBus() {
		return mapNodeToBus;
	}

}
