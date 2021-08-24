/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.base.diagnostic;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.i18n.DiagnosticExplainedMessages;

/**
 * @since 2.1
 */
public final class TroubleExplained implements Supplier<String> {
	private final Trouble trouble;

	public TroubleExplained(Trouble trouble) {
		this.trouble = trouble;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append("\r\n\t> ") //$NON-NLS-1$
				.append(trouble.details()) //
				.append("\n\t\t(") //$NON-NLS-1$
				.append(trouble.code().code()) //
				.append(": ") //$NON-NLS-1$
				.append(trouble.code().explanation())//
				.append(")") //$NON-NLS-1$
				.append(trouble.exception().isPresent()
						? DiagnosticExplainedMessages.getString("DiagnosticExplained.failure") //$NON-NLS-1$
						: ""); //$NON-NLS-1$
		if (trouble.exception().isPresent()) {
			StringWriter media = new StringWriter();
			trouble.exception().get().printStackTrace(new PrintWriter(media));
			out//
					.append("\r\n----\r\n") //$NON-NLS-1$
					.append(media.toString())//
					.append("----\r\n"); //$NON-NLS-1$
		}
		return out.toString();
	}

}
