/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.meter.impl;

import dss.DSSPackage;

import dss.common.CommonPackage;

import dss.common.impl.CommonPackageImpl;

import dss.control.ControlPackage;

import dss.control.impl.ControlPackageImpl;

import dss.conversion.ConversionPackage;

import dss.conversion.impl.ConversionPackageImpl;

import dss.delivery.DeliveryPackage;

import dss.delivery.impl.DeliveryPackageImpl;

import dss.executive.ExecutivePackage;

import dss.executive.impl.ExecutivePackageImpl;

import dss.general.GeneralPackage;

import dss.general.impl.GeneralPackageImpl;

import dss.impl.DSSPackageImpl;

import dss.meter.EnergyMeter;
import dss.meter.MeterElement;
import dss.meter.MeterFactory;
import dss.meter.MeterPackage;
import dss.meter.Monitor;
import dss.meter.Sensor;
import dss.meter.meterAction;
import dss.meter.monitorAction;
import dss.meter.monitorValues;
import dss.meter.sensorAction;
import dss.meter.sensorMode;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MeterPackageImpl extends EPackageImpl implements MeterPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meterElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass energyMeterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass monitorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sensorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum meterActionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum monitorValuesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum monitorActionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sensorModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sensorActionEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see dss.meter.MeterPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MeterPackageImpl() {
		super(eNS_URI, MeterFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MeterPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MeterPackage init() {
		if (isInited) return (MeterPackage)EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI);

		// Obtain or create and register package
		MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MeterPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		DSSPackageImpl theDSSPackage = (DSSPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DSSPackage.eNS_URI) instanceof DSSPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DSSPackage.eNS_URI) : DSSPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);

		// Create package meta-data objects
		theMeterPackage.createPackageContents();
		theDSSPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theDeliveryPackage.createPackageContents();
		theExecutivePackage.createPackageContents();
		theGeneralPackage.createPackageContents();

		// Initialize created meta-data
		theMeterPackage.initializePackageContents();
		theDSSPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theDeliveryPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMeterPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MeterPackage.eNS_URI, theMeterPackage);
		return theMeterPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeterElement() {
		return meterElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeterElement_ElementName() {
		return (EAttribute)meterElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeterElement_MeteredElement() {
		return (EReference)meterElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeterElement_MeteredTerminal() {
		return (EAttribute)meterElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnergyMeter() {
		return energyMeterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_Element() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_Terminal() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_Action() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_Option() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_KVANorm() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_KVAEmerg() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_PeakCurrent() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_ZoneList() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_LocalOnly() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_Mask() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_Losses() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_LineLosses() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_XfmrLosses() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_SeqLosses() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_VBaseLosses() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnergyMeter_OverloadReport() {
		return (EAttribute)energyMeterEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMonitor() {
		return monitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_Element() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_Terminal() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_Mode() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_Action() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_Residual() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_VIPolar() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMonitor_PPolar() {
		return (EAttribute)monitorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSensor() {
		return sensorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Element() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Terminal() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Modes() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_V() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_I() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_P() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Q() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Phases() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Conn() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_PctError() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSensor_Action() {
		return (EAttribute)sensorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getmeterAction() {
		return meterActionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getmonitorValues() {
		return monitorValuesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getmonitorAction() {
		return monitorActionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getsensorMode() {
		return sensorModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getsensorAction() {
		return sensorActionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeterFactory getMeterFactory() {
		return (MeterFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		meterElementEClass = createEClass(METER_ELEMENT);
		createEAttribute(meterElementEClass, METER_ELEMENT__ELEMENT_NAME);
		createEReference(meterElementEClass, METER_ELEMENT__METERED_ELEMENT);
		createEAttribute(meterElementEClass, METER_ELEMENT__METERED_TERMINAL);

		energyMeterEClass = createEClass(ENERGY_METER);
		createEAttribute(energyMeterEClass, ENERGY_METER__ELEMENT);
		createEAttribute(energyMeterEClass, ENERGY_METER__TERMINAL);
		createEAttribute(energyMeterEClass, ENERGY_METER__ACTION);
		createEAttribute(energyMeterEClass, ENERGY_METER__OPTION);
		createEAttribute(energyMeterEClass, ENERGY_METER__KVA_NORM);
		createEAttribute(energyMeterEClass, ENERGY_METER__KVA_EMERG);
		createEAttribute(energyMeterEClass, ENERGY_METER__PEAK_CURRENT);
		createEAttribute(energyMeterEClass, ENERGY_METER__ZONE_LIST);
		createEAttribute(energyMeterEClass, ENERGY_METER__LOCAL_ONLY);
		createEAttribute(energyMeterEClass, ENERGY_METER__MASK);
		createEAttribute(energyMeterEClass, ENERGY_METER__LOSSES);
		createEAttribute(energyMeterEClass, ENERGY_METER__LINE_LOSSES);
		createEAttribute(energyMeterEClass, ENERGY_METER__XFMR_LOSSES);
		createEAttribute(energyMeterEClass, ENERGY_METER__SEQ_LOSSES);
		createEAttribute(energyMeterEClass, ENERGY_METER__VBASE_LOSSES);
		createEAttribute(energyMeterEClass, ENERGY_METER__OVERLOAD_REPORT);

		monitorEClass = createEClass(MONITOR);
		createEAttribute(monitorEClass, MONITOR__ELEMENT);
		createEAttribute(monitorEClass, MONITOR__TERMINAL);
		createEAttribute(monitorEClass, MONITOR__MODE);
		createEAttribute(monitorEClass, MONITOR__ACTION);
		createEAttribute(monitorEClass, MONITOR__RESIDUAL);
		createEAttribute(monitorEClass, MONITOR__VI_POLAR);
		createEAttribute(monitorEClass, MONITOR__PPOLAR);

		sensorEClass = createEClass(SENSOR);
		createEAttribute(sensorEClass, SENSOR__ELEMENT);
		createEAttribute(sensorEClass, SENSOR__TERMINAL);
		createEAttribute(sensorEClass, SENSOR__MODES);
		createEAttribute(sensorEClass, SENSOR__V);
		createEAttribute(sensorEClass, SENSOR__I);
		createEAttribute(sensorEClass, SENSOR__P);
		createEAttribute(sensorEClass, SENSOR__Q);
		createEAttribute(sensorEClass, SENSOR__PHASES);
		createEAttribute(sensorEClass, SENSOR__CONN);
		createEAttribute(sensorEClass, SENSOR__PCT_ERROR);
		createEAttribute(sensorEClass, SENSOR__ACTION);

		// Create enums
		meterActionEEnum = createEEnum(METER_ACTION);
		monitorValuesEEnum = createEEnum(MONITOR_VALUES);
		monitorActionEEnum = createEEnum(MONITOR_ACTION);
		sensorModeEEnum = createEEnum(SENSOR_MODE);
		sensorActionEEnum = createEEnum(SENSOR_ACTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		meterElementEClass.getESuperTypes().add(theCommonPackage.getCircuitElement());
		energyMeterEClass.getESuperTypes().add(this.getMeterElement());
		monitorEClass.getESuperTypes().add(this.getMeterElement());
		sensorEClass.getESuperTypes().add(this.getMeterElement());

		// Initialize classes and features; add operations and parameters
		initEClass(meterElementEClass, MeterElement.class, "MeterElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeterElement_ElementName(), ecorePackage.getEString(), "elementName", null, 0, 1, MeterElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeterElement_MeteredElement(), theCommonPackage.getCircuitElement(), null, "meteredElement", null, 0, 1, MeterElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeterElement_MeteredTerminal(), ecorePackage.getEInt(), "meteredTerminal", "1", 0, 1, MeterElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(energyMeterEClass, EnergyMeter.class, "EnergyMeter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnergyMeter_Element(), ecorePackage.getEString(), "element", null, 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_Terminal(), ecorePackage.getEInt(), "terminal", "1", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_Action(), this.getmeterAction(), "action", null, 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_Option(), ecorePackage.getEString(), "option", null, 0, -1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_KVANorm(), ecorePackage.getEDouble(), "kVANorm", null, 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_KVAEmerg(), ecorePackage.getEDouble(), "kVAEmerg", null, 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_PeakCurrent(), ecorePackage.getEDouble(), "peakCurrent", null, 0, -1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_ZoneList(), ecorePackage.getEString(), "zoneList", null, 0, -1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_LocalOnly(), ecorePackage.getEBoolean(), "localOnly", "false", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_Mask(), ecorePackage.getEDouble(), "mask", null, 0, -1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_Losses(), ecorePackage.getEBoolean(), "losses", "true", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_LineLosses(), ecorePackage.getEBoolean(), "lineLosses", "true", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_XfmrLosses(), ecorePackage.getEBoolean(), "xfmrLosses", "true", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_SeqLosses(), ecorePackage.getEBoolean(), "seqLosses", "true", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_VBaseLosses(), ecorePackage.getEBoolean(), "vBaseLosses", "true", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnergyMeter_OverloadReport(), ecorePackage.getEBoolean(), "overloadReport", "true", 0, 1, EnergyMeter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(monitorEClass, Monitor.class, "Monitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMonitor_Element(), ecorePackage.getEString(), "element", null, 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMonitor_Terminal(), ecorePackage.getEInt(), "terminal", "1", 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMonitor_Mode(), this.getmonitorValues(), "mode", null, 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMonitor_Action(), this.getmonitorAction(), "action", null, 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMonitor_Residual(), ecorePackage.getEBoolean(), "residual", "false", 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMonitor_VIPolar(), ecorePackage.getEBoolean(), "vIPolar", "true", 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMonitor_PPolar(), ecorePackage.getEBoolean(), "pPolar", "false", 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sensorEClass, Sensor.class, "Sensor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSensor_Element(), ecorePackage.getEString(), "element", null, 0, 1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_Terminal(), ecorePackage.getEInt(), "terminal", "1", 0, 1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_Modes(), this.getsensorMode(), "modes", null, 0, -1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_V(), ecorePackage.getEDouble(), "v", "7.2", 0, -1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_I(), ecorePackage.getEDouble(), "i", null, 0, -1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_P(), ecorePackage.getEDouble(), "p", null, 0, -1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_Q(), ecorePackage.getEDouble(), "q", null, 0, -1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_Phases(), ecorePackage.getEInt(), "phases", null, 0, 3, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_Conn(), theCommonPackage.getconnectionType(), "conn", null, 0, 1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_PctError(), ecorePackage.getEDouble(), "pctError", "1.0", 0, 1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSensor_Action(), this.getsensorAction(), "action", null, 0, 1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(meterActionEEnum, meterAction.class, "meterAction");
		addEEnumLiteral(meterActionEEnum, meterAction.CLEAR);
		addEEnumLiteral(meterActionEEnum, meterAction.SAVE);
		addEEnumLiteral(meterActionEEnum, meterAction.TAKE);
		addEEnumLiteral(meterActionEEnum, meterAction.ZONEDUMP);
		addEEnumLiteral(meterActionEEnum, meterAction.ALLOCATE);
		addEEnumLiteral(meterActionEEnum, meterAction.REDUCE);

		initEEnum(monitorValuesEEnum, monitorValues.class, "monitorValues");
		addEEnumLiteral(monitorValuesEEnum, monitorValues.VI);
		addEEnumLiteral(monitorValuesEEnum, monitorValues.PQ);
		addEEnumLiteral(monitorValuesEEnum, monitorValues.TAP_POSITION);
		addEEnumLiteral(monitorValuesEEnum, monitorValues.STATE_VARIABLES);

		initEEnum(monitorActionEEnum, monitorAction.class, "monitorAction");
		addEEnumLiteral(monitorActionEEnum, monitorAction.CLEAR);
		addEEnumLiteral(monitorActionEEnum, monitorAction.SAVE);
		addEEnumLiteral(monitorActionEEnum, monitorAction.TAKE);

		initEEnum(sensorModeEEnum, sensorMode.class, "sensorMode");
		addEEnumLiteral(sensorModeEEnum, sensorMode.VOLTAGE);
		addEEnumLiteral(sensorModeEEnum, sensorMode.CURRENT);
		addEEnumLiteral(sensorModeEEnum, sensorMode.KW);
		addEEnumLiteral(sensorModeEEnum, sensorMode.KVAR);

		initEEnum(sensorActionEEnum, sensorAction.class, "sensorAction");
		addEEnumLiteral(sensorActionEEnum, sensorAction.SQUARE_ERROR);
		addEEnumLiteral(sensorActionEEnum, sensorAction.ACTUAL_VALUES);
	}

} //MeterPackageImpl
