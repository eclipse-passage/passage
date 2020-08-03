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
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

@SuppressWarnings("restriction")
final class DecryptedConditions implements ResponseHandler {

	private final ConditionTransportRegistry transports;

	DecryptedConditions(ConditionTransportRegistry transports) {
		this.transports = transports;
	}

	/**
	 * FIXME: we treat all remote condition =s to be of the same origin until
	 * {@linkplain ConditionTransport} evolves to support condition packs
	 */
	@Override
	public Collection<ConditionPack> read(byte[] raw, String contentType) throws LicensingException {
		try (ByteArrayInputStream stream = new ByteArrayInputStream(keyDecoded(base64Decoded(raw)))) {
			return Collections.singleton(//
					new BaseConditionPack(//
							"net", //$NON-NLS-1$
							transport(new ContentType.Of(contentType)).read(stream)));
		} catch (IOException e) {
			throw new LicensingException(HcMessages.DecryptedConditions_reading_error, e);
		}
	}

	private byte[] base64Decoded(byte[] raw) throws LicensingException {
		return raw; // FIXME: implement
	}

	private byte[] keyDecoded(byte[] data) throws LicensingException {
		return data; // FIXME: implement
	}

	private ConditionTransport transport(ContentType contentType) throws LicensingException {
		if (!transports.get().hasService(contentType)) {
			throw new LicensingException(String.format(HcMessages.DecryptedConditions_no_transport_for_content_type,
					contentType.contentType()));
		}
		return transports.get().service(contentType);
	}

}
