package com.ncond.dss.common;

public interface Terminal {

	int getBusRef();

	void setBusRef(int busRef);

	int getTermNodeRef(int idx);

	int[] getTermNodeRef();

	void setTermNodeRef(int[] termNodeRef);

	Conductor[] getConductors();

	void setConductors(Conductor[] conductors);

	boolean isChecked();

	void setChecked(boolean checked);

	void setActiveConductor(int Value);

	int getActiveConductor();

}
