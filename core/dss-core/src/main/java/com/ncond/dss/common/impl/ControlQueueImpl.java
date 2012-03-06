package com.ncond.dss.common.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ncond.dss.common.ControlQueue;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.control.impl.ControlAction;

public class ControlQueueImpl implements ControlQueue {

	public static class TimeRec {
		public int hour;
		public double sec;
	}

	public static class ActionRecord {
		public TimeRec actionTime;
		public int actionCode;
		public int actionHandle;
		public int proxyHandle;
		public ControlElem controlElement;
	}

	private List<ActionRecord> actionList;
	private boolean debugTrace;
	private File traceFile;
	private int ctrlHandle;

	public ControlQueueImpl() {
		super();
		actionList = new ArrayList<ControlQueueImpl.ActionRecord>();
		actionList.clear();

		ctrlHandle = 0;  // just a serial number

		debugTrace = false;
	}

	public int push(int hour, double sec, int code, int proxyHdl, final ControlElem owner) {
		int hr;
		double thisActionTime, s;
		TimeRec timeRec = new TimeRec();
		ActionRecord pAction;
		boolean actionInserted;

		ctrlHandle += 1;  // just a serial number

		/* Normalize the time */
		hr = hour;
		s  = sec;
		if (s > 3600.0) {
			while (s >= 3600.0) {
				hr++;
				s -= 3600.0;
			}
		}

		timeRec.hour = hr;
		timeRec.sec = s;

		thisActionTime = timeRecToTime(timeRec);
		pAction = new ActionRecord();  // make a new action

		/* Insert the action in the list in order of time */
		actionInserted = false;
		for (int i = 0; i < actionList.size(); i++) {
			if (thisActionTime <= timeRecToTime( ((ActionRecord) actionList.get(i)).actionTime )) {
				actionList.add(i, pAction);
				actionInserted = true;
				break;
			}
		}

		if (!actionInserted)
			actionList.add(pAction);

		pAction.actionTime = timeRec;
		pAction.actionCode = code;
		pAction.actionHandle = ctrlHandle;
		pAction.proxyHandle = proxyHdl;
		pAction.controlElement = owner;

		if (debugTrace) {
			writeTraceRecord(owner.getName(), code, owner.getDblTraceParameter(),
					String.format("Handle %d pushed onto stack", ctrlHandle));
		}

		return ctrlHandle;
	}

	public int push(int hour, double sec, ControlAction code, int proxyHdl, final ControlElem owner) {
		return push(hour, sec, code.code(), proxyHdl, owner);
	}

	public void clear() {
		actionList.clear();
	}

	public void doAllActions() {
		ActionRecord action;
		for (int i = 0; i < actionList.size(); i++) {
			action = actionList.get(i);
			action.controlElement.doPendingAction(action.actionCode, action.proxyHandle);
		}
		actionList.clear();
	}

	public boolean doNearestActions(int[] hour, double[] sec) {
		ControlElem pElem;
		TimeRec t;
		int[] code = new int[1];
		int[] hdl = new int[1];
		int[] proxyHdl = new int[1];

		boolean result = false;
		if (actionList.size() > 0) {
			t = actionList.get(0).actionTime;
			hour[0] = t.hour;
			sec[0] = t.sec;
			pElem = pop(t, code, proxyHdl, hdl);
			while (pElem != null) {
				if (debugTrace) {
					writeTraceRecord(pElem.getName(), code[0], pElem.getDblTraceParameter(),
							String.format("Pop Handle %d Do Nearest Action", hdl));
				}
				pElem.doPendingAction(code[0], proxyHdl[0]);
				result = true;
				pElem = pop(t, code, proxyHdl, hdl);
			}
		}
		return result;
	}

	public boolean isEmpty() {
		return actionList.size() == 0;
	}

