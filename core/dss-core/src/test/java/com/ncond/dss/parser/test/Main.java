package com.ncond.dss.parser.test;

import com.ncond.dss.executive.Executive;
import com.ncond.dss.executive.Executive;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Executive ex = Executive.getInstance();

		ex.createDefaultDSSItems();

		String cmd = "Compile (/home/rwl/tmp/OpenDSS/Test/IEEE13_LineSpacing.dss)";

		ex.setCommand(cmd);

	}

}
