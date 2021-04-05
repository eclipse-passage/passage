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
package org.eclipse.passage.lic.shipments.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.passage.lic.shipments.model.api.FloatingLicense;

import org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Floating License</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.impl.FloatingLicenseImpl#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.impl.FloatingLicenseImpl#getLicense <em>License</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatingLicenseImpl extends FloatingLicenseDescriptorImpl implements FloatingLicense {
	/**
	 * The default value of the '{@link #getCompany() <em>Company</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompany()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPANY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompany() <em>Company</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompany()
	 * @generated
	 * @ordered
	 */
	private String company = COMPANY_EDEFAULT;

	/**
	 * The default value of the '{@link #getLicense() <em>License</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	protected static final String LICENSE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	private String license = LICENSE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatingLicenseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShipmentsPackage.eINSTANCE.getFloatingLicense();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCompany() {
		return company;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompany(String newCompany) {
		String oldCompany = company;
		company = newCompany;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ShipmentsPackage.FLOATING_LICENSE__COMPANY,
					oldCompany, company));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLicense() {
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLicense(String newLicense) {
		String oldLicense = license;
		license = newLicense;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ShipmentsPackage.FLOATING_LICENSE__LICENSE,
					oldLicense, license));
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
		case ShipmentsPackage.FLOATING_LICENSE__COMPANY:
			return getCompany();
		case ShipmentsPackage.FLOATING_LICENSE__LICENSE:
			return getLicense();
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
		case ShipmentsPackage.FLOATING_LICENSE__COMPANY:
			setCompany((String) newValue);
			return;
		case ShipmentsPackage.FLOATING_LICENSE__LICENSE:
			setLicense((String) newValue);
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
		case ShipmentsPackage.FLOATING_LICENSE__COMPANY:
			setCompany(COMPANY_EDEFAULT);
			return;
		case ShipmentsPackage.FLOATING_LICENSE__LICENSE:
			setLicense(LICENSE_EDEFAULT);
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
		case ShipmentsPackage.FLOATING_LICENSE__COMPANY:
			return !Objects.equals(COMPANY_EDEFAULT, company);
		case ShipmentsPackage.FLOATING_LICENSE__LICENSE:
			return !Objects.equals(LICENSE_EDEFAULT, license);
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
		result.append(" (company: "); //$NON-NLS-1$
		result.append(company);
		result.append(", license: "); //$NON-NLS-1$
		result.append(license);
		result.append(')');
		return result.toString();
	}

} //FloatingLicenseImpl
