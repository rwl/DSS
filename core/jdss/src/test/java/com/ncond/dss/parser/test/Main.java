package com.ncond.dss.parser.test;

import com.ncond.dss.executive.Executive;
import com.ncond.dss.executive.impl.DSSExecutive;

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
