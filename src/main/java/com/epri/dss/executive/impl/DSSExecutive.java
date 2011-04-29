package com.epri.dss.executive.impl;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.executive.IExecCommands;
import com.epri.dss.executive.ExecOptions;
import com.epri.dss.executive.Executive;
import com.epri.dss.shared.impl.CommandListImpl;

public class DSSExecutive implements Executive {
	
	private static Executive DSSExecutive;

	private boolean RecorderOn;
	private String RecorderFile;

	public DSSExecutive() {
		super();
		
		IExecCommands.CommandList = null;
		
		ExecOptions.OptionList = null;
		
		/* Instantiate All DSS Classe Definitions, Intrinsic and User-defined */
		DSSClassDefs.createDSSClasses();
		
		DSSGlobals.getInstance().setCircuits(new ArrayList<Circuit>(2));  // default buffer for 2 active circuits
		DSSGlobals.getInstance().setNumCircuits(0);
		DSSGlobals.getInstance().setActiveCircuit(null);
		
		Parser.INSTANCE = new Parser();  // Create global parser object
		
		IExecCommands.LastCmdLine = "";
		IExecCommands.RedirFile = "";
		
		this.RecorderOn = false;
		this.RecorderFile = "";
		
		DSSGlobals.getInstance().readDSS_Registry();
	}

	protected void finalize() throws Throwable {
		DSSGlobals.getInstance().writeDSS_Registry();
		
		DSSGlobals.getInstance().clearAllCircuits();
		
		IExecCommands.CommandList = null;
		ExecOptions.OptionList = null;
		DSSGlobals.getInstance().setCircuits(null);
		
		Parser.INSTANCE = null;
		
		DSSClassDefs.disposeDSSClasses();
	}

	public String getLastError() {
		return DSSGlobals.getInstance().getLastErrorMessage();
	}

	public int getErrorResult() {
		return DSSGlobals.getInstance().getErrorNumber();
	}

	/**
	 * Create default loadshapes, growthshapes, and other general DSS objects
	 * used by all circuits.
	 */
	public void createDefaultDSSItems() {
		/* this load shape used for generator dispatching, etc.   Loads may refer to it, also. */
		setCommand("new loadshape.default npts=24 1.0 mult=(.677 .6256 .6087 .5833 .58028 .6025 .657 .7477 .832 .88 .94 .989 .985 .98 .9898 .999 1 .958 .936 .913 .876 .876 .828 .756)");
		if (DSSGlobals.getInstance().getCmdResult() == 0) {
			setCommand("new growthshape.default 2 year=\"1 20\" mult=(1.025 1.025)");  // 20 years at 2.5%
			setCommand("new spectrum.default 7  Harmonic=(1 3 5 7 9 11 13)  %mag=(100 33 20 14 11 9 7) Angle=(0 0 0 0 0 0 0)");
			setCommand("new spectrum.defaultload 7  Harmonic=(1 3 5 7 9 11 13)  %mag=(100 1.5 20 14 1 9 7) Angle=(0 180 180 180 180 180 180)");
			setCommand("new spectrum.defaultgen 7  Harmonic=(1 3 5 7 9 11 13)  %mag=(100 5 3 1.5 1 .7 .5) Angle=(0 0 0 0 0 0 0)");
			setCommand("new spectrum.defaultvsource 1  Harmonic=(1 )  %mag=(100 ) Angle=(0 ) ");
			setCommand("new spectrum.linear 1  Harmonic=(1 )  %mag=(100 ) Angle=(0 ) ");
			setCommand("new spectrum.pwm6 13  Harmonic=(1 3 5 7 9 11 13 15 17 19 21 23 25) %mag=(100 4.4 76.5 62.7 2.9 24.8 12.7 0.5 7.1 8.4 0.9 4.4 3.3) Angle=(-103 -5 28 -180 -33 -59 79 36 -253 -124 3 -30 86)");
			setCommand("new spectrum.dc6 10  Harmonic=(1 3 5 7 9 11 13 15 17 19)  %mag=(100 1.2 33.6 1.6 0.4 8.7  1.2  0.3  4.5 1.3) Angle=(-75 28 156 29 -91 49 54 148 -57 -46)");
			setCommand("New TCC_Curve.A 5 c_array=(1, 2.5, 4.5, 8.0, 14.)  t_array=(0.15 0.07 .05 .045 .045) ");
			setCommand("New TCC_Curve.D 5 c_array=(1, 2.5, 4.5, 8.0, 14.)  t_array=(6 0.7 .2 .06 .02)");
			setCommand("New TCC_Curve.TLink 7 c_array=(2 2.1 3 4 6 22 50)  t_array=(300 100 10.1 4.0 1.4 0.1  0.02)");
			setCommand("New TCC_Curve.KLink 6 c_array=(2 2.2 3 4 6 30)    t_array=(300 20 4 1.3 0.41 0.02)");
		}
	}

	public String getCommand() {
		return IExecCommands.getLastCmdLine();
	}

	public void setCommand(String Value) {
		IExecCommands.processCommand(Value);
	}

	public void clear() {
		if (DSSGlobals.getInstance().getNumCircuits() > 0) {
			/* First get rid of all existing stuff */
			DSSGlobals.getInstance().clearAllCircuits();
			DSSClassDefs.disposeDSSClasses();

			/* Now, Start over */
			DSSClassDefs.createDSSClasses();
			createDefaultDSSItems();
			DSSForms.rebuildHelpForm(true); // because class strings have changed
		}
	}

	public void setRecorderOn(boolean Value) {
		if (Value) {
			if (!RecorderOn) {
				RecorderFile = DSSGlobals.getInstance().getDSSDataDirectory() + "DSSRecorder.dss";
				File RecorderFile = new File(this.RecorderFile);
			}
		} else if (RecorderOn) {
			RecorderFile.close();
		}
		DSSGlobals.getInstance().setGlobalResult(RecorderFile);
		RecorderOn = Value;
	}

	public boolean isRecorderOn() {
		return RecorderOn;
	}

	public void writeToRecorderFile(String S) {
		RecorderFile.println(S);
	}

	public static void setDSSExecutive(Executive dSSExecutive) {
		DSSExecutive = dSSExecutive;
	}

	public static Executive getDSSExecutive() {
		return DSSExecutive;
	}

}
