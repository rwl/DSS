/**
 *
 */
package electrickery.resource;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import electrickery.common.CommonPackage;
import electrickery.control.ControlPackage;
import electrickery.conversion.ConversionPackage;
import electrickery.delivery.DeliveryFactory;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.impl.DeliveryFactoryImpl;
import electrickery.general.GeneralPackage;
import electrickery.meter.MeterPackage;

/**
 * @author rwl
 *
 */
public interface DSSResource extends Resource {


    public static final EStructuralFeature[] CAPACITOR_FEATURES = {
        DeliveryPackage.Literals.CAPACITOR__BUS1,
        DeliveryPackage.Literals.CAPACITOR__BUS2,
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        DeliveryPackage.Literals.CAPACITOR__KV_AR,
        DeliveryPackage.Literals.CAPACITOR__KV,
        DeliveryPackage.Literals.CAPACITOR__CONN,
        DeliveryPackage.Literals.CAPACITOR__CMATRIX,
        DeliveryPackage.Literals.CAPACITOR__CUF,
        DeliveryPackage.Literals.CAPACITOR__R,
        DeliveryPackage.Literals.CAPACITOR__XL,
        DeliveryPackage.Literals.CAPACITOR__HARM,
        DeliveryPackage.Literals.CAPACITOR__NSTEPS,
        DeliveryPackage.Literals.CAPACITOR__STATES,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__NORM_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__EMERG_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__FAULT_RATE,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__PCT_PERM,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__REPAIR,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] CAPACITORCONTROL_FEATURES = {
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_NAME,
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_TERMINAL,
        ControlPackage.Literals.CAPACITOR_CONTROL__CAPACITOR,
        ControlPackage.Literals.CAPACITOR_CONTROL__TYPE,
        ControlPackage.Literals.CAPACITOR_CONTROL__PT_RATIO,
        ControlPackage.Literals.CAPACITOR_CONTROL__CT_RATIO,
        ControlPackage.Literals.CAPACITOR_CONTROL__ON_SETTING,
        ControlPackage.Literals.CAPACITOR_CONTROL__OFF_SETTING,
        ControlPackage.Literals.CAPACITOR_CONTROL__DELAY,
        ControlPackage.Literals.CAPACITOR_CONTROL__VOLT_OVERRIDE,
        ControlPackage.Literals.CAPACITOR_CONTROL__VMAX,
        ControlPackage.Literals.CAPACITOR_CONTROL__VMIN,
        ControlPackage.Literals.CAPACITOR_CONTROL__DELAY_OFF,
        ControlPackage.Literals.CAPACITOR_CONTROL__DEAD_TIME,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE

    };

    public static final EStructuralFeature[] ENERGYMETER_FEATURES = {
        MeterPackage.Literals.METER_ELEMENT__ELEMENT_NAME,
        MeterPackage.Literals.ENERGY_METER__TERMINAL,
        MeterPackage.Literals.ENERGY_METER__ACTION,
        MeterPackage.Literals.ENERGY_METER__OPTION,
        MeterPackage.Literals.ENERGY_METER__KVA_NORM,
        MeterPackage.Literals.ENERGY_METER__KVA_EMERG,
        MeterPackage.Literals.ENERGY_METER__PEAK_CURRENT,
        MeterPackage.Literals.ENERGY_METER__ZONE_LIST,
        MeterPackage.Literals.ENERGY_METER__LOCAL_ONLY,
        MeterPackage.Literals.ENERGY_METER__MASK,
        MeterPackage.Literals.ENERGY_METER__LOSSES,
        MeterPackage.Literals.ENERGY_METER__LINE_LOSSES,
        MeterPackage.Literals.ENERGY_METER__XFMR_LOSSES,
        MeterPackage.Literals.ENERGY_METER__SEQ_LOSSES,
        MeterPackage.Literals.ENERGY_METER__THREE_PHASE_LOSSES,
        MeterPackage.Literals.ENERGY_METER__VBASE_LOSSES,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] FAULT_FEATURES = {
        DeliveryPackage.Literals.FAULT__BUS1,
        DeliveryPackage.Literals.FAULT__BUS2,
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        DeliveryPackage.Literals.FAULT__R,
        DeliveryPackage.Literals.FAULT__PCT_STD_DEV,
        DeliveryPackage.Literals.FAULT__GMATRIX,
        DeliveryPackage.Literals.FAULT__ON_TIME,
        DeliveryPackage.Literals.FAULT__TEMPORARY,
        DeliveryPackage.Literals.FAULT__MIN_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__NORM_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__EMERG_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__FAULT_RATE,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__PCT_PERM,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__REPAIR,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] FEEDER_FEATURES = {
        CommonPackage.Literals.FEEDER__SPECTRUM,
        CommonPackage.Literals.FEEDER__BASE_FREQ,
        CommonPackage.Literals.FEEDER__ENABLED,
//        CommonPackage.Literals.FEEDER__LIKE
    };

