package com.epri.dss.control.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.RegControl;
import com.epri.dss.control.RegControlObj;
import com.epri.dss.delivery.TransformerObj;

public class RegControlObjImpl extends ControlElemImpl implements RegControlObj {

	private static int LastChange;

	private double Vreg,
		Bandwidth,
		PTRatio,
		CTRating,
		R,
		X;

	/* Reverse Power Variables */
	private double revVreg,
		revBandwidth,
        RevPowerThreshold,   // W
        kWRevPowerThreshold,
        revDelay,
		revR,
		revX;

	private boolean IsReversible, InReverseMode, ReversePending, ReverseNeutral;
	private boolean LDCActive, UsingRegulatedBus;
    private String RegulatedBus;

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
		setName(RegControlName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(1);   // this forces allocation of terminals and conductors in base class

		this.Vreg         = 120.0;
		this.Bandwidth    = 3.0;
		this.PTRatio      = 60.0;
		this.CTRating     = 300.0;
		this.R            = 0.0;
		this.X            = 0.0;
		this.TimeDelay    = 15.0;

		this.PTphase = 1;

		this.LDCActive    = false;
		this.TapDelay = 2.0;
		this.TapLimitPerChange = 16;

		this.DebugTrace = false;
		this.Armed      = false;

		/* Reverse mode variables */
		this.revVreg      = 120.0;
		this.revBandwidth = 3.0;
		this.revR         = 0.0;
		this.revX         = 0.0;
		this.revDelay     =  60.0;  // power must be reversed this long before it will reverse
		this.RevPowerThreshold   = 100000.0;  // 100 kW
		this.kWRevPowerThreshold = 100.0;
		this.IsReversible = false;
		this.ReversePending = false;
		this.InReverseMode  = false;
		this.ReverseNeutral = false;

		this.ElementName = "";
		setControlledElement(null);
		this.ElementTerminal = 1;
		this.TapWinding = ElementTerminal;

		this.VBuffer = null;
		this.CBuffer = null;

		this.DSSObjType = ParClass.getDSSClassType();  // REG_CONTROL;

		initPropertyValues(0);
		this.Inversetime = false;
		this.RegulatedBus = "";
		this.Vlimit = 0.0;
		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if ((R != 0.0) || (X != 0.0)) {
			LDCActive = true;
		} else {
			LDCActive = false;
		}
		if (RegulatedBus.length() == 0) {
			UsingRegulatedBus = false;
		} else {
			UsingRegulatedBus = true;
		}

		int DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {  // TODO Check zero based indexing
			// RegControled element must already exist
			setControlledElement(Globals.getActiveCircuit().getCktElements().get(DevIndex));

			if (UsingRegulatedBus) {
				setNPhases(1);  // only need one phase
				setNConds(2);
			} else {
				setNPhases( getControlledElement().getNPhases() );
				setNConds(nPhases);
				if (PTphase > nPhases) {
					PTphase = 1;
					setPropertyValue(21, "1");
				}
			}

			if (getControlledElement().getDSSClassName().equalsIgnoreCase("transformer")) {
				if (ElementTerminal > getControlledElement().getNTerms()) {
					Globals.doErrorMsg("RegControl: \"" + getName() + "\"", "Winding no. \"" +"\" does not exist.",
							"Respecify Monitored Winding no.", 122);
				} else {
					// Sets name of i-th terminal's connected bus in RegControl's buslist
					// This value will be used to set the NodeRef array (see Sample function)
					if (UsingRegulatedBus) {
						setBus(1, RegulatedBus);   // hopefully this will actually exist
					} else {
						setBus(1, getControlledElement().getBus(ElementTerminal));
					}
					// buffer to hold regulator voltages
					VBuffer = (Complex[]) Utilities.resizeArray(VBuffer, getControlledElement().getNPhases());
					CBuffer = (Complex[]) Utilities.resizeArray(CBuffer, getControlledElement().getYorder());
				}
			} else {
				setControlledElement(null);  // we get here if element not found
				Globals.doErrorMsg("RegControl: \"" + getName() + "\"", "Controlled Regulator Element \""+ ElementName + "\" Is not a transformer.",
						" Element must be defined previously.", 123);
			}
		} else {
			setControlledElement(null);  // element not found
			Globals.doErrorMsg("RegControl: \"" + getName() + "\"", "Transformer Element \""+ ElementName + "\" Not Found.",
					" Element must be defined previously.", 124);
		}
	}

	@Override
	public void calcYPrim() {
		// Leave YPrim as null and it will be ignored ... zero current source
		// Yprim is zeroed when created.  Leave it as is.
	}

