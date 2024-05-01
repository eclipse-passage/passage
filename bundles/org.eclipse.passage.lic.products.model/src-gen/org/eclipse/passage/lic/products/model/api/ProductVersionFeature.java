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
package org.eclipse.passage.lic.products.model.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * 
 * A representation of the model object '<em><b>Product Version
 * Feature</b></em>'.
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureVersion <em>Feature Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getRestrictionLevel <em>Restriction Level</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getProductVersion <em>Product Version</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersionFeature()
 * @model
 * @generated
 */
public interface ProductVersionFeature extends EObject {
	/**
	 * Returns the value of the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Identifier</em>' attribute.
	 * @see #setFeatureIdentifier(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersionFeature_FeatureIdentifier()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureIdentifier <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Identifier</em>' attribute.
	 * @see #getFeatureIdentifier()
	 * @generated
	 */
	void setFeatureIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Feature Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Version</em>' attribute.
	 * @see #setFeatureVersion(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersionFeature_FeatureVersion()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getFeatureVersion <em>Feature Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Version</em>' attribute.
	 * @see #getFeatureVersion()
	 * @generated
	 */
	void setFeatureVersion(String value);

	/**
	 * Returns the value of the '<em><b>Restriction Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Restriction Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Restriction Level</em>' attribute.
	 * @see #setRestrictionLevel(String)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersionFeature_RestrictionLevel()
	 * @model
	 * @generated
	 */
	String getRestrictionLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getRestrictionLevel <em>Restriction Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Restriction Level</em>' attribute.
	 * @see #getRestrictionLevel()
	 * @generated
	 */
	void setRestrictionLevel(String value);

	/**
	 * Returns the value of the '<em><b>Product Version</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.products.model.api.ProductVersion#getProductVersionFeatures <em>Product Version Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Version</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Version</em>' container reference.
	 * @see #setProductVersion(ProductVersion)
	 * @see org.eclipse.passage.lic.products.model.meta.ProductsPackage#getProductVersionFeature_ProductVersion()
	 * @see org.eclipse.passage.lic.products.model.api.ProductVersion#getProductVersionFeatures
	 * @model opposite="productVersionFeatures" required="true" transient="false"
	 * @generated
	 */
	ProductVersion getProductVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.products.model.api.ProductVersionFeature#getProductVersion <em>Product Version</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Version</em>' container reference.
	 * @see #getProductVersion()
	 * @generated
	 */
	void setProductVersion(ProductVersion value);

} // ProductVersionFeature