    public static final EStructuralFeature[] FUSE_FEATURES = {
        DeliveryPackage.Literals.FUSE__MONITORED_OBJ,
        DeliveryPackage.Literals.FUSE__MONITOR_TERM,
        DeliveryPackage.Literals.FUSE__SWITCHED_OBJ,
        DeliveryPackage.Literals.FUSE__SWITCHED_TERM,
        DeliveryPackage.Literals.FUSE__FUSE_CURVE,
        DeliveryPackage.Literals.FUSE__RATED_CURRENT,
        DeliveryPackage.Literals.FUSE__DELAY,
        DeliveryPackage.Literals.FUSE__ACTION,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] GENERATORDISPATCHER_FEATURES = {
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_NAME,
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_TERMINAL,
        ControlPackage.Literals.GENERATOR_DISPATCHER__KW_LIMIT,
        ControlPackage.Literals.GENERATOR_DISPATCHER__KW_BAND,
        ControlPackage.Literals.GENERATOR_DISPATCHER__KVAR_LIMIT,
        ControlPackage.Literals.GENERATOR_DISPATCHER__GEN_LIST,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] GENERATOR_FEATURES = {
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        ConversionPackage.Literals.GENERATOR__BUS1,
        ConversionPackage.Literals.GENERATOR__KV,
        ConversionPackage.Literals.GENERATOR__KW,
        ConversionPackage.Literals.GENERATOR__PF,
        ConversionPackage.Literals.GENERATOR__KV_AR,
        ConversionPackage.Literals.GENERATOR__MODEL,
        ConversionPackage.Literals.GENERATOR__VMIN_PU,
        ConversionPackage.Literals.GENERATOR__VMAX_PU,
        ConversionPackage.Literals.GENERATOR__YEARLY,
        ConversionPackage.Literals.GENERATOR__DAILY,
        ConversionPackage.Literals.GENERATOR__DUTY,
        ConversionPackage.Literals.GENERATOR__DISP_MODE,
        ConversionPackage.Literals.GENERATOR__DISP_VALUE,
        ConversionPackage.Literals.GENERATOR__CONN,
        ConversionPackage.Literals.GENERATOR__RNEUT,
        ConversionPackage.Literals.GENERATOR__XNEUT,
        ConversionPackage.Literals.GENERATOR__STATUS,
        ConversionPackage.Literals.GENERATOR__CLASS,
        ConversionPackage.Literals.GENERATOR__VPU,
        ConversionPackage.Literals.GENERATOR__MAX_KV_AR,
        ConversionPackage.Literals.GENERATOR__MIN_KV_AR,
        ConversionPackage.Literals.GENERATOR__PV_FACTOR,
        ConversionPackage.Literals.GENERATOR__FORCE_ON,
        ConversionPackage.Literals.GENERATOR__KVA,
        ConversionPackage.Literals.GENERATOR__MVA,
        ConversionPackage.Literals.GENERATOR__XD,
        ConversionPackage.Literals.GENERATOR__XDP,
        ConversionPackage.Literals.GENERATOR__XDPP,
        ConversionPackage.Literals.GENERATOR__H,
        ConversionPackage.Literals.GENERATOR__D,
        ConversionPackage.Literals.GENERATOR__USER_MODEL,
        ConversionPackage.Literals.GENERATOR__USER_DATA,
        ConversionPackage.Literals.GENERATOR__SHAFT_MODEL,
        ConversionPackage.Literals.GENERATOR__SHAFT_DATA,
        ConversionPackage.Literals.GENERATOR__DEBUG_TRACE,
        ConversionPackage.Literals.POWER_CONVERSION_ELEMENT__SPECTRUM,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] GROWTHSHAPE_FEATURES = {
        GeneralPackage.Literals.GROWTH_SHAPE__NPTS,
        GeneralPackage.Literals.GROWTH_SHAPE__YEAR,
//        GeneralPackage.Literals.GROWTH_SHAPE__MULT,
        GeneralPackage.Literals.GROWTH_SHAPE__CSV_FILE,
        GeneralPackage.Literals.GROWTH_SHAPE__SNG_FILE,
        GeneralPackage.Literals.GROWTH_SHAPE__DBL_FILE,
//        GeneralPackage.Literals.GROWTH_SHAPE__LIKE
    };

