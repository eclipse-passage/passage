/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.users.model.api;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User License</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getPlanIdentifier <em>Plan Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getProductIdentifier <em>Product Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getProductVersion <em>Product Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getValidUntil <em>Valid Until</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getIssueDate <em>Issue Date</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getOperatorIdentifier <em>Operator Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getPackIdentifier <em>Pack Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.UserLicense#getUser <em>User</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense()
 * @model superTypes="org.eclipse.passage.lic.users.model.api.UserLicenseDescriptor"
 * @generated
 */
public interface UserLicense extends EObject, UserLicenseDescriptor {
	/**
	 * Returns the value of the '<em><b>Plan Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan Identifier</em>' attribute.
	 * @see #setPlanIdentifier(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_PlanIdentifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getPlanIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getPlanIdentifier <em>Plan Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plan Identifier</em>' attribute.
	 * @see #getPlanIdentifier()
	 * @generated
	 */
	void setPlanIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Product Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Identifier</em>' attribute.
	 * @see #setProductIdentifier(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_ProductIdentifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getProductIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getProductIdentifier <em>Product Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Identifier</em>' attribute.
	 * @see #getProductIdentifier()
	 * @generated
	 */
	void setProductIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Product Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Version</em>' attribute.
	 * @see #setProductVersion(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_ProductVersion()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getProductVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getProductVersion <em>Product Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Version</em>' attribute.
	 * @see #getProductVersion()
	 * @generated
	 */
	void setProductVersion(String value);

	/**
	 * Returns the value of the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid From</em>' attribute.
	 * @see #setValidFrom(Date)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_ValidFrom()
	 * @model required="true"
	 * @generated
	 */
	@Override
	Date getValidFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getValidFrom <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid From</em>' attribute.
	 * @see #getValidFrom()
	 * @generated
	 */
	void setValidFrom(Date value);

	/**
	 * Returns the value of the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid Until</em>' attribute.
	 * @see #setValidUntil(Date)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_ValidUntil()
	 * @model required="true"
	 * @generated
	 */
	@Override
	Date getValidUntil();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getValidUntil <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid Until</em>' attribute.
	 * @see #getValidUntil()
	 * @generated
	 */
	void setValidUntil(Date value);

	/**
	 * Returns the value of the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Type</em>' attribute.
	 * @see #setConditionType(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_ConditionType()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getConditionType();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getConditionType <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Type</em>' attribute.
	 * @see #getConditionType()
	 * @generated
	 */
	void setConditionType(String value);

	/**
	 * Returns the value of the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Expression</em>' attribute.
	 * @see #setConditionExpression(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_ConditionExpression()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getConditionExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getConditionExpression <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Expression</em>' attribute.
	 * @see #getConditionExpression()
	 * @generated
	 */
	void setConditionExpression(String value);

	/**
	 * Returns the value of the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Date</em>' attribute.
	 * @see #setIssueDate(Date)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_IssueDate()
	 * @model
	 * @generated
	 */
	@Override
	Date getIssueDate();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getIssueDate <em>Issue Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Issue Date</em>' attribute.
	 * @see #getIssueDate()
	 * @generated
	 */
	void setIssueDate(Date value);

	/**
	 * Returns the value of the '<em><b>Operator Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator Identifier</em>' attribute.
	 * @see #setOperatorIdentifier(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_OperatorIdentifier()
	 * @model
	 * @generated
	 */
	@Override
	String getOperatorIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getOperatorIdentifier <em>Operator Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator Identifier</em>' attribute.
	 * @see #getOperatorIdentifier()
	 * @generated
	 */
	void setOperatorIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Pack Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pack Identifier</em>' attribute.
	 * @see #setPackIdentifier(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_PackIdentifier()
	 * @model
	 * @generated
	 */
	@Override
	String getPackIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getPackIdentifier <em>Pack Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pack Identifier</em>' attribute.
	 * @see #getPackIdentifier()
	 * @generated
	 */
	void setPackIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.users.model.api.User#getUserLicenses <em>User Licenses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' container reference.
	 * @see #setUser(User)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUserLicense_User()
	 * @see org.eclipse.passage.lic.users.model.api.User#getUserLicenses
	 * @model opposite="userLicenses" required="true" transient="false"
	 * @generated
	 */
	@Override
	User getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.UserLicense#getUser <em>User</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' container reference.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(User value);

} // UserLicense
