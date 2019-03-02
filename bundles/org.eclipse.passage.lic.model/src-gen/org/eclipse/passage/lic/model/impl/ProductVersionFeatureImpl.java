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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.model.api.ProductVersion;
import org.eclipse.passage.lic.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Version Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl#getFeatureVersion <em>Feature Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl#getRestrictionLevel <em>Restriction Level</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl#getProductVersion <em>Product Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductVersionFeatureImpl extends MinimalEObjectImpl.Container implements ProductVersionFeature {
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
	 * The default value of the '{@link #getFeatureVersion() <em>Feature Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeatureVersion() <em>Feature Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureVersion()
	 * @generated
	 * @ordered
	 */
	protected String featureVersion = FEATURE_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRestrictionLevel() <em>Restriction Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRestrictionLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String RESTRICTION_LEVEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRestrictionLevel() <em>Restriction Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRestrictionLevel()
	 * @generated
	 * @ordered
	 */
	protected String restrictionLevel = RESTRICTION_LEVEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductVersionFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicPackage.Literals.PRODUCT_VERSION_FEATURE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER, oldFeatureIdentifier, featureIdentifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFeatureVersion() {
		return featureVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeatureVersion(String newFeatureVersion) {
		String oldFeatureVersion = featureVersion;
		featureVersion = newFeatureVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION, oldFeatureVersion, featureVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRestrictionLevel() {
		return restrictionLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRestrictionLevel(String newRestrictionLevel) {
		String oldRestrictionLevel = restrictionLevel;
		restrictionLevel = newRestrictionLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL, oldRestrictionLevel, restrictionLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductVersion getProductVersion() {
		if (eContainerFeatureID() != LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION) return null;
		return (ProductVersion)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProductVersion(ProductVersion newProductVersion, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProductVersion, LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductVersion(ProductVersion newProductVersion) {
		if (newProductVersion != eInternalContainer() || (eContainerFeatureID() != LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION && newProductVersion != null)) {
			if (EcoreUtil.isAncestor(this, newProductVersion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProductVersion != null)
				msgs = ((InternalEObject)newProductVersion).eInverseAdd(this, LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES, ProductVersion.class, msgs);
			msgs = basicSetProductVersion(newProductVersion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION, newProductVersion, newProductVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProductVersion((ProductVersion)otherEnd, msgs);
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
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				return basicSetProductVersion(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				return eInternalContainer().eInverseRemove(this, LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES, ProductVersion.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
				return getFeatureIdentifier();
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
				return getFeatureVersion();
			case LicPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
				return getRestrictionLevel();
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				return getProductVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
				setFeatureIdentifier((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
				setFeatureVersion((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
				setRestrictionLevel((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				setProductVersion((ProductVersion)newValue);
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
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
				setFeatureIdentifier(FEATURE_IDENTIFIER_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
				setFeatureVersion(FEATURE_VERSION_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
				setRestrictionLevel(RESTRICTION_LEVEL_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				setProductVersion((ProductVersion)null);
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
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
				return FEATURE_IDENTIFIER_EDEFAULT == null ? featureIdentifier != null : !FEATURE_IDENTIFIER_EDEFAULT.equals(featureIdentifier);
			case LicPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
				return FEATURE_VERSION_EDEFAULT == null ? featureVersion != null : !FEATURE_VERSION_EDEFAULT.equals(featureVersion);
			case LicPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
				return RESTRICTION_LEVEL_EDEFAULT == null ? restrictionLevel != null : !RESTRICTION_LEVEL_EDEFAULT.equals(restrictionLevel);
			case LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
				return getProductVersion() != null;
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
		result.append(" (featureIdentifier: "); //$NON-NLS-1$
		result.append(featureIdentifier);
		result.append(", featureVersion: "); //$NON-NLS-1$
		result.append(featureVersion);
		result.append(", restrictionLevel: "); //$NON-NLS-1$
		result.append(restrictionLevel);
		result.append(')');
		return result.toString();
	}

} //ProductVersionFeatureImpl
