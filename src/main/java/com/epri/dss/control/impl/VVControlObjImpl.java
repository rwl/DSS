package com.epri.dss.control.impl;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.VVControl;
import com.epri.dss.control.VVControlObj;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.PointerList;
import com.epri.dss.shared.impl.ComplexUtil;
import com.epri.dss.shared.impl.PointerListImpl;

public class VVControlObjImpl extends ControlElemImpl implements VVControlObj {

	private static final int NONE = 0;
	private static final int CHANGEVARLEVEL = 1;

	private double vvc_Vmaxpu, vvc_Vminpu, kva_rating, kW_rating, kvar_fulloutput, pf,
		delay, delayoff, kW_ramp_rate, kvar_ramp_rate, kW_limit,
		// kw limit at the monitored element
		kvar_limit, // kvar limit at the monitored element
		DeltaVTolerance, // tolerance of voltage change from one solution to the
		// next for the voltage at the monitored element - in pu
		TotalWeight,
		QOldDeliver,
		Qdeliver,
		QNew,
		VavgPuPrior,
		VavgPu,
		presentHour;
	private int ControlActionHandle;
	private int ListSize;
	private List<String> GeneratorNameList;
	private PointerList GenPointerList;
	private double[] Weights;
	private int vvc_curve_size;
	private XYCurveObj vvc_curve;
	private int PendingChange;
	private double deltaQ_factor;

	private CktElement MonitoredElement;

	private Complex[] cBuffer;
	private int CondOffset;  // offset for monitored terminal

