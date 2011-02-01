package com.epri.dss.meter.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.meter.SensorObj;

public class SensorObjImpl extends MeterElementImpl implements SensorObj {
	
	private boolean ValidSensor;
	private double[] SensorKW;
	private double[] SensorKVar;
	private double kVBase; // value specified
	private double Vbase; // in volts

	private int Conn;

	private boolean Vspecified, Ispecified, Pspecified, Qspecified;

	private boolean ClearSpecified;
	private int DeltaDirection;
	
	protected double pctError, Weight;

	public SensorObjImpl(DSSClassImpl ParClass, String SensorName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	/* Connection code */
	public void setConn(int Value) {
		
	}
	
	public int getConn() {
		return Conn;
	}
	
	public void setAction(String Value) {
		
	}
	
	private void zeroSensorArrays() {
		
	}
	
	private void allocateSensorObjArrays() {
		
		
	}
	private void recalcVbase() {
		
	}
	
	private int rotatePhases(int j) {
		return 0;
	}
	
	private int limitToPlusMinusOne(int i) {
		return 0;
	}
	
	private void clearSensor() {
		
	}
	
	public double getWLSCurrentError() {
		return 0.0;
	}
	
	public double getWLSVoltageError() {
		return 0.0;
	}

	public double[] getSensorKW() {
		return SensorKW;
	}

	public double[] getSensorKVar() {
		return SensorKVar;
	}

	public double getkVBase() {
		return kVBase;
	}

	public int getDeltaDirection() {
		return DeltaDirection;
	}

	public double getPctError() {
		return pctError;
	}

	public void setPctError(double pctError) {
		this.pctError = pctError;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
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
