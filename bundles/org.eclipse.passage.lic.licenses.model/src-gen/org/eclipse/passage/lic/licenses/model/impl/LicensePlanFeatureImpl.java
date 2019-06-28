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
package org.eclipse.passage.lic.licenses.model.impl;

import java.util.Objects;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>License Plan Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getMatchVersion <em>Match Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getMatchRule <em>Match Rule</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getLicensePlan <em>License Plan</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LicensePlanFeatureImpl extends MinimalEObjectImpl.Container implements LicensePlanFeature {
	/**
	 * The default value of the '{@link #getFeatureIdentifier() <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURE_IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeatureIdentifier() <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String featureIdentifier = FEATURE_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatchVersion() <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String MATCH_VERSION_EDEFAULT = "0.0.0"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getMatchVersion() <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchVersion()
	 * @generated
	 * @ordered
	 */
	protected String matchVersion = MATCH_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatchRule() <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchRule()
	 * @generated
	 * @ordered
	 */
	protected static final String MATCH_RULE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMatchRule() <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchRule()
	 * @generated
	 * @ordered
	 */
	protected String matchRule = MATCH_RULE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLicensePlan() <em>License Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicensePlan()
	 * @generated
	 * @ordered
	 */
	protected LicensePlan licensePlan;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LicensePlanFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicensesPackage.eINSTANCE.getLicensePlanFeature();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFeatureIdentifier() {
		return featureIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeatureIdentifier(String newFeatureIdentifier) {
		String oldFeatureIdentifier = featureIdentifier;
		featureIdentifier = newFeatureIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER, oldFeatureIdentifier, featureIdentifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMatchVersion() {
		return matchVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMatchVersion(String newMatchVersion) {
		String oldMatchVersion = matchVersion;
		matchVersion = newMatchVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_VERSION,
					oldMatchVersion, matchVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMatchRule() {
		return matchRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMatchRule(String newMatchRule) {
		String oldMatchRule = matchRule;
		matchRule = newMatchRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_RULE,
					oldMatchRule, matchRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensePlan getLicensePlan() {
		if (licensePlan != null && licensePlan.eIsProxy()) {
			InternalEObject oldLicensePlan = (InternalEObject) licensePlan;
			licensePlan = (LicensePlan) eResolveProxy(oldLicensePlan);
			if (licensePlan != oldLicensePlan) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							LicensesPackage.LICENSE_PLAN_FEATURE__LICENSE_PLAN, oldLicensePlan, licensePlan));
				}
			}
		}
		return licensePlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicensePlan basicGetLicensePlan() {
		return licensePlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLicensePlan(LicensePlan newLicensePlan) {
		LicensePlan oldLicensePlan = licensePlan;
		licensePlan = newLicensePlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__LICENSE_PLAN,
					oldLicensePlan, licensePlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER:
			return getFeatureIdentifier();
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_VERSION:
			return getMatchVersion();
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_RULE:
			return getMatchRule();
		case LicensesPackage.LICENSE_PLAN_FEATURE__LICENSE_PLAN:
			if (resolve)
				return getLicensePlan();
			return basicGetLicensePlan();
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER:
			setFeatureIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_VERSION:
			setMatchVersion((String) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_RULE:
			setMatchRule((String) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__LICENSE_PLAN:
			setLicensePlan((LicensePlan) newValue);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER:
			setFeatureIdentifier(FEATURE_IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_VERSION:
			setMatchVersion(MATCH_VERSION_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_RULE:
			setMatchRule(MATCH_RULE_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__LICENSE_PLAN:
			setLicensePlan((LicensePlan) null);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE_IDENTIFIER:
			return !Objects.equals(FEATURE_IDENTIFIER_EDEFAULT, featureIdentifier);
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_VERSION:
			return !Objects.equals(MATCH_VERSION_EDEFAULT, matchVersion);
		case LicensesPackage.LICENSE_PLAN_FEATURE__MATCH_RULE:
			return !Objects.equals(MATCH_RULE_EDEFAULT, matchRule);
		case LicensesPackage.LICENSE_PLAN_FEATURE__LICENSE_PLAN:
			return licensePlan != null;
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
		result.append(" (featureIdentifier: "); //$NON-NLS-1$
		result.append(featureIdentifier);
		result.append(", matchVersion: "); //$NON-NLS-1$
		result.append(matchVersion);
		result.append(", matchRule: "); //$NON-NLS-1$
		result.append(matchRule);
		result.append(')');
		return result.toString();
	}

} //LicensePlanFeatureImpl
