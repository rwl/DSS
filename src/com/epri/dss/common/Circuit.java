package com.epri.dss.common;

import java.io.PrintStream;

import com.epri.dss.common.impl.DSSCircuit.CktElementDef;
import com.epri.dss.general.NamedObject;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.PointerList;

public interface Circuit extends NamedObject {

	public int getActiveBusIndex();

	public void setActiveBusIndex(int activeBusIndex);

	public double getFundamental();

	public void setFundamental(double fundamental);

	public boolean isControl_BusNameRedefined();

	public void setControl_BusNameRedefined(boolean control_BusNameRedefined);

	public HashList getBusList();

	public void setBusList(HashList busList);

	public HashList getAutoAddBusList();

	public void setAutoAddBusList(HashList autoAddBusList);

	public HashList getDeviceList();

	public void setDeviceList(HashList deviceList);

	public CktElementDef[] getDeviceRef();

	public void setDeviceRef(CktElementDef[] deviceRef);

	public PointerList getFaults();

	public void setFaults(PointerList faults);

	public PointerList getCktElements();

	public void setCktElements(PointerList cktElements);

	public PointerList getPDElements();

	public void setPDElements(PointerList pDElements);

	public PointerList getPCElements();

	public void setPCElements(PointerList pCElements);

	public PointerList getDSSControls();

	public void setDSSControls(PointerList dSSControls);

	public PointerList getSources();

	public void setSources(PointerList sources);

	public PointerList getMeterElements();

	public void setMeterElements(PointerList meterElements);

	public PointerList getSensors();

	public void setSensors(PointerList sensors);

	public PointerList getMonitors();

	public void setMonitors(PointerList monitors);

	public PointerList getEnergyMeters();

	public void setEnergyMeters(PointerList energyMeters);

	public PointerList getGenerators();

	public void setGenerators(PointerList generators);

	public PointerList getStorageElements();

	public void setStorageElements(PointerList storageElements);

	public PointerList getSubstations();

	public void setSubstations(PointerList substations);

	public PointerList getTransformers();

	public void setTransformers(PointerList transformers);

	public PointerList getCapControls();

	public void setCapControls(PointerList capControls);

	public PointerList getRegControls();

	public void setRegControls(PointerList regControls);

	public PointerList getLines();

	public void setLines(PointerList lines);

	public PointerList getLoads();

	public void setLoads(PointerList loads);

	public PointerList getShuntCapacitors();

	public void setShuntCapacitors(PointerList shuntCapacitors);

	public PointerList getFeeders();

	public void setFeeders(PointerList feeders);

	public PointerList getSwtControls();

	public void setSwtControls(PointerList swtControls);

	public ControlQueue getControlQueue();

	public void setControlQueue(ControlQueue controlQueue);

	public SolutionObj getSolution();

	public void setSolution(SolutionObj solution);

	public AutoAdd getAutoAddObj();

	public void setAutoAddObj(AutoAdd autoAddObj);

	public double getUEWeight();

	public void setUEWeight(double uEWeight);

	public double getLossWeight();

	public void setLossWeight(double lossWeight);

	public int getNumUEregs();

	public void setNumUEregs(int numUEregs);

	public int getNumLossRegs();

	public void setNumLossRegs(int numLossRegs);

	public int[] getUEregs();

	public void setUEregs(int[] uEregs);

	public int[] getLossRegs();

	public void setLossRegs(int[] lossRegs);

	public double getCapacityStart();

	public void setCapacityStart(double capacityStart);

	public double getCapacityIncrement();

	public void setCapacityIncrement(double capacityIncrement);

	public boolean isTrapezoidalIntegration();

	public void setTrapezoidalIntegration(boolean trapezoidalIntegration);

	public boolean isLogEvents();

	public void setLogEvents(boolean logEvents);

	public String getLoadDurCurve();

	public void setLoadDurCurve(String loadDurCurve);

	public LoadShapeObj getLoadDurCurveObj();

	public void setLoadDurCurveObj(LoadShapeObj loadDurCurveObj);

	public String getPriceCurve();

	public void setPriceCurve(String priceCurve);

	public LoadShapeObj getPriceCurveObj();

	public void setPriceCurveObj(LoadShapeObj priceCurveObj);

	public int getNumDevices();

	public void setNumDevices(int numDevices);

	public int getNumBuses();

	public void setNumBuses(int numBuses);

	public int getNumNodes();

	public void setNumNodes(int numNodes);

	public int getMaxDevices();

	public void setMaxDevices(int maxDevices);

	public int getMaxBuses();

	public void setMaxBuses(int maxBuses);

	public int getMaxNodes();

	public void setMaxNodes(int maxNodes);

	public int getIncDevices();

	public void setIncDevices(int incDevices);

	public int getIncBuses();