	private Complex getControlVoltage(Complex[] VBuffer, int Nphs, double PTRatio) {
		int i;
		double V;
		Complex Result;

		switch (PTphase) {
//		case AVGPHASES:
//			Result = Complex.ZERO;
//			for (i = 0; i < Nphs; i++)
//				Result = Result + VBuffer[i].abs();
//			Result = Result.divide(Nphs * PTRatio);
//			break;
		case RegControl.MAXPHASE:
			ControlledPhase = 1;
			V = VBuffer[ControlledPhase].abs();
			for (i = 1; i < Nphs; i++) {
				if (VBuffer[i].abs() > V) {
					V = VBuffer[i].abs();
					ControlledPhase = i;
				}
			}
			Result = VBuffer[ControlledPhase].divide(PTRatio);
			break;
		case RegControl.MINPHASE:
			ControlledPhase = 1;
			V = VBuffer[ControlledPhase].abs();
			for (i = 1; i < Nphs; i++) {
				if (VBuffer[i].abs() < V) {
					V = VBuffer[i].abs();
					ControlledPhase = i;
				}
			}
			Result = VBuffer[ControlledPhase].divide(PTRatio);
			break;
		default:
			/* Just use one phase because that's what most controls do. */
			Result = VBuffer[PTphase].divide(PTRatio);
			ControlledPhase = PTphase;
			break;
		}
		return Result;
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {
		for (int i = 0; i < nConds; i++)
			Curr[i] = Complex.ZERO;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete) {
			F.println("! Bus =" + getBus(1));
			F.println();
		}
	}

	/**
	 * Called in static mode.
	 * Changes 70% of the way but at least one tap, subject to maximum allowable tap change.
	 */
	private double atLeastOneTap(double ProposedChange, double Increment) {
		double Result;

		int NumTaps = (int) (0.7 * Math.abs(ProposedChange) / Increment);

		if (NumTaps == 0) NumTaps = 1;

		if (NumTaps > TapLimitPerChange) NumTaps = TapLimitPerChange;

		LastChange = NumTaps;

		if (ProposedChange > 0.0) {  // check sign on change
			Result = NumTaps * Increment;
		} else {
			Result = -NumTaps * Increment;
			LastChange = -NumTaps;
		}

		return Result;
	}

