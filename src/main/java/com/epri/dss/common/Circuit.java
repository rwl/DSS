package com.epri.dss.common;

import java.io.PrintStream;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSBus.NodeBus;
import com.epri.dss.common.impl.DSSCircuit.CktElementDef;
import com.epri.dss.common.impl.DSSCircuit.ReductionStrategy;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.NamedObject;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.HashList;
import com.epri.dss.shared.PointerList;

public interface Circuit extends NamedObject {

	int getActiveBusIndex();

	void setActiveBusIndex(int activeBusIndex);

	double getFundamental();

	void setFundamental(double fundamental);

	boolean isControl_BusNameRedefined();

	void setControl_BusNameRedefined(boolean control_BusNameRedefined);

	HashList getBusList();

	void setBusList(HashList busList);

	HashList getAutoAddBusList();

	void setAutoAddBusList(HashList autoAddBusList);

	HashList getDeviceList();

	void setDeviceList(HashList deviceList);

	CktElementDef[] getDeviceRef();

	void setDeviceRef(CktElementDef[] deviceRef);

	PointerList getFaults();

	void setFaults(PointerList faults);

	PointerList getCktElements();

	void setCktElements(PointerList cktElements);

	PointerList getPDElements();

	void setPDElements(PointerList pDElements);

	PointerList getPCElements();

	void setPCElements(PointerList pCElements);

	PointerList getDSSControls();

	void setDSSControls(PointerList dSSControls);

	PointerList getSources();

	void setSources(PointerList sources);

	PointerList getMeterElements();

	void setMeterElements(PointerList meterElements);

	PointerList getSensors();

	void setSensors(PointerList sensors);

	PointerList getMonitors();

	void setMonitors(PointerList monitors);

	PointerList getEnergyMeters();

	void setEnergyMeters(PointerList energyMeters);

	PointerList getGenerators();

	void setGenerators(PointerList generators);

	PointerList getStorageElements();

	void setStorageElements(PointerList storageElements);

	PointerList getSubstations();

	void setSubstations(PointerList substations);

	PointerList getTransformers();

	void setTransformers(PointerList transformers);

	PointerList getCapControls();

	void setCapControls(PointerList capControls);

	PointerList getRegControls();

	void setRegControls(PointerList regControls);

	PointerList getLines();

	void setLines(PointerList lines);

	PointerList getLoads();

	void setLoads(PointerList loads);

	PointerList getShuntCapacitors();

	void setShuntCapacitors(PointerList shuntCapacitors);

	PointerList getFeeders();

	void setFeeders(PointerList feeders);

	PointerList getSwtControls();

	void setSwtControls(PointerList swtControls);

	ControlQueue getControlQueue();

	void setControlQueue(ControlQueue controlQueue);

	SolutionObj getSolution();

	void setSolution(SolutionObj solution);

	AutoAdd getAutoAddObj();

	void setAutoAddObj(AutoAdd autoAddObj);

	double getUEWeight();

	void setUEWeight(double uEWeight);

	double getLossWeight();

	void setLossWeight(double lossWeight);

	int getNumUEregs();

	void setNumUEregs(int numUEregs);

	int getNumLossRegs();

	void setNumLossRegs(int numLossRegs);

	int[] getUEregs();

	void setUEregs(int[] uEregs);

	int[] getLossRegs();

	void setLossRegs(int[] lossRegs);

	double getCapacityStart();

	void setCapacityStart(double capacityStart);

	double getCapacityIncrement();

	void setCapacityIncrement(double capacityIncrement);

	boolean isTrapezoidalIntegration();

	void setTrapezoidalIntegration(boolean trapezoidalIntegration);

	boolean isLogEvents();

	void setLogEvents(boolean logEvents);

	String getLoadDurCurve();

	void setLoadDurCurve(String loadDurCurve);

	LoadShapeObj getLoadDurCurveObj();

	void setLoadDurCurveObj(LoadShapeObj loadDurCurveObj);

	String getPriceCurve();

	void setPriceCurve(String priceCurve);

	LoadShapeObj getPriceCurveObj();

	void setPriceCurveObj(LoadShapeObj priceCurveObj);

	int getNumDevices();

	void setNumDevices(int numDevices);

	int getNumBuses();

	void setNumBuses(int numBuses);

	int getNumNodes();

	void setNumNodes(int numNodes);

	int getMaxDevices();

	void setMaxDevices(int maxDevices);

	int getMaxBuses();

	void setMaxBuses(int maxBuses);

	int getMaxNodes();

	void setMaxNodes(int maxNodes);

	int getIncDevices();

	void setIncDevices(int incDevices);

