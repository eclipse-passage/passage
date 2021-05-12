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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchVersion <em>Match Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchRule <em>Match Rule</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getLicensePlan <em>License Plan</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.LicensePlanFeatureDescriptor"
 * @generated
 */
public interface LicensePlanFeature extends EObject, LicensePlanFeatureDescriptor {
	/**
	 * Returns the value of the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Identifier</em>' attribute.
	 * @see #setFeatureIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_FeatureIdentifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getFeatureIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getFeatureIdentifier <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Identifier</em>' attribute.
	 * @see #getFeatureIdentifier()
	 * @generated
	 */
	void setFeatureIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Match Version</b></em>' attribute.
	 * The default value is <code>"0.0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Version</em>' attribute.
	 * @see #setMatchVersion(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_MatchVersion()
	 * @model default="0.0.0" required="true"
	 * @generated
	 */
	@Override
	String getMatchVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchVersion <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Version</em>' attribute.
	 * @see #getMatchVersion()
	 * @generated
	 */
	void setMatchVersion(String value);

	/**
	 * Returns the value of the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Rule</em>' attribute.
	 * @see #setMatchRule(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_MatchRule()
	 * @model
	 * @generated
	 */
	@Override
	String getMatchRule();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getMatchRule <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Rule</em>' attribute.
	 * @see #getMatchRule()
	 * @generated
	 */
	void setMatchRule(String value);

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
