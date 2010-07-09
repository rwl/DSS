/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general;

import electrickery.common.Named;
import electrickery.common.lengthUnit;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wire Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The WireData object is a general DSS object used by all circuits
 * as a reference for obtaining line impedances.
 * 
 * This class of data defines the raw conductor data that is used to compute
 * the impedance for a line geometry.
 * 
 * Note that you can use whatever units you want for any of the dimensional
 * data - be sure to declare the units. Otherwise, the units are all assumed
 * to match, which would be very rare for conductor data.  Conductor data is
 * usually supplied in a hodge-podge of units. Everything is converted to
 * meters internally to the DSS.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.general.WireData#getRDC <em>RDC</em>}</li>
 *   <li>{@link electrickery.general.WireData#getRAC <em>RAC</em>}</li>
 *   <li>{@link electrickery.general.WireData#getRUnits <em>RUnits</em>}</li>
 *   <li>{@link electrickery.general.WireData#getGmrAC <em>Gmr AC</em>}</li>
 *   <li>{@link electrickery.general.WireData#getGmrUnits <em>Gmr Units</em>}</li>
 *   <li>{@link electrickery.general.WireData#getRadius <em>Radius</em>}</li>
 *   <li>{@link electrickery.general.WireData#getRadUnits <em>Rad Units</em>}</li>
 *   <li>{@link electrickery.general.WireData#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link electrickery.general.WireData#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link electrickery.general.WireData#getDiameter <em>Diameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.general.GeneralPackage#getWireData()
 * @model
 * @generated
 */
public interface WireData extends Named {
	/**
	 * Returns the value of the '<em><b>RDC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * DC resistance, ohms per unit length (see rUnits). Defaults to rAC if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RDC</em>' attribute.
	 * @see #setRDC(double)
	 * @see electrickery.general.GeneralPackage#getWireData_RDC()
	 * @model
	 * @generated
	 */
	double getRDC();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getRDC <em>RDC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RDC</em>' attribute.
	 * @see #getRDC()
	 * @generated
	 */
	void setRDC(double value);

	/**
	 * Returns the value of the '<em><b>RAC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resistance at 60 Hz per unit length. Defaults to rDC if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RAC</em>' attribute.
	 * @see #setRAC(double)
	 * @see electrickery.general.GeneralPackage#getWireData_RAC()
	 * @model
	 * @generated
	 */
	double getRAC();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getRAC <em>RAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RAC</em>' attribute.
	 * @see #getRAC()
	 * @generated
	 */
	void setRAC(double value);

	/**
	 * Returns the value of the '<em><b>RUnits</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.lengthUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Length units for resistance: ohms per {mi|kft|km|m|Ft|in|cm}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RUnits</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #setRUnits(lengthUnit)
	 * @see electrickery.general.GeneralPackage#getWireData_RUnits()
	 * @model
	 * @generated
	 */
	lengthUnit getRUnits();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getRUnits <em>RUnits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RUnits</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #getRUnits()
	 * @generated
	 */
	void setRUnits(lengthUnit value);

	/**
	 * Returns the value of the '<em><b>Gmr AC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * GMR at 60 Hz. Defaults to .7788*radius if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gmr AC</em>' attribute.
	 * @see #setGmrAC(double)
	 * @see electrickery.general.GeneralPackage#getWireData_GmrAC()
	 * @model
	 * @generated
	 */
	double getGmrAC();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getGmrAC <em>Gmr AC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gmr AC</em>' attribute.
	 * @see #getGmrAC()
	 * @generated
	 */
	void setGmrAC(double value);

	/**
	 * Returns the value of the '<em><b>Gmr Units</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.lengthUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Units for GMR: {mi|kft|km|m|Ft|in|cm}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gmr Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #setGmrUnits(lengthUnit)
	 * @see electrickery.general.GeneralPackage#getWireData_GmrUnits()
	 * @model
	 * @generated
	 */
	lengthUnit getGmrUnits();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getGmrUnits <em>Gmr Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gmr Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #getGmrUnits()
	 * @generated
	 */
	void setGmrUnits(lengthUnit value);

	/**
	 * Returns the value of the '<em><b>Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Outside radius of conductor. Defaults to GMR/0.7788 if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Radius</em>' attribute.
	 * @see #setRadius(double)
	 * @see electrickery.general.GeneralPackage#getWireData_Radius()
	 * @model
	 * @generated
	 */
	double getRadius();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getRadius <em>Radius</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Radius</em>' attribute.
	 * @see #getRadius()
	 * @generated
	 */
	void setRadius(double value);

	/**
	 * Returns the value of the '<em><b>Rad Units</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.lengthUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Units for outside radius: {mi|kft|km|m|Ft|in|cm}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rad Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #setRadUnits(lengthUnit)
	 * @see electrickery.general.GeneralPackage#getWireData_RadUnits()
	 * @model
	 * @generated
	 */
	lengthUnit getRadUnits();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getRadUnits <em>Rad Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rad Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #getRadUnits()
	 * @generated
	 */
	void setRadUnits(lengthUnit value);

	/**
	 * Returns the value of the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Normal ampacity, amperes. Defaults to Emergency amps/1.5 if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm Amps</em>' attribute.
	 * @see #setNormAmps(double)
	 * @see electrickery.general.GeneralPackage#getWireData_NormAmps()
	 * @model
	 * @generated
	 */
	double getNormAmps();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getNormAmps <em>Norm Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Norm Amps</em>' attribute.
	 * @see #getNormAmps()
	 * @generated
	 */
	void setNormAmps(double value);

	/**
	 * Returns the value of the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Emergency ampacity, amperes. Defaults to 1.5 * Normal Amps if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg Amps</em>' attribute.
	 * @see #setEmergAmps(double)
	 * @see electrickery.general.GeneralPackage#getWireData_EmergAmps()
	 * @model
	 * @generated
	 */
	double getEmergAmps();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getEmergAmps <em>Emerg Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg Amps</em>' attribute.
	 * @see #getEmergAmps()
	 * @generated
	 */
	void setEmergAmps(double value);

	/**
	 * Returns the value of the '<em><b>Diameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Diameter; Alternative method for entering radius.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Diameter</em>' attribute.
	 * @see #setDiameter(double)
	 * @see electrickery.general.GeneralPackage#getWireData_Diameter()
	 * @model
	 * @generated
	 */
	double getDiameter();

	/**
	 * Sets the value of the '{@link electrickery.general.WireData#getDiameter <em>Diameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diameter</em>' attribute.
	 * @see #getDiameter()
	 * @generated
	 */
	void setDiameter(double value);

} // WireData
