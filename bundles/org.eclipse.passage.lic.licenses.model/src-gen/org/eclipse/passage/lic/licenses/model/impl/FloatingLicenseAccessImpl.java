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
package org.eclipse.passage.lic.licenses.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Floating License Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl#getServer <em>Server</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.FloatingLicenseAccessImpl#getOriginLicensePack <em>Origin License Pack</em>}</li>
 * </ul>
 *
 * @since 2.0
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
	 * The cached value of the '{@link #getServer() <em>Server</em>}' containment reference.
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
		return LicensesPackage.eINSTANCE.getFloatingLicenseAccess();
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.FLOATING_LICENSE_ACCESS__USER,
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
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServer(FloatingServerConnection newServer, NotificationChain msgs) {
		FloatingServerConnection oldServer = server;
		server = newServer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER, oldServer, newServer);
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
	public void setServer(FloatingServerConnection newServer) {
		if (newServer != server) {
			NotificationChain msgs = null;
			if (server != null)
				msgs = ((InternalEObject) server).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER, null, msgs);
			if (newServer != null)
				msgs = ((InternalEObject) newServer).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER, null, msgs);
			msgs = basicSetServer(newServer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER,
					newServer, newServer));
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
					LicensesPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK, oldOriginLicensePack,
					originLicensePack));
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
		case LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER:
			return basicSetServer(null, msgs);
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
		case LicensesPackage.FLOATING_LICENSE_ACCESS__USER:
			return getUser();
		case LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER:
			return getServer();
		case LicensesPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
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
		case LicensesPackage.FLOATING_LICENSE_ACCESS__USER:
			setUser((String) newValue);
			return;
		case LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER:
			setServer((FloatingServerConnection) newValue);
			return;
		case LicensesPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
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
		case LicensesPackage.FLOATING_LICENSE_ACCESS__USER:
			setUser(USER_EDEFAULT);
			return;
		case LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER:
			setServer((FloatingServerConnection) null);
			return;
		case LicensesPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
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
		case LicensesPackage.FLOATING_LICENSE_ACCESS__USER:
			return !Objects.equals(USER_EDEFAULT, user);
		case LicensesPackage.FLOATING_LICENSE_ACCESS__SERVER:
			return server != null;
		case LicensesPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
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
