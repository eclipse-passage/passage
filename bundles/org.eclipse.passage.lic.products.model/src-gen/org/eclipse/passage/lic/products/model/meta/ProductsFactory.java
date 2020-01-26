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
package org.eclipse.passage.lic.products.model.meta;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;

/**
 * <!-- begin-user-doc -->
 * 
 * The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model.
 * 
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage
 * @generated
 */
public interface ProductsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProductsFactory eINSTANCE = org.eclipse.passage.lic.products.model.impl.ProductsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Product Line</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Line</em>'.
	 * @generated
	 */
	ProductLine createProductLine();

	/**
	 * Returns a new object of class '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product</em>'.
	 * @generated
	 */
	Product createProduct();

	/**
	 * Returns a new object of class '<em>Product Version</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Version</em>'.
	 * @generated
	 */
	ProductVersion createProductVersion();

	/**
	 * Returns a new object of class '<em>Product Version Feature</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Version Feature</em>'.
	 * @generated
	 */
	ProductVersionFeature createProductVersionFeature();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ProductsPackage getProductsPackage();

} // ProductsFactory
