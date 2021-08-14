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
package org.eclipse.passage.lic.licenses.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.licenses.AgreementDataDescriptor;
import org.eclipse.passage.lic.licenses.CompanyRefDescriptor;
import org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor;
import org.eclipse.passage.lic.licenses.FeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.FeatureRefDescriptor;
import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.FloatingServerDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.ProductRefDescriptor;
import org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor;
import org.eclipse.passage.lic.licenses.SignatureDescriptor;
import org.eclipse.passage.lic.licenses.UserGrantDescriptor;
import org.eclipse.passage.lic.licenses.UserRefDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor;
import org.eclipse.passage.lic.licenses.VersionMatchDescriptor;

import org.eclipse.passage.lic.licenses.model.api.*;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.licenses.model.meta.LicensesPackage
 * @generated
 */
public class LicensesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LicensesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicensesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LicensesPackage.eINSTANCE;
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
	protected LicensesSwitch<Adapter> modelSwitch = new LicensesSwitch<Adapter>() {
		@Override
		public Adapter caseAgreementDataDescriptor(AgreementDataDescriptor object) {
			return createAgreementDataDescriptorAdapter();
		}

		@Override
		public Adapter caseCompanyRefDescriptor(CompanyRefDescriptor object) {
			return createCompanyRefDescriptorAdapter();
		}

		@Override
		public Adapter caseEvaluationInstructionsDescriptor(EvaluationInstructionsDescriptor object) {
			return createEvaluationInstructionsDescriptorAdapter();
		}

		@Override
		public Adapter caseFeatureGrantDescriptor(FeatureGrantDescriptor object) {
			return createFeatureGrantDescriptorAdapter();
		}

		@Override
		public Adapter caseFeatureRefDescriptor(FeatureRefDescriptor object) {
			return createFeatureRefDescriptorAdapter();
		}

		@Override
		public Adapter caseFloatingLicensePackDescriptor(FloatingLicensePackDescriptor object) {
			return createFloatingLicensePackDescriptorAdapter();
		}

		@Override
		public Adapter caseFloatingLicenseRequisitesDescriptor(FloatingLicenseRequisitesDescriptor object) {
			return createFloatingLicenseRequisitesDescriptorAdapter();
		}

		@Override
		public Adapter caseFloatingServerDescriptor(FloatingServerDescriptor object) {
			return createFloatingServerDescriptorAdapter();
		}

		@Override
		public Adapter caseLicensePlanDescriptor(LicensePlanDescriptor object) {
			return createLicensePlanDescriptorAdapter();
		}

		@Override
		public Adapter caseLicensePlanFeatureDescriptor(LicensePlanFeatureDescriptor object) {
			return createLicensePlanFeatureDescriptorAdapter();
		}

		@Override
		public Adapter caseLicenseRequisitesDescriptor(LicenseRequisitesDescriptor object) {
			return createLicenseRequisitesDescriptorAdapter();
		}

		@Override
		public Adapter casePersonalFeatureGrantDescriptor(PersonalFeatureGrantDescriptor object) {
			return createPersonalFeatureGrantDescriptorAdapter();
		}

		@Override
		public Adapter casePersonalLicensePackDescriptor(PersonalLicensePackDescriptor object) {
			return createPersonalLicensePackDescriptorAdapter();
		}

		@Override
		public Adapter casePersonalLicenseRequisitesDescriptor(PersonalLicenseRequisitesDescriptor object) {
			return createPersonalLicenseRequisitesDescriptorAdapter();
		}

		@Override
		public Adapter caseProductRefDescriptor(ProductRefDescriptor object) {
			return createProductRefDescriptorAdapter();
		}

		@Override
		public Adapter caseSignatureAttributeDescriptor(SignatureAttributeDescriptor object) {
			return createSignatureAttributeDescriptorAdapter();
		}

		@Override
		public Adapter caseSignatureDescriptor(SignatureDescriptor object) {
			return createSignatureDescriptorAdapter();
		}

		@Override
		public Adapter caseUserGrantDescriptor(UserGrantDescriptor object) {
			return createUserGrantDescriptorAdapter();
		}

		@Override
		public Adapter caseUserRefDescriptor(UserRefDescriptor object) {
			return createUserRefDescriptorAdapter();
		}

		@Override
		public Adapter caseValidityPeriodClosedDescriptor(ValidityPeriodClosedDescriptor object) {
			return createValidityPeriodClosedDescriptorAdapter();
		}

		@Override
		public Adapter caseValidityPeriodDescriptor(ValidityPeriodDescriptor object) {
			return createValidityPeriodDescriptorAdapter();
		}

		@Override
		public Adapter caseVersionMatchDescriptor(VersionMatchDescriptor object) {
			return createVersionMatchDescriptorAdapter();
		}

		@Override
		public Adapter caseAgreementData(AgreementData object) {
			return createAgreementDataAdapter();
		}

		@Override
		public Adapter caseCompanyRef(CompanyRef object) {
			return createCompanyRefAdapter();
		}

		@Override
		public Adapter caseEvaluationInstructions(EvaluationInstructions object) {
			return createEvaluationInstructionsAdapter();
		}

		@Override
		public Adapter caseFeatureGrant(FeatureGrant object) {
			return createFeatureGrantAdapter();
		}

		@Override
		public Adapter caseFeatureRef(FeatureRef object) {
			return createFeatureRefAdapter();
		}

		@Override
		public Adapter caseFloatingLicenseAccess(FloatingLicenseAccess object) {
			return createFloatingLicenseAccessAdapter();
		}

		@Override
		public Adapter caseFloatingLicensePack(FloatingLicensePack object) {
			return createFloatingLicensePackAdapter();
		}

		@Override
		public Adapter caseFloatingLicenseRequisites(FloatingLicenseRequisites object) {
			return createFloatingLicenseRequisitesAdapter();
		}

		@Override
		public Adapter caseFloatingServer(FloatingServer object) {
			return createFloatingServerAdapter();
		}

		@Override
		public Adapter caseFloatingServerConnection(FloatingServerConnection object) {
			return createFloatingServerConnectionAdapter();
		}

		@Override
		public Adapter caseGrantAcqisition(GrantAcqisition object) {
			return createGrantAcqisitionAdapter();
		}

		@Override
		public Adapter caseLicensePlan(LicensePlan object) {
			return createLicensePlanAdapter();
		}

		@Override
		public Adapter caseLicensePlanFeature(LicensePlanFeature object) {
			return createLicensePlanFeatureAdapter();
		}

		@Override
		public Adapter caseLicenseRequisites(LicenseRequisites object) {
			return createLicenseRequisitesAdapter();
		}

		@Override
		public Adapter casePersonalFeatureGrant(PersonalFeatureGrant object) {
			return createPersonalFeatureGrantAdapter();
		}

		@Override
		public Adapter casePersonalLicensePack(PersonalLicensePack object) {
			return createPersonalLicensePackAdapter();
		}

		@Override
		public Adapter casePersonalLicenseRequisites(PersonalLicenseRequisites object) {
			return createPersonalLicenseRequisitesAdapter();
		}

		@Override
		public Adapter caseProductRef(ProductRef object) {
			return createProductRefAdapter();
		}

		@Override
		public Adapter caseSignature(Signature object) {
			return createSignatureAdapter();
		}

		@Override
		public Adapter caseSignatureAttribute(SignatureAttribute object) {
			return createSignatureAttributeAdapter();
		}

		@Override
		public Adapter caseUserGrant(UserGrant object) {
			return createUserGrantAdapter();
		}

		@Override
		public Adapter caseUserRef(UserRef object) {
			return createUserRefAdapter();
		}

		@Override
		public Adapter caseValidityPeriod(ValidityPeriod object) {
			return createValidityPeriodAdapter();
		}

		@Override
		public Adapter caseValidityPeriodClosed(ValidityPeriodClosed object) {
			return createValidityPeriodClosedAdapter();
		}

		@Override
		public Adapter caseVersionMatch(VersionMatch object) {
			return createVersionMatchAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.AgreementDataDescriptor <em>Agreement Data Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.AgreementDataDescriptor
	 * @since 2.1
	 * @generated
	 */
	public Adapter createAgreementDataDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.LicensePlanDescriptor <em>License Plan Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.LicensePlanDescriptor
	 * @generated
	 */
	public Adapter createLicensePlanDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor <em>License Plan Feature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor
	 * @generated
	 */
	public Adapter createLicensePlanFeatureDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor <em>Personal License Pack Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createPersonalLicensePackDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.ProductRefDescriptor <em>Product Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.ProductRefDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createProductRefDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.FeatureRefDescriptor <em>Feature Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.FeatureRefDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFeatureRefDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.UserRefDescriptor <em>User Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.UserRefDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createUserRefDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.CompanyRefDescriptor <em>Company Ref Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.CompanyRefDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createCompanyRefDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor <em>License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createLicenseRequisitesDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor <em>Personal Feature Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createPersonalFeatureGrantDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor <em>Personal License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.PersonalLicenseRequisitesDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createPersonalLicenseRequisitesDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor <em>Floating License Requisites Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.FloatingLicenseRequisitesDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingLicenseRequisitesDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor <em>Validity Period Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createValidityPeriodDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor <em>Validity Period Closed Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createValidityPeriodClosedDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor <em>Floating License Pack Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingLicensePackDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.FloatingServerDescriptor <em>Floating Server Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.FloatingServerDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingServerDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.UserGrantDescriptor <em>User Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.UserGrantDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createUserGrantDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.FeatureGrantDescriptor <em>Feature Grant Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.FeatureGrantDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFeatureGrantDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor <em>Evaluation Instructions Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createEvaluationInstructionsDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.VersionMatchDescriptor <em>Version Match Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.VersionMatchDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createVersionMatchDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.AgreementData <em>Agreement Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.AgreementData
	 * @since 2.1
	 * @generated
	 */
	public Adapter createAgreementDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.SignatureDescriptor <em>Signature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.SignatureDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createSignatureDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor <em>Signature Attribute Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.SignatureAttributeDescriptor
	 * @since 2.0
	 * @generated
	 */
	public Adapter createSignatureAttributeDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlan <em>License Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlan
	 * @generated
	 */
	public Adapter createLicensePlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature <em>License Plan Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature
	 * @generated
	 */
	public Adapter createLicensePlanFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant <em>Personal Feature Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant
	 * @since 2.0
	 * @generated
	 */
	public Adapter createPersonalFeatureGrantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack <em>Personal License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack
	 * @since 2.0
	 * @generated
	 */
	public Adapter createPersonalLicensePackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack <em>Floating License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingLicensePackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.LicenseRequisites <em>License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.LicenseRequisites
	 * @since 2.1
	 * @generated
	 */
	public Adapter createLicenseRequisitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites <em>Personal License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites
	 * @since 2.0
	 * @generated
	 */
	public Adapter createPersonalLicenseRequisitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites <em>Floating License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingLicenseRequisitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.ProductRef <em>Product Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.ProductRef
	 * @since 2.0
	 * @generated
	 */
	public Adapter createProductRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FeatureRef <em>Feature Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureRef
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFeatureRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.UserRef <em>User Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserRef
	 * @since 2.0
	 * @generated
	 */
	public Adapter createUserRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.CompanyRef <em>Company Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.CompanyRef
	 * @since 2.0
	 * @generated
	 */
	public Adapter createCompanyRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServer <em>Floating Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServer
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.UserGrant <em>User Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.UserGrant
	 * @since 2.0
	 * @generated
	 */
	public Adapter createUserGrantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FeatureGrant <em>Feature Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FeatureGrant
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFeatureGrantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriod <em>Validity Period</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriod
	 * @since 2.0
	 * @generated
	 */
	public Adapter createValidityPeriodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed <em>Validity Period Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed
	 * @since 2.0
	 * @generated
	 */
	public Adapter createValidityPeriodClosedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions <em>Evaluation Instructions</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions
	 * @since 2.0
	 * @generated
	 */
	public Adapter createEvaluationInstructionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.VersionMatch <em>Version Match</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.VersionMatch
	 * @since 2.0
	 * @generated
	 */
	public Adapter createVersionMatchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess <em>Floating License Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingLicenseAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection <em>Floating Server Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection
	 * @since 2.0
	 * @generated
	 */
	public Adapter createFloatingServerConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.GrantAcqisition <em>Grant Acqisition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.GrantAcqisition
	 * @since 2.0
	 * @generated
	 */
	public Adapter createGrantAcqisitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.Signature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.Signature
	 * @since 2.0
	 * @generated
	 */
	public Adapter createSignatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.licenses.model.api.SignatureAttribute <em>Signature Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.licenses.model.api.SignatureAttribute
	 * @since 2.0
	 * @generated
	 */
	public Adapter createSignatureAttributeAdapter() {
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

} //LicensesAdapterFactory
