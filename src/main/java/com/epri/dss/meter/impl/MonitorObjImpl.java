package com.epri.dss.meter.impl;

import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.meter.Monitor;
import com.epri.dss.meter.MonitorObj;

public class MonitorObjImpl extends MeterElementImpl implements MonitorObj {

	private static StringBuffer StrBuffer = new StringBuffer();

	private int BufferSize;
	private int Hour;
	/* last time entered in the buffer */
	private double Sec;
	private float[] MonBuffer;
	/* point to present (last) element in buffer must be incremented to add */
	private int BufPtr;

	private Complex[] CurrentBuffer;
	private Complex[] VoltageBuffer;

	private int NumStateVars;
	private double[] StateBuffer;

	private boolean IncludeResidual;
	private boolean VIpolar;
	private boolean Ppolar;

	private int FileSignature;
	private int FileVersion;

	//private double BaseFrequency;

	/* Name of file for catching buffer overflow */
	private String BufferFile;

	private boolean IsFileOpen;
	private boolean ValidMonitor;

	protected int Mode;
	protected CharArrayWriter MonitorStream;
	protected int SampleCount;  // This is the number of samples taken

	public MonitorObjImpl(DSSClassImpl ParClass, String MonitorName) {
		super(ParClass);
		setName(MonitorName.toLowerCase());

		setNPhases(3);  // directly set conds and phases
		setNConds(3);
		setNTerms(1);   // this forces allocation of terminals and conductors in base class

		/* Current Buffer has to be big enough to hold all terminals */
		this.CurrentBuffer = null;
		this.VoltageBuffer = null;
		this.StateBuffer   = null;

		this.BaseFrequency = 60.0;
		this.Hour          = 0;
		this.Sec           = 0.0;

		this.Mode = 0;  // Standard Mode: V & I, complex values

		this.BufferSize = 1024;       // Makes a 4K buffer
		this.MonBuffer  = new float[BufferSize];
		this.BufPtr     = 0;

		// Default to first circuit element (source)
		this.ElementName    = ((CktElement) DSSGlobals.getInstance().getActiveCircuit().getCktElements().get(0)).getName();
		this.MeteredElement = null;
		this.BufferFile     = "";

		this.MonitorStream = new CharArrayWriter();  // Create memory stream

		this.IsFileOpen      = false;
		this.MeteredTerminal = 1;
		this.IncludeResidual = false;
		this.VIpolar         = true;
		this.Ppolar          = true;
		this.FileSignature   = 43756;
		this.FileVersion     = 1;
		this.SampleCount     = 0;

		this.DSSObjType = ParClass.getDSSClassType();  // MON_ELEMENT;

		initPropertyValues(0);
	}

