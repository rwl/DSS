/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Growth Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The GrowthShape object is a general DSS object used by all circuits
 * as a reference for obtaining yearly growth curves.
 * 
 * A GrowthShape object is similar to a Loadshape object.  However, it is
 * intended to represent the growth in load year-by-year and the way the curve
 * is specified is entirely different.  You must enter the growth for the
 * first year.  Thereafter, only the years where there is a change must be
 * entered.  Otherwise it is assumed the growth stays the same.
 * 
 * Growth shapes are entered as multipliers for the previous year's load.  If
 * the load grows by 2.5% in a year, the multiplier is entered as 1.025.  You
 * do not need to enter subsequent years if the multiplier remains the same.
 * You need only enter the years in which the growth rate is assumed to have
 * changed.
 * 
 * The user may place the data in CSV or binary files as well as passing
 * through the command interface. The rules are the same as for LoadShapes
 * except that the year is always entered.  CSV files are text separated by
 * commas, one interval to a line. There are two binary formats permitted:
 * 1) a file of Singles; 2) a file of Doubles.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.general.GrowthShape#getNPts <em>NPts</em>}</li>
 *   <li>{@link electrickery.general.GrowthShape#getYear <em>Year</em>}</li>
 *   <li>{@link electrickery.general.GrowthShape#getCsvFile <em>Csv File</em>}</li>
 *   <li>{@link electrickery.general.GrowthShape#getSngFile <em>Sng File</em>}</li>
 *   <li>{@link electrickery.general.GrowthShape#getDblFile <em>Dbl File</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.general.GeneralPackage#getGrowthShape()
 * @model
 * @generated
 */
public interface GrowthShape extends EObject {
	/**
	 * Returns the value of the '<em><b>NPts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of points to expect in subsequent vector.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NPts</em>' attribute.
	 * @see #setNPts(int)
	 * @see electrickery.general.GeneralPackage#getGrowthShape_NPts()
	 * @model
	 * @generated
	 */
	int getNPts();

	/**
	 * Sets the value of the '{@link electrickery.general.GrowthShape#getNPts <em>NPts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPts</em>' attribute.
	 * @see #getNPts()
	 * @generated
	 */
	void setNPts(int value);

	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of year values, or a text file spec, corresponding to the multipliers.  Enter only those years where the growth changes.  May be any integer sequence -- just so it is consistent. See help on mult.  Setting the global solution variable Year=0 causes the growth factor to default to 1.0, effectively neglecting growth.  This is what you would do for all base year analyses.  You may also use the syntax:  year=(file=filename.ext) in which the array values are entered one per line in the text file referenced.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Year</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getGrowthShape_Year()
	 * @model
	 * @generated
	 */
	EList<Double> getYear();

	/**
	 * Returns the value of the '<em><b>Csv File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Switch input of growth curve data to a csv file containing (year, mult) points, one per line.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Csv File</em>' attribute.
	 * @see #setCsvFile(String)
	 * @see electrickery.general.GeneralPackage#getGrowthShape_CsvFile()
	 * @model
	 * @generated
	 */
	String getCsvFile();

	/**
	 * Sets the value of the '{@link electrickery.general.GrowthShape#getCsvFile <em>Csv File</em>}' attribute.
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
	 * Switch input of growth curve data to a binary file of singles containing (year, mult) points, packed one after another.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sng File</em>' attribute.
	 * @see #setSngFile(String)
	 * @see electrickery.general.GeneralPackage#getGrowthShape_SngFile()
	 * @model
	 * @generated
	 */
	String getSngFile();

	/**
	 * Sets the value of the '{@link electrickery.general.GrowthShape#getSngFile <em>Sng File</em>}' attribute.
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
	 * Switch input of growth curve data to a binary file of doubles containing (year, mult) points, packed one after another.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dbl File</em>' attribute.
	 * @see #setDblFile(String)
	 * @see electrickery.general.GeneralPackage#getGrowthShape_DblFile()
	 * @model
	 * @generated
	 */
	String getDblFile();

	/**
	 * Sets the value of the '{@link electrickery.general.GrowthShape#getDblFile <em>Dbl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dbl File</em>' attribute.
	 * @see #getDblFile()
	 * @generated
	 */
	void setDblFile(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get multiplier for specified year.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	double getMult(int year);

} // GrowthShape
