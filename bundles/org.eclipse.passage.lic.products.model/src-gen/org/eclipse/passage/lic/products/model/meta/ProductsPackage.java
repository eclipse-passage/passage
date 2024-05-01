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
package org.eclipse.passage.lic.products.model.meta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * 
 * The <b>Package</b> for the model. It contains accessors for the meta objects
 * to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.products.model.meta.ProductsFactory
 * @model kind="package"
 * @generated
 */
public interface ProductsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "products"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/products/3.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.passage.lic"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProductsPackage eINSTANCE = org.eclipse.passage.lic.products.model.impl.ProductsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.products.model.impl.ProductLineImpl <em>Product Line</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.products.model.impl.ProductLineImpl
	 * @see org.eclipse.passage.lic.products.model.impl.ProductsPackageImpl#getProductLine()
	 * @generated
	 */
	int PRODUCT_LINE = 0;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__PRODUCTS = 3;

	/**
	 * The number of structural features of the '<em>Product Line</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Product Line</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.products.model.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.products.model.impl.ProductImpl
	 * @see org.eclipse.passage.lic.products.model.impl.ProductsPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 1;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Product Line</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_LINE = 3;

	/**
	 * The feature id for the '<em><b>Product Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_VERSIONS = 4;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl <em>Product Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.products.model.impl.ProductVersionImpl
	 * @see org.eclipse.passage.lic.products.model.impl.ProductsPackageImpl#getProductVersion()
	 * @generated
	 */
	int PRODUCT_VERSION = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__VERSION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Installation Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__INSTALLATION_TOKEN = 2;

	/**
	 * The feature id for the '<em><b>Secure Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__SECURE_TOKEN = 3;

	/**
	 * The feature id for the '<em><b>News</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__NEWS = 4;

	/**
	 * The feature id for the '<em><b>Product</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__PRODUCT = 5;

	/**
	 * The feature id for the '<em><b>Product Version Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__PRODUCT_VERSION_FEATURES = 6;

	/**
	 * The number of structural features of the '<em>Product Version</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Product Version</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.products.model.impl.ProductVersionFeatureImpl <em>Product Version Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.products.model.impl.ProductVersionFeatureImpl
	 * @see org.eclipse.passage.lic.products.model.impl.ProductsPackageImpl#getProductVersionFeature()
	 * @generated
	 */
	int PRODUCT_VERSION_FEATURE = 3;

	/**
	 * The feature id for the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Feature Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__FEATURE_VERSION = 1;

	/**
	 * The feature id for the '<em><b>Restriction Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL = 2;

	/**
	 * The feature id for the '<em><b>Product Version</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__PRODUCT_VERSION = 3;

	/**
	 * The number of structural features of the '<em>Product Version Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Product Version Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.products.model.api.ProductLine <em>Product Line</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Line</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine
	 * @generated
	 */
	EClass getProductLine();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductLine#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine#getIdentifier()
	 * @see #getProductLine()
	 * @generated
	 */
	EAttribute getProductLine_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductLine#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine#getName()
	 * @see #getProductLine()
	 * @generated
	 */
	EAttribute getProductLine_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductLine#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine#getDescription()
	 * @see #getProductLine()
	 * @generated
	 */
	EAttribute getProductLine_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.products.model.api.ProductLine#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine#getProducts()
	 * @see #getProductLine()
	 * @generated
	 */
	EReference getProductLine_Products();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.products.model.api.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.Product#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.Product#getIdentifier()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.Product#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.Product#getName()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.Product#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.Product#getDescription()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Description();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.products.model.api.Product#getProductLine <em>Product Line</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product Line</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.Product#getProductLine()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductLine();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.products.model.api.Product#getProductVersions <em>Product Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Product Versions</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.Product#getProductVersions()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductVersions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.products.model.api.ProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion
	 * @generated
	 */
	EClass getProductVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getVersion()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getName()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getInstallationToken <em>Installation Token</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Installation Token</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getInstallationToken()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_InstallationToken();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getSecureToken <em>Secure Token</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secure Token</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getSecureToken()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_SecureToken();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getNews <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>News</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getNews()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_News();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getProduct()
	 * @see #getProductVersion()
	 * @generated
	 */
	EReference getProductVersion_Product();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProductVersionFeatures <em>Product Version Features</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Product Version Features</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getProductVersionFeatures()
	 * @see #getProductVersion()
	 * @generated
	 */
	EReference getProductVersion_ProductVersionFeatures();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature <em>Product Version Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Version Feature</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature
	 * @generated
	 */
	EClass getProductVersionFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureIdentifier <em>Feature Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Identifier</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureIdentifier()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EAttribute getProductVersionFeature_FeatureIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureVersion <em>Feature Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Version</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureVersion()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EAttribute getProductVersionFeature_FeatureVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getRestrictionLevel <em>Restriction Level</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Restriction Level</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getRestrictionLevel()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EAttribute getProductVersionFeature_RestrictionLevel();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getProductVersion()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EReference getProductVersionFeature_ProductVersion();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProductsFactory getProductsFactory();

} // ProductsPackage
