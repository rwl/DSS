package com.ncond.dss.common.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ncond.dss.common.ControlQueue;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.control.impl.ControlAction;

public class ControlQueueImpl implements ControlQueue {

	public class TimeRec {
		public int hour;
		public double sec;
	}

	public class ActionRecord {
		public TimeRec actionTime;
		public int actionCode;
		public int actionHandle;
		public int proxyHandle;
		public ControlElem controlElement;
	}

	private List<ActionRecord> actionList;
	private boolean debugTrace;
	private FileWriter traceFile;
	private int ctrlHandle;

	public int push(int hour, double sec, ControlAction code, int proxyHdl, final ControlElem owner) {
		return push(hour, sec, code.code(), proxyHdl, owner);
	}

	/**
	 * Add a control action to the queue, sorted by lowest time first.
	 *
	 * @return handle to the action
	 */
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
				hr = hr + 1;
				s  = s - 3600.0;
			}
		}

		timeRec.hour = hr;
		timeRec.sec  = s;

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

		if (debugTrace)
			writeTraceRecord(owner.getName(), code, owner.getDblTraceParameter(),
					String.format("Handle %d pushed onto stack", ctrlHandle));

		return ctrlHandle;
	}

	public void clear() {
		actionList.clear();
	}

	public ControlQueueImpl() {
		super();
		actionList = new ArrayList<ControlQueueImpl.ActionRecord>();
		actionList.clear();

		ctrlHandle = 0;  // just a serial number

		debugTrace = false;
	}

	public void doAllActions() {
		ActionRecord action;
		for (int i = 0; i < actionList.size(); i++) {
			action = actionList.get(i);
			action.controlElement.doPendingAction(action.actionCode, action.proxyHandle);
		}
		actionList.clear();
	}

	/**
	 * Do only those actions with the same delay time as the first action return time.
	 */
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
				if (debugTrace)
					writeTraceRecord(pElem.getName(), code[0], pElem.getDblTraceParameter(),
							String.format("Pop Handle %d Do Nearest Action", hdl));
				pElem.doPendingAction(code[0], proxyHdl[0]);
				result = true;
				pElem = pop(t, code, proxyHdl, hdl);
			}
		}
		return result;
	}

	public boolean isEmpty() {
		if (actionList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Pop off next control action with an action time <= actionTime (sec).
	 */
	private ControlElem pop(TimeRec actionTime, int[] code, int[] proxyHdl, int[] hdl) {

		ControlElem result = null;
		ActionRecord action;

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
			if (popped) {
				s = "by Pop function";
			} else {
				s = "by control device";
			}
			writeTraceRecord(pElem.getName(), action.actionCode, pElem.getDblTraceParameter(),
					String.format("Handle %d deleted from queue %s", action.actionHandle, s));
		}

		actionList.remove(i);
	}

	/**
	 * Do actions with time <= t.
	 */
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
				if (debugTrace)
					writeTraceRecord(pElem.getName(), code[0], pElem.getDblTraceParameter(),
							String.format("Pop handle %d do action", hdl));
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

	public void setTrace(final boolean Value) {
		debugTrace = Value;

		if (debugTrace) {
			try {
				traceFile = new FileWriter(DSS.DSSDataDirectory + "Trace_ControlQueue.csv");
				BufferedWriter traceBuffer = new BufferedWriter(traceFile);
				traceBuffer.write("\"Hour\", \"sec\", \"Control Iteration\", \"Element\", \"Action Code\", \"Trace Parameter\", \"Description\"");
				traceBuffer.newLine();
				traceBuffer.close();
			} catch (IOException e) {
				DSS.doSimpleMsg("Error initialising control queue trace: " + e.getMessage(), 0);
			}
		}
	}

	public boolean getTrace() {
		return debugTrace;
	}

	public void showQueue(String fileName) {
		FileWriter f;
		ActionRecord pAction;

		try {
			f = new FileWriter(fileName);
			BufferedWriter fileBuffer = new BufferedWriter(f);

			fileBuffer.write("Handle, Hour, Sec, ActionCode, ProxyDevRef, Device");
			fileBuffer.newLine();

			for (int i = 0; i < actionList.size(); i++) {
				pAction = actionList.get(i);
				if (pAction != null) {
					fileBuffer.write(String.format("%d, %d, %-.g, %d, %d, %s ",
							pAction.actionHandle, pAction.actionTime.hour, pAction.actionTime.sec, pAction.actionCode, pAction.proxyHandle, pAction.controlElement.getName()));
					fileBuffer.newLine();
				}
			}
			fileBuffer.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing control queue: " + e.getMessage(), 0);
		} finally {
			Util.fireOffEditor(fileName);
		}
	}

	private void writeTraceRecord(String elementName, int code, double traceParameter, String s) {

		try {
			if (!DSS.inShowResults) {
				BufferedWriter traceBuffer = new BufferedWriter(traceFile);

				traceBuffer.write(String.format("%d, %.6g, %d, %s, %d, %-.g, %s",
						DSS.activeCircuit.getSolution().getIntHour(),
						DSS.activeCircuit.getSolution().getDynaVars().t,
						DSS.activeCircuit.getSolution().getControlIteration(),
						elementName,
						code,
						traceParameter,
						s));

				traceBuffer.close();
			}
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing control queue trace: " + e.getMessage(), 0);
		}
	}

	/**
	 * Delete queue item by handle.
	 */
	public void delete(int hdl) {
		for (int i = 0; i < actionList.size(); i++) {
 			if (actionList.get(i).actionHandle == hdl) {
				deleteFromQueue(i, false);
				return;
			}
		}
	}

}
