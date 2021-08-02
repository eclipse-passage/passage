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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.agreements.AgreementDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agreement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getSpdx <em>Spdx</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getMime <em>Mime</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreement()
 * @model superTypes="org.eclipse.passage.lic.agreements.model.api.AgreementDescriptor"
 * @generated
 */
public interface Agreement extends EObject, AgreementDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreement_Identifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Spdx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spdx</em>' attribute.
	 * @see #setSpdx(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreement_Spdx()
	 * @model
	 * @generated
	 */
	@Override
	String getSpdx();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getSpdx <em>Spdx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spdx</em>' attribute.
	 * @see #getSpdx()
	 * @generated
	 */
	void setSpdx(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreement_Name()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreement_File()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getFile();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Mime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mime</em>' attribute.
	 * @see #setMime(String)
	 * @see org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage#getAgreement_Mime()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getMime();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.agreements.model.api.Agreement#getMime <em>Mime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mime</em>' attribute.
	 * @see #getMime()
	 * @generated
	 */
	void setMime(String value);

} // Agreement
