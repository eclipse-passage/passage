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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Personal License Pack</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getGrants <em>Grants</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalLicensePack()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.PersonalLicensePackDescriptor"
 * @generated
 */
public interface PersonalLicensePack extends EObject, PersonalLicensePackDescriptor {
	/**
	 * Returns the value of the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License</em>' containment reference.
	 * @see #setLicense(PersonalLicenseRequisites)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalLicensePack_License()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	PersonalLicenseRequisites getLicense();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getLicense <em>License</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License</em>' containment reference.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(PersonalLicenseRequisites value);

	/**
	 * Returns the value of the '<em><b>Grants</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getPack <em>Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grants</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalLicensePack_Grants()
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getPack
	 * @model opposite="pack" containment="true"
	 * @generated
	 */
	@Override
	EList<PersonalFeatureGrant> getGrants();

} // PersonalLicensePack
