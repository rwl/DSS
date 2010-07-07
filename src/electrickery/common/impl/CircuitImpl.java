/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;


import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CircuitElement;
import electrickery.common.CommonPackage;
import electrickery.common.ControlQueue;
import electrickery.common.Feeder;
import electrickery.common.Solution;
import electrickery.common.yBuildOption;
import electrickery.control.CapacitorControl;
import electrickery.control.ControlElement;
import electrickery.control.RegulatorControl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.CurrentSource;
import electrickery.conversion.Generator;
import electrickery.conversion.Load;
import electrickery.conversion.PowerConversionElement;
import electrickery.conversion.VoltageSource;
import electrickery.delivery.Capacitor;
import electrickery.delivery.Fault;
import electrickery.delivery.Line;
import electrickery.delivery.PowerDeliveryElement;
import electrickery.delivery.Transformer;
import electrickery.meter.EnergyMeter;
import electrickery.meter.MeterElement;
import electrickery.meter.Monitor;
import electrickery.meter.Sensor;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.ecore.util.InternalEList;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Circuit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getSolution <em>Solution</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getControlQueue <em>Control Queue</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getBusList <em>Bus List</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getFaults <em>Faults</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getVoltageSources <em>Voltage Sources</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getCurrentSources <em>Current Sources</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getSensors <em>Sensors</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getMonitors <em>Monitors</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getEnergyMeters <em>Energy Meters</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getGenerators <em>Generators</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getTransformers <em>Transformers</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getCapControls <em>Cap Controls</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getRegControls <em>Reg Controls</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getLines <em>Lines</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getLoads <em>Loads</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getShuntCapacitors <em>Shunt Capacitors</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getFeeder <em>Feeder</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getName <em>Name</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getNumNodes <em>Num Nodes</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getGenMultiplier <em>Gen Multiplier</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#isSolved <em>Solved</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#isBusNameRedefined <em>Bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#isControl_busNameRedefined <em>Control bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getLoadMultiplier <em>Load Multiplier</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getDefaultGrowthFactor <em>Default Growth Factor</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getDefaultHourMult <em>Default Hour Mult</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getPriceSignal <em>Price Signal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CircuitImpl extends EObjectImpl implements Circuit {
    /**
	 * The cached value of the '{@link #getSolution() <em>Solution</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSolution()
	 * @generated
	 * @ordered
	 */
    protected Solution solution;

    /**
	 * The cached value of the '{@link #getControlQueue() <em>Control Queue</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getControlQueue()
	 * @generated
	 * @ordered
	 */
    protected ControlQueue controlQueue;

                /**
	 * The cached value of the '{@link #getBusList() <em>Bus List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBusList()
	 * @generated
	 * @ordered
	 */
    protected EList<Bus> busList;

                /**
	 * The cached value of the '{@link #getFaults() <em>Faults</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFaults()
	 * @generated
	 * @ordered
	 */
    protected EList<Fault> faults;

                /**
	 * The cached value of the '{@link #getVoltageSources() <em>Voltage Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVoltageSources()
	 * @generated
	 * @ordered
	 */
    protected EList<VoltageSource> voltageSources;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getCurrentSources() <em>Current Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCurrentSources()
	 * @generated
	 * @ordered
	 */
    protected EList<CurrentSource> currentSources;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getSensors() <em>Sensors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSensors()
	 * @generated
	 * @ordered
	 */
    protected EList<Sensor> sensors;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getMonitors() <em>Monitors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMonitors()
	 * @generated
	 * @ordered
	 */
    protected EList<Monitor> monitors;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getEnergyMeters() <em>Energy Meters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getEnergyMeters()
	 * @generated
	 * @ordered
	 */
    protected EList<EnergyMeter> energyMeters;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getGenerators() <em>Generators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGenerators()
	 * @generated
	 * @ordered
	 */
    protected EList<Generator> generators;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getTransformers() <em>Transformers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTransformers()
	 * @generated
	 * @ordered
	 */
    protected EList<Transformer> transformers;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getCapControls() <em>Cap Controls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCapControls()
	 * @generated
	 * @ordered
	 */
    protected EList<CapacitorControl> capControls;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getRegControls() <em>Reg Controls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRegControls()
	 * @generated
	 * @ordered
	 */
    protected EList<RegulatorControl> regControls;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getLines() <em>Lines</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLines()
	 * @generated
	 * @ordered
	 */
    protected EList<Line> lines;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getLoads() <em>Loads</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLoads()
	 * @generated
	 * @ordered
	 */
    protected EList<Load> loads;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getShuntCapacitors() <em>Shunt Capacitors</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getShuntCapacitors()
	 * @generated
	 * @ordered
	 */
    protected EList<Capacitor> shuntCapacitors;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getFeeder() <em>Feeder</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFeeder()
	 * @generated
	 * @ordered
	 */
    protected EList<Feeder> feeder;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