	/**
	 * Pop off next control action with an action time <= actionTime (sec).
	 */
	private ControlElem pop(TimeRec actionTime, int[] code, int[] proxyHdl, int[] hdl) {
		ActionRecord action;
		ControlElem result = null;

		double t = timeRecToTime(actionTime);

		for (int i = 0; i < actionList.size(); i++) {
			action = actionList.get(i);
			if (timeRecToTime(action.actionTime) <= t) {
				result = action.controlElement;
				code[0] = action.actionCode;
				proxyHdl[0] = action.proxyHandle;
				hdl[0] = action.actionHandle;
				deleteFromQueue(i, true);
				break;
			}
		}

		return result;
	}

	/**
	 * Delete i-th element from the queue.
	 */
	private void deleteFromQueue(int i, boolean popped) {
		ControlElem pElem;
		String s;

		ActionRecord action = actionList.get(i);

		pElem = action.controlElement;
		if (debugTrace) {
			s = popped ? "by Pop function" : "by control device";
			writeTraceRecord(pElem.getName(), action.actionCode, pElem.getDblTraceParameter(),
					String.format("Handle %d deleted from queue %s", action.actionHandle, s));
		}
		actionList.remove(i);
	}

	public boolean doActions(int hour, double sec) {
		TimeRec t = new TimeRec();
		int[] code = new int[1];
		int[] hdl = new int[1];
		int[] proxyHdl = new int[1];

		boolean result = false;
		if (actionList.size() > 0) {
			t.hour = hour;
			t.sec  = sec;
			ControlElem pElem = pop(t, code, proxyHdl, hdl);
			while (pElem != null) {
				if (debugTrace) {
					writeTraceRecord(pElem.getName(), code[0], pElem.getDblTraceParameter(),
							String.format("Pop handle %d do action", hdl));
				}
				pElem.doPendingAction(code[0], proxyHdl[0]);
				result = true;
				pElem = pop(t, code, proxyHdl, hdl);
			}
		}
		return result;
	}

	private double timeRecToTime(TimeRec timeRec) {
		return timeRec.hour * 3600.0 + timeRec.sec;
	}

	public void setTrace(final boolean value) {
		debugTrace = value;

		if (debugTrace) {
			try {
				traceFile = new File(DSS.dataDirectory + "Trace_ControlQueue.csv");
				PrintWriter pw = new PrintWriter(traceFile);

				pw.println("\"Hour\", \"sec\", \"Control Iteration\", \"Element\", \"Action Code\", \"Trace Parameter\", \"Description\"");

				pw.close();
			} catch (IOException e) {
				DSS.doSimpleMsg("Error initialising control queue trace: " + e.getMessage(), 0);
			}
		}
	}

	public boolean isTrace() {
		return debugTrace;
	}

	public void showQueue(String fileName) {
		ActionRecord pAction;

		try {
			PrintWriter pw = new PrintWriter(fileName);

			pw.println("Handle, Hour, Sec, ActionCode, ProxyDevRef, Device");

			for (int i = 0; i < actionList.size(); i++) {
				pAction = actionList.get(i);
				if (pAction != null) {
					pw.printf("%d, %d, %-.g, %d, %d, %s ",
							pAction.actionHandle,
							pAction.actionTime.hour,
							pAction.actionTime.sec,
							pAction.actionCode,
							pAction.proxyHandle,
							pAction.controlElement.getName());
					pw.println();
				}
			}
			pw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing control queue: " + e.getMessage(), 0);
		} finally {
			Util.fireOffEditor(fileName);
		}
	}

	private void writeTraceRecord(String elementName, int code, double traceParameter, String s) {
		try {
			if (!DSS.inShowResults) {
				PrintWriter pw = new PrintWriter(traceFile);

				pw.printf("%d, %.6g, %d, %s, %d, %-.g, %s",
						DSS.activeCircuit.getSolution().getIntHour(),
						DSS.activeCircuit.getSolution().getDynaVars().t,
						DSS.activeCircuit.getSolution().getControlIteration(),
						elementName,
						code,
						traceParameter,
						s);
				pw.close();
			}
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing control queue trace: " + e.getMessage(), 0);
		}
	}

	public void delete(int hdl) {
		for (int i = 0; i < actionList.size(); i++) {
 			if (actionList.get(i).actionHandle == hdl) {
				deleteFromQueue(i, false);
				return;
			}
		}
	}

}
