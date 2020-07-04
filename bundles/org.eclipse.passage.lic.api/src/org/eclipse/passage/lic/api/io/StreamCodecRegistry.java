/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.io;

import java.util.Map;

import org.eclipse.passage.lic.api.LicensingConfiguration;

/**
 * Registry for {@link StreamCodec}s available at runtime
 *
 * @since 0.4.0
 * @deprecated use internal (1.0) StreamCodecRegistry
 */
@Deprecated
public interface StreamCodecRegistry {

	/**
	 * Get a {@link StreamCodec} for the given {@code configuration}
	 *
	 * @param configuration general licensing configuration for running product
	 * @since 0.4.0
	 */
	StreamCodec getStreamCodec(LicensingConfiguration configuration);

	/**
	 * Register the given {@code streamCodec} for a {@code LicensingConfiguration},
	 * created from the given {@code properties}.
	 *
	 * @param streamCodec instance to be registered
	 * @param properties  source information for {@code LicensingConfiguration}
	 *                    creation
	 * @see LicensingConfiguration
	 * @since 0.4.0
	 */
	void registerStreamCodec(StreamCodec streamCodec, Map<String, Object> properties);

	/**
	 * Unregister the given {@code streamCodec}. The {@code streamCodec} will no
	 * longer available on {@link #getStreamCodec(LicensingConfiguration)}
	 * invocation
	 *
	 * @param streamCodec instance to be unregistered
	 * @see LicensingConfiguration
	 * @since 0.4.0
	 */
	void unregisterStreamCodec(StreamCodec streamCodec, Map<String, Object> properties);

}
