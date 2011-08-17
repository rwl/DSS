from dss.general.impl.XYCurveImpl import (XYCurveImpl,)
from dss.control.impl.RegControlImpl import (RegControlImpl,)
from dss.delivery.impl.LineImpl import (LineImpl,)
from dss.delivery.impl.TransformerImpl import (TransformerImpl,)
from dss.meter.impl.MonitorImpl import (MonitorImpl,)
from dss.general.impl.LineCodeImpl import (LineCodeImpl,)
from dss.general.impl.TShapeImpl import (TShapeImpl,)
from dss.control.impl.RelayImpl import (RelayImpl,)
from dss.general.impl.LineSpacingImpl import (LineSpacingImpl,)
from dss.meter.impl.EnergyMeterImpl import (EnergyMeterImpl,)
from dss.general.impl.TCC_CurveImpl import (TCC_CurveImpl,)
from dss.control.impl.GenDispatcherImpl import (GenDispatcherImpl,)
from dss.general.impl.GrowthShapeImpl import (GrowthShapeImpl,)
from dss.delivery.impl.CapacitorImpl import (CapacitorImpl,)
from dss.general.impl.LoadShapeImpl import (LoadShapeImpl,)
from dss.general.impl.PriceShapeImpl import (PriceShapeImpl,)
from dss.conversion.impl.PVSystemImpl import (PVSystemImpl,)
from dss.conversion.impl.LoadImpl import (LoadImpl,)
from dss.conversion.impl.GeneratorImpl import (GeneratorImpl,)
from dss.control.impl.StorageControllerImpl import (StorageControllerImpl,)
from dss.delivery.impl.FuseImpl import (FuseImpl,)
from dss.control.impl.SwtControlImpl import (SwtControlImpl,)
from dss.general.impl.SpectrumImpl import (SpectrumImpl,)
from dss.general.impl.CNDataImpl import (CNDataImpl,)
from dss.general.impl.WireDataImpl import (WireDataImpl,)
from dss.common.impl.SolutionImpl import (SolutionImpl,)
from dss.control.impl.RecloserImpl import (RecloserImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.general.impl.TSDataImpl import (TSDataImpl,)
from dss.conversion.impl.StorageImpl import (StorageImpl,)
from dss.general.impl.XfmrCodeImpl import (XfmrCodeImpl,)
from dss.conversion.impl.ISourceImpl import (ISourceImpl,)
from dss.meter.impl.SensorImpl import (SensorImpl,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)
from dss.delivery.impl.FaultImpl import (FaultImpl,)
from dss.delivery.impl.GICTransformerImpl import (GICTransformerImpl,)
from dss.conversion.impl.VSourceImpl import (VSourceImpl,)
from dss.shared.impl.HashListImpl import (HashListImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.impl.LineGeometryImpl import (LineGeometryImpl,)
from dss.conversion.impl.GICLineImpl import (GICLineImpl,)
from dss.delivery.impl.ReactorImpl import (ReactorImpl,)
from dss.control.impl.CapControlImpl import (CapControlImpl,)
from dss.common.impl.DSSClassesImpl import (DSSClassesImpl,)
# from java.util.ArrayList import (ArrayList,)


class DSSClassDefs(object):
    BASECLASSMASK = 7
    CLASSMASK = 4294967288L
    # Basic element types
    NON_PCPD_ELEM = 1
    # a circuit element we don't want enumerated in PD and PC Elements
    PD_ELEMENT = 2
    PC_ELEMENT = 3
    CTRL_ELEMENT = 4
    METER_ELEMENT = 5
    HIDDEN_ELEMENT = 6
    # Specific element types
    MON_ELEMENT = 1 * 8
    DSS_OBJECT = 2 * 8
    # just a general DSS object, accessible to all circuits
    SOURCE = 3 * 8
    XFMR_ELEMENT = 4 * 8
    SUBSTATION = 5 * 8
    # not used
    LINE_ELEMENT = 6 * 8
    LOAD_ELEMENT = 7 * 8
    FAULTOBJECT = 8 * 8
    ENERGY_METER = 9 * 8
    GEN_ELEMENT = 10 * 8
    CAP_CONTROL = 11 * 8
    REG_CONTROL = 12 * 8
    CAP_ELEMENT = 13 * 8
    RELAY_CONTROL = 14 * 8
    RECLOSER_CONTROL = 15 * 8
    FUSE_CONTROL = 16 * 8
    REACTOR_ELEMENT = 17 * 8
    FEEDER_ELEMENT = 18 * 8
    GEN_CONTROL = 19 * 8
    SENSOR_ELEMENT = 20 * 8
    STORAGE_ELEMENT = 21 * 8
    STORAGE_CONTROL = 22 * 8
    SWT_CONTROL = 23 * 8
    PVSYSTEM_ELEMENT = 24 * 8
    GIC_TRANSFORMER = 25 * 8
    # special models for GIC studies
    GIC_LINE = 26 * 8
    numIntrinsicClasses = None
    numUserClasses = None

    @classmethod
    def getNumIntrinsicClasses(cls):
        return cls.numIntrinsicClasses

    @classmethod
    def setNumIntrinsicClasses(cls, num):
        cls.numIntrinsicClasses = num

    @classmethod
    def getNumUserClasses(cls):
        return cls.numUserClasses

    @classmethod
    def setNumUserClasses(cls, num):
        cls.numUserClasses = num

    @classmethod
    def createDSSClasses(cls):
        DSSGlobals.classNames = HashListImpl(25)
        # makes 5 sub lists
        DSSGlobals.DSSClassList = list(10)
        # 10 is initial size and increment
        DSSClassImpl.setDSSClasses(DSSClassesImpl())
        # class to handle defining DSS classes
        # General DSS objects, not circuit elements
        DSSGlobals.DSSObjs = list(25)
        # 25 is initial size and increment
        # Instantiate all intrinsic object classes
        # Generic object classes first in case others refer to them
        DSSClassImpl.getDSSClasses().setNew(SolutionImpl())
        DSSGlobals.solutionClass = DSSGlobals.activeDSSClass
        # this is a special class
        DSSClassImpl.getDSSClasses().setNew(LineCodeImpl())
        DSSGlobals.loadShapeClass = LoadShapeImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.loadShapeClass)
        DSSGlobals.TShapeClass = TShapeImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.TShapeClass)
        DSSGlobals.priceShapeClass = PriceShapeImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.priceShapeClass)
        DSSGlobals.XYCurveClass = XYCurveImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.XYCurveClass)
        DSSGlobals.growthShapeClass = GrowthShapeImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.growthShapeClass)
        DSSGlobals.TCC_CurveClass = TCC_CurveImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.TCC_CurveClass)
        DSSGlobals.spectrumClass = SpectrumImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.spectrumClass)
        DSSGlobals.wireDataClass = WireDataImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.wireDataClass)
        DSSGlobals.CNDataClass = CNDataImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.CNDataClass)
        DSSGlobals.TSDataClass = TSDataImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.TSDataClass)
        DSSClassImpl.getDSSClasses().setNew(LineGeometryImpl())
        DSSGlobals.lineSpacingClass = LineSpacingImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.lineSpacingClass)
        DSSClassImpl.getDSSClasses().setNew(XfmrCodeImpl())
        # Circuit element classes
        DSSClassImpl.getDSSClasses().setNew(LineImpl())
        DSSClassImpl.getDSSClasses().setNew(VSourceImpl())
        DSSClassImpl.getDSSClasses().setNew(ISourceImpl())
        DSSClassImpl.getDSSClasses().setNew(LoadImpl())
        DSSClassImpl.getDSSClasses().setNew(TransformerImpl())
        DSSClassImpl.getDSSClasses().setNew(RegControlImpl())
        DSSClassImpl.getDSSClasses().setNew(CapacitorImpl())
        DSSClassImpl.getDSSClasses().setNew(ReactorImpl())
        DSSClassImpl.getDSSClasses().setNew(CapControlImpl())
        DSSClassImpl.getDSSClasses().setNew(FaultImpl())
        DSSClassImpl.getDSSClasses().setNew(GeneratorImpl())
        DSSClassImpl.getDSSClasses().setNew(GenDispatcherImpl())
        DSSGlobals.storageClass = StorageImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.storageClass)
        DSSClassImpl.getDSSClasses().setNew(StorageControllerImpl())
        DSSClassImpl.getDSSClasses().setNew(RelayImpl())
        DSSClassImpl.getDSSClasses().setNew(RecloserImpl())
        DSSClassImpl.getDSSClasses().setNew(FuseImpl())
        # Globals.setFeederClass(new FeederImpl());
        # DSSClassImpl.getDSSClasses().setNew( Globals.getFeederClass() );
        DSSClassImpl.getDSSClasses().setNew(SwtControlImpl())
        DSSGlobals.PVSystemClass = PVSystemImpl()
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.PVSystemClass)
        DSSGlobals.monitorClass = MonitorImpl()
        # have to do this after Generator
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.monitorClass)
        DSSGlobals.energyMeterClass = EnergyMeterImpl()
        # have to do this after Generator
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.energyMeterClass)
        DSSGlobals.sensorClass = SensorImpl()
        # create state estimation sensors
        DSSClassImpl.getDSSClasses().setNew(DSSGlobals.sensorClass)
        DSSClassImpl.getDSSClasses().setNew(GICTransformerImpl())
        DSSClassImpl.getDSSClasses().setNew(GICLineImpl())
        # Create classes for custom implementations
        # MyClassDefs.createMyDSSClasses();
        cls.numIntrinsicClasses = len(DSSGlobals.DSSClassList)
        cls.numUserClasses = 0
        # Add user-defined objects

    @classmethod
    def disposeDSSClasses(cls):
        traceName = ''
        successFree = ''
        try:
            successFree = 'First Object'
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(DSSGlobals.DSSObjs)):
                    break
                DSSObj = DSSGlobals.DSSObjs.get(i)
                traceName = DSSObj.getParentClass().getName() + '.' + DSSObj.getName()
                DSSObj = None
                successFree = traceName
            traceName = '(DSSObjs Class)'
            DSSGlobals.DSSObjs = None
        except Exception, e:
            DSSGlobals.doSimpleMsg('Exception disposing of DSS obj \"' + traceName + '\". ' + DSSGlobals.CRLF + 'Last successful dispose was for object \"' + successFree + '\" ' + DSSGlobals.CRLF + e.getMessage(), 901)
        try:
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < len(DSSGlobals.DSSClassList)):
                    break
                DSSGlobals.DSSClassList.set(i, None)
            traceName = '(DSS Class List)'
            DSSGlobals.DSSClassList = None
            traceName = '(DSS Classes)'
            DSSClassImpl.setDSSClasses(None)
            traceName = '(ClassNames)'
            DSSGlobals.classNames = None
        except Exception, e:
            DSSGlobals.doSimpleMsg('Exception disposing of DSS class\"' + traceName + '\". ' + DSSGlobals.CRLF + e.getMessage(), 902)

    @classmethod
    def addUserClass(cls):
        raise cls.UnsupportedOperationException()

    @classmethod
    def loadUserClasses(cls):
        raise cls.UnsupportedOperationException()

    @classmethod
    def setObjectClass(cls, objType):
        """Set lastClassReferenced variable by class name."""
        ClassRef = DSSGlobals.classNames.find(objType)
        _0 = ClassRef
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                DSSGlobals.doSimpleMsg('Error: Object class \"' + objType + '\" not found.' + DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 903)
                return False
            if True:
                _1 = True
                DSSGlobals.lastClassReferenced = ClassRef
                break
            break
        return True

    @classmethod
    def getDSSClass(cls, className):
        return DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find(className.toLowerCase()))
