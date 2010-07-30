/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import java.util.Collection;

import java.util.Map;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CircuitElement;
import electrickery.common.CommonFactory;
import electrickery.common.CommonPackage;
import electrickery.common.Parser;
import electrickery.common.Solution;
import electrickery.common.yBuildOption;
import electrickery.conversion.VoltageSource;
import electrickery.executive.Executive;
import electrickery.executive.ExecutivePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Circuit</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getExecutive <em>Executive</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getSolution <em>Solution</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getBuses <em>Buses</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getMapNodeToBus <em>Map Node To Bus</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getBusList <em>Bus List</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getVoltageSources <em>Voltage Sources</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getActiveCircuitElement <em>Active Circuit Element</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getName <em>Name</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getNumNodes <em>Num Nodes</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getNumBuses <em>Num Buses</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#isBusNameRedefined <em>Bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#isSolved <em>Solved</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getLoadMultiplier <em>Load Multiplier</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getDefaultGrowthFactor <em>Default Growth Factor</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getDefaultGrowthRate <em>Default Growth Rate</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getGenMultiplier <em>Gen Multiplier</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getDefaultHourMult <em>Default Hour Mult</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#isControl_busNameRedefined <em>Control bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getPriceSignal <em>Price Signal</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getEmergMinVolts <em>Emerg Min Volts</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getEmergMaxVolts <em>Emerg Max Volts</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getNormalMinVolts <em>Normal Min Volts</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitImpl#getNormalMaxVolts <em>Normal Max Volts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CircuitImpl extends EObjectImpl implements Circuit {
    /**
	 * The cached value of the '{@link #getSolution() <em>Solution</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSolution()
	 * @generated
	 * @ordered
	 */
    protected Solution solution;

    /**
	 * The cached value of the '{@link #getBuses() <em>Buses</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBuses()
	 * @generated
	 * @ordered
	 */
    protected EList<Bus> buses;

    /**
	 * The cached value of the '{@link #getMapNodeToBus() <em>Map Node To Bus</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMapNodeToBus()
	 * @generated
	 * @ordered
	 */
    protected Map<Integer, Bus> mapNodeToBus;

    /**
	 * The cached value of the '{@link #getBusList() <em>Bus List</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBusList()
	 * @generated
	 * @ordered
	 */
    protected EList<String> busList;

    /**
	 * The cached value of the '{@link #getVoltageSources() <em>Voltage Sources</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVoltageSources()
	 * @generated
	 * @ordered
	 */
    protected EList<VoltageSource> voltageSources;

    /**
	 * The cached value of the '{@link #getActiveCircuitElement() <em>Active Circuit Element</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getActiveCircuitElement()
	 * @generated
	 * @ordered
	 */
    protected CircuitElement activeCircuitElement;

    /**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
    protected static final String NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
    protected String name = NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #getNumNodes() <em>Num Nodes</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNumNodes()
	 * @generated
	 * @ordered
	 */
    protected static final int NUM_NODES_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getNumNodes() <em>Num Nodes</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNumNodes()
	 * @generated
	 * @ordered
	 */
    protected int numNodes = NUM_NODES_EDEFAULT;

    /**
	 * The default value of the '{@link #getNumBuses() <em>Num Buses</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNumBuses()
	 * @generated
	 * @ordered
	 */
    protected static final int NUM_BUSES_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getNumBuses() <em>Num Buses</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNumBuses()
	 * @generated
	 * @ordered
	 */
    protected int numBuses = NUM_BUSES_EDEFAULT;

    /**
	 * The default value of the '{@link #isBusNameRedefined() <em>Bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isBusNameRedefined()
	 * @generated
	 * @ordered
	 */
    protected static final boolean BUS_NAME_REDEFINED_EDEFAULT = true;

    /**
	 * The cached value of the '{@link #isBusNameRedefined() <em>Bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isBusNameRedefined()
	 * @generated
	 * @ordered
	 */
    protected boolean busNameRedefined = BUS_NAME_REDEFINED_EDEFAULT;

    /**
	 * The default value of the '{@link #isSolved() <em>Solved</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSolved()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SOLVED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isSolved() <em>Solved</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSolved()
	 * @generated
	 * @ordered
	 */
    protected boolean solved = SOLVED_EDEFAULT;

    /**
	 * The default value of the '{@link #getLoadMultiplier() <em>Load Multiplier</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLoadMultiplier()
	 * @generated
	 * @ordered
	 */
    protected static final double LOAD_MULTIPLIER_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getLoadMultiplier() <em>Load Multiplier</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLoadMultiplier()
	 * @generated
	 * @ordered
	 */
    protected double loadMultiplier = LOAD_MULTIPLIER_EDEFAULT;

    /**
	 * The default value of the '{@link #getDefaultGrowthFactor() <em>Default Growth Factor</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefaultGrowthFactor()
	 * @generated
	 * @ordered
	 */
    protected static final double DEFAULT_GROWTH_FACTOR_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getDefaultGrowthFactor() <em>Default Growth Factor</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefaultGrowthFactor()
	 * @generated
	 * @ordered
	 */
    protected double defaultGrowthFactor = DEFAULT_GROWTH_FACTOR_EDEFAULT;

    /**
	 * The default value of the '{@link #getDefaultGrowthRate() <em>Default Growth Rate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefaultGrowthRate()
	 * @generated
	 * @ordered
	 */
    protected static final double DEFAULT_GROWTH_RATE_EDEFAULT = 1.025;

    /**
	 * The cached value of the '{@link #getDefaultGrowthRate() <em>Default Growth Rate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefaultGrowthRate()
	 * @generated
	 * @ordered
	 */
    protected double defaultGrowthRate = DEFAULT_GROWTH_RATE_EDEFAULT;

    /**
	 * The default value of the '{@link #getGeneratorDispatchReference() <em>Generator Dispatch Reference</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGeneratorDispatchReference()
	 * @generated
	 * @ordered
	 */
    protected static final double GENERATOR_DISPATCH_REFERENCE_EDEFAULT = 1000.0;

    /**
	 * The cached value of the '{@link #getGeneratorDispatchReference() <em>Generator Dispatch Reference</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGeneratorDispatchReference()
	 * @generated
	 * @ordered
	 */
    protected double generatorDispatchReference = GENERATOR_DISPATCH_REFERENCE_EDEFAULT;

    /**
	 * The default value of the '{@link #getGenMultiplier() <em>Gen Multiplier</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGenMultiplier()
	 * @generated
	 * @ordered
	 */
    protected static final double GEN_MULTIPLIER_EDEFAULT = 1.0;

    /**
	 * The cached value of the '{@link #getGenMultiplier() <em>Gen Multiplier</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGenMultiplier()
	 * @generated
	 * @ordered
	 */
    protected double genMultiplier = GEN_MULTIPLIER_EDEFAULT;

    /**
	 * The default value of the '{@link #getDefaultHourMult() <em>Default Hour Mult</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefaultHourMult()
	 * @generated
	 * @ordered
	 */
    protected static final double DEFAULT_HOUR_MULT_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getDefaultHourMult() <em>Default Hour Mult</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefaultHourMult()
	 * @generated
	 * @ordered
	 */
    protected double defaultHourMult = DEFAULT_HOUR_MULT_EDEFAULT;

    /**
	 * The default value of the '{@link #isControl_busNameRedefined() <em>Control bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isControl_busNameRedefined()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CONTROL_BUS_NAME_REDEFINED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isControl_busNameRedefined() <em>Control bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isControl_busNameRedefined()
	 * @generated
	 * @ordered
	 */
    protected boolean control_busNameRedefined = CONTROL_BUS_NAME_REDEFINED_EDEFAULT;

    /**
	 * The default value of the '{@link #getPriceSignal() <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPriceSignal()
	 * @generated
	 * @ordered
	 */
    protected static final double PRICE_SIGNAL_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getPriceSignal() <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPriceSignal()
	 * @generated
	 * @ordered
	 */
    protected double priceSignal = PRICE_SIGNAL_EDEFAULT;

    /**
	 * The default value of the '{@link #getEmergMinVolts() <em>Emerg Min Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEmergMinVolts()
	 * @generated
	 * @ordered
	 */
    protected static final double EMERG_MIN_VOLTS_EDEFAULT = 0.9;

    /**
	 * The cached value of the '{@link #getEmergMinVolts() <em>Emerg Min Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEmergMinVolts()
	 * @generated
	 * @ordered
	 */
    protected double emergMinVolts = EMERG_MIN_VOLTS_EDEFAULT;

    /**
	 * The default value of the '{@link #getEmergMaxVolts() <em>Emerg Max Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEmergMaxVolts()
	 * @generated
	 * @ordered
	 */
    protected static final double EMERG_MAX_VOLTS_EDEFAULT = 1.08;

    /**
	 * The cached value of the '{@link #getEmergMaxVolts() <em>Emerg Max Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEmergMaxVolts()
	 * @generated
	 * @ordered
	 */
    protected double emergMaxVolts = EMERG_MAX_VOLTS_EDEFAULT;

    /**
	 * The default value of the '{@link #getNormalMinVolts() <em>Normal Min Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNormalMinVolts()
	 * @generated
	 * @ordered
	 */
    protected static final double NORMAL_MIN_VOLTS_EDEFAULT = 0.95;

    /**
	 * The cached value of the '{@link #getNormalMinVolts() <em>Normal Min Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNormalMinVolts()
	 * @generated
	 * @ordered
	 */
    protected double normalMinVolts = NORMAL_MIN_VOLTS_EDEFAULT;

    /**
	 * The default value of the '{@link #getNormalMaxVolts() <em>Normal Max Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNormalMaxVolts()
	 * @generated
	 * @ordered
	 */
    protected static final double NORMAL_MAX_VOLTS_EDEFAULT = 1.05;

    /**
	 * The cached value of the '{@link #getNormalMaxVolts() <em>Normal Max Volts</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNormalMaxVolts()
	 * @generated
	 * @ordered
	 */
    protected double normalMaxVolts = NORMAL_MAX_VOLTS_EDEFAULT;

    /**
     * A place to hold the nodes.
     */
    private int[] nodeBuffer = new int[20];

    /**
     * Temp arrays for when the bus swap takes place.
     */
    private EList<Bus> savedBuses;
    private EList<String> savedBusNames;
    private int savedNumBuses;

    private boolean abortBusProcess;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected CircuitImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return CommonPackage.Literals.CIRCUIT;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public Executive getExecutive() {
		if (eContainerFeatureID() != CommonPackage.CIRCUIT__EXECUTIVE) return null;
		return (Executive)eContainer();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetExecutive(Executive newExecutive, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExecutive, CommonPackage.CIRCUIT__EXECUTIVE, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setExecutive(Executive newExecutive) {
		if (newExecutive != eInternalContainer() || (eContainerFeatureID() != CommonPackage.CIRCUIT__EXECUTIVE && newExecutive != null)) {
			if (EcoreUtil.isAncestor(this, newExecutive))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExecutive != null)
				msgs = ((InternalEObject)newExecutive).eInverseAdd(this, ExecutivePackage.EXECUTIVE__CIRCUITS, Executive.class, msgs);
			msgs = basicSetExecutive(newExecutive, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__EXECUTIVE, newExecutive, newExecutive));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public Solution basicGetSolution() {
		return solution;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Bus> getBuses() {
		if (buses == null) {
			buses = new EObjectContainmentWithInverseEList<Bus>(Bus.class, this, CommonPackage.CIRCUIT__BUSES, CommonPackage.BUS__CIRCUIT);
		}
		return buses;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public Map<Integer, Bus> getMapNodeToBus() {
		return mapNodeToBus;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setMapNodeToBus(Map<Integer, Bus> newMapNodeToBus) {
		Map<Integer, Bus> oldMapNodeToBus = mapNodeToBus;
		mapNodeToBus = newMapNodeToBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__MAP_NODE_TO_BUS, oldMapNodeToBus, mapNodeToBus));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public EList<String> getBusList() {
		if (busList == null) {
			busList = new EDataTypeUniqueEList<String>(String.class, this, CommonPackage.CIRCUIT__BUS_LIST);
		}
		return busList;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isBusNameRedefined() {
		return busNameRedefined;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setBusNameRedefined(boolean newBusNameRedefined) {
		boolean oldBusNameRedefined = busNameRedefined;
		busNameRedefined = newBusNameRedefined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__BUS_NAME_REDEFINED, oldBusNameRedefined, busNameRedefined));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSolved() {
		return solved;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setSolved(boolean newSolved) {
		boolean oldSolved = solved;
		solved = newSolved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__SOLVED, oldSolved, solved));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getLoadMultiplier() {
		return loadMultiplier;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setLoadMultiplier(double newLoadMultiplier) {
		double oldLoadMultiplier = loadMultiplier;
		loadMultiplier = newLoadMultiplier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__LOAD_MULTIPLIER, oldLoadMultiplier, loadMultiplier));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getDefaultGrowthFactor() {
		return defaultGrowthFactor;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setDefaultGrowthFactor(double newDefaultGrowthFactor) {
		double oldDefaultGrowthFactor = defaultGrowthFactor;
		defaultGrowthFactor = newDefaultGrowthFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR, oldDefaultGrowthFactor, defaultGrowthFactor));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getDefaultGrowthRate() {
		return defaultGrowthRate;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setDefaultGrowthRate(double newDefaultGrowthRate) {
		double oldDefaultGrowthRate = defaultGrowthRate;
		defaultGrowthRate = newDefaultGrowthRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__DEFAULT_GROWTH_RATE, oldDefaultGrowthRate, defaultGrowthRate));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getGeneratorDispatchReference() {
		return generatorDispatchReference;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setGeneratorDispatchReference(double newGeneratorDispatchReference) {
		double oldGeneratorDispatchReference = generatorDispatchReference;
		generatorDispatchReference = newGeneratorDispatchReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE, oldGeneratorDispatchReference, generatorDispatchReference));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getGenMultiplier() {
		return genMultiplier;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setGenMultiplier(double newGenMultiplier) {
		double oldGenMultiplier = genMultiplier;
		genMultiplier = newGenMultiplier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__GEN_MULTIPLIER, oldGenMultiplier, genMultiplier));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getDefaultHourMult() {
		return defaultHourMult;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setDefaultHourMult(double newDefaultHourMult) {
		double oldDefaultHourMult = defaultHourMult;
		defaultHourMult = newDefaultHourMult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT, oldDefaultHourMult, defaultHourMult));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isControl_busNameRedefined() {
		return control_busNameRedefined;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setControl_busNameRedefined(boolean newControl_busNameRedefined) {
		boolean oldControl_busNameRedefined = control_busNameRedefined;
		control_busNameRedefined = newControl_busNameRedefined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED, oldControl_busNameRedefined, control_busNameRedefined));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getPriceSignal() {
		return priceSignal;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setPriceSignal(double newPriceSignal) {
		double oldPriceSignal = priceSignal;
		priceSignal = newPriceSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__PRICE_SIGNAL, oldPriceSignal, priceSignal));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getEmergMinVolts() {
		return emergMinVolts;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setEmergMinVolts(double newEmergMinVolts) {
		double oldEmergMinVolts = emergMinVolts;
		emergMinVolts = newEmergMinVolts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__EMERG_MIN_VOLTS, oldEmergMinVolts, emergMinVolts));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getEmergMaxVolts() {
		return emergMaxVolts;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setEmergMaxVolts(double newEmergMaxVolts) {
		double oldEmergMaxVolts = emergMaxVolts;
		emergMaxVolts = newEmergMaxVolts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__EMERG_MAX_VOLTS, oldEmergMaxVolts, emergMaxVolts));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getNormalMinVolts() {
		return normalMinVolts;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setNormalMinVolts(double newNormalMinVolts) {
		double oldNormalMinVolts = normalMinVolts;
		normalMinVolts = newNormalMinVolts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NORMAL_MIN_VOLTS, oldNormalMinVolts, normalMinVolts));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public double getNormalMaxVolts() {
		return normalMaxVolts;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setNormalMaxVolts(double newNormalMaxVolts) {
		double oldNormalMaxVolts = normalMaxVolts;
		normalMaxVolts = newNormalMaxVolts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NORMAL_MAX_VOLTS, oldNormalMaxVolts, normalMaxVolts));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public int getNumNodes() {
		return numNodes;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setNumNodes(int newNumNodes) {
		int oldNumNodes = numNodes;
		numNodes = newNumNodes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NUM_NODES, oldNumNodes, numNodes));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public int getNumBuses() {
		return numBuses;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setNumBuses(int newNumBuses) {
		int oldNumBuses = numBuses;
		numBuses = newNumBuses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NUM_BUSES, oldNumBuses, numBuses));
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public EList<CircuitElement> getCircuitElements() {
        return null;// getVoltageSources();
    }

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void initialiseNodeVBase() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public String getName() {
		return name;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__NAME, oldName, name));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public EList<VoltageSource> getVoltageSources() {
		if (voltageSources == null) {
			voltageSources = new EObjectContainmentEList<VoltageSource>(VoltageSource.class, this, CommonPackage.CIRCUIT__VOLTAGE_SOURCES);
		}
		return voltageSources;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public CircuitElement getActiveCircuitElement() {
		if (activeCircuitElement != null && activeCircuitElement.eIsProxy()) {
			InternalEObject oldActiveCircuitElement = (InternalEObject)activeCircuitElement;
			activeCircuitElement = (CircuitElement)eResolveProxy(oldActiveCircuitElement);
			if (activeCircuitElement != oldActiveCircuitElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT__ACTIVE_CIRCUIT_ELEMENT, oldActiveCircuitElement, activeCircuitElement));
			}
		}
		return activeCircuitElement;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public CircuitElement basicGetActiveCircuitElement() {
		return activeCircuitElement;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setActiveCircuitElement(CircuitElement newActiveCircuitElement) {
		CircuitElement oldActiveCircuitElement = activeCircuitElement;
		activeCircuitElement = newActiveCircuitElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT__ACTIVE_CIRCUIT_ELEMENT, oldActiveCircuitElement, activeCircuitElement));
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void reProcessBusDefs() {
        // Keeps present definitions of bus objects until new ones created.
        saveBusInfo();

        // Clear hash list of Bus names for adding more.
        getBusList().clear();

        // Leave allocations same, but start count over.
        setNumBuses(0);
        setNumNodes(0);

        // Redo all enabled circuit elements.
        CircuitElement cktElementSave = getActiveCircuitElement();
        for (int i = 0; i < getCircuitElements().size(); i++) {
            CircuitElement cktElement = getCircuitElements().get(i);
            if (cktElement.isEnabled()) {
                setActiveCircuitElement(cktElement);
                processBusDefs();
                if (abortBusProcess)
                    return;
            }
        }

        setActiveCircuitElement(cktElementSave); // restore active circuit element

        for (Bus bus : getBuses()) {
            bus.allocateBusVoltages();
            bus.allocateBusCurrents();
        }

        restoreBusInfo();
        // doResetMeterZones();

        busNameRedefined = false;
    }

    /**
     * Saves existing bus definitions and names for info that needs to be restored.
     */
    protected void saveBusInfo() {
        int nb = getBusList().size();
        savedBuses = new BasicEList<Bus>(nb);
        savedBusNames = new BasicEList<String>(nb);
        for (int i = 0; i < nb; i++) {
            Bus bus = getBuses().get(i);
            savedBuses.add(bus);
            savedBusNames.add(bus.getName());
        }
        savedNumBuses = nb;
    }

    /**
     * Restores kV bases, other values to buses still in the list.
     */
    protected void restoreBusInfo() {
        for (int i = 0; i < savedNumBuses; i++) {
            // TODO: Implement bus list hash map.
            String name = savedBusNames.get(i);
            int idx;
            for (idx = 0; idx < getBuses().size(); idx++) {
                if (getBuses().get(idx).getName() == name)
                    break;
            }
            Bus bus = getBuses().get(idx);
            Bus pBus = savedBuses.get(i);
            bus.setKVBase(pBus.getKVBase());
            bus.setX(pBus.getX());
            bus.setY(pBus.getY());
            bus.setCoordDefined(pBus.isCoordDefined());
            bus.setKeep(pBus.isKeep());
            // Restore voltages in new bus def that existed in old bus def.
            if (pBus.getVBus() != null) {
                for (int j = 0; j < pBus.getNumNodesThisBus(); j++) {
                    int jdx = bus.findIdx(pBus.getNum(j));
                    if (jdx > 0)
                        bus.getVBus().set(jdx, pBus.getVBus().get(j));
                }
            }
            savedBusNames.remove(i);
        }
        savedBuses.clear();
        savedBusNames.clear();
    }

    protected void processBusDefs() {
        int nNodes = 0, np, nCond, iTerm, retVal;
        boolean nodesOK;

        CircuitElement ce = getActiveCircuitElement();
        np = ce.getNPhases();
        nCond = ce.getNConds();

        Parser parser = CommonFactory.eINSTANCE.createParser();
        parser.setToken(ce.getFirstBus());

        for (iTerm = 0; iTerm < ce.getNTerms(); iTerm++) {
            nodesOK = true;
            // Assume normal phase rotation for default.
            for (int i = 0; i < np; i++)
                nodeBuffer[i] = i; // set up buffer with defaults

            // Default all other conductors to a ground connection
            // If user wants them ungrounded, must be specified explicitly!
            for (int i = np + 1; i < nCond; i++)
                nodeBuffer[i] = 0;

            String busName = parser.parseAsBusName(nNodes, nodeBuffer);

            // Check for error in node specification.
            for (int i = 0; i < nodeBuffer.length; i++) {
                if (nodeBuffer[i] < 0) {
                    nodesOK = false;
                    abortBusProcess = true;
                    throw new UnsupportedOperationException();
                }
            }

            // Node-terminal connnections.
            ce.setActiveTerminalIndex(iTerm);
            ce.getActiveTerminal().setBusRef(addBus(busName, nCond));
            ce.setNodeRef(iTerm, nodeBuffer);

            parser.setToken(ce.getNextBus());
        }
    }

    protected int addBus(String busName, int nNodes) {
        if (busName.length() == 0) {
            System.err.println("Invalid bus name.");
            for (int i = 0; i < getActiveCircuitElement().getNConds(); i++)
                nodeBuffer[i] = 0;
            return -1;
        }

        int idx;
        for (idx = 0; idx < getBusList().size(); idx++) {
            Bus bus = getBuses().get(idx);
            if (bus.getName() == busName)
                break;
        }
        Bus bus = CommonFactory.eINSTANCE.createBus();
        bus.setName(busName);
        getBuses().add(bus);

        // Define nodes belonging to the bus.
        // Replace nodeBuffer values with global reference number.
        for (int i = 0; i < nNodes; i++) {
            int nodeRef = bus.add(nodeBuffer[i]);
            if (nodeRef == nNodes) {
                // This was a new node so Add a NodeToBus element.
                getMapNodeToBus().get(numNodes).setBusRef(idx);
                getMapNodeToBus().get(numNodes).setNodeNum(nodeBuffer[idx]);
            }
            nodeBuffer[i] = nodeRef; // Swap out in preparation to setNodeRef call.
        }

        return getBusList().size() - 1;
    }

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExecutive((Executive)otherEnd, msgs);
			case CommonPackage.CIRCUIT__SOLUTION:
				if (solution != null)
					msgs = ((InternalEObject)solution).eInverseRemove(this, CommonPackage.SOLUTION__CIRCUIT, Solution.class, msgs);
				return basicSetSolution((Solution)otherEnd, msgs);
			case CommonPackage.CIRCUIT__BUSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBuses()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				return basicSetExecutive(null, msgs);
			case CommonPackage.CIRCUIT__SOLUTION:
				return basicSetSolution(null, msgs);
			case CommonPackage.CIRCUIT__BUSES:
				return ((InternalEList<?>)getBuses()).basicRemove(otherEnd, msgs);
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				return ((InternalEList<?>)getVoltageSources()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				return eInternalContainer().eInverseRemove(this, ExecutivePackage.EXECUTIVE__CIRCUITS, Executive.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				return getExecutive();
			case CommonPackage.CIRCUIT__SOLUTION:
				if (resolve) return getSolution();
				return basicGetSolution();
			case CommonPackage.CIRCUIT__BUSES:
				return getBuses();
			case CommonPackage.CIRCUIT__MAP_NODE_TO_BUS:
				return getMapNodeToBus();
			case CommonPackage.CIRCUIT__BUS_LIST:
				return getBusList();
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				return getVoltageSources();
			case CommonPackage.CIRCUIT__ACTIVE_CIRCUIT_ELEMENT:
				if (resolve) return getActiveCircuitElement();
				return basicGetActiveCircuitElement();
			case CommonPackage.CIRCUIT__NAME:
				return getName();
			case CommonPackage.CIRCUIT__NUM_NODES:
				return getNumNodes();
			case CommonPackage.CIRCUIT__NUM_BUSES:
				return getNumBuses();
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				return isBusNameRedefined();
			case CommonPackage.CIRCUIT__SOLVED:
				return isSolved();
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				return getLoadMultiplier();
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				return getDefaultGrowthFactor();
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_RATE:
				return getDefaultGrowthRate();
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				return getGeneratorDispatchReference();
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				return getGenMultiplier();
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				return getDefaultHourMult();
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				return isControl_busNameRedefined();
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				return getPriceSignal();
			case CommonPackage.CIRCUIT__EMERG_MIN_VOLTS:
				return getEmergMinVolts();
			case CommonPackage.CIRCUIT__EMERG_MAX_VOLTS:
				return getEmergMaxVolts();
			case CommonPackage.CIRCUIT__NORMAL_MIN_VOLTS:
				return getNormalMinVolts();
			case CommonPackage.CIRCUIT__NORMAL_MAX_VOLTS:
				return getNormalMaxVolts();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				setExecutive((Executive)newValue);
				return;
			case CommonPackage.CIRCUIT__SOLUTION:
				setSolution((Solution)newValue);
				return;
			case CommonPackage.CIRCUIT__BUSES:
				getBuses().clear();
				getBuses().addAll((Collection<? extends Bus>)newValue);
				return;
			case CommonPackage.CIRCUIT__MAP_NODE_TO_BUS:
				setMapNodeToBus((Map<Integer, Bus>)newValue);
				return;
			case CommonPackage.CIRCUIT__BUS_LIST:
				getBusList().clear();
				getBusList().addAll((Collection<? extends String>)newValue);
				return;
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				getVoltageSources().clear();
				getVoltageSources().addAll((Collection<? extends VoltageSource>)newValue);
				return;
			case CommonPackage.CIRCUIT__ACTIVE_CIRCUIT_ELEMENT:
				setActiveCircuitElement((CircuitElement)newValue);
				return;
			case CommonPackage.CIRCUIT__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.CIRCUIT__NUM_NODES:
				setNumNodes((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT__NUM_BUSES:
				setNumBuses((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				setBusNameRedefined((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT__SOLVED:
				setSolved((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				setLoadMultiplier((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				setDefaultGrowthFactor((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_RATE:
				setDefaultGrowthRate((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				setGeneratorDispatchReference((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				setGenMultiplier((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				setDefaultHourMult((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				setControl_busNameRedefined((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				setPriceSignal((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__EMERG_MIN_VOLTS:
				setEmergMinVolts((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__EMERG_MAX_VOLTS:
				setEmergMaxVolts((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__NORMAL_MIN_VOLTS:
				setNormalMinVolts((Double)newValue);
				return;
			case CommonPackage.CIRCUIT__NORMAL_MAX_VOLTS:
				setNormalMaxVolts((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				setExecutive((Executive)null);
				return;
			case CommonPackage.CIRCUIT__SOLUTION:
				setSolution((Solution)null);
				return;
			case CommonPackage.CIRCUIT__BUSES:
				getBuses().clear();
				return;
			case CommonPackage.CIRCUIT__MAP_NODE_TO_BUS:
				setMapNodeToBus((Map<Integer, Bus>)null);
				return;
			case CommonPackage.CIRCUIT__BUS_LIST:
				getBusList().clear();
				return;
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				getVoltageSources().clear();
				return;
			case CommonPackage.CIRCUIT__ACTIVE_CIRCUIT_ELEMENT:
				setActiveCircuitElement((CircuitElement)null);
				return;
			case CommonPackage.CIRCUIT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__NUM_NODES:
				setNumNodes(NUM_NODES_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__NUM_BUSES:
				setNumBuses(NUM_BUSES_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				setBusNameRedefined(BUS_NAME_REDEFINED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__SOLVED:
				setSolved(SOLVED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				setLoadMultiplier(LOAD_MULTIPLIER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				setDefaultGrowthFactor(DEFAULT_GROWTH_FACTOR_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_RATE:
				setDefaultGrowthRate(DEFAULT_GROWTH_RATE_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				setGeneratorDispatchReference(GENERATOR_DISPATCH_REFERENCE_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				setGenMultiplier(GEN_MULTIPLIER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				setDefaultHourMult(DEFAULT_HOUR_MULT_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				setControl_busNameRedefined(CONTROL_BUS_NAME_REDEFINED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				setPriceSignal(PRICE_SIGNAL_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__EMERG_MIN_VOLTS:
				setEmergMinVolts(EMERG_MIN_VOLTS_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__EMERG_MAX_VOLTS:
				setEmergMaxVolts(EMERG_MAX_VOLTS_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__NORMAL_MIN_VOLTS:
				setNormalMinVolts(NORMAL_MIN_VOLTS_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT__NORMAL_MAX_VOLTS:
				setNormalMaxVolts(NORMAL_MAX_VOLTS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.CIRCUIT__EXECUTIVE:
				return getExecutive() != null;
			case CommonPackage.CIRCUIT__SOLUTION:
				return solution != null;
			case CommonPackage.CIRCUIT__BUSES:
				return buses != null && !buses.isEmpty();
			case CommonPackage.CIRCUIT__MAP_NODE_TO_BUS:
				return mapNodeToBus != null;
			case CommonPackage.CIRCUIT__BUS_LIST:
				return busList != null && !busList.isEmpty();
			case CommonPackage.CIRCUIT__VOLTAGE_SOURCES:
				return voltageSources != null && !voltageSources.isEmpty();
			case CommonPackage.CIRCUIT__ACTIVE_CIRCUIT_ELEMENT:
				return activeCircuitElement != null;
			case CommonPackage.CIRCUIT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.CIRCUIT__NUM_NODES:
				return numNodes != NUM_NODES_EDEFAULT;
			case CommonPackage.CIRCUIT__NUM_BUSES:
				return numBuses != NUM_BUSES_EDEFAULT;
			case CommonPackage.CIRCUIT__BUS_NAME_REDEFINED:
				return busNameRedefined != BUS_NAME_REDEFINED_EDEFAULT;
			case CommonPackage.CIRCUIT__SOLVED:
				return solved != SOLVED_EDEFAULT;
			case CommonPackage.CIRCUIT__LOAD_MULTIPLIER:
				return loadMultiplier != LOAD_MULTIPLIER_EDEFAULT;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_FACTOR:
				return defaultGrowthFactor != DEFAULT_GROWTH_FACTOR_EDEFAULT;
			case CommonPackage.CIRCUIT__DEFAULT_GROWTH_RATE:
				return defaultGrowthRate != DEFAULT_GROWTH_RATE_EDEFAULT;
			case CommonPackage.CIRCUIT__GENERATOR_DISPATCH_REFERENCE:
				return generatorDispatchReference != GENERATOR_DISPATCH_REFERENCE_EDEFAULT;
			case CommonPackage.CIRCUIT__GEN_MULTIPLIER:
				return genMultiplier != GEN_MULTIPLIER_EDEFAULT;
			case CommonPackage.CIRCUIT__DEFAULT_HOUR_MULT:
				return defaultHourMult != DEFAULT_HOUR_MULT_EDEFAULT;
			case CommonPackage.CIRCUIT__CONTROL_BUS_NAME_REDEFINED:
				return control_busNameRedefined != CONTROL_BUS_NAME_REDEFINED_EDEFAULT;
			case CommonPackage.CIRCUIT__PRICE_SIGNAL:
				return priceSignal != PRICE_SIGNAL_EDEFAULT;
			case CommonPackage.CIRCUIT__EMERG_MIN_VOLTS:
				return emergMinVolts != EMERG_MIN_VOLTS_EDEFAULT;
			case CommonPackage.CIRCUIT__EMERG_MAX_VOLTS:
				return emergMaxVolts != EMERG_MAX_VOLTS_EDEFAULT;
			case CommonPackage.CIRCUIT__NORMAL_MIN_VOLTS:
				return normalMinVolts != NORMAL_MIN_VOLTS_EDEFAULT;
			case CommonPackage.CIRCUIT__NORMAL_MAX_VOLTS:
				return normalMaxVolts != NORMAL_MAX_VOLTS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mapNodeToBus: ");
		result.append(mapNodeToBus);
		result.append(", busList: ");
		result.append(busList);
		result.append(", name: ");
		result.append(name);
		result.append(", numNodes: ");
		result.append(numNodes);
		result.append(", numBuses: ");
		result.append(numBuses);
		result.append(", busNameRedefined: ");
		result.append(busNameRedefined);
		result.append(", solved: ");
		result.append(solved);
		result.append(", loadMultiplier: ");
		result.append(loadMultiplier);
		result.append(", defaultGrowthFactor: ");
		result.append(defaultGrowthFactor);
		result.append(", defaultGrowthRate: ");
		result.append(defaultGrowthRate);
		result.append(", generatorDispatchReference: ");
		result.append(generatorDispatchReference);
		result.append(", genMultiplier: ");
		result.append(genMultiplier);
		result.append(", defaultHourMult: ");
		result.append(defaultHourMult);
		result.append(", control_busNameRedefined: ");
		result.append(control_busNameRedefined);
		result.append(", priceSignal: ");
		result.append(priceSignal);
		result.append(", emergMinVolts: ");
		result.append(emergMinVolts);
		result.append(", emergMaxVolts: ");
		result.append(emergMaxVolts);
		result.append(", normalMinVolts: ");
		result.append(normalMinVolts);
		result.append(", normalMaxVolts: ");
		result.append(normalMaxVolts);
		result.append(')');
		return result.toString();
	}

} // CircuitImpl
