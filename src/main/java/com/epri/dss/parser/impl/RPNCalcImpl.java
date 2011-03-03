package com.epri.dss.parser.impl;

import com.epri.dss.parser.RPNCalc;

public class RPNCalcImpl implements RPNCalc {

	private static final int MaxStackSize = 10;
	
	private static final double DegToRad = 3.14159265359 / 180.0;
	private static final double RadToDeg = 1.0 / DegToRad;

	private double[] Stack = new double[MaxStackSize];

	public RPNCalcImpl() {
		for (int i = 0; i < MaxStackSize; i++) 
			Stack[i] = 0.0;
	}

	public void aCosDeg() {
		Stack[0] = RadToDeg * Math.acos(Stack[0]);
	}

	public void add() {
		Stack[1] = Stack[0] + Stack[1];
		rollDn();
	}

	public void aSinDeg() {
		Stack[0] = RadToDeg * Math.asin(Stack[0]);
	}

	public void aTanDeg() {
		Stack[0] = RadToDeg * Math.atan(Stack[0]);
	}

	public void aTan2Deg() {
		Stack[1] = RadToDeg * Math.atan2(Stack[1], Stack[0]);
		rollDn();
	}

	public void cosDeg() {
		Stack[0] = Math.cos(DegToRad * Stack[0]);
	}

	public void divide() {
		Stack[1] = Stack[1] / Stack[0];
		rollDn();
	}

	public double getX() {
		return Stack[0];
	}

	public double getY() {
		return Stack[1];
	}

	public double getZ() {
		return Stack[2];
	}

	public void multiply() {
		Stack[1] = Stack[1] * Stack[0];
		rollDn();
	}

	public void rollDn() {
		for (int i = 1; i < MaxStackSize; i++) 
			Stack[i - 1] = Stack[i];
	}

	public void rollUp() {
		for (int i = 1; i < MaxStackSize; i++) 
			Stack[i] = Stack[i - 1];
		
	}

	public void setX(double x) {
		rollUp();
		Stack[0] = x;
	}

	public void setY(double y) {
		Stack[1] = y;
	}

	public void setZ(double z) {
		Stack[2] = z;
	}

	public void sinDeg() {
		Stack[0] = Math.sin(DegToRad * Stack[0]);
	}

	public void sqrt() {
		Stack[0] = Math.sqrt(Stack[0]);
	}

	public void square() {
		Stack[0] = Math.pow(Stack[0], 2);
	}

	public void subtract() {
		Stack[1] = Stack[1] - Stack[0];
		rollDn();
	}
	
	public void swapXY() {
		double Temp = Stack[0];
		Stack[0] = Stack[1];
		Stack[1] = Temp;
	}

	public void tanDeg() {
		Stack[0] = Math.tan(DegToRad * Stack[0]);
	}

	public void yToTheXPower() {
		Stack[1] = Math.pow(Stack[1], Stack[0]);
		rollDn();
	}

	public void enterPi() {
		rollUp();
		Stack[0] = Math.PI;
	}

	public void eToTheX() {
		Stack[0] = Math.exp(Stack[0]);
	}

	public void natLog() {
		Stack[0] = Math.log(Stack[0]);
	}

	public void tenLog() {
		Stack[0] = Math.log10(Stack[0]);
	}

	public void inv() {
		Stack[0] = 1.0 / Stack[0];
	}

}
