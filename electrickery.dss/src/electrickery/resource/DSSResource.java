/**
 *
 */
package electrickery.resource;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import electrickery.common.CommonPackage;
import electrickery.control.ControlPackage;
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
        GeneralPackage.Literals.LINE_CODE__NEUTRAL
    };
}
