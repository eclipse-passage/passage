/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.shipments.model.meta;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.passage.lic.shipments.model.api.Floating;
import org.eclipse.passage.lic.shipments.model.api.FloatingLicense;
import org.eclipse.passage.lic.shipments.model.api.Personal;
import org.eclipse.passage.lic.shipments.model.api.PersonalLicense;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage
 * @generated
 */
public interface ShipmentsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ShipmentsFactory eINSTANCE = org.eclipse.passage.lic.shipments.model.impl.ShipmentsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Personal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Personal</em>'.
	 * @generated
	 */
	Personal createPersonal();

	/**
	 * Returns a new object of class '<em>Floating</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating</em>'.
	 * @generated
	 */
	Floating createFloating();

	/**
	 * Returns a new object of class '<em>Personal License</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Personal License</em>'.
	 * @generated
	 */
	PersonalLicense createPersonalLicense();

	/**
	 * Returns a new object of class '<em>Floating License</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating License</em>'.
	 * @generated
	 */
	FloatingLicense createFloatingLicense();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ShipmentsPackage getShipmentsPackage();

} //ShipmentsFactory
