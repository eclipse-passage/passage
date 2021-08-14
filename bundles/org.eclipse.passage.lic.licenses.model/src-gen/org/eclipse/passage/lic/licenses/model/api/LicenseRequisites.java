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

import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License Requisites</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIssueDate <em>Issue Date</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getPlan <em>Plan</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getValid <em>Valid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getSignature <em>Signature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getAgreements <em>Agreements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites()
 * @model abstract="true" superTypes="org.eclipse.passage.lic.licenses.model.api.LicenseRequisitesDescriptor"
 * @generated
 */
public interface LicenseRequisites extends EObject, LicenseRequisitesDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_Identifier()
	 * @model id="true" required="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Date</em>' attribute.
	 * @see #setIssueDate(Date)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_IssueDate()
	 * @model required="true"
	 * @generated
	 */
	@Override
	Date getIssueDate();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getIssueDate <em>Issue Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Issue Date</em>' attribute.
	 * @see #getIssueDate()
	 * @generated
	 */
	void setIssueDate(Date value);

	/**
	 * Returns the value of the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan</em>' attribute.
	 * @see #setPlan(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_Plan()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getPlan();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getPlan <em>Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plan</em>' attribute.
	 * @see #getPlan()
	 * @generated
	 */
	void setPlan(String value);

	/**
	 * Returns the value of the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' containment reference.
	 * @see #setProduct(ProductRef)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_Product()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	ProductRef getProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getProduct <em>Product</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' containment reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(ProductRef value);

	/**
	 * Returns the value of the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid</em>' containment reference.
	 * @see #setValid(ValidityPeriod)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_Valid()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	ValidityPeriod getValid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getValid <em>Valid</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' containment reference.
	 * @see #getValid()
	 * @generated
	 */
	void setValid(ValidityPeriod value);

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' containment reference.
	 * @see #setSignature(Signature)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_Signature()
	 * @model containment="true"
	 * @generated
	 */
	@Override
	Signature getSignature();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites#getSignature <em>Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' containment reference.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(Signature value);

	/**
	 * Returns the value of the '<em><b>Agreements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.AgreementData}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agreements</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicenseRequisites_Agreements()
	 * @model containment="true"
	 * @generated
	 */
	@Override
	EList<AgreementData> getAgreements();

} // LicenseRequisites
