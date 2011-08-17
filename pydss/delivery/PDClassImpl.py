from dss.common.impl.CktElementClassImpl import (CktElementClassImpl,)
from dss.delivery.PDClass import (PDClass,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class PDClassImpl(CktElementClassImpl, PDClass):
    numPDClassProps = None

    def __init__(self):
        super(PDClassImpl, self)()
        self.numPDClassProps = 5
        self.classType = DSSClassDefs.PD_ELEMENT

    def countProperties(self):
        self.numProperties = self.numProperties + self.numPDClassProps
        super(PDClassImpl, self).countProperties()

    def defineProperties(self):
        """Define the properties for the base power delivery element class."""
        self.propertyName[self.activeProperty + 1] = 'normamps'
        # TODO Check zero based indexing
        self.propertyName[self.activeProperty + 2] = 'emergamps'
        self.propertyName[self.activeProperty + 3] = 'faultrate'
        self.propertyName[self.activeProperty + 4] = 'pctperm'
        self.propertyName[self.activeProperty + 5] = 'repair'
        self.propertyHelp[self.activeProperty + 1] = 'Normal rated current.'
        self.propertyHelp[self.activeProperty + 2] = 'Maximum current.'
        self.propertyHelp[self.activeProperty + 3] = 'No. of failures per year.'
        self.propertyHelp[self.activeProperty + 4] = 'Percent of failures that become permanent.'
        self.propertyHelp[self.activeProperty + 5] = 'Hours to repair.'
        self.activeProperty = self.activeProperty + self.numPDClassProps
        super(PDClassImpl, self).defineProperties()

    def classEdit(self, activePDObj, paramPointer):
        # continue parsing with contents of parser
        parser = Parser.getInstance()
        if paramPointer >= 0:
            PDElem = activePDObj
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    PDElem.setNormAmps(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    PDElem.setEmergAmps(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    PDElem.setFaultRate(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    PDElem.setPctPerm(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    PDElem.setHrsToRepair(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    super(PDClassImpl, self).classEdit(activePDObj, paramPointer - self.numPDClassProps)
                    break
                break
        return 0

    def classMakeLike(self, otherObj):
        otherPDObj = otherObj
        PDElem = DSSGlobals.activeDSSObject
        PDElem.setNormAmps(otherPDObj.getNormAmps())
        PDElem.setEmergAmps(otherPDObj.getEmergAmps())
        PDElem.setFaultRate(otherPDObj.getFaultRate())
        PDElem.setPctPerm(otherPDObj.getPctPerm())
        PDElem.setHrsToRepair(otherPDObj.getHrsToRepair())
        super(PDClassImpl, self).classMakeLike(otherObj)

    def getNumPDClassProps(self):
        return self.numPDClassProps

    def setNumPDClassProps(self, num):
        self.numPDClassProps = num
