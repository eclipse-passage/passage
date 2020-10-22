/**
 */
package org.eclipse.passage.lic.floating.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.UserGrant;

import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>License Pack</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicensePackImpl#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicensePackImpl#getHost <em>Host</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicensePackImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicensePackImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatingLicensePackImpl extends MinimalEObjectImpl.Container implements FloatingLicensePack {
	/**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	protected LicenseRequisites license;

	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected FloatingServer host;

	/**
	 * The cached value of the '{@link #getUsers() <em>Users</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<UserGrant> users;

	/**
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<FeatureGrant> features;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatingLicensePackImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FloatingPackage.eINSTANCE.getFloatingLicensePack();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicenseRequisites getLicense() {
		if (license != null && license.eIsProxy()) {
			InternalEObject oldLicense = (InternalEObject) license;
			license = (LicenseRequisites) eResolveProxy(oldLicense);
			if (license != oldLicense) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							FloatingPackage.FLOATING_LICENSE_PACK__LICENSE, oldLicense, license));
				}
			}
		}
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicenseRequisites basicGetLicense() {
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLicense(LicenseRequisites newLicense) {
		LicenseRequisites oldLicense = license;
		license = newLicense;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_LICENSE_PACK__LICENSE,
					oldLicense, license));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingServer getHost() {
		if (host != null && host.eIsProxy()) {
			InternalEObject oldHost = (InternalEObject) host;
			host = (FloatingServer) eResolveProxy(oldHost);
			if (host != oldHost) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							FloatingPackage.FLOATING_LICENSE_PACK__HOST, oldHost, host));
				}
			}
		}
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingServer basicGetHost() {
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHost(FloatingServer newHost) {
		FloatingServer oldHost = host;
		host = newHost;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_LICENSE_PACK__HOST, oldHost,
					host));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<UserGrant> getUsers() {
		if (users == null) {
			users = new EObjectResolvingEList<UserGrant>(UserGrant.class, this,
					FloatingPackage.FLOATING_LICENSE_PACK__USERS);
		}
		return users;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<FeatureGrant> getFeatures() {
		if (features == null) {
			features = new EObjectWithInverseResolvingEList<FeatureGrant>(FeatureGrant.class, this,
					FloatingPackage.FLOATING_LICENSE_PACK__FEATURES, FloatingPackage.FEATURE_GRANT__PACK);
		}
		return features;
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
		case FloatingPackage.FLOATING_LICENSE_PACK__FEATURES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getFeatures()).basicAdd(otherEnd, msgs);
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
		case FloatingPackage.FLOATING_LICENSE_PACK__FEATURES:
			return ((InternalEList<?>) getFeatures()).basicRemove(otherEnd, msgs);
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
		case FloatingPackage.FLOATING_LICENSE_PACK__LICENSE:
			if (resolve)
				return getLicense();
			return basicGetLicense();
		case FloatingPackage.FLOATING_LICENSE_PACK__HOST:
			if (resolve)
				return getHost();
			return basicGetHost();
		case FloatingPackage.FLOATING_LICENSE_PACK__USERS:
			return getUsers();
		case FloatingPackage.FLOATING_LICENSE_PACK__FEATURES:
			return getFeatures();
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
		case FloatingPackage.FLOATING_LICENSE_PACK__LICENSE:
			setLicense((LicenseRequisites) newValue);
			return;
		case FloatingPackage.FLOATING_LICENSE_PACK__HOST:
			setHost((FloatingServer) newValue);
			return;
		case FloatingPackage.FLOATING_LICENSE_PACK__USERS:
			getUsers().clear();
			getUsers().addAll((Collection<? extends UserGrant>) newValue);
			return;
		case FloatingPackage.FLOATING_LICENSE_PACK__FEATURES:
			getFeatures().clear();
			getFeatures().addAll((Collection<? extends FeatureGrant>) newValue);
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
		case FloatingPackage.FLOATING_LICENSE_PACK__LICENSE:
			setLicense((LicenseRequisites) null);
			return;
		case FloatingPackage.FLOATING_LICENSE_PACK__HOST:
			setHost((FloatingServer) null);
			return;
		case FloatingPackage.FLOATING_LICENSE_PACK__USERS:
			getUsers().clear();
			return;
		case FloatingPackage.FLOATING_LICENSE_PACK__FEATURES:
			getFeatures().clear();
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
		case FloatingPackage.FLOATING_LICENSE_PACK__LICENSE:
			return license != null;
		case FloatingPackage.FLOATING_LICENSE_PACK__HOST:
			return host != null;
		case FloatingPackage.FLOATING_LICENSE_PACK__USERS:
			return users != null && !users.isEmpty();
		case FloatingPackage.FLOATING_LICENSE_PACK__FEATURES:
			return features != null && !features.isEmpty();
		default:
			return super.eIsSet(featureID);
		}
	}

} //FloatingLicensePackImpl
