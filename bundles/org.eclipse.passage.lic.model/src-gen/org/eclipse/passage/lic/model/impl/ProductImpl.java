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
import org.eclipse.passage.lic.model.api.ProductLine;
import org.eclipse.passage.lic.model.api.ProductVersion;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductImpl#getProductLine <em>Product Line</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.model.impl.ProductImpl#getProductVersions <em>Product Versions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductImpl extends MinimalEObjectImpl.Container implements Product {
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
  protected String identifier = IDENTIFIER_EDEFAULT;

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
  protected String name = NAME_EDEFAULT;

  /**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
  protected String description = DESCRIPTION_EDEFAULT;

		/**
	 * The cached value of the '{@link #getProductVersions() <em>Product Versions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductVersions()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductVersion> productVersions;

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ProductImpl() {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass() {
		return LicPackage.Literals.PRODUCT;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getIdentifier() {
		return identifier;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT__IDENTIFIER, oldIdentifier, identifier));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getName() {
		return name;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT__NAME, oldName, name));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getDescription() {
		return description;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT__DESCRIPTION, oldDescription, description));
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductLine getProductLine() {
		if (eContainerFeatureID() != LicPackage.PRODUCT__PRODUCT_LINE) return null;
		return (ProductLine)eInternalContainer();
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProductLine(ProductLine newProductLine, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProductLine, LicPackage.PRODUCT__PRODUCT_LINE, msgs);
		return msgs;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProductLine(ProductLine newProductLine) {
		if (newProductLine != eInternalContainer() || (eContainerFeatureID() != LicPackage.PRODUCT__PRODUCT_LINE && newProductLine != null)) {
			if (EcoreUtil.isAncestor(this, newProductLine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProductLine != null)
				msgs = ((InternalEObject)newProductLine).eInverseAdd(this, LicPackage.PRODUCT_LINE__PRODUCTS, ProductLine.class, msgs);
			msgs = basicSetProductLine(newProductLine, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LicPackage.PRODUCT__PRODUCT_LINE, newProductLine, newProductLine));
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProductVersion> getProductVersions() {
		if (productVersions == null) {
			productVersions = new EObjectContainmentWithInverseEList<ProductVersion>(ProductVersion.class, this, LicPackage.PRODUCT__PRODUCT_VERSIONS, LicPackage.PRODUCT_VERSION__PRODUCT);
		}
		return productVersions;
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
			case LicPackage.PRODUCT__PRODUCT_LINE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProductLine((ProductLine)otherEnd, msgs);
			case LicPackage.PRODUCT__PRODUCT_VERSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProductVersions()).basicAdd(otherEnd, msgs);
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
			case LicPackage.PRODUCT__PRODUCT_LINE:
				return basicSetProductLine(null, msgs);
			case LicPackage.PRODUCT__PRODUCT_VERSIONS:
				return ((InternalEList<?>)getProductVersions()).basicRemove(otherEnd, msgs);
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
			case LicPackage.PRODUCT__PRODUCT_LINE:
				return eInternalContainer().eInverseRemove(this, LicPackage.PRODUCT_LINE__PRODUCTS, ProductLine.class, msgs);
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
			case LicPackage.PRODUCT__IDENTIFIER:
				return getIdentifier();
			case LicPackage.PRODUCT__NAME:
				return getName();
			case LicPackage.PRODUCT__DESCRIPTION:
				return getDescription();
			case LicPackage.PRODUCT__PRODUCT_LINE:
				return getProductLine();
			case LicPackage.PRODUCT__PRODUCT_VERSIONS:
				return getProductVersions();
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
			case LicPackage.PRODUCT__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case LicPackage.PRODUCT__NAME:
				setName((String)newValue);
				return;
			case LicPackage.PRODUCT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case LicPackage.PRODUCT__PRODUCT_LINE:
				setProductLine((ProductLine)newValue);
				return;
			case LicPackage.PRODUCT__PRODUCT_VERSIONS:
				getProductVersions().clear();
				getProductVersions().addAll((Collection<? extends ProductVersion>)newValue);
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
			case LicPackage.PRODUCT__IDENTIFIER:
				setIdentifier(IDENTIFIER_EDEFAULT);
				return;
			case LicPackage.PRODUCT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case LicPackage.PRODUCT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case LicPackage.PRODUCT__PRODUCT_LINE:
				setProductLine((ProductLine)null);
				return;
			case LicPackage.PRODUCT__PRODUCT_VERSIONS:
				getProductVersions().clear();
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
			case LicPackage.PRODUCT__IDENTIFIER:
				return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
			case LicPackage.PRODUCT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case LicPackage.PRODUCT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case LicPackage.PRODUCT__PRODUCT_LINE:
				return getProductLine() != null;
			case LicPackage.PRODUCT__PRODUCT_VERSIONS:
				return productVersions != null && !productVersions.isEmpty();
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
		result.append(" (identifier: "); //$NON-NLS-1$
		result.append(identifier);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //ProductImpl
