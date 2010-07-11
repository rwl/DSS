/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general;

import electrickery.common.Named;
import electrickery.common.lengthUnit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Line Geometry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The LineGeometry object is a general DSS object used by all circuits
 * as a reference for obtaining line impedances.
 * 
 * Defines the positions of the conductors.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.general.LineGeometry#getNConds <em>NConds</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getCond <em>Cond</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getWire <em>Wire</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getSpacing <em>Spacing</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getX <em>X</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getH <em>H</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getUnits <em>Units</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#isReduce <em>Reduce</em>}</li>
 *   <li>{@link electrickery.general.LineGeometry#getWires <em>Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.general.GeneralPackage#getLineGeometry()
 * @model
 * @generated
 */
public interface LineGeometry extends Named {
	/**
	 * Returns the value of the '<em><b>NConds</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of conductors in this geometry.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NConds</em>' attribute.
	 * @see #setNConds(int)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_NConds()
	 * @model default="3"
	 * @generated
	 */
	int getNConds();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getNConds <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NConds</em>' attribute.
	 * @see #getNConds()
	 * @generated
	 */
	void setNConds(int value);

	/**
	 * Returns the value of the '<em><b>NPhases</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of phases.  All other conductors are considered neutrals and might be reduced out.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NPhases</em>' attribute.
	 * @see #setNPhases(int)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_NPhases()
	 * @model default="3"
	 * @generated
	 */
	int getNPhases();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getNPhases <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPhases</em>' attribute.
	 * @see #getNPhases()
	 * @generated
	 */
	void setNPhases(int value);

	/**
	 * Returns the value of the '<em><b>Cond</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set this = number of the conductor you wish to define.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cond</em>' attribute.
	 * @see #setCond(int)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_Cond()
	 * @model default="1"
	 * @generated
	 */
	int getCond();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getCond <em>Cond</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cond</em>' attribute.
	 * @see #getCond()
	 * @generated
	 */
	void setCond(int value);

	/**
	 * Returns the value of the '<em><b>Wire</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wire</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wire</em>' reference.
	 * @see #setWire(WireData)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_Wire()
	 * @model required="true"
	 * @generated
	 */
	WireData getWire();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getWire <em>Wire</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wire</em>' reference.
	 * @see #getWire()
	 * @generated
	 */
	void setWire(WireData value);

	/**
	 * Returns the value of the '<em><b>Wires</b></em>' reference list.
	 * The list contents are of type {@link electrickery.general.WireData}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wires</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wires</em>' reference list.
	 * @see electrickery.general.GeneralPackage#getLineGeometry_Wires()
	 * @model
	 * @generated
	 */
	EList<WireData> getWires();

	/**
	 * Returns the value of the '<em><b>Spacing</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spacing</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spacing</em>' reference.
	 * @see #setSpacing(LineSpacing)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_Spacing()
	 * @model
	 * @generated
	 */
	LineSpacing getSpacing();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getSpacing <em>Spacing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spacing</em>' reference.
	 * @see #getSpacing()
	 * @generated
	 */
	void setSpacing(LineSpacing value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * x coordinate.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(double)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_X()
	 * @model
	 * @generated
	 */
	double getX();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(double value);

	/**
	 * Returns the value of the '<em><b>H</b></em>' attribute.
	 * The default value is <code>"32.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Height of conductor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>H</em>' attribute.
	 * @see #setH(double)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_H()
	 * @model default="32.0"
	 * @generated
	 */
	double getH();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getH <em>H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>H</em>' attribute.
	 * @see #getH()
	 * @generated
	 */
	void setH(double value);

	/**
	 * Returns the value of the '<em><b>Units</b></em>' attribute.
	 * The default value is <code>"ft"</code>.
	 * The literals are from the enumeration {@link electrickery.common.lengthUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is "ft", but defaults to last unit defined
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #setUnits(lengthUnit)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_Units()
	 * @model default="ft"
	 * @generated
	 */
	lengthUnit getUnits();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getUnits <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #getUnits()
	 * @generated
	 */
	void setUnits(lengthUnit value);

	/**
	 * Returns the value of the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Normal ampacity, amperes for the line. Defaults to first conductor if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm Amps</em>' attribute.
	 * @see #setNormAmps(double)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_NormAmps()
	 * @model
	 * @generated
	 */
	double getNormAmps();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getNormAmps <em>Norm Amps</em>}' attribute.
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
	 * Emergency ampacity, amperes. Defaults to first conductor if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg Amps</em>' attribute.
	 * @see #setEmergAmps(double)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_EmergAmps()
	 * @model
	 * @generated
	 */
	double getEmergAmps();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#getEmergAmps <em>Emerg Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg Amps</em>' attribute.
	 * @see #getEmergAmps()
	 * @generated
	 */
	void setEmergAmps(double value);

	/**
	 * Returns the value of the '<em><b>Reduce</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reduce to n_phases (Kron Reduction). Reduce out neutrals.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reduce</em>' attribute.
	 * @see #setReduce(boolean)
	 * @see electrickery.general.GeneralPackage#getLineGeometry_Reduce()
	 * @model default="false"
	 * @generated
	 */
	boolean isReduce();

	/**
	 * Sets the value of the '{@link electrickery.general.LineGeometry#isReduce <em>Reduce</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reduce</em>' attribute.
	 * @see #isReduce()
	 * @generated
	 */
	void setReduce(boolean value);

} // LineGeometry
