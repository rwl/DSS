package com.epri.dss.common.impl;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.general.impl.NamedObjectImpl;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.PointerList;
import com.epri.dss.shared.impl.CktTreeImpl;
import com.epri.dss.shared.impl.HashListImpl;
import com.epri.dss.shared.impl.PointerListImpl;

public class DSSCircuit extends NamedObjectImpl implements Circuit {

	public class CktElementDef {
		public int CktElementClass;
		public int devHandle;
	}

	private int[] NodeBuffer;
	private int NodeBufferMax;
	private boolean BusNameRedefined;
	private CktElement ActiveCktElement;

    // Temp arrays for when the bus swap takes place
    private Bus[] SavedBuses;
    private String[] SavedBusNames;
    private int SavedNumBuses;

    /* global multiplier for every load */
    private double LoadMultiplier;

    private boolean AbortBusProcess;

    /* topology from the first source, lazy evaluation */
    private CktTree Branch_List;
    /* bus adjacency lists of PD and PC elements */
    private List<List<Object>> BusAdjPC, BusAdjPD;

    protected String CaseName;

    protected int ActiveBusIndex;
    /* fundamental and default base frequency */
    protected double Fundamental;

    /* Flag for use by control elements to detect redefinition of buses */
    protected boolean Control_BusNameRedefined;

    protected HashList BusList, AutoAddBusList, DeviceList;
    protected CktElementDef[] DeviceRef;  //Type and handle of device

    // lists of pointers to different elements by class
    protected PointerList Faults, CktElements, PDElements, PCElements, DSSControls,
    	Sources, MeterElements, Sensors, Monitors, EnergyMeters, Generators,
    	StorageElements, Substations, Transformers, CapControls, RegControls,
    	Lines, Loads, ShuntCapacitors, Feeders, SwtControls;

    protected ControlQueue ControlQueue;

    protected SolutionObj Solution;
    protected AutoAdd AutoAddObj;

    // For AutoAdd stuff
    protected double UEWeight, LossWeight;

    protected int NumUEregs, NumLossRegs;
    protected int[] UEregs, LossRegs;

    protected double CapacityStart, CapacityIncrement;

    protected boolean TrapezoidalIntegration,  // flag for trapezoidal integration
    LogEvents;

    protected String LoadDurCurve;
    protected LoadShapeObj LoadDurCurveObj;
    protected String PriceCurve;
    protected LoadShapeObj PriceCurveObj;

    protected int NumDevices, NumBuses, NumNodes;
    protected int MaxDevices, MaxBuses, MaxNodes;
    protected int IncDevices, IncBuses, IncNodes;

    // Bus and Node stuff
    protected Bus[] Buses;
    protected NodeBus[] MapNodeToBus;

    // Flags
    protected boolean IsSolved;
    protected boolean DuplicatesAllowed;
    protected boolean ZonesLocked;
    protected boolean MeterZonesComputed;
    protected boolean PositiveSequence;  // Model is to be interpreted as Pos seq

    // Voltage limits
    /* per unit voltage restraints for this circuit */
    protected double NormalMinVolts, NormalMaxVolts, EmergMaxVolts, EmergMinVolts;
    protected double[] LegalVoltageBases;

    // Global circuit multipliers
    protected double GeneratorDispatchReference,
    DefaultGrowthFactor,
    DefaultGrowthRate,
    GenMultiplier,   // global multiplier for every generator
    HarmMult;
    protected double[] DefaultHourMult; // complex

    protected double PriceSignal; // price signal for entire circuit

    // EnergyMeter Totals
    protected double[] RegisterTotals;

    protected LoadShapeObj DefaultDailyShapeObj, DefaultYearlyShapeObj;

    protected String CurrentDirectory;

    protected ReductionStrategy ReductionStrategy;
    protected double ReductionMaxAngle, ReductionZmag;
    protected String ReductionStrategyString;

    protected double PctNormalFactor;

    /* Plot Markers */
    protected int NodeMarkerCode, NodeMarkerWidth, SwitchMarkerCode;
    protected int TransMarkerSize, TransMarkerCode;

    protected boolean MarkSwitches;
    protected boolean MarkTransformers;

