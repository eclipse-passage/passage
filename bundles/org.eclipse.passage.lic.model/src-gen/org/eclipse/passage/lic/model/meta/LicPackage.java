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
package org.eclipse.passage.lic.model.meta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.model.meta.LicFactory
 * @model kind="package"
 * @generated
 */
public interface LicPackage extends EPackage {
  /**
	 * The package name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNAME = "lic"; //$NON-NLS-1$

  /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNS_URI = "http://www.eclipse.org/passage/lic/0.4.0"; //$NON-NLS-1$

  /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNS_PREFIX = "org.eclipse.passage.lic"; //$NON-NLS-1$

  /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  LicPackage eINSTANCE = org.eclipse.passage.lic.model.impl.LicPackageImpl.init();

  /**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor <em>Feature Set Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureSetDescriptor()
	 * @generated
	 */
	int FEATURE_SET_DESCRIPTOR = 0;

		/**
	 * The number of structural features of the '<em>Feature Set Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Feature Set Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.products.ProductDescriptor <em>Product Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.products.ProductDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductDescriptor()
	 * @generated
	 */
	int PRODUCT_DESCRIPTOR = 4;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor <em>Product Version Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersionDescriptor()
	 * @generated
	 */
	int PRODUCT_VERSION_DESCRIPTOR = 5;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.features.FeatureDescriptor <em>Feature Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.features.FeatureDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureDescriptor()
	 * @generated
	 */
	int FEATURE_DESCRIPTOR = 1;

		/**
	 * The number of structural features of the '<em>Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor <em>Feature Version Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureVersionDescriptor()
	 * @generated
	 */
	int FEATURE_VERSION_DESCRIPTOR = 2;

		/**
	 * The number of structural features of the '<em>Feature Version Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Feature Version Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.products.ProductLineDescriptor <em>Product Line Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.products.ProductLineDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductLineDescriptor()
	 * @generated
	 */
	int PRODUCT_LINE_DESCRIPTOR = 3;

		/**
	 * The number of structural features of the '<em>Product Line Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Product Line Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The number of structural features of the '<em>Product Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Product Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The number of structural features of the '<em>Product Version Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Product Version Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor <em>Product Version Feature Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersionFeatureDescriptor()
	 * @generated
	 */
	int PRODUCT_VERSION_FEATURE_DESCRIPTOR = 6;

		/**
	 * The number of structural features of the '<em>Product Version Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>Product Version Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.users.UserOriginDescriptor <em>User Origin Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.users.UserOriginDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUserOriginDescriptor()
	 * @generated
	 */
	int USER_ORIGIN_DESCRIPTOR = 7;

		/**
	 * The number of structural features of the '<em>User Origin Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>User Origin Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.users.UserDescriptor <em>User Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.users.UserDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUserDescriptor()
	 * @generated
	 */
	int USER_DESCRIPTOR = 8;

		/**
	 * The number of structural features of the '<em>User Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>User Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor <em>License Pack Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicensePackDescriptor()
	 * @generated
	 */
	int LICENSE_PACK_DESCRIPTOR = 9;

		/**
	 * The number of structural features of the '<em>License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>License Pack Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor <em>License Grant Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicenseGrantDescriptor()
	 * @generated
	 */
	int LICENSE_GRANT_DESCRIPTOR = 10;

		/**
	 * The number of structural features of the '<em>License Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT = 0;

		/**
	 * The number of operations of the '<em>License Grant Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_DESCRIPTOR_OPERATION_COUNT = 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.FeatureSetImpl <em>Feature Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.FeatureSetImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureSet()
	 * @generated
	 */
	int FEATURE_SET = 11;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__IDENTIFIER = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__NAME = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__DESCRIPTION = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__FEATURES = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The number of structural features of the '<em>Feature Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_FEATURE_COUNT = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of operations of the '<em>Feature Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_OPERATION_COUNT = FEATURE_SET_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.ProductImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProduct()
	 * @generated
	 */
  int PRODUCT = 15;

