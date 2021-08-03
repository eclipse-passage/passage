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
package org.eclipse.passage.lic.features.model.api;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.features.FeatureSetDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureSet()
 * @model superTypes="org.eclipse.passage.lic.features.model.api.FeatureSetDescriptor"
 * @generated
 */
public interface FeatureSet extends EObject, FeatureSetDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureSet_Identifier()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureSet_Name()
	 * @model
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getName <em>Name</em>}' attribute.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureSet_Description()
	 * @model
	 * @generated
	 */
	@Override
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.features.model.api.Feature}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.features.model.api.Feature#getFeatureSet <em>Feature Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureSet_Features()
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getFeatureSet
	 * @model opposite="featureSet" containment="true"
	 * @generated
	 */
	@Override
	EList<Feature> getFeatures();

} // FeatureSet
