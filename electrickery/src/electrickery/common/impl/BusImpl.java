/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CommonPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bus</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.BusImpl#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getVBus <em>VBus</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getBusCurrent <em>Bus Current</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getNumNodesThisBus <em>Num Nodes This Bus</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getKVBase <em>KV Base</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getX <em>X</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getY <em>Y</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#isCoordDefined <em>Coord Defined</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#isKeep <em>Keep</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getBusRef <em>Bus Ref</em>}</li>
 *   <li>{@link electrickery.common.impl.BusImpl#getNodeNum <em>Node Num</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BusImpl extends NamedImpl implements Bus {
    /**
	 * The cached value of the '{@link #getVBus() <em>VBus</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBus()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix1D vBus;

    /**
	 * The cached value of the '{@link #getBusCurrent() <em>Bus Current</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBusCurrent()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix1D busCurrent;

    /**
	 * The default value of the '{@link #getNumNodesThisBus() <em>Num Nodes This Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNumNodesThisBus()
	 * @generated
	 * @ordered
	 */
    protected static final int NUM_NODES_THIS_BUS_EDEFAULT = 0;

                /**
	 * The cached value of the '{@link #getNumNodesThisBus() <em>Num Nodes This Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumNodesThisBus()
	 * @generated
	 * @ordered
	 */
	protected int numNodesThisBus = NUM_NODES_THIS_BUS_EDEFAULT;

																/**
	 * This is true if the Num Nodes This Bus attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean numNodesThisBusESet;

    /**
	 * The default value of the '{@link #getKVBase() <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVBase()
	 * @generated
	 * @ordered
	 */
	protected static final double KV_BASE_EDEFAULT = 0.0;

																/**
	 * The cached value of the '{@link #getKVBase() <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVBase()
	 * @generated
	 * @ordered
	 */
	protected double kVBase = KV_BASE_EDEFAULT;

																/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final double X_EDEFAULT = 0.0;

																/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected double x = X_EDEFAULT;

																/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final double Y_EDEFAULT = 0.0;

																/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected double y = Y_EDEFAULT;

																/**
	 * The default value of the '{@link #isCoordDefined() <em>Coord Defined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCoordDefined()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COORD_DEFINED_EDEFAULT = false;

																/**
	 * The cached value of the '{@link #isCoordDefined() <em>Coord Defined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCoordDefined()
	 * @generated
	 * @ordered
	 */
	protected boolean coordDefined = COORD_DEFINED_EDEFAULT;

																/**
	 * The default value of the '{@link #isKeep() <em>Keep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isKeep()
	 * @generated
	 * @ordered
	 */
	protected static final boolean KEEP_EDEFAULT = false;

																/**
	 * The cached value of the '{@link #isKeep() <em>Keep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isKeep()
	 * @generated
	 * @ordered
	 */
	protected boolean keep = KEEP_EDEFAULT;

				/**
	 * The default value of the '{@link #getBusRef() <em>Bus Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusRef()
	 * @generated
	 * @ordered
	 */
	protected static final int BUS_REF_EDEFAULT = 0;

																/**
	 * The cached value of the '{@link #getBusRef() <em>Bus Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusRef()
	 * @generated
	 * @ordered
	 */
	protected int busRef = BUS_REF_EDEFAULT;

																/**
	 * The default value of the '{@link #getNodeNum() <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeNum()
	 * @generated
	 * @ordered
	 */
	protected static final int NODE_NUM_EDEFAULT = 0;

																/**
	 * The cached value of the '{@link #getNodeNum() <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeNum()
	 * @generated
	 * @ordered
	 */
	protected int nodeNum = NODE_NUM_EDEFAULT;

				private int[] nodes, refNo;
    private int allocation;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected BusImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return CommonPackage.Literals.BUS;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit getCircuit() {
		if (eContainerFeatureID() != CommonPackage.BUS__CIRCUIT) return null;
		return (Circuit)eContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetCircuit(Circuit newCircuit, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCircuit, CommonPackage.BUS__CIRCUIT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCircuit(Circuit newCircuit) {
		if (newCircuit != eInternalContainer() || (eContainerFeatureID() != CommonPackage.BUS__CIRCUIT && newCircuit != null)) {
			if (EcoreUtil.isAncestor(this, newCircuit))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCircuit != null)
				msgs = ((InternalEObject)newCircuit).eInverseAdd(this, CommonPackage.CIRCUIT__BUSES, Circuit.class, msgs);
			msgs = basicSetCircuit(newCircuit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__CIRCUIT, newCircuit, newCircuit));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix1D getVBus() {
		if (vBus != null && ((EObject)vBus).eIsProxy()) {
			InternalEObject oldVBus = (InternalEObject)vBus;
			vBus = (DComplexMatrix1D)eResolveProxy(oldVBus);
			if (vBus != oldVBus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.BUS__VBUS, oldVBus, vBus));
			}
		}
		return vBus;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix1D basicGetVBus() {
		return vBus;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVBus(DComplexMatrix1D newVBus) {
		DComplexMatrix1D oldVBus = vBus;
		vBus = newVBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__VBUS, oldVBus, vBus));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix1D getBusCurrent() {
		if (busCurrent != null && ((EObject)busCurrent).eIsProxy()) {
			InternalEObject oldBusCurrent = (InternalEObject)busCurrent;
			busCurrent = (DComplexMatrix1D)eResolveProxy(oldBusCurrent);
			if (busCurrent != oldBusCurrent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.BUS__BUS_CURRENT, oldBusCurrent, busCurrent));
			}
		}
		return busCurrent;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix1D basicGetBusCurrent() {
		return busCurrent;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBusCurrent(DComplexMatrix1D newBusCurrent) {
		DComplexMatrix1D oldBusCurrent = busCurrent;
		busCurrent = newBusCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__BUS_CURRENT, oldBusCurrent, busCurrent));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNumNodesThisBus() {
		return numNodesThisBus;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNumNodesThisBus(int newNumNodesThisBus) {
		int oldNumNodesThisBus = numNodesThisBus;
		numNodesThisBus = newNumNodesThisBus;
		boolean oldNumNodesThisBusESet = numNodesThisBusESet;
		numNodesThisBusESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__NUM_NODES_THIS_BUS, oldNumNodesThisBus, numNodesThisBus, !oldNumNodesThisBusESet));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetNumNodesThisBus() {
		int oldNumNodesThisBus = numNodesThisBus;
		boolean oldNumNodesThisBusESet = numNodesThisBusESet;
		numNodesThisBus = NUM_NODES_THIS_BUS_EDEFAULT;
		numNodesThisBusESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.BUS__NUM_NODES_THIS_BUS, oldNumNodesThisBus, NUM_NODES_THIS_BUS_EDEFAULT, oldNumNodesThisBusESet));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetNumNodesThisBus() {
		return numNodesThisBusESet;
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVBase() {
		return kVBase;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVBase(double newKVBase) {
		double oldKVBase = kVBase;
		kVBase = newKVBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__KV_BASE, oldKVBase, kVBase));
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getX() {
		return x;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(double newX) {
		double oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__X, oldX, x));
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getY() {
		return y;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(double newY) {
		double oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__Y, oldY, y));
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCoordDefined() {
		return coordDefined;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoordDefined(boolean newCoordDefined) {
		boolean oldCoordDefined = coordDefined;
		coordDefined = newCoordDefined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__COORD_DEFINED, oldCoordDefined, coordDefined));
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isKeep() {
		return keep;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeep(boolean newKeep) {
		boolean oldKeep = keep;
		keep = newKeep;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__KEEP, oldKeep, keep));
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getBusRef() {
		return busRef;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBusRef(int newBusRef) {
		int oldBusRef = busRef;
		busRef = newBusRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__BUS_REF, oldBusRef, busRef));
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNodeNum() {
		return nodeNum;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeNum(int newNodeNum) {
		int oldNodeNum = nodeNum;
		nodeNum = newNodeNum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__NODE_NUM, oldNodeNum, nodeNum));
	}

																/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int add(int nodeNum) {
        int result;
        if (nodeNum == 0) {
            result = 0;
        } else {
            result = find(nodeNum);
            if (result == 0) {
                nodes[numNodesThisBus] = nodeNum;
                getCircuit().setNumNodes(getCircuit().getNumNodes() + 1);
                refNo[numNodesThisBus] = getCircuit().getNumNodes();
                // Return global node number.
                result = getCircuit().getNumNodes();
            }
        }
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int find(int nodeNum) {
        for (int i = 0; i < numNodesThisBus; i++) {
            if (nodes[i] == nodeNum)
                return refNo[i];
        }
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int findIdx(int nodeNum) {
        for (int i = 0; i < numNodesThisBus; i++) {
            if (nodes[i] == nodeNum) {
                return i;
            }
        }
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int getRef(int nodeIndex) {
        if (nodeIndex > 0 && nodeIndex <= numNodesThisBus) {
            return refNo[nodeIndex];
        } else {
            return 0;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int getNum(int nodeIndex) {
        if (nodeIndex > 0 && nodeIndex <= numNodesThisBus) {
            return nodes[nodeIndex];
        } else {
            return 0;
        }
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void allocateBusVoltages() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void allocateBusCurrents() {
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.BUS__CIRCUIT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCircuit((Circuit)otherEnd, msgs);
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
			case CommonPackage.BUS__CIRCUIT:
				return basicSetCircuit(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CommonPackage.BUS__CIRCUIT:
				return eInternalContainer().eInverseRemove(this, CommonPackage.CIRCUIT__BUSES, Circuit.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.BUS__CIRCUIT:
				return getCircuit();
			case CommonPackage.BUS__VBUS:
				if (resolve) return getVBus();
				return basicGetVBus();
			case CommonPackage.BUS__BUS_CURRENT:
				if (resolve) return getBusCurrent();
				return basicGetBusCurrent();
			case CommonPackage.BUS__NUM_NODES_THIS_BUS:
				return getNumNodesThisBus();
			case CommonPackage.BUS__KV_BASE:
				return getKVBase();
			case CommonPackage.BUS__X:
				return getX();
			case CommonPackage.BUS__Y:
				return getY();
			case CommonPackage.BUS__COORD_DEFINED:
				return isCoordDefined();
			case CommonPackage.BUS__KEEP:
				return isKeep();
			case CommonPackage.BUS__BUS_REF:
				return getBusRef();
			case CommonPackage.BUS__NODE_NUM:
				return getNodeNum();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.BUS__CIRCUIT:
				setCircuit((Circuit)newValue);
				return;
			case CommonPackage.BUS__VBUS:
				setVBus((DComplexMatrix1D)newValue);
				return;
			case CommonPackage.BUS__BUS_CURRENT:
				setBusCurrent((DComplexMatrix1D)newValue);
				return;
			case CommonPackage.BUS__NUM_NODES_THIS_BUS:
				setNumNodesThisBus((Integer)newValue);
				return;
			case CommonPackage.BUS__KV_BASE:
				setKVBase((Double)newValue);
				return;
			case CommonPackage.BUS__X:
				setX((Double)newValue);
				return;
			case CommonPackage.BUS__Y:
				setY((Double)newValue);
				return;
			case CommonPackage.BUS__COORD_DEFINED:
				setCoordDefined((Boolean)newValue);
				return;
			case CommonPackage.BUS__KEEP:
				setKeep((Boolean)newValue);
				return;
			case CommonPackage.BUS__BUS_REF:
				setBusRef((Integer)newValue);
				return;
			case CommonPackage.BUS__NODE_NUM:
				setNodeNum((Integer)newValue);
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
			case CommonPackage.BUS__CIRCUIT:
				setCircuit((Circuit)null);
				return;
			case CommonPackage.BUS__VBUS:
				setVBus((DComplexMatrix1D)null);
				return;
			case CommonPackage.BUS__BUS_CURRENT:
				setBusCurrent((DComplexMatrix1D)null);
				return;
			case CommonPackage.BUS__NUM_NODES_THIS_BUS:
				unsetNumNodesThisBus();
				return;
			case CommonPackage.BUS__KV_BASE:
				setKVBase(KV_BASE_EDEFAULT);
				return;
			case CommonPackage.BUS__X:
				setX(X_EDEFAULT);
				return;
			case CommonPackage.BUS__Y:
				setY(Y_EDEFAULT);
				return;
			case CommonPackage.BUS__COORD_DEFINED:
				setCoordDefined(COORD_DEFINED_EDEFAULT);
				return;
			case CommonPackage.BUS__KEEP:
				setKeep(KEEP_EDEFAULT);
				return;
			case CommonPackage.BUS__BUS_REF:
				setBusRef(BUS_REF_EDEFAULT);
				return;
			case CommonPackage.BUS__NODE_NUM:
				setNodeNum(NODE_NUM_EDEFAULT);
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
			case CommonPackage.BUS__CIRCUIT:
				return getCircuit() != null;
			case CommonPackage.BUS__VBUS:
				return vBus != null;
			case CommonPackage.BUS__BUS_CURRENT:
				return busCurrent != null;
			case CommonPackage.BUS__NUM_NODES_THIS_BUS:
				return isSetNumNodesThisBus();
			case CommonPackage.BUS__KV_BASE:
				return kVBase != KV_BASE_EDEFAULT;
			case CommonPackage.BUS__X:
				return x != X_EDEFAULT;
			case CommonPackage.BUS__Y:
				return y != Y_EDEFAULT;
			case CommonPackage.BUS__COORD_DEFINED:
				return coordDefined != COORD_DEFINED_EDEFAULT;
			case CommonPackage.BUS__KEEP:
				return keep != KEEP_EDEFAULT;
			case CommonPackage.BUS__BUS_REF:
				return busRef != BUS_REF_EDEFAULT;
			case CommonPackage.BUS__NODE_NUM:
				return nodeNum != NODE_NUM_EDEFAULT;
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
		result.append(" (numNodesThisBus: ");
		if (numNodesThisBusESet) result.append(numNodesThisBus); else result.append("<unset>");
		result.append(", kVBase: ");
		result.append(kVBase);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(", coordDefined: ");
		result.append(coordDefined);
		result.append(", keep: ");
		result.append(keep);
		result.append(", busRef: ");
		result.append(busRef);
		result.append(", nodeNum: ");
		result.append(nodeNum);
		result.append(')');
		return result.toString();
	}

} //BusImpl
