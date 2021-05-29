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

import org.eclipse.passage.lic.licenses.VersionMatchDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Version Match</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch#getRule <em>Rule</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getVersionMatch()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.VersionMatchDescriptor"
 * @generated
 */
public interface VersionMatch extends EObject, VersionMatchDescriptor {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getVersionMatch_Version()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule</em>' attribute.
	 * @see #setRule(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getVersionMatch_Rule()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getRule();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch#getRule <em>Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule</em>' attribute.
	 * @see #getRule()
	 * @generated
	 */
	void setRule(String value);

} // VersionMatch
