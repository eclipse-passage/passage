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

import java.util.Collection;
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
import org.eclipse.passage.lic.model.api.Feature;
import org.eclipse.passage.lic.model.api.FeatureSet;
import org.eclipse.passage.lic.model.api.FeatureVersion;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.impl.FeatureImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.FeatureImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.FeatureImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.FeatureImpl#getFeatureSet <em>Feature Set</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.FeatureImpl#getFeatureVersions <em>Feature Versions</em>}</li>
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
  protected String identifier = IDENTIFIER_EDEFAULT;

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
  protected String name = NAME_EDEFAULT;

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
  protected String description = DESCRIPTION_EDEFAULT;

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
		return LicPackage.Literals.FEATURE;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getName() {
		return name;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.FEATURE__NAME, oldName, name));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getDescription() {
		return description;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.FEATURE__DESCRIPTION, oldDescription, description));
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureSet getFeatureSet() {
		if (eContainerFeatureID() != LicPackage.FEATURE__FEATURE_SET) return null;
		return (FeatureSet)eInternalContainer();
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeatureSet(FeatureSet newFeatureSet, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFeatureSet, LicPackage.FEATURE__FEATURE_SET, msgs);
		return msgs;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatureSet(FeatureSet newFeatureSet) {
		if (newFeatureSet != eInternalContainer() || (eContainerFeatureID() != LicPackage.FEATURE__FEATURE_SET && newFeatureSet != null)) {
			if (EcoreUtil.isAncestor(this, newFeatureSet))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFeatureSet != null)
				msgs = ((InternalEObject)newFeatureSet).eInverseAdd(this, LicPackage.FEATURE_SET__FEATURES, FeatureSet.class, msgs);
			msgs = basicSetFeatureSet(newFeatureSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.FEATURE__FEATURE_SET, newFeatureSet, newFeatureSet));
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FeatureVersion> getFeatureVersions() {
		if (featureVersions == null) {
			featureVersions = new EObjectContainmentWithInverseEList<FeatureVersion>(FeatureVersion.class, this, LicPackage.FEATURE__FEATURE_VERSIONS, LicPackage.FEATURE_VERSION__FEATURE);
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
			case LicPackage.FEATURE__FEATURE_SET:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFeatureSet((FeatureSet)otherEnd, msgs);
			case LicPackage.FEATURE__FEATURE_VERSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeatureVersions()).basicAdd(otherEnd, msgs);
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
			case LicPackage.FEATURE__FEATURE_SET:
				return basicSetFeatureSet(null, msgs);
			case LicPackage.FEATURE__FEATURE_VERSIONS:
				return ((InternalEList<?>)getFeatureVersions()).basicRemove(otherEnd, msgs);
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
			case LicPackage.FEATURE__FEATURE_SET:
				return eInternalContainer().eInverseRemove(this, LicPackage.FEATURE_SET__FEATURES, FeatureSet.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getIdentifier() {
		return identifier;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.FEATURE__IDENTIFIER, oldIdentifier, identifier));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LicPackage.FEATURE__IDENTIFIER:
				return getIdentifier();
			case LicPackage.FEATURE__NAME:
				return getName();
			case LicPackage.FEATURE__DESCRIPTION:
				return getDescription();
			case LicPackage.FEATURE__FEATURE_SET:
				return getFeatureSet();
			case LicPackage.FEATURE__FEATURE_VERSIONS:
				return getFeatureVersions();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case LicPackage.FEATURE__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case LicPackage.FEATURE__NAME:
				setName((String)newValue);
				return;
			case LicPackage.FEATURE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case LicPackage.FEATURE__FEATURE_SET:
				setFeatureSet((FeatureSet)newValue);
				return;
			case LicPackage.FEATURE__FEATURE_VERSIONS:
				getFeatureVersions().clear();
				getFeatureVersions().addAll((Collection<? extends FeatureVersion>)newValue);
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
			case LicPackage.FEATURE__IDENTIFIER:
				setIdentifier(IDENTIFIER_EDEFAULT);
				return;
			case LicPackage.FEATURE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case LicPackage.FEATURE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case LicPackage.FEATURE__FEATURE_SET:
				setFeatureSet((FeatureSet)null);
				return;
			case LicPackage.FEATURE__FEATURE_VERSIONS:
				getFeatureVersions().clear();
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
			case LicPackage.FEATURE__IDENTIFIER:
				return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
			case LicPackage.FEATURE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case LicPackage.FEATURE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case LicPackage.FEATURE__FEATURE_SET:
				return getFeatureSet() != null;
			case LicPackage.FEATURE__FEATURE_VERSIONS:
				return featureVersions != null && !featureVersions.isEmpty();
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
		result.append(" (identifier: "); //$NON-NLS-1$
		result.append(identifier);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //ComponentImpl