	/**
	 * Convert spaces to underscores.
	 */
	private void convertBlanks(String S) {
		S.replace(' ', '_');
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		ValidMonitor = false;
		int DevIndex = Utilities.getCktElementIndex(ElementName);  // Global function
		if (DevIndex >= 0) {  // Monitored element must already exist
			MeteredElement = (CktElement) Globals.getActiveCircuit().getCktElements().get(DevIndex);
			switch (Mode & Monitor.MODEMASK) {
			case 2:  // Must be transformer
				if ((MeteredElement.getDSSObjType() & DSSClassDefs.CLASSMASK) != DSSClassDefs.XFMR_ELEMENT) {
					Globals.doSimpleMsg(MeteredElement.getName() + " is not a transformer!", 663);
					return;
				}
				break;
			case 3:  // Must be PCElement
				if ((MeteredElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PC_ELEMENT) {
					Globals.doSimpleMsg(MeteredElement.getName() + " must be a power conversion element (Load or Generator)!", 664);
					return;
				}
				break;
			}

			if (MeteredTerminal > MeteredElement.getNTerms()) {
				Globals.doErrorMsg("Monitor: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Respecify terminal no.", 665);
			} else {
				setNPhases( MeteredElement.getNPhases() );
				setNConds( MeteredElement.getNConds() );

				// Sets name of i-th terminal's connected bus in monitor's buslist
				// This value will be used to set the NodeRef array (see TakeSample)
				setBus(1, MeteredElement.getBus(MeteredTerminal));
				// Make a name for the Buffer File
				BufferFile = /*ActiveCircuit.CurrentDirectory + */Globals.getCircuitName_() + "Mon_" + getName() + ".mon";
				// removed 10/19/99 ConvertBlanks(BufferFile); // turn blanks into '_'

				/* Allocate Buffers */

				switch (Mode & Monitor.MODEMASK) {
				case 3:
					NumStateVars = ((PCElement) MeteredElement).numVariables();
					StateBuffer = (double[]) Utilities.resizeArray(StateBuffer, NumStateVars);
					break;
				default:
					CurrentBuffer = (Complex[]) Utilities.resizeArray(CurrentBuffer, MeteredElement.getYorder());
					VoltageBuffer = (Complex[]) Utilities.resizeArray(VoltageBuffer, MeteredElement.getNConds());
					break;
				}

				clearMonitorStream();

				ValidMonitor = true;
			}
		} else {
			MeteredElement = null;  // element not found
			Globals.doErrorMsg("Monitor: \"" + getName() + "\"", "Circuit Element \""+ ElementName + "\" Not Found.",
					" Element must be defined previously.", 666);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		if (MeteredElement != null) {
			setBus(1, MeteredElement.getBus(MeteredTerminal));
			setNPhases( MeteredElement.getNPhases() );
			setNConds( MeteredElement.getNConds() );
			switch (Mode & Monitor.MODEMASK) {
			case 3:
				NumStateVars = ((PCElement) MeteredElement).numVariables();
				StateBuffer = (double[]) Utilities.resizeArray(StateBuffer, NumStateVars);
				break;
			default:
				CurrentBuffer = (Complex[]) Utilities.resizeArray(CurrentBuffer, MeteredElement.getYorder());
				VoltageBuffer = (Complex[]) Utilities.resizeArray(VoltageBuffer, MeteredElement.getNConds());
				break;
			}
			clearMonitorStream();
			ValidMonitor = true;
		}
		super.makePosSequence();
	}

	@Override
	public void calcYPrim() {
		/* A Monitor is a zero current source; Yprim is always zero. */
	}

	public void clearMonitorStream() {
		int i;
		int iMax;
		int iMin;
		boolean IsPosSeq;
		boolean IsPower;
		String NameOfState;
		int NumVI;
//		int RecordSize;
//		int strPtr;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
//			if (!IsFileOpen) openMonitorFile();  // always opens for appending
//			buffer.seek(0);  // back to BOF
//			buffer.truncate();  // Get rid of anything remaining


			MonitorStream.reset();
			SampleCount = 0;
			IsPosSeq = false;
			StrBuffer.delete(0, StrBuffer.length());  /* clear buffer */
//			strPtr = 0;  // Init string
			if (Globals.getActiveCircuit().getSolution().isIsHarmonicModel()) {
				StrBuffer.append("Freq, Harmonic, ");
			} else {
				StrBuffer.append("hour, t(sec), ");
			}

			switch (Mode & Monitor.MODEMASK) {
			case 2:
//				RecordSize = 1;  // Transformer Taps
				StrBuffer.append("Tap (pu)");
				break;
			case 3:
//				RecordSize = NumStateVars;   // State variables
				for (i = 0; i < NumStateVars; i++) {
					NameOfState = String.valueOf( ((PCElement) MeteredElement).getVariable(i) );
					StrBuffer.append(NameOfState);
					if (i < NumStateVars)
						StrBuffer.append(", ");
				}
				break;
			default:
				/* Compute recordSize */
				// use same logic as in takeSample method

				if (((Mode & Monitor.SEQUENCEMASK) > 0) && (nPhases == 3)) {  // convert to symmetrical components
					IsPosSeq = true;
					NumVI = 3;
				} else {
					NumVI = nConds;
				}
				// Convert Voltage Buffer to power kW, kvar
				if ((Mode & Monitor.MODEMASK) == 1) {
					IsPower = true;
				} else {
					IsPower = false;
				}

				switch (Mode & (Monitor.MAGNITUDEMASK + Monitor.POSSEQONLYMASK)) {
				case 32:  // Save magnitudes only
//					RecordSize = 0;
//					for (i = 0; i < NumVI; i++) RecordSize += 1;
					if (!IsPower) {
//						for (i = 0; i < NumVI; i++) RecordSize += 1;
//						if (IncludeResidual) RecordSize += 2;
						for (i = 0; i < NumVI; i++) {
							StrBuffer.append(String.format("|V|%d (volts)", i));
							StrBuffer.append(", ");
						}
						if (IncludeResidual) {
							StrBuffer.append("|VN| (volts)");
							StrBuffer.append(", ");
						}
						for (i = 0; i < NumVI; i++) {
							StrBuffer.append("|I|"+String.valueOf(i)+" (amps)");
							if (i < NumVI)
								StrBuffer.append(", ");
						}
						if (IncludeResidual)
							StrBuffer.append(",|IN| (volts)");
					} else {
						for (i = 0; i < NumVI; i++) {
							StrBuffer.append("S"+String.valueOf(i)+" (kVA)");
							if (i < NumVI)
								StrBuffer.append(", ");
						}
					}
					break;
				case 64:  // Save Pos Seq or Total of all Phases or Total power (Complex)
//						RecordSize = 2;
					if (!IsPower) {
//							RecordSize = RecordSize + 2;
						if (VIpolar) {
							StrBuffer.append("V1, V1ang, I1, I1ang");
						} else {
							StrBuffer.append("V1.re, V1.im, I1.re, I1.im");
						}
					} else {
						if (Ppolar) {
							StrBuffer.append("S1 (kVA), Ang ");
						} else {
							StrBuffer.append("P1 (kW), Q1 (kvar)");
						}
					}
					break;
				case 96:  // Save Pos Seq or Aver magnitude of all Phases of total kVA (Magnitude)
//						RecordSize = 1;
					if (!IsPower) {
//							RecordSize = RecordSize + 1;
						StrBuffer.append("V, I ");
					} else {
						if (Ppolar) {
							StrBuffer.append("S1 (kVA)");
						} else {
							StrBuffer.append("P1 (kW)");
						}
					}
					break;

				default:  // save  V and I in mag and angle or complex kW, kvar
//						RecordSize = NumVI * 2;
					if (!IsPower) {
						if (IsPosSeq) {
							iMin = 0;
							iMax = NumVI - 1;
						} else {
							iMin = 1;
							iMax = NumVI;
						}
//							RecordSize = RecordSize + NumVI * 2;
//							if (IncludeResidual) RecordSize += 4;
						for (i = iMin; i < iMax; i++) {
							if (VIpolar) {
								StrBuffer.append("V"+String.valueOf(i)+", VAngle"+String.valueOf(i));
							} else {
								StrBuffer.append("V"+String.valueOf(i)+".re, V"+String.valueOf(i)+".im");
							}
							StrBuffer.append(", ");
						}
						if (IncludeResidual) {
							if (VIpolar) {
								StrBuffer.append("VN, VNAngle");
							} else {
								StrBuffer.append("VN.re, VN.im");
							}
							StrBuffer.append(", ");
						}
						for (i = iMin; i < iMax; i++) {
							if (VIpolar) {
								StrBuffer.append("I"+String.valueOf(i)+", IAngle"+String.valueOf(i));
							} else {
								StrBuffer.append("I"+String.valueOf(i)+".re, I"+String.valueOf(i)+".im");
							}
							if (i < NumVI)
								StrBuffer.append(", ");
						}
						if (IncludeResidual) {
							if (VIpolar) {
								StrBuffer.append(", IN, INAngle");
							} else {
								StrBuffer.append(", IN.re, IN.im");
							}
						}
					} else {
						if (IsPosSeq) {
							iMin = 0;
							iMax = NumVI - 1;
						} else {
							iMin = 1;
							iMax = NumVI;
						}
						for (i = iMin; i < iMax; i++) {
							if (Ppolar) {
								StrBuffer.append("S"+String.valueOf(i)+" (kVA), Ang"+String.valueOf(i));
							} else {
								StrBuffer.append("P"+String.valueOf(i)+" (kW), Q"+String.valueOf(i)+" (kvar)");
							}
							if (i < NumVI)
								StrBuffer.append(", ");
						}
						break;
					}
				}
				break;
			}  /* switch */

			// RecordSize is the number of singles in the sample (after the hour and sec)

			// Write Header to Monitor Stream
			// Write ID so we know it is a DSS Monitor file and which version in case we
			// change it down the road

//			MonitorStream.write(FileSignature, Sizeof(FileSignature) );
//			MonitorStream.write(FileVersion,   Sizeof(FileVersion) );
//			MonitorStream.write(RecordSize,    Sizeof(RecordSize) );
//			MonitorStream.write(Mode,          Sizeof(Mode)       );
			MonitorStream.write(StrBuffer.toString().toCharArray());

			/* So the file now looks like:
			 *   FileSignature (4 bytes)    32-bit Integers
			 *   FileVersion   (4)
			 *   RecordSize    (4)
			 *   Mode          (4)
			 *   String        (256)
			 *
			 *   hr   (4)       all singles
			 *   Sec  (4)
			 *   Sample  (4*RecordSize)
			 *   ...
			 */

			//closeMonitorFile();  // Ready now for appending

		} catch (Exception e) {
			Globals.doErrorMsg("Cannot open Monitor file.", e.getMessage(), "Monitor: \"" + getName() + "\"", 670);
		}
	}

	public void openMonitorStream() {
		if (!IsFileOpen) {
//			MonitorStream.seek(-1);    // Positioned at End of Stream
			IsFileOpen = true;
		}
	}

	public void closeMonitorStream() {
		try {
			if (IsFileOpen) {  // only close open files
//				MonitorStream.seek(0);   // just move stream position to the beginning
				IsFileOpen = false;
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg("Cannot open monitor stream.",
					e.getMessage(), "Monitor: \"" + getName() + "\"", 671);
		}
	}

	/**
	 * Saves present buffer to monitor file.
	 */
	public void save() {
		if (!IsFileOpen) openMonitorStream();  // Position to end of stream

		/* Write present monitor buffer to monitor stream */
//		MonitorStream.write(MonBuffer);

		BufPtr = 0; // reset buffer for next
	}

	public void resetIt() {
		BufPtr = 0;
		clearMonitorStream();
	}

	@Override
	public void takeSample() {
		double dHour;
		double dSum;
		int i;
		boolean IsPower;
		boolean IsSequence;
		int NumVI;
		int Offset;
		Complex ResidualCurr = null;
		Complex ResidualVolt = null;
		Complex Sum;
		Complex[] V012 = new Complex[3];
		Complex[] I012 = new Complex[3];

		DSSGlobals Globals = DSSGlobals.getInstance();
		SolutionObj sol = Globals.getActiveCircuit().getSolution();

		if (!(ValidMonitor && isEnabled()))
			return;

		SampleCount += 1;

		Hour = sol.getIntHour();
		Sec =  sol.getDynaVars().t;

		Offset = (MeteredTerminal - 1)  * MeteredElement.getNConds();

		// Save time unless Harmonics mode and then save frequency and harmonic
		if (sol.isIsHarmonicModel()) {
			addDblsToBuffer(sol.getFrequency(), 1);  // put freq in hour slot as a double
			addDblsToBuffer(sol.getHarmonic(), 1);  // stick harmonic in time slot in buffer
		} else {
			dHour = Hour;      // convert to double
			addDblsToBuffer(dHour, 1);  // put hours in buffer as a double
			addDblsToBuffer(Sec, 1);  // stick time in sec in buffer
		}

		switch (Mode & Monitor.MODEMASK) {
		case 0:  // Voltage, current. Powers

			// MeteredElement.GetCurrents(CurrentBuffer);
			// To save some time, call computeITerminal
			MeteredElement.computeIterminal();  // only does calc if needed
			for (i = 0; i < MeteredElement.getYorder(); i++)
				CurrentBuffer[i] = MeteredElement.getIterminal()[i];

			try {
				for (i = 0; i < nConds; i++) {
					// NodeRef is set by the main Circuit object
					// It is the index of the terminal into the system node list
					VoltageBuffer[i] = sol.getNodeV()[NodeRef[i]];
				}
			} catch (Exception e) {
				Globals.doSimpleMsg(e.getMessage() + DSSGlobals.CRLF + "NodeRef is invalid. Try solving a snapshot or direct before solving in a mode that takes a monitor sample.", 672);
			}
			break;

		case 1:

			// MeteredElement.GetCurrents(CurrentBuffer);
			// To save some time, call computeITerminal
			MeteredElement.computeIterminal();  // only does calc if needed
			for (i = 0; i < MeteredElement.getYorder(); i++)
				CurrentBuffer[i] = MeteredElement.getIterminal()[i];

			try {
				for (i = 0; i < nConds; i++) {
					// NodeRef is set by the main Circuit object
					// It is the index of the terminal into the system node list
					VoltageBuffer[i] = sol.getNodeV()[NodeRef[i]];
				}
			} catch (Exception e) {
				Globals.doSimpleMsg(e.getMessage() + DSSGlobals.CRLF + "NodeRef is invalid. Try solving a snapshot or direct before solving in a mode that takes a monitor sample.", 672);
			}
			break;

		case 2:  // Monitor Transformer Tap Position

			addDblToBuffer( ((TransformerObj) MeteredElement).getPresentTap(MeteredTerminal) );
			return;  // Done with this mode now.

		case 3:  // Pick up device state variables
			((PCElement) MeteredElement).getAllVariables(StateBuffer);
			addDblsToBuffer(StateBuffer, NumStateVars);
			return;  // Done with this mode now

		default:
			return;  // Ignore invalid mask
		}


		if (((Mode & Monitor.SEQUENCEMASK) > 0) && (nPhases == 3)) {

			// Convert to Symmetrical components
			MathUtil.phase2SymComp(VoltageBuffer, V012);
			MathUtil.phase2SymComp(CurrentBuffer[Offset + 1], I012);
			NumVI = 3;
			IsSequence = true;
			// Replace voltage and current buffer with sequence quantities
			for (i = 0; i < 3; i++) VoltageBuffer[i]          = V012[i];
			for (i = 0; i < 3; i++) CurrentBuffer[Offset + i] = I012[i];
		} else {
			NumVI      = nConds;
			IsSequence = false;
		}

		IsPower = false;  // Init so compiler won't complain
		switch (Mode & Monitor.MODEMASK) {
		case 0:  // Convert to Mag, Angle   and compute residual if required
			IsPower = false;
			if (IncludeResidual) {
				if (VIpolar) {
					ResidualVolt = Utilities.residualPolar(VoltageBuffer[0], nPhases);
					ResidualCurr = Utilities.residualPolar(CurrentBuffer[Offset + 1], nPhases);  // TODO Check zero based indexing
				} else {
					ResidualVolt = Utilities.residual(VoltageBuffer[0], nPhases);
					ResidualCurr = Utilities.residual(CurrentBuffer[Offset + 1], nPhases);  // TODO Check zero based indexing
				}
			}
			if (VIpolar) {
				Utilities.convertComplexArrayToPolar(VoltageBuffer, NumVI);
				Utilities.convertComplexArrayToPolar(CurrentBuffer, NumVI * MeteredElement.getNTerms());  // get all of current buffer
			}
			break;

		case 1:  // Convert Voltage Buffer to power kW, kvar or Mag/Angle
			MathUtil.calckPowers(VoltageBuffer, VoltageBuffer, CurrentBuffer[Offset + 1], NumVI);
			if (IsSequence || Globals.getActiveCircuit().isPositiveSequence()) Utilities.CmulArray(VoltageBuffer, 3.0, NumVI); // convert to total power
			if (Ppolar) Utilities.convertComplexArrayToPolar(VoltageBuffer, NumVI);
			IsPower = true;
			break;
		}

		// Now check to see what to write to disk
		switch (Mode & (Monitor.MAGNITUDEMASK + Monitor.POSSEQONLYMASK)) {
		case 32:  // Save magnitudes only
			for (i = 0; i < NumVI; i++)
				addDblToBuffer(VoltageBuffer[i].getReal() /*VoltageBuffer[i].abs()*/);
			if (IncludeResidual) addDblToBuffer(ResidualVolt.getReal());
			if (!IsPower) {
				for (i = 0; i < NumVI; i++)
					addDblToBuffer(CurrentBuffer[Offset + i].getReal() /*CurrentBuffer[Offset + i].abs()*/);
				if (IncludeResidual) addDblToBuffer(ResidualCurr.getReal());
			}
			break;

		case 64:  // Save Pos Seq or Avg of all Phases or Total power (Complex)
			if (IsSequence) {
				addDblsToBuffer(VoltageBuffer[1].getReal(), 2);
				if (!IsPower) addDblsToBuffer(CurrentBuffer[Offset + 2].getReal(), 2);
			} else {
				if (!IsPower) {
					Sum = Complex.ZERO;
					for (i = 0; i < nPhases; i++)
						Sum = Sum.add( VoltageBuffer[i] );
					addDblsToBuffer(Sum.getReal(), 2);
				} else {
					// Average the phase magnitudes and  sum angles
					Sum = Complex.ZERO;
					for (i = 0; i < nPhases; i++)
						Sum = Sum.add( VoltageBuffer[i] );
					Sum = new Complex(Sum.getReal() / nPhases, Sum.getImaginary());
					addDblsToBuffer(Sum.getReal(), 2);
					Sum = Complex.ZERO;
					for (i = 0; i < nPhases; i++)
						Sum = Sum.add( CurrentBuffer[i] );
					Sum = new Complex(Sum.getReal() / nPhases, Sum.getImaginary());
					addDblsToBuffer(Sum.getReal(), 2);
				}
			}
			break;

		case 96:  // Save Pos Seq or Aver magnitude of all Phases of total kVA (Magnitude)
			if (IsSequence) {
				addDblToBuffer(VoltageBuffer[1].getReal());    // First double is magnitude
				if (!IsPower) addDblToBuffer(CurrentBuffer[Offset + 2].getReal());
			} else {
				dSum = 0.0;
				for (i = 0; i < nPhases; i++)
					dSum = dSum + VoltageBuffer[i].getReal();  //VoltageBuffer[i].abs();
				if (!IsPower) dSum = dSum / nPhases;
				addDblToBuffer(dSum);
				if (!IsPower) {
					dSum = 0.0;
					for (i = 0; i < nPhases; i++)
						dSum = dSum + CurrentBuffer[Offset + i].getReal(); // CurrentBuffer[Offset+i].abs();
					dSum = dSum / nPhases;
					addDblToBuffer(dSum);
				}
			}
			break;

		default:
			addDblsToBuffer(VoltageBuffer[1].getReal(), NumVI * 2);
			if (!IsPower) {
				if (IncludeResidual) addDblsToBuffer(ResidualVolt.asArray(), 2);
				addDblsToBuffer(CurrentBuffer[Offset + 1].getReal(), NumVI * 2);
				if (IncludeResidual) addDblsToBuffer(ResidualCurr.asArray(), 2);
			}
			break;
		}
	}

	private void addDblsToBuffer(double Dbl, int nDoubles) {
		addDblToBuffer(Dbl);
	}

	private void addDblsToBuffer(double[] Dbl, int nDoubles) {
		for (int i = 0; i < nDoubles; i++)
			addDblToBuffer(Dbl[i]);
	}

	private void addDblToBuffer(double Dbl) {
		// First check to see if there's enough room
		// if not, save to monitorstream first.
		if (BufPtr == BufferSize) save();
		BufPtr += 1;
		MonBuffer[BufPtr] = (float) Dbl;
	}

	public void translateToCSV(boolean Show) {
		String CSVName;
		File F;
		FileWriter FStream;
		BufferedWriter FBuffer;
		int FSignature;
		int Fversion;
		float hr;
		int i;
		int Mode;
		int Nread = 0;
		int pStr;
		int RecordBytes;
		int RecordSize = 0;
		float s = 0;
		float[] sngBuffer = new float[100];

		DSSGlobals Globals = DSSGlobals.getInstance();

		save();  // Save present buffer
		closeMonitorStream();  // Position at beginning

		CSVName = getFileName();

		try {
			F = new File(CSVName);  // Make CSV file
			FStream = new FileWriter(F, false);
			FBuffer = new BufferedWriter(FStream);
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening CSVFile \""+CSVName+"\" for writing" +DSSGlobals.CRLF + e.getMessage(), 672);
			return;
		}

//		MonitorStream.seek(0);  // Start at the beginning of the Stream
//		MonitorStream.read(Fsignature);
//		MonitorStream.read(Fversion);
//		MonitorStream.read(RecordSize);
//		MonitorStream.read(Mode);
//		MonitorStream.read(StrBuffer);

		pStr = StrBuffer.length();
//		FBuffer.write(pStr);
//		FBuffer.newLine();
		RecordBytes = RecordSize;

		try {

//			while (!(MonitorStream.position() >= MonitorStream.size())) {
//				MonitorStream.read(hr);
//				MonitorStream.read(s);
//				Nread = MonitorStream.read(sngBuffer, RecordBytes);
//			}
//			if (Nread < RecordBytes) break;
//			FBuffer.write(hr);  // hours
			FBuffer.write(", " + s);  // sec
			for (i = 0; i < RecordSize; i++)
				FBuffer.write(", " + String.format("%-.6g", sngBuffer[i]));
			FBuffer.newLine();

			closeMonitorStream();
			FBuffer.close();
			FStream.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error Writing CSVFile \""+CSVName+"\" " +DSSGlobals.CRLF + e.getMessage(), 673);
		}

		if (Show) Utilities.fireOffEditor(CSVName);

		Globals.setGlobalResult(CSVName);
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
			F.println();
			F.println("// BufferSize=" + BufferSize);
			F.println("// Hour=" + Hour);
			F.println("// Sec=" + Sec);
			F.println("// BaseFrequency=" + BaseFrequency);
			F.println("// Bufptr=" + BufPtr);
			F.println("// Buffer=");
			int k = 0;
			for (int i = 0; i < BufPtr; i++) {
				F.print(MonBuffer[i] + ", ");
				k += 1;
				if (k == (2 + nConds * 4)) {
					F.println();
					k = 0;
				}
			}
			F.println();
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(0, ""); //"element";
		setPropertyValue(1, "1"); //"terminal";
		setPropertyValue(2, "0"); //"mode";
		setPropertyValue(3, ""); // "action";  // buffer=clear|save
		setPropertyValue(4, "NO");
		setPropertyValue(5, "YES");
		setPropertyValue(6, "YES");

		super.initPropertyValues(Monitor.NumPropsThisClass);
	}

