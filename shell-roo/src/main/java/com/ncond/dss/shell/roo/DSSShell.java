package com.ncond.dss.shell.roo;

import java.net.URL;
import java.util.Collection;

import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.Parser;
import org.springframework.roo.shell.jline.DSSJLineShell;

public class DSSShell extends DSSJLineShell {

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
		return new DSSParser();
	}

	public String version(String extra) {
		return null;
	}

	public static void main(String[] args) {
		new DSSShell().run();
	}

}