    public static final EStructuralFeature[] CURRENTSOURCE_FEATURES = {
        ConversionPackage.Literals.CURRENT_SOURCE__BUS1,
        ConversionPackage.Literals.CURRENT_SOURCE__AMPS,
        ConversionPackage.Literals.CURRENT_SOURCE__ANGLE,
        ConversionPackage.Literals.CURRENT_SOURCE__FREQUENCY,
        ConversionPackage.Literals.CURRENT_SOURCE__PHASES,
        ConversionPackage.Literals.CURRENT_SOURCE__SCAN_TYPE
    };

    public static final EStructuralFeature[] LINE_FEATURES = {
        DeliveryPackage.Literals.LINE__BUS1,
        DeliveryPackage.Literals.LINE__BUS2,
        DeliveryPackage.Literals.LINE__LINE_CODE,
        DeliveryPackage.Literals.LINE__LENGTH,
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        DeliveryPackage.Literals.LINE__R1,
        DeliveryPackage.Literals.LINE__X1,
        DeliveryPackage.Literals.LINE__R0,
        DeliveryPackage.Literals.LINE__X0,
        DeliveryPackage.Literals.LINE__C1,
        DeliveryPackage.Literals.LINE__C0,
        DeliveryPackage.Literals.LINE__RMATRIX,
        DeliveryPackage.Literals.LINE__CMATRIX,
        DeliveryPackage.Literals.LINE__XMATRIX,
        DeliveryPackage.Literals.LINE__SWITCH,
        DeliveryPackage.Literals.LINE__RG,
        DeliveryPackage.Literals.LINE__XG,
        DeliveryPackage.Literals.LINE__RHO,
        DeliveryPackage.Literals.LINE__GEOMETRY,
        DeliveryPackage.Literals.LINE__UNITS,
//        DeliveryPackage.Literals.LINE__SPACING,
//        DeliveryPackage.Literals.LINE__WIRES,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__NORM_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__EMERG_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__FAULT_RATE,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__PCT_PERM,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__REPAIR,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] LINECODE_FEATURES = {
        GeneralPackage.Literals.LINE_CODE__NPHASES,
        GeneralPackage.Literals.LINE_CODE__R1,
        GeneralPackage.Literals.LINE_CODE__X1,
        GeneralPackage.Literals.LINE_CODE__R0,
        GeneralPackage.Literals.LINE_CODE__X0,
        GeneralPackage.Literals.LINE_CODE__C1,
        GeneralPackage.Literals.LINE_CODE__C0,
        GeneralPackage.Literals.LINE_CODE__UNITS,
        GeneralPackage.Literals.LINE_CODE__RMATRIX,
        GeneralPackage.Literals.LINE_CODE__CMATRIX,
        GeneralPackage.Literals.LINE_CODE__BASE_FREQ,
        GeneralPackage.Literals.LINE_CODE__NORM_AMPS,
        GeneralPackage.Literals.LINE_CODE__EMERG_AMPS,
        GeneralPackage.Literals.LINE_CODE__FAULT_RATE,
        GeneralPackage.Literals.LINE_CODE__PCT_PERM,
        GeneralPackage.Literals.LINE_CODE__REPAIR,
        GeneralPackage.Literals.LINE_CODE__KRON,
        GeneralPackage.Literals.LINE_CODE__RG,
        GeneralPackage.Literals.LINE_CODE__XG,
        GeneralPackage.Literals.LINE_CODE__RHO,
        GeneralPackage.Literals.LINE_CODE__NEUTRAL,
        GeneralPackage.Literals.LINE_CODE__LIKE
    };

