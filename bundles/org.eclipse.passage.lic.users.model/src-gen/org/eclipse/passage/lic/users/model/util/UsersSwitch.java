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
package org.eclipse.passage.lic.users.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.passage.lic.users.ContactDescriptor;
import org.eclipse.passage.lic.users.LicenseOwnerDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserGroupDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

import org.eclipse.passage.lic.users.model.api.*;

import org.eclipse.passage.lic.users.model.meta.UsersPackage;

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
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage
 * @generated
 */
public class UsersSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UsersPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsersSwitch() {
		if (modelPackage == null) {
			modelPackage = UsersPackage.eINSTANCE;
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
		case UsersPackage.CONTACT_DESCRIPTOR: {
			ContactDescriptor contactDescriptor = (ContactDescriptor) theEObject;
			T result = caseContactDescriptor(contactDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.LICENSE_OWNER_DESCRIPTOR: {
			LicenseOwnerDescriptor licenseOwnerDescriptor = (LicenseOwnerDescriptor) theEObject;
			T result = caseLicenseOwnerDescriptor(licenseOwnerDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.USER_DESCRIPTOR: {
			UserDescriptor userDescriptor = (UserDescriptor) theEObject;
			T result = caseUserDescriptor(userDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.USER_GROUP_DESCRIPTOR: {
			UserGroupDescriptor userGroupDescriptor = (UserGroupDescriptor) theEObject;
			T result = caseUserGroupDescriptor(userGroupDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.USER_ORIGIN_DESCRIPTOR: {
			UserOriginDescriptor userOriginDescriptor = (UserOriginDescriptor) theEObject;
			T result = caseUserOriginDescriptor(userOriginDescriptor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.CONTACT: {
			Contact contact = (Contact) theEObject;
			T result = caseContact(contact);
			if (result == null)
				result = caseContactDescriptor(contact);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.LICENSE_OWNER: {
			LicenseOwner licenseOwner = (LicenseOwner) theEObject;
			T result = caseLicenseOwner(licenseOwner);
			if (result == null)
				result = caseLicenseOwnerDescriptor(licenseOwner);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.USER: {
			User user = (User) theEObject;
			T result = caseUser(user);
			if (result == null)
				result = caseUserDescriptor(user);
			if (result == null)
				result = caseLicenseOwner(user);
			if (result == null)
				result = caseLicenseOwnerDescriptor(user);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.USER_GROUP: {
			UserGroup userGroup = (UserGroup) theEObject;
			T result = caseUserGroup(userGroup);
			if (result == null)
				result = caseUserGroupDescriptor(userGroup);
			if (result == null)
				result = caseLicenseOwner(userGroup);
			if (result == null)
				result = caseLicenseOwnerDescriptor(userGroup);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case UsersPackage.USER_ORIGIN: {
			UserOrigin userOrigin = (UserOrigin) theEObject;
			T result = caseUserOrigin(userOrigin);
			if (result == null)
				result = caseUserOriginDescriptor(userOrigin);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Origin Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Origin Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserOriginDescriptor(UserOriginDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contact Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contact Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseContactDescriptor(ContactDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Owner Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Owner Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseLicenseOwnerDescriptor(LicenseOwnerDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserDescriptor(UserDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Group Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Group Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseUserGroupDescriptor(UserGroupDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Origin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Origin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserOrigin(UserOrigin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUser(User object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseUserGroup(UserGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseContact(Contact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License Owner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.0
	 * @generated
	 */
	public T caseLicenseOwner(LicenseOwner object) {
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

} //UsersSwitch
