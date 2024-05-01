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
 *     ArSysOp - further evolution
 *******************************************************************************/
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.users.UserDescriptor;

public final class FloatingDataPack {

	private final Optional<LicensePlan> plan;
	private final List<UserDescriptor> users;
	private final Optional<ProductVersion> product;

	public FloatingDataPack(//
			Optional<LicensePlan> plan, //
			List<UserDescriptor> users, //
			Optional<ProductVersion> product) {
		this.plan = plan;
		this.users = users;
		this.product = product;
	}

	public FloatingDataPack(//
			Optional<LicensePlan> plan, //
			Optional<UserDescriptor> user, //
			Optional<ProductVersion> product) {
		this.plan = plan;
		this.users = users(user);
		this.product = product;
	}

	private List<UserDescriptor> users(Optional<UserDescriptor> user) {
		if (user.isEmpty()) {
			return Collections.emptyList();
		}
		return Collections.singletonList(user.get());
	}

	public FloatingDataPack() {
		this(Optional.empty(), Collections.emptyList(), Optional.empty());
	}

	Optional<LicensePlan> plan() {
		return plan;
	}

	List<UserDescriptor> users() {
		return users;
	}

	Optional<ProductVersion> product() {
		return product;
	}

}
