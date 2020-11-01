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
package org.eclipse.passage.lic.floating.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.passage.lic.floating.model.api.*;
import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.api.UserGrant;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.floating.model.api.VersionMatch;
import org.eclipse.passage.lic.floating.model.meta.FloatingFactory;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class FloatingFactoryImpl extends EFactoryImpl implements FloatingFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static FloatingFactory init() {
		try {
			FloatingFactory theFloatingFactory = (FloatingFactory) EPackage.Registry.INSTANCE
					.getEFactory(FloatingPackage.eNS_URI);
			if (theFloatingFactory != null) {
				return theFloatingFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FloatingFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public FloatingFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case FloatingPackage.FLOATING_LICENSE_PACK:
			return createFloatingLicensePack();
		case FloatingPackage.LICENSE_REQUISITES:
			return createLicenseRequisites();
		case FloatingPackage.PRODUCT_REF:
			return createProductRef();
		case FloatingPackage.FLOATING_SERVER:
			return createFloatingServer();
		case FloatingPackage.USER_GRANT:
			return createUserGrant();
		case FloatingPackage.FEATURE_GRANT:
			return createFeatureGrant();
		case FloatingPackage.VALIDITY_PERIOD_CLOSED:
			return createValidityPeriodClosed();
		case FloatingPackage.EVALUATION_INSTRUCTIONS:
			return createEvaluationInstructions();
		case FloatingPackage.VERSION_MATCH:
			return createVersionMatch();
		case FloatingPackage.FLOATING_LICENSE_ACCESS:
			return createFloatingLicenseAccess();
		case FloatingPackage.FLOATING_SERVER_CONNECTION:
			return createFloatingServerConnection();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingLicensePack createFloatingLicensePack() {
		FloatingLicensePackImpl floatingLicensePack = new FloatingLicensePackImpl();
		return floatingLicensePack;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicenseRequisites createLicenseRequisites() {
		LicenseRequisitesImpl licenseRequisites = new LicenseRequisitesImpl();
		return licenseRequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductRef createProductRef() {
		ProductRefImpl productRef = new ProductRefImpl();
		return productRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingServer createFloatingServer() {
		FloatingServerImpl floatingServer = new FloatingServerImpl();
		return floatingServer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UserGrant createUserGrant() {
		UserGrantImpl userGrant = new UserGrantImpl();
		return userGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureGrant createFeatureGrant() {
		FeatureGrantImpl featureGrant = new FeatureGrantImpl();
		return featureGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidityPeriodClosed createValidityPeriodClosed() {
		ValidityPeriodClosedImpl validityPeriodClosed = new ValidityPeriodClosedImpl();
		return validityPeriodClosed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EvaluationInstructions createEvaluationInstructions() {
		EvaluationInstructionsImpl evaluationInstructions = new EvaluationInstructionsImpl();
		return evaluationInstructions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VersionMatch createVersionMatch() {
		VersionMatchImpl versionMatch = new VersionMatchImpl();
		return versionMatch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingLicenseAccess createFloatingLicenseAccess() {
		FloatingLicenseAccessImpl floatingLicenseAccess = new FloatingLicenseAccessImpl();
		return floatingLicenseAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingServerConnection createFloatingServerConnection() {
		FloatingServerConnectionImpl floatingServerConnection = new FloatingServerConnectionImpl();
		return floatingServerConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingPackage getFloatingPackage() {
		return (FloatingPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FloatingPackage getPackage() {
		return FloatingPackage.eINSTANCE;
	}

} // FloatingFactoryImpl
