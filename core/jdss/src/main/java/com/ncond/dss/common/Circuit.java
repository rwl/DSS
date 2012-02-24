package com.ncond.dss.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.impl.DSSBus.NodeBus;
import com.ncond.dss.common.impl.DSSCircuit.CktElementDef;
import com.ncond.dss.common.impl.DSSCircuit.ReductionStrategyType;
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
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.HashList;

public interface Circuit extends NamedObject {

	int getActiveBusIndex();

	void setActiveBusIndex(int activeBusIndex);

	double getFundamental();

	void setFundamental(double fundamental);

	boolean isControlBusNameRedefined();

	void setControlBusNameRedefined(boolean controlBusNameRedefined);

	HashList getBusList();

	void setBusList(HashList busList);

	HashList getAutoAddBusList();

	void setAutoAddBusList(HashList autoAddBusList);

	HashList getDeviceList();

	void setDeviceList(HashList deviceList);

	CktElementDef[] getDeviceRef();

	void setDeviceRef(CktElementDef[] deviceRef);

	ArrayList<FaultObj> getFaults();

	void setFaults(ArrayList<FaultObj> faults);

	ArrayList<CktElement> getCktElements();

	void setCktElements(ArrayList<CktElement> cktElements);

	ArrayList<PDElement> getPDElements();

	void setPDElements(ArrayList<PDElement> PDElements);

	ArrayList<PCElement> getPCElements();

	void setPCElements(ArrayList<PCElement> PCElements);

	ArrayList<ControlElem> getDSSControls();

	void setDSSControls(ArrayList<ControlElem> DSSControls);

	ArrayList<PCElement> getSources();

	void setSources(ArrayList<PCElement> sources);

	ArrayList<MeterElement> getMeterElements();

	void setMeterElements(ArrayList<MeterElement> meterElements);

	ArrayList<SensorObj> getSensors();

	void setSensors(ArrayList<SensorObj> sensors);

	ArrayList<MonitorObj> getMonitors();

	void setMonitors(ArrayList<MonitorObj> monitors);

	ArrayList<EnergyMeterObj> getEnergyMeters();

	void setEnergyMeters(ArrayList<EnergyMeterObj> energyMeters);

	ArrayList<GeneratorObj> getGenerators();

	void setGenerators(ArrayList<GeneratorObj> generators);

	ArrayList<StorageObj> getStorageElements();

	void setStorageElements(ArrayList<StorageObj> storageElements);

	ArrayList<PVSystemObj> getPVSystems();

	void setPVSystems(ArrayList<PVSystemObj> PVSystems);

	ArrayList<DSSObject> getSubstations();

	void setSubstations(ArrayList<DSSObject> substations);

	ArrayList<TransformerObj> getTransformers();

	void setTransformers(ArrayList<TransformerObj> transformers);

	ArrayList<CapControlObj> getCapControls();

	void setCapControls(ArrayList<CapControlObj> capControls);

	ArrayList<RegControlObj> getRegControls();

	void setRegControls(ArrayList<RegControlObj> regControls);

	ArrayList<LineObj> getLines();

	void setLines(ArrayList<LineObj> lines);

	ArrayList<LoadObj> getLoads();

	void setLoads(ArrayList<LoadObj> loads);

	ArrayList<CapacitorObj> getShuntCapacitors();

	void setShuntCapacitors(ArrayList<CapacitorObj> shuntCapacitors);

	ArrayList<FeederObj> getFeeders();

	void setFeeders(ArrayList<FeederObj> feeders);

	ArrayList<SwtControlObj> getSwtControls();

	void setSwtControls(ArrayList<SwtControlObj> swtControls);

	ControlQueue getControlQueue();

	void setControlQueue(ControlQueue controlQueue);

	SolutionObj getSolution();

	void setSolution(SolutionObj solution);

	AutoAdd getAutoAddObj();

	void setAutoAddObj(AutoAdd autoAddObj);

	double getUEWeight();

	void setUEWeight(double UEWeight);

	double getLossWeight();

	void setLossWeight(double lossWeight);

	int getNumUERegs();

	void setNumUERegs(int numUERegs);

	int getNumLossRegs();

	void setNumLossRegs(int numLossRegs);

	int[] getUERegs();

	void setUERegs(int[] UEregs);

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

	PriceShapeObj getPriceCurveObj();

	void setPriceCurveObj(PriceShapeObj priceCurveObj);

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

	Bus getBus(int idx);

	void setBuses(Bus[] buses);

	NodeBus[] getMapNodeToBus();

	void setMapNodeToBus(NodeBus[] mapNodeToBus);

	boolean isSolved();

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

	Complex getDefaultHourMult();

	void setDefaultHourMult(Complex defaultHourMult);

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

	ReductionStrategyType getReductionStrategy();

	void setReductionStrategy(ReductionStrategyType reductionStrategy);

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

	int getActiveLoadShapeClass();

	void setActiveLoadShapeClass(int activeLoadShapeClass);

	void setActiveCktElement(CktElement shuntElement);

	CktElement getActiveCktElement();

	void setBusNameRedefined(boolean value);

	boolean isBusNameRedefined();

	/** Total circuit PD element losses */
	Complex getLosses();

	void setLoadMultiplier(double value);

	double getLoadMultiplier();

	void setCaseName(String value);

	String getCaseName();

	String getName();

	/** Adds last DSS object created to circuit */
	void addCktElement(int handle);

	/** Totalize all energymeters in the problem */
	void totalizeMeters();

	boolean computeCapacity();

	boolean save(String dir);

	void processBusDefs();

	/** Redo all bus lists and node lists */
	void reProcessBusDefs();

	void doResetMeterZones();

	int setElementActive(String fullObjectName);

	void invalidateAllPCElements();

	void debugDump(PrintStream f);

	/** Access to topology from the first source */
	CktTree getTopology();

	void freeTopology();

	List<PDElement>[] getBusAdjacentPDLists();

	List<PCElement>[] getBusAdjacentPCLists();

}