    public static final EStructuralFeature[] LINEGEOMETRY_FEATURES = {
        GeneralPackage.Literals.LINE_GEOMETRY__NCONDS,
        GeneralPackage.Literals.LINE_GEOMETRY__NPHASES,
        GeneralPackage.Literals.LINE_GEOMETRY__COND,
        GeneralPackage.Literals.LINE_GEOMETRY__WIRE,
        GeneralPackage.Literals.LINE_GEOMETRY__X,
        GeneralPackage.Literals.LINE_GEOMETRY__H,
        GeneralPackage.Literals.LINE_GEOMETRY__UNITS,
        GeneralPackage.Literals.LINE_GEOMETRY__NORM_AMPS,
        GeneralPackage.Literals.LINE_GEOMETRY__EMERG_AMPS,
        GeneralPackage.Literals.LINE_GEOMETRY__REDUCE,
        GeneralPackage.Literals.LINE_GEOMETRY__SPACING,
        GeneralPackage.Literals.LINE_GEOMETRY__WIRES,
//        GeneralPackage.Literals.LINE_GEOMETRY__LIKE
    };

    public static final EStructuralFeature[] LINESPACING_FEATURES = {
        GeneralPackage.Literals.LINE_SPACING__NCONDS,
        GeneralPackage.Literals.LINE_SPACING__NPHASES,
        GeneralPackage.Literals.LINE_SPACING__X,
        GeneralPackage.Literals.LINE_SPACING__H,
        GeneralPackage.Literals.LINE_SPACING__UNITS,
        GeneralPackage.Literals.LINE_SPACING__LIKE
    };

    public static final EStructuralFeature[] LOAD_FEATURES = {
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        ConversionPackage.Literals.LOAD__BUS1,
        ConversionPackage.Literals.LOAD__KV,
        ConversionPackage.Literals.LOAD__KW,
        ConversionPackage.Literals.LOAD__PF,
        ConversionPackage.Literals.LOAD__MODEL,
        ConversionPackage.Literals.LOAD__YEARLY,
        ConversionPackage.Literals.LOAD__DAILY,
        ConversionPackage.Literals.LOAD__DUTY,
        ConversionPackage.Literals.LOAD__GROWTH,
        ConversionPackage.Literals.LOAD__CONN,
        ConversionPackage.Literals.LOAD__KV_AR,
        ConversionPackage.Literals.LOAD__RNEUT,
        ConversionPackage.Literals.LOAD__XNEUT,
        ConversionPackage.Literals.LOAD__STATUS,
        ConversionPackage.Literals.LOAD__CLASS,
        ConversionPackage.Literals.LOAD__VMIN_PU,
        ConversionPackage.Literals.LOAD__VMAX_PU,
        ConversionPackage.Literals.LOAD__VMIN_NORM,
        ConversionPackage.Literals.LOAD__VMIN_EMERG,
        ConversionPackage.Literals.LOAD__XF_KVA,
        ConversionPackage.Literals.LOAD__ALLOCATION_FACTOR,
        ConversionPackage.Literals.LOAD__KVA,
        ConversionPackage.Literals.LOAD__PCT_MEAN,
        ConversionPackage.Literals.LOAD__PCT_STD_DEV,
        ConversionPackage.Literals.LOAD__CVR_WATTS,
        ConversionPackage.Literals.LOAD__CVR_VARS,
//        ConversionPackage.Literals.LOAD__KWH,
//        ConversionPackage.Literals.LOAD__KWHDAYS,
//        ConversionPackage.Literals.LOAD__CFACTOR,
//        ConversionPackage.Literals.LOAD__CVRCURVE,
//        ConversionPackage.Literals.LOAD__NUMCUST,
//        ConversionPackage.Literals.LOAD__SPECTRUM,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] LOADSHAPE_FEATURES = {
        GeneralPackage.Literals.LOAD_SHAPE__NPTS,
        GeneralPackage.Literals.LOAD_SHAPE__INTERVAL,
        GeneralPackage.Literals.LOAD_SHAPE__MULT,
        GeneralPackage.Literals.LOAD_SHAPE__HOUR,
        GeneralPackage.Literals.LOAD_SHAPE__MEAN,
        GeneralPackage.Literals.LOAD_SHAPE__STD_DEV,
        GeneralPackage.Literals.LOAD_SHAPE__CSV_FILE,
        GeneralPackage.Literals.LOAD_SHAPE__SNG_FILE,
        GeneralPackage.Literals.LOAD_SHAPE__DBL_FILE,
//        GeneralPackage.Literals.LOAD_SHAPE__ACTION,
        GeneralPackage.Literals.LOAD_SHAPE__QMULT,
//        GeneralPackage.Literals.LOAD_SHAPE__LIKE
    };

