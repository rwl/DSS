package com.ncond.dss.shell.jline;

import com.ncond.dss.executive.Executive;

public class DSSShell extends AbstractShell {

	private Executive executive;

	@Override
	public void run() {
		AbstractShell.setPromptString("dss>");

		executive = Executive.getInstance();
		executive.createDefaultDSSItems();

		super.run();
	}

	public void executeCommand(String line) {
		executive.setCommand(line);
	}

}
