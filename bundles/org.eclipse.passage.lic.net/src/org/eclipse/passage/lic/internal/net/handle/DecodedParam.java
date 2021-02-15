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
package org.eclipse.passage.lbc.internal.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.api.LicensingException;

final class DecodedParam {

	private final String raw;

	DecodedParam(String raw) {
		this.raw = raw;
	}

	String get() throws LicensingException {
		try {
			return URLDecoder.decode(raw, "UTF-8"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new LicensingException(String.format(BaseMessages.DecodedParam_decode_failed, raw), e);
		}
	}
}