    public static final EStructuralFeature[] MONITOR_FEATURES = {
        MeterPackage.Literals.METER_ELEMENT__ELEMENT_NAME,
        MeterPackage.Literals.METER_ELEMENT__METERED_TERMINAL,
        MeterPackage.Literals.MONITOR__MODE,
        MeterPackage.Literals.MONITOR__ACTION,
        MeterPackage.Literals.MONITOR__RESIDUAL,
        MeterPackage.Literals.MONITOR__VI_POLAR,
        MeterPackage.Literals.MONITOR__PPOLAR,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] REACTOR_FEATURES = {
        DeliveryPackage.Literals.REACTOR__BUS1,
        DeliveryPackage.Literals.REACTOR__BUS2,
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        DeliveryPackage.Literals.REACTOR__KV_AR,
        DeliveryPackage.Literals.REACTOR__KV,
        DeliveryPackage.Literals.REACTOR__CONN,
        DeliveryPackage.Literals.REACTOR__RMATRIX,
        DeliveryPackage.Literals.REACTOR__XMATRIX,
        DeliveryPackage.Literals.REACTOR__PARALLEL,
        DeliveryPackage.Literals.REACTOR__R,
        DeliveryPackage.Literals.REACTOR__X,
        DeliveryPackage.Literals.REACTOR__RP,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__NORM_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__EMERG_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__FAULT_RATE,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__PCT_PERM,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__REPAIR,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] RECLOSER_FEATURES = {
        ControlPackage.Literals.RECLOSER__MONITORED_OBJ,
        ControlPackage.Literals.RECLOSER__MONITORED_TERM,
        ControlPackage.Literals.RECLOSER__SWITCHED_OBJ,
        ControlPackage.Literals.RECLOSER__SWITCHED_TERM,
        ControlPackage.Literals.RECLOSER__NFAST,
        ControlPackage.Literals.RECLOSER__PHASE_FAST,
        ControlPackage.Literals.RECLOSER__PHASE_DELAYED,
        ControlPackage.Literals.RECLOSER__GROUND_FAST,
        ControlPackage.Literals.RECLOSER__GROUND_DELAYED,
        ControlPackage.Literals.RECLOSER__PHASE_TRIP,
        ControlPackage.Literals.RECLOSER__GROUND_TRIP,
        ControlPackage.Literals.RECLOSER__PHASE_INST,
        ControlPackage.Literals.RECLOSER__GROUND_INST,
        ControlPackage.Literals.RECLOSER__RESET,
        ControlPackage.Literals.RECLOSER__SHOTS,
        ControlPackage.Literals.RECLOSER__RECLOSE_INTERVALS,
        ControlPackage.Literals.RECLOSER__DELAY,
        ControlPackage.Literals.RECLOSER__ACTION,
        ControlPackage.Literals.RECLOSER__TD_PH_FAST,
        ControlPackage.Literals.RECLOSER__TD_PH_DELAYED,
        ControlPackage.Literals.RECLOSER__TD_GR_FAST,
        ControlPackage.Literals.RECLOSER__TD_GR_DELAYED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] REGULATORCONTROL_FEATURES = {
        ControlPackage.Literals.REGULATOR_CONTROL__TRANSFORMER,
        ControlPackage.Literals.REGULATOR_CONTROL__WINDING,
        ControlPackage.Literals.REGULATOR_CONTROL__VREG,
        ControlPackage.Literals.REGULATOR_CONTROL__BAND,
        ControlPackage.Literals.REGULATOR_CONTROL__PT_RATIO,
        ControlPackage.Literals.REGULATOR_CONTROL__CT_PRIM,
        ControlPackage.Literals.REGULATOR_CONTROL__R,
        ControlPackage.Literals.REGULATOR_CONTROL__X,
        ControlPackage.Literals.REGULATOR_CONTROL__BUS,
        ControlPackage.Literals.REGULATOR_CONTROL__DELAY,
        ControlPackage.Literals.REGULATOR_CONTROL__REVERSIBLE,
        ControlPackage.Literals.REGULATOR_CONTROL__REV_VREG,
        ControlPackage.Literals.REGULATOR_CONTROL__REV_BAND,
        ControlPackage.Literals.REGULATOR_CONTROL__REV_R,
        ControlPackage.Literals.REGULATOR_CONTROL__REV_X,
        ControlPackage.Literals.REGULATOR_CONTROL__TAP_DELAY,
        ControlPackage.Literals.REGULATOR_CONTROL__DEBUG_TRACE,
        ControlPackage.Literals.REGULATOR_CONTROL__MAX_TAP_CHANGE,
        ControlPackage.Literals.REGULATOR_CONTROL__INVERSE_TIME,
        ControlPackage.Literals.REGULATOR_CONTROL__TAP_WINDING,
//    	ControlPackage.Literals.REGULATOR_CONTROL__V_LIMIT,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] RELAY_FEATURES = {
        ControlPackage.Literals.RELAY__MONITORED_OBJ,
        ControlPackage.Literals.RELAY__MONITORED_TERM,
        ControlPackage.Literals.RELAY__SWITCHED_OBJ,
        ControlPackage.Literals.RELAY__SWITCHED_TERM,
        ControlPackage.Literals.RELAY__TYPE,
        ControlPackage.Literals.RELAY__PHASE_CURVE,
        ControlPackage.Literals.RELAY__GROUND_CURVE,
        ControlPackage.Literals.RELAY__PHASE_TRIP,
        ControlPackage.Literals.RELAY__GROUND_TRIP,
        ControlPackage.Literals.RELAY__TD_PHASE,
        ControlPackage.Literals.RELAY__TD_GROUND,
        ControlPackage.Literals.RELAY__PHASE_INST,
        ControlPackage.Literals.RELAY__GROUND_INST,
        ControlPackage.Literals.RELAY__RESET,
        ControlPackage.Literals.RELAY__SHOTS,
        ControlPackage.Literals.RELAY__RECLOSE_INTERVALS,
        ControlPackage.Literals.RELAY__DELAY,
        ControlPackage.Literals.RELAY__OVERVOLT_CURVE,
        ControlPackage.Literals.RELAY__UNDERVOLT_CURVE,
        ControlPackage.Literals.RELAY__KV_BASE,
        ControlPackage.Literals.RELAY__PCT_PICKUP47,
//    	ControlPackage.Literals.RELAY__BASE_AMPS46,
        ControlPackage.Literals.RELAY__PCT_PICKUP46,
        ControlPackage.Literals.RELAY__ISQT46,
        ControlPackage.Literals.RELAY__VARIABLE,
        ControlPackage.Literals.RELAY__OVERTRIP,
        ControlPackage.Literals.RELAY__UNDERTRIP,
        ControlPackage.Literals.RELAY__BREAKER_TIME,
        ControlPackage.Literals.RELAY__ACTION,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] SENSOR_FEATURES = {
        MeterPackage.Literals.METER_ELEMENT__METERED_ELEMENT,
        MeterPackage.Literals.METER_ELEMENT__METERED_TERMINAL,
//    	MeterPackage.Literals.SENSOR__KV_BASE,
//        MeterPackage.Literals.SENSOR__CLEAR,
//        MeterPackage.Literals.SENSOR__KVS,
//        MeterPackage.Literals.SENSOR__CURRENTS,
//        MeterPackage.Literals.SENSOR__KWS,
//        MeterPackage.Literals.SENSOR__KVARS,
        MeterPackage.Literals.SENSOR__CONN,
//        MeterPackage.Literals.SENSOR__DELTA_DIRECTION,
        MeterPackage.Literals.SENSOR__PCT_ERROR,
//        MeterPackage.Literals.SENSOR__WEIGHT,
        MeterPackage.Literals.SENSOR__ACTION,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] SPECTRUM_FEATURES = {
        GeneralPackage.Literals.SPECTRUM__NHARM,
        GeneralPackage.Literals.SPECTRUM__HARMONIC,
        GeneralPackage.Literals.SPECTRUM__PCT_MAG,
        GeneralPackage.Literals.SPECTRUM__ANGLE,
        GeneralPackage.Literals.SPECTRUM__CSV_FILE,
//    	GeneralPackage.Literals.SPECTRUM__LIKE
    };

