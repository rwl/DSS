/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;



import electrickery.common.Circuit;
import electrickery.common.connectionType;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.Load;
import electrickery.conversion.loadModel;
import electrickery.conversion.loadSpecType;
import electrickery.conversion.loadStatus;
import electrickery.general.GrowthShape;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import cern.jet.math.tdcomplex.DComplexFunctions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Load</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getGrowthShapeObj <em>Growth Shape Obj</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getKW <em>KW</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getPF <em>PF</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getModel <em>Model</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getLoadSpec <em>Load Spec</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getGrowth <em>Growth</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getVMinPU <em>VMin PU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getVMaxPU <em>VMax PU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getVMinNorm <em>VMin Norm</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getVMinEmerg <em>VMin Emerg</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getXfKVA <em>Xf KVA</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getAllocationFactor <em>Allocation Factor</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getPctMean <em>Pct Mean</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getPctStdDev <em>Pct Std Dev</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getCvrWatts <em>Cvr Watts</em>}</li>
 *   <li>{@link electrickery.conversion.impl.LoadImpl#getCvrVars <em>Cvr Vars</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoadImpl extends PowerConversionElementImpl implements Load {
    /**
	 * The cached value of the '{@link #getCircuit() <em>Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCircuit()
	 * @generated
	 * @ordered
	 */
    protected Circuit circuit;

                /**
	 * The cached value of the '{@link #getGrowthShapeObj() <em>Growth Shape Obj</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGrowthShapeObj()
	 * @generated
	 * @ordered
	 */
    protected GrowthShape growthShapeObj;

                                                                /**
	 * The default value of the '{@link #getBus1() <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBus1()
	 * @generated
	 * @ordered
	 */
    protected static final String BUS1_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getBus1() <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBus1()
	 * @generated
	 * @ordered
	 */
    protected String bus1 = BUS1_EDEFAULT;

    /**
	 * The default value of the '{@link #getKV() <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKV()
	 * @generated
	 * @ordered
	 */
    protected static final double KV_EDEFAULT = 12.47;

    /**
	 * The cached value of the '{@link #getKV() <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKV()
	 * @generated
	 * @ordered
	 */
    protected double kV = KV_EDEFAULT;

    /**
	 * The default value of the '{@link #getKW() <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKW()
	 * @generated
	 * @ordered
	 */
    protected static final double KW_EDEFAULT = 10.0;

    /**
	 * The cached value of the '{@link #getKW() <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKW()
	 * @generated
	 * @ordered
	 */
    protected double kW = KW_EDEFAULT;

    /**
	 * The default value of the '{@link #getKVAr() <em>KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKVAr()
	 * @generated
	 * @ordered
	 */
    protected static final double KV_AR_EDEFAULT = 5.0;

    /**
	 * The cached value of the '{@link #getKVAr() <em>KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKVAr()
	 * @generated
	 * @ordered
	 */
    protected double kVAr = KV_AR_EDEFAULT;

                /**
	 * The default value of the '{@link #getPF() <em>PF</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPF()
	 * @generated
	 * @ordered
	 */
    protected static final double PF_EDEFAULT = 0.88;

                /**
	 * The cached value of the '{@link #getPF() <em>PF</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPF()
	 * @generated
	 * @ordered
	 */
    protected double pF = PF_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
    protected static final loadModel MODEL_EDEFAULT = loadModel.PQ;

                /**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
    protected loadModel model = MODEL_EDEFAULT;

                /**
	 * The default value of the '{@link #getLoadSpec() <em>Load Spec</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLoadSpec()
	 * @generated
	 * @ordered
	 */
    protected static final loadSpecType LOAD_SPEC_EDEFAULT = loadSpecType.KW_PF;

                                                                /**
	 * The cached value of the '{@link #getLoadSpec() <em>Load Spec</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLoadSpec()
	 * @generated
	 * @ordered
	 */
    protected loadSpecType loadSpec = LOAD_SPEC_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getYearly() <em>Yearly</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYearly()
	 * @generated
	 * @ordered
	 */
    protected static final String YEARLY_EDEFAULT = null;

                /**
	 * The cached value of the '{@link #getYearly() <em>Yearly</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYearly()
	 * @generated
	 * @ordered
	 */
    protected String yearly = YEARLY_EDEFAULT;

                /**
	 * The default value of the '{@link #getDaily() <em>Daily</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDaily()
	 * @generated
	 * @ordered
	 */
    protected static final String DAILY_EDEFAULT = null;

                /**
	 * The cached value of the '{@link #getDaily() <em>Daily</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDaily()
	 * @generated
	 * @ordered
	 */
    protected String daily = DAILY_EDEFAULT;

                /**
	 * The default value of the '{@link #getDuty() <em>Duty</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDuty()
	 * @generated
	 * @ordered
	 */
    protected static final String DUTY_EDEFAULT = null;

                /**
	 * The cached value of the '{@link #getDuty() <em>Duty</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDuty()
	 * @generated
	 * @ordered
	 */
    protected String duty = DUTY_EDEFAULT;

                /**
	 * The default value of the '{@link #getGrowth() <em>Growth</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGrowth()
	 * @generated
	 * @ordered
	 */
    protected static final String GROWTH_EDEFAULT = null;

                /**
	 * The cached value of the '{@link #getGrowth() <em>Growth</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGrowth()
	 * @generated
	 * @ordered
	 */
    protected String growth = GROWTH_EDEFAULT;

                /**
	 * The default value of the '{@link #getConn() <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getConn()
	 * @generated
	 * @ordered
	 */
    protected static final connectionType CONN_EDEFAULT = connectionType.WYE;

                /**
	 * The cached value of the '{@link #getConn() <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getConn()
	 * @generated
	 * @ordered
	 */
    protected connectionType conn = CONN_EDEFAULT;

    /**
	 * The default value of the '{@link #getRNeut() <em>RNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRNeut()
	 * @generated
	 * @ordered
	 */
    protected static final double RNEUT_EDEFAULT = -1.0;

    /**
	 * The cached value of the '{@link #getRNeut() <em>RNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRNeut()
	 * @generated
	 * @ordered
	 */
    protected double rNeut = RNEUT_EDEFAULT;

    /**
	 * The default value of the '{@link #getXNeut() <em>XNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXNeut()
	 * @generated
	 * @ordered
	 */
    protected static final double XNEUT_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getXNeut() <em>XNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXNeut()
	 * @generated
	 * @ordered
	 */
    protected double xNeut = XNEUT_EDEFAULT;

    /**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
    protected static final loadStatus STATUS_EDEFAULT = loadStatus.VARIABLE;

    /**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
    protected loadStatus status = STATUS_EDEFAULT;

    /**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
    protected static final int CLASS_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
    protected int class_ = CLASS_EDEFAULT;

    /**
	 * The default value of the '{@link #getVMinPU() <em>VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMinPU()
	 * @generated
	 * @ordered
	 */
    protected static final double VMIN_PU_EDEFAULT = 0.95;

    /**
	 * The cached value of the '{@link #getVMinPU() <em>VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMinPU()
	 * @generated
	 * @ordered
	 */
    protected double vMinPU = VMIN_PU_EDEFAULT;

    /**
	 * The default value of the '{@link #getVMaxPU() <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMaxPU()
	 * @generated
	 * @ordered
	 */
    protected static final double VMAX_PU_EDEFAULT = 1.05;

    /**
	 * The cached value of the '{@link #getVMaxPU() <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMaxPU()
	 * @generated
	 * @ordered
	 */
    protected double vMaxPU = VMAX_PU_EDEFAULT;

    /**
	 * The default value of the '{@link #getVMinNorm() <em>VMin Norm</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMinNorm()
	 * @generated
	 * @ordered
	 */
    protected static final double VMIN_NORM_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getVMinNorm() <em>VMin Norm</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMinNorm()
	 * @generated
	 * @ordered
	 */
    protected double vMinNorm = VMIN_NORM_EDEFAULT;

    /**
	 * The default value of the '{@link #getVMinEmerg() <em>VMin Emerg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMinEmerg()
	 * @generated
	 * @ordered
	 */
    protected static final double VMIN_EMERG_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getVMinEmerg() <em>VMin Emerg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMinEmerg()
	 * @generated
	 * @ordered
	 */
    protected double vMinEmerg = VMIN_EMERG_EDEFAULT;

    /**
	 * The default value of the '{@link #getXfKVA() <em>Xf KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXfKVA()
	 * @generated
	 * @ordered
	 */
    protected static final double XF_KVA_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getXfKVA() <em>Xf KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXfKVA()
	 * @generated
	 * @ordered
	 */
    protected double xfKVA = XF_KVA_EDEFAULT;

    /**
	 * The default value of the '{@link #getAllocationFactor() <em>Allocation Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAllocationFactor()
	 * @generated
	 * @ordered
	 */
    protected static final double ALLOCATION_FACTOR_EDEFAULT = 0.5;

    /**
	 * The cached value of the '{@link #getAllocationFactor() <em>Allocation Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAllocationFactor()
	 * @generated
	 * @ordered
	 */
    protected double allocationFactor = ALLOCATION_FACTOR_EDEFAULT;

    /**
	 * The default value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
    protected static final double KVA_EDEFAULT = 11.3636;

    /**
	 * The cached value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
    protected double kVA = KVA_EDEFAULT;

    /**
	 * The default value of the '{@link #getPctMean() <em>Pct Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPctMean()
	 * @generated
	 * @ordered
	 */
    protected static final double PCT_MEAN_EDEFAULT = 50.0;

    /**
	 * The cached value of the '{@link #getPctMean() <em>Pct Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPctMean()
	 * @generated
	 * @ordered
	 */
    protected double pctMean = PCT_MEAN_EDEFAULT;

    /**
	 * The default value of the '{@link #getPctStdDev() <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPctStdDev()
	 * @generated
	 * @ordered
	 */
    protected static final double PCT_STD_DEV_EDEFAULT = 10.0;

    /**
	 * The cached value of the '{@link #getPctStdDev() <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPctStdDev()
	 * @generated
	 * @ordered
	 */
    protected double pctStdDev = PCT_STD_DEV_EDEFAULT;

    /**
	 * The default value of the '{@link #getCvrWatts() <em>Cvr Watts</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCvrWatts()
	 * @generated
	 * @ordered
	 */
    protected static final double CVR_WATTS_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getCvrWatts() <em>Cvr Watts</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCvrWatts()
	 * @generated
	 * @ordered
	 */
    protected double cvrWatts = CVR_WATTS_EDEFAULT;

    /**
	 * The default value of the '{@link #getCvrVars() <em>Cvr Vars</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCvrVars()
	 * @generated
	 * @ordered
	 */
    protected static final double CVR_VARS_EDEFAULT = 2.0;

    /**
	 * The cached value of the '{@link #getCvrVars() <em>Cvr Vars</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCvrVars()
	 * @generated
	 * @ordered
	 */
    protected double cvrVars = CVR_VARS_EDEFAULT;

    private boolean exemptFromLDCurve;

    /**
     * If fixed, always at base value
     */
    private boolean fixed;

    private double lastGrowthFactor;

    private int lastYear;

    private double[] shapeFactor;

    private double varBase;
    private double varNominal;

    /**
     * Base volts suitable for computing currents.
     */
    private double vBase;
    private double vBase105;
    private double vBase95;

    /**
     * Nominal Watts per phase.
     */
    private double wNominal;

    /**
     * Y at nominal voltage.
     */
    private double[] yEq;

    /**
     * Y at 105% voltage.
     */
    private double[] yEq105;

    /**
     * Y at 95% voltage.
     */
    private double[] yEq95;

    private double[] yNeut;

    /**
     * Fixed value of y for type 7 load.
     */
    private double yQFixed;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected LoadImpl() {
        super();
        exemptFromLDCurve = false;
        fixed = false;
        lastGrowthFactor = 1.0;
        lastYear = 0;
        vBase = 7200.0;
        yEq = new double[2];
        yEq105 = new double[2];
        yEq95 = new double[2];
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConversionPackage.Literals.LOAD;
	}

    /**
     *
     */
    public void setNominalLoad() {
        double factor = 1.0;
        double[] doubleOne = {1.0, 1.0};
        shapeFactor = doubleOne;
        if (fixed) {
            // For fixed loads, consider only growth factor.
            factor = growthFactor(getCircuit().getSolution().getYear());
        } else {
            switch (getCircuit().getSolution().getMode()) {
                case SNAPSHOT:
                    if (exemptFromLDCurve) {
                        factor = growthFactor(getCircuit().getSolution().getYear());
                    } else {
                        factor = getCircuit().getLoadMultiplier() * growthFactor(getCircuit().getSolution().getYear());
                    }
                case DAILY:
                    // TODO: Remaining solution modes.
            }
        }

        wNominal = 1000.0 * getKW() * factor * shapeFactor[0] / getNPhases();
        varNominal = 1000.0 * getKVAr() * factor * shapeFactor[1] / getNPhases();

        yEq[0] = wNominal / Math.pow(vBase, 2);
        yEq[1] = -varNominal / Math.pow(vBase, 2);

        if (getVMinPU() != 0.0) {
            yEq95[0] = yEq[0] / Math.pow(getVMinPU(), 2);
            yEq95[1] = yEq[1] / Math.pow(getVMinPU(), 2);
        } else {
            yEq95[0] = 0.0;
            yEq95[1] = 0.0;
        }

        if (getVMaxPU() != 0.0) {
            yEq105[0] = yEq[0] / Math.pow(getVMaxPU(), 2);
            yEq105[1] = yEq[1] / Math.pow(getVMaxPU(), 2);
        } else {
            yEq105 = yEq;
        }
    }

    /**
     *
     */
    @Override
    public void recalcElementData() {
        vBase95 = getVMinPU() * vBase;
        vBase105 = getVMaxPU() * vBase;

        // Set kW and kVAr from root values of kVA and PF.

        switch (getLoadSpec()) {
        case KW_PF:
            setKVAr(getKW() * Math.sqrt(1.0 / Math.pow(getPF(), 2) - 1.0));
            if (getPF() < 0.0)
                setKVAr(-getKVAr());
            break;
        case KW_KVAR: // Need to set PFNominal.
            setKVA(Math.sqrt(Math.pow(getKW(), 2) + Math.pow(getKVAr(), 2)));
            if (getKVA() > 0.0) {
                setPF(getKW() / getKVA());
                // If kW and kVAr are different signs, PF is negative.
                if (getKVAr() != 0.0)
                    setPF(getPF() * Math.signum(getKW() * getKVAr()));
            }
        case KVA_PF:
            setKW(getKVA() * Math.abs(getPF()));
            setKVAr(getKW() * Math.sqrt(1.0 / Math.pow(getPF(), 2) - 1.0));
            if (getPF() < 0.0)
                setKVAr(-getKVAr());
        }

        setNominalLoad();

        if (getRNeut() < 0.0) { // Flag for open neutral.
            yNeut = new double[] {0.0, 0.0};
        } else if (getRNeut() == 0.0 && getXNeut() == 0.0) { // Solidly grounded.
            yNeut = new double[] {1.0e6, 0.0}; // 1 micro ohm resistor.
        } else {
            yNeut = new double[] {getRNeut(), getXNeut()};
            yNeut = DComplexFunctions.inv.apply(yNeut);
        }

        varBase = 1000.0 * getKVAr() / getNPhases();
        yQFixed = -varBase / Math.pow(vBase, 2);
    }

    /**
     *
     * @param year
     * @return
     */
    private double growthFactor(int year) {
        if (year == 0) {
            // Default all to 1 in year 0; use base values.
            lastGrowthFactor = 1.0;
        } else {
            if (getGrowthShapeObj() == null) {
                lastGrowthFactor = getCircuit().getDefaultGrowthFactor();
            } else if (year != lastYear) {
                lastGrowthFactor = getGrowthShapeObj().getMult(year);
            }
        }
        return lastGrowthFactor;
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit getCircuit() {
		if (circuit != null && circuit.eIsProxy()) {
			InternalEObject oldCircuit = (InternalEObject)circuit;
			circuit = (Circuit)eResolveProxy(oldCircuit);
			if (circuit != oldCircuit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.LOAD__CIRCUIT, oldCircuit, circuit));
			}
		}
		return circuit;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit basicGetCircuit() {
		return circuit;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCircuit(Circuit newCircuit) {
		Circuit oldCircuit = circuit;
		circuit = newCircuit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__CIRCUIT, oldCircuit, circuit));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GrowthShape getGrowthShapeObj() {
		if (growthShapeObj != null && growthShapeObj.eIsProxy()) {
			InternalEObject oldGrowthShapeObj = (InternalEObject)growthShapeObj;
			growthShapeObj = (GrowthShape)eResolveProxy(oldGrowthShapeObj);
			if (growthShapeObj != oldGrowthShapeObj) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.LOAD__GROWTH_SHAPE_OBJ, oldGrowthShapeObj, growthShapeObj));
			}
		}
		return growthShapeObj;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GrowthShape basicGetGrowthShapeObj() {
		return growthShapeObj;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrowthShapeObj(GrowthShape newGrowthShapeObj) {
		GrowthShape oldGrowthShapeObj = growthShapeObj;
		growthShapeObj = newGrowthShapeObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__GROWTH_SHAPE_OBJ, oldGrowthShapeObj, growthShapeObj));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getBus1() {
		return bus1;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBus1(String newBus1) {
		String oldBus1 = bus1;
		bus1 = newBus1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__BUS1, oldBus1, bus1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getKV() {
		return kV;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKV(double newKV) {
		double oldKV = kV;
		kV = newKV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__KV, oldKV, kV));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getKW() {
		return kW;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKW(double newKW) {
		double oldKW = kW;
		kW = newKW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__KW, oldKW, kW));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public loadModel getModel() {
		return model;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setModel(loadModel newModel) {
		loadModel oldModel = model;
		model = newModel == null ? MODEL_EDEFAULT : newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__MODEL, oldModel, model));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public loadSpecType getLoadSpec() {
		return loadSpec;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLoadSpec(loadSpecType newLoadSpec) {
		loadSpecType oldLoadSpec = loadSpec;
		loadSpec = newLoadSpec == null ? LOAD_SPEC_EDEFAULT : newLoadSpec;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__LOAD_SPEC, oldLoadSpec, loadSpec));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getYearly() {
		return yearly;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYearly(String newYearly) {
		String oldYearly = yearly;
		yearly = newYearly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__YEARLY, oldYearly, yearly));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getDaily() {
		return daily;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDaily(String newDaily) {
		String oldDaily = daily;
		daily = newDaily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__DAILY, oldDaily, daily));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getDuty() {
		return duty;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDuty(String newDuty) {
		String oldDuty = duty;
		duty = newDuty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__DUTY, oldDuty, duty));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getGrowth() {
		return growth;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrowth(String newGrowth) {
		String oldGrowth = growth;
		growth = newGrowth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__GROWTH, oldGrowth, growth));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public connectionType getConn() {
		return conn;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setConn(connectionType newConn) {
		connectionType oldConn = conn;
		conn = newConn == null ? CONN_EDEFAULT : newConn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__CONN, oldConn, conn));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getKVAr() {
		return kVAr;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKVAr(double newKVAr) {
		double oldKVAr = kVAr;
		kVAr = newKVAr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__KV_AR, oldKVAr, kVAr));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPF() {
		return pF;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPF(double newPF) {
		double oldPF = pF;
		pF = newPF;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__PF, oldPF, pF));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getRNeut() {
		return rNeut;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRNeut(double newRNeut) {
		double oldRNeut = rNeut;
		rNeut = newRNeut;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__RNEUT, oldRNeut, rNeut));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getXNeut() {
		return xNeut;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXNeut(double newXNeut) {
		double oldXNeut = xNeut;
		xNeut = newXNeut;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__XNEUT, oldXNeut, xNeut));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public loadStatus getStatus() {
		return status;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStatus(loadStatus newStatus) {
		loadStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__STATUS, oldStatus, status));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getClass_() {
		return class_;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setClass(int newClass) {
		int oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__CLASS, oldClass, class_));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVMinPU() {
		return vMinPU;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVMinPU(double newVMinPU) {
		double oldVMinPU = vMinPU;
		vMinPU = newVMinPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__VMIN_PU, oldVMinPU, vMinPU));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVMaxPU() {
		return vMaxPU;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVMaxPU(double newVMaxPU) {
		double oldVMaxPU = vMaxPU;
		vMaxPU = newVMaxPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__VMAX_PU, oldVMaxPU, vMaxPU));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVMinNorm() {
		return vMinNorm;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVMinNorm(double newVMinNorm) {
		double oldVMinNorm = vMinNorm;
		vMinNorm = newVMinNorm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__VMIN_NORM, oldVMinNorm, vMinNorm));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVMinEmerg() {
		return vMinEmerg;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVMinEmerg(double newVMinEmerg) {
		double oldVMinEmerg = vMinEmerg;
		vMinEmerg = newVMinEmerg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__VMIN_EMERG, oldVMinEmerg, vMinEmerg));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getXfKVA() {
		return xfKVA;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXfKVA(double newXfKVA) {
		double oldXfKVA = xfKVA;
		xfKVA = newXfKVA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__XF_KVA, oldXfKVA, xfKVA));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getAllocationFactor() {
		return allocationFactor;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAllocationFactor(double newAllocationFactor) {
		double oldAllocationFactor = allocationFactor;
		allocationFactor = newAllocationFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__ALLOCATION_FACTOR, oldAllocationFactor, allocationFactor));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getKVA() {
		return kVA;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKVA(double newKVA) {
		double oldKVA = kVA;
		kVA = newKVA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__KVA, oldKVA, kVA));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPctMean() {
		return pctMean;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPctMean(double newPctMean) {
		double oldPctMean = pctMean;
		pctMean = newPctMean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__PCT_MEAN, oldPctMean, pctMean));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPctStdDev() {
		return pctStdDev;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPctStdDev(double newPctStdDev) {
		double oldPctStdDev = pctStdDev;
		pctStdDev = newPctStdDev;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__PCT_STD_DEV, oldPctStdDev, pctStdDev));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getCvrWatts() {
		return cvrWatts;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCvrWatts(double newCvrWatts) {
		double oldCvrWatts = cvrWatts;
		cvrWatts = newCvrWatts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__CVR_WATTS, oldCvrWatts, cvrWatts));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getCvrVars() {
		return cvrVars;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCvrVars(double newCvrVars) {
		double oldCvrVars = cvrVars;
		cvrVars = newCvrVars;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.LOAD__CVR_VARS, oldCvrVars, cvrVars));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.LOAD__CIRCUIT:
				if (resolve) return getCircuit();
				return basicGetCircuit();
			case ConversionPackage.LOAD__GROWTH_SHAPE_OBJ:
				if (resolve) return getGrowthShapeObj();
				return basicGetGrowthShapeObj();
			case ConversionPackage.LOAD__BUS1:
				return getBus1();
			case ConversionPackage.LOAD__KV:
				return getKV();
			case ConversionPackage.LOAD__KW:
				return getKW();
			case ConversionPackage.LOAD__KV_AR:
				return getKVAr();
			case ConversionPackage.LOAD__PF:
				return getPF();
			case ConversionPackage.LOAD__MODEL:
				return getModel();
			case ConversionPackage.LOAD__LOAD_SPEC:
				return getLoadSpec();
			case ConversionPackage.LOAD__YEARLY:
				return getYearly();
			case ConversionPackage.LOAD__DAILY:
				return getDaily();
			case ConversionPackage.LOAD__DUTY:
				return getDuty();
			case ConversionPackage.LOAD__GROWTH:
				return getGrowth();
			case ConversionPackage.LOAD__CONN:
				return getConn();
			case ConversionPackage.LOAD__RNEUT:
				return getRNeut();
			case ConversionPackage.LOAD__XNEUT:
				return getXNeut();
			case ConversionPackage.LOAD__STATUS:
				return getStatus();
			case ConversionPackage.LOAD__CLASS:
				return getClass_();
			case ConversionPackage.LOAD__VMIN_PU:
				return getVMinPU();
			case ConversionPackage.LOAD__VMAX_PU:
				return getVMaxPU();
			case ConversionPackage.LOAD__VMIN_NORM:
				return getVMinNorm();
			case ConversionPackage.LOAD__VMIN_EMERG:
				return getVMinEmerg();
			case ConversionPackage.LOAD__XF_KVA:
				return getXfKVA();
			case ConversionPackage.LOAD__ALLOCATION_FACTOR:
				return getAllocationFactor();
			case ConversionPackage.LOAD__KVA:
				return getKVA();
			case ConversionPackage.LOAD__PCT_MEAN:
				return getPctMean();
			case ConversionPackage.LOAD__PCT_STD_DEV:
				return getPctStdDev();
			case ConversionPackage.LOAD__CVR_WATTS:
				return getCvrWatts();
			case ConversionPackage.LOAD__CVR_VARS:
				return getCvrVars();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConversionPackage.LOAD__CIRCUIT:
				setCircuit((Circuit)newValue);
				return;
			case ConversionPackage.LOAD__GROWTH_SHAPE_OBJ:
				setGrowthShapeObj((GrowthShape)newValue);
				return;
			case ConversionPackage.LOAD__BUS1:
				setBus1((String)newValue);
				return;
			case ConversionPackage.LOAD__KV:
				setKV((Double)newValue);
				return;
			case ConversionPackage.LOAD__KW:
				setKW((Double)newValue);
				return;
			case ConversionPackage.LOAD__KV_AR:
				setKVAr((Double)newValue);
				return;
			case ConversionPackage.LOAD__PF:
				setPF((Double)newValue);
				return;
			case ConversionPackage.LOAD__MODEL:
				setModel((loadModel)newValue);
				return;
			case ConversionPackage.LOAD__LOAD_SPEC:
				setLoadSpec((loadSpecType)newValue);
				return;
			case ConversionPackage.LOAD__YEARLY:
				setYearly((String)newValue);
				return;
			case ConversionPackage.LOAD__DAILY:
				setDaily((String)newValue);
				return;
			case ConversionPackage.LOAD__DUTY:
				setDuty((String)newValue);
				return;
			case ConversionPackage.LOAD__GROWTH:
				setGrowth((String)newValue);
				return;
			case ConversionPackage.LOAD__CONN:
				setConn((connectionType)newValue);
				return;
			case ConversionPackage.LOAD__RNEUT:
				setRNeut((Double)newValue);
				return;
			case ConversionPackage.LOAD__XNEUT:
				setXNeut((Double)newValue);
				return;
			case ConversionPackage.LOAD__STATUS:
				setStatus((loadStatus)newValue);
				return;
			case ConversionPackage.LOAD__CLASS:
				setClass((Integer)newValue);
				return;
			case ConversionPackage.LOAD__VMIN_PU:
				setVMinPU((Double)newValue);
				return;
			case ConversionPackage.LOAD__VMAX_PU:
				setVMaxPU((Double)newValue);
				return;
			case ConversionPackage.LOAD__VMIN_NORM:
				setVMinNorm((Double)newValue);
				return;
			case ConversionPackage.LOAD__VMIN_EMERG:
				setVMinEmerg((Double)newValue);
				return;
			case ConversionPackage.LOAD__XF_KVA:
				setXfKVA((Double)newValue);
				return;
			case ConversionPackage.LOAD__ALLOCATION_FACTOR:
				setAllocationFactor((Double)newValue);
				return;
			case ConversionPackage.LOAD__KVA:
				setKVA((Double)newValue);
				return;
			case ConversionPackage.LOAD__PCT_MEAN:
				setPctMean((Double)newValue);
				return;
			case ConversionPackage.LOAD__PCT_STD_DEV:
				setPctStdDev((Double)newValue);
				return;
			case ConversionPackage.LOAD__CVR_WATTS:
				setCvrWatts((Double)newValue);
				return;
			case ConversionPackage.LOAD__CVR_VARS:
				setCvrVars((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case ConversionPackage.LOAD__CIRCUIT:
				setCircuit((Circuit)null);
				return;
			case ConversionPackage.LOAD__GROWTH_SHAPE_OBJ:
				setGrowthShapeObj((GrowthShape)null);
				return;
			case ConversionPackage.LOAD__BUS1:
				setBus1(BUS1_EDEFAULT);
				return;
			case ConversionPackage.LOAD__KV:
				setKV(KV_EDEFAULT);
				return;
			case ConversionPackage.LOAD__KW:
				setKW(KW_EDEFAULT);
				return;
			case ConversionPackage.LOAD__KV_AR:
				setKVAr(KV_AR_EDEFAULT);
				return;
			case ConversionPackage.LOAD__PF:
				setPF(PF_EDEFAULT);
				return;
			case ConversionPackage.LOAD__MODEL:
				setModel(MODEL_EDEFAULT);
				return;
			case ConversionPackage.LOAD__LOAD_SPEC:
				setLoadSpec(LOAD_SPEC_EDEFAULT);
				return;
			case ConversionPackage.LOAD__YEARLY:
				setYearly(YEARLY_EDEFAULT);
				return;
			case ConversionPackage.LOAD__DAILY:
				setDaily(DAILY_EDEFAULT);
				return;
			case ConversionPackage.LOAD__DUTY:
				setDuty(DUTY_EDEFAULT);
				return;
			case ConversionPackage.LOAD__GROWTH:
				setGrowth(GROWTH_EDEFAULT);
				return;
			case ConversionPackage.LOAD__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case ConversionPackage.LOAD__RNEUT:
				setRNeut(RNEUT_EDEFAULT);
				return;
			case ConversionPackage.LOAD__XNEUT:
				setXNeut(XNEUT_EDEFAULT);
				return;
			case ConversionPackage.LOAD__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case ConversionPackage.LOAD__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case ConversionPackage.LOAD__VMIN_PU:
				setVMinPU(VMIN_PU_EDEFAULT);
				return;
			case ConversionPackage.LOAD__VMAX_PU:
				setVMaxPU(VMAX_PU_EDEFAULT);
				return;
			case ConversionPackage.LOAD__VMIN_NORM:
				setVMinNorm(VMIN_NORM_EDEFAULT);
				return;
			case ConversionPackage.LOAD__VMIN_EMERG:
				setVMinEmerg(VMIN_EMERG_EDEFAULT);
				return;
			case ConversionPackage.LOAD__XF_KVA:
				setXfKVA(XF_KVA_EDEFAULT);
				return;
			case ConversionPackage.LOAD__ALLOCATION_FACTOR:
				setAllocationFactor(ALLOCATION_FACTOR_EDEFAULT);
				return;
			case ConversionPackage.LOAD__KVA:
				setKVA(KVA_EDEFAULT);
				return;
			case ConversionPackage.LOAD__PCT_MEAN:
				setPctMean(PCT_MEAN_EDEFAULT);
				return;
			case ConversionPackage.LOAD__PCT_STD_DEV:
				setPctStdDev(PCT_STD_DEV_EDEFAULT);
				return;
			case ConversionPackage.LOAD__CVR_WATTS:
				setCvrWatts(CVR_WATTS_EDEFAULT);
				return;
			case ConversionPackage.LOAD__CVR_VARS:
				setCvrVars(CVR_VARS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConversionPackage.LOAD__CIRCUIT:
				return circuit != null;
			case ConversionPackage.LOAD__GROWTH_SHAPE_OBJ:
				return growthShapeObj != null;
			case ConversionPackage.LOAD__BUS1:
				return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
			case ConversionPackage.LOAD__KV:
				return kV != KV_EDEFAULT;
			case ConversionPackage.LOAD__KW:
				return kW != KW_EDEFAULT;
			case ConversionPackage.LOAD__KV_AR:
				return kVAr != KV_AR_EDEFAULT;
			case ConversionPackage.LOAD__PF:
				return pF != PF_EDEFAULT;
			case ConversionPackage.LOAD__MODEL:
				return model != MODEL_EDEFAULT;
			case ConversionPackage.LOAD__LOAD_SPEC:
				return loadSpec != LOAD_SPEC_EDEFAULT;
			case ConversionPackage.LOAD__YEARLY:
				return YEARLY_EDEFAULT == null ? yearly != null : !YEARLY_EDEFAULT.equals(yearly);
			case ConversionPackage.LOAD__DAILY:
				return DAILY_EDEFAULT == null ? daily != null : !DAILY_EDEFAULT.equals(daily);
			case ConversionPackage.LOAD__DUTY:
				return DUTY_EDEFAULT == null ? duty != null : !DUTY_EDEFAULT.equals(duty);
			case ConversionPackage.LOAD__GROWTH:
				return GROWTH_EDEFAULT == null ? growth != null : !GROWTH_EDEFAULT.equals(growth);
			case ConversionPackage.LOAD__CONN:
				return conn != CONN_EDEFAULT;
			case ConversionPackage.LOAD__RNEUT:
				return rNeut != RNEUT_EDEFAULT;
			case ConversionPackage.LOAD__XNEUT:
				return xNeut != XNEUT_EDEFAULT;
			case ConversionPackage.LOAD__STATUS:
				return status != STATUS_EDEFAULT;
			case ConversionPackage.LOAD__CLASS:
				return class_ != CLASS_EDEFAULT;
			case ConversionPackage.LOAD__VMIN_PU:
				return vMinPU != VMIN_PU_EDEFAULT;
			case ConversionPackage.LOAD__VMAX_PU:
				return vMaxPU != VMAX_PU_EDEFAULT;
			case ConversionPackage.LOAD__VMIN_NORM:
				return vMinNorm != VMIN_NORM_EDEFAULT;
			case ConversionPackage.LOAD__VMIN_EMERG:
				return vMinEmerg != VMIN_EMERG_EDEFAULT;
			case ConversionPackage.LOAD__XF_KVA:
				return xfKVA != XF_KVA_EDEFAULT;
			case ConversionPackage.LOAD__ALLOCATION_FACTOR:
				return allocationFactor != ALLOCATION_FACTOR_EDEFAULT;
			case ConversionPackage.LOAD__KVA:
				return kVA != KVA_EDEFAULT;
			case ConversionPackage.LOAD__PCT_MEAN:
				return pctMean != PCT_MEAN_EDEFAULT;
			case ConversionPackage.LOAD__PCT_STD_DEV:
				return pctStdDev != PCT_STD_DEV_EDEFAULT;
			case ConversionPackage.LOAD__CVR_WATTS:
				return cvrWatts != CVR_WATTS_EDEFAULT;
			case ConversionPackage.LOAD__CVR_VARS:
				return cvrVars != CVR_VARS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (bus1: ");
		result.append(bus1);
		result.append(", kV: ");
		result.append(kV);
		result.append(", kW: ");
		result.append(kW);
		result.append(", kVAr: ");
		result.append(kVAr);
		result.append(", pF: ");
		result.append(pF);
		result.append(", model: ");
		result.append(model);
		result.append(", loadSpec: ");
		result.append(loadSpec);
		result.append(", yearly: ");
		result.append(yearly);
		result.append(", daily: ");
		result.append(daily);
		result.append(", duty: ");
		result.append(duty);
		result.append(", growth: ");
		result.append(growth);
		result.append(", conn: ");
		result.append(conn);
		result.append(", rNeut: ");
		result.append(rNeut);
		result.append(", xNeut: ");
		result.append(xNeut);
		result.append(", status: ");
		result.append(status);
		result.append(", class: ");
		result.append(class_);
		result.append(", vMinPU: ");
		result.append(vMinPU);
		result.append(", vMaxPU: ");
		result.append(vMaxPU);
		result.append(", vMinNorm: ");
		result.append(vMinNorm);
		result.append(", vMinEmerg: ");
		result.append(vMinEmerg);
		result.append(", xfKVA: ");
		result.append(xfKVA);
		result.append(", allocationFactor: ");
		result.append(allocationFactor);
		result.append(", kVA: ");
		result.append(kVA);
		result.append(", pctMean: ");
		result.append(pctMean);
		result.append(", pctStdDev: ");
		result.append(pctStdDev);
		result.append(", cvrWatts: ");
		result.append(cvrWatts);
		result.append(", cvrVars: ");
		result.append(cvrVars);
		result.append(')');
		return result.toString();
	}

} //LoadImpl
