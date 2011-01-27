package com.epri.dss.common;

/*
 * Unit for processing the AutoAdd Solution FUNCTIONs
 *
 * Note: Make sure this class in instantiated after EnergyMeter class
 *
 * There is one of these per circuit
 */
public interface AutoAdd {

	public double getGenkW();

	public void setGenkW(double genkW);

	public double getGenPF();

	public void setGenPF(double genPF);

	public double getGenkvar();

	public void setGenkvar(double genkvar);

	public double getCapkvar();

	public void setCapkvar(double capkvar);

	public int getAddType();

	public void setAddType(int addType);

	public boolean isModeChanged();

	public void setModeChanged(boolean modeChanged);

	public void makeBusList();

	public void appendToFile(String WhichFile, String S);

	public void addCurrents(int SolveType);

}
