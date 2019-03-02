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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.passage.lic.model.api.*;
import org.eclipse.passage.lic.model.meta.LicFactory;
import org.eclipse.passage.lic.model.meta.LicPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LicFactoryImpl extends EFactoryImpl implements LicFactory {
  /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public static LicFactory init() {
		try {
			LicFactory theLicFactory = (LicFactory)EPackage.Registry.INSTANCE.getEFactory(LicPackage.eNS_URI);
			if (theLicFactory != null) {
				return theLicFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LicFactoryImpl();
	}

  /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public LicFactoryImpl() {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LicPackage.FEATURE_SET: return createFeatureSet();
			case LicPackage.FEATURE: return createFeature();
			case LicPackage.FEATURE_VERSION: return createFeatureVersion();
			case LicPackage.PRODUCT_LINE: return createProductLine();
			case LicPackage.PRODUCT: return createProduct();
			case LicPackage.PRODUCT_VERSION: return createProductVersion();
			case LicPackage.PRODUCT_VERSION_FEATURE: return createProductVersionFeature();
			case LicPackage.USER_ORIGIN: return createUserOrigin();
			case LicPackage.USER: return createUser();
			case LicPackage.LICENSE_PACK: return createLicensePack();
			case LicPackage.LICENSE_GRANT: return createLicenseGrant();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureSet createFeatureSet() {
		FeatureSetImpl featureSet = new FeatureSetImpl();
		return featureSet;
	}

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
		public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductVersion createProductVersion() {
		ProductVersionImpl productVersion = new ProductVersionImpl();
		return productVersion;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductVersionFeature createProductVersionFeature() {
		ProductVersionFeatureImpl productVersionFeature = new ProductVersionFeatureImpl();
		return productVersionFeature;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UserOrigin createUserOrigin() {
		UserOriginImpl userOrigin = new UserOriginImpl();
		return userOrigin;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Feature createFeature() {
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureVersion createFeatureVersion() {
		FeatureVersionImpl featureVersion = new FeatureVersionImpl();
		return featureVersion;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductLine createProductLine() {
		ProductLineImpl productLine = new ProductLineImpl();
		return productLine;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public User createUser() {
		UserImpl user = new UserImpl();
		return user;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensePack createLicensePack() {
		LicensePackImpl licensePack = new LicensePackImpl();
		return licensePack;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicenseGrant createLicenseGrant() {
		LicenseGrantImpl licenseGrant = new LicenseGrantImpl();
		return licenseGrant;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicPackage getLicPackage() {
		return (LicPackage)getEPackage();
	}

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
  @Deprecated
  public static LicPackage getPackage() {
		return LicPackage.eINSTANCE;
	}

} //PassageFactoryImpl
