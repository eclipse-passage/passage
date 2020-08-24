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
package org.eclipse.passage.lbc.internal.base;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lbc.internal.api.BackendServerConfiguration;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;

/**
 * @since 1.0
 */
public class BaseServerConfiguration implements BackendServerConfiguration {

	private final Registry<LicensedProduct, StreamCodec> codecs;
	private final Registry<LicensedProduct, KeyKeeper> keys;
	private final Registry<ContentType, ConditionTransport> transports;

	public BaseServerConfiguration(Collection<StreamCodec> codecs, Collection<KeyKeeper> keys,
			Collection<ConditionTransport> transports) {
		this.codecs = new ReadOnlyRegistry<>(codecs);
		this.keys = new ReadOnlyRegistry<>(keys);
		this.transports = new ReadOnlyRegistry<>(transports);
	}

	@Override
	public StreamCodecRegistry codecs() {
		return () -> codecs;
	}

	@Override
	public KeyKeeperRegistry keyKeepers() {
		return () -> keys;
	}

	@Override
	public ConditionTransportRegistry transports() {
		return () -> transports;
	}

	public static BackendServerConfiguration empty() {
		return new BaseServerConfiguration(Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
	}

}
