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

import java.util.Arrays;
import java.util.List;

import org.eclipse.passage.loc.yars.internal.api.ListMedia;

@SuppressWarnings("restriction")
final class Csv implements ListMedia<ExportEntry> {

	private final StringBuilder builder;
	private final List<String> header;

	public Csv(StringBuilder builder, String... header) {
		this.builder = builder;
		this.header = Arrays.asList(header);
	}

	@Override
	public final void start() {
		builder.delete(0, builder.length());
		header.forEach(facet -> builder.append(facet).append(";")); //$NON-NLS-1$
	}

	@Override
	public final void startNode(ExportEntry node) {
		builder.append("\n"); //$NON-NLS-1$
	}

	@Override
	public final void inner(String data, String name) {
		builder.append(data).append(";"); //$NON-NLS-1$
	}

	@Override
	public final void finish() {
	}

	@Override
	public final void finishNode(ExportEntry node) {
	}

}
