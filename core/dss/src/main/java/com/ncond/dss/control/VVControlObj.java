package com.ncond.dss.control;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.general.XYCurveObj;
import com.ncond.dss.shared.PointerList;

@Getter @Setter
public class VVControlObj extends ControlElem {

	private static final int NONE = 0;
	private static final int CHANGEVARLEVEL = 1;

	private double vvc_VMaxPU, vvc_VMinPU;
	private double kVA_Rating, kW_Rating, kVAr_FullOutput;
	private double pf, delay, delayOff;
	private double kW_RampRate, kVAr_RampRate;
	private double kW_Limit;  // kW limit at the monitored element
	private double kVAr_Limit;  // kVAr limit at the monitored element
	/* tolerance of voltage change from one solution to the next
	 * for the voltage at the monitored element - in pu */
	private double deltaVTolerance;
	private double totalWeight;
	private double QOldDeliver, QDeliver, QNew;
	private double VAvgPuPrior, VAvgPu;
	private double presentHour;

	private int controlActionHandle;
	private int listSize;
	private List<String> generatorNames;
	private PointerList generators;
	private double[] weights;
	private int vvc_CurveSize;
	private XYCurveObj vvc_Curve;
	private int pendingChange;
	private double deltaQFactor;

	private CktElement monitoredElement;

	private Complex[] cBuffer;
	private int condOffset;  // offset for monitored terminal

	public VVControlObj(DSSClass parClass, final String vvc_ControlName) {
		super(parClass);

		setName(vvc_ControlName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(1);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);  // this forces allocation of terminals and conductors in base class

		elementName = "";
		setControlledElement(null);
		elementTerminalIdx = 0;
		monitoredElement = null;
		generatorNames = new ArrayList<String>();
		weights = null;
		generators = new PointerList(20);
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

			if (elementTerminalIdx >= monitoredElement.getNumTerms()) {
				DSS.doErrorMsg("VVCControl: \"" + getName() + "\"",
						"Terminal no. \"" + (elementTerminalIdx + 1) + "\" does not exist.",
						"Re-specify terminal no.", 371);
			} else {
				// sets name of i-th terminal's connected bus in VVCControl's buslist
				setBus(0, monitoredElement.getBus(elementTerminalIdx));
			}
			Util.resizeArray(cBuffer, monitoredElement.getYOrder());
			condOffset = elementTerminalIdx * monitoredElement.getNumConds();
			// for speedy sampling
		} else {
			DSS.doSimpleMsg("Monitored Element in VVCControl." + getName() +
					" does not exist: \"" + elementName + "\"", 372);
		}

		if (generators.size() == 0) makeGenList();

		devIndex = Util.getCktElementIndex("generator." + generatorNames.get(0));

