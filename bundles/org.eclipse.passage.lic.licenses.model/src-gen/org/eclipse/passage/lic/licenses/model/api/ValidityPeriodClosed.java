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

import java.util.Date;

import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validity Period Closed</b></em>'.
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getUntil <em>Until</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getValidityPeriodClosed()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.ValidityPeriod org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosedDescriptor"
 * @generated
 */
public interface ValidityPeriodClosed extends ValidityPeriod, ValidityPeriodClosedDescriptor {
	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(Date)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getValidityPeriodClosed_From()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	@Override
	Date getFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Date value);

	/**
	 * Returns the value of the '<em><b>Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Until</em>' attribute.
	 * @see #setUntil(Date)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getValidityPeriodClosed_Until()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	@Override
	Date getUntil();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed#getUntil <em>Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Until</em>' attribute.
	 * @see #getUntil()
	 * @generated
	 */
	void setUntil(Date value);

} // ValidityPeriodClosed
