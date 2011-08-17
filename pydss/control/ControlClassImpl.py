from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.common.impl.CktElementClassImpl import (CktElementClassImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.ControlClass import (ControlClass,)


class ControlClassImpl(CktElementClassImpl, ControlClass):
    numControlClassProps = None

    def __init__(self):
        super(ControlClassImpl, self)()
        self.numControlClassProps = 0
        self.classType = DSSClassDefs.CTRL_ELEMENT

    def countProperties(self):
        self.numProperties = self.numProperties + self.numControlClassProps
        super(ControlClassImpl, self).countProperties()

    def defineProperties(self):
        self.activeProperty = self.activeProperty + self.numControlClassProps
        super(ControlClassImpl, self).defineProperties()

    def classEdit(self, activeControlObj, paramPointer):
        if paramPointer >= 0:
            super(ControlClassImpl, self).classEdit(activeControlObj, paramPointer - self.numControlClassProps)
        return 0

    def classMakeLike(self, otherObj):
        ControlElemImpl(otherObj)

    def getNumControlClassProps(self):
        return self.numControlClassProps

    def setNumControlClassProps(self, num):
        self.numControlClassProps = num
