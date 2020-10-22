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
package org.eclipse.passage.lic.floating.model.meta;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.api.UserGrant;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.floating.model.api.VersionMatch;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage
 * @generated
 */
public interface FloatingFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	FloatingFactory eINSTANCE = org.eclipse.passage.lic.floating.model.impl.FloatingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>License Pack</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>License Pack</em>'.
	 * @generated
	 */
	FloatingLicensePack createFloatingLicensePack();

	/**
	 * Returns a new object of class '<em>License Requisites</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>License Requisites</em>'.
	 * @generated
	 */
	LicenseRequisites createLicenseRequisites();

	/**
	 * Returns a new object of class '<em>Product Ref</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Product Ref</em>'.
	 * @generated
	 */
	ProductRef createProductRef();

	/**
	 * Returns a new object of class '<em>Server</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>Server</em>'.
	 * @generated
	 */
	FloatingServer createFloatingServer();

	/**
	 * Returns a new object of class '<em>User Grant</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>User Grant</em>'.
	 * @generated
	 */
	UserGrant createUserGrant();

	/**
	 * Returns a new object of class '<em>Feature Grant</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Feature Grant</em>'.
	 * @generated
	 */
	FeatureGrant createFeatureGrant();

	/**
	 * Returns a new object of class '<em>Validity Period Closed</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Validity Period Closed</em>'.
	 * @generated
	 */
	ValidityPeriodClosed createValidityPeriodClosed();

	/**
	 * Returns a new object of class '<em>Evaluation Instructions</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Evaluation Instructions</em>'.
	 * @generated
	 */
	EvaluationInstructions createEvaluationInstructions();

	/**
	 * Returns a new object of class '<em>Version Match</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Version Match</em>'.
	 * @generated
	 */
	VersionMatch createVersionMatch();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	FloatingPackage getFloatingPackage();

} // FloatingFactory
