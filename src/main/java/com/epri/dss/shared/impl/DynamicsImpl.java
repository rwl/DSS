package com.epri.dss.shared.impl;

import com.epri.dss.shared.Dynamics;

public class DynamicsImpl implements Dynamics {

	/* Variables needed for dynamics and user-written models. */
	public class DynamicsRec {
		/* time vars */
		public double h;     // Time step size in sec for dynamics
		public double t;     // sec from top of hour
		public double tstart;
		public double tstop;

		/* 0 = New Time Step; 1 = Same Time Step as last iteration */
		public int IterationFlag;
		/* PEAKSNAP, DAILYMODE, YEARLYMODE, MONTECARLO, etc. (see DSSGlobals) */
		public int SolutionMode;
	}

	/* Generator state record */
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
	}

}
