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

import org.eclipse.passage.lic.licenses.model.api.CompanyRef;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Floating License Requisites</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseRequisitesImpl#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseRequisitesImpl#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @since 2.0
 * @generated
 */
public class FloatingLicenseRequisitesImpl extends LicenseRequisitesImpl implements FloatingLicenseRequisites {
	/**
	 * The cached value of the '{@link #getCompany() <em>Company</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompany()
	 * @generated
	 * @ordered
	 */
	protected CompanyRef company;

	/**
	 * The default value of the '{@link #getGroup() <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	private String group = GROUP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatingLicenseRequisitesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicensesPackage.eINSTANCE.getFloatingLicenseRequisites();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompanyRef getCompany() {
		return company;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompany(CompanyRef newCompany, NotificationChain msgs) {
		CompanyRef oldCompany = company;
		company = newCompany;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY, oldCompany, newCompany);
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
	public void setCompany(CompanyRef newCompany) {
		if (newCompany != company) {
			NotificationChain msgs = null;
			if (company != null)
				msgs = ((InternalEObject) company).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY, null, msgs);
			if (newCompany != null)
				msgs = ((InternalEObject) newCompany).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY, null, msgs);
			msgs = basicSetCompany(newCompany, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY,
					newCompany, newCompany));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGroup() {
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGroup(String newGroup) {
		String oldGroup = group;
		group = newGroup;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.FLOATING_LICENSE_REQUISITES__GROUP,
					oldGroup, group));
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
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY:
			return basicSetCompany(null, msgs);
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
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY:
			return getCompany();
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__GROUP:
			return getGroup();
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
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY:
			setCompany((CompanyRef) newValue);
			return;
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__GROUP:
			setGroup((String) newValue);
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
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY:
			setCompany((CompanyRef) null);
			return;
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__GROUP:
			setGroup(GROUP_EDEFAULT);
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
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY:
			return company != null;
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__GROUP:
			return !Objects.equals(GROUP_EDEFAULT, group);
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
		result.append(" (group: "); //$NON-NLS-1$
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //FloatingLicenseRequisitesImpl
