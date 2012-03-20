/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.parser;


public class RPNCalculator {

	private static final int MaxStackSize = 10;

	private static final double DegToRad = 3.14159265359 / 180.0;
	private static final double RadToDeg = 1.0 / DegToRad;

	private double[] stack = new double[MaxStackSize];

	public RPNCalculator() {
		for (int i = 0; i < MaxStackSize; i++) stack[i] = 0.0;
	}

	public void aCosDeg() {
		stack[0] = RadToDeg * Math.acos(stack[0]);
	}

	public void add() {
		stack[1] = stack[0] + stack[1];
		rollDn();
	}

	public void aSinDeg() {
		stack[0] = RadToDeg * Math.asin(stack[0]);
	}

	public void aTanDeg() {
		stack[0] = RadToDeg * Math.atan(stack[0]);
	}

	public void aTan2Deg() {
		stack[1] = RadToDeg * Math.atan2(stack[1], stack[0]);
		rollDn();
	}

	public void cosDeg() {
		stack[0] = Math.cos(DegToRad * stack[0]);
	}

	public void divide() {
		stack[1] = stack[1] / stack[0];
		rollDn();
	}

	public double getX() {
		return stack[0];
	}

	public double getY() {
		return stack[1];
	}

	public double getZ() {
		return stack[2];
	}

	public void multiply() {
		stack[1] = stack[1] * stack[0];
		rollDn();
	}

	public void rollDn() {
		for (int i = 1; i < MaxStackSize; i++) {
			stack[i - 1] = stack[i];
		}
	}

	public void rollUp() {
		for (int i = 1; i < MaxStackSize; i++) {
			stack[i] = stack[i - 1];
		}

	}

	public void setX(double x) {
		rollUp();
		stack[0] = x;
	}

	public void setY(double y) {
		stack[1] = y;
	}

	public void setZ(double z) {
		stack[2] = z;
	}

	public void sinDeg() {
		stack[0] = Math.sin(DegToRad * stack[0]);
	}

	public void sqrt() {
		stack[0] = Math.sqrt(stack[0]);
	}

	public void square() {
		stack[0] = Math.pow(stack[0], 2);
	}

	public void subtract() {
		stack[1] = stack[1] - stack[0];
		rollDn();
	}

	public void swapXY() {
		double Temp = stack[0];
		stack[0] = stack[1];
		stack[1] = Temp;
	}

	public void tanDeg() {
		stack[0] = Math.tan(DegToRad * stack[0]);
	}

	public void yToTheXPower() {
		stack[1] = Math.pow(stack[1], stack[0]);
		rollDn();
	}

	public void enterPi() {
		rollUp();
		stack[0] = Math.PI;
	}

	public void eToTheX() {
		stack[0] = Math.exp(stack[0]);
	}

	public void natLog() {
		stack[0] = Math.log(stack[0]);
	}

	public void tenLog() {
		stack[0] = Math.log10(stack[0]);
	}

	public void inv() {
		stack[0] = 1.0 / stack[0];
	}

}