		if (devIndex >= 0) {
			// right now we only support one controlled element (generator) per vvcontrol
			// controlled element must already exist
			setControlledElement(DSS.activeCircuit.getCktElements().get(devIndex));
			getControlledElement().setActiveTerminalIdx(0);  // make the 1 st terminal active
			// get control synched up with capacitor
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("VVControl: \"" + getName() + "\"",
				"Controlled element \"" + generatorNames.get(0) + "\" not found.",
				"Element must be defined previously.", 361);
		}
	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled(getControlledElement().isEnabled());
			setNumPhases(getControlledElement().getNumPhases());
			setNumConds(getNumPhases());
		}

		if (monitoredElement != null) {
			setBus(0, monitoredElement.getBus(elementTerminalIdx));
			// allocate a buffer big enough to hold everything from the monitored element
			//Util.resizeArray(cBuffer, monitoredElement.getYorder());
			//condOffset = elementTerminal * monitoredElement.getNConds();  // for speedy sampling
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
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	/**
	 * Returns injextion currents
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
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
		double deltaQ, Qheadroom, QdesiredPU, Qneeded, PpresentGenOutput,
			QpresentGenOutput, QmonitoredElement, genKVAr;
		Complex SmonitoredElement, SpresentGenOutput;
		GeneratorObj gen;

		genKVAr = 0.0;
		// we need P and/or we need Q
		if (pendingChange == CHANGEVARLEVEL) {
			SmonitoredElement = monitoredElement.getPower( elementTerminalIdx );  // S is in VA
			// PMonitoredElement = SMonitoredElement.getReal();
			QmonitoredElement = SmonitoredElement.getImaginary();

			// PNeeded = kW_limit * 1000 - PMonitoredElement;
			Qneeded = kVAr_Limit * 1000 - QmonitoredElement;
			// if the generator list is not defined, go make one
			if (generators.size() == 0) makeGenList();

			getControlledElement().setActiveTerminalIdx(0);  // set active terminal of generator to terminal 1
			if (Qneeded != 0.0) {
				for (i = 0; i < listSize; i++) {
					SpresentGenOutput = getControlledElement().getPower(0);
					// S is in VA; we want terminal 1 of the generator
					PpresentGenOutput = SpresentGenOutput.getReal();
					QpresentGenOutput = SpresentGenOutput.getImaginary();

					// Q desired pu is the desired output based on the avg pu voltage on the
					// monitored element
					QdesiredPU = vvc_Curve.getYValue(VAvgPu);  // Y value = var in pu

					// the var 'head-room' available on the inverter given its rating
					// and present kW output
					if (Math.abs(PpresentGenOutput) > kVA_Rating * 1000.0) {
						Qheadroom = 0.0;
					} else {
						Qheadroom = Math.sqrt(Math.pow(kVA_Rating * 1000.0, 2) - Math.pow(PpresentGenOutput, 2));
					}
					QDeliver = Math.min(Math.abs(kVAr_FullOutput * 1000.0), Math.abs(Qheadroom));

					QDeliver = QDeliver * QdesiredPU;
					deltaQ = QDeliver - QOldDeliver;

					// only allow a small movement from old delivered (prior gen Q)
					// to the desired delivered Q
					QNew = QOldDeliver + deltaQ * deltaQFactor;

					if (QNew != QpresentGenOutput) {
						gen = (GeneratorObj) generators.get(i);
						genKVAr = Math.signum(QNew) * Math.min( Math.abs(kVAr_Limit * 1000.0), Math.abs(QNew) ) / 1000.0;
						if (genKVAr != gen.getKVArBase()) gen.setPresentKVAr(genKVAr);
					}
					Util.appendToEventLog("VoltVarControl." + getName(),
						String.format("**Set var output level to**, kvar= %.5g", genKVAr));
				}
			}
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
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		// if list is not defined, go make one for all generators in circuit
		if (generators.size() == 0) makeGenList();

		if (listSize > 0 && vvc_CurveSize > 0) {
			//if (presentHour != sol.getDblHour()) {
			//	DSS.writeDLLDebugFile(getName() + ',' +
			//		String.format("%-.5g", sol.getDblHour()) + ',' +
			//		String.format("%-.5g", QPresentGenOutput) + ',' +
			//		String.format("%-.5g", QDeliver) + ',' +
			//		String.format("%-.5g", QNew) + ',' +
			//		String.format("%-.5g", gen.getPresentkvar() * 1000.0) + ',' +
			//		String.format("%-.5g", QOldDeliver) + ',' +
			//		String.format("%-.5g", PPresentGenOutput) + ',' +
			//		String.format("%-.5g", VAvgPu) + ',' +
			//		String.format("%-.5g", VAvgPuPrior));
			//	presentHour = sol.dblHour;
			//}

			monitoredElement.computeVTerminal();
			cBuffer = monitoredElement.getVTerminal();

			// get the basekV for the monitored bus
			baseKV = ckt.getBus(terminals[elementTerminalIdx].getBusRef()).getKVBase();

			VAvg = 0;  // calculate the average voltage
			for (i = 0; i < monitoredElement.getNumPhases(); i++)
				VAvg = VAvg + cBuffer[i].abs();

			// convert to pu
			VAvgPu = (VAvg / monitoredElement.getNumPhases()) / (baseKV * 1000.0);

			timeDelay = delay;
			// if (sol.getControlIteration() < sol.getMaxControlIterations()) {
			if ((Math.abs(VAvgPu - VAvgPuPrior) > deltaVTolerance) || (Math.abs(Math.abs(QDeliver) - Math.abs(QNew)) > 0.5)) {
				setPendingChange(CHANGEVARLEVEL);

				// sol.setLoadsNeedUpdating(true);  // force recalc of power parms
				controlActionHandle = ckt.getControlQueue().push(sol.getIntHour(),
						sol.getDynaVars().t + timeDelay, pendingChange, 0, this);
				Util.appendToEventLog("VoltVarControl." + getName(),
						String.format("**Ready to change var output**, Vavgpu= %.5g sec,", VAvgPu));
			} else {
				ckt.getControlQueue().delete(controlActionHandle);
				Util.appendToEventLog("VoltVarControl." + getName(), "**DONE**");
			}
		} else {
			DSS.doSimpleMsg("Could not find any generators, or the vvc curve size is zero.", 1234);
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
			return String.format("%-d", elementTerminalIdx);
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
		int i;
		DSSClass genClass;
		GeneratorObj gen;

		boolean made = false;
		genClass = DSSClassDefs.getDSSClass("generator");

		if (listSize > 0) {
			// name list is defined - use it
			for (i = 0; i < listSize; i++) {
				gen = (GeneratorObj) genClass.find(generatorNames.get(i));
				if ((gen != null) && gen.isEnabled()) generators.add(gen);
			}
		} else {
			/* Search through the entire circuit for enabled generators and add them to the list */
			for (i = 0; i < genClass.getElementCount(); i++) {
				gen = (GeneratorObj) genClass.getElementList().get(i);
				if (gen.isEnabled()) generators.add(gen);
				generatorNames.add(gen.getDisplayName());
			}

			/* Allocate uniform weights */
			listSize = generators.size();
			Util.resizeArray(weights, listSize);
			for (i = 0; i < listSize; i++) weights[i] = 1.0;
		}

		// add up total weights
		totalWeight = 0.0;
		for (i = 0; i < listSize; i++)
			totalWeight = totalWeight + weights[i];

		if (generators.size() > 0) made = true;

		return made;
	}

	public String returnGensList() {
		if (listSize == 0) return "";


		StringBuffer sb = new StringBuffer("[");
		sb.append(generatorNames.get(0));
		for (int i = 1; i < listSize; i++) {
			sb.append(", ");
			sb.append(generatorNames.get(i));
		}
		sb.append("]");

		return sb.toString();
	}

	public String returnWeightsList() {
		if (listSize == 0) return "";

		StringBuilder sb = new StringBuilder("[");
		sb.append(String.format("%-.6g", weights[0]));
		for (int i = 1; i < listSize; i++)
			sb.append(String.format(", %-.6g", weights[i]));

		sb.append("]");  // terminate the array

		return sb.toString();
	}

	public String returnVVCurve() {
		if (vvc_CurveSize == 0) return "";

		StringBuilder sb = new StringBuilder("[{");
		sb.append(String.format("%-.3g,", vvc_Curve.getXValue(0)));
		sb.append(String.format("%-.3g", vvc_Curve.getYValue(0)));
		sb.append("},");
		for (int i = 1; i < vvc_CurveSize; i++) {
			sb.append(String.format("{ %-.3g,", vvc_Curve.getXValue(i)));
			sb.append(String.format("%-.3g", vvc_Curve.getYValue(i)));
			sb.append("},");
		}
		sb.append("]");

		return sb.toString();
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
		setTraceParameter(Value);
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

}
