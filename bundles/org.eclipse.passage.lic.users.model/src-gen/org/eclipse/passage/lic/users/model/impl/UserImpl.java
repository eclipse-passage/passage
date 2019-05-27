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
package org.eclipse.passage.lic.users.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model object '<em><b>User</b></em>'.
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getFullName <em>Full Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getPreferredConditionType <em>Preferred Condition Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getPreferredConditionExpression <em>Preferred Condition Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserImpl#getUserOrigin <em>User Origin</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UserImpl extends MinimalEObjectImpl.Container implements User {
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmail() <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getEmail()
	 * @generated
	 * @ordered
	 */
	protected static final String EMAIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEmail() <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getEmail()
	 * @generated
	 * @ordered
	 */
	protected String email = EMAIL_EDEFAULT;

	/**
	 * The default value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected String fullName = FULL_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferredConditionType() <em>Preferred Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredConditionType()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFERRED_CONDITION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredConditionType() <em>Preferred Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredConditionType()
	 * @generated
	 * @ordered
	 */
	protected String preferredConditionType = PREFERRED_CONDITION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferredConditionExpression() <em>Preferred Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredConditionExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFERRED_CONDITION_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredConditionExpression() <em>Preferred Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredConditionExpression()
	 * @generated
	 * @ordered
	 */
	protected String preferredConditionExpression = PREFERRED_CONDITION_EXPRESSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.eINSTANCE.getUser();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__IDENTIFIER, oldIdentifier,
					identifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEmail(String newEmail) {
		String oldEmail = email;
		email = newEmail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__EMAIL, oldEmail, email));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFullName() {
		return fullName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFullName(String newFullName) {
		String oldFullName = fullName;
		fullName = newFullName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__FULL_NAME, oldFullName, fullName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__DESCRIPTION, oldDescription,
					description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPreferredConditionType() {
		return preferredConditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPreferredConditionType(String newPreferredConditionType) {
		String oldPreferredConditionType = preferredConditionType;
		preferredConditionType = newPreferredConditionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__PREFERRED_CONDITION_TYPE,
					oldPreferredConditionType, preferredConditionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPreferredConditionExpression() {
		return preferredConditionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPreferredConditionExpression(String newPreferredConditionExpression) {
		String oldPreferredConditionExpression = preferredConditionExpression;
		preferredConditionExpression = newPreferredConditionExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__PREFERRED_CONDITION_EXPRESSION,
					oldPreferredConditionExpression, preferredConditionExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UserOrigin getUserOrigin() {
		if (eContainerFeatureID() != UsersPackage.USER__USER_ORIGIN)
			return null;
		return (UserOrigin) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserOrigin(UserOrigin newUserOrigin, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newUserOrigin, UsersPackage.USER__USER_ORIGIN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUserOrigin(UserOrigin newUserOrigin) {
		if (newUserOrigin != eInternalContainer()
				|| (eContainerFeatureID() != UsersPackage.USER__USER_ORIGIN && newUserOrigin != null)) {
			if (EcoreUtil.isAncestor(this, newUserOrigin))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUserOrigin != null)
				msgs = ((InternalEObject) newUserOrigin).eInverseAdd(this, UsersPackage.USER_ORIGIN__USERS,
						UserOrigin.class, msgs);
			msgs = basicSetUserOrigin(newUserOrigin, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER__USER_ORIGIN, newUserOrigin,
					newUserOrigin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UsersPackage.USER__USER_ORIGIN:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetUserOrigin((UserOrigin) otherEnd, msgs);
		default:
			return super.eInverseAdd(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UsersPackage.USER__USER_ORIGIN:
			return basicSetUserOrigin(null, msgs);
		default:
			return super.eInverseRemove(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case UsersPackage.USER__USER_ORIGIN:
			return eInternalContainer().eInverseRemove(this, UsersPackage.USER_ORIGIN__USERS, UserOrigin.class, msgs);
		default:
			return super.eBasicRemoveFromContainerFeature(msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UsersPackage.USER__IDENTIFIER:
			return getIdentifier();
		case UsersPackage.USER__EMAIL:
			return getEmail();
		case UsersPackage.USER__FULL_NAME:
			return getFullName();
		case UsersPackage.USER__DESCRIPTION:
			return getDescription();
		case UsersPackage.USER__PREFERRED_CONDITION_TYPE:
			return getPreferredConditionType();
		case UsersPackage.USER__PREFERRED_CONDITION_EXPRESSION:
			return getPreferredConditionExpression();
		case UsersPackage.USER__USER_ORIGIN:
			return getUserOrigin();
		default:
			return super.eGet(featureID, resolve, coreType);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UsersPackage.USER__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case UsersPackage.USER__EMAIL:
			setEmail((String) newValue);
			return;
		case UsersPackage.USER__FULL_NAME:
			setFullName((String) newValue);
			return;
		case UsersPackage.USER__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case UsersPackage.USER__PREFERRED_CONDITION_TYPE:
			setPreferredConditionType((String) newValue);
			return;
		case UsersPackage.USER__PREFERRED_CONDITION_EXPRESSION:
			setPreferredConditionExpression((String) newValue);
			return;
		case UsersPackage.USER__USER_ORIGIN:
			setUserOrigin((UserOrigin) newValue);
			return;
		default:
			super.eSet(featureID, newValue);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case UsersPackage.USER__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER__EMAIL:
			setEmail(EMAIL_EDEFAULT);
			return;
		case UsersPackage.USER__FULL_NAME:
			setFullName(FULL_NAME_EDEFAULT);
			return;
		case UsersPackage.USER__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case UsersPackage.USER__PREFERRED_CONDITION_TYPE:
			setPreferredConditionType(PREFERRED_CONDITION_TYPE_EDEFAULT);
			return;
		case UsersPackage.USER__PREFERRED_CONDITION_EXPRESSION:
			setPreferredConditionExpression(PREFERRED_CONDITION_EXPRESSION_EDEFAULT);
			return;
		case UsersPackage.USER__USER_ORIGIN:
			setUserOrigin((UserOrigin) null);
			return;
		default:
			super.eUnset(featureID);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case UsersPackage.USER__IDENTIFIER:
			return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
		case UsersPackage.USER__EMAIL:
			return EMAIL_EDEFAULT == null ? email != null : !EMAIL_EDEFAULT.equals(email);
		case UsersPackage.USER__FULL_NAME:
			return FULL_NAME_EDEFAULT == null ? fullName != null : !FULL_NAME_EDEFAULT.equals(fullName);
		case UsersPackage.USER__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		case UsersPackage.USER__PREFERRED_CONDITION_TYPE:
			return PREFERRED_CONDITION_TYPE_EDEFAULT == null ? preferredConditionType != null
					: !PREFERRED_CONDITION_TYPE_EDEFAULT.equals(preferredConditionType);
		case UsersPackage.USER__PREFERRED_CONDITION_EXPRESSION:
			return PREFERRED_CONDITION_EXPRESSION_EDEFAULT == null ? preferredConditionExpression != null
					: !PREFERRED_CONDITION_EXPRESSION_EDEFAULT.equals(preferredConditionExpression);
		case UsersPackage.USER__USER_ORIGIN:
			return getUserOrigin() != null;
		default:
			return super.eIsSet(featureID);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
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
		result.append(", email: "); //$NON-NLS-1$
		result.append(email);
		result.append(", fullName: "); //$NON-NLS-1$
		result.append(fullName);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", preferredConditionType: "); //$NON-NLS-1$
		result.append(preferredConditionType);
		result.append(", preferredConditionExpression: "); //$NON-NLS-1$
		result.append(preferredConditionExpression);
		result.append(')');
		return result.toString();
	}

} // UserImpl
