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
package org.eclipse.passage.lic.agreements.model.api;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.agreements.AgreementsGroupDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.AgreementsGroup#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.AgreementsGroup#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.AgreementsGroup#getAgreements <em>Agreements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreementsGroup()
 * @model superTypes="org.eclipse.passage.lic.agreements.model.api.AgreementsGroupDescriptor"
 * @generated
 */
public interface AgreementsGroup extends EObject, AgreementsGroupDescriptor {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreementsGroup_Name()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.AgreementsGroup#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreementsGroup_Description()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.AgreementsGroup#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Agreements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.agreements.model.api.Agreement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agreements</em>' containment reference list.
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreementsGroup_Agreements()
	 * @model containment="true"
	 * @generated
	 */
	@Override
	EList<Agreement> getAgreements();

} // AgreementsGroup
