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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.licenses.model.api.AgreementData;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agreement Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getHashAlgo <em>Hash Algo</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getHash <em>Hash</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.licenses.model.impl.AgreementDataImpl#getContentType <em>Content Type</em>}</li>
 * </ul>
 *
 * @since 2.1
 * @generated
 */
public class AgreementDataImpl extends MinimalEObjectImpl.Container implements AgreementData {
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
	 * The default value of the '{@link #getHashAlgo() <em>Hash Algo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHashAlgo()
	 * @generated
	 * @ordered
	 */
	protected static final String HASH_ALGO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHashAlgo() <em>Hash Algo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHashAlgo()
	 * @generated
	 * @ordered
	 */
	private String hashAlgo = HASH_ALGO_EDEFAULT;

	/**
	 * The default value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] HASH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	private byte[] hash = HASH_EDEFAULT;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	private byte[] content = CONTENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getContentType() <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentType()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContentType() <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentType()
	 * @generated
	 * @ordered
	 */
	private String contentType = CONTENT_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgreementDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicensesPackage.eINSTANCE.getAgreementData();
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__IDENTIFIER,
					oldIdentifier, identifier));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__FILE, oldFile, file));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHashAlgo() {
		return hashAlgo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHashAlgo(String newHashAlgo) {
		String oldHashAlgo = hashAlgo;
		hashAlgo = newHashAlgo;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__HASH_ALGO,
					oldHashAlgo, hashAlgo));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public byte[] getHash() {
		return hash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHash(byte[] newHash) {
		byte[] oldHash = hash;
		hash = newHash;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__HASH, oldHash, hash));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public byte[] getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContent(byte[] newContent) {
		byte[] oldContent = content;
		content = newContent;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__CONTENT, oldContent,
					content));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContentType() {
		return contentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContentType(String newContentType) {
		String oldContentType = contentType;
		contentType = newContentType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, LicensesPackage.AGREEMENT_DATA__CONTENT_TYPE,
					oldContentType, contentType));
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
		case LicensesPackage.AGREEMENT_DATA__IDENTIFIER:
			return getIdentifier();
		case LicensesPackage.AGREEMENT_DATA__NAME:
			return getName();
		case LicensesPackage.AGREEMENT_DATA__FILE:
			return getFile();
		case LicensesPackage.AGREEMENT_DATA__HASH_ALGO:
			return getHashAlgo();
		case LicensesPackage.AGREEMENT_DATA__HASH:
			return getHash();
		case LicensesPackage.AGREEMENT_DATA__CONTENT:
			return getContent();
		case LicensesPackage.AGREEMENT_DATA__CONTENT_TYPE:
			return getContentType();
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
		case LicensesPackage.AGREEMENT_DATA__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case LicensesPackage.AGREEMENT_DATA__NAME:
			setName((String) newValue);
			return;
		case LicensesPackage.AGREEMENT_DATA__FILE:
			setFile((String) newValue);
			return;
		case LicensesPackage.AGREEMENT_DATA__HASH_ALGO:
			setHashAlgo((String) newValue);
			return;
		case LicensesPackage.AGREEMENT_DATA__HASH:
			setHash((byte[]) newValue);
			return;
		case LicensesPackage.AGREEMENT_DATA__CONTENT:
			setContent((byte[]) newValue);
			return;
		case LicensesPackage.AGREEMENT_DATA__CONTENT_TYPE:
			setContentType((String) newValue);
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
		case LicensesPackage.AGREEMENT_DATA__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case LicensesPackage.AGREEMENT_DATA__NAME:
			setName(NAME_EDEFAULT);
			return;
		case LicensesPackage.AGREEMENT_DATA__FILE:
			setFile(FILE_EDEFAULT);
			return;
		case LicensesPackage.AGREEMENT_DATA__HASH_ALGO:
			setHashAlgo(HASH_ALGO_EDEFAULT);
			return;
		case LicensesPackage.AGREEMENT_DATA__HASH:
			setHash(HASH_EDEFAULT);
			return;
		case LicensesPackage.AGREEMENT_DATA__CONTENT:
			setContent(CONTENT_EDEFAULT);
			return;
		case LicensesPackage.AGREEMENT_DATA__CONTENT_TYPE:
			setContentType(CONTENT_TYPE_EDEFAULT);
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
		case LicensesPackage.AGREEMENT_DATA__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case LicensesPackage.AGREEMENT_DATA__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case LicensesPackage.AGREEMENT_DATA__FILE:
			return !Objects.equals(FILE_EDEFAULT, file);
		case LicensesPackage.AGREEMENT_DATA__HASH_ALGO:
			return !Objects.equals(HASH_ALGO_EDEFAULT, hashAlgo);
		case LicensesPackage.AGREEMENT_DATA__HASH:
			return !Objects.equals(HASH_EDEFAULT, hash);
		case LicensesPackage.AGREEMENT_DATA__CONTENT:
			return !Objects.equals(CONTENT_EDEFAULT, content);
		case LicensesPackage.AGREEMENT_DATA__CONTENT_TYPE:
			return !Objects.equals(CONTENT_TYPE_EDEFAULT, contentType);
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
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", file: "); //$NON-NLS-1$
		result.append(file);
		result.append(", hashAlgo: "); //$NON-NLS-1$
		result.append(hashAlgo);
		result.append(", hash: "); //$NON-NLS-1$
		result.append(hash);
		result.append(", content: "); //$NON-NLS-1$
		result.append(content);
		result.append(", contentType: "); //$NON-NLS-1$
		result.append(contentType);
		result.append(')');
		return result.toString();
	}

} //AgreementDataImpl
