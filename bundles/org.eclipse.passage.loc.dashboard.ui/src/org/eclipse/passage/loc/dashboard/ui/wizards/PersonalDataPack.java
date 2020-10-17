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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.Optional;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;

public final class PersonalDataPack {
	private final Optional<LicensePlanDescriptor> plan;
	private final Optional<UserDescriptor> user;
	private final Optional<ProductVersionDescriptor> product;

	public PersonalDataPack(//
			Optional<LicensePlanDescriptor> plan, //
			Optional<UserDescriptor> user, //
			Optional<ProductVersionDescriptor> product) {
		this.plan = plan;
		this.user = user;
		this.product = product;
	}

	public PersonalDataPack() {
		this(Optional.empty(), Optional.empty(), Optional.empty());
	}

	Optional<LicensePlanDescriptor> plan() {
		return plan;
	}

	Optional<UserDescriptor> user() {
		return user;
	}

	Optional<ProductVersionDescriptor> product() {
		return product;
	}

}
