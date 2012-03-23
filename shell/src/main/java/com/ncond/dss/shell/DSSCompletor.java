package com.ncond.dss.shell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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

//	private static final Set<String> classNames = getClassNameSet();

	private Parser parser = DSS.auxParser;

//	private static Set<String> getClassNameSet() {
//		Executive.getInstance();  // builds class lists
//		Set<String> names = new HashSet<String>();
//		for (String name : DSS.classNames.toArray())
//			if (name != null) names.add(name);
//		return names;
//	}

	public int complete(String buf, int cursor, List<String> clist) {
		String buffer = (buf == null) ? "" : buf;

		// the portion of the buffer represented to the present cursor position
		final String translated = buffer.substring(0, cursor);

		// the list of candidates that will be completed
		Set<String> candidates = new TreeSet<String>();
		int idx = locateCandidates(translated, candidates);

		if (candidates.size() == 0) {
			return cursor;
//		} else if (candidates.size() == 1) {
//			clist.add(candidates.iterator().next());
//
//			clist.set(0, ((String) clist.get(0)) + " ");
		} else {
			clist.addAll(candidates);
		}

		return (clist.size() == 0) ? (-1) : idx;
	}

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
	private int locateCandidates(String buffer, Set<String> candidates) {
		int clsIdx, idx, i;
		boolean abbrev;
		String key, value, c;

		String[] clsName = new String[1];
		String[] objName = new String[1];
		String[] propName = new String[1];

		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		parser.setCmdBuffer(buffer);
		while (true) {
			key = parser.getNextParam();
			value = parser.makeString();
			if (key.length() == 0 && value.length() == 0) break;
			keys.add(key.toLowerCase());
			values.add(value.toLowerCase());
		}
		assert keys.size() == values.size();

		idx = 0;  // complete from first column by default

		switch (keys.size()) {
		case 0:
			candidates.add(COMMAND_KEY);
			for (String cmd : ExecCommands.execCommand)
				if (cmd.charAt(0) != '_') candidates.add(cmd);
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

				if (suitable(COMMAND_KEY, key))
					candidates.add(COMMAND_KEY);

				// command values
				for (String cmd : ExecCommands.execCommand)
					if (suitable(cmd, value))
						candidates.add(cmd);

				/* If no dot this assumes its all object name, but
				 * without a dot, we're completing a class name. */
				Util.parseObjectClassandName(key, clsName, objName);

				if (clsName[0].length() == 0) {  // complete class name
					clsName[0] = objName[0];
					for (String validClassName : DSS.classNames.toArray()) {
						if (suitable(validClassName, clsName[0])) {
							candidates.add(validClassName);
						}
					}
					if (candidates.size() == 0 && DSS.classNames.find(clsName[0]) >= 0) {
						candidates.add(".");
						idx = clsName[0].length();
					} else if (candidates.size() == 1) {
						c = candidates.iterator().next();
						candidates.clear();
						candidates.add(c + ".");
					}
				} else {  // complete object name or property name
					clsIdx = DSS.classNames.find(clsName[0]);
					if (clsIdx < 0) return idx;
					DSSClass cls = DSS.DSSClassList.get(clsIdx);

					/* If no dot in object name this assumes its all property name,
					 * but without a dot we're completing an object name. */
					ExecHelper.parseObjName(objName[0], objName, propName);

					if (objName[0].length() == 0) {  // complete object name
						objName[0] = propName[0];
						for (String elemName : cls.getElementNameList().toArray()) {
							if (suitable(elemName, objName[0])) {
								candidates.add(elemName);
							}
						}
						if (candidates.size() == 1) {
							c = candidates.iterator().next();
							candidates.clear();
							candidates.add(c + ".");
						}
						if (candidates.size() > 0) {
							idx = clsName[0].length() + 1;
						} else {
							i = cls.getElementNameList().find(objName[0]);
							if (i >= 0) {  // only add dot for valid object names
								candidates.add(".");
								idx = clsName[0].length() + 1 + objName[0].length();
							}
						}

					} else {  // complete object property name
						for (String name : cls.getPropertyName()) {
							if (suitable(name.toLowerCase(), propName[0])) {
								candidates.add(name);
							}
						}
						if (candidates.size() == 1) {
							c = candidates.iterator().next();
							candidates.clear();
							candidates.add(c + "=");
						}
						if (candidates.size() > 0) {
							idx = clsName[0].length() + 1 + objName[0].length() + 1;
						} else {
							// turn off and restore command list abbreviations
							abbrev = cls.getCommandList().isAbbrevAllowed();
							cls.getCommandList().setAbbrevAllowed(false);
							i = cls.getCommandList().getCommand(propName[0]);
							cls.getCommandList().setAbbrevAllowed(abbrev);

							if (i >= 0) {  // only add = for valid property names
								candidates.add("=");
								idx = clsName[0].length() + 1 + objName[0].length() + 1 + propName[0].length();
							}
						}
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

		return idx;

	}

	private boolean suitable(String s, String buf) {
		return s != null && s.startsWith(buf) && !buf.equals(s);
	}

//	private Set<String> completeParam(String param) {
//
//		Set<String> candidates = new TreeSet<String>();
//
//		if (param.length() == 0) {
//			Collections.addAll(candidates, ExecCommands.execCommand);
//			// defaults to edit command
//			Collections.addAll(candidates, DSS.classNames.toArray());
//		} else {
//
//		}
//
//		return candidates;
//	}
//
//	private Set<String> completeParamName(String buffer) {
//
//		Set<String> candidates = new TreeSet<String>();
//
//		return candidates;
//	}

//	public static void main(String[] args) {
//		Executive.getInstance();
//
//		DSSCompletor comp = new DSSCompletor();
//
//
////		String cmd = "command=new object=line.line1 bus1=b1240";
////
////		Set<String> candidates = comp.locateCandidates(cmd);
////
////		comp.locateCandidates("command=new object=line.line1 bus1=b1240");
////		// keys: [command, object, bus1]
////		// values: [new, line.line1, b1240]
////
////		comp.locateCandidates("new xfmr.Xfmr1, b90");
////		// keys: [, , ]
////		// values: [new, xfmr.Xfmr1, b90]
////
////		comp.locateCandidates("Class.ElementName.Property = Value");
////		// keys: [Class.ElementName.Property]
////		// values: [Value]
//
//		System.out.println(comp.locateCandidates("comm").toString());
//
//		System.out.println(comp.locateCandidates("Monit").toString());
//
//		System.out.println(comp.locateCandidates("Transformer.Reg1.Ta").toString());
//
//	}

}
