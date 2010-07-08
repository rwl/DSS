/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.common.lengthUnit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Line</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Line impedances are specified in per unit length and are multiplied by
 * the length when the primitive Y matrix is computed.
 * 
 * You may specify the impedances of the line either by symmetrical components
 * or by R, X, and nodal C matrices  (also per unit length).
 * 
 * All C's is entered in nano farads.
 * 
 * The ultimate values are in the matrices.  If you specify matrices, then the
 * symmetrical component values are ignored.  However, if you change any of
 * the symmetrical component values the matrices will be recomputed.  It is
 * assumed you want to use symmetrical component values.  Don't mix data entry
 * by matrix and by symmetrical components.
 * 
 * Note that if you change the number of phases, the matrices are reallocated
 * and reinitialized with whatever is currently in the symmetrical component
 * data.
 * 
 * 
 * Multi-phase, two-port line or cable.  Pi model.  Power delivery element
 * described by its impedance.  Impedances may be specified by symmetrical
 * component values or by matrix values.  Alternatively, you may simply refer
 * to an existing LineCode object from which the impedance values will be
 * copied.  Then you need only specify the length.
 * 
 * You can define the line impedance at a base frequency directly in a Line
 * object definition or you can import the impedance definition from a
 * LineCode object. Both of these definitions of impedance are quite similar
 * except that the LineCode object can perform Kron reduction.
 * 
 * If the geometry property is specified all previous definitions are ignored.
 * The DSS will compute the impedance matrices from the specified geometry
 * each time the frequency changes.
 * 
 * Whichever definition is the most recent applies, as with nearly all DSS
 * functions.
 * 
 * Note the units property; you can declare any length measurement in whatever
 * units you please.  Internally, everything is converted to meters. Just be
 * sure to declare the units. Otherwise, they are assumed to be compatible
 * with other data or irrelevant.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.delivery.Line#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getLineCode <em>Line Code</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getLength <em>Length</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getR1 <em>R1</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getX1 <em>X1</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getR0 <em>R0</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getX0 <em>X0</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getC1 <em>C1</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getC0 <em>C0</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getRMatrix <em>RMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getXMatrix <em>XMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getCMatrix <em>CMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Line#isSwitch <em>Switch</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getRg <em>Rg</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getXg <em>Xg</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getRho <em>Rho</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link electrickery.delivery.Line#getUnits <em>Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.delivery.DeliveryPackage#getLine()
 * @model
 * @generated
 */
public interface Line extends PowerDeliveryElement {
    /**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of bus for terminal 1. Node order definitions optional.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Bus1()
	 * @model
	 * @generated
	 */
    String getBus1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getBus1 <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus1</em>' attribute.
	 * @see #getBus1()
	 * @generated
	 */
    void setBus1(String value);

    /**
	 * Returns the value of the '<em><b>Bus2</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of bus for terminal 2.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus2</em>' attribute.
	 * @see #setBus2(String)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Bus2()
	 * @model
	 * @generated
	 */
    String getBus2();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getBus2 <em>Bus2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus2</em>' attribute.
	 * @see #getBus2()
	 * @generated
	 */
    void setBus2(String value);

    /**
	 * Returns the value of the '<em><b>Line Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of linecode object describing line impedances.  If you use a line code, you do not need to specify the impedances here.  The line code must have been PREVIOUSLY defined.  The values specified last will prevail over those specified earlier (left-to-right sequence of properties).  If no line code or impedance data are specified, line object defaults to 336 MCM ACSR on 4 ft spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Code</em>' attribute.
	 * @see #setLineCode(String)
	 * @see electrickery.delivery.DeliveryPackage#getLine_LineCode()
	 * @model
	 * @generated
	 */
    String getLineCode();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getLineCode <em>Line Code</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Code</em>' attribute.
	 * @see #getLineCode()
	 * @generated
	 */
    void setLineCode(String value);

    /**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Length of line. If units do not match the impedance data, specify "units" property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Length()
	 * @model
	 * @generated
	 */
    double getLength();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
    void setLength(double value);

    /**
	 * Returns the value of the '<em><b>R1</b></em>' attribute.
	 * The default value is <code>"0.058"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive-sequence Resistance, ohms per unit length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R1</em>' attribute.
	 * @see #setR1(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_R1()
	 * @model default="0.058"
	 * @generated
	 */
    double getR1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getR1 <em>R1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R1</em>' attribute.
	 * @see #getR1()
	 * @generated
	 */
    void setR1(double value);

    /**
	 * Returns the value of the '<em><b>X1</b></em>' attribute.
	 * The default value is <code>"0.1206"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive-sequence Reactance, ohms per unit length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X1</em>' attribute.
	 * @see #setX1(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_X1()
	 * @model default="0.1206"
	 * @generated
	 */
    double getX1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getX1 <em>X1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X1</em>' attribute.
	 * @see #getX1()
	 * @generated
	 */
    void setX1(double value);

    /**
	 * Returns the value of the '<em><b>R0</b></em>' attribute.
	 * The default value is <code>"0.1784"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Zero-sequence resistance, ohms per unit length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R0</em>' attribute.
	 * @see #setR0(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_R0()
	 * @model default="0.1784"
	 * @generated
	 */
    double getR0();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getR0 <em>R0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R0</em>' attribute.
	 * @see #getR0()
	 * @generated
	 */
    void setR0(double value);

