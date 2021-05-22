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
package org.eclipse.passage.lic.internal.users.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.emf.meta.ClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.meta.PlainEntityMetadata;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

public final class UsersClassMetadata implements ClassMetadata {

	private final UsersPackage meta;
	private final Map<Class<?>, EntityMetadata> map;

	public UsersClassMetadata() {
		meta = UsersPackage.eINSTANCE;
		map = new HashMap<Class<?>, EntityMetadata>();
		map.put(UserOriginDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getUserOrigin(), //
						meta.getUserOrigin_Identifier(), //
						meta.getUserOrigin_Name()));
		map.put(UserOrigin.class, map.get(UserOriginDescriptor.class));
		map.put(UserDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getUser(), //
						meta.getLicenseOwner_Identifier(), //
						meta.getLicenseOwner_Name()));
		map.put(User.class, map.get(UserDescriptor.class));
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return Optional.ofNullable(map.get(clazz));
	}

}
