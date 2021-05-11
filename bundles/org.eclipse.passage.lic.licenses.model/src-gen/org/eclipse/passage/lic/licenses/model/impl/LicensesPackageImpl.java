/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.ProductRefDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.FloatingServer;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;
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
	private EClass productRefDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseRequisitesDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personalLicenseRequisitesDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicenseRequisitesDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validityPeriodDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validityPeriodClosedDescriptorEClass = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicensePackEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personalLicenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingLicenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userGrantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureGrantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validityPeriodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validityPeriodClosedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluationInstructionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	public EClass getProductRefDescriptor() {
		return productRefDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicenseRequisitesDescriptor() {
		return licenseRequisitesDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPersonalLicenseRequisitesDescriptor() {
		return personalLicenseRequisitesDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseRequisitesDescriptor() {
		return floatingLicenseRequisitesDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodDescriptor() {
		return validityPeriodDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodClosedDescriptor() {
		return validityPeriodClosedDescriptorEClass;
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
	public EReference getLicensePlan_Personal() {
		return (EReference) licensePlanEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicensePlan_Floating() {
		return (EReference) licensePlanEClass.getEStructuralFeatures().get(5);
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
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_ProductVersion() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_UserIdentifier() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_UserFullName() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_RequestIdentifier() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicensePack_PlanIdentifier() {
		return (EAttribute) licensePackEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicensePack_LicenseGrants() {
		return (EReference) licensePackEClass.getEStructuralFeatures().get(8);
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
	public EAttribute getLicenseGrant_Identifier() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_FeatureIdentifier() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_MatchVersion() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_MatchRule() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ValidFrom() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ValidUntil() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ConditionType() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_ConditionExpression() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseGrant_Capacity() {
		return (EAttribute) licenseGrantEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseGrant_LicensePack() {
		return (EReference) licenseGrantEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicensePack() {
		return floatingLicensePackEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_License() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Host() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Users() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Features() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLicenseRequisites() {
		return licenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Identifier() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_IssueDate() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Plan() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Product() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Valid() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPersonalLicenseRequisites() {
		return personalLicenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPersonalLicenseRequisites_User() {
		return (EAttribute) personalLicenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseRequisites() {
		return floatingLicenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicenseRequisites_Company() {
		return (EAttribute) floatingLicenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductRef() {
		return productRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Identifier() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Version() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatingServer() {
		return floatingServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServer_Identifier() {
		return (EAttribute) floatingServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatingServer_Authentication() {
		return (EReference) floatingServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUserGrant() {
		return userGrantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUserGrant_User() {
		return (EAttribute) userGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUserGrant_Authentication() {
		return (EReference) userGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureGrant() {
		return featureGrantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Identifier() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Feature() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Version() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Capacity() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Pack() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidityPeriod() {
		return validityPeriodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodClosed() {
		return validityPeriodClosedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidityPeriodClosed_From() {
		return (EAttribute) validityPeriodClosedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidityPeriodClosed_Until() {
		return (EAttribute) validityPeriodClosedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEvaluationInstructions() {
		return evaluationInstructionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEvaluationInstructions_Type() {
		return (EAttribute) evaluationInstructionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEvaluationInstructions_Expression() {
		return (EAttribute) evaluationInstructionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVersionMatch() {
		return versionMatchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVersionMatch_Version() {
		return (EAttribute) versionMatchEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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

		productRefDescriptorEClass = createEClass(PRODUCT_REF_DESCRIPTOR);

		licenseRequisitesDescriptorEClass = createEClass(LICENSE_REQUISITES_DESCRIPTOR);

		personalLicenseRequisitesDescriptorEClass = createEClass(PERSONAL_LICENSE_REQUISITES_DESCRIPTOR);

		floatingLicenseRequisitesDescriptorEClass = createEClass(FLOATING_LICENSE_REQUISITES_DESCRIPTOR);

		validityPeriodDescriptorEClass = createEClass(VALIDITY_PERIOD_DESCRIPTOR);

		validityPeriodClosedDescriptorEClass = createEClass(VALIDITY_PERIOD_CLOSED_DESCRIPTOR);

		licensePlanEClass = createEClass(LICENSE_PLAN);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__IDENTIFIER);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__NAME);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__DESCRIPTION);
		createEReference(licensePlanEClass, LICENSE_PLAN__LICENSE_PLAN_FEATURES);
		createEReference(licensePlanEClass, LICENSE_PLAN__PERSONAL);
		createEReference(licensePlanEClass, LICENSE_PLAN__FLOATING);

		licensePlanFeatureEClass = createEClass(LICENSE_PLAN_FEATURE);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__MATCH_VERSION);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__MATCH_RULE);
		createEReference(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__LICENSE_PLAN);

		licensePackEClass = createEClass(LICENSE_PACK);
		createEAttribute(licensePackEClass, LICENSE_PACK__IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__ISSUE_DATE);
		createEAttribute(licensePackEClass, LICENSE_PACK__USER_IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__USER_FULL_NAME);
		createEAttribute(licensePackEClass, LICENSE_PACK__REQUEST_IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__PLAN_IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__PRODUCT_IDENTIFIER);
		createEAttribute(licensePackEClass, LICENSE_PACK__PRODUCT_VERSION);
		createEReference(licensePackEClass, LICENSE_PACK__LICENSE_GRANTS);

		licenseGrantEClass = createEClass(LICENSE_GRANT);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__IDENTIFIER);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__FEATURE_IDENTIFIER);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__MATCH_VERSION);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__MATCH_RULE);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__VALID_FROM);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__VALID_UNTIL);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__CONDITION_TYPE);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__CONDITION_EXPRESSION);
		createEAttribute(licenseGrantEClass, LICENSE_GRANT__CAPACITY);
		createEReference(licenseGrantEClass, LICENSE_GRANT__LICENSE_PACK);

		floatingLicensePackEClass = createEClass(FLOATING_LICENSE_PACK);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__LICENSE);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__HOST);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__USERS);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__FEATURES);

		licenseRequisitesEClass = createEClass(LICENSE_REQUISITES);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__IDENTIFIER);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__ISSUE_DATE);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__PLAN);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__PRODUCT);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__VALID);

		personalLicenseRequisitesEClass = createEClass(PERSONAL_LICENSE_REQUISITES);
		createEAttribute(personalLicenseRequisitesEClass, PERSONAL_LICENSE_REQUISITES__USER);

		floatingLicenseRequisitesEClass = createEClass(FLOATING_LICENSE_REQUISITES);
		createEAttribute(floatingLicenseRequisitesEClass, FLOATING_LICENSE_REQUISITES__COMPANY);

		productRefEClass = createEClass(PRODUCT_REF);
		createEAttribute(productRefEClass, PRODUCT_REF__IDENTIFIER);
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
		personalLicenseRequisitesDescriptorEClass.getESuperTypes().add(this.getLicenseRequisitesDescriptor());
		floatingLicenseRequisitesDescriptorEClass.getESuperTypes().add(this.getLicenseRequisitesDescriptor());
		licensePlanEClass.getESuperTypes().add(this.getLicensePlanDescriptor());
		licensePlanFeatureEClass.getESuperTypes().add(this.getLicensePlanFeatureDescriptor());
		licensePackEClass.getESuperTypes().add(this.getLicensePackDescriptor());
		licenseGrantEClass.getESuperTypes().add(this.getLicenseGrantDescriptor());
		licenseRequisitesEClass.getESuperTypes().add(this.getLicenseRequisitesDescriptor());
		personalLicenseRequisitesEClass.getESuperTypes().add(this.getLicenseRequisites());
		personalLicenseRequisitesEClass.getESuperTypes().add(this.getPersonalLicenseRequisitesDescriptor());
		floatingLicenseRequisitesEClass.getESuperTypes().add(this.getLicenseRequisites());
		floatingLicenseRequisitesEClass.getESuperTypes().add(this.getFloatingLicenseRequisitesDescriptor());
		productRefEClass.getESuperTypes().add(this.getProductRefDescriptor());
		validityPeriodEClass.getESuperTypes().add(this.getValidityPeriodDescriptor());
		validityPeriodClosedEClass.getESuperTypes().add(this.getValidityPeriod());
		validityPeriodClosedEClass.getESuperTypes().add(this.getValidityPeriodClosedDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(licensePlanDescriptorEClass, LicensePlanDescriptor.class, "LicensePlanDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licensePlanFeatureDescriptorEClass, LicensePlanFeatureDescriptor.class,
				"LicensePlanFeatureDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(licensePackDescriptorEClass, LicensePackDescriptor.class, "LicensePackDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licenseGrantDescriptorEClass, LicenseGrantDescriptor.class, "LicenseGrantDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(productRefDescriptorEClass, ProductRefDescriptor.class, "ProductRefDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licenseRequisitesDescriptorEClass, LicenseRequisitesDescriptor.class, "LicenseRequisitesDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(personalLicenseRequisitesDescriptorEClass, PersonalLicenseRequisitesDescriptor.class,
				"PersonalLicenseRequisitesDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(floatingLicenseRequisitesDescriptorEClass, FloatingLicenseRequisitesDescriptor.class,
				"FloatingLicenseRequisitesDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(validityPeriodDescriptorEClass, ValidityPeriodDescriptor.class, "ValidityPeriodDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(validityPeriodClosedDescriptorEClass, ValidityPeriodClosedDescriptor.class,
				"ValidityPeriodClosedDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
		initEReference(getLicensePlan_Personal(), this.getLicensePack(), null, "personal", null, 0, -1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlan_Floating(), this.getFloatingLicensePack(), null, "floating", null, 0, -1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEAttribute(getLicensePack_UserIdentifier(), ecorePackage.getEString(), "userIdentifier", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_UserFullName(), ecorePackage.getEString(), "userFullName", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_RequestIdentifier(), ecorePackage.getEString(), "requestIdentifier", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_PlanIdentifier(), ecorePackage.getEString(), "planIdentifier", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_ProductIdentifier(), ecorePackage.getEString(), "productIdentifier", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePack_ProductVersion(), ecorePackage.getEString(), "productVersion", null, 1, 1, //$NON-NLS-1$
				LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePack_LicenseGrants(), this.getLicenseGrant(), this.getLicenseGrant_LicensePack(),
				"licenseGrants", null, 0, -1, LicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licenseGrantEClass, LicenseGrant.class, "LicenseGrant", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicenseGrant_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, //$NON-NLS-1$
				LicenseGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
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

		initEClass(floatingLicensePackEClass, FloatingLicensePack.class, "FloatingLicensePack", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFloatingLicensePack_License(), this.getFloatingLicenseRequisites(), null, "license", null, 1, //$NON-NLS-1$
				1, FloatingLicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
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

		initEClass(licenseRequisitesEClass, LicenseRequisites.class, "LicenseRequisites", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicenseRequisites_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicenseRequisites_IssueDate(), ecorePackage.getEDate(), "issueDate", null, 1, 1, //$NON-NLS-1$
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

		initEClass(personalLicenseRequisitesEClass, PersonalLicenseRequisites.class, "PersonalLicenseRequisites", //$NON-NLS-1$
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersonalLicenseRequisites_User(), ecorePackage.getEString(), "user", null, 1, 1, //$NON-NLS-1$
				PersonalLicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingLicenseRequisitesEClass, FloatingLicenseRequisites.class, "FloatingLicenseRequisites", //$NON-NLS-1$
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingLicenseRequisites_Company(), ecorePackage.getEString(), "company", null, 1, 1, //$NON-NLS-1$
				FloatingLicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productRefEClass, ProductRef.class, "ProductRef", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductRef_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				ProductRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
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

} // LicensesPackageImpl
