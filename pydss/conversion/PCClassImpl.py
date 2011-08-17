from dss.common.impl.CktElementClassImpl import (CktElementClassImpl,)
from dss.conversion.PCClass import (PCClass,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class PCClassImpl(CktElementClassImpl, PCClass):
    numPCClassProps = None

    def __init__(self):
        super(PCClassImpl, self)()
        self.numPCClassProps = 1
        self.classType = DSSClassDefs.PC_ELEMENT

    def countProperties(self):
        """Add no. of intrinsic properties."""
        self.numProperties = self.numProperties + self.numPCClassProps
        super(PCClassImpl, self).countProperties()

    def defineProperties(self):
        """Add properties of this class to propName.

        Define the properties for the base power delivery element class.
        """
        self.propertyName[self.activeProperty + 1] = 'spectrum'
        self.propertyHelp[self.activeProperty + 1] = 'Name of harmonic spectrum for this device.'
        self.activeProperty = self.activeProperty + self.numPCClassProps
        super(PCClassImpl, self).defineProperties()

    def classEdit(self, activePCObj, paramPointer):
        result = 0
        # continue parsing with contents of parser
        if paramPointer >= 0:
            pElem = activePCObj
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    pElem.setSpectrum(Parser.getInstance().makeString())
                    break
                if True:
                    _1 = True
                    super(PCClassImpl, self).classEdit(activePCObj, paramPointer - self.numPCClassProps)
                    break
                break
        return result

    def classMakeLike(self, otherObj):
        otherPCObj = otherObj
        pElem = DSSGlobals.activeDSSObject
        pElem.setSpectrum(otherPCObj.getSpectrum())
        pElem.setSpectrumObj(otherPCObj.getSpectrumObj())
        super(PCClassImpl, self).classMakeLike(otherObj)
