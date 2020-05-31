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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.users.model.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.users.model.UsersClassMetadata;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.junit.Test;

public class UsersClassMetadataTest {

	private final UsersClassMetadata metadata;

	public UsersClassMetadataTest() {
		metadata = new UsersClassMetadata();
	}

	@Test
	public void userOriginMetadata() {
		assertTrue(metadata.find(UserOriginDescriptor.class).isPresent());
		assertTrue(metadata.find(UserOrigin.class).isPresent());
	}

	@Test
	public void userMetadata() {
		assertTrue(metadata.find(UserDescriptor.class).isPresent());
		assertTrue(metadata.find(User.class).isPresent());
	}

}
