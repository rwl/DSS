package com.epri.dss.control.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.SwtControlObj;

public class SwtControlObjImpl extends ControlElemImpl implements SwtControlObj {
	
	private ControlAction PresentState;
	private boolean Locked;

	public SwtControlObjImpl(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private void interpretSwitchAction(String Action) {
		
	}

	public ControlAction getPresentState() {
		return PresentState;
	}

	public boolean isLocked() {
		return Locked;
	}

	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}

	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	/* Sample control quantities and set action times in Control Queue */
	@Override
	public void sample() {
		
	}
	
	/* Do the action that is pending from last sample */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {
		
	}
	
	/* Reset to initial defined state */
	@Override
	public void reset() {
		
	}
	
	@Override
	public void getInjCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public void getCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}

}
