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
import org.eclipse.passage.lic.internal.net.EObjectFromBytes;

public final class EObjectFromXmiResponse<T> implements ResponseHandler<T> {

	private final Class<T> expected;

	public EObjectFromXmiResponse(Class<T> expected) {
		this.expected = expected;
	}

	@Override
	public T read(ResultsTransfered results) throws LicensingException {
		// TODO we use `transport`s for Conditions,
		// design transports here too should the need arise
		contentTypeIsExpected(results);
		return new EObjectFromBytes<>(results.data(), expected).get();
	}

	private void contentTypeIsExpected(ResultsTransfered results) throws LicensingException {
		ContentType.Xml xml = new ContentType.Xml();
		if (!xml.equals(results.contentType())) {
			throw new LicensingException(String.format(AccessMessages.EObjectFromXmiResponse_unexpected_content_type,
					results.contentType(), xml.contentType()));
		}
	}

}
