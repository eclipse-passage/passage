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
package org.eclipse.passage.lic.users.model.api;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.users.UserGroupDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since 2.0
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserGroup#getUsers <em>Users</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserGroup#getOrigin <em>Origin</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserGroup()
 * @model superTypes="org.eclipse.passage.lic.users.model.api.UserGroupDescriptor org.eclipse.passage.lic.users.model.api.LicenseOwner"
 * @generated
 */
public interface UserGroup extends UserGroupDescriptor, LicenseOwner {
	/**
	 * Returns the value of the '<em><b>Users</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.users.model.api.User}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' reference list.
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserGroup_Users()
	 * @model
	 * @generated
	 */
	@Override
	EList<User> getUsers();

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' container reference.
	 * @see #setOrigin(UserOrigin)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserGroup_Origin()
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getGroups
	 * @model opposite="groups" required="true" transient="false"
	 * @generated
	 */
	@Override
	UserOrigin getOrigin();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserGroup#getOrigin <em>Origin</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' container reference.
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(UserOrigin value);

} // UserGroup
