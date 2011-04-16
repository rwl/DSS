package com.epri.dss.shell;

import com.epri.dss.executive.Executive;
import com.epri.dss.executive.impl.DSSExecutive;

public class DSSShell extends AbstractShell {
	
	private Executive executive;
	
	@Override
	public void run() {
		AbstractShell.setPromptString("dss>");
		
		executive = new DSSExecutive();
		executive.createDefaultDSSItems();
		
		super.run();
	}

	public void executeCommand(String line) {
		executive.setCommand(line);
	}
	
}