	/**
	 * Computes the amount of one tap change in the direction of the pending tapchange.
	 * Automatically decrements the proposed change by that amount.
	 */
	private double oneInDirectionOf(double ProposedChange, double Increment) {
		double Result;

		LastChange = 0;
		if (ProposedChange > 0.0) {
			Result = Increment;
			LastChange = 1;
			ProposedChange = ProposedChange - Increment;
		} else {
			Result = -Increment;
			LastChange = -1;
			ProposedChange = ProposedChange + Increment;
		}

		if (Math.abs(ProposedChange) < 0.9 * Increment)
			ProposedChange = 0.0;

		return Result;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		double TapChangeToMake;

		switch (Code) {
		case RegControl.ACTION_TAPCHANGE:
			if (DebugTrace)
				regWriteDebugRecord(String.format("+++ %.6g s: Handling TapChange = %.8g",
						ckt.getSolution().getDynaVars().t, PendingTapChange));

			if (PendingTapChange == 0.0) {  /* Check to make sure control has not reset */
				Armed = false;
			} else {
				TransformerObj pElem = (TransformerObj) getControlledElement();

				switch (sol.getControlMode()) {
				case DSSGlobals.CTRLSTATIC:
                    TapChangeToMake = atLeastOneTap(PendingTapChange, pElem.getTapIncrement(TapWinding));
					if (DebugTrace)
						regWriteTraceRecord(TapChangeToMake);
					pElem.setPresentTap(TapWinding, pElem.getPresentTap(TapWinding) + TapChangeToMake);
					Utilities.appendToEventLog("Regulator." + getControlledElement().getName(), String.format(" Changed %d taps to %-.6g.", LastChange, pElem.getPresentTap(TapWinding)));
					setPendingTapChange(0.0);  // reset to no change; program will determine if another needed
					Armed = false;

				case DSSGlobals.EVENTDRIVEN:
                    TapChangeToMake = oneInDirectionOf(PendingTapChange, pElem.getTapIncrement(TapWinding));
					if (DebugTrace)
						regWriteTraceRecord(TapChangeToMake);
					pElem.setPresentTap(TapWinding, pElem.getPresentTap(TapWinding) + TapChangeToMake);
					if (PendingTapChange != 0.0) {
						ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + TapDelay, 0, 0, this);
					} else {
						Armed = false;
					}

				case DSSGlobals.TIMEDRIVEN:
                    TapChangeToMake = oneInDirectionOf(PendingTapChange, pElem.getTapIncrement(TapWinding));
					if (DebugTrace)
						regWriteTraceRecord(TapChangeToMake);
					pElem.setPresentTap(TapWinding, pElem.getPresentTap(TapWinding) + TapChangeToMake);
					Utilities.appendToEventLog("Regulator." + getControlledElement().getName(), String.format(" Changed %d tap to %-.6g.", LastChange, pElem.getPresentTap(TapWinding)));
					if (DebugTrace)
						regWriteDebugRecord(String.format("--- Regulator.%s Changed %d tap to %-.6g.",
								pElem.getControlElement().getName(), LastChange, pElem.getPresentTap(TapWinding)));

					if (PendingTapChange != 0.0) {
						ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + TapDelay, 0, 0, this);
					} else {
						Armed = false;
					}
				}
			}
			break;
		case RegControl.ACTION_REVERSE:
			// Toggle reverse mode flag
			if (DebugTrace)
				regWriteDebugRecord(String.format("Handling Reverse Action, ReversePending=%s, InReverseMode=%s",
						String.valueOf(ReversePending), String.valueOf(InReverseMode)));
			if (ReversePending) {  // check to see if action has reset
				if (InReverseMode) {
					InReverseMode = false;
				} else {
					InReverseMode = true;
				}
				ReversePending = false;
			}
			break;
		}
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		double BoostNeeded, Increment, Vactual, Vboost;
		double VlocalBus;
		double FwdPower;
		Complex Vcontrol, VLDC, ILDC;
		boolean TapChangeIsNeeded;
		int i, ii;
		TransformerObj ControlledTransformer;
		int TransformerConnection;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		ControlledTransformer = (TransformerObj) getControlledElement();

		/* First, check the direction of power flow to see if we need to
		 * reverse direction */
		/* Don't do this if using regulated bus logic */
		if (!UsingRegulatedBus) {
			if (IsReversible) {

				if (!InReverseMode) {  // If looking forward, check to see if we should reverse

					if (!ReversePending) {  // If reverse is already pending, don't send any more messages

						FwdPower = -ControlledTransformer.getPower(ElementTerminal).getReal();  // watts
						if (FwdPower < -RevPowerThreshold) {

							if (DebugTrace)
								regWriteDebugRecord(String.format("Pushing Reverse Action, FwdPower=%.8g", FwdPower));
							ReversePending = true;
							ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
									ckt.getSolution().getDynaVars().t + revDelay,
									RegControl.ACTION_REVERSE, 0, this);
						} else {
							ReversePending = false;  // Reset it if power goes back
						}
					}
				} else {
					// If reversed look to see if power is back in forward direction
					if (!ReversePending) {
						FwdPower = -ControlledTransformer.getPower(ElementTerminal).getReal();  // watts
						if (FwdPower > RevPowerThreshold) {

							if (DebugTrace)
								regWriteDebugRecord(String.format("Pushing Reverse Action to switch back, FwdPower=%.8g", FwdPower));
	                        ReversePending = true;
	                        ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
	                        		ckt.getSolution().getDynaVars().t + revDelay,
	                        		RegControl.ACTION_REVERSE, 0, this);
						} else {
							ReversePending = false; // Reset it if power went back to reverse
						}
					}

					/* Check for special case of Reverse Neutral where
					 * regulator is to move to neutral position */
					if (ReverseNeutral) {

						if (!Armed) {

							setPendingTapChange(0.0);
							if (Math.abs(ControlledTransformer.getPresentTap(TapWinding) - 1.0) > DSSGlobals.EPSILON) {

								Increment = ControlledTransformer.getTapIncrement(TapWinding);
								setPendingTapChange( Math.round((1.0 - ControlledTransformer.getPresentTap(TapWinding)) / Increment) * Increment );
								if ((PendingTapChange != 0.0) && (!Armed)) {

									if (DebugTrace)
										regWriteDebugRecord(String.format("*** %.6g s: Pushing TapChange = %.8g, delay= %.8g",
												ckt.getSolution().getDynaVars().t,
												PendingTapChange, TapDelay));
									ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
											ckt.getSolution().getDynaVars().t + TapDelay,
											RegControl.ACTION_TAPCHANGE, 0, this);
									Armed = true;
								}
							}
						}
						return;  // We're done here in any case if Reverse neutral specified
					}
				}  // else
			}
		}

		if (UsingRegulatedBus) {

			TransformerConnection = ControlledTransformer.getWinding()[ElementTerminal].getConnection();
			computeVterminal();   // Computes the voltage at the bus being regulated
			for (i = 0; i < getNPhases(); i++) {
				switch (TransformerConnection) {
				case 0:  // Wye
					VBuffer[i] = Vterminal[i];
					break;
				case 1:  // Delta
					// Get next phase in sequence using Transformer Obj rotate
					ii = ControlledTransformer.rotatePhases(i);
					VBuffer[i] = Vterminal[i].subtract( Vterminal[ii] );
					break;
				}
			}
		} else {
			ControlledTransformer.getWindingVoltages(ElementTerminal, VBuffer);
		}

		Vcontrol = getControlVoltage(VBuffer, getNPhases(), PTRatio);

		// Check Vlimit
		if (VLimitActive) {

			if (UsingRegulatedBus) {

				ControlledTransformer.getWindingVoltages(ElementTerminal,
						VBuffer);
				VlocalBus = VBuffer[1].divide( PTRatio ).abs();
			} else {
				VlocalBus = Vcontrol.abs();
			}
		} else {
			VlocalBus = 0.0;  // to get rid of warning message;
		}

		// Check for LDC
		if ((!UsingRegulatedBus) && LDCActive) {

			getControlledElement().getCurrents(CBuffer);
			ILDC  = CBuffer[getControlledElement().getNConds() * (ElementTerminal-1) + ControlledPhase].divide(CTRating);
			if (InReverseMode) {
				VLDC  = new Complex(revR, revX).multiply(ILDC);
			} else {
				VLDC  = new Complex(R, X).multiply(ILDC);
			}
			Vcontrol = Vcontrol.add(VLDC);   // Direction on ILDC is INTO terminal, so this is equivalent to Vterm - (R+jX)*ILDC
		}

		Vactual = Vcontrol.abs();

		// Check for out of band voltage
		if (Math.abs(Vreg - Vactual) > Bandwidth / 2.0) {
			TapChangeIsNeeded = true;
		} else {
			TapChangeIsNeeded = false;
		}

		if (VLimitActive)
			if (VlocalBus > Vlimit)
				TapChangeIsNeeded = true;

		if (TapChangeIsNeeded) {

			// Compute tapchange
			Vboost = (Vreg - Vactual);
			if (VLimitActive)
				if (VlocalBus > Vlimit)
					Vboost = (Vlimit - VlocalBus);
			BoostNeeded = Vboost * PTRatio / ControlledTransformer.getBaseVoltage(ElementTerminal);  // per unit Winding boost needed
			Increment = ControlledTransformer.getTapIncrement(TapWinding);
			setPendingTapChange( Math.round(BoostNeeded / Increment) * Increment );  // Make sure it is an even increment

			/* If Tap is another winding or in reverse mode, it has to move
			 * the other way to accomplish the change */
			if ((TapWinding != ElementTerminal) || InReverseMode)
				setPendingTapChange(-PendingTapChange);

            // Send Initial Tap Change message to control queue
            // Add Delay time to solution control queue
			if ((PendingTapChange != 0.0) && (!Armed)) {

				// Now see if any tap change is possible in desired direction  Else ignore
				if (PendingTapChange > 0.0) {

					if (ControlledTransformer.getPresentTap(TapWinding) < ControlledTransformer.getMaxTap(TapWinding)) {
						ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
								ckt.getSolution().getDynaVars().t + computeTimeDelay(Vactual),
								RegControl.ACTION_TAPCHANGE, 0, this);
						Armed = true;  // Armed to change taps
					}
				} else {
					if (ControlledTransformer.getPresentTap(TapWinding) > ControlledTransformer.getMinTap(TapWinding)) {
						ckt.getControlQueue().push(ckt.getSolution().getIntHour(),
								ckt.getSolution().getDynaVars().t + computeTimeDelay(Vactual),
								RegControl.ACTION_TAPCHANGE, 0, this);
						Armed = true;  // Armed to change taps
					}
				}
			}
		}  // if TapChangeIsNeeded
		else {
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
		return TapWinding;
	}

	public double getMinTap() {
		return getTransformer().getMinTap(TapWinding);
	}

	public double getMaxTap() {
		return getTransformer().getMaxTap(TapWinding);
	}

	public double getTapIncrement() {
		return getTransformer().getTapIncrement(TapWinding);
	}

	public int getNumTaps() {
		return getTransformer().getNumTaps(TapWinding);
	}

	private void regWriteDebugRecord(String S) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		// write a general debug string
		try {
			if (!Globals.isInShowResults()) {
				FileWriter TraceStream = new FileWriter(Tracefile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
				TraceBuffer.write(S);
				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
			}
		} catch (Exception e) {

		}
	}

	private void regWriteTraceRecord(double TapChangeMade) {
		String Separator = ", ";

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			if (!Globals.isInShowResults()) {
				FileWriter TraceStream = new FileWriter(Tracefile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);

				TransformerObj pElem = (TransformerObj) getControlledElement();

				TraceBuffer.write(
						ckt.getSolution().getIntHour() + Separator +
						ckt.getSolution().getDynaVars().t + Separator +
						ckt.getSolution().getControlIteration() + Separator +
						ckt.getSolution().getIteration() + Separator +
						ckt.getLoadMultiplier() + Separator +
						pElem.getPresentTap(ElementTerminal) + Separator +
						PendingTapChange + Separator +
						TapChangeMade + Separator +
						pElem.getTapIncrement(ElementTerminal) + Separator +
						pElem.getMinTap(ElementTerminal) + Separator +
						pElem.getMaxTap(ElementTerminal));
				TraceBuffer.newLine();

				TraceBuffer.close();
				TraceStream.close();
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
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = ""; //"element";
		PropertyValue[1] = "1"; //"terminal";
		PropertyValue[2] = "120";
		PropertyValue[3] = "3";
		PropertyValue[4] = "60";
		PropertyValue[5] = "300";
		PropertyValue[6] = "0";
		PropertyValue[7] = "0";
		PropertyValue[8] = "";
		PropertyValue[9] = "15";
		PropertyValue[10] = "no";
		PropertyValue[11] = "120";
		PropertyValue[12] = "3";
		PropertyValue[13] = "0";
		PropertyValue[14] = "0";
		PropertyValue[15] = "2";
		PropertyValue[16] = "no";
		PropertyValue[17] = "16";
		PropertyValue[18] = "no";
		PropertyValue[19] = "1";
		PropertyValue[20] = "0.0";
		PropertyValue[21] = "1";
		PropertyValue[22] = "100";
		PropertyValue[23] = "60";
		PropertyValue[24] = "No";

		super.initPropertyValues(RegControl.NumPropsThisClass);
	}

	public void setPendingTapChange(double Value) {
		PendingTapChange = Value;
		setDblTraceParameter(Value);
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (getControlledElement() != null) {
			setEnabled( getControlledElement().isEnabled() );
			if (UsingRegulatedBus) {
				setNPhases(1);
			} else {
				setNPhases( getControlledElement().getNPhases() );
			}
			setNConds(nPhases);
			if (getControlledElement().getDSSClassName().equalsIgnoreCase("transformer")) {
				// Sets name of i-th terminal's connected bus in RegControl's buslist
				// This value will be used to set the NodeRef array (see Sample function)
				if (UsingRegulatedBus) {
					setBus(1, RegulatedBus);  // hopefully this will actually exist
				} else {
					setBus(1, getControlledElement().getBus(ElementTerminal));
					// buffer to hold regulator voltages
					VBuffer = (Complex[]) Utilities.resizeArray(VBuffer, getControlledElement().getNPhases());
					CBuffer = (Complex[]) Utilities.resizeArray(CBuffer, getControlledElement().getYorder());
				}
			}
		}
		super.makePosSequence();
	}

	private double computeTimeDelay(double Vavg) {
		if (Inversetime) {
			return TimeDelay / Math.min(10.0, (2.0 * Math.abs(Vreg - Vavg) / Bandwidth));
		} else {
			return TimeDelay;
		}
	}

	public double getPendingTapChange() {
		return PendingTapChange;
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

	public double getkWRevPowerThreshold() {
		return kWRevPowerThreshold;
	}

	public void setkWRevPowerThreshold(double kWRevPowerThreshold) {
		this.kWRevPowerThreshold = kWRevPowerThreshold;
	}

	public void setRevDelay(double revDelay) {
		this.revDelay = revDelay;
	}

	public void setReverseNeutral(boolean reverseNeutral) {
		ReverseNeutral = reverseNeutral;
	}

	public void setRevPowerThreshold(double revPowerThreshold) {
		RevPowerThreshold = revPowerThreshold;
	}

	public double getRevPowerThreshold() {
		return RevPowerThreshold;
	}

	public double getRevDelay() {
		return revDelay;
	}

	public boolean isReverseNeutral() {
		return ReverseNeutral;
	}

}
