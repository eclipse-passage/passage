/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.model.api;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>License
 * Requisites</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIssueDate <em>Issue Date</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getPlan <em>Plan</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getValid <em>Valid</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites()
 * @model
 * @generated
 */
public interface LicenseRequisites extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites_Identifier()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIdentifier
	 * <em>Identifier</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Issue Date</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Issue Date</em>' attribute.
	 * @see #setIssueDate(Date)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites_IssueDate()
	 * @model required="true"
	 * @generated
	 */
	Date getIssueDate();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIssueDate
	 * <em>Issue Date</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Issue Date</em>' attribute.
	 * @see #getIssueDate()
	 * @generated
	 */
	void setIssueDate(Date value);

	/**
	 * Returns the value of the '<em><b>Company</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Company</em>' attribute.
	 * @see #setCompany(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites_Company()
	 * @model required="true"
	 * @generated
	 */
	String getCompany();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getCompany <em>Company</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' attribute.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(String value);

	/**
	 * Returns the value of the '<em><b>Plan</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Plan</em>' attribute.
	 * @see #setPlan(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites_Plan()
	 * @model required="true"
	 * @generated
	 */
	String getPlan();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getPlan <em>Plan</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plan</em>' attribute.
	 * @see #getPlan()
	 * @generated
	 */
	void setPlan(String value);

	/**
	 * Returns the value of the '<em><b>Product</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product</em>' reference.
	 * @see #setProduct(ProductRef)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites_Product()
	 * @model required="true"
	 * @generated
	 */
	ProductRef getProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getProduct <em>Product</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(ProductRef value);

	/**
	 * Returns the value of the '<em><b>Valid</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Valid</em>' reference.
	 * @see #setValid(ValidityPeriod)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getLicenseRequisites_Valid()
	 * @model required="true"
	 * @generated
	 */
	ValidityPeriod getValid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getValid <em>Valid</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' reference.
	 * @see #getValid()
	 * @generated
	 */
	void setValid(ValidityPeriod value);

} // LicenseRequisites
