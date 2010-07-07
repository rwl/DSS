/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;


import electrickery.common.Circuit;
import electrickery.common.CommonPackage;
import electrickery.common.connectionType;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.Generator;
import electrickery.conversion.dispatchType;
import electrickery.conversion.generatorModel;
import electrickery.conversion.generatorStatus;
import electrickery.executive.loadModelType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cern.colt.matrix.tdcomplex.DComplexFactory2D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import cern.jet.math.tdcomplex.DComplexFunctions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getKW <em>KW</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getPf <em>Pf</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getModel <em>Model</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVMinPU <em>VMin PU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVMaxPU <em>VMax PU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDispMode <em>Disp Mode</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDispValue <em>Disp Value</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVPU <em>VPU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVTarget <em>VTarget</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getMaxKVAr <em>Max KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getMinKVAr <em>Min KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getPvFactor <em>Pv Factor</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#isForceOn <em>Force On</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getMVA <em>MVA</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getXD <em>XD</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getXDp <em>XDp</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getXDpp <em>XDpp</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getH <em>H</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getD <em>D</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getUserModel <em>User Model</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getUserData <em>User Data</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getShaftModel <em>Shaft Model</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getShaftData <em>Shaft Data</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#isDebugTrace <em>Debug Trace</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#isGenOn <em>Gen On</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getShapeFactor <em>Shape Factor</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#isForcedOn <em>Forced On</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#isFixed <em>Fixed</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getYEq <em>YEq</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getYEq95 <em>YEq95</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getYEq105 <em>YEq105</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVBase <em>VBase</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVBase95 <em>VBase95</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVBase105 <em>VBase105</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVarBase <em>Var Base</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVarMin <em>Var Min</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getVarMax <em>Var Max</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDeltaQMax <em>Delta QMax</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDQdV <em>DQd V</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getDQdVSaved <em>DQd VSaved</em>}</li>
 *   <li>{@link electrickery.conversion.impl.GeneratorImpl#getYQFixed <em>YQ Fixed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneratorImpl extends PowerConversionElementImpl implements Generator {
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
    protected static final double KW_EDEFAULT = 100.0;

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
	 * The default value of the '{@link #getPf() <em>Pf</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPf()
	 * @generated
	 * @ordered
	 */
    protected static final double PF_EDEFAULT = 0.8;

    /**
	 * The cached value of the '{@link #getPf() <em>Pf</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPf()
	 * @generated
	 * @ordered
	 */
    protected double pf = PF_EDEFAULT;

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
	 * The default value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
    protected static final generatorModel MODEL_EDEFAULT = generatorModel.CONSTANT_KW;

    /**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
    protected generatorModel model = MODEL_EDEFAULT;

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
	 * The default value of the '{@link #getDispMode() <em>Disp Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDispMode()
	 * @generated
	 * @ordered
	 */
    protected static final dispatchType DISP_MODE_EDEFAULT = dispatchType.LOAD_MODE;

    /**
	 * The cached value of the '{@link #getDispMode() <em>Disp Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDispMode()
	 * @generated
	 * @ordered
	 */
    protected dispatchType dispMode = DISP_MODE_EDEFAULT;

    /**
	 * The default value of the '{@link #getDispValue() <em>Disp Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDispValue()
	 * @generated
	 * @ordered
	 */
    protected static final double DISP_VALUE_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getDispValue() <em>Disp Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDispValue()
	 * @generated
	 * @ordered
	 */
    protected double dispValue = DISP_VALUE_EDEFAULT;

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
    protected static final generatorStatus STATUS_EDEFAULT = generatorStatus.VARIABLE;

    /**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
    protected generatorStatus status = STATUS_EDEFAULT;

    /**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
    protected static final int CLASS_EDEFAULT = 1;

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
	 * The default value of the '{@link #getVPU() <em>VPU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVPU()
	 * @generated
	 * @ordered
	 */
    protected static final double VPU_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getVPU() <em>VPU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVPU()
	 * @generated
	 * @ordered
	 */
    protected double vPU = VPU_EDEFAULT;

    /**
	 * The default value of the '{@link #getVTarget() <em>VTarget</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVTarget()
	 * @generated
	 * @ordered
	 */
    protected static final double VTARGET_EDEFAULT = 0.0;

                /**
	 * The cached value of the '{@link #getVTarget() <em>VTarget</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVTarget()
	 * @generated
	 * @ordered
	 */
    protected double vTarget = VTARGET_EDEFAULT;

                /**
	 * The default value of the '{@link #getMaxKVAr() <em>Max KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxKVAr()
	 * @generated
	 * @ordered
	 */
    protected static final double MAX_KV_AR_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getMaxKVAr() <em>Max KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxKVAr()
	 * @generated
	 * @ordered
	 */
    protected double maxKVAr = MAX_KV_AR_EDEFAULT;

    /**
	 * The default value of the '{@link #getMinKVAr() <em>Min KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMinKVAr()
	 * @generated
	 * @ordered
	 */
    protected static final double MIN_KV_AR_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getMinKVAr() <em>Min KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMinKVAr()
	 * @generated
	 * @ordered
	 */
    protected double minKVAr = MIN_KV_AR_EDEFAULT;

    /**
	 * The default value of the '{@link #getPvFactor() <em>Pv Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPvFactor()
	 * @generated
	 * @ordered
	 */
    protected static final double PV_FACTOR_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getPvFactor() <em>Pv Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPvFactor()
	 * @generated
	 * @ordered
	 */
    protected double pvFactor = PV_FACTOR_EDEFAULT;

    /**
	 * The default value of the '{@link #isForceOn() <em>Force On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isForceOn()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FORCE_ON_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isForceOn() <em>Force On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isForceOn()
	 * @generated
	 * @ordered
	 */
    protected boolean forceOn = FORCE_ON_EDEFAULT;

    /**
	 * The default value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
    protected static final double KVA_EDEFAULT = 1.2;

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
	 * The default value of the '{@link #getMVA() <em>MVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMVA()
	 * @generated
	 * @ordered
	 */
    protected static final double MVA_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getMVA() <em>MVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMVA()
	 * @generated
	 * @ordered
	 */
    protected double mva = MVA_EDEFAULT;

    /**
	 * The default value of the '{@link #getXD() <em>XD</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXD()
	 * @generated
	 * @ordered
	 */
    protected static final double XD_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getXD() <em>XD</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXD()
	 * @generated
	 * @ordered
	 */
    protected double xD = XD_EDEFAULT;

    /**
	 * The default value of the '{@link #getXDp() <em>XDp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXDp()
	 * @generated
	 * @ordered
	 */
    protected static final double XDP_EDEFAULT = 0.27;

    /**
	 * The cached value of the '{@link #getXDp() <em>XDp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXDp()
	 * @generated
	 * @ordered
	 */
    protected double xDp = XDP_EDEFAULT;

    /**
	 * The default value of the '{@link #getXDpp() <em>XDpp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXDpp()
	 * @generated
	 * @ordered
	 */
    protected static final double XDPP_EDEFAULT = 0.2;

    /**
	 * The cached value of the '{@link #getXDpp() <em>XDpp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXDpp()
	 * @generated
	 * @ordered
	 */
    protected double xDpp = XDPP_EDEFAULT;

    /**
	 * The default value of the '{@link #getH() <em>H</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getH()
	 * @generated
	 * @ordered
	 */
    protected static final double H_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getH() <em>H</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getH()
	 * @generated
	 * @ordered
	 */
    protected double h = H_EDEFAULT;

    /**
	 * The default value of the '{@link #getD() <em>D</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getD()
	 * @generated
	 * @ordered
	 */
    protected static final double D_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getD() <em>D</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getD()
	 * @generated
	 * @ordered
	 */
    protected double d = D_EDEFAULT;

    /**
	 * The default value of the '{@link #getUserModel() <em>User Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUserModel()
	 * @generated
	 * @ordered
	 */
    protected static final String USER_MODEL_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getUserModel() <em>User Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUserModel()
	 * @generated
	 * @ordered
	 */
    protected String userModel = USER_MODEL_EDEFAULT;

    /**
	 * The default value of the '{@link #getUserData() <em>User Data</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUserData()
	 * @generated
	 * @ordered
	 */
    protected static final String USER_DATA_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getUserData() <em>User Data</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUserData()
	 * @generated
	 * @ordered
	 */
    protected String userData = USER_DATA_EDEFAULT;

    /**
	 * The default value of the '{@link #getShaftModel() <em>Shaft Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShaftModel()
	 * @generated
	 * @ordered
	 */
    protected static final String SHAFT_MODEL_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getShaftModel() <em>Shaft Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShaftModel()
	 * @generated
	 * @ordered
	 */
    protected String shaftModel = SHAFT_MODEL_EDEFAULT;

    /**
	 * The default value of the '{@link #getShaftData() <em>Shaft Data</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShaftData()
	 * @generated
	 * @ordered
	 */
    protected static final String SHAFT_DATA_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getShaftData() <em>Shaft Data</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShaftData()
	 * @generated
	 * @ordered
	 */
    protected String shaftData = SHAFT_DATA_EDEFAULT;

    /**
	 * The default value of the '{@link #isDebugTrace() <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDebugTrace()
	 * @generated
	 * @ordered
	 */
    protected static final boolean DEBUG_TRACE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isDebugTrace() <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDebugTrace()
	 * @generated
	 * @ordered
	 */
    protected boolean debugTrace = DEBUG_TRACE_EDEFAULT;

    /**
	 * The default value of the '{@link #isGenOn() <em>Gen On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isGenOn()
	 * @generated
	 * @ordered
	 */
    protected static final boolean GEN_ON_EDEFAULT = false;

                /**
	 * The cached value of the '{@link #isGenOn() <em>Gen On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isGenOn()
	 * @generated
	 * @ordered
	 */
    protected boolean genOn = GEN_ON_EDEFAULT;

                /**
	 * The default value of the '{@link #getShapeFactor() <em>Shape Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShapeFactor()
	 * @generated
	 * @ordered
	 */
    protected static final double[] SHAPE_FACTOR_EDEFAULT = null;

                                                                /**
	 * The cached value of the '{@link #getShapeFactor() <em>Shape Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShapeFactor()
	 * @generated
	 * @ordered
	 */
    protected double[] shapeFactor = SHAPE_FACTOR_EDEFAULT;

                /**
	 * The default value of the '{@link #isForcedOn() <em>Forced On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isForcedOn()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FORCED_ON_EDEFAULT = false;

                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #isForcedOn() <em>Forced On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isForcedOn()
	 * @generated
	 * @ordered
	 */
    protected boolean forcedOn = FORCED_ON_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFixed()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FIXED_EDEFAULT = false;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFixed()
	 * @generated
	 * @ordered
	 */
    protected boolean fixed = FIXED_EDEFAULT;

                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getYEq() <em>YEq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYEq()
	 * @generated
	 * @ordered
	 */
    protected static final double[] YEQ_EDEFAULT = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getYEq() <em>YEq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYEq()
	 * @generated
	 * @ordered
	 */
    protected double[] yEq = YEQ_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getYEq95() <em>YEq95</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYEq95()
	 * @generated
	 * @ordered
	 */
    protected static final double[] YEQ95_EDEFAULT = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getYEq95() <em>YEq95</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYEq95()
	 * @generated
	 * @ordered
	 */
    protected double[] yEq95 = YEQ95_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getYEq105() <em>YEq105</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYEq105()
	 * @generated
	 * @ordered
	 */
    protected static final double[] YEQ105_EDEFAULT = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getYEq105() <em>YEq105</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYEq105()
	 * @generated
	 * @ordered
	 */
    protected double[] yEq105 = YEQ105_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getVBase() <em>VBase</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBase()
	 * @generated
	 * @ordered
	 */
    protected static final double VBASE_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getVBase() <em>VBase</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBase()
	 * @generated
	 * @ordered
	 */
    protected double vBase = VBASE_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getVBase95() <em>VBase95</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBase95()
	 * @generated
	 * @ordered
	 */
    protected static final double VBASE95_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getVBase95() <em>VBase95</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBase95()
	 * @generated
	 * @ordered
	 */
    protected double vBase95 = VBASE95_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getVBase105() <em>VBase105</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBase105()
	 * @generated
	 * @ordered
	 */
    protected static final double VBASE105_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getVBase105() <em>VBase105</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBase105()
	 * @generated
	 * @ordered
	 */
    protected double vBase105 = VBASE105_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getVarBase() <em>Var Base</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVarBase()
	 * @generated
	 * @ordered
	 */
    protected static final double VAR_BASE_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getVarBase() <em>Var Base</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVarBase()
	 * @generated
	 * @ordered
	 */
    protected double varBase = VAR_BASE_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getVarMin() <em>Var Min</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVarMin()
	 * @generated
	 * @ordered
	 */
    protected static final double VAR_MIN_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getVarMin() <em>Var Min</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVarMin()
	 * @generated
	 * @ordered
	 */
    protected double varMin = VAR_MIN_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getVarMax() <em>Var Max</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVarMax()
	 * @generated
	 * @ordered
	 */
    protected static final double VAR_MAX_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getVarMax() <em>Var Max</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVarMax()
	 * @generated
	 * @ordered
	 */
    protected double varMax = VAR_MAX_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getDeltaQMax() <em>Delta QMax</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDeltaQMax()
	 * @generated
	 * @ordered
	 */
    protected static final double DELTA_QMAX_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getDeltaQMax() <em>Delta QMax</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDeltaQMax()
	 * @generated
	 * @ordered
	 */
    protected double deltaQMax = DELTA_QMAX_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getDQdV() <em>DQd V</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDQdV()
	 * @generated
	 * @ordered
	 */
    protected static final double DQD_V_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getDQdV() <em>DQd V</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDQdV()
	 * @generated
	 * @ordered
	 */
    protected double dQdV = DQD_V_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getDQdVSaved() <em>DQd VSaved</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDQdVSaved()
	 * @generated
	 * @ordered
	 */
    protected static final double DQD_VSAVED_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getDQdVSaved() <em>DQd VSaved</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDQdVSaved()
	 * @generated
	 * @ordered
	 */
    protected double dQdVSaved = DQD_VSAVED_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getYQFixed() <em>YQ Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYQFixed()
	 * @generated
	 * @ordered
	 */
    protected static final double YQ_FIXED_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getYQFixed() <em>YQ Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYQFixed()
	 * @generated
	 * @ordered
	 */
    protected double yQFixed = YQ_FIXED_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GeneratorImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConversionPackage.Literals.GENERATOR;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit getCircuit() {
		if (eContainerFeatureID() != ConversionPackage.GENERATOR__CIRCUIT) return null;
		return (Circuit)eContainer();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetCircuit(Circuit newCircuit, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCircuit, ConversionPackage.GENERATOR__CIRCUIT, msgs);
		return msgs;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCircuit(Circuit newCircuit) {
		if (newCircuit != eInternalContainer() || (eContainerFeatureID() != ConversionPackage.GENERATOR__CIRCUIT && newCircuit != null)) {
			if (EcoreUtil.isAncestor(this, newCircuit))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCircuit != null)
				msgs = ((InternalEObject)newCircuit).eInverseAdd(this, CommonPackage.CIRCUIT__GENERATORS, Circuit.class, msgs);
			msgs = basicSetCircuit(newCircuit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__CIRCUIT, newCircuit, newCircuit));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__BUS1, oldBus1, bus1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__KV, oldKV, kV));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__KW, oldKW, kW));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPf() {
		return pf;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPf(double newPf) {
		double oldPf = pf;
		pf = newPf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__PF, oldPf, pf));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__KV_AR, oldKVAr, kVAr));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public generatorModel getModel() {
		return model;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setModel(generatorModel newModel) {
		generatorModel oldModel = model;
		model = newModel == null ? MODEL_EDEFAULT : newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__MODEL, oldModel, model));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VMIN_PU, oldVMinPU, vMinPU));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VMAX_PU, oldVMaxPU, vMaxPU));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__YEARLY, oldYearly, yearly));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DAILY, oldDaily, daily));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DUTY, oldDuty, duty));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public dispatchType getDispMode() {
		return dispMode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDispMode(dispatchType newDispMode) {
		dispatchType oldDispMode = dispMode;
		dispMode = newDispMode == null ? DISP_MODE_EDEFAULT : newDispMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DISP_MODE, oldDispMode, dispMode));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getDispValue() {
		return dispValue;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDispValue(double newDispValue) {
		double oldDispValue = dispValue;
		dispValue = newDispValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DISP_VALUE, oldDispValue, dispValue));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__CONN, oldConn, conn));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__RNEUT, oldRNeut, rNeut));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__XNEUT, oldXNeut, xNeut));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public generatorStatus getStatus() {
		return status;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStatus(generatorStatus newStatus) {
		generatorStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__STATUS, oldStatus, status));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__CLASS, oldClass, class_));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVPU() {
		return vPU;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVPU(double newVPU) {
		double oldVPU = vPU;
		vPU = newVPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VPU, oldVPU, vPU));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVTarget() {
		return vTarget;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVTarget(double newVTarget) {
		double oldVTarget = vTarget;
		vTarget = newVTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VTARGET, oldVTarget, vTarget));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getMaxKVAr() {
		return maxKVAr;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMaxKVAr(double newMaxKVAr) {
		double oldMaxKVAr = maxKVAr;
		maxKVAr = newMaxKVAr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__MAX_KV_AR, oldMaxKVAr, maxKVAr));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getMinKVAr() {
		return minKVAr;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMinKVAr(double newMinKVAr) {
		double oldMinKVAr = minKVAr;
		minKVAr = newMinKVAr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__MIN_KV_AR, oldMinKVAr, minKVAr));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPvFactor() {
		return pvFactor;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPvFactor(double newPvFactor) {
		double oldPvFactor = pvFactor;
		pvFactor = newPvFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__PV_FACTOR, oldPvFactor, pvFactor));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isForceOn() {
		return forceOn;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setForceOn(boolean newForceOn) {
		boolean oldForceOn = forceOn;
		forceOn = newForceOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__FORCE_ON, oldForceOn, forceOn));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__KVA, oldKVA, kVA));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getMVA() {
		return mva;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMVA(double newMVA) {
		double oldMVA = mva;
		mva = newMVA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__MVA, oldMVA, mva));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getXD() {
		return xD;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXD(double newXD) {
		double oldXD = xD;
		xD = newXD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__XD, oldXD, xD));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getXDp() {
		return xDp;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXDp(double newXDp) {
		double oldXDp = xDp;
		xDp = newXDp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__XDP, oldXDp, xDp));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getXDpp() {
		return xDpp;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXDpp(double newXDpp) {
		double oldXDpp = xDpp;
		xDpp = newXDpp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__XDPP, oldXDpp, xDpp));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getH() {
		return h;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setH(double newH) {
		double oldH = h;
		h = newH;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__H, oldH, h));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getD() {
		return d;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setD(double newD) {
		double oldD = d;
		d = newD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__D, oldD, d));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getUserModel() {
		return userModel;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUserModel(String newUserModel) {
		String oldUserModel = userModel;
		userModel = newUserModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__USER_MODEL, oldUserModel, userModel));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getUserData() {
		return userData;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUserData(String newUserData) {
		String oldUserData = userData;
		userData = newUserData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__USER_DATA, oldUserData, userData));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getShaftModel() {
		return shaftModel;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setShaftModel(String newShaftModel) {
		String oldShaftModel = shaftModel;
		shaftModel = newShaftModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__SHAFT_MODEL, oldShaftModel, shaftModel));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getShaftData() {
		return shaftData;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setShaftData(String newShaftData) {
		String oldShaftData = shaftData;
		shaftData = newShaftData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__SHAFT_DATA, oldShaftData, shaftData));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isDebugTrace() {
		return debugTrace;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDebugTrace(boolean newDebugTrace) {
		boolean oldDebugTrace = debugTrace;
		debugTrace = newDebugTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DEBUG_TRACE, oldDebugTrace, debugTrace));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isGenOn() {
		return genOn;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGenOn(boolean newGenOn) {
		boolean oldGenOn = genOn;
		genOn = newGenOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__GEN_ON, oldGenOn, genOn));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double[] getShapeFactor() {
		return shapeFactor;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setShapeFactor(double[] newShapeFactor) {
		double[] oldShapeFactor = shapeFactor;
		shapeFactor = newShapeFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__SHAPE_FACTOR, oldShapeFactor, shapeFactor));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isForcedOn() {
		return forcedOn;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setForcedOn(boolean newForcedOn) {
		boolean oldForcedOn = forcedOn;
		forcedOn = newForcedOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__FORCED_ON, oldForcedOn, forcedOn));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isFixed() {
		return fixed;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFixed(boolean newFixed) {
		boolean oldFixed = fixed;
		fixed = newFixed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__FIXED, oldFixed, fixed));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double[] getYEq() {
		return yEq;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYEq(double[] newYEq) {
		double[] oldYEq = yEq;
		yEq = newYEq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__YEQ, oldYEq, yEq));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double[] getYEq95() {
		return yEq95;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYEq95(double[] newYEq95) {
		double[] oldYEq95 = yEq95;
		yEq95 = newYEq95;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__YEQ95, oldYEq95, yEq95));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double[] getYEq105() {
		return yEq105;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYEq105(double[] newYEq105) {
		double[] oldYEq105 = yEq105;
		yEq105 = newYEq105;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__YEQ105, oldYEq105, yEq105));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVBase() {
		return vBase;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVBase(double newVBase) {
		double oldVBase = vBase;
		vBase = newVBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VBASE, oldVBase, vBase));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVBase95() {
		return vBase95;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVBase95(double newVBase95) {
		double oldVBase95 = vBase95;
		vBase95 = newVBase95;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VBASE95, oldVBase95, vBase95));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVBase105() {
		return vBase105;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVBase105(double newVBase105) {
		double oldVBase105 = vBase105;
		vBase105 = newVBase105;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VBASE105, oldVBase105, vBase105));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVarBase() {
		return varBase;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVarBase(double newVarBase) {
		double oldVarBase = varBase;
		varBase = newVarBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VAR_BASE, oldVarBase, varBase));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVarMin() {
		return varMin;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVarMin(double newVarMin) {
		double oldVarMin = varMin;
		varMin = newVarMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VAR_MIN, oldVarMin, varMin));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVarMax() {
		return varMax;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVarMax(double newVarMax) {
		double oldVarMax = varMax;
		varMax = newVarMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__VAR_MAX, oldVarMax, varMax));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getDeltaQMax() {
		return deltaQMax;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDeltaQMax(double newDeltaQMax) {
		double oldDeltaQMax = deltaQMax;
		deltaQMax = newDeltaQMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DELTA_QMAX, oldDeltaQMax, deltaQMax));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getDQdV() {
		return dQdV;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDQdV(double newDQdV) {
		double oldDQdV = dQdV;
		dQdV = newDQdV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DQD_V, oldDQdV, dQdV));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getDQdVSaved() {
		return dQdVSaved;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDQdVSaved(double newDQdVSaved) {
		double oldDQdVSaved = dQdVSaved;
		dQdVSaved = newDQdVSaved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__DQD_VSAVED, oldDQdVSaved, dQdVSaved));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getYQFixed() {
		return yQFixed;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYQFixed(double newYQFixed) {
		double oldYQFixed = yQFixed;
		yQFixed = newYQFixed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.GENERATOR__YQ_FIXED, oldYQFixed, yQFixed));
	}

                                                                                                                                                                                                                                                                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setNominalGeneration() {
        double factor;
        boolean genOn = isGenOn();
        double[] doubleOne = {1.0, 1.0};
        setShapeFactor(doubleOne);

        if (!getCircuit().getSolution().isDynamicModel() || !getCircuit().getSolution().isHarmonicModel()) {
            // Leave generator in whatever state it was prior to entering dynamic mode.
            setGenOn(true);
            // Check if it should be off.
            if (!isForceOn()) {
                switch (getDispMode()) {
                    case LOAD_MODE:
                        if (getDispValue() > 0.0 && (getCircuit().getGeneratorDispatchReference() < getDispValue()))
                            setGenOn(false);
                    case PRICE_MODE:
                        if (getDispValue() > 0.0 && (getCircuit().getPriceSignal() < getDispValue()))
                            setGenOn(false);
                }
            }
        }

        if (!isGenOn()) {
            // TODO: If generator is OFF, enter as tiny resistive load (0.0001 p.u.) to avoid divide by zero error.
        } else {
            // Generator is on, compute it's nominal watts and vars.
            if (isFixed()) {
                factor = 1.0;
            } else {
                switch (getCircuit().getSolution().getMode()) {
                    case SNAPSHOT:
                        factor = getCircuit().getGenMultiplier() * 1.0;
                    case DAILY:
                    case YEARLY:
                    case MONTE_CARLO1:
                    case MONTE_FAULT:
                    case FAULT_STUDY:
                    case DYNAMIC:
                        factor = getCircuit().getGenMultiplier() * 1.0;
                    case MONTE_CARLO2:
                    case MONTE_CARLO3:
                    case LOAD_DURATION1:
                    case LOAD_DURATION2:
                    case PEAKDAY:
                    case DUTYCYCLE:
                    case AUTO_ADD:
                        factor = 1.0;
                }
            }
        }

        if (!getCircuit().getSolution().isDynamicModel() || !getCircuit().getSolution().isHarmonicModel()) {
            // TODO: Genvars
        }

        // If generator state changes, force re-calc of Y matrix.
        if (isGenOn() != genOn)
            setYPrimInvalid(true);
    }

    /**
     *
     */
    @Override
    public void recalcElementData() {
        setVBase95(getVMinPU() * getVBase());
        setVBase105(getVMaxPU() * getVBase());

        setVarBase(1000.0 * getKVAr() / getNPhases());
        setVarMin(1000.0 * getMinKVAr() / getNPhases());
        setVarMax(1000.0 * getMaxKVAr() / getNPhases());

        // TODO: Populate data structures used for interchange with user-written models.

        setNominalGeneration();

        setYQFixed(-1.0 * getVarBase() / Math.pow(getVBase(), 2.0));
        //setVTarget(getVPU() * 1000.0 * getKV() / Math.sqrt(3.0));

        // Initialise to Zero - defaults to PQ generator.
        // Solution object will reset after circuit modifications.
        setDQdV(getDQdVSaved()); // for model=3
        setDeltaQMax((getVarMax() - getVarMin()) * 0.10); // Limit to 10% of range.

        // TODO: Update any user-written models.
    }

    /**
     *
     * @param dComplexMatrix2D
     */
    public void calcYPrimMatrix(DComplexMatrix2D dComplexMatrix2D) {
        setYPrimFreq(getCircuit().getSolution().getFrequency());
        double freqMultiplier = getYPrimFreq() / getBaseFreq();
        double[] Y = {0.0, 0.0}, Yij = {0.0, 0.0};
        double EPSILON = 1e-12;

        if (getCircuit().getSolution().isDynamicModel() || getCircuit().getSolution().isHarmonicModel()) {
            if (isGenOn()) {
                Y = getYEq(); // L-N value computed in initialisation routines.
            } else {
                Y[0] = EPSILON;
            }

            if (getConn() == connectionType.DELTA) {
                // Convert to delta impedance.
                Y[0] = Y[0] / 3.0;
            }

            Y[1] = Y[1] / freqMultiplier;
            Yij[0] = -Y[0];
            Yij[1] = -Y[1];

            for (int i = 0; i < getNPhases(); i++) {
                switch (getConn()) {
                    case LN:
                        dComplexMatrix2D.setQuick(i, i, Y);
                        dComplexMatrix2D.setQuick(getNConds(), getNConds(),
                                dComplexMatrix2D.getRealPart().getQuick(getNConds(), getNConds()) + Y[0],
                                dComplexMatrix2D.getImaginaryPart().getQuick(getNConds(), getNConds()) + Y[1]);
                        dComplexMatrix2D.setQuick(i, getNConds(), Yij);
                        if (i != getNConds())
                            dComplexMatrix2D.setQuick(getNConds(), i, Yij);
                    case DELTA:
                        dComplexMatrix2D.setQuick(i, i, Y);
                        dComplexMatrix2D.setQuick(i, i,
                                dComplexMatrix2D.getRealPart().getQuick(i, i) + Y[0],
                                dComplexMatrix2D.getImaginaryPart().getQuick(i, i) + Y[1]);
                        for (int j = 0; j < i-1; j++) {
                            dComplexMatrix2D.setQuick(i, j, Yij);
                            if (i != j)
                                dComplexMatrix2D.setQuick(j, i, Yij);
                        }
                }
            }
        } else {
            // Regular power flow generator model.
            // Yeq is always expected as the equivalent line-neutral admittance.
            Y = DComplexFunctions.neg.apply(getYEq()); // Negate for generation.

            // Need to modify the base admittance for real harmonics calcs.
            Y[1] = Y[1] / freqMultiplier;

            switch (getConn()) {
                case WYE:
                    Yij = DComplexFunctions.neg.apply(Y);

                    for (int i = 0; i < getNPhases(); i++) {
                        dComplexMatrix2D.setQuick(i, i, Y);
                        dComplexMatrix2D.setQuick(getNConds(), getNConds(),
                                DComplexFunctions.plus.apply(dComplexMatrix2D.getQuick(getNConds(), getNConds()), Y));
                        dComplexMatrix2D.setQuick(i, getNConds(), Yij);
                        if (i != getNConds())
                            dComplexMatrix2D.setQuick(getNConds(), i, Yij);
                    }
                case DELTA:
                    // FIXME: or L-L
                    Y[0] = Y[0] / 3.0; // Convert to delta impedance.
                    Yij = DComplexFunctions.neg.apply(Y);
                    for (int i = 0; i < getNPhases(); i++) {
                        int j = i + 1;
                        if (j > getNConds())
                            // Wrap around for closed connections.
                            j = 1;
                        dComplexMatrix2D.setQuick(i, i, DComplexFunctions.plus.apply(dComplexMatrix2D.getQuick(i, i), Y));
                        dComplexMatrix2D.setQuick(j, j, DComplexFunctions.plus.apply(dComplexMatrix2D.getQuick(j, j), Y));

                        dComplexMatrix2D.setQuick(i, j, DComplexFunctions.plus.apply(dComplexMatrix2D.getQuick(i, j), Yij));
                        if (i != j)
                            dComplexMatrix2D.setQuick(j, i, DComplexFunctions.plus.apply(dComplexMatrix2D.getQuick(j, i), Yij));
                    }
            }
        }
    }

    /**
     *
     */
    public void calcYPrim() {
        // Build only shunt YPrim.
        // Build a dummy YPrimSeries so that calcV does not fail.
        setYPrimShunt(DComplexFactory2D.sparse.make(yOrder, yOrder));
        setYPrimSeries(DComplexFactory2D.sparse.make(yOrder, yOrder));
        setYPrim(DComplexFactory2D.sparse.make(yOrder, yOrder));

        if (getCircuit().getSolution().getLoadModel() == loadModelType.POWERFLOW) {
            setNominalGeneration();
            calcYPrimMatrix(getYPrimShunt());
        } else {
            // Admittance model wanted.
            setNominalGeneration();
            calcYPrimMatrix(getYPrimShunt());
        }

        // Set YPrimSeries based on diagonals of YPrimShunt so that calcV doesn't fail.
        for (int i = 0; i < yOrder; i++) {
            getYPrimSeries().setQuick(i, i,
                    getYPrimShunt().getRealPart().getQuick(i, i) * 1e-10,
                    getYPrimShunt().getImaginaryPart().getQuick(i, i));
        }

        setYPrim(getYPrimShunt().copy());

        // FIXME: Call method from super class.
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConversionPackage.GENERATOR__CIRCUIT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCircuit((Circuit)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConversionPackage.GENERATOR__CIRCUIT:
				return basicSetCircuit(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ConversionPackage.GENERATOR__CIRCUIT:
				return eInternalContainer().eInverseRemove(this, CommonPackage.CIRCUIT__GENERATORS, Circuit.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.GENERATOR__CIRCUIT:
				return getCircuit();
			case ConversionPackage.GENERATOR__BUS1:
				return getBus1();
			case ConversionPackage.GENERATOR__KV:
				return getKV();
			case ConversionPackage.GENERATOR__KW:
				return getKW();
			case ConversionPackage.GENERATOR__PF:
				return getPf();
			case ConversionPackage.GENERATOR__KV_AR:
				return getKVAr();
			case ConversionPackage.GENERATOR__MODEL:
				return getModel();
			case ConversionPackage.GENERATOR__VMIN_PU:
				return getVMinPU();
			case ConversionPackage.GENERATOR__VMAX_PU:
				return getVMaxPU();
			case ConversionPackage.GENERATOR__YEARLY:
				return getYearly();
			case ConversionPackage.GENERATOR__DAILY:
				return getDaily();
			case ConversionPackage.GENERATOR__DUTY:
				return getDuty();
			case ConversionPackage.GENERATOR__DISP_MODE:
				return getDispMode();
			case ConversionPackage.GENERATOR__DISP_VALUE:
				return getDispValue();
			case ConversionPackage.GENERATOR__CONN:
				return getConn();
			case ConversionPackage.GENERATOR__RNEUT:
				return getRNeut();
			case ConversionPackage.GENERATOR__XNEUT:
				return getXNeut();
			case ConversionPackage.GENERATOR__STATUS:
				return getStatus();
			case ConversionPackage.GENERATOR__CLASS:
				return getClass_();
			case ConversionPackage.GENERATOR__VPU:
				return getVPU();
			case ConversionPackage.GENERATOR__VTARGET:
				return getVTarget();
			case ConversionPackage.GENERATOR__MAX_KV_AR:
				return getMaxKVAr();
			case ConversionPackage.GENERATOR__MIN_KV_AR:
				return getMinKVAr();
			case ConversionPackage.GENERATOR__PV_FACTOR:
				return getPvFactor();
			case ConversionPackage.GENERATOR__FORCE_ON:
				return isForceOn();
			case ConversionPackage.GENERATOR__KVA:
				return getKVA();
			case ConversionPackage.GENERATOR__MVA:
				return getMVA();
			case ConversionPackage.GENERATOR__XD:
				return getXD();
			case ConversionPackage.GENERATOR__XDP:
				return getXDp();
			case ConversionPackage.GENERATOR__XDPP:
				return getXDpp();
			case ConversionPackage.GENERATOR__H:
				return getH();
			case ConversionPackage.GENERATOR__D:
				return getD();
			case ConversionPackage.GENERATOR__USER_MODEL:
				return getUserModel();
			case ConversionPackage.GENERATOR__USER_DATA:
				return getUserData();
			case ConversionPackage.GENERATOR__SHAFT_MODEL:
				return getShaftModel();
			case ConversionPackage.GENERATOR__SHAFT_DATA:
				return getShaftData();
			case ConversionPackage.GENERATOR__DEBUG_TRACE:
				return isDebugTrace();
			case ConversionPackage.GENERATOR__GEN_ON:
				return isGenOn();
			case ConversionPackage.GENERATOR__SHAPE_FACTOR:
				return getShapeFactor();
			case ConversionPackage.GENERATOR__FORCED_ON:
				return isForcedOn();
			case ConversionPackage.GENERATOR__FIXED:
				return isFixed();
			case ConversionPackage.GENERATOR__YEQ:
				return getYEq();
			case ConversionPackage.GENERATOR__YEQ95:
				return getYEq95();
			case ConversionPackage.GENERATOR__YEQ105:
				return getYEq105();
			case ConversionPackage.GENERATOR__VBASE:
				return getVBase();
			case ConversionPackage.GENERATOR__VBASE95:
				return getVBase95();
			case ConversionPackage.GENERATOR__VBASE105:
				return getVBase105();
			case ConversionPackage.GENERATOR__VAR_BASE:
				return getVarBase();
			case ConversionPackage.GENERATOR__VAR_MIN:
				return getVarMin();
			case ConversionPackage.GENERATOR__VAR_MAX:
				return getVarMax();
			case ConversionPackage.GENERATOR__DELTA_QMAX:
				return getDeltaQMax();
			case ConversionPackage.GENERATOR__DQD_V:
				return getDQdV();
			case ConversionPackage.GENERATOR__DQD_VSAVED:
				return getDQdVSaved();
			case ConversionPackage.GENERATOR__YQ_FIXED:
				return getYQFixed();
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
			case ConversionPackage.GENERATOR__CIRCUIT:
				setCircuit((Circuit)newValue);
				return;
			case ConversionPackage.GENERATOR__BUS1:
				setBus1((String)newValue);
				return;
			case ConversionPackage.GENERATOR__KV:
				setKV((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__KW:
				setKW((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__PF:
				setPf((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__KV_AR:
				setKVAr((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__MODEL:
				setModel((generatorModel)newValue);
				return;
			case ConversionPackage.GENERATOR__VMIN_PU:
				setVMinPU((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VMAX_PU:
				setVMaxPU((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__YEARLY:
				setYearly((String)newValue);
				return;
			case ConversionPackage.GENERATOR__DAILY:
				setDaily((String)newValue);
				return;
			case ConversionPackage.GENERATOR__DUTY:
				setDuty((String)newValue);
				return;
			case ConversionPackage.GENERATOR__DISP_MODE:
				setDispMode((dispatchType)newValue);
				return;
			case ConversionPackage.GENERATOR__DISP_VALUE:
				setDispValue((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__CONN:
				setConn((connectionType)newValue);
				return;
			case ConversionPackage.GENERATOR__RNEUT:
				setRNeut((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__XNEUT:
				setXNeut((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__STATUS:
				setStatus((generatorStatus)newValue);
				return;
			case ConversionPackage.GENERATOR__CLASS:
				setClass((Integer)newValue);
				return;
			case ConversionPackage.GENERATOR__VPU:
				setVPU((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VTARGET:
				setVTarget((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__MAX_KV_AR:
				setMaxKVAr((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__MIN_KV_AR:
				setMinKVAr((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__PV_FACTOR:
				setPvFactor((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__FORCE_ON:
				setForceOn((Boolean)newValue);
				return;
			case ConversionPackage.GENERATOR__KVA:
				setKVA((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__MVA:
				setMVA((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__XD:
				setXD((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__XDP:
				setXDp((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__XDPP:
				setXDpp((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__H:
				setH((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__D:
				setD((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__USER_MODEL:
				setUserModel((String)newValue);
				return;
			case ConversionPackage.GENERATOR__USER_DATA:
				setUserData((String)newValue);
				return;
			case ConversionPackage.GENERATOR__SHAFT_MODEL:
				setShaftModel((String)newValue);
				return;
			case ConversionPackage.GENERATOR__SHAFT_DATA:
				setShaftData((String)newValue);
				return;
			case ConversionPackage.GENERATOR__DEBUG_TRACE:
				setDebugTrace((Boolean)newValue);
				return;
			case ConversionPackage.GENERATOR__GEN_ON:
				setGenOn((Boolean)newValue);
				return;
			case ConversionPackage.GENERATOR__SHAPE_FACTOR:
				setShapeFactor((double[])newValue);
				return;
			case ConversionPackage.GENERATOR__FORCED_ON:
				setForcedOn((Boolean)newValue);
				return;
			case ConversionPackage.GENERATOR__FIXED:
				setFixed((Boolean)newValue);
				return;
			case ConversionPackage.GENERATOR__YEQ:
				setYEq((double[])newValue);
				return;
			case ConversionPackage.GENERATOR__YEQ95:
				setYEq95((double[])newValue);
				return;
			case ConversionPackage.GENERATOR__YEQ105:
				setYEq105((double[])newValue);
				return;
			case ConversionPackage.GENERATOR__VBASE:
				setVBase((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VBASE95:
				setVBase95((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VBASE105:
				setVBase105((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VAR_BASE:
				setVarBase((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VAR_MIN:
				setVarMin((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__VAR_MAX:
				setVarMax((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__DELTA_QMAX:
				setDeltaQMax((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__DQD_V:
				setDQdV((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__DQD_VSAVED:
				setDQdVSaved((Double)newValue);
				return;
			case ConversionPackage.GENERATOR__YQ_FIXED:
				setYQFixed((Double)newValue);
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
			case ConversionPackage.GENERATOR__CIRCUIT:
				setCircuit((Circuit)null);
				return;
			case ConversionPackage.GENERATOR__BUS1:
				setBus1(BUS1_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__KV:
				setKV(KV_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__KW:
				setKW(KW_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__PF:
				setPf(PF_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__KV_AR:
				setKVAr(KV_AR_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__MODEL:
				setModel(MODEL_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VMIN_PU:
				setVMinPU(VMIN_PU_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VMAX_PU:
				setVMaxPU(VMAX_PU_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__YEARLY:
				setYearly(YEARLY_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DAILY:
				setDaily(DAILY_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DUTY:
				setDuty(DUTY_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DISP_MODE:
				setDispMode(DISP_MODE_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DISP_VALUE:
				setDispValue(DISP_VALUE_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__RNEUT:
				setRNeut(RNEUT_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__XNEUT:
				setXNeut(XNEUT_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VPU:
				setVPU(VPU_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VTARGET:
				setVTarget(VTARGET_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__MAX_KV_AR:
				setMaxKVAr(MAX_KV_AR_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__MIN_KV_AR:
				setMinKVAr(MIN_KV_AR_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__PV_FACTOR:
				setPvFactor(PV_FACTOR_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__FORCE_ON:
				setForceOn(FORCE_ON_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__KVA:
				setKVA(KVA_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__MVA:
				setMVA(MVA_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__XD:
				setXD(XD_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__XDP:
				setXDp(XDP_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__XDPP:
				setXDpp(XDPP_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__H:
				setH(H_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__D:
				setD(D_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__USER_MODEL:
				setUserModel(USER_MODEL_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__USER_DATA:
				setUserData(USER_DATA_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__SHAFT_MODEL:
				setShaftModel(SHAFT_MODEL_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__SHAFT_DATA:
				setShaftData(SHAFT_DATA_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DEBUG_TRACE:
				setDebugTrace(DEBUG_TRACE_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__GEN_ON:
				setGenOn(GEN_ON_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__SHAPE_FACTOR:
				setShapeFactor(SHAPE_FACTOR_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__FORCED_ON:
				setForcedOn(FORCED_ON_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__FIXED:
				setFixed(FIXED_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__YEQ:
				setYEq(YEQ_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__YEQ95:
				setYEq95(YEQ95_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__YEQ105:
				setYEq105(YEQ105_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VBASE:
				setVBase(VBASE_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VBASE95:
				setVBase95(VBASE95_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VBASE105:
				setVBase105(VBASE105_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VAR_BASE:
				setVarBase(VAR_BASE_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VAR_MIN:
				setVarMin(VAR_MIN_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__VAR_MAX:
				setVarMax(VAR_MAX_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DELTA_QMAX:
				setDeltaQMax(DELTA_QMAX_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DQD_V:
				setDQdV(DQD_V_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__DQD_VSAVED:
				setDQdVSaved(DQD_VSAVED_EDEFAULT);
				return;
			case ConversionPackage.GENERATOR__YQ_FIXED:
				setYQFixed(YQ_FIXED_EDEFAULT);
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
			case ConversionPackage.GENERATOR__CIRCUIT:
				return getCircuit() != null;
			case ConversionPackage.GENERATOR__BUS1:
				return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
			case ConversionPackage.GENERATOR__KV:
				return kV != KV_EDEFAULT;
			case ConversionPackage.GENERATOR__KW:
				return kW != KW_EDEFAULT;
			case ConversionPackage.GENERATOR__PF:
				return pf != PF_EDEFAULT;
			case ConversionPackage.GENERATOR__KV_AR:
				return kVAr != KV_AR_EDEFAULT;
			case ConversionPackage.GENERATOR__MODEL:
				return model != MODEL_EDEFAULT;
			case ConversionPackage.GENERATOR__VMIN_PU:
				return vMinPU != VMIN_PU_EDEFAULT;
			case ConversionPackage.GENERATOR__VMAX_PU:
				return vMaxPU != VMAX_PU_EDEFAULT;
			case ConversionPackage.GENERATOR__YEARLY:
				return YEARLY_EDEFAULT == null ? yearly != null : !YEARLY_EDEFAULT.equals(yearly);
			case ConversionPackage.GENERATOR__DAILY:
				return DAILY_EDEFAULT == null ? daily != null : !DAILY_EDEFAULT.equals(daily);
			case ConversionPackage.GENERATOR__DUTY:
				return DUTY_EDEFAULT == null ? duty != null : !DUTY_EDEFAULT.equals(duty);
			case ConversionPackage.GENERATOR__DISP_MODE:
				return dispMode != DISP_MODE_EDEFAULT;
			case ConversionPackage.GENERATOR__DISP_VALUE:
				return dispValue != DISP_VALUE_EDEFAULT;
			case ConversionPackage.GENERATOR__CONN:
				return conn != CONN_EDEFAULT;
			case ConversionPackage.GENERATOR__RNEUT:
				return rNeut != RNEUT_EDEFAULT;
			case ConversionPackage.GENERATOR__XNEUT:
				return xNeut != XNEUT_EDEFAULT;
			case ConversionPackage.GENERATOR__STATUS:
				return status != STATUS_EDEFAULT;
			case ConversionPackage.GENERATOR__CLASS:
				return class_ != CLASS_EDEFAULT;
			case ConversionPackage.GENERATOR__VPU:
				return vPU != VPU_EDEFAULT;
			case ConversionPackage.GENERATOR__VTARGET:
				return vTarget != VTARGET_EDEFAULT;
			case ConversionPackage.GENERATOR__MAX_KV_AR:
				return maxKVAr != MAX_KV_AR_EDEFAULT;
			case ConversionPackage.GENERATOR__MIN_KV_AR:
				return minKVAr != MIN_KV_AR_EDEFAULT;
			case ConversionPackage.GENERATOR__PV_FACTOR:
				return pvFactor != PV_FACTOR_EDEFAULT;
			case ConversionPackage.GENERATOR__FORCE_ON:
				return forceOn != FORCE_ON_EDEFAULT;
			case ConversionPackage.GENERATOR__KVA:
				return kVA != KVA_EDEFAULT;
			case ConversionPackage.GENERATOR__MVA:
				return mva != MVA_EDEFAULT;
			case ConversionPackage.GENERATOR__XD:
				return xD != XD_EDEFAULT;
			case ConversionPackage.GENERATOR__XDP:
				return xDp != XDP_EDEFAULT;
			case ConversionPackage.GENERATOR__XDPP:
				return xDpp != XDPP_EDEFAULT;
			case ConversionPackage.GENERATOR__H:
				return h != H_EDEFAULT;
			case ConversionPackage.GENERATOR__D:
				return d != D_EDEFAULT;
			case ConversionPackage.GENERATOR__USER_MODEL:
				return USER_MODEL_EDEFAULT == null ? userModel != null : !USER_MODEL_EDEFAULT.equals(userModel);
			case ConversionPackage.GENERATOR__USER_DATA:
				return USER_DATA_EDEFAULT == null ? userData != null : !USER_DATA_EDEFAULT.equals(userData);
			case ConversionPackage.GENERATOR__SHAFT_MODEL:
				return SHAFT_MODEL_EDEFAULT == null ? shaftModel != null : !SHAFT_MODEL_EDEFAULT.equals(shaftModel);
			case ConversionPackage.GENERATOR__SHAFT_DATA:
				return SHAFT_DATA_EDEFAULT == null ? shaftData != null : !SHAFT_DATA_EDEFAULT.equals(shaftData);
			case ConversionPackage.GENERATOR__DEBUG_TRACE:
				return debugTrace != DEBUG_TRACE_EDEFAULT;
			case ConversionPackage.GENERATOR__GEN_ON:
				return genOn != GEN_ON_EDEFAULT;
			case ConversionPackage.GENERATOR__SHAPE_FACTOR:
				return SHAPE_FACTOR_EDEFAULT == null ? shapeFactor != null : !SHAPE_FACTOR_EDEFAULT.equals(shapeFactor);
			case ConversionPackage.GENERATOR__FORCED_ON:
				return forcedOn != FORCED_ON_EDEFAULT;
			case ConversionPackage.GENERATOR__FIXED:
				return fixed != FIXED_EDEFAULT;
			case ConversionPackage.GENERATOR__YEQ:
				return YEQ_EDEFAULT == null ? yEq != null : !YEQ_EDEFAULT.equals(yEq);
			case ConversionPackage.GENERATOR__YEQ95:
				return YEQ95_EDEFAULT == null ? yEq95 != null : !YEQ95_EDEFAULT.equals(yEq95);
			case ConversionPackage.GENERATOR__YEQ105:
				return YEQ105_EDEFAULT == null ? yEq105 != null : !YEQ105_EDEFAULT.equals(yEq105);
			case ConversionPackage.GENERATOR__VBASE:
				return vBase != VBASE_EDEFAULT;
			case ConversionPackage.GENERATOR__VBASE95:
				return vBase95 != VBASE95_EDEFAULT;
			case ConversionPackage.GENERATOR__VBASE105:
				return vBase105 != VBASE105_EDEFAULT;
			case ConversionPackage.GENERATOR__VAR_BASE:
				return varBase != VAR_BASE_EDEFAULT;
			case ConversionPackage.GENERATOR__VAR_MIN:
				return varMin != VAR_MIN_EDEFAULT;
			case ConversionPackage.GENERATOR__VAR_MAX:
				return varMax != VAR_MAX_EDEFAULT;
			case ConversionPackage.GENERATOR__DELTA_QMAX:
				return deltaQMax != DELTA_QMAX_EDEFAULT;
			case ConversionPackage.GENERATOR__DQD_V:
				return dQdV != DQD_V_EDEFAULT;
			case ConversionPackage.GENERATOR__DQD_VSAVED:
				return dQdVSaved != DQD_VSAVED_EDEFAULT;
			case ConversionPackage.GENERATOR__YQ_FIXED:
				return yQFixed != YQ_FIXED_EDEFAULT;
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
		result.append(", pf: ");
		result.append(pf);
		result.append(", kVAr: ");
		result.append(kVAr);
		result.append(", model: ");
		result.append(model);
		result.append(", vMinPU: ");
		result.append(vMinPU);
		result.append(", vMaxPU: ");
		result.append(vMaxPU);
		result.append(", yearly: ");
		result.append(yearly);
		result.append(", daily: ");
		result.append(daily);
		result.append(", duty: ");
		result.append(duty);
		result.append(", dispMode: ");
		result.append(dispMode);
		result.append(", dispValue: ");
		result.append(dispValue);
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
		result.append(", vPU: ");
		result.append(vPU);
		result.append(", vTarget: ");
		result.append(vTarget);
		result.append(", maxKVAr: ");
		result.append(maxKVAr);
		result.append(", minKVAr: ");
		result.append(minKVAr);
		result.append(", pvFactor: ");
		result.append(pvFactor);
		result.append(", forceOn: ");
		result.append(forceOn);
		result.append(", kVA: ");
		result.append(kVA);
		result.append(", MVA: ");
		result.append(mva);
		result.append(", xD: ");
		result.append(xD);
		result.append(", xDp: ");
		result.append(xDp);
		result.append(", xDpp: ");
		result.append(xDpp);
		result.append(", h: ");
		result.append(h);
		result.append(", d: ");
		result.append(d);
		result.append(", userModel: ");
		result.append(userModel);
		result.append(", userData: ");
		result.append(userData);
		result.append(", shaftModel: ");
		result.append(shaftModel);
		result.append(", shaftData: ");
		result.append(shaftData);
		result.append(", debugTrace: ");
		result.append(debugTrace);
		result.append(", genOn: ");
		result.append(genOn);
		result.append(", shapeFactor: ");
		result.append(shapeFactor);
		result.append(", forcedOn: ");
		result.append(forcedOn);
		result.append(", fixed: ");
		result.append(fixed);
		result.append(", yEq: ");
		result.append(yEq);
		result.append(", yEq95: ");
		result.append(yEq95);
		result.append(", yEq105: ");
		result.append(yEq105);
		result.append(", vBase: ");
		result.append(vBase);
		result.append(", vBase95: ");
		result.append(vBase95);
		result.append(", vBase105: ");
		result.append(vBase105);
		result.append(", varBase: ");
		result.append(varBase);
		result.append(", varMin: ");
		result.append(varMin);
		result.append(", varMax: ");
		result.append(varMax);
		result.append(", deltaQMax: ");
		result.append(deltaQMax);
		result.append(", dQdV: ");
		result.append(dQdV);
		result.append(", dQdVSaved: ");
		result.append(dQdVSaved);
		result.append(", yQFixed: ");
		result.append(yQFixed);
		result.append(')');
		return result.toString();
	}

} //GeneratorImpl
