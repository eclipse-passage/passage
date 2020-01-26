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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
 * The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the
 * <code>caseXXX</code> method for each class of the model, starting with the
 * actual class of the object and proceeding up the inheritance hierarchy until
 * a non-null result is returned, which is the result of the switch.
 * 
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage
 * @generated
 */
public class ProductsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ProductsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductsSwitch() {
		if (modelPackage == null) {
			modelPackage = ProductsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case ProductsPackage.PRODUCT_LINE_DESCRIPTOR: {
			ProductLineDescriptor productLineDescriptor = (ProductLineDescriptor) theEObject;
			T result = caseProductLineDescriptor(productLineDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT_DESCRIPTOR: {
			ProductDescriptor productDescriptor = (ProductDescriptor) theEObject;
			T result = caseProductDescriptor(productDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT_VERSION_DESCRIPTOR: {
			ProductVersionDescriptor productVersionDescriptor = (ProductVersionDescriptor) theEObject;
			T result = caseProductVersionDescriptor(productVersionDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT_VERSION_FEATURE_DESCRIPTOR: {
			ProductVersionFeatureDescriptor productVersionFeatureDescriptor = (ProductVersionFeatureDescriptor) theEObject;
			T result = caseProductVersionFeatureDescriptor(productVersionFeatureDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT_LINE: {
			ProductLine productLine = (ProductLine) theEObject;
			T result = caseProductLine(productLine);
			if (result == null)
				result = caseProductLineDescriptor(productLine);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT: {
			Product product = (Product) theEObject;
			T result = caseProduct(product);
			if (result == null)
				result = caseProductDescriptor(product);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT_VERSION: {
			ProductVersion productVersion = (ProductVersion) theEObject;
			T result = caseProductVersion(productVersion);
			if (result == null)
				result = caseProductVersionDescriptor(productVersion);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ProductsPackage.PRODUCT_VERSION_FEATURE: {
			ProductVersionFeature productVersionFeature = (ProductVersionFeature) theEObject;
			T result = caseProductVersionFeature(productVersionFeature);
			if (result == null)
				result = caseProductVersionFeatureDescriptor(productVersionFeature);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Line Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Line Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductLineDescriptor(ProductLineDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductDescriptor(ProductDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Version Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Version Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductVersionDescriptor(ProductVersionDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Version Feature Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Version Feature Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductVersionFeatureDescriptor(ProductVersionFeatureDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Line</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Line</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductLine(ProductLine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduct(Product object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Version</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Version</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductVersion(ProductVersion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Version Feature</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Version Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductVersionFeature(ProductVersionFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch, but this is the last case anyway.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // ProductsSwitch
