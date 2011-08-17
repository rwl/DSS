from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.SolutionObjImpl import (SolutionObjImpl,)
from dss.common.Solution import (Solution,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)


class SolutionImpl(DSSClassImpl, Solution):
    activeSolutionObj = None
    # private static File fDebug;
    NumPropsThisClass = 1

    def __init__(self):
        super(SolutionImpl, self)()
        self.className = 'Solution'
        self.classType = DSSClassDefs.DSS_OBJECT + DSSClassDefs.HIDDEN_ELEMENT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = '-------'
        # define property help values
        self.propertyHelp[0] = 'Use Set Command to set Solution properties.'
        self.activeProperty = self.NumPropsThisClass - 1
        super(SolutionImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        # make a new solution object and add it to solution class list
        self.activeSolutionObj = SolutionObjImpl(self, objName)
        # this one is different than the rest of the objects
        return self.addObjectToList(self.activeSolutionObj)

    def edit(self):
        self.activeSolutionObj = DSSGlobals.activeCircuit.getSolution()
        # This is all we do here now...
        self.activeSolutionObj.solve()
        return 0

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Solution.init()', -1)
        return 0
