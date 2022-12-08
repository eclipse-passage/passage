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
package org.eclipse.passage.lic.internal.execute;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

public final class Logging {

	private final ConfigSupplier config;

	public Logging(ConfigSupplier config) {
		this.config = config;
	}

	@SuppressWarnings("resource")
	public void configure() {
		try (InputStream configuration = config.get()) {
			PropertyConfigurator.configure(config.get());
		} catch (Exception e) {
			System.err.println("Failed to configure logging"); //$NON-NLS-1$
			e.printStackTrace();
		}
	}

	public static interface ConfigSupplier {

		InputStream get() throws Exception;

	}

}
