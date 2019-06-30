/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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

import java.util.Date;

import java.util.Objects;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User License</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getPlanIdentifier <em>Plan Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getProductIdentifier <em>Product Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getProductVersion <em>Product Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getValidUntil <em>Valid Until</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getPackIdentifier <em>Pack Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getIssueDate <em>Issue Date</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.impl.UserLicenseImpl#getUser <em>User</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UserLicenseImpl extends MinimalEObjectImpl.Container implements UserLicense {
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
	private String planIdentifier = PLAN_IDENTIFIER_EDEFAULT;

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
	private String productIdentifier = PRODUCT_IDENTIFIER_EDEFAULT;

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
	private String productVersion = PRODUCT_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	private Date validFrom = VALID_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidUntil() <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidUntil()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_UNTIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidUntil() <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidUntil()
	 * @generated
	 * @ordered
	 */
	private Date validUntil = VALID_UNTIL_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionType() <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionType()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionType() <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionType()
	 * @generated
	 * @ordered
	 */
	private String conditionType = CONDITION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionExpression() <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionExpression() <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionExpression()
	 * @generated
	 * @ordered
	 */
	private String conditionExpression = CONDITION_EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackIdentifier() <em>Pack Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String PACK_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackIdentifier() <em>Pack Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackIdentifier()
	 * @generated
	 * @ordered
	 */
	private String packIdentifier = PACK_IDENTIFIER_EDEFAULT;

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
	private Date issueDate = ISSUE_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserLicenseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.eINSTANCE.getUserLicense();
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__PLAN_IDENTIFIER,
					oldPlanIdentifier, planIdentifier));
		}
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__PRODUCT_IDENTIFIER,
					oldProductIdentifier, productIdentifier));
		}
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__PRODUCT_VERSION,
					oldProductVersion, productVersion));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidFrom(Date newValidFrom) {
		Date oldValidFrom = validFrom;
		validFrom = newValidFrom;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__VALID_FROM, oldValidFrom,
					validFrom));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getValidUntil() {
		return validUntil;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidUntil(Date newValidUntil) {
		Date oldValidUntil = validUntil;
		validUntil = newValidUntil;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__VALID_UNTIL, oldValidUntil,
					validUntil));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConditionType(String newConditionType) {
		String oldConditionType = conditionType;
		conditionType = newConditionType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__CONDITION_TYPE,
					oldConditionType, conditionType));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConditionExpression() {
		return conditionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConditionExpression(String newConditionExpression) {
		String oldConditionExpression = conditionExpression;
		conditionExpression = newConditionExpression;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__CONDITION_EXPRESSION,
					oldConditionExpression, conditionExpression));
		}
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
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__ISSUE_DATE, oldIssueDate,
					issueDate));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPackIdentifier() {
		return packIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPackIdentifier(String newPackIdentifier) {
		String oldPackIdentifier = packIdentifier;
		packIdentifier = newPackIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__PACK_IDENTIFIER,
					oldPackIdentifier, packIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public User getUser() {
		if (eContainerFeatureID() != UsersPackage.USER_LICENSE__USER) {
			return null;
		}
		return (User) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUser(User newUser, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newUser, UsersPackage.USER_LICENSE__USER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUser(User newUser) {
		if (newUser != eInternalContainer()
				|| (eContainerFeatureID() != UsersPackage.USER_LICENSE__USER && newUser != null)) {
			if (EcoreUtil.isAncestor(this, newUser)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newUser != null)
				msgs = ((InternalEObject) newUser).eInverseAdd(this, UsersPackage.USER__USER_LICENSES, User.class,
						msgs);
			msgs = basicSetUser(newUser, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.USER_LICENSE__USER, newUser, newUser));
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
		case UsersPackage.USER_LICENSE__USER:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetUser((User) otherEnd, msgs);
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
		case UsersPackage.USER_LICENSE__USER:
			return basicSetUser(null, msgs);
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
		case UsersPackage.USER_LICENSE__USER:
			return eInternalContainer().eInverseRemove(this, UsersPackage.USER__USER_LICENSES, User.class, msgs);
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
		case UsersPackage.USER_LICENSE__PLAN_IDENTIFIER:
			return getPlanIdentifier();
		case UsersPackage.USER_LICENSE__PRODUCT_IDENTIFIER:
			return getProductIdentifier();
		case UsersPackage.USER_LICENSE__PRODUCT_VERSION:
			return getProductVersion();
		case UsersPackage.USER_LICENSE__VALID_FROM:
			return getValidFrom();
		case UsersPackage.USER_LICENSE__VALID_UNTIL:
			return getValidUntil();
		case UsersPackage.USER_LICENSE__CONDITION_TYPE:
			return getConditionType();
		case UsersPackage.USER_LICENSE__CONDITION_EXPRESSION:
			return getConditionExpression();
		case UsersPackage.USER_LICENSE__PACK_IDENTIFIER:
			return getPackIdentifier();
		case UsersPackage.USER_LICENSE__ISSUE_DATE:
			return getIssueDate();
		case UsersPackage.USER_LICENSE__USER:
			return getUser();
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
		case UsersPackage.USER_LICENSE__PLAN_IDENTIFIER:
			setPlanIdentifier((String) newValue);
			return;
		case UsersPackage.USER_LICENSE__PRODUCT_IDENTIFIER:
			setProductIdentifier((String) newValue);
			return;
		case UsersPackage.USER_LICENSE__PRODUCT_VERSION:
			setProductVersion((String) newValue);
			return;
		case UsersPackage.USER_LICENSE__VALID_FROM:
			setValidFrom((Date) newValue);
			return;
		case UsersPackage.USER_LICENSE__VALID_UNTIL:
			setValidUntil((Date) newValue);
			return;
		case UsersPackage.USER_LICENSE__CONDITION_TYPE:
			setConditionType((String) newValue);
			return;
		case UsersPackage.USER_LICENSE__CONDITION_EXPRESSION:
			setConditionExpression((String) newValue);
			return;
		case UsersPackage.USER_LICENSE__PACK_IDENTIFIER:
			setPackIdentifier((String) newValue);
			return;
		case UsersPackage.USER_LICENSE__ISSUE_DATE:
			setIssueDate((Date) newValue);
			return;
		case UsersPackage.USER_LICENSE__USER:
			setUser((User) newValue);
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
		case UsersPackage.USER_LICENSE__PLAN_IDENTIFIER:
			setPlanIdentifier(PLAN_IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__PRODUCT_IDENTIFIER:
			setProductIdentifier(PRODUCT_IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__PRODUCT_VERSION:
			setProductVersion(PRODUCT_VERSION_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__VALID_FROM:
			setValidFrom(VALID_FROM_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__VALID_UNTIL:
			setValidUntil(VALID_UNTIL_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__CONDITION_TYPE:
			setConditionType(CONDITION_TYPE_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__CONDITION_EXPRESSION:
			setConditionExpression(CONDITION_EXPRESSION_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__PACK_IDENTIFIER:
			setPackIdentifier(PACK_IDENTIFIER_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__ISSUE_DATE:
			setIssueDate(ISSUE_DATE_EDEFAULT);
			return;
		case UsersPackage.USER_LICENSE__USER:
			setUser((User) null);
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
		case UsersPackage.USER_LICENSE__PLAN_IDENTIFIER:
			return !Objects.equals(PLAN_IDENTIFIER_EDEFAULT, planIdentifier);
		case UsersPackage.USER_LICENSE__PRODUCT_IDENTIFIER:
			return !Objects.equals(PRODUCT_IDENTIFIER_EDEFAULT, productIdentifier);
		case UsersPackage.USER_LICENSE__PRODUCT_VERSION:
			return !Objects.equals(PRODUCT_VERSION_EDEFAULT, productVersion);
		case UsersPackage.USER_LICENSE__VALID_FROM:
			return !Objects.equals(VALID_FROM_EDEFAULT, validFrom);
		case UsersPackage.USER_LICENSE__VALID_UNTIL:
			return !Objects.equals(VALID_UNTIL_EDEFAULT, validUntil);
		case UsersPackage.USER_LICENSE__CONDITION_TYPE:
			return !Objects.equals(CONDITION_TYPE_EDEFAULT, conditionType);
		case UsersPackage.USER_LICENSE__CONDITION_EXPRESSION:
			return !Objects.equals(CONDITION_EXPRESSION_EDEFAULT, conditionExpression);
		case UsersPackage.USER_LICENSE__PACK_IDENTIFIER:
			return !Objects.equals(PACK_IDENTIFIER_EDEFAULT, packIdentifier);
		case UsersPackage.USER_LICENSE__ISSUE_DATE:
			return !Objects.equals(ISSUE_DATE_EDEFAULT, issueDate);
		case UsersPackage.USER_LICENSE__USER:
			return getUser() != null;
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
		result.append(" (planIdentifier: "); //$NON-NLS-1$
		result.append(planIdentifier);
		result.append(", productIdentifier: "); //$NON-NLS-1$
		result.append(productIdentifier);
		result.append(", productVersion: "); //$NON-NLS-1$
		result.append(productVersion);
		result.append(", validFrom: "); //$NON-NLS-1$
		result.append(validFrom);
		result.append(", validUntil: "); //$NON-NLS-1$
		result.append(validUntil);
		result.append(", conditionType: "); //$NON-NLS-1$
		result.append(conditionType);
		result.append(", conditionExpression: "); //$NON-NLS-1$
		result.append(conditionExpression);
		result.append(", packIdentifier: "); //$NON-NLS-1$
		result.append(packIdentifier);
		result.append(", issueDate: "); //$NON-NLS-1$
		result.append(issueDate);
		result.append(')');
		return result.toString();
	}

} //UserLicenseImpl
