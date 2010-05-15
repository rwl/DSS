/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Load Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The LoadShape object is a general DSS object used by all circuits
 * as a reference for obtaining yearly, daily, and other load shapes.
 * 
 * Loadshapes default to fixed interval data.  If the Interval is specified to
 * be 0.0, then both time and multiplier data are expected.  If the Interval
 * is  greater than 0.0, the user specifies only the multipliers.  The Hour
 * command is ignored and the files are assumed to contain only the multiplier
 * data.
 * 
 * The user may place the data in CSV or binary files as well as passing
 * through the command interface. Obviously, for large amounts of data such as
 * 8760 load curves, the command interface is cumbersome.  CSV files are text
 * separated by commas, one interval to a line. There are two binary formats
 * permitted: 1) a file of Singles; 2) a file of Doubles.
 * 
 * For fixed interval data, only the multiplier is expected.  Therefore, the
 * CSV format would contain only one number per line.  The two binary formats
 * are packed.
 * 
 * For variable interval data, (hour, multiplier) pairs are expected in both
 * formats.
 * 
 * The Mean and Std Deviation are automatically computed when a new series of
 * points is entered.
 * 
 * The data may also be entered in unnormalized form.  The normalize=Yes
 * command will force normalization.  That is, the multipliers are scaled so
 * that the maximum value is 1.0.
 * 
 * 
 * A LoadShape object consists of a series of multipliers, nominally ranging
 * from 0.0 to 1.0 that are applied to the base kW values of the load to
 * represent variation of the load over some time period.
 * 
 * Load shapes are generally fixed interval, but may also be variable
 * interval.  For the latter, both the time, hr, and the multiplier must be
 * specified.
 * 
 * All loadshapes, whether they be daily, yearly, or some arbitrary duty
 * cycle, are maintained in this class.  Each load simply refers to the
 * appropriate shape by name.
 * 
 * The loadshape arrays may be entered directly in command line, or the load
 * shapes may be stored in one of three different types of files from which
 * the shapes are loaded into memory.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.general.LoadShape#getNPts <em>NPts</em>}</li>
 *   <li>{@link dss.general.LoadShape#getInterval <em>Interval</em>}</li>
 *   <li>{@link dss.general.LoadShape#getMult <em>Mult</em>}</li>
 *   <li>{@link dss.general.LoadShape#getHour <em>Hour</em>}</li>
 *   <li>{@link dss.general.LoadShape#getMean <em>Mean</em>}</li>
 *   <li>{@link dss.general.LoadShape#getStdDev <em>Std Dev</em>}</li>
 *   <li>{@link dss.general.LoadShape#getCsvFile <em>Csv File</em>}</li>
 *   <li>{@link dss.general.LoadShape#getSngFile <em>Sng File</em>}</li>
 *   <li>{@link dss.general.LoadShape#getDblFile <em>Dbl File</em>}</li>
 *   <li>{@link dss.general.LoadShape#getQMult <em>QMult</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.general.GeneralPackage#getLoadShape()
 * @model
 * @generated
 */
public interface LoadShape extends EObject {
	/**
	 * Returns the value of the '<em><b>NPts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Max number of points to expect in load shape vectors. This gets reset to the number of multiplier values found (in files only) if less than specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NPts</em>' attribute.
	 * @see #setNPts(int)
	 * @see dss.general.GeneralPackage#getLoadShape_NPts()
	 * @model
	 * @generated
	 */
	int getNPts();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getNPts <em>NPts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPts</em>' attribute.
	 * @see #getNPts()
	 * @generated
	 */
	void setNPts(int value);

	/**
	 * Returns the value of the '<em><b>Interval</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time interval (hrs) for fixed interval data.  If set = 0 then time data (in hours) is expected using either the Hour property or input files.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interval</em>' attribute.
	 * @see #setInterval(int)
	 * @see dss.general.GeneralPackage#getLoadShape_Interval()
	 * @model default="1"
	 * @generated
	 */
	int getInterval();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getInterval <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interval</em>' attribute.
	 * @see #getInterval()
	 * @generated
	 */
	void setInterval(int value);

	/**
	 * Returns the value of the '<em><b>Mult</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of multiplier values for active power (P).  Can also use the syntax: mult = (file=filename) where the file contains one value per line. In "file=" syntax, the number of points may be altered.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mult</em>' attribute list.
	 * @see dss.general.GeneralPackage#getLoadShape_Mult()
	 * @model
	 * @generated
	 */
	EList<Double> getMult();

	/**
	 * Returns the value of the '<em><b>Hour</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of hour values. Only necessary to define for variable interval data.  If the data are fixed interval, do not use this property.  Can also use the syntax: mult = (file=filename) where the file contains one value per line.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hour</em>' attribute list.
	 * @see dss.general.GeneralPackage#getLoadShape_Hour()
	 * @model
	 * @generated
	 */
	EList<Double> getHour();

	/**
	 * Returns the value of the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mean of the active power multipliers.  Automatically computed when a curve is defined.  However, you may set it independently.  Used for Monte Carlo load simulations.
	 * 
	 * The mean and standard deviation are always computed after an array of points are entered or normalized (see below).  However, if you are doing only parametric load studies using the Monte Carlo solution mode, only the Mean and Std Deviation are required to define a loadshape.  These two values may be defined directly rather than by supplying the curve.  Of course, the multiplier points are not generated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mean</em>' attribute.
	 * @see #setMean(double)
	 * @see dss.general.GeneralPackage#getLoadShape_Mean()
	 * @model
	 * @generated
	 */
	double getMean();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getMean <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mean</em>' attribute.
	 * @see #getMean()
	 * @generated
	 */
	void setMean(double value);

	/**
	 * Returns the value of the '<em><b>Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Standard deviation of active power multipliers.  This is automatically computed when a vector or file of multipliers is entered.  However, you may set it to another value indepently.  Is overwritten if you subsequently read in a curve.  Used for Monte Carlo load simulations.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Std Dev</em>' attribute.
	 * @see #setStdDev(double)
	 * @see dss.general.GeneralPackage#getLoadShape_StdDev()
	 * @model
	 * @generated
	 */
	double getStdDev();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getStdDev <em>Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Std Dev</em>' attribute.
	 * @see #getStdDev()
	 * @generated
	 */
	void setStdDev(double value);

	/**
	 * Returns the value of the '<em><b>Csv File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Three different formats are allowed. If Interval>0 then only the multiplier is entered.  For variable interval data, set Interval=0.0 and enter both the time (in hours) and multiplier, in that order for each interval.
	 * 
	 * Switch input of active power load curve data to a csv file containing (hour, mult) points, or simply (mult) values for fixed time interval data, one per line.
	 * 
	 * NOTE: This action may reset the number of points to a lower value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Csv File</em>' attribute.
	 * @see #setCsvFile(String)
	 * @see dss.general.GeneralPackage#getLoadShape_CsvFile()
	 * @model
	 * @generated
	 */
	String getCsvFile();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getCsvFile <em>Csv File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Csv File</em>' attribute.
	 * @see #getCsvFile()
	 * @generated
	 */
	void setCsvFile(String value);

	/**
	 * Returns the value of the '<em><b>Sng File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Switch input of active power load curve data to a binary file of singles containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another.
	 * 
	 * NOTE: This action may reset the number of points to a lower value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sng File</em>' attribute.
	 * @see #setSngFile(String)
	 * @see dss.general.GeneralPackage#getLoadShape_SngFile()
	 * @model
	 * @generated
	 */
	String getSngFile();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getSngFile <em>Sng File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sng File</em>' attribute.
	 * @see #getSngFile()
	 * @generated
	 */
	void setSngFile(String value);

	/**
	 * Returns the value of the '<em><b>Dbl File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Switch input of active power load curve data to a binary file of doubles containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another.
	 * 
	 * NOTE: This action may reset the number of points to a lower value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dbl File</em>' attribute.
	 * @see #setDblFile(String)
	 * @see dss.general.GeneralPackage#getLoadShape_DblFile()
	 * @model
	 * @generated
	 */
	String getDblFile();

	/**
	 * Sets the value of the '{@link dss.general.LoadShape#getDblFile <em>Dbl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dbl File</em>' attribute.
	 * @see #getDblFile()
	 * @generated
	 */
	void setDblFile(String value);

	/**
	 * Returns the value of the '<em><b>QMult</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of multiplier values for reactive power (Q).  Can also use the syntax: qmult = (file=filename) where the file contains one value per line.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>QMult</em>' attribute list.
	 * @see dss.general.GeneralPackage#getLoadShape_QMult()
	 * @model
	 * @generated
	 */
	EList<Double> getQMult();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * After defining load curve data, setting action=normalize will modify the multipliers so that the peak is 1.0.  The mean and std deviation are recomputed.
	 * 
	 * Many times the raw load shape data is in actual kW or some other unit.  The load shapes normally will have a maximum value of 1.0.  Specifying this parameter as "Action=N" after the load shape multiplier data are imported will force the normalization of the data in memory and recalculation of the mean and standard deviation.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void normalise();

} // LoadShape
