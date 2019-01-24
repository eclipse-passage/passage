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

import java.util.Date;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.registry.LicenseGrantDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>LicensePack Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getMatchVersion <em>Match Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getMatchRule <em>Match Rule</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getValidUntil <em>Valid Until</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.api.LicenseGrant#getLicensePack <em>License Pack</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant()
 * @model superTypes="org.eclipse.passage.lic.model.api.LicenseGrantDescriptor"
 * @generated
 */
public interface LicenseGrant extends EObject, LicenseGrantDescriptor {
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
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_FeatureIdentifier()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getFeatureIdentifier <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Identifier</em>' attribute.
	 * @see #getFeatureIdentifier()
	 * @generated
	 */
	void setFeatureIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Match Version</b></em>' attribute.
	 * The default value is <code>"0.0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Match Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Version</em>' attribute.
	 * @see #setMatchVersion(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_MatchVersion()
	 * @model default="0.0.0" required="true"
	 * @generated
	 */
	String getMatchVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getMatchVersion <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Version</em>' attribute.
	 * @see #getMatchVersion()
	 * @generated
	 */
	void setMatchVersion(String value);

	/**
	 * Returns the value of the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Match Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Rule</em>' attribute.
	 * @see #setMatchRule(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_MatchRule()
	 * @model
	 * @generated
	 */
	String getMatchRule();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getMatchRule <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Rule</em>' attribute.
	 * @see #getMatchRule()
	 * @generated
	 */
	void setMatchRule(String value);

	/**
	 * Returns the value of the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid From</em>' attribute.
	 * @see #setValidFrom(Date)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_ValidFrom()
	 * @model required="true"
	 * @generated
	 */
	Date getValidFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getValidFrom <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid From</em>' attribute.
	 * @see #getValidFrom()
	 * @generated
	 */
	void setValidFrom(Date value);

	/**
	 * Returns the value of the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid Until</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid Until</em>' attribute.
	 * @see #setValidUntil(Date)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_ValidUntil()
	 * @model required="true"
	 * @generated
	 */
	Date getValidUntil();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getValidUntil <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid Until</em>' attribute.
	 * @see #getValidUntil()
	 * @generated
	 */
	void setValidUntil(Date value);

	/**
	 * Returns the value of the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Type</em>' attribute.
	 * @see #setConditionType(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_ConditionType()
	 * @model required="true"
	 * @generated
	 */
	String getConditionType();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getConditionType <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Type</em>' attribute.
	 * @see #getConditionType()
	 * @generated
	 */
	void setConditionType(String value);

	/**
	 * Returns the value of the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Expression</em>' attribute.
	 * @see #setConditionExpression(String)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_ConditionExpression()
	 * @model required="true"
	 * @generated
	 */
	String getConditionExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getConditionExpression <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Expression</em>' attribute.
	 * @see #getConditionExpression()
	 * @generated
	 */
	void setConditionExpression(String value);

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_Capacity()
	 * @model default="1"
	 * @generated
	 */
	int getCapacity();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);

	/**
	 * Returns the value of the '<em><b>License Pack</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.model.api.LicensePack#getLicenseGrants <em>License Grants</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License Pack</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License Pack</em>' container reference.
	 * @see #setLicensePack(LicensePack)
	 * @see org.eclipse.passage.lic.model.meta.LicPackage#getLicenseGrant_LicensePack()
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getLicenseGrants
	 * @model opposite="licenseGrants" required="true" transient="false"
	 * @generated
	 */
	LicensePack getLicensePack();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getLicensePack <em>License Pack</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License Pack</em>' container reference.
	 * @see #getLicensePack()
	 * @generated
	 */
	void setLicensePack(LicensePack value);

} // LicenseGrant
