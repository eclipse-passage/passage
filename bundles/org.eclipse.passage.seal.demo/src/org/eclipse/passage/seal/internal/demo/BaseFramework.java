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
package org.eclipse.passage.seal.internal.demo;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.internal.api.io.UnemployedCodecs;
import org.eclipse.passage.lic.internal.base.conditions.mining.BaseLicenseReadingService;
import org.eclipse.passage.lic.internal.bc.UnemployedBcCodecs;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public abstract class BaseFramework implements Framework {

	private final SealedAccessCycleConfiguration access;
	private final LicensedProduct product;
	private final LicenseReadingService reader;
	private final UnemployedCodecs codecs;

	protected BaseFramework(Supplier<Bundle> bundle) {
		this.product = productRead();
		this.access = new SealedAccessCycleConfiguration(this::product, bundle);
		this.reader = new BaseLicenseReadingService(product, access.miningEquipment());
		this.codecs = new UnemployedBcCodecs();
	}

	protected BaseFramework() {
		this(() -> FrameworkUtil.getBundle(BaseFramework.class));
	}

	protected abstract LicensedProduct productRead();

	@Override
	public final LicensedProduct product() {
		return product;
	}

	@Override
	public final AccessCycleConfiguration accessCycleConfiguration() {
		return access;
	}

	@Override
	public final LicenseReadingService licenseReader() {
		return reader;
	}

	@Override
	public final UnemployedCodecs unemployedCodecs() {
		return codecs;
	}

}
