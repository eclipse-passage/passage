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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.licenses.model.api.FeatureRef;
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
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getPlan <em>Plan</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getVivid <em>Vivid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanFeatureImpl#getCapacity <em>Capacity</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LicensePlanFeatureImpl extends MinimalEObjectImpl.Container implements LicensePlanFeature {
	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected FeatureRef feature;

	/**
	 * The default value of the '{@link #getVivid() <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVivid()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected static final long VIVID_EDEFAULT = 0L;
	/**
	 * The cached value of the '{@link #getVivid() <em>Vivid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVivid()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	private long vivid = VIVID_EDEFAULT;
	/**
	 * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	protected static final int CAPACITY_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @since 2.0
	 * @generated
	 * @ordered
	 */
	private int capacity = CAPACITY_EDEFAULT;

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
	 * @since 2.0
	 * @generated
	 */
	@Override
	public FeatureRef getFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	public NotificationChain basicSetFeature(FeatureRef newFeature, NotificationChain msgs) {
		FeatureRef oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE, oldFeature, newFeature);
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
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setFeature(FeatureRef newFeature) {
		if (newFeature != feature) {
			NotificationChain msgs = null;
			if (feature != null)
				msgs = ((InternalEObject) feature).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE, null, msgs);
			if (newFeature != null)
				msgs = ((InternalEObject) newFeature).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE, null, msgs);
			msgs = basicSetFeature(newFeature, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE,
					newFeature, newFeature));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public LicensePlan getPlan() {
		if (eContainerFeatureID() != LicensesPackage.LICENSE_PLAN_FEATURE__PLAN) {
			return null;
		}
		return (LicensePlan) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	public NotificationChain basicSetPlan(LicensePlan newPlan, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPlan, LicensesPackage.LICENSE_PLAN_FEATURE__PLAN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setPlan(LicensePlan newPlan) {
		if (newPlan != eInternalContainer()
				|| (eContainerFeatureID() != LicensesPackage.LICENSE_PLAN_FEATURE__PLAN && newPlan != null)) {
			if (EcoreUtil.isAncestor(this, newPlan)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newPlan != null)
				msgs = ((InternalEObject) newPlan).eInverseAdd(this, LicensesPackage.LICENSE_PLAN__FEATURES,
						LicensePlan.class, msgs);
			msgs = basicSetPlan(newPlan, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__PLAN, newPlan,
					newPlan));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public long getVivid() {
		return vivid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setVivid(long newVivid) {
		long oldVivid = vivid;
		vivid = newVivid;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__VIVID, oldVivid,
					vivid));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	@Override
	public void setCapacity(int newCapacity) {
		int oldCapacity = capacity;
		capacity = newCapacity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN_FEATURE__CAPACITY,
					oldCapacity, capacity));
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetPlan((LicensePlan) otherEnd, msgs);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE:
			return basicSetFeature(null, msgs);
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			return basicSetPlan(null, msgs);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			return eInternalContainer().eInverseRemove(this, LicensesPackage.LICENSE_PLAN__FEATURES, LicensePlan.class,
					msgs);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE:
			return getFeature();
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			return getPlan();
		case LicensesPackage.LICENSE_PLAN_FEATURE__VIVID:
			return getVivid();
		case LicensesPackage.LICENSE_PLAN_FEATURE__CAPACITY:
			return getCapacity();
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE:
			setFeature((FeatureRef) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			setPlan((LicensePlan) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__VIVID:
			setVivid((Long) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__CAPACITY:
			setCapacity((Integer) newValue);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE:
			setFeature((FeatureRef) null);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			setPlan((LicensePlan) null);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__VIVID:
			setVivid(VIVID_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN_FEATURE__CAPACITY:
			setCapacity(CAPACITY_EDEFAULT);
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
		case LicensesPackage.LICENSE_PLAN_FEATURE__FEATURE:
			return feature != null;
		case LicensesPackage.LICENSE_PLAN_FEATURE__PLAN:
			return getPlan() != null;
		case LicensesPackage.LICENSE_PLAN_FEATURE__VIVID:
			return vivid != VIVID_EDEFAULT;
		case LicensesPackage.LICENSE_PLAN_FEATURE__CAPACITY:
			return capacity != CAPACITY_EDEFAULT;
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
		result.append(" (vivid: "); //$NON-NLS-1$
		result.append(vivid);
		result.append(", capacity: "); //$NON-NLS-1$
		result.append(capacity);
		result.append(')');
		return result.toString();
	}

} //LicensePlanFeatureImpl
