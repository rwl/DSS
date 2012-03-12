package com.ncond.dss.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.delivery.TransformerObj;
import com.ncond.dss.shared.ComplexUtil;

/**
 * A control element that is connected to a terminal of another
 * circuit element that must be a transformer.
 *
 *   new regControl.name=myName transformer=name terminal=[1,2,...] controlledBus=name etc...
 *
 * Transformer to be controlled must already exist.
 *
 */
@Getter @Setter
public class RegControlObj extends ControlElem {

	private static int lastChange;

	private double Vreg,
		bandwidth,
		PTRatio,
		CTRating,
		R,
		X;

	/* Reverse power variables */
	private double revVreg,
		revBandwidth,
		revPowerThreshold,   // W
		kWRevPowerThreshold,
		revDelay,
		revR,
		revX;

	private boolean isReversible, inReverseMode, reversePending, reverseNeutral;
	private boolean LDCActive, usingRegulatedBus;
	private String regulatedBus;

	private double pendingTapChange;  // amount of tap change pending
	private double tapDelay;          // delay between taps

	private boolean debugTrace;
	private boolean armed;
	private File traceFile;

	private int tapLimitPerChange;
	private int tapWinding;
	private boolean inverseTime;
	private double VLimit;
	private boolean VLimitActive;

	private int PTPhase;
	private int controlledPhase;

	private Complex[] VBuffer, CBuffer;

	public RegControlObj(DSSClass parClass, String regControlName) {
		super(parClass);
		setName(regControlName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);   // this forces allocation of terminals and conductors in base class

		Vreg         = 120.0;
		bandwidth    = 3.0;
		PTRatio      = 60.0;
		CTRating     = 300.0;
		R            = 0.0;
		X            = 0.0;
		timeDelay    = 15.0;

		PTPhase = 1;

		LDCActive    = false;
		tapDelay = 2.0;
		tapLimitPerChange = 16;

		debugTrace = false;
		armed      = false;

		/* Reverse mode variables */
		revVreg      = 120.0;
		revBandwidth = 3.0;
		revR         = 0.0;
		revX         = 0.0;
		revDelay     =  60.0;  // power must be reversed this long before it will reverse
		revPowerThreshold   = 100000.0;  // 100 kW
		kWRevPowerThreshold = 100.0;
		isReversible = false;
		reversePending = false;
		inReverseMode  = false;
		reverseNeutral = false;

		elementName = "";
		setControlledElement(null);
		elementTerminal = 0;
		tapWinding = elementTerminal;

		VBuffer = null;
		CBuffer = null;

		objType = parClass.getClassType();  // REG_CONTROL;

		initPropertyValues(0);
		inverseTime = false;
		regulatedBus = "";
		VLimit = 0.0;
		//recalcElementData();
	}

	@Override
	public void recalcElementData() {

		if (R != 0.0 || X != 0.0) {
			LDCActive = true;
		} else {
			LDCActive = false;
		}
		if (regulatedBus.length() == 0) {
			usingRegulatedBus = false;
		} else {
			usingRegulatedBus = true;
		}

		int devIndex = Util.getCktElementIndex(elementName);
		if (devIndex >= 0) {
			// RegControled element must already exist
			setControlledElement(DSS.activeCircuit.getCktElements().get(devIndex));

			if (usingRegulatedBus) {
				setNumPhases(1);  // only need one phase
				setNumConds(2);
			} else {
				setNumPhases( getControlledElement().getNumPhases() );
				setNumConds(nPhases);
				if (PTPhase > nPhases) {
					PTPhase = 1;
					setPropertyValue(21, "1");
				}
			}

			if (getControlledElement().getDSSClassName().equalsIgnoreCase("transformer")) {
				if (elementTerminal > getControlledElement().getNumTerms()) {
					DSS.doErrorMsg("RegControl: \"" + getName() + "\"", "Winding no. \"" +"\" does not exist.",
							"Respecify monitored winding no.", 122);
				} else {
					// sets name of i-th terminal's connected bus in RegControl's bus list
					// this value will be used to set the nodeRef array (see sample function)
					if (usingRegulatedBus) {
						setBus(0, regulatedBus);  // hopefully this will actually exist
					} else {
						setBus(0, getControlledElement().getBus(elementTerminal));
					}
					// buffer to hold regulator voltages
					VBuffer = Util.resizeArray(VBuffer, getControlledElement().getNumPhases());
					CBuffer = Util.resizeArray(CBuffer, getControlledElement().getYOrder());
				}
			} else {
				setControlledElement(null);  // we get here if element not found
				DSS.doErrorMsg("RegControl: \"" + getName() + "\"", "Controlled regulator element \""+ elementName + "\" is not a transformer.",
						" Element must be defined previously.", 123);
			}
		} else {
			setControlledElement(null);  // element not found
			DSS.doErrorMsg("RegControl: \"" + getName() + "\"", "Transformer element \""+ elementName + "\" not found.",
					" Element must be defined previously.", 124);
		}
	}

