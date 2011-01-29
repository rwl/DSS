package com.epri.dss.meter.impl;

import java.io.File;

import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.SystemMeter;

public class EnergyMeterImpl extends MeterClassImpl implements EnergyMeter {

	private Generator GeneratorClass;
    private boolean SaveDemandInterval;
    private boolean DI_Verbose;
    private File OverLoadFile;
    private File VoltageFile;

    protected double[] DI_RegisterTotals;
    protected String DI_Dir;
    protected File DI_Totals;
    protected File MeterTotals;
    protected SystemMeter SystemMeter;
    protected boolean Do_OverloadReport;
    protected boolean Do_VoltageExceptionReport;
    protected boolean OverLoadFileIsOpen;
    protected boolean VoltageFileIsOpen;

	public EnergyMeterImpl() {
		// TODO Auto-generated constructor stub
	}

	private void processOptions(String Opts) {

	}

	public void setSaveDemandInterval(boolean Value) {

	}

	public boolean isSaveDemandInterval() {
		return SaveDemandInterval;
	}

	private void createMeterTotals() {

	}

	private void createFDI_Totals() {

	}

	private void clearDI_Totals() {

	}

	private void writeTotalsFile() {

	}

	private void openOverloadReportFile() {

	}

	private void openVoltageReportFile() {

	}

	private void writeOverloadReport() {

	}

	private void writeVoltageReport() {

	}

	private void interpretRegisterMaskArray(double[] Mask) {

	}

	public void setDIVerbose(boolean Value) {

	}

	public boolean isDIVerbose() {
		return DI_Verbose;
	}

	protected void defineProperties() {

	}

	protected int makeLike(String EnergyMeterName) {
		return 0;
	}

	protected void setHasMeterFlag() {

	}

	public double[] getDI_RegisterTotals() {
		return DI_RegisterTotals;
	}

	public void setDI_RegisterTotals(double[] dI_RegisterTotals) {
		DI_RegisterTotals = dI_RegisterTotals;
	}

	public String getDI_Dir() {
		return DI_Dir;
	}

	public void setDI_Dir(String dI_Dir) {
		DI_Dir = dI_Dir;
	}

	public File getDI_Totals() {
		return DI_Totals;
	}

	public void setDI_Totals(File dI_Totals) {
		DI_Totals = dI_Totals;
	}

	public File getMeterTotals() {
		return MeterTotals;
	}

	public void setMeterTotals(File meterTotals) {
		MeterTotals = meterTotals;
	}

	public SystemMeter getSystemMeter() {
		return SystemMeter;
	}

	public void setSystemMeter(SystemMeter systemMeter) {
		SystemMeter = systemMeter;
	}

	public boolean isDo_OverloadReport() {
		return Do_OverloadReport;
	}

	public void setDo_OverloadReport(boolean do_OverloadReport) {
		Do_OverloadReport = do_OverloadReport;
	}

	public boolean isDo_VoltageExceptionReport() {
		return Do_VoltageExceptionReport;
	}

	public void setDo_VoltageExceptionReport(boolean do_VoltageExceptionReport) {
		Do_VoltageExceptionReport = do_VoltageExceptionReport;
	}

	public boolean isOverLoadFileIsOpen() {
		return OverLoadFileIsOpen;
	}

	public void setOverLoadFileIsOpen(boolean overLoadFileIsOpen) {
		OverLoadFileIsOpen = overLoadFileIsOpen;
	}

	public boolean isVoltageFileIsOpen() {
		return VoltageFileIsOpen;
	}

	public void setVoltageFileIsOpen(boolean voltageFileIsOpen) {
		VoltageFileIsOpen = voltageFileIsOpen;
	}

	public int edit() {
		return 0;
	}

	public int init(int Handle) {
		return 0;
	}

	public int newObject(String ObjName) {
		return 0;
	}

	public void resetMeterZonesAll() {

	}

	/* Reset all meters in active circuit to zero */
	@Override
	public void resetAll() {

	}

	/* Force all meters in active circuit to sample */
	@Override
	public void sampleAll() {

	}

	@Override
	public void saveAll() {

	}

	public void appendAllDIFiles() {

	}

	public void openAllDIFiles() {

	}

	public void closeAllDIFiles() {

	}

}
