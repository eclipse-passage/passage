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
package org.eclipse.passage.lic.licenses.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FeatureRef;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>License Grant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getValid <em>Valid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getUserAuthentication <em>User Authentication</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getVivid <em>Vivid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalFeatureGrantImpl#getPack <em>Pack</em>}</li>
 * </ul>
 *
 * @since 2.0
 * @generated
 */
public class PersonalFeatureGrantImpl extends MinimalEObjectImpl.Container implements PersonalFeatureGrant {
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
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected FeatureRef feature;

	/**
	 * The cached value of the '{@link #getValid() <em>Valid</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValid()
	 * @generated
	 * @ordered
	 */
	protected ValidityPeriod valid;

	/**
	 * The cached value of the '{@link #getUserAuthentication() <em>User Authentication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserAuthentication()
	 * @generated
	 * @ordered
	 */
	protected EvaluationInstructions userAuthentication;

	/**
	 * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	protected static final int CAPACITY_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	private int capacity = CAPACITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getVivid() <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVivid()
	 * @generated
	 * @ordered
	 */
	protected static final long VIVID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getVivid() <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVivid()
	 * @generated
	 * @ordered
	 */
	private long vivid = VIVID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonalFeatureGrantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicensesPackage.eINSTANCE.getPersonalFeatureGrant();
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_FEATURE_GRANT__IDENTIFIER,
					oldIdentifier, identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureRef getFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeature(FeatureRef newFeature, NotificationChain msgs) {
		FeatureRef oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE, oldFeature, newFeature);
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
	 * @generated
	 */
	@Override
	public void setFeature(FeatureRef newFeature) {
		if (newFeature != feature) {
			NotificationChain msgs = null;
			if (feature != null)
				msgs = ((InternalEObject) feature).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE, null, msgs);
			if (newFeature != null)
				msgs = ((InternalEObject) newFeature).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE, null, msgs);
			msgs = basicSetFeature(newFeature, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE,
					newFeature, newFeature));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidityPeriod getValid() {
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValid(ValidityPeriod newValid, NotificationChain msgs) {
		ValidityPeriod oldValid = valid;
		valid = newValid;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.PERSONAL_FEATURE_GRANT__VALID, oldValid, newValid);
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
	 * @generated
	 */
	@Override
	public void setValid(ValidityPeriod newValid) {
		if (newValid != valid) {
			NotificationChain msgs = null;
			if (valid != null)
				msgs = ((InternalEObject) valid).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_FEATURE_GRANT__VALID, null, msgs);
			if (newValid != null)
				msgs = ((InternalEObject) newValid).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_FEATURE_GRANT__VALID, null, msgs);
			msgs = basicSetValid(newValid, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_FEATURE_GRANT__VALID,
					newValid, newValid));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EvaluationInstructions getUserAuthentication() {
		return userAuthentication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserAuthentication(EvaluationInstructions newUserAuthentication,
			NotificationChain msgs) {
		EvaluationInstructions oldUserAuthentication = userAuthentication;
		userAuthentication = newUserAuthentication;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION, oldUserAuthentication,
					newUserAuthentication);
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
	 * @generated
	 */
	@Override
	public void setUserAuthentication(EvaluationInstructions newUserAuthentication) {
		if (newUserAuthentication != userAuthentication) {
			NotificationChain msgs = null;
			if (userAuthentication != null)
				msgs = ((InternalEObject) userAuthentication).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION, null,
						msgs);
			if (newUserAuthentication != null)
				msgs = ((InternalEObject) newUserAuthentication).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION, null,
						msgs);
			msgs = basicSetUserAuthentication(newUserAuthentication, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION, newUserAuthentication,
					newUserAuthentication));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCapacity(int newCapacity) {
		int oldCapacity = capacity;
		capacity = newCapacity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_FEATURE_GRANT__CAPACITY,
					oldCapacity, capacity));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getVivid() {
		return vivid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVivid(long newVivid) {
		long oldVivid = vivid;
		vivid = newVivid;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_FEATURE_GRANT__VIVID,
					oldVivid, vivid));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonalLicensePack getPack() {
		if (eContainerFeatureID() != LicensesPackage.PERSONAL_FEATURE_GRANT__PACK) {
			return null;
		}
		return (PersonalLicensePack) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPack(PersonalLicensePack newPack, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPack, LicensesPackage.PERSONAL_FEATURE_GRANT__PACK, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPack(PersonalLicensePack newPack) {
		if (newPack != eInternalContainer()
				|| (eContainerFeatureID() != LicensesPackage.PERSONAL_FEATURE_GRANT__PACK && newPack != null)) {
			if (EcoreUtil.isAncestor(this, newPack)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newPack != null)
				msgs = ((InternalEObject) newPack).eInverseAdd(this, LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS,
						PersonalLicensePack.class, msgs);
			msgs = basicSetPack(newPack, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_FEATURE_GRANT__PACK, newPack,
					newPack));
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetPack((PersonalLicensePack) otherEnd, msgs);
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE:
			return basicSetFeature(null, msgs);
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VALID:
			return basicSetValid(null, msgs);
		case LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION:
			return basicSetUserAuthentication(null, msgs);
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			return basicSetPack(null, msgs);
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			return eInternalContainer().eInverseRemove(this, LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS,
					PersonalLicensePack.class, msgs);
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__IDENTIFIER:
			return getIdentifier();
		case LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE:
			return getFeature();
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VALID:
			return getValid();
		case LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION:
			return getUserAuthentication();
		case LicensesPackage.PERSONAL_FEATURE_GRANT__CAPACITY:
			return getCapacity();
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VIVID:
			return getVivid();
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			return getPack();
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE:
			setFeature((FeatureRef) newValue);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VALID:
			setValid((ValidityPeriod) newValue);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION:
			setUserAuthentication((EvaluationInstructions) newValue);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__CAPACITY:
			setCapacity((Integer) newValue);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VIVID:
			setVivid((Long) newValue);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			setPack((PersonalLicensePack) newValue);
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE:
			setFeature((FeatureRef) null);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VALID:
			setValid((ValidityPeriod) null);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION:
			setUserAuthentication((EvaluationInstructions) null);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__CAPACITY:
			setCapacity(CAPACITY_EDEFAULT);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VIVID:
			setVivid(VIVID_EDEFAULT);
			return;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			setPack((PersonalLicensePack) null);
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
		case LicensesPackage.PERSONAL_FEATURE_GRANT__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case LicensesPackage.PERSONAL_FEATURE_GRANT__FEATURE:
			return feature != null;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VALID:
			return valid != null;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__USER_AUTHENTICATION:
			return userAuthentication != null;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__CAPACITY:
			return capacity != CAPACITY_EDEFAULT;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__VIVID:
			return vivid != VIVID_EDEFAULT;
		case LicensesPackage.PERSONAL_FEATURE_GRANT__PACK:
			return getPack() != null;
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
		result.append(", capacity: "); //$NON-NLS-1$
		result.append(capacity);
		result.append(", vivid: "); //$NON-NLS-1$
		result.append(vivid);
		result.append(')');
		return result.toString();
	}

} //LicenseGrantImpl