	int getIncBuses();

	void setIncBuses(int incBuses);

	int getIncNodes();

	void setIncNodes(int incNodes);

	Bus[] getBuses();

	void setBuses(Bus[] buses);

	NodeBus[] getMapNodeToBus();

	void setMapNodeToBus(NodeBus[] mapNodeToBus);

	boolean isIsSolved();

	void setIsSolved(boolean isSolved);

	boolean isDuplicatesAllowed();

	void setDuplicatesAllowed(boolean duplicatesAllowed);

	boolean isZonesLocked();

	void setZonesLocked(boolean zonesLocked);

	boolean isMeterZonesComputed();

	void setMeterZonesComputed(boolean meterZonesComputed);

	boolean isPositiveSequence();

	void setPositiveSequence(boolean positiveSequence);

	double getNormalMinVolts();

	void setNormalMinVolts(double normalMinVolts);

	double getNormalMaxVolts();

	void setNormalMaxVolts(double normalMaxVolts);

	double getEmergMaxVolts();

	void setEmergMaxVolts(double emergMaxVolts);

	double getEmergMinVolts();

	void setEmergMinVolts(double emergMinVolts);

	double[] getLegalVoltageBases();

	void setLegalVoltageBases(double[] legalVoltageBases);

	double getGeneratorDispatchReference();

	void setGeneratorDispatchReference(double generatorDispatchReference);

	double getDefaultGrowthFactor();

	void setDefaultGrowthFactor(double defaultGrowthFactor);

	double getDefaultGrowthRate();

	void setDefaultGrowthRate(double defaultGrowthRate);

	double getGenMultiplier();

	void setGenMultiplier(double genMultiplier);

	double getHarmMult();

	void setHarmMult(double harmMult);

	double[] getDefaultHourMult();

	void setDefaultHourMult(double[] defaultHourMult);

	double getPriceSignal();

	void setPriceSignal(double priceSignal);

	double[] getRegisterTotals();

	void setRegisterTotals(double[] registerTotals);

	LoadShapeObj getDefaultDailyShapeObj();

	void setDefaultDailyShapeObj(LoadShapeObj defaultDailyShapeObj);

	LoadShapeObj getDefaultYearlyShapeObj();

	void setDefaultYearlyShapeObj(LoadShapeObj defaultYearlyShapeObj);

	String getCurrentDirectory();

	void setCurrentDirectory(String currentDirectory);

	ReductionStrategy getReductionStrategy();

	void setReductionStrategy(ReductionStrategy reductionStrategy);

	double getReductionMaxAngle();

	void setReductionMaxAngle(double reductionMaxAngle);

	double getReductionZmag();

	void setReductionZmag(double reductionZmag);

	String getReductionStrategyString();

	void setReductionStrategyString(String reductionStrategyString);

	double getPctNormalFactor();

	void setPctNormalFactor(double pctNormalFactor);

	int getNodeMarkerCode();

	void setNodeMarkerCode(int nodeMarkerCode);

	int getNodeMarkerWidth();

	void setNodeMarkerWidth(int nodeMarkerWidth);

	int getSwitchMarkerCode();

	void setSwitchMarkerCode(int switchMarkerCode);

	int getTransMarkerSize();

	void setTransMarkerSize(int transMarkerSize);

	int getTransMarkerCode();

	void setTransMarkerCode(int transMarkerCode);

	boolean isMarkSwitches();

	void setMarkSwitches(boolean markSwitches);

	boolean isMarkTransformers();

	void setMarkTransformers(boolean markTransformers) ;

	void setActiveCktElement(DSSCktElement Value);

	DSSCktElement getActiveCktElement();

	void setBusNameRedefined(boolean Value);

	boolean isBusNameRedefined();

	/* Total Circuit PD Element losses */
	Complex getLosses();

	void setLoadMultiplier(double Value);

	double getLoadMultiplier();

	void setCaseName(String Value);

	String getCaseName();

	String getName();

	/* Adds last DSS object created to circuit */
	void addCktElement(int Handle);

	/* Totalize all energymeters in the problem */
	void totalizeMeters();

	boolean computeCapacity();

	boolean save(String Dir);

	void processBusDefs();

	/* Redo all Buslists, nodelists */
	void reProcessBusDefs();

	void doResetMeterZones();

	int setElementActive(String FullObjectName);

	void invalidateAllPCElements();

	void debugDump(PrintStream F);

	/* Access to topology from the first source */
	CktTree getTopology();

	void freeTopology();

	List<List<Object>> getBusAdjacentPDLists();

	List<List<Object>> getBusAdjacentPCLists();

}
