package com.ncond.dss.shell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.ExecHelper;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.parser.Parser;

public class DSSCompletor {

	private static final String COMMAND_KEY = "command";

	public static void main(String[] args) {
		Executive.getInstance();

		DSSCompletor comp = new DSSCompletor();


//		String cmd = "command=new object=line.line1 bus1=b1240";
//
//		Set<String> candidates = comp.locateCandidates(cmd);
//
//		comp.locateCandidates("command=new object=line.line1 bus1=b1240");
//		// keys: [command, object, bus1]
//		// values: [new, line.line1, b1240]
//
//		comp.locateCandidates("new xfmr.Xfmr1, b90");
//		// keys: [, , ]
//		// values: [new, xfmr.Xfmr1, b90]
//
//		comp.locateCandidates("Class.ElementName.Property = Value");
//		// keys: [Class.ElementName.Property]
//		// values: [Value]

		System.out.println(comp.locateCandidates("comm").toString());

		System.out.println(comp.locateCandidates("Monit").toString());

		System.out.println(comp.locateCandidates("Transformer.Reg1.Ta").toString());

	}

	private Parser parser = DSS.auxParser;

	/*
	 * Comamnd syntax:
	 *   command param1, param2, param3 ...
	 *
	 * Parameters:
	 *   paramName=param (named)
	 *   param           (positional)
	 *
	 * E.g:
	 *   command=new object="line.First Line" bus1=b1240 bus2=32 lineCode=336ACSR, ...
	 *
	 *   new “line.First Line”, b1240, 32, 336ACSR, ...
	 *
	 *   Class.ElementName.Property = Value
	 *
	 */
	private Set<String> locateCandidates(String buffer) {
		int clsIdx;
		String key, value;

		String[] clsName = new String[1];
		String[] objName = new String[1];
		String[] propName = new String[1];

		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		Set<String> candidates = new TreeSet<String>();


		parser.setCmdString(buffer);

		while (true) {
			key = parser.getNextParam();
			value = parser.makeString();

			if (key.length() == 0 && value.length() == 0) break;

			keys.add(key.toLowerCase());
			values.add(value.toLowerCase());
		}

		assert keys.size() == values.size();

		switch (keys.size()) {
		case 0:
			candidates.add(COMMAND_KEY);
			Collections.addAll(candidates, ExecCommands.execCommand);
			// defaults to edit command
			/*for (String name : DSS.classNames.toArray())  // too many
				if (name != null) candidates.add(name);*/
			break;
		case 1:
			key = keys.get(0);
			value = values.get(0);

			if (key.length() == 0) {  // no equals sign (complete for keys and values)
				// keys ("command" or Class.ElementName.Property  TODO: or just Property (if activeObject != null))
				key = value;

				if (COMMAND_KEY.startsWith(key)) {
//					candidates.add(COMMAND_KEY.substring(key.length(), COMMAND_KEY.length()));
					candidates.add(COMMAND_KEY);
				}

				Util.parseObjectClassandName(key, clsName, objName);  // if no dot, assumes its all object name
				if (clsName[0].length() == 0) {  // complete class name
					clsName[0] = objName[0];
					for (String validClassName : DSS.classNames.toArray()) {
						if (validClassName != null && validClassName.startsWith(clsName[0])) {
//							candidates.add(validClassName.substring(clsName[0].length(), validClassName.length()));
							candidates.add(validClassName);
						}
					}
				} else {  // complete object or property name
					clsIdx = DSS.classNames.find(clsName[0]);
					if (clsIdx < 0) return candidates;
					DSSClass cls = DSS.DSSClassList.get(clsIdx);

					ExecHelper.parseObjName(objName[0], objName, propName);  // if no dot, assumes its all property name
					if (objName[0].length() == 0) {  // complete object name
						objName[0] = propName[0];
						for (String elemName : cls.getElementNameList().toArray()) {
							if (elemName != null && elemName.startsWith(objName[0])) {
//								candidates.add(elemName.substring(objName[0].length(), elemName.length()));
								candidates.add(elemName);
							}

						}
					} else {  // complete object property name
						for (String name : cls.getPropertyName()) {
							if (name.startsWith(propName[0])) {
//								candidates.add(name.substring(propName[0].length(), name.length()));
								candidates.add(name);
							}
						}
					}
				}

				// command values
				for (String cmd : ExecCommands.execCommand) {
					if (cmd.startsWith(value)) {
//						candidates.add(cmd.substring(value.length(), cmd.length()));
						candidates.add(cmd);
					}
				}
			} else {  // just complete value

			}
			break;
		default:
			break;
		}

//		paramPointer = -1;
//
//		if (paramName.length() == 0 && param.length() == 0) {
//			candidates = completeParam("");
//		}
//
//		if (paramName.length() == 0)
//			paramPointer = ExecCommands.commandList.getCommand(param);
//
//		/* If not a command or the command is unknown, then it could be  a property
//		 * of a circuit element */
//		if (paramPointer == -1) {
//			/* If a command or no text before the = sign, then error */
//			if (paramName.length() == 0 || paramName.equalsIgnoreCase("command")) {
//				return candidates;
//			}
//		} else {
//			ExecHelper.parseObjName(paramName, objName, propName);
//
//		}
//
//		candidates.add(buffer);

		return candidates;

	}

	private Set<String> completeParam(String param) {

		Set<String> candidates = new TreeSet<String>();

		if (param.length() == 0) {
			Collections.addAll(candidates, ExecCommands.execCommand);
			// defaults to edit command
			Collections.addAll(candidates, DSS.classNames.toArray());
		} else {

		}

		return candidates;
	}

	private Set<String> completeParamName(String buffer) {

		Set<String> candidates = new TreeSet<String>();

		return candidates;
	}

	public int complete(String buf, int cursor, List<String> clist) {
		String buffer = (buf == null) ? "" : buf;

		// the portion of the buffer represented to the present cursor position
		final String translated = buffer.substring(0, cursor);

		// the list of candidates that will be completed
		Set<String> candidates = locateCandidates(translated);

		if (candidates.size() == 0) {
			return cursor;
//		} else if (candidates.size() == 1) {
//			clist.add(candidates.iterator().next());
//
//			clist.set(0, ((String) clist.get(0)) + " ");
		} else {
			clist.addAll(candidates);
		}


//		// the index of the completion is always from the beginning of the buffer
		return (clist.size() == 0) ? (-1) : 0;
	}

}
