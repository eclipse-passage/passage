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
package org.eclipse.passage.lic.features.model.impl;

import java.util.Collection;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.features.model.api.FeatureVersion;

import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl#getFeatureSet <em>Feature Set</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl#getFeatureVersions <em>Feature Versions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpl extends MinimalEObjectImpl.Container implements Feature {
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
	 * The default value of the '{@link #getProvider() <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvider()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProvider() <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvider()
	 * @generated
	 * @ordered
	 */
	private String provider = PROVIDER_EDEFAULT;

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
	 * The cached value of the '{@link #getFeatureVersions() <em>Feature Versions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureVersions()
	 * @generated
	 * @ordered
	 */
	protected EList<FeatureVersion> featureVersions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FeaturesPackage.eINSTANCE.getFeature();
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
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE__IDENTIFIER, oldIdentifier,
					identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProvider() {
		return provider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProvider(String newProvider) {
		String oldProvider = provider;
		provider = newProvider;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE__PROVIDER, oldProvider,
					provider));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE__DESCRIPTION, oldDescription,
					description));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureSet getFeatureSet() {
		if (eContainerFeatureID() != FeaturesPackage.FEATURE__FEATURE_SET) {
			return null;
		}
		return (FeatureSet) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeatureSet(FeatureSet newFeatureSet, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newFeatureSet, FeaturesPackage.FEATURE__FEATURE_SET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeatureSet(FeatureSet newFeatureSet) {
		if (newFeatureSet != eInternalContainer()
				|| (eContainerFeatureID() != FeaturesPackage.FEATURE__FEATURE_SET && newFeatureSet != null)) {
			if (EcoreUtil.isAncestor(this, newFeatureSet)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newFeatureSet != null)
				msgs = ((InternalEObject) newFeatureSet).eInverseAdd(this, FeaturesPackage.FEATURE_SET__FEATURES,
						FeatureSet.class, msgs);
			msgs = basicSetFeatureSet(newFeatureSet, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE__FEATURE_SET, newFeatureSet,
					newFeatureSet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<FeatureVersion> getFeatureVersions() {
		if (featureVersions == null) {
			featureVersions = new EObjectContainmentWithInverseEList<FeatureVersion>(FeatureVersion.class, this,
					FeaturesPackage.FEATURE__FEATURE_VERSIONS, FeaturesPackage.FEATURE_VERSION__FEATURE);
		}
		return featureVersions;
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
		case FeaturesPackage.FEATURE__FEATURE_SET:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetFeatureSet((FeatureSet) otherEnd, msgs);
		case FeaturesPackage.FEATURE__FEATURE_VERSIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getFeatureVersions()).basicAdd(otherEnd, msgs);
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
		case FeaturesPackage.FEATURE__FEATURE_SET:
			return basicSetFeatureSet(null, msgs);
		case FeaturesPackage.FEATURE__FEATURE_VERSIONS:
			return ((InternalEList<?>) getFeatureVersions()).basicRemove(otherEnd, msgs);
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
		case FeaturesPackage.FEATURE__FEATURE_SET:
			return eInternalContainer().eInverseRemove(this, FeaturesPackage.FEATURE_SET__FEATURES, FeatureSet.class,
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
		case FeaturesPackage.FEATURE__IDENTIFIER:
			return getIdentifier();
		case FeaturesPackage.FEATURE__PROVIDER:
			return getProvider();
		case FeaturesPackage.FEATURE__NAME:
			return getName();
		case FeaturesPackage.FEATURE__DESCRIPTION:
			return getDescription();
		case FeaturesPackage.FEATURE__FEATURE_SET:
			return getFeatureSet();
		case FeaturesPackage.FEATURE__FEATURE_VERSIONS:
			return getFeatureVersions();
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
		case FeaturesPackage.FEATURE__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case FeaturesPackage.FEATURE__PROVIDER:
			setProvider((String) newValue);
			return;
		case FeaturesPackage.FEATURE__NAME:
			setName((String) newValue);
			return;
		case FeaturesPackage.FEATURE__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case FeaturesPackage.FEATURE__FEATURE_SET:
			setFeatureSet((FeatureSet) newValue);
			return;
		case FeaturesPackage.FEATURE__FEATURE_VERSIONS:
			getFeatureVersions().clear();
			getFeatureVersions().addAll((Collection<? extends FeatureVersion>) newValue);
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
		case FeaturesPackage.FEATURE__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case FeaturesPackage.FEATURE__PROVIDER:
			setProvider(PROVIDER_EDEFAULT);
			return;
		case FeaturesPackage.FEATURE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case FeaturesPackage.FEATURE__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case FeaturesPackage.FEATURE__FEATURE_SET:
			setFeatureSet((FeatureSet) null);
			return;
		case FeaturesPackage.FEATURE__FEATURE_VERSIONS:
			getFeatureVersions().clear();
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
		case FeaturesPackage.FEATURE__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case FeaturesPackage.FEATURE__PROVIDER:
			return !Objects.equals(PROVIDER_EDEFAULT, provider);
		case FeaturesPackage.FEATURE__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case FeaturesPackage.FEATURE__DESCRIPTION:
			return !Objects.equals(DESCRIPTION_EDEFAULT, description);
		case FeaturesPackage.FEATURE__FEATURE_SET:
			return getFeatureSet() != null;
		case FeaturesPackage.FEATURE__FEATURE_VERSIONS:
			return featureVersions != null && !featureVersions.isEmpty();
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
		result.append(", provider: "); //$NON-NLS-1$
		result.append(provider);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //FeatureImpl
