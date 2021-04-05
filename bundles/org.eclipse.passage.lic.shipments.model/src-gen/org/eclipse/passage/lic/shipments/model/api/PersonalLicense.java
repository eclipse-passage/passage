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
package org.eclipse.passage.lic.shipments.model.api;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Personal License</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getLicense <em>License</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getPersonalLicense()
 * @model superTypes="org.eclipse.passage.lic.shipments.model.api.PersonalLicenseDescriptor"
 * @generated
 */
public interface PersonalLicense extends EObject, PersonalLicenseDescriptor {
	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getPersonalLicense_User()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>License</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License</em>' attribute.
	 * @see #setLicense(String)
	 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getPersonalLicense_License()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getLicense();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getLicense <em>License</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License</em>' attribute.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(String value);

} // PersonalLicense
