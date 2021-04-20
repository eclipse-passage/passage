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
package org.eclipse.passage.lic.keys.model.api;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.keys.KeyPairRO;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Key Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getProduct <em>Product</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getPub <em>Pub</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getScr <em>Scr</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#getKeyPair()
 * @model superTypes="org.eclipse.passage.lic.keys.model.api.KeyPairRO"
 * @generated
 */
public interface KeyPair extends EObject, KeyPairRO {
	/**
	 * Returns the value of the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' containment reference.
	 * @see #setProduct(ProductRef)
	 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#getKeyPair_Product()
	 * @model containment="true" required="true"
	 * @generated
	 */
	@Override
	ProductRef getProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getProduct <em>Product</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' containment reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(ProductRef value);

	/**
	 * Returns the value of the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Algorithm</em>' attribute.
	 * @see #setAlgorithm(String)
	 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#getKeyPair_Algorithm()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getAlgorithm();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getAlgorithm <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Algorithm</em>' attribute.
	 * @see #getAlgorithm()
	 * @generated
	 */
	void setAlgorithm(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(int)
	 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#getKeyPair_Key()
	 * @model required="true"
	 * @generated
	 */
	@Override
	int getKey();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(int value);

	/**
	 * Returns the value of the '<em><b>Pub</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pub</em>' attribute.
	 * @see #setPub(String)
	 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#getKeyPair_Pub()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getPub();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getPub <em>Pub</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pub</em>' attribute.
	 * @see #getPub()
	 * @generated
	 */
	void setPub(String value);

	/**
	 * Returns the value of the '<em><b>Scr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scr</em>' attribute.
	 * @see #setScr(String)
	 * @see org.eclipse.passage.lic.keys.model.meta.KeysPackage#getKeyPair_Scr()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getScr();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.keys.model.api.KeyPair#getScr <em>Scr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scr</em>' attribute.
	 * @see #getScr()
	 * @generated
	 */
	void setScr(String value);

} // KeyPair
