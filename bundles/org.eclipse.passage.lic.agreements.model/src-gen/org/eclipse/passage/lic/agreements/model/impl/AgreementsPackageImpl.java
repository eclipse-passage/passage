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
package org.eclipse.passage.lic.agreements.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.passage.lic.agreements.AgreementDescriptor;

import org.eclipse.passage.lic.agreements.AgreementsGroupDescriptor;
import org.eclipse.passage.lic.agreements.model.api.Agreement;

import org.eclipse.passage.lic.agreements.model.api.AgreementsGroup;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsFactory;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AgreementsPackageImpl extends EPackageImpl implements AgreementsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agreementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agreementsGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agreementDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agreementsGroupDescriptorEClass = null;

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
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AgreementsPackageImpl() {
		super(eNS_URI, AgreementsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AgreementsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AgreementsPackage init() {
		if (isInited)
			return (AgreementsPackage) EPackage.Registry.INSTANCE.getEPackage(AgreementsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAgreementsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		AgreementsPackageImpl theAgreementsPackage = registeredAgreementsPackage instanceof AgreementsPackageImpl
				? (AgreementsPackageImpl) registeredAgreementsPackage
				: new AgreementsPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theAgreementsPackage.createPackageContents();

		// Initialize created meta-data
		theAgreementsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAgreementsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AgreementsPackage.eNS_URI, theAgreementsPackage);
		return theAgreementsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgreement() {
		return agreementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreement_Identifier() {
		return (EAttribute) agreementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreement_Spdx() {
		return (EAttribute) agreementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreement_Name() {
		return (EAttribute) agreementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreement_File() {
		return (EAttribute) agreementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreement_Mime() {
		return (EAttribute) agreementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgreementsGroup() {
		return agreementsGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreementsGroup_Name() {
		return (EAttribute) agreementsGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgreementsGroup_Description() {
		return (EAttribute) agreementsGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAgreementsGroup_Agreements() {
		return (EReference) agreementsGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgreementDescriptor() {
		return agreementDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgreementsGroupDescriptor() {
		return agreementsGroupDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreementsFactory getAgreementsFactory() {
		return (AgreementsFactory) getEFactoryInstance();
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
		agreementDescriptorEClass = createEClass(AGREEMENT_DESCRIPTOR);

		agreementsGroupDescriptorEClass = createEClass(AGREEMENTS_GROUP_DESCRIPTOR);

		agreementEClass = createEClass(AGREEMENT);
		createEAttribute(agreementEClass, AGREEMENT__IDENTIFIER);
		createEAttribute(agreementEClass, AGREEMENT__SPDX);
		createEAttribute(agreementEClass, AGREEMENT__NAME);
		createEAttribute(agreementEClass, AGREEMENT__FILE);
		createEAttribute(agreementEClass, AGREEMENT__MIME);

		agreementsGroupEClass = createEClass(AGREEMENTS_GROUP);
		createEAttribute(agreementsGroupEClass, AGREEMENTS_GROUP__NAME);
		createEAttribute(agreementsGroupEClass, AGREEMENTS_GROUP__DESCRIPTION);
		createEReference(agreementsGroupEClass, AGREEMENTS_GROUP__AGREEMENTS);
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
		agreementEClass.getESuperTypes().add(this.getAgreementDescriptor());
		agreementsGroupEClass.getESuperTypes().add(this.getAgreementsGroupDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(agreementDescriptorEClass, AgreementDescriptor.class, "AgreementDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(agreementsGroupDescriptorEClass, AgreementsGroupDescriptor.class, "AgreementsGroupDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(agreementEClass, Agreement.class, "Agreement", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgreement_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, Agreement.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreement_Spdx(), ecorePackage.getEString(), "spdx", null, 0, 1, Agreement.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreement_Name(), ecorePackage.getEString(), "name", null, 1, 1, Agreement.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreement_File(), ecorePackage.getEString(), "file", null, 1, 1, Agreement.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreement_Mime(), ecorePackage.getEString(), "mime", null, 1, 1, Agreement.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agreementsGroupEClass, AgreementsGroup.class, "AgreementsGroup", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgreementsGroup_Name(), ecorePackage.getEString(), "name", null, 1, 1, AgreementsGroup.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreementsGroup_Description(), ecorePackage.getEString(), "description", null, 1, 1, //$NON-NLS-1$
				AgreementsGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAgreementsGroup_Agreements(), this.getAgreement(), null, "agreements", null, 0, -1, //$NON-NLS-1$
				AgreementsGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //AgreementsPackageImpl
