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
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.eclipse.passage.loc.internal.workbench.SelectRoot;

public class SupplyUserOrigin implements InstanceSupply<UserOrigin> {

	private final MandatoryService context;

	public SupplyUserOrigin(MandatoryService context) {
		this.context = context;
	}

	@Override
	public Optional<UserOrigin> supply() {
		return new SelectRoot<>(new SelectUserOrigin(context).get(), context).get();
	}

}