  /**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl <em>Product Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.ProductVersionImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersion()
	 * @generated
	 */
	int PRODUCT_VERSION = 16;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl <em>Product Version Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersionFeature()
	 * @generated
	 */
	int PRODUCT_VERSION_FEATURE = 17;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.FeatureImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 12;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__IDENTIFIER = FEATURE_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = FEATURE_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__DESCRIPTION = FEATURE_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Feature Set</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_SET = FEATURE_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The feature id for the '<em><b>Feature Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_VERSIONS = FEATURE_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = FEATURE_DESCRIPTOR_FEATURE_COUNT + 5;

		/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = FEATURE_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.FeatureVersionImpl <em>Feature Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.FeatureVersionImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureVersion()
	 * @generated
	 */
	int FEATURE_VERSION = 13;

		/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION__VERSION = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Feature</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION__FEATURE = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>News</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION__NEWS = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The number of structural features of the '<em>Feature Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_FEATURE_COUNT = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The number of operations of the '<em>Feature Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_OPERATION_COUNT = FEATURE_VERSION_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.ProductLineImpl <em>Product Line</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.ProductLineImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductLine()
	 * @generated
	 */
	int PRODUCT_LINE = 14;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__IDENTIFIER = PRODUCT_LINE_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__NAME = PRODUCT_LINE_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__DESCRIPTION = PRODUCT_LINE_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE__PRODUCTS = PRODUCT_LINE_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The number of structural features of the '<em>Product Line</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE_FEATURE_COUNT = PRODUCT_LINE_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of operations of the '<em>Product Line</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_LINE_OPERATION_COUNT = PRODUCT_LINE_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PRODUCT__IDENTIFIER = PRODUCT_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PRODUCT__NAME = PRODUCT_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PRODUCT__DESCRIPTION = PRODUCT_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Product Line</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_LINE = PRODUCT_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The feature id for the '<em><b>Product Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_VERSIONS = PRODUCT_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PRODUCT_FEATURE_COUNT = PRODUCT_DESCRIPTOR_FEATURE_COUNT + 5;

		/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PRODUCT_OPERATION_COUNT = PRODUCT_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__VERSION = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Installation Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__INSTALLATION_TOKEN = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Secure Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__SECURE_TOKEN = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>News</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__NEWS = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The feature id for the '<em><b>Product</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__PRODUCT = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The feature id for the '<em><b>Product Version Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION__PRODUCT_VERSION_FEATURES = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 5;

		/**
	 * The number of structural features of the '<em>Product Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_COUNT = PRODUCT_VERSION_DESCRIPTOR_FEATURE_COUNT + 6;

		/**
	 * The number of operations of the '<em>Product Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_OPERATION_COUNT = PRODUCT_VERSION_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER = PRODUCT_VERSION_FEATURE_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Feature Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__FEATURE_VERSION = PRODUCT_VERSION_FEATURE_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Restriction Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL = PRODUCT_VERSION_FEATURE_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Product Version</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE__PRODUCT_VERSION = PRODUCT_VERSION_FEATURE_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The number of structural features of the '<em>Product Version Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_FEATURE_COUNT = PRODUCT_VERSION_FEATURE_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of operations of the '<em>Product Version Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_VERSION_FEATURE_OPERATION_COUNT = PRODUCT_VERSION_FEATURE_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.UserOriginImpl <em>User Origin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.UserOriginImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUserOrigin()
	 * @generated
	 */
	int USER_ORIGIN = 18;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__IDENTIFIER = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__NAME = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__DESCRIPTION = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN__USERS = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The number of structural features of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_FEATURE_COUNT = USER_ORIGIN_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of operations of the '<em>User Origin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_ORIGIN_OPERATION_COUNT = USER_ORIGIN_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.UserImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUser()
	 * @generated
	 */
	int USER = 19;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__IDENTIFIER = USER_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__EMAIL = USER_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__FULL_NAME = USER_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DESCRIPTION = USER_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The feature id for the '<em><b>User Origin</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__USER_ORIGIN = USER_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = USER_DESCRIPTOR_FEATURE_COUNT + 5;

