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

import org.eclipse.passage.lic.users.UserDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationType <em>Preferred Evaluation Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationExpression <em>Preferred Evaluation Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getOrigin <em>Origin</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser()
 * @model superTypes="org.eclipse.passage.lic.users.model.api.UserDescriptor org.eclipse.passage.lic.users.model.api.LicenseOwner"
 * @generated
 */
public interface User extends UserDescriptor, LicenseOwner {
	/**
	 * Returns the value of the '<em><b>Preferred Evaluation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Preferred Evaluation Type</em>' attribute.
	 * @see #setPreferredEvaluationType(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_PreferredEvaluationType()
	 * @model
	 * @generated
	 */
	@Override
	String getPreferredEvaluationType();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationType <em>Preferred Evaluation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Evaluation Type</em>' attribute.
	 * @see #getPreferredEvaluationType()
	 * @since 2.0
	 * @generated
	 */
	void setPreferredEvaluationType(String value);

	/**
	 * Returns the value of the '<em><b>Preferred Evaluation Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Preferred Evaluation Expression</em>' attribute.
	 * @see #setPreferredEvaluationExpression(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_PreferredEvaluationExpression()
	 * @model
	 * @generated
	 */
	@Override
	String getPreferredEvaluationExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getPreferredEvaluationExpression <em>Preferred Evaluation Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Evaluation Expression</em>' attribute.
	 * @see #getPreferredEvaluationExpression()
	 * @since 2.0
	 * @generated
	 */
	void setPreferredEvaluationExpression(String value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Origin</em>' container reference.
	 * @see #setOrigin(UserOrigin)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_Origin()
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers
	 * @model opposite="users" required="true" transient="false"
	 * @generated
	 */
	@Override
	UserOrigin getOrigin();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getOrigin <em>Origin</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' container reference.
	 * @see #getOrigin()
	 * @since 2.0
	 * @generated
	 */
	void setOrigin(UserOrigin value);

} // User
