/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Voltage Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This is a special power conversion element.  It is special because
 * voltage sources must be identified to initialize the solution with all
 * other injection sources set to zero.
 * 
 * A Vsource object is simply a multi-phase Thevenin equivalent with data
 * specified as it would commonly be for a power system source equivalent:
 * Line-line voltage (kV) and short circuit MVA.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.conversion.VoltageSource#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getBaseKV <em>Base KV</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getPerUnit <em>Per Unit</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getAngle <em>Angle</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getMvaSC3 <em>Mva SC3</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getMvaSC1 <em>Mva SC1</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getX1R1 <em>X1R1</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getX0R0 <em>X0R0</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getISC3 <em>ISC3</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getISC1 <em>ISC1</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getR1 <em>R1</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getX1 <em>X1</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getR0 <em>R0</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getX0 <em>X0</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getScanType <em>Scan Type</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getZ <em>Z</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getZInv <em>ZInv</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getZSpecType <em>ZSpec Type</em>}</li>
 *   <li>{@link electrickery.conversion.VoltageSource#getVMag <em>VMag</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.conversion.ConversionPackage#getVoltageSource()
 * @model
 * @generated
 */
public interface VoltageSource extends PowerConversionElement {
    /**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of bus to which the source's one terminal is connected.  Remember to specify the node order if the terminals are connected in some unusual manner.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_Bus1()
	 * @model
	 * @generated
	 */
    String getBus1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getBus1 <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus1</em>' attribute.
	 * @see #getBus1()
	 * @generated
	 */
    void setBus1(String value);

    /**
	 * Returns the value of the '<em><b>Base KV</b></em>' attribute.
	 * The default value is <code>"115.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base Source kV, usually L-L unless you are making a positive-sequence model in which case, it will be L-N.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base KV</em>' attribute.
	 * @see #setBaseKV(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_BaseKV()
	 * @model default="115.0"
	 * @generated
	 */
    double getBaseKV();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getBaseKV <em>Base KV</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base KV</em>' attribute.
	 * @see #getBaseKV()
	 * @generated
	 */
    void setBaseKV(double value);

    /**
	 * Returns the value of the '<em><b>Per Unit</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit of the base voltage that the source is actually operating at.  Assumed balanced for all phases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Per Unit</em>' attribute.
	 * @see #setPerUnit(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_PerUnit()
	 * @model default="1.0"
	 * @generated
	 */
    double getPerUnit();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getPerUnit <em>Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Per Unit</em>' attribute.
	 * @see #getPerUnit()
	 * @generated
	 */
    void setPerUnit(double value);

    /**
	 * Returns the value of the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Angle</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Angle</em>' attribute.
	 * @see #setAngle(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_Angle()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel docmentation='Phase angle in degrees of first phase.'"
	 * @generated
	 */
    double getAngle();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getAngle <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Angle</em>' attribute.
	 * @see #getAngle()
	 * @generated
	 */
    void setAngle(double value);

    /**
	 * Returns the value of the '<em><b>Frequency</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Source frequency.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Frequency</em>' attribute.
	 * @see #setFrequency(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_Frequency()
	 * @model default="60.0"
	 * @generated
	 */
    double getFrequency();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
    void setFrequency(double value);

    /**
	 * Returns the value of the '<em><b>Mva SC3</b></em>' attribute.
	 * The default value is <code>"2000.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MVA Short circuit, 3-phase fault.  Z1 is determined by squaring the base kv and dividing by this value.  For single-phase source, this value is not used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mva SC3</em>' attribute.
	 * @see #setMvaSC3(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_MvaSC3()
	 * @model default="2000.0"
	 * @generated
	 */
    double getMvaSC3();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getMvaSC3 <em>Mva SC3</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mva SC3</em>' attribute.
	 * @see #getMvaSC3()
	 * @generated
	 */
    void setMvaSC3(double value);

    /**
	 * Returns the value of the '<em><b>Mva SC1</b></em>' attribute.
	 * The default value is <code>"2100.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MVA Short Circuit, 1-phase fault.  The "single-phase impedance", Zs, is determined by squaring the base kV and dividing by this value.  Then Z0 is determined by Z0 = 3Zs - 2Z1.  For 1-phase sources, Zs is used directly. Use x0_r0 to define X/R ratio for 1-phase source.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mva SC1</em>' attribute.
	 * @see #setMvaSC1(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_MvaSC1()
	 * @model default="2100.0"
	 * @generated
	 */
    double getMvaSC1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getMvaSC1 <em>Mva SC1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mva SC1</em>' attribute.
	 * @see #getMvaSC1()
	 * @generated
	 */
    void setMvaSC1(double value);

    /**
	 * Returns the value of the '<em><b>X1R1</b></em>' attribute.
	 * The default value is <code>"4.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive-sequence X/R ratio.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X1R1</em>' attribute.
	 * @see #setX1R1(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_X1R1()
	 * @model default="4.0"
	 * @generated
	 */
    double getX1R1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getX1R1 <em>X1R1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X1R1</em>' attribute.
	 * @see #getX1R1()
	 * @generated
	 */
    void setX1R1(double value);

    /**
	 * Returns the value of the '<em><b>X0R0</b></em>' attribute.
	 * The default value is <code>"3.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Zero-sequence X/R ratio.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X0R0</em>' attribute.
	 * @see #setX0R0(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_X0R0()
	 * @model default="3.0"
	 * @generated
	 */
    double getX0R0();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getX0R0 <em>X0R0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X0R0</em>' attribute.
	 * @see #getX0R0()
	 * @generated
	 */
    void setX0R0(double value);

    /**
	 * Returns the value of the '<em><b>ISC3</b></em>' attribute.
	 * The default value is <code>"10000.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Alternate method of defining the source impedance. 3-phase short circuit current, amps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ISC3</em>' attribute.
	 * @see #setISC3(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_ISC3()
	 * @model default="10000.0"
	 * @generated
	 */
    double getISC3();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getISC3 <em>ISC3</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ISC3</em>' attribute.
	 * @see #getISC3()
	 * @generated
	 */
    void setISC3(double value);

    /**
	 * Returns the value of the '<em><b>ISC1</b></em>' attribute.
	 * The default value is <code>"10500.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Alternate method of defining the source impedance. Single-phase short circuit current, amps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ISC1</em>' attribute.
	 * @see #setISC1(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_ISC1()
	 * @model default="10500.0"
	 * @generated
	 */
    double getISC1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getISC1 <em>ISC1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ISC1</em>' attribute.
	 * @see #getISC1()
	 * @generated
	 */
    void setISC1(double value);

    /**
	 * Returns the value of the '<em><b>R1</b></em>' attribute.
	 * The default value is <code>"1.65"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Alternate method of defining the source impedance. Positive-sequence resistance, ohms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R1</em>' attribute.
	 * @see #setR1(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_R1()
	 * @model default="1.65"
	 * @generated
	 */
    double getR1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getR1 <em>R1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R1</em>' attribute.
	 * @see #getR1()
	 * @generated
	 */
    void setR1(double value);

    /**
	 * Returns the value of the '<em><b>X1</b></em>' attribute.
	 * The default value is <code>"6.6"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Alternate method of defining the source impedance. Positive-sequence reactance, ohms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X1</em>' attribute.
	 * @see #setX1(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_X1()
	 * @model default="6.6"
	 * @generated
	 */
    double getX1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getX1 <em>X1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X1</em>' attribute.
	 * @see #getX1()
	 * @generated
	 */
    void setX1(double value);

    /**
	 * Returns the value of the '<em><b>R0</b></em>' attribute.
	 * The default value is <code>"1.9"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Alternate method of defining the source impedance. Zero-sequence resistance, ohms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R0</em>' attribute.
	 * @see #setR0(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_R0()
	 * @model default="1.9"
	 * @generated
	 */
    double getR0();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getR0 <em>R0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R0</em>' attribute.
	 * @see #getR0()
	 * @generated
	 */
    void setR0(double value);

    /**
	 * Returns the value of the '<em><b>X0</b></em>' attribute.
	 * The default value is <code>"5.7"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Alternate method of defining the source impedance. Zero-sequence reactance, ohms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X0</em>' attribute.
	 * @see #setX0(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_X0()
	 * @model default="5.7"
	 * @generated
	 */
    double getX0();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getX0 <em>X0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X0</em>' attribute.
	 * @see #getX0()
	 * @generated
	 */
    void setX0(double value);

    /**
	 * Returns the value of the '<em><b>Scan Type</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.sequenceType}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maintain specified sequence for harmonic solution.  Default is positive sequence. Otherwise, angle between phases rotates with harmonic.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scan Type</em>' attribute.
	 * @see electrickery.conversion.sequenceType
	 * @see #setScanType(sequenceType)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_ScanType()
	 * @model
	 * @generated
	 */
    sequenceType getScanType();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getScanType <em>Scan Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scan Type</em>' attribute.
	 * @see electrickery.conversion.sequenceType
	 * @see #getScanType()
	 * @generated
	 */
    void setScanType(sequenceType value);

    /**
	 * Returns the value of the '<em><b>Z</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base frequency series Z matrix.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Z</em>' reference.
	 * @see #setZ(DComplexMatrix2D)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_Z()
	 * @model type="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    DComplexMatrix2D getZ();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getZ <em>Z</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Z</em>' reference.
	 * @see #getZ()
	 * @generated
	 */
    void setZ(DComplexMatrix2D value);

    /**
	 * Returns the value of the '<em><b>ZInv</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ZInv</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>ZInv</em>' reference.
	 * @see #setZInv(DComplexMatrix2D)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_ZInv()
	 * @model type="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    DComplexMatrix2D getZInv();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getZInv <em>ZInv</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ZInv</em>' reference.
	 * @see #getZInv()
	 * @generated
	 */
    void setZInv(DComplexMatrix2D value);

    /**
	 * Returns the value of the '<em><b>ZSpec Type</b></em>' attribute.
	 * The default value is <code>"MVAsc"</code>.
	 * The literals are from the enumeration {@link electrickery.conversion.specType}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ZSpec Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>ZSpec Type</em>' attribute.
	 * @see electrickery.conversion.specType
	 * @see #setZSpecType(specType)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_ZSpecType()
	 * @model default="MVAsc"
	 * @generated
	 */
    specType getZSpecType();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getZSpecType <em>ZSpec Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ZSpec Type</em>' attribute.
	 * @see electrickery.conversion.specType
	 * @see #getZSpecType()
	 * @generated
	 */
    void setZSpecType(specType value);

    /**
	 * Returns the value of the '<em><b>VMag</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VMag</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>VMag</em>' attribute.
	 * @see #setVMag(double)
	 * @see electrickery.conversion.ConversionPackage#getVoltageSource_VMag()
	 * @model
	 * @generated
	 */
    double getVMag();

    /**
	 * Sets the value of the '{@link electrickery.conversion.VoltageSource#getVMag <em>VMag</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMag</em>' attribute.
	 * @see #getVMag()
	 * @generated
	 */
    void setVMag(double value);

} // VoltageSource
