package com.epri.dss.meter.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.meter.Monitor;
import com.epri.dss.meter.MonitorObj;

public class MonitorObjImpl extends MeterElementImpl implements MonitorObj {

	private static char[] StrBuffer = new char[256];

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
//	protected MemoryStream MonitorStream;
	protected int SampleCount;  // This is the number of samples taken

	public MonitorObjImpl(DSSClassImpl ParClass, String MonitorName) {
		super(ParClass);
		setName(MonitorName.toLowerCase());

		this.nPhases = 3;  // Directly set conds and phases
		this.nConds  = 3;
		this.nTerms  = 1;  // this forces allocation of terminals and conductors in base class

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

//		this.MonitorStream = new MemoryStream();  // Create memory stream

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
			case 3:  // Must be PCElement
				if ((MeteredElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PC_ELEMENT) {
					Globals.doSimpleMsg(MeteredElement.getName() + " must be a power conversion element (Load or Generator)!", 664);
					return;
				}
			}

			if (MeteredTerminal > MeteredElement.getNTerms()) {
				Globals.doErrorMsg("Monitor: \"" + getName() + "\"",
						"Terminal no. \"" +"\" does not exist.",
						"Respecify terminal no.", 665);
			} else {
				nPhases = MeteredElement.getNPhases();
				nConds  = MeteredElement.getNConds();

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
				default:
					CurrentBuffer = (Complex[]) Utilities.resizeArray(CurrentBuffer, MeteredElement.getYorder());
					VoltageBuffer = (Complex[]) Utilities.resizeArray(VoltageBuffer, MeteredElement.getNConds());
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
			nPhases = MeteredElement.getNPhases();
			nConds  = MeteredElement.getNConds();
			switch (Mode & Monitor.MODEMASK) {
			case 3:
				NumStateVars = ((PCElement) MeteredElement).numVariables();
				StateBuffer = (double[]) Utilities.resizeArray(StateBuffer, NumStateVars);
			default:
				CurrentBuffer = (Complex[]) Utilities.resizeArray(CurrentBuffer, MeteredElement.getYorder());
				VoltageBuffer = (Complex[]) Utilities.resizeArray(VoltageBuffer, MeteredElement.getNConds());
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

	@Override
	public void takeSample() {

	}

	public void resetIt() {

	}

	/* Saves present buffer to file */
	public void save() {

	}

	public void openMonitorStream() {

	}

	public void clearMonitorStream() {

	}

	public void closeMonitorStream() {

	}

	public void translateToCSV(boolean Show) {

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

	private void addDblsToBuffer(double[] Dbl, int nDoubles) {

	}

	private void addDblToBuffer(double Dbl) {

	}

	public String getCSVFileName() {
		return null;
	}

	public int getMode() {
		return Mode;
	}

	public void setMode(int mode) {
		Mode = mode;
	}

//	public MemoryStream getMonitorStream() {
//		return MonitorStream;
//	}
//
//	public void setMonitorStream(MemoryStream monitorStream) {
//		MonitorStream = monitorStream;
//	}

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
