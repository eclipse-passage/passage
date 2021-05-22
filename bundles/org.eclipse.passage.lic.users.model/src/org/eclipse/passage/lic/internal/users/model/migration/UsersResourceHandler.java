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
package org.eclipse.passage.lic.internal.users.model.migration;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.passage.lic.emf.migration.DelegateClassifiers;
import org.eclipse.passage.lic.emf.migration.EClassRoutes;
import org.eclipse.passage.lic.emf.migration.MigrationRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleMigrationRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

public final class UsersResourceHandler extends MigratingResourceHandler {

	@Override
	protected void ensureMigrations() {
		migrate033();
		migrate040();
		migrate050();
	}

	@Override
	protected MigrationRoutes attributes() {
		return new SimpleMigrationRoutes();
	}

	private void migrate033() {
		String nsUri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		UsersPackage delegate = UsersPackage.eINSTANCE;
		EClassRoutes.Smart smart = new EClassRoutes.Smart(new SimpleClassRoutes());
		smart.define(delegate.getUserOrigin());
		smart.define(delegate.getUser());
		new DelegateClassifiers(nsUri).delegate(smart);
	}

	private void migrate040() {
		String nsUri = "http://www.eclipse.org/passage/lic/users/0.4.0"; //$NON-NLS-1$
		UsersPackage delegate = UsersPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(nsUri, delegate);
	}

	private void migrate050() {
		String nsUri = "http://www.eclipse.org/passage/lic/users/0.5.0"; //$NON-NLS-1$
		UsersPackage delegate = UsersPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(nsUri, delegate);
	}
}
