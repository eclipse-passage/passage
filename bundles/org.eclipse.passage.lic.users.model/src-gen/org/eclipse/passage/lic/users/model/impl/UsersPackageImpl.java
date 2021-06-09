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
package org.eclipse.passage.lic.users.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.passage.lic.users.ContactDescriptor;
import org.eclipse.passage.lic.users.LicenseOwnerDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserGroupDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

import org.eclipse.passage.lic.users.model.api.Contact;
import org.eclipse.passage.lic.users.model.api.LicenseOwner;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserGroup;
import org.eclipse.passage.lic.users.model.api.UserOrigin;

import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UsersPackageImpl extends EPackageImpl implements UsersPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userOriginDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass contactDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass licenseOwnerDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass userGroupDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userOriginEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass userGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass contactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass licenseOwnerEClass = null;

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
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UsersPackageImpl() {
		super(eNS_URI, UsersFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UsersPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UsersPackage init() {
		if (isInited)
			return (UsersPackage) EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredUsersPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		UsersPackageImpl theUsersPackage = registeredUsersPackage instanceof UsersPackageImpl
				? (UsersPackageImpl) registeredUsersPackage
				: new UsersPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theUsersPackage.createPackageContents();

		// Initialize created meta-data
		theUsersPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUsersPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UsersPackage.eNS_URI, theUsersPackage);
		return theUsersPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUserOriginDescriptor() {
		return userOriginDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getContactDescriptor() {
		return contactDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getLicenseOwnerDescriptor() {
		return licenseOwnerDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUserDescriptor() {
		return userDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getUserGroupDescriptor() {
		return userGroupDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUserOrigin() {
		return userOriginEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUserOrigin_Identifier() {
		return (EAttribute) userOriginEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUserOrigin_Name() {
		return (EAttribute) userOriginEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUserOrigin_Description() {
		return (EAttribute) userOriginEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUserOrigin_Users() {
		return (EReference) userOriginEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getUserOrigin_Groups() {
		return (EReference) userOriginEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUser() {
		return userEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getUser_PreferredEvaluationType() {
		return (EAttribute) userEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getUser_PreferredEvaluationExpression() {
		return (EAttribute) userEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getUser_Origin() {
		return (EReference) userEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getUserGroup() {
		return userGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getUserGroup_Users() {
		return (EReference) userGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getUserGroup_Origin() {
		return (EReference) userGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getContact() {
		return contactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getContact_Name() {
		return (EAttribute) contactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getContact_Title() {
		return (EAttribute) contactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getContact_Position() {
		return (EAttribute) contactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getContact_Email() {
		return (EAttribute) contactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getContact_Address() {
		return (EAttribute) contactEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getLicenseOwner() {
		return licenseOwnerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getLicenseOwner_Identifier() {
		return (EAttribute) licenseOwnerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getLicenseOwner_Name() {
		return (EAttribute) licenseOwnerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getLicenseOwner_Description() {
		return (EAttribute) licenseOwnerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getLicenseOwner_Contact() {
		return (EReference) licenseOwnerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UsersFactory getUsersFactory() {
		return (UsersFactory) getEFactoryInstance();
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
		contactDescriptorEClass = createEClass(CONTACT_DESCRIPTOR);

		licenseOwnerDescriptorEClass = createEClass(LICENSE_OWNER_DESCRIPTOR);

		userDescriptorEClass = createEClass(USER_DESCRIPTOR);

		userGroupDescriptorEClass = createEClass(USER_GROUP_DESCRIPTOR);

		userOriginDescriptorEClass = createEClass(USER_ORIGIN_DESCRIPTOR);

		contactEClass = createEClass(CONTACT);
		createEAttribute(contactEClass, CONTACT__NAME);
		createEAttribute(contactEClass, CONTACT__TITLE);
		createEAttribute(contactEClass, CONTACT__POSITION);
		createEAttribute(contactEClass, CONTACT__EMAIL);
		createEAttribute(contactEClass, CONTACT__ADDRESS);

		licenseOwnerEClass = createEClass(LICENSE_OWNER);
		createEAttribute(licenseOwnerEClass, LICENSE_OWNER__IDENTIFIER);
		createEAttribute(licenseOwnerEClass, LICENSE_OWNER__NAME);
		createEAttribute(licenseOwnerEClass, LICENSE_OWNER__DESCRIPTION);
		createEReference(licenseOwnerEClass, LICENSE_OWNER__CONTACT);

		userEClass = createEClass(USER);
		createEAttribute(userEClass, USER__PREFERRED_EVALUATION_TYPE);
		createEAttribute(userEClass, USER__PREFERRED_EVALUATION_EXPRESSION);
		createEReference(userEClass, USER__ORIGIN);

		userGroupEClass = createEClass(USER_GROUP);
		createEReference(userGroupEClass, USER_GROUP__USERS);
		createEReference(userGroupEClass, USER_GROUP__ORIGIN);

		userOriginEClass = createEClass(USER_ORIGIN);
		createEAttribute(userOriginEClass, USER_ORIGIN__IDENTIFIER);
		createEAttribute(userOriginEClass, USER_ORIGIN__NAME);
		createEAttribute(userOriginEClass, USER_ORIGIN__DESCRIPTION);
		createEReference(userOriginEClass, USER_ORIGIN__USERS);
		createEReference(userOriginEClass, USER_ORIGIN__GROUPS);
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
		contactEClass.getESuperTypes().add(this.getContactDescriptor());
		licenseOwnerEClass.getESuperTypes().add(this.getLicenseOwnerDescriptor());
		userEClass.getESuperTypes().add(this.getUserDescriptor());
		userEClass.getESuperTypes().add(this.getLicenseOwner());
		userGroupEClass.getESuperTypes().add(this.getUserGroupDescriptor());
		userGroupEClass.getESuperTypes().add(this.getLicenseOwner());
		userOriginEClass.getESuperTypes().add(this.getUserOriginDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(contactDescriptorEClass, ContactDescriptor.class, "ContactDescriptor", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(licenseOwnerDescriptorEClass, LicenseOwnerDescriptor.class, "LicenseOwnerDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(userDescriptorEClass, UserDescriptor.class, "UserDescriptor", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(userGroupDescriptorEClass, UserGroupDescriptor.class, "UserGroupDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(userOriginDescriptorEClass, UserOriginDescriptor.class, "UserOriginDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(contactEClass, Contact.class, "Contact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getContact_Name(), ecorePackage.getEString(), "name", null, 1, 1, Contact.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContact_Title(), ecorePackage.getEString(), "title", null, 0, 1, Contact.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContact_Position(), ecorePackage.getEString(), "position", null, 0, 1, Contact.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContact_Email(), ecorePackage.getEString(), "email", null, 1, 1, Contact.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContact_Address(), ecorePackage.getEString(), "address", null, 0, 1, Contact.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licenseOwnerEClass, LicenseOwner.class, "LicenseOwner", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicenseOwner_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, //$NON-NLS-1$
				LicenseOwner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseOwner_Name(), ecorePackage.getEString(), "name", null, 0, 1, LicenseOwner.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseOwner_Description(), ecorePackage.getEString(), "description", null, 0, 1, //$NON-NLS-1$
				LicenseOwner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicenseOwner_Contact(), this.getContact(), null, "contact", null, 1, 1, LicenseOwner.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userEClass, User.class, "User", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getUser_PreferredEvaluationType(), ecorePackage.getEString(), "preferredEvaluationType", null, 0, //$NON-NLS-1$
				1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getUser_PreferredEvaluationExpression(), ecorePackage.getEString(),
				"preferredEvaluationExpression", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUser_Origin(), this.getUserOrigin(), this.getUserOrigin_Users(), "origin", null, 1, 1, //$NON-NLS-1$
				User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userGroupEClass, UserGroup.class, "UserGroup", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserGroup_Users(), this.getUser(), null, "users", null, 0, -1, UserGroup.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getUserGroup_Origin(), this.getUserOrigin(), this.getUserOrigin_Groups(), "origin", null, 1, 1, //$NON-NLS-1$
				UserGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userOriginEClass, UserOrigin.class, "UserOrigin", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserOrigin_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				UserOrigin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserOrigin_Name(), ecorePackage.getEString(), "name", null, 0, 1, UserOrigin.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserOrigin_Description(), ecorePackage.getEString(), "description", null, 1, 1, //$NON-NLS-1$
				UserOrigin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getUserOrigin_Users(), this.getUser(), this.getUser_Origin(), "users", null, 0, -1, //$NON-NLS-1$
				UserOrigin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserOrigin_Groups(), this.getUserGroup(), this.getUserGroup_Origin(), "groups", null, 0, -1, //$NON-NLS-1$
				UserOrigin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //UsersPackageImpl
