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
package org.eclipse.passage.lic.licenses.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.passage.lic.licenses.model.api.*;
import org.eclipse.passage.lic.licenses.model.api.CompanyRef;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FeatureRef;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.FloatingServer;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.api.SignatureAttribute;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.api.UserRef;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class LicensesFactoryImpl extends EFactoryImpl implements LicensesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
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
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public LicensesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case LicensesPackage.AGREEMENT_DATA:
			return createAgreementData();
		case LicensesPackage.COMPANY_REF:
			return createCompanyRef();
		case LicensesPackage.EVALUATION_INSTRUCTIONS:
			return createEvaluationInstructions();
		case LicensesPackage.FEATURE_GRANT:
			return createFeatureGrant();
		case LicensesPackage.FEATURE_REF:
			return createFeatureRef();
		case LicensesPackage.FLOATING_LICENSE_ACCESS:
			return createFloatingLicenseAccess();
		case LicensesPackage.FLOATING_LICENSE_PACK:
			return createFloatingLicensePack();
		case LicensesPackage.FLOATING_LICENSE_REQUISITES:
			return createFloatingLicenseRequisites();
		case LicensesPackage.FLOATING_SERVER:
			return createFloatingServer();
		case LicensesPackage.FLOATING_SERVER_CONNECTION:
			return createFloatingServerConnection();
		case LicensesPackage.GRANT_ACQISITION:
			return createGrantAcqisition();
		case LicensesPackage.LICENSE_PLAN:
			return createLicensePlan();
		case LicensesPackage.LICENSE_PLAN_FEATURE:
			return createLicensePlanFeature();
		case LicensesPackage.PERSONAL_FEATURE_GRANT:
			return createPersonalFeatureGrant();
		case LicensesPackage.PERSONAL_LICENSE_PACK:
			return createPersonalLicensePack();
		case LicensesPackage.PERSONAL_LICENSE_REQUISITES:
			return createPersonalLicenseRequisites();
		case LicensesPackage.PRODUCT_REF:
			return createProductRef();
		case LicensesPackage.SIGNATURE:
			return createSignature();
		case LicensesPackage.SIGNATURE_ATTRIBUTE:
			return createSignatureAttribute();
		case LicensesPackage.USER_GRANT:
			return createUserGrant();
		case LicensesPackage.USER_REF:
			return createUserRef();
		case LicensesPackage.VALIDITY_PERIOD_CLOSED:
			return createValidityPeriodClosed();
		case LicensesPackage.VERSION_MATCH:
			return createVersionMatch();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	@Override
	public AgreementData createAgreementData() {
		AgreementDataImpl agreementData = new AgreementDataImpl();
		return agreementData;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensePlan createLicensePlan() {
		LicensePlanImpl licensePlan = new LicensePlanImpl();
		return licensePlan;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensePlanFeature createLicensePlanFeature() {
		LicensePlanFeatureImpl licensePlanFeature = new LicensePlanFeatureImpl();
		return licensePlanFeature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public PersonalFeatureGrant createPersonalFeatureGrant() {
		PersonalFeatureGrantImpl personalFeatureGrant = new PersonalFeatureGrantImpl();
		return personalFeatureGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public PersonalLicensePack createPersonalLicensePack() {
		PersonalLicensePackImpl personalLicensePack = new PersonalLicensePackImpl();
		return personalLicensePack;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FloatingLicensePack createFloatingLicensePack() {
		FloatingLicensePackImpl floatingLicensePack = new FloatingLicensePackImpl();
		return floatingLicensePack;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public PersonalLicenseRequisites createPersonalLicenseRequisites() {
		PersonalLicenseRequisitesImpl personalLicenseRequisites = new PersonalLicenseRequisitesImpl();
		return personalLicenseRequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FloatingLicenseRequisites createFloatingLicenseRequisites() {
		FloatingLicenseRequisitesImpl floatingLicenseRequisites = new FloatingLicenseRequisitesImpl();
		return floatingLicenseRequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public ProductRef createProductRef() {
		ProductRefImpl productRef = new ProductRefImpl();
		return productRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FeatureRef createFeatureRef() {
		FeatureRefImpl featureRef = new FeatureRefImpl();
		return featureRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public UserRef createUserRef() {
		UserRefImpl userRef = new UserRefImpl();
		return userRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public CompanyRef createCompanyRef() {
		CompanyRefImpl companyRef = new CompanyRefImpl();
		return companyRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FloatingServer createFloatingServer() {
		FloatingServerImpl floatingServer = new FloatingServerImpl();
		return floatingServer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public UserGrant createUserGrant() {
		UserGrantImpl userGrant = new UserGrantImpl();
		return userGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FeatureGrant createFeatureGrant() {
		FeatureGrantImpl featureGrant = new FeatureGrantImpl();
		return featureGrant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public ValidityPeriodClosed createValidityPeriodClosed() {
		ValidityPeriodClosedImpl validityPeriodClosed = new ValidityPeriodClosedImpl();
		return validityPeriodClosed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EvaluationInstructions createEvaluationInstructions() {
		EvaluationInstructionsImpl evaluationInstructions = new EvaluationInstructionsImpl();
		return evaluationInstructions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public VersionMatch createVersionMatch() {
		VersionMatchImpl versionMatch = new VersionMatchImpl();
		return versionMatch;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FloatingLicenseAccess createFloatingLicenseAccess() {
		FloatingLicenseAccessImpl floatingLicenseAccess = new FloatingLicenseAccessImpl();
		return floatingLicenseAccess;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FloatingServerConnection createFloatingServerConnection() {
		FloatingServerConnectionImpl floatingServerConnection = new FloatingServerConnectionImpl();
		return floatingServerConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public GrantAcqisition createGrantAcqisition() {
		GrantAcqisitionImpl grantAcqisition = new GrantAcqisitionImpl();
		return grantAcqisition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public Signature createSignature() {
		SignatureImpl signature = new SignatureImpl();
		return signature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public SignatureAttribute createSignatureAttribute() {
		SignatureAttributeImpl signatureAttribute = new SignatureAttributeImpl();
		return signatureAttribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensesPackage getLicensesPackage() {
		return (LicensesPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LicensesPackage getPackage() {
		return LicensesPackage.eINSTANCE;
	}

} // LicensesFactoryImpl
