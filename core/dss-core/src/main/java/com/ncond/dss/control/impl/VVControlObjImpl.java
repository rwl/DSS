package com.ncond.dss.control.impl;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.control.VVControl;
import com.ncond.dss.control.VVControlObj;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.general.XYCurveObj;
import com.ncond.dss.shared.PointerList;
import com.ncond.dss.shared.impl.PointerListImpl;

public class VVControlObjImpl extends ControlElemImpl implements VVControlObj {

	private static final int NONE = 0;
	private static final int CHANGEVARLEVEL = 1;

	private double vvc_VMaxPU, vvc_VMinPU, kVA_Rating, kW_Rating, kVAr_FullOutput, pf,
		delay, delayOff, kW_RampRate, kVAr_RampRate, kW_Limit,
		// kW limit at the monitored element
		kVAr_Limit, // kVAr limit at the monitored element
		deltaVTolerance, // tolerance of voltage change from one solution to the
		// next for the voltage at the monitored element - in pu
		totalWeight,
		QOldDeliver,
		QDeliver,
		QNew,
		VAvgPuPrior,
		VAvgPu,
		presentHour;
	private int controlActionHandle;
	private int listSize;
	private List<String> generatorNameList;
	private PointerList genPointerList;
	private double[] weights;
	private int vvc_CurveSize;
	private XYCurveObj vvc_Curve;
	private int pendingChange;
	private double deltaQFactor;

	private CktElement monitoredElement;

	private Complex[] cBuffer;
	private int condOffset;  // offset for monitored terminal

	public VVControlObjImpl(DSSClass parClass, final String VVCControlName) {
		super(parClass);

		setName(VVCControlName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNumPhases(1);  // directly set conds and phases
		ncond = 3;
		setNumTerms(1);  // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);
		elementTerminal = 0;
		monitoredElement = null;
		generatorNameList = new ArrayList<String>();
		weights = null;
		genPointerList = new PointerListImpl(20);
		// default size and increment
		listSize = 0;
		vvc_VMaxPU = 1.1;
		vvc_VMinPU = 0.9;
		kVA_Rating = 7.0;
		kW_Rating = 5.83;
		kVAr_FullOutput = 3.86;
		pf = 0.83;
		delay = 0.0;
		delayOff = 0.0;
		kW_RampRate = -1.0;
		kVAr_RampRate = -1.0;
		kW_Limit = 10000;
		kVAr_Limit = kW_Limit / 2.0;
		deltaQFactor = 0.1;
		deltaVTolerance = 0.00001; // in per-unit
		QDeliver = 1.0;
		QOldDeliver = 0.0;
		QNew = 0.0;
		VAvgPuPrior = 0.0;
		VAvgPu = 0.0;
		presentHour = -1.0;
		totalWeight = 1.0;
		initPropertyValues(0);

		vvc_Curve = null;
		vvc_CurveSize = 0;
		pendingChange = NONE;
	}

	@Override
	public void recalcElementData() {
		int devIndex;

		/* Check for existence of monitored element */

		devIndex = Util.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			monitoredElement = DSS.activeCircuit.getCktElements().get(devIndex);
			if (elementTerminal > monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("VVCControl: \"" + getName() + "\"",
						"Terminal no. \"" + String.format("%-d", elementTerminal)
						+ "\" does not exist.", "Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in VVCControl's buslist
				setBus(0, monitoredElement.getBus( elementTerminal ));
			}
			Util.resizeArray(cBuffer, monitoredElement.getYorder());
			condOffset = (elementTerminal - 1) * monitoredElement.getNumConds();
			// for speedy sampling
		} else {
			DSS.doSimpleMsg("Monitored Element in VVCControl." + getName() +
					" does not exist:\"" + elementName + "\"", 372);
		}

		if (genPointerList.size() == 0)
			makeGenList();

		devIndex = Util.getCktElementIndex("generator." + generatorNameList.get(0));

		if (devIndex >= 0) {
			// right now we only support one controlled element (generator) per vvcontrol
			// controlled element must already exist
			setControlledElement(DSS.activeCircuit.getCktElements().get( devIndex ));
			getControlledElement().setActiveTerminalIdx(0);  // make the 1 st terminal active
			// get control synched up with capacitor
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("VVControl: \"" + getName() + "\"",
					"Controlled Element \"" + generatorNameList.get(0) + "\" Not Found.",
					" Element must be defined previously.", 361);
		}
	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled( getControlledElement().isEnabled() );
			setNumPhases( getControlledElement().getNumPhases() );
			setNumConds( getNumPhases() );
		}

		if (monitoredElement != null) {
			setBus(0, monitoredElement.getBus( elementTerminal ));
			// allocate a buffer big enough to hold everything from the monitored element
			//Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
			//CondOffset = (elementTerminal - 1) * MonitoredElement.getNConds();  // for speedy sampling
		}

		super.makePosSequence();
	}