    /**
	 * Returns the value of the '<em><b>X0</b></em>' attribute.
	 * The default value is <code>"0.4047"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Zero-sequence Reactance, ohms per unit length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X0</em>' attribute.
	 * @see #setX0(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_X0()
	 * @model default="0.4047"
	 * @generated
	 */
    double getX0();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getX0 <em>X0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X0</em>' attribute.
	 * @see #getX0()
	 * @generated
	 */
    void setX0(double value);

    /**
	 * Returns the value of the '<em><b>C1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive-sequence capacitance, nF per unit length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>C1</em>' attribute.
	 * @see #setC1(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_C1()
	 * @model
	 * @generated
	 */
    double getC1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getC1 <em>C1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>C1</em>' attribute.
	 * @see #getC1()
	 * @generated
	 */
    void setC1(double value);

    /**
	 * Returns the value of the '<em><b>C0</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Zero-sequence capacitance, nF per unit length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>C0</em>' attribute.
	 * @see #setC0(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_C0()
	 * @model
	 * @generated
	 */
    double getC0();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getC0 <em>C0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>C0</em>' attribute.
	 * @see #getC0()
	 * @generated
	 */
    void setC0(double value);

    /**
	 * Returns the value of the '<em><b>RMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. May be used to specify the impedance of any line configuration.  For balanced line models, you may use the standard symmetrical component data definition instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RMatrix</em>' reference.
	 * @see #setRMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getLine_RMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getRMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getRMatrix <em>RMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RMatrix</em>' reference.
	 * @see #getRMatrix()
	 * @generated
	 */
    void setRMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>XMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. May be used to specify the impedance of any line configuration.  For balanced line models, you may use the standard symmetrical component data definition instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XMatrix</em>' reference.
	 * @see #setXMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getLine_XMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getXMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getXMatrix <em>XMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XMatrix</em>' reference.
	 * @see #getXMatrix()
	 * @generated
	 */
    void setXMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>CMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nodal Capacitance matrix, lower triangle, nf per unit length.  Order of the matrix is the number of phases.  May be used to specify the shunt capacitance of any line configuration.  For balanced line models, you may use the standard symmetrical component data definition instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>CMatrix</em>' reference.
	 * @see #setCMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getLine_CMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getCMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getCMatrix <em>CMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CMatrix</em>' reference.
	 * @see #getCMatrix()
	 * @generated
	 */
    void setCMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>Switch</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Designates this line as a switch for graphics and algorithmic purposes.  SIDE EFFECT: Sets R1=0.001 X1=0.0. You must reset if you want something different.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switch</em>' attribute.
	 * @see #setSwitch(boolean)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Switch()
	 * @model default="false"
	 * @generated
	 */
    boolean isSwitch();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#isSwitch <em>Switch</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switch</em>' attribute.
	 * @see #isSwitch()
	 * @generated
	 */
    void setSwitch(boolean value);

    /**
	 * Returns the value of the '<em><b>Rg</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Carson earth return resistance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rg</em>' attribute.
	 * @see #setRg(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Rg()
	 * @model
	 * @generated
	 */
    double getRg();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getRg <em>Rg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rg</em>' attribute.
	 * @see #getRg()
	 * @generated
	 */
    void setRg(double value);

    /**
	 * Returns the value of the '<em><b>Xg</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xg</em>' attribute.
	 * @see #setXg(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Xg()
	 * @model
	 * @generated
	 */
    double getXg();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getXg <em>Xg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xg</em>' attribute.
	 * @see #getXg()
	 * @generated
	 */
    void setXg(double value);

    /**
	 * Returns the value of the '<em><b>Rho</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Earth resistivity used to compute earth correction factor. Overrides Line geometry definition if specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rho</em>' attribute.
	 * @see #setRho(double)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Rho()
	 * @model default="100.0"
	 * @generated
	 */
    double getRho();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getRho <em>Rho</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rho</em>' attribute.
	 * @see #getRho()
	 * @generated
	 */
    void setRho(double value);

    /**
	 * Returns the value of the '<em><b>Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Geometry code for LineGeometry Object. Supercedes any previous definition of line impedance. Line constants are computed for each frequency change or rho change. CAUTION: may alter number of phases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Geometry</em>' attribute.
	 * @see #setGeometry(String)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Geometry()
	 * @model
	 * @generated
	 */
    String getGeometry();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getGeometry <em>Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geometry</em>' attribute.
	 * @see #getGeometry()
	 * @generated
	 */
    void setGeometry(String value);

    /**
	 * Returns the value of the '<em><b>Units</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.lengthUnit}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default is None - assumes length units match impedance units.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #setUnits(lengthUnit)
	 * @see electrickery.delivery.DeliveryPackage#getLine_Units()
	 * @model
	 * @generated
	 */
    lengthUnit getUnits();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Line#getUnits <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #getUnits()
	 * @generated
	 */
    void setUnits(lengthUnit value);

} // Line
