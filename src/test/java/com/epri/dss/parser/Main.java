package com.epri.dss.parser;

import com.epri.dss.executive.Executive;
import com.epri.dss.executive.impl.DSSExecutive;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Executive ex = DSSExecutive.getInstance();

		ex.createDefaultDSSItems();

		String cmd = "Compile (/home/rwl/tmp/OpenDSS/Test/IEEE13_LineSpacing.dss)";

		ex.setCommand(cmd);

	}

}
