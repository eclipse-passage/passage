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
package org.eclipse.passage.lic.keys.model.meta;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.lic.keys.model.api.ProductRef;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage
 * @generated
 */
public interface KeysFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KeysFactory eINSTANCE = org.eclipse.passage.lic.keys.model.impl.KeysFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Key Pair</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Key Pair</em>'.
	 * @generated
	 */
	KeyPair createKeyPair();

	/**
	 * Returns a new object of class '<em>Product Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Ref</em>'.
	 * @generated
	 */
	ProductRef createProductRef();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	KeysPackage getKeysPackage();

} //KeysFactory
