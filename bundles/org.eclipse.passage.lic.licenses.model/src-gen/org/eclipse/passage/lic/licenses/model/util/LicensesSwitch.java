/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
import org.eclipse.passage.lic.licenses.model.api.AgreementData;
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
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.api.SignatureAttribute;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.api.UserRef;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;
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
		case LicensesPackage.AGREEMENT_DATA: {
			AgreementData agreementData = (AgreementData) theEObject;
			T result = caseAgreementData(agreementData);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.COMPANY_REF: {
			CompanyRef companyRef = (CompanyRef) theEObject;
			T result = caseCompanyRef(companyRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.EVALUATION_INSTRUCTIONS: {
			EvaluationInstructions evaluationInstructions = (EvaluationInstructions) theEObject;
			T result = caseEvaluationInstructions(evaluationInstructions);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FEATURE_GRANT: {
			FeatureGrant featureGrant = (FeatureGrant) theEObject;
			T result = caseFeatureGrant(featureGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FEATURE_REF: {
			FeatureRef featureRef = (FeatureRef) theEObject;
			T result = caseFeatureRef(featureRef);
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
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_LICENSE_REQUISITES: {
			FloatingLicenseRequisites floatingLicenseRequisites = (FloatingLicenseRequisites) theEObject;
			T result = caseFloatingLicenseRequisites(floatingLicenseRequisites);
			if (result == null)
				result = caseLicenseRequisites(floatingLicenseRequisites);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.FLOATING_SERVER: {
			FloatingServer floatingServer = (FloatingServer) theEObject;
			T result = caseFloatingServer(floatingServer);
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
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_PLAN_FEATURE: {
			LicensePlanFeature licensePlanFeature = (LicensePlanFeature) theEObject;
			T result = caseLicensePlanFeature(licensePlanFeature);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_REQUISITES: {
			LicenseRequisites licenseRequisites = (LicenseRequisites) theEObject;
			T result = caseLicenseRequisites(licenseRequisites);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_FEATURE_GRANT: {
			PersonalFeatureGrant personalFeatureGrant = (PersonalFeatureGrant) theEObject;
			T result = casePersonalFeatureGrant(personalFeatureGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PERSONAL_LICENSE_PACK: {
			PersonalLicensePack personalLicensePack = (PersonalLicensePack) theEObject;
			T result = casePersonalLicensePack(personalLicensePack);
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
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.PRODUCT_REF: {
			ProductRef productRef = (ProductRef) theEObject;
			T result = caseProductRef(productRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.SIGNATURE: {
			Signature signature = (Signature) theEObject;
			T result = caseSignature(signature);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.SIGNATURE_ATTRIBUTE: {
			SignatureAttribute signatureAttribute = (SignatureAttribute) theEObject;
			T result = caseSignatureAttribute(signatureAttribute);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.USER_GRANT: {
			UserGrant userGrant = (UserGrant) theEObject;
			T result = caseUserGrant(userGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.USER_REF: {
			UserRef userRef = (UserRef) theEObject;
			T result = caseUserRef(userRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VALIDITY_PERIOD: {
			ValidityPeriod validityPeriod = (ValidityPeriod) theEObject;
			T result = caseValidityPeriod(validityPeriod);
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
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.VERSION_MATCH: {
			VersionMatch versionMatch = (VersionMatch) theEObject;
			T result = caseVersionMatch(versionMatch);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
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

} // LicensesSwitch
