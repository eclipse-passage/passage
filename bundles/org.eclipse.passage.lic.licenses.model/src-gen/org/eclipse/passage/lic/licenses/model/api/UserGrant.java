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
package org.eclipse.passage.lic.licenses.model.api;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.licenses.UserGrantDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Grant</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.UserGrant#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.UserGrant#getAuthentication <em>Authentication</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getUserGrant()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.UserGrantDescriptor"
 * @generated
 */
public interface UserGrant extends EObject, UserGrantDescriptor {
	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getUserGrant_User()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authentication</em>' containment reference.
	 * @see #setAuthentication(EvaluationInstructions)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getUserGrant_Authentication()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	EvaluationInstructions getAuthentication();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant#getAuthentication <em>Authentication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authentication</em>' containment reference.
	 * @see #getAuthentication()
	 * @generated
	 */
	void setAuthentication(EvaluationInstructions value);

} // UserGrant
