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
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.migration.DelegateClassifiers;
import org.eclipse.passage.lic.emf.migration.EClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleFeatureRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.internal.emf.migration.RecognizeFeatures;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

public class LicensesResourceHandler extends MigratingResourceHandler {

	@Override
	protected void ensureMigrations() {
		migrate033();
		migrate040();
		migrate050();
		migrate100();
		migrate110();
	}

	@Override
	public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		super.postLoad(resource, inputStream, options);
		resource.getContents().stream()//
				.filter(PersonalLicensePack.class::isInstance)//
				.map(PersonalLicensePack.class::cast).forEach(new AssignGrantIdentifiers());
	}

	@Override
	protected void convertEntry(Entry<EObject, AnyType> entry) {
		EObject key = entry.getKey();
		if (key instanceof PersonalLicensePack) {
			PersonalLicensePack pack = (PersonalLicensePack) key;
			SimpleFeatureRoutes attributes = new SimpleFeatureRoutes();
//LicensePack
			attributes.define("identifier", LicensesPackage.eINSTANCE.getLicenseRequisites_Identifier()); //$NON-NLS-1$
			attributes.define("productIdentifier", LicensesPackage.eINSTANCE.getProductRef_Identifier()); //$NON-NLS-1$
			attributes.define("productVersion", LicensesPackage.eINSTANCE.getProductRef_Version()); //$NON-NLS-1$
			attributes.define("userIdentifier", LicensesPackage.eINSTANCE.getUserRef_Identifier()); //$NON-NLS-1$
			attributes.define("licenseGrants", LicensesPackage.eINSTANCE.getPersonalLicensePack_Grants()); //$NON-NLS-1$
//LicenseGrant
			attributes.define("conditionExpression", LicensesPackage.eINSTANCE.getLicenseGrant_ConditionExpression()); //$NON-NLS-1$
			attributes.define("conditionType", LicensesPackage.eINSTANCE.getLicenseGrant_ConditionType()); //$NON-NLS-1$
			attributes.define("featureIdentifier", LicensesPackage.eINSTANCE.getLicenseGrant_FeatureIdentifier()); //$NON-NLS-1$
			attributes.define("matchRule", LicensesPackage.eINSTANCE.getLicenseGrant_MatchRule()); //$NON-NLS-1$
			attributes.define("matchVersion", LicensesPackage.eINSTANCE.getLicenseGrant_MatchVersion()); //$NON-NLS-1$
			attributes.define("validFrom", LicensesPackage.eINSTANCE.getLicenseGrant_ValidFrom()); //$NON-NLS-1$
			attributes.define("validUntil", LicensesPackage.eINSTANCE.getLicenseGrant_ValidUntil()); //$NON-NLS-1$
			RecognizeFeatures unknown = new RecognizeFeatures(entry.getValue(), attributes);
			unknown//
					.mixed(pack)//
					.attributes(new EnsurePersonalPackLicense().apply(pack))//
					.attributes(new EnsurePersonalPackUser().apply(pack))//
					.attributes(new EnsurePersonalPackProduct().apply(pack))//
			;
		} else {
			System.out.println(entry);
		}
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
		EClassRoutes smart = new SimpleClassRoutes();
		smart.define("LicensePack", delegate.getPersonalLicensePack()); //$NON-NLS-1$
		smart.define("LicenseGrant", delegate.getLicenseGrant()); //$NON-NLS-1$
		return smart;
	}

}
