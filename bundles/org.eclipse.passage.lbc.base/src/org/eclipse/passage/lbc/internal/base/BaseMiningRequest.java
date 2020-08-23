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
package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lbc.internal.api.ProductLicensesRequest;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

/**
 * @since 1.0
 */
public final class BaseMiningRequest implements ProductLicensesRequest {

	private final ProductIdentifier identifier;
	private final ProductVersion version;
	private final Requester user;

	public BaseMiningRequest(ProductIdentifier identifier, ProductVersion version, Requester user) {
		this.identifier = identifier;
		this.version = version;
		this.user = user;
	}

	@Override
	public Requester requester() {
		return user;
	}

	@Override
	public LicensedProduct product() {
		return new BaseLicensedProduct(identifier.get().get(), version.get().get());
	}

}
