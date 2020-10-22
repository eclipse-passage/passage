/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Server</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingServerImpl#getIdentifier
 * <em>Identifier</em>}</li>
 * <li>{@link org.eclipse.passage.lic.floating.model.impl.FloatingServerImpl#getAuthentication
 * <em>Authentication</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatingServerImpl extends MinimalEObjectImpl.Container implements FloatingServer {
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	private String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAuthentication()
	 * <em>Authentication</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAuthentication()
	 * @generated
	 * @ordered
	 */
	protected EvaluationInstructions authentication;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FloatingServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FloatingPackage.eINSTANCE.getFloatingServer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_SERVER__IDENTIFIER,
					oldIdentifier, identifier));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
							FloatingPackage.FLOATING_SERVER__AUTHENTICATION, oldAuthentication, authentication));
				}
			}
		}
		return authentication;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EvaluationInstructions basicGetAuthentication() {
		return authentication;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAuthentication(EvaluationInstructions newAuthentication) {
		EvaluationInstructions oldAuthentication = authentication;
		authentication = newAuthentication;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.FLOATING_SERVER__AUTHENTICATION,
					oldAuthentication, authentication));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FloatingPackage.FLOATING_SERVER__IDENTIFIER:
			return getIdentifier();
		case FloatingPackage.FLOATING_SERVER__AUTHENTICATION:
			if (resolve)
				return getAuthentication();
			return basicGetAuthentication();
		default:
			return super.eGet(featureID, resolve, coreType);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case FloatingPackage.FLOATING_SERVER__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case FloatingPackage.FLOATING_SERVER__AUTHENTICATION:
			setAuthentication((EvaluationInstructions) newValue);
			return;
		default:
			super.eSet(featureID, newValue);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case FloatingPackage.FLOATING_SERVER__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case FloatingPackage.FLOATING_SERVER__AUTHENTICATION:
			setAuthentication((EvaluationInstructions) null);
			return;
		default:
			super.eUnset(featureID);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case FloatingPackage.FLOATING_SERVER__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case FloatingPackage.FLOATING_SERVER__AUTHENTICATION:
			return authentication != null;
		default:
			return super.eIsSet(featureID);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
		result.append(')');
		return result.toString();
	}

} // FloatingServerImpl
