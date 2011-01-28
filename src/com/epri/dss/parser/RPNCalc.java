package com.epri.dss.parser;

public interface RPNCalc {

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double getZ();

	void setZ(double z);

	void multiply();

	void divide();

	void sqrt();

	void square();

	void add();

	void subtract();

	void yToTheXPower();

	void sinDeg();

	void cosDeg();

	void tanDeg();

	void aSinDeg();

	void aCosDeg();

	void aTanDeg();

	void aTan2Deg();

	void natLog();

	void tenLog();

	void eToTheX();

	void enterPi();

	void inv();


	void swapXY();

	void rollUp();

	void rollDn();

}
