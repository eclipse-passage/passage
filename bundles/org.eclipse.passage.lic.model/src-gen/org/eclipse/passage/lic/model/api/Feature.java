/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.model.api;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.registry.FeatureDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.api.Feature#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.Feature#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.Feature#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.Feature#getFeatureSet <em>Feature Set</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.Feature#getFeatureVersions <em>Feature Versions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.model.meta.LicPackage#getFeature()
 * @model superTypes="org.eclipse.passage.lic.model.api.FeatureDescriptor"
 * @generated
 */
public interface Feature extends EObject, FeatureDescriptor {
  /**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getFeature_Name()
	 * @model
	 * @generated
	 */
  String getName();

  /**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.Feature#getName <em>Name</em>}' attribute.
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
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getFeature_Description()
	 * @model
	 * @generated
	 */
  String getDescription();

  /**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.Feature#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
  void setDescription(String value);

  /**
	 * Returns the value of the '<em><b>Feature Set</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.model.api.FeatureSet#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Set</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Set</em>' container reference.
	 * @see #setFeatureSet(FeatureSet)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getFeature_FeatureSet()
	 * @see org.eclipse.passage.lic.model.api.FeatureSet#getFeatures
	 * @model opposite="features" required="true" transient="false"
	 * @generated
	 */
	FeatureSet getFeatureSet();

		/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.Feature#getFeatureSet <em>Feature Set</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Set</em>' container reference.
	 * @see #getFeatureSet()
	 * @generated
	 */
	void setFeatureSet(FeatureSet value);

		/**
	 * Returns the value of the '<em><b>Feature Versions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.model.api.FeatureVersion}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.model.api.FeatureVersion#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Versions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Versions</em>' containment reference list.
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getFeature_FeatureVersions()
	 * @see org.eclipse.passage.lic.model.api.FeatureVersion#getFeature
	 * @model opposite="feature" containment="true"
	 * @generated
	 */
	EList<FeatureVersion> getFeatureVersions();

		/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getFeature_Identifier()
	 * @model required="true"
	 * @generated
	 */
  String getIdentifier();

  /**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.Feature#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
  void setIdentifier(String value);

} // Component
