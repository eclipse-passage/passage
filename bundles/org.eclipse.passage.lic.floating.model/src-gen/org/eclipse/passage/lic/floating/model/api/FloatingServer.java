/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.model.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Server</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingServer#getIdentifier
 * <em>Identifier</em>}</li>
 * <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingServer#getAuthentication
 * <em>Authentication</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServer()
 * @model
 * @generated
 */
public interface FloatingServer extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServer_Identifier()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.FloatingServer#getIdentifier
	 * <em>Identifier</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Authentication</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Authentication</em>' reference.
	 * @see #setAuthentication(EvaluationInstructions)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServer_Authentication()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EvaluationInstructions getAuthentication();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.FloatingServer#getAuthentication
	 * <em>Authentication</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Authentication</em>' reference.
	 * @see #getAuthentication()
	 * @generated
	 */
	void setAuthentication(EvaluationInstructions value);

} // FloatingServer
