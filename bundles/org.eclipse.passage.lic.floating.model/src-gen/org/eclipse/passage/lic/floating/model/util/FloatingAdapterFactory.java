/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.floating.model.api.*;
import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.api.UserGrant;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriod;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.floating.model.api.VersionMatch;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage
 * @generated
 */
public class FloatingAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static FloatingPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public FloatingAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FloatingPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!--
	 * begin-user-doc --> This implementation returns <code>true</code> if the
	 * object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * 
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
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FloatingSwitch<Adapter> modelSwitch = new FloatingSwitch<Adapter>() {
		@Override
		public Adapter caseFloatingLicensePack(FloatingLicensePack object) {
			return createFloatingLicensePackAdapter();
		}

		@Override
		public Adapter caseLicenseRequisites(LicenseRequisites object) {
			return createLicenseRequisitesAdapter();
		}

		@Override
		public Adapter caseProductRef(ProductRef object) {
			return createProductRefAdapter();
		}

		@Override
		public Adapter caseFloatingServer(FloatingServer object) {
			return createFloatingServerAdapter();
		}

		@Override
		public Adapter caseUserGrant(UserGrant object) {
			return createUserGrantAdapter();
		}

		@Override
		public Adapter caseFeatureGrant(FeatureGrant object) {
			return createFeatureGrantAdapter();
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
		public Adapter caseEvaluationInstructions(EvaluationInstructions object) {
			return createEvaluationInstructionsAdapter();
		}

		@Override
		public Adapter caseVersionMatch(VersionMatch object) {
			return createVersionMatchAdapter();
		}

		@Override
		public Adapter caseFloatingLicenseAccess(FloatingLicenseAccess object) {
			return createFloatingLicenseAccessAdapter();
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
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack <em>License Pack</em>}'.
	 * <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack
	 * @generated
	 */
	public Adapter createFloatingLicensePackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites <em>License Requisites</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites
	 * @generated
	 */
	public Adapter createLicenseRequisitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.ProductRef <em>Product Ref</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.ProductRef
	 * @generated
	 */
	public Adapter createProductRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.FloatingServer <em>Server</em>}'.
	 * <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingServer
	 * @generated
	 */
	public Adapter createFloatingServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.UserGrant <em>User Grant</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.UserGrant
	 * @generated
	 */
	public Adapter createUserGrantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant <em>Feature Grant</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant
	 * @generated
	 */
	public Adapter createFeatureGrantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriod <em>Validity Period</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriod
	 * @generated
	 */
	public Adapter createValidityPeriodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed <em>Validity Period Closed</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed
	 * @generated
	 */
	public Adapter createValidityPeriodClosedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.EvaluationInstructions <em>Evaluation Instructions</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.EvaluationInstructions
	 * @generated
	 */
	public Adapter createEvaluationInstructionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.VersionMatch <em>Version Match</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.VersionMatch
	 * @generated
	 */
	public Adapter createVersionMatchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess <em>License Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess
	 * @generated
	 */
	public Adapter createFloatingLicenseAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection <em>Server Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingServerConnection
	 * @generated
	 */
	public Adapter createFloatingServerConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.passage.lic.floating.model.api.GrantAcqisition <em>Grant Acqisition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.passage.lic.floating.model.api.GrantAcqisition
	 * @generated
	 */
	public Adapter createGrantAcqisitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // FloatingAdapterFactory
