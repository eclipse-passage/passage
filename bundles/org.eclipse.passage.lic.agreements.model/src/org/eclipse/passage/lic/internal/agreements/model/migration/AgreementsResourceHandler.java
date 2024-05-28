/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.lic.internal.agreements.model.migration;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.emf.migration.MigrationRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleMigrationRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;

@SuppressWarnings("restriction")
public final class AgreementsResourceHandler extends MigratingResourceHandler {

	@Override
	protected void complete(XMLResource resource) {
		// do nothing
	}

	@Override
	protected void register() {
		migrate010();
	}

	@Override
	protected MigrationRoutes attributes() {
		return new SimpleMigrationRoutes();
	}

	private void migrate010() {
		String uri = "http://www.eclipse.org/passage/lic/agreements/0.1.0"; //$NON-NLS-1$
		AgreementsPackage delegate = AgreementsPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(uri, ns -> delegate);
	}

}