	public void TOPExport(String ObjName) {
		throw new UnsupportedOperationException();
	}

	public String getFileName() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		return Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Mon_" + getName() + ".csv";
	}

	public int getMode() {
		return Mode;
	}

	public void setMode(int mode) {
		Mode = mode;
	}

	public CharArrayWriter getMonitorStream() {
		return MonitorStream;
	}

	public void setMonitorStream(CharArrayWriter monitorStream) {
		MonitorStream = monitorStream;
	}

	public int getSampleCount() {
		return SampleCount;
	}

	public void setSampleCount(int sampleCount) {
		SampleCount = sampleCount;
	}


	// FIXME Private members in OpenDSS

	public int getBufferSize() {
		return BufferSize;
	}

	public void setBufferSize(int bufferSize) {
		BufferSize = bufferSize;
	}

	public int getHour() {
		return Hour;
	}

	public void setHour(int hour) {
		Hour = hour;
	}

	public double getSec() {
		return Sec;
	}

	public void setSec(double sec) {
		Sec = sec;
	}

	public float[] getMonBuffer() {
		return MonBuffer;
	}

	public void setMonBuffer(float[] monBuffer) {
		MonBuffer = monBuffer;
	}

	public int getBufPtr() {
		return BufPtr;
	}

	public void setBufPtr(int bufPtr) {
		BufPtr = bufPtr;
	}

	public Complex[] getCurrentBuffer() {
		return CurrentBuffer;
	}

	public void setCurrentBuffer(Complex[] currentBuffer) {
		CurrentBuffer = currentBuffer;
	}

	public Complex[] getVoltageBuffer() {
		return VoltageBuffer;
	}

	public void setVoltageBuffer(Complex[] voltageBuffer) {
		VoltageBuffer = voltageBuffer;
	}

	public int getNumStateVars() {
		return NumStateVars;
	}

	public void setNumStateVars(int numStateVars) {
		NumStateVars = numStateVars;
	}

	public double[] getStateBuffer() {
		return StateBuffer;
	}

	public void setStateBuffer(double[] stateBuffer) {
		StateBuffer = stateBuffer;
	}

	public boolean isIncludeResidual() {
		return IncludeResidual;
	}

	public void setIncludeResidual(boolean includeResidual) {
		IncludeResidual = includeResidual;
	}

	public boolean isVIpolar() {
		return VIpolar;
	}

	public void setVIpolar(boolean vIpolar) {
		VIpolar = vIpolar;
	}

	public boolean isPpolar() {
		return Ppolar;
	}

	public void setPpolar(boolean ppolar) {
		Ppolar = ppolar;
	}

	public int getFileSignature() {
		return FileSignature;
	}

	public void setFileSignature(int fileSignature) {
		FileSignature = fileSignature;
	}

	public int getFileVersion() {
		return FileVersion;
	}

	public void setFileVersion(int fileVersion) {
		FileVersion = fileVersion;
	}

	public String getBufferFile() {
		return BufferFile;
	}

	public void setBufferFile(String bufferFile) {
		BufferFile = bufferFile;
	}

	public boolean isIsFileOpen() {
		return IsFileOpen;
	}

	public void setIsFileOpen(boolean isFileOpen) {
		IsFileOpen = isFileOpen;
	}

	public boolean isValidMonitor() {
		return ValidMonitor;
	}

	public void setValidMonitor(boolean validMonitor) {
		ValidMonitor = validMonitor;
	}

}
