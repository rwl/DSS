from dss.common.impl.CktElementClassImpl import (CktElementClassImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.meter.MeterClass import (MeterClass,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.meter.impl.MeterElementImpl import (MeterElementImpl,)


class MeterClassImpl(CktElementClassImpl, MeterClass):
    numMeterClassProps = None

    def __init__(self):
        super(MeterClassImpl, self)()
        self.numMeterClassProps = 0
        self.classType = DSSClassDefs.METER_ELEMENT

    def countProperties(self):
        self.numProperties = self.numProperties + self.numMeterClassProps
        super(MeterClassImpl, self).countProperties()

    def defineProperties(self):
        self.activeProperty = self.activeProperty + self.numMeterClassProps
        super(MeterClassImpl, self).defineProperties()

    def classEdit(self, activeMeterObj, paramPointer):
        if paramPointer > 0:
            super(MeterClassImpl, self).classEdit(activeMeterObj, paramPointer - self.numMeterClassProps)
        return 0

    def classMakeLike(self, otherObj):
        MeterElementImpl(otherObj)

    def resetAll(self):
        DSSGlobals.doSimpleMsg('Programming Error: Base MeterClass.resetAll reached for class: ' + self.getName(), 760)

    def sampleAll(self):
        """Force all monitors to take a sample."""
        DSSGlobals.doSimpleMsg('Programming Error: Base MeterClass.sampleAll reached for class: ' + self.getName(), 761)

    def saveAll(self):
        """Force all monitors to save their buffers to disk."""
        DSSGlobals.doSimpleMsg('Programming Error: Base MeterClass.saveAll reached for Class: ' + self.getName(), 762)

    def getNumMeterClassProps(self):
        return self.numMeterClassProps

    def setNumMeterClassProps(self, numProps):
        self.numMeterClassProps = numProps
