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
	String eNS_URI = "http://www.eclipse.org/passage/lic/licenses/0.5.0"; //$NON-NLS-1$

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
	 * The meta object id for the '{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl <em>License Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl
	 * @see org.eclipse.passage.lic.licenses.model.impl.LicensesPackageImpl#getLicensePlan()
	 * @generated
	 */
	int LICENSE_PLAN = 4;

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
	 * The number of structural features of the '<em>License Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PLAN_FEATURE_COUNT = LICENSE_PLAN_DESCRIPTOR_FEATURE_COUNT + 4;

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
	int LICENSE_PLAN_FEATURE = 5;

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
	int LICENSE_PACK = 6;

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
	int LICENSE_GRANT = 7;

	/**
	 * The feature id for the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__FEATURE_IDENTIFIER = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Match Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__MATCH_VERSION = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__MATCH_RULE = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__VALID_FROM = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__VALID_UNTIL = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CONDITION_TYPE = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CONDITION_EXPRESSION = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CAPACITY = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>License Pack</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__LICENSE_PACK = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>License Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_FEATURE_COUNT = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 9;

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
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LicensesFactory getLicensesFactory();

} // LicensesPackage
