/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
package org.eclipse.passage.lic.keys.model.meta;

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
 * @see org.eclipse.passage.lic.keys.model.meta.KeysFactory
 * @model kind="package"
 * @generated
 */
public interface KeysPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "keys"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/keys/3.0.0"; //$NON-NLS-1$

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
	KeysPackage eINSTANCE = org.eclipse.passage.lic.keys.model.impl.KeysPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.keys.model.impl.KeyPairImpl <em>Key Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.keys.model.impl.KeyPairImpl
	 * @see org.eclipse.passage.lic.keys.model.impl.KeysPackageImpl#getKeyPair()
	 * @generated
	 */
	int KEY_PAIR = 0;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__PRODUCT = 0;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__ALGORITHM = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__KEY = 2;

	/**
	 * The feature id for the '<em><b>Pub</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__PUB = 3;

	/**
	 * The feature id for the '<em><b>Scr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR__SCR = 4;

	/**
	 * The number of structural features of the '<em>Key Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Key Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_PAIR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.keys.model.impl.ProductRefImpl <em>Product Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.keys.model.impl.ProductRefImpl
	 * @see org.eclipse.passage.lic.keys.model.impl.KeysPackageImpl#getProductRef()
	 * @generated
	 */
	int PRODUCT_REF = 1;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__VERSION = 1;

	/**
	 * The number of structural features of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.keys.model.api.KeyPair <em>Key Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Pair</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.KeyPair
	 * @generated
	 */
	EClass getKeyPair();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.KeyPair#getProduct()
	 * @see #getKeyPair()
	 * @generated
	 */
	EReference getKeyPair_Product();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.KeyPair#getAlgorithm()
	 * @see #getKeyPair()
	 * @generated
	 */
	EAttribute getKeyPair_Algorithm();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.KeyPair#getKey()
	 * @see #getKeyPair()
	 * @generated
	 */
	EAttribute getKeyPair_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getPub <em>Pub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pub</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.KeyPair#getPub()
	 * @see #getKeyPair()
	 * @generated
	 */
	EAttribute getKeyPair_Pub();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getScr <em>Scr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scr</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.KeyPair#getScr()
	 * @see #getKeyPair()
	 * @generated
	 */
	EAttribute getKeyPair_Scr();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.keys.model.api.ProductRef <em>Product Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Ref</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.ProductRef
	 * @generated
	 */
	EClass getProductRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.keys.model.api.ProductRef#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.ProductRef#getIdentifier()
	 * @see #getProductRef()
	 * @generated
	 */
	EAttribute getProductRef_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.keys.model.api.ProductRef#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.keys.model.api.ProductRef#getVersion()
	 * @see #getProductRef()
	 * @generated
	 */
	EAttribute getProductRef_Version();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	KeysFactory getKeysFactory();

} // KeysPackage
