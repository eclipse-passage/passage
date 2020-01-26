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
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model object '<em><b>Product</b></em>'.
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductImpl#getProductLine <em>Product Line</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.products.model.impl.ProductImpl#getProductVersions <em>Product Versions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductImpl extends MinimalEObjectImpl.Container implements Product {
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	private String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	private String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	private String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProductVersions() <em>Product Versions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @see #getProductVersions()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductVersion> productVersions;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductImpl() {
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
		return ProductsPackage.eINSTANCE.getProduct();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT__IDENTIFIER, oldIdentifier,
					identifier));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT__DESCRIPTION, oldDescription,
					description));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductLine getProductLine() {
		if (eContainerFeatureID() != ProductsPackage.PRODUCT__PRODUCT_LINE) {
			return null;
		}
		return (ProductLine) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProductLine(ProductLine newProductLine, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newProductLine, ProductsPackage.PRODUCT__PRODUCT_LINE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProductLine(ProductLine newProductLine) {
		if (newProductLine != eInternalContainer()
				|| (eContainerFeatureID() != ProductsPackage.PRODUCT__PRODUCT_LINE && newProductLine != null)) {
			if (EcoreUtil.isAncestor(this, newProductLine)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newProductLine != null)
				msgs = ((InternalEObject) newProductLine).eInverseAdd(this, ProductsPackage.PRODUCT_LINE__PRODUCTS,
						ProductLine.class, msgs);
			msgs = basicSetProductLine(newProductLine, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProductsPackage.PRODUCT__PRODUCT_LINE, newProductLine,
					newProductLine));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ProductVersion> getProductVersions() {
		if (productVersions == null) {
			productVersions = new EObjectContainmentWithInverseEList<ProductVersion>(ProductVersion.class, this,
					ProductsPackage.PRODUCT__PRODUCT_VERSIONS, ProductsPackage.PRODUCT_VERSION__PRODUCT);
		}
		return productVersions;
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
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetProductLine((ProductLine) otherEnd, msgs);
		case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getProductVersions()).basicAdd(otherEnd, msgs);
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
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			return basicSetProductLine(null, msgs);
		case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
			return ((InternalEList<?>) getProductVersions()).basicRemove(otherEnd, msgs);
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
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			return eInternalContainer().eInverseRemove(this, ProductsPackage.PRODUCT_LINE__PRODUCTS, ProductLine.class,
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
		case ProductsPackage.PRODUCT__IDENTIFIER:
			return getIdentifier();
		case ProductsPackage.PRODUCT__NAME:
			return getName();
		case ProductsPackage.PRODUCT__DESCRIPTION:
			return getDescription();
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			return getProductLine();
		case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
			return getProductVersions();
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
		case ProductsPackage.PRODUCT__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case ProductsPackage.PRODUCT__NAME:
			setName((String) newValue);
			return;
		case ProductsPackage.PRODUCT__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			setProductLine((ProductLine) newValue);
			return;
		case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
			getProductVersions().clear();
			getProductVersions().addAll((Collection<? extends ProductVersion>) newValue);
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
		case ProductsPackage.PRODUCT__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			setProductLine((ProductLine) null);
			return;
		case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
			getProductVersions().clear();
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
		case ProductsPackage.PRODUCT__IDENTIFIER:
			return !Objects.equals(IDENTIFIER_EDEFAULT, identifier);
		case ProductsPackage.PRODUCT__NAME:
			return !Objects.equals(NAME_EDEFAULT, name);
		case ProductsPackage.PRODUCT__DESCRIPTION:
			return !Objects.equals(DESCRIPTION_EDEFAULT, description);
		case ProductsPackage.PRODUCT__PRODUCT_LINE:
			return getProductLine() != null;
		case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
			return productVersions != null && !productVersions.isEmpty();
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
		result.append(" (identifier: "); //$NON-NLS-1$
		result.append(identifier);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} // ProductImpl
