package com.epri.dss.shared.impl;

/**
 * Generator state record.
 */
public class GeneratorVars {

	/** Direct-axis voltage magnitude & angle */
	public double theta;
	public double PShaft;
	public double speed;
	/**
	 * Present shaft power and relative speed, rad/sec, difference from
	 * synchronous speed, w0
	 * actual speed = Speed + w0
	 */
	public double w0;
	/** Per unit mass constant */
	public double HMass;
	/** Mass constant actual values (Joule-sec/rad) */
	public double MMass;
	/** Actual and per unit damping factors */
	public double D, Dpu;
	public double kVARating;
	public double kVGeneratorBase;
	/** Machine reactances, ohms */
	public double Xd, Xdp, Xdpp;
	/** Machine reactances, per unit */
	public double puXd, puXdp, puXdpp;
	public double dTheta;
	/** Derivatives of theta and speed */
	public double dSpeed;
	/** History variables for integration */
	public double thetaHistory, speedHistory;
	/** Target P and Q for power flow solution, watts, vars */
	public double PNominalPerPhase, QNominalPerPhase;

	/** Number of phases */
	public int numPhases;
	/** Total number of conductors (wye-connected will have 4) */
	public int numConductors;
	/** 0 = wye; 1 = delta */
	public int conn;

	// FIXME Generate getters and setters

}
