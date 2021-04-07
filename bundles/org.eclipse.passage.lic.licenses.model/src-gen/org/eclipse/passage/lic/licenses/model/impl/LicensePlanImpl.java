/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>License Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl#getLicensePlanFeatures <em>License Plan Features</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl#getPersonal <em>Personal</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.LicensePlanImpl#getFloating <em>Floating</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LicensePlanImpl extends MinimalEObjectImpl.Container implements LicensePlan {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	private String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	private String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLicensePlanFeatures() <em>License Plan Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicensePlanFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<LicensePlanFeatureDescriptor> licensePlanFeatures;

	/**
	 * The cached value of the '{@link #getPersonal() <em>Personal</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersonal()
	 * @generated
	 * @ordered
	 */
	protected EList<LicensePack> personal;

	/**
	 * The cached value of the '{@link #getFloating() <em>Floating</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloating()
	 * @generated
	 * @ordered
	 */
	protected EList<FloatingLicensePack> floating;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LicensePlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicensesPackage.eINSTANCE.getLicensePlan();
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN__IDENTIFIER,
					oldIdentifier, identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.LICENSE_PLAN__DESCRIPTION,
					oldDescription, description));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LicensePlanFeatureDescriptor> getLicensePlanFeatures() {
		if (licensePlanFeatures == null) {
			licensePlanFeatures = new EObjectContainmentEList<LicensePlanFeatureDescriptor>(
					LicensePlanFeatureDescriptor.class, this, LicensesPackage.LICENSE_PLAN__LICENSE_PLAN_FEATURES);
		}
		return licensePlanFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LicensePack> getPersonal() {
		if (personal == null) {
			personal = new EObjectContainmentEList<LicensePack>(LicensePack.class, this,
					LicensesPackage.LICENSE_PLAN__PERSONAL);
		}
		return personal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<FloatingLicensePack> getFloating() {
		if (floating == null) {
			floating = new EObjectContainmentEList<FloatingLicensePack>(FloatingLicensePack.class, this,
					LicensesPackage.LICENSE_PLAN__FLOATING);
		}
		return floating;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case LicensesPackage.LICENSE_PLAN__LICENSE_PLAN_FEATURES:
			return ((InternalEList<?>) getLicensePlanFeatures()).basicRemove(otherEnd, msgs);
		case LicensesPackage.LICENSE_PLAN__PERSONAL:
			return ((InternalEList<?>) getPersonal()).basicRemove(otherEnd, msgs);
		case LicensesPackage.LICENSE_PLAN__FLOATING:
			return ((InternalEList<?>) getFloating()).basicRemove(otherEnd, msgs);
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
		case LicensesPackage.LICENSE_PLAN__IDENTIFIER:
			return getIdentifier();
		case LicensesPackage.LICENSE_PLAN__NAME:
			return getName();
		case LicensesPackage.LICENSE_PLAN__DESCRIPTION:
			return getDescription();
		case LicensesPackage.LICENSE_PLAN__LICENSE_PLAN_FEATURES:
			return getLicensePlanFeatures();
		case LicensesPackage.LICENSE_PLAN__PERSONAL:
			return getPersonal();
		case LicensesPackage.LICENSE_PLAN__FLOATING:
			return getFloating();
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
		case LicensesPackage.LICENSE_PLAN__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN__NAME:
			setName((String) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN__LICENSE_PLAN_FEATURES:
			getLicensePlanFeatures().clear();
			getLicensePlanFeatures().addAll((Collection<? extends LicensePlanFeatureDescriptor>) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN__PERSONAL:
			getPersonal().clear();
			getPersonal().addAll((Collection<? extends LicensePack>) newValue);
			return;
		case LicensesPackage.LICENSE_PLAN__FLOATING:
			getFloating().clear();
			getFloating().addAll((Collection<? extends FloatingLicensePack>) newValue);
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
		case LicensesPackage.LICENSE_PLAN__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN__NAME:
			setName(NAME_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case LicensesPackage.LICENSE_PLAN__LICENSE_PLAN_FEATURES:
			getLicensePlanFeatures().clear();
			return;
		case LicensesPackage.LICENSE_PLAN__PERSONAL:
			getPersonal().clear();
			return;
		case LicensesPackage.LICENSE_PLAN__FLOATING:
			getFloating().clear();
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
		case LicensesPackage.LICENSE_PLAN__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case LicensesPackage.LICENSE_PLAN__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case LicensesPackage.LICENSE_PLAN__DESCRIPTION:
			return !Objects.equals(DESCRIPTION_EDEFAULT, description);
		case LicensesPackage.LICENSE_PLAN__LICENSE_PLAN_FEATURES:
			return licensePlanFeatures != null && !licensePlanFeatures.isEmpty();
		case LicensesPackage.LICENSE_PLAN__PERSONAL:
			return personal != null && !personal.isEmpty();
		case LicensesPackage.LICENSE_PLAN__FLOATING:
			return floating != null && !floating.isEmpty();
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
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //LicensePlanImpl