		/**
	 * The number of operations of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_OPERATION_COUNT = USER_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.LicensePackImpl <em>License Pack</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.LicensePackImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicensePack()
	 * @generated
	 */
	int LICENSE_PACK = 20;

		/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__ISSUE_DATE = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Product Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__PRODUCT_IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Product Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__PRODUCT_VERSION = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The feature id for the '<em><b>User Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__USER_IDENTIFIER = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The feature id for the '<em><b>License Grants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK__LICENSE_GRANTS = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 5;

		/**
	 * The number of structural features of the '<em>License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_FEATURE_COUNT = LICENSE_PACK_DESCRIPTOR_FEATURE_COUNT + 6;

		/**
	 * The number of operations of the '<em>License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_PACK_OPERATION_COUNT = LICENSE_PACK_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.model.impl.LicenseGrantImpl <em>License Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.model.impl.LicenseGrantImpl
	 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicenseGrant()
	 * @generated
	 */
	int LICENSE_GRANT = 21;

		/**
	 * The feature id for the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__FEATURE_IDENTIFIER = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Match Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__MATCH_VERSION = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 1;

		/**
	 * The feature id for the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__MATCH_RULE = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 2;

		/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__VALID_FROM = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 3;

		/**
	 * The feature id for the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__VALID_UNTIL = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 4;

		/**
	 * The feature id for the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CONDITION_TYPE = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 5;

		/**
	 * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CONDITION_EXPRESSION = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 6;

		/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__CAPACITY = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 7;

		/**
	 * The feature id for the '<em><b>License Pack</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT__LICENSE_PACK = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 8;

		/**
	 * The number of structural features of the '<em>License Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_FEATURE_COUNT = LICENSE_GRANT_DESCRIPTOR_FEATURE_COUNT + 9;

		/**
	 * The number of operations of the '<em>License Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_GRANT_OPERATION_COUNT = LICENSE_GRANT_DESCRIPTOR_OPERATION_COUNT + 0;

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor <em>Feature Set Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Set Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor"
	 * @generated
	 */
	EClass getFeatureSetDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.products.ProductDescriptor <em>Product Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.products.ProductDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.products.ProductDescriptor"
	 * @generated
	 */
	EClass getProductDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor <em>Product Version Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Version Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor"
	 * @generated
	 */
	EClass getProductVersionDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor <em>Product Version Feature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Version Feature Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor"
	 * @generated
	 */
	EClass getProductVersionFeatureDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.users.UserOriginDescriptor <em>User Origin Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Origin Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.users.UserOriginDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.users.UserOriginDescriptor"
	 * @generated
	 */
	EClass getUserOriginDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.features.FeatureDescriptor <em>Feature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.features.FeatureDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.features.FeatureDescriptor"
	 * @generated
	 */
	EClass getFeatureDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor <em>Feature Version Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Version Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor"
	 * @generated
	 */
	EClass getFeatureVersionDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.products.ProductLineDescriptor <em>Product Line Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Line Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.products.ProductLineDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.products.ProductLineDescriptor"
	 * @generated
	 */
	EClass getProductLineDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.users.UserDescriptor <em>User Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.users.UserDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.users.UserDescriptor"
	 * @generated
	 */
	EClass getUserDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor <em>License Pack Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Pack Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor"
	 * @generated
	 */
	EClass getLicensePackDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor <em>License Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Grant Descriptor</em>'.
	 * @see org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor"
	 * @generated
	 */
	EClass getLicenseGrantDescriptor();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.FeatureSet <em>Feature Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Set</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureSet
	 * @generated
	 */
	EClass getFeatureSet();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.FeatureSet#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureSet#getIdentifier()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EAttribute getFeatureSet_Identifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.FeatureSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureSet#getName()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EAttribute getFeatureSet_Name();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.FeatureSet#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureSet#getDescription()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EAttribute getFeatureSet_Description();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.FeatureSet#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureSet#getFeatures()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EReference getFeatureSet_Features();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.model.api.Product
	 * @generated
	 */
  EClass getProduct();

