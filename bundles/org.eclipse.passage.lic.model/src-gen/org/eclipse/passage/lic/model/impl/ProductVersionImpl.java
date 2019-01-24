/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.passage.lic.model.api.Product;
import org.eclipse.passage.lic.model.api.ProductVersion;
import org.eclipse.passage.lic.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl#getInstallationToken <em>Installation Token</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl#getSecureToken <em>Secure Token</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl#getNews <em>News</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl#getProductVersionFeatures <em>Product Version Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductVersionImpl extends MinimalEObjectImpl.Container implements ProductVersion {
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
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstallationToken() <em>Installation Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstallationToken()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTALLATION_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstallationToken() <em>Installation Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstallationToken()
	 * @generated
	 * @ordered
	 */
	protected String installationToken = INSTALLATION_TOKEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecureToken() <em>Secure Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecureToken()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURE_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecureToken() <em>Secure Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecureToken()
	 * @generated
	 * @ordered
	 */
	protected String secureToken = SECURE_TOKEN_EDEFAULT;

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
	protected String news = NEWS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProductVersionFeatures() <em>Product Version Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductVersionFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductVersionFeature> productVersionFeatures;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductVersionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LicPackage.Literals.PRODUCT_VERSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product getProduct() {
		if (eContainerFeatureID() != LicPackage.PRODUCT_VERSION__PRODUCT) return null;
		return (Product)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProduct(Product newProduct, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProduct, LicPackage.PRODUCT_VERSION__PRODUCT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProduct(Product newProduct) {
		if (newProduct != eInternalContainer() || (eContainerFeatureID() != LicPackage.PRODUCT_VERSION__PRODUCT && newProduct != null)) {
			if (EcoreUtil.isAncestor(this, newProduct))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProduct != null)
				msgs = ((InternalEObject)newProduct).eInverseAdd(this, LicPackage.PRODUCT__PRODUCT_VERSIONS, Product.class, msgs);
			msgs = basicSetProduct(newProduct, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION__PRODUCT, newProduct, newProduct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstallationToken() {
		return installationToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstallationToken(String newInstallationToken) {
		String oldInstallationToken = installationToken;
		installationToken = newInstallationToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION__INSTALLATION_TOKEN, oldInstallationToken, installationToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecureToken() {
		return secureToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecureToken(String newSecureToken) {
		String oldSecureToken = secureToken;
		secureToken = newSecureToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION__SECURE_TOKEN, oldSecureToken, secureToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProductVersionFeature> getProductVersionFeatures() {
		if (productVersionFeatures == null) {
			productVersionFeatures = new EObjectContainmentWithInverseEList<ProductVersionFeature>(ProductVersionFeature.class, this, LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES, LicPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION);
		}
		return productVersionFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNews() {
		return news;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNews(String newNews) {
		String oldNews = news;
		news = newNews;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT_VERSION__NEWS, oldNews, news));
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
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProduct((Product)otherEnd, msgs);
			case LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProductVersionFeatures()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				return basicSetProduct(null, msgs);
			case LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				return ((InternalEList<?>)getProductVersionFeatures()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				return eInternalContainer().eInverseRemove(this, LicPackage.PRODUCT__PRODUCT_VERSIONS, Product.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION__VERSION:
				return getVersion();
			case LicPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
				return getInstallationToken();
			case LicPackage.PRODUCT_VERSION__SECURE_TOKEN:
				return getSecureToken();
			case LicPackage.PRODUCT_VERSION__NEWS:
				return getNews();
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				return getProduct();
			case LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				return getProductVersionFeatures();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case LicPackage.PRODUCT_VERSION__VERSION:
				setVersion((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
				setInstallationToken((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION__SECURE_TOKEN:
				setSecureToken((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION__NEWS:
				setNews((String)newValue);
				return;
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				setProduct((Product)newValue);
				return;
			case LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				getProductVersionFeatures().clear();
				getProductVersionFeatures().addAll((Collection<? extends ProductVersionFeature>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
				setInstallationToken(INSTALLATION_TOKEN_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION__SECURE_TOKEN:
				setSecureToken(SECURE_TOKEN_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION__NEWS:
				setNews(NEWS_EDEFAULT);
				return;
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				setProduct((Product)null);
				return;
			case LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				getProductVersionFeatures().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LicPackage.PRODUCT_VERSION__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case LicPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
				return INSTALLATION_TOKEN_EDEFAULT == null ? installationToken != null : !INSTALLATION_TOKEN_EDEFAULT.equals(installationToken);
			case LicPackage.PRODUCT_VERSION__SECURE_TOKEN:
				return SECURE_TOKEN_EDEFAULT == null ? secureToken != null : !SECURE_TOKEN_EDEFAULT.equals(secureToken);
			case LicPackage.PRODUCT_VERSION__NEWS:
				return NEWS_EDEFAULT == null ? news != null : !NEWS_EDEFAULT.equals(news);
			case LicPackage.PRODUCT_VERSION__PRODUCT:
				return getProduct() != null;
			case LicPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				return productVersionFeatures != null && !productVersionFeatures.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", installationToken: "); //$NON-NLS-1$
		result.append(installationToken);
		result.append(", secureToken: "); //$NON-NLS-1$
		result.append(secureToken);
		result.append(", news: "); //$NON-NLS-1$
		result.append(news);
		result.append(')');
		return result.toString();
	}

} //ProductVersionImpl
