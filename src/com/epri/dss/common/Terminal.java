package com.epri.dss.common;

public interface Terminal {

	public int getBusRef();

	public void setBusRef(int busRef);

	public int[] getTermNodeRef();

	public void setTermNodeRef(int[] termNodeRef);

	public Conductor[] getConductors();

	public void setConductors(Conductor[] conductors);

	public boolean isChecked();

	public void setChecked(boolean checked);

	public void setActiveConductor(int Value);

	public int getActiveConductor();

}
