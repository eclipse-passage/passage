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

import org.eclipse.passage.lic.users.ContactDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contact</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.users.model.api.Contact#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.Contact#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.Contact#getPosition <em>Position</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.Contact#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.Contact#getAddress <em>Address</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getContact()
 * @model superTypes="org.eclipse.passage.lic.users.model.api.ContactDescriptor"
 * @generated
 */
public interface Contact extends EObject, ContactDescriptor {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getContact_Name()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.Contact#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getContact_Title()
	 * @model
	 * @generated
	 */
	@Override
	String getTitle();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.Contact#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getContact_Position()
	 * @model
	 * @generated
	 */
	@Override
	String getPosition();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.Contact#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(String value);

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getContact_Email()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getEmail();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.Contact#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 * @generated
	 */
	void setEmail(String value);

	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getContact_Address()
	 * @model
	 * @generated
	 */
	@Override
	String getAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.Contact#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

} // Contact
