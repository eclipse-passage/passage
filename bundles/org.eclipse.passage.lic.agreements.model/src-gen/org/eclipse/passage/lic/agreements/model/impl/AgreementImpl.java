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
package org.eclipse.passage.lic.agreements.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.agreements.model.api.Agreement;

import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agreement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.impl.AgreementImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.impl.AgreementImpl#getSpdx <em>Spdx</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.impl.AgreementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.impl.AgreementImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.agreements.model.impl.AgreementImpl#getMime <em>Mime</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AgreementImpl extends MinimalEObjectImpl.Container implements Agreement {
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
	 * The default value of the '{@link #getSpdx() <em>Spdx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpdx()
	 * @generated
	 * @ordered
	 */
	protected static final String SPDX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpdx() <em>Spdx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpdx()
	 * @generated
	 * @ordered
	 */
	private String spdx = SPDX_EDEFAULT;

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
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	private String file = FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMime() <em>Mime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMime()
	 * @generated
	 * @ordered
	 */
	protected static final String MIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMime() <em>Mime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMime()
	 * @generated
	 * @ordered
	 */
	private String mime = MIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgreementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AgreementsPackage.eINSTANCE.getAgreement();
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
			eNotify(new ENotificationImpl(this, Notification.SET, AgreementsPackage.AGREEMENT__IDENTIFIER,
					oldIdentifier, identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSpdx() {
		return spdx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpdx(String newSpdx) {
		String oldSpdx = spdx;
		spdx = newSpdx;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, AgreementsPackage.AGREEMENT__SPDX, oldSpdx, spdx));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AgreementsPackage.AGREEMENT__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFile(String newFile) {
		String oldFile = file;
		file = newFile;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, AgreementsPackage.AGREEMENT__FILE, oldFile, file));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMime() {
		return mime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMime(String newMime) {
		String oldMime = mime;
		mime = newMime;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, AgreementsPackage.AGREEMENT__MIME, oldMime, mime));
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
		case AgreementsPackage.AGREEMENT__IDENTIFIER:
			return getIdentifier();
		case AgreementsPackage.AGREEMENT__SPDX:
			return getSpdx();
		case AgreementsPackage.AGREEMENT__NAME:
			return getName();
		case AgreementsPackage.AGREEMENT__FILE:
			return getFile();
		case AgreementsPackage.AGREEMENT__MIME:
			return getMime();
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
		case AgreementsPackage.AGREEMENT__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case AgreementsPackage.AGREEMENT__SPDX:
			setSpdx((String) newValue);
			return;
		case AgreementsPackage.AGREEMENT__NAME:
			setName((String) newValue);
			return;
		case AgreementsPackage.AGREEMENT__FILE:
			setFile((String) newValue);
			return;
		case AgreementsPackage.AGREEMENT__MIME:
			setMime((String) newValue);
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
		case AgreementsPackage.AGREEMENT__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case AgreementsPackage.AGREEMENT__SPDX:
			setSpdx(SPDX_EDEFAULT);
			return;
		case AgreementsPackage.AGREEMENT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case AgreementsPackage.AGREEMENT__FILE:
			setFile(FILE_EDEFAULT);
			return;
		case AgreementsPackage.AGREEMENT__MIME:
			setMime(MIME_EDEFAULT);
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
		case AgreementsPackage.AGREEMENT__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case AgreementsPackage.AGREEMENT__SPDX:
			return !Objects.equals(SPDX_EDEFAULT, spdx);
		case AgreementsPackage.AGREEMENT__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case AgreementsPackage.AGREEMENT__FILE:
			return !Objects.equals(FILE_EDEFAULT, file);
		case AgreementsPackage.AGREEMENT__MIME:
			return !Objects.equals(MIME_EDEFAULT, mime);
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
		result.append(", spdx: "); //$NON-NLS-1$
		result.append(spdx);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", file: "); //$NON-NLS-1$
		result.append(file);
		result.append(", mime: "); //$NON-NLS-1$
		result.append(mime);
		result.append(')');
		return result.toString();
	}

} //AgreementImpl
