package com.epri.dss.executive;

public interface IExecHelper {

	int doNewCmd();
	int doEditCmd();
	int doSelectCmd();
	int doMoreCmd();
	int doRedirect(boolean IsCompile);
	int doSaveCmd();
	int doSampleCmd();


	int doSolveCmd();
	int doEnableCmd();
	int doDisableCmd();

	int doOpenCmd();
	int doResetCmd();
	int doNextCmd();
	int doFormEditCmd();
	int doClassesCmd();
	int doUserClassesCmd();
	int doHelpCmd();
	int doClearCmd();
	int doReduceCmd();
	int doInterpolateCmd();

	int doCloseCmd();
	int doResetMonitors();

	int doFileEditCmd();
	int doQueryCmd();
	int doResetMeters();
	void doAboutBox();
	int doSetVoltageBases();
	int doSetkVBase();

	void doLegalVoltageBases();
	void doAutoAddBusList(String S);
	void doKeeperBusList(String S);
	void doSetReduceStrategy(String S);
	void doSetAllocationFactors(double X);
	void doSetCFactors(double X);

	int doVoltagesCmd(boolean PerUnit);
	int doCurrentsCmd ();
	int doPowersCmd ();
	int doSeqVoltagesCmd ();
	int doSeqCurrentsCmd ();
	int doSeqPowersCmd ();
	int doLossesCmd ();
	int doPhaseLossesCmd ();
	int doCktLossesCmd ();
	int doAllocateLoadsCmd ();
	int doHarmonicsList(String S);
	int doMeterTotals();
	int doCapacityCmd();
	int doZscCmd(boolean Zmatrix);
	int doZsc10Cmd();
	int doZscRefresh();

	int doBusCoordsCmd();
	int doGuidsCmd();
	int doSetLoadAndGenKVCmd();
	int doVarValuesCmd();
	int doVarNamesCmd ();

	int doMakePosSeq();
	int doAlignFileCmd();
	int doTOPCmd();
	int doRotateCmd();
	int doVDiffCmd();
	int doSummaryCmd();
	int doDistributeCmd();
	int doDI_PlotCmd();
	int doCompareCasesCmd();
	int doYearlyCurvesCmd();
	int doVisualizeCmd();
	int doCloseDICmd();
	int doADOScmd();
	int doEstimateCmd();
	int doReconductorCmd();
	int doAddMarkerCmd();
	int doCvrtLoadshapesCmd();

	void doSetNormal(double pctNormal);

	void setTime();

	void parseObjName(String fullname, String objname, String propname);

	void getObjClassAndName(String ObjClass, String ObjName);

	int addObject(String ObjType, String name);
	int editObject(String ObjType, String name);

	void setActiveCircuit(String cktname);

	int setActiveCktElement();

	int doPropertyDump();

}
