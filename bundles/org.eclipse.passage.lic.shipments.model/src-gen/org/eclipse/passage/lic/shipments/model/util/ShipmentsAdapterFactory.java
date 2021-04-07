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
package org.eclipse.passage.lic.shipments.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor;
import org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor;

import org.eclipse.passage.lic.shipments.model.api.*;

import org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage
 * @generated
 */
public class ShipmentsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ShipmentsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShipmentsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ShipmentsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShipmentsSwitch<Adapter> modelSwitch = new ShipmentsSwitch<Adapter>() {
		@Override
		public Adapter casePersonal(Personal object) {
			return createPersonalAdapter();
		}

		@Override
		public Adapter caseFloating(Floating object) {
			return createFloatingAdapter();
		}

		@Override
		public Adapter casePersonalLicenseDescriptor(PersonalLicenseDescriptor object) {
			return createPersonalLicenseDescriptorAdapter();
		}

		@Override
		public Adapter casePersonalLicense(PersonalLicense object) {
			return createPersonalLicenseAdapter();
		}

		@Override
		public Adapter caseFloatingLicenseDescriptor(FloatingLicenseDescriptor object) {
			return createFloatingLicenseDescriptorAdapter();
		}

		@Override
		public Adapter caseFloatingLicense(FloatingLicense object) {
			return createFloatingLicenseAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.shipments.model.api.Personal <em>Personal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.shipments.model.api.Personal
	 * @generated
	 */
	public Adapter createPersonalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.shipments.model.api.Floating <em>Floating</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.shipments.model.api.Floating
	 * @generated
	 */
	public Adapter createFloatingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor <em>Personal License Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor
	 * @generated
	 */
	public Adapter createPersonalLicenseDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense <em>Personal License</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.shipments.model.api.PersonalLicense
	 * @generated
	 */
	public Adapter createPersonalLicenseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor <em>Floating License Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor
	 * @generated
	 */
	public Adapter createFloatingLicenseDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense <em>Floating License</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.shipments.model.api.FloatingLicense
	 * @generated
	 */
	public Adapter createFloatingLicenseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ShipmentsAdapterFactory