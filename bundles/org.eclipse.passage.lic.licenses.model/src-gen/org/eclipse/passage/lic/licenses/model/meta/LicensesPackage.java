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
package org.eclipse.passage.lic.licenses.model.meta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesFactory
 * @model kind="package"
 * @generated
 */
public interface LicensesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "licenses"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/licenses/2.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.passage.lic"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LicensesPackage eINSTANCE = org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.AgreementDataDescriptor <em>Agreement Data Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.AgreementDataDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getAgreementDataDescriptor()
	 * @since 2.1
	 * @generated
	 */
	int AGREEMENT_DATA_DESCRIPTOR = 0;

	/**
	 * The number of structural features of the '<em>Agreement Data Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Agreement Data Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.CompanyRefDescriptor <em>Company Ref Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.CompanyRefDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getCompanyRefDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int COMPANY_REF_DESCRIPTOR = 1;

	/**
	 * The number of structural features of the '<em>Company Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Company Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor <em>Evaluation Instructions Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getEvaluationInstructionsDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int EVALUATION_INSTRUCTIONS_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>Evaluation Instructions Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Evaluation Instructions Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.FeatureGrantDescriptor <em>Feature Grant Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.FeatureGrantDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFeatureGrantDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int FEATURE_GRANT_DESCRIPTOR = 3;

	/**
	 * The number of structural features of the '<em>Feature Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.FeatureRefDescriptor <em>Feature Ref Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.FeatureRefDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFeatureRefDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int FEATURE_REF_DESCRIPTOR = 4;

	/**
	 * The number of structural features of the '<em>Feature Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_REF_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_REF_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor <em>Floating License Pack Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicensePackDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_LICENSE_PACK_DESCRIPTOR = 5;

	/**
	 * The number of structural features of the '<em>Floating License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Floating License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor <em>License Requisites Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicenseRequisitesDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int LICENSE_REQUISITES_DESCRIPTOR = 10;

	/**
	 * The number of structural features of the '<em>License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor <em>Floating License Requisites Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicenseRequisitesDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_LICENSE_REQUISITES_DESCRIPTOR = 6;

	/**
	 * The number of structural features of the '<em>Floating License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Floating License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT = LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.FloatingServerDescriptor <em>Floating Server Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.FloatingServerDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingServerDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_SERVER_DESCRIPTOR = 7;

	/**
	 * The number of structural features of the '<em>Floating Server Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Floating Server Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicensePlanDescriptor <em>License Plan Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicensePlanDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlanDescriptor()
	 * @generated
	 */
	int LICENSE_PLAN_DESCRIPTOR = 8;

	/**
	 * The number of structural features of the '<em>License Plan Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Plan Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor <em>License Plan Feature Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlanFeatureDescriptor()
	 * @generated
	 */
	int LICENSE_PLAN_FEATURE_DESCRIPTOR = 9;

	/**
	 * The number of structural features of the '<em>License Plan Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Plan Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor <em>Personal Feature Grant Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalFeatureGrantDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int PERSONAL_FEATURE_GRANT_DESCRIPTOR = 11;

	/**
	 * The number of structural features of the '<em>Personal Feature Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Personal Feature Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor <em>Personal License Pack Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalLicensePackDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int PERSONAL_LICENSE_PACK_DESCRIPTOR = 12;

	/**
	 * The number of structural features of the '<em>Personal License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Personal License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor <em>Personal License Requisites Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalLicenseRequisitesDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int PERSONAL_LICENSE_REQUISITES_DESCRIPTOR = 13;

	/**
	 * The number of structural features of the '<em>Personal License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Personal License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT = LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.ProductRefDescriptor <em>Product Ref Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.ProductRefDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getProductRefDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int PRODUCT_REF_DESCRIPTOR = 14;

	/**
	 * The number of structural features of the '<em>Product Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Product Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor <em>Signature Attribute Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getSignatureAttributeDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int SIGNATURE_ATTRIBUTE_DESCRIPTOR = 15;

	/**
	 * The number of structural features of the '<em>Signature Attribute Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_ATTRIBUTE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Signature Attribute Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_ATTRIBUTE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.SignatureDescriptor <em>Signature Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.SignatureDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getSignatureDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int SIGNATURE_DESCRIPTOR = 16;

	/**
	 * The number of structural features of the '<em>Signature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Signature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.UserGrantDescriptor <em>User Grant Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.UserGrantDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getUserGrantDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int USER_GRANT_DESCRIPTOR = 17;

	/**
	 * The number of structural features of the '<em>User Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.UserRefDescriptor <em>User Ref Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.UserRefDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getUserRefDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int USER_REF_DESCRIPTOR = 18;

	/**
	 * The number of structural features of the '<em>User Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_REF_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_REF_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor <em>Validity Period Closed Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriodClosedDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int VALIDITY_PERIOD_CLOSED_DESCRIPTOR = 19;

	/**
	 * The number of structural features of the '<em>Validity Period Closed Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Validity Period Closed Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor <em>Validity Period Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriodDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int VALIDITY_PERIOD_DESCRIPTOR = 20;

	/**
	 * The number of structural features of the '<em>Validity Period Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Validity Period Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.VersionMatchDescriptor <em>Version Match Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.VersionMatchDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getVersionMatchDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int VERSION_MATCH_DESCRIPTOR = 21;

	/**
	 * The number of structural features of the '<em>Version Match Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Version Match Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl <em>Agreement Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	int AGREEMENT_DATA = 22;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__IDENTIFIER = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__NAME = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__FILE = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Hash Algo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__HASH_ALGO = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__HASH = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__CONTENT = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA__CONTENT_TYPE = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Agreement Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA_FEATURE_COUNT = AGREEMENT_DATA_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Agreement Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int AGREEMENT_DATA_OPERATION_COUNT = AGREEMENT_DATA_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.CompanyRefImpl <em>Company Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.CompanyRefImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getCompanyRef()
	 * @since 2.0
	 * @generated
	 */
	int COMPANY_REF = 23;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF__IDENTIFIER = COMPANY_REF_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF__NAME = COMPANY_REF_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF__INFO = COMPANY_REF_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Company Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF_FEATURE_COUNT = COMPANY_REF_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Company Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int COMPANY_REF_OPERATION_COUNT = COMPANY_REF_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.EvaluationInstructionsImpl <em>Evaluation Instructions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.EvaluationInstructionsImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getEvaluationInstructions()
	 * @since 2.0
	 * @generated
	 */
	int EVALUATION_INSTRUCTIONS = 24;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS__TYPE = EVALUATION_INSTRUCTIONS_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS__EXPRESSION = EVALUATION_INSTRUCTIONS_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Evaluation Instructions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_FEATURE_COUNT = EVALUATION_INSTRUCTIONS_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Evaluation Instructions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_OPERATION_COUNT = EVALUATION_INSTRUCTIONS_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FeatureGrantImpl <em>Feature Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FeatureGrantImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	int FEATURE_GRANT = 25;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__IDENTIFIER = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__FEATURE = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VALID = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VIVID = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__CAPACITY = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Pack</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__PACK = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_FEATURE_COUNT = FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_OPERATION_COUNT = FEATURE_GRANT_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FeatureRefImpl <em>Feature Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FeatureRefImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFeatureRef()
	 * @since 2.0
	 * @generated
	 */
	int FEATURE_REF = 26;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_REF__IDENTIFIER = FEATURE_REF_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version Match</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_REF__VERSION_MATCH = FEATURE_REF_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_REF_FEATURE_COUNT = FEATURE_REF_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Feature Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FEATURE_REF_OPERATION_COUNT = FEATURE_REF_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl <em>Floating License Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicenseAccess()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_LICENSE_ACCESS = 27;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS__USER = 0;

	/**
	 * The feature id for the '<em><b>Server</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS__SERVER = 1;

	/**
	 * The feature id for the '<em><b>Origin License Pack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK = 2;

	/**
	 * The number of structural features of the '<em>Floating License Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Floating License Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicensePackImpl <em>Floating License Pack</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingLicensePackImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicensePack()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_LICENSE_PACK = 28;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__LICENSE = FLOATING_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__HOST = FLOATING_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__USERS = FLOATING_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__FEATURES = FLOATING_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Floating License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_FEATURE_COUNT = FLOATING_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Floating License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_OPERATION_COUNT = FLOATING_LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicenseRequisitesImpl <em>License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	int LICENSE_REQUISITES = 35;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__IDENTIFIER = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__ISSUE_DATE = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__PLAN = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__PRODUCT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__VALID = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__SIGNATURE = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Agreements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__AGREEMENTS = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_FEATURE_COUNT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_OPERATION_COUNT = LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseRequisitesImpl <em>Floating License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicenseRequisites()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_LICENSE_REQUISITES = 29;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__IDENTIFIER = LICENSE_REQUISITES__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__ISSUE_DATE = LICENSE_REQUISITES__ISSUE_DATE;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__PLAN = LICENSE_REQUISITES__PLAN;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__PRODUCT = LICENSE_REQUISITES__PRODUCT;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__VALID = LICENSE_REQUISITES__VALID;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__SIGNATURE = LICENSE_REQUISITES__SIGNATURE;

	/**
	 * The feature id for the '<em><b>Agreements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__AGREEMENTS = LICENSE_REQUISITES__AGREEMENTS;

	/**
	 * The feature id for the '<em><b>Company</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__COMPANY = LICENSE_REQUISITES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__GROUP = LICENSE_REQUISITES_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Floating License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_FEATURE_COUNT = LICENSE_REQUISITES_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Floating License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_OPERATION_COUNT = LICENSE_REQUISITES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingServerImpl <em>Floating Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingServerImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingServer()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_SERVER = 30;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER__IDENTIFIER = FLOATING_SERVER_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER__AUTHENTICATION = FLOATING_SERVER_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Floating Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_FEATURE_COUNT = FLOATING_SERVER_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Floating Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_OPERATION_COUNT = FLOATING_SERVER_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingServerConnectionImpl <em>Floating Server Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingServerConnectionImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingServerConnection()
	 * @since 2.0
	 * @generated
	 */
	int FLOATING_SERVER_CONNECTION = 31;

	/**
	 * The feature id for the '<em><b>Ip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION__IP = 0;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION__PORT = 1;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION__AUTHENTICATION = 2;

	/**
	 * The number of structural features of the '<em>Floating Server Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Floating Server Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.GrantAcqisitionImpl <em>Grant Acqisition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.GrantAcqisitionImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getGrantAcqisition()
	 * @since 2.0
	 * @generated
	 */
	int GRANT_ACQISITION = 32;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Grant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__GRANT = 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__USER = 3;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__CREATED = 4;

	/**
	 * The number of structural features of the '<em>Grant Acqisition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Grant Acqisition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl <em>License Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlan()
	 * @generated
	 */
	int LICENSE_PLAN = 33;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__IDENTIFIER = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__NAME = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__DESCRIPTION = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Agreements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__AGREEMENTS = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__FEATURES = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Personal</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__PERSONAL = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Floating</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__FLOATING = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>License Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_COUNT = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>License Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_OPERATION_COUNT = LICENSE_PLAN_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl <em>License Plan Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlanFeature()
	 * @generated
	 */
	int LICENSE_PLAN_FEATURE = 34;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__FEATURE = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__PLAN = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__VIVID = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__CAPACITY = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>License Plan Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_FEATURE_COUNT = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>License Plan Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_OPERATION_COUNT = LICENSE_PLAN_FEATURE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl <em>Personal Feature Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	int PERSONAL_FEATURE_GRANT = 36;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__IDENTIFIER = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__FEATURE = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__VALID = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>User Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__CAPACITY = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__VIVID = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Pack</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT__PACK = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Personal Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT_FEATURE_COUNT = PERSONAL_FEATURE_GRANT_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Personal Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_GRANT_OPERATION_COUNT = PERSONAL_FEATURE_GRANT_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.PersonalLicensePackImpl <em>Personal License Pack</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.PersonalLicensePackImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalLicensePack()
	 * @since 2.0
	 * @generated
	 */
	int PERSONAL_LICENSE_PACK = 37;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_PACK__LICENSE = PERSONAL_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Grants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_PACK__GRANTS = PERSONAL_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Personal License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_PACK_FEATURE_COUNT = PERSONAL_LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Personal License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_PACK_OPERATION_COUNT = PERSONAL_LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.PersonalLicenseRequisitesImpl <em>Personal License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.PersonalLicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalLicenseRequisites()
	 * @since 2.0
	 * @generated
	 */
	int PERSONAL_LICENSE_REQUISITES = 38;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__IDENTIFIER = LICENSE_REQUISITES__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__ISSUE_DATE = LICENSE_REQUISITES__ISSUE_DATE;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__PLAN = LICENSE_REQUISITES__PLAN;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__PRODUCT = LICENSE_REQUISITES__PRODUCT;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__VALID = LICENSE_REQUISITES__VALID;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__SIGNATURE = LICENSE_REQUISITES__SIGNATURE;

	/**
	 * The feature id for the '<em><b>Agreements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__AGREEMENTS = LICENSE_REQUISITES__AGREEMENTS;

	/**
	 * The feature id for the '<em><b>User</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__USER = LICENSE_REQUISITES_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Personal License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_FEATURE_COUNT = LICENSE_REQUISITES_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Personal License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_OPERATION_COUNT = LICENSE_REQUISITES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.ProductRefImpl <em>Product Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.ProductRefImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getProductRef()
	 * @since 2.0
	 * @generated
	 */
	int PRODUCT_REF = 39;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__IDENTIFIER = PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__VERSION = PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_FEATURE_COUNT = PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_OPERATION_COUNT = PRODUCT_REF_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.SignatureImpl <em>Signature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.SignatureImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getSignature()
	 * @since 2.0
	 * @generated
	 */
	int SIGNATURE = 40;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__ATTRIBUTES = SIGNATURE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__PARENT = SIGNATURE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Signature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_FEATURE_COUNT = SIGNATURE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Signature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_OPERATION_COUNT = SIGNATURE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.SignatureAttributeImpl <em>Signature Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.SignatureAttributeImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getSignatureAttribute()
	 * @since 2.0
	 * @generated
	 */
	int SIGNATURE_ATTRIBUTE = 41;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_ATTRIBUTE__NAME = SIGNATURE_ATTRIBUTE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_ATTRIBUTE__VALUE = SIGNATURE_ATTRIBUTE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Signature Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_ATTRIBUTE_FEATURE_COUNT = SIGNATURE_ATTRIBUTE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Signature Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_ATTRIBUTE_OPERATION_COUNT = SIGNATURE_ATTRIBUTE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.UserGrantImpl <em>User Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.UserGrantImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getUserGrant()
	 * @since 2.0
	 * @generated
	 */
	int USER_GRANT = 42;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GRANT__USER = USER_GRANT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GRANT__AUTHENTICATION = USER_GRANT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_FEATURE_COUNT = USER_GRANT_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>User Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_OPERATION_COUNT = USER_GRANT_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.UserRefImpl <em>User Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.UserRefImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getUserRef()
	 * @since 2.0
	 * @generated
	 */
	int USER_REF = 43;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_REF__IDENTIFIER = USER_REF_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_REF__NAME = USER_REF_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_REF_FEATURE_COUNT = USER_REF_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>User Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_REF_OPERATION_COUNT = USER_REF_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriod <em>Validity Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriod
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriod()
	 * @since 2.0
	 * @generated
	 */
	int VALIDITY_PERIOD = 44;

	/**
	 * The number of structural features of the '<em>Validity Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_FEATURE_COUNT = VALIDITY_PERIOD_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Validity Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_OPERATION_COUNT = VALIDITY_PERIOD_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.ValidityPeriodClosedImpl <em>Validity Period Closed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.ValidityPeriodClosedImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriodClosed()
	 * @since 2.0
	 * @generated
	 */
	int VALIDITY_PERIOD_CLOSED = 45;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED__FROM = VALIDITY_PERIOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED__UNTIL = VALIDITY_PERIOD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Validity Period Closed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_FEATURE_COUNT = VALIDITY_PERIOD_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Validity Period Closed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_OPERATION_COUNT = VALIDITY_PERIOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.VersionMatchImpl <em>Version Match</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.VersionMatchImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getVersionMatch()
	 * @since 2.0
	 * @generated
	 */
	int VERSION_MATCH = 46;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH__VERSION = VERSION_MATCH_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH__RULE = VERSION_MATCH_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Version Match</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_FEATURE_COUNT = VERSION_MATCH_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Version Match</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_OPERATION_COUNT = VERSION_MATCH_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.AgreementDataDescriptor <em>Agreement Data Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agreement Data Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.AgreementDataDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.AgreementDataDescriptor"
	 * @since 2.1
	 * @generated
	 */
	EClass getAgreementDataDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.CompanyRefDescriptor <em>Company Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Company Ref Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.CompanyRefDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.CompanyRefDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getCompanyRefDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor <em>Evaluation Instructions Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Instructions Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getEvaluationInstructionsDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.FeatureGrantDescriptor <em>Feature Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Grant Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.FeatureGrantDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.FeatureGrantDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getFeatureGrantDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.FeatureRefDescriptor <em>Feature Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Ref Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.FeatureRefDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.FeatureRefDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getFeatureRefDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor <em>Floating License Pack Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Pack Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingLicensePackDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor <em>Floating License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Requisites Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor" superTypes="org.eclipse.passage.lic.licenses.model.api.LicenseRequisitesDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingLicenseRequisitesDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.FloatingServerDescriptor <em>Floating Server Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating Server Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.FloatingServerDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.FloatingServerDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingServerDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.LicensePlanDescriptor <em>License Plan Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Plan Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.LicensePlanDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.LicensePlanDescriptor"
	 * @generated
	 */
	EClass getLicensePlanDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor <em>License Plan Feature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Plan Feature Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor"
	 * @generated
	 */
	EClass getLicensePlanFeatureDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor <em>License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Requisites Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getLicenseRequisitesDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor <em>Personal Feature Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal Feature Grant Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getPersonalFeatureGrantDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor <em>Personal License Pack Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Pack Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getPersonalLicensePackDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor <em>Personal License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Requisites Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor" superTypes="org.eclipse.passage.lic.licenses.model.api.LicenseRequisitesDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getPersonalLicenseRequisitesDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.ProductRefDescriptor <em>Product Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Ref Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.ProductRefDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.ProductRefDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getProductRefDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor <em>Signature Attribute Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature Attribute Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getSignatureAttributeDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.SignatureDescriptor <em>Signature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.SignatureDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.SignatureDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getSignatureDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.UserGrantDescriptor <em>User Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Grant Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.UserGrantDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.UserGrantDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getUserGrantDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.UserRefDescriptor <em>User Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Ref Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.UserRefDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.UserRefDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getUserRefDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor <em>Validity Period Closed Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Closed Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getValidityPeriodClosedDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor <em>Validity Period Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getValidityPeriodDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.VersionMatchDescriptor <em>Version Match Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version Match Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.VersionMatchDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.VersionMatchDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getVersionMatchDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData <em>Agreement Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agreement Data</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData
	 * @since 2.1
	 * @generated
	 */
	EClass getAgreementData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getIdentifier()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getName()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getFile()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_File();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getHashAlgo <em>Hash Algo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hash Algo</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getHashAlgo()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_HashAlgo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getHash <em>Hash</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hash</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getHash()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_Hash();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getContent()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Type</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData#getContentType()
	 * @see #getAgreementData()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getAgreementData_ContentType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.CompanyRef <em>Company Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Company Ref</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.CompanyRef
	 * @since 2.0
	 * @generated
	 */
	EClass getCompanyRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.CompanyRef#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.CompanyRef#getIdentifier()
	 * @see #getCompanyRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getCompanyRef_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.CompanyRef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.CompanyRef#getName()
	 * @see #getCompanyRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getCompanyRef_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.CompanyRef#getInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Info</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.CompanyRef#getInfo()
	 * @see #getCompanyRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getCompanyRef_Info();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions <em>Evaluation Instructions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Instructions</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions
	 * @since 2.0
	 * @generated
	 */
	EClass getEvaluationInstructions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getType()
	 * @see #getEvaluationInstructions()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getEvaluationInstructions_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getExpression()
	 * @see #getEvaluationInstructions()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getEvaluationInstructions_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant <em>Feature Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant
	 * @since 2.0
	 * @generated
	 */
	EClass getFeatureGrant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getIdentifier()
	 * @see #getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFeatureGrant_Identifier();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getFeature()
	 * @see #getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getFeatureGrant_Feature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Valid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getValid()
	 * @see #getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getFeatureGrant_Valid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getVivid <em>Vivid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vivid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getVivid()
	 * @see #getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFeatureGrant_Vivid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getCapacity()
	 * @see #getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFeatureGrant_Capacity();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getPack <em>Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getPack()
	 * @see #getFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getFeatureGrant_Pack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef <em>Feature Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Ref</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureRef
	 * @since 2.0
	 * @generated
	 */
	EClass getFeatureRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureRef#getIdentifier()
	 * @see #getFeatureRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFeatureRef_Identifier();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef#getVersionMatch <em>Version Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Version Match</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureRef#getVersionMatch()
	 * @see #getFeatureRef()
	 * @since 2.0
	 * @generated
	 */
	EReference getFeatureRef_VersionMatch();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess <em>Floating License Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Access</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingLicenseAccess();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getUser()
	 * @see #getFloatingLicenseAccess()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFloatingLicenseAccess_User();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Server</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getServer()
	 * @see #getFloatingLicenseAccess()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingLicenseAccess_Server();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getOriginLicensePack <em>Origin License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin License Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getOriginLicensePack()
	 * @see #getFloatingLicenseAccess()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFloatingLicenseAccess_OriginLicensePack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack <em>Floating License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingLicensePack();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getLicense <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>License</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getLicense()
	 * @see #getFloatingLicensePack()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingLicensePack_License();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Host</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getHost()
	 * @see #getFloatingLicensePack()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingLicensePack_Host();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Users</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getUsers()
	 * @see #getFloatingLicensePack()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingLicensePack_Users();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getFeatures()
	 * @see #getFloatingLicensePack()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingLicensePack_Features();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites <em>Floating License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Requisites</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingLicenseRequisites();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Company</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getCompany()
	 * @see #getFloatingLicenseRequisites()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingLicenseRequisites_Company();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getGroup()
	 * @see #getFloatingLicenseRequisites()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFloatingLicenseRequisites_Group();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServer <em>Floating Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating Server</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServer
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingServer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServer#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServer#getIdentifier()
	 * @see #getFloatingServer()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFloatingServer_Identifier();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServer#getAuthentication <em>Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Authentication</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServer#getAuthentication()
	 * @see #getFloatingServer()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingServer_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection <em>Floating Server Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating Server Connection</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection
	 * @since 2.0
	 * @generated
	 */
	EClass getFloatingServerConnection();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection#getIp <em>Ip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ip</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection#getIp()
	 * @see #getFloatingServerConnection()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFloatingServerConnection_Ip();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection#getPort()
	 * @see #getFloatingServerConnection()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getFloatingServerConnection_Port();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection#getAuthentication <em>Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Authentication</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection#getAuthentication()
	 * @see #getFloatingServerConnection()
	 * @since 2.0
	 * @generated
	 */
	EReference getFloatingServerConnection_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition <em>Grant Acqisition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grant Acqisition</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition
	 * @since 2.0
	 * @generated
	 */
	EClass getGrantAcqisition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getIdentifier()
	 * @see #getGrantAcqisition()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getGrantAcqisition_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getFeature()
	 * @see #getGrantAcqisition()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getGrantAcqisition_Feature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getGrant <em>Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getGrant()
	 * @see #getGrantAcqisition()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getGrantAcqisition_Grant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getUser()
	 * @see #getGrantAcqisition()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getGrantAcqisition_User();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition#getCreated()
	 * @see #getGrantAcqisition()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getGrantAcqisition_Created();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan <em>License Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Plan</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan
	 * @generated
	 */
	EClass getLicensePlan();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getIdentifier()
	 * @see #getLicensePlan()
	 * @generated
	 */
	EAttribute getLicensePlan_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getName()
	 * @see #getLicensePlan()
	 * @generated
	 */
	EAttribute getLicensePlan_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getDescription()
	 * @see #getLicensePlan()
	 * @generated
	 */
	EAttribute getLicensePlan_Description();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getAgreements <em>Agreements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Agreements</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getAgreements()
	 * @see #getLicensePlan()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getLicensePlan_Agreements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFeatures()
	 * @see #getLicensePlan()
	 * @since 2.0
	 * @generated
	 */
	EReference getLicensePlan_Features();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getPersonal <em>Personal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Personal</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getPersonal()
	 * @see #getLicensePlan()
	 * @since 2.0
	 * @generated
	 */
	EReference getLicensePlan_Personal();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFloating <em>Floating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Floating</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFloating()
	 * @see #getLicensePlan()
	 * @since 2.0
	 * @generated
	 */
	EReference getLicensePlan_Floating();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature <em>License Plan Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Plan Feature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature
	 * @generated
	 */
	EClass getLicensePlanFeature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeature()
	 * @see #getLicensePlanFeature()
	 * @since 2.0
	 * @generated
	 */
	EReference getLicensePlanFeature_Feature();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Plan</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getPlan()
	 * @see #getLicensePlanFeature()
	 * @since 2.0
	 * @generated
	 */
	EReference getLicensePlanFeature_Plan();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getVivid <em>Vivid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vivid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getVivid()
	 * @see #getLicensePlanFeature()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getLicensePlanFeature_Vivid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getCapacity()
	 * @see #getLicensePlanFeature()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getLicensePlanFeature_Capacity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant <em>Personal Feature Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal Feature Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant
	 * @since 2.0
	 * @generated
	 */
	EClass getPersonalFeatureGrant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getIdentifier()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getPersonalFeatureGrant_Identifier();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getFeature()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalFeatureGrant_Feature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Valid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getValid()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalFeatureGrant_Valid();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getUserAuthentication <em>User Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>User Authentication</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getUserAuthentication()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalFeatureGrant_UserAuthentication();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getCapacity()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getPersonalFeatureGrant_Capacity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getVivid <em>Vivid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vivid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getVivid()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getPersonalFeatureGrant_Vivid();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getPack <em>Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getPack()
	 * @see #getPersonalFeatureGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalFeatureGrant_Pack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites <em>License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Requisites</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites
	 * @since 2.1
	 * @generated
	 */
	EClass getLicenseRequisites();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIdentifier()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getLicenseRequisites_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIssueDate <em>Issue Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Date</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIssueDate()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getLicenseRequisites_IssueDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plan</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getPlan()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EAttribute getLicenseRequisites_Plan();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getProduct()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EReference getLicenseRequisites_Product();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Valid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getValid()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EReference getLicenseRequisites_Valid();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Signature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getSignature()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EReference getLicenseRequisites_Signature();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getAgreements <em>Agreements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Agreements</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getAgreements()
	 * @see #getLicenseRequisites()
	 * @since 2.1
	 * @generated
	 */
	EReference getLicenseRequisites_Agreements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack <em>Personal License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack
	 * @since 2.0
	 * @generated
	 */
	EClass getPersonalLicensePack();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getLicense <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>License</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getLicense()
	 * @see #getPersonalLicensePack()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalLicensePack_License();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getGrants <em>Grants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Grants</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getGrants()
	 * @see #getPersonalLicensePack()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalLicensePack_Grants();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites <em>Personal License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Requisites</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites
	 * @since 2.0
	 * @generated
	 */
	EClass getPersonalLicenseRequisites();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>User</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites#getUser()
	 * @see #getPersonalLicenseRequisites()
	 * @since 2.0
	 * @generated
	 */
	EReference getPersonalLicenseRequisites_User();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef <em>Product Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Ref</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ProductRef
	 * @since 2.0
	 * @generated
	 */
	EClass getProductRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ProductRef#getIdentifier()
	 * @see #getProductRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getProductRef_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ProductRef#getVersion()
	 * @see #getProductRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getProductRef_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.Signature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.Signature
	 * @since 2.0
	 * @generated
	 */
	EClass getSignature();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.Signature#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.Signature#getAttributes()
	 * @see #getSignature()
	 * @since 2.0
	 * @generated
	 */
	EReference getSignature_Attributes();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.Signature#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parent</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.Signature#getParent()
	 * @see #getSignature()
	 * @since 2.0
	 * @generated
	 */
	EReference getSignature_Parent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.SignatureAttribute <em>Signature Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature Attribute</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.SignatureAttribute
	 * @since 2.0
	 * @generated
	 */
	EClass getSignatureAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.SignatureAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.SignatureAttribute#getName()
	 * @see #getSignatureAttribute()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getSignatureAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.SignatureAttribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.SignatureAttribute#getValue()
	 * @see #getSignatureAttribute()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getSignatureAttribute_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant <em>User Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserGrant
	 * @since 2.0
	 * @generated
	 */
	EClass getUserGrant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserGrant#getUser()
	 * @see #getUserGrant()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getUserGrant_User();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant#getAuthentication <em>Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Authentication</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserGrant#getAuthentication()
	 * @see #getUserGrant()
	 * @since 2.0
	 * @generated
	 */
	EReference getUserGrant_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.UserRef <em>User Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Ref</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserRef
	 * @since 2.0
	 * @generated
	 */
	EClass getUserRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.UserRef#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserRef#getIdentifier()
	 * @see #getUserRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getUserRef_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.UserRef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserRef#getName()
	 * @see #getUserRef()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getUserRef_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriod <em>Validity Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriod
	 * @since 2.0
	 * @generated
	 */
	EClass getValidityPeriod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed <em>Validity Period Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Closed</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed
	 * @since 2.0
	 * @generated
	 */
	EClass getValidityPeriodClosed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getFrom()
	 * @see #getValidityPeriodClosed()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getValidityPeriodClosed_From();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getUntil <em>Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Until</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getUntil()
	 * @see #getValidityPeriodClosed()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getValidityPeriodClosed_Until();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch <em>Version Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version Match</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.VersionMatch
	 * @since 2.0
	 * @generated
	 */
	EClass getVersionMatch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.VersionMatch#getVersion()
	 * @see #getVersionMatch()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getVersionMatch_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.VersionMatch#getRule()
	 * @see #getVersionMatch()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getVersionMatch_Rule();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LicensesFactory getLicensesFactory();

} //LicensesPackage
