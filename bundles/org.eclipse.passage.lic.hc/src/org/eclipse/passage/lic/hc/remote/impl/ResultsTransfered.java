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

import java.util.Collections;

import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;

public final class ResultsTransfered {

	private final byte[] data;
	private final int code;
	private final String message;
	private final ContentType contentType;
	private final boolean successful;

	public ResultsTransfered(Connection connection) throws Exception {
		code = connection.code();
		message = connection.message();
		contentType = connection.contentType();
		data = connection.payload(); // should be eager
		successful = connection.successful();
	}

	public ContentType contentType() {
		return contentType;
	}

	public byte[] data() {
		return data;
	}

	public boolean successful() {
		return successful;
	}

	public Diagnostic diagnose() {
		if (successful()) {
			return new BaseDiagnostic();
		}
		return new BaseDiagnostic(//
				Collections.emptyList(), //
				Collections.singletonList(//
						new Trouble(//
								new ServiceFailedOnMorsel(), //
								String.format("%d: %s", code, message)) //$NON-NLS-1$
				));
	}

}
