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
package org.eclipse.passage.lic.licenses.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
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
		default:
			return defaultCase(theEObject);
		}
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
