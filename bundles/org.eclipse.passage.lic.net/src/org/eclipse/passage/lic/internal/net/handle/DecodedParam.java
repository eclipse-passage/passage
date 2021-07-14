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
package org.eclipse.passage.lic.internal.net.handle;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.net.i18n.NetMessages;

final class DecodedParam {

	private final String raw;

	DecodedParam(String raw) {
		this.raw = raw;
	}

	String get() throws LicensingException {
		try {
			return URLDecoder.decode(raw, "UTF-8"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new LicensingException(
					String.format(NetMessages.getString("DecodedParam.DecodedParam_decode_failed"), raw), e); //$NON-NLS-1$
		}
	}
}
