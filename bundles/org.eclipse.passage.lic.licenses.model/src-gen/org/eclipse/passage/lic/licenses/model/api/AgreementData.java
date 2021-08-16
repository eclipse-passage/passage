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

import org.eclipse.passage.lic.licenses.AgreementDataDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agreement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since 2.1
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getHashAlgo <em>Hash Algo</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getHash <em>Hash</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getContentType <em>Content Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.AgreementDataDescriptor"
 * @generated
 */
public interface AgreementData extends EObject, AgreementDataDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_Identifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getIdentifier <em>Identifier</em>}' attribute.
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
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_Name()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getName <em>Name</em>}' attribute.
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
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_File()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getFile();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Hash Algo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hash Algo</em>' attribute.
	 * @see #setHashAlgo(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_HashAlgo()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getHashAlgo();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getHashAlgo <em>Hash Algo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hash Algo</em>' attribute.
	 * @see #getHashAlgo()
	 * @generated
	 */
	void setHashAlgo(String value);

	/**
	 * Returns the value of the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hash</em>' attribute.
	 * @see #setHash(byte[])
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_Hash()
	 * @model required="true"
	 * @generated
	 */
	@Override
	byte[] getHash();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getHash <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hash</em>' attribute.
	 * @see #getHash()
	 * @generated
	 */
	void setHash(byte[] value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(byte[])
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_Content()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	@Override
	byte[] getContent();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(byte[] value);

	/**
	 * Returns the value of the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Type</em>' attribute.
	 * @see #setContentType(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getAgreementData_ContentType()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getContentType();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData#getContentType <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content Type</em>' attribute.
	 * @see #getContentType()
	 * @generated
	 */
	void setContentType(String value);

} // AgreementData
