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
package org.eclipse.passage.lic.base.conditions.mining;

import java.nio.file.Path;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.MiningTool;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.StreamCodec;

/**
 * @since 2.1
 */
public abstract class ArmedMiningTool implements MiningTool {

	protected final KeyKeeper key;
	protected final StreamCodec codec;
	protected final ConditionTransport transport;
	protected final ConditionMiningTarget miner;

	public ArmedMiningTool(KeyKeeper key, StreamCodec codec, ConditionTransport transport,
			ConditionMiningTarget miner) {
		this.key = key;
		this.codec = codec;
		this.transport = transport;
		this.miner = miner;
	}

	protected final String source(Path file) {
		return file.normalize().toAbsolutePath().toString();
	}

	protected final byte[] decoded(Path path) throws LicensingException {
		return new DecodedContent(path, key, codec).get();
	}

}