	public DSSCircuit(String aName) {
		super("Circuit");

		this.IsSolved = false;
	    /*Retval   = */ SolutionClass.NewObject(getName());
	    this.Solution = ActiveSolutionObj;

	    this.LocalName = aName.toLowerCase();

	    this.CaseName = aName;  // Default case name to circuitname
	                            // Sets CircuitName_

	    this.Fundamental = DSSGlobalsImpl.getInstance().DefaultBaseFreq;
	    this.ActiveCktElement = null;
	    this.ActiveBusIndex = 1;    // Always a bus

	    // initial allocations increased from 100 to 1000 to speed things up

	    this.MaxBuses   = 1000;  // good sized allocation to start
	    this.MaxDevices = 1000;
	    this.MaxNodes   = 3 * MaxBuses;
	    this.IncDevices = 1000;
	    this.IncBuses   = 1000;
	    this.IncNodes   = 3000;

	    // Allocate some nominal sizes
	    this.BusList    = new HashListImpl(900);  // Bus name list Nominal size to start; gets reallocated
	    this.DeviceList = new HashListImpl(900);
	    this.AutoAddBusList = new HashListImpl(100);

	    this.NumBuses   = 0;  // Eventually allocate a single source
	    this.NumDevices = 0;
	    this.NumNodes   = 0;

	    this.Faults       = new PointerListImpl(2);
	    this.CktElements  = new PointerListImpl(1000);
	    this.PDElements   = new PointerListImpl(1000);
	    this.PCElements   = new PointerListImpl(1000);
	    this.DSSControls  = new PointerListImpl(10);
	    this.Sources      = new PointerListImpl(10);
	    this.MeterElements= new PointerListImpl(20);
	    this.Monitors     = new PointerListImpl(20);
	    this.EnergyMeters = new PointerListImpl(5);
	    this.Sensors      = new PointerListImpl(5);
	    this.Generators   = new PointerListImpl(5);
	    this.StorageElements = new PointerListImpl(5);
	    this.Feeders      = new PointerListImpl(10);
	    this.Substations  = new PointerListImpl(5);
	    this.Transformers = new PointerListImpl(10);
	    this.CapControls  = new PointerListImpl(10);
	    this.SwtControls  = new PointerListImpl(50);
	    this.RegControls  = new PointerListImpl(5);
	    this.Lines        = new PointerListImpl(1000);
	    this.Loads        = new PointerListImpl(1000);
	    this.ShuntCapacitors = new PointerListImpl(20);

	    this.Buses     = new Bus[this.MaxBuses];
	    this.MapNodeToBus = NodeBus[this.MaxNodes];
	    this.DeviceRef = new CktElementDef[MaxDevices];

	    this.ControlQueue = new ControlQueue();

	    this.LegalVoltageBases = new double[8];
	     // Default Voltage Bases
	    this.LegalVoltageBases[0] = 0.208;
	    this.LegalVoltageBases[1] = 0.480;
	    this.LegalVoltageBases[2] = 12.47;
	    this.LegalVoltageBases[3] = 24.9;
	    this.LegalVoltageBases[4] = 34.5;
	    this.LegalVoltageBases[5] = 115.0;
	    this.LegalVoltageBases[6] = 230.0;
	    this.LegalVoltageBases[7] = 0.0;  // terminates array

	    this.NodeBufferMax = 20;
	    this.NodeBuffer    = new int[this.NodeBufferMax]; // A place to hold the nodes

	     // Init global circuit load and harmonic source multipliers
	    this.LoadMultiplier = 1.0;
	    this.GenMultiplier = 1.0;
	    this.HarmMult = 1.0;

	    this.PriceSignal = 25.0;   // $25/MWH

	     // Factors for Autoadd stuff
	    this.UEWeight   = 1.0;  // Default to weighting UE same as losses
	    this.LossWeight = 1.0;
	    this.NumUEregs  = 1;
	    this.NumLossRegs = 1;
	    this.UEregs = new int[NumUEregs];
	    this.LossRegs = new int[NumLossRegs];
	    this.UEregs[0]      = 10;   // Overload UE
	    this.LossRegs[0]    = 13;   // Zone Losses

	    this.CapacityStart = 0.9;     // for Capacity search
	    this.CapacityIncrement = 0.005;

	    this.LoadDurCurve    = "";
	    this.LoadDurCurveObj = null;
	    this.PriceCurve    = "";
	    this.PriceCurveObj = null;

        // Flags
	    this.DuplicatesAllowed   = false;
	    this.ZonesLocked         = false;   // Meter zones recomputed after each change
	    this.MeterZonesComputed  = false;
	    this.PositiveSequence    = false;

	    this.NormalMinVolts = 0.95;
	    this.NormalMaxVolts = 1.05;
	    this.EmergMaxVolts  = 1.08;
	    this.EmergMinVolts  = 0.90;

	    this.NodeMarkerCode = 16;
	    this.NodeMarkerWidth = 1;
	    this.MarkSwitches     = false;
	    this.MarkTransformers = false;
	    this.SwitchMarkerCode = 5;
	    this.TransMarkerCode  = 35;
	    this.TransMarkerSize  = 1;


	    this.TrapezoidalIntegration = false;  // Default to Euler method
	    this.LogEvents = false;

	    this.GeneratorDispatchReference = 0.0;
	    this.DefaultGrowthRate = 1.025;
	    this.DefaultGrowthFactor = 1.0;

	    this.DefaultDailyShapeObj  = LoadShapeClass.Find("default");
	    this.DefaultYearlyShapeObj = LoadShapeClass.Find("default");

	    this.CurrentDirectory = "";

	    this.BusNameRedefined = true;  // set to force rebuild of buslists, nodelists

	    this.SavedBuses = null;
	    this.SavedBusNames = null;

	    this.ReductionStrategy = rsDefault;
	    this.ReductionMaxAngle = 15.0;
	    this.ReductionZmag = 0.02;

	    /* Misc objects */
	    this.AutoAddObj = new AutoAdd();

	    this.Branch_List = null;
	    this.BusAdjPC = null;
	    this.BusAdjPD = null;
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

	public PointerList getFaults() {
		return Faults;
	}

	public void setFaults(PointerList faults) {
		Faults = faults;
	}

	public PointerList getCktElements() {
		return CktElements;
	}

	public void setCktElements(PointerList cktElements) {
		CktElements = cktElements;
	}

	public PointerList getPDElements() {
		return PDElements;
	}

	public void setPDElements(PointerList pDElements) {
		PDElements = pDElements;
	}

	public PointerList getPCElements() {
		return PCElements;
	}

	public void setPCElements(PointerList pCElements) {
		PCElements = pCElements;
	}

	public PointerList getDSSControls() {
		return DSSControls;
	}

	public void setDSSControls(PointerList dSSControls) {
		DSSControls = dSSControls;
	}

	public PointerList getSources() {
		return Sources;
	}

	public void setSources(PointerList sources) {
		Sources = sources;
	}

	public PointerList getMeterElements() {
		return MeterElements;
	}

	public void setMeterElements(PointerList meterElements) {
		MeterElements = meterElements;
	}

	public PointerList getSensors() {
		return Sensors;
	}

	public void setSensors(PointerList sensors) {
		Sensors = sensors;
	}

	public PointerList getMonitors() {
		return Monitors;
	}

	public void setMonitors(PointerList monitors) {
		Monitors = monitors;
	}

	public PointerList getEnergyMeters() {
		return EnergyMeters;
	}

	public void setEnergyMeters(PointerList energyMeters) {
		EnergyMeters = energyMeters;
	}

	public PointerList getGenerators() {
		return Generators;
	}

	public void setGenerators(PointerList generators) {
		Generators = generators;
	}

	public PointerList getStorageElements() {
		return StorageElements;
	}

	public void setStorageElements(PointerList storageElements) {
		StorageElements = storageElements;
	}

	public PointerList getSubstations() {
		return Substations;
	}

	public void setSubstations(PointerList substations) {
		Substations = substations;
	}

	public PointerList getTransformers() {
		return Transformers;
	}

	public void setTransformers(PointerList transformers) {
		Transformers = transformers;
	}

	public PointerList getCapControls() {
		return CapControls;
	}

	public void setCapControls(PointerList capControls) {
		CapControls = capControls;
	}

	public PointerList getRegControls() {
		return RegControls;
	}

	public void setRegControls(PointerList regControls) {
		RegControls = regControls;
	}

	public PointerList getLines() {
		return Lines;
	}

	public void setLines(PointerList lines) {
		Lines = lines;
	}

	public PointerList getLoads() {
		return Loads;
	}

	public void setLoads(PointerList loads) {
		Loads = loads;
	}

	public PointerList getShuntCapacitors() {
		return ShuntCapacitors;
	}

	public void setShuntCapacitors(PointerList shuntCapacitors) {
		ShuntCapacitors = shuntCapacitors;
	}

	public PointerList getFeeders() {
		return Feeders;
	}

	public void setFeeders(PointerList feeders) {
		Feeders = feeders;
	}

	public PointerList getSwtControls() {
		return SwtControls;
	}

	public void setSwtControls(PointerList swtControls) {
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

	public int getNumUEregs() {
		return NumUEregs;
	}

	public void setNumUEregs(int numUEregs) {
		NumUEregs = numUEregs;
	}

	public int getNumLossRegs() {
		return NumLossRegs;
	}

	public void setNumLossRegs(int numLossRegs) {
		NumLossRegs = numLossRegs;
	}

	public int[] getUEregs() {
		return UEregs;
	}

	public void setUEregs(int[] uEregs) {
		UEregs = uEregs;
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

	public LoadShapeObj getPriceCurveObj() {
		return PriceCurveObj;
	}

	public void setPriceCurveObj(LoadShapeObj priceCurveObj) {
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

	public boolean isIsSolved() {
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

	public double[] getDefaultHourMult() {
		return DefaultHourMult;
	}

	public void setDefaultHourMult(double[] defaultHourMult) {
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

	public ReductionStrategy getReductionStrategy() {
		return ReductionStrategy;
	}

	public void setReductionStrategy(ReductionStrategy reductionStrategy) {
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

	private void addDeviceHandle(int Handle) {
		if (NumDevices > MaxDevices) {
			MaxDevices = MaxDevices + IncDevices;
			// FIXME: Set min capacity of array list
			DeviceRef = new CktElementDef[MaxDevices];
		}
		DeviceRef[NumDevices].devHandle = Handle;    // Index into CktElements
		DeviceRef[NumDevices].CktElementClass = DSSGlobalsImpl.getInstance().LastClassReferenced;
	}

	private void addABus() {
	    if (NumBuses > MaxBuses) {
	    	MaxBuses += IncBuses;
			// FIXME: Set min capacity of array list
	    	Buses = new Bus[MaxBuses];
	    }
	}

	private void addANodeBus() {
		if (NumNodes > MaxNodes) {
			MaxNodes += IncNodes;
			// FIXME: Set min capacity of array list
			MapNodeToBus = new NodeBus[MaxNodes];
		}
	}

	private int addBus(String BusName, int NNodes) {
		// Trap error in bus name
	    if (BusName.length() == 0) {  // Error in busname
	       DSSGlobalsImpl.getInstance().DoErrorMsg("TDSSCircuit.AddBus", "BusName for Object \"" + ActiveCktElement.Name + "\" is null.",
	                  "Error in definition of object.", 424);
	       for (int i = 0; i < ActiveCktElement.NConds; i++) {
	    	   NodeBuffer[i] = 0;
	       }
	       return 0;
	    }

	    int Result = BusList.Find(BusName);
	    if (Result == 0) {
	         Result = BusList.Add(BusName);    // Result is index of bus
	         NumBuses += 1;
	         AddABus();   // Allocates more memory if necessary
	         Buses[NumBuses] = new DSSBus();
	    }

	    /* Define nodes belonging to the bus */
	    /* Replace Nodebuffer values with global reference number */
	    int NodeRef;
	    for (int i = 0; i < NNodes; i++) {
	         NodeRef = Buses[Result].Add(NodeBuffer[i]);
	         if (NodeRef == NumNodes) { // This was a new node so Add a NodeToBus element ????
	             AddANodeBus();   // Allocates more memory if necessary
	             MapNodeToBus[NumNodes].BusRef  = Result;
	             MapNodeToBus[NumNodes].NodeNum = NodeBuffer[i];
	         }
	         NodeBuffer[i] = NodeRef;  //  Swap out in preparation to setnoderef call
	    }
		return Result;
	}

	public void setActiveCktElement(DSSCktElement Value) {
		ActiveCktElement = Value;
		DSSGlobalsImpl.getInstance().ActiveDSSObject = Value;
	}

	public DSSCktElement getActiveCktElement() {
		return null;
	}

	public void setBusNameRedefined(boolean Value) {
		BusNameRedefined = Value;

		if (Value) {
			Solution.SystemYChanged = true;  // Force Rebuilding of SystemY if bus def has changed
			Control_BusNameRedefined = true;  // So controls will know buses redefined
		}
	}

	public boolean isBusNameRedefined() {

	}

	/* Total Circuit PD Element losses */
	public double[] getLosses() {
		PDElement pdElem = PDElements.First();
		Result = DSSGlobalsImpl.getInstance().cZERO;
		while (pdElem != null) {
			if (pdElem.isEnabled()) {
				/* Ignore Shunt Elements */
				if (!pdElem.IsShunt())
					Result = Resault + pdElem.getLosses();
			}
			pdElem = PDElements.Next();
		}
		return Result;
	}

	public void setLoadMultiplier(double Value) {
		if (Value != LoadMultiplier) {
			// We may have to change the Y matrix if the load multiplier  has changed
			switch (Solution.getLoadModel()) {
			case ADMITTANCE:
				InvalidateAllPCElements();
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
		/* Restore  kV bases, other values to buses still in the list */
		for (int i = 0; i < SavedNumBuses; i++) {
			int idx = BusList.Find(SavedBusNames[i]);
			if (idx != -1) {
				Buses[idx].pBus = SavedBuses[i];
				Buses[idx].kvBase = pBus.kVBase;
				Buses[idx].x = pBus.x;
				Buses[idx].y = pBus.y;
				Buses[idx].CoordDefined = pBus.CoordDefined;
				Buses[idx].Keep = pBus.Keep;
	            /* Restore Voltages in new bus def that existed in old bus def */
				if (pBus.VBus != null) {
					for (int j = 0; j < pBus.NumNodesThisBus; j++) {
						// Find index in new bus for j-th node  in old bus
						int jdx = Buses[idx].FindIdx(pBus.GetNum(j));
	                    if (jdx > -1) Vbus[jdx] = pBus.VBus[j];
					}
				}
			}
			SavedBusNames[i] = ""; // De-allocate string
		}

	    if (SavedBuses != null)
	    	for (int i = 0; i < SavedNumBuses; i++)
	    		SavedBuses[i].Free();  // gets rid of old bus voltages, too

	    SavedBuses = null; //ReallocMem(SavedBuses, 0);
	    SavedBusNames = null; //ReallocMem(SavedBusNames, 0);
	}

	private boolean saveMasterFile() {
		boolean Result = false;
		try {
			File FD = new File("Master.DSS");
			PrintStream F = new PrintStream(FD);

		    F.println("Clear");
		    F.println("New Circuit." + getName());
		    F.println();
		    if (PositiveSequence) F.println("Set Cktmodel=Positive");
		    if (DuplicatesAllowed) F.println("set allowdup=yes");
		    F.println();

		    // Write Redirect for all populated DSS Classes  Except Solution Class
		    for (int i = 0; i < DSSGlobalsImpl.getInstance().SavedFileList.Count; i++) {
		    	F.println("Redirect " + SavedFileList.Strings[i-1]);
			}

		    if (new File("buscoords.dss").exists()) {
		        F.println("MakeBusList");
		        F.println("Buscoords buscoords.dss");
		    }

		    F.close();
		    Result = true;
		} catch (Exception e) {
			DoSimpleMsg("Error Saving Master File: " + e.getMessage(), 435);
		}

		return Result;
	}

	private boolean saveDSSObjects() {
		DSSClassImpl DSS_Class;
		// Write Files for all populated DSS Classes  Except Solution Class
		for (int i = 0; i < DSSGlobalsImpl.getInstance().DSSClassList.getListSize(); i++) {
			Dss_Class = DSSClassList.Get(i);
			if ((DSS_Class == SolutionClass) || Dss_Class.isSaved()) continue;   // Cycle to next
	            /* use default filename=classname */
			if (!Utilities.WriteClassFile(Dss_Class, "", (DSS_Class == TCktElementClass) )) return false;  // bail on error
			DSS_Class.setSaved(true);
		}
		return true;
	}

	private boolean saveFeeders() {
		String SaveDir, CurrDir;
		EnergyMeter Meter;

		boolean Result = true;
		/* Write out all energy meter  zones to separate subdirectories */
		SaveDir = "";//GetCurrentDir;
		for (int i = 0; i < EnergyMeters.getListSize(); i++) {
			Meter = EnergyMeters.Get(i); // Recast pointer
			CurrDir = Meter.getName();
			if (new File(CurrDir).mkdir()) {
//				SetCurrentDir(CurrDir);
				Meter.SaveZone(CurrDir);
//				SetCurrentDir(SaveDir);
			} else {
				DSSGlobalsImpl.getInstance().DoSimpleMsg("Cannot create directory: " + CurrDir, 436);
				Result = false;
//				SetCurrentDir(SaveDir);  // back to whence we came
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

			for (int i = 0; i < NumBuses; i++) {
				if (Buses[i].CoordDefined)
					F.println(Utilities.CheckForBlanks(BusList.Get(i)), Format(", %-g, %-g", Buses[i].x, Buses[i].y));
			}

			F.close();

			Result = true;
		} catch (Exception e) {
			DSSGlobalsImpl.getInstance().DoSimpleMsg("Error creating Buscoords.dss.", 437);
		}

		return false;
	}

	/* Reallocate the device list to improve the performance of searches */
	private void reallocDeviceList() {
		if (LogEvents) Utilities.LogThisEvent("Reallocating Device List");
	    HashListImpl TempList = new HashListImpl(2 * NumDevices);

	    for (int i = 0; i < DeviceList.getListSize(); i++) {
	        Templist.Add(DeviceList.Get(i));
	    }

	    DeviceList.Free(); // Throw away the old one.
	    DeviceList = TempList;
	}

	public void setCaseName(String Value) {
		CaseName = Value;
		DSSGlobalsImpl.getInstance().CircuitName_ = Value + "_";
	}

	public String getCaseName() {
		return this.getLocalName();
	}

	public String getName() {
		return null;
	}

	/* Adds last DSS object created to circuit */
	public void addCktElement(int Handle) {
		// Update lists that keep track of individual circuit elements
		NumDevices += 1;

		// Resize DeviceList if no. of devices greatly exceeds allocation
		if (NumDevices > 2 * DeviceList.InitialAllocation) ReAllocDeviceList();
		DeviceList.Add(ActiveCktElement.getName());
		CktElements.Add(ActiveCktElement);

		/* Build Lists of PC and PD elements */
		if (ActiveCktElement instanceof PDElement) {
			PDElements.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof PCElement) {
			PCElements.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof ControlElement) {
			DSSControls.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof MeterElement) {
			MeterElements.Add(ActiveCktElement);
		}

		/* Build  lists of Special elements and generic types */
		if (ActiveCktElement instanceof MonitorElement) {
			Monitors.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof EnergyMeter) {
		    EnergyMeters.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof SensorElement) {
			Sensors.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Generator) {
			Generators.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Source) {
			Sources.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof CapacitorControl) {
			CapControls.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof SWTControl) {
			SwtControls.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof RegulatorControl) {
			RegControls.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Load) {
			Loads.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof CapacitorElement) {
			ShuntCapacitors.Add(ActiveCktElement);
		}
		/* Keep Lines, Transformer, and Lines and Faults in PDElements and
		separate lists so we can find them quickly. */
		else if (ActiveCktElement instanceof TransformerElement) {
			Transformers.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Line) {
			Lines.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Fault) {
			Faults.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Feeder) {
			Feeders.Add(ActiveCktElement);
		} else if (ActiveCktElement instanceof Storage) {
			StorageElements.Add(ActiveCktElement);
		}

		// AddDeviceHandle(Handle); // Keep Track of this device result is handle
		AddDeviceHandle(CktElements.getListSize()); // Handle is global index into CktElements
		ActiveCktElement.Handle = CktElements.getListSize();
	}

	/* Totalize all energymeters in the problem */
	public void totalizeMeters() {
		for (int i = 0; i < EnergyMeter.NumEMRegisters; i++) {
			RegisterTotals[i] = 0.0;
		}

	    EnergyMeter pEM = EnergyMeters.First();
	    while (pEM != null) {
	    	for (int i = 0; i < NumEMRegisters; i++) {
	    		RegisterTotals[i] = RegisterTotals[i] + Registers[i] * TotalsMask[i];
			}
	        pEM = EnergyMeters.Next();
	    }
	}

	private double sumSelectedRegisters(double[] mtrRegisters, int[] Regs, int count) {
		double Result = 0.0;
		for (int i = 0; i < count; i++)
			Result = Result + mtrRegisters[Regs[i]];
		return Result;
	}
	public boolean computeCapacity() {
		boolean Result = false;
		if (EnergyMeters.getListSize() == 0) {
			DSSGlobalsImpl.getInstance().DoSimpleMsg("Cannot compute system capacity with EnergyMeter objects!", 430);
			return;
		}

		if (NumUERegs == 0) {
			DSSGlobalsImpl.getInstance().DoSimpleMsg("Cannot compute system capacity with no UE resisters defined.  Use SET UEREGS=(...) command.", 431);
			return;
		}

		Solution.Mode = SNAPSHOT;
		LoadMultiplier = CapacityStart;
		CapacityFound = False;

		while ((LoadMultiplier <= 1.0) && !CapacityFound) {
			EnergyMeterClass.ResetAll();
			Solution.Solve();
			EnergyMeterClass.SampleAll();
			TotalizeMeters();

			// Check for non-zero in UEregs
			if (SumSelectedRegisters(RegisterTotals, UEregs, NumUEregs) != 0.0)
				CapacityFound = true;
			// LoadMultiplier is a property ...
			if (!CapacityFound) LoadMultiplier = LoadMultiplier + CapacityIncrement;
		}

		if (LoadMultiplier > 1.0) LoadMultiplier = 1.0;
		Result = true;

		return Result;
	}

	public boolean save(String Dir) {
		// Make a new subfolder in the present folder based on the circuit name and
		// a unique sequence number
		String SaveDir = "";//GetCurrentDir;  // remember where to come back to
		boolean Success = false;
		if (Dir.length() == 0) {
		    Dir = getName();

		    String CurrDir = Dir;
		    for (int i = 0; i < 999; i++) {  // Find a unique dir name
		    	File F = new File(CurrDir);
		        if (!F.exists()) {
		            if (F.mkdir()) {
//		            	SetCurrentDir(CurrDir);
		                Success = true;
		                break;
		            }
		        }
		        CurrDir = Dir + Format("%.3d", i);
		    }
		} else {
			File F = new File(Dir);
			if (!F.exists()) {
				CurrDir = Dir;
		        F = new File(CurrDir);
		        if (F.mkdir()) {
//		            SetCurrentDir(CurrDir);
		            Success = true;
		        }
			} else {  // Exists - overwrite
		        CurrDir = Dir;
//		        SetCurrentDir(CurrDir);
		        Success = true;
			}
		}

		if (!Success) {
		       DSSGlobalsImpl.getInstance().DoSimpleMsg("Could not create a folder \"" + Dir + "\" for saving the circuit.", 432);
		       return;
		}

		SavedFileList.Clear();  // This list keeps track of all files saved

		// Initialize so we will know when we have saved the circuit elements
		for (int i = 0; i < CktElements.getListSize(); i++) {
			DSSCktElement(CktElements.Get(i)).HasBeenSaved = false;
		}

		// Initialize so we don't save a class twice
		for (int i = 0; i < DSSClassList.getListSize(); i++) {
			DssClass(DSSClassList.Get(i)).Saved = false;
		}

		// Ignore Feeder Class -- gets saved with Energymeters
		FeederClass.Saved = true;  // will think this class is already saved

		// Define voltage sources first
		Success = Utilities.WriteVsourceClassFile(GetDSSClassPtr("vsource"), true);
		// Write library files so that they will be available to lines, loads, etc
		/* Use default filename=classname */
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("wiredata"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("linegeometry"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("linecode"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("linespacing"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("linecode"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("xfmrcode"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("growthshape"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("TCC_Curve"), "", false);
	    if (Success) Success = Utilities.WriteClassFile(GetDssClassPtr("Spectrum"), "", false);
	    if (Success) Success = SaveFeeders(); // Save feeders first
	    if (Success) Success = SaveDSSObjects();  // Save rest ot the objects
	    if (Success) Success = SaveBusCoords();
	    if (Success) Success = SaveMasterFile();

	    if (Success) {
	    	DSSGlobalsImpl.getInstance().DoSimpleMsg("Circuit saved in directory: " + GetCurrentDir, 433);
	    } else {
	    	DSSGlobalsImpl.getInstance().DoSimpleMsg("Error attempting to save circuit in " + GetCurrentDir, 434);
	    }
	    // Return to Original directory
//		SetCurrentDir(SaveDir);

		return true;
	}

	public void processBusDefs() {
	    int np = ActiveCktElement.NPhases;
	    int NCond = ActiveCktElement.NConds;

	    Parser.Token = ActiveCktElement.FirstBus;     // use parser functions to decode
	    for (int iTerm = 0; iTerm < ActiveCktElement.Nterms; iTerm++) {
	    	boolean NodesOK = true;
	        // Assume normal phase rotation  for default
	    	for (int i = 0; i < np; i++)
				NodeBuffer[i] = i; // set up buffer with defaults

	        // Default all other conductors to a ground connection
	        // If user wants them ungrounded, must be specified explicitly!
	    	for (int i = np + 1; i < NCond; i++)
				NodeBuffer[i] = 0;

			// Parser will override bus connection if any specified
	        BusName = Parser.ParseAsBusName(NNodes, NodeBuffer);

	    	// Check for error in node specification
	    	for (int j = 0; j < NNodes; j++) {
				if (NodeBuffer[j] < 0) {
					int retval = DSSMessageDlg("Error in Node specification for Element: \""
	                     + ActiveCktElement.ParentClass.Name + "." + Name + "\"" + CRLF +
	                     "Bus Spec: \"" + Parser.Token + "\"", false);
					NodesOK = false;
	                if (retval == -1) {
	                    AbortBusProcess = true;
	                    DSSGlobalsImpl.getInstance().AppendGlobalResult("Aborted bus process.");
	                    return;
	                }
	                break;
				}
	    	}


	        // Node -Terminal Connnections
	        // Caution: Magic -- AddBus replaces values in nodeBuffer to correspond
	        // with global node reference number.
	        if (NodesOK) {
	        	ActiveCktElement.ActiveTerminalIdx = iTerm;
	        	ActiveCktElement.ActiveTerminal.BusRef = AddBus(BusName, Ncond);
	            SetNodeRef(iTerm, NodeBuffer);  // for active circuit
	        }
	        Parser.Token = NextBus;
	    }
	}

	/* Redo all Buslists, nodelists */
	public void reProcessBusDefs() {
		if (LogEvents) Utilities.LogThisEvent("Reprocessing Bus Definitions");

		AbortBusProcess = false;
	    SaveBusInfo();  // So we don't have to keep re-doing this
	    // Keeps present definitions of bus objects until new ones created

	    // get rid of old bus lists
	    BusList.Free();  // Clears hash list of Bus names for adding more
	    BusList = new HashListImpl(NumDevices);  // won't have many more buses than this

	    NumBuses = 0;  // Leave allocations same, but start count over
	    NumNodes = 0;

	    // Now redo all enabled circuit elements
	    CktElement CktElementSave = ActiveCktElement;
	    ActiveCktElement = CktElements.First();
	    while (ActiveCktElement != null) {
	        if (ActiveCktElement.isEnabled) ProcessBusDefs();
	        if (AbortBusProcess) return;
	        ActiveCktElement = CktElements.Next();
	    }

	    ActiveCktElement = CktElementSave;  // restore active circuit element

	    for (int i = 0; i < NumBuses; i++) Buses[i].AllocateBusVoltages();
	    for (int i = 0; i < array.length; i++) Buses[i].AllocateBusCurrents();

	    RestoreBusInfo();     // frees old bus info, too
	    DoResetMeterZones();  // Fix up meter zones to correspond

	    BusNameRedefined = false;  // Get ready for next time
	}

	public void doResetMeterZones() {
		/* Do this only if meterzones unlocked .  Normally, Zones will remain
		   unlocked so that all changes to the circuit will result in rebuilding
		   the lists */
		if (!MeterZonesComputed || !ZonesLocked) {
			if (LogEvents) Utilities.LogThisEvent("Resetting Meter Zones");
			DSSGlobalsImpl.getInstance().EnergyMeterClass.ResetMeterZonesAll();
			MeterZonesComputed = true;
			if (LogEvents) Utilities.LogThisEvent("Done Resetting Meter Zones");
		}

		FreeTopology();
	}

	public int setElementActive(String FullObjectName) {
		int Result = 0;

		Utilities.ParseObjectClassandName(FullObjectName, DevType, DevName);
		DevClassIndex = ClassNames.Find(DevType);
		if (DevClassIndex == 0)
			DevClassIndex = DSSGlobalsImpl.getInstance().LastClassReferenced;
		Devindex = DeviceList.Find(DevName);
		while (DevIndex >= 0) {
			if (DeviceRef[Devindex].CktElementClass == DevClassIndex) {  // we got a match
				DSSGlobalsImpl.getInstance().ActiveDSSClass = DSSGlobalsImpl.getInstance().DSSClassList.Get(DevClassIndex);
				DSSGlobalsImpl.getInstance().LastClassReferenced = DevClassIndex;
				Result = DeviceRef[Devindex].devHandle;
				// ActiveDSSClass.Active = Result;
				//  ActiveCktElement = ActiveDSSClass.GetActiveObj;
				ActiveCktElement = CktElements.Get(Result);
				break;
			}
			DevIndex = Devicelist.FindNext();   // Could be duplicates
		}

		DSSGlobalsImpl.getInstance().CmdResult = Result;

		return Result;
	}

	public void invalidateAllPCElements() {
		DSSCktElement p = PCElements.First();
		while (p != null) {
	        p.YprimInvalid = true;
	        p = PCElements.Next();
		}

		// Force rebuild of matrix on next solution
		Solution.SystemYChanged = true;
	}

	public void debugDump(PrintStream F) {
		F.println("NumBuses= " + NumBuses);
	    F.println("NumNodes= " + NumNodes);
	    F.println("NumDevices= " + NumDevices);
	    F.println("BusList:");
	    for (int i = 0; i < NumBuses; i++) {
	        F.print("  %12s", BusList.Get(i));
	        F.print(" (" + Buses[i].NumNodesThisBus + " Nodes)");
	        for (int j = 0; j < Buses[i].NumNodesThisBus; j++) {
	        	F.print(" " + Buses[i].GetNum(j));
	        }
	        F.println();
	    }
	    F.println("DeviceList:");
	    for (int i = 0; i < NumDevices; i++) {
			F.println("  %12s", DeviceList.Get(i));
	        ActiveCktElement = CktElements.Get(i);
	        if (!ActiveCktElement.isEnabled())
	        	F.print("  DISABLED");
	        F.println();
	    }
	    F.println("NodeToBus Array:");
	    for (int i = 0; i < NumNodes; i++) {
	       int j = MapNodeToBus[i].BusRef;
	       F.print("  " + i + " " + j + " (=" +BusList.Get(j) + "." + MapNodeToBus[i].NodeNum + ")");
	       F.println();
	    }
	}

	/* Access to topology from the first source */
	public CktTree getTopology() {
		DSSCktElement Elem;

		if (Branch_List == null) {
			/* Initialize all Circuit Elements and Buses to not checked, then build a new tree */
			Elem = CktElements.First();
			while (elem != null) {
				Elem.Checked = false;
				for (int i = 0; i < Elem.Nterms; i++) {
					Elem.Terminals[i].Checked = false;
				}
				Elem.IsIsolated = true; // till proven otherwise
				Elem = CktElements.Next();
			}
			for (int i = 0; i < NumBuses; i++) {
				Buses[i].BusChecked = false;
			}
			Branch_List = CktTreeImpl.GetIsolatedSubArea(Sources.First, true);  // calls back to build adjacency lists
		}
		return Branch_List;
	}

	public void freeTopology() {
		if (Branch_List != null) Branch_List.Free();
		Branch_List = null;
		if (BusAdjPC != null) CktTreeImpl.FreeAndNilBusAdjacencyLists(BusAdjPD, BusAdjPC);
	}

	public AdjArray getBusAdjacentPDLists() {
		if (BusAdjPD == null) CktTreeImpl.BuildActiveBusAdjacencyLists(BusAdjPD, BusAdjPC);
		return BusAdjPD;
	}

	public AdjArray getBusAdjacentPCLists() {
		if (BusAdjPC == null) CktTreeImpl.BuildActiveBusAdjacencyLists(BusAdjPD, BusAdjPC);
		return BusAdjPC;
	}

}
