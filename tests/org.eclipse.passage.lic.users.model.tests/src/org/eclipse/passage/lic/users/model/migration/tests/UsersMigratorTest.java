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
package org.eclipse.passage.lic.users.model.migration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.util.UsersResourceImpl;
import org.junit.Test;

public final class UsersMigratorTest {

	@Test
	public void test033Migration() throws Exception {
		assert033(origin("model/0_3_3.lic_users")); //$NON-NLS-1$
	}

	@Test
	public void test100Migration() throws Exception {
		assert100(origin("model/1_0_0.users_xmi")); //$NON-NLS-1$
	}

	private void assert100(UserOrigin origin) {
		assertEquals("magicians", origin.getIdentifier()); //$NON-NLS-1$
		assertEquals("Magicians Co", origin.getName()); //$NON-NLS-1$
		List<User> all = origin.getUsers();
		assertEquals(3, all.size());
		assertUser(user(all, "elder@magic.com"), "The Elder"); //$NON-NLS-1$//$NON-NLS-2$
		assertUser(user(all, "Albert_Rose@garden.ga"), "Albert Red Rose"); //$NON-NLS-1$//$NON-NLS-2$
		assertUser(user(all, "junior@magic.com"), "The Junior"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private User user(List<User> all, String mail) {
		Optional<User> user = all.stream()//
				.filter(u -> mail.equals(u.getContact().getEmail()))//
				.findAny();
		assertTrue(user.isPresent());
		return user.get();
	}

	private void assertUser(User user, String name) {
		assertEquals(name, user.getContact().getName());
		assertEquals("hardware", user.getPreferredEvaluationType()); //$NON-NLS-1$
		assertEquals("os.family=*", user.getPreferredEvaluationExpression()); //$NON-NLS-1$
	}

	private void assert033(UserOrigin origin) {
		assertEquals("org.eclipse.passage.lic", origin.getIdentifier()); //$NON-NLS-1$
		assertEquals("Eclipse Passage LIC", origin.getName()); //$NON-NLS-1$
		assertEquals("Eclipse Passage Licensing Integration Components users", origin.getDescription()); //$NON-NLS-1$

		EList<User> users = origin.getUsers();
		assertEquals(2, users.size());

		User u0 = users.get(0);
		assertEquals("alexander.fedorov@arsysop.ru", u0.getContact().getEmail()); //$NON-NLS-1$
		assertEquals("Alexander Fedorov", u0.getContact().getName()); //$NON-NLS-1$
		assertEquals(null, u0.getDescription());

		User u1 = users.get(1);
		assertEquals("sergei.kovalchuk@arsysop.ru", u1.getContact().getEmail()); //$NON-NLS-1$
		assertEquals("Sergei Kovalchuk", u1.getContact().getName()); //$NON-NLS-1$
		assertEquals(null, u1.getDescription());
	}

	private UserOrigin origin(String path) throws IOException {
		File legacy = new File(System.getProperty("user.dir") + File.separator + path); //$NON-NLS-1$
		// FIXME:AF: should be done via factory
		Resource resource = new UsersResourceImpl(URI.createFileURI(legacy.getPath()));
		resource.load(Collections.emptyMap());
		return UserOrigin.class.cast(resource.getContents().get(0));
	}

}