    public static final EStructuralFeature[] STORAGE_FEATURES = {
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        ConversionPackage.Literals.STORAGE__BUS1,
        ConversionPackage.Literals.STORAGE__KV,
        ConversionPackage.Literals.STORAGE__KW,
        ConversionPackage.Literals.STORAGE__PF,
        ConversionPackage.Literals.STORAGE__CONN,
        ConversionPackage.Literals.STORAGE__KVAR,
        ConversionPackage.Literals.STORAGE__KVA,
        ConversionPackage.Literals.STORAGE__KW_RATED,
        ConversionPackage.Literals.STORAGE__KWH_RATED,
        ConversionPackage.Literals.STORAGE__KWH_STORED,
        ConversionPackage.Literals.STORAGE__PCT_STORED,
        ConversionPackage.Literals.STORAGE__PCT_RESERVE,
        ConversionPackage.Literals.STORAGE__STATE,
        ConversionPackage.Literals.STORAGE__PCT_DISCHARGE,
        ConversionPackage.Literals.STORAGE__PCT_CHARGE,
        ConversionPackage.Literals.STORAGE__PCT_EFF_CHARGE,
        ConversionPackage.Literals.STORAGE__PCT_EFF_DISCHARGE,
        ConversionPackage.Literals.STORAGE__PCT_IDLING_KW,
        ConversionPackage.Literals.STORAGE__PCT_IDLING_KV_AR,
        ConversionPackage.Literals.STORAGE__PCT_R,
        ConversionPackage.Literals.STORAGE__PCT_X,
        ConversionPackage.Literals.STORAGE__MODEL,
        ConversionPackage.Literals.STORAGE__VMIN_PU,
        ConversionPackage.Literals.STORAGE__VMAX_PU,
        ConversionPackage.Literals.STORAGE__YEARLY,
        ConversionPackage.Literals.STORAGE__DAILY,
        ConversionPackage.Literals.STORAGE__DUTY,
        ConversionPackage.Literals.STORAGE__DISP_MODE,
        ConversionPackage.Literals.STORAGE__DISCHARGE_TRIGGER,
        ConversionPackage.Literals.STORAGE__CHARGE_TRIGGER,
        ConversionPackage.Literals.STORAGE__TIME_CHARGE_TRIG,
        ConversionPackage.Literals.STORAGE__CLASS,
        ConversionPackage.Literals.STORAGE__USER_MODEL,
        ConversionPackage.Literals.STORAGE__USER_DATA,
        ConversionPackage.Literals.STORAGE__DEBUG_TRACE,
        ConversionPackage.Literals.POWER_CONVERSION_ELEMENT__SPECTRUM,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] STORAGECONTROLLER_FEATURES = {
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_NAME,
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_TERMINAL,
        ControlPackage.Literals.STORAGE_CONTROLLER__KW_TARGET,
        ControlPackage.Literals.STORAGE_CONTROLLER__PCT_KW_BAND,
        ControlPackage.Literals.STORAGE_CONTROLLER__PF_TARGET,
        ControlPackage.Literals.STORAGE_CONTROLLER__PF_BAND,
        ControlPackage.Literals.STORAGE_CONTROLLER__ELEMENTS,
        ControlPackage.Literals.STORAGE_CONTROLLER__WEIGHTS,
        ControlPackage.Literals.STORAGE_CONTROLLER__MODE_DISCHARGE,
        ControlPackage.Literals.STORAGE_CONTROLLER__MODE_CHARGE,
        ControlPackage.Literals.STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER,
        ControlPackage.Literals.STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER,
        ControlPackage.Literals.STORAGE_CONTROLLER__PCT_RATE_KW,
        ControlPackage.Literals.STORAGE_CONTROLLER__PCT_RATE_KV_AR,
        ControlPackage.Literals.STORAGE_CONTROLLER__PCT_RATE_CHARGE,
        ControlPackage.Literals.STORAGE_CONTROLLER__PCT_RESERVE,
        ControlPackage.Literals.STORAGE_CONTROLLER__KWH_TOTAL,
        ControlPackage.Literals.STORAGE_CONTROLLER__KW_TOTAL,
        ControlPackage.Literals.STORAGE_CONTROLLER__KWH_ACTUAL,
        ControlPackage.Literals.STORAGE_CONTROLLER__KW_ACTUAL,
        ControlPackage.Literals.STORAGE_CONTROLLER__KW_NEED,
        ControlPackage.Literals.STORAGE_CONTROLLER__PCT_PARTICIPATION,
        ControlPackage.Literals.STORAGE_CONTROLLER__YEARLY,
        ControlPackage.Literals.STORAGE_CONTROLLER__DAILY,
        ControlPackage.Literals.STORAGE_CONTROLLER__DUTY,
        ControlPackage.Literals.STORAGE_CONTROLLER__EVENT_LOG,
        ControlPackage.Literals.STORAGE_CONTROLLER__VAR_DISPATCH,
        ControlPackage.Literals.STORAGE_CONTROLLER__INHIBIT_TIME,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] SWITCHCONTROL_FEATURES = {
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_NAME,
        ControlPackage.Literals.CONTROL_ELEMENT__ELEMENT_TERMINAL,
        ControlPackage.Literals.SWITCH_CONTROL__ACTION,
        ControlPackage.Literals.SWITCH_CONTROL__LOCK,
        ControlPackage.Literals.SWITCH_CONTROL__DELAY,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] TIMECURRENTCURVE_FEATURES = {
        GeneralPackage.Literals.TIME_CURRENT_CURVE__NPTS,
        GeneralPackage.Literals.TIME_CURRENT_CURVE__CARRAY,
        GeneralPackage.Literals.TIME_CURRENT_CURVE__TARRAY,
//    	GeneralPackage.Literals.TIME_CURRENT_CURVE__LIKE
    };

