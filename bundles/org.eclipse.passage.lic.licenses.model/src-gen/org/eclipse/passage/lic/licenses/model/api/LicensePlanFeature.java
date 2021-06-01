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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getPlan <em>Plan</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getVivid <em>Vivid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getCapacity <em>Capacity</em>}</li>
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
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
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
	 * @since 2.0
	 * @generated
	 */
	void setFeature(FeatureRef value);

	/**
	 * Returns the value of the '<em><b>Plan</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Plan</em>' container reference.
	 * @see #setPlan(LicensePlan)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_Plan()
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan#getFeatures
	 * @model opposite="features" required="true" transient="false"
	 * @generated
	 */
	@Override
	LicensePlan getPlan();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getPlan <em>Plan</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plan</em>' container reference.
	 * @see #getPlan()
	 * @since 2.0
	 * @generated
	 */
	void setPlan(LicensePlan value);

	/**
	 * Returns the value of the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vivid</em>' attribute.
	 * @see #setVivid(long)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_Vivid()
	 * @model required="true"
	 * @generated
	 */
	@Override
	long getVivid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getVivid <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vivid</em>' attribute.
	 * @see #getVivid()
	 * @since 2.0
	 * @generated
	 */
	void setVivid(long value);

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since 2.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getLicensePlanFeature_Capacity()
	 * @model required="true"
	 * @generated
	 */
	@Override
	int getCapacity();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @since 2.0
	 * @generated
	 */
	void setCapacity(int value);

} // LicensePlanFeature
