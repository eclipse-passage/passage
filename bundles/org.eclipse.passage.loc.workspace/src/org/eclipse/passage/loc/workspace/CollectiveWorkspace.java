/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.loc.workspace;

import org.eclipse.passage.loc.internal.api.workspace.Agreements;
import org.eclipse.passage.loc.internal.api.workspace.Features;
import org.eclipse.passage.loc.internal.api.workspace.Keys;
import org.eclipse.passage.loc.internal.api.workspace.LicensePacks;
import org.eclipse.passage.loc.internal.api.workspace.Licenses;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.Products;
import org.eclipse.passage.loc.internal.api.workspace.Users;

public final class CollectiveWorkspace implements OperatorWorkspace {

	private final Features features;
	private final Products products;
	private final Keys keys;
	private final LicensePacks packs;
	private final Agreements agreements;
	private final Licenses licenses;
	private final Users users;

	public CollectiveWorkspace() {
		this.features = new UncertainFeatures();
		this.products = new UncertainProducts();
		this.keys = new HomeBasedKeys();
		this.packs = new HomeBasedLicensePacks();
		this.agreements = new HomeBasedAgreements();
		this.licenses = new UncertainLicenses();
		this.users = new UncertainUsers();
	}

	@Override
	public Features features() {
		return features;
	}

	@Override
	public Products products() {
		return products;
	}

	@Override
	public Keys keys() {
		return keys;
	}

	@Override
	public LicensePacks licensePacks() {
		return packs;
	}

	@Override
	public Agreements agreements() {
		return agreements;
	}

	@Override
	public Licenses licenses() {
		return licenses;
	}

	@Override
	public Users users() {
		return users;
	}

}
