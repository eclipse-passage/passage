/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.model.migration;

import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.passage.lic.emf.migration.DelegateClassifiers;
import org.eclipse.passage.lic.emf.migration.EClassRoutes;
import org.eclipse.passage.lic.emf.migration.EnsureStructure;
import org.eclipse.passage.lic.emf.migration.SimpleClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleFeatureRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

public class LicensesResourceHandler extends MigratingResourceHandler {

	@Override
	public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		super.postLoad(resource, inputStream, options);
		resource.getContents().stream()//
				.filter(PersonalLicensePack.class::isInstance)//
				.map(PersonalLicensePack.class::cast).forEach(new AssignGrantIdentifiers());
	}

	@Override
	protected void ensureMigrations() {
		migrate033();
		migrate040();
		migrate050();
		migrate100();
		migrate110();
	}

	@Override
	protected SimpleFeatureRoutes attributes() {
		SimpleFeatureRoutes routes = new SimpleFeatureRoutes();
		LicensesPackage licenses = LicensesPackage.eINSTANCE;
		routes.define("conditionExpression", licenses.getEvaluationInstructions_Expression()); //$NON-NLS-1$
		routes.define("conditionType", licenses.getEvaluationInstructions_Type()); //$NON-NLS-1$
		routes.define("featureIdentifier", licenses.getFeatureRef_Identifier()); //$NON-NLS-1$
		routes.define("identifier", licenses.getLicenseGrant_Identifier()); //$NON-NLS-1$
		routes.define("identifier", licenses.getLicenseRequisites_Identifier()); //$NON-NLS-1$
		routes.define("issueDate", licenses.getLicenseRequisites_IssueDate()); //$NON-NLS-1$
		routes.define("licenseGrants", licenses.getPersonalLicensePack_Grants()); //$NON-NLS-1$
		routes.define("matchRule", licenses.getVersionMatch_Rule()); //$NON-NLS-1$
		routes.define("matchVersion", licenses.getVersionMatch_Version()); //$NON-NLS-1$
		routes.define("planIdentifier", licenses.getLicenseRequisites_Plan()); //$NON-NLS-1$
		routes.define("productIdentifier", licenses.getProductRef_Identifier()); //$NON-NLS-1$
		routes.define("productVersion", licenses.getProductRef_Version()); //$NON-NLS-1$
		routes.define("userIdentifier", licenses.getUserRef_Identifier()); //$NON-NLS-1$
		routes.define("userFullName", licenses.getUserRef_Name()); //$NON-NLS-1$
		routes.define("validFrom", licenses.getValidityPeriodClosed_From()); //$NON-NLS-1$
		routes.define("validUntil", licenses.getValidityPeriodClosed_Until()); //$NON-NLS-1$
		routes.define("licensePlanFeatures", licenses.getLicensePlan_Features()); //$NON-NLS-1$
		routes.define("licensePlan", licenses.getLicensePlanFeature_Plan()); //$NON-NLS-1$
		routes.define("feature", licenses.getFeatureRef_Identifier()); //$NON-NLS-1$
		routes.define("version", licenses.getFeatureRef_VersionMatch()); //$NON-NLS-1$
		return routes;
	}

	@Override
	protected EnsureStructure structures() {
		return new LicensesContainers();
	}

	private void migrate033() {
		String uri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate040() {
		String uri = "http://www.eclipse.org/passage/lic/licenses/0.4.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate050() {
		String uri = "http://www.eclipse.org/passage/lic/licenses/0.5.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate100() {
		String uri = "http://www.eclipse.org/passage/lic/licenses/1.0.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate110() {
		String uri = "http://www.eclipse.org/passage/lic/licenses/1.1.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private EClassRoutes classRoutes200() {
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EClassRoutes routes = new SimpleClassRoutes();
		routes.define("LicensePack", delegate.getPersonalLicensePack()); //$NON-NLS-1$
		routes.define("LicenseGrant", delegate.getLicenseGrant()); //$NON-NLS-1$
		routes.define("LicensePlan", delegate.getLicensePlan()); //$NON-NLS-1$
		routes.define("LicensePlanFeature", delegate.getLicensePlanFeature()); //$NON-NLS-1$
		return routes;
	}

}
