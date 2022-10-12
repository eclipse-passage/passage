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
package org.eclipse.passage.lic.internal.base.logging;

import java.io.InputStream;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public final class Logging {

	private final ConfigSupplier config;

	public Logging(ConfigSupplier config) {
		this.config = config;
	}

	@SuppressWarnings("resource")
	public void configure() {
		try (InputStream configuration = config.get()) {
			System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "ERROR"); //$NON-NLS-1$//$NON-NLS-2$
			ConfigurationSource source = new ConfigurationSource(configuration);
			Configurator.initialize(getClass().getClassLoader().getParent(), source);
		} catch (Exception e) {
			System.err.println("Failed to configure logging"); //$NON-NLS-1$
			e.printStackTrace();
		}
	}

	public static interface ConfigSupplier {

		InputStream get() throws Exception;

	}

}
