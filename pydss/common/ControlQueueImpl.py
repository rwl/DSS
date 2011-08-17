from __pyjamas__ import (ARGERROR,)
from dss.common.ControlQueue import (ControlQueue,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
# from java.io.BufferedWriter import (BufferedWriter,)
# from java.io.FileWriter import (FileWriter,)
# from java.io.IOException import (IOException,)
# from java.util.ArrayList import (ArrayList,)
# from java.util.List import (List,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)


class ControlQueueImpl(object, ControlQueue):

    class TimeRec(object):
        hour = None
        sec = None

    class ActionRecord(object):
        actionTime = None
        actionCode = None
        actionHandle = None
        proxyHandle = None
        controlElement = None

    actionList = None
    debugTrace = None
    traceFile = None
    ctrlHandle = None

    def push(self, *args):
        """None
        ---
        Add a control action to the queue, sorted by lowest time first.

        @return handle to the action
        """
        _0 = args
        _1 = len(args)
        if _1 == 5:
            if isinstance(_0[2], ControlAction):
                hour, sec, code, proxyHdl, owner = _0
                return self.push(hour, sec, code.code(), proxyHdl, owner)
            else:
                hour, sec, code, proxyHdl, owner = _0
                timeRec = self.TimeRec()
                self.ctrlHandle += 1
                # just a serial number
                # Normalize the time
                hr = hour
                s = sec
                if s > 3600.0:
                    while s >= 3600.0:
                        hr = hr + 1
                        s = s - 3600.0
                timeRec.hour = hr
                timeRec.sec = s
                thisActionTime = self.timeRecToTime(timeRec)
                pAction = self.ActionRecord()
                # make a new action
                # Insert the action in the list in order of time
                actionInserted = False
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < len(self.actionList) - 1):
                        break
                    # TODO Check zero based indexing
                    if thisActionTime <= self.timeRecToTime(self.actionList[i].actionTime):
                        self.actionList.add(i, pAction)
                        actionInserted = True
                        break
                if not actionInserted:
                    self.actionList.add(pAction)
                pAction.actionTime = timeRec
                pAction.actionCode = code
                pAction.actionHandle = self.ctrlHandle
                pAction.proxyHandle = proxyHdl
                pAction.controlElement = owner
                if self.debugTrace:
                    self.writeTraceRecord(owner.getName(), code, owner.getDblTraceParameter(), String.format.format('Handle %d pushed onto stack', self.ctrlHandle))
                return self.ctrlHandle
        else:
            raise ARGERROR(5, 5)

    def clear(self):
        self.actionList.clear()

    def __init__(self):
        super(ControlQueueImpl, self)()
        self.actionList = list()
        self.actionList.clear()
        self.ctrlHandle = 0
        # just a serial number
        self.debugTrace = False

    def doAllActions(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.actionList) - 1):
                break
            # TODO Check zero based indexing
            action = self.actionList[i]
            action.controlElement.doPendingAction(action.actionCode, action.proxyHandle)
            # TODO Check translation
        self.actionList.clear()

    def doNearestActions(self, hour, sec):
        """Do only those actions with the same delay time as the first action return time."""
        code = MutableInt()
        hdl = MutableInt()
        proxyHdl = MutableInt()
        result = False
        if len(self.actionList) > 0:
            t = self.actionList[0].actionTime
            hour.setValue(t.hour)
            sec.setValue(t.sec)
            pElem = self.pop(t, code, proxyHdl, hdl)
            while pElem is not None:
                if self.debugTrace:
                    self.writeTraceRecord(pElem.getName(), code.intValue(), pElem.getDblTraceParameter(), String.format.format('Pop Handle %d Do Nearest Action', hdl))
                pElem.doPendingAction(code.intValue(), proxyHdl.intValue())
                result = True
                pElem = self.pop(t, code, proxyHdl, hdl)
        return result

    def isEmpty(self):
        if len(self.actionList) == 0:
            return True
        else:
            return False

    def pop(self, actionTime, code, proxyHdl, hdl):
        """Pop off next control action with an action time <= actionTime (sec)."""
        result = None
        t = self.timeRecToTime(actionTime)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.actionList) - 1):
                break
            # TODO Check zero based indexing
            action = self.actionList[i]
            if self.timeRecToTime(action.actionTime) <= t:
                result = action.controlElement
                code.setValue(action.actionCode)
                proxyHdl.setValue(action.proxyHandle)
                hdl.setValue(action.actionHandle)
                self.deleteFromQueue(i, True)
                break
        return result

    def deleteFromQueue(self, i, popped):
        """Delete i-th element from the queue."""
        action = self.actionList[i]
        pElem = action.controlElement
        if self.debugTrace:
            if popped:
                s = 'by Pop function'
            else:
                s = 'by control device'
            self.writeTraceRecord(pElem.getName(), action.actionCode, pElem.getDblTraceParameter(), String.format.format('Handle %d deleted from queue %s', action.actionHandle, s))
        self.actionList.remove(i)

    def doActions(self, hour, sec):
        """Do actions with time <= t."""
        t = self.TimeRec()
        code = MutableInt()
        hdl = MutableInt()
        proxyHdl = MutableInt()
        result = False
        if len(self.actionList) > 0:
            t.hour = hour
            t.sec = sec
            pElem = self.pop(t, code, proxyHdl, hdl)
            while pElem is not None:
                if self.debugTrace:
                    self.writeTraceRecord(pElem.getName(), code.intValue(), pElem.getDblTraceParameter(), String.format.format('Pop handle %d do action', hdl))
                pElem.doPendingAction(code.intValue(), proxyHdl.intValue())
                result = True
                pElem = self.pop(t, code, proxyHdl, hdl)
        return result

    def timeRecToTime(self, timeRec):
        return (timeRec.hour * 3600.0) + timeRec.sec

    def setTrace(self, Value):
        self.debugTrace = Value
        if self.debugTrace:
            # TODO Auto-generated catch block
            try:
                self.traceFile = FileWriter(DSSGlobals.DSSDataDirectory + 'Trace_ControlQueue.csv')
                traceBuffer = BufferedWriter(self.traceFile)
                traceBuffer.write('\"Hour\", \"sec\", \"Control Iteration\", \"Element\", \"Action Code\", \"Trace Parameter\", \"Description\"')
                traceBuffer.newLine()
                traceBuffer.close()
            except IOException, e:
                e.printStackTrace()

    def getTrace(self):
        return self.debugTrace

    def showQueue(self, fileName):
        try:
            f = FileWriter(fileName)
            fileBuffer = BufferedWriter(f)
            fileBuffer.write('Handle, Hour, Sec, ActionCode, ProxyDevRef, Device')
            fileBuffer.newLine()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(self.actionList)):
                    break
                pAction = self.actionList[i]
                if pAction is not None:
                    fileBuffer.write(String.format.format('%d, %d, %-.g, %d, %d, %s ', pAction.actionHandle, pAction.actionTime.hour, pAction.actionTime.sec, pAction.actionCode, pAction.proxyHandle, pAction.controlElement.getName()))
                    fileBuffer.newLine()
            fileBuffer.close()
        except IOException, e:
            e.printStackTrace()
        finally:
            Utilities.fireOffEditor(fileName)
        # TODO Auto-generated catch block

    def writeTraceRecord(self, elementName, code, traceParameter, s):
        # TODO Auto-generated catch block
        try:
            if not DSSGlobals.inShowResults:
                traceBuffer = BufferedWriter(self.traceFile)
                traceBuffer.write(String.format.format('%d, %.6g, %d, %s, %d, %-.g, %s', DSSGlobals.activeCircuit.getSolution().getIntHour(), DSSGlobals.activeCircuit.getSolution().getDynaVars().t, DSSGlobals.activeCircuit.getSolution().getControlIteration(), elementName, code, traceParameter, s))
                traceBuffer.close()
        except IOException, e:
            e.printStackTrace()

    def delete(self, hdl):
        """Delete queue item by handle."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.actionList) - 1):
                break
            # TODO Check zero based indexing
            if self.actionList[i].actionHandle == hdl:
                self.deleteFromQueue(i, False)
                return
