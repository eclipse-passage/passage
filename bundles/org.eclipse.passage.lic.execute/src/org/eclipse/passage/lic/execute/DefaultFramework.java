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
package org.eclipse.passage.lic.execute;

import java.io.InputStream;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.BaseFramework;
import org.eclipse.passage.lic.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.equinox.LicensedApplication;
import org.eclipse.passage.lic.equinox.io.FileFromBundle;
import org.eclipse.passage.lic.internal.base.logging.Logging;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
public final class DefaultFramework extends BaseFramework {

	private final Logger log;
	private final AccessCycleConfiguration configuration;

	public DefaultFramework(Supplier<Bundle> bundle) {
		configureLogging();
		this.log = LogManager.getLogger(getClass());
		logConfiguration();
		this.configuration = new FocusedAccessCycleConfiguration.Wide(this::product, bundle);
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
	public AccessCycleConfiguration accessCycleConfiguration() {
		return configuration;
	}

	private void configureLogging() {
		new Logging(this::logConfig).configure();
	}

	private InputStream logConfig() throws Exception {
		return new FileFromBundle(//
				FrameworkUtil.getBundle(getClass()), //
				"config/log4j2.xml")//$NON-NLS-1$
						.get();
	}

	private void logConfiguration() {
		log.debug(String.format("%s runs for %s", //$NON-NLS-1$
				getClass().getName(), //
				product()));
	}

}
