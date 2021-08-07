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
package org.eclipse.passage.lic.agreements.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.passage.lic.agreements.model.api.*;

import org.eclipse.passage.lic.agreements.model.meta.AgreementsFactory;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AgreementsFactoryImpl extends EFactoryImpl implements AgreementsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AgreementsFactory init() {
		try {
			AgreementsFactory theAgreementsFactory = (AgreementsFactory) EPackage.Registry.INSTANCE
					.getEFactory(AgreementsPackage.eNS_URI);
			if (theAgreementsFactory != null) {
				return theAgreementsFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AgreementsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AgreementsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case AgreementsPackage.AGREEMENT:
			return createAgreement();
		case AgreementsPackage.AGREEMENT_GROUP:
			return createAgreementGroup();
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
	public Agreement createAgreement() {
		AgreementImpl agreement = new AgreementImpl();
		return agreement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreementGroup createAgreementGroup() {
		AgreementGroupImpl agreementGroup = new AgreementGroupImpl();
		return agreementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreementsPackage getAgreementsPackage() {
		return (AgreementsPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AgreementsPackage getPackage() {
		return AgreementsPackage.eINSTANCE;
	}

} //AgreementsFactoryImpl
