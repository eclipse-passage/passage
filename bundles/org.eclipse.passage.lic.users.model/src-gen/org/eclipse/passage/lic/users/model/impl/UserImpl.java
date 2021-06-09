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

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.passage.lic.users.LicenseOwnerDescriptor;
import org.eclipse.passage.lic.users.model.api.Contact;
import org.eclipse.passage.lic.users.model.api.LicenseOwner;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;

import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getContact <em>Contact</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getPreferredEvaluationType <em>Preferred Evaluation Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getPreferredEvaluationExpression <em>Preferred Evaluation Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getOrigin <em>Origin</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UserImpl extends MinimalEObjectImpl.Container implements User {
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
	 * The default value of the '{@link #getPreferredEvaluationType() <em>Preferred Evaluation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredEvaluationType()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected static final String PREFERRED_EVALUATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredEvaluationType() <em>Preferred Evaluation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredEvaluationType()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	private String preferredEvaluationType = PREFERRED_EVALUATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferredEvaluationExpression() <em>Preferred Evaluation Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredEvaluationExpression()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected static final String PREFERRED_EVALUATION_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredEvaluationExpression() <em>Preferred Evaluation Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredEvaluationExpression()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	private String preferredEvaluationExpression = PREFERRED_EVALUATION_EXPRESSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.eINSTANCE.getUser();
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__IDENTIFIER, oldIdentifier,
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__DESCRIPTION, oldDescription,
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UsersPackage.USER__CONTACT,
					oldContact, newContact);
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
						EOPPOSITE_FEATURE_BASE - UsersPackage.USER__CONTACT, null, msgs);
			if (newContact != null)
				msgs = ((InternalEObject) newContact).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - UsersPackage.USER__CONTACT, null, msgs);
			msgs = basicSetContact(newContact, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__CONTACT, newContact, newContact));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public String getPreferredEvaluationType() {
		return preferredEvaluationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setPreferredEvaluationType(String newPreferredEvaluationType) {
		String oldPreferredEvaluationType = preferredEvaluationType;
		preferredEvaluationType = newPreferredEvaluationType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__PREFERRED_EVALUATION_TYPE,
					oldPreferredEvaluationType, preferredEvaluationType));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public String getPreferredEvaluationExpression() {
		return preferredEvaluationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setPreferredEvaluationExpression(String newPreferredEvaluationExpression) {
		String oldPreferredEvaluationExpression = preferredEvaluationExpression;
		preferredEvaluationExpression = newPreferredEvaluationExpression;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__PREFERRED_EVALUATION_EXPRESSION,
					oldPreferredEvaluationExpression, preferredEvaluationExpression));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public UserOrigin getOrigin() {
		if (eContainerFeatureID() != UsersPackage.USER__ORIGIN) {
			return null;
		}
		return (UserOrigin) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	public NotificationChain basicSetOrigin(UserOrigin newOrigin, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newOrigin, UsersPackage.USER__ORIGIN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setOrigin(UserOrigin newOrigin) {
		if (newOrigin != eInternalContainer()
				|| (eContainerFeatureID() != UsersPackage.USER__ORIGIN && newOrigin != null)) {
			if (EcoreUtil.isAncestor(this, newOrigin)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newOrigin != null)
				msgs = ((InternalEObject) newOrigin).eInverseAdd(this, UsersPackage.USER_ORIGIN__USERS,
						UserOrigin.class, msgs);
			msgs = basicSetOrigin(newOrigin, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__ORIGIN, newOrigin, newOrigin));
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
		case UsersPackage.USER__ORIGIN:
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
		case UsersPackage.USER__CONTACT:
			return basicSetContact(null, msgs);
		case UsersPackage.USER__ORIGIN:
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
		case UsersPackage.USER__ORIGIN:
			return eInternalContainer().eInverseRemove(this, UsersPackage.USER_ORIGIN__USERS, UserOrigin.class, msgs);
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
		case UsersPackage.USER__IDENTIFIER:
			return getIdentifier();
		case UsersPackage.USER__NAME:
			return getName();
		case UsersPackage.USER__DESCRIPTION:
			return getDescription();
		case UsersPackage.USER__CONTACT:
			return getContact();
		case UsersPackage.USER__PREFERRED_EVALUATION_TYPE:
			return getPreferredEvaluationType();
		case UsersPackage.USER__PREFERRED_EVALUATION_EXPRESSION:
			return getPreferredEvaluationExpression();
		case UsersPackage.USER__ORIGIN:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UsersPackage.USER__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case UsersPackage.USER__NAME:
			setName((String) newValue);
			return;
		case UsersPackage.USER__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UsersPackage.USER__CONTACT:
			setContact((Contact) newValue);
			return;
		case UsersPackage.USER__PREFERRED_EVALUATION_TYPE:
			setPreferredEvaluationType((String) newValue);
			return;
		case UsersPackage.USER__PREFERRED_EVALUATION_EXPRESSION:
			setPreferredEvaluationExpression((String) newValue);
			return;
		case UsersPackage.USER__ORIGIN:
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
		case UsersPackage.USER__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER__NAME:
			setName(NAME_EDEFAULT);
			return;
		case UsersPackage.USER__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UsersPackage.USER__CONTACT:
			setContact((Contact) null);
			return;
		case UsersPackage.USER__PREFERRED_EVALUATION_TYPE:
			setPreferredEvaluationType(PREFERRED_EVALUATION_TYPE_EDEFAULT);
			return;
		case UsersPackage.USER__PREFERRED_EVALUATION_EXPRESSION:
			setPreferredEvaluationExpression(PREFERRED_EVALUATION_EXPRESSION_EDEFAULT);
			return;
		case UsersPackage.USER__ORIGIN:
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
		case UsersPackage.USER__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case UsersPackage.USER__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case UsersPackage.USER__DESCRIPTION:
			return !Objects.equals(DESCRIPTION_EDEFAULT, description);
		case UsersPackage.USER__CONTACT:
			return contact != null;
		case UsersPackage.USER__PREFERRED_EVALUATION_TYPE:
			return !Objects.equals(PREFERRED_EVALUATION_TYPE_EDEFAULT, preferredEvaluationType);
		case UsersPackage.USER__PREFERRED_EVALUATION_EXPRESSION:
			return !Objects.equals(PREFERRED_EVALUATION_EXPRESSION_EDEFAULT, preferredEvaluationExpression);
		case UsersPackage.USER__ORIGIN:
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
			case UsersPackage.USER__IDENTIFIER:
				return UsersPackage.LICENSE_OWNER__IDENTIFIER;
			case UsersPackage.USER__NAME:
				return UsersPackage.LICENSE_OWNER__NAME;
			case UsersPackage.USER__DESCRIPTION:
				return UsersPackage.LICENSE_OWNER__DESCRIPTION;
			case UsersPackage.USER__CONTACT:
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
				return UsersPackage.USER__IDENTIFIER;
			case UsersPackage.LICENSE_OWNER__NAME:
				return UsersPackage.USER__NAME;
			case UsersPackage.LICENSE_OWNER__DESCRIPTION:
				return UsersPackage.USER__DESCRIPTION;
			case UsersPackage.LICENSE_OWNER__CONTACT:
				return UsersPackage.USER__CONTACT;
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
		result.append(", preferredEvaluationType: "); //$NON-NLS-1$
		result.append(preferredEvaluationType);
		result.append(", preferredEvaluationExpression: "); //$NON-NLS-1$
		result.append(preferredEvaluationExpression);
		result.append(')');
		return result.toString();
	}

} //UserImpl
