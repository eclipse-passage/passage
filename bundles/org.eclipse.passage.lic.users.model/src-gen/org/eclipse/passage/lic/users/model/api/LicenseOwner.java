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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.users.LicenseOwnerDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License Owner</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getContact <em>Contact</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getLicenseOwner()
 * @model interface="true" abstract="true" superTypes="org.eclipse.passage.lic.users.model.api.LicenseOwnerDescriptor"
 * @generated
 */
public interface LicenseOwner extends EObject, LicenseOwnerDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getLicenseOwner_Identifier()
	 * @model
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getLicenseOwner_Name()
	 * @model
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @since 2.0
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getLicenseOwner_Description()
	 * @model
	 * @generated
	 */
	@Override
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact</em>' containment reference.
	 * @see #setContact(Contact)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getLicenseOwner_Contact()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	Contact getContact();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner#getContact <em>Contact</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact</em>' containment reference.
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 */
	void setContact(Contact value);

} // LicenseOwner
