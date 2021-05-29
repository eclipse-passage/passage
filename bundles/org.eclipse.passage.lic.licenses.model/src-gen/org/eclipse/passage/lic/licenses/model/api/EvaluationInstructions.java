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

import org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evaluation Instructions</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getEvaluationInstructions()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.EvaluationInstructionsDescriptor"
 * @generated
 */
public interface EvaluationInstructions extends EObject, EvaluationInstructionsDescriptor {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getEvaluationInstructions_Type()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getEvaluationInstructions_Expression()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

} // EvaluationInstructions
