/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.api.UserGrant;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriod;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.floating.model.api.VersionMatch;
import org.eclipse.passage.lic.floating.model.meta.FloatingFactory;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class FloatingPackageImpl extends EPackageImpl implements FloatingPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicensePackEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingServerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userGrantEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureGrantEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validityPeriodEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validityPeriodClosedEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluationInstructionsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass versionMatchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicenseAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingServerConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass grantAcqisitionEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FloatingPackageImpl() {
		super(eNS_URI, FloatingFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link FloatingPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FloatingPackage init() {
		if (isInited)
			return (FloatingPackage) EPackage.Registry.INSTANCE.getEPackage(FloatingPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFloatingPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FloatingPackageImpl theFloatingPackage = registeredFloatingPackage instanceof FloatingPackageImpl
				? (FloatingPackageImpl) registeredFloatingPackage
				: new FloatingPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theFloatingPackage.createPackageContents();

		// Initialize created meta-data
		theFloatingPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFloatingPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FloatingPackage.eNS_URI, theFloatingPackage);
		return theFloatingPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicensePack() {
		return floatingLicensePackEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_License() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Host() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Users() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Features() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicenseRequisites() {
		return licenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Identifier() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_IssueDate() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Company() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Plan() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Product() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Valid() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductRef() {
		return productRefEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Product() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Version() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingServer() {
		return floatingServerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServer_Identifier() {
		return (EAttribute) floatingServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingServer_Authentication() {
		return (EReference) floatingServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUserGrant() {
		return userGrantEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUserGrant_User() {
		return (EAttribute) userGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUserGrant_Authentication() {
		return (EReference) userGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureGrant() {
		return featureGrantEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Identifier() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Feature() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Version() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Valid() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Vivid() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Capacity() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Pack() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidityPeriod() {
		return validityPeriodEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodClosed() {
		return validityPeriodClosedEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidityPeriodClosed_From() {
		return (EAttribute) validityPeriodClosedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidityPeriodClosed_Until() {
		return (EAttribute) validityPeriodClosedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEvaluationInstructions() {
		return evaluationInstructionsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEvaluationInstructions_Type() {
		return (EAttribute) evaluationInstructionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEvaluationInstructions_Expression() {
		return (EAttribute) evaluationInstructionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVersionMatch() {
		return versionMatchEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVersionMatch_Version() {
		return (EAttribute) versionMatchEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVersionMatch_Rule() {
		return (EAttribute) versionMatchEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseAccess() {
		return floatingLicenseAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicenseAccess_User() {
		return (EAttribute) floatingLicenseAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicenseAccess_Server() {
		return (EReference) floatingLicenseAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicenseAccess_OriginLicensePack() {
		return (EAttribute) floatingLicenseAccessEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingServerConnection() {
		return floatingServerConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServerConnection_Ip() {
		return (EAttribute) floatingServerConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServerConnection_Port() {
		return (EAttribute) floatingServerConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingServerConnection_Authentication() {
		return (EReference) floatingServerConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGrantAcqisition() {
		return grantAcqisitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Identifier() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Feature() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Grant() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_User() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Created() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingFactory getFloatingFactory() {
		return (FloatingFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		floatingLicensePackEClass = createEClass(FLOATING_LICENSE_PACK);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__LICENSE);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__HOST);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__USERS);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__FEATURES);

		licenseRequisitesEClass = createEClass(LICENSE_REQUISITES);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__IDENTIFIER);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__ISSUE_DATE);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__COMPANY);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__PLAN);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__PRODUCT);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__VALID);

		productRefEClass = createEClass(PRODUCT_REF);
		createEAttribute(productRefEClass, PRODUCT_REF__PRODUCT);
		createEAttribute(productRefEClass, PRODUCT_REF__VERSION);

		floatingServerEClass = createEClass(FLOATING_SERVER);
		createEAttribute(floatingServerEClass, FLOATING_SERVER__IDENTIFIER);
		createEReference(floatingServerEClass, FLOATING_SERVER__AUTHENTICATION);

		userGrantEClass = createEClass(USER_GRANT);
		createEAttribute(userGrantEClass, USER_GRANT__USER);
		createEReference(userGrantEClass, USER_GRANT__AUTHENTICATION);

		featureGrantEClass = createEClass(FEATURE_GRANT);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__IDENTIFIER);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__FEATURE);
		createEReference(featureGrantEClass, FEATURE_GRANT__VERSION);
		createEReference(featureGrantEClass, FEATURE_GRANT__VALID);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__VIVID);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__CAPACITY);
		createEReference(featureGrantEClass, FEATURE_GRANT__PACK);

		validityPeriodEClass = createEClass(VALIDITY_PERIOD);

		validityPeriodClosedEClass = createEClass(VALIDITY_PERIOD_CLOSED);
		createEAttribute(validityPeriodClosedEClass, VALIDITY_PERIOD_CLOSED__FROM);
		createEAttribute(validityPeriodClosedEClass, VALIDITY_PERIOD_CLOSED__UNTIL);

		evaluationInstructionsEClass = createEClass(EVALUATION_INSTRUCTIONS);
		createEAttribute(evaluationInstructionsEClass, EVALUATION_INSTRUCTIONS__TYPE);
		createEAttribute(evaluationInstructionsEClass, EVALUATION_INSTRUCTIONS__EXPRESSION);

		versionMatchEClass = createEClass(VERSION_MATCH);
		createEAttribute(versionMatchEClass, VERSION_MATCH__VERSION);
		createEAttribute(versionMatchEClass, VERSION_MATCH__RULE);

		floatingLicenseAccessEClass = createEClass(FLOATING_LICENSE_ACCESS);
		createEAttribute(floatingLicenseAccessEClass, FLOATING_LICENSE_ACCESS__USER);
		createEReference(floatingLicenseAccessEClass, FLOATING_LICENSE_ACCESS__SERVER);
		createEAttribute(floatingLicenseAccessEClass, FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK);

		floatingServerConnectionEClass = createEClass(FLOATING_SERVER_CONNECTION);
		createEAttribute(floatingServerConnectionEClass, FLOATING_SERVER_CONNECTION__IP);
		createEAttribute(floatingServerConnectionEClass, FLOATING_SERVER_CONNECTION__PORT);
		createEReference(floatingServerConnectionEClass, FLOATING_SERVER_CONNECTION__AUTHENTICATION);

		grantAcqisitionEClass = createEClass(GRANT_ACQISITION);
		createEAttribute(grantAcqisitionEClass, GRANT_ACQISITION__IDENTIFIER);
		createEAttribute(grantAcqisitionEClass, GRANT_ACQISITION__FEATURE);
		createEAttribute(grantAcqisitionEClass, GRANT_ACQISITION__GRANT);
		createEAttribute(grantAcqisitionEClass, GRANT_ACQISITION__USER);
		createEAttribute(grantAcqisitionEClass, GRANT_ACQISITION__CREATED);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is
	 * guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
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
		validityPeriodClosedEClass.getESuperTypes().add(this.getValidityPeriod());

		// Initialize classes, features, and operations; add parameters
		initEClass(floatingLicensePackEClass, FloatingLicensePack.class, "FloatingLicensePack", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFloatingLicensePack_License(), this.getLicenseRequisites(), null, "license", null, 1, 1, //$NON-NLS-1$
				FloatingLicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingLicensePack_Host(), this.getFloatingServer(), null, "host", null, 1, 1, //$NON-NLS-1$
				FloatingLicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingLicensePack_Users(), this.getUserGrant(), null, "users", null, 1, -1, //$NON-NLS-1$
				FloatingLicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingLicensePack_Features(), this.getFeatureGrant(), this.getFeatureGrant_Pack(),
				"features", null, 1, -1, FloatingLicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licenseRequisitesEClass, LicenseRequisites.class, "LicenseRequisites", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicenseRequisites_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseRequisites_IssueDate(), ecorePackage.getEDate(), "issueDate", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseRequisites_Company(), ecorePackage.getEString(), "company", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseRequisites_Plan(), ecorePackage.getEString(), "plan", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicenseRequisites_Product(), this.getProductRef(), null, "product", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicenseRequisites_Valid(), this.getValidityPeriod(), null, "valid", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productRefEClass, ProductRef.class, "ProductRef", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductRef_Product(), ecorePackage.getEString(), "product", null, 1, 1, ProductRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductRef_Version(), ecorePackage.getEString(), "version", null, 1, 1, ProductRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingServerEClass, FloatingServer.class, "FloatingServer", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingServer_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				FloatingServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingServer_Authentication(), this.getEvaluationInstructions(), null, "authentication", //$NON-NLS-1$
				null, 1, 1, FloatingServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(userGrantEClass, UserGrant.class, "UserGrant", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserGrant_User(), ecorePackage.getEString(), "user", null, 1, 1, UserGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserGrant_Authentication(), this.getEvaluationInstructions(), null, "authentication", null, 1, //$NON-NLS-1$
				1, UserGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featureGrantEClass, FeatureGrant.class, "FeatureGrant", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureGrant_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				FeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureGrant_Feature(), ecorePackage.getEString(), "feature", null, 1, 1, FeatureGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureGrant_Version(), this.getVersionMatch(), null, "version", null, 1, 1, //$NON-NLS-1$
				FeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureGrant_Valid(), this.getValidityPeriod(), null, "valid", null, 1, 1, FeatureGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureGrant_Vivid(), ecorePackage.getELong(), "vivid", null, 1, 1, FeatureGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeatureGrant_Capacity(), ecorePackage.getEInt(), "capacity", null, 1, 1, FeatureGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureGrant_Pack(), this.getFloatingLicensePack(), this.getFloatingLicensePack_Features(),
				"pack", null, 1, 1, FeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validityPeriodEClass, ValidityPeriod.class, "ValidityPeriod", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(validityPeriodClosedEClass, ValidityPeriodClosed.class, "ValidityPeriodClosed", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidityPeriodClosed_From(), ecorePackage.getEDate(), "from", null, 1, 1, //$NON-NLS-1$
				ValidityPeriodClosed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getValidityPeriodClosed_Until(), ecorePackage.getEDate(), "until", null, 1, 1, //$NON-NLS-1$
				ValidityPeriodClosed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(evaluationInstructionsEClass, EvaluationInstructions.class, "EvaluationInstructions", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvaluationInstructions_Type(), ecorePackage.getEString(), "type", null, 1, 1, //$NON-NLS-1$
				EvaluationInstructions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluationInstructions_Expression(), ecorePackage.getEString(), "expression", null, 1, 1, //$NON-NLS-1$
				EvaluationInstructions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(versionMatchEClass, VersionMatch.class, "VersionMatch", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVersionMatch_Version(), ecorePackage.getEString(), "version", null, 1, 1, VersionMatch.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVersionMatch_Rule(), ecorePackage.getEString(), "rule", null, 1, 1, VersionMatch.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingLicenseAccessEClass, FloatingLicenseAccess.class, "FloatingLicenseAccess", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingLicenseAccess_User(), ecorePackage.getEString(), "user", null, 1, 1, //$NON-NLS-1$
				FloatingLicenseAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingLicenseAccess_Server(), this.getFloatingServerConnection(), null, "server", null, 1, //$NON-NLS-1$
				1, FloatingLicenseAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFloatingLicenseAccess_OriginLicensePack(), ecorePackage.getEString(), "originLicensePack", //$NON-NLS-1$
				null, 1, 1, FloatingLicenseAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingServerConnectionEClass, FloatingServerConnection.class, "FloatingServerConnection", //$NON-NLS-1$
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingServerConnection_Ip(), ecorePackage.getEString(), "ip", null, 1, 1, //$NON-NLS-1$
				FloatingServerConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFloatingServerConnection_Port(), ecorePackage.getEInt(), "port", null, 1, 1, //$NON-NLS-1$
				FloatingServerConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingServerConnection_Authentication(), this.getEvaluationInstructions(), null,
				"authentication", null, 1, 1, FloatingServerConnection.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(grantAcqisitionEClass, GrantAcqisition.class, "GrantAcqisition", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGrantAcqisition_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				GrantAcqisition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrantAcqisition_Feature(), ecorePackage.getEString(), "feature", null, 1, 1, //$NON-NLS-1$
				GrantAcqisition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrantAcqisition_Grant(), ecorePackage.getEString(), "grant", null, 1, 1, //$NON-NLS-1$
				GrantAcqisition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrantAcqisition_User(), ecorePackage.getEString(), "user", null, 1, 1, GrantAcqisition.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrantAcqisition_Created(), ecorePackage.getEDate(), "created", null, 1, 1, //$NON-NLS-1$
				GrantAcqisition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // FloatingPackageImpl
