package com.ncond.dss.shell;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.ExecHelper;
import com.ncond.dss.parser.Parser;

public class DSSCompletor {

	private Parser parser = DSS.auxParser;

	public int complete(String buf, int cursor, List<String> clist) {
		String buffer = (buf == null) ? "" : buf;

		// the list of candidates that will be completed
		Set<String> candidates = locateCandidates(buffer);

		if (candidates.size() == 0) {
			return cursor;
		} else if (candidates.size() == 1) {
			clist.add(candidates.iterator().next());

			clist.set(0, ((String) clist.get(0)) + " ");
		} else {
			clist.addAll(candidates);
		}


		// the index of the completion is always from the beginning of the buffer
		return (clist.size() == 0) ? (-1) : 0;
	}

	/*
	 * Comamnd syntax:
	 *   command param1, param2, param3 ...
	 *
	 * Parameters:
	 *   paramName=param (named) (Class.ElementName.Property = Value)
	 *   param           (positional)
	 *
	 * E.g:
	 *   new object="line.First Line" bus1=b1240 bus2=32 lineCode=336ACSR, ...
	 *
	 *   new “line.First Line”, b1240, 32,  336ACSR, ...
	 *
	 */
	private Set<String> locateCandidates(String buffer) {
		String[] objName = new String[1];
		String[] propName = new String[1];

		Set<String> candidates = new TreeSet<String>();

		parser.setCmdString(buffer);

		int paramPointer = -1;
		String paramName = parser.getNextParam();  // new,
		String param = parser.makeString();

		if (param.length() == 0) {
			Collections.addAll(candidates, ExecCommands.execCommand);
			// defaults to edit command
			Collections.addAll(candidates, DSS.classNames.toArray());
			return candidates;
		}

		if (paramName.length() == 0)
			paramPointer = ExecCommands.commandList.getCommand(param);

		/* If not a command or the command is unknown, then it could be  a property
		 * of a circuit element */
		if (paramPointer == -1) {
			/* If a command or no text before the = sign, then error */
			if (paramName.length() == 0 || paramName.equalsIgnoreCase("command")) {
				return candidates;
			}
		} else {
			ExecHelper.parseObjName(paramName, objName, propName);

		}

		candidates.add(buffer);

		return candidates;

	}

}
