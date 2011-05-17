package com.epri.dss.shared.impl;

/**
 * Generator state record.
 */
public class GeneratorVars {

	/* Direct-Axis voltage magnitude & angle */
	public double Theta;
	public double Pshaft;
	public double Speed;
	/* present Shaft Power and relative Speed, rad/sec, difference from
	 * Synchronous speed, w0
	 * actual speed = Speed + w0
	 */
	public double w0;
	/* Per unit mass constant */
	public double Hmass;
	/* Mass constant actual values (Joule-sec/rad) */
	public double Mmass;
	/* Actual and per unit damping factors */
	public double D, Dpu;
	public double kVArating;
	public double kVGeneratorBase;
	/* machine Reactances, ohms */
	public double Xd, Xdp, Xdpp;
	/* machine Reactances, per unit */
	public double puXd, puXdp, puXdpp;
	public double dTheta;
	/* Derivatives of Theta and Speed */
	public double dSpeed;
	/* history variables for integration */
	public double ThetaHistory, SpeedHistory;
	/* Target P and Q for power flow solution, watts, vars */
	public double Pnominalperphase, Qnominalperphase;

	/* Number of phases */
	public int NumPhases;
	/* Total Number of conductors (wye-connected will have 4) */
	public int NumConductors;
	/* 0 = wye; 1 = Delta */
	public int Conn;

	// FIXME Generate getters and setters

}
