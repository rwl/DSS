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

}
