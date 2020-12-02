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

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.internal.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.internal.api.io.UnemployedCodecs;
import org.eclipse.passage.lic.internal.base.conditions.mining.BaseLicenseReadingService;
import org.eclipse.passage.lic.internal.bc.UnemployedBcCodecs;

public abstract class BaseFramework implements Framework {

	private final AccessCycleConfiguration access;
	private final LicensedProduct product;
	private final LicenseReadingService reader;
	private final UnemployedCodecs codecs;

	protected BaseFramework() {
		this.product = productRead();
		this.access = configuration(product);
		this.reader = new BaseLicenseReadingService(product, miningEquipment());
		this.codecs = new UnemployedBcCodecs();
	}

	protected abstract LicensedProduct productRead();

	protected abstract AccessCycleConfiguration configuration(LicensedProduct prod);

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

	private MiningEquipment miningEquipment() {
		return new MiningEquipmentConfigured(access).get();
	}

}
