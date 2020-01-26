/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.products.model.impl;

import java.util.Collection;

import java.util.Objects;
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
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model object '<em><b>Product Version</b></em>'.
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getInstallationToken <em>Installation Token</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getSecureToken <em>Secure Token</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getNews <em>News</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductVersionImpl#getProductVersionFeatures <em>Product Version Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductVersionImpl extends MinimalEObjectImpl.Container implements ProductVersion {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	private String version = VERSION_EDEFAULT;

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
	 * The default value of the '{@link #getInstallationToken() <em>Installation Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getInstallationToken()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTALLATION_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstallationToken() <em>Installation Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getInstallationToken()
	 * @generated
	 * @ordered
	 */
	private String installationToken = INSTALLATION_TOKEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecureToken() <em>Secure Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getSecureToken()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURE_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecureToken() <em>Secure Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getSecureToken()
	 * @generated
	 * @ordered
	 */
	private String secureToken = SECURE_TOKEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getNews() <em>News</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getNews()
	 * @generated
	 * @ordered
	 */
	protected static final String NEWS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNews() <em>News</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getNews()
	 * @generated
	 * @ordered
	 */
	private String news = NEWS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProductVersionFeatures() <em>Product Version Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getProductVersionFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductVersionFeature> productVersionFeatures;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductVersionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProductsPackage.eINSTANCE.getProductVersion();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT_VERSION__VERSION, oldVersion,
					version));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT_VERSION__NAME, oldName,
					name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInstallationToken() {
		return installationToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInstallationToken(String newInstallationToken) {
		String oldInstallationToken = installationToken;
		installationToken = newInstallationToken;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT_VERSION__INSTALLATION_TOKEN,
					oldInstallationToken, installationToken));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSecureToken() {
		return secureToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSecureToken(String newSecureToken) {
		String oldSecureToken = secureToken;
		secureToken = newSecureToken;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT_VERSION__SECURE_TOKEN,
					oldSecureToken, secureToken));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNews() {
		return news;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNews(String newNews) {
		String oldNews = news;
		news = newNews;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT_VERSION__NEWS, oldNews,
					news));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Product getProduct() {
		if (eContainerFeatureID() != ProductsPackage.PRODUCT_VERSION__PRODUCT) {
			return null;
		}
		return (Product) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProduct(Product newProduct, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newProduct, ProductsPackage.PRODUCT_VERSION__PRODUCT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProduct(Product newProduct) {
		if (newProduct != eInternalContainer()
				|| (eContainerFeatureID() != ProductsPackage.PRODUCT_VERSION__PRODUCT && newProduct != null)) {
			if (EcoreUtil.isAncestor(this, newProduct)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newProduct != null)
				msgs = ((InternalEObject) newProduct).eInverseAdd(this, ProductsPackage.PRODUCT__PRODUCT_VERSIONS,
						Product.class, msgs);
			msgs = basicSetProduct(newProduct, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT_VERSION__PRODUCT, newProduct,
					newProduct));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ProductVersionFeature> getProductVersionFeatures() {
		if (productVersionFeatures == null) {
			productVersionFeatures = new EObjectContainmentWithInverseEList<ProductVersionFeature>(
					ProductVersionFeature.class, this, ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES,
					ProductsPackage.PRODUCT_VERSION_FEATURE__PRODUCT_VERSION);
		}
		return productVersionFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetProduct((Product) otherEnd, msgs);
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getProductVersionFeatures()).basicAdd(otherEnd,
					msgs);
		default:
			return super.eInverseAdd(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			return basicSetProduct(null, msgs);
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
			return ((InternalEList<?>) getProductVersionFeatures()).basicRemove(otherEnd, msgs);
		default:
			return super.eInverseRemove(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			return eInternalContainer().eInverseRemove(this, ProductsPackage.PRODUCT__PRODUCT_VERSIONS, Product.class,
					msgs);
		default:
			return super.eBasicRemoveFromContainerFeature(msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ProductsPackage.PRODUCT_VERSION__VERSION:
			return getVersion();
		case ProductsPackage.PRODUCT_VERSION__NAME:
			return getName();
		case ProductsPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
			return getInstallationToken();
		case ProductsPackage.PRODUCT_VERSION__SECURE_TOKEN:
			return getSecureToken();
		case ProductsPackage.PRODUCT_VERSION__NEWS:
			return getNews();
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			return getProduct();
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
			return getProductVersionFeatures();
		default:
			return super.eGet(featureID, resolve, coreType);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ProductsPackage.PRODUCT_VERSION__VERSION:
			setVersion((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION__NAME:
			setName((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
			setInstallationToken((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION__SECURE_TOKEN:
			setSecureToken((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION__NEWS:
			setNews((String) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			setProduct((Product) newValue);
			return;
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
			getProductVersionFeatures().clear();
			getProductVersionFeatures().addAll((Collection<? extends ProductVersionFeature>) newValue);
			return;
		default:
			super.eSet(featureID, newValue);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ProductsPackage.PRODUCT_VERSION__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
			setInstallationToken(INSTALLATION_TOKEN_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION__SECURE_TOKEN:
			setSecureToken(SECURE_TOKEN_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION__NEWS:
			setNews(NEWS_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			setProduct((Product) null);
			return;
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
			getProductVersionFeatures().clear();
			return;
		default:
			super.eUnset(featureID);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ProductsPackage.PRODUCT_VERSION__VERSION:
			return !Objects.equals(VERSION_EDEFAULT, version);
		case ProductsPackage.PRODUCT_VERSION__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case ProductsPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
			return !Objects.equals(INSTALLATION_TOKEN_EDEFAULT, installationToken);
		case ProductsPackage.PRODUCT_VERSION__SECURE_TOKEN:
			return !Objects.equals(SECURE_TOKEN_EDEFAULT, secureToken);
		case ProductsPackage.PRODUCT_VERSION__NEWS:
			return !Objects.equals(NEWS_EDEFAULT, news);
		case ProductsPackage.PRODUCT_VERSION__PRODUCT:
			return getProduct() != null;
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
			return productVersionFeatures != null && !productVersionFeatures.isEmpty();
		default:
			return super.eIsSet(featureID);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}
		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", installationToken: "); //$NON-NLS-1$
		result.append(installationToken);
		result.append(", secureToken: "); //$NON-NLS-1$
		result.append(secureToken);
		result.append(", news: "); //$NON-NLS-1$
		result.append(news);
		result.append(')');
		return result.toString();
	}

} // ProductVersionImpl
