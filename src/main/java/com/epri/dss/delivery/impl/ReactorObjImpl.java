package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.ReactorObj;

public class ReactorObjImpl extends PDElementImpl implements ReactorObj {
	
	private double R, Rp, Gp, X, kvarrating, kvrating;
	/* If not null then overrides C */
	private double[] Rmatrix, Gmatrix, XMatrix, Bmatrix;  

	private int Connection;  // 0 or 1 for wye (default) or delta, respectively
	private int SpecType;   // 1=kvar, 2=R+jX, 3=R and X matrices

	private boolean IsParallel;
	private boolean RpSpecified;

	public ReactorObjImpl(DSSClass ParClass, String ReactorName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses) {
		
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
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

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public double getRp() {
		return Rp;
	}

	public void setRp(double rp) {
		Rp = rp;
	}

	public double getGp() {
		return Gp;
	}

	public void setGp(double gp) {
		Gp = gp;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getKvarrating() {
		return kvarrating;
	}

	public void setKvarrating(double kvarrating) {
		this.kvarrating = kvarrating;
	}

	public double getKvrating() {
		return kvrating;
	}

	public void setKvrating(double kvrating) {
		this.kvrating = kvrating;
	}

	public double[] getRmatrix() {
		return Rmatrix;
	}

	public void setRmatrix(double[] rmatrix) {
		Rmatrix = rmatrix;
	}

	public double[] getGmatrix() {
		return Gmatrix;
	}

	public void setGmatrix(double[] gmatrix) {
		Gmatrix = gmatrix;
	}

	public double[] getXMatrix() {
		return XMatrix;
	}

	public void setXMatrix(double[] xMatrix) {
		XMatrix = xMatrix;
	}

	public double[] getBmatrix() {
		return Bmatrix;
	}

	public void setBmatrix(double[] bmatrix) {
		Bmatrix = bmatrix;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public int getSpecType() {
		return SpecType;
	}

	public void setSpecType(int specType) {
		SpecType = specType;
	}

	public boolean isIsParallel() {
		return IsParallel;
	}

	public void setIsParallel(boolean isParallel) {
		IsParallel = isParallel;
	}

	public boolean isRpSpecified() {
		return RpSpecified;
	}

	public void setRpSpecified(boolean rpSpecified) {
		RpSpecified = rpSpecified;
	}

}
