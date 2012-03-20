package com.ncond.dss.shell.jline;

public class Main {

	public static void main(String[] args) {
//		JLineShell dssShell = new JLineShell();
//		dssShell.run();

		RooShell sh = new RooShell();
		sh.shellPrompt = "dss>";
		sh.run();
	}

}
