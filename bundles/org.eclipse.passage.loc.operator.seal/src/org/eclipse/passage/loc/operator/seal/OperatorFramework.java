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
 *     ArSysOp - initial API and implementation, further support
 *******************************************************************************/
package org.eclipse.passage.loc.operator.seal;

import java.io.InputStream;

import org.eclipse.passage.lic.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.BaseFramework;
import org.eclipse.passage.lic.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.equinox.LicensedApplication;
import org.eclipse.passage.lic.equinox.io.FileFromBundle;
import org.eclipse.passage.lic.execute.FocusedAccessCycleConfiguration;
import org.eclipse.passage.lic.internal.execute.Logging;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("restriction")
final class OperatorFramework extends BaseFramework {

	static final Framework instance = new OperatorFramework();

	private final Logger log;
	private final AccessCycleConfiguration configuration;

	private OperatorFramework() {
		configureLogging();
		this.log = LoggerFactory.getLogger(getClass());
		logConfiguration();
		this.configuration = new FocusedAccessCycleConfiguration.Personal(this::product,
				() -> FrameworkUtil.getBundle(OperatorFramework.class));
	}

	@Override
	public AccessCycleConfiguration accessCycleConfiguration() {
		return configuration;
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
