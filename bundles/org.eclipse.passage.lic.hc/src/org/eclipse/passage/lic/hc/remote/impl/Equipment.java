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
package org.eclipse.passage.lic.hc.remote.impl;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.hc.i18n.MineMessages;

/**
 * 
 * @since 1.1
 */
public final class Equipment {

	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final ConditionTransportRegistry transports;
	private final HashesRegistry hashes;

	public Equipment(KeyKeeperRegistry keys, StreamCodecRegistry codecs, ConditionTransportRegistry transports,
			HashesRegistry hashes) {
		this.keys = keys;
		this.codecs = codecs;
		this.transports = transports;
		this.hashes = hashes;
	}

	public KeyKeeperRegistry keys() {
		return keys;
	}

	public StreamCodecRegistry codecs() {
		return codecs;
	}

	public ConditionTransportRegistry transports() {
		return transports;
	}

	public HashesRegistry hashes() {
		return hashes;
	}

	public KeyKeeper keeper(LicensedProduct product) {
		return keys.get().service(product);
	}

	public Hashes hash(String algorithm) {
		return hashes.get().service(new StringServiceId(algorithm));
	}

	public ConditionTransport transport(ContentType contentType) throws LicensingException {
		if (!transports.get().hasService(contentType)) {
			throw new LicensingException(String.format(MineMessages.DecryptedConditions_no_transport_for_content_type,
					contentType.contentType()));
		}
		return transports.get().service(contentType);
	}

}