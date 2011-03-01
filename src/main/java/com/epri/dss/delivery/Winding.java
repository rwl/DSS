package com.epri.dss.delivery;

public interface Winding {

	int getConnection();

	void setConnection(int connection);

	double getKvll();

	void setKvll(double kvll);

	double getVBase();

	void setVBase(double vBase);

	double getKva();

	void setKva(double kva);

	double getPuTap();

	void setPuTap(double puTap);

	double getRpu();

	void setRpu(double rpu);

	double getRneut();

	void setRneut(double rneut);

	double getXneut();

	void setXneut(double xneut);

	double getY_PPM();

	void setY_PPM(double y_PPM);

	double getTapIncrement();

	void setTapIncrement(double tapIncrement);

	double getMinTap();

	void setMinTap(double minTap);

	double getMaxTap();

	void setMaxTap(double maxTap);

	int getNumTaps();

	void setNumTaps(int numTaps);
	
	void computeAntiFloatAdder(double PPM_Factor, double VABase1ph);

}
