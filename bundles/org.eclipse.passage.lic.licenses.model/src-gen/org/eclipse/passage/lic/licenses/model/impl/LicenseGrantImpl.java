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

import java.util.Date;

import java.util.Objects;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model object '<em><b>License Grant</b></em>'.
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getMatchVersion <em>Match Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getMatchRule <em>Match Rule</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getValidUntil <em>Valid Until</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicenseGrantImpl#getLicensePack <em>License Pack</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LicenseGrantImpl extends MinimalEObjectImpl.Container implements LicenseGrant {
	/**
	 * The default value of the '{@link #getFeatureIdentifier() <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getFeatureIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURE_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeatureIdentifier() <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getFeatureIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String featureIdentifier = FEATURE_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatchVersion() <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getMatchVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String MATCH_VERSION_EDEFAULT = "0.0.0"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getMatchVersion() <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getMatchVersion()
	 * @generated
	 * @ordered
	 */
	protected String matchVersion = MATCH_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatchRule() <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getMatchRule()
	 * @generated
	 * @ordered
	 */
	protected static final String MATCH_RULE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMatchRule() <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getMatchRule()
	 * @generated
	 * @ordered
	 */
	protected String matchRule = MATCH_RULE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected Date validFrom = VALID_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidUntil() <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getValidUntil()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_UNTIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidUntil() <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getValidUntil()
	 * @generated
	 * @ordered
	 */
	protected Date validUntil = VALID_UNTIL_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionType() <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getConditionType()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionType() <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getConditionType()
	 * @generated
	 * @ordered
	 */
	protected String conditionType = CONDITION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionExpression() <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getConditionExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionExpression() <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getConditionExpression()
	 * @generated
	 * @ordered
	 */
	protected String conditionExpression = CONDITION_EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	protected static final int CAPACITY_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	protected int capacity = CAPACITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LicenseGrantImpl() {
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
		return LicensesPackage.eINSTANCE.getLicenseGrant();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFeatureIdentifier() {
		return featureIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeatureIdentifier(String newFeatureIdentifier) {
		String oldFeatureIdentifier = featureIdentifier;
		featureIdentifier = newFeatureIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__FEATURE_IDENTIFIER,
					oldFeatureIdentifier, featureIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMatchVersion() {
		return matchVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMatchVersion(String newMatchVersion) {
		String oldMatchVersion = matchVersion;
		matchVersion = newMatchVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__MATCH_VERSION,
					oldMatchVersion, matchVersion));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMatchRule() {
		return matchRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMatchRule(String newMatchRule) {
		String oldMatchRule = matchRule;
		matchRule = newMatchRule;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__MATCH_RULE,
					oldMatchRule, matchRule));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidFrom(Date newValidFrom) {
		Date oldValidFrom = validFrom;
		validFrom = newValidFrom;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__VALID_FROM,
					oldValidFrom, validFrom));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getValidUntil() {
		return validUntil;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidUntil(Date newValidUntil) {
		Date oldValidUntil = validUntil;
		validUntil = newValidUntil;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__VALID_UNTIL,
					oldValidUntil, validUntil));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConditionType(String newConditionType) {
		String oldConditionType = conditionType;
		conditionType = newConditionType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__CONDITION_TYPE,
					oldConditionType, conditionType));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConditionExpression() {
		return conditionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConditionExpression(String newConditionExpression) {
		String oldConditionExpression = conditionExpression;
		conditionExpression = newConditionExpression;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__CONDITION_EXPRESSION,
					oldConditionExpression, conditionExpression));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCapacity(int newCapacity) {
		int oldCapacity = capacity;
		capacity = newCapacity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__CAPACITY, oldCapacity,
					capacity));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensePack getLicensePack() {
		if (eContainerFeatureID() != LicensesPackage.LICENSE_GRANT__LICENSE_PACK) {
			return null;
		}
		return (LicensePack) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLicensePack(LicensePack newLicensePack, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newLicensePack, LicensesPackage.LICENSE_GRANT__LICENSE_PACK, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLicensePack(LicensePack newLicensePack) {
		if (newLicensePack != eInternalContainer()
				|| (eContainerFeatureID() != LicensesPackage.LICENSE_GRANT__LICENSE_PACK && newLicensePack != null)) {
			if (EcoreUtil.isAncestor(this, newLicensePack)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newLicensePack != null)
				msgs = ((InternalEObject) newLicensePack).eInverseAdd(this,
						LicensesPackage.LICENSE_PACK__LICENSE_GRANTS, LicensePack.class, msgs);
			msgs = basicSetLicensePack(newLicensePack, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_GRANT__LICENSE_PACK,
					newLicensePack, newLicensePack));
		}
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
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetLicensePack((LicensePack) otherEnd, msgs);
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
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			return basicSetLicensePack(null, msgs);
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
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			return eInternalContainer().eInverseRemove(this, LicensesPackage.LICENSE_PACK__LICENSE_GRANTS,
					LicensePack.class, msgs);
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
		case LicensesPackage.LICENSE_GRANT__FEATURE_IDENTIFIER:
			return getFeatureIdentifier();
		case LicensesPackage.LICENSE_GRANT__MATCH_VERSION:
			return getMatchVersion();
		case LicensesPackage.LICENSE_GRANT__MATCH_RULE:
			return getMatchRule();
		case LicensesPackage.LICENSE_GRANT__VALID_FROM:
			return getValidFrom();
		case LicensesPackage.LICENSE_GRANT__VALID_UNTIL:
			return getValidUntil();
		case LicensesPackage.LICENSE_GRANT__CONDITION_TYPE:
			return getConditionType();
		case LicensesPackage.LICENSE_GRANT__CONDITION_EXPRESSION:
			return getConditionExpression();
		case LicensesPackage.LICENSE_GRANT__CAPACITY:
			return getCapacity();
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			return getLicensePack();
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
		case LicensesPackage.LICENSE_GRANT__FEATURE_IDENTIFIER:
			setFeatureIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__MATCH_VERSION:
			setMatchVersion((String) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__MATCH_RULE:
			setMatchRule((String) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__VALID_FROM:
			setValidFrom((Date) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__VALID_UNTIL:
			setValidUntil((Date) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__CONDITION_TYPE:
			setConditionType((String) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__CONDITION_EXPRESSION:
			setConditionExpression((String) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__CAPACITY:
			setCapacity((Integer) newValue);
			return;
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			setLicensePack((LicensePack) newValue);
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
		case LicensesPackage.LICENSE_GRANT__FEATURE_IDENTIFIER:
			setFeatureIdentifier(FEATURE_IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__MATCH_VERSION:
			setMatchVersion(MATCH_VERSION_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__MATCH_RULE:
			setMatchRule(MATCH_RULE_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__VALID_FROM:
			setValidFrom(VALID_FROM_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__VALID_UNTIL:
			setValidUntil(VALID_UNTIL_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__CONDITION_TYPE:
			setConditionType(CONDITION_TYPE_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__CONDITION_EXPRESSION:
			setConditionExpression(CONDITION_EXPRESSION_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__CAPACITY:
			setCapacity(CAPACITY_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			setLicensePack((LicensePack) null);
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
		case LicensesPackage.LICENSE_GRANT__FEATURE_IDENTIFIER:
			return !Objects.equals(FEATURE_IDENTIFIER_EDEFAULT, featureIdentifier);
		case LicensesPackage.LICENSE_GRANT__MATCH_VERSION:
			return !Objects.equals(MATCH_VERSION_EDEFAULT, matchVersion);
		case LicensesPackage.LICENSE_GRANT__MATCH_RULE:
			return !Objects.equals(MATCH_RULE_EDEFAULT, matchRule);
		case LicensesPackage.LICENSE_GRANT__VALID_FROM:
			return !Objects.equals(VALID_FROM_EDEFAULT, validFrom);
		case LicensesPackage.LICENSE_GRANT__VALID_UNTIL:
			return !Objects.equals(VALID_UNTIL_EDEFAULT, validUntil);
		case LicensesPackage.LICENSE_GRANT__CONDITION_TYPE:
			return !Objects.equals(CONDITION_TYPE_EDEFAULT, conditionType);
		case LicensesPackage.LICENSE_GRANT__CONDITION_EXPRESSION:
			return !Objects.equals(CONDITION_EXPRESSION_EDEFAULT, conditionExpression);
		case LicensesPackage.LICENSE_GRANT__CAPACITY:
			return capacity != CAPACITY_EDEFAULT;
		case LicensesPackage.LICENSE_GRANT__LICENSE_PACK:
			return getLicensePack() != null;
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
		result.append(" (featureIdentifier: "); //$NON-NLS-1$
		result.append(featureIdentifier);
		result.append(", matchVersion: "); //$NON-NLS-1$
		result.append(matchVersion);
		result.append(", matchRule: "); //$NON-NLS-1$
		result.append(matchRule);
		result.append(", validFrom: "); //$NON-NLS-1$
		result.append(validFrom);
		result.append(", validUntil: "); //$NON-NLS-1$
		result.append(validUntil);
		result.append(", conditionType: "); //$NON-NLS-1$
		result.append(conditionType);
		result.append(", conditionExpression: "); //$NON-NLS-1$
		result.append(conditionExpression);
		result.append(", capacity: "); //$NON-NLS-1$
		result.append(capacity);
		result.append(')');
		return result.toString();
	}

} // LicenseGrantImpl
