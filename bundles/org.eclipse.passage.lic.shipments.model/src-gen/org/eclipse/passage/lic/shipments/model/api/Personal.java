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
package org.eclipse.passage.lic.shipments.model.api;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Personal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.api.Personal#getLicenses <em>Licenses</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getPersonal()
 * @model
 * @generated
 */
public interface Personal extends EObject {
	/**
	 * Returns the value of the '<em><b>Licenses</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Licenses</em>' containment reference list.
	 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getPersonal_Licenses()
	 * @model containment="true"
	 * @generated
	 */
	EList<PersonalLicense> getLicenses();

} // Personal
