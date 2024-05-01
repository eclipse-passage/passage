/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
	String eNS_URI = "http://www.eclipse.org/passage/lic/users/3.0.0"; //$NON-NLS-1$

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
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl <em>User Origin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserOriginImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserOrigin()
	 * @generated
	 */
	int USER_ORIGIN = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUser()
	 * @generated
	 */
	int USER = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl <em>User Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.UserGroupImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getUserGroup()
	 * @since 2.0
	 * @generated
	 */
	int USER_GROUP = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.impl.ContactImpl <em>Contact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.impl.ContactImpl
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getContact()
	 * @since 2.0
	 * @generated
	 */
	int CONTACT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__TITLE = 1;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__POSITION = 2;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__EMAIL = 3;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT__ADDRESS = 4;

	/**
	 * The number of structural features of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int CONTACT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner <em>License Owner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner
	 * @see org.eclipse.passage.lic.users.model.impl.UsersPackageImpl#getLicenseOwner()
	 * @since 2.0
	 * @generated
	 */
	int LICENSE_OWNER = 1;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER__CONTACT = 3;

	/**
	 * The number of structural features of the '<em>License Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>License Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int LICENSE_OWNER_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__IDENTIFIER = LICENSE_OWNER__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__NAME = LICENSE_OWNER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__DESCRIPTION = LICENSE_OWNER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__CONTACT = LICENSE_OWNER__CONTACT;

	/**
	 * The feature id for the '<em><b>Preferred Evaluation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__PREFERRED_EVALUATION_TYPE = LICENSE_OWNER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preferred Evaluation Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__PREFERRED_EVALUATION_EXPRESSION = LICENSE_OWNER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER__ORIGIN = LICENSE_OWNER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = LICENSE_OWNER_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_OPERATION_COUNT = LICENSE_OWNER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__IDENTIFIER = LICENSE_OWNER__IDENTIFIER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__NAME = LICENSE_OWNER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__DESCRIPTION = LICENSE_OWNER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__CONTACT = LICENSE_OWNER__CONTACT;

	/**
	 * The feature id for the '<em><b>Users</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__USERS = LICENSE_OWNER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP__ORIGIN = LICENSE_OWNER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP_FEATURE_COUNT = LICENSE_OWNER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>User Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_GROUP_OPERATION_COUNT = LICENSE_OWNER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__USERS = 3;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__GROUPS = 4;

	/**
	 * The number of structural features of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_OPERATION_COUNT = 0;

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

} // UsersPackage
