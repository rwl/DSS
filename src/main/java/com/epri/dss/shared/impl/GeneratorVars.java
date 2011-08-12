package com.epri.dss.shared.impl;

/**
 * Generator state record.
 */
public class GeneratorVars {

	/** Direct-axis voltage magnitude & angle */
	public double Theta;
	public double Pshaft;
	public double Speed;
	/**
	 * Present shaft power and relative speed, rad/sec, difference from
	 * synchronous speed, w0
	 * actual speed = Speed + w0
	 */
	public double w0;
	/** Per unit mass constant */
	public double Hmass;
	/** Mass constant actual values (Joule-sec/rad) */
	public double Mmass;
	/** Actual and per unit damping factors */
	public double D, Dpu;
	public double kVArating;
	public double kVGeneratorBase;
	/** Machine reactances, ohms */
	public double Xd, Xdp, Xdpp;
	/** Machine reactances, per unit */
	public double puXd, puXdp, puXdpp;
	public double dTheta;
	/** Derivatives of theta and speed */
	public double dSpeed;
	/** History variables for integration */
	public double ThetaHistory, SpeedHistory;
	/** Target P and Q for power flow solution, watts, vars */
	public double Pnominalperphase, Qnominalperphase;

	/** Number of phases */
	public int NumPhases;
	/** Total number of conductors (wye-connected will have 4) */
	public int NumConductors;
	/** 0 = wye; 1 = delta */
	public int Conn;

	// FIXME Generate getters and setters

}
