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
package org.eclipse.passage.lic.licenses.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.*;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the
 * <code>caseXXX</code> method for each class of the model, starting with the
 * actual class of the object and proceeding up the inheritance hierarchy until
 * a non-null result is returned, which is the result of the switch.
 * 
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage
 * @generated
 */
public class LicensesSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LicensesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * 
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
	 * 
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
	 * 
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
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
		case LicensesPackage.LICENSE_PACK_DESCRIPTOR: {
			LicensePackDescriptor licensePackDescriptor = (LicensePackDescriptor) theEObject;
			T result = caseLicensePackDescriptor(licensePackDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_GRANT_DESCRIPTOR: {
			LicenseGrantDescriptor licenseGrantDescriptor = (LicenseGrantDescriptor) theEObject;
			T result = caseLicenseGrantDescriptor(licenseGrantDescriptor);
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
		case LicensesPackage.LICENSE_PACK: {
			LicensePack licensePack = (LicensePack) theEObject;
			T result = caseLicensePack(licensePack);
			if (result == null)
				result = caseLicensePackDescriptor(licensePack);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case LicensesPackage.LICENSE_GRANT: {
			LicenseGrant licenseGrant = (LicenseGrant) theEObject;
			T result = caseLicenseGrant(licenseGrant);
			if (result == null)
				result = caseLicenseGrantDescriptor(licenseGrant);
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
		case LicensesPackage.LICENSE_REQUISITES: {
			LicenseRequisites licenseRequisites = (LicenseRequisites) theEObject;
			T result = caseLicenseRequisites(licenseRequisites);
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
		case LicensesPackage.FLOATING_SERVER: {
			FloatingServer floatingServer = (FloatingServer) theEObject;
			T result = caseFloatingServer(floatingServer);
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
		case LicensesPackage.FEATURE_GRANT: {
			FeatureGrant featureGrant = (FeatureGrant) theEObject;
			T result = caseFeatureGrant(featureGrant);
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
		case LicensesPackage.EVALUATION_INSTRUCTIONS: {
			EvaluationInstructions evaluationInstructions = (EvaluationInstructions) theEObject;
			T result = caseEvaluationInstructions(evaluationInstructions);
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
		case LicensesPackage.FLOATING_LICENSE_ACCESS: {
			FloatingLicenseAccess floatingLicenseAccess = (FloatingLicenseAccess) theEObject;
			T result = caseFloatingLicenseAccess(floatingLicenseAccess);
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
		default:
			return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>License Pack Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Pack Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensePackDescriptor(LicensePackDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Grant Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Grant Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicenseGrantDescriptor(LicenseGrantDescriptor object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>License Pack</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Pack</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicensePack(LicensePack object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Grant</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch.
	 * 
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Grant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicenseGrant(LicenseGrant object) {
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
	 * @generated
	 */
	public T caseLicenseRequisites(LicenseRequisites object) {
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
	 * @generated
	 */
	public T caseProductRef(ProductRef object) {
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
	 * @generated
	 */
	public T caseGrantAcqisition(GrantAcqisition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * 
	 * This implementation returns null; returning a non-null result will terminate
	 * the switch, but this is the last case anyway.
	 * 
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
