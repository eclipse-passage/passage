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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Product
 * Ref</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.passage.lic.floating.model.api.ProductRef#getProduct
 * <em>Product</em>}</li>
 * <li>{@link org.eclipse.passage.lic.floating.model.api.ProductRef#getVersion
 * <em>Version</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getProductRef()
 * @model
 * @generated
 */
public interface ProductRef extends EObject {
	/**
	 * Returns the value of the '<em><b>Product</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product</em>' attribute.
	 * @see #setProduct(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getProductRef_Product()
	 * @model required="true"
	 * @generated
	 */
	String getProduct();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.ProductRef#getProduct
	 * <em>Product</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Product</em>' attribute.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getProductRef_Version()
	 * @model required="true"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.ProductRef#getVersion
	 * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // ProductRef
