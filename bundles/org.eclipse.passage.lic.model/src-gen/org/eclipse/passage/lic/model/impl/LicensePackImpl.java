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
package org.eclipse.passage.lic.model.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.passage.lic.model.api.LicenseGrant;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>LicensePack</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.impl.LicensePackImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.LicensePackImpl#getIssueDate <em>Issue Date</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.LicensePackImpl#getProductIdentifier <em>Product Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.LicensePackImpl#getProductVersion <em>Product Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.LicensePackImpl#getUserIdentifier <em>User Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.LicensePackImpl#getLicenseGrants <em>License Grants</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LicensePackImpl extends MinimalEObjectImpl.Container implements LicensePack {
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
	protected String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getIssueDate() <em>Issue Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssueDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date ISSUE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIssueDate() <em>Issue Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssueDate()
	 * @generated
	 * @ordered
	 */
	protected Date issueDate = ISSUE_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductIdentifier() <em>Product Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductIdentifier() <em>Product Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String productIdentifier = PRODUCT_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductVersion() <em>Product Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductVersion() <em>Product Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductVersion()
	 * @generated
	 * @ordered
	 */
	protected String productVersion = PRODUCT_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserIdentifier() <em>User Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserIdentifier() <em>User Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String userIdentifier = USER_IDENTIFIER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLicenseGrants() <em>License Grants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicenseGrants()
	 * @generated
	 * @ordered
	 */
	protected EList<LicenseGrant> licenseGrants;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LicensePackImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicPackage.Literals.LICENSE_PACK;
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.LICENSE_PACK__IDENTIFIER, oldIdentifier, identifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIssueDate(Date newIssueDate) {
		Date oldIssueDate = issueDate;
		issueDate = newIssueDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.LICENSE_PACK__ISSUE_DATE, oldIssueDate, issueDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProductIdentifier() {
		return productIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductIdentifier(String newProductIdentifier) {
		String oldProductIdentifier = productIdentifier;
		productIdentifier = newProductIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.LICENSE_PACK__PRODUCT_IDENTIFIER, oldProductIdentifier, productIdentifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProductVersion() {
		return productVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductVersion(String newProductVersion) {
		String oldProductVersion = productVersion;
		productVersion = newProductVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.LICENSE_PACK__PRODUCT_VERSION, oldProductVersion, productVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUserIdentifier(String newUserIdentifier) {
		String oldUserIdentifier = userIdentifier;
		userIdentifier = newUserIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.LICENSE_PACK__USER_IDENTIFIER, oldUserIdentifier, userIdentifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LicenseGrant> getLicenseGrants() {
		if (licenseGrants == null) {
			licenseGrants = new EObjectContainmentWithInverseEList<LicenseGrant>(LicenseGrant.class, this, LicPackage.LICENSE_PACK__LICENSE_GRANTS, LicPackage.LICENSE_GRANT__LICENSE_PACK);
		}
		return licenseGrants;
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
			case LicPackage.LICENSE_PACK__LICENSE_GRANTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLicenseGrants()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LicPackage.LICENSE_PACK__LICENSE_GRANTS:
				return ((InternalEList<?>)getLicenseGrants()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LicPackage.LICENSE_PACK__IDENTIFIER:
				return getIdentifier();
			case LicPackage.LICENSE_PACK__ISSUE_DATE:
				return getIssueDate();
			case LicPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
				return getProductIdentifier();
			case LicPackage.LICENSE_PACK__PRODUCT_VERSION:
				return getProductVersion();
			case LicPackage.LICENSE_PACK__USER_IDENTIFIER:
				return getUserIdentifier();
			case LicPackage.LICENSE_PACK__LICENSE_GRANTS:
				return getLicenseGrants();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case LicPackage.LICENSE_PACK__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case LicPackage.LICENSE_PACK__ISSUE_DATE:
				setIssueDate((Date)newValue);
				return;
			case LicPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
				setProductIdentifier((String)newValue);
				return;
			case LicPackage.LICENSE_PACK__PRODUCT_VERSION:
				setProductVersion((String)newValue);
				return;
			case LicPackage.LICENSE_PACK__USER_IDENTIFIER:
				setUserIdentifier((String)newValue);
				return;
			case LicPackage.LICENSE_PACK__LICENSE_GRANTS:
				getLicenseGrants().clear();
				getLicenseGrants().addAll((Collection<? extends LicenseGrant>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LicPackage.LICENSE_PACK__IDENTIFIER:
				setIdentifier(IDENTIFIER_EDEFAULT);
				return;
			case LicPackage.LICENSE_PACK__ISSUE_DATE:
				setIssueDate(ISSUE_DATE_EDEFAULT);
				return;
			case LicPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
				setProductIdentifier(PRODUCT_IDENTIFIER_EDEFAULT);
				return;
			case LicPackage.LICENSE_PACK__PRODUCT_VERSION:
				setProductVersion(PRODUCT_VERSION_EDEFAULT);
				return;
			case LicPackage.LICENSE_PACK__USER_IDENTIFIER:
				setUserIdentifier(USER_IDENTIFIER_EDEFAULT);
				return;
			case LicPackage.LICENSE_PACK__LICENSE_GRANTS:
				getLicenseGrants().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LicPackage.LICENSE_PACK__IDENTIFIER:
				return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
			case LicPackage.LICENSE_PACK__ISSUE_DATE:
				return ISSUE_DATE_EDEFAULT == null ? issueDate != null : !ISSUE_DATE_EDEFAULT.equals(issueDate);
			case LicPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
				return PRODUCT_IDENTIFIER_EDEFAULT == null ? productIdentifier != null : !PRODUCT_IDENTIFIER_EDEFAULT.equals(productIdentifier);
			case LicPackage.LICENSE_PACK__PRODUCT_VERSION:
				return PRODUCT_VERSION_EDEFAULT == null ? productVersion != null : !PRODUCT_VERSION_EDEFAULT.equals(productVersion);
			case LicPackage.LICENSE_PACK__USER_IDENTIFIER:
				return USER_IDENTIFIER_EDEFAULT == null ? userIdentifier != null : !USER_IDENTIFIER_EDEFAULT.equals(userIdentifier);
			case LicPackage.LICENSE_PACK__LICENSE_GRANTS:
				return licenseGrants != null && !licenseGrants.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (identifier: "); //$NON-NLS-1$
		result.append(identifier);
		result.append(", issueDate: "); //$NON-NLS-1$
		result.append(issueDate);
		result.append(", productIdentifier: "); //$NON-NLS-1$
		result.append(productIdentifier);
		result.append(", productVersion: "); //$NON-NLS-1$
		result.append(productVersion);
		result.append(", userIdentifier: "); //$NON-NLS-1$
		result.append(userIdentifier);
		result.append(')');
		return result.toString();
	}

} //LicensePackImpl
