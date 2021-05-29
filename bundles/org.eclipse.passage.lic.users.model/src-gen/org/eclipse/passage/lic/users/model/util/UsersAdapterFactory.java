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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.users.ContactDescriptor;
import org.eclipse.passage.lic.users.LicenseOwnerDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserGroupDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

import org.eclipse.passage.lic.users.model.api.*;

import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage
 * @generated
 */
public class UsersAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UsersPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsersAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UsersPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UsersSwitch<Adapter> modelSwitch = new UsersSwitch<Adapter>() {
		@Override
		public Adapter caseContactDescriptor(ContactDescriptor object) {
			return createContactDescriptorAdapter();
		}

		@Override
		public Adapter caseLicenseOwnerDescriptor(LicenseOwnerDescriptor object) {
			return createLicenseOwnerDescriptorAdapter();
		}

		@Override
		public Adapter caseUserDescriptor(UserDescriptor object) {
			return createUserDescriptorAdapter();
		}

		@Override
		public Adapter caseUserGroupDescriptor(UserGroupDescriptor object) {
			return createUserGroupDescriptorAdapter();
		}

		@Override
		public Adapter caseUserOriginDescriptor(UserOriginDescriptor object) {
			return createUserOriginDescriptorAdapter();
		}

		@Override
		public Adapter caseContact(Contact object) {
			return createContactAdapter();
		}

		@Override
		public Adapter caseLicenseOwner(LicenseOwner object) {
			return createLicenseOwnerAdapter();
		}

		@Override
		public Adapter caseUser(User object) {
			return createUserAdapter();
		}

		@Override
		public Adapter caseUserGroup(UserGroup object) {
			return createUserGroupAdapter();
		}

		@Override
		public Adapter caseUserOrigin(UserOrigin object) {
			return createUserOriginAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.UserOriginDescriptor <em>User Origin Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.UserOriginDescriptor
	 * @generated
	 */
	public Adapter createUserOriginDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.ContactDescriptor <em>Contact Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.ContactDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createContactDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.LicenseOwnerDescriptor <em>License Owner Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.LicenseOwnerDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createLicenseOwnerDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.UserDescriptor <em>User Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.UserDescriptor
	 * @generated
	 */
	public Adapter createUserDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.UserGroupDescriptor <em>User Group Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.UserGroupDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createUserGroupDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.model.api.UserOrigin <em>User Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin
	 * @generated
	 */
	public Adapter createUserOriginAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.model.api.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.model.api.User
	 * @generated
	 */
	public Adapter createUserAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.model.api.UserGroup <em>User Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.model.api.UserGroup
	 * @since 2.0
	 * @generated
	 */
	public Adapter createUserGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.model.api.Contact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.model.api.Contact
	 * @since 2.0
	 * @generated
	 */
	public Adapter createContactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.users.model.api.LicenseOwner <em>License Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.users.model.api.LicenseOwner
	 * @since 2.0
	 * @generated
	 */
	public Adapter createLicenseOwnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //UsersAdapterFactory
