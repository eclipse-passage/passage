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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.passage.lic.keys.KeyPairRO;
import org.eclipse.passage.lic.keys.ProductRefRO;

import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.lic.keys.model.api.ProductRef;

import org.eclipse.passage.lic.keys.model.meta.KeysFactory;
import org.eclipse.passage.lic.keys.model.meta.KeysPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KeysPackageImpl extends EPackageImpl implements KeysPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keyPairEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keyPairROEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productRefROEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private KeysPackageImpl() {
		super(eNS_URI, KeysFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link KeysPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static KeysPackage init() {
		if (isInited)
			return (KeysPackage) EPackage.Registry.INSTANCE.getEPackage(KeysPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredKeysPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		KeysPackageImpl theKeysPackage = registeredKeysPackage instanceof KeysPackageImpl
				? (KeysPackageImpl) registeredKeysPackage
				: new KeysPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theKeysPackage.createPackageContents();

		// Initialize created meta-data
		theKeysPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKeysPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KeysPackage.eNS_URI, theKeysPackage);
		return theKeysPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKeyPair() {
		return keyPairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getKeyPair_Product() {
		return (EReference) keyPairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeyPair_Algorithm() {
		return (EAttribute) keyPairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeyPair_Key() {
		return (EAttribute) keyPairEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeyPair_Pub() {
		return (EAttribute) keyPairEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeyPair_Scr() {
		return (EAttribute) keyPairEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductRef() {
		return productRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Identifier() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProductRef_Version() {
		return (EAttribute) productRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKeyPairRO() {
		return keyPairROEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProductRefRO() {
		return productRefROEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KeysFactory getKeysFactory() {
		return (KeysFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		keyPairEClass = createEClass(KEY_PAIR);
		createEReference(keyPairEClass, KEY_PAIR__PRODUCT);
		createEAttribute(keyPairEClass, KEY_PAIR__ALGORITHM);
		createEAttribute(keyPairEClass, KEY_PAIR__KEY);
		createEAttribute(keyPairEClass, KEY_PAIR__PUB);
		createEAttribute(keyPairEClass, KEY_PAIR__SCR);

		productRefEClass = createEClass(PRODUCT_REF);
		createEAttribute(productRefEClass, PRODUCT_REF__IDENTIFIER);
		createEAttribute(productRefEClass, PRODUCT_REF__VERSION);

		keyPairROEClass = createEClass(KEY_PAIR_RO);

		productRefROEClass = createEClass(PRODUCT_REF_RO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		keyPairEClass.getESuperTypes().add(this.getKeyPairRO());
		productRefEClass.getESuperTypes().add(this.getProductRefRO());

		// Initialize classes, features, and operations; add parameters
		initEClass(keyPairEClass, KeyPair.class, "KeyPair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getKeyPair_Product(), this.getProductRef(), null, "product", null, 1, 1, KeyPair.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyPair_Algorithm(), ecorePackage.getEString(), "algorithm", null, 1, 1, KeyPair.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyPair_Key(), ecorePackage.getEInt(), "key", null, 1, 1, KeyPair.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyPair_Pub(), ecorePackage.getEString(), "pub", null, 1, 1, KeyPair.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyPair_Scr(), ecorePackage.getEString(), "scr", null, 1, 1, KeyPair.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productRefEClass, ProductRef.class, "ProductRef", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductRef_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, //$NON-NLS-1$
				ProductRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductRef_Version(), ecorePackage.getEString(), "version", null, 1, 1, ProductRef.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(keyPairROEClass, KeyPairRO.class, "KeyPairRO", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(productRefROEClass, ProductRefRO.class, "ProductRefRO", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //KeysPackageImpl
