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

import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License Plan Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getLicensePlan <em>License Plan</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.LicensePlanFeatureDescriptor"
 * @generated
 */
public interface LicensePlanFeature extends EObject, LicensePlanFeatureDescriptor {
	/**
	 * Returns the value of the '<em><b>Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' containment reference.
	 * @see #setFeature(FeatureRef)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_Feature()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	FeatureRef getFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeature <em>Feature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' containment reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(FeatureRef value);

	/**
	 * Returns the value of the '<em><b>License Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License Plan</em>' reference.
	 * @see #setLicensePlan(LicensePlan)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_LicensePlan()
	 * @model required="true"
	 * @generated
	 */
	@Override
	LicensePlan getLicensePlan();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getLicensePlan <em>License Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License Plan</em>' reference.
	 * @see #getLicensePlan()
	 * @generated
	 */
	void setLicensePlan(LicensePlan value);

} // LicensePlanFeature
