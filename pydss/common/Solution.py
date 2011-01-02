# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

class Solution(object):

    def __init__(self, year=0, preserveNodeVoltages=False,
            frequencyChanged=False, frequency=0.0, mode="Snapshot",
            solutionAbort=False, solutionInitialised=False,
            seriesYInvalid=False, systemYChanged=False, loadModel="Powerflow",
            voltageBaseChanged=False, harmonicModel=False, dynamicModel=False,
            useAuxillaryCurrents=False, loadsNeedUpdating=False, iteration=0,
            maxIterations=0, maxError=0.0, convergenceTolerance=0.0,
            converged=False, controlIteration=0, maxControlIterations=0,
            controlMode="EventDriven", controlActionsDone=False,
            mostIterationsDone=0, algorithm="NormalSolve",
            lastSolutionWasDirect=False, circuit=None, nodeV=None,
            currents=None):
        """Initialises a new 'Solution' instance.
        """
        self.year = year

        self.preserveNodeVoltages = preserveNodeVoltages

        self.frequencyChanged = frequencyChanged

        self.frequency = frequency

        #: Values are: "Snapshot", "Daily", "Direct", "Dutycycle", "Dynamic",
        #  "Harmonic", "MonteCarlo1", "MonteCarlo2", "MonteCarlo3",
        #  "FaultStudy", "Yearly", "MonteFault", "Peakday", "LoadDuration1",
        #  "LoadDuration2", "AutoAdd"
        self.mode = mode

        self.solutionAbort = solutionAbort

        self.solutionInitialised = solutionInitialised

        self.seriesYInvalid = seriesYInvalid

        self.systemYChanged = systemYChanged

        #: Values are: "Powerflow", "Admittance"
        self.loadModel = loadModel

        self.voltageBaseChanged = voltageBaseChanged

        self.harmonicModel = harmonicModel

        self.dynamicModel = dynamicModel

        self.useAuxillaryCurrents = useAuxillaryCurrents

        self.loadsNeedUpdating = loadsNeedUpdating

        self.iteration = iteration

        self.maxIterations = maxIterations

        self.maxError = maxError

        self.convergenceTolerance = convergenceTolerance

        self.converged = converged

        self.controlIteration = controlIteration

        self.maxControlIterations = maxControlIterations

        #: Values are: "EventDriven", "TimeDriven", "Static"
        self.controlMode = controlMode

        self.controlActionsDone = controlActionsDone

        self.mostIterationsDone = mostIterationsDone

        #: Values are: "NormalSolve", "NewtonSolve"
        self.algorithm = algorithm

        self.lastSolutionWasDirect = lastSolutionWasDirect

        self._circuit = None
        self.circuit = circuit

        self.nodeV = nodeV

        self.currents = currents


    def getcircuit(self):

        return self._circuit

    def setcircuit(self, value):
        if self._circuit is not None:
            self._circuit._solution = None

        self._circuit = value
        if self._circuit is not None:
            self._circuit.solution = None
            self._circuit._solution = self

    circuit = property(getcircuit, setcircuit)

    def solve(self):
        pass
    def solveCircuit(self):
        pass
    def solveYDirect(self):
        pass
    def doPowerFlowSolution(self):
        pass
    def doNewtonSolution(self):
        pass
    def restoreNodeVFromVBus(self):
        pass
    def getSourceInjCurrents(self):
        pass
    def getPCInjCurrents(self):
        pass
    def setGeneratorDispRef(self):
        pass
    def convergenceCheck(self):
        pass
    def sampleControlDevices(self):
        pass
    def checkFaultStatus(self):
        pass