	public void setIncBuses(int incBuses);

	public int getIncNodes();

	public void setIncNodes(int incNodes);

	public Bus[] getBuses();

	public void setBuses(Bus[] buses);

	public NodeBus[] getMapNodeToBus();

	public void setMapNodeToBus(NodeBus[] mapNodeToBus);

	public boolean isIsSolved();

	public void setIsSolved(boolean isSolved);

	public boolean isDuplicatesAllowed();

	public void setDuplicatesAllowed(boolean duplicatesAllowed);

	public boolean isZonesLocked();

	public void setZonesLocked(boolean zonesLocked);

	public boolean isMeterZonesComputed();

	public void setMeterZonesComputed(boolean meterZonesComputed);

	public boolean isPositiveSequence();

	public void setPositiveSequence(boolean positiveSequence);

	public double getNormalMinVolts();

	public void setNormalMinVolts(double normalMinVolts);

	public double getNormalMaxVolts();

	public void setNormalMaxVolts(double normalMaxVolts);

	public double getEmergMaxVolts();

	public void setEmergMaxVolts(double emergMaxVolts);

	public double getEmergMinVolts();

	public void setEmergMinVolts(double emergMinVolts);

	public double[] getLegalVoltageBases();

	public void setLegalVoltageBases(double[] legalVoltageBases);

	public double getGeneratorDispatchReference();

	public void setGeneratorDispatchReference(double generatorDispatchReference);

	public double getDefaultGrowthFactor();

	public void setDefaultGrowthFactor(double defaultGrowthFactor);

	public double getDefaultGrowthRate();

	public void setDefaultGrowthRate(double defaultGrowthRate);

	public double getGenMultiplier();

	public void setGenMultiplier(double genMultiplier);

	public double getHarmMult();

	public void setHarmMult(double harmMult);

	public double[] getDefaultHourMult();

	public void setDefaultHourMult(double[] defaultHourMult);

	public double getPriceSignal();

	public void setPriceSignal(double priceSignal);

	public double[] getRegisterTotals();

	public void setRegisterTotals(double[] registerTotals);

	public LoadShapeObj getDefaultDailyShapeObj();

	public void setDefaultDailyShapeObj(LoadShapeObj defaultDailyShapeObj);

	public LoadShapeObj getDefaultYearlyShapeObj();

	public void setDefaultYearlyShapeObj(LoadShapeObj defaultYearlyShapeObj);

	public String getCurrentDirectory();

	public void setCurrentDirectory(String currentDirectory);

	public ReductionStrategy getReductionStrategy();

	public void setReductionStrategy(ReductionStrategy reductionStrategy);

	public double getReductionMaxAngle();

	public void setReductionMaxAngle(double reductionMaxAngle);

	public double getReductionZmag();

	public void setReductionZmag(double reductionZmag);

	public String getReductionStrategyString();

	public void setReductionStrategyString(String reductionStrategyString);

	public double getPctNormalFactor();

	public void setPctNormalFactor(double pctNormalFactor);

	public int getNodeMarkerCode();

	public void setNodeMarkerCode(int nodeMarkerCode);

	public int getNodeMarkerWidth();

	public void setNodeMarkerWidth(int nodeMarkerWidth);

	public int getSwitchMarkerCode();

	public void setSwitchMarkerCode(int switchMarkerCode);

	public int getTransMarkerSize();

	public void setTransMarkerSize(int transMarkerSize);

	public int getTransMarkerCode();

	public void setTransMarkerCode(int transMarkerCode);

	public boolean isMarkSwitches();

	public void setMarkSwitches(boolean markSwitches);

	public boolean isMarkTransformers();

	public void setMarkTransformers(boolean markTransformers) ;

	public void setActiveCktElement(DSSCktElement Value);

	public DSSCktElement getActiveCktElement();

	public void setBusNameRedefined(boolean Value);

	public boolean isBusNameRedefined();

	/* Total Circuit PD Element losses */
	public double[] getLosses();

	public void setLoadMultiplier(double Value);

	public double getLoadMultiplier();

	public void setCaseName(String Value);

	public String getCaseName();

	public String getName();

	/* Adds last DSS object created to circuit */
	public void addCktElement(int Handle);

	/* Totalize all energymeters in the problem */
	public void totalizeMeters();

	public boolean computeCapacity();

	public boolean save(String Dir);

	public void processBusDefs();

	/* Redo all Buslists, nodelists */
	public void reProcessBusDefs();

	public void doResetMeterZones();

	public int setElementActive(String FullObjectName);

	public void invalidateAllPCElements();

	public void debugDump(PrintStream F);

	/* Access to topology from the first source */
	public CktTree getTopology();

	public void freeTopology();

	public AdjArray getBusAdjacentPDLists();

	public AdjArray getBusAdjacentPCLists();

}
