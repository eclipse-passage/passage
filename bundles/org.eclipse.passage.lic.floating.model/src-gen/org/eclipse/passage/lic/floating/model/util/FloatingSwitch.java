/**
 */
package org.eclipse.passage.lic.floating.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.passage.lic.floating.model.api.*;

import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

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
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage
 * @generated
 */
public class FloatingSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FloatingPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingSwitch() {
		if (modelPackage == null) {
			modelPackage = FloatingPackage.eINSTANCE;
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
		case FloatingPackage.FLOATING_LICENSE_PACK: {
			FloatingLicensePack floatingLicensePack = (FloatingLicensePack) theEObject;
			T result = caseFloatingLicensePack(floatingLicensePack);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.LICENSE_REQUISITES: {
			LicenseRequisites licenseRequisites = (LicenseRequisites) theEObject;
			T result = caseLicenseRequisites(licenseRequisites);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.PRODUCT_REF: {
			ProductRef productRef = (ProductRef) theEObject;
			T result = caseProductRef(productRef);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.FLOATING_SERVER: {
			FloatingServer floatingServer = (FloatingServer) theEObject;
			T result = caseFloatingServer(floatingServer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.USER_GRANT: {
			UserGrant userGrant = (UserGrant) theEObject;
			T result = caseUserGrant(userGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.FEATURE_GRANT: {
			FeatureGrant featureGrant = (FeatureGrant) theEObject;
			T result = caseFeatureGrant(featureGrant);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.VALIDITY_PERIOD: {
			ValidityPeriod validityPeriod = (ValidityPeriod) theEObject;
			T result = caseValidityPeriod(validityPeriod);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.VALIDITY_PERIOD_CLOSED: {
			ValidityPeriodClosed validityPeriodClosed = (ValidityPeriodClosed) theEObject;
			T result = caseValidityPeriodClosed(validityPeriodClosed);
			if (result == null)
				result = caseValidityPeriod(validityPeriodClosed);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.EVALUATION_INSTRUCTIONS: {
			EvaluationInstructions evaluationInstructions = (EvaluationInstructions) theEObject;
			T result = caseEvaluationInstructions(evaluationInstructions);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case FloatingPackage.VERSION_MATCH: {
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
	 * Returns the result of interpreting the object as an instance of '<em>License Pack</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Pack</em>'.
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
	 * Returns the result of interpreting the object as an instance of '<em>Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Server</em>'.
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

} //FloatingSwitch
