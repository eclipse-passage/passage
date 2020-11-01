/**
 */
package org.eclipse.passage.lic.floating.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FloatingServerConnection;

import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Server Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingServerConnectionImpl#getIp <em>Ip</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingServerConnectionImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingServerConnectionImpl#getAuthentication <em>Authentication</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatingServerConnectionImpl extends MinimalEObjectImpl.Container implements FloatingServerConnection {
	/**
	 * The default value of the '{@link #getIp() <em>Ip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIp()
	 * @generated
	 * @ordered
	 */
	protected static final String IP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIp() <em>Ip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIp()
	 * @generated
	 * @ordered
	 */
	private String ip = IP_EDEFAULT;

	/**
	 * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected static final int PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	private int port = PORT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAuthentication() <em>Authentication</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthentication()
	 * @generated
	 * @ordered
	 */
	protected EvaluationInstructions authentication;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatingServerConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FloatingPackage.eINSTANCE.getFloatingServerConnection();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIp() {
		return ip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIp(String newIp) {
		String oldIp = ip;
		ip = newIp;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_SERVER_CONNECTION__IP, oldIp,
					ip));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getPort() {
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPort(int newPort) {
		int oldPort = port;
		port = newPort;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_SERVER_CONNECTION__PORT,
					oldPort, port));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EvaluationInstructions getAuthentication() {
		if (authentication != null && authentication.eIsProxy()) {
			InternalEObject oldAuthentication = (InternalEObject) authentication;
			authentication = (EvaluationInstructions) eResolveProxy(oldAuthentication);
			if (authentication != oldAuthentication) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							FloatingPackage.FLOATING_SERVER_CONNECTION__AUTHENTICATION, oldAuthentication,
							authentication));
				}
			}
		}
		return authentication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationInstructions basicGetAuthentication() {
		return authentication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAuthentication(EvaluationInstructions newAuthentication) {
		EvaluationInstructions oldAuthentication = authentication;
		authentication = newAuthentication;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					FloatingPackage.FLOATING_SERVER_CONNECTION__AUTHENTICATION, oldAuthentication, authentication));
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
		case FloatingPackage.FLOATING_SERVER_CONNECTION__IP:
			return getIp();
		case FloatingPackage.FLOATING_SERVER_CONNECTION__PORT:
			return getPort();
		case FloatingPackage.FLOATING_SERVER_CONNECTION__AUTHENTICATION:
			if (resolve)
				return getAuthentication();
			return basicGetAuthentication();
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
		case FloatingPackage.FLOATING_SERVER_CONNECTION__IP:
			setIp((String) newValue);
			return;
		case FloatingPackage.FLOATING_SERVER_CONNECTION__PORT:
			setPort((Integer) newValue);
			return;
		case FloatingPackage.FLOATING_SERVER_CONNECTION__AUTHENTICATION:
			setAuthentication((EvaluationInstructions) newValue);
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
		case FloatingPackage.FLOATING_SERVER_CONNECTION__IP:
			setIp(IP_EDEFAULT);
			return;
		case FloatingPackage.FLOATING_SERVER_CONNECTION__PORT:
			setPort(PORT_EDEFAULT);
			return;
		case FloatingPackage.FLOATING_SERVER_CONNECTION__AUTHENTICATION:
			setAuthentication((EvaluationInstructions) null);
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
		case FloatingPackage.FLOATING_SERVER_CONNECTION__IP:
			return !Objects.equals(IP_EDEFAULT, ip);
		case FloatingPackage.FLOATING_SERVER_CONNECTION__PORT:
			return port != PORT_EDEFAULT;
		case FloatingPackage.FLOATING_SERVER_CONNECTION__AUTHENTICATION:
			return authentication != null;
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
		result.append(" (ip: "); //$NON-NLS-1$
		result.append(ip);
		result.append(", port: "); //$NON-NLS-1$
		result.append(port);
		result.append(')');
		return result.toString();
	}

} //FloatingServerConnectionImpl
