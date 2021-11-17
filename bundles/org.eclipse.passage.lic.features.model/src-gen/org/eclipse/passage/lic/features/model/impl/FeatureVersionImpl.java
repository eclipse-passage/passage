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

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureVersion;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureVersionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureVersionImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.features.model.impl.FeatureVersionImpl#getNews <em>News</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureVersionImpl extends MinimalEObjectImpl.Container implements FeatureVersion {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	private String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNews() <em>News</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNews()
	 * @generated
	 * @ordered
	 */
	protected static final String NEWS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNews() <em>News</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNews()
	 * @generated
	 * @ordered
	 */
	private String news = NEWS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureVersionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FeaturesPackage.eINSTANCE.getFeatureVersion();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE_VERSION__VERSION, oldVersion,
					version));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Feature getFeature() {
		if (eContainerFeatureID() != FeaturesPackage.FEATURE_VERSION__FEATURE) {
			return null;
		}
		return (Feature) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeature(Feature newFeature, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newFeature, FeaturesPackage.FEATURE_VERSION__FEATURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeature(Feature newFeature) {
		if (newFeature != eInternalContainer()
				|| (eContainerFeatureID() != FeaturesPackage.FEATURE_VERSION__FEATURE && newFeature != null)) {
			if (EcoreUtil.isAncestor(this, newFeature)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newFeature != null)
				msgs = ((InternalEObject) newFeature).eInverseAdd(this, FeaturesPackage.FEATURE__FEATURE_VERSIONS,
						Feature.class, msgs);
			msgs = basicSetFeature(newFeature, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE_VERSION__FEATURE, newFeature,
					newFeature));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNews() {
		return news;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNews(String newNews) {
		String oldNews = news;
		news = newNews;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FeaturesPackage.FEATURE_VERSION__NEWS, oldNews,
					news));
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
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetFeature((Feature) otherEnd, msgs);
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
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			return basicSetFeature(null, msgs);
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
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			return eInternalContainer().eInverseRemove(this, FeaturesPackage.FEATURE__FEATURE_VERSIONS, Feature.class,
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
		case FeaturesPackage.FEATURE_VERSION__VERSION:
			return getVersion();
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			return getFeature();
		case FeaturesPackage.FEATURE_VERSION__NEWS:
			return getNews();
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
		case FeaturesPackage.FEATURE_VERSION__VERSION:
			setVersion((String) newValue);
			return;
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			setFeature((Feature) newValue);
			return;
		case FeaturesPackage.FEATURE_VERSION__NEWS:
			setNews((String) newValue);
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
		case FeaturesPackage.FEATURE_VERSION__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			setFeature((Feature) null);
			return;
		case FeaturesPackage.FEATURE_VERSION__NEWS:
			setNews(NEWS_EDEFAULT);
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
		case FeaturesPackage.FEATURE_VERSION__VERSION:
			return !Objects.equals(VERSION_EDEFAULT, version);
		case FeaturesPackage.FEATURE_VERSION__FEATURE:
			return getFeature() != null;
		case FeaturesPackage.FEATURE_VERSION__NEWS:
			return !Objects.equals(NEWS_EDEFAULT, news);
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
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", news: "); //$NON-NLS-1$
		result.append(news);
		result.append(')');
		return result.toString();
	}

} //FeatureVersionImpl
