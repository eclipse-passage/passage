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
package org.eclipse.passage.lic.products.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.products.model.meta.ProductsFactory;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model <b>Package</b>.
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class ProductsPackageImpl extends EPackageImpl implements ProductsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productLineDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productVersionDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productVersionFeatureDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productLineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productVersionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productVersionFeatureEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ProductsPackageImpl() {
		super(eNS_URI, ProductsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ProductsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ProductsPackage init() {
		if (isInited)
			return (ProductsPackage) EPackage.Registry.INSTANCE.getEPackage(ProductsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredProductsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ProductsPackageImpl theProductsPackage = registeredProductsPackage instanceof ProductsPackageImpl
				? (ProductsPackageImpl) registeredProductsPackage
				: new ProductsPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theProductsPackage.createPackageContents();

		// Initialize created meta-data
		theProductsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theProductsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ProductsPackage.eNS_URI, theProductsPackage);
		return theProductsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductLineDescriptor() {
		return productLineDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductDescriptor() {
		return productDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductVersionDescriptor() {
		return productVersionDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductVersionFeatureDescriptor() {
		return productVersionFeatureDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductLine() {
		return productLineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductLine_Identifier() {
		return (EAttribute) productLineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductLine_Name() {
		return (EAttribute) productLineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductLine_Description() {
		return (EAttribute) productLineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProductLine_Products() {
		return (EReference) productLineEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProduct() {
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProduct_Identifier() {
		return (EAttribute) productEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProduct_Name() {
		return (EAttribute) productEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProduct_Description() {
		return (EAttribute) productEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProduct_ProductLine() {
		return (EReference) productEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProduct_ProductVersions() {
		return (EReference) productEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductVersion() {
		return productVersionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersion_Version() {
		return (EAttribute) productVersionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersion_Name() {
		return (EAttribute) productVersionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersion_InstallationToken() {
		return (EAttribute) productVersionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersion_SecureToken() {
		return (EAttribute) productVersionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersion_News() {
		return (EAttribute) productVersionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProductVersion_Product() {
		return (EReference) productVersionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProductVersion_ProductVersionFeatures() {
		return (EReference) productVersionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductVersionFeature() {
		return productVersionFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersionFeature_FeatureIdentifier() {
		return (EAttribute) productVersionFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersionFeature_FeatureVersion() {
		return (EAttribute) productVersionFeatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductVersionFeature_RestrictionLevel() {
		return (EAttribute) productVersionFeatureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProductVersionFeature_ProductVersion() {
		return (EReference) productVersionFeatureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductsFactory getProductsFactory() {
		return (ProductsFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		productLineDescriptorEClass = createEClass(PRODUCT_LINE_DESCRIPTOR);

		productDescriptorEClass = createEClass(PRODUCT_DESCRIPTOR);

		productVersionDescriptorEClass = createEClass(PRODUCT_VERSION_DESCRIPTOR);

		productVersionFeatureDescriptorEClass = createEClass(PRODUCT_VERSION_FEATURE_DESCRIPTOR);

		productLineEClass = createEClass(PRODUCT_LINE);
		createEAttribute(productLineEClass, PRODUCT_LINE__IDENTIFIER);
		createEAttribute(productLineEClass, PRODUCT_LINE__NAME);
		createEAttribute(productLineEClass, PRODUCT_LINE__DESCRIPTION);
		createEReference(productLineEClass, PRODUCT_LINE__PRODUCTS);

		productEClass = createEClass(PRODUCT);
		createEAttribute(productEClass, PRODUCT__IDENTIFIER);
		createEAttribute(productEClass, PRODUCT__NAME);
		createEAttribute(productEClass, PRODUCT__DESCRIPTION);
		createEReference(productEClass, PRODUCT__PRODUCT_LINE);
		createEReference(productEClass, PRODUCT__PRODUCT_VERSIONS);

		productVersionEClass = createEClass(PRODUCT_VERSION);
		createEAttribute(productVersionEClass, PRODUCT_VERSION__VERSION);
		createEAttribute(productVersionEClass, PRODUCT_VERSION__NAME);
		createEAttribute(productVersionEClass, PRODUCT_VERSION__INSTALLATION_TOKEN);
		createEAttribute(productVersionEClass, PRODUCT_VERSION__SECURE_TOKEN);
		createEAttribute(productVersionEClass, PRODUCT_VERSION__NEWS);
		createEReference(productVersionEClass, PRODUCT_VERSION__PRODUCT);
		createEReference(productVersionEClass, PRODUCT_VERSION__PRODUCT_VERSION_FEATURES);

		productVersionFeatureEClass = createEClass(PRODUCT_VERSION_FEATURE);
		createEAttribute(productVersionFeatureEClass, PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER);
		createEAttribute(productVersionFeatureEClass, PRODUCT_VERSION_FEATURE__FEATURE_VERSION);
		createEAttribute(productVersionFeatureEClass, PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL);
		createEReference(productVersionFeatureEClass, PRODUCT_VERSION_FEATURE__PRODUCT_VERSION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		productLineEClass.getESuperTypes().add(this.getProductLineDescriptor());
		productEClass.getESuperTypes().add(this.getProductDescriptor());
		productVersionEClass.getESuperTypes().add(this.getProductVersionDescriptor());
		productVersionFeatureEClass.getESuperTypes().add(this.getProductVersionFeatureDescriptor());

		// Initialize classes, features, and operations; add parameters
		initEClass(productLineDescriptorEClass, ProductLineDescriptor.class, "ProductLineDescriptor", IS_ABSTRACT, //$NON-NLS-1$
				IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(productDescriptorEClass, ProductDescriptor.class, "ProductDescriptor", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(productVersionDescriptorEClass, ProductVersionDescriptor.class, "ProductVersionDescriptor", //$NON-NLS-1$
				IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(productVersionFeatureDescriptorEClass, ProductVersionFeatureDescriptor.class,
				"ProductVersionFeatureDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(productLineEClass, ProductLine.class, "ProductLine", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductLine_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				ProductLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductLine_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProductLine.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductLine_Description(), ecorePackage.getEString(), "description", null, 0, 1, //$NON-NLS-1$
				ProductLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getProductLine_Products(), this.getProduct(), this.getProduct_ProductLine(), "products", null, 0, //$NON-NLS-1$
				-1, ProductLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getProduct_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, Product.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProduct_Name(), ecorePackage.getEString(), "name", null, 0, 1, Product.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProduct_Description(), ecorePackage.getEString(), "description", null, 0, 1, Product.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_ProductLine(), this.getProductLine(), this.getProductLine_Products(), "productLine", //$NON-NLS-1$
				null, 1, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_ProductVersions(), this.getProductVersion(), this.getProductVersion_Product(),
				"productVersions", null, 0, -1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productVersionEClass, ProductVersion.class, "ProductVersion", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductVersion_Version(), ecorePackage.getEString(), "version", null, 1, 1, //$NON-NLS-1$
				ProductVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductVersion_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProductVersion.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductVersion_InstallationToken(), ecorePackage.getEString(), "installationToken", null, 0, //$NON-NLS-1$
				1, ProductVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductVersion_SecureToken(), ecorePackage.getEString(), "secureToken", null, 0, 1, //$NON-NLS-1$
				ProductVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductVersion_News(), ecorePackage.getEString(), "news", null, 0, 1, ProductVersion.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductVersion_Product(), this.getProduct(), this.getProduct_ProductVersions(), "product", //$NON-NLS-1$
				null, 1, 1, ProductVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductVersion_ProductVersionFeatures(), this.getProductVersionFeature(),
				this.getProductVersionFeature_ProductVersion(), "productVersionFeatures", null, 0, -1, //$NON-NLS-1$
				ProductVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productVersionFeatureEClass, ProductVersionFeature.class, "ProductVersionFeature", !IS_ABSTRACT, //$NON-NLS-1$
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductVersionFeature_FeatureIdentifier(), ecorePackage.getEString(), "featureIdentifier", //$NON-NLS-1$
				null, 1, 1, ProductVersionFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductVersionFeature_FeatureVersion(), ecorePackage.getEString(), "featureVersion", null, 1, //$NON-NLS-1$
				1, ProductVersionFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductVersionFeature_RestrictionLevel(), ecorePackage.getEString(), "restrictionLevel", null, //$NON-NLS-1$
				0, 1, ProductVersionFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductVersionFeature_ProductVersion(), this.getProductVersion(),
				this.getProductVersion_ProductVersionFeatures(), "productVersion", null, 1, 1, //$NON-NLS-1$
				ProductVersionFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // ProductsPackageImpl
