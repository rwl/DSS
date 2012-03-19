package com.ncond.dss.shell;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.DSSObject;

abstract public class AbstractDSSShell implements Shell {

	public static String promptString = "dss>";

	protected String shellPrompt = promptString + " ";

	public static String completionKeys = "TAB";

	private Executive executive;

	public void run() {
		executive = Executive.getInstance();
		executive.createDefaultDSSItems();
	}

	public void executeCommand(String line) {
		String clsName, objName, base;

		executive.setCommand(line);

		if (DSS.activeDSSObject == null) {
			base = "";
		} else {
			DSSObject obj = DSS.activeDSSObject;
			clsName = obj.getDSSClassName();
			objName = obj.getName();
			base = String.format("%s.%s", clsName, objName);
		}
		setPromptBase(base);
	}

	public void setPromptBase(String base) {
		if (base == null || base.equals("")) {
			shellPrompt = promptString + " ";
		} else {
			shellPrompt = base + " " + promptString + " ";
		}
	}

	public String getShellPrompt() {
		return shellPrompt;
	}

	public static void setPromptString(String promptString) {
		AbstractDSSShell.promptString = promptString;
	}

}