	@Override
	public void calcYPrim() {
		// leave YPrim as null and it will be ignored ... zero current source
	}

	private Complex getControlVoltage(Complex[] VBuffer, int nphs, double PTRatio) {
		int i;
		double V;
		Complex result;

		switch (PTPhase) {
		//case RegControl.AVGPHASES:
		//	result = Complex.ZERO;
		//	for (i = 0; i < nphs; i++)
		//		result = result + VBuffer[i].abs();
		//	result = result.divide(nphs * PTRatio);
		//	break;
		case RegControl.MAXPHASE:
			controlledPhase = 1;
			V = VBuffer[controlledPhase].abs();
			for (i = 1; i < nphs; i++) {
				if (VBuffer[i].abs() > V) {
					V = VBuffer[i].abs();
					controlledPhase = i;
				}
			}
			result = ComplexUtil.divide(VBuffer[controlledPhase], PTRatio);
			break;
		case RegControl.MINPHASE:
			controlledPhase = 1;
			V = VBuffer[controlledPhase].abs();
			for (i = 1; i < nphs; i++) {
				if (VBuffer[i].abs() < V) {
					V = VBuffer[i].abs();
					controlledPhase = i;
				}
			}
			result = ComplexUtil.divide(VBuffer[controlledPhase], PTRatio);
			break;
		default:
			/* Just use one phase because that's what most controls do. */
			result = ComplexUtil.divide(VBuffer[PTPhase], PTRatio);
			controlledPhase = PTPhase;
			break;
		}
		return result;
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) {
			pw.println("! Bus =" + getBus(0));
			pw.println();
		}
		pw.close();
	}

	/**
	 * Called in static mode.
	 * Changes 70% of the way but at least one tap, subject to maximum allowable tap change.
	 */
	private double atLeastOneTap(double proposedChange, double increment) {
		int numTaps;
		double result;

		numTaps = (int) (0.7 * Math.abs(proposedChange) / increment);

		if (numTaps == 0)
			numTaps = 1;

		if (numTaps > tapLimitPerChange)
			numTaps = tapLimitPerChange;

		lastChange = numTaps;

		if (proposedChange > 0.0) {  // check sign on change
			result = numTaps * increment;
		} else {
			result = -numTaps * increment;
			lastChange = -numTaps;
		}

		return result;
	}

	/**
	 * Computes the amount of one tap change in the direction of the pending tapchange.
	 * Automatically decrements the proposed change by that amount.
	 */
	private double oneInDirectionOf(double proposedChange, double increment) {
		double result;

		lastChange = 0;
		if (proposedChange > 0.0) {
			result = increment;
			lastChange = 1;
			proposedChange = proposedChange - increment;
		} else {
			result = -increment;
			lastChange = -1;
			proposedChange = proposedChange + increment;
		}

		if (Math.abs(proposedChange) < 0.9 * increment)
			proposedChange = 0.0;

		return result;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int code, int proxyHdl) {

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		double tapChangeToMake;

		switch (code) {
		case RegControl.ACTION_TAPCHANGE:
			if (debugTrace)
				regWriteDebugRecord(String.format("+++ %.6g s: Handling TapChange = %.8g",
						ckt.getSolution().getDynaVars().t, pendingTapChange));

			if (pendingTapChange == 0.0) {  /* Check to make sure control has not reset */
				armed = false;
			} else {
				TransformerObj pElem = (TransformerObj) getControlledElement();

				switch (sol.getControlMode()) {
				case CTRLSTATIC:
					tapChangeToMake = atLeastOneTap(pendingTapChange, pElem.getTapIncrement(tapWinding));
					if (debugTrace)
						regWriteTraceRecord(tapChangeToMake);
					pElem.setPresentTap(tapWinding, pElem.getPresentTap(tapWinding) + tapChangeToMake);
					if (showEventLog)
						Util.appendToEventLog("Regulator." + getControlledElement().getName(), String.format(" Changed %d taps to %-.6g.", lastChange, pElem.getPresentTap(tapWinding)));
					setPendingTapChange(0.0);  // reset to no change; program will determine if another needed
					armed = false;

				case EVENTDRIVEN:
					tapChangeToMake = oneInDirectionOf(pendingTapChange, pElem.getTapIncrement(tapWinding));
					if (debugTrace)
						regWriteTraceRecord(tapChangeToMake);
					pElem.setPresentTap(tapWinding, pElem.getPresentTap(tapWinding) + tapChangeToMake);
					if (pendingTapChange != 0.0) {
						ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + tapDelay, 0, 0, this);
					} else {
						armed = false;
					}

				case TIMEDRIVEN:
					tapChangeToMake = oneInDirectionOf(pendingTapChange, pElem.getTapIncrement(tapWinding));
					if (debugTrace)
						regWriteTraceRecord(tapChangeToMake);
					pElem.setPresentTap(tapWinding, pElem.getPresentTap(tapWinding) + tapChangeToMake);
					if (showEventLog)
						Util.appendToEventLog("Regulator." + getControlledElement().getName(), String.format(" Changed %d tap to %-.6g.", lastChange, pElem.getPresentTap(tapWinding)));
					if (debugTrace)
						regWriteDebugRecord(String.format("--- Regulator.%s Changed %d tap to %-.6g.",
								pElem.getControlElement().getName(), lastChange, pElem.getPresentTap(tapWinding)));

					if (pendingTapChange != 0.0) {
						ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + tapDelay, 0, 0, this);
					} else {
						armed = false;
					}
				}
			}
			break;
		case RegControl.ACTION_REVERSE:
			// toggle reverse mode flag
			if (debugTrace)
				regWriteDebugRecord(String.format("Handling Reverse Action, ReversePending=%s, InReverseMode=%s",
						String.valueOf(reversePending), String.valueOf(inReverseMode)));
			if (reversePending) {  // check to see if action has reset
				if (inReverseMode) {
					inReverseMode = false;
				} else {
					inReverseMode = true;
				}
				reversePending = false;
			}
			break;
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		double boostNeeded, increment, VActual, VBoost;
		double VLocalBus;
		double fwdPower;
		Complex VControl, VLDC, ILDC;
		boolean tapChangeIsNeeded;
		int i, ii;
		TransformerObj controlledTransformer;
		Connection transformerConnection;

		Circuit ckt = DSS.activeCircuit;
		controlledTransformer = (TransformerObj) getControlledElement();

		/* First, check the direction of power flow to see if we need to
		 * reverse direction */
		/* Don't do this if using regulated bus logic */
		if (!usingRegulatedBus) {
			if (isReversible) {

				if (!inReverseMode) {  // if looking forward, check to see if we should reverse

					if (!reversePending) {  // if reverse is already pending, don't send any more messages

						fwdPower = -controlledTransformer.getPower(elementTerminal).getReal();  // Watts
						if (fwdPower < -revPowerThreshold) {

							if (debugTrace)
								regWriteDebugRecord(String.format("Pushing Reverse Action, FwdPower=%.8g", fwdPower));
							reversePending = true;
							ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
							ckt.getSolution().getDynaVars().t + revDelay,
							RegControl.ACTION_REVERSE, 0, this);
						} else {
							reversePending = false;  // reset it if power goes back
						}
					}
				} else {
					// if reversed look to see if power is back in forward direction
					if (!reversePending) {
						fwdPower = -controlledTransformer.getPower(elementTerminal).getReal();  // Watts
						if (fwdPower > revPowerThreshold) {

							if (debugTrace)
								regWriteDebugRecord(String.format("Pushing Reverse Action to switch back, FwdPower=%.8g", fwdPower));
							reversePending = true;
							ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
							ckt.getSolution().getDynaVars().t + revDelay,
							RegControl.ACTION_REVERSE, 0, this);
						} else {
							reversePending = false;  // reset it if power went back to reverse
						}
					}

					/* Check for special case of reverse neutral where
					 * regulator is to move to neutral position */
					if (reverseNeutral) {

						if (!armed) {

							setPendingTapChange(0.0);
							if (Math.abs(controlledTransformer.getPresentTap(tapWinding) - 1.0) > DSS.EPSILON) {

								increment = controlledTransformer.getTapIncrement(tapWinding);
								setPendingTapChange( Math.round((1.0 - controlledTransformer.getPresentTap(tapWinding)) / increment) * increment );
								if ((pendingTapChange != 0.0) && (!armed)) {

									if (debugTrace)
										regWriteDebugRecord(String.format("*** %.6g s: Pushing TapChange = %.8g, delay= %.8g",
									ckt.getSolution().getDynaVars().t,
									pendingTapChange, tapDelay));
									ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
									ckt.getSolution().getDynaVars().t + tapDelay,
									RegControl.ACTION_TAPCHANGE, 0, this);
									armed = true;
								}
							}
						}
						return;  // we're done here in any case if reverse neutral specified
					}
				}  // else
			}
		}

		if (usingRegulatedBus) {

			transformerConnection = controlledTransformer.getWinding()[elementTerminal].getConnection();
			computeVTerminal();  // computes the voltage at the bus being regulated
			for (i = 0; i < getNumPhases(); i++) {
				switch (transformerConnection) {
				case WYE:
					VBuffer[i] = VTerminal[i];
					break;
				case DELTA:
					// get next phase in sequence using transformer obj rotate
					ii = controlledTransformer.rotatePhases(i);
					VBuffer[i] = VTerminal[i].subtract( VTerminal[ii] );
					break;
				}
			}
		} else {
			controlledTransformer.getWindingVoltages(elementTerminal, VBuffer);
		}

		VControl = getControlVoltage(VBuffer, getNumPhases(), PTRatio);

		// check VLimit
		if (VLimitActive) {

			if (usingRegulatedBus) {

				controlledTransformer.getWindingVoltages(elementTerminal,
						VBuffer);
				VLocalBus = ComplexUtil.divide(VBuffer[1], PTRatio).abs();
			} else {
				VLocalBus = VControl.abs();
			}
		} else {
			VLocalBus = 0.0;  // to get rid of warning message
		}

		// check for LDC
		if ((!usingRegulatedBus) && LDCActive) {

			getControlledElement().getCurrents(CBuffer);
			ILDC  = ComplexUtil.divide(CBuffer[getControlledElement().getNumConds() * elementTerminal + controlledPhase], CTRating);
			if (inReverseMode) {
				VLDC  = new Complex(revR, revX).multiply(ILDC);
			} else {
				VLDC  = new Complex(R, X).multiply(ILDC);
			}
			VControl = VControl.add(VLDC);  // direction on ILDC is into terminal, so this is equivalent to Vterm - (R+jX)*ILDC
		}

		VActual = VControl.abs();

		// check for out of band voltage
		if (Math.abs(Vreg - VActual) > bandwidth / 2.0) {
			tapChangeIsNeeded = true;
		} else {
			tapChangeIsNeeded = false;
		}

		if (VLimitActive)
			if (VLocalBus > VLimit)
				tapChangeIsNeeded = true;

		if (tapChangeIsNeeded) {

			// compute tapchange
			VBoost = Vreg - VActual;
			if (VLimitActive)
				if (VLocalBus > VLimit)
					VBoost = VLimit - VLocalBus;
			boostNeeded = VBoost * PTRatio / controlledTransformer.getBaseVoltage(elementTerminal);  // per unit winding boost needed
			increment = controlledTransformer.getTapIncrement(tapWinding);
			setPendingTapChange( Math.round(boostNeeded / increment) * increment );  // make sure it is an even increment

			/* If tap is another winding or in reverse mode, it has to move
			 * the other way to accomplish the change */
			if (tapWinding != elementTerminal || inReverseMode)
				setPendingTapChange(-pendingTapChange);

			// send initial tap change message to control queue
			// add delay time to solution control queue
			if (pendingTapChange != 0.0 && !armed) {

				// now see if any tap change is possible in desired direction, else ignore
				if (pendingTapChange > 0.0) {

					if (controlledTransformer.getPresentTap(tapWinding) < controlledTransformer.getMaxTap(tapWinding)) {
						ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
								ckt.getSolution().getDynaVars().t + computeTimeDelay(VActual),
								RegControl.ACTION_TAPCHANGE, 0, this);
						armed = true;  // armed to change taps
					}
				} else {
					if (controlledTransformer.getPresentTap(tapWinding) > controlledTransformer.getMinTap(tapWinding)) {
						ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
								ckt.getSolution().getDynaVars().t + computeTimeDelay(VActual),
								RegControl.ACTION_TAPCHANGE, 0, this);
						armed = true;  // armed to change taps
					}
				}
			}
		} else {
			setPendingTapChange(0.0);
		}
	}

	/**
	 * Controlled transformer.
	 */
	public TransformerObj getTransformer() {
		return (TransformerObj) getControlledElement();
	}

	/**
	 * Report tapped winding.
	 */
	public int getWinding() {
		return tapWinding;
	}

	public double getMinTap() {
		return getTransformer().getMinTap(tapWinding);
	}

	public double getMaxTap() {
		return getTransformer().getMaxTap(tapWinding);
	}

	public double getTapIncrement() {
		return getTransformer().getTapIncrement(tapWinding);
	}

	public int getNumTaps() {
		return getTransformer().getNumTaps(tapWinding);
	}

	private void regWriteDebugRecord(String s) {
		FileWriter fw;
		BufferedWriter bw;

		// write a general debug string
		try {
			if (!DSS.inShowResults) {
				fw = new FileWriter(traceFile, true);
				bw = new BufferedWriter(fw);
				bw.write(s);
				bw.newLine();
				bw.close();
				fw.close();
			}
		} catch (Exception e) {

		}
	}

	private void regWriteTraceRecord(double tapChangeMade) {
		FileWriter fw;
		BufferedWriter bw;
		String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		try {
			if (!DSS.inShowResults) {
				fw = new FileWriter(traceFile, true);
				bw = new BufferedWriter(fw);

				TransformerObj pElem = (TransformerObj) getControlledElement();

				bw.write(ckt.getSolution().getIntHour() + sep +
						ckt.getSolution().getDynaVars().t + sep +
						ckt.getSolution().getControlIteration() + sep +
						ckt.getSolution().getIteration() + sep +
						ckt.getLoadMultiplier() + sep +
						pElem.getPresentTap(elementTerminal) + sep +
						pendingTapChange + sep +
						tapChangeMade + sep +
						pElem.getTapIncrement(elementTerminal) + sep +
						pElem.getMinTap(elementTerminal) + sep +
						pElem.getMaxTap(elementTerminal));
				bw.newLine();

				bw.close();
				fw.close();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Reset to initial defined state.
	 */
	@Override
	public void reset() {
		setPendingTapChange(0.0);
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "");   // "element";
		setPropertyValue(1, "1");  // "terminal";
		setPropertyValue(2, "120");
		setPropertyValue(3, "3");
		setPropertyValue(4, "60");
		setPropertyValue(5, "300");
		setPropertyValue(6, "0");
		setPropertyValue(7, "0");
		setPropertyValue(8, "");
		setPropertyValue(9, "15");
		setPropertyValue(10, "no");
		setPropertyValue(11, "120");
		setPropertyValue(12, "3");
		setPropertyValue(13, "0");
		setPropertyValue(14, "0");
		setPropertyValue(15, "2");
		setPropertyValue(16, "no");
		setPropertyValue(17, "16");
		setPropertyValue(18, "no");
		setPropertyValue(19, "1");
		setPropertyValue(20, "0.0");
		setPropertyValue(21, "1");
		setPropertyValue(22, "100");
		setPropertyValue(23, "60");
		setPropertyValue(24, "No");
		setPropertyValue(25, "YES");

		super.initPropertyValues(RegControl.NumPropsThisClass - 1);
	}

	public void setPendingTapChange(double value) {
		pendingTapChange = value;
		setDblTraceParameter(value);
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled( getControlledElement().isEnabled() );
			if (usingRegulatedBus) {
				setNumPhases(1);
			} else {
				setNumPhases( getControlledElement().getNumPhases() );
			}
			setNumConds(nPhases);
			if (getControlledElement().getDSSClassName().equalsIgnoreCase("transformer")) {
				// sets name of i-th terminal's connected bus in RegControl's bus list
				// this value will be used to set the nodeRef array (see sample function)
				if (usingRegulatedBus) {
					setBus(0, regulatedBus);  // hopefully this will actually exist
				} else {
					setBus(0, getControlledElement().getBus(elementTerminal));
					// buffer to hold regulator voltages
					VBuffer = Util.resizeArray(VBuffer, getControlledElement().getNumPhases());
					CBuffer = Util.resizeArray(CBuffer, getControlledElement().getYOrder());
				}
			}
		}
		super.makePosSequence();
	}

	private double computeTimeDelay(double Vavg) {
		if (inverseTime) {
			return timeDelay / Math.min(10.0, (2.0 * Math.abs(Vreg - Vavg) / bandwidth));
		} else {
			return timeDelay;
		}
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

}
