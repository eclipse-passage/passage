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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Personal License Pack</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalLicensePackImpl#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.PersonalLicensePackImpl#getGrants <em>Grants</em>}</li>
 * </ul>
 *
 * @since 2.0
 * @generated
 */
public class PersonalLicensePackImpl extends MinimalEObjectImpl.Container implements PersonalLicensePack {
	/**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	protected PersonalLicenseRequisites license;

	/**
	 * The cached value of the '{@link #getGrants() <em>Grants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrants()
	 * @generated
	 * @ordered
	 */
	protected EList<PersonalFeatureGrant> grants;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonalLicensePackImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicensesPackage.eINSTANCE.getPersonalLicensePack();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonalLicenseRequisites getLicense() {
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLicense(PersonalLicenseRequisites newLicense, NotificationChain msgs) {
		PersonalLicenseRequisites oldLicense = license;
		license = newLicense;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE, oldLicense, newLicense);
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
	public void setLicense(PersonalLicenseRequisites newLicense) {
		if (newLicense != license) {
			NotificationChain msgs = null;
			if (license != null)
				msgs = ((InternalEObject) license).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE, null, msgs);
			if (newLicense != null)
				msgs = ((InternalEObject) newLicense).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE, null, msgs);
			msgs = basicSetLicense(newLicense, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE,
					newLicense, newLicense));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PersonalFeatureGrant> getGrants() {
		if (grants == null) {
			grants = new EObjectContainmentWithInverseEList<PersonalFeatureGrant>(PersonalFeatureGrant.class, this,
					LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS, LicensesPackage.PERSONAL_FEATURE_GRANT__PACK);
		}
		return grants;
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
		case LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getGrants()).basicAdd(otherEnd, msgs);
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
		case LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE:
			return basicSetLicense(null, msgs);
		case LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS:
			return ((InternalEList<?>) getGrants()).basicRemove(otherEnd, msgs);
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
		case LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE:
			return getLicense();
		case LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS:
			return getGrants();
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
		case LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE:
			setLicense((PersonalLicenseRequisites) newValue);
			return;
		case LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS:
			getGrants().clear();
			getGrants().addAll((Collection<? extends PersonalFeatureGrant>) newValue);
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
		case LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE:
			setLicense((PersonalLicenseRequisites) null);
			return;
		case LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS:
			getGrants().clear();
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
		case LicensesPackage.PERSONAL_LICENSE_PACK__LICENSE:
			return license != null;
		case LicensesPackage.PERSONAL_LICENSE_PACK__GRANTS:
			return grants != null && !grants.isEmpty();
		default:
			return super.eIsSet(featureID);
		}
	}

} //PersonalLicensePackImpl
