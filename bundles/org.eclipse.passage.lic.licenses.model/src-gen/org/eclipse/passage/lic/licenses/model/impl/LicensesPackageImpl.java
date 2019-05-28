/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.licenses.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model <b>Package</b>.
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class LicensesPackageImpl extends EPackageImpl implements LicensesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePlanDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePlanFeatureDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePackDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseGrantDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePlanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePlanFeatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePackEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseGrantEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LicensesPackageImpl() {
		super(eNS_URI, LicensesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link LicensesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LicensesPackage init() {
		if (isInited)
			return (LicensesPackage) EPackage.Registry.INSTANCE.getEPackage(LicensesPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredLicensesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		LicensesPackageImpl theLicensesPackage = registeredLicensesPackage instanceof LicensesPackageImpl
				? (LicensesPackageImpl) registeredLicensesPackage
				: new LicensesPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theLicensesPackage.createPackageContents();

		// Initialize created meta-data
		theLicensesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLicensesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LicensesPackage.eNS_URI, theLicensesPackage);
		return theLicensesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePlanDescriptor() {
		return licensePlanDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePlanFeatureDescriptor() {
		return licensePlanFeatureDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePackDescriptor() {
		return licensePackDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicenseGrantDescriptor() {
		return licenseGrantDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePlan() {
		return licensePlanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlan_Identifier() {
		return (EAttribute) licensePlanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlan_Name() {
		return (EAttribute) licensePlanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlan_Description() {
		return (EAttribute) licensePlanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicensePlan_LicensePlanFeatures() {
		return (EReference) licensePlanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePlanFeature() {
		return licensePlanFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlanFeature_FeatureIdentifier() {
		return (EAttribute) licensePlanFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlanFeature_MatchVersion() {
		return (EAttribute) licensePlanFeatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlanFeature_MatchRule() {
		return (EAttribute) licensePlanFeatureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicensePlanFeature_LicensePlan() {
		return (EReference) licensePlanFeatureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePack() {
		return licensePackEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_Identifier() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_IssueDate() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_ProductIdentifier() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_ProductVersion() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_UserIdentifier() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicensePack_LicenseGrants() {
		return (EReference) licensePackEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicenseGrant() {
		return licenseGrantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_FeatureIdentifier() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_MatchVersion() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_MatchRule() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ValidFrom() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ValidUntil() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ConditionType() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ConditionExpression() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_Capacity() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseGrant_LicensePack() {
		return (EReference) licenseGrantEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensesFactory getLicensesFactory() {
		return (LicensesFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		licensePlanDescriptorEClass = createEClass(LICENSE_PLAN_DESCRIPTOR);

		licensePlanFeatureDescriptorEClass = createEClass(LICENSE_PLAN_FEATURE_DESCRIPTOR);

		licensePackDescriptorEClass = createEClass(LICENSE_PACK_DESCRIPTOR);

		licenseGrantDescriptorEClass = createEClass(LICENSE_GRANT_DESCRIPTOR);

		licensePlanEClass = createEClass(LICENSE_PLAN);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__IDENTIFIER);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__NAME);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__DESCRIPTION);
		createEReference(licensePlanEClass, LICENSE_PLAN__LICENSE_PLAN_FEATURES);

		licensePlanFeatureEClass = createEClass(LICENSE_PLAN_FEATURE);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__MATCH_VERSION);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__MATCH_RULE);
		createEReference(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__LICENSE_PLAN);

		licensePackEClass = createEClass(LICENSE_PACK);
		createEAttribute(licensePackEClass, LICENSE_PACK__IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__ISSUE_DATE);
		createEAttribute(licensePackEClass, LICENSE_PACK__PRODUCT_IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__PRODUCT_VERSION);
		createEAttribute(licensePackEClass, LICENSE_PACK__USER_IDENTIFIER);
		createEReference(licensePackEClass, LICENSE_PACK__LICENSE_GRANTS);

		licenseGrantEClass = createEClass(LICENSE_GRANT);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__FEATURE_IDENTIFIER);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__MATCH_VERSION);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__MATCH_RULE);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__VALID_FROM);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__VALID_UNTIL);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__CONDITION_TYPE);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__CONDITION_EXPRESSION);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__CAPACITY);
		createEReference(licenseGrantEClass, LICENSE_GRANT__LICENSE_PACK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * 
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
		licensePlanEClass.getESuperTypes().add(this.getLicensePlanDescriptor());
		licensePlanFeatureEClass.getESuperTypes().add(this.getLicensePlanFeatureDescriptor());
		licensePackEClass.getESuperTypes().add(this.getLicensePackDescriptor());
		licenseGrantEClass.getESuperTypes().add(this.getLicenseGrantDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(licensePlanDescriptorEClass, LicensePlanDescriptor.class, "LicensePlanDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licensePlanFeatureDescriptorEClass, LicensePlanFeatureDescriptor.class,
				"LicensePlanFeatureDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(licensePackDescriptorEClass, LicensePackDescriptor.class, "LicensePackDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licenseGrantDescriptorEClass, LicenseGrantDescriptor.class, "LicenseGrantDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licensePlanEClass, LicensePlan.class, "LicensePlan", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicensePlan_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePlan_Name(), ecorePackage.getEString(), "name", null, 0, 1, LicensePlan.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePlan_Description(), ecorePackage.getEString(), "description", null, 0, 1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlan_LicensePlanFeatures(), this.getLicensePlanFeatureDescriptor(), null,
				"licensePlanFeatures", null, 0, -1, LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licensePlanFeatureEClass, LicensePlanFeature.class, "LicensePlanFeature", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicensePlanFeature_FeatureIdentifier(), ecorePackage.getEString(), "featureIdentifier", null, //$NON-NLS-1$
				1, 1, LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePlanFeature_MatchVersion(), ecorePackage.getEString(), "matchVersion", "0.0.0", 1, 1, //$NON-NLS-1$//$NON-NLS-2$
				LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePlanFeature_MatchRule(), ecorePackage.getEString(), "matchRule", null, 0, 1, //$NON-NLS-1$
				LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlanFeature_LicensePlan(), this.getLicensePlan(), null, "licensePlan", null, 1, 1, //$NON-NLS-1$
				LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licensePackEClass, LicensePack.class, "LicensePack", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicensePack_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_IssueDate(), ecorePackage.getEDate(), "issueDate", null, 0, 1, LicensePack.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_ProductIdentifier(), ecorePackage.getEString(), "productIdentifier", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_ProductVersion(), ecorePackage.getEString(), "productVersion", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_UserIdentifier(), ecorePackage.getEString(), "userIdentifier", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePack_LicenseGrants(), this.getLicenseGrant(), this.getLicenseGrant_LicensePack(),
				"licenseGrants", null, 0, -1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licenseGrantEClass, LicenseGrant.class, "LicenseGrant", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicenseGrant_FeatureIdentifier(), ecorePackage.getEString(), "featureIdentifier", null, 1, 1, //$NON-NLS-1$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_MatchVersion(), ecorePackage.getEString(), "matchVersion", "0.0.0", 1, 1, //$NON-NLS-1$//$NON-NLS-2$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_MatchRule(), ecorePackage.getEString(), "matchRule", null, 0, 1, //$NON-NLS-1$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_ValidFrom(), ecorePackage.getEDate(), "validFrom", null, 1, 1, //$NON-NLS-1$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_ValidUntil(), ecorePackage.getEDate(), "validUntil", null, 1, 1, //$NON-NLS-1$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_ConditionType(), ecorePackage.getEString(), "conditionType", null, 1, 1, //$NON-NLS-1$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_ConditionExpression(), ecorePackage.getEString(), "conditionExpression", null, 1, //$NON-NLS-1$
				1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseGrant_Capacity(), ecorePackage.getEInt(), "capacity", "1", 0, 1, LicenseGrant.class, //$NON-NLS-1$//$NON-NLS-2$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicenseGrant_LicensePack(), this.getLicensePack(), this.getLicensePack_LicenseGrants(),
				"licensePack", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // LicensesPackageImpl
