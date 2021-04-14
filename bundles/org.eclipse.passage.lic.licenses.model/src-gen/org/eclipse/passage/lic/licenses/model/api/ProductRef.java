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
import org.eclipse.passage.lic.licenses.ProductRefDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.ProductRef#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.ProductRef#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getProductRef()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.ProductRefDescriptor"
 * @generated
 */
public interface ProductRef extends EObject, ProductRefDescriptor {
	/**
	 * Returns the value of the '<em><b>Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' attribute.
	 * @see #setProduct(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getProductRef_Product()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef#getProduct <em>Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' attribute.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getProductRef_Version()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // ProductRef
