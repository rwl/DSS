package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.FaultObj;

public class FaultObjImpl extends PDElementImpl implements FaultObj {
	
	private double MinAmps;
	private boolean IsTemporary, Cleared, Is_ON;
	private double On_Time;
	private double RandomMult;
	
	/* single G per phase (line rating) if Gmatrix not specified */
	protected double G;
	/* If not null then overrides G */
	protected double[] Gmatrix;
	
	/* per unit stddev */
	protected double Stddev;
	protected int SpecType;

	public FaultObjImpl(DSSClass ParClass, String FaultName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private boolean faultStillGoing() {
		return false;
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	public void randomize() {
		
	}
	
	public void checkStatus(int ControlMode) {
		
	}
	
	public void reset() {
		
	}
	
	@Override
	public void makePosSequence() {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	// FIXME Private members in OpenDSS

	public double getMinAmps() {
		return MinAmps;
	}

	public void setMinAmps(double minAmps) {
		MinAmps = minAmps;
	}

	public boolean isIsTemporary() {
		return IsTemporary;
	}

	public void setIsTemporary(boolean isTemporary) {
		IsTemporary = isTemporary;
	}

	public boolean isCleared() {
		return Cleared;
	}

	public void setCleared(boolean cleared) {
		Cleared = cleared;
	}

	public boolean isIs_ON() {
		return Is_ON;
	}

	public void setIs_ON(boolean is_ON) {
		Is_ON = is_ON;
	}

	public double getOn_Time() {
		return On_Time;
	}

	public void setOn_Time(double on_Time) {
		On_Time = on_Time;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public double[] getGmatrix() {
		return Gmatrix;
	}

	public void setGmatrix(double[] gmatrix) {
		Gmatrix = gmatrix;
	}

	public double getStddev() {
		return Stddev;
	}

	public void setStddev(double stddev) {
		Stddev = stddev;
	}

	public int getSpecType() {
		return SpecType;
	}

	public void setSpecType(int specType) {
		SpecType = specType;
	}

}
