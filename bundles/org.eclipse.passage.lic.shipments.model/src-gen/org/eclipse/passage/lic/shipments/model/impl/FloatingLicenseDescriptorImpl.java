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
package org.eclipse.passage.lic.shipments.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor;

import org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Floating License Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class FloatingLicenseDescriptorImpl extends MinimalEObjectImpl.Container
		implements FloatingLicenseDescriptor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatingLicenseDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShipmentsPackage.eINSTANCE.getFloatingLicenseDescriptor();
	}

} //FloatingLicenseDescriptorImpl
