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
package org.eclipse.passage.lic.licenses.model.impl;

import java.util.Collection;
import java.util.Date;

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
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model object '<em><b>License Pack</b></em>'.
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getIssueDate <em>Issue Date</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getUserIdentifier <em>User Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getUserFullName <em>User Full Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getRequestIdentifier <em>Request Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getPlanIdentifier <em>Plan Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getProductIdentifier <em>Product Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getProductVersion <em>Product Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePackImpl#getLicenseGrants <em>License Grants</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LicensePackImpl extends MinimalEObjectImpl.Container implements LicensePack {
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
	 * The default value of the '{@link #getIssueDate() <em>Issue Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getIssueDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date ISSUE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIssueDate() <em>Issue Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getIssueDate()
	 * @generated
	 * @ordered
	 */
	protected Date issueDate = ISSUE_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserIdentifier() <em>User Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getUserIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserIdentifier() <em>User Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getUserIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String userIdentifier = USER_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserFullName() <em>User Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserFullName()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_FULL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserFullName() <em>User Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserFullName()
	 * @generated
	 * @ordered
	 */
	protected String userFullName = USER_FULL_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequestIdentifier() <em>Request Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUEST_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequestIdentifier() <em>Request Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String requestIdentifier = REQUEST_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPlanIdentifier() <em>Plan Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlanIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String PLAN_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPlanIdentifier() <em>Plan Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlanIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String planIdentifier = PLAN_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductIdentifier() <em>Product Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getProductIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductIdentifier() <em>Product Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getProductIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String productIdentifier = PRODUCT_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductVersion() <em>Product Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getProductVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductVersion() <em>Product Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getProductVersion()
	 * @generated
	 * @ordered
	 */
	protected String productVersion = PRODUCT_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLicenseGrants() <em>License Grants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getLicenseGrants()
	 * @generated
	 * @ordered
	 */
	protected EList<LicenseGrant> licenseGrants;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LicensePackImpl() {
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
		return LicensesPackage.eINSTANCE.getLicensePack();
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__IDENTIFIER,
					oldIdentifier, identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIssueDate(Date newIssueDate) {
		Date oldIssueDate = issueDate;
		issueDate = newIssueDate;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__ISSUE_DATE,
					oldIssueDate, issueDate));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProductIdentifier() {
		return productIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductIdentifier(String newProductIdentifier) {
		String oldProductIdentifier = productIdentifier;
		productIdentifier = newProductIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__PRODUCT_IDENTIFIER,
					oldProductIdentifier, productIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProductVersion() {
		return productVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductVersion(String newProductVersion) {
		String oldProductVersion = productVersion;
		productVersion = newProductVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__PRODUCT_VERSION,
					oldProductVersion, productVersion));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUserIdentifier(String newUserIdentifier) {
		String oldUserIdentifier = userIdentifier;
		userIdentifier = newUserIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__USER_IDENTIFIER,
					oldUserIdentifier, userIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUserFullName() {
		return userFullName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUserFullName(String newUserFullName) {
		String oldUserFullName = userFullName;
		userFullName = newUserFullName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__USER_FULL_NAME,
					oldUserFullName, userFullName));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRequestIdentifier() {
		return requestIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequestIdentifier(String newRequestIdentifier) {
		String oldRequestIdentifier = requestIdentifier;
		requestIdentifier = newRequestIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__REQUEST_IDENTIFIER,
					oldRequestIdentifier, requestIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPlanIdentifier() {
		return planIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlanIdentifier(String newPlanIdentifier) {
		String oldPlanIdentifier = planIdentifier;
		planIdentifier = newPlanIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PACK__PLAN_IDENTIFIER,
					oldPlanIdentifier, planIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LicenseGrant> getLicenseGrants() {
		if (licenseGrants == null) {
			licenseGrants = new EObjectContainmentWithInverseEList<LicenseGrant>(LicenseGrant.class, this,
					LicensesPackage.LICENSE_PACK__LICENSE_GRANTS, LicensesPackage.LICENSE_GRANT__LICENSE_PACK);
		}
		return licenseGrants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case LicensesPackage.LICENSE_PACK__LICENSE_GRANTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getLicenseGrants()).basicAdd(otherEnd, msgs);
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
		case LicensesPackage.LICENSE_PACK__LICENSE_GRANTS:
			return ((InternalEList<?>) getLicenseGrants()).basicRemove(otherEnd, msgs);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case LicensesPackage.LICENSE_PACK__IDENTIFIER:
			return getIdentifier();
		case LicensesPackage.LICENSE_PACK__ISSUE_DATE:
			return getIssueDate();
		case LicensesPackage.LICENSE_PACK__USER_IDENTIFIER:
			return getUserIdentifier();
		case LicensesPackage.LICENSE_PACK__USER_FULL_NAME:
			return getUserFullName();
		case LicensesPackage.LICENSE_PACK__REQUEST_IDENTIFIER:
			return getRequestIdentifier();
		case LicensesPackage.LICENSE_PACK__PLAN_IDENTIFIER:
			return getPlanIdentifier();
		case LicensesPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
			return getProductIdentifier();
		case LicensesPackage.LICENSE_PACK__PRODUCT_VERSION:
			return getProductVersion();
		case LicensesPackage.LICENSE_PACK__LICENSE_GRANTS:
			return getLicenseGrants();
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case LicensesPackage.LICENSE_PACK__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__ISSUE_DATE:
			setIssueDate((Date) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__USER_IDENTIFIER:
			setUserIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__USER_FULL_NAME:
			setUserFullName((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__REQUEST_IDENTIFIER:
			setRequestIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__PLAN_IDENTIFIER:
			setPlanIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
			setProductIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__PRODUCT_VERSION:
			setProductVersion((String) newValue);
			return;
		case LicensesPackage.LICENSE_PACK__LICENSE_GRANTS:
			getLicenseGrants().clear();
			getLicenseGrants().addAll((Collection<? extends LicenseGrant>) newValue);
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
		case LicensesPackage.LICENSE_PACK__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__ISSUE_DATE:
			setIssueDate(ISSUE_DATE_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__USER_IDENTIFIER:
			setUserIdentifier(USER_IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__USER_FULL_NAME:
			setUserFullName(USER_FULL_NAME_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__REQUEST_IDENTIFIER:
			setRequestIdentifier(REQUEST_IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__PLAN_IDENTIFIER:
			setPlanIdentifier(PLAN_IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
			setProductIdentifier(PRODUCT_IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__PRODUCT_VERSION:
			setProductVersion(PRODUCT_VERSION_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PACK__LICENSE_GRANTS:
			getLicenseGrants().clear();
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
		case LicensesPackage.LICENSE_PACK__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case LicensesPackage.LICENSE_PACK__ISSUE_DATE:
			return !Objects.equals(ISSUE_DATE_EDEFAULT, issueDate);
		case LicensesPackage.LICENSE_PACK__USER_IDENTIFIER:
			return !Objects.equals(USER_IDENTIFIER_EDEFAULT, userIdentifier);
		case LicensesPackage.LICENSE_PACK__USER_FULL_NAME:
			return !Objects.equals(USER_FULL_NAME_EDEFAULT, userFullName);
		case LicensesPackage.LICENSE_PACK__REQUEST_IDENTIFIER:
			return !Objects.equals(REQUEST_IDENTIFIER_EDEFAULT, requestIdentifier);
		case LicensesPackage.LICENSE_PACK__PLAN_IDENTIFIER:
			return !Objects.equals(PLAN_IDENTIFIER_EDEFAULT, planIdentifier);
		case LicensesPackage.LICENSE_PACK__PRODUCT_IDENTIFIER:
			return !Objects.equals(PRODUCT_IDENTIFIER_EDEFAULT, productIdentifier);
		case LicensesPackage.LICENSE_PACK__PRODUCT_VERSION:
			return !Objects.equals(PRODUCT_VERSION_EDEFAULT, productVersion);
		case LicensesPackage.LICENSE_PACK__LICENSE_GRANTS:
			return licenseGrants != null && !licenseGrants.isEmpty();
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
		result.append(", issueDate: "); //$NON-NLS-1$
		result.append(issueDate);
		result.append(", userIdentifier: "); //$NON-NLS-1$
		result.append(userIdentifier);
		result.append(", userFullName: "); //$NON-NLS-1$
		result.append(userFullName);
		result.append(", requestIdentifier: "); //$NON-NLS-1$
		result.append(requestIdentifier);
		result.append(", planIdentifier: "); //$NON-NLS-1$
		result.append(planIdentifier);
		result.append(", productIdentifier: "); //$NON-NLS-1$
		result.append(productIdentifier);
		result.append(", productVersion: "); //$NON-NLS-1$
		result.append(productVersion);
		result.append(')');
		return result.toString();
	}

} // LicensePackImpl
