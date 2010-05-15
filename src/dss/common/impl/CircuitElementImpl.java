/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D;
import cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D;
import cern.jet.math.tdcomplex.DComplexFunctions;

import dss.common.CircuitElement;
import dss.common.CommonPackage;

import dss.common.Terminal;
import dss.common.yBuildOption;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Circuit Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getBaseFreq <em>Base Freq</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getNodeRef <em>Node Ref</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getYOrder <em>YOrder</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isYPrimInvalid <em>YPrim Invalid</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getLastTerminalChecked <em>Last Terminal Checked</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isChecked <em>Checked</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isHasMeter <em>Has Meter</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isIsolated <em>Isolated</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isHasControl <em>Has Control</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#isPartOfFeeder <em>Part Of Feeder</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getControlElement <em>Control Element</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getTerminals <em>Terminals</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getActiveTerminal <em>Active Terminal</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getNTerms <em>NTerms</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getNConds <em>NConds</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getBusIndex <em>Bus Index</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getYPrimSeries <em>YPrim Series</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getYPrimShunt <em>YPrim Shunt</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getYPrim <em>YPrim</em>}</li>
 *   <li>{@link dss.common.impl.CircuitElementImpl#getYPrimFreq <em>YPrim Freq</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CircuitElementImpl extends EObjectImpl implements CircuitElement {
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
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
    protected static final boolean ENABLED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
    protected boolean enabled = ENABLED_EDEFAULT;

    /**
	 * The default value of the '{@link #getBaseFreq() <em>Base Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBaseFreq()
	 * @generated
	 * @ordered
	 */
    protected static final double BASE_FREQ_EDEFAULT = 60.0;

    /**
	 * The cached value of the '{@link #getBaseFreq() <em>Base Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBaseFreq()
	 * @generated
	 * @ordered
	 */
    protected double baseFreq = BASE_FREQ_EDEFAULT;

    /**
	 * The cached value of the '{@link #getNodeRef() <em>Node Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNodeRef()
	 * @generated
	 * @ordered
	 */
    protected EList<Integer> nodeRef;

    /**
	 * The default value of the '{@link #getYOrder() <em>YOrder</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYOrder()
	 * @generated
	 * @ordered
	 */
    protected static final int YORDER_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getYOrder() <em>YOrder</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYOrder()
	 * @generated
	 * @ordered
	 */
    protected int yOrder = YORDER_EDEFAULT;

    /**
	 * The default value of the '{@link #isYPrimInvalid() <em>YPrim Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isYPrimInvalid()
	 * @generated
	 * @ordered
	 */
	protected static final boolean YPRIM_INVALID_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isYPrimInvalid() <em>YPrim Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isYPrimInvalid()
	 * @generated
	 * @ordered
	 */
	protected boolean yPrimInvalid = YPRIM_INVALID_EDEFAULT;

				/**
	 * The default value of the '{@link #getLastTerminalChecked() <em>Last Terminal Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLastTerminalChecked()
	 * @generated
	 * @ordered
	 */
    protected static final int LAST_TERMINAL_CHECKED_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getLastTerminalChecked() <em>Last Terminal Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLastTerminalChecked()
	 * @generated
	 * @ordered
	 */
    protected int lastTerminalChecked = LAST_TERMINAL_CHECKED_EDEFAULT;

    /**
	 * The default value of the '{@link #isChecked() <em>Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isChecked()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CHECKED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isChecked() <em>Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isChecked()
	 * @generated
	 * @ordered
	 */
    protected boolean checked = CHECKED_EDEFAULT;

    /**
	 * The default value of the '{@link #isHasMeter() <em>Has Meter</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasMeter()
	 * @generated
	 * @ordered
	 */
    protected static final boolean HAS_METER_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isHasMeter() <em>Has Meter</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasMeter()
	 * @generated
	 * @ordered
	 */
    protected boolean hasMeter = HAS_METER_EDEFAULT;

    /**
	 * The default value of the '{@link #isIsolated() <em>Isolated</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isIsolated()
	 * @generated
	 * @ordered
	 */
    protected static final boolean ISOLATED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isIsolated() <em>Isolated</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isIsolated()
	 * @generated
	 * @ordered
	 */
    protected boolean isolated = ISOLATED_EDEFAULT;

    /**
	 * The default value of the '{@link #isHasControl() <em>Has Control</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasControl()
	 * @generated
	 * @ordered
	 */
    protected static final boolean HAS_CONTROL_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isHasControl() <em>Has Control</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasControl()
	 * @generated
	 * @ordered
	 */
    protected boolean hasControl = HAS_CONTROL_EDEFAULT;

    /**
	 * The default value of the '{@link #isPartOfFeeder() <em>Part Of Feeder</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isPartOfFeeder()
	 * @generated
	 * @ordered
	 */
    protected static final boolean PART_OF_FEEDER_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isPartOfFeeder() <em>Part Of Feeder</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isPartOfFeeder()
	 * @generated
	 * @ordered
	 */
    protected boolean partOfFeeder = PART_OF_FEEDER_EDEFAULT;

    /**
	 * The cached value of the '{@link #getControlElement() <em>Control Element</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getControlElement()
	 * @generated
	 * @ordered
	 */
    protected EClass controlElement;

    /**
	 * The cached value of the '{@link #getTerminals() <em>Terminals</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTerminals()
	 * @generated
	 * @ordered
	 */
    protected EList<Terminal> terminals;

    /**
	 * The cached value of the '{@link #getActiveTerminal() <em>Active Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getActiveTerminal()
	 * @generated
	 * @ordered
	 */
    protected EClass activeTerminal;

    /**
	 * The default value of the '{@link #getNTerms() <em>NTerms</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNTerms()
	 * @generated
	 * @ordered
	 */
    protected static final int NTERMS_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getNTerms() <em>NTerms</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNTerms()
	 * @generated
	 * @ordered
	 */
    protected int nTerms = NTERMS_EDEFAULT;

    /**
	 * The default value of the '{@link #getNConds() <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNConds()
	 * @generated
	 * @ordered
	 */
    protected static final int NCONDS_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getNConds() <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNConds()
	 * @generated
	 * @ordered
	 */
    protected int nConds = NCONDS_EDEFAULT;

    /**
	 * The default value of the '{@link #getNPhases() <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNPhases()
	 * @generated
	 * @ordered
	 */
    protected static final int NPHASES_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getNPhases() <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNPhases()
	 * @generated
	 * @ordered
	 */
    protected int nPhases = NPHASES_EDEFAULT;

    /**
	 * The default value of the '{@link #getBusIndex() <em>Bus Index</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBusIndex()
	 * @generated
	 * @ordered
	 */
    protected static final int BUS_INDEX_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getBusIndex() <em>Bus Index</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBusIndex()
	 * @generated
	 * @ordered
	 */
    protected int busIndex = BUS_INDEX_EDEFAULT;

    /**
	 * The default value of the '{@link #getYPrimSeries() <em>YPrim Series</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimSeries()
	 * @generated
	 * @ordered
	 */
    protected static final SparseDComplexMatrix2D YPRIM_SERIES_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getYPrimSeries() <em>YPrim Series</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimSeries()
	 * @generated
	 * @ordered
	 */
    protected SparseDComplexMatrix2D yPrimSeries = YPRIM_SERIES_EDEFAULT;

    /**
	 * The default value of the '{@link #getYPrimShunt() <em>YPrim Shunt</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimShunt()
	 * @generated
	 * @ordered
	 */
    protected static final SparseDComplexMatrix2D YPRIM_SHUNT_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getYPrimShunt() <em>YPrim Shunt</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimShunt()
	 * @generated
	 * @ordered
	 */
    protected SparseDComplexMatrix2D yPrimShunt = YPRIM_SHUNT_EDEFAULT;

    /**
	 * The default value of the '{@link #getYPrim() <em>YPrim</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrim()
	 * @generated
	 * @ordered
	 */
    protected static final SparseDComplexMatrix2D YPRIM_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getYPrim() <em>YPrim</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrim()
	 * @generated
	 * @ordered
	 */
    protected SparseDComplexMatrix2D yPrim = YPRIM_EDEFAULT;

    /**
	 * The default value of the '{@link #getYPrimFreq() <em>YPrim Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimFreq()
	 * @generated
	 * @ordered
	 */
    protected static final double YPRIM_FREQ_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getYPrimFreq() <em>YPrim Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimFreq()
	 * @generated
	 * @ordered
	 */
    protected double yPrimFreq = YPRIM_FREQ_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected CircuitElementImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return CommonPackage.Literals.CIRCUIT_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__NAME, oldName, name));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isEnabled() {
		return enabled;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__ENABLED, oldEnabled, enabled));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getBaseFreq() {
		return baseFreq;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBaseFreq(double newBaseFreq) {
		double oldBaseFreq = baseFreq;
		baseFreq = newBaseFreq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ, oldBaseFreq, baseFreq));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Integer> getNodeRef() {
		if (nodeRef == null) {
			nodeRef = new EDataTypeUniqueEList<Integer>(Integer.class, this, CommonPackage.CIRCUIT_ELEMENT__NODE_REF);
		}
		return nodeRef;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getYOrder() {
		return yOrder;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYOrder(int newYOrder) {
		int oldYOrder = yOrder;
		yOrder = newYOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YORDER, oldYOrder, yOrder));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isYPrimInvalid() {
		return yPrimInvalid;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYPrimInvalid(boolean newYPrimInvalid) {
		boolean oldYPrimInvalid = yPrimInvalid;
		yPrimInvalid = newYPrimInvalid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID, oldYPrimInvalid, yPrimInvalid));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getLastTerminalChecked() {
		return lastTerminalChecked;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLastTerminalChecked(int newLastTerminalChecked) {
		int oldLastTerminalChecked = lastTerminalChecked;
		lastTerminalChecked = newLastTerminalChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED, oldLastTerminalChecked, lastTerminalChecked));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isChecked() {
		return checked;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setChecked(boolean newChecked) {
		boolean oldChecked = checked;
		checked = newChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__CHECKED, oldChecked, checked));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isHasMeter() {
		return hasMeter;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setHasMeter(boolean newHasMeter) {
		boolean oldHasMeter = hasMeter;
		hasMeter = newHasMeter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__HAS_METER, oldHasMeter, hasMeter));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isIsolated() {
		return isolated;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setIsolated(boolean newIsolated) {
		boolean oldIsolated = isolated;
		isolated = newIsolated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__ISOLATED, oldIsolated, isolated));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isHasControl() {
		return hasControl;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setHasControl(boolean newHasControl) {
		boolean oldHasControl = hasControl;
		hasControl = newHasControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL, oldHasControl, hasControl));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isPartOfFeeder() {
		return partOfFeeder;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPartOfFeeder(boolean newPartOfFeeder) {
		boolean oldPartOfFeeder = partOfFeeder;
		partOfFeeder = newPartOfFeeder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER, oldPartOfFeeder, partOfFeeder));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getControlElement() {
		if (controlElement != null && controlElement.eIsProxy()) {
			InternalEObject oldControlElement = (InternalEObject)controlElement;
			controlElement = (EClass)eResolveProxy(oldControlElement);
			if (controlElement != oldControlElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT, oldControlElement, controlElement));
			}
		}
		return controlElement;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass basicGetControlElement() {
		return controlElement;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setControlElement(EClass newControlElement) {
		EClass oldControlElement = controlElement;
		controlElement = newControlElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT, oldControlElement, controlElement));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Terminal> getTerminals() {
		if (terminals == null) {
			terminals = new EObjectResolvingEList<Terminal>(Terminal.class, this, CommonPackage.CIRCUIT_ELEMENT__TERMINALS);
		}
		return terminals;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getActiveTerminal() {
		if (activeTerminal != null && activeTerminal.eIsProxy()) {
			InternalEObject oldActiveTerminal = (InternalEObject)activeTerminal;
			activeTerminal = (EClass)eResolveProxy(oldActiveTerminal);
			if (activeTerminal != oldActiveTerminal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL, oldActiveTerminal, activeTerminal));
			}
		}
		return activeTerminal;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass basicGetActiveTerminal() {
		return activeTerminal;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setActiveTerminal(EClass newActiveTerminal) {
		EClass oldActiveTerminal = activeTerminal;
		activeTerminal = newActiveTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL, oldActiveTerminal, activeTerminal));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNTerms() {
		return nTerms;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNTerms(int newNTerms) {
		int oldNTerms = nTerms;
		nTerms = newNTerms;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__NTERMS, oldNTerms, nTerms));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNConds() {
		return nConds;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNConds(int newNConds) {
		int oldNConds = nConds;
		nConds = newNConds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__NCONDS, oldNConds, nConds));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNPhases() {
		return nPhases;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNPhases(int newNPhases) {
		int oldNPhases = nPhases;
		nPhases = newNPhases;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__NPHASES, oldNPhases, nPhases));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getBusIndex() {
		return busIndex;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBusIndex(int newBusIndex) {
		int oldBusIndex = busIndex;
		busIndex = newBusIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX, oldBusIndex, busIndex));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SparseDComplexMatrix2D getYPrimSeries() {
		return yPrimSeries;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrimSeries(SparseDComplexMatrix2D newYPrimSeries) {
		SparseDComplexMatrix2D oldYPrimSeries = yPrimSeries;
		yPrimSeries = newYPrimSeries;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES, oldYPrimSeries, yPrimSeries));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SparseDComplexMatrix2D getYPrimShunt() {
		return yPrimShunt;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrimShunt(SparseDComplexMatrix2D newYPrimShunt) {
		SparseDComplexMatrix2D oldYPrimShunt = yPrimShunt;
		yPrimShunt = newYPrimShunt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT, oldYPrimShunt, yPrimShunt));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SparseDComplexMatrix2D getYPrim() {
		return yPrim;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrim(SparseDComplexMatrix2D newYPrim) {
		SparseDComplexMatrix2D oldYPrim = yPrim;
		yPrim = newYPrim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM, oldYPrim, yPrim));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getYPrimFreq() {
		return yPrimFreq;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrimFreq(double newYPrimFreq) {
		double oldYPrimFreq = yPrimFreq;
		yPrimFreq = newYPrimFreq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ, oldYPrimFreq, yPrimFreq));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void calcYPrim(double yPrimFreq) {
        if (getYPrimSeries() != null)
            doYPrimCalcs(getYPrimSeries());
        if (getYPrimShunt() != null)
            doYPrimCalcs(getYPrimShunt());
        if (getYPrim() != null)
            doYPrimCalcs(getYPrim());
    }

    /**
     * Account for open conductors.
     * For any conductor that is open, zero out row and column.
     * @generated NOT
     */
    public void doYPrimCalcs(SparseDComplexMatrix2D yMatrix) {
        double EPSILON = 1e-12;

        boolean elementOpen = false;
        int k = 0;

        for (int i = 0; i < getNTerms(); i++) {
            for (int j = 0; j < getNConds(); j++) {
                if (getTerminals().get(i).getConductors().get(j).isClosed()) {
                    if (!elementOpen) {
                        elementOpen = true;
                    }
                    // First do Kron Reduction.
                    int elimRow = j +k;
                    double[] ynn = yMatrix.getQuick(elimRow, elimRow);
                    if (DComplexFunctions.abs.apply(ynn) == 0.0)
                        ynn[0] = EPSILON;
                    for (int ii = 0; ii < yOrder; ii++) {
                        double[] yin = yMatrix.getQuick(ii, elimRow);
                        for (int jj = ii; jj < yOrder; jj++) {
                            double[] yij = yMatrix.getQuick(ii, jj);
                            double[] ynj = yMatrix.getQuick(elimRow, jj);
                            double[] kron = DComplexFunctions.minus.apply(yij, DComplexFunctions.div.apply(DComplexFunctions.mult.apply(yin, ynj), ynn));
                            yMatrix.setQuick(ii, jj, kron);
                            if (ii != jj)
                                yMatrix.setQuick(jj, ii, kron);
                        }
                    }
                    yMatrix.viewRow(elimRow).assign(0.0, 0.0);
                    yMatrix.viewColumn(elimRow).assign(0.0, 0.0);
                }
            }
            k = k + getNConds();
        }
        if (elementOpen) {
            // TODO: Reallocate eliminated memory.
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public SparseDComplexMatrix2D getYPrimValues(yBuildOption opt) {
        if (opt == yBuildOption.WHOLE_MATRIX) {
            return getYPrim();
        } else if (opt == yBuildOption.SERIES_ONLY) {
            return getYPrimSeries();
        } else {
            return getYPrimShunt();
        }
    }

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void makePosSequence() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.CIRCUIT_ELEMENT__NAME:
				return getName();
			case CommonPackage.CIRCUIT_ELEMENT__ENABLED:
				return isEnabled();
			case CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ:
				return getBaseFreq();
			case CommonPackage.CIRCUIT_ELEMENT__NODE_REF:
				return getNodeRef();
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				return getYOrder();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				return isYPrimInvalid();
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				return getLastTerminalChecked();
			case CommonPackage.CIRCUIT_ELEMENT__CHECKED:
				return isChecked();
			case CommonPackage.CIRCUIT_ELEMENT__HAS_METER:
				return isHasMeter();
			case CommonPackage.CIRCUIT_ELEMENT__ISOLATED:
				return isIsolated();
			case CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL:
				return isHasControl();
			case CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER:
				return isPartOfFeeder();
			case CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT:
				if (resolve) return getControlElement();
				return basicGetControlElement();
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				return getTerminals();
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				if (resolve) return getActiveTerminal();
				return basicGetActiveTerminal();
			case CommonPackage.CIRCUIT_ELEMENT__NTERMS:
				return getNTerms();
			case CommonPackage.CIRCUIT_ELEMENT__NCONDS:
				return getNConds();
			case CommonPackage.CIRCUIT_ELEMENT__NPHASES:
				return getNPhases();
			case CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX:
				return getBusIndex();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				return getYPrimSeries();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				return getYPrimShunt();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				return getYPrim();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ:
				return getYPrimFreq();
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
			case CommonPackage.CIRCUIT_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ:
				setBaseFreq((Double)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NODE_REF:
				getNodeRef().clear();
				getNodeRef().addAll((Collection<? extends Integer>)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				setYOrder((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				setYPrimInvalid((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				setLastTerminalChecked((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__CHECKED:
				setChecked((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__HAS_METER:
				setHasMeter((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ISOLATED:
				setIsolated((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL:
				setHasControl((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER:
				setPartOfFeeder((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT:
				setControlElement((EClass)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				getTerminals().clear();
				getTerminals().addAll((Collection<? extends Terminal>)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				setActiveTerminal((EClass)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NTERMS:
				setNTerms((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NCONDS:
				setNConds((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NPHASES:
				setNPhases((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX:
				setBusIndex((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				setYPrimSeries((SparseDComplexMatrix2D)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				setYPrimShunt((SparseDComplexMatrix2D)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				setYPrim((SparseDComplexMatrix2D)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ:
				setYPrimFreq((Double)newValue);
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
			case CommonPackage.CIRCUIT_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ:
				setBaseFreq(BASE_FREQ_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NODE_REF:
				getNodeRef().clear();
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				setYOrder(YORDER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				setYPrimInvalid(YPRIM_INVALID_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				setLastTerminalChecked(LAST_TERMINAL_CHECKED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__CHECKED:
				setChecked(CHECKED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__HAS_METER:
				setHasMeter(HAS_METER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ISOLATED:
				setIsolated(ISOLATED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL:
				setHasControl(HAS_CONTROL_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER:
				setPartOfFeeder(PART_OF_FEEDER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT:
				setControlElement((EClass)null);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				getTerminals().clear();
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				setActiveTerminal((EClass)null);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NTERMS:
				setNTerms(NTERMS_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NCONDS:
				setNConds(NCONDS_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__NPHASES:
				setNPhases(NPHASES_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX:
				setBusIndex(BUS_INDEX_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				setYPrimSeries(YPRIM_SERIES_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				setYPrimShunt(YPRIM_SHUNT_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				setYPrim(YPRIM_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ:
				setYPrimFreq(YPRIM_FREQ_EDEFAULT);
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
			case CommonPackage.CIRCUIT_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.CIRCUIT_ELEMENT__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ:
				return baseFreq != BASE_FREQ_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__NODE_REF:
				return nodeRef != null && !nodeRef.isEmpty();
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				return yOrder != YORDER_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				return yPrimInvalid != YPRIM_INVALID_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				return lastTerminalChecked != LAST_TERMINAL_CHECKED_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__CHECKED:
				return checked != CHECKED_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__HAS_METER:
				return hasMeter != HAS_METER_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__ISOLATED:
				return isolated != ISOLATED_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL:
				return hasControl != HAS_CONTROL_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER:
				return partOfFeeder != PART_OF_FEEDER_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT:
				return controlElement != null;
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				return terminals != null && !terminals.isEmpty();
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				return activeTerminal != null;
			case CommonPackage.CIRCUIT_ELEMENT__NTERMS:
				return nTerms != NTERMS_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__NCONDS:
				return nConds != NCONDS_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__NPHASES:
				return nPhases != NPHASES_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX:
				return busIndex != BUS_INDEX_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				return YPRIM_SERIES_EDEFAULT == null ? yPrimSeries != null : !YPRIM_SERIES_EDEFAULT.equals(yPrimSeries);
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				return YPRIM_SHUNT_EDEFAULT == null ? yPrimShunt != null : !YPRIM_SHUNT_EDEFAULT.equals(yPrimShunt);
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				return YPRIM_EDEFAULT == null ? yPrim != null : !YPRIM_EDEFAULT.equals(yPrim);
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ:
				return yPrimFreq != YPRIM_FREQ_EDEFAULT;
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
		result.append(", enabled: ");
		result.append(enabled);
		result.append(", baseFreq: ");
		result.append(baseFreq);
		result.append(", nodeRef: ");
		result.append(nodeRef);
		result.append(", yOrder: ");
		result.append(yOrder);
		result.append(", yPrimInvalid: ");
		result.append(yPrimInvalid);
		result.append(", lastTerminalChecked: ");
		result.append(lastTerminalChecked);
		result.append(", checked: ");
		result.append(checked);
		result.append(", hasMeter: ");
		result.append(hasMeter);
		result.append(", isolated: ");
		result.append(isolated);
		result.append(", hasControl: ");
		result.append(hasControl);
		result.append(", partOfFeeder: ");
		result.append(partOfFeeder);
		result.append(", nTerms: ");
		result.append(nTerms);
		result.append(", nConds: ");
		result.append(nConds);
		result.append(", nPhases: ");
		result.append(nPhases);
		result.append(", busIndex: ");
		result.append(busIndex);
		result.append(", yPrimSeries: ");
		result.append(yPrimSeries);
		result.append(", yPrimShunt: ");
		result.append(yPrimShunt);
		result.append(", yPrim: ");
		result.append(yPrim);
		result.append(", yPrimFreq: ");
		result.append(yPrimFreq);
		result.append(')');
		return result.toString();
	}

} //CircuitElementImpl
