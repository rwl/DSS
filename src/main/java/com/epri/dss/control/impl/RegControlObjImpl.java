package com.epri.dss.control.impl;

import java.io.File;
import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.RegControlObj;
import com.epri.dss.delivery.TransformerObj;

public class RegControlObjImpl extends ControlElemImpl implements RegControlObj {

	private double Vreg,
		Bandwidth,
		PTRatio,
		CTRating,
		R,
		X,
		revVreg,
		revBandwidth,
		revR,
		revX;
	private String RegulatedBus;
	private boolean IsReversible, LDCActive, UsingRegulatedBus;

	private double PendingTapChange;   // amount of tap change pending
	private double TapDelay;           // delay between taps

	private boolean DebugTrace;
	private boolean Armed;
	private File Tracefile;

	private int TapLimitPerChange;
	private int TapWinding;
	private boolean Inversetime;
	private double Vlimit;
	private boolean VLimitActive;

	private int PTphase;
	private int ControlledPhase;

	private Complex[] VBuffer, CBuffer;

	public RegControlObjImpl(DSSClassImpl ParClass, String RegControlName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	/* Controlled transformer. */
	public TransformerObj getTransformer() {
		return null;
	}

	/* Report tapped winding. */
	public int getWinding() {
		return 0;
	}

	// CIM accessors

	public double getMinTap() {
		return 0.0;
	}

	public double getMaxTap() {
		return 0.0;
	}

	public double getTapIncrement() {
		return 0.0;
	}

	public int getNumTaps() {
		return 0;
	}

	private void writeTraceRecord(double TapChangeMade) {

	}

	public void setPendingTapChange(double Value) {

	}

	public double getPendingTapChange() {
		return PendingTapChange;
	}

	private double atLeastOneTap(double ProposedChange, double Increment) {
		return 0.0;
	}

	private double computeTimeDelay(double Vavg) {
		return 0.0;
	}

	private Complex getControlVoltage(Complex[] VBuffer, int Nphs,
			double PTRatio) {
		return null;
	}

	public double getTargetVoltage() {
		return Vreg;
	}

	public double getBandwidth() {
		return Bandwidth;
	}

	public double getPTRatio() {
		return PTRatio;
	}

	public double getCTRating() {
		return CTRating;
	}

	public double getLineDropR() {
		return R;
	}

	public double getLineDropX() {
		return X;
	}

	public double getRevTargetVoltage() {
		return revVreg;
	}

	public double getRevBandwidth() {
		return revBandwidth;
	}

	public double getRevR() {
		return revR;
	}

	public double getRevX() {
		return revX;
	}

	public boolean useReverseDrop() {
		return IsReversible;
	}

	public boolean useLineDrop() {
		return LDCActive;
	}

	public double getTapDelay() {
		return TapDelay;
	}

	public int getMaxTapChange() {
		return TapLimitPerChange;
	}

	public boolean isInverseTime() {
		return Inversetime;
	}

	public double getVoltageLimit() {
		return Vlimit;
	}

	public boolean useLimit() {
		return VLimitActive;
	}

	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {

	}

	@Override
	public void recalcElementData() {

	}

	@Override
	public void calcYPrim() {

	}

	/* Sample control quantities and set action times in Control Queue */
	@Override
	public void sample() {

	}

	/* Do the action that is pending from last sample */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {

	}

	/* Reset to initial defined state */
	@Override
	public void reset() {

	}

	@Override
	public void getInjCurrents(Complex[] Curr) {

	}

	@Override
	public void getCurrents(Complex[] Curr) {

	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	// FIXME Private members in OpenDSS

	public double getVreg() {
		return Vreg;
	}

	public void setVreg(double vreg) {
		Vreg = vreg;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getRevVreg() {
		return revVreg;
	}

	public void setRevVreg(double revVreg) {
		this.revVreg = revVreg;
	}

	public String getRegulatedBus() {
		return RegulatedBus;
	}

	public void setRegulatedBus(String regulatedBus) {
		RegulatedBus = regulatedBus;
	}

	public boolean isIsReversible() {
		return IsReversible;
	}

	public void setIsReversible(boolean isReversible) {
		IsReversible = isReversible;
	}

	public boolean isLDCActive() {
		return LDCActive;
	}

	public void setLDCActive(boolean lDCActive) {
		LDCActive = lDCActive;
	}

	public boolean isUsingRegulatedBus() {
		return UsingRegulatedBus;
	}

	public void setUsingRegulatedBus(boolean usingRegulatedBus) {
		UsingRegulatedBus = usingRegulatedBus;
	}

	public boolean isDebugTrace() {
		return DebugTrace;
	}

	public void setDebugTrace(boolean debugTrace) {
		DebugTrace = debugTrace;
	}

	public boolean isArmed() {
		return Armed;
	}

	public void setArmed(boolean armed) {
		Armed = armed;
	}

	public File getTracefile() {
		return Tracefile;
	}

	public void setTracefile(File tracefile) {
		Tracefile = tracefile;
	}

	public int getTapLimitPerChange() {
		return TapLimitPerChange;
	}

	public void setTapLimitPerChange(int tapLimitPerChange) {
		TapLimitPerChange = tapLimitPerChange;
	}

	public int getTapWinding() {
		return TapWinding;
	}

	public void setTapWinding(int tapWinding) {
		TapWinding = tapWinding;
	}

	public boolean isInversetime() {
		return Inversetime;
	}

	public void setInversetime(boolean inversetime) {
		Inversetime = inversetime;
	}

	public double getVlimit() {
		return Vlimit;
	}

	public void setVlimit(double vlimit) {
		Vlimit = vlimit;
	}

	public boolean isVLimitActive() {
		return VLimitActive;
	}

	public void setVLimitActive(boolean vLimitActive) {
		VLimitActive = vLimitActive;
	}

	public int getPTphase() {
		return PTphase;
	}

	public void setPTphase(int pTphase) {
		PTphase = pTphase;
	}

	public int getControlledPhase() {
		return ControlledPhase;
	}

	public void setControlledPhase(int controlledPhase) {
		ControlledPhase = controlledPhase;
	}

	public Complex[] getVBuffer() {
		return VBuffer;
	}

	public void setVBuffer(Complex[] vBuffer) {
		VBuffer = vBuffer;
	}

	public Complex[] getCBuffer() {
		return CBuffer;
	}

	public void setCBuffer(Complex[] cBuffer) {
		CBuffer = cBuffer;
	}

	public void setBandwidth(double bandwidth) {
		Bandwidth = bandwidth;
	}

	public void setPTRatio(double pTRatio) {
		PTRatio = pTRatio;
	}

	public void setCTRating(double cTRating) {
		CTRating = cTRating;
	}

	public void setRevBandwidth(double revBandwidth) {
		this.revBandwidth = revBandwidth;
	}

	public void setRevR(double revR) {
		this.revR = revR;
	}

	public void setRevX(double revX) {
		this.revX = revX;
	}

	public void setTapDelay(double tapDelay) {
		TapDelay = tapDelay;
	}

}
