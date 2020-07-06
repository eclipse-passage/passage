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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

@SuppressWarnings("restriction")
public final class MiningEquipment {

	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final ConditionTransportRegistry transports;

	public MiningEquipment(KeyKeeperRegistry keys, StreamCodecRegistry codecs, ConditionTransportRegistry transports) {
		this.keys = keys;
		this.codecs = codecs;
		this.transports = transports;
	}

	MiningTool tool(LicensedProduct product) throws ConditionMiningException {
		return new MiningTool(key(product), codec(product), transport(product));
	}

	private KeyKeeper key(LicensedProduct product) throws ConditionMiningException {
		if (!keys.get().hasService(product)) {
			throw new ConditionMiningException(
					String.format(BaseMessages.getString("LicenseReadingTool.error_no_key_keeper"), product)); //$NON-NLS-1$
		}
		return keys.get().service(product);
	}

	private StreamCodec codec(LicensedProduct product) throws ConditionMiningException {
		if (!codecs.get().hasService(product)) {
			throw new ConditionMiningException(
					String.format(BaseMessages.getString("LicenseReadingTool.error_no_stream_codec"), product)); //$NON-NLS-1$
		}
		return codecs.get().service(product);
	}

	private ConditionTransport transport(LicensedProduct product) throws ConditionMiningException {
		ContentType contentType = new ContentType.Xml();
		if (!transports.get().hasService(contentType)) {
			throw new ConditionMiningException(
					String.format(BaseMessages.getString("LicenseReadingTool.error_no_transport"), //$NON-NLS-1$
							product, contentType));
		}
		return transports.get().service(contentType);
	}

}
