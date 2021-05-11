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
package org.eclipse.passage.lic.licenses.model.meta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * 
 * The <b>Package</b> for the model. It contains accessors for the meta objects
 * to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
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
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "licenses"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/licenses/2.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.passage.lic"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LicensesPackage eINSTANCE = org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicensePlanDescriptor <em>License Plan Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicensePlanDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlanDescriptor()
	 * @generated
	 */
	int LICENSE_PLAN_DESCRIPTOR = 0;

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
	int LICENSE_PLAN_FEATURE_DESCRIPTOR = 1;

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
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicensePackDescriptor <em>License Pack Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicensePackDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePackDescriptor()
	 * @generated
	 */
	int LICENSE_PACK_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicenseGrantDescriptor <em>License Grant Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicenseGrantDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicenseGrantDescriptor()
	 * @generated
	 */
	int LICENSE_GRANT_DESCRIPTOR = 3;

	/**
	 * The number of structural features of the '<em>License Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.ProductRefDescriptor <em>Product Ref Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.ProductRefDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getProductRefDescriptor()
	 * @generated
	 */
	int PRODUCT_REF_DESCRIPTOR = 4;

	/**
	 * The number of structural features of the '<em>Product Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Product Ref Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor <em>License Requisites Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicenseRequisitesDescriptor()
	 * @generated
	 */
	int LICENSE_REQUISITES_DESCRIPTOR = 5;

	/**
	 * The number of structural features of the '<em>License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	int FLOATING_LICENSE_REQUISITES_DESCRIPTOR = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor <em>Personal License Requisites Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalLicenseRequisitesDescriptor()
	 * @generated
	 */
	int PERSONAL_LICENSE_REQUISITES_DESCRIPTOR = 6;

	/**
	 * The number of structural features of the '<em>Personal License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Personal License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT = LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Floating License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Floating License Requisites Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT = LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor <em>Validity Period Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriodDescriptor()
	 * @generated
	 */
	int VALIDITY_PERIOD_DESCRIPTOR = 8;

	/**
	 * The number of structural features of the '<em>Validity Period Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Validity Period Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor <em>Validity Period Closed Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriodClosedDescriptor()
	 * @generated
	 */
	int VALIDITY_PERIOD_CLOSED_DESCRIPTOR = 9;

	/**
	 * The number of structural features of the '<em>Validity Period Closed Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Validity Period Closed Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl <em>License Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlan()
	 * @generated
	 */
	int LICENSE_PLAN = 10;

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
	 * The feature id for the '<em><b>License Plan Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__LICENSE_PLAN_FEATURES = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Personal</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__PERSONAL = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Floating</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN__FLOATING = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>License Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_COUNT = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 6;

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
	int LICENSE_PLAN_FEATURE = 11;

	/**
	 * The feature id for the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Match Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__MATCH_VERSION = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__MATCH_RULE = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>License Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE__LICENSE_PLAN = LICENSE_PLAN_FEATURE_DESCRIPTOR_FEATURE_COUNT + 3;

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
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl <em>License Pack</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePack()
	 * @generated
	 */
	int LICENSE_PACK = 12;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__ISSUE_DATE = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>User Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__USER_IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>User Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__USER_FULL_NAME = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Request Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__REQUEST_IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Plan Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__PLAN_IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Product Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__PRODUCT_IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Product Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__PRODUCT_VERSION = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>License Grants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__LICENSE_GRANTS = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_FEATURE_COUNT = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 9;

	/**
	 * The number of operations of the '<em>License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_OPERATION_COUNT = LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl <em>License Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicenseGrant()
	 * @generated
	 */
	int LICENSE_GRANT = 13;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__IDENTIFIER = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__FEATURE_IDENTIFIER = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Match Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__MATCH_VERSION = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__MATCH_RULE = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__VALID_FROM = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__VALID_UNTIL = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CONDITION_TYPE = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CONDITION_EXPRESSION = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CAPACITY = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>License Pack</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__LICENSE_PACK = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>License Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_FEATURE_COUNT = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>License Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_OPERATION_COUNT = LICENSE_GRANT_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicensePackImpl <em>Floating License Pack</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingLicensePackImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicensePack()
	 * @generated
	 */
	int FLOATING_LICENSE_PACK = 14;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__LICENSE = 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__HOST = 1;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__USERS = 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__FEATURES = 3;

	/**
	 * The number of structural features of the '<em>Floating License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Floating License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicenseRequisitesImpl <em>License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicenseRequisites()
	 * @generated
	 */
	int LICENSE_REQUISITES = 15;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__IDENTIFIER = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__ISSUE_DATE = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__PLAN = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__PRODUCT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__VALID = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_FEATURE_COUNT = LICENSE_REQUISITES_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_OPERATION_COUNT = LICENSE_REQUISITES_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.PersonalLicenseRequisitesImpl <em>Personal License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.PersonalLicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getPersonalLicenseRequisites()
	 * @generated
	 */
	int PERSONAL_LICENSE_REQUISITES = 16;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__IDENTIFIER = LICENSE_REQUISITES__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__ISSUE_DATE = LICENSE_REQUISITES__ISSUE_DATE;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__PLAN = LICENSE_REQUISITES__PLAN;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__PRODUCT = LICENSE_REQUISITES__PRODUCT;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__VALID = LICENSE_REQUISITES__VALID;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES__USER = LICENSE_REQUISITES_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Personal License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_FEATURE_COUNT = LICENSE_REQUISITES_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Personal License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_REQUISITES_OPERATION_COUNT = LICENSE_REQUISITES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseRequisitesImpl <em>Floating License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicenseRequisites()
	 * @generated
	 */
	int FLOATING_LICENSE_REQUISITES = 17;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__IDENTIFIER = LICENSE_REQUISITES__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__ISSUE_DATE = LICENSE_REQUISITES__ISSUE_DATE;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__PLAN = LICENSE_REQUISITES__PLAN;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__PRODUCT = LICENSE_REQUISITES__PRODUCT;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__VALID = LICENSE_REQUISITES__VALID;

	/**
	 * The feature id for the '<em><b>Company</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES__COMPANY = LICENSE_REQUISITES_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Floating License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_FEATURE_COUNT = LICENSE_REQUISITES_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Floating License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_REQUISITES_OPERATION_COUNT = LICENSE_REQUISITES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.ProductRefImpl <em>Product Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.ProductRefImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getProductRef()
	 * @generated
	 */
	int PRODUCT_REF = 18;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__IDENTIFIER = PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__VERSION = PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_FEATURE_COUNT = PRODUCT_REF_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_OPERATION_COUNT = PRODUCT_REF_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingServerImpl <em>Floating Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingServerImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingServer()
	 * @generated
	 */
	int FLOATING_SERVER = 19;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER__AUTHENTICATION = 1;

	/**
	 * The number of structural features of the '<em>Floating Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Floating Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.UserGrantImpl <em>User Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.UserGrantImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getUserGrant()
	 * @generated
	 */
	int USER_GRANT = 20;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT__USER = 0;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT__AUTHENTICATION = 1;

	/**
	 * The number of structural features of the '<em>User Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>User Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FeatureGrantImpl <em>Feature Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FeatureGrantImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFeatureGrant()
	 * @generated
	 */
	int FEATURE_GRANT = 21;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VALID = 3;

	/**
	 * The feature id for the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VIVID = 4;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__CAPACITY = 5;

	/**
	 * The feature id for the '<em><b>Pack</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__PACK = 6;

	/**
	 * The number of structural features of the '<em>Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriod <em>Validity Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriod
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getValidityPeriod()
	 * @generated
	 */
	int VALIDITY_PERIOD = 22;

	/**
	 * The number of structural features of the '<em>Validity Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_FEATURE_COUNT = VALIDITY_PERIOD_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Validity Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	int VALIDITY_PERIOD_CLOSED = 23;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED__FROM = VALIDITY_PERIOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED__UNTIL = VALIDITY_PERIOD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Validity Period Closed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_FEATURE_COUNT = VALIDITY_PERIOD_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Validity Period Closed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_OPERATION_COUNT = VALIDITY_PERIOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.EvaluationInstructionsImpl <em>Evaluation Instructions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.EvaluationInstructionsImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getEvaluationInstructions()
	 * @generated
	 */
	int EVALUATION_INSTRUCTIONS = 24;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Evaluation Instructions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Evaluation Instructions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.VersionMatchImpl <em>Version Match</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.VersionMatchImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getVersionMatch()
	 * @generated
	 */
	int VERSION_MATCH = 25;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH__VERSION = 0;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH__RULE = 1;

	/**
	 * The number of structural features of the '<em>Version Match</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Version Match</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl <em>Floating License Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingLicenseAccess()
	 * @generated
	 */
	int FLOATING_LICENSE_ACCESS = 26;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS__USER = 0;

	/**
	 * The feature id for the '<em><b>Server</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS__SERVER = 1;

	/**
	 * The feature id for the '<em><b>Origin License Pack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK = 2;

	/**
	 * The number of structural features of the '<em>Floating License Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Floating License Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_ACCESS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.FloatingServerConnectionImpl <em>Floating Server Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.FloatingServerConnectionImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getFloatingServerConnection()
	 * @generated
	 */
	int FLOATING_SERVER_CONNECTION = 27;

	/**
	 * The feature id for the '<em><b>Ip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION__IP = 0;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION__PORT = 1;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION__AUTHENTICATION = 2;

	/**
	 * The number of structural features of the '<em>Floating Server Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_CONNECTION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Floating Server Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	int GRANT_ACQISITION = 28;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Grant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__GRANT = 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__USER = 3;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION__CREATED = 4;

	/**
	 * The number of structural features of the '<em>Grant Acqisition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Grant Acqisition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRANT_ACQISITION_OPERATION_COUNT = 0;

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
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.LicensePackDescriptor <em>License Pack Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Pack Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.LicensePackDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.LicensePackDescriptor"
	 * @generated
	 */
	EClass getLicensePackDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.LicenseGrantDescriptor <em>License Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Grant Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.LicenseGrantDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.LicenseGrantDescriptor"
	 * @generated
	 */
	EClass getLicenseGrantDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.ProductRefDescriptor <em>Product Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Ref Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.ProductRefDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.ProductRefDescriptor"
	 * @generated
	 */
	EClass getProductRefDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor <em>License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Requisites Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor"
	 * @generated
	 */
	EClass getLicenseRequisitesDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor <em>Personal License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Requisites Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor" superTypes="org.eclipse.passage.lic.licenses.model.api.LicenseRequisitesDescriptor"
	 * @generated
	 */
	EClass getPersonalLicenseRequisitesDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor <em>Floating License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Requisites Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor" superTypes="org.eclipse.passage.lic.licenses.model.api.LicenseRequisitesDescriptor"
	 * @generated
	 */
	EClass getFloatingLicenseRequisitesDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor <em>Validity Period Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor"
	 * @generated
	 */
	EClass getValidityPeriodDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor <em>Validity Period Closed Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Closed Descriptor</em>'.
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor"
	 * @generated
	 */
	EClass getValidityPeriodClosedDescriptor();

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
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getLicensePlanFeatures <em>License Plan Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>License Plan Features</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getLicensePlanFeatures()
	 * @see #getLicensePlan()
	 * @generated
	 */
	EReference getLicensePlan_LicensePlanFeatures();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getPersonal <em>Personal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Personal</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getPersonal()
	 * @see #getLicensePlan()
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
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeatureIdentifier <em>Feature Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeatureIdentifier()
	 * @see #getLicensePlanFeature()
	 * @generated
	 */
	EAttribute getLicensePlanFeature_FeatureIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchVersion <em>Match Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Version</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchVersion()
	 * @see #getLicensePlanFeature()
	 * @generated
	 */
	EAttribute getLicensePlanFeature_MatchVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchRule <em>Match Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Rule</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchRule()
	 * @see #getLicensePlanFeature()
	 * @generated
	 */
	EAttribute getLicensePlanFeature_MatchRule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getLicensePlan <em>License Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>License Plan</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getLicensePlan()
	 * @see #getLicensePlanFeature()
	 * @generated
	 */
	EReference getLicensePlanFeature_LicensePlan();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack <em>License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack
	 * @generated
	 */
	EClass getLicensePack();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getIssueDate <em>Issue Date</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Date</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getIssueDate()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_IssueDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductIdentifier <em>Product Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_ProductIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductVersion()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_ProductVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getUserIdentifier <em>User Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getUserIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_UserIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getUserFullName <em>User Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Full Name</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getUserFullName()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_UserFullName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getRequestIdentifier <em>Request Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Request Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getRequestIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_RequestIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getPlanIdentifier <em>Plan Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plan Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getPlanIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_PlanIdentifier();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getLicenseGrants <em>License Grants</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>License Grants</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePack#getLicenseGrants()
	 * @see #getLicensePack()
	 * @generated
	 */
	EReference getLicensePack_LicenseGrants();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant <em>License Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant
	 * @generated
	 */
	EClass getLicenseGrant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getIdentifier()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getFeatureIdentifier <em>Feature Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Identifier</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getFeatureIdentifier()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_FeatureIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getMatchVersion <em>Match Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Version</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getMatchVersion()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_MatchVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getMatchRule <em>Match Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Rule</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getMatchRule()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_MatchRule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getValidFrom()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ValidFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getValidUntil <em>Valid Until</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Until</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getValidUntil()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ValidUntil();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getConditionType <em>Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Type</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getConditionType()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ConditionType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getConditionExpression <em>Condition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Expression</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getConditionExpression()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ConditionExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getCapacity()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_Capacity();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getLicensePack <em>License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>License Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getLicensePack()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EReference getLicenseGrant_LicensePack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack <em>Floating License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Pack</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack
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
	 * @generated
	 */
	EReference getFloatingLicensePack_Features();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites <em>License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Requisites</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites
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
	 * @generated
	 */
	EReference getLicenseRequisites_Valid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites <em>Personal License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Requisites</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites
	 * @generated
	 */
	EClass getPersonalLicenseRequisites();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites#getUser()
	 * @see #getPersonalLicenseRequisites()
	 * @generated
	 */
	EAttribute getPersonalLicenseRequisites_User();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites <em>Floating License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Requisites</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites
	 * @generated
	 */
	EClass getFloatingLicenseRequisites();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Company</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getCompany()
	 * @see #getFloatingLicenseRequisites()
	 * @generated
	 */
	EAttribute getFloatingLicenseRequisites_Company();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef <em>Product Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Ref</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ProductRef
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
	 * @generated
	 */
	EAttribute getProductRef_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServer <em>Floating Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating Server</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServer
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
	 * @generated
	 */
	EReference getFloatingServer_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant <em>User Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserGrant
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
	 * @generated
	 */
	EReference getUserGrant_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant <em>Feature Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Grant</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant
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
	 * @generated
	 */
	EAttribute getFeatureGrant_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getFeature()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EAttribute getFeatureGrant_Feature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getVersion()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EReference getFeatureGrant_Version();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Valid</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getValid()
	 * @see #getFeatureGrant()
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
	 * @generated
	 */
	EReference getFeatureGrant_Pack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriod <em>Validity Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriod
	 * @generated
	 */
	EClass getValidityPeriod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed <em>Validity Period Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Closed</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed
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
	 * @generated
	 */
	EAttribute getValidityPeriodClosed_Until();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions <em>Evaluation Instructions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Instructions</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions
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
	 * @generated
	 */
	EAttribute getEvaluationInstructions_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch <em>Version Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version Match</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.VersionMatch
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
	 * @generated
	 */
	EAttribute getVersionMatch_Rule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess <em>Floating License Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Access</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess
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
	 * @generated
	 */
	EAttribute getFloatingLicenseAccess_OriginLicensePack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection <em>Floating Server Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating Server Connection</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection
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
	 * @generated
	 */
	EReference getFloatingServerConnection_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition <em>Grant Acqisition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grant Acqisition</em>'.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition
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
	 * @generated
	 */
	EAttribute getGrantAcqisition_Created();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LicensesFactory getLicensesFactory();

} // LicensesPackage