* <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
protected static final String NAME_EDEFAULT = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
    protected String name = NAME_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getNumNodes() <em>Num Nodes</em>}' attribute.
	 * <!-- begin-user-doc -->
* <!-- end-user-doc -->
	 * @see #getNumNodes()
	 * @generated
	 * @ordered
	 */
protected static final int NUM_NODES_EDEFAULT = 0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getNumNodes() <em>Num Nodes</em>}' attribute.
	 * <!-- begin-user-doc -->
* <!-- end-user-doc -->
	 * @see #getNumNodes()
	 * @generated
	 * @ordered
	 */
protected int numNodes = NUM_NODES_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getGeneratorDispatchReference() <em>Generator Dispatch Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGeneratorDispatchReference()
	 * @generated
	 * @ordered
	 */
    protected static final double GENERATOR_DISPATCH_REFERENCE_EDEFAULT = 1000.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getGeneratorDispatchReference() <em>Generator Dispatch Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGeneratorDispatchReference()
	 * @generated
	 * @ordered
	 */
    protected double generatorDispatchReference = GENERATOR_DISPATCH_REFERENCE_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getGenMultiplier() <em>Gen Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGenMultiplier()
	 * @generated
	 * @ordered
	 */
    protected static final double GEN_MULTIPLIER_EDEFAULT = 1.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getGenMultiplier() <em>Gen Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGenMultiplier()
	 * @generated
	 * @ordered
	 */
    protected double genMultiplier = GEN_MULTIPLIER_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #isSolved() <em>Solved</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSolved()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SOLVED_EDEFAULT = false;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #isSolved() <em>Solved</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSolved()
	 * @generated
	 * @ordered
	 */
    protected boolean solved = SOLVED_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #isBusNameRedefined() <em>Bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc -->
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 * <!-- end-user-doc -->
	 * @see #isBusNameRedefined()
	 * @generated
	 * @ordered
	 */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                protected static final boolean BUS_NAME_REDEFINED_EDEFAULT = true;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #isBusNameRedefined() <em>Bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc -->
* <!-- end-user-doc -->
	 * @see #isBusNameRedefined()
	 * @generated
	 * @ordered
	 */
