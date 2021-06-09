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
package org.eclipse.passage.lic.users.model.impl;

import java.util.Collection;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserGroup;
import org.eclipse.passage.lic.users.model.api.UserOrigin;

import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Origin</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserOriginImpl#getGroups <em>Groups</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UserOriginImpl extends MinimalEObjectImpl.Container implements UserOrigin {
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	private String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	private String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	private String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUsers() <em>Users</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<User> users;

	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected EList<UserGroup> groups;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserOriginImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.eINSTANCE.getUserOrigin();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_ORIGIN__IDENTIFIER, oldIdentifier,
					identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_ORIGIN__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_ORIGIN__DESCRIPTION, oldDescription,
					description));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<User> getUsers() {
		if (users == null) {
			users = new EObjectContainmentWithInverseEList<User>(User.class, this, UsersPackage.USER_ORIGIN__USERS,
					UsersPackage.USER__ORIGIN);
		}
		return users;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public EList<UserGroup> getGroups() {
		if (groups == null) {
			groups = new EObjectContainmentWithInverseEList<UserGroup>(UserGroup.class, this,
					UsersPackage.USER_ORIGIN__GROUPS, UsersPackage.USER_GROUP__ORIGIN);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UsersPackage.USER_ORIGIN__USERS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getUsers()).basicAdd(otherEnd, msgs);
		case UsersPackage.USER_ORIGIN__GROUPS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getGroups()).basicAdd(otherEnd, msgs);
		default:
			return super.eInverseAdd(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UsersPackage.USER_ORIGIN__USERS:
			return ((InternalEList<?>) getUsers()).basicRemove(otherEnd, msgs);
		case UsersPackage.USER_ORIGIN__GROUPS:
			return ((InternalEList<?>) getGroups()).basicRemove(otherEnd, msgs);
		default:
			return super.eInverseRemove(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UsersPackage.USER_ORIGIN__IDENTIFIER:
			return getIdentifier();
		case UsersPackage.USER_ORIGIN__NAME:
			return getName();
		case UsersPackage.USER_ORIGIN__DESCRIPTION:
			return getDescription();
		case UsersPackage.USER_ORIGIN__USERS:
			return getUsers();
		case UsersPackage.USER_ORIGIN__GROUPS:
			return getGroups();
		default:
			return super.eGet(featureID, resolve, coreType);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UsersPackage.USER_ORIGIN__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case UsersPackage.USER_ORIGIN__NAME:
			setName((String) newValue);
			return;
		case UsersPackage.USER_ORIGIN__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UsersPackage.USER_ORIGIN__USERS:
			getUsers().clear();
			getUsers().addAll((Collection<? extends User>) newValue);
			return;
		case UsersPackage.USER_ORIGIN__GROUPS:
			getGroups().clear();
			getGroups().addAll((Collection<? extends UserGroup>) newValue);
			return;
		default:
			super.eSet(featureID, newValue);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case UsersPackage.USER_ORIGIN__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER_ORIGIN__NAME:
			setName(NAME_EDEFAULT);
			return;
		case UsersPackage.USER_ORIGIN__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UsersPackage.USER_ORIGIN__USERS:
			getUsers().clear();
			return;
		case UsersPackage.USER_ORIGIN__GROUPS:
			getGroups().clear();
			return;
		default:
			super.eUnset(featureID);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case UsersPackage.USER_ORIGIN__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case UsersPackage.USER_ORIGIN__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case UsersPackage.USER_ORIGIN__DESCRIPTION:
			return !Objects.equals(DESCRIPTION_EDEFAULT, description);
		case UsersPackage.USER_ORIGIN__USERS:
			return users != null && !users.isEmpty();
		case UsersPackage.USER_ORIGIN__GROUPS:
			return groups != null && !groups.isEmpty();
		default:
			return super.eIsSet(featureID);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}
		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (identifier: "); //$NON-NLS-1$
		result.append(identifier);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //UserOriginImpl