  /**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.Product#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.Product#getIdentifier()
	 * @see #getProduct()
	 * @generated
	 */
  EAttribute getProduct_Identifier();

  /**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.Product#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.model.api.Product#getName()
	 * @see #getProduct()
	 * @generated
	 */
  EAttribute getProduct_Name();

  /**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.model.api.Feature
	 * @generated
	 */
	EClass getFeature();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.Feature#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.Feature#getIdentifier()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Identifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.Feature#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.model.api.Feature#getName()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Name();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.Feature#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.model.api.Feature#getDescription()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Description();

		/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.Feature#getFeatureSet <em>Feature Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Feature Set</em>'.
	 * @see org.eclipse.passage.lic.model.api.Feature#getFeatureSet()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureSet();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.Feature#getFeatureVersions <em>Feature Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Feature Versions</em>'.
	 * @see org.eclipse.passage.lic.model.api.Feature#getFeatureVersions()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureVersions();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.FeatureVersion <em>Feature Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureVersion
	 * @generated
	 */
	EClass getFeatureVersion();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.FeatureVersion#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureVersion#getVersion()
	 * @see #getFeatureVersion()
	 * @generated
	 */
	EAttribute getFeatureVersion_Version();

		/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.FeatureVersion#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureVersion#getFeature()
	 * @see #getFeatureVersion()
	 * @generated
	 */
	EReference getFeatureVersion_Feature();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.FeatureVersion#getNews <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>News</em>'.
	 * @see org.eclipse.passage.lic.model.api.FeatureVersion#getNews()
	 * @see #getFeatureVersion()
	 * @generated
	 */
	EAttribute getFeatureVersion_News();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.ProductLine <em>Product Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Line</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductLine
	 * @generated
	 */
	EClass getProductLine();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductLine#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductLine#getIdentifier()
	 * @see #getProductLine()
	 * @generated
	 */
	EAttribute getProductLine_Identifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductLine#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductLine#getName()
	 * @see #getProductLine()
	 * @generated
	 */
	EAttribute getProductLine_Name();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductLine#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductLine#getDescription()
	 * @see #getProductLine()
	 * @generated
	 */
	EAttribute getProductLine_Description();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.ProductLine#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductLine#getProducts()
	 * @see #getProductLine()
	 * @generated
	 */
	EReference getProductLine_Products();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see org.eclipse.passage.lic.model.api.User
	 * @generated
	 */
	EClass getUser();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.User#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.User#getIdentifier()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Identifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.User#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see org.eclipse.passage.lic.model.api.User#getEmail()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Email();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.User#getFullName <em>Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Name</em>'.
	 * @see org.eclipse.passage.lic.model.api.User#getFullName()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_FullName();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.User#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.model.api.User#getDescription()
	 * @see #getUser()
	 * @generated
	 */
	EAttribute getUser_Description();

		/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.User#getUserOrigin <em>User Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>User Origin</em>'.
	 * @see org.eclipse.passage.lic.model.api.User#getUserOrigin()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_UserOrigin();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.LicensePack <em>License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Pack</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack
	 * @generated
	 */
	EClass getLicensePack();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicensePack#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_Identifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicensePack#getIssueDate <em>Issue Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Date</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getIssueDate()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_IssueDate();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicensePack#getProductIdentifier <em>Product Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getProductIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_ProductIdentifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicensePack#getProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getProductVersion()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_ProductVersion();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicensePack#getUserIdentifier <em>User Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getUserIdentifier()
	 * @see #getLicensePack()
	 * @generated
	 */
	EAttribute getLicensePack_UserIdentifier();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.LicensePack#getLicenseGrants <em>License Grants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>License Grants</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicensePack#getLicenseGrants()
	 * @see #getLicensePack()
	 * @generated
	 */
	EReference getLicensePack_LicenseGrants();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.LicenseGrant <em>License Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Grant</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant
	 * @generated
	 */
	EClass getLicenseGrant();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getFeatureIdentifier <em>Feature Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getFeatureIdentifier()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_FeatureIdentifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getMatchVersion <em>Match Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getMatchVersion()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_MatchVersion();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getMatchRule <em>Match Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Rule</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getMatchRule()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_MatchRule();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getValidFrom()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ValidFrom();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getValidUntil <em>Valid Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Until</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getValidUntil()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ValidUntil();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getConditionType <em>Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Type</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getConditionType()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ConditionType();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getConditionExpression <em>Condition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Expression</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getConditionExpression()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_ConditionExpression();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getCapacity()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EAttribute getLicenseGrant_Capacity();

