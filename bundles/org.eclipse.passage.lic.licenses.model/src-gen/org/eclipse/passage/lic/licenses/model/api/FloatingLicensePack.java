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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Floating License Pack</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getHost <em>Host</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getUsers <em>Users</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicensePack()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.FloatingLicensePackDescriptor"
 * @generated
 */
public interface FloatingLicensePack extends EObject, FloatingLicensePackDescriptor {
	/**
	 * Returns the value of the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License</em>' containment reference.
	 * @see #setLicense(FloatingLicenseRequisites)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicensePack_License()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	FloatingLicenseRequisites getLicense();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getLicense <em>License</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License</em>' containment reference.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(FloatingLicenseRequisites value);

	/**
	 * Returns the value of the '<em><b>Host</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' containment reference.
	 * @see #setHost(FloatingServer)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicensePack_Host()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	FloatingServer getHost();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack#getHost <em>Host</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' containment reference.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(FloatingServer value);

	/**
	 * Returns the value of the '<em><b>Users</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.UserGrant}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicensePack_Users()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	EList<UserGrant> getUsers();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getPack <em>Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicensePack_Features()
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant#getPack
	 * @model opposite="pack" containment="true" required="true"
	 * @generated
	 */
	@Override
	EList<FeatureGrant> getFeatures();

} // FloatingLicensePack
