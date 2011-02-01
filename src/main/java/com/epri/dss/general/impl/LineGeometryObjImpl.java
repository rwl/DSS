package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.OHLineConstants;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.CMatrix;

public class LineGeometryObjImpl extends DSSObjectImpl implements LineGeometryObj {

	private int NConds;
	private int NPhases;
	private String[] condType;
	private WireDataObj[] WireData;
	private double[] X;
	private double[] Y;
	private int[] Units;
	private int LastUnit;
	private boolean DataChanged;
	private boolean Reduce;
	private int ActiveCond;
	private String SpacingType;

	private OHLineConstants LineData;
	
	public double NormAmps;
	public double EmergAmps;

	public LineGeometryObjImpl(DSSClass ParClass, String LineGeometryName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public void setNconds(int Value) {

	}

	public void setNphases(int Value) {

	}
	
	public int getNphases() {
		return NConds;
	}

	public void setActiveCond(int Value) {

	}
	
	public int getActiveCond() {
		return ActiveCond;
	}

	public CMatrix getYCmatrix(double f, double Length, int Units) {
		return null;
	}

	public CMatrix getZmatrix(double f, double Length, int Units) {
		return null;
	}

	public double getRhoEarth() {
		return 0.0;
	}

	public void setRhoEarth(double Value) {

	}

	public int getNconds() {
		return 0;
	}
	
	/* call this before using the line data */
	private void updateLineGeometryData(double f) {
		
	}
	
	public double getXcoord(int i) {
		return 0.0;
	}
	
	public double getYcoord(int i) {
		return 0.0;
	}
	
	public String getWireName(int i) {
		return null;
	}
	
	public WireDataObj getWireData (int i) {
		return null;
	}
	
	public int getNWires() {
		return NConds;
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public void saveWrite(PrintStream F) {
		
	}
	
	/**
	 * called from a Line object that has its own Spacing and Wires input
	 * automatically sets reduce=y if the spacing has more wires than phases
	 */
	public void LoadSpacingAndWires(LineSpacingObj Spc, WireDataObj[] Wires) {
		
	}

}
