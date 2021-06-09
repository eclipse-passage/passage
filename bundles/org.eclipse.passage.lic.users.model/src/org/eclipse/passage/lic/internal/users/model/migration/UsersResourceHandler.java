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

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.passage.lic.emf.migration.DelegateClassifiers;
import org.eclipse.passage.lic.emf.migration.EClassRoutes;
import org.eclipse.passage.lic.emf.migration.MigrationRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleAttributeRoute;
import org.eclipse.passage.lic.emf.migration.SimpleClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleMigrationRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.users.model.api.Contact;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

public final class UsersResourceHandler extends MigratingResourceHandler {

	@Override
	protected void register() {
		migrate033();
		migrate040();
		migrate050();
		migrate100();
		migrate110();
	}

	@Override
	protected void complete(XMLResource resource) {
		resource.getContents().stream()//
		.filter(UserOrigin.class::isInstance)//
		.map(UserOrigin.class::cast) //
				.forEach(this::complete);
	}

	@Override
	protected MigrationRoutes attributes() {
		MigrationRoutes routes = new SimpleMigrationRoutes();
		UsersPackage users = UsersPackage.eINSTANCE;
		routes.define("preferredConditionType", new SimpleAttributeRoute(users.getUser_PreferredEvaluationType())); //$NON-NLS-1$
		routes.define("preferredConditionExpression", //$NON-NLS-1$
				new SimpleAttributeRoute(users.getUser_PreferredEvaluationExpression()));
		routes.define("email", //$NON-NLS-1$
				new SimpleAttributeRoute(users.getContact_Email(), users.getLicenseOwner_Contact()));
		routes.define("fullName", //$NON-NLS-1$
				new SimpleAttributeRoute(users.getContact_Name(), users.getLicenseOwner_Contact()));
		routes.ignore("userLicenses", users.getUser()); //$NON-NLS-1$
		return routes;
	}

	private void migrate033() {
		String uri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate040() {
		String uri = "http://www.eclipse.org/passage/lic/users/0.4.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate050() {
		String uri = "http://www.eclipse.org/passage/lic/users/0.5.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate100() {
		String uri = "http://www.eclipse.org/passage/lic/users/1.0.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private void migrate110() {
		String uri = "http://www.eclipse.org/passage/lic/users/1.1.0"; //$NON-NLS-1$
		new DelegateClassifiers(uri).delegate(classRoutes200());
	}

	private EClassRoutes classRoutes200() {
		UsersPackage delegate = UsersPackage.eINSTANCE;
		EClassRoutes.Smart routes = new EClassRoutes.Smart(new SimpleClassRoutes());
		routes.define(delegate.getUserOrigin());
		routes.define(delegate.getUser());
		return routes;
	}

	private void complete(UserOrigin origin) {
		origin.getUsers().forEach(this::complete);
	}

	private void complete(User user) {
		Contact contact = new EnsureLicenseOwnerContact().apply(user);
		if(user.getIdentifier() == null) {
			user.setIdentifier(contact.getEmail());
		}
		if(user.getName() == null) {
			user.setName(contact.getName());
		}
	}

}
