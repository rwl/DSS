package com.epri.dss.common;

public class Conductor {

	private String TCCName;
	private double AmbientTemp;
	/* Accumulated I2t */
	private double Accum_Isqt;

	/* change this variable to indicate open or closed switch */
	public boolean Closed;
	public boolean FuseBlown;

	public Conductor() {
		this.Closed = true;
		this.FuseBlown = false;
		this.Accum_Isqt = 0.0;
		this.TCCName = "";
	}

	public void Set_Ambient(double Value) {

	}

	public void Set_TCCname(String Value) {

	}

	public String Get_TCCname() {
		return null;
	}

	/* Computes whether conductor has burned down */
	public void CalcIsqt(double CurrentMag) {

	}

	/* restore the conductor and reset the i2t calcs */
	public void ResetIsqt() {

	}

}
