from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.DSSClasses import (DSSClasses,)


class DSSClassesImpl(object, DSSClasses):

    def setNew(self, value):
        DSSGlobals.DSSClassList.add(value)
        # add to class list
        DSSGlobals.activeDSSClass = value
        # declare to be active
        DSSGlobals.classNames.add(DSSGlobals.activeDSSClass.getName())
        # add to classname list
