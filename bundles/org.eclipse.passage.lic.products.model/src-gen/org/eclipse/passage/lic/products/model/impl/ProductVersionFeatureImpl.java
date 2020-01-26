/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.products.model.impl;

import java.util.Objects;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model object '<em><b>Product Version
 * Feature</b></em>'.
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionFeatureImpl#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionFeatureImpl#getFeatureVersion <em>Feature Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionFeatureImpl#getRestrictionLevel <em>Restriction Level</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionFeatureImpl#getProductVersion <em>Product Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductVersionFeatureImpl extends MinimalEObjectImpl.Container implements ProductVersionFeature {
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
	private String featureIdentifier = FEATURE_IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getFeatureVersion() <em>Feature Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getFeatureVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeatureVersion() <em>Feature Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getFeatureVersion()
	 * @generated
	 * @ordered
	 */
	private String featureVersion = FEATURE_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRestrictionLevel() <em>Restriction Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getRestrictionLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String RESTRICTION_LEVEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRestrictionLevel() <em>Restriction Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getRestrictionLevel()
	 * @generated
	 * @ordered
	 */
	private String restrictionLevel = RESTRICTION_LEVEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductVersionFeatureImpl() {
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
		return ProductsPackage.eINSTANCE.getProductVersionFeature();
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER, oldFeatureIdentifier,
					featureIdentifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFeatureVersion() {
		return featureVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeatureVersion(String newFeatureVersion) {
		String oldFeatureVersion = featureVersion;
		featureVersion = newFeatureVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION, oldFeatureVersion, featureVersion));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRestrictionLevel() {
		return restrictionLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRestrictionLevel(String newRestrictionLevel) {
		String oldRestrictionLevel = restrictionLevel;
		restrictionLevel = newRestrictionLevel;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					ProductsPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL, oldRestrictionLevel, restrictionLevel));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductVersion getProductVersion() {
		if (eContainerFeatureID() != ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION) {
			return null;
		}
		return (ProductVersion) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProductVersion(ProductVersion newProductVersion, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newProductVersion,
				ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductVersion(ProductVersion newProductVersion) {
		if (newProductVersion != eInternalContainer()
				|| (eContainerFeatureID() != ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION
						&& newProductVersion != null)) {
			if (EcoreUtil.isAncestor(this, newProductVersion)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newProductVersion != null)
				msgs = ((InternalEObject) newProductVersion).eInverseAdd(this,
						ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES, ProductVersion.class, msgs);
			msgs = basicSetProductVersion(newProductVersion, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION, newProductVersion, newProductVersion));
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetProductVersion((ProductVersion) otherEnd, msgs);
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			return basicSetProductVersion(null, msgs);
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			return eInternalContainer().eInverseRemove(this, ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES,
					ProductVersion.class, msgs);
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
			return getFeatureIdentifier();
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
			return getFeatureVersion();
		case ProductsPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
			return getRestrictionLevel();
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			return getProductVersion();
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
			setFeatureIdentifier((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
			setFeatureVersion((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
			setRestrictionLevel((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			setProductVersion((ProductVersion) newValue);
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
			setFeatureIdentifier(FEATURE_IDENTIFIER_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
			setFeatureVersion(FEATURE_VERSION_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
			setRestrictionLevel(RESTRICTION_LEVEL_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			setProductVersion((ProductVersion) null);
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
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
			return !Objects.equals(FEATURE_IDENTIFIER_EDEFAULT, featureIdentifier);
		case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_VERSION:
			return !Objects.equals(FEATURE_VERSION_EDEFAULT, featureVersion);
		case ProductsPackage.PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL:
			return !Objects.equals(RESTRICTION_LEVEL_EDEFAULT, restrictionLevel);
		case ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION:
			return getProductVersion() != null;
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
		result.append(", featureVersion: "); //$NON-NLS-1$
		result.append(featureVersion);
		result.append(", restrictionLevel: "); //$NON-NLS-1$
		result.append(restrictionLevel);
		result.append(')');
		return result.toString();
	}

} // ProductVersionFeatureImpl
