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
package org.eclipse.passage.lic.hc.remote.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.hc.remote.RequestContext;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.net.io.SafePayload;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;

/**
 * 
 * @since 1.1
 */
public final class EObjectFromXmiResponse<T extends EObject> implements ResponseHandler<T> {

	private final Class<T> expected;
	private final Equipment equipment;

	public EObjectFromXmiResponse(Class<T> expected, Equipment equipment) {
		this.expected = expected;
		this.equipment = equipment;
	}

	@Override
	public T read(ResultsTransfered results, RequestContext context) throws LicensingException {
		contentTypeIsExpected(results);
		// FIXME:AF: should be done via factory
		return new EObjectFromBytes<T>(decoded(results.data(), context), expected, LicensesResourceImpl::new).get();
	}

	private byte[] decoded(byte[] raw, RequestContext context) throws LicensingException {
		return new SafePayload(equipment.keeper(context.product()), equipment.hash(context.hash())).decode(raw);
	}

	private void contentTypeIsExpected(ResultsTransfered results) throws LicensingException {
		ContentType.Xml xml = new ContentType.Xml();
		if (!xml.equals(results.contentType())) {
			throw new LicensingException(String.format(AccessMessages.EObjectFromXmiResponse_unexpected_content_type,
					results.contentType(), xml.contentType()));
		}
	}

}
