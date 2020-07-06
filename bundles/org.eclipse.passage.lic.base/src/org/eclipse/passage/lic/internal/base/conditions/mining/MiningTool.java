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
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
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

	Collection<Condition> mine(Collection<Path> sources, Consumer<LicensingException> handler) {
		return sources.stream()//
				.map(path -> mine(path, handler)) //
				.flatMap(Collection::stream)//
				.collect(Collectors.toSet());
	}

	private Collection<Condition> mine(Path source, Consumer<LicensingException> handler) {
		try {
			return from(decoded(source));
		} catch (IOException | LicensingException e) {
			handler.accept(new LicensingException(String.format(//
					BaseMessages.getString("MiningTool.error_mining_file"), source), e)); //$NON-NLS-1$
		}
		return Collections.emptySet();
	}

	private byte[] decoded(Path path) throws IOException, LicensingException {
		try (FileInputStream encoded = new FileInputStream(path.toFile());
				ByteArrayOutputStream decoded = new ByteArrayOutputStream();
				InputStream ring = key.productPublicKey()) {
			codec.decode(encoded, decoded, ring, new DigestExpectation.None());
			return decoded.toByteArray();
		}
	}

	private Collection<Condition> from(byte[] decoded) throws IOException {
		try (ByteArrayInputStream input = new ByteArrayInputStream(decoded)) {
			return transport.read(input);
		}
	}

}
