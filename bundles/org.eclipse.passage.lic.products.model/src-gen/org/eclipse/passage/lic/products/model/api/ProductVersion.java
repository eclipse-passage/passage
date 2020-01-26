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
import org.eclipse.passage.lic.products.ProductVersionDescriptor;

/**
 * <!-- begin-user-doc -->
 * 
 * A representation of the model object '<em><b>Product Version</b></em>'.
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getInstallationToken <em>Installation Token</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getSecureToken <em>Secure Token</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getNews <em>News</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProductVersionFeatures <em>Product Version Features</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion()
 * @model superTypes="org.eclipse.passage.lic.products.model.api.ProductVersionDescriptor"
 * @generated
 */
public interface ProductVersion extends EObject, ProductVersionDescriptor {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_Version()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_Name()
	 * @model
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Installation Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Installation Token</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Installation Token</em>' attribute.
	 * @see #setInstallationToken(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_InstallationToken()
	 * @model
	 * @generated
	 */
	@Override
	String getInstallationToken();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getInstallationToken <em>Installation Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Installation Token</em>' attribute.
	 * @see #getInstallationToken()
	 * @generated
	 */
	void setInstallationToken(String value);

	/**
	 * Returns the value of the '<em><b>Secure Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secure Token</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secure Token</em>' attribute.
	 * @see #setSecureToken(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_SecureToken()
	 * @model
	 * @generated
	 */
	@Override
	String getSecureToken();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getSecureToken <em>Secure Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secure Token</em>' attribute.
	 * @see #getSecureToken()
	 * @generated
	 */
	void setSecureToken(String value);

	/**
	 * Returns the value of the '<em><b>News</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>News</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>News</em>' attribute.
	 * @see #setNews(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_News()
	 * @model
	 * @generated
	 */
	@Override
	String getNews();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getNews <em>News</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>News</em>' attribute.
	 * @see #getNews()
	 * @generated
	 */
	void setNews(String value);

	/**
	 * Returns the value of the '<em><b>Product</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.products.model.api.Product#getProductVersions <em>Product Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' container reference.
	 * @see #setProduct(Product)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_Product()
	 * @see org.eclipse.passage.lic.products.model.api.Product#getProductVersions
	 * @model opposite="productVersions" required="true" transient="false"
	 * @generated
	 */
	@Override
	Product getProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProduct <em>Product</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' container reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(Product value);

	/**
	 * Returns the value of the '<em><b>Product Version Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Version Features</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Version Features</em>' containment reference list.
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersion_ProductVersionFeatures()
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getProductVersion
	 * @model opposite="productVersion" containment="true"
	 * @generated
	 */
	@Override
	EList<ProductVersionFeature> getProductVersionFeatures();

} // ProductVersion
