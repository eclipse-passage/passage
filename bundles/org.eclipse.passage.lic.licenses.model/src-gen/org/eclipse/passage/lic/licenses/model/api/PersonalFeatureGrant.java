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
package org.eclipse.passage.lic.licenses.model.api;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License Grant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since 2.0
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getValid <em>Valid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getUserAuthentication <em>User Authentication</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getVivid <em>Vivid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getPack <em>Pack</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant()
 * @model superTypes="org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrantDescriptor"
 * @generated
 */
public interface PersonalFeatureGrant extends EObject, PersonalFeatureGrantDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_Identifier()
	 * @model id="true"
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' containment reference.
	 * @see #setFeature(FeatureRef)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_Feature()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	FeatureRef getFeature();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getFeature <em>Feature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' containment reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(FeatureRef value);

	/**
	 * Returns the value of the '<em><b>Valid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid</em>' containment reference.
	 * @see #setValid(ValidityPeriod)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_Valid()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	ValidityPeriod getValid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getValid <em>Valid</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' containment reference.
	 * @see #getValid()
	 * @generated
	 */
	void setValid(ValidityPeriod value);

	/**
	 * Returns the value of the '<em><b>User Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Authentication</em>' containment reference.
	 * @see #setUserAuthentication(EvaluationInstructions)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_UserAuthentication()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	EvaluationInstructions getUserAuthentication();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getUserAuthentication <em>User Authentication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Authentication</em>' containment reference.
	 * @see #getUserAuthentication()
	 * @generated
	 */
	void setUserAuthentication(EvaluationInstructions value);

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_Capacity()
	 * @model default="1"
	 * @generated
	 */
	@Override
	int getCapacity();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);

	/**
	 * Returns the value of the '<em><b>Vivid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vivid</em>' attribute.
	 * @see #setVivid(long)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_Vivid()
	 * @model required="true"
	 * @generated
	 */
	@Override
	long getVivid();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getVivid <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vivid</em>' attribute.
	 * @see #getVivid()
	 * @generated
	 */
	void setVivid(long value);

	/**
	 * Returns the value of the '<em><b>Pack</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getGrants <em>Grants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pack</em>' container reference.
	 * @see #setPack(PersonalLicensePack)
	 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage#getPersonalFeatureGrant_Pack()
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack#getGrants
	 * @model opposite="grants" required="true" transient="false"
	 * @generated
	 */
	@Override
	PersonalLicensePack getPack();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant#getPack <em>Pack</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pack</em>' container reference.
	 * @see #getPack()
	 * @generated
	 */
	void setPack(PersonalLicensePack value);

} // LicenseGrant
