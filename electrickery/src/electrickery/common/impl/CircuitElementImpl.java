/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import cern.jet.math.tdcomplex.DComplexFunctions;
import electrickery.common.CircuitElement;
import electrickery.common.CommonPackage;
import electrickery.common.Terminal;
import electrickery.common.yBuildOption;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Circuit Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getBaseFreq <em>Base Freq</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getYOrder <em>YOrder</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#isYPrimInvalid <em>YPrim Invalid</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getLastTerminalChecked <em>Last Terminal Checked</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getTerminals <em>Terminals</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getActiveTerminal <em>Active Terminal</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getActiveTerminalIndex <em>Active Terminal Index</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getNTerms <em>NTerms</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getNConds <em>NConds</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getYPrimSeries <em>YPrim Series</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getYPrimShunt <em>YPrim Shunt</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getYPrim <em>YPrim</em>}</li>
 *   <li>{@link electrickery.common.impl.CircuitElementImpl#getYPrimFreq <em>YPrim Freq</em>}</li>
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
    protected Terminal activeTerminal;

    /**
	 * The default value of the '{@link #getActiveTerminalIndex() <em>Active Terminal Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveTerminalIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTIVE_TERMINAL_INDEX_EDEFAULT = 0;

				/**
	 * The cached value of the '{@link #getActiveTerminalIndex() <em>Active Terminal Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveTerminalIndex()
	 * @generated
	 * @ordered
	 */
	protected int activeTerminalIndex = ACTIVE_TERMINAL_INDEX_EDEFAULT;

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
	 * The cached value of the '{@link #getYPrimSeries() <em>YPrim Series</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimSeries()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix2D yPrimSeries;

    /**
	 * The cached value of the '{@link #getYPrimShunt() <em>YPrim Shunt</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrimShunt()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix2D yPrimShunt;

    /**
	 * The cached value of the '{@link #getYPrim() <em>YPrim</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getYPrim()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix2D yPrim;

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

    /*
     *
     */
    private String[] busNames;

    /*
     *
     */
    protected int busIndex;

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
    public Terminal getActiveTerminal() {
		if (activeTerminal != null && activeTerminal.eIsProxy()) {
			InternalEObject oldActiveTerminal = (InternalEObject)activeTerminal;
			activeTerminal = (Terminal)eResolveProxy(oldActiveTerminal);
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
	public Terminal basicGetActiveTerminal() {
		return activeTerminal;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveTerminal(Terminal newActiveTerminal) {
		Terminal oldActiveTerminal = activeTerminal;
		activeTerminal = newActiveTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL, oldActiveTerminal, activeTerminal));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getActiveTerminalIndex() {
		return activeTerminalIndex;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveTerminalIndex(int newActiveTerminalIndex) {
		int oldActiveTerminalIndex = activeTerminalIndex;
		activeTerminalIndex = newActiveTerminalIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX, oldActiveTerminalIndex, activeTerminalIndex));
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
    public DComplexMatrix2D getYPrimSeries() {
		if (yPrimSeries != null && ((EObject)yPrimSeries).eIsProxy()) {
			InternalEObject oldYPrimSeries = (InternalEObject)yPrimSeries;
			yPrimSeries = (DComplexMatrix2D)eResolveProxy(oldYPrimSeries);
			if (yPrimSeries != oldYPrimSeries) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES, oldYPrimSeries, yPrimSeries));
			}
		}
		return yPrimSeries;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D basicGetYPrimSeries() {
		return yPrimSeries;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrimSeries(DComplexMatrix2D newYPrimSeries) {
		DComplexMatrix2D oldYPrimSeries = yPrimSeries;
		yPrimSeries = newYPrimSeries;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES, oldYPrimSeries, yPrimSeries));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D getYPrimShunt() {
		if (yPrimShunt != null && ((EObject)yPrimShunt).eIsProxy()) {
			InternalEObject oldYPrimShunt = (InternalEObject)yPrimShunt;
			yPrimShunt = (DComplexMatrix2D)eResolveProxy(oldYPrimShunt);
			if (yPrimShunt != oldYPrimShunt) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT, oldYPrimShunt, yPrimShunt));
			}
		}
		return yPrimShunt;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D basicGetYPrimShunt() {
		return yPrimShunt;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrimShunt(DComplexMatrix2D newYPrimShunt) {
		DComplexMatrix2D oldYPrimShunt = yPrimShunt;
		yPrimShunt = newYPrimShunt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT, oldYPrimShunt, yPrimShunt));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D getYPrim() {
		if (yPrim != null && ((EObject)yPrim).eIsProxy()) {
			InternalEObject oldYPrim = (InternalEObject)yPrim;
			yPrim = (DComplexMatrix2D)eResolveProxy(oldYPrim);
			if (yPrim != oldYPrim) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.CIRCUIT_ELEMENT__YPRIM, oldYPrim, yPrim));
			}
		}
		return yPrim;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D basicGetYPrim() {
		return yPrim;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setYPrim(DComplexMatrix2D newYPrim) {
		DComplexMatrix2D oldYPrim = yPrim;
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
    public void doYPrimCalcs(DComplexMatrix2D yMatrix) {
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
    public DComplexMatrix2D getYPrimValues(yBuildOption opt) {
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
	public void setNodeRef(int iTerm, int[] nodeRefArray) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

																/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setNodeRef(int iTerm, EList<Integer> nodeRefArray) {
//        int size = getYOrder() * nodeRefArray.size();
//        int size2 = nodeRefArray.size() * getNConds(); // Size for one terminal.

        for (int i = 0; i < nodeRefArray.size(); i++) {
            nodeRef.set((iTerm -1) * getNConds() + 1, nodeRefArray.get(i));
        }
        getTerminals().get(iTerm).setBusRef(nodeRefArray.get(0));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getFirstBus() {
        if (getNTerms() > 0) {
            busIndex = 1;
            return busNames[busIndex];
        } else {
            return "";
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getNextBus() {
        if (getNTerms() > 0) {
            busIndex += 1;
            if (busIndex <= getNTerms()) {
                return busNames[busIndex];
            } else {
                busIndex = getNTerms();
            }
        }
        return "";
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
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				return getYOrder();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				return isYPrimInvalid();
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				return getLastTerminalChecked();
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				return getTerminals();
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				if (resolve) return getActiveTerminal();
				return basicGetActiveTerminal();
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX:
				return getActiveTerminalIndex();
			case CommonPackage.CIRCUIT_ELEMENT__NTERMS:
				return getNTerms();
			case CommonPackage.CIRCUIT_ELEMENT__NCONDS:
				return getNConds();
			case CommonPackage.CIRCUIT_ELEMENT__NPHASES:
				return getNPhases();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				if (resolve) return getYPrimSeries();
				return basicGetYPrimSeries();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				if (resolve) return getYPrimShunt();
				return basicGetYPrimShunt();
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				if (resolve) return getYPrim();
				return basicGetYPrim();
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
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				setYOrder((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				setYPrimInvalid((Boolean)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				setLastTerminalChecked((Integer)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				getTerminals().clear();
				getTerminals().addAll((Collection<? extends Terminal>)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				setActiveTerminal((Terminal)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX:
				setActiveTerminalIndex((Integer)newValue);
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
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				setYPrimSeries((DComplexMatrix2D)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				setYPrimShunt((DComplexMatrix2D)newValue);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				setYPrim((DComplexMatrix2D)newValue);
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
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				setYOrder(YORDER_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				setYPrimInvalid(YPRIM_INVALID_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				setLastTerminalChecked(LAST_TERMINAL_CHECKED_EDEFAULT);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				getTerminals().clear();
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				setActiveTerminal((Terminal)null);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX:
				setActiveTerminalIndex(ACTIVE_TERMINAL_INDEX_EDEFAULT);
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
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				setYPrimSeries((DComplexMatrix2D)null);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				setYPrimShunt((DComplexMatrix2D)null);
				return;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				setYPrim((DComplexMatrix2D)null);
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
			case CommonPackage.CIRCUIT_ELEMENT__YORDER:
				return yOrder != YORDER_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID:
				return yPrimInvalid != YPRIM_INVALID_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED:
				return lastTerminalChecked != LAST_TERMINAL_CHECKED_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__TERMINALS:
				return terminals != null && !terminals.isEmpty();
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL:
				return activeTerminal != null;
			case CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX:
				return activeTerminalIndex != ACTIVE_TERMINAL_INDEX_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__NTERMS:
				return nTerms != NTERMS_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__NCONDS:
				return nConds != NCONDS_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__NPHASES:
				return nPhases != NPHASES_EDEFAULT;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES:
				return yPrimSeries != null;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT:
				return yPrimShunt != null;
			case CommonPackage.CIRCUIT_ELEMENT__YPRIM:
				return yPrim != null;
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
		result.append(", yOrder: ");
		result.append(yOrder);
		result.append(", yPrimInvalid: ");
		result.append(yPrimInvalid);
		result.append(", lastTerminalChecked: ");
		result.append(lastTerminalChecked);
		result.append(", activeTerminalIndex: ");
		result.append(activeTerminalIndex);
		result.append(", nTerms: ");
		result.append(nTerms);
		result.append(", nConds: ");
		result.append(nConds);
		result.append(", nPhases: ");
		result.append(nPhases);
		result.append(", yPrimFreq: ");
		result.append(yPrimFreq);
		result.append(')');
		return result.toString();
	}

} //CircuitElementImpl
