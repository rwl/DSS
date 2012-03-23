package com.ncond.dss.shell.roo;

import java.lang.reflect.InvocationTargetException;

import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.ExitShellRequest;
import org.springframework.roo.shell.ParseResult;

import com.ncond.dss.executive.Executive;

public class DSSExecutionStrategy implements ExecutionStrategy {

	@Override
	public Object execute(ParseResult parseResult) throws RuntimeException {
//		try {
//			parseResult.getMethod().invoke(
//					parseResult.getInstance(),
//			                parseResult.getArguments()[0]);
//		} catch (IllegalAccessException e) {
//			throw new IllegalStateException("Executive.setCommand not invoked");
//		} catch (InvocationTargetException e) {
//			throw new IllegalStateException("Executive.setCommand not invoked");
//		}

		String cmd = (String) parseResult.getArguments()[0];
		Executive.getInstance().setCommand(cmd);
		if (cmd.trim().equalsIgnoreCase("quit")) {
			return ExitShellRequest.NORMAL_EXIT;
		} else {
			return null;
		}
	}

	@Override
	public boolean isReadyForCommands() {
		return true;
	}

	@Override
	public void terminate() {

	}

}
