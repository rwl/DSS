package com.ncond.dss.shell.jline;

import java.net.URL;
import java.util.Collection;

import org.springframework.roo.shell.AbstractShell;
import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.Parser;
import org.springframework.roo.shell.jline.JLineShell;

public class RooShell extends JLineShell {

	@Override
	protected Collection<URL> findResources(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ExecutionStrategy getExecutionStrategy() {
		return new DSSExecutionStrategy();
	}

	@Override
	protected Parser getParser() {
		return new BasicParser();
	}

	public String version(String extra) {
		return "";
	}

}