		/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.LicenseGrant#getLicensePack <em>License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>License Pack</em>'.
	 * @see org.eclipse.passage.lic.model.api.LicenseGrant#getLicensePack()
	 * @see #getLicenseGrant()
	 * @generated
	 */
	EReference getLicenseGrant_LicensePack();

		/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LicFactory getLicFactory();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.Product#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.model.api.Product#getDescription()
	 * @see #getProduct()
	 * @generated
	 */
  EAttribute getProduct_Description();

  /**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.Product#getProductLine <em>Product Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product Line</em>'.
	 * @see org.eclipse.passage.lic.model.api.Product#getProductLine()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductLine();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.Product#getProductVersions <em>Product Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Product Versions</em>'.
	 * @see org.eclipse.passage.lic.model.api.Product#getProductVersions()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductVersions();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.ProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion
	 * @generated
	 */
	EClass getProductVersion();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersion#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion#getVersion()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_Version();

		/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.ProductVersion#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion#getProduct()
	 * @see #getProductVersion()
	 * @generated
	 */
	EReference getProductVersion_Product();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersion#getInstallationToken <em>Installation Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Installation Token</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion#getInstallationToken()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_InstallationToken();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersion#getSecureToken <em>Secure Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secure Token</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion#getSecureToken()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_SecureToken();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.ProductVersion#getProductVersionFeatures <em>Product Version Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Product Version Features</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion#getProductVersionFeatures()
	 * @see #getProductVersion()
	 * @generated
	 */
	EReference getProductVersion_ProductVersionFeatures();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersion#getNews <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>News</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersion#getNews()
	 * @see #getProductVersion()
	 * @generated
	 */
	EAttribute getProductVersion_News();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.ProductVersionFeature <em>Product Version Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Version Feature</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersionFeature
	 * @generated
	 */
	EClass getProductVersionFeature();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersionFeature#getFeatureIdentifier <em>Feature Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersionFeature#getFeatureIdentifier()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EAttribute getProductVersionFeature_FeatureIdentifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersionFeature#getFeatureVersion <em>Feature Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersionFeature#getFeatureVersion()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EAttribute getProductVersionFeature_FeatureVersion();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.ProductVersionFeature#getRestrictionLevel <em>Restriction Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Restriction Level</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersionFeature#getRestrictionLevel()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EAttribute getProductVersionFeature_RestrictionLevel();

		/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.model.api.ProductVersionFeature#getProductVersion <em>Product Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product Version</em>'.
	 * @see org.eclipse.passage.lic.model.api.ProductVersionFeature#getProductVersion()
	 * @see #getProductVersionFeature()
	 * @generated
	 */
	EReference getProductVersionFeature_ProductVersion();

		/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.model.api.UserOrigin <em>User Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Origin</em>'.
	 * @see org.eclipse.passage.lic.model.api.UserOrigin
	 * @generated
	 */
	EClass getUserOrigin();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.UserOrigin#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.model.api.UserOrigin#getIdentifier()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EAttribute getUserOrigin_Identifier();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.UserOrigin#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.model.api.UserOrigin#getName()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EAttribute getUserOrigin_Name();

		/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.model.api.UserOrigin#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.model.api.UserOrigin#getDescription()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EAttribute getUserOrigin_Description();

