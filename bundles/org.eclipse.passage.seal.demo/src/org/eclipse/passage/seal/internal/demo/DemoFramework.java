/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.BaseFramework;
import org.eclipse.passage.lic.internal.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.internal.base.logging.Logging;
import org.eclipse.passage.lic.internal.equinox.LicensedApplication;
import org.eclipse.passage.lic.internal.equinox.io.FileFromBundle;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
final class DemoFramework extends BaseFramework {

	static final Framework demo = new DemoFramework();

	private final Logger log;

	private DemoFramework() {
		configureLogging();
		this.log = LogManager.getLogger(getClass());
		logConfiguration();
	}

	@Override
	protected final LicensedProduct productRead() {
		LicensedProduct prod;
		try {
			prod = new LicensedApplication().product();
		} catch (LicensingException e) {
			prod = new InvalidLicensedProduct();
		}
		return prod;
	}

	@Override
	protected AccessCycleConfiguration configuration(LicensedProduct product) {
		return new FocusedAccessCycleConfiguration.Wide(() -> product);
	}

	private void configureLogging() {
		new Logging(this::logConfig).configure();
	}

	private InputStream logConfig() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		return new FileFromBundle(bundle, "config/log4j2.xml").get(); //$NON-NLS-1$
	}

	private void logConfiguration() {
		log.debug(String.format("%s runs for %s", //$NON-NLS-1$
				getClass().getName(), //
				product()));
	}

}
