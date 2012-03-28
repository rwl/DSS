package com.ncond.dss.shell.roo;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.ExitShellRequest;
import org.springframework.roo.shell.ParseResult;

import com.ncond.dss.executive.ExecHelper;
import com.ncond.dss.executive.Executive;

public class DSSExecutionStrategy implements ExecutionStrategy {

	private static Logger log = Logger.getLogger(DSSExecutionStrategy.class.getName());

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
		log.info(cmd);
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
