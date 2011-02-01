package com.epri.dss.meter.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.meter.MonitorObj;

public class MonitorObjImpl extends MeterElementImpl implements MonitorObj {
	
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

	public MonitorObjImpl(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
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
	
	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}

	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
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

}
