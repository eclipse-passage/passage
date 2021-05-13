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
package org.eclipse.passage.lic.licenses.model.meta;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.passage.lic.licenses.model.api.CompanyRef;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.FloatingServer;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.api.UserRef;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage
 * @generated
 */
public interface LicensesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LicensesFactory eINSTANCE = org.eclipse.passage.lic.licenses.model.impl.LicensesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>License Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>License Plan</em>'.
	 * @generated
	 */
	LicensePlan createLicensePlan();

	/**
	 * Returns a new object of class '<em>License Plan Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>License Plan Feature</em>'.
	 * @generated
	 */
	LicensePlanFeature createLicensePlanFeature();

	/**
	 * Returns a new object of class '<em>Personal License Pack</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Personal License Pack</em>'.
	 * @generated
	 */
	PersonalLicensePack createPersonalLicensePack();

	/**
	 * Returns a new object of class '<em>License Grant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>License Grant</em>'.
	 * @generated
	 */
	LicenseGrant createLicenseGrant();

	/**
	 * Returns a new object of class '<em>Floating License Pack</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating License Pack</em>'.
	 * @generated
	 */
	FloatingLicensePack createFloatingLicensePack();

	/**
	 * Returns a new object of class '<em>Personal License Requisites</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Personal License Requisites</em>'.
	 * @generated
	 */
	PersonalLicenseRequisites createPersonalLicenseRequisites();

	/**
	 * Returns a new object of class '<em>Floating License Requisites</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating License Requisites</em>'.
	 * @generated
	 */
	FloatingLicenseRequisites createFloatingLicenseRequisites();

	/**
	 * Returns a new object of class '<em>Product Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Ref</em>'.
	 * @generated
	 */
	ProductRef createProductRef();

	/**
	 * Returns a new object of class '<em>User Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Ref</em>'.
	 * @generated
	 */
	UserRef createUserRef();

	/**
	 * Returns a new object of class '<em>Company Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Company Ref</em>'.
	 * @generated
	 */
	CompanyRef createCompanyRef();

	/**
	 * Returns a new object of class '<em>Floating Server</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating Server</em>'.
	 * @generated
	 */
	FloatingServer createFloatingServer();

	/**
	 * Returns a new object of class '<em>User Grant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Grant</em>'.
	 * @generated
	 */
	UserGrant createUserGrant();

	/**
	 * Returns a new object of class '<em>Feature Grant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Grant</em>'.
	 * @generated
	 */
	FeatureGrant createFeatureGrant();

	/**
	 * Returns a new object of class '<em>Validity Period Closed</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Validity Period Closed</em>'.
	 * @generated
	 */
	ValidityPeriodClosed createValidityPeriodClosed();

	/**
	 * Returns a new object of class '<em>Evaluation Instructions</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Evaluation Instructions</em>'.
	 * @generated
	 */
	EvaluationInstructions createEvaluationInstructions();

	/**
	 * Returns a new object of class '<em>Version Match</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Version Match</em>'.
	 * @generated
	 */
	VersionMatch createVersionMatch();

	/**
	 * Returns a new object of class '<em>Floating License Access</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating License Access</em>'.
	 * @generated
	 */
	FloatingLicenseAccess createFloatingLicenseAccess();

	/**
	 * Returns a new object of class '<em>Floating Server Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating Server Connection</em>'.
	 * @generated
	 */
	FloatingServerConnection createFloatingServerConnection();

	/**
	 * Returns a new object of class '<em>Grant Acqisition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grant Acqisition</em>'.
	 * @generated
	 */
	GrantAcqisition createGrantAcqisition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	LicensesPackage getLicensesPackage();

} //LicensesFactory
