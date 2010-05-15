/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery.impl;

import dss.common.connectionType;

import dss.delivery.DeliveryPackage;
import dss.delivery.Transformer;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getWindings <em>Windings</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getWdg <em>Wdg</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getBus <em>Bus</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getKV <em>KV</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getKVA <em>KVA</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getTap <em>Tap</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getRPct <em>RPct</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getBuses <em>Buses</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getConns <em>Conns</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getKVs <em>KVs</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getKVAs <em>KV As</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getTaps <em>Taps</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getXHL <em>XHL</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getXHT <em>XHT</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getXLT <em>XLT</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getXSCArray <em>XSC Array</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getThermal <em>Thermal</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getN <em>N</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getM <em>M</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getFLRise <em>FL Rise</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getHSRise <em>HS Rise</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getPctLoadLoss <em>Pct Load Loss</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getPctNoLoadLoss <em>Pct No Load Loss</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getNormHKVa <em>Norm HK Va</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getEmergHKVa <em>Emerg HK Va</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#isSubstation <em>Substation</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getMaxTap <em>Max Tap</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getMinTap <em>Min Tap</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getNumTaps <em>Num Taps</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getSubName <em>Sub Name</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getPctImage <em>Pct Image</em>}</li>
 *   <li>{@link dss.delivery.impl.TransformerImpl#getPpmAntiFloat <em>Ppm Anti Float</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformerImpl extends PowerDeliveryElementImpl implements Transformer {
	/**
	 * The default value of the '{@link #getWindings() <em>Windings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindings()
	 * @generated
	 * @ordered
	 */
	protected static final int WINDINGS_EDEFAULT = 2;

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
	protected static final int WDG_EDEFAULT = 1;

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
	 * The default value of the '{@link #getBus() <em>Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBus()
	 * @generated
	 * @ordered
	 */
	protected static final String BUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBus() <em>Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBus()
	 * @generated
	 * @ordered
	 */
	protected String bus = BUS_EDEFAULT;

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
	 * The default value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
	protected static final double KVA_EDEFAULT = 1000.0;

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
	protected static final double TAP_EDEFAULT = 1.0;

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
	 * The default value of the '{@link #getRPct() <em>RPct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRPct()
	 * @generated
	 * @ordered
	 */
	protected static final double RPCT_EDEFAULT = 0.2;

	/**
	 * The cached value of the '{@link #getRPct() <em>RPct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRPct()
	 * @generated
	 * @ordered
	 */
	protected double rPct = RPCT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRNeut() <em>RNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRNeut()
	 * @generated
	 * @ordered
	 */
	protected static final double RNEUT_EDEFAULT = 0.0;

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
	 * The cached value of the '{@link #getBuses() <em>Buses</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuses()
	 * @generated
	 * @ordered
	 */
	protected EList<String> buses;

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
	 * The default value of the '{@link #getXHL() <em>XHL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXHL()
	 * @generated
	 * @ordered
	 */
	protected static final double XHL_EDEFAULT = 7.0;

	/**
	 * The cached value of the '{@link #getXHL() <em>XHL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXHL()
	 * @generated
	 * @ordered
	 */
	protected double xHL = XHL_EDEFAULT;

	/**
	 * The default value of the '{@link #getXHT() <em>XHT</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXHT()
	 * @generated
	 * @ordered
	 */
	protected static final double XHT_EDEFAULT = 35.0;

	/**
	 * The cached value of the '{@link #getXHT() <em>XHT</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXHT()
	 * @generated
	 * @ordered
	 */
	protected double xHT = XHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getXLT() <em>XLT</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXLT()
	 * @generated
	 * @ordered
	 */
	protected static final double XLT_EDEFAULT = 30.0;

	/**
	 * The cached value of the '{@link #getXLT() <em>XLT</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXLT()
	 * @generated
	 * @ordered
	 */
	protected double xLT = XLT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getXSCArray() <em>XSC Array</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSCArray()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> xSCArray;

	/**
	 * The default value of the '{@link #getThermal() <em>Thermal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThermal()
	 * @generated
	 * @ordered
	 */
	protected static final double THERMAL_EDEFAULT = 2.0;

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
	protected static final double N_EDEFAULT = 0.8;

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
	protected static final double M_EDEFAULT = 0.8;

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
	 * The default value of the '{@link #getNormHKVa() <em>Norm HK Va</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormHKVa()
	 * @generated
	 * @ordered
	 */
	protected static final double NORM_HK_VA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getNormHKVa() <em>Norm HK Va</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormHKVa()
	 * @generated
	 * @ordered
	 */
	protected double normHKVa = NORM_HK_VA_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmergHKVa() <em>Emerg HK Va</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergHKVa()
	 * @generated
	 * @ordered
	 */
	protected static final double EMERG_HK_VA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEmergHKVa() <em>Emerg HK Va</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergHKVa()
	 * @generated
	 * @ordered
	 */
	protected double emergHKVa = EMERG_HK_VA_EDEFAULT;

	/**
	 * The default value of the '{@link #isSubstation() <em>Substation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSubstation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUBSTATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSubstation() <em>Substation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSubstation()
	 * @generated
	 * @ordered
	 */
	protected boolean substation = SUBSTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxTap() <em>Max Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTap()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_TAP_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #getSubName() <em>Sub Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubName()
	 * @generated
	 * @ordered
	 */
	protected static final String SUB_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubName() <em>Sub Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubName()
	 * @generated
	 * @ordered
	 */
	protected String subName = SUB_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctImage() <em>Pct Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctImage()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_IMAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctImage() <em>Pct Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctImage()
	 * @generated
	 * @ordered
	 */
	protected double pctImage = PCT_IMAGE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeliveryPackage.Literals.TRANSFORMER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__WINDINGS, oldWindings, windings));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__WDG, oldWdg, wdg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBus() {
		return bus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBus(String newBus) {
		String oldBus = bus;
		bus = newBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__BUS, oldBus, bus));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__CONN, oldConn, conn));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__KV, oldKV, kV));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__KVA, oldKVA, kVA));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__TAP, oldTap, tap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRPct() {
		return rPct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRPct(double newRPct) {
		double oldRPct = rPct;
		rPct = newRPct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__RPCT, oldRPct, rPct));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__RNEUT, oldRNeut, rNeut));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__XNEUT, oldXNeut, xNeut));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBuses() {
		if (buses == null) {
			buses = new EDataTypeUniqueEList<String>(String.class, this, DeliveryPackage.TRANSFORMER__BUSES);
		}
		return buses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<connectionType> getConns() {
		if (conns == null) {
			conns = new EDataTypeUniqueEList<connectionType>(connectionType.class, this, DeliveryPackage.TRANSFORMER__CONNS);
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
			kVs = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.TRANSFORMER__KVS);
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
			kVAs = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.TRANSFORMER__KV_AS);
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
			taps = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.TRANSFORMER__TAPS);
		}
		return taps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXHL() {
		return xHL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXHL(double newXHL) {
		double oldXHL = xHL;
		xHL = newXHL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__XHL, oldXHL, xHL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXHT() {
		return xHT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXHT(double newXHT) {
		double oldXHT = xHT;
		xHT = newXHT;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__XHT, oldXHT, xHT));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXLT() {
		return xLT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXLT(double newXLT) {
		double oldXLT = xLT;
		xLT = newXLT;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__XLT, oldXLT, xLT));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getXSCArray() {
		if (xSCArray == null) {
			xSCArray = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.TRANSFORMER__XSC_ARRAY);
		}
		return xSCArray;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__THERMAL, oldThermal, thermal));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__N, oldN, n));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__M, oldM, m));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__FL_RISE, oldFLRise, fLRise));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__HS_RISE, oldHSRise, hSRise));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__PCT_LOAD_LOSS, oldPctLoadLoss, pctLoadLoss));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__PCT_NO_LOAD_LOSS, oldPctNoLoadLoss, pctNoLoadLoss));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getNormHKVa() {
		return normHKVa;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormHKVa(double newNormHKVa) {
		double oldNormHKVa = normHKVa;
		normHKVa = newNormHKVa;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__NORM_HK_VA, oldNormHKVa, normHKVa));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEmergHKVa() {
		return emergHKVa;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmergHKVa(double newEmergHKVa) {
		double oldEmergHKVa = emergHKVa;
		emergHKVa = newEmergHKVa;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__EMERG_HK_VA, oldEmergHKVa, emergHKVa));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSubstation() {
		return substation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubstation(boolean newSubstation) {
		boolean oldSubstation = substation;
		substation = newSubstation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__SUBSTATION, oldSubstation, substation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__MAX_TAP, oldMaxTap, maxTap));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__MIN_TAP, oldMinTap, minTap));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__NUM_TAPS, oldNumTaps, numTaps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubName() {
		return subName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubName(String newSubName) {
		String oldSubName = subName;
		subName = newSubName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__SUB_NAME, oldSubName, subName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctImage() {
		return pctImage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctImage(double newPctImage) {
		double oldPctImage = pctImage;
		pctImage = newPctImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__PCT_IMAGE, oldPctImage, pctImage));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.TRANSFORMER__PPM_ANTI_FLOAT, oldPpmAntiFloat, ppmAntiFloat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeliveryPackage.TRANSFORMER__WINDINGS:
				return getWindings();
			case DeliveryPackage.TRANSFORMER__WDG:
				return getWdg();
			case DeliveryPackage.TRANSFORMER__BUS:
				return getBus();
			case DeliveryPackage.TRANSFORMER__CONN:
				return getConn();
			case DeliveryPackage.TRANSFORMER__KV:
				return getKV();
			case DeliveryPackage.TRANSFORMER__KVA:
				return getKVA();
			case DeliveryPackage.TRANSFORMER__TAP:
				return getTap();
			case DeliveryPackage.TRANSFORMER__RPCT:
				return getRPct();
			case DeliveryPackage.TRANSFORMER__RNEUT:
				return getRNeut();
			case DeliveryPackage.TRANSFORMER__XNEUT:
				return getXNeut();
			case DeliveryPackage.TRANSFORMER__BUSES:
				return getBuses();
			case DeliveryPackage.TRANSFORMER__CONNS:
				return getConns();
			case DeliveryPackage.TRANSFORMER__KVS:
				return getKVs();
			case DeliveryPackage.TRANSFORMER__KV_AS:
				return getKVAs();
			case DeliveryPackage.TRANSFORMER__TAPS:
				return getTaps();
			case DeliveryPackage.TRANSFORMER__XHL:
				return getXHL();
			case DeliveryPackage.TRANSFORMER__XHT:
				return getXHT();
			case DeliveryPackage.TRANSFORMER__XLT:
				return getXLT();
			case DeliveryPackage.TRANSFORMER__XSC_ARRAY:
				return getXSCArray();
			case DeliveryPackage.TRANSFORMER__THERMAL:
				return getThermal();
			case DeliveryPackage.TRANSFORMER__N:
				return getN();
			case DeliveryPackage.TRANSFORMER__M:
				return getM();
			case DeliveryPackage.TRANSFORMER__FL_RISE:
				return getFLRise();
			case DeliveryPackage.TRANSFORMER__HS_RISE:
				return getHSRise();
			case DeliveryPackage.TRANSFORMER__PCT_LOAD_LOSS:
				return getPctLoadLoss();
			case DeliveryPackage.TRANSFORMER__PCT_NO_LOAD_LOSS:
				return getPctNoLoadLoss();
			case DeliveryPackage.TRANSFORMER__NORM_HK_VA:
				return getNormHKVa();
			case DeliveryPackage.TRANSFORMER__EMERG_HK_VA:
				return getEmergHKVa();
			case DeliveryPackage.TRANSFORMER__SUBSTATION:
				return isSubstation();
			case DeliveryPackage.TRANSFORMER__MAX_TAP:
				return getMaxTap();
			case DeliveryPackage.TRANSFORMER__MIN_TAP:
				return getMinTap();
			case DeliveryPackage.TRANSFORMER__NUM_TAPS:
				return getNumTaps();
			case DeliveryPackage.TRANSFORMER__SUB_NAME:
				return getSubName();
			case DeliveryPackage.TRANSFORMER__PCT_IMAGE:
				return getPctImage();
			case DeliveryPackage.TRANSFORMER__PPM_ANTI_FLOAT:
				return getPpmAntiFloat();
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
			case DeliveryPackage.TRANSFORMER__WINDINGS:
				setWindings((Integer)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__WDG:
				setWdg((Integer)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__BUS:
				setBus((String)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__CONN:
				setConn((connectionType)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__KV:
				setKV((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__KVA:
				setKVA((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__TAP:
				setTap((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__RPCT:
				setRPct((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__RNEUT:
				setRNeut((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__XNEUT:
				setXNeut((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__BUSES:
				getBuses().clear();
				getBuses().addAll((Collection<? extends String>)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__CONNS:
				getConns().clear();
				getConns().addAll((Collection<? extends connectionType>)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__KVS:
				getKVs().clear();
				getKVs().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__KV_AS:
				getKVAs().clear();
				getKVAs().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__TAPS:
				getTaps().clear();
				getTaps().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__XHL:
				setXHL((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__XHT:
				setXHT((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__XLT:
				setXLT((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__XSC_ARRAY:
				getXSCArray().clear();
				getXSCArray().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__THERMAL:
				setThermal((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__N:
				setN((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__M:
				setM((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__FL_RISE:
				setFLRise((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__HS_RISE:
				setHSRise((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__PCT_LOAD_LOSS:
				setPctLoadLoss((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__PCT_NO_LOAD_LOSS:
				setPctNoLoadLoss((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__NORM_HK_VA:
				setNormHKVa((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__EMERG_HK_VA:
				setEmergHKVa((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__SUBSTATION:
				setSubstation((Boolean)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__MAX_TAP:
				setMaxTap((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__MIN_TAP:
				setMinTap((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__NUM_TAPS:
				setNumTaps((Integer)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__SUB_NAME:
				setSubName((String)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__PCT_IMAGE:
				setPctImage((Double)newValue);
				return;
			case DeliveryPackage.TRANSFORMER__PPM_ANTI_FLOAT:
				setPpmAntiFloat((Double)newValue);
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
			case DeliveryPackage.TRANSFORMER__WINDINGS:
				setWindings(WINDINGS_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__WDG:
				setWdg(WDG_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__BUS:
				setBus(BUS_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__KV:
				setKV(KV_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__KVA:
				setKVA(KVA_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__TAP:
				setTap(TAP_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__RPCT:
				setRPct(RPCT_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__RNEUT:
				setRNeut(RNEUT_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__XNEUT:
				setXNeut(XNEUT_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__BUSES:
				getBuses().clear();
				return;
			case DeliveryPackage.TRANSFORMER__CONNS:
				getConns().clear();
				return;
			case DeliveryPackage.TRANSFORMER__KVS:
				getKVs().clear();
				return;
			case DeliveryPackage.TRANSFORMER__KV_AS:
				getKVAs().clear();
				return;
			case DeliveryPackage.TRANSFORMER__TAPS:
				getTaps().clear();
				return;
			case DeliveryPackage.TRANSFORMER__XHL:
				setXHL(XHL_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__XHT:
				setXHT(XHT_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__XLT:
				setXLT(XLT_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__XSC_ARRAY:
				getXSCArray().clear();
				return;
			case DeliveryPackage.TRANSFORMER__THERMAL:
				setThermal(THERMAL_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__N:
				setN(N_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__M:
				setM(M_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__FL_RISE:
				setFLRise(FL_RISE_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__HS_RISE:
				setHSRise(HS_RISE_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__PCT_LOAD_LOSS:
				setPctLoadLoss(PCT_LOAD_LOSS_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__PCT_NO_LOAD_LOSS:
				setPctNoLoadLoss(PCT_NO_LOAD_LOSS_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__NORM_HK_VA:
				setNormHKVa(NORM_HK_VA_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__EMERG_HK_VA:
				setEmergHKVa(EMERG_HK_VA_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__SUBSTATION:
				setSubstation(SUBSTATION_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__MAX_TAP:
				setMaxTap(MAX_TAP_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__MIN_TAP:
				setMinTap(MIN_TAP_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__NUM_TAPS:
				setNumTaps(NUM_TAPS_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__SUB_NAME:
				setSubName(SUB_NAME_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__PCT_IMAGE:
				setPctImage(PCT_IMAGE_EDEFAULT);
				return;
			case DeliveryPackage.TRANSFORMER__PPM_ANTI_FLOAT:
				setPpmAntiFloat(PPM_ANTI_FLOAT_EDEFAULT);
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
			case DeliveryPackage.TRANSFORMER__WINDINGS:
				return windings != WINDINGS_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__WDG:
				return wdg != WDG_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__BUS:
				return BUS_EDEFAULT == null ? bus != null : !BUS_EDEFAULT.equals(bus);
			case DeliveryPackage.TRANSFORMER__CONN:
				return conn != CONN_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__KV:
				return kV != KV_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__KVA:
				return kVA != KVA_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__TAP:
				return tap != TAP_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__RPCT:
				return rPct != RPCT_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__RNEUT:
				return rNeut != RNEUT_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__XNEUT:
				return xNeut != XNEUT_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__BUSES:
				return buses != null && !buses.isEmpty();
			case DeliveryPackage.TRANSFORMER__CONNS:
				return conns != null && !conns.isEmpty();
			case DeliveryPackage.TRANSFORMER__KVS:
				return kVs != null && !kVs.isEmpty();
			case DeliveryPackage.TRANSFORMER__KV_AS:
				return kVAs != null && !kVAs.isEmpty();
			case DeliveryPackage.TRANSFORMER__TAPS:
				return taps != null && !taps.isEmpty();
			case DeliveryPackage.TRANSFORMER__XHL:
				return xHL != XHL_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__XHT:
				return xHT != XHT_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__XLT:
				return xLT != XLT_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__XSC_ARRAY:
				return xSCArray != null && !xSCArray.isEmpty();
			case DeliveryPackage.TRANSFORMER__THERMAL:
				return thermal != THERMAL_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__N:
				return n != N_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__M:
				return m != M_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__FL_RISE:
				return fLRise != FL_RISE_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__HS_RISE:
				return hSRise != HS_RISE_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__PCT_LOAD_LOSS:
				return pctLoadLoss != PCT_LOAD_LOSS_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__PCT_NO_LOAD_LOSS:
				return pctNoLoadLoss != PCT_NO_LOAD_LOSS_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__NORM_HK_VA:
				return normHKVa != NORM_HK_VA_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__EMERG_HK_VA:
				return emergHKVa != EMERG_HK_VA_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__SUBSTATION:
				return substation != SUBSTATION_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__MAX_TAP:
				return maxTap != MAX_TAP_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__MIN_TAP:
				return minTap != MIN_TAP_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__NUM_TAPS:
				return numTaps != NUM_TAPS_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__SUB_NAME:
				return SUB_NAME_EDEFAULT == null ? subName != null : !SUB_NAME_EDEFAULT.equals(subName);
			case DeliveryPackage.TRANSFORMER__PCT_IMAGE:
				return pctImage != PCT_IMAGE_EDEFAULT;
			case DeliveryPackage.TRANSFORMER__PPM_ANTI_FLOAT:
				return ppmAntiFloat != PPM_ANTI_FLOAT_EDEFAULT;
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
		result.append(" (windings: ");
		result.append(windings);
		result.append(", wdg: ");
		result.append(wdg);
		result.append(", bus: ");
		result.append(bus);
		result.append(", conn: ");
		result.append(conn);
		result.append(", kV: ");
		result.append(kV);
		result.append(", kVA: ");
		result.append(kVA);
		result.append(", tap: ");
		result.append(tap);
		result.append(", rPct: ");
		result.append(rPct);
		result.append(", rNeut: ");
		result.append(rNeut);
		result.append(", xNeut: ");
		result.append(xNeut);
		result.append(", buses: ");
		result.append(buses);
		result.append(", conns: ");
		result.append(conns);
		result.append(", kVs: ");
		result.append(kVs);
		result.append(", kVAs: ");
		result.append(kVAs);
		result.append(", taps: ");
		result.append(taps);
		result.append(", xHL: ");
		result.append(xHL);
		result.append(", xHT: ");
		result.append(xHT);
		result.append(", xLT: ");
		result.append(xLT);
		result.append(", xSCArray: ");
		result.append(xSCArray);
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
		result.append(", normHKVa: ");
		result.append(normHKVa);
		result.append(", emergHKVa: ");
		result.append(emergHKVa);
		result.append(", substation: ");
		result.append(substation);
		result.append(", maxTap: ");
		result.append(maxTap);
		result.append(", minTap: ");
		result.append(minTap);
		result.append(", numTaps: ");
		result.append(numTaps);
		result.append(", subName: ");
		result.append(subName);
		result.append(", pctImage: ");
		result.append(pctImage);
		result.append(", ppmAntiFloat: ");
		result.append(ppmAntiFloat);
		result.append(')');
		return result.toString();
	}

} //TransformerImpl
