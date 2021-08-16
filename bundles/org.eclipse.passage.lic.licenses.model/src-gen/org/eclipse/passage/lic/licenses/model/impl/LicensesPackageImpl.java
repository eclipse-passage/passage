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
package org.eclipse.passage.lic.licenses.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.passage.lic.licenses.AgreementDataDescriptor;
import org.eclipse.passage.lic.licenses.CompanyRefDescriptor;
import org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor;
import org.eclipse.passage.lic.licenses.FeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.FeatureRefDescriptor;
import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.FloatingServerDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.ProductRefDescriptor;
import org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor;
import org.eclipse.passage.lic.licenses.SignatureDescriptor;
import org.eclipse.passage.lic.licenses.UserGrantDescriptor;
import org.eclipse.passage.lic.licenses.UserRefDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor;
import org.eclipse.passage.lic.licenses.VersionMatchDescriptor;

import org.eclipse.passage.lic.licenses.model.api.AgreementData;
import org.eclipse.passage.lic.licenses.model.api.CompanyRef;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FeatureRef;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.FloatingServer;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.api.SignatureAttribute;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.api.UserRef;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;

import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LicensesPackageImpl extends EPackageImpl implements LicensesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	private EClass agreementDataDescriptorEClass = null;

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
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass personalLicensePackDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass productRefDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass featureRefDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass userRefDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass companyRefDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass licenseRequisitesDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass personalFeatureGrantDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass personalLicenseRequisitesDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingLicenseRequisitesDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass validityPeriodDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass validityPeriodClosedDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingLicensePackDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingServerDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass userGrantDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass featureGrantDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass evaluationInstructionsDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass versionMatchDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	private EClass agreementDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass signatureDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass signatureAttributeDescriptorEClass = null;

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
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass personalFeatureGrantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass personalLicensePackEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingLicensePackEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	private EClass licenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass personalLicenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingLicenseRequisitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass productRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass featureRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass userRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass companyRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass userGrantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass featureGrantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass validityPeriodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass validityPeriodClosedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass evaluationInstructionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass versionMatchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingLicenseAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass floatingServerConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass grantAcqisitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass signatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	private EClass signatureAttributeEClass = null;

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
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LicensesPackageImpl() {
		super(eNS_URI, LicensesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LicensesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
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
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EClass getAgreementDataDescriptor() {
		return agreementDataDescriptorEClass;
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
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getPersonalLicensePackDescriptor() {
		return personalLicensePackDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getProductRefDescriptor() {
		return productRefDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFeatureRefDescriptor() {
		return featureRefDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getUserRefDescriptor() {
		return userRefDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getCompanyRefDescriptor() {
		return companyRefDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getLicenseRequisitesDescriptor() {
		return licenseRequisitesDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getPersonalFeatureGrantDescriptor() {
		return personalFeatureGrantDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getPersonalLicenseRequisitesDescriptor() {
		return personalLicenseRequisitesDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseRequisitesDescriptor() {
		return floatingLicenseRequisitesDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodDescriptor() {
		return validityPeriodDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodClosedDescriptor() {
		return validityPeriodClosedDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingLicensePackDescriptor() {
		return floatingLicensePackDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingServerDescriptor() {
		return floatingServerDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getUserGrantDescriptor() {
		return userGrantDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFeatureGrantDescriptor() {
		return featureGrantDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getEvaluationInstructionsDescriptor() {
		return evaluationInstructionsDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getVersionMatchDescriptor() {
		return versionMatchDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EClass getAgreementData() {
		return agreementDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_Identifier() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_Name() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_File() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_HashAlgo() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_Hash() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_Content() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getAgreementData_ContentType() {
		return (EAttribute) agreementDataEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getSignatureDescriptor() {
		return signatureDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getSignatureAttributeDescriptor() {
		return signatureAttributeDescriptorEClass;
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
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlan_Agreements() {
		return (EAttribute) licensePlanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getLicensePlan_Features() {
		return (EReference) licensePlanEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getLicensePlan_Personal() {
		return (EReference) licensePlanEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getLicensePlan_Floating() {
		return (EReference) licensePlanEClass.getEStructuralFeatures().get(6);
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
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getLicensePlanFeature_Feature() {
		return (EReference) licensePlanFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getLicensePlanFeature_Plan() {
		return (EReference) licensePlanFeatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlanFeature_Vivid() {
		return (EAttribute) licensePlanFeatureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getLicensePlanFeature_Capacity() {
		return (EAttribute) licensePlanFeatureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getPersonalFeatureGrant() {
		return personalFeatureGrantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getPersonalFeatureGrant_Identifier() {
		return (EAttribute) personalFeatureGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalFeatureGrant_Feature() {
		return (EReference) personalFeatureGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalFeatureGrant_Valid() {
		return (EReference) personalFeatureGrantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalFeatureGrant_UserAuthentication() {
		return (EReference) personalFeatureGrantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getPersonalFeatureGrant_Capacity() {
		return (EAttribute) personalFeatureGrantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getPersonalFeatureGrant_Vivid() {
		return (EAttribute) personalFeatureGrantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalFeatureGrant_Pack() {
		return (EReference) personalFeatureGrantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getPersonalLicensePack() {
		return personalLicensePackEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalLicensePack_License() {
		return (EReference) personalLicensePackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalLicensePack_Grants() {
		return (EReference) personalLicensePackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingLicensePack() {
		return floatingLicensePackEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_License() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Host() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Users() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingLicensePack_Features() {
		return (EReference) floatingLicensePackEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EClass getLicenseRequisites() {
		return licenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Identifier() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_IssueDate() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EAttribute getLicenseRequisites_Plan() {
		return (EAttribute) licenseRequisitesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Product() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Valid() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Signature() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public EReference getLicenseRequisites_Agreements() {
		return (EReference) licenseRequisitesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getPersonalLicenseRequisites() {
		return personalLicenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getPersonalLicenseRequisites_User() {
		return (EReference) personalLicenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseRequisites() {
		return floatingLicenseRequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingLicenseRequisites_Company() {
		return (EReference) floatingLicenseRequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicenseRequisites_Group() {
		return (EAttribute) floatingLicenseRequisitesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getProductRef() {
		return productRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Identifier() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Version() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFeatureRef() {
		return featureRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFeatureRef_Identifier() {
		return (EAttribute) featureRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFeatureRef_VersionMatch() {
		return (EReference) featureRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getUserRef() {
		return userRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getUserRef_Identifier() {
		return (EAttribute) userRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getUserRef_Name() {
		return (EAttribute) userRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getCompanyRef() {
		return companyRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getCompanyRef_Identifier() {
		return (EAttribute) companyRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getCompanyRef_Name() {
		return (EAttribute) companyRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getCompanyRef_Info() {
		return (EAttribute) companyRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingServer() {
		return floatingServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServer_Identifier() {
		return (EAttribute) floatingServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingServer_Authentication() {
		return (EReference) floatingServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getUserGrant() {
		return userGrantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getUserGrant_User() {
		return (EAttribute) userGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getUserGrant_Authentication() {
		return (EReference) userGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFeatureGrant() {
		return featureGrantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Identifier() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Feature() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Valid() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Vivid() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFeatureGrant_Capacity() {
		return (EAttribute) featureGrantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFeatureGrant_Pack() {
		return (EReference) featureGrantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getValidityPeriod() {
		return validityPeriodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getValidityPeriodClosed() {
		return validityPeriodClosedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getValidityPeriodClosed_From() {
		return (EAttribute) validityPeriodClosedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getValidityPeriodClosed_Until() {
		return (EAttribute) validityPeriodClosedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getEvaluationInstructions() {
		return evaluationInstructionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getEvaluationInstructions_Type() {
		return (EAttribute) evaluationInstructionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getEvaluationInstructions_Expression() {
		return (EAttribute) evaluationInstructionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getVersionMatch() {
		return versionMatchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getVersionMatch_Version() {
		return (EAttribute) versionMatchEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getVersionMatch_Rule() {
		return (EAttribute) versionMatchEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingLicenseAccess() {
		return floatingLicenseAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicenseAccess_User() {
		return (EAttribute) floatingLicenseAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingLicenseAccess_Server() {
		return (EReference) floatingLicenseAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFloatingLicenseAccess_OriginLicensePack() {
		return (EAttribute) floatingLicenseAccessEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getFloatingServerConnection() {
		return floatingServerConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServerConnection_Ip() {
		return (EAttribute) floatingServerConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getFloatingServerConnection_Port() {
		return (EAttribute) floatingServerConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getFloatingServerConnection_Authentication() {
		return (EReference) floatingServerConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getGrantAcqisition() {
		return grantAcqisitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Identifier() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Feature() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Grant() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_User() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getGrantAcqisition_Created() {
		return (EAttribute) grantAcqisitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getSignature() {
		return signatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getSignature_Attributes() {
		return (EReference) signatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EReference getSignature_Parent() {
		return (EReference) signatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EClass getSignatureAttribute() {
		return signatureAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getSignatureAttribute_Name() {
		return (EAttribute) signatureAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EAttribute getSignatureAttribute_Value() {
		return (EAttribute) signatureAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensesFactory getLicensesFactory() {
		return (LicensesFactory) getEFactoryInstance();
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
		agreementDataDescriptorEClass = createEClass(AGREEMENT_DATA_DESCRIPTOR);

		companyRefDescriptorEClass = createEClass(COMPANY_REF_DESCRIPTOR);

		evaluationInstructionsDescriptorEClass = createEClass(EVALUATION_INSTRUCTIONS_DESCRIPTOR);

		featureGrantDescriptorEClass = createEClass(FEATURE_GRANT_DESCRIPTOR);

		featureRefDescriptorEClass = createEClass(FEATURE_REF_DESCRIPTOR);

		floatingLicensePackDescriptorEClass = createEClass(FLOATING_LICENSE_PACK_DESCRIPTOR);

		floatingLicenseRequisitesDescriptorEClass = createEClass(FLOATING_LICENSE_REQUISITES_DESCRIPTOR);

		floatingServerDescriptorEClass = createEClass(FLOATING_SERVER_DESCRIPTOR);

		licensePlanDescriptorEClass = createEClass(LICENSE_PLAN_DESCRIPTOR);

		licensePlanFeatureDescriptorEClass = createEClass(LICENSE_PLAN_FEATURE_DESCRIPTOR);

		licenseRequisitesDescriptorEClass = createEClass(LICENSE_REQUISITES_DESCRIPTOR);

		personalFeatureGrantDescriptorEClass = createEClass(PERSONAL_FEATURE_GRANT_DESCRIPTOR);

		personalLicensePackDescriptorEClass = createEClass(PERSONAL_LICENSE_PACK_DESCRIPTOR);

		personalLicenseRequisitesDescriptorEClass = createEClass(PERSONAL_LICENSE_REQUISITES_DESCRIPTOR);

		productRefDescriptorEClass = createEClass(PRODUCT_REF_DESCRIPTOR);

		signatureAttributeDescriptorEClass = createEClass(SIGNATURE_ATTRIBUTE_DESCRIPTOR);

		signatureDescriptorEClass = createEClass(SIGNATURE_DESCRIPTOR);

		userGrantDescriptorEClass = createEClass(USER_GRANT_DESCRIPTOR);

		userRefDescriptorEClass = createEClass(USER_REF_DESCRIPTOR);

		validityPeriodClosedDescriptorEClass = createEClass(VALIDITY_PERIOD_CLOSED_DESCRIPTOR);

		validityPeriodDescriptorEClass = createEClass(VALIDITY_PERIOD_DESCRIPTOR);

		versionMatchDescriptorEClass = createEClass(VERSION_MATCH_DESCRIPTOR);

		agreementDataEClass = createEClass(AGREEMENT_DATA);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__IDENTIFIER);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__NAME);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__FILE);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__HASH_ALGO);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__HASH);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__CONTENT);
		createEAttribute(agreementDataEClass, AGREEMENT_DATA__CONTENT_TYPE);

		companyRefEClass = createEClass(COMPANY_REF);
		createEAttribute(companyRefEClass, COMPANY_REF__IDENTIFIER);
		createEAttribute(companyRefEClass, COMPANY_REF__NAME);
		createEAttribute(companyRefEClass, COMPANY_REF__INFO);

		evaluationInstructionsEClass = createEClass(EVALUATION_INSTRUCTIONS);
		createEAttribute(evaluationInstructionsEClass, EVALUATION_INSTRUCTIONS__TYPE);
		createEAttribute(evaluationInstructionsEClass, EVALUATION_INSTRUCTIONS__EXPRESSION);

		featureGrantEClass = createEClass(FEATURE_GRANT);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__IDENTIFIER);
		createEReference(featureGrantEClass, FEATURE_GRANT__FEATURE);
		createEReference(featureGrantEClass, FEATURE_GRANT__VALID);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__VIVID);
		createEAttribute(featureGrantEClass, FEATURE_GRANT__CAPACITY);
		createEReference(featureGrantEClass, FEATURE_GRANT__PACK);

		featureRefEClass = createEClass(FEATURE_REF);
		createEAttribute(featureRefEClass, FEATURE_REF__IDENTIFIER);
		createEReference(featureRefEClass, FEATURE_REF__VERSION_MATCH);

		floatingLicenseAccessEClass = createEClass(FLOATING_LICENSE_ACCESS);
		createEAttribute(floatingLicenseAccessEClass, FLOATING_LICENSE_ACCESS__USER);
		createEReference(floatingLicenseAccessEClass, FLOATING_LICENSE_ACCESS__SERVER);
		createEAttribute(floatingLicenseAccessEClass, FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK);

		floatingLicensePackEClass = createEClass(FLOATING_LICENSE_PACK);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__LICENSE);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__HOST);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__USERS);
		createEReference(floatingLicensePackEClass, FLOATING_LICENSE_PACK__FEATURES);

		floatingLicenseRequisitesEClass = createEClass(FLOATING_LICENSE_REQUISITES);
		createEReference(floatingLicenseRequisitesEClass, FLOATING_LICENSE_REQUISITES__COMPANY);
		createEAttribute(floatingLicenseRequisitesEClass, FLOATING_LICENSE_REQUISITES__GROUP);

		floatingServerEClass = createEClass(FLOATING_SERVER);
		createEAttribute(floatingServerEClass, FLOATING_SERVER__IDENTIFIER);
		createEReference(floatingServerEClass, FLOATING_SERVER__AUTHENTICATION);

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

		licensePlanEClass = createEClass(LICENSE_PLAN);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__IDENTIFIER);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__NAME);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__DESCRIPTION);
		createEAttribute(licensePlanEClass, LICENSE_PLAN__AGREEMENTS);
		createEReference(licensePlanEClass, LICENSE_PLAN__FEATURES);
		createEReference(licensePlanEClass, LICENSE_PLAN__PERSONAL);
		createEReference(licensePlanEClass, LICENSE_PLAN__FLOATING);

		licensePlanFeatureEClass = createEClass(LICENSE_PLAN_FEATURE);
		createEReference(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__FEATURE);
		createEReference(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__PLAN);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__VIVID);
		createEAttribute(licensePlanFeatureEClass, LICENSE_PLAN_FEATURE__CAPACITY);

		licenseRequisitesEClass = createEClass(LICENSE_REQUISITES);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__IDENTIFIER);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__ISSUE_DATE);
		createEAttribute(licenseRequisitesEClass, LICENSE_REQUISITES__PLAN);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__PRODUCT);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__VALID);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__SIGNATURE);
		createEReference(licenseRequisitesEClass, LICENSE_REQUISITES__AGREEMENTS);

		personalFeatureGrantEClass = createEClass(PERSONAL_FEATURE_GRANT);
		createEAttribute(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__IDENTIFIER);
		createEReference(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__FEATURE);
		createEReference(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__VALID);
		createEReference(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION);
		createEAttribute(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__CAPACITY);
		createEAttribute(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__VIVID);
		createEReference(personalFeatureGrantEClass, PERSONAL_FEATURE_GRANT__PACK);

		personalLicensePackEClass = createEClass(PERSONAL_LICENSE_PACK);
		createEReference(personalLicensePackEClass, PERSONAL_LICENSE_PACK__LICENSE);
		createEReference(personalLicensePackEClass, PERSONAL_LICENSE_PACK__GRANTS);

		personalLicenseRequisitesEClass = createEClass(PERSONAL_LICENSE_REQUISITES);
		createEReference(personalLicenseRequisitesEClass, PERSONAL_LICENSE_REQUISITES__USER);

		productRefEClass = createEClass(PRODUCT_REF);
		createEAttribute(productRefEClass, PRODUCT_REF__IDENTIFIER);
		createEAttribute(productRefEClass, PRODUCT_REF__VERSION);

		signatureEClass = createEClass(SIGNATURE);
		createEReference(signatureEClass, SIGNATURE__ATTRIBUTES);
		createEReference(signatureEClass, SIGNATURE__PARENT);

		signatureAttributeEClass = createEClass(SIGNATURE_ATTRIBUTE);
		createEAttribute(signatureAttributeEClass, SIGNATURE_ATTRIBUTE__NAME);
		createEAttribute(signatureAttributeEClass, SIGNATURE_ATTRIBUTE__VALUE);

		userGrantEClass = createEClass(USER_GRANT);
		createEAttribute(userGrantEClass, USER_GRANT__USER);
		createEReference(userGrantEClass, USER_GRANT__AUTHENTICATION);

		userRefEClass = createEClass(USER_REF);
		createEAttribute(userRefEClass, USER_REF__IDENTIFIER);
		createEAttribute(userRefEClass, USER_REF__NAME);

		validityPeriodEClass = createEClass(VALIDITY_PERIOD);

		validityPeriodClosedEClass = createEClass(VALIDITY_PERIOD_CLOSED);
		createEAttribute(validityPeriodClosedEClass, VALIDITY_PERIOD_CLOSED__FROM);
		createEAttribute(validityPeriodClosedEClass, VALIDITY_PERIOD_CLOSED__UNTIL);

		versionMatchEClass = createEClass(VERSION_MATCH);
		createEAttribute(versionMatchEClass, VERSION_MATCH__VERSION);
		createEAttribute(versionMatchEClass, VERSION_MATCH__RULE);
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
		floatingLicenseRequisitesDescriptorEClass.getESuperTypes().add(this.getLicenseRequisitesDescriptor());
		personalLicenseRequisitesDescriptorEClass.getESuperTypes().add(this.getLicenseRequisitesDescriptor());
		agreementDataEClass.getESuperTypes().add(this.getAgreementDataDescriptor());
		companyRefEClass.getESuperTypes().add(this.getCompanyRefDescriptor());
		evaluationInstructionsEClass.getESuperTypes().add(this.getEvaluationInstructionsDescriptor());
		featureGrantEClass.getESuperTypes().add(this.getFeatureGrantDescriptor());
		featureRefEClass.getESuperTypes().add(this.getFeatureRefDescriptor());
		floatingLicensePackEClass.getESuperTypes().add(this.getFloatingLicensePackDescriptor());
		floatingLicenseRequisitesEClass.getESuperTypes().add(this.getLicenseRequisites());
		floatingLicenseRequisitesEClass.getESuperTypes().add(this.getFloatingLicenseRequisitesDescriptor());
		floatingServerEClass.getESuperTypes().add(this.getFloatingServerDescriptor());
		licensePlanEClass.getESuperTypes().add(this.getLicensePlanDescriptor());
		licensePlanFeatureEClass.getESuperTypes().add(this.getLicensePlanFeatureDescriptor());
		licenseRequisitesEClass.getESuperTypes().add(this.getLicenseRequisitesDescriptor());
		personalFeatureGrantEClass.getESuperTypes().add(this.getPersonalFeatureGrantDescriptor());
		personalLicensePackEClass.getESuperTypes().add(this.getPersonalLicensePackDescriptor());
		personalLicenseRequisitesEClass.getESuperTypes().add(this.getLicenseRequisites());
		personalLicenseRequisitesEClass.getESuperTypes().add(this.getPersonalLicenseRequisitesDescriptor());
		productRefEClass.getESuperTypes().add(this.getProductRefDescriptor());
		signatureEClass.getESuperTypes().add(this.getSignatureDescriptor());
		signatureAttributeEClass.getESuperTypes().add(this.getSignatureAttributeDescriptor());
		userGrantEClass.getESuperTypes().add(this.getUserGrantDescriptor());
		userRefEClass.getESuperTypes().add(this.getUserRefDescriptor());
		validityPeriodEClass.getESuperTypes().add(this.getValidityPeriodDescriptor());
		validityPeriodClosedEClass.getESuperTypes().add(this.getValidityPeriod());
		validityPeriodClosedEClass.getESuperTypes().add(this.getValidityPeriodClosedDescriptor());
		versionMatchEClass.getESuperTypes().add(this.getVersionMatchDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(agreementDataDescriptorEClass, AgreementDataDescriptor.class, "AgreementDataDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(companyRefDescriptorEClass, CompanyRefDescriptor.class, "CompanyRefDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(evaluationInstructionsDescriptorEClass, EvaluationInstructionsDescriptor.class,
				"EvaluationInstructionsDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(featureGrantDescriptorEClass, FeatureGrantDescriptor.class, "FeatureGrantDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(featureRefDescriptorEClass, FeatureRefDescriptor.class, "FeatureRefDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(floatingLicensePackDescriptorEClass, FloatingLicensePackDescriptor.class,
				"FloatingLicensePackDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(floatingLicenseRequisitesDescriptorEClass, FloatingLicenseRequisitesDescriptor.class,
				"FloatingLicenseRequisitesDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(floatingServerDescriptorEClass, FloatingServerDescriptor.class, "FloatingServerDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licensePlanDescriptorEClass, LicensePlanDescriptor.class, "LicensePlanDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(licensePlanFeatureDescriptorEClass, LicensePlanFeatureDescriptor.class,
				"LicensePlanFeatureDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(licenseRequisitesDescriptorEClass, LicenseRequisitesDescriptor.class, "LicenseRequisitesDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(personalFeatureGrantDescriptorEClass, PersonalFeatureGrantDescriptor.class,
				"PersonalFeatureGrantDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(personalLicensePackDescriptorEClass, PersonalLicensePackDescriptor.class,
				"PersonalLicensePackDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(personalLicenseRequisitesDescriptorEClass, PersonalLicenseRequisitesDescriptor.class,
				"PersonalLicenseRequisitesDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(productRefDescriptorEClass, ProductRefDescriptor.class, "ProductRefDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(signatureAttributeDescriptorEClass, SignatureAttributeDescriptor.class,
				"SignatureAttributeDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(signatureDescriptorEClass, SignatureDescriptor.class, "SignatureDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(userGrantDescriptorEClass, UserGrantDescriptor.class, "UserGrantDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(userRefDescriptorEClass, UserRefDescriptor.class, "UserRefDescriptor", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(validityPeriodClosedDescriptorEClass, ValidityPeriodClosedDescriptor.class,
				"ValidityPeriodClosedDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(validityPeriodDescriptorEClass, ValidityPeriodDescriptor.class, "ValidityPeriodDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(versionMatchDescriptorEClass, VersionMatchDescriptor.class, "VersionMatchDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(agreementDataEClass, AgreementData.class, "AgreementData", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgreementData_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				AgreementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreementData_Name(), ecorePackage.getEString(), "name", null, 1, 1, AgreementData.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreementData_File(), ecorePackage.getEString(), "file", null, 1, 1, AgreementData.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreementData_HashAlgo(), ecorePackage.getEString(), "hashAlgo", null, 1, 1, //$NON-NLS-1$
				AgreementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreementData_Hash(), ecorePackage.getEByteArray(), "hash", null, 1, 1, AgreementData.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgreementData_Content(), ecorePackage.getEByteArray(), "content", null, 1, 1, //$NON-NLS-1$
				AgreementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAgreementData_ContentType(), ecorePackage.getEString(), "contentType", null, 1, 1, //$NON-NLS-1$
				AgreementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(companyRefEClass, CompanyRef.class, "CompanyRef", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompanyRef_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				CompanyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompanyRef_Name(), ecorePackage.getEString(), "name", null, 1, 1, CompanyRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompanyRef_Info(), ecorePackage.getEString(), "info", null, 1, 1, CompanyRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evaluationInstructionsEClass, EvaluationInstructions.class, "EvaluationInstructions", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvaluationInstructions_Type(), ecorePackage.getEString(), "type", null, 1, 1, //$NON-NLS-1$
				EvaluationInstructions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluationInstructions_Expression(), ecorePackage.getEString(), "expression", null, 1, 1, //$NON-NLS-1$
				EvaluationInstructions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featureGrantEClass, FeatureGrant.class, "FeatureGrant", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureGrant_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				FeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureGrant_Feature(), this.getFeatureRef(), null, "feature", null, 1, 1, FeatureGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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

		initEClass(featureRefEClass, FeatureRef.class, "FeatureRef", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureRef_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				FeatureRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureRef_VersionMatch(), this.getVersionMatch(), null, "versionMatch", null, 1, 1, //$NON-NLS-1$
				FeatureRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		initEClass(floatingLicenseRequisitesEClass, FloatingLicenseRequisites.class, "FloatingLicenseRequisites", //$NON-NLS-1$
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFloatingLicenseRequisites_Company(), this.getCompanyRef(), null, "company", null, 1, 1, //$NON-NLS-1$
				FloatingLicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFloatingLicenseRequisites_Group(), ecorePackage.getEString(), "group", null, 0, 1, //$NON-NLS-1$
				FloatingLicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingServerEClass, FloatingServer.class, "FloatingServer", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingServer_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				FloatingServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFloatingServer_Authentication(), this.getEvaluationInstructions(), null, "authentication", //$NON-NLS-1$
				null, 1, 1, FloatingServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

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
		initEAttribute(getLicensePlan_Agreements(), ecorePackage.getEString(), "agreements", null, 0, -1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlan_Features(), this.getLicensePlanFeature(), this.getLicensePlanFeature_Plan(),
				"features", null, 1, -1, LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlan_Personal(), this.getPersonalLicensePack(), null, "personal", null, 0, -1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlan_Floating(), this.getFloatingLicensePack(), null, "floating", null, 0, -1, //$NON-NLS-1$
				LicensePlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licensePlanFeatureEClass, LicensePlanFeature.class, "LicensePlanFeature", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLicensePlanFeature_Feature(), this.getFeatureRef(), null, "feature", null, 1, 1, //$NON-NLS-1$
				LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicensePlanFeature_Plan(), this.getLicensePlan(), this.getLicensePlan_Features(), "plan", //$NON-NLS-1$
				null, 1, 1, LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePlanFeature_Vivid(), ecorePackage.getELong(), "vivid", null, 1, 1, //$NON-NLS-1$
				LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicensePlanFeature_Capacity(), ecorePackage.getEInt(), "capacity", null, 1, 1, //$NON-NLS-1$
				LicensePlanFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

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
		initEReference(getLicenseRequisites_Signature(), this.getSignature(), null, "signature", null, 0, 1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicenseRequisites_Agreements(), this.getAgreementData(), null, "agreements", null, 0, -1, //$NON-NLS-1$
				LicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(personalFeatureGrantEClass, PersonalFeatureGrant.class, "PersonalFeatureGrant", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersonalFeatureGrant_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, //$NON-NLS-1$
				PersonalFeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPersonalFeatureGrant_Feature(), this.getFeatureRef(), null, "feature", null, 1, 1, //$NON-NLS-1$
				PersonalFeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPersonalFeatureGrant_Valid(), this.getValidityPeriod(), null, "valid", null, 1, 1, //$NON-NLS-1$
				PersonalFeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPersonalFeatureGrant_UserAuthentication(), this.getEvaluationInstructions(), null,
				"userAuthentication", null, 1, 1, PersonalFeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPersonalFeatureGrant_Capacity(), ecorePackage.getEInt(), "capacity", "1", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
				PersonalFeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPersonalFeatureGrant_Vivid(), ecorePackage.getELong(), "vivid", null, 1, 1, //$NON-NLS-1$
				PersonalFeatureGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPersonalFeatureGrant_Pack(), this.getPersonalLicensePack(),
				this.getPersonalLicensePack_Grants(), "pack", null, 1, 1, PersonalFeatureGrant.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(personalLicensePackEClass, PersonalLicensePack.class, "PersonalLicensePack", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPersonalLicensePack_License(), this.getPersonalLicenseRequisites(), null, "license", null, 1, //$NON-NLS-1$
				1, PersonalLicensePack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPersonalLicensePack_Grants(), this.getPersonalFeatureGrant(),
				this.getPersonalFeatureGrant_Pack(), "grants", null, 0, -1, PersonalLicensePack.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(personalLicenseRequisitesEClass, PersonalLicenseRequisites.class, "PersonalLicenseRequisites", //$NON-NLS-1$
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPersonalLicenseRequisites_User(), this.getUserRef(), null, "user", null, 1, 1, //$NON-NLS-1$
				PersonalLicenseRequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productRefEClass, ProductRef.class, "ProductRef", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductRef_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				ProductRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductRef_Version(), ecorePackage.getEString(), "version", null, 1, 1, ProductRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signatureEClass, Signature.class, "Signature", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSignature_Attributes(), this.getSignatureAttribute(), null, "attributes", null, 0, -1, //$NON-NLS-1$
				Signature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignature_Parent(), this.getSignature(), null, "parent", null, 0, 1, Signature.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signatureAttributeEClass, SignatureAttribute.class, "SignatureAttribute", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignatureAttribute_Name(), ecorePackage.getEString(), "name", null, 1, 1, //$NON-NLS-1$
				SignatureAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSignatureAttribute_Value(), ecorePackage.getEString(), "value", null, 1, 1, //$NON-NLS-1$
				SignatureAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(userGrantEClass, UserGrant.class, "UserGrant", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserGrant_User(), ecorePackage.getEString(), "user", null, 1, 1, UserGrant.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserGrant_Authentication(), this.getEvaluationInstructions(), null, "authentication", null, 1, //$NON-NLS-1$
				1, UserGrant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userRefEClass, UserRef.class, "UserRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getUserRef_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, UserRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserRef_Name(), ecorePackage.getEString(), "name", null, 1, 1, UserRef.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		initEClass(versionMatchEClass, VersionMatch.class, "VersionMatch", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVersionMatch_Version(), ecorePackage.getEString(), "version", null, 1, 1, VersionMatch.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVersionMatch_Rule(), ecorePackage.getEString(), "rule", null, 1, 1, VersionMatch.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //LicensesPackageImpl