	public VVControlObjImpl(DSSClass parClass, final String VVCControlName) {
		super(parClass);

		setName(VVCControlName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(1);  // directly set conds and phases
		nConds = 3;
		setNTerms(1);  // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);
		elementTerminal = 1;
		MonitoredElement = null;
		GeneratorNameList = new ArrayList<String>();
		Weights = null;
		GenPointerList = new PointerListImpl(20);
		// default size and increment
		ListSize = 0;
		vvc_Vmaxpu = 1.1;
		vvc_Vminpu = 0.9;
		kva_rating = 7.0;
		kW_rating = 5.83;
		kvar_fulloutput = 3.86;
		pf = 0.83;
		delay = 0.0;
		delayoff = 0.0;
		kW_ramp_rate = -1.0;
		kvar_ramp_rate = -1.0;
		kW_limit = 10000;
		kvar_limit = kW_limit / 2.0;
		deltaQ_factor = 0.1;
		DeltaVTolerance = 0.00001; // in per-unit
		Qdeliver = 1.0;
		QOldDeliver = 0.0;
		QNew = 0.0;
		VavgPuPrior = 0.0;
		VavgPu = 0.0;
		presentHour = -1.0;
		TotalWeight = 1.0;
		initPropertyValues(0);

		vvc_curve = null;
		vvc_curve_size = 0;
		PendingChange = NONE;
	}

	@Override
	public void recalcElementData() {
		int DevIndex;
		String tempstring;

		/* Check for existence of monitored element */

		DevIndex = Utilities.getCktElementIndex(elementName);
		if (DevIndex >= 0) {
			MonitoredElement = DSSGlobals.activeCircuit.getCktElements().get(DevIndex);
			if (elementTerminal > MonitoredElement.getNTerms()) {
				DSSGlobals.doErrorMsg("VVCControl: \"" + getName() + "\"",
						"Terminal no. \"" + String.format("%-d", elementTerminal)
						+ "\" does not exist.", "Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in VVCControl's buslist
				setBus(0, MonitoredElement.getBus( elementTerminal ));
			}
			Utilities.resizeArray(cBuffer, MonitoredElement.getYorder());
			CondOffset = (elementTerminal - 1) * MonitoredElement.getNConds();
			// for speedy sampling
		} else {
			DSSGlobals.doSimpleMsg("Monitored Element in VVCControl." + getName() +
					" does not exist:\"" + elementName + "\"", 372);
		}

		if (GenPointerList.size() == 0)
			makeGenList();

		DevIndex = Utilities.getCktElementIndex("generator." + GeneratorNameList.get(0));

		if (DevIndex >= 0) {
			// right now we only support one controlled element (generator) per vvcontrol
			// controlled element must already exist
			setControlledElement(DSSGlobals.activeCircuit.getCktElements().get( DevIndex ));
			getControlledElement().setActiveTerminalIdx(1);  // make the 1 st terminal active
			// get control synched up with capacitor
		} else {
			setControlledElement(null);  // element not found
			DSSGlobals.doErrorMsg("VVControl: \"" + getName() + "\"",
					"Controlled Element \"" + GeneratorNameList.get(0) + "\" Not Found.",
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
			setNPhases( getControlledElement().getNPhases() );
			setNConds( getNPhases() );
		}

		if (MonitoredElement != null) {
			setBus(0, MonitoredElement.getBus( elementTerminal ));
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
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	/**
	 * Returns injextion currents
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete)
			F.println();
	}

	/**
	 * Do the action that is pending from last sample
	 */
	@Override
	public void doPendingAction(final int Code, int ProxyHdl) {
		int i;
		double DeltaQ, Qheadroom, Qdesiredpu, PNeeded, QNeeded, PPresentGenOutput,
			QPresentGenOutput, PMonitoredElement, QMonitoredElement, Genkvar;
		Complex SMonitoredElement, SPresentGenOutput;
		boolean GenkvarChanged;
		GeneratorObj Gen;

		Genkvar = 0.0;
		// we need P and/or we need Q
		if (PendingChange == CHANGEVARLEVEL) {

			SMonitoredElement = MonitoredElement.getPower( ElementTerminal );  // S is in VA
			// PMonitoredElement = SMonitoredElement.getReal();
			QMonitoredElement = SMonitoredElement.getImaginary();

			// PNeeded = kW_limit * 1000 - PMonitoredElement;
			QNeeded = kvar_limit * 1000 - QMonitoredElement;
			// if the generator list is not defined, go make one
			if (GenPointerList.size() == 0)
				makeGenList();

			getControlledElement().setActiveTerminalIdx(1);  // set active terminal of generator to terminal 1  TODO Check zero based indexing
			if (QNeeded != 0.0) {
				for (i = 0; i < ListSize; i++) {

					SPresentGenOutput = getControlledElement().getPower(1);  // TODO Check zero based indexing
					// S is in VA; we want terminal 1 of the generator
					PPresentGenOutput = SPresentGenOutput.getReal();
					QPresentGenOutput = SPresentGenOutput.getImaginary();

					// Q desired pu is the desired output based on the avg pu voltage on the
					// monitored element
					Qdesiredpu = vvc_curve.getYValue(VavgPu);  // Y value = var in pu

					// the var 'head-room' available on the inverter given its rating
					// and present kW output
					if (Math.abs(PPresentGenOutput) > kva_rating * 1000.0) {
						Qheadroom = 0.0;
					} else {
						Qheadroom = Math.sqrt( Math.pow(kva_rating * 1000.0, 2) - Math.pow(PPresentGenOutput, 2) );
					}
					Qdeliver = Math.min(Math.abs(kvar_fulloutput * 1000.0), Math.abs(Qheadroom));

					Qdeliver = Qdeliver * Qdesiredpu;
					DeltaQ = Qdeliver - QOldDeliver;

					// only allow a small movement from old delivered (prior gen Q)
					// to the desired delivered Q
					QNew = QOldDeliver + DeltaQ * deltaQ_factor;

					if (QNew != QPresentGenOutput) {
						Gen = (GeneratorObj) GenPointerList.get(i);  // TODO implement generics
						Genkvar = Math.signum(QNew) * Math.min( Math.abs(kvar_limit * 1000.0), Math.abs(QNew) ) / 1000.0;
						if (Genkvar != Gen.getKVArBase())
							Gen.setPresentKVAr(Genkvar);
					}
					Utilities.appendToEventLog("VoltVarControl." + getName(),
							String.format("**Set var output level to**, kvar= %.5g", Genkvar));
				}  // end for loop  (number of generators under this control)
			}  // end if vars needed is not equal to zero

			QOldDeliver = QNew;
			VavgPuPrior = VavgPu;
			DSSGlobals.activeCircuit.getSolution().setLoadsNeedUpdating(true);
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
		Complex SMonitoredElement;
		double basekV, Vavg, PNeeded, QNeeded, PMonitoredElement, QMonitoredElement;

		// if list is not defined, go make one for all generators in circuit
		if (GenPointerList.size() == 0)
			makeGenList();

		if ((ListSize > 0) && (vvc_curve_size > 0)) {

			//if (presentHour != DSSGlobals.activeCircuit.getSolution().getDblHour()) {
			//	WriteDLLDebugFile(Self.Name+','+String.format("%-.5g',[ActiveCircuit.Solution.dblHour])+','+String.format("%-.5g',[QPresentGenOutput])+','+String.format("%-.5g',[Qdeliver])+','+String.format("%-.5g',[QNew])+','+String.format("%-.5g',[Gen.Presentkvar*1000.0])+','+String.format("%-.5g',[QoldDeliver])+','+String.format("%-.5g',[PPresentGenOutput])+','+String.format("%-.5g',[Vavgpu])+','+String.format("%-.5g',[Vavgpuprior]));
			//	presentHour = ActiveCircuit.Solution.dblHour;
			//}

			MonitoredElement.computeVTerminal();
			cBuffer = MonitoredElement.getVTerminal();

			// get the basekV for the monitored bus
			basekV = DSSGlobals.activeCircuit.getBuses()[ terminals[elementTerminal].getBusRef() ].getKVBase();
			Vavg = 0;

			// calculate the average voltage
			for (i = 0; i < MonitoredElement.getNPhases(); i++)
				Vavg = Vavg + cBuffer[i].abs();

			// and convert to pu
			VavgPu = (Vavg / MonitoredElement.getNPhases()) / (basekV * 1000.0);

			timeDelay = delay;
			// and
			// if (ActiveCircuit.Solution.ControlIteration < ActiveCircuit.Solution.MaxControlIterations) then
			// begin
			if ((Math.abs(VavgPu - VavgPuPrior) > DeltaVTolerance) || (Math.abs(Math.abs(Qdeliver) - Math.abs(QNew)) > 0.5)) {
				setPendingChange(CHANGEVARLEVEL);
				// ActiveCircuit.Solution.LoadsNeedUpdating = TRUE; // Force recalc of power parms
				ControlActionHandle = DSSGlobals.activeCircuit.getControlQueue().push(DSSGlobals.activeCircuit.getSolution().getIntHour(),
						DSSGlobals.activeCircuit.getSolution().getDynaVars().t + timeDelay, PendingChange, 0, this);
				Utilities.appendToEventLog("VoltVarControl." + getName(), String.format
						("**Ready to change var output**, Vavgpu= %.5g sec,", VavgPu));
			} else {
				DSSGlobals.activeCircuit.getControlQueue().delete( ControlActionHandle );
				Utilities.appendToEventLog("VoltVarControl." + getName(), "**DONE**");
			}
		} else {
			DSSGlobals.doSimpleMsg("Could not find any generators, or the vvc curve size is zero.  Please correct in your script.", 1234);
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

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

		super.initPropertyValues(VVControl.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int index) {

		switch (index) {
		case 0:
			return MonitoredElement.getDisplayName();
		case 1:
			return String.format("%-d", elementTerminal);
		case 2:
			return String.format("%-.3g", vvc_Vmaxpu);
		case 3:
			return String.format("%-.3g", vvc_Vminpu);
		case 4:
			return String.format("%-.3g", kva_rating);
		case 5:
			return String.format("%-.3g", kW_rating);
		case 6:
			return String.format("%-.3g", kvar_fulloutput);
		case 7:
			return String.format("%-.3g", pf);
		case 8:
			return String.format("%-.3g", delay);
		case 9:
			return String.format("%-.3g", delayoff);
		case 10:
			return String.format("%-.3g", kW_ramp_rate);
		case 11:
			return String.format("%-.3g", kvar_ramp_rate);
		case 12:
			return String.format("%-.3g", kW_limit);
		case 13:
			return String.format("%-.3g", kvar_limit);
		case 14:
			return returnGensList();
		case 15:
			return returnWeightsList();
		case 16:
			return String.format("%-d", vvc_curve_size);
		case 17:
			return returnVVCurve();
		case 18:
			return String.format("%-.3g", deltaQ_factor);
		default:
			// take the generic handler
			return super.getPropertyValue(index);
		}
	}

	public void setPendingChange(final int Value) {

	}

	/**
	 * Reset to initial defined state
	 */
	@Override
	public void reset() {

	}

	public boolean makeGenList() {

	}

	public String returnGensList() {

	}

	public String returnWeightsList() {

	}

	public String returnVVCurve() {

	}

	// FIXME Private members in OpenDSS

	public double getVvc_Vmaxpu() {
		return vvc_Vmaxpu;
	}

	public void setVvc_Vmaxpu(double vvc_Vmaxpu) {
		this.vvc_Vmaxpu = vvc_Vmaxpu;
	}

	public double getVvc_Vminpu() {
		return vvc_Vminpu;
	}

	public void setVvc_Vminpu(double vvc_Vminpu) {
		this.vvc_Vminpu = vvc_Vminpu;
	}

	public double getKva_rating() {
		return kva_rating;
	}

	public void setKva_rating(double kva_rating) {
		this.kva_rating = kva_rating;
	}

	public double getkW_rating() {
		return kW_rating;
	}

	public void setkW_rating(double kW_rating) {
		this.kW_rating = kW_rating;
	}

	public double getKvar_fulloutput() {
		return kvar_fulloutput;
	}

	public void setKvar_fulloutput(double kvar_fulloutput) {
		this.kvar_fulloutput = kvar_fulloutput;
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
		return delayoff;
	}

	public void setDelayoff(double delayoff) {
		this.delayoff = delayoff;
	}

	public double getkW_ramp_rate() {
		return kW_ramp_rate;
	}

	public void setkW_ramp_rate(double kW_ramp_rate) {
		this.kW_ramp_rate = kW_ramp_rate;
	}

	public double getKvar_ramp_rate() {
		return kvar_ramp_rate;
	}

	public void setKvar_ramp_rate(double kvar_ramp_rate) {
		this.kvar_ramp_rate = kvar_ramp_rate;
	}

	public double getkW_limit() {
		return kW_limit;
	}

	public void setkW_limit(double kW_limit) {
		this.kW_limit = kW_limit;
	}

	public double getKvar_limit() {
		return kvar_limit;
	}

	public void setKvar_limit(double kvar_limit) {
		this.kvar_limit = kvar_limit;
	}

	public double getDeltaVTolerance() {
		return DeltaVTolerance;
	}

	public void setDeltaVTolerance(double deltaVTolerance) {
		DeltaVTolerance = deltaVTolerance;
	}

	public double getTotalWeight() {
		return TotalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		TotalWeight = totalWeight;
	}

	public double getQOldDeliver() {
		return QOldDeliver;
	}

	public void setQOldDeliver(double qOldDeliver) {
		QOldDeliver = qOldDeliver;
	}

	public double getQdeliver() {
		return Qdeliver;
	}

	public void setQdeliver(double qdeliver) {
		Qdeliver = qdeliver;
	}

	public double getQNew() {
		return QNew;
	}

	public void setQNew(double qNew) {
		QNew = qNew;
	}

	public double getVavgPuPrior() {
		return VavgPuPrior;
	}

	public void setVavgPuPrior(double vavgPuPrior) {
		VavgPuPrior = vavgPuPrior;
	}

	public double getVavgPu() {
		return VavgPu;
	}

	public void setVavgPu(double vavgPu) {
		VavgPu = vavgPu;
	}

	public double getPresentHour() {
		return presentHour;
	}

	public void setPresentHour(double presentHour) {
		this.presentHour = presentHour;
	}

	public int getControlActionHandle() {
		return ControlActionHandle;
	}

	public void setControlActionHandle(int controlActionHandle) {
		ControlActionHandle = controlActionHandle;
	}

	public int getListSize() {
		return ListSize;
	}

	public void setListSize(int listSize) {
		ListSize = listSize;
	}

	public List<String> getGeneratorNameList() {
		return GeneratorNameList;
	}

	public void setGeneratorNameList(List<String> generatorNameList) {
		GeneratorNameList = generatorNameList;
	}

	public PointerList getGenPointerList() {
		return GenPointerList;
	}

	public void setGenPointerList(PointerList genPointerList) {
		GenPointerList = genPointerList;
	}

	public double[] getWeights() {
		return Weights;
	}

	public void setWeights(double[] weights) {
		Weights = weights;
	}

	public int getVvc_curve_size() {
		return vvc_curve_size;
	}

	public void setVvc_curve_size(int vvc_curve_size) {
		this.vvc_curve_size = vvc_curve_size;
	}

	public XYCurveObj getVvc_curve() {
		return vvc_curve;
	}

	public void setVvc_curve(XYCurveObj vvc_curve) {
		this.vvc_curve = vvc_curve;
	}

	public double getDeltaQ_factor() {
		return deltaQ_factor;
	}

	public void setDeltaQ_factor(double deltaQ_factor) {
		this.deltaQ_factor = deltaQ_factor;
	}

	public int getPendingChange() {
		return PendingChange;
	}

	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

	public int getCondOffset() {
		return CondOffset;
	}

	public void setCondOffset(int condOffset) {
		CondOffset = condOffset;
	}

}
