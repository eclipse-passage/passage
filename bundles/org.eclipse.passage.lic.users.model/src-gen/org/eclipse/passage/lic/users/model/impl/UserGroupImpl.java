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

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.users.LicenseOwnerDescriptor;

import org.eclipse.passage.lic.users.model.api.Contact;
import org.eclipse.passage.lic.users.model.api.LicenseOwner;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserGroup;
import org.eclipse.passage.lic.users.model.api.UserOrigin;

import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl#getContact <em>Contact</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserGroupImpl#getOrigin <em>Origin</em>}</li>
 * </ul>
 *
 * @since 2.0
 * @generated
 */
public class UserGroupImpl extends MinimalEObjectImpl.Container implements UserGroup {
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
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @since 2.0
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
	 * The cached value of the '{@link #getContact() <em>Contact</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContact()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected Contact contact;

	/**
	 * The cached value of the '{@link #getUsers() <em>Users</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<User> users;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.eINSTANCE.getUserGroup();
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_GROUP__IDENTIFIER, oldIdentifier,
					identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_GROUP__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_GROUP__DESCRIPTION, oldDescription,
					description));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public Contact getContact() {
		return contact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	public NotificationChain basicSetContact(Contact newContact, NotificationChain msgs) {
		Contact oldContact = contact;
		contact = newContact;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					UsersPackage.USER_GROUP__CONTACT, oldContact, newContact);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setContact(Contact newContact) {
		if (newContact != contact) {
			NotificationChain msgs = null;
			if (contact != null)
				msgs = ((InternalEObject) contact).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - UsersPackage.USER_GROUP__CONTACT, null, msgs);
			if (newContact != null)
				msgs = ((InternalEObject) newContact).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - UsersPackage.USER_GROUP__CONTACT, null, msgs);
			msgs = basicSetContact(newContact, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_GROUP__CONTACT, newContact,
					newContact));
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
			users = new EObjectResolvingEList<User>(User.class, this, UsersPackage.USER_GROUP__USERS);
		}
		return users;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UserOrigin getOrigin() {
		if (eContainerFeatureID() != UsersPackage.USER_GROUP__ORIGIN) {
			return null;
		}
		return (UserOrigin) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrigin(UserOrigin newOrigin, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newOrigin, UsersPackage.USER_GROUP__ORIGIN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrigin(UserOrigin newOrigin) {
		if (newOrigin != eInternalContainer()
				|| (eContainerFeatureID() != UsersPackage.USER_GROUP__ORIGIN && newOrigin != null)) {
			if (EcoreUtil.isAncestor(this, newOrigin)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newOrigin != null)
				msgs = ((InternalEObject) newOrigin).eInverseAdd(this, UsersPackage.USER_ORIGIN__GROUPS,
						UserOrigin.class, msgs);
			msgs = basicSetOrigin(newOrigin, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_GROUP__ORIGIN, newOrigin,
					newOrigin));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UsersPackage.USER_GROUP__ORIGIN:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetOrigin((UserOrigin) otherEnd, msgs);
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
		case UsersPackage.USER_GROUP__CONTACT:
			return basicSetContact(null, msgs);
		case UsersPackage.USER_GROUP__ORIGIN:
			return basicSetOrigin(null, msgs);
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case UsersPackage.USER_GROUP__ORIGIN:
			return eInternalContainer().eInverseRemove(this, UsersPackage.USER_ORIGIN__GROUPS, UserOrigin.class, msgs);
		default:
			return super.eBasicRemoveFromContainerFeature(msgs);
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
		case UsersPackage.USER_GROUP__IDENTIFIER:
			return getIdentifier();
		case UsersPackage.USER_GROUP__NAME:
			return getName();
		case UsersPackage.USER_GROUP__DESCRIPTION:
			return getDescription();
		case UsersPackage.USER_GROUP__CONTACT:
			return getContact();
		case UsersPackage.USER_GROUP__USERS:
			return getUsers();
		case UsersPackage.USER_GROUP__ORIGIN:
			return getOrigin();
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
		case UsersPackage.USER_GROUP__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case UsersPackage.USER_GROUP__NAME:
			setName((String) newValue);
			return;
		case UsersPackage.USER_GROUP__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UsersPackage.USER_GROUP__CONTACT:
			setContact((Contact) newValue);
			return;
		case UsersPackage.USER_GROUP__USERS:
			getUsers().clear();
			getUsers().addAll((Collection<? extends User>) newValue);
			return;
		case UsersPackage.USER_GROUP__ORIGIN:
			setOrigin((UserOrigin) newValue);
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
		case UsersPackage.USER_GROUP__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER_GROUP__NAME:
			setName(NAME_EDEFAULT);
			return;
		case UsersPackage.USER_GROUP__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UsersPackage.USER_GROUP__CONTACT:
			setContact((Contact) null);
			return;
		case UsersPackage.USER_GROUP__USERS:
			getUsers().clear();
			return;
		case UsersPackage.USER_GROUP__ORIGIN:
			setOrigin((UserOrigin) null);
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
		case UsersPackage.USER_GROUP__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case UsersPackage.USER_GROUP__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case UsersPackage.USER_GROUP__DESCRIPTION:
			return !Objects.equals(DESCRIPTION_EDEFAULT, description);
		case UsersPackage.USER_GROUP__CONTACT:
			return contact != null;
		case UsersPackage.USER_GROUP__USERS:
			return users != null && !users.isEmpty();
		case UsersPackage.USER_GROUP__ORIGIN:
			return getOrigin() != null;
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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == LicenseOwnerDescriptor.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == LicenseOwner.class) {
			switch (derivedFeatureID) {
			case UsersPackage.USER_GROUP__IDENTIFIER:
				return UsersPackage.LICENSE_OWNER__IDENTIFIER;
			case UsersPackage.USER_GROUP__NAME:
				return UsersPackage.LICENSE_OWNER__NAME;
			case UsersPackage.USER_GROUP__DESCRIPTION:
				return UsersPackage.LICENSE_OWNER__DESCRIPTION;
			case UsersPackage.USER_GROUP__CONTACT:
				return UsersPackage.LICENSE_OWNER__CONTACT;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == LicenseOwnerDescriptor.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == LicenseOwner.class) {
			switch (baseFeatureID) {
			case UsersPackage.LICENSE_OWNER__IDENTIFIER:
				return UsersPackage.USER_GROUP__IDENTIFIER;
			case UsersPackage.LICENSE_OWNER__NAME:
				return UsersPackage.USER_GROUP__NAME;
			case UsersPackage.LICENSE_OWNER__DESCRIPTION:
				return UsersPackage.USER_GROUP__DESCRIPTION;
			case UsersPackage.LICENSE_OWNER__CONTACT:
				return UsersPackage.USER_GROUP__CONTACT;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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

} //UserGroupImpl
