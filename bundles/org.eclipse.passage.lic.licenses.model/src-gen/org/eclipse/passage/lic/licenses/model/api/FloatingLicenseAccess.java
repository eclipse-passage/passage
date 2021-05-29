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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Floating License Access</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getServer <em>Server</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getOriginLicensePack <em>Origin License Pack</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseAccess()
 * @model
 * @generated
 */
public interface FloatingLicenseAccess extends EObject {
	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseAccess_User()
	 * @model required="true"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>Server</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' containment reference.
	 * @see #setServer(FloatingServerConnection)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseAccess_Server()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FloatingServerConnection getServer();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getServer <em>Server</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' containment reference.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(FloatingServerConnection value);

	/**
	 * Returns the value of the '<em><b>Origin License Pack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin License Pack</em>' attribute.
	 * @see #setOriginLicensePack(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseAccess_OriginLicensePack()
	 * @model required="true"
	 * @generated
	 */
	String getOriginLicensePack();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess#getOriginLicensePack <em>Origin License Pack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin License Pack</em>' attribute.
	 * @see #getOriginLicensePack()
	 * @generated
	 */
	void setOriginLicensePack(String value);

} // FloatingLicenseAccess
