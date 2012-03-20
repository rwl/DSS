package com.ncond.dss.shell.roo;

import java.lang.reflect.InvocationTargetException;

import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.ParseResult;

public class DSSExecutionStrategy implements ExecutionStrategy {

	@Override
	public Object execute(ParseResult parseResult) throws RuntimeException {
		try {
			parseResult.getMethod().invoke(
					parseResult.getInstance(),
			                parseResult.getArguments());
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Executive.setCommand not invoked");
		} catch (InvocationTargetException e) {
			throw new IllegalStateException("Executive.setCommand not invoked");
		}
		return null;
	}

	@Override
	public boolean isReadyForCommands() {
		return true;
	}

	@Override
	public void terminate() {

	}

}
