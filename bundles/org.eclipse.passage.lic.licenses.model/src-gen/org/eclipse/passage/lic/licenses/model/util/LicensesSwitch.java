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
package org.eclipse.passage.lic.licenses.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.passage.lic.licenses.AgreementDataDescriptor;
import org.eclipse.passage.lic.licenses.CompanyRefDescriptor;
import org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor;
import org.eclipse.passage.lic.licenses.FeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.FeatureRefDescriptor;
import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.FloatingServerDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.ProductRefDescriptor;
import org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor;
import org.eclipse.passage.lic.licenses.SignatureDescriptor;
import org.eclipse.passage.lic.licenses.UserGrantDescriptor;
import org.eclipse.passage.lic.licenses.UserRefDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor;
import org.eclipse.passage.lic.licenses.VersionMatchDescriptor;

import org.eclipse.passage.lic.licenses.model.api.*;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage
 * @generated
 */
public class LicensesSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LicensesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicensesSwitch() {
		if (modelPackage == null) {
			modelPackage = LicensesPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case LicensesPackage.AGREEMENT_DATA_DESCRIPTOR: {
			AgreementDataDescriptor agreementDataDescriptor = (AgreementDataDescriptor) theEObject;
			T result = caseAgreementDataDescriptor(agreementDataDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.COMPANY_REF_DESCRIPTOR: {
			CompanyRefDescriptor companyRefDescriptor = (CompanyRefDescriptor) theEObject;
			T result = caseCompanyRefDescriptor(companyRefDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.EVALUATION_INSTRUCTIONS_DESCRIPTOR: {
			EvaluationInstructionsDescriptor evaluationInstructionsDescriptor = (EvaluationInstructionsDescriptor) theEObject;
			T result = caseEvaluationInstructionsDescriptor(evaluationInstructionsDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FEATURE_GRANT_DESCRIPTOR: {
			FeatureGrantDescriptor featureGrantDescriptor = (FeatureGrantDescriptor) theEObject;
			T result = caseFeatureGrantDescriptor(featureGrantDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FEATURE_REF_DESCRIPTOR: {
			FeatureRefDescriptor featureRefDescriptor = (FeatureRefDescriptor) theEObject;
			T result = caseFeatureRefDescriptor(featureRefDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_LICENSE_PACK_DESCRIPTOR: {
			FloatingLicensePackDescriptor floatingLicensePackDescriptor = (FloatingLicensePackDescriptor) theEObject;
			T result = caseFloatingLicensePackDescriptor(floatingLicensePackDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_LICENSE_REQUISITES_DESCRIPTOR: {
			FloatingLicenseRequisitesDescriptor floatingLicenseRequisitesDescriptor = (FloatingLicenseRequisitesDescriptor) theEObject;
			T result = caseFloatingLicenseRequisitesDescriptor(floatingLicenseRequisitesDescriptor);
			if (result == null)
				result = caseLicenseRequisitesDescriptor(floatingLicenseRequisitesDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_SERVER_DESCRIPTOR: {
			FloatingServerDescriptor floatingServerDescriptor = (FloatingServerDescriptor) theEObject;
			T result = caseFloatingServerDescriptor(floatingServerDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_PLAN_DESCRIPTOR: {
			LicensePlanDescriptor licensePlanDescriptor = (LicensePlanDescriptor) theEObject;
			T result = caseLicensePlanDescriptor(licensePlanDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_PLAN_FEATURE_DESCRIPTOR: {
			LicensePlanFeatureDescriptor licensePlanFeatureDescriptor = (LicensePlanFeatureDescriptor) theEObject;
			T result = caseLicensePlanFeatureDescriptor(licensePlanFeatureDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_REQUISITES_DESCRIPTOR: {
			LicenseRequisitesDescriptor licenseRequisitesDescriptor = (LicenseRequisitesDescriptor) theEObject;
			T result = caseLicenseRequisitesDescriptor(licenseRequisitesDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_FEATURE_GRANT_DESCRIPTOR: {
			PersonalFeatureGrantDescriptor personalFeatureGrantDescriptor = (PersonalFeatureGrantDescriptor) theEObject;
			T result = casePersonalFeatureGrantDescriptor(personalFeatureGrantDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_LICENSE_PACK_DESCRIPTOR: {
			PersonalLicensePackDescriptor personalLicensePackDescriptor = (PersonalLicensePackDescriptor) theEObject;
			T result = casePersonalLicensePackDescriptor(personalLicensePackDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_LICENSE_REQUISITES_DESCRIPTOR: {
			PersonalLicenseRequisitesDescriptor personalLicenseRequisitesDescriptor = (PersonalLicenseRequisitesDescriptor) theEObject;
			T result = casePersonalLicenseRequisitesDescriptor(personalLicenseRequisitesDescriptor);
			if (result == null)
				result = caseLicenseRequisitesDescriptor(personalLicenseRequisitesDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PRODUCT_REF_DESCRIPTOR: {
			ProductRefDescriptor productRefDescriptor = (ProductRefDescriptor) theEObject;
			T result = caseProductRefDescriptor(productRefDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.SIGNATURE_ATTRIBUTE_DESCRIPTOR: {
			SignatureAttributeDescriptor signatureAttributeDescriptor = (SignatureAttributeDescriptor) theEObject;
			T result = caseSignatureAttributeDescriptor(signatureAttributeDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.SIGNATURE_DESCRIPTOR: {
			SignatureDescriptor signatureDescriptor = (SignatureDescriptor) theEObject;
			T result = caseSignatureDescriptor(signatureDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.USER_GRANT_DESCRIPTOR: {
			UserGrantDescriptor userGrantDescriptor = (UserGrantDescriptor) theEObject;
			T result = caseUserGrantDescriptor(userGrantDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.USER_REF_DESCRIPTOR: {
			UserRefDescriptor userRefDescriptor = (UserRefDescriptor) theEObject;
			T result = caseUserRefDescriptor(userRefDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VALIDITY_PERIOD_CLOSED_DESCRIPTOR: {
			ValidityPeriodClosedDescriptor validityPeriodClosedDescriptor = (ValidityPeriodClosedDescriptor) theEObject;
			T result = caseValidityPeriodClosedDescriptor(validityPeriodClosedDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VALIDITY_PERIOD_DESCRIPTOR: {
			ValidityPeriodDescriptor validityPeriodDescriptor = (ValidityPeriodDescriptor) theEObject;
			T result = caseValidityPeriodDescriptor(validityPeriodDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VERSION_MATCH_DESCRIPTOR: {
			VersionMatchDescriptor versionMatchDescriptor = (VersionMatchDescriptor) theEObject;
			T result = caseVersionMatchDescriptor(versionMatchDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.AGREEMENT_DATA: {
			AgreementData agreementData = (AgreementData) theEObject;
			T result = caseAgreementData(agreementData);
			if (result == null)
				result = caseAgreementDataDescriptor(agreementData);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.COMPANY_REF: {
			CompanyRef companyRef = (CompanyRef) theEObject;
			T result = caseCompanyRef(companyRef);
			if (result == null)
				result = caseCompanyRefDescriptor(companyRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.EVALUATION_INSTRUCTIONS: {
			EvaluationInstructions evaluationInstructions = (EvaluationInstructions) theEObject;
			T result = caseEvaluationInstructions(evaluationInstructions);
			if (result == null)
				result = caseEvaluationInstructionsDescriptor(evaluationInstructions);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FEATURE_GRANT: {
			FeatureGrant featureGrant = (FeatureGrant) theEObject;
			T result = caseFeatureGrant(featureGrant);
			if (result == null)
				result = caseFeatureGrantDescriptor(featureGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FEATURE_REF: {
			FeatureRef featureRef = (FeatureRef) theEObject;
			T result = caseFeatureRef(featureRef);
			if (result == null)
				result = caseFeatureRefDescriptor(featureRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_LICENSE_ACCESS: {
			FloatingLicenseAccess floatingLicenseAccess = (FloatingLicenseAccess) theEObject;
			T result = caseFloatingLicenseAccess(floatingLicenseAccess);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_LICENSE_PACK: {
			FloatingLicensePack floatingLicensePack = (FloatingLicensePack) theEObject;
			T result = caseFloatingLicensePack(floatingLicensePack);
			if (result == null)
				result = caseFloatingLicensePackDescriptor(floatingLicensePack);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_LICENSE_REQUISITES: {
			FloatingLicenseRequisites floatingLicenseRequisites = (FloatingLicenseRequisites) theEObject;
			T result = caseFloatingLicenseRequisites(floatingLicenseRequisites);
			if (result == null)
				result = caseLicenseRequisites(floatingLicenseRequisites);
			if (result == null)
				result = caseFloatingLicenseRequisitesDescriptor(floatingLicenseRequisites);
			if (result == null)
				result = caseLicenseRequisitesDescriptor(floatingLicenseRequisites);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_SERVER: {
			FloatingServer floatingServer = (FloatingServer) theEObject;
			T result = caseFloatingServer(floatingServer);
			if (result == null)
				result = caseFloatingServerDescriptor(floatingServer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_SERVER_CONNECTION: {
			FloatingServerConnection floatingServerConnection = (FloatingServerConnection) theEObject;
			T result = caseFloatingServerConnection(floatingServerConnection);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.GRANT_ACQISITION: {
			GrantAcqisition grantAcqisition = (GrantAcqisition) theEObject;
			T result = caseGrantAcqisition(grantAcqisition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_PLAN: {
			LicensePlan licensePlan = (LicensePlan) theEObject;
			T result = caseLicensePlan(licensePlan);
			if (result == null)
				result = caseLicensePlanDescriptor(licensePlan);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_PLAN_FEATURE: {
			LicensePlanFeature licensePlanFeature = (LicensePlanFeature) theEObject;
			T result = caseLicensePlanFeature(licensePlanFeature);
			if (result == null)
				result = caseLicensePlanFeatureDescriptor(licensePlanFeature);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_REQUISITES: {
			LicenseRequisites licenseRequisites = (LicenseRequisites) theEObject;
			T result = caseLicenseRequisites(licenseRequisites);
			if (result == null)
				result = caseLicenseRequisitesDescriptor(licenseRequisites);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_FEATURE_GRANT: {
			PersonalFeatureGrant personalFeatureGrant = (PersonalFeatureGrant) theEObject;
			T result = casePersonalFeatureGrant(personalFeatureGrant);
			if (result == null)
				result = casePersonalFeatureGrantDescriptor(personalFeatureGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_LICENSE_PACK: {
			PersonalLicensePack personalLicensePack = (PersonalLicensePack) theEObject;
			T result = casePersonalLicensePack(personalLicensePack);
			if (result == null)
				result = casePersonalLicensePackDescriptor(personalLicensePack);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_LICENSE_REQUISITES: {
			PersonalLicenseRequisites personalLicenseRequisites = (PersonalLicenseRequisites) theEObject;
			T result = casePersonalLicenseRequisites(personalLicenseRequisites);
			if (result == null)
				result = caseLicenseRequisites(personalLicenseRequisites);
			if (result == null)
				result = casePersonalLicenseRequisitesDescriptor(personalLicenseRequisites);
			if (result == null)
				result = caseLicenseRequisitesDescriptor(personalLicenseRequisites);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PRODUCT_REF: {
			ProductRef productRef = (ProductRef) theEObject;
			T result = caseProductRef(productRef);
			if (result == null)
				result = caseProductRefDescriptor(productRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.SIGNATURE: {
			Signature signature = (Signature) theEObject;
			T result = caseSignature(signature);
			if (result == null)
				result = caseSignatureDescriptor(signature);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.SIGNATURE_ATTRIBUTE: {
			SignatureAttribute signatureAttribute = (SignatureAttribute) theEObject;
			T result = caseSignatureAttribute(signatureAttribute);
			if (result == null)
				result = caseSignatureAttributeDescriptor(signatureAttribute);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.USER_GRANT: {
			UserGrant userGrant = (UserGrant) theEObject;
			T result = caseUserGrant(userGrant);
			if (result == null)
				result = caseUserGrantDescriptor(userGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.USER_REF: {
			UserRef userRef = (UserRef) theEObject;
			T result = caseUserRef(userRef);
			if (result == null)
				result = caseUserRefDescriptor(userRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VALIDITY_PERIOD: {
			ValidityPeriod validityPeriod = (ValidityPeriod) theEObject;
			T result = caseValidityPeriod(validityPeriod);
			if (result == null)
				result = caseValidityPeriodDescriptor(validityPeriod);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VALIDITY_PERIOD_CLOSED: {
			ValidityPeriodClosed validityPeriodClosed = (ValidityPeriodClosed) theEObject;
			T result = caseValidityPeriodClosed(validityPeriodClosed);
			if (result == null)
				result = caseValidityPeriod(validityPeriodClosed);
			if (result == null)
				result = caseValidityPeriodClosedDescriptor(validityPeriodClosed);
			if (result == null)
				result = caseValidityPeriodDescriptor(validityPeriodClosed);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VERSION_MATCH: {
			VersionMatch versionMatch = (VersionMatch) theEObject;
			T result = caseVersionMatch(versionMatch);
			if (result == null)
				result = caseVersionMatchDescriptor(versionMatch);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agreement Data Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agreement Data Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.1
	 * @generated
	 */
	public T caseAgreementDataDescriptor(AgreementDataDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Plan Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Plan Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensePlanDescriptor(LicensePlanDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Plan Feature Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Plan Feature Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensePlanFeatureDescriptor(LicensePlanFeatureDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Personal License Pack Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Personal License Pack Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T casePersonalLicensePackDescriptor(PersonalLicensePackDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Ref Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Ref Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseProductRefDescriptor(ProductRefDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Ref Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Ref Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFeatureRefDescriptor(FeatureRefDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Ref Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Ref Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseUserRefDescriptor(UserRefDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Company Ref Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Company Ref Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseCompanyRefDescriptor(CompanyRefDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Requisites Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Requisites Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseLicenseRequisitesDescriptor(LicenseRequisitesDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Personal Feature Grant Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Personal Feature Grant Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T casePersonalFeatureGrantDescriptor(PersonalFeatureGrantDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Personal License Requisites Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Personal License Requisites Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T casePersonalLicenseRequisitesDescriptor(PersonalLicenseRequisitesDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating License Requisites Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating License Requisites Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingLicenseRequisitesDescriptor(FloatingLicenseRequisitesDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validity Period Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validity Period Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseValidityPeriodDescriptor(ValidityPeriodDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validity Period Closed Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validity Period Closed Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseValidityPeriodClosedDescriptor(ValidityPeriodClosedDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating License Pack Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating License Pack Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingLicensePackDescriptor(FloatingLicensePackDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating Server Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating Server Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingServerDescriptor(FloatingServerDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Grant Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Grant Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseUserGrantDescriptor(UserGrantDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Grant Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Grant Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFeatureGrantDescriptor(FeatureGrantDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Evaluation Instructions Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Evaluation Instructions Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseEvaluationInstructionsDescriptor(EvaluationInstructionsDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Version Match Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Version Match Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseVersionMatchDescriptor(VersionMatchDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agreement Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agreement Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.1
	 * @generated
	 */
	public T caseAgreementData(AgreementData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signature Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signature Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseSignatureDescriptor(SignatureDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signature Attribute Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signature Attribute Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseSignatureAttributeDescriptor(SignatureAttributeDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensePlan(LicensePlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Plan Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Plan Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensePlanFeature(LicensePlanFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Personal Feature Grant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Personal Feature Grant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T casePersonalFeatureGrant(PersonalFeatureGrant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Personal License Pack</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Personal License Pack</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T casePersonalLicensePack(PersonalLicensePack object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating License Pack</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating License Pack</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingLicensePack(FloatingLicensePack object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Requisites</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Requisites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.1
	 * @generated
	 */
	public T caseLicenseRequisites(LicenseRequisites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Personal License Requisites</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Personal License Requisites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T casePersonalLicenseRequisites(PersonalLicenseRequisites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating License Requisites</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating License Requisites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingLicenseRequisites(FloatingLicenseRequisites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseProductRef(ProductRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFeatureRef(FeatureRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseUserRef(UserRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Company Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Company Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseCompanyRef(CompanyRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingServer(FloatingServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Grant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Grant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseUserGrant(UserGrant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Grant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Grant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFeatureGrant(FeatureGrant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validity Period</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validity Period</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseValidityPeriod(ValidityPeriod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validity Period Closed</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validity Period Closed</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseValidityPeriodClosed(ValidityPeriodClosed object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Evaluation Instructions</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Evaluation Instructions</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseEvaluationInstructions(EvaluationInstructions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Version Match</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Version Match</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseVersionMatch(VersionMatch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating License Access</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating License Access</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingLicenseAccess(FloatingLicenseAccess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating Server Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating Server Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseFloatingServerConnection(FloatingServerConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Grant Acqisition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Grant Acqisition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseGrantAcqisition(GrantAcqisition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseSignature(Signature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signature Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signature Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseSignatureAttribute(SignatureAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //LicensesSwitch
