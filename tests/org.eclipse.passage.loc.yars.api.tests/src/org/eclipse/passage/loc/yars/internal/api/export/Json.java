/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.yars.internal.api.export;

import org.eclipse.passage.loc.yars.internal.api.ListMedia;

@SuppressWarnings("restriction")
class Json implements ListMedia<ExportedEntry, String> {

	private final StringBuilder builder = new StringBuilder();

	@Override
	public Json start() {
		builder.delete(0, builder.length());
		builder.append("{\n"); //$NON-NLS-1$
		return this;
	}

	@Override
	public ListMedia<ExportedEntry, String> finish() {
		builder.append("}\n"); //$NON-NLS-1$
		return this;
	}

	@Override
	public Json nodeStart(ExportedEntry node) {
		builder.append("\t\"node\" : {\n"); //$NON-NLS-1$
		return this;
	}

	@Override
	public Json nodeFinish(ExportedEntry node) {
		builder.append("\t}\n"); //$NON-NLS-1$
		return this;
	}

	@Override
	public Json inner(String data, String name) {
		builder.append("\t\t") //$NON-NLS-1$
				.append("\"") //$NON-NLS-1$
				.append(name) //
				.append("\" : ") //$NON-NLS-1$
				.append("\"") //$NON-NLS-1$
				.append(data)//
				.append("\"\n"); //$NON-NLS-1$
		return this;
	}

	@Override
	public String content() {
		return builder.toString();
	}

}
