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
package org.eclipse.passage.lic.internal.features.model.migration;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.passage.lic.emf.migration.DelegateClassifiers;
import org.eclipse.passage.lic.emf.migration.EClassRoutes;
import org.eclipse.passage.lic.emf.migration.MigrationRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleMigrationRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;

public final class FeaturesResourceHandler extends MigratingResourceHandler {

	@Override
	protected void complete(XMLResource resource) {
		// do nothing
	}

	@Override
	protected void register() {
		migrate033();
		migrate040();
		migrate050();
	}

	@Override
	protected MigrationRoutes attributes() {
		return new SimpleMigrationRoutes();
	}

	private void migrate033() {
		String uri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		FeaturesPackage delegate = FeaturesPackage.eINSTANCE;
		EClassRoutes.Smart smart = new EClassRoutes.Smart(new SimpleClassRoutes());
		smart.define(delegate.getFeatureSet());
		smart.define(delegate.getFeature());
		smart.define(delegate.getFeatureVersion());
		new DelegateClassifiers(uri).delegate(smart);
	}

	private void migrate040() {
		String uri = "http://www.eclipse.org/passage/lic/features/0.4.0"; //$NON-NLS-1$
		FeaturesPackage delegate = FeaturesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(uri, ns -> delegate);
	}

	private void migrate050() {
		String uri = "http://www.eclipse.org/passage/lic/features/0.5.0"; //$NON-NLS-1$
		FeaturesPackage delegate = FeaturesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(uri, ns -> delegate);
	}

}