	/**
	 * Always zero for a VVCControl
	 */
	@Override
	public void calcYPrim() {
		// Yprim is zeroed when created.  Leave it as is.
	}

	/**
	 * Get present value of terminal curr
	 */
	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < ncond; i++)
			curr[i] = Complex.ZERO;
	}

	/**
	 * Returns injextion currents
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < ncond; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) pw.println();

		pw.close();
	}

	/**
	 * Do the action that is pending from last sample
	 */
	@Override
	public void doPendingAction(final int code, int proxyHdl) {
		int i;
		double deltaQ, QHeadroom, QDesiredPU, QNeeded, PPresentGenOutput,
			QPresentGenOutput, QMonitoredElement, genKVAr;
		Complex SMonitoredElement, SPresentGenOutput;
		GeneratorObj gen;

		genKVAr = 0.0;
		// we need P and/or we need Q
		if (pendingChange == CHANGEVARLEVEL) {

			SMonitoredElement = monitoredElement.getPower( elementTerminal );  // S is in VA
			// PMonitoredElement = SMonitoredElement.getReal();
			QMonitoredElement = SMonitoredElement.getImaginary();

			// PNeeded = kW_limit * 1000 - PMonitoredElement;
			QNeeded = kVAr_Limit * 1000 - QMonitoredElement;
			// if the generator list is not defined, go make one
			if (genPointerList.size() == 0)
				makeGenList();

			getControlledElement().setActiveTerminalIdx(1);  // set active terminal of generator to terminal 1  TODO Check zero based indexing
			if (QNeeded != 0.0) {
				for (i = 0; i < listSize; i++) {

					SPresentGenOutput = getControlledElement().getPower(1);  // TODO Check zero based indexing
					// S is in VA; we want terminal 1 of the generator
					PPresentGenOutput = SPresentGenOutput.getReal();
					QPresentGenOutput = SPresentGenOutput.getImaginary();

					// Q desired pu is the desired output based on the avg pu voltage on the
					// monitored element
					QDesiredPU = vvc_Curve.getYValue(VAvgPu);  // Y value = var in pu

					// the var 'head-room' available on the inverter given its rating
					// and present kW output
					if (Math.abs(PPresentGenOutput) > kVA_Rating * 1000.0) {
						QHeadroom = 0.0;
					} else {
						QHeadroom = Math.sqrt( Math.pow(kVA_Rating * 1000.0, 2) - Math.pow(PPresentGenOutput, 2) );
					}
					QDeliver = Math.min(Math.abs(kVAr_FullOutput * 1000.0), Math.abs(QHeadroom));

					QDeliver = QDeliver * QDesiredPU;
					deltaQ = QDeliver - QOldDeliver;

					// only allow a small movement from old delivered (prior gen Q)
					// to the desired delivered Q
					QNew = QOldDeliver + deltaQ * deltaQFactor;

					if (QNew != QPresentGenOutput) {
						gen = (GeneratorObj) genPointerList.get(i);  // TODO implement generics
						genKVAr = Math.signum(QNew) * Math.min( Math.abs(kVAr_Limit * 1000.0), Math.abs(QNew) ) / 1000.0;
						if (genKVAr != gen.getKVArBase())
							gen.setPresentKVAr(genKVAr);
					}
					Util.appendToEventLog("VoltVarControl." + getName(),
							String.format("**Set var output level to**, kvar= %.5g", genKVAr));
				}  // end for loop  (number of generators under this control)
			}  // end if vars needed is not equal to zero

			QOldDeliver = QNew;
			VAvgPuPrior = VAvgPu;
			DSS.activeCircuit.getSolution().setLoadsNeedUpdating(true);
			// force recalc of power parms
			setPendingChange(NONE);
		} else {  // end if pendingChange = CHANGEVARLEVEL
			setPendingChange(NONE);
		}
	}

	/**
	 * Sample control quantities and set action times in control queue
	 */
	@Override
	public void sample() {
		int i;
		double baseKV, VAvg;

		// if list is not defined, go make one for all generators in circuit
		if (genPointerList.size() == 0)
			makeGenList();

		if (listSize > 0 && vvc_CurveSize > 0) {

			//if (presentHour != DSSGlobals.activeCircuit.getSolution().getDblHour()) {
			//	WriteDLLDebugFile(Self.Name+','+String.format("%-.5g',[ActiveCircuit.Solution.dblHour])+','+String.format("%-.5g',[QPresentGenOutput])+','+String.format("%-.5g',[Qdeliver])+','+String.format("%-.5g',[QNew])+','+String.format("%-.5g',[Gen.Presentkvar*1000.0])+','+String.format("%-.5g',[QoldDeliver])+','+String.format("%-.5g',[PPresentGenOutput])+','+String.format("%-.5g',[Vavgpu])+','+String.format("%-.5g',[Vavgpuprior]));
			//	presentHour = ActiveCircuit.Solution.dblHour;
			//}

			monitoredElement.computeVTerminal();
			cBuffer = monitoredElement.getVTerminal();

			// get the basekV for the monitored bus
			baseKV = DSS.activeCircuit.getBus( terminals[elementTerminal].getBusRef() ).getKVBase();
			VAvg = 0;

			// calculate the average voltage
			for (i = 0; i < monitoredElement.getNumPhases(); i++)
				VAvg = VAvg + cBuffer[i].abs();

			// and convert to pu
			VAvgPu = (VAvg / monitoredElement.getNumPhases()) / (baseKV * 1000.0);

			timeDelay = delay;
			// and
			// if (ActiveCircuit.Solution.ControlIteration < ActiveCircuit.Solution.MaxControlIterations) then
			// begin
			if ((Math.abs(VAvgPu - VAvgPuPrior) > deltaVTolerance) || (Math.abs(Math.abs(QDeliver) - Math.abs(QNew)) > 0.5)) {
				setPendingChange(CHANGEVARLEVEL);
				// ActiveCircuit.Solution.LoadsNeedUpdating = TRUE; // Force recalc of power parms
				controlActionHandle = DSS.activeCircuit.getControlQueue().push(DSS.activeCircuit.getSolution().getIntHour(),
						DSS.activeCircuit.getSolution().getDynaVars().t + timeDelay, pendingChange, 0, this);
				Util.appendToEventLog("VoltVarControl." + getName(), String.format
						("**Ready to change var output**, Vavgpu= %.5g sec,", VAvgPu));
			} else {
				DSS.activeCircuit.getControlQueue().delete( controlActionHandle );
				Util.appendToEventLog("VoltVarControl." + getName(), "**DONE**");
			}
		} else {
			DSS.doSimpleMsg("Could not find any generators, or the vvc curve size is zero.  Please correct in your script.", 1234);
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "");     // "element";
		setPropertyValue(1, "1");    // "terminal";
		setPropertyValue(2, "1.1");  // vmax_pu of the inverter
		setPropertyValue(3, "0.9");  // vmin_pu of the inverter
		setPropertyValue(4, "7.0");
		setPropertyValue(5, "5.83");
		setPropertyValue(6, "3.5");
		setPropertyValue(7, "0.83");
		setPropertyValue(8, "0.0");
		setPropertyValue(9, "0.0");
		setPropertyValue(10, "-1.0");
		setPropertyValue(11, "-1.0");
		setPropertyValue(12, "1000.0");  // kw_limit through the monitored element
		setPropertyValue(13, "500.0");   // kvar_limit through the monitored element
		setPropertyValue(14, "");
		setPropertyValue(15, "");
		setPropertyValue(16, "0");     // curve size
		setPropertyValue(17, "none");  // volt-var curve
		setPropertyValue(18, "0.1");   // deltaQ_factor

		super.initPropertyValues(VVControl.NumPropsThisClass - 1);
	}

	@Override
	public String getPropertyValue(int index) {

		switch (index) {
		case 0:
			return monitoredElement.getDisplayName();
		case 1:
			return String.format("%-d", elementTerminal);
		case 2:
			return String.format("%-.3g", vvc_VMaxPU);
		case 3:
			return String.format("%-.3g", vvc_VMinPU);
		case 4:
			return String.format("%-.3g", kVA_Rating);
		case 5:
			return String.format("%-.3g", kW_Rating);
		case 6:
			return String.format("%-.3g", kVAr_FullOutput);
		case 7:
			return String.format("%-.3g", pf);
		case 8:
			return String.format("%-.3g", delay);
		case 9:
			return String.format("%-.3g", delayOff);
		case 10:
			return String.format("%-.3g", kW_RampRate);
		case 11:
			return String.format("%-.3g", kVAr_RampRate);
		case 12:
			return String.format("%-.3g", kW_Limit);
		case 13:
			return String.format("%-.3g", kVAr_Limit);
		case 14:
			return returnGensList();
		case 15:
			return returnWeightsList();
		case 16:
			return String.format("%-d", vvc_CurveSize);
		case 17:
			return returnVVCurve();
		case 18:
			return String.format("%-.3g", deltaQFactor);
		default:
			// take the generic handler
			return super.getPropertyValue(index);
		}
	}

	public boolean makeGenList() {
		DSSClass genClass;
		GeneratorObj gen;
		int i;

		boolean result = false;
		genClass = DSSClassDefs.getDSSClass("generator");

		if (listSize > 0) {
			// name list is defined - use it
			for (i = 0; i < listSize; i++) {
				gen = (GeneratorObj) genClass.find( generatorNameList.get(i - 1) );
				if ((gen != null) && gen.isEnabled())
					genPointerList.add(gen);
			}
		} else {
			/* Search through the entire circuit for enabled generators and add them to the list */
			for (i = 0; i < genClass.getElementCount(); i++) {
				gen = (GeneratorObj) genClass.getElementList().get(i);
				if (gen.isEnabled())
					genPointerList.add(gen);
				generatorNameList.add(gen.getDisplayName());
			}

			/* Allocate uniform weights */
			listSize = genPointerList.size();
			Util.resizeArray(weights, listSize);
			for (i = 0; i < listSize; i++)
				weights[i] = 1.0;
		}

		// add up total weights
		totalWeight = 0.0;
		for (i = 0; i < listSize; i++)
			totalWeight = totalWeight + weights[i];

		if (genPointerList.size() > 0)
			result = true;

		return result;
	}

	public String returnGensList() {
		String result;

		if (listSize == 0) {
			result = "";
			return result;
		}

		result = "[" + generatorNameList.get(0);
		for (int i = 0; i < listSize; i++)
			result = result + ", " + generatorNameList.get(i);

		result = result + "]";  // terminate the array

		return result;
	}

	public String returnWeightsList() {
		String result;

		if (listSize == 0) {
			result = "";
			return result;
		}

		result = "[" + String.format("%-.6g", weights[0]);
		for (int i = 1; i < listSize; i++)
			result = result + String.format(", %-.6g", weights[i]);

		result = result + "]";  // terminate the array

		return result;
	}

	public String returnVVCurve() {
		String result;

		if (vvc_CurveSize == 0) {
			result = "";
			return result;
		}

		result = "[{" + String.format("%-.3g,", vvc_Curve.getXValue(0)) + String.format("%-.3g", vvc_Curve.getYValue(0)) + "},";
		for (int i = 1; i < vvc_CurveSize; i++)
			result = result + String.format("{ %-.3g,", vvc_Curve.getXValue(i)) + String.format("%-.3g", vvc_Curve.getYValue(i)) + "},";
		result = result + "]";  // terminate the array

		return result;
	}

	/**
	 * Reset to initial defined state
	 */
	@Override
	public void reset() {
		pendingChange = NONE;
	}

	public void setPendingChange(final int Value) {
		pendingChange = Value;
		setDblTraceParameter(Value);
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	// FIXME Private members in OpenDSS

	public double getVvc_Vmaxpu() {
		return vvc_VMaxPU;
	}

	public void setVvc_Vmaxpu(double vvc_Vmaxpu) {
		this.vvc_VMaxPU = vvc_Vmaxpu;
	}

	public double getVvc_Vminpu() {
		return vvc_VMinPU;
	}

	public void setVvc_Vminpu(double vvc_Vminpu) {
		this.vvc_VMinPU = vvc_Vminpu;
	}

	public double getKva_rating() {
		return kVA_Rating;
	}

	public void setKva_rating(double kva_rating) {
		this.kVA_Rating = kva_rating;
	}

	public double getkW_rating() {
		return kW_Rating;
	}

	public void setkW_rating(double kW_rating) {
		this.kW_Rating = kW_rating;
	}

	public double getKvar_fulloutput() {
		return kVAr_FullOutput;
	}

	public void setKvar_fulloutput(double kvar_fulloutput) {
		this.kVAr_FullOutput = kvar_fulloutput;
	}

	public double getPf() {
		return pf;
	}

	public void setPf(double pf) {
		this.pf = pf;
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}

	public double getDelayoff() {
		return delayOff;
	}

	public void setDelayoff(double delayoff) {
		this.delayOff = delayoff;
	}

	public double getkW_ramp_rate() {
		return kW_RampRate;
	}

	public void setkW_ramp_rate(double kW_ramp_rate) {
		this.kW_RampRate = kW_ramp_rate;
	}

	public double getKvar_ramp_rate() {
		return kVAr_RampRate;
	}

	public void setKvar_ramp_rate(double kvar_ramp_rate) {
		this.kVAr_RampRate = kvar_ramp_rate;
	}

	public double getkW_limit() {
		return kW_Limit;
	}

	public void setkW_limit(double kW_limit) {
		this.kW_Limit = kW_limit;
	}

	public double getKvar_limit() {
		return kVAr_Limit;
	}

	public void setKvar_limit(double kvar_limit) {
		this.kVAr_Limit = kvar_limit;
	}

	public double getDeltaVTolerance() {
		return deltaVTolerance;
	}

	public void setDeltaVTolerance(double deltaVTolerance) {
		this.deltaVTolerance = deltaVTolerance;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public double getQOldDeliver() {
		return QOldDeliver;
	}

	public void setQOldDeliver(double qOldDeliver) {
		QOldDeliver = qOldDeliver;
	}

	public double getQdeliver() {
		return QDeliver;
	}

	public void setQdeliver(double qdeliver) {
		QDeliver = qdeliver;
	}

	public double getQNew() {
		return QNew;
	}

	public void setQNew(double qNew) {
		QNew = qNew;
	}

	public double getVavgPuPrior() {
		return VAvgPuPrior;
	}

	public void setVavgPuPrior(double vavgPuPrior) {
		VAvgPuPrior = vavgPuPrior;
	}

	public double getVavgPu() {
		return VAvgPu;
	}

	public void setVavgPu(double vavgPu) {
		VAvgPu = vavgPu;
	}

	public double getPresentHour() {
		return presentHour;
	}

	public void setPresentHour(double presentHour) {
		this.presentHour = presentHour;
	}

	public int getControlActionHandle() {
		return controlActionHandle;
	}

	public void setControlActionHandle(int controlActionHandle) {
		this.controlActionHandle = controlActionHandle;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public List<String> getGeneratorNameList() {
		return generatorNameList;
	}

	public void setGeneratorNameList(List<String> generatorNameList) {
		this.generatorNameList = generatorNameList;
	}

	public PointerList getGenPointerList() {
		return genPointerList;
	}

	public void setGenPointerList(PointerList genPointerList) {
		this.genPointerList = genPointerList;
	}

	public double[] getWeights() {
		return weights;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public int getVvc_curve_size() {
		return vvc_CurveSize;
	}

	public void setVvc_curve_size(int vvc_curve_size) {
		this.vvc_CurveSize = vvc_curve_size;
	}

	public XYCurveObj getVvc_curve() {
		return vvc_Curve;
	}

	public void setVvc_curve(XYCurveObj vvc_curve) {
		this.vvc_Curve = vvc_curve;
	}

	public double getDeltaQ_factor() {
		return deltaQFactor;
	}

	public void setDeltaQ_factor(double deltaQ_factor) {
		this.deltaQFactor = deltaQ_factor;
	}

	public int getPendingChange() {
		return pendingChange;
	}

	public CktElement getMonitoredElement() {
		return monitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		this.monitoredElement = monitoredElement;
	}

	public int getCondOffset() {
		return condOffset;
	}

	public void setCondOffset(int condOffset) {
		this.condOffset = condOffset;
	}

}
