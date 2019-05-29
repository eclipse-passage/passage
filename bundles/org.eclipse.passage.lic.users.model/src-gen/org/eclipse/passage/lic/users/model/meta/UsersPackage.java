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
package org.eclipse.passage.lic.users.model.meta;

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
 * @see org.eclipse.passage.lic.users.model.meta.UsersFactory
 * @model kind="package"
 * @generated
 */
public interface UsersPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "users"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/users/0.5.0"; //$NON-NLS-1$

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
	UsersPackage eINSTANCE = org.eclipse.passage.lic.users.model.impl.UsersPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.UserOriginDescriptor <em>User Origin Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.UserOriginDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserOriginDescriptor()
	 * @generated
	 */
	int USER_ORIGIN_DESCRIPTOR = 0;

	/**
	 * The number of structural features of the '<em>User Origin Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Origin Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.UserDescriptor <em>User Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.UserDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserDescriptor()
	 * @generated
	 */
	int USER_DESCRIPTOR = 1;

	/**
	 * The number of structural features of the '<em>User Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.UserLicenseDescriptor <em>User License Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.UserLicenseDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserLicenseDescriptor()
	 * @generated
	 */
	int USER_LICENSE_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>User License Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User License Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl <em>User Origin</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserOriginImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserOrigin()
	 * @generated
	 */
	int USER_ORIGIN = 3;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__IDENTIFIER = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__NAME = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__DESCRIPTION = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__USERS = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_FEATURE_COUNT = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_OPERATION_COUNT = USER_ORIGIN_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUser()
	 * @generated
	 */
	int USER = 4;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__IDENTIFIER = USER_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__EMAIL = USER_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__FULL_NAME = USER_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DESCRIPTION = USER_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Preferred Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__PREFERRED_CONDITION_TYPE = USER_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Preferred Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__PREFERRED_CONDITION_EXPRESSION = USER_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>User Origin</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__USER_ORIGIN = USER_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>User Licenses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__USER_LICENSES = USER_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = USER_DESCRIPTOR_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_OPERATION_COUNT = USER_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl <em>User License</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserLicenseImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserLicense()
	 * @generated
	 */
	int USER_LICENSE = 5;

	/**
	 * The feature id for the '<em><b>Plan Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__PLAN_IDENTIFIER = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Product Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__PRODUCT_IDENTIFIER = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Product Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__PRODUCT_VERSION = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__VALID_FROM = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__VALID_UNTIL = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__CONDITION_TYPE = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__CONDITION_EXPRESSION = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__ISSUE_DATE = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Operator Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__OPERATOR_IDENTIFIER = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Pack Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__PACK_IDENTIFIER = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>User</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE__USER = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>User License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE_FEATURE_COUNT = USER_LICENSE_DESCRIPTOR_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>User License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_LICENSE_OPERATION_COUNT = USER_LICENSE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.UserOriginDescriptor <em>User Origin Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Origin Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.UserOriginDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.UserOriginDescriptor"
	 * @generated
	 */
	EClass getUserOriginDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.UserDescriptor <em>User Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.UserDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.UserDescriptor"
	 * @generated
	 */
	EClass getUserDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.UserLicenseDescriptor <em>User License Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User License Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.UserLicenseDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.UserLicenseDescriptor"
	 * @generated
	 */
	EClass getUserLicenseDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.UserOrigin <em>User Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Origin</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin
	 * @generated
	 */
	EClass getUserOrigin();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getIdentifier()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EAttribute getUserOrigin_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getName()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EAttribute getUserOrigin_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getDescription()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EAttribute getUserOrigin_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Users</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EReference getUserOrigin_Users();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getIdentifier()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getEmail()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Email();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getFullName <em>Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Name</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getFullName()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_FullName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getDescription()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getPreferredConditionType <em>Preferred Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Condition Type</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getPreferredConditionType()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_PreferredConditionType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getPreferredConditionExpression <em>Preferred Condition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Condition Expression</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getPreferredConditionExpression()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_PreferredConditionExpression();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.users.model.api.User#getUserOrigin <em>User Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>User Origin</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getUserOrigin()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_UserOrigin();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.users.model.api.User#getUserLicenses <em>User Licenses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>User Licenses</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getUserLicenses()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_UserLicenses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.UserLicense <em>User License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User License</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense
	 * @generated
	 */
	EClass getUserLicense();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getPlanIdentifier <em>Plan Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plan Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getPlanIdentifier()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_PlanIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getProductIdentifier <em>Product Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getProductIdentifier()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_ProductIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getProductVersion()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_ProductVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getValidFrom()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_ValidFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getValidUntil <em>Valid Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Until</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getValidUntil()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_ValidUntil();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getConditionType <em>Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Type</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getConditionType()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_ConditionType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getConditionExpression <em>Condition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Expression</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getConditionExpression()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_ConditionExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getIssueDate <em>Issue Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Date</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getIssueDate()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_IssueDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getOperatorIdentifier <em>Operator Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getOperatorIdentifier()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_OperatorIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getPackIdentifier <em>Pack Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pack Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getPackIdentifier()
	 * @see #getUserLicense()
	 * @generated
	 */
	EAttribute getUserLicense_PackIdentifier();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>User</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserLicense#getUser()
	 * @see #getUserLicense()
	 * @generated
	 */
	EReference getUserLicense_User();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UsersFactory getUsersFactory();

} // UsersPackage
