package com.epri.dss.control.impl;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.control.VVControlObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.PointerList;
import com.epri.dss.shared.impl.ComplexUtil;

public class VVControlObjImpl extends ControlElemImpl implements VVControlObj {

	private double Fvvc_Vmaxpu, Fvvc_Vminpu, Fkva_rating, FkW_rating, Fkvar_fulloutput, Fpf,
		Fdelay, Fdelayoff, FkW_ramp_rate, Fkvar_ramp_rate, FkW_limit,
		// kw limit at the monitored element
		Fkvar_limit, // kvar limit at the monitored element
		DeltaVTolerance, // tolerance of voltage change from one solution to the
		// next for the voltage at the monitored element - in pu
		TotalWeight,
		QOldDeliver,
		Qdeliver,
		QNew,
		VavgpuPrior,
		Vavgpu,
		presentHour;
	private int ControlActionHandle;
	private int FListSize;
	private List<String> FGeneratorNameList;
	private PointerList FGenPointerList;
	private double[] FWeights;
	private int Fvvc_curve_size;
	private XYCurveObj Fvvc_curve;
	private int FPendingChange;
	private double FdeltaQ_factor;

	private CktElement MonitoredElement;

	private Complex[] cBuffer;
	private int CondOffset;  // offset for monitored terminal

	public VVControlObjImpl(DSSClass parClass, final String VVCControlName) {
		super(parClass);
		// TODO Auto-generated constructor stub
	}

	public void setPendingChange(final int Value) {

	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {

	}

	@Override
	public void recalcElementData() {

	}

	/**
	 * Always zero for a VVCControl
	 */
	@Override
	public void calcYPrim() {

	}

	/**
	 * Sample control quantities and set action times in control queue
	 */
	@Override
	public void sample() {

	}

	/**
	 * Do the action that is pending from last sample
	 */
	@Override
	public void doPendingAction(final int Code, int ProxyHdl) {

	}

	/**
	 * Reset to initial defined state
	 */
	@Override
	public void reset() {

	}

	/**
	 * Get present value of terminal curr
	 */
	@Override
	public void getCurrents(Complex[] Curr) {

	}

	/**
	 * Returns injextion currents
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {

	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	@Override
	public String getPropertyValue(int Index) {

	}

	public boolean makeGenList() {

	}

	public String returnGensList() {

	}

	public String returnWeightsList() {

	}

	public String returnVVCurve() {

	}

}
