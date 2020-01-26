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
package org.eclipse.passage.lic.products.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model.
 * 
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage
 * @generated
 */
public class ProductsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ProductsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ProductsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns <code>true</code> if the object is either the
	 * model's package or is an instance object of the model.
	 * 
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductsSwitch<Adapter> modelSwitch = new ProductsSwitch<Adapter>() {
		@Override
		public Adapter caseProductLineDescriptor(ProductLineDescriptor object) {
			return createProductLineDescriptorAdapter();
		}

		@Override
		public Adapter caseProductDescriptor(ProductDescriptor object) {
			return createProductDescriptorAdapter();
		}

		@Override
		public Adapter caseProductVersionDescriptor(ProductVersionDescriptor object) {
			return createProductVersionDescriptorAdapter();
		}

		@Override
		public Adapter caseProductVersionFeatureDescriptor(ProductVersionFeatureDescriptor object) {
			return createProductVersionFeatureDescriptorAdapter();
		}

		@Override
		public Adapter caseProductLine(ProductLine object) {
			return createProductLineAdapter();
		}

		@Override
		public Adapter caseProduct(Product object) {
			return createProductAdapter();
		}

		@Override
		public Adapter caseProductVersion(ProductVersion object) {
			return createProductVersionAdapter();
		}

		@Override
		public Adapter caseProductVersionFeature(ProductVersionFeature object) {
			return createProductVersionFeatureAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.ProductLineDescriptor <em>Product Line Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.ProductLineDescriptor
	 * @generated
	 */
	public Adapter createProductLineDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.ProductDescriptor <em>Product Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.ProductDescriptor
	 * @generated
	 */
	public Adapter createProductDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.ProductVersionDescriptor <em>Product Version Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.ProductVersionDescriptor
	 * @generated
	 */
	public Adapter createProductVersionDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor <em>Product Version Feature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor
	 * @generated
	 */
	public Adapter createProductVersionFeatureDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.model.api.ProductLine <em>Product Line</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.model.api.ProductLine
	 * @generated
	 */
	public Adapter createProductLineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.model.api.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.model.api.Product
	 * @generated
	 */
	public Adapter createProductAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.model.api.ProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion
	 * @generated
	 */
	public Adapter createProductVersionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature <em>Product Version Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersionFeature
	 * @generated
	 */
	public Adapter createProductVersionFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * 
	 * This default implementation returns null.
	 * 
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ProductsAdapterFactory
