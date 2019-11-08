/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.api.io;

import org.eclipse.passage.lic.api.LicensingConfiguration;

import java.util.Map;

/**
 * Registry for {@link KeyKeeper} instances available at runtime.
 *
 * @since 0.4.0
 */
public interface KeyKeeperRegistry {

	/**
	 * Get a {@link KeyKeeper} for the {@code configuration}
	 *
	 * @param configuration general licensing configuration of running product
	 * @since 0.4.0
	 */
	KeyKeeper getKeyKeeper(LicensingConfiguration configuration);

	/**
	 * Register the given {@code keyKeeper} for a {@code LicensingConfiguration},
	 * created from the given {@code properties}.
	 *
	 * @param keyKeeper  instance to be registered
	 * @param properties source information for {@code LicensingConfiguration} creation
	 * @see LicensingConfiguration
	 * @since 0.4.0
	 */
	void registerKeyKeeper(KeyKeeper keyKeeper, Map<String, Object> properties);

	/**
	 * Unregister the given {@code keyKeeper}. The {@code keyKeeper} will no longer available on
	 * {@link #getKeyKeeper(LicensingConfiguration)} invocation
	 *
	 * @param keyKeeper  instance to be unregistered
	 * @see LicensingConfiguration
	 * @since 0.4.0
	 */
	void unregisterKeyKeeper(KeyKeeper keyKeeper, Map<String, Object> properties);

}
