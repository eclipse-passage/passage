/**
 */
package org.eclipse.passage.lic.shipments.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.passage.lic.shipments.model.api.*;

import org.eclipse.passage.lic.shipments.model.meta.ShipmentsFactory;
import org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ShipmentsFactoryImpl extends EFactoryImpl implements ShipmentsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ShipmentsFactory init() {
		try {
			ShipmentsFactory theShipmentsFactory = (ShipmentsFactory) EPackage.Registry.INSTANCE
					.getEFactory(ShipmentsPackage.eNS_URI);
			if (theShipmentsFactory != null) {
				return theShipmentsFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ShipmentsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShipmentsFactoryImpl() {
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
		case ShipmentsPackage.PERSONAL:
			return createPersonal();
		case ShipmentsPackage.FLOATING:
			return createFloating();
		case ShipmentsPackage.PERSONAL_LICENSE:
			return createPersonalLicense();
		case ShipmentsPackage.FLOATING_LICENSE:
			return createFloatingLicense();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Personal createPersonal() {
		PersonalImpl personal = new PersonalImpl();
		return personal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Floating createFloating() {
		FloatingImpl floating = new FloatingImpl();
		return floating;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonalLicense createPersonalLicense() {
		PersonalLicenseImpl personalLicense = new PersonalLicenseImpl();
		return personalLicense;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingLicense createFloatingLicense() {
		FloatingLicenseImpl floatingLicense = new FloatingLicenseImpl();
		return floatingLicense;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ShipmentsPackage getShipmentsPackage() {
		return (ShipmentsPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ShipmentsPackage getPackage() {
		return ShipmentsPackage.eINSTANCE;
	}

} //ShipmentsFactoryImpl
