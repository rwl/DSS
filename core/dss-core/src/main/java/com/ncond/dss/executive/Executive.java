package com.ncond.dss.executive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Executive {

	private boolean recorderOn;
	private String recorderFile;
	private FileWriter recorderFileWriter;

	/**
	 * Private constructor prevents instantiation from other classes
	 */
	private Executive() {
		super();

		ExecCommands execCmd = ExecCommands.getInstance();
		execCmd.setCommandList(new CommandList(execCmd.getExecCommand()));

		ExecOptions execOpts = ExecOptions.getInstance();
		execOpts.setOptionList(execOpts.getOptionList());

		/* Instantiate all DSS class definitions, intrinsic and user-defined */
		DSSClassDefs.createDSSClasses();

		// default buffer for 2 active circuits
		DSS.circuits = new ArrayList<Circuit>(2);
		DSS.numCircuits = 0;
		DSS.activeCircuit = null;

		Parser.getInstance();  // create global parser object

		ExecCommands.getInstance().setLastCmdLine("");
		ExecCommands.getInstance().setRedirFile("");

		setRecorderOn(false);
		this.recorderFile = "";

		//DSSGlobals.getInstance().readDSS_Registry();

		/* FIXME: Override Locale defaults so that CSV files get written properly */
//		DSSGlobals.decimalSeparator = ".";
//		DSSGlobals.thousandSeparator = ",";
	}

	private static class DSSExecutiveHolder {
		public static final Executive INSTANCE = new Executive();
	}

	public static Executive getInstance() {
		return DSSExecutiveHolder.INSTANCE;
	}

	@Override
	protected void finalize() throws Throwable {
		DSS.writeDSS_Registry();

		DSS.clearAllCircuits();

		ExecCommands.getInstance().setCommandList(null);
		ExecOptions.getInstance().setOptionList(null);
		DSS.circuits = null;

		Parser.getInstance();

		DSSClassDefs.disposeDSSClasses();
	}

	public String getLastError() {
		return DSS.lastErrorMessage;
	}

	public int getErrorResult() {
		return DSS.errorNumber;
	}

	/**
	 * Create default loadshapes, growthshapes, and other general DSS objects
	 * used by all circuits.
	 */
	public void createDefaultDSSItems() {
		/* this load shape used for generator dispatching, etc. loads may refer to it, also. */
		setCommand("new loadshape.default npts=24 1.0 mult=(.677 .6256 .6087 .5833 .58028 .6025 .657 .7477 .832 .88 .94 .989 .985 .98 .9898 .999 1 .958 .936 .913 .876 .876 .828 .756)");
		if (DSS.cmdResult == 0) {
			setCommand("new growthshape.default 2 year=\"1 20\" mult=(1.025 1.025)");  // 20 years at 2.5%
			setCommand("new spectrum.default 7 harmonic=(1 3 5 7 9 11 13) %mag=(100 33 20 14 11 9 7) angle=(0 0 0 0 0 0 0)");
			setCommand("new spectrum.defaultload 7 harmonic=(1 3 5 7 9 11 13) %mag=(100 1.5 20 14 1 9 7) angle=(0 180 180 180 180 180 180)");
			setCommand("new spectrum.defaultgen 7 harmonic=(1 3 5 7 9 11 13) %mag=(100 5 3 1.5 1 .7 .5) angle=(0 0 0 0 0 0 0)");
			setCommand("new spectrum.defaultvsource 1 harmonic=(1 ) %mag=(100 ) angle=(0 )");
			setCommand("new spectrum.linear 1 harmonic=(1 ) %mag=(100 ) angle=(0 )");
			setCommand("new spectrum.pwm6 13 harmonic=(1 3 5 7 9 11 13 15 17 19 21 23 25) %mag=(100 4.4 76.5 62.7 2.9 24.8 12.7 0.5 7.1 8.4 0.9 4.4 3.3) Angle=(-103 -5 28 -180 -33 -59 79 36 -253 -124 3 -30 86)");
			setCommand("new spectrum.dc6 10 harmonic=(1 3 5 7 9 11 13 15 17 19) %mag=(100 1.2 33.6 1.6 0.4 8.7  1.2  0.3  4.5 1.3) angle=(-75 28 156 29 -91 49 54 148 -57 -46)");
			setCommand("new TCC_Curve.A 5 c_array=(1, 2.5, 4.5, 8.0, 14.) t_array=(0.15 0.07 .05 .045 .045) ");
			setCommand("new TCC_Curve.D 5 c_array=(1, 2.5, 4.5, 8.0, 14.) t_array=(6 0.7 .2 .06 .02)");
			setCommand("new TCC_Curve.TLink 7 c_array=(2 2.1 3 4 6 22 50) t_array=(300 100 10.1 4.0 1.4 0.1 0.02)");
			setCommand("new TCC_Curve.KLink 6 c_array=(2 2.2 3 4 6 30) t_array=(300 20 4 1.3 0.41 0.02)");
		}
	}

	public String getCommand() {
		return ExecCommands.getInstance().getLastCmdLine();
	}

	public void setCommand(String value) {
		ExecCommands.getInstance().processCommand(value);
	}

	public void clear() {
		if (DSS.numCircuits > 0) {
			/* First get rid of all existing stuff */
			DSS.clearAllCircuits();
			DSSClassDefs.disposeDSSClasses();

			/* Start over */
			DSSClassDefs.createDSSClasses();
			createDefaultDSSItems();
			DSS.forms.setRebuildHelpForm(true);  // because class strings have changed
		}
	}

	public void setRecorderOn(boolean value) {
		try {
			if (value) {
				if (!recorderOn) {
					recorderFile = DSS.dataDirectory + "DSSRecorder.dss";
					recorderFileWriter = new FileWriter(recorderFile);
				}
			} else if (recorderOn) {
				recorderFileWriter.close();
			}
		} catch (IOException e) {
			DSS.doErrorMsg("setRecorderOn", e.getMessage(),
					"Lack of write access", 678);
		}
		DSS.globalResult = recorderFile;
		recorderOn = value;
	}

	public boolean isRecorderOn() {
		return recorderOn;
	}

	public void writeToRecorderFile(String s) {
		new PrintWriter(recorderFileWriter).println(s);
	}

}
