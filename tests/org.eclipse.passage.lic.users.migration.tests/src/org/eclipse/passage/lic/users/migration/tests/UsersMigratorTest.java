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
package org.eclipse.passage.lic.users.migration.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.junit.Test;

public class UsersMigratorTest {

	@Test
	public void testMigratorPositive() throws Exception {
		File legacy = new File(
				System.getProperty("user.dir") + File.separator + "model/org.eclipse.passage.lic.lic_users"); //$NON-NLS-1$ //$NON-NLS-2$
		URI uri = URI.createFileURI(legacy.getPath());
		Resource resource = new XMIResourceImpl(uri);
		resource.load(null);
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		UserOrigin userOrigin = UserOrigin.class.cast(eObject);
		assertEquals("org.eclipse.passage.lic", userOrigin.getIdentifier()); //$NON-NLS-1$
		assertEquals("Eclipse Passage LIC", userOrigin.getName()); //$NON-NLS-1$
		assertEquals("Eclipse Passage Licensing Integration Components users", userOrigin.getDescription()); //$NON-NLS-1$

		EList<User> users = userOrigin.getUsers();
		assertEquals(2, users.size());

		User u0 = users.get(0);
		assertEquals(null, u0.getIdentifier());
		assertEquals("alexander.fedorov@arsysop.ru", u0.getEmail()); //$NON-NLS-1$
		assertEquals("Alexander Fedorov", u0.getFullName()); //$NON-NLS-1$
		assertEquals(null, u0.getDescription());
		
		User u1 = users.get(1);
		assertEquals(null, u1.getIdentifier());
		assertEquals("sergei.kovalchuk@arsysop.ru", u1.getEmail()); //$NON-NLS-1$
		assertEquals("Sergei Kovalchuk", u1.getFullName()); //$NON-NLS-1$
		assertEquals(null, u1.getDescription());
	}
}
