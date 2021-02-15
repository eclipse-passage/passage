/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lac.internal.seal;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.internal.api.io.UnemployedCodecs;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
final class AgentFramework implements Framework {

	private final Supplier<String> user;
	private final Supplier<LicensedProduct> product;

	public AgentFramework(ProductUserRequest<?> request) {
		this(request.user()::get, request.product()::get);
	}

	public AgentFramework(Supplier<String> user, Supplier<LicensedProduct> product) {
		this.user = user;
		this.product = product;
	}

	@Override
	public LicensedProduct product() {
		return product.get();
	}

	@Override
	public AccessCycleConfiguration accessCycleConfiguration() {
		return new AgentConfiguration(new UserHomePath(), user, product, this::bundle);
	}

	@Override
	public LicenseReadingService licenseReader() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UnemployedCodecs unemployedCodecs() {
		throw new UnsupportedOperationException();
	}

	private Bundle bundle() {
		return FrameworkUtil.getBundle(getClass());
	}
}
