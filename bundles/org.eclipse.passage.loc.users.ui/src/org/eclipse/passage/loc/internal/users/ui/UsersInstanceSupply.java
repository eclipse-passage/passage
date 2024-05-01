/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
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
package org.eclipse.passage.loc.internal.users.ui;

import java.util.Optional;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.loc.internal.api.ClassSupply;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.osgi.service.component.annotations.Component;

@Component
public final class UsersInstanceSupply implements ClassSupply {

	@Override
	public Optional<InstanceSupply<?>> find(Class<?> clazz, MandatoryService context) {
		if (UserOrigin.class.isAssignableFrom(clazz)) {
			return Optional.of(new SupplyUserOrigin(context));
		}
		return Optional.empty();
	}

}