		/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.model.api.UserOrigin#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Users</em>'.
	 * @see org.eclipse.passage.lic.model.api.UserOrigin#getUsers()
	 * @see #getUserOrigin()
	 * @generated
	 */
	EReference getUserOrigin_Users();

		/**
	 * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
	 * @generated
	 */
  interface Literals {
    /**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor <em>Feature Set Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureSetDescriptor()
		 * @generated
		 */
		EClass FEATURE_SET_DESCRIPTOR = eINSTANCE.getFeatureSetDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.products.ProductDescriptor <em>Product Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.products.ProductDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductDescriptor()
		 * @generated
		 */
		EClass PRODUCT_DESCRIPTOR = eINSTANCE.getProductDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor <em>Product Version Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersionDescriptor()
		 * @generated
		 */
		EClass PRODUCT_VERSION_DESCRIPTOR = eINSTANCE.getProductVersionDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor <em>Product Version Feature Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersionFeatureDescriptor()
		 * @generated
		 */
		EClass PRODUCT_VERSION_FEATURE_DESCRIPTOR = eINSTANCE.getProductVersionFeatureDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.users.UserOriginDescriptor <em>User Origin Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.users.UserOriginDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUserOriginDescriptor()
		 * @generated
		 */
		EClass USER_ORIGIN_DESCRIPTOR = eINSTANCE.getUserOriginDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.features.FeatureDescriptor <em>Feature Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.features.FeatureDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureDescriptor()
		 * @generated
		 */
		EClass FEATURE_DESCRIPTOR = eINSTANCE.getFeatureDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor <em>Feature Version Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureVersionDescriptor()
		 * @generated
		 */
		EClass FEATURE_VERSION_DESCRIPTOR = eINSTANCE.getFeatureVersionDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.products.ProductLineDescriptor <em>Product Line Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.products.ProductLineDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductLineDescriptor()
		 * @generated
		 */
		EClass PRODUCT_LINE_DESCRIPTOR = eINSTANCE.getProductLineDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.users.UserDescriptor <em>User Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.users.UserDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUserDescriptor()
		 * @generated
		 */
		EClass USER_DESCRIPTOR = eINSTANCE.getUserDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor <em>License Pack Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicensePackDescriptor()
		 * @generated
		 */
		EClass LICENSE_PACK_DESCRIPTOR = eINSTANCE.getLicensePackDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor <em>License Grant Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.runtime.licenses.LicenseGrantDescriptor
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicenseGrantDescriptor()
		 * @generated
		 */
		EClass LICENSE_GRANT_DESCRIPTOR = eINSTANCE.getLicenseGrantDescriptor();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.FeatureSetImpl <em>Feature Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.FeatureSetImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureSet()
		 * @generated
		 */
		EClass FEATURE_SET = eINSTANCE.getFeatureSet();

				/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_SET__IDENTIFIER = eINSTANCE.getFeatureSet_Identifier();

				/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_SET__NAME = eINSTANCE.getFeatureSet_Name();

				/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_SET__DESCRIPTION = eINSTANCE.getFeatureSet_Description();

				/**
		 * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_SET__FEATURES = eINSTANCE.getFeatureSet_Features();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.ProductImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProduct()
		 * @generated
		 */
    EClass PRODUCT = eINSTANCE.getProduct();

    /**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @generated
		 */
    EAttribute PRODUCT__IDENTIFIER = eINSTANCE.getProduct_Identifier();

    /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @generated
		 */
    EAttribute PRODUCT__NAME = eINSTANCE.getProduct_Name();

    /**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.FeatureImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

				/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__IDENTIFIER = eINSTANCE.getFeature_Identifier();

				/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

				/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__DESCRIPTION = eINSTANCE.getFeature_Description();

				/**
		 * The meta object literal for the '<em><b>Feature Set</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__FEATURE_SET = eINSTANCE.getFeature_FeatureSet();

				/**
		 * The meta object literal for the '<em><b>Feature Versions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__FEATURE_VERSIONS = eINSTANCE.getFeature_FeatureVersions();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.FeatureVersionImpl <em>Feature Version</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.FeatureVersionImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getFeatureVersion()
		 * @generated
		 */
		EClass FEATURE_VERSION = eINSTANCE.getFeatureVersion();

