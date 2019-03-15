/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.api;

import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>License
 * Pack</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getIdentifier
 * <em>Identifier</em>}</li>
 * <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getIssueDate
 * <em>Issue Date</em>}</li>
 * <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductIdentifier
 * <em>Product Identifier</em>}</li>
 * <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductVersion
 * <em>Product Version</em>}</li>
 * <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getUserIdentifier
 * <em>User Identifier</em>}</li>
 * <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getLicenseGrants
 * <em>License Grants</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.LicensePackDescriptor"
 * @generated
 */
public interface LicensePack extends EObject, LicensePackDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack_Identifier()
	 * @model id="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getIdentifier
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
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Issue Date</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Issue Date</em>' attribute.
	 * @see #setIssueDate(Date)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack_IssueDate()
	 * @model
	 * @generated
	 */
	Date getIssueDate();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getIssueDate
	 * <em>Issue Date</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Issue Date</em>' attribute.
	 * @see #getIssueDate()
	 * @generated
	 */
	void setIssueDate(Date value);

	/**
	 * Returns the value of the '<em><b>Product Identifier</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product Identifier</em>' attribute.
	 * @see #setProductIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack_ProductIdentifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getProductIdentifier();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductIdentifier
	 * <em>Product Identifier</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Product Identifier</em>' attribute.
	 * @see #getProductIdentifier()
	 * @generated
	 */
	void setProductIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Product Version</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product Version</em>' attribute.
	 * @see #setProductVersion(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack_ProductVersion()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getProductVersion();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getProductVersion
	 * <em>Product Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Product Version</em>' attribute.
	 * @see #getProductVersion()
	 * @generated
	 */
	void setProductVersion(String value);

	/**
	 * Returns the value of the '<em><b>User Identifier</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Identifier</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>User Identifier</em>' attribute.
	 * @see #setUserIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack_UserIdentifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getUserIdentifier();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.licenses.model.api.LicensePack#getUserIdentifier
	 * <em>User Identifier</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>User Identifier</em>' attribute.
	 * @see #getUserIdentifier()
	 * @generated
	 */
	void setUserIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>License Grants</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant}. It is
	 * bidirectional and its opposite is
	 * '{@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getLicensePack
	 * <em>License Pack</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License Grants</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>License Grants</em>' containment reference
	 *         list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePack_LicenseGrants()
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseGrant#getLicensePack
	 * @model opposite="licensePack" containment="true"
	 * @generated
	 */
	@Override
	EList<LicenseGrant> getLicenseGrants();

} // LicensePack
