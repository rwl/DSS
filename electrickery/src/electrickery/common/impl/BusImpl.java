/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
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
 * </ul>
 * </p>
 *
 * @generated
 */
public class BusImpl extends NamedImpl implements Bus {
    /**
	 * The default value of the '{@link #getVBus() <em>VBus</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBus()
	 * @generated
	 * @ordered
	 */
    protected static final double VBUS_EDEFAULT = 115.0;

    /**
	 * The cached value of the '{@link #getVBus() <em>VBus</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVBus()
	 * @generated
	 * @ordered
	 */
    protected double vBus = VBUS_EDEFAULT;

    /**
	 * The default value of the '{@link #getBusCurrent() <em>Bus Current</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBusCurrent()
	 * @generated
	 * @ordered
	 */
    protected static final double BUS_CURRENT_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getBusCurrent() <em>Bus Current</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBusCurrent()
	 * @generated
	 * @ordered
	 */
    protected double busCurrent = BUS_CURRENT_EDEFAULT;

    private int[] nodes, refNo;
    private int numNodesThisBus, allocation;

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
				msgs = ((InternalEObject)newCircuit).eInverseAdd(this, CommonPackage.CIRCUIT__BUS_LIST, Circuit.class, msgs);
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
    public double getVBus() {
		return vBus;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVBus(double newVBus) {
		double oldVBus = vBus;
		vBus = newVBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__VBUS, oldVBus, vBus));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getBusCurrent() {
		return busCurrent;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBusCurrent(double newBusCurrent) {
		double oldBusCurrent = busCurrent;
		busCurrent = newBusCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__BUS_CURRENT, oldBusCurrent, busCurrent));
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
				return eInternalContainer().eInverseRemove(this, CommonPackage.CIRCUIT__BUS_LIST, Circuit.class, msgs);
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
				return getVBus();
			case CommonPackage.BUS__BUS_CURRENT:
				return getBusCurrent();
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
				setVBus((Double)newValue);
				return;
			case CommonPackage.BUS__BUS_CURRENT:
				setBusCurrent((Double)newValue);
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
				setVBus(VBUS_EDEFAULT);
				return;
			case CommonPackage.BUS__BUS_CURRENT:
				setBusCurrent(BUS_CURRENT_EDEFAULT);
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
				return vBus != VBUS_EDEFAULT;
			case CommonPackage.BUS__BUS_CURRENT:
				return busCurrent != BUS_CURRENT_EDEFAULT;
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
		result.append(" (vBus: ");
		result.append(vBus);
		result.append(", busCurrent: ");
		result.append(busCurrent);
		result.append(')');
		return result.toString();
	}

} //BusImpl
