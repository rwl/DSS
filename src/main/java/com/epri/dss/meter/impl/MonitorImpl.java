package com.epri.dss.meter.impl;

import com.epri.dss.meter.Monitor;

public class MonitorImpl extends MeterClassImpl implements Monitor {

	public MonitorImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected int makeLike(String Name) {
		return 0;
	}
	
	protected void defineProperties() {
		
	}
	
	@Override
	public int edit() {
		return 0;
	}
	
	@Override
	public int init(int Handle) {
		return 0;
	}
	
	@Override
	public int newObject(String ObjName) {
		return 0;
	}
	
	@Override
	public void resetAll() {
		
	}
	
	/* Force all monitors to take a sample */
	@Override
	public void sampleAll() {
		
	}
	
	/* Force all monitors to save their buffers to disk */
	@Override
	public void saveAll() {
		
	}
	
	public void tOPExport(String ObjName) {
		
	}

}
