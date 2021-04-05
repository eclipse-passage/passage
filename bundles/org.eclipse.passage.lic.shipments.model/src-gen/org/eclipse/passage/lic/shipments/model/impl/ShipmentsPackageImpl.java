/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.shipments.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor;
import org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor;

import org.eclipse.passage.lic.shipments.model.api.Floating;
import org.eclipse.passage.lic.shipments.model.api.FloatingLicense;
import org.eclipse.passage.lic.shipments.model.api.Personal;
import org.eclipse.passage.lic.shipments.model.api.PersonalLicense;

import org.eclipse.passage.lic.shipments.model.meta.ShipmentsFactory;
import org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ShipmentsPackageImpl extends EPackageImpl implements ShipmentsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personalLicenseDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personalLicenseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicenseDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicenseEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ShipmentsPackageImpl() {
		super(eNS_URI, ShipmentsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ShipmentsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ShipmentsPackage init() {
		if (isInited)
			return (ShipmentsPackage) EPackage.Registry.INSTANCE.getEPackage(ShipmentsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredShipmentsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ShipmentsPackageImpl theShipmentsPackage = registeredShipmentsPackage instanceof ShipmentsPackageImpl
				? (ShipmentsPackageImpl) registeredShipmentsPackage
				: new ShipmentsPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theShipmentsPackage.createPackageContents();

		// Initialize created meta-data
		theShipmentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theShipmentsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ShipmentsPackage.eNS_URI, theShipmentsPackage);
		return theShipmentsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPersonal() {
		return personalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPersonal_Licenses() {
		return (EReference) personalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloating() {
		return floatingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloating_Licenses() {
		return (EReference) floatingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPersonalLicenseDescriptor() {
		return personalLicenseDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPersonalLicense() {
		return personalLicenseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPersonalLicense_User() {
		return (EAttribute) personalLicenseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPersonalLicense_License() {
		return (EAttribute) personalLicenseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseDescriptor() {
		return floatingLicenseDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicense() {
		return floatingLicenseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicense_Company() {
		return (EAttribute) floatingLicenseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicense_License() {
		return (EAttribute) floatingLicenseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ShipmentsFactory getShipmentsFactory() {
		return (ShipmentsFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		personalEClass = createEClass(PERSONAL);
		createEReference(personalEClass, PERSONAL__LICENSES);

		floatingEClass = createEClass(FLOATING);
		createEReference(floatingEClass, FLOATING__LICENSES);

		personalLicenseDescriptorEClass = createEClass(PERSONAL_LICENSE_DESCRIPTOR);

		personalLicenseEClass = createEClass(PERSONAL_LICENSE);
		createEAttribute(personalLicenseEClass, PERSONAL_LICENSE__USER);
		createEAttribute(personalLicenseEClass, PERSONAL_LICENSE__LICENSE);

		floatingLicenseDescriptorEClass = createEClass(FLOATING_LICENSE_DESCRIPTOR);

		floatingLicenseEClass = createEClass(FLOATING_LICENSE);
		createEAttribute(floatingLicenseEClass, FLOATING_LICENSE__COMPANY);
		createEAttribute(floatingLicenseEClass, FLOATING_LICENSE__LICENSE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		personalLicenseEClass.getESuperTypes().add(this.getPersonalLicenseDescriptor());
		floatingLicenseEClass.getESuperTypes().add(this.getFloatingLicenseDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(personalEClass, Personal.class, "Personal", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPersonal_Licenses(), this.getPersonalLicense(), null, "licenses", null, 0, -1, Personal.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingEClass, Floating.class, "Floating", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFloating_Licenses(), this.getFloatingLicense(), null, "licenses", null, 0, -1, Floating.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(personalLicenseDescriptorEClass, PersonalLicenseDescriptor.class, "PersonalLicenseDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(personalLicenseEClass, PersonalLicense.class, "PersonalLicense", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersonalLicense_User(), ecorePackage.getEString(), "user", null, 1, 1, PersonalLicense.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPersonalLicense_License(), ecorePackage.getEString(), "license", null, 1, 1, //$NON-NLS-1$
				PersonalLicense.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(floatingLicenseDescriptorEClass, FloatingLicenseDescriptor.class, "FloatingLicenseDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(floatingLicenseEClass, FloatingLicense.class, "FloatingLicense", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingLicense_Company(), ecorePackage.getEString(), "company", null, 1, 1, //$NON-NLS-1$
				FloatingLicense.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getFloatingLicense_License(), ecorePackage.getEString(), "license", null, 1, 1, //$NON-NLS-1$
				FloatingLicense.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ShipmentsPackageImpl
