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

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getAgreements <em>Agreements</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getPersonal <em>Personal</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFloating <em>Floating</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.LicensePlanDescriptor"
 * @generated
 */
public interface LicensePlan extends EObject, LicensePlanDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Identifier()
	 * @model id="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getIdentifier <em>Identifier</em>}' attribute.
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
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Name()
	 * @model
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Description()
	 * @model
	 * @generated
	 */
	@Override
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Agreements</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.1
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Agreements</em>' attribute list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Agreements()
	 * @model
	 * @generated
	 */
	@Override
	EList<String> getAgreements();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Features()
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getPlan
	 * @model opposite="plan" containment="true" required="true"
	 * @generated
	 */
	@Override
	EList<LicensePlanFeature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Personal</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Personal</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Personal()
	 * @model containment="true"
	 * @generated
	 */
	@Override
	EList<PersonalLicensePack> getPersonal();

	/**
	 * Returns the value of the '<em><b>Floating</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Floating</em>' containment reference list.
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlan_Floating()
	 * @model containment="true"
	 * @generated
	 */
	@Override
	EList<FloatingLicensePack> getFloating();

} // LicensePlan
