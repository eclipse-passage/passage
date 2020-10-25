/**
 */
package org.eclipse.passage.lic.floating.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.floating.model.api.FloatingServerConnection;

import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>License Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicenseAccessImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicenseAccessImpl#getServer <em>Server</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicenseAccessImpl#getOriginLicensePack <em>Origin License Pack</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatingLicenseAccessImpl extends MinimalEObjectImpl.Container implements FloatingLicenseAccess {
	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	private String user = USER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getServer() <em>Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected FloatingServerConnection server;

	/**
	 * The default value of the '{@link #getOriginLicensePack() <em>Origin License Pack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginLicensePack()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGIN_LICENSE_PACK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginLicensePack() <em>Origin License Pack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginLicensePack()
	 * @generated
	 * @ordered
	 */
	private String originLicensePack = ORIGIN_LICENSE_PACK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatingLicenseAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FloatingPackage.eINSTANCE.getFloatingLicenseAccess();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUser(String newUser) {
		String oldUser = user;
		user = newUser;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_LICENSE_ACCESS__USER,
					oldUser, user));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatingServerConnection getServer() {
		if (server != null && server.eIsProxy()) {
			InternalEObject oldServer = (InternalEObject) server;
			server = (FloatingServerConnection) eResolveProxy(oldServer);
			if (server != oldServer) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							FloatingPackage.FLOATING_LICENSE_ACCESS__SERVER, oldServer, server));
				}
			}
		}
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingServerConnection basicGetServer() {
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setServer(FloatingServerConnection newServer) {
		FloatingServerConnection oldServer = server;
		server = newServer;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_LICENSE_ACCESS__SERVER,
					oldServer, server));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOriginLicensePack() {
		return originLicensePack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOriginLicensePack(String newOriginLicensePack) {
		String oldOriginLicensePack = originLicensePack;
		originLicensePack = newOriginLicensePack;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					FloatingPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK, oldOriginLicensePack,
					originLicensePack));
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
		case FloatingPackage.FLOATING_LICENSE_ACCESS__USER:
			return getUser();
		case FloatingPackage.FLOATING_LICENSE_ACCESS__SERVER:
			if (resolve)
				return getServer();
			return basicGetServer();
		case FloatingPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
			return getOriginLicensePack();
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
		case FloatingPackage.FLOATING_LICENSE_ACCESS__USER:
			setUser((String) newValue);
			return;
		case FloatingPackage.FLOATING_LICENSE_ACCESS__SERVER:
			setServer((FloatingServerConnection) newValue);
			return;
		case FloatingPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
			setOriginLicensePack((String) newValue);
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
		case FloatingPackage.FLOATING_LICENSE_ACCESS__USER:
			setUser(USER_EDEFAULT);
			return;
		case FloatingPackage.FLOATING_LICENSE_ACCESS__SERVER:
			setServer((FloatingServerConnection) null);
			return;
		case FloatingPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
			setOriginLicensePack(ORIGIN_LICENSE_PACK_EDEFAULT);
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
		case FloatingPackage.FLOATING_LICENSE_ACCESS__USER:
			return !Objects.equals(USER_EDEFAULT, user);
		case FloatingPackage.FLOATING_LICENSE_ACCESS__SERVER:
			return server != null;
		case FloatingPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
			return !Objects.equals(ORIGIN_LICENSE_PACK_EDEFAULT, originLicensePack);
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
		result.append(" (user: "); //$NON-NLS-1$
		result.append(user);
		result.append(", originLicensePack: "); //$NON-NLS-1$
		result.append(originLicensePack);
		result.append(')');
		return result.toString();
	}

} //FloatingLicenseAccessImpl