				/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_VERSION__VERSION = eINSTANCE.getFeatureVersion_Version();

				/**
		 * The meta object literal for the '<em><b>Feature</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_VERSION__FEATURE = eINSTANCE.getFeatureVersion_Feature();

				/**
		 * The meta object literal for the '<em><b>News</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_VERSION__NEWS = eINSTANCE.getFeatureVersion_News();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.ProductLineImpl <em>Product Line</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.ProductLineImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductLine()
		 * @generated
		 */
		EClass PRODUCT_LINE = eINSTANCE.getProductLine();

				/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_LINE__IDENTIFIER = eINSTANCE.getProductLine_Identifier();

				/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_LINE__NAME = eINSTANCE.getProductLine_Name();

				/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_LINE__DESCRIPTION = eINSTANCE.getProductLine_Description();

				/**
		 * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_LINE__PRODUCTS = eINSTANCE.getProductLine_Products();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.UserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.UserImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUser()
		 * @generated
		 */
		EClass USER = eINSTANCE.getUser();

				/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__IDENTIFIER = eINSTANCE.getUser_Identifier();

				/**
		 * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__EMAIL = eINSTANCE.getUser_Email();

				/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__FULL_NAME = eINSTANCE.getUser_FullName();

				/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER__DESCRIPTION = eINSTANCE.getUser_Description();

				/**
		 * The meta object literal for the '<em><b>User Origin</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__USER_ORIGIN = eINSTANCE.getUser_UserOrigin();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.LicensePackImpl <em>License Pack</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.LicensePackImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicensePack()
		 * @generated
		 */
		EClass LICENSE_PACK = eINSTANCE.getLicensePack();

				/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_PACK__IDENTIFIER = eINSTANCE.getLicensePack_Identifier();

				/**
		 * The meta object literal for the '<em><b>Issue Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_PACK__ISSUE_DATE = eINSTANCE.getLicensePack_IssueDate();

				/**
		 * The meta object literal for the '<em><b>Product Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_PACK__PRODUCT_IDENTIFIER = eINSTANCE.getLicensePack_ProductIdentifier();

				/**
		 * The meta object literal for the '<em><b>Product Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_PACK__PRODUCT_VERSION = eINSTANCE.getLicensePack_ProductVersion();

				/**
		 * The meta object literal for the '<em><b>User Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_PACK__USER_IDENTIFIER = eINSTANCE.getLicensePack_UserIdentifier();

				/**
		 * The meta object literal for the '<em><b>License Grants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LICENSE_PACK__LICENSE_GRANTS = eINSTANCE.getLicensePack_LicenseGrants();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.LicenseGrantImpl <em>License Grant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.LicenseGrantImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getLicenseGrant()
		 * @generated
		 */
		EClass LICENSE_GRANT = eINSTANCE.getLicenseGrant();

				/**
		 * The meta object literal for the '<em><b>Feature Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__FEATURE_IDENTIFIER = eINSTANCE.getLicenseGrant_FeatureIdentifier();

				/**
		 * The meta object literal for the '<em><b>Match Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__MATCH_VERSION = eINSTANCE.getLicenseGrant_MatchVersion();

				/**
		 * The meta object literal for the '<em><b>Match Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__MATCH_RULE = eINSTANCE.getLicenseGrant_MatchRule();

				/**
		 * The meta object literal for the '<em><b>Valid From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__VALID_FROM = eINSTANCE.getLicenseGrant_ValidFrom();

				/**
		 * The meta object literal for the '<em><b>Valid Until</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__VALID_UNTIL = eINSTANCE.getLicenseGrant_ValidUntil();

				/**
		 * The meta object literal for the '<em><b>Condition Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__CONDITION_TYPE = eINSTANCE.getLicenseGrant_ConditionType();

				/**
		 * The meta object literal for the '<em><b>Condition Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__CONDITION_EXPRESSION = eINSTANCE.getLicenseGrant_ConditionExpression();

				/**
		 * The meta object literal for the '<em><b>Capacity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE_GRANT__CAPACITY = eINSTANCE.getLicenseGrant_Capacity();

				/**
		 * The meta object literal for the '<em><b>License Pack</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LICENSE_GRANT__LICENSE_PACK = eINSTANCE.getLicenseGrant_LicensePack();

				/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @generated
		 */
    EAttribute PRODUCT__DESCRIPTION = eINSTANCE.getProduct_Description();

