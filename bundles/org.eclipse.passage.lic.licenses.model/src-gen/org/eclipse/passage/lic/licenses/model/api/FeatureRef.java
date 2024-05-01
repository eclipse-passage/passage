/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Ref</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef#getVersionMatch <em>Version Match</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFeatureRef()
 * @model
 * @generated
 */
public interface FeatureRef extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFeatureRef_Identifier()
	 * @model required="true"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Version Match</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version Match</em>' containment reference.
	 * @see #setVersionMatch(VersionMatch)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getFeatureRef_VersionMatch()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VersionMatch getVersionMatch();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef#getVersionMatch <em>Version Match</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version Match</em>' containment reference.
	 * @see #getVersionMatch()
	 * @generated
	 */
	void setVersionMatch(VersionMatch value);

} // FeatureRef
