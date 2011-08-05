package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.common.ControlQueue;
import com.epri.dss.control.ControlElem;
import com.epri.dss.control.impl.ControlAction;

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
	private FileWriter TraceFile;
	private int ctrlHandle;

	public int push(int Hour, double Sec, ControlAction Code, int ProxyHdl, final ControlElem Owner) {

		return push(Hour, Sec, Code.code(), ProxyHdl, Owner);
	}

	/**
	 * Add a control action to the queue, sorted by lowest time first.
	 *
	 * @return handle to the action
	 */
	public int push(int Hour, double Sec, int Code, int ProxyHdl, final ControlElem Owner) {
		int Hr;
		double ThisActionTime, S;
		TimeRec TRec = null;
		ActionRecord pAction;
		boolean ActionInserted;

		ctrlHandle += 1; // just a serial number

		/* Normalize the time */
		Hr = Hour;
		S  = Sec;
		if (S > 3600.0)
			while (S >= 3600.0) {
				Hr = Hr + 1;
				S  = S - 3600.0;
			}

		TRec.Hour = Hr;
		TRec.Sec  = S;

		ThisActionTime = timeRecToTime(TRec);
		pAction = new ActionRecord();  // Make a new Action

		/* Insert the action in the list in order of time */
		ActionInserted = false;
		for (int i = 0; i < ActionList.size() - 1; i++) {  // TODO Check zero based indexing
			if (ThisActionTime <= timeRecToTime( ((ActionRecord) ActionList.get(i)).ActionTime )) {
				ActionList.add(i, pAction);
				ActionInserted = true;
				break;
			}
		}

		if (!ActionInserted)
			ActionList.add(pAction);

		pAction.ActionTime = TRec;
		pAction.ActionCode = Code;
		pAction.ActionHandle = ctrlHandle;
		pAction.ProxyHandle = ProxyHdl;
		pAction.ControlElement = Owner;

		if (DebugTrace)
			writeTraceRecord(Owner.getName(), Code, Owner.getDblTraceParameter(),
					String.format("Handle %d Pushed onto Stack", ctrlHandle));

		return ctrlHandle;
	}

	public void clear() {
		ActionList.clear();
	}

	public ControlQueueImpl() {
		super();
		ActionList = new ArrayList<ControlQueueImpl.ActionRecord>();
		ActionList.clear();

		ctrlHandle = 0;

		DebugTrace = false;
	}

	public void doAllActions() {
		for (int i = 0; i < ActionList.size() - 1; i++) {  // TODO Check zero based indexing
			ActionRecord action = ActionList.get(i);
			action.ControlElement.doPendingAction(action.ActionCode, action.ProxyHandle);  // TODO Check translation
		}
		ActionList.clear();
	}

	/**
	 * Do only those actions with the same delay time as the first action return time.
	 */
	public boolean doNearestActions(MutableInt Hour, MutableDouble Sec) {
		ControlElem pElem;
		TimeRec t;
		MutableInt Code = new MutableInt();
		MutableInt hdl = new MutableInt();
		MutableInt ProxyHdl = new MutableInt();

		boolean Result = false;
		if (ActionList.size() > 0) {
			t = ActionList.get(0).ActionTime;
			Hour.setValue(t.Hour);
			Sec.setValue(t.Sec);
			pElem = pop(t, Code, ProxyHdl, hdl);
			while (pElem != null) {
				if (DebugTrace)
					writeTraceRecord(pElem.getName(), Code.intValue(), pElem.getDblTraceParameter(),
							String.format("Pop Handle %d Do Nearest Action", hdl));
				pElem.doPendingAction(Code.intValue(), ProxyHdl.intValue());
				Result = true;
				pElem = pop(t, Code, ProxyHdl, hdl);
			}
		}
		return Result;
	}

	public boolean isEmpty() {
		if (ActionList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Pop off next control action with an action time <= ActionTime (sec).
	 */
	private ControlElem pop(TimeRec ActionTime, MutableInt Code, MutableInt ProxyHdl, MutableInt Hdl) {
		ControlElem Result = null;

		double t = timeRecToTime(ActionTime);

		for (int i = 0; i < ActionList.size() - 1; i++) {  // TODO Check zero based indexing
			ActionRecord action = ActionList.get(i);
			if (timeRecToTime(action.ActionTime) <= t) {
				Result   = action.ControlElement;
				Code.setValue(action.ActionCode);
				ProxyHdl.setValue(action.ProxyHandle);
				Hdl.setValue(action.ActionHandle);
				deleteFromQueue(i, true);
				break;
			}
		}

		return Result;
	}

	/**
	 * Delete i-th element from the queue.
	 */
	private void deleteFromQueue(int i, boolean popped) {
		ControlElem pElem;
		String S;

		ActionRecord action = ActionList.get(i);

		pElem = action.ControlElement;
		if (DebugTrace) {
			if (popped) {
				S = "by Pop function";
			} else {
				S = "by control device";
			}
			writeTraceRecord(pElem.getName(), action.ActionCode, pElem.getDblTraceParameter(),
					String.format("Handle %d deleted from Queue %s", action.ActionHandle, S));
		}

		ActionList.remove(i);
	}

	/**
	 * Do actions with time <= t.
	 */
	public boolean doActions(int Hour, double Sec) {
		TimeRec t = new TimeRec();
		MutableInt Code = new MutableInt();
		MutableInt hdl = new MutableInt();
		MutableInt ProxyHdl = new MutableInt();

		boolean Result = false;
		if (ActionList.size() > 0) {

		t.Hour = Hour;
		t.Sec  = Sec;
		ControlElem pElem = pop(t, Code, ProxyHdl, hdl);
		while (pElem != null) {
			if (DebugTrace)
				writeTraceRecord(pElem.getName(), Code.intValue(), pElem.getDblTraceParameter(),
						String.format("Pop Handle %d Do Action", hdl));
			pElem.doPendingAction(Code.intValue(), ProxyHdl.intValue());
			Result = true;
			pElem = pop(t, Code, ProxyHdl, hdl);
		}
		}

		return Result;
	}

	private double timeRecToTime(TimeRec TRec) {
		return TRec.Hour * 3600.0 + TRec.Sec;
	}

	public void setTrace(final boolean Value) {
		DebugTrace = Value;

		if (DebugTrace) {
			try {
				TraceFile = new FileWriter(DSSGlobals.getInstance().getDSSDataDirectory() + "Trace_ControlQueue.csv");
				BufferedWriter TraceBuffer = new BufferedWriter(TraceFile);
				TraceBuffer.write("\"Hour\", \"sec\", \"Control Iteration\", \"Element\", \"Action Code\", \"Trace Parameter\", \"Description\"");
				TraceBuffer.newLine();
				TraceBuffer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean getTrace() {
		return DebugTrace;
	}

	public void showQueue(String FileName) {
		FileWriter F;
		ActionRecord pAction;

		try {
			F = new FileWriter(FileName);
			BufferedWriter FileBuffer = new BufferedWriter(F);

			FileBuffer.write("Handle, Hour, Sec, ActionCode, ProxyDevRef, Device");
			FileBuffer.newLine();

			for (int i = 0; i < ActionList.size(); i++) {
				pAction = ActionList.get(i);
				if (pAction != null) {
					FileBuffer.write(String.format("%d, %d, %-.g, %d, %d, %s ",
							pAction.ActionHandle, pAction.ActionTime.Hour, pAction.ActionTime.Sec, pAction.ActionCode, pAction.ProxyHandle, pAction.ControlElement.getName()));
					FileBuffer.newLine();
				}
			}
			FileBuffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utilities.fireOffEditor(FileName);
		}
	}

	private void writeTraceRecord(String ElementName, int Code, double TraceParameter, String S) {

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (!Globals.isInShowResults()) {
				BufferedWriter TraceBuffer = new BufferedWriter(TraceFile);

				TraceBuffer.write(String.format("%d, %.6g, %d, %s, %d, %-.g, %s",
						Globals.getActiveCircuit().getSolution().getIntHour(),
						Globals.getActiveCircuit().getSolution().getDynaVars().t,
						Globals.getActiveCircuit().getSolution().getControlIteration(),
						ElementName,
						Code,
						TraceParameter,
						S));

				TraceBuffer.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Delete queue item by handle.
	 */
	public void delete(int Hdl) {
		for (int i = 0; i < ActionList.size() - 1; i++)  // TODO Check zero based indexing
			if (ActionList.get(i).ActionHandle == Hdl) {
				deleteFromQueue(i, false);
				return;
			}
	}

}
