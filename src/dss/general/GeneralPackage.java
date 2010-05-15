/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see dss.general.GeneralFactory
 * @model kind="package"
 * @generated
 */
public interface GeneralPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "general";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.openpowersystem.com/dss/general";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "gen";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GeneralPackage eINSTANCE = dss.general.impl.GeneralPackageImpl.init();

	/**
	 * The meta object id for the '{@link dss.general.impl.DSSObjectImpl <em>DSS Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.DSSObjectImpl
	 * @see dss.general.impl.GeneralPackageImpl#getDSSObject()
	 * @generated
	 */
	int DSS_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>DSS Obj Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__DSS_OBJ_TYPE = 0;

	/**
	 * The feature id for the '<em><b>DSS Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__DSS_CLASS_NAME = 1;

	/**
	 * The feature id for the '<em><b>Parent Class</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__PARENT_CLASS = 2;

	/**
	 * The feature id for the '<em><b>Class Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__CLASS_INDEX = 3;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__DIRTY = 4;

	/**
	 * The feature id for the '<em><b>Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__FLAG = 5;

	/**
	 * The feature id for the '<em><b>Prop Sequence</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT__PROP_SEQUENCE = 6;

	/**
	 * The number of structural features of the '<em>DSS Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSS_OBJECT_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link dss.general.impl.GrowthShapeImpl <em>Growth Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.GrowthShapeImpl
	 * @see dss.general.impl.GeneralPackageImpl#getGrowthShape()
	 * @generated
	 */
	int GROWTH_SHAPE = 1;

	/**
	 * The feature id for the '<em><b>NPts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROWTH_SHAPE__NPTS = 0;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROWTH_SHAPE__YEAR = 1;

	/**
	 * The feature id for the '<em><b>Csv File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROWTH_SHAPE__CSV_FILE = 2;

	/**
	 * The feature id for the '<em><b>Sng File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROWTH_SHAPE__SNG_FILE = 3;

	/**
	 * The feature id for the '<em><b>Dbl File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROWTH_SHAPE__DBL_FILE = 4;

	/**
	 * The number of structural features of the '<em>Growth Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROWTH_SHAPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link dss.general.impl.LineCodeImpl <em>Line Code</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.LineCodeImpl
	 * @see dss.general.impl.GeneralPackageImpl#getLineCode()
	 * @generated
	 */
	int LINE_CODE = 2;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__NPHASES = 0;

	/**
	 * The feature id for the '<em><b>R1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__R1 = 1;

	/**
	 * The feature id for the '<em><b>X1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__X1 = 2;

	/**
	 * The feature id for the '<em><b>X0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__X0 = 3;

	/**
	 * The feature id for the '<em><b>C1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__C1 = 4;

	/**
	 * The feature id for the '<em><b>C0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__C0 = 5;

	/**
	 * The feature id for the '<em><b>Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__UNITS = 6;

	/**
	 * The feature id for the '<em><b>RMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__RMATRIX = 7;

	/**
	 * The feature id for the '<em><b>XMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__XMATRIX = 8;

	/**
	 * The feature id for the '<em><b>CMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__CMATRIX = 9;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__BASE_FREQ = 10;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__NORM_AMPS = 11;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__EMERG_AMPS = 12;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__FAULT_RATE = 13;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__PCT_PERM = 14;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__REPAIR = 15;

	/**
	 * The feature id for the '<em><b>Kron</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__KRON = 16;

	/**
	 * The feature id for the '<em><b>Rg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__RG = 17;

	/**
	 * The feature id for the '<em><b>Xg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__XG = 18;

	/**
	 * The feature id for the '<em><b>Rho</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE__RHO = 19;

	/**
	 * The number of structural features of the '<em>Line Code</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_CODE_FEATURE_COUNT = 20;

	/**
	 * The meta object id for the '{@link dss.general.impl.LineGeometryImpl <em>Line Geometry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.LineGeometryImpl
	 * @see dss.general.impl.GeneralPackageImpl#getLineGeometry()
	 * @generated
	 */
	int LINE_GEOMETRY = 3;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__NCONDS = 0;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__NPHASES = 1;

	/**
	 * The feature id for the '<em><b>Cond</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__COND = 2;

	/**
	 * The feature id for the '<em><b>Wire</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__WIRE = 3;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__X = 4;

	/**
	 * The feature id for the '<em><b>H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__H = 5;

	/**
	 * The feature id for the '<em><b>Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__UNITS = 6;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__NORM_AMPS = 7;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__EMERG_AMPS = 8;

	/**
	 * The feature id for the '<em><b>Reduce</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY__REDUCE = 9;

	/**
	 * The number of structural features of the '<em>Line Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_GEOMETRY_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link dss.general.impl.LoadShapeImpl <em>Load Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.LoadShapeImpl
	 * @see dss.general.impl.GeneralPackageImpl#getLoadShape()
	 * @generated
	 */
	int LOAD_SHAPE = 4;

	/**
	 * The feature id for the '<em><b>NPts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__NPTS = 0;

	/**
	 * The feature id for the '<em><b>Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__INTERVAL = 1;

	/**
	 * The feature id for the '<em><b>Mult</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__MULT = 2;

	/**
	 * The feature id for the '<em><b>Hour</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__HOUR = 3;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__MEAN = 4;

	/**
	 * The feature id for the '<em><b>Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__STD_DEV = 5;

	/**
	 * The feature id for the '<em><b>Csv File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__CSV_FILE = 6;

	/**
	 * The feature id for the '<em><b>Sng File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__SNG_FILE = 7;

	/**
	 * The feature id for the '<em><b>Dbl File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__DBL_FILE = 8;

	/**
	 * The feature id for the '<em><b>QMult</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE__QMULT = 9;

	/**
	 * The number of structural features of the '<em>Load Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SHAPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link dss.general.impl.SpectrumImpl <em>Spectrum</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.SpectrumImpl
	 * @see dss.general.impl.GeneralPackageImpl#getSpectrum()
	 * @generated
	 */
	int SPECTRUM = 5;

	/**
	 * The feature id for the '<em><b>NHarm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECTRUM__NHARM = 0;

	/**
	 * The feature id for the '<em><b>Harmonic</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECTRUM__HARMONIC = 1;

	/**
	 * The feature id for the '<em><b>Pct Mag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECTRUM__PCT_MAG = 2;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECTRUM__ANGLE = 3;

	/**
	 * The feature id for the '<em><b>Csv File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECTRUM__CSV_FILE = 4;

	/**
	 * The number of structural features of the '<em>Spectrum</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECTRUM_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link dss.general.impl.TimeCurrentCurveImpl <em>Time Current Curve</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.TimeCurrentCurveImpl
	 * @see dss.general.impl.GeneralPackageImpl#getTimeCurrentCurve()
	 * @generated
	 */
	int TIME_CURRENT_CURVE = 6;

	/**
	 * The feature id for the '<em><b>NPts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CURRENT_CURVE__NPTS = 0;

	/**
	 * The feature id for the '<em><b>CArray</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CURRENT_CURVE__CARRAY = 1;

	/**
	 * The feature id for the '<em><b>TArray</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CURRENT_CURVE__TARRAY = 2;

	/**
	 * The number of structural features of the '<em>Time Current Curve</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CURRENT_CURVE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link dss.general.impl.WireDataImpl <em>Wire Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.general.impl.WireDataImpl
	 * @see dss.general.impl.GeneralPackageImpl#getWireData()
	 * @generated
	 */
	int WIRE_DATA = 7;

	/**
	 * The feature id for the '<em><b>RDC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__RDC = 0;

	/**
	 * The feature id for the '<em><b>RAC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__RAC = 1;

	/**
	 * The feature id for the '<em><b>RUnits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__RUNITS = 2;

	/**
	 * The feature id for the '<em><b>Gmr AC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__GMR_AC = 3;

	/**
	 * The feature id for the '<em><b>Gmr Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__GMR_UNITS = 4;

	/**
	 * The feature id for the '<em><b>Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__RADIUS = 5;

	/**
	 * The feature id for the '<em><b>Rad Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__RAD_UNITS = 6;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__NORM_AMPS = 7;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__EMERG_AMPS = 8;

	/**
	 * The feature id for the '<em><b>Diameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA__DIAMETER = 9;

	/**
	 * The number of structural features of the '<em>Wire Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_DATA_FEATURE_COUNT = 10;


	/**
	 * Returns the meta object for class '{@link dss.general.DSSObject <em>DSS Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DSS Object</em>'.
	 * @see dss.general.DSSObject
	 * @generated
	 */
	EClass getDSSObject();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.DSSObject#getDSSObjType <em>DSS Obj Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>DSS Obj Type</em>'.
	 * @see dss.general.DSSObject#getDSSObjType()
	 * @see #getDSSObject()
	 * @generated
	 */
	EAttribute getDSSObject_DSSObjType();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.DSSObject#getDSSClassName <em>DSS Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>DSS Class Name</em>'.
	 * @see dss.general.DSSObject#getDSSClassName()
	 * @see #getDSSObject()
	 * @generated
	 */
	EAttribute getDSSObject_DSSClassName();

	/**
	 * Returns the meta object for the reference list '{@link dss.general.DSSObject#getParentClass <em>Parent Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Class</em>'.
	 * @see dss.general.DSSObject#getParentClass()
	 * @see #getDSSObject()
	 * @generated
	 */
	EReference getDSSObject_ParentClass();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.DSSObject#getClassIndex <em>Class Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Index</em>'.
	 * @see dss.general.DSSObject#getClassIndex()
	 * @see #getDSSObject()
	 * @generated
	 */
	EAttribute getDSSObject_ClassIndex();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.DSSObject#isDirty <em>Dirty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dirty</em>'.
	 * @see dss.general.DSSObject#isDirty()
	 * @see #getDSSObject()
	 * @generated
	 */
	EAttribute getDSSObject_Dirty();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.DSSObject#isFlag <em>Flag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flag</em>'.
	 * @see dss.general.DSSObject#isFlag()
	 * @see #getDSSObject()
	 * @generated
	 */
	EAttribute getDSSObject_Flag();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.DSSObject#getPropSequence <em>Prop Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Prop Sequence</em>'.
	 * @see dss.general.DSSObject#getPropSequence()
	 * @see #getDSSObject()
	 * @generated
	 */
	EAttribute getDSSObject_PropSequence();

	/**
	 * Returns the meta object for class '{@link dss.general.GrowthShape <em>Growth Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Growth Shape</em>'.
	 * @see dss.general.GrowthShape
	 * @generated
	 */
	EClass getGrowthShape();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.GrowthShape#getNPts <em>NPts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPts</em>'.
	 * @see dss.general.GrowthShape#getNPts()
	 * @see #getGrowthShape()
	 * @generated
	 */
	EAttribute getGrowthShape_NPts();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.GrowthShape#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Year</em>'.
	 * @see dss.general.GrowthShape#getYear()
	 * @see #getGrowthShape()
	 * @generated
	 */
	EAttribute getGrowthShape_Year();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.GrowthShape#getCsvFile <em>Csv File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Csv File</em>'.
	 * @see dss.general.GrowthShape#getCsvFile()
	 * @see #getGrowthShape()
	 * @generated
	 */
	EAttribute getGrowthShape_CsvFile();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.GrowthShape#getSngFile <em>Sng File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sng File</em>'.
	 * @see dss.general.GrowthShape#getSngFile()
	 * @see #getGrowthShape()
	 * @generated
	 */
	EAttribute getGrowthShape_SngFile();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.GrowthShape#getDblFile <em>Dbl File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dbl File</em>'.
	 * @see dss.general.GrowthShape#getDblFile()
	 * @see #getGrowthShape()
	 * @generated
	 */
	EAttribute getGrowthShape_DblFile();

	/**
	 * Returns the meta object for class '{@link dss.general.LineCode <em>Line Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Code</em>'.
	 * @see dss.general.LineCode
	 * @generated
	 */
	EClass getLineCode();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getNPhases <em>NPhases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPhases</em>'.
	 * @see dss.general.LineCode#getNPhases()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_NPhases();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getR1 <em>R1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R1</em>'.
	 * @see dss.general.LineCode#getR1()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_R1();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getX1 <em>X1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X1</em>'.
	 * @see dss.general.LineCode#getX1()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_X1();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getX0 <em>X0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X0</em>'.
	 * @see dss.general.LineCode#getX0()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_X0();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getC1 <em>C1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>C1</em>'.
	 * @see dss.general.LineCode#getC1()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_C1();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getC0 <em>C0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>C0</em>'.
	 * @see dss.general.LineCode#getC0()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_C0();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getUnits <em>Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Units</em>'.
	 * @see dss.general.LineCode#getUnits()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_Units();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getRMatrix <em>RMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RMatrix</em>'.
	 * @see dss.general.LineCode#getRMatrix()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_RMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getXMatrix <em>XMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XMatrix</em>'.
	 * @see dss.general.LineCode#getXMatrix()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_XMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getCMatrix <em>CMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CMatrix</em>'.
	 * @see dss.general.LineCode#getCMatrix()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_CMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getBaseFreq <em>Base Freq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Freq</em>'.
	 * @see dss.general.LineCode#getBaseFreq()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_BaseFreq();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getNormAmps <em>Norm Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm Amps</em>'.
	 * @see dss.general.LineCode#getNormAmps()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_NormAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getEmergAmps <em>Emerg Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg Amps</em>'.
	 * @see dss.general.LineCode#getEmergAmps()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_EmergAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getFaultRate <em>Fault Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Rate</em>'.
	 * @see dss.general.LineCode#getFaultRate()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_FaultRate();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getPctPerm <em>Pct Perm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Perm</em>'.
	 * @see dss.general.LineCode#getPctPerm()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_PctPerm();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getRepair <em>Repair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repair</em>'.
	 * @see dss.general.LineCode#getRepair()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_Repair();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#isKron <em>Kron</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kron</em>'.
	 * @see dss.general.LineCode#isKron()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_Kron();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getRg <em>Rg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rg</em>'.
	 * @see dss.general.LineCode#getRg()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_Rg();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getXg <em>Xg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xg</em>'.
	 * @see dss.general.LineCode#getXg()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_Xg();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineCode#getRho <em>Rho</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rho</em>'.
	 * @see dss.general.LineCode#getRho()
	 * @see #getLineCode()
	 * @generated
	 */
	EAttribute getLineCode_Rho();

	/**
	 * Returns the meta object for class '{@link dss.general.LineGeometry <em>Line Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Geometry</em>'.
	 * @see dss.general.LineGeometry
	 * @generated
	 */
	EClass getLineGeometry();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getNConds <em>NConds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NConds</em>'.
	 * @see dss.general.LineGeometry#getNConds()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_NConds();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getNPhases <em>NPhases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPhases</em>'.
	 * @see dss.general.LineGeometry#getNPhases()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_NPhases();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getCond <em>Cond</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cond</em>'.
	 * @see dss.general.LineGeometry#getCond()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_Cond();

	/**
	 * Returns the meta object for the reference '{@link dss.general.LineGeometry#getWire <em>Wire</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Wire</em>'.
	 * @see dss.general.LineGeometry#getWire()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EReference getLineGeometry_Wire();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see dss.general.LineGeometry#getX()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_X();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getH <em>H</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>H</em>'.
	 * @see dss.general.LineGeometry#getH()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_H();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getUnits <em>Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Units</em>'.
	 * @see dss.general.LineGeometry#getUnits()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_Units();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getNormAmps <em>Norm Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm Amps</em>'.
	 * @see dss.general.LineGeometry#getNormAmps()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_NormAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#getEmergAmps <em>Emerg Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg Amps</em>'.
	 * @see dss.general.LineGeometry#getEmergAmps()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_EmergAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LineGeometry#isReduce <em>Reduce</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reduce</em>'.
	 * @see dss.general.LineGeometry#isReduce()
	 * @see #getLineGeometry()
	 * @generated
	 */
	EAttribute getLineGeometry_Reduce();

	/**
	 * Returns the meta object for class '{@link dss.general.LoadShape <em>Load Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load Shape</em>'.
	 * @see dss.general.LoadShape
	 * @generated
	 */
	EClass getLoadShape();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getNPts <em>NPts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPts</em>'.
	 * @see dss.general.LoadShape#getNPts()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_NPts();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getInterval <em>Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interval</em>'.
	 * @see dss.general.LoadShape#getInterval()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_Interval();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.LoadShape#getMult <em>Mult</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mult</em>'.
	 * @see dss.general.LoadShape#getMult()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_Mult();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.LoadShape#getHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Hour</em>'.
	 * @see dss.general.LoadShape#getHour()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_Hour();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see dss.general.LoadShape#getMean()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_Mean();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getStdDev <em>Std Dev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Std Dev</em>'.
	 * @see dss.general.LoadShape#getStdDev()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_StdDev();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getCsvFile <em>Csv File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Csv File</em>'.
	 * @see dss.general.LoadShape#getCsvFile()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_CsvFile();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getSngFile <em>Sng File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sng File</em>'.
	 * @see dss.general.LoadShape#getSngFile()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_SngFile();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.LoadShape#getDblFile <em>Dbl File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dbl File</em>'.
	 * @see dss.general.LoadShape#getDblFile()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_DblFile();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.LoadShape#getQMult <em>QMult</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>QMult</em>'.
	 * @see dss.general.LoadShape#getQMult()
	 * @see #getLoadShape()
	 * @generated
	 */
	EAttribute getLoadShape_QMult();

	/**
	 * Returns the meta object for class '{@link dss.general.Spectrum <em>Spectrum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Spectrum</em>'.
	 * @see dss.general.Spectrum
	 * @generated
	 */
	EClass getSpectrum();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.Spectrum#getNHarm <em>NHarm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NHarm</em>'.
	 * @see dss.general.Spectrum#getNHarm()
	 * @see #getSpectrum()
	 * @generated
	 */
	EAttribute getSpectrum_NHarm();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.Spectrum#getHarmonic <em>Harmonic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Harmonic</em>'.
	 * @see dss.general.Spectrum#getHarmonic()
	 * @see #getSpectrum()
	 * @generated
	 */
	EAttribute getSpectrum_Harmonic();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.Spectrum#getPctMag <em>Pct Mag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Mag</em>'.
	 * @see dss.general.Spectrum#getPctMag()
	 * @see #getSpectrum()
	 * @generated
	 */
	EAttribute getSpectrum_PctMag();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.Spectrum#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Angle</em>'.
	 * @see dss.general.Spectrum#getAngle()
	 * @see #getSpectrum()
	 * @generated
	 */
	EAttribute getSpectrum_Angle();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.Spectrum#getCsvFile <em>Csv File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Csv File</em>'.
	 * @see dss.general.Spectrum#getCsvFile()
	 * @see #getSpectrum()
	 * @generated
	 */
	EAttribute getSpectrum_CsvFile();

	/**
	 * Returns the meta object for class '{@link dss.general.TimeCurrentCurve <em>Time Current Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Current Curve</em>'.
	 * @see dss.general.TimeCurrentCurve
	 * @generated
	 */
	EClass getTimeCurrentCurve();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.TimeCurrentCurve#getNPts <em>NPts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPts</em>'.
	 * @see dss.general.TimeCurrentCurve#getNPts()
	 * @see #getTimeCurrentCurve()
	 * @generated
	 */
	EAttribute getTimeCurrentCurve_NPts();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.TimeCurrentCurve#getCArray <em>CArray</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>CArray</em>'.
	 * @see dss.general.TimeCurrentCurve#getCArray()
	 * @see #getTimeCurrentCurve()
	 * @generated
	 */
	EAttribute getTimeCurrentCurve_CArray();

	/**
	 * Returns the meta object for the attribute list '{@link dss.general.TimeCurrentCurve#getTArray <em>TArray</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>TArray</em>'.
	 * @see dss.general.TimeCurrentCurve#getTArray()
	 * @see #getTimeCurrentCurve()
	 * @generated
	 */
	EAttribute getTimeCurrentCurve_TArray();

	/**
	 * Returns the meta object for class '{@link dss.general.WireData <em>Wire Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wire Data</em>'.
	 * @see dss.general.WireData
	 * @generated
	 */
	EClass getWireData();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getRDC <em>RDC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RDC</em>'.
	 * @see dss.general.WireData#getRDC()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_RDC();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getRAC <em>RAC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RAC</em>'.
	 * @see dss.general.WireData#getRAC()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_RAC();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getRUnits <em>RUnits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RUnits</em>'.
	 * @see dss.general.WireData#getRUnits()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_RUnits();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getGmrAC <em>Gmr AC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gmr AC</em>'.
	 * @see dss.general.WireData#getGmrAC()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_GmrAC();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getGmrUnits <em>Gmr Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gmr Units</em>'.
	 * @see dss.general.WireData#getGmrUnits()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_GmrUnits();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getRadius <em>Radius</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Radius</em>'.
	 * @see dss.general.WireData#getRadius()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_Radius();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getRadUnits <em>Rad Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rad Units</em>'.
	 * @see dss.general.WireData#getRadUnits()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_RadUnits();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getNormAmps <em>Norm Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm Amps</em>'.
	 * @see dss.general.WireData#getNormAmps()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_NormAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getEmergAmps <em>Emerg Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg Amps</em>'.
	 * @see dss.general.WireData#getEmergAmps()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_EmergAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.general.WireData#getDiameter <em>Diameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Diameter</em>'.
	 * @see dss.general.WireData#getDiameter()
	 * @see #getWireData()
	 * @generated
	 */
	EAttribute getWireData_Diameter();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeneralFactory getGeneralFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link dss.general.impl.DSSObjectImpl <em>DSS Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.DSSObjectImpl
		 * @see dss.general.impl.GeneralPackageImpl#getDSSObject()
		 * @generated
		 */
		EClass DSS_OBJECT = eINSTANCE.getDSSObject();

		/**
		 * The meta object literal for the '<em><b>DSS Obj Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSS_OBJECT__DSS_OBJ_TYPE = eINSTANCE.getDSSObject_DSSObjType();

		/**
		 * The meta object literal for the '<em><b>DSS Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSS_OBJECT__DSS_CLASS_NAME = eINSTANCE.getDSSObject_DSSClassName();

		/**
		 * The meta object literal for the '<em><b>Parent Class</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DSS_OBJECT__PARENT_CLASS = eINSTANCE.getDSSObject_ParentClass();

		/**
		 * The meta object literal for the '<em><b>Class Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSS_OBJECT__CLASS_INDEX = eINSTANCE.getDSSObject_ClassIndex();

		/**
		 * The meta object literal for the '<em><b>Dirty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSS_OBJECT__DIRTY = eINSTANCE.getDSSObject_Dirty();

		/**
		 * The meta object literal for the '<em><b>Flag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSS_OBJECT__FLAG = eINSTANCE.getDSSObject_Flag();

		/**
		 * The meta object literal for the '<em><b>Prop Sequence</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSS_OBJECT__PROP_SEQUENCE = eINSTANCE.getDSSObject_PropSequence();

		/**
		 * The meta object literal for the '{@link dss.general.impl.GrowthShapeImpl <em>Growth Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.GrowthShapeImpl
		 * @see dss.general.impl.GeneralPackageImpl#getGrowthShape()
		 * @generated
		 */
		EClass GROWTH_SHAPE = eINSTANCE.getGrowthShape();

		/**
		 * The meta object literal for the '<em><b>NPts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROWTH_SHAPE__NPTS = eINSTANCE.getGrowthShape_NPts();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROWTH_SHAPE__YEAR = eINSTANCE.getGrowthShape_Year();

		/**
		 * The meta object literal for the '<em><b>Csv File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROWTH_SHAPE__CSV_FILE = eINSTANCE.getGrowthShape_CsvFile();

		/**
		 * The meta object literal for the '<em><b>Sng File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROWTH_SHAPE__SNG_FILE = eINSTANCE.getGrowthShape_SngFile();

		/**
		 * The meta object literal for the '<em><b>Dbl File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROWTH_SHAPE__DBL_FILE = eINSTANCE.getGrowthShape_DblFile();

		/**
		 * The meta object literal for the '{@link dss.general.impl.LineCodeImpl <em>Line Code</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.LineCodeImpl
		 * @see dss.general.impl.GeneralPackageImpl#getLineCode()
		 * @generated
		 */
		EClass LINE_CODE = eINSTANCE.getLineCode();

		/**
		 * The meta object literal for the '<em><b>NPhases</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__NPHASES = eINSTANCE.getLineCode_NPhases();

		/**
		 * The meta object literal for the '<em><b>R1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__R1 = eINSTANCE.getLineCode_R1();

		/**
		 * The meta object literal for the '<em><b>X1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__X1 = eINSTANCE.getLineCode_X1();

		/**
		 * The meta object literal for the '<em><b>X0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__X0 = eINSTANCE.getLineCode_X0();

		/**
		 * The meta object literal for the '<em><b>C1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__C1 = eINSTANCE.getLineCode_C1();

		/**
		 * The meta object literal for the '<em><b>C0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__C0 = eINSTANCE.getLineCode_C0();

		/**
		 * The meta object literal for the '<em><b>Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__UNITS = eINSTANCE.getLineCode_Units();

		/**
		 * The meta object literal for the '<em><b>RMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__RMATRIX = eINSTANCE.getLineCode_RMatrix();

		/**
		 * The meta object literal for the '<em><b>XMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__XMATRIX = eINSTANCE.getLineCode_XMatrix();

		/**
		 * The meta object literal for the '<em><b>CMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__CMATRIX = eINSTANCE.getLineCode_CMatrix();

		/**
		 * The meta object literal for the '<em><b>Base Freq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__BASE_FREQ = eINSTANCE.getLineCode_BaseFreq();

		/**
		 * The meta object literal for the '<em><b>Norm Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__NORM_AMPS = eINSTANCE.getLineCode_NormAmps();

		/**
		 * The meta object literal for the '<em><b>Emerg Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__EMERG_AMPS = eINSTANCE.getLineCode_EmergAmps();

		/**
		 * The meta object literal for the '<em><b>Fault Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__FAULT_RATE = eINSTANCE.getLineCode_FaultRate();

		/**
		 * The meta object literal for the '<em><b>Pct Perm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__PCT_PERM = eINSTANCE.getLineCode_PctPerm();

		/**
		 * The meta object literal for the '<em><b>Repair</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__REPAIR = eINSTANCE.getLineCode_Repair();

		/**
		 * The meta object literal for the '<em><b>Kron</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__KRON = eINSTANCE.getLineCode_Kron();

		/**
		 * The meta object literal for the '<em><b>Rg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__RG = eINSTANCE.getLineCode_Rg();

		/**
		 * The meta object literal for the '<em><b>Xg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__XG = eINSTANCE.getLineCode_Xg();

		/**
		 * The meta object literal for the '<em><b>Rho</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_CODE__RHO = eINSTANCE.getLineCode_Rho();

		/**
		 * The meta object literal for the '{@link dss.general.impl.LineGeometryImpl <em>Line Geometry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.LineGeometryImpl
		 * @see dss.general.impl.GeneralPackageImpl#getLineGeometry()
		 * @generated
		 */
		EClass LINE_GEOMETRY = eINSTANCE.getLineGeometry();

		/**
		 * The meta object literal for the '<em><b>NConds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__NCONDS = eINSTANCE.getLineGeometry_NConds();

		/**
		 * The meta object literal for the '<em><b>NPhases</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__NPHASES = eINSTANCE.getLineGeometry_NPhases();

		/**
		 * The meta object literal for the '<em><b>Cond</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__COND = eINSTANCE.getLineGeometry_Cond();

		/**
		 * The meta object literal for the '<em><b>Wire</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINE_GEOMETRY__WIRE = eINSTANCE.getLineGeometry_Wire();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__X = eINSTANCE.getLineGeometry_X();

		/**
		 * The meta object literal for the '<em><b>H</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__H = eINSTANCE.getLineGeometry_H();

		/**
		 * The meta object literal for the '<em><b>Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__UNITS = eINSTANCE.getLineGeometry_Units();

		/**
		 * The meta object literal for the '<em><b>Norm Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__NORM_AMPS = eINSTANCE.getLineGeometry_NormAmps();

		/**
		 * The meta object literal for the '<em><b>Emerg Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__EMERG_AMPS = eINSTANCE.getLineGeometry_EmergAmps();

		/**
		 * The meta object literal for the '<em><b>Reduce</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_GEOMETRY__REDUCE = eINSTANCE.getLineGeometry_Reduce();

		/**
		 * The meta object literal for the '{@link dss.general.impl.LoadShapeImpl <em>Load Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.LoadShapeImpl
		 * @see dss.general.impl.GeneralPackageImpl#getLoadShape()
		 * @generated
		 */
		EClass LOAD_SHAPE = eINSTANCE.getLoadShape();

		/**
		 * The meta object literal for the '<em><b>NPts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__NPTS = eINSTANCE.getLoadShape_NPts();

		/**
		 * The meta object literal for the '<em><b>Interval</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__INTERVAL = eINSTANCE.getLoadShape_Interval();

		/**
		 * The meta object literal for the '<em><b>Mult</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__MULT = eINSTANCE.getLoadShape_Mult();

		/**
		 * The meta object literal for the '<em><b>Hour</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__HOUR = eINSTANCE.getLoadShape_Hour();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__MEAN = eINSTANCE.getLoadShape_Mean();

		/**
		 * The meta object literal for the '<em><b>Std Dev</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__STD_DEV = eINSTANCE.getLoadShape_StdDev();

		/**
		 * The meta object literal for the '<em><b>Csv File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__CSV_FILE = eINSTANCE.getLoadShape_CsvFile();

		/**
		 * The meta object literal for the '<em><b>Sng File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__SNG_FILE = eINSTANCE.getLoadShape_SngFile();

		/**
		 * The meta object literal for the '<em><b>Dbl File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__DBL_FILE = eINSTANCE.getLoadShape_DblFile();

		/**
		 * The meta object literal for the '<em><b>QMult</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SHAPE__QMULT = eINSTANCE.getLoadShape_QMult();

		/**
		 * The meta object literal for the '{@link dss.general.impl.SpectrumImpl <em>Spectrum</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.SpectrumImpl
		 * @see dss.general.impl.GeneralPackageImpl#getSpectrum()
		 * @generated
		 */
		EClass SPECTRUM = eINSTANCE.getSpectrum();

		/**
		 * The meta object literal for the '<em><b>NHarm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECTRUM__NHARM = eINSTANCE.getSpectrum_NHarm();

		/**
		 * The meta object literal for the '<em><b>Harmonic</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECTRUM__HARMONIC = eINSTANCE.getSpectrum_Harmonic();

		/**
		 * The meta object literal for the '<em><b>Pct Mag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECTRUM__PCT_MAG = eINSTANCE.getSpectrum_PctMag();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECTRUM__ANGLE = eINSTANCE.getSpectrum_Angle();

		/**
		 * The meta object literal for the '<em><b>Csv File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECTRUM__CSV_FILE = eINSTANCE.getSpectrum_CsvFile();

		/**
		 * The meta object literal for the '{@link dss.general.impl.TimeCurrentCurveImpl <em>Time Current Curve</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.TimeCurrentCurveImpl
		 * @see dss.general.impl.GeneralPackageImpl#getTimeCurrentCurve()
		 * @generated
		 */
		EClass TIME_CURRENT_CURVE = eINSTANCE.getTimeCurrentCurve();

		/**
		 * The meta object literal for the '<em><b>NPts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_CURRENT_CURVE__NPTS = eINSTANCE.getTimeCurrentCurve_NPts();

		/**
		 * The meta object literal for the '<em><b>CArray</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_CURRENT_CURVE__CARRAY = eINSTANCE.getTimeCurrentCurve_CArray();

		/**
		 * The meta object literal for the '<em><b>TArray</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_CURRENT_CURVE__TARRAY = eINSTANCE.getTimeCurrentCurve_TArray();

		/**
		 * The meta object literal for the '{@link dss.general.impl.WireDataImpl <em>Wire Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.general.impl.WireDataImpl
		 * @see dss.general.impl.GeneralPackageImpl#getWireData()
		 * @generated
		 */
		EClass WIRE_DATA = eINSTANCE.getWireData();

		/**
		 * The meta object literal for the '<em><b>RDC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__RDC = eINSTANCE.getWireData_RDC();

		/**
		 * The meta object literal for the '<em><b>RAC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__RAC = eINSTANCE.getWireData_RAC();

		/**
		 * The meta object literal for the '<em><b>RUnits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__RUNITS = eINSTANCE.getWireData_RUnits();

		/**
		 * The meta object literal for the '<em><b>Gmr AC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__GMR_AC = eINSTANCE.getWireData_GmrAC();

		/**
		 * The meta object literal for the '<em><b>Gmr Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__GMR_UNITS = eINSTANCE.getWireData_GmrUnits();

		/**
		 * The meta object literal for the '<em><b>Radius</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__RADIUS = eINSTANCE.getWireData_Radius();

		/**
		 * The meta object literal for the '<em><b>Rad Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__RAD_UNITS = eINSTANCE.getWireData_RadUnits();

		/**
		 * The meta object literal for the '<em><b>Norm Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__NORM_AMPS = eINSTANCE.getWireData_NormAmps();

		/**
		 * The meta object literal for the '<em><b>Emerg Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__EMERG_AMPS = eINSTANCE.getWireData_EmergAmps();

		/**
		 * The meta object literal for the '<em><b>Diameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIRE_DATA__DIAMETER = eINSTANCE.getWireData_Diameter();

	}

} //GeneralPackage
