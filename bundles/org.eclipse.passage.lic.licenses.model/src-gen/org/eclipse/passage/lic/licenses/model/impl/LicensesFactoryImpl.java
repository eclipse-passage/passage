/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.passage.lic.licenses.model.api.*;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * 
 * An implementation of the model <b>Factory</b>.
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class LicensesFactoryImpl extends EFactoryImpl implements LicensesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LicensesFactory init() {
		try {
			LicensesFactory theLicensesFactory = (LicensesFactory) EPackage.Registry.INSTANCE
					.getEFactory(LicensesPackage.eNS_URI);
			if (theLicensesFactory != null) {
				return theLicensesFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LicensesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicensesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case LicensesPackage.LICENSE_PLAN:
			return createLicensePlan();
		case LicensesPackage.LICENSE_PLAN_FEATURE:
			return createLicensePlanFeature();
		case LicensesPackage.LICENSE_PACK:
			return createLicensePack();
		case LicensesPackage.LICENSE_GRANT:
			return createLicenseGrant();
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
	public LicensePlan createLicensePlan() {
		LicensePlanImpl licensePlan = new LicensePlanImpl();
		return licensePlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensePlanFeature createLicensePlanFeature() {
		LicensePlanFeatureImpl licensePlanFeature = new LicensePlanFeatureImpl();
		return licensePlanFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
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
	 * 
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
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LicensesPackage getLicensesPackage() {
		return (LicensesPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LicensesPackage getPackage() {
		return LicensesPackage.eINSTANCE;
	}

} // LicensesFactoryImpl
