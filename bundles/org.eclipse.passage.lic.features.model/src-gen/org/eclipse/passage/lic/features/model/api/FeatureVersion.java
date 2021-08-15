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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.features.FeatureVersionDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getNews <em>News</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureVersion()
 * @model superTypes="org.eclipse.passage.lic.features.model.api.FeatureVersionDescriptor"
 * @generated
 */
public interface FeatureVersion extends EObject, FeatureVersionDescriptor {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureVersion_Version()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.features.model.api.Feature#getFeatureVersions <em>Feature Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' container reference.
	 * @see #setFeature(Feature)
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureVersion_Feature()
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getFeatureVersions
	 * @model opposite="featureVersions" required="true" transient="false"
	 * @generated
	 */
	@Override
	Feature getFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getFeature <em>Feature</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' container reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(Feature value);

	/**
	 * Returns the value of the '<em><b>News</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>News</em>' attribute.
	 * @see #setNews(String)
	 * @see org.eclipse.passage.lic.features.model.meta.FeaturesPackage#getFeatureVersion_News()
	 * @model
	 * @generated
	 */
	@Override
	String getNews();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getNews <em>News</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>News</em>' attribute.
	 * @see #getNews()
	 * @generated
	 */
	void setNews(String value);

} // FeatureVersion
