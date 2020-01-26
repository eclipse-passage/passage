/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.products.model.api;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.products.ProductDescriptor;

/**
 * <!-- begin-user-doc -->
 * 
 * A representation of the model object '<em><b>Product</b></em>'.
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.Product#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.Product#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.Product#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.Product#getProductLine <em>Product Line</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.Product#getProductVersions <em>Product Versions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProduct()
 * @model superTypes="org.eclipse.passage.lic.products.model.api.ProductDescriptor"
 * @generated
 */
public interface Product extends EObject, ProductDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProduct_Identifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.Product#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProduct_Name()
	 * @model
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.Product#getName <em>Name</em>}' attribute.
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
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProduct_Description()
	 * @model
	 * @generated
	 */
	@Override
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.Product#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Product Line</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.products.model.api.ProductLine#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Line</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Line</em>' container reference.
	 * @see #setProductLine(ProductLine)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProduct_ProductLine()
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine#getProducts
	 * @model opposite="products" required="true" transient="false"
	 * @generated
	 */
	@Override
	ProductLine getProductLine();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.Product#getProductLine <em>Product Line</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Line</em>' container reference.
	 * @see #getProductLine()
	 * @generated
	 */
	void setProductLine(ProductLine value);

	/**
	 * Returns the value of the '<em><b>Product Versions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.products.model.api.ProductVersion}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Versions</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Versions</em>' containment reference list.
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProduct_ProductVersions()
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getProduct
	 * @model opposite="product" containment="true"
	 * @generated
	 */
	@Override
	EList<ProductVersion> getProductVersions();

} // Product
