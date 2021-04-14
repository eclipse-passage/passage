/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.FloatingServer;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model <b>Factory</b>.
 * 
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class LicensesFactoryImpl extends EFactoryImpl implements LicensesFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static LicensesFactory init() {
		try {
			LicensesFactory theLicensesFactory = (LicensesFactory) EPackage.Registry.INSTANCE
					.getEFactory(LicensesPackage.eNS_URI);
			if (theLicensesFactory != null) {
				return theLicensesFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LicensesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public LicensesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case LicensesPackage.LICENSE_PLAN:
			return createLicensePlan();
		case LicensesPackage.LICENSE_PLAN_FEATURE:
			return createLicensePlanFeature();
		case LicensesPackage.LICENSE_PACK:
			return createLicensePack();
		case LicensesPackage.LICENSE_GRANT:
			return createLicenseGrant();
		case LicensesPackage.FLOATING_LICENSE_PACK:
			return createFloatingLicensePack();
		case LicensesPackage.PERSONAL_LICENSE_REQUISITES:
			return createPersonalLicenseRequisites();
		case LicensesPackage.FLOATING_LICENSE_REQUISITES:
			return createFloatingLicenseRequisites();
		case LicensesPackage.PRODUCT_REF:
			return createProductRef();
		case LicensesPackage.FLOATING_SERVER:
			return createFloatingServer();
		case LicensesPackage.USER_GRANT:
			return createUserGrant();
		case LicensesPackage.FEATURE_GRANT:
			return createFeatureGrant();
		case LicensesPackage.VALIDITY_PERIOD_CLOSED:
			return createValidityPeriodClosed();
		case LicensesPackage.EVALUATION_INSTRUCTIONS:
			return createEvaluationInstructions();
		case LicensesPackage.VERSION_MATCH:
			return createVersionMatch();
		case LicensesPackage.FLOATING_LICENSE_ACCESS:
			return createFloatingLicenseAccess();
		case LicensesPackage.FLOATING_SERVER_CONNECTION:
			return createFloatingServerConnection();
		case LicensesPackage.GRANT_ACQISITION:
			return createGrantAcqisition();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public LicensePlan createLicensePlan() {
		LicensePlanImpl licensePlan = new LicensePlanImpl();
		return licensePlan;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public LicensePlanFeature createLicensePlanFeature() {
		LicensePlanFeatureImpl licensePlanFeature = new LicensePlanFeatureImpl();
		return licensePlanFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public LicensePack createLicensePack() {
		LicensePackImpl licensePack = new LicensePackImpl();
		return licensePack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public LicenseGrant createLicenseGrant() {
		LicenseGrantImpl licenseGrant = new LicenseGrantImpl();
		return licenseGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FloatingLicensePack createFloatingLicensePack() {
		FloatingLicensePackImpl floatingLicensePack = new FloatingLicensePackImpl();
		return floatingLicensePack;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PersonalLicenseRequisites createPersonalLicenseRequisites() {
		PersonalLicenseRequisitesImpl personalLicenseRequisites = new PersonalLicenseRequisitesImpl();
		return personalLicenseRequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FloatingLicenseRequisites createFloatingLicenseRequisites() {
		FloatingLicenseRequisitesImpl floatingLicenseRequisites = new FloatingLicenseRequisitesImpl();
		return floatingLicenseRequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ProductRef createProductRef() {
		ProductRefImpl productRef = new ProductRefImpl();
		return productRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FloatingServer createFloatingServer() {
		FloatingServerImpl floatingServer = new FloatingServerImpl();
		return floatingServer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public UserGrant createUserGrant() {
		UserGrantImpl userGrant = new UserGrantImpl();
		return userGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureGrant createFeatureGrant() {
		FeatureGrantImpl featureGrant = new FeatureGrantImpl();
		return featureGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ValidityPeriodClosed createValidityPeriodClosed() {
		ValidityPeriodClosedImpl validityPeriodClosed = new ValidityPeriodClosedImpl();
		return validityPeriodClosed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EvaluationInstructions createEvaluationInstructions() {
		EvaluationInstructionsImpl evaluationInstructions = new EvaluationInstructionsImpl();
		return evaluationInstructions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public VersionMatch createVersionMatch() {
		VersionMatchImpl versionMatch = new VersionMatchImpl();
		return versionMatch;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FloatingLicenseAccess createFloatingLicenseAccess() {
		FloatingLicenseAccessImpl floatingLicenseAccess = new FloatingLicenseAccessImpl();
		return floatingLicenseAccess;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FloatingServerConnection createFloatingServerConnection() {
		FloatingServerConnectionImpl floatingServerConnection = new FloatingServerConnectionImpl();
		return floatingServerConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GrantAcqisition createGrantAcqisition() {
		GrantAcqisitionImpl grantAcqisition = new GrantAcqisitionImpl();
		return grantAcqisition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public LicensesPackage getLicensesPackage() {
		return (LicensesPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LicensesPackage getPackage() {
		return LicensesPackage.eINSTANCE;
	}

} // LicensesFactoryImpl
