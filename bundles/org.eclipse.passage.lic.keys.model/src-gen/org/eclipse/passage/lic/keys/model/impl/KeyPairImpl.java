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
package org.eclipse.passage.lic.keys.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.lic.keys.model.api.ProductRef;

import org.eclipse.passage.lic.keys.model.meta.KeysPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Key Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.keys.model.impl.KeyPairImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.impl.KeyPairImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.impl.KeyPairImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.impl.KeyPairImpl#getPub <em>Pub</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.impl.KeyPairImpl#getScr <em>Scr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KeyPairImpl extends MinimalEObjectImpl.Container implements KeyPair {
	/**
	 * The cached value of the '{@link #getProduct() <em>Product</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	protected ProductRef product;

	/**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected static final String ALGORITHM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	private String algorithm = ALGORITHM_EDEFAULT;

	/**
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final int KEY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	private int key = KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPub() <em>Pub</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPub()
	 * @generated
	 * @ordered
	 */
	protected static final String PUB_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPub() <em>Pub</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPub()
	 * @generated
	 * @ordered
	 */
	private String pub = PUB_EDEFAULT;

	/**
	 * The default value of the '{@link #getScr() <em>Scr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScr()
	 * @generated
	 * @ordered
	 */
	protected static final String SCR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScr() <em>Scr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScr()
	 * @generated
	 * @ordered
	 */
	private String scr = SCR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KeyPairImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KeysPackage.eINSTANCE.getKeyPair();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductRef getProduct() {
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProduct(ProductRef newProduct, NotificationChain msgs) {
		ProductRef oldProduct = product;
		product = newProduct;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					KeysPackage.KEY_PAIR__PRODUCT, oldProduct, newProduct);
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
	public void setProduct(ProductRef newProduct) {
		if (newProduct != product) {
			NotificationChain msgs = null;
			if (product != null)
				msgs = ((InternalEObject) product).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - KeysPackage.KEY_PAIR__PRODUCT, null, msgs);
			if (newProduct != null)
				msgs = ((InternalEObject) newProduct).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - KeysPackage.KEY_PAIR__PRODUCT, null, msgs);
			msgs = basicSetProduct(newProduct, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, KeysPackage.KEY_PAIR__PRODUCT, newProduct,
					newProduct));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlgorithm(String newAlgorithm) {
		String oldAlgorithm = algorithm;
		algorithm = newAlgorithm;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, KeysPackage.KEY_PAIR__ALGORITHM, oldAlgorithm,
					algorithm));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKey(int newKey) {
		int oldKey = key;
		key = newKey;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, KeysPackage.KEY_PAIR__KEY, oldKey, key));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPub() {
		return pub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPub(String newPub) {
		String oldPub = pub;
		pub = newPub;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, KeysPackage.KEY_PAIR__PUB, oldPub, pub));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getScr() {
		return scr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScr(String newScr) {
		String oldScr = scr;
		scr = newScr;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, KeysPackage.KEY_PAIR__SCR, oldScr, scr));
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
		case KeysPackage.KEY_PAIR__PRODUCT:
			return basicSetProduct(null, msgs);
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
		case KeysPackage.KEY_PAIR__PRODUCT:
			return getProduct();
		case KeysPackage.KEY_PAIR__ALGORITHM:
			return getAlgorithm();
		case KeysPackage.KEY_PAIR__KEY:
			return getKey();
		case KeysPackage.KEY_PAIR__PUB:
			return getPub();
		case KeysPackage.KEY_PAIR__SCR:
			return getScr();
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
		case KeysPackage.KEY_PAIR__PRODUCT:
			setProduct((ProductRef) newValue);
			return;
		case KeysPackage.KEY_PAIR__ALGORITHM:
			setAlgorithm((String) newValue);
			return;
		case KeysPackage.KEY_PAIR__KEY:
			setKey((Integer) newValue);
			return;
		case KeysPackage.KEY_PAIR__PUB:
			setPub((String) newValue);
			return;
		case KeysPackage.KEY_PAIR__SCR:
			setScr((String) newValue);
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
		case KeysPackage.KEY_PAIR__PRODUCT:
			setProduct((ProductRef) null);
			return;
		case KeysPackage.KEY_PAIR__ALGORITHM:
			setAlgorithm(ALGORITHM_EDEFAULT);
			return;
		case KeysPackage.KEY_PAIR__KEY:
			setKey(KEY_EDEFAULT);
			return;
		case KeysPackage.KEY_PAIR__PUB:
			setPub(PUB_EDEFAULT);
			return;
		case KeysPackage.KEY_PAIR__SCR:
			setScr(SCR_EDEFAULT);
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
		case KeysPackage.KEY_PAIR__PRODUCT:
			return product != null;
		case KeysPackage.KEY_PAIR__ALGORITHM:
			return !Objects.equals(ALGORITHM_EDEFAULT, algorithm);
		case KeysPackage.KEY_PAIR__KEY:
			return key != KEY_EDEFAULT;
		case KeysPackage.KEY_PAIR__PUB:
			return !Objects.equals(PUB_EDEFAULT, pub);
		case KeysPackage.KEY_PAIR__SCR:
			return !Objects.equals(SCR_EDEFAULT, scr);
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
		result.append(" (algorithm: "); //$NON-NLS-1$
		result.append(algorithm);
		result.append(", key: "); //$NON-NLS-1$
		result.append(key);
		result.append(", pub: "); //$NON-NLS-1$
		result.append(pub);
		result.append(", scr: "); //$NON-NLS-1$
		result.append(scr);
		result.append(')');
		return result.toString();
	}

} //KeyPairImpl
