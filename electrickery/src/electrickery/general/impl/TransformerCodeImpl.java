/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general.impl;

import electrickery.common.connectionType;

import electrickery.general.GeneralPackage;
import electrickery.general.TransformerCode;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformer Code</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPhases <em>Phases</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getWindings <em>Windings</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getWdg <em>Wdg</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getTap <em>Tap</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPctR <em>Pct R</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getConns <em>Conns</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getKVs <em>KVs</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getKVAs <em>KV As</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getTaps <em>Taps</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getXhl <em>Xhl</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getXht <em>Xht</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getXlt <em>Xlt</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getXscArray <em>Xsc Array</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getThermal <em>Thermal</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getN <em>N</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getM <em>M</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getFLRise <em>FL Rise</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getHSRise <em>HS Rise</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPctLoadLoss <em>Pct Load Loss</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPctNoLoadLoss <em>Pct No Load Loss</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getNormHKVA <em>Norm HKVA</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getEmergHKVA <em>Emerg HKVA</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getMaxTap <em>Max Tap</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getMinTap <em>Min Tap</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getNumTaps <em>Num Taps</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPctIMag <em>Pct IMag</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPpmAntiFloat <em>Ppm Anti Float</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getPctRS <em>Pct RS</em>}</li>
 *   <li>{@link electrickery.general.impl.TransformerCodeImpl#getLike <em>Like</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformerCodeImpl extends EObjectImpl implements TransformerCode {
	/**
	 * The default value of the '{@link #getPhases() <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhases()
	 * @generated
	 * @ordered
	 */
	protected static final int PHASES_EDEFAULT = 3;

	/**
	 * The cached value of the '{@link #getPhases() <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhases()
	 * @generated
	 * @ordered
	 */
	protected int phases = PHASES_EDEFAULT;

	/**
	 * The default value of the '{@link #getWindings() <em>Windings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindings()
	 * @generated
	 * @ordered
	 */
	protected static final int WINDINGS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWindings() <em>Windings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindings()
	 * @generated
	 * @ordered
	 */
	protected int windings = WINDINGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWdg() <em>Wdg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWdg()
	 * @generated
	 * @ordered
	 */
	protected static final int WDG_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWdg() <em>Wdg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWdg()
	 * @generated
	 * @ordered
	 */
	protected int wdg = WDG_EDEFAULT;

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
	 * The default value of the '{@link #getKV() <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKV()
	 * @generated
	 * @ordered
	 */
	protected static final double KV_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
	protected static final double KVA_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #getTap() <em>Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTap()
	 * @generated
	 * @ordered
	 */
	protected static final double TAP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTap() <em>Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTap()
	 * @generated
	 * @ordered
	 */
	protected double tap = TAP_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctR() <em>Pct R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctR()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_R_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctR() <em>Pct R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctR()
	 * @generated
	 * @ordered
	 */
	protected double pctR = PCT_R_EDEFAULT;

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
	 * The cached value of the '{@link #getConns() <em>Conns</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConns()
	 * @generated
	 * @ordered
	 */
	protected EList<connectionType> conns;

	/**
	 * The cached value of the '{@link #getKVs() <em>KVs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVs()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> kVs;

	/**
	 * The cached value of the '{@link #getKVAs() <em>KV As</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVAs()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> kVAs;

	/**
	 * The cached value of the '{@link #getTaps() <em>Taps</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaps()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> taps;

	/**
	 * The default value of the '{@link #getXhl() <em>Xhl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXhl()
	 * @generated
	 * @ordered
	 */
	protected static final double XHL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXhl() <em>Xhl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXhl()
	 * @generated
	 * @ordered
	 */
	protected double xhl = XHL_EDEFAULT;

	/**
	 * The default value of the '{@link #getXht() <em>Xht</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXht()
	 * @generated
	 * @ordered
	 */
	protected static final double XHT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXht() <em>Xht</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXht()
	 * @generated
	 * @ordered
	 */
	protected double xht = XHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getXlt() <em>Xlt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXlt()
	 * @generated
	 * @ordered
	 */
	protected static final double XLT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXlt() <em>Xlt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXlt()
	 * @generated
	 * @ordered
	 */
	protected double xlt = XLT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getXscArray() <em>Xsc Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXscArray()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> xscArray;

	/**
	 * The default value of the '{@link #getThermal() <em>Thermal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThermal()
	 * @generated
	 * @ordered
	 */
	protected static final double THERMAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getThermal() <em>Thermal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThermal()
	 * @generated
	 * @ordered
	 */
	protected double thermal = THERMAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getN() <em>N</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getN()
	 * @generated
	 * @ordered
	 */
	protected static final double N_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getN() <em>N</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getN()
	 * @generated
	 * @ordered
	 */
	protected double n = N_EDEFAULT;

	/**
	 * The default value of the '{@link #getM() <em>M</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getM()
	 * @generated
	 * @ordered
	 */
	protected static final double M_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getM() <em>M</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getM()
	 * @generated
	 * @ordered
	 */
	protected double m = M_EDEFAULT;

	/**
	 * The default value of the '{@link #getFLRise() <em>FL Rise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFLRise()
	 * @generated
	 * @ordered
	 */
	protected static final double FL_RISE_EDEFAULT = 65.0;

	/**
	 * The cached value of the '{@link #getFLRise() <em>FL Rise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFLRise()
	 * @generated
	 * @ordered
	 */
	protected double fLRise = FL_RISE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHSRise() <em>HS Rise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHSRise()
	 * @generated
	 * @ordered
	 */
	protected static final double HS_RISE_EDEFAULT = 15.0;

	/**
	 * The cached value of the '{@link #getHSRise() <em>HS Rise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHSRise()
	 * @generated
	 * @ordered
	 */
	protected double hSRise = HS_RISE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctLoadLoss() <em>Pct Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctLoadLoss()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_LOAD_LOSS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctLoadLoss() <em>Pct Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctLoadLoss()
	 * @generated
	 * @ordered
	 */
	protected double pctLoadLoss = PCT_LOAD_LOSS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctNoLoadLoss() <em>Pct No Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctNoLoadLoss()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_NO_LOAD_LOSS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctNoLoadLoss() <em>Pct No Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctNoLoadLoss()
	 * @generated
	 * @ordered
	 */
	protected double pctNoLoadLoss = PCT_NO_LOAD_LOSS_EDEFAULT;

	/**
	 * The default value of the '{@link #getNormHKVA() <em>Norm HKVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormHKVA()
	 * @generated
	 * @ordered
	 */
	protected static final double NORM_HKVA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getNormHKVA() <em>Norm HKVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormHKVA()
	 * @generated
	 * @ordered
	 */
	protected double normHKVA = NORM_HKVA_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmergHKVA() <em>Emerg HKVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergHKVA()
	 * @generated
	 * @ordered
	 */
	protected static final double EMERG_HKVA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEmergHKVA() <em>Emerg HKVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergHKVA()
	 * @generated
	 * @ordered
	 */
	protected double emergHKVA = EMERG_HKVA_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxTap() <em>Max Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTap()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_TAP_EDEFAULT = 1.1;

	/**
	 * The cached value of the '{@link #getMaxTap() <em>Max Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTap()
	 * @generated
	 * @ordered
	 */
	protected double maxTap = MAX_TAP_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinTap() <em>Min Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinTap()
	 * @generated
	 * @ordered
	 */
	protected static final double MIN_TAP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMinTap() <em>Min Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinTap()
	 * @generated
	 * @ordered
	 */
	protected double minTap = MIN_TAP_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumTaps() <em>Num Taps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumTaps()
	 * @generated
	 * @ordered
	 */
	protected static final int NUM_TAPS_EDEFAULT = 32;

	/**
	 * The cached value of the '{@link #getNumTaps() <em>Num Taps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumTaps()
	 * @generated
	 * @ordered
	 */
	protected int numTaps = NUM_TAPS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctIMag() <em>Pct IMag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctIMag()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_IMAG_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctIMag() <em>Pct IMag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctIMag()
	 * @generated
	 * @ordered
	 */
	protected double pctIMag = PCT_IMAG_EDEFAULT;

	/**
	 * The default value of the '{@link #getPpmAntiFloat() <em>Ppm Anti Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPpmAntiFloat()
	 * @generated
	 * @ordered
	 */
	protected static final double PPM_ANTI_FLOAT_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getPpmAntiFloat() <em>Ppm Anti Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPpmAntiFloat()
	 * @generated
	 * @ordered
	 */
	protected double ppmAntiFloat = PPM_ANTI_FLOAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctRS() <em>Pct RS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRS()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_RS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctRS() <em>Pct RS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRS()
	 * @generated
	 * @ordered
	 */
	protected double pctRS = PCT_RS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLike() <em>Like</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLike()
	 * @generated
	 * @ordered
	 */
	protected TransformerCode like;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformerCodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.TRANSFORMER_CODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPhases() {
		return phases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhases(int newPhases) {
		int oldPhases = phases;
		phases = newPhases;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PHASES, oldPhases, phases));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWindings() {
		return windings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWindings(int newWindings) {
		int oldWindings = windings;
		windings = newWindings;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__WINDINGS, oldWindings, windings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWdg() {
		return wdg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWdg(int newWdg) {
		int oldWdg = wdg;
		wdg = newWdg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__WDG, oldWdg, wdg));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__CONN, oldConn, conn));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__KV, oldKV, kV));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__KVA, oldKVA, kVA));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTap() {
		return tap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTap(double newTap) {
		double oldTap = tap;
		tap = newTap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__TAP, oldTap, tap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctR() {
		return pctR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctR(double newPctR) {
		double oldPctR = pctR;
		pctR = newPctR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PCT_R, oldPctR, pctR));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__RNEUT, oldRNeut, rNeut));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__XNEUT, oldXNeut, xNeut));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<connectionType> getConns() {
		if (conns == null) {
			conns = new EDataTypeUniqueEList<connectionType>(connectionType.class, this, GeneralPackage.TRANSFORMER_CODE__CONNS);
		}
		return conns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getKVs() {
		if (kVs == null) {
			kVs = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.TRANSFORMER_CODE__KVS);
		}
		return kVs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getKVAs() {
		if (kVAs == null) {
			kVAs = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.TRANSFORMER_CODE__KV_AS);
		}
		return kVAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getTaps() {
		if (taps == null) {
			taps = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.TRANSFORMER_CODE__TAPS);
		}
		return taps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXhl() {
		return xhl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXhl(double newXhl) {
		double oldXhl = xhl;
		xhl = newXhl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__XHL, oldXhl, xhl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXht() {
		return xht;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXht(double newXht) {
		double oldXht = xht;
		xht = newXht;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__XHT, oldXht, xht));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXlt() {
		return xlt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXlt(double newXlt) {
		double oldXlt = xlt;
		xlt = newXlt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__XLT, oldXlt, xlt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getXscArray() {
		if (xscArray == null) {
			xscArray = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.TRANSFORMER_CODE__XSC_ARRAY);
		}
		return xscArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getThermal() {
		return thermal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThermal(double newThermal) {
		double oldThermal = thermal;
		thermal = newThermal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__THERMAL, oldThermal, thermal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getN() {
		return n;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setN(double newN) {
		double oldN = n;
		n = newN;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__N, oldN, n));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getM() {
		return m;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setM(double newM) {
		double oldM = m;
		m = newM;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__M, oldM, m));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFLRise() {
		return fLRise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFLRise(double newFLRise) {
		double oldFLRise = fLRise;
		fLRise = newFLRise;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__FL_RISE, oldFLRise, fLRise));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getHSRise() {
		return hSRise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHSRise(double newHSRise) {
		double oldHSRise = hSRise;
		hSRise = newHSRise;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__HS_RISE, oldHSRise, hSRise));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctLoadLoss(double newPctLoadLoss) {
		double oldPctLoadLoss = pctLoadLoss;
		pctLoadLoss = newPctLoadLoss;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PCT_LOAD_LOSS, oldPctLoadLoss, pctLoadLoss));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctNoLoadLoss(double newPctNoLoadLoss) {
		double oldPctNoLoadLoss = pctNoLoadLoss;
		pctNoLoadLoss = newPctNoLoadLoss;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PCT_NO_LOAD_LOSS, oldPctNoLoadLoss, pctNoLoadLoss));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getNormHKVA() {
		return normHKVA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormHKVA(double newNormHKVA) {
		double oldNormHKVA = normHKVA;
		normHKVA = newNormHKVA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__NORM_HKVA, oldNormHKVA, normHKVA));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEmergHKVA() {
		return emergHKVA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmergHKVA(double newEmergHKVA) {
		double oldEmergHKVA = emergHKVA;
		emergHKVA = newEmergHKVA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__EMERG_HKVA, oldEmergHKVA, emergHKVA));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMaxTap() {
		return maxTap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxTap(double newMaxTap) {
		double oldMaxTap = maxTap;
		maxTap = newMaxTap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__MAX_TAP, oldMaxTap, maxTap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMinTap() {
		return minTap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinTap(double newMinTap) {
		double oldMinTap = minTap;
		minTap = newMinTap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__MIN_TAP, oldMinTap, minTap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumTaps() {
		return numTaps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumTaps(int newNumTaps) {
		int oldNumTaps = numTaps;
		numTaps = newNumTaps;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__NUM_TAPS, oldNumTaps, numTaps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctIMag() {
		return pctIMag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctIMag(double newPctIMag) {
		double oldPctIMag = pctIMag;
		pctIMag = newPctIMag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PCT_IMAG, oldPctIMag, pctIMag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPpmAntiFloat() {
		return ppmAntiFloat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPpmAntiFloat(double newPpmAntiFloat) {
		double oldPpmAntiFloat = ppmAntiFloat;
		ppmAntiFloat = newPpmAntiFloat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PPM_ANTI_FLOAT, oldPpmAntiFloat, ppmAntiFloat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctRS() {
		return pctRS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctRS(double newPctRS) {
		double oldPctRS = pctRS;
		pctRS = newPctRS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__PCT_RS, oldPctRS, pctRS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformerCode getLike() {
		if (like != null && like.eIsProxy()) {
			InternalEObject oldLike = (InternalEObject)like;
			like = (TransformerCode)eResolveProxy(oldLike);
			if (like != oldLike) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.TRANSFORMER_CODE__LIKE, oldLike, like));
			}
		}
		return like;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformerCode basicGetLike() {
		return like;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLike(TransformerCode newLike) {
		TransformerCode oldLike = like;
		like = newLike;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TRANSFORMER_CODE__LIKE, oldLike, like));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.TRANSFORMER_CODE__PHASES:
				return getPhases();
			case GeneralPackage.TRANSFORMER_CODE__WINDINGS:
				return getWindings();
			case GeneralPackage.TRANSFORMER_CODE__WDG:
				return getWdg();
			case GeneralPackage.TRANSFORMER_CODE__CONN:
				return getConn();
			case GeneralPackage.TRANSFORMER_CODE__KV:
				return getKV();
			case GeneralPackage.TRANSFORMER_CODE__KVA:
				return getKVA();
			case GeneralPackage.TRANSFORMER_CODE__TAP:
				return getTap();
			case GeneralPackage.TRANSFORMER_CODE__PCT_R:
				return getPctR();
			case GeneralPackage.TRANSFORMER_CODE__RNEUT:
				return getRNeut();
			case GeneralPackage.TRANSFORMER_CODE__XNEUT:
				return getXNeut();
			case GeneralPackage.TRANSFORMER_CODE__CONNS:
				return getConns();
			case GeneralPackage.TRANSFORMER_CODE__KVS:
				return getKVs();
			case GeneralPackage.TRANSFORMER_CODE__KV_AS:
				return getKVAs();
			case GeneralPackage.TRANSFORMER_CODE__TAPS:
				return getTaps();
			case GeneralPackage.TRANSFORMER_CODE__XHL:
				return getXhl();
			case GeneralPackage.TRANSFORMER_CODE__XHT:
				return getXht();
			case GeneralPackage.TRANSFORMER_CODE__XLT:
				return getXlt();
			case GeneralPackage.TRANSFORMER_CODE__XSC_ARRAY:
				return getXscArray();
			case GeneralPackage.TRANSFORMER_CODE__THERMAL:
				return getThermal();
			case GeneralPackage.TRANSFORMER_CODE__N:
				return getN();
			case GeneralPackage.TRANSFORMER_CODE__M:
				return getM();
			case GeneralPackage.TRANSFORMER_CODE__FL_RISE:
				return getFLRise();
			case GeneralPackage.TRANSFORMER_CODE__HS_RISE:
				return getHSRise();
			case GeneralPackage.TRANSFORMER_CODE__PCT_LOAD_LOSS:
				return getPctLoadLoss();
			case GeneralPackage.TRANSFORMER_CODE__PCT_NO_LOAD_LOSS:
				return getPctNoLoadLoss();
			case GeneralPackage.TRANSFORMER_CODE__NORM_HKVA:
				return getNormHKVA();
			case GeneralPackage.TRANSFORMER_CODE__EMERG_HKVA:
				return getEmergHKVA();
			case GeneralPackage.TRANSFORMER_CODE__MAX_TAP:
				return getMaxTap();
			case GeneralPackage.TRANSFORMER_CODE__MIN_TAP:
				return getMinTap();
			case GeneralPackage.TRANSFORMER_CODE__NUM_TAPS:
				return getNumTaps();
			case GeneralPackage.TRANSFORMER_CODE__PCT_IMAG:
				return getPctIMag();
			case GeneralPackage.TRANSFORMER_CODE__PPM_ANTI_FLOAT:
				return getPpmAntiFloat();
			case GeneralPackage.TRANSFORMER_CODE__PCT_RS:
				return getPctRS();
			case GeneralPackage.TRANSFORMER_CODE__LIKE:
				if (resolve) return getLike();
				return basicGetLike();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GeneralPackage.TRANSFORMER_CODE__PHASES:
				setPhases((Integer)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__WINDINGS:
				setWindings((Integer)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__WDG:
				setWdg((Integer)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__CONN:
				setConn((connectionType)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__KV:
				setKV((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__KVA:
				setKVA((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__TAP:
				setTap((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_R:
				setPctR((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__RNEUT:
				setRNeut((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XNEUT:
				setXNeut((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__CONNS:
				getConns().clear();
				getConns().addAll((Collection<? extends connectionType>)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__KVS:
				getKVs().clear();
				getKVs().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__KV_AS:
				getKVAs().clear();
				getKVAs().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__TAPS:
				getTaps().clear();
				getTaps().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XHL:
				setXhl((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XHT:
				setXht((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XLT:
				setXlt((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XSC_ARRAY:
				getXscArray().clear();
				getXscArray().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__THERMAL:
				setThermal((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__N:
				setN((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__M:
				setM((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__FL_RISE:
				setFLRise((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__HS_RISE:
				setHSRise((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_LOAD_LOSS:
				setPctLoadLoss((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_NO_LOAD_LOSS:
				setPctNoLoadLoss((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__NORM_HKVA:
				setNormHKVA((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__EMERG_HKVA:
				setEmergHKVA((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__MAX_TAP:
				setMaxTap((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__MIN_TAP:
				setMinTap((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__NUM_TAPS:
				setNumTaps((Integer)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_IMAG:
				setPctIMag((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PPM_ANTI_FLOAT:
				setPpmAntiFloat((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_RS:
				setPctRS((Double)newValue);
				return;
			case GeneralPackage.TRANSFORMER_CODE__LIKE:
				setLike((TransformerCode)newValue);
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
			case GeneralPackage.TRANSFORMER_CODE__PHASES:
				setPhases(PHASES_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__WINDINGS:
				setWindings(WINDINGS_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__WDG:
				setWdg(WDG_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__KV:
				setKV(KV_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__KVA:
				setKVA(KVA_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__TAP:
				setTap(TAP_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_R:
				setPctR(PCT_R_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__RNEUT:
				setRNeut(RNEUT_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XNEUT:
				setXNeut(XNEUT_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__CONNS:
				getConns().clear();
				return;
			case GeneralPackage.TRANSFORMER_CODE__KVS:
				getKVs().clear();
				return;
			case GeneralPackage.TRANSFORMER_CODE__KV_AS:
				getKVAs().clear();
				return;
			case GeneralPackage.TRANSFORMER_CODE__TAPS:
				getTaps().clear();
				return;
			case GeneralPackage.TRANSFORMER_CODE__XHL:
				setXhl(XHL_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XHT:
				setXht(XHT_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XLT:
				setXlt(XLT_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__XSC_ARRAY:
				getXscArray().clear();
				return;
			case GeneralPackage.TRANSFORMER_CODE__THERMAL:
				setThermal(THERMAL_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__N:
				setN(N_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__M:
				setM(M_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__FL_RISE:
				setFLRise(FL_RISE_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__HS_RISE:
				setHSRise(HS_RISE_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_LOAD_LOSS:
				setPctLoadLoss(PCT_LOAD_LOSS_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_NO_LOAD_LOSS:
				setPctNoLoadLoss(PCT_NO_LOAD_LOSS_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__NORM_HKVA:
				setNormHKVA(NORM_HKVA_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__EMERG_HKVA:
				setEmergHKVA(EMERG_HKVA_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__MAX_TAP:
				setMaxTap(MAX_TAP_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__MIN_TAP:
				setMinTap(MIN_TAP_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__NUM_TAPS:
				setNumTaps(NUM_TAPS_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_IMAG:
				setPctIMag(PCT_IMAG_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PPM_ANTI_FLOAT:
				setPpmAntiFloat(PPM_ANTI_FLOAT_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__PCT_RS:
				setPctRS(PCT_RS_EDEFAULT);
				return;
			case GeneralPackage.TRANSFORMER_CODE__LIKE:
				setLike((TransformerCode)null);
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
			case GeneralPackage.TRANSFORMER_CODE__PHASES:
				return phases != PHASES_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__WINDINGS:
				return windings != WINDINGS_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__WDG:
				return wdg != WDG_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__CONN:
				return conn != CONN_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__KV:
				return kV != KV_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__KVA:
				return kVA != KVA_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__TAP:
				return tap != TAP_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__PCT_R:
				return pctR != PCT_R_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__RNEUT:
				return rNeut != RNEUT_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__XNEUT:
				return xNeut != XNEUT_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__CONNS:
				return conns != null && !conns.isEmpty();
			case GeneralPackage.TRANSFORMER_CODE__KVS:
				return kVs != null && !kVs.isEmpty();
			case GeneralPackage.TRANSFORMER_CODE__KV_AS:
				return kVAs != null && !kVAs.isEmpty();
			case GeneralPackage.TRANSFORMER_CODE__TAPS:
				return taps != null && !taps.isEmpty();
			case GeneralPackage.TRANSFORMER_CODE__XHL:
				return xhl != XHL_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__XHT:
				return xht != XHT_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__XLT:
				return xlt != XLT_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__XSC_ARRAY:
				return xscArray != null && !xscArray.isEmpty();
			case GeneralPackage.TRANSFORMER_CODE__THERMAL:
				return thermal != THERMAL_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__N:
				return n != N_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__M:
				return m != M_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__FL_RISE:
				return fLRise != FL_RISE_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__HS_RISE:
				return hSRise != HS_RISE_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__PCT_LOAD_LOSS:
				return pctLoadLoss != PCT_LOAD_LOSS_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__PCT_NO_LOAD_LOSS:
				return pctNoLoadLoss != PCT_NO_LOAD_LOSS_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__NORM_HKVA:
				return normHKVA != NORM_HKVA_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__EMERG_HKVA:
				return emergHKVA != EMERG_HKVA_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__MAX_TAP:
				return maxTap != MAX_TAP_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__MIN_TAP:
				return minTap != MIN_TAP_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__NUM_TAPS:
				return numTaps != NUM_TAPS_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__PCT_IMAG:
				return pctIMag != PCT_IMAG_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__PPM_ANTI_FLOAT:
				return ppmAntiFloat != PPM_ANTI_FLOAT_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__PCT_RS:
				return pctRS != PCT_RS_EDEFAULT;
			case GeneralPackage.TRANSFORMER_CODE__LIKE:
				return like != null;
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
		result.append(" (phases: ");
		result.append(phases);
		result.append(", windings: ");
		result.append(windings);
		result.append(", wdg: ");
		result.append(wdg);
		result.append(", conn: ");
		result.append(conn);
		result.append(", kV: ");
		result.append(kV);
		result.append(", kVA: ");
		result.append(kVA);
		result.append(", tap: ");
		result.append(tap);
		result.append(", pctR: ");
		result.append(pctR);
		result.append(", rNeut: ");
		result.append(rNeut);
		result.append(", xNeut: ");
		result.append(xNeut);
		result.append(", conns: ");
		result.append(conns);
		result.append(", kVs: ");
		result.append(kVs);
		result.append(", kVAs: ");
		result.append(kVAs);
		result.append(", taps: ");
		result.append(taps);
		result.append(", xhl: ");
		result.append(xhl);
		result.append(", xht: ");
		result.append(xht);
		result.append(", xlt: ");
		result.append(xlt);
		result.append(", xscArray: ");
		result.append(xscArray);
		result.append(", thermal: ");
		result.append(thermal);
		result.append(", n: ");
		result.append(n);
		result.append(", m: ");
		result.append(m);
		result.append(", fLRise: ");
		result.append(fLRise);
		result.append(", hSRise: ");
		result.append(hSRise);
		result.append(", pctLoadLoss: ");
		result.append(pctLoadLoss);
		result.append(", pctNoLoadLoss: ");
		result.append(pctNoLoadLoss);
		result.append(", normHKVA: ");
		result.append(normHKVA);
		result.append(", emergHKVA: ");
		result.append(emergHKVA);
		result.append(", maxTap: ");
		result.append(maxTap);
		result.append(", minTap: ");
		result.append(minTap);
		result.append(", numTaps: ");
		result.append(numTaps);
		result.append(", pctIMag: ");
		result.append(pctIMag);
		result.append(", ppmAntiFloat: ");
		result.append(ppmAntiFloat);
		result.append(", pctRS: ");
		result.append(pctRS);
		result.append(')');
		return result.toString();
	}

} //TransformerCodeImpl
