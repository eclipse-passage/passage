/**
 */
package org.eclipse.passage.lic.floating.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriod;
import org.eclipse.passage.lic.floating.model.api.VersionMatch;

import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Grant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl#getValid <em>Valid</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl#getPack <em>Pack</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureGrantImpl extends MinimalEObjectImpl.Container implements FeatureGrant {
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
	 * The default value of the '{@link #getFeature() <em>Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	private String feature = FEATURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected VersionMatch version;

	/**
	 * The cached value of the '{@link #getValid() <em>Valid</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValid()
	 * @generated
	 * @ordered
	 */
	protected ValidityPeriod valid;

	/**
	 * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	protected static final int CAPACITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	private int capacity = CAPACITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPack() <em>Pack</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPack()
	 * @generated
	 * @ordered
	 */
	protected FloatingLicensePack pack;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureGrantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FloatingPackage.eINSTANCE.getFeatureGrant();
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
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FEATURE_GRANT__IDENTIFIER,
					oldIdentifier, identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeature(String newFeature) {
		String oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FEATURE_GRANT__FEATURE, oldFeature,
					feature));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VersionMatch getVersion() {
		if (version != null && version.eIsProxy()) {
			InternalEObject oldVersion = (InternalEObject) version;
			version = (VersionMatch) eResolveProxy(oldVersion);
			if (version != oldVersion) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FloatingPackage.FEATURE_GRANT__VERSION,
							oldVersion, version));
				}
			}
		}
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VersionMatch basicGetVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(VersionMatch newVersion) {
		VersionMatch oldVersion = version;
		version = newVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FEATURE_GRANT__VERSION, oldVersion,
					version));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidityPeriod getValid() {
		if (valid != null && valid.eIsProxy()) {
			InternalEObject oldValid = (InternalEObject) valid;
			valid = (ValidityPeriod) eResolveProxy(oldValid);
			if (valid != oldValid) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FloatingPackage.FEATURE_GRANT__VALID,
							oldValid, valid));
				}
			}
		}
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidityPeriod basicGetValid() {
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValid(ValidityPeriod newValid) {
		ValidityPeriod oldValid = valid;
		valid = newValid;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FEATURE_GRANT__VALID, oldValid,
					valid));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCapacity(int newCapacity) {
		int oldCapacity = capacity;
		capacity = newCapacity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FEATURE_GRANT__CAPACITY, oldCapacity,
					capacity));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingLicensePack getPack() {
		if (pack != null && pack.eIsProxy()) {
			InternalEObject oldPack = (InternalEObject) pack;
			pack = (FloatingLicensePack) eResolveProxy(oldPack);
			if (pack != oldPack) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FloatingPackage.FEATURE_GRANT__PACK,
							oldPack, pack));
				}
			}
		}
		return pack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingLicensePack basicGetPack() {
		return pack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPack(FloatingLicensePack newPack, NotificationChain msgs) {
		FloatingLicensePack oldPack = pack;
		pack = newPack;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					FloatingPackage.FEATURE_GRANT__PACK, oldPack, newPack);
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
	 * @generated
	 */
	@Override
	public void setPack(FloatingLicensePack newPack) {
		if (newPack != pack) {
			NotificationChain msgs = null;
			if (pack != null)
				msgs = ((InternalEObject) pack).eInverseRemove(this, FloatingPackage.FLOATING_LICENSE_PACK__FEATURES,
						FloatingLicensePack.class, msgs);
			if (newPack != null)
				msgs = ((InternalEObject) newPack).eInverseAdd(this, FloatingPackage.FLOATING_LICENSE_PACK__FEATURES,
						FloatingLicensePack.class, msgs);
			msgs = basicSetPack(newPack, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FEATURE_GRANT__PACK, newPack,
					newPack));
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
		case FloatingPackage.FEATURE_GRANT__PACK:
			if (pack != null)
				msgs = ((InternalEObject) pack).eInverseRemove(this, FloatingPackage.FLOATING_LICENSE_PACK__FEATURES,
						FloatingLicensePack.class, msgs);
			return basicSetPack((FloatingLicensePack) otherEnd, msgs);
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
		case FloatingPackage.FEATURE_GRANT__PACK:
			return basicSetPack(null, msgs);
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
		case FloatingPackage.FEATURE_GRANT__IDENTIFIER:
			return getIdentifier();
		case FloatingPackage.FEATURE_GRANT__FEATURE:
			return getFeature();
		case FloatingPackage.FEATURE_GRANT__VERSION:
			if (resolve)
				return getVersion();
			return basicGetVersion();
		case FloatingPackage.FEATURE_GRANT__VALID:
			if (resolve)
				return getValid();
			return basicGetValid();
		case FloatingPackage.FEATURE_GRANT__CAPACITY:
			return getCapacity();
		case FloatingPackage.FEATURE_GRANT__PACK:
			if (resolve)
				return getPack();
			return basicGetPack();
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
		case FloatingPackage.FEATURE_GRANT__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case FloatingPackage.FEATURE_GRANT__FEATURE:
			setFeature((String) newValue);
			return;
		case FloatingPackage.FEATURE_GRANT__VERSION:
			setVersion((VersionMatch) newValue);
			return;
		case FloatingPackage.FEATURE_GRANT__VALID:
			setValid((ValidityPeriod) newValue);
			return;
		case FloatingPackage.FEATURE_GRANT__CAPACITY:
			setCapacity((Integer) newValue);
			return;
		case FloatingPackage.FEATURE_GRANT__PACK:
			setPack((FloatingLicensePack) newValue);
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
		case FloatingPackage.FEATURE_GRANT__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case FloatingPackage.FEATURE_GRANT__FEATURE:
			setFeature(FEATURE_EDEFAULT);
			return;
		case FloatingPackage.FEATURE_GRANT__VERSION:
			setVersion((VersionMatch) null);
			return;
		case FloatingPackage.FEATURE_GRANT__VALID:
			setValid((ValidityPeriod) null);
			return;
		case FloatingPackage.FEATURE_GRANT__CAPACITY:
			setCapacity(CAPACITY_EDEFAULT);
			return;
		case FloatingPackage.FEATURE_GRANT__PACK:
			setPack((FloatingLicensePack) null);
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
		case FloatingPackage.FEATURE_GRANT__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case FloatingPackage.FEATURE_GRANT__FEATURE:
			return !Objects.equals(FEATURE_EDEFAULT, feature);
		case FloatingPackage.FEATURE_GRANT__VERSION:
			return version != null;
		case FloatingPackage.FEATURE_GRANT__VALID:
			return valid != null;
		case FloatingPackage.FEATURE_GRANT__CAPACITY:
			return capacity != CAPACITY_EDEFAULT;
		case FloatingPackage.FEATURE_GRANT__PACK:
			return pack != null;
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
		result.append(", feature: "); //$NON-NLS-1$
		result.append(feature);
		result.append(", capacity: "); //$NON-NLS-1$
		result.append(capacity);
		result.append(')');
		return result.toString();
	}

} //FeatureGrantImpl
