/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.model.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Feature
 * Grant</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getValid <em>Valid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getVivid <em>Vivid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getPack <em>Pack</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant()
 * @model
 * @generated
 */
public interface FeatureGrant extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Identifier()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getIdentifier
	 * <em>Identifier</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Feature</em>' attribute.
	 * @see #setFeature(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Feature()
	 * @model required="true"
	 * @generated
	 */
	String getFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getFeature <em>Feature</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' attribute.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' reference.
	 * @see #setVersion(VersionMatch)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Version()
	 * @model
	 * @generated
	 */
	VersionMatch getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getVersion <em>Version</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' reference.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(VersionMatch value);

	/**
	 * Returns the value of the '<em><b>Valid</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Valid</em>' reference.
	 * @see #setValid(ValidityPeriod)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Valid()
	 * @model required="true"
	 * @generated
	 */
	ValidityPeriod getValid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getValid <em>Valid</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' reference.
	 * @see #getValid()
	 * @generated
	 */
	void setValid(ValidityPeriod value);

	/**
	 * Returns the value of the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vivid</em>' attribute.
	 * @see #setVivid(long)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Vivid()
	 * @model required="true"
	 * @generated
	 */
	long getVivid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getVivid <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vivid</em>' attribute.
	 * @see #getVivid()
	 * @generated
	 */
	void setVivid(long value);

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Capacity()
	 * @model required="true"
	 * @generated
	 */
	int getCapacity();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);

	/**
	 * Returns the value of the '<em><b>Pack</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the value of the '<em>Pack</em>' reference.
	 * @see #setPack(FloatingLicensePack)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFeatureGrant_Pack()
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getFeatures
	 * @model opposite="features" required="true"
	 * @generated
	 */
	FloatingLicensePack getPack();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getPack <em>Pack</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pack</em>' reference.
	 * @see #getPack()
	 * @generated
	 */
	void setPack(FloatingLicensePack value);

} // FeatureGrant
