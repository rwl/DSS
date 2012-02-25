package com.ncond.dss.shell;

import com.ncond.dss.executive.Executive;
import com.ncond.dss.executive.impl.DSSExecutive;

public class DSSShell extends AbstractShell {

	private Executive executive;

	@Override
	public void run() {
		AbstractShell.setPromptString("dss>");

		executive = DSSExecutive.getInstance();
		executive.createDefaultDSSItems();

		super.run();
	}

	public void executeCommand(String line) {
		executive.setCommand(line);
	}

}
