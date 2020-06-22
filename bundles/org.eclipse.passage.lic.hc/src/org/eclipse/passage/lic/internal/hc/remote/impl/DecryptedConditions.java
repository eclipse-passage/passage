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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

@SuppressWarnings("restriction")
final class DecryptedConditions implements ResponseHandler {

	private final ConditionTransportRegistry transports;

	DecryptedConditions(ConditionTransportRegistry transports) {
		this.transports = transports;
	}

	@Override
	public Collection<Condition> read(byte[] raw, String contentType) throws ConditionMiningException {
		try (ByteArrayInputStream stream = new ByteArrayInputStream(keyDecoded(base64Decoded(raw)))) {
			return transport(new ContentType(contentType)).read(stream);
		} catch (IOException e) {
			throw new ConditionMiningException(HcMessages.DecryptedConditions_reading_error, e);
		}
	}

	private byte[] base64Decoded(byte[] raw) throws ConditionMiningException {
		return raw; // FIXME: implement
	}

	private byte[] keyDecoded(byte[] data) throws ConditionMiningException {
		return data; // FIXME: implement
	}

	private ConditionTransport transport(ContentType contentType) throws ConditionMiningException {
		if (!transports.get().hasService(contentType)) {
			throw new ConditionMiningException(String
					.format(HcMessages.DecryptedConditions_no_transport_for_content_type, contentType.contentType()));
		}
		return transports.get().service(contentType);
	}

}
