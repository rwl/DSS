package com.epri.dss.delivery;

public interface Winding {

	int getConnection();

	void setConnection(int connection);

	double getKVLL();

	void setKVLL(double kvll);

	double getVBase();

	void setVBase(double vBase);

	double getKVA();

	void setKVA(double kva);

	double getPUTap();

	void setPUTap(double puTap);

	double getRpu();

	void setRpu(double rpu);

	double getRNeut();

	void setRNeut(double rneut);

	double getXNeut();

	void setXNeut(double xneut);

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
