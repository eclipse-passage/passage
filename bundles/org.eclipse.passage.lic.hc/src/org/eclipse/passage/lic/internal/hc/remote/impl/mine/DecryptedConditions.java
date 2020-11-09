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
package org.eclipse.passage.lic.internal.hc.remote.impl.mine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.floating.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

final class DecryptedConditions implements ResponseHandler<Collection<ConditionPack>> {

	private final ConditionTransportRegistry transports;
	private final FloatingServerConnection coordinates;

	DecryptedConditions(ConditionTransportRegistry transports, FloatingServerConnection coordinates) {
		this.transports = transports;
		this.coordinates = coordinates;
	}

	/**
	 * FIXME: we treat all remote conditions to be of the same origin until
	 * {@linkplain ConditionTransport} evolves to support condition packs #568621
	 */
	@Override
	public Collection<ConditionPack> read(byte[] raw, String contentType) throws LicensingException {
		try (ByteArrayInputStream stream = new ByteArrayInputStream(raw)) {
			return Collections.singleton(//
					new BaseConditionPack(//
							source(), //
							transport(contentType).read(stream)//
					));
		} catch (IOException e) {
			throw new LicensingException(HcMessages.DecryptedConditions_reading_error, e);
		}
	}

	private String source() {
		return String.format("net:%s:%d", coordinates.getIp(), coordinates.getPort());//$NON-NLS-1$
	}

	private ConditionTransport transport(String contentType) throws LicensingException {
		ContentType type = new ContentType.Of(contentType);
		if (!transports.get().hasService(type)) {
			throw new LicensingException(
					String.format(HcMessages.DecryptedConditions_no_transport_for_content_type, contentType));
		}
		return transports.get().service(type);
	}

}
