/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.passage.lic.shipments.model.api.Personal;
import org.eclipse.passage.lic.shipments.model.api.PersonalLicense;
import org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Personal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.impl.PersonalImpl#getLicenses <em>Licenses</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PersonalImpl extends MinimalEObjectImpl.Container implements Personal {
	/**
	 * The cached value of the '{@link #getLicenses() <em>Licenses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicenses()
	 * @generated
	 * @ordered
	 */
	protected EList<PersonalLicense> licenses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShipmentsPackage.eINSTANCE.getPersonal();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PersonalLicense> getLicenses() {
		if (licenses == null) {
			licenses = new EObjectContainmentEList<PersonalLicense>(PersonalLicense.class, this,
					ShipmentsPackage.PERSONAL__LICENSES);
		}
		return licenses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ShipmentsPackage.PERSONAL__LICENSES:
			return ((InternalEList<?>) getLicenses()).basicRemove(otherEnd, msgs);
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
		case ShipmentsPackage.PERSONAL__LICENSES:
			return getLicenses();
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
		case ShipmentsPackage.PERSONAL__LICENSES:
			getLicenses().clear();
			getLicenses().addAll((Collection<? extends PersonalLicense>) newValue);
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
		case ShipmentsPackage.PERSONAL__LICENSES:
			getLicenses().clear();
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
		case ShipmentsPackage.PERSONAL__LICENSES:
			return licenses != null && !licenses.isEmpty();
		default:
			return super.eIsSet(featureID);
		}
	}

} //PersonalImpl
