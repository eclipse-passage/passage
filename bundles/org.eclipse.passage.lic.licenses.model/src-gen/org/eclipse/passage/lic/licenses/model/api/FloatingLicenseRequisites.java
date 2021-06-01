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

import org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Floating License Requisites</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseRequisites()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.LicenseRequisites org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisitesDescriptor"
 * @generated
 */
public interface FloatingLicenseRequisites extends LicenseRequisites, FloatingLicenseRequisitesDescriptor {
	/**
	 * Returns the value of the '<em><b>Company</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company</em>' containment reference.
	 * @see #setCompany(CompanyRef)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseRequisites_Company()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	CompanyRef getCompany();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getCompany <em>Company</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' containment reference.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(CompanyRef value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute.
	 * @see #setGroup(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFloatingLicenseRequisites_Group()
	 * @model
	 * @generated
	 */
	String getGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites#getGroup <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' attribute.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(String value);

} // FloatingLicenseRequisites
