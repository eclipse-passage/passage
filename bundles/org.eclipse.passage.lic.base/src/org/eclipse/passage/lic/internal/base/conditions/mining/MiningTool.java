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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

@SuppressWarnings("restriction")
final class MiningTool {

	private final KeyKeeper key;
	private final StreamCodec codec;
	private final ConditionTransport transport;

	public MiningTool(KeyKeeper key, StreamCodec codec, ConditionTransport transport) {
		this.key = key;
		this.codec = codec;
		this.transport = transport;
	}

	ServiceInvocationResult<Collection<ConditionPack>> mine(Collection<Path> sources) {
		return sources.stream()//
				.map(path -> mine(path)) //
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<ConditionPack>())) //
				.orElse(new BaseServiceInvocationResult<Collection<ConditionPack>>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<ConditionPack>> mine(Path source) {
		try {
			return new BaseServiceInvocationResult<>(Collections.singleton(//
					new BaseConditionPack(source, from(decoded(source)))));
		} catch (IOException | LicensingException e) {
			return new BaseServiceInvocationResult<>(//
					new Trouble(//
							new ServiceFailedOnMorsel(), //
							String.format(BaseMessages.getString("MiningTool.error_mining_file"), //$NON-NLS-1$
									source.normalize().toAbsolutePath()), //
							e));
		}
	}

	private byte[] decoded(Path path) throws IOException, LicensingException {
		try (FileInputStream encoded = new FileInputStream(path.toFile());
				ByteArrayOutputStream decoded = new ByteArrayOutputStream();
				InputStream ring = key.productPublicKey()) {
			codec.decode(encoded, decoded, ring, new DigestExpectation.None());
			decoded.flush();
			return decoded.toByteArray();
		}
	}

	private Collection<Condition> from(byte[] decoded) throws IOException {
		try (ByteArrayInputStream input = new ByteArrayInputStream(decoded)) {
			return transport.read(input);
		}
	}

}
