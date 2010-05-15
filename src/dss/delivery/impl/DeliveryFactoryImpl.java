/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery.impl;

import dss.delivery.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeliveryFactoryImpl extends EFactoryImpl implements DeliveryFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeliveryFactory init() {
		try {
			DeliveryFactory theDeliveryFactory = (DeliveryFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openpowersystem.com/dss/delivery"); 
			if (theDeliveryFactory != null) {
				return theDeliveryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DeliveryFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DeliveryPackage.CAPACITOR: return createCapacitor();
			case DeliveryPackage.FAULT: return createFault();
			case DeliveryPackage.FUSE: return createFuse();
			case DeliveryPackage.LINE: return createLine();
			case DeliveryPackage.REACTOR: return createReactor();
			case DeliveryPackage.TRANSFORMER: return createTransformer();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Capacitor createCapacitor() {
		CapacitorImpl capacitor = new CapacitorImpl();
		return capacitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fault createFault() {
		FaultImpl fault = new FaultImpl();
		return fault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fuse createFuse() {
		FuseImpl fuse = new FuseImpl();
		return fuse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Line createLine() {
		LineImpl line = new LineImpl();
		return line;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reactor createReactor() {
		ReactorImpl reactor = new ReactorImpl();
		return reactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transformer createTransformer() {
		TransformerImpl transformer = new TransformerImpl();
		return transformer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryPackage getDeliveryPackage() {
		return (DeliveryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DeliveryPackage getPackage() {
		return DeliveryPackage.eINSTANCE;
	}

} //DeliveryFactoryImpl
