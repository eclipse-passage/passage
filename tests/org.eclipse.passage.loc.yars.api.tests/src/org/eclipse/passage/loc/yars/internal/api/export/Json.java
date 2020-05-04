/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.yars.internal.api.export;

import org.eclipse.passage.loc.yars.internal.api.ListMedia;

@SuppressWarnings("restriction")
final class Json implements ListMedia<ExportEntry> {

	private final StringBuilder builder;

	Json(StringBuilder builder) {
		this.builder = builder;
	}

	@Override
	public final void start() {
		builder.delete(0, builder.length());
		builder.append("{\n"); //$NON-NLS-1$
	}

	@Override
	public final void finish() {
		builder.append("}\n"); //$NON-NLS-1$
	}

	@Override
	public final void startNode(ExportEntry node) {
		builder.append("\t\"node\" : {\n"); //$NON-NLS-1$
	}

	@Override
	public final void finishNode(ExportEntry node) {
		builder.append("\t}\n"); //$NON-NLS-1$
	}

	@Override
	public final void inner(String data, String name) {
		builder.append("\t\t") //$NON-NLS-1$
				.append("\"") //$NON-NLS-1$
				.append(name) //
				.append("\" : ") //$NON-NLS-1$
				.append("\"") //$NON-NLS-1$
				.append(data)//
				.append("\"\n"); //$NON-NLS-1$
	}

}
