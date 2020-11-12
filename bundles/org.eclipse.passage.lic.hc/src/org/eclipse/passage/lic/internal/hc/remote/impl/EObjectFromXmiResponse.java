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

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

public final class EObjectFromXmiResponse<T> implements ResponseHandler<T> {

	private final Class<T> expected;

	public EObjectFromXmiResponse(Class<T> expected) {
		this.expected = expected;
	}

	@Override
	public T read(byte[] raw, String contentType) throws LicensingException {
		// TODO we use `transport`s for Conditions,
		// design transports here too should the need arise
		contentTypeIsExpected(contentType);
		return new EObjectFromBytes<>(raw, expected).get();
	}

	private void contentTypeIsExpected(String contentType) throws LicensingException {
		if (!new ContentType.Xml().contentType().equals(contentType)) {
			throw new LicensingException(String.format(AccessMessages.EObjectFromXmiResponse_unexpected_content_type,
					contentType, new ContentType.Xml().contentType()));
		}
	}

}
