package com.epri.dss.executive;

public interface ExecOptions {

	public int doGetCmd();

	public int doSetCmd(int SolveOption);

	/* Set Commands that do not require a circuit */
	public boolean doSetCmd_NoCircuit();

}