protected boolean busNameRedefined = BUS_NAME_REDEFINED_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #isControl_busNameRedefined() <em>Control bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isControl_busNameRedefined()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CONTROL_BUS_NAME_REDEFINED_EDEFAULT = false;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #isControl_busNameRedefined() <em>Control bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isControl_busNameRedefined()
	 * @generated
	 * @ordered
	 */
    protected boolean control_busNameRedefined = CONTROL_BUS_NAME_REDEFINED_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getLoadMultiplier() <em>Load Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLoadMultiplier()
	 * @generated
	 * @ordered
	 */
    protected static final double LOAD_MULTIPLIER_EDEFAULT = 1.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getLoadMultiplier() <em>Load Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLoadMultiplier()
	 * @generated
	 * @ordered
	 */
    protected double loadMultiplier = LOAD_MULTIPLIER_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getDefaultGrowthFactor() <em>Default Growth Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefaultGrowthFactor()
	 * @generated
	 * @ordered
	 */
    protected static final double DEFAULT_GROWTH_FACTOR_EDEFAULT = 1.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getDefaultGrowthFactor() <em>Default Growth Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefaultGrowthFactor()
	 * @generated
	 * @ordered
	 */
    protected double defaultGrowthFactor = DEFAULT_GROWTH_FACTOR_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getDefaultHourMult() <em>Default Hour Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefaultHourMult()
	 * @generated
	 * @ordered
	 */
    protected static final double DEFAULT_HOUR_MULT_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getDefaultHourMult() <em>Default Hour Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefaultHourMult()
	 * @generated
	 * @ordered
	 */
    protected double defaultHourMult = DEFAULT_HOUR_MULT_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getPriceSignal() <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPriceSignal()
	 * @generated
	 * @ordered
	 */
    protected static final double PRICE_SIGNAL_EDEFAULT = 0.0;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getPriceSignal() <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPriceSignal()
	 * @generated
	 * @ordered
	 */
    protected double priceSignal = PRICE_SIGNAL_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected CircuitImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return CommonPackage.Literals.CIRCUIT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Solution getSolution() {
		if (solution != null && solution.eIsProxy()) {
			InternalEObject oldSolution = (InternalEObject)solution;
			solution = (Solution)eResolveProxy(oldSolution);
			if (solution != oldSolution) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT__SOLUTION, oldSolution, solution));
			}
		}
		return solution;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Solution basicGetSolution() {
		return solution;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetSolution(Solution newSolution, NotificationChain msgs) {
		Solution oldSolution = solution;
		solution = newSolution;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__SOLUTION, oldSolution, newSolution);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSolution(Solution newSolution) {
		if (newSolution != solution) {
			NotificationChain msgs = null;
			if (solution != null)
				msgs = ((InternalEObject)solution).eInverseRemove(this, CommonPackage.SOLUTION__CIRCUIT, Solution.class, msgs);
			if (newSolution != null)
				msgs = ((InternalEObject)newSolution).eInverseAdd(this, CommonPackage.SOLUTION__CIRCUIT, Solution.class, msgs);
			msgs = basicSetSolution(newSolution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__SOLUTION, newSolution, newSolution));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ControlQueue getControlQueue() {
		if (controlQueue != null && controlQueue.eIsProxy()) {
			InternalEObject oldControlQueue = (InternalEObject)controlQueue;
			controlQueue = (ControlQueue)eResolveProxy(oldControlQueue);
			if (controlQueue != oldControlQueue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT__CONTROL_QUEUE, oldControlQueue, controlQueue));
			}
		}
		return controlQueue;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ControlQueue basicGetControlQueue() {
		return controlQueue;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setControlQueue(ControlQueue newControlQueue) {
		ControlQueue oldControlQueue = controlQueue;
		controlQueue = newControlQueue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__CONTROL_QUEUE, oldControlQueue, controlQueue));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Bus> getBusList() {
		if (busList == null) {
			busList = new EObjectContainmentEList<Bus>(Bus.class, this, CommonPackage.CIRCUIT__BUS_LIST);
		}
		return busList;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Fault> getFaults() {
		if (faults == null) {
			faults = new EObjectContainmentEList<Fault>(Fault.class, this, CommonPackage.CIRCUIT__FAULTS);
		}
		return faults;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isBusNameRedefined() {
		return busNameRedefined;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBusNameRedefined(boolean newBusNameRedefined) {
		boolean oldBusNameRedefined = busNameRedefined;
		busNameRedefined = newBusNameRedefined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__BUS_NAME_REDEFINED, oldBusNameRedefined, busNameRedefined));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNumNodes() {
		return numNodes;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNumNodes(int newNumNodes) {
		int oldNumNodes = numNodes;
		numNodes = newNumNodes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NUM_NODES, oldNumNodes, numNodes));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList<CircuitElement> getCircuitElements() {
        return null;//getVoltageSources();
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<PowerDeliveryElement> getPDElements() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<PowerConversionElement> getPCElements() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<ControlElement> getDSSControls() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<MeterElement> getMeterElements() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Transformer> getSubstations() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initialiseNodeVBase() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getName() {
		return name;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NAME, oldName, name));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<VoltageSource> getVoltageSources() {
		if (voltageSources == null) {
			voltageSources = new EObjectContainmentEList<VoltageSource>(VoltageSource.class, this, CommonPackage.CIRCUIT__VOLTAGE_SOURCES);
		}
		return voltageSources;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<CurrentSource> getCurrentSources() {
		if (currentSources == null) {
			currentSources = new EObjectContainmentEList<CurrentSource>(CurrentSource.class, this, CommonPackage.CIRCUIT__CURRENT_SOURCES);
		}
		return currentSources;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Sensor> getSensors() {
		if (sensors == null) {
			sensors = new EObjectContainmentEList<Sensor>(Sensor.class, this, CommonPackage.CIRCUIT__SENSORS);
		}
		return sensors;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Monitor> getMonitors() {
		if (monitors == null) {
			monitors = new EObjectContainmentEList<Monitor>(Monitor.class, this, CommonPackage.CIRCUIT__MONITORS);
		}
		return monitors;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<EnergyMeter> getEnergyMeters() {
		if (energyMeters == null) {
			energyMeters = new EObjectContainmentEList<EnergyMeter>(EnergyMeter.class, this, CommonPackage.CIRCUIT__ENERGY_METERS);
		}
		return energyMeters;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Generator> getGenerators() {
		if (generators == null) {
			generators = new EObjectContainmentWithInverseEList<Generator>(Generator.class, this, CommonPackage.CIRCUIT__GENERATORS, ConversionPackage.GENERATOR__CIRCUIT);
		}
		return generators;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Transformer> getTransformers() {
		if (transformers == null) {
			transformers = new EObjectContainmentEList<Transformer>(Transformer.class, this, CommonPackage.CIRCUIT__TRANSFORMERS);
		}
		return transformers;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<CapacitorControl> getCapControls() {
		if (capControls == null) {
			capControls = new EObjectContainmentEList<CapacitorControl>(CapacitorControl.class, this, CommonPackage.CIRCUIT__CAP_CONTROLS);
		}
		return capControls;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<RegulatorControl> getRegControls() {
		if (regControls == null) {
			regControls = new EObjectContainmentEList<RegulatorControl>(RegulatorControl.class, this, CommonPackage.CIRCUIT__REG_CONTROLS);
		}
		return regControls;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Line> getLines() {
		if (lines == null) {
			lines = new EObjectResolvingEList<Line>(Line.class, this, CommonPackage.CIRCUIT__LINES);
		}
		return lines;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Load> getLoads() {
		if (loads == null) {
			loads = new EObjectResolvingEList<Load>(Load.class, this, CommonPackage.CIRCUIT__LOADS);
		}
		return loads;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Capacitor> getShuntCapacitors() {
		if (shuntCapacitors == null) {
			shuntCapacitors = new EObjectResolvingEList<Capacitor>(Capacitor.class, this, CommonPackage.CIRCUIT__SHUNT_CAPACITORS);
		}
		return shuntCapacitors;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Feeder> getFeeder() {
		if (feeder == null) {
			feeder = new EObjectResolvingEList<Feeder>(Feeder.class, this, CommonPackage.CIRCUIT__FEEDER);
		}
		return feeder;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getGeneratorDispatchReference() {
		return generatorDispatchReference;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGeneratorDispatchReference(double newGeneratorDispatchReference) {
		double oldGeneratorDispatchReference = generatorDispatchReference;
		generatorDispatchReference = newGeneratorDispatchReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE, oldGeneratorDispatchReference, generatorDispatchReference));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getGenMultiplier() {
		return genMultiplier;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGenMultiplier(double newGenMultiplier) {
		double oldGenMultiplier = genMultiplier;
		genMultiplier = newGenMultiplier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__GEN_MULTIPLIER, oldGenMultiplier, genMultiplier));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSolved() {
		return solved;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSolved(boolean newSolved) {
		boolean oldSolved = solved;
		solved = newSolved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__SOLVED, oldSolved, solved));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isControl_busNameRedefined() {
		return control_busNameRedefined;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setControl_busNameRedefined(boolean newControl_busNameRedefined) {
		boolean oldControl_busNameRedefined = control_busNameRedefined;
		control_busNameRedefined = newControl_busNameRedefined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED, oldControl_busNameRedefined, control_busNameRedefined));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getLoadMultiplier() {
		return loadMultiplier;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLoadMultiplier(double newLoadMultiplier) {
		double oldLoadMultiplier = loadMultiplier;
		loadMultiplier = newLoadMultiplier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__LOAD_MULTIPLIER, oldLoadMultiplier, loadMultiplier));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getDefaultGrowthFactor() {
		return defaultGrowthFactor;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDefaultGrowthFactor(double newDefaultGrowthFactor) {
		double oldDefaultGrowthFactor = defaultGrowthFactor;
		defaultGrowthFactor = newDefaultGrowthFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR, oldDefaultGrowthFactor, defaultGrowthFactor));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getDefaultHourMult() {
		return defaultHourMult;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDefaultHourMult(double newDefaultHourMult) {
		double oldDefaultHourMult = defaultHourMult;
		defaultHourMult = newDefaultHourMult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT, oldDefaultHourMult, defaultHourMult));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPriceSignal() {
		return priceSignal;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPriceSignal(double newPriceSignal) {
		double oldPriceSignal = priceSignal;
		priceSignal = newPriceSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__PRICE_SIGNAL, oldPriceSignal, priceSignal));
	}

                                                                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void buildYMatrix(yBuildOption buildOption, boolean allocateVI) {
        if (getSolution().isPreserveNodeVoltages())
            getSolution().updateVBus();

        if (isBusNameRedefined())
            reProcessBusDefs();

        if (getSolution().isFrequencyChanged()) {
            reCalcAllYPrims();
        } else {
            reCalcInvalidYPrims();
        }

        if (getSolution().isSolutionAbort())
            System.out.println("Y matrix build aborted due to error in primitive Y calculations.");

        getSolution().setFrequencyChanged(false);

        if (buildOption == yBuildOption.WHOLE_MATRIX) {
            System.out.println("Building whole Y matrix.");
        } else if (buildOption == yBuildOption.SERIES_ONLY) {
            System.out.println("Building series Y matrix.");
        }

        // Add in all YPrims for all devices.
        for (int i = 0; i < getCircuitElements().size(); i++) {
            CircuitElement element = getCircuitElements().get(i);
            if (element.isEnabled()) {
                DComplexMatrix2D yPrimValues = element.getYPrimValues(buildOption);
                // Add primitive Y to Y matrix.
//                AddPrimitiveMatrix(element.getYOrder(), yPrimValues);
            }
        }

        // Allocate voltage and current vectors if requested.
        if (allocateVI) {
            // TODO: Allocate voltage and current vectors.
        }

        if (buildOption == yBuildOption.WHOLE_MATRIX) {
            // Indicate that the series matrix may not match.
            getSolution().setSeriesYInvalid(true);
            getSolution().setSystemYChanged(false);
        } else if (buildOption == yBuildOption.SERIES_ONLY) {
            getSolution().setSeriesYInvalid(false);
            // systemYChanged unchanged.
        }

        if (getSolution().isPreserveNodeVoltages()) {
//			getSolution().restoreNodeVFromVBus();
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void reProcessBusDefs() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void reCalcAllYPrims() {
        System.out.println("Recalc All Yprims");

        for (int i = 0; i < getCircuitElements().size(); i++) {
            CircuitElement element = getCircuitElements().get(i);
            element.calcYPrim(getSolution().getFrequency());
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void reCalcInvalidYPrims() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
                @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__SOLUTION:
				if (solution != null)
					msgs = ((InternalEObject)solution).eInverseRemove(this, CommonPackage.SOLUTION__CIRCUIT, Solution.class, msgs);
				return basicSetSolution((Solution)otherEnd, msgs);
			case CommonPackage.CIRCUIT__GENERATORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGenerators()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__SOLUTION:
				return basicSetSolution(null, msgs);
			case CommonPackage.CIRCUIT__BUS_LIST:
				return ((InternalEList<?>)getBusList()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__FAULTS:
				return ((InternalEList<?>)getFaults()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				return ((InternalEList<?>)getVoltageSources()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__CURRENT_SOURCES:
				return ((InternalEList<?>)getCurrentSources()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__SENSORS:
				return ((InternalEList<?>)getSensors()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__MONITORS:
				return ((InternalEList<?>)getMonitors()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__ENERGY_METERS:
				return ((InternalEList<?>)getEnergyMeters()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__GENERATORS:
				return ((InternalEList<?>)getGenerators()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__TRANSFORMERS:
				return ((InternalEList<?>)getTransformers()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__CAP_CONTROLS:
				return ((InternalEList<?>)getCapControls()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__REG_CONTROLS:
				return ((InternalEList<?>)getRegControls()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__SOLUTION:
				if (resolve) return getSolution();
				return basicGetSolution();
			case CommonPackage.CIRCUIT__CONTROL_QUEUE:
				if (resolve) return getControlQueue();
				return basicGetControlQueue();
			case CommonPackage.CIRCUIT__BUS_LIST:
				return getBusList();
			case CommonPackage.CIRCUIT__FAULTS:
				return getFaults();
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				return getVoltageSources();
			case CommonPackage.CIRCUIT__CURRENT_SOURCES:
				return getCurrentSources();
			case CommonPackage.CIRCUIT__SENSORS:
				return getSensors();
			case CommonPackage.CIRCUIT__MONITORS:
				return getMonitors();
			case CommonPackage.CIRCUIT__ENERGY_METERS:
				return getEnergyMeters();
			case CommonPackage.CIRCUIT__GENERATORS:
				return getGenerators();
			case CommonPackage.CIRCUIT__TRANSFORMERS:
				return getTransformers();
			case CommonPackage.CIRCUIT__CAP_CONTROLS:
				return getCapControls();
			case CommonPackage.CIRCUIT__REG_CONTROLS:
				return getRegControls();
			case CommonPackage.CIRCUIT__LINES:
				return getLines();
			case CommonPackage.CIRCUIT__LOADS:
				return getLoads();
			case CommonPackage.CIRCUIT__SHUNT_CAPACITORS:
				return getShuntCapacitors();
			case CommonPackage.CIRCUIT__FEEDER:
				return getFeeder();
			case CommonPackage.CIRCUIT__NAME:
				return getName();
			case CommonPackage.CIRCUIT__NUM_NODES:
				return getNumNodes();
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				return getGeneratorDispatchReference();
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				return getGenMultiplier();
			case CommonPackage.CIRCUIT__SOLVED:
				return isSolved();
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				return isBusNameRedefined();
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				return isControl_busNameRedefined();
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				return getLoadMultiplier();
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				return getDefaultGrowthFactor();
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				return getDefaultHourMult();
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				return getPriceSignal();
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
			case CommonPackage.CIRCUIT__SOLUTION:
				setSolution((Solution)newValue);
				return;
			case CommonPackage.CIRCUIT__CONTROL_QUEUE:
				setControlQueue((ControlQueue)newValue);
				return;
			case CommonPackage.CIRCUIT__BUS_LIST:
				getBusList().clear();
				getBusList().addAll((Collection<? extends Bus>)newValue);
				return;
			case CommonPackage.CIRCUIT__FAULTS:
				getFaults().clear();
				getFaults().addAll((Collection<? extends Fault>)newValue);
				return;
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				getVoltageSources().clear();
				getVoltageSources().addAll((Collection<? extends VoltageSource>)newValue);
				return;
			case CommonPackage.CIRCUIT__CURRENT_SOURCES:
				getCurrentSources().clear();
				getCurrentSources().addAll((Collection<? extends CurrentSource>)newValue);
				return;
			case CommonPackage.CIRCUIT__SENSORS:
				getSensors().clear();
				getSensors().addAll((Collection<? extends Sensor>)newValue);
				return;
			case CommonPackage.CIRCUIT__MONITORS:
				getMonitors().clear();
				getMonitors().addAll((Collection<? extends Monitor>)newValue);
				return;
			case CommonPackage.CIRCUIT__ENERGY_METERS:
				getEnergyMeters().clear();
				getEnergyMeters().addAll((Collection<? extends EnergyMeter>)newValue);
				return;
			case CommonPackage.CIRCUIT__GENERATORS:
				getGenerators().clear();
				getGenerators().addAll((Collection<? extends Generator>)newValue);
				return;
			case CommonPackage.CIRCUIT__TRANSFORMERS:
				getTransformers().clear();
				getTransformers().addAll((Collection<? extends Transformer>)newValue);
				return;
			case CommonPackage.CIRCUIT__CAP_CONTROLS:
				getCapControls().clear();
				getCapControls().addAll((Collection<? extends CapacitorControl>)newValue);
				return;
			case CommonPackage.CIRCUIT__REG_CONTROLS:
				getRegControls().clear();
				getRegControls().addAll((Collection<? extends RegulatorControl>)newValue);
				return;
			case CommonPackage.CIRCUIT__LINES:
				getLines().clear();
				getLines().addAll((Collection<? extends Line>)newValue);
				return;
			case CommonPackage.CIRCUIT__LOADS:
				getLoads().clear();
				getLoads().addAll((Collection<? extends Load>)newValue);
				return;
			case CommonPackage.CIRCUIT__SHUNT_CAPACITORS:
				getShuntCapacitors().clear();
				getShuntCapacitors().addAll((Collection<? extends Capacitor>)newValue);
				return;
			case CommonPackage.CIRCUIT__FEEDER:
				getFeeder().clear();
				getFeeder().addAll((Collection<? extends Feeder>)newValue);
				return;
			case CommonPackage.CIRCUIT__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.CIRCUIT__NUM_NODES:
				setNumNodes((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				setGeneratorDispatchReference((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				setGenMultiplier((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__SOLVED:
				setSolved((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				setBusNameRedefined((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				setControl_busNameRedefined((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				setLoadMultiplier((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				setDefaultGrowthFactor((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				setDefaultHourMult((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				setPriceSignal((Double)newValue);
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
			case CommonPackage.CIRCUIT__SOLUTION:
				setSolution((Solution)null);
				return;
			case CommonPackage.CIRCUIT__CONTROL_QUEUE:
				setControlQueue((ControlQueue)null);
				return;
			case CommonPackage.CIRCUIT__BUS_LIST:
				getBusList().clear();
				return;
			case CommonPackage.CIRCUIT__FAULTS:
				getFaults().clear();
				return;
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				getVoltageSources().clear();
				return;
			case CommonPackage.CIRCUIT__CURRENT_SOURCES:
				getCurrentSources().clear();
				return;
			case CommonPackage.CIRCUIT__SENSORS:
				getSensors().clear();
				return;
			case CommonPackage.CIRCUIT__MONITORS:
				getMonitors().clear();
				return;
			case CommonPackage.CIRCUIT__ENERGY_METERS:
				getEnergyMeters().clear();
				return;
			case CommonPackage.CIRCUIT__GENERATORS:
				getGenerators().clear();
				return;
			case CommonPackage.CIRCUIT__TRANSFORMERS:
				getTransformers().clear();
				return;
			case CommonPackage.CIRCUIT__CAP_CONTROLS:
				getCapControls().clear();
				return;
			case CommonPackage.CIRCUIT__REG_CONTROLS:
				getRegControls().clear();
				return;
			case CommonPackage.CIRCUIT__LINES:
				getLines().clear();
				return;
			case CommonPackage.CIRCUIT__LOADS:
				getLoads().clear();
				return;
			case CommonPackage.CIRCUIT__SHUNT_CAPACITORS:
				getShuntCapacitors().clear();
				return;
			case CommonPackage.CIRCUIT__FEEDER:
				getFeeder().clear();
				return;
			case CommonPackage.CIRCUIT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__NUM_NODES:
				setNumNodes(NUM_NODES_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				setGeneratorDispatchReference(GENERATOR_DISPATCH_REFERENCE_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				setGenMultiplier(GEN_MULTIPLIER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__SOLVED:
				setSolved(SOLVED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				setBusNameRedefined(BUS_NAME_REDEFINED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				setControl_busNameRedefined(CONTROL_BUS_NAME_REDEFINED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				setLoadMultiplier(LOAD_MULTIPLIER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				setDefaultGrowthFactor(DEFAULT_GROWTH_FACTOR_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				setDefaultHourMult(DEFAULT_HOUR_MULT_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				setPriceSignal(PRICE_SIGNAL_EDEFAULT);
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
			case CommonPackage.CIRCUIT__SOLUTION:
				return solution != null;
			case CommonPackage.CIRCUIT__CONTROL_QUEUE:
				return controlQueue != null;
			case CommonPackage.CIRCUIT__BUS_LIST:
				return busList != null && !busList.isEmpty();
			case CommonPackage.CIRCUIT__FAULTS:
				return faults != null && !faults.isEmpty();
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				return voltageSources != null && !voltageSources.isEmpty();
			case CommonPackage.CIRCUIT__CURRENT_SOURCES:
				return currentSources != null && !currentSources.isEmpty();
			case CommonPackage.CIRCUIT__SENSORS:
				return sensors != null && !sensors.isEmpty();
			case CommonPackage.CIRCUIT__MONITORS:
				return monitors != null && !monitors.isEmpty();
			case CommonPackage.CIRCUIT__ENERGY_METERS:
				return energyMeters != null && !energyMeters.isEmpty();
			case CommonPackage.CIRCUIT__GENERATORS:
				return generators != null && !generators.isEmpty();
			case CommonPackage.CIRCUIT__TRANSFORMERS:
				return transformers != null && !transformers.isEmpty();
			case CommonPackage.CIRCUIT__CAP_CONTROLS:
				return capControls != null && !capControls.isEmpty();
			case CommonPackage.CIRCUIT__REG_CONTROLS:
				return regControls != null && !regControls.isEmpty();
			case CommonPackage.CIRCUIT__LINES:
				return lines != null && !lines.isEmpty();
			case CommonPackage.CIRCUIT__LOADS:
				return loads != null && !loads.isEmpty();
			case CommonPackage.CIRCUIT__SHUNT_CAPACITORS:
				return shuntCapacitors != null && !shuntCapacitors.isEmpty();
			case CommonPackage.CIRCUIT__FEEDER:
				return feeder != null && !feeder.isEmpty();
			case CommonPackage.CIRCUIT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.CIRCUIT__NUM_NODES:
				return numNodes != NUM_NODES_EDEFAULT;
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				return generatorDispatchReference != GENERATOR_DISPATCH_REFERENCE_EDEFAULT;
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				return genMultiplier != GEN_MULTIPLIER_EDEFAULT;
			case CommonPackage.CIRCUIT__SOLVED:
				return solved != SOLVED_EDEFAULT;
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				return busNameRedefined != BUS_NAME_REDEFINED_EDEFAULT;
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				return control_busNameRedefined != CONTROL_BUS_NAME_REDEFINED_EDEFAULT;
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				return loadMultiplier != LOAD_MULTIPLIER_EDEFAULT;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				return defaultGrowthFactor != DEFAULT_GROWTH_FACTOR_EDEFAULT;
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				return defaultHourMult != DEFAULT_HOUR_MULT_EDEFAULT;
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				return priceSignal != PRICE_SIGNAL_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", numNodes: ");
		result.append(numNodes);
		result.append(", generatorDispatchReference: ");
		result.append(generatorDispatchReference);
		result.append(", genMultiplier: ");
		result.append(genMultiplier);
		result.append(", solved: ");
		result.append(solved);
		result.append(", busNameRedefined: ");
		result.append(busNameRedefined);
		result.append(", control_busNameRedefined: ");
		result.append(control_busNameRedefined);
		result.append(", loadMultiplier: ");
		result.append(loadMultiplier);
		result.append(", defaultGrowthFactor: ");
		result.append(defaultGrowthFactor);
		result.append(", defaultHourMult: ");
		result.append(defaultHourMult);
		result.append(", priceSignal: ");
		result.append(priceSignal);
		result.append(')');
		return result.toString();
	}

} //CircuitImpl
