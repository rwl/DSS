/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Energy Meter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class of device accumulates the energy of the voltage and current
 * in the terminal of the device to which it is connected.
 * 
 * It is an intelligent energy meter capable of measuring losses of all
 * devices within its "zone".
 * 
 * The Zone is determined automatically after a circuit change.  The Zone
 * starts on the opposite side of the branch on which the meter is located and
 * continues in the same direction through the network until
 *     a) an open point is encountered
 *     b) an open terminal or switch is encountered
 *     c) another energy meter is encountered
 *     d) a branch that is already included in a zone is encountered
 * 
 * It keeps track of kwh, kvarh, UE,  EEN, Losses, etc., having registers FOR
 * each of these quantities.
 * 
 * In EEN/UE calculations, line overload takes precedence.
 * 
 * If the Max Zone kW limits are specified, then these replace the line
 * overload UE/EEN numbers. These limits were added so that the user can
 * override line limits in cases such as networks where it is difficult to
 * judge the UE from the individual line limits.
 * 
 * Only the maximum |kVA| overload is accumulated, not all.  Loads downline
 * from an overload are marked WITH a factor representing the degree of
 * overload.  This is used to compute EEN/UE FOR loads.
 * 
 * FOR low voltages, the full kW FOR loads below the emergency min voltage are
 * counted. The EEN is proportioned based on how low the voltage is.
 * 
 * Emergency min voltage must be less than normal min voltage.
 * 
 * 
 * An EnergyMeter object is an intelligent meter connected to a terminal of a
 * circuit element.  It simulates the behavior of an actual energy meter.
 * However, it has more capability because it can access values at other
 * places in the circuit rather than simply at the location at which it is
 * installed.  It measures not only power and energy values at its location,
 * but losses and overload values within a defined region of the circuit.
 * The operation of the object is simple.  It has several registers that
 * accumulate certain values.  At the beginning of a study, the registers are
 * cleared (reset) to zero.  At the end of each subsequent solution, the meter
 * is instructed to take a sample.  Energy values are then integrated using
 * the interval of time that has passed since the previous solution.
 * 
 * Registers
 * 
 * There are two types of registers:
 *     1.Energy Accumulators (for energy values)
 *     2.Maximum power values ("drag hand" registers).
 * 
 * The energy registers use trapezoidal integration, which allows to use
 * somewhat arbitrary time step sizes between solutions with less integration
 * error. This is important for using load duration curves approximated with
 * straight lines, for example.
 * 
 * The present definitions of the registers are:
 *     1.KWh at the meter location.
 *     2.Kvarh at the meter location.
 *     3.Maximum kW at the meter location.
 *     4.Maximum kVA at the meter location.
 *     5.KWh in the meter zone.
 *     6.Kvarh in the meter zone.
 *     7.Maximum kW in the meter zone.
 *     8.Maximum kVA in the meter zone.
 *     9.Overload kWh in the meter zone, normal ratings.
 *     10.Overload kWh in the meter zone, emergency ratings.
 *     11.Energy Exceeding Normal (EEN) in the loads in the meter zone.
 *     12.Unserved Energy (UE) in the loads in the meter zone.
 *     13.Losses (kWh) in power delivery elements in the meter zone.
 *     14.Reactive losses (kvarh) in power delivery elements in the meter
 *     zone.
 *     15.Maximum losses (kW) in  power delivery elements in the meter zone.
 *     16.Maximum reactive losses (kvar) in power delivery elements in the
 *     meter zone.
 * 
 * Zones
 * 
 * The EnergyMeter object uses the concept of a zone.  This is an area of the
 * circuit for which the meter is responsible.  It can compute energies,
 * losses, etc for any power delivery object and Load object in its zone
 * (Generator objects have their own intrinsic meters).
 * 
 * 
 * A zone is a collection of circuit elements "downline" from the meter.  This
 * concept is nominally applicable to radial circuits, but also has some
 * applicability to meshed circuits.  The zones are automatically determined
 * according to the following rules:
 *     1.Start with the circuit element in which the meter is located.  Ignore
 *     the terminal on which the meter is connected.  This terminal is the
 *     start of the zone. Begin tracing with the other terminal(s).
 *     2.Trace out the circuit, finding all other circuit elements (loads and
 *     power delivery elements) connected to the zone.  Continue tracing out
 *     every branch of the circuit. Stop tracing a branch when:
 *     The end of the circuit branch is reached
 * A circuit element containing another EnergyMeter object is encountered
 * A OPEN terminal is encountered.  (all phases in the terminal are open.)
 * A disabled device is encountered.
 * A circuit element already included in another zone is encountered.
 * There are no more circuit elements to consider.
 * Zones are automatically updated after a change in the circuit unless
 * the ZONELOCK option (Set command) is set to true (Yes).  Then zones
 * remain fixed after initial determination.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.meter.EnergyMeter#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getAction <em>Action</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getOption <em>Option</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getKVANorm <em>KVA Norm</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getKVAEmerg <em>KVA Emerg</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getPeakCurrent <em>Peak Current</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getZoneList <em>Zone List</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isLocalOnly <em>Local Only</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#getMask <em>Mask</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isLosses <em>Losses</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isLineLosses <em>Line Losses</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isXfmrLosses <em>Xfmr Losses</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isSeqLosses <em>Seq Losses</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isVBaseLosses <em>VBase Losses</em>}</li>
 *   <li>{@link electrickery.meter.EnergyMeter#isOverloadReport <em>Overload Report</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.meter.MeterPackage#getEnergyMeter()
 * @model
 * @generated
 */
public interface EnergyMeter extends MeterElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name (Full Object name) of element to which the monitor is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_Element()
	 * @model
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(String value);

	/**
	 * Returns the value of the '<em><b>Terminal</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the circuit element to which the monitor is connected.  1 or 2, typically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Terminal</em>' attribute.
	 * @see #setTerminal(int)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_Terminal()
	 * @model default="1"
	 * @generated
	 */
	int getTerminal();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#getTerminal <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' attribute.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(int value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.meter.meterAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * (A)llocate = Allocate loads on the meter zone to match PeakCurrent.
	 * (C)lear = reset all registers to zero
	 * (R)educe = reduces zone by merging lines (see Set Keeplist &
	 * ReduceOption)
	 * (S)ave = saves the current register values to a file. File name is
	 * "MTR_metername.CSV". (T)ake = Takes a sample at present solution
	 * (Z)onedump = Dump names of elements in meter zone to a file
	 * File name is "Zone_metername.CSV".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see electrickery.meter.meterAction
	 * @see #setAction(meterAction)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_Action()
	 * @model
	 * @generated
	 */
	meterAction getAction();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see electrickery.meter.meterAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(meterAction value);

	/**
	 * Returns the value of the '<em><b>Option</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Enter a string ARRAY of any combination of the following. Options
	 * processed left-to-right:
	 *      (E)xcess : (default) UE/EEN is estimate of energy over capacity
	 *      (T)otal : UE/EEN is total energy after capacity exceeded
	 *      (R)adial : (default) Treats zone as a radial circuit
	 *      (M)esh : Treats zone as meshed network (not radial).
	 *      (C)ombined : (default) Load UE/EEN computed from combination of
	 *      overload and undervoltage.
	 *      (V)oltage : Load UE/EEN computed based on voltage only.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Option</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_Option()
	 * @model
	 * @generated
	 */
	EList<String> getOption();

	/**
	 * Returns the value of the '<em><b>KVA Norm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Upper limit on kVA load in the zone, Normal configuration. Default is 0.0 (ignored).  Overrides limits on individual lines for overload EEN. With "LocalOnly=Yes" option, uses only load in metered branch.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA Norm</em>' attribute.
	 * @see #setKVANorm(double)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_KVANorm()
	 * @model
	 * @generated
	 */
	double getKVANorm();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#getKVANorm <em>KVA Norm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA Norm</em>' attribute.
	 * @see #getKVANorm()
	 * @generated
	 */
	void setKVANorm(double value);

	/**
	 * Returns the value of the '<em><b>KVA Emerg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Upper limit on kVA load in the zone, Emergency configuration. Default is 0.0 (ignored). Overrides limits on individual lines for overload UE.  With "LocalOnly=Yes" option, uses only load in metered branch.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA Emerg</em>' attribute.
	 * @see #setKVAEmerg(double)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_KVAEmerg()
	 * @model
	 * @generated
	 */
	double getKVAEmerg();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#getKVAEmerg <em>KVA Emerg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA Emerg</em>' attribute.
	 * @see #getKVAEmerg()
	 * @generated
	 */
	void setKVAEmerg(double value);

	/**
	 * Returns the value of the '<em><b>Peak Current</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of current magnitudes representing the peak currents measured at this location for the load allocation function.  Default is (400, 400, 400). Enter one current for each phase
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Peak Current</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_PeakCurrent()
	 * @model
	 * @generated
	 */
	EList<Double> getPeakCurrent();

	/**
	 * Returns the value of the '<em><b>Zone List</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of full element names for this meter''s zone.  Default is for meter to find it''s own zone. If specified, DSS uses this list instead.  Can access the names in a single-column text file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Zone List</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_ZoneList()
	 * @model
	 * @generated
	 */
	EList<String> getZoneList();

	/**
	 * Returns the value of the '<em><b>Local Only</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If Yes, meter considers only the monitored element for EEN and UE calcs.  Uses whole zone for losses.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Local Only</em>' attribute.
	 * @see #setLocalOnly(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_LocalOnly()
	 * @model default="false"
	 * @generated
	 */
	boolean isLocalOnly();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isLocalOnly <em>Local Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Only</em>' attribute.
	 * @see #isLocalOnly()
	 * @generated
	 */
	void setLocalOnly(boolean value);

	/**
	 * Returns the value of the '<em><b>Mask</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mask for adding registers whenever all meters are totalized.  Array of floating point numbers representing the multiplier to be used for summing each register from this meter.  Default = (1, 1, 1, 1, ... ).  You only have to enter as many as are changed (positional). Useful when two meters monitor same energy, etc.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mask</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_Mask()
	 * @model
	 * @generated
	 */
	EList<Double> getMask();

	/**
	 * Returns the value of the '<em><b>Losses</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Compute Zone losses. If NO, then no losses at all are computed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Losses</em>' attribute.
	 * @see #setLosses(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_Losses()
	 * @model default="true"
	 * @generated
	 */
	boolean isLosses();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isLosses <em>Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Losses</em>' attribute.
	 * @see #isLosses()
	 * @generated
	 */
	void setLosses(boolean value);

	/**
	 * Returns the value of the '<em><b>Line Losses</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Compute Line losses. If NO, then none of the losses are computed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Losses</em>' attribute.
	 * @see #setLineLosses(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_LineLosses()
	 * @model default="true"
	 * @generated
	 */
	boolean isLineLosses();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isLineLosses <em>Line Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Losses</em>' attribute.
	 * @see #isLineLosses()
	 * @generated
	 */
	void setLineLosses(boolean value);

	/**
	 * Returns the value of the '<em><b>Xfmr Losses</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Compute Transformer losses. If NO, transformers are ignored in loss calculations.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xfmr Losses</em>' attribute.
	 * @see #setXfmrLosses(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_XfmrLosses()
	 * @model default="true"
	 * @generated
	 */
	boolean isXfmrLosses();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isXfmrLosses <em>Xfmr Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xfmr Losses</em>' attribute.
	 * @see #isXfmrLosses()
	 * @generated
	 */
	void setXfmrLosses(boolean value);

	/**
	 * Returns the value of the '<em><b>Seq Losses</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Compute Sequence losses in lines and segregate by line mode losses and zero mode losses.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Seq Losses</em>' attribute.
	 * @see #setSeqLosses(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_SeqLosses()
	 * @model default="true"
	 * @generated
	 */
	boolean isSeqLosses();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isSeqLosses <em>Seq Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seq Losses</em>' attribute.
	 * @see #isSeqLosses()
	 * @generated
	 */
	void setSeqLosses(boolean value);

	/**
	 * Returns the value of the '<em><b>VBase Losses</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Compute losses and segregate by voltage base. If NO, then voltage-based tabulation is not reported.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VBase Losses</em>' attribute.
	 * @see #setVBaseLosses(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_VBaseLosses()
	 * @model default="true"
	 * @generated
	 */
	boolean isVBaseLosses();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isVBaseLosses <em>VBase Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VBase Losses</em>' attribute.
	 * @see #isVBaseLosses()
	 * @generated
	 */
	void setVBaseLosses(boolean value);

	/**
	 * Returns the value of the '<em><b>Overload Report</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, write Overload exception report when Demand Intervals are written.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overload Report</em>' attribute.
	 * @see #setOverloadReport(boolean)
	 * @see electrickery.meter.MeterPackage#getEnergyMeter_OverloadReport()
	 * @model default="true"
	 * @generated
	 */
	boolean isOverloadReport();

	/**
	 * Sets the value of the '{@link electrickery.meter.EnergyMeter#isOverloadReport <em>Overload Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overload Report</em>' attribute.
	 * @see #isOverloadReport()
	 * @generated
	 */
	void setOverloadReport(boolean value);

} // EnergyMeter
