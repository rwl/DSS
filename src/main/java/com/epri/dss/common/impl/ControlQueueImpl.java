package com.epri.dss.common.impl;

import java.io.PrintStream;
import java.util.List;

import com.epri.dss.common.ControlQueue;
import com.epri.dss.control.ControlElem;
import com.epri.dss.control.impl.ControlElemImpl.ControlAction;

public class ControlQueueImpl implements ControlQueue {

	public class TimeRec {
		public int Hour;
		public double Sec;
	}

	public class ActionRecord {
		public TimeRec ActionTime;
		public int ActionCode;
		public int ActionHandle;
		public int ProxyHandle;
		public ControlElem ControlElement;
	}

	private List<ActionRecord> ActionList;
	private boolean DebugTrace;
	private PrintStream Tracefile;
	private int ctrlHandle;

	public int push(int Hour, double Sec, ControlAction Code, int ProxyHdl,
			ControlElem Owner) {
		
		ControlAction[] actions = ControlAction.values();

		for (int i = 0; i < actions.length; i++) 
			if (actions[i].equals(Code))
				return push(Hour, Sec, i, ProxyHdl, Owner);
		
		return -1;
	}

	public int push(int Hour, double Sec, int Code, int ProxyHdl,
			ControlElem Owner) {
		return 0;
	}

	public ControlQueueImpl() {
		// TODO Auto-generated constructor stub
	}

	/* Pop action from queue <= given time */
	private ControlElem pop(TimeRec ActionTime, int Code, int ProxyHdl,
			int Hdl) {
		return null;
	}

	private void deleteFromQueue(int i, boolean popped) {

	}

	private double timeRecToTime(TimeRec TRec) {
		return 0.0;
	}

	public void setTrace(boolean Value) {

	}

	public boolean getTrace() {
		return DebugTrace;
	}

	private void writeTraceRecord(String ElementName, int Code,
			double TraceParameter, String s) {

	}

	public void clear() {

	}

	public void doAllActions() {

	}

	/* Do only actions with lowest time */
	public boolean doNearestActions(int Hour, double Sec) {
		return false;
	}

	/* Do actions with time <= t */
	public boolean doActions(int Hour, double sec) {
		return false;
	}

	public boolean isEmpty() {
		return false;
	}

	/* Delete queue item by handle */
	public void delete(int Hdl) {

	}

	public void showQueue(String FileName) {

	}

}