				/**
		 * The meta object literal for the '<em><b>Product Line</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__PRODUCT_LINE = eINSTANCE.getProduct_ProductLine();

				/**
		 * The meta object literal for the '<em><b>Product Versions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__PRODUCT_VERSIONS = eINSTANCE.getProduct_ProductVersions();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.ProductVersionImpl <em>Product Version</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.ProductVersionImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersion()
		 * @generated
		 */
		EClass PRODUCT_VERSION = eINSTANCE.getProductVersion();

				/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION__VERSION = eINSTANCE.getProductVersion_Version();

				/**
		 * The meta object literal for the '<em><b>Product</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_VERSION__PRODUCT = eINSTANCE.getProductVersion_Product();

				/**
		 * The meta object literal for the '<em><b>Installation Token</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION__INSTALLATION_TOKEN = eINSTANCE.getProductVersion_InstallationToken();

				/**
		 * The meta object literal for the '<em><b>Secure Token</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION__SECURE_TOKEN = eINSTANCE.getProductVersion_SecureToken();

				/**
		 * The meta object literal for the '<em><b>Product Version Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_VERSION__PRODUCT_VERSION_FEATURES = eINSTANCE.getProductVersion_ProductVersionFeatures();

				/**
		 * The meta object literal for the '<em><b>News</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION__NEWS = eINSTANCE.getProductVersion_News();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl <em>Product Version Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.ProductVersionFeatureImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getProductVersionFeature()
		 * @generated
		 */
		EClass PRODUCT_VERSION_FEATURE = eINSTANCE.getProductVersionFeature();

				/**
		 * The meta object literal for the '<em><b>Feature Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER = eINSTANCE.getProductVersionFeature_FeatureIdentifier();

				/**
		 * The meta object literal for the '<em><b>Feature Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION_FEATURE__FEATURE_VERSION = eINSTANCE.getProductVersionFeature_FeatureVersion();

				/**
		 * The meta object literal for the '<em><b>Restriction Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_VERSION_FEATURE__RESTRICTION_LEVEL = eINSTANCE.getProductVersionFeature_RestrictionLevel();

				/**
		 * The meta object literal for the '<em><b>Product Version</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_VERSION_FEATURE__PRODUCT_VERSION = eINSTANCE.getProductVersionFeature_ProductVersion();

				/**
		 * The meta object literal for the '{@link org.eclipse.passage.lic.model.impl.UserOriginImpl <em>User Origin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.passage.lic.model.impl.UserOriginImpl
		 * @see org.eclipse.passage.lic.model.impl.LicPackageImpl#getUserOrigin()
		 * @generated
		 */
		EClass USER_ORIGIN = eINSTANCE.getUserOrigin();

				/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_ORIGIN__IDENTIFIER = eINSTANCE.getUserOrigin_Identifier();

				/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_ORIGIN__NAME = eINSTANCE.getUserOrigin_Name();

				/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_ORIGIN__DESCRIPTION = eINSTANCE.getUserOrigin_Description();

				/**
		 * The meta object literal for the '<em><b>Users</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_ORIGIN__USERS = eINSTANCE.getUserOrigin_Users();

  }

} //PassagePackage