    public static final EStructuralFeature[] TRANSFORMER_FEATURES = {
        CommonPackage.Literals.CIRCUIT_ELEMENT__NPHASES,
        DeliveryPackage.Literals.TRANSFORMER__WINDINGS,
        DeliveryPackage.Literals.TRANSFORMER__WDG,
        DeliveryPackage.Literals.TRANSFORMER__BUS,
        DeliveryPackage.Literals.TRANSFORMER__CONN,
        DeliveryPackage.Literals.TRANSFORMER__KV,
        DeliveryPackage.Literals.TRANSFORMER__KVA,
        DeliveryPackage.Literals.TRANSFORMER__TAP,
        DeliveryPackage.Literals.TRANSFORMER__RPCT,
        DeliveryPackage.Literals.TRANSFORMER__RNEUT,
        DeliveryPackage.Literals.TRANSFORMER__XNEUT,
        DeliveryPackage.Literals.TRANSFORMER__BUSES,
        DeliveryPackage.Literals.TRANSFORMER__CONNS,
        DeliveryPackage.Literals.TRANSFORMER__KVS,
        DeliveryPackage.Literals.TRANSFORMER__KV_AS,
        DeliveryPackage.Literals.TRANSFORMER__TAPS,
        DeliveryPackage.Literals.TRANSFORMER__XHL,
        DeliveryPackage.Literals.TRANSFORMER__XHT,
        DeliveryPackage.Literals.TRANSFORMER__XLT,
        DeliveryPackage.Literals.TRANSFORMER__XSC_ARRAY,
        DeliveryPackage.Literals.TRANSFORMER__THERMAL,
        DeliveryPackage.Literals.TRANSFORMER__N,
        DeliveryPackage.Literals.TRANSFORMER__M,
        DeliveryPackage.Literals.TRANSFORMER__FL_RISE,
        DeliveryPackage.Literals.TRANSFORMER__HS_RISE,
        DeliveryPackage.Literals.TRANSFORMER__PCT_LOAD_LOSS,
        DeliveryPackage.Literals.TRANSFORMER__PCT_NO_LOAD_LOSS,
        DeliveryPackage.Literals.TRANSFORMER__NORM_HK_VA,
        DeliveryPackage.Literals.TRANSFORMER__EMERG_HK_VA,
        DeliveryPackage.Literals.TRANSFORMER__SUBSTATION,
        DeliveryPackage.Literals.TRANSFORMER__MAX_TAP,
        DeliveryPackage.Literals.TRANSFORMER__NUM_TAPS,
        DeliveryPackage.Literals.TRANSFORMER__SUB_NAME,
        DeliveryPackage.Literals.TRANSFORMER__PCT_IMAGE,
        DeliveryPackage.Literals.TRANSFORMER__PPM_ANTI_FLOAT,
//    	DeliveryPackage.Literals.TRANSFORMER__PCT_RS,
//    	DeliveryPackage.Literals.TRANSFORMER__BANK,
//    	DeliveryPackage.Literals.TRANSFORMER__XFMR_CODE,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__NORM_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__EMERG_AMPS,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__FAULT_RATE,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__PCT_PERM,
        DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT__REPAIR,
        CommonPackage.Literals.CIRCUIT_ELEMENT__BASE_FREQ,
        CommonPackage.Literals.CIRCUIT_ELEMENT__ENABLED,
        CommonPackage.Literals.CIRCUIT_ELEMENT__LIKE
    };

    public static final EStructuralFeature[] VOLTAGESOURCE_FEATURES = {

    };
}
