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
package org.eclipse.passage.lic.users.model.meta;

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
 * @see org.eclipse.passage.lic.users.model.meta.UsersFactory
 * @model kind="package"
 * @generated
 */
public interface UsersPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "users"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/users/2.0.0"; //$NON-NLS-1$

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
	UsersPackage eINSTANCE = org.eclipse.passage.lic.users.model.impl.UsersPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.UserOriginDescriptor <em>User Origin Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.UserOriginDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserOriginDescriptor()
	 * @generated
	 */
	int USER_ORIGIN_DESCRIPTOR = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.ContactDescriptor <em>Contact Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.ContactDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getContactDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int CONTACT_DESCRIPTOR = 0;

	/**
	 * The number of structural features of the '<em>Contact Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Contact Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.LicenseOwnerDescriptor <em>License Owner Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.LicenseOwnerDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getLicenseOwnerDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int LICENSE_OWNER_DESCRIPTOR = 1;

	/**
	 * The number of structural features of the '<em>License Owner Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>License Owner Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.UserDescriptor <em>User Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.UserDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserDescriptor()
	 * @generated
	 */
	int USER_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>User Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.UserGroupDescriptor <em>User Group Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.UserGroupDescriptor
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserGroupDescriptor()
	 * @since 2.0
	 * @generated
	 */
	int USER_GROUP_DESCRIPTOR = 3;

	/**
	 * The number of structural features of the '<em>User Group Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Group Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>User Origin Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>User Origin Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl <em>User Origin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserOriginImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserOrigin()
	 * @generated
	 */
	int USER_ORIGIN = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUser()
	 * @generated
	 */
	int USER = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl <em>User Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserGroupImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserGroup()
	 * @since 2.0
	 * @generated
	 */
	int USER_GROUP = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.ContactImpl <em>Contact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.ContactImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getContact()
	 * @since 2.0
	 * @generated
	 */
	int CONTACT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__NAME = CONTACT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__TITLE = CONTACT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__POSITION = CONTACT_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__EMAIL = CONTACT_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__ADDRESS = CONTACT_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT_FEATURE_COUNT = CONTACT_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT_OPERATION_COUNT = CONTACT_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner <em>License Owner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getLicenseOwner()
	 * @since 2.0
	 * @generated
	 */
	int LICENSE_OWNER = 6;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__IDENTIFIER = LICENSE_OWNER_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__NAME = LICENSE_OWNER_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__DESCRIPTION = LICENSE_OWNER_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__CONTACT = LICENSE_OWNER_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>License Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER_FEATURE_COUNT = LICENSE_OWNER_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>License Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER_OPERATION_COUNT = LICENSE_OWNER_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__IDENTIFIER = USER_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__NAME = USER_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__DESCRIPTION = USER_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__CONTACT = USER_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Preferred Evaluation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__PREFERRED_EVALUATION_TYPE = USER_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Preferred Evaluation Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__PREFERRED_EVALUATION_EXPRESSION = USER_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__ORIGIN = USER_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = USER_DESCRIPTOR_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_OPERATION_COUNT = USER_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__IDENTIFIER = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__NAME = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__DESCRIPTION = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__CONTACT = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Users</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__USERS = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__ORIGIN = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>User Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP_FEATURE_COUNT = USER_GROUP_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>User Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP_OPERATION_COUNT = USER_GROUP_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__IDENTIFIER = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__NAME = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__DESCRIPTION = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__USERS = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__GROUPS = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_FEATURE_COUNT = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_OPERATION_COUNT = USER_ORIGIN_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.UserOriginDescriptor <em>User Origin Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Origin Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.UserOriginDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.UserOriginDescriptor"
	 * @generated
	 */
	EClass getUserOriginDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.ContactDescriptor <em>Contact Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.ContactDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.ContactDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getContactDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.LicenseOwnerDescriptor <em>License Owner Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Owner Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.LicenseOwnerDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.LicenseOwnerDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getLicenseOwnerDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.UserDescriptor <em>User Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.UserDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.UserDescriptor"
	 * @generated
	 */
	EClass getUserDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.UserGroupDescriptor <em>User Group Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Group Descriptor</em>'.
	 * @see org.eclipse.passage.lic.users.UserGroupDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.users.UserGroupDescriptor"
	 * @since 2.0
	 * @generated
	 */
	EClass getUserGroupDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.UserOrigin <em>User Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Origin</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin
	 * @generated
	 */
	EClass getUserOrigin();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
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
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Users</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EReference getUserOrigin_Users();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Groups</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getGroups()
	 * @see #getUserOrigin()
	 * @since 2.0
	 * @generated
	 */
	EReference getUserOrigin_Groups();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationType <em>Preferred Evaluation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Evaluation Type</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationType()
	 * @see #getUser()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getUser_PreferredEvaluationType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationExpression <em>Preferred Evaluation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preferred Evaluation Expression</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationExpression()
	 * @see #getUser()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getUser_PreferredEvaluationExpression();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.users.model.api.User#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Origin</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.User#getOrigin()
	 * @see #getUser()
	 * @since 2.0
	 * @generated
	 */
	EReference getUser_Origin();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.UserGroup <em>User Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Group</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserGroup
	 * @since 2.0
	 * @generated
	 */
	EClass getUserGroup();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.passage.lic.users.model.api.UserGroup#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Users</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserGroup#getUsers()
	 * @see #getUserGroup()
	 * @since 2.0
	 * @generated
	 */
	EReference getUserGroup_Users();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.users.model.api.UserGroup#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Origin</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.UserGroup#getOrigin()
	 * @see #getUserGroup()
	 * @since 2.0
	 * @generated
	 */
	EReference getUserGroup_Origin();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.Contact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.Contact
	 * @since 2.0
	 * @generated
	 */
	EClass getContact();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.Contact#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.Contact#getName()
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getContact_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.Contact#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.Contact#getTitle()
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getContact_Title();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.Contact#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.Contact#getPosition()
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getContact_Position();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.Contact#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.Contact#getEmail()
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getContact_Email();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.Contact#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.Contact#getAddress()
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getContact_Address();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner <em>License Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Owner</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner
	 * @since 2.0
	 * @generated
	 */
	EClass getLicenseOwner();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner#getIdentifier()
	 * @see #getLicenseOwner()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getLicenseOwner_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner#getName()
	 * @see #getLicenseOwner()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getLicenseOwner_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner#getDescription()
	 * @see #getLicenseOwner()
	 * @since 2.0
	 * @generated
	 */
	EAttribute getLicenseOwner_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Contact</em>'.
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner#getContact()
	 * @see #getLicenseOwner()
	 * @since 2.0
	 * @generated
	 */
	EReference getLicenseOwner_Contact();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UsersFactory getUsersFactory();

} //UsersPackage
