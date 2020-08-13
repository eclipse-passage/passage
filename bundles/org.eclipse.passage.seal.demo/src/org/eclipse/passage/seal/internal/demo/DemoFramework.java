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
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.internal.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.mining.BaseLicenseReadingService;
import org.eclipse.passage.lic.internal.equinox.LicensedApplication;

@SuppressWarnings("restriction")
final class DemoFramework implements Framework {

	private final SealedAccessCycleConfiguration access;
	private final LicensedProduct product;
	private final LicenseReadingService reader;

	static final Framework demo = new DemoFramework();

	private DemoFramework() {
		product = productRead();
		access = new SealedAccessCycleConfiguration(this::product);
		reader = new BaseLicenseReadingService(product, access.miningEquipment());
	}

	private LicensedProduct productRead() {
		LicensedProduct prod;
		try {
			prod = new LicensedApplication().product();
		} catch (LicensingException e) {
			prod = new InvalidLicensedProduct();
		}
		return prod;
	}

	@Override
	public LicensedProduct product() {
		return product;
	}

	@Override
	public AccessCycleConfiguration accessCycleConfiguration() {
		return access;
	}

	@Override
	public LicenseReadingService licenseReader() {
		return reader;
	}

}
