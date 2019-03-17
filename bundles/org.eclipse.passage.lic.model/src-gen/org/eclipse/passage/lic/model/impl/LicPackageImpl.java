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
package org.eclipse.passage.lic.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.model.api.LicenseGrant;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.model.meta.LicFactory;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LicPackageImpl extends EPackageImpl implements LicPackage {
  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePackDescriptorEClass = null;

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseGrantDescriptorEClass = null;

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licensePackEClass = null;

		/**
	 * <!-- begin-user-doc -->
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
   * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
  private LicPackageImpl() {
		super(eNS_URI, LicFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LicPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
  public static LicPackage init() {
		if (isInited) return (LicPackage)EPackage.Registry.INSTANCE.getEPackage(LicPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredLicPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		LicPackageImpl theLicPackage = registeredLicPackage instanceof LicPackageImpl ? (LicPackageImpl)registeredLicPackage : new LicPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theLicPackage.createPackageContents();

		// Initialize created meta-data
		theLicPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLicPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LicPackage.eNS_URI, theLicPackage);
		return theLicPackage;
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicensePackDescriptor() {
		return licensePackDescriptorEClass;
	}

		/**
	 * <!-- begin-user-doc -->
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
	public EClass getLicensePack() {
		return licensePackEClass;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_Identifier() {
		return (EAttribute)licensePackEClass.getEStructuralFeatures().get(0);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_IssueDate() {
		return (EAttribute)licensePackEClass.getEStructuralFeatures().get(1);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_ProductIdentifier() {
		return (EAttribute)licensePackEClass.getEStructuralFeatures().get(2);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_ProductVersion() {
		return (EAttribute)licensePackEClass.getEStructuralFeatures().get(3);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_UserIdentifier() {
		return (EAttribute)licensePackEClass.getEStructuralFeatures().get(4);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicensePack_LicenseGrants() {
		return (EReference)licensePackEClass.getEStructuralFeatures().get(5);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicenseGrant() {
		return licenseGrantEClass;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_FeatureIdentifier() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(0);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_MatchVersion() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(1);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_MatchRule() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(2);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ValidFrom() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(3);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ValidUntil() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(4);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ConditionType() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(5);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ConditionExpression() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(6);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_Capacity() {
		return (EAttribute)licenseGrantEClass.getEStructuralFeatures().get(7);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseGrant_LicensePack() {
		return (EReference)licenseGrantEClass.getEStructuralFeatures().get(8);
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicFactory getLicFactory() {
		return (LicFactory)getEFactoryInstance();
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
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		licensePackDescriptorEClass = createEClass(LICENSE_PACK_DESCRIPTOR);

		licenseGrantDescriptorEClass = createEClass(LICENSE_GRANT_DESCRIPTOR);

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
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		licensePackEClass.getESuperTypes().add(this.getLicensePackDescriptor());
		licenseGrantEClass.getESuperTypes().add(this.getLicenseGrantDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(licensePackDescriptorEClass, LicensePackDescriptor.class, "LicensePackDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(licenseGrantDescriptorEClass, LicenseGrantDescriptor.class, "LicenseGrantDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(licensePackEClass, LicensePack.class, "LicensePack", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLicensePack_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicensePack_IssueDate(), ecorePackage.getEDate(), "issueDate", null, 0, 1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicensePack_ProductIdentifier(), ecorePackage.getEString(), "productIdentifier", null, 1, 1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicensePack_ProductVersion(), ecorePackage.getEString(), "productVersion", null, 1, 1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicensePack_UserIdentifier(), ecorePackage.getEString(), "userIdentifier", null, 1, 1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLicensePack_LicenseGrants(), this.getLicenseGrant(), this.getLicenseGrant_LicensePack(), "licenseGrants", null, 0, -1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(licenseGrantEClass, LicenseGrant.class, "LicenseGrant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_FeatureIdentifier(), ecorePackage.getEString(), "featureIdentifier", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_MatchVersion(), ecorePackage.getEString(), "matchVersion", "0.0.0", 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getLicenseGrant_MatchRule(), ecorePackage.getEString(), "matchRule", null, 0, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_ValidFrom(), ecorePackage.getEDate(), "validFrom", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_ValidUntil(), ecorePackage.getEDate(), "validUntil", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_ConditionType(), ecorePackage.getEString(), "conditionType", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_ConditionExpression(), ecorePackage.getEString(), "conditionExpression", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicenseGrant_Capacity(), ecorePackage.getEInt(), "capacity", "1", 0, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getLicenseGrant_LicensePack(), this.getLicensePack(), this.getLicensePack_LicenseGrants(), "licensePack", null, 1, 1, LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //PassagePackageImpl
