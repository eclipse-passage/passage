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

import java.io.FileInputStream;
import java.nio.file.Path;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public final class Logging {

	private final ConfigSupplier config;

	public Logging(ConfigSupplier config) {
		this.config = config;
	}

	@SuppressWarnings("resource")
	public void configure() {
		try {
			ConfigurationSource source = new ConfigurationSource(new FileInputStream(config.get().toFile()));
			Configurator.initialize(getClass().getClassLoader().getParent(), source);
		} catch (Exception e) {
			System.err.println("Failed to configure logging"); //$NON-NLS-1$
			e.printStackTrace();
		}
	}

	public static interface ConfigSupplier {

		Path get() throws Exception;

	}

}
