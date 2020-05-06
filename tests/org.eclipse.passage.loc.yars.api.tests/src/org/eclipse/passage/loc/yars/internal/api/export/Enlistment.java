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

import java.util.List;

import org.eclipse.passage.loc.yars.internal.api.ListMedia;

@SuppressWarnings("restriction")
final class Enlistment<T> implements ListMedia<T> {

	private final List<T> content;

	public Enlistment(List<T> content) {
		this.content = content;
	}

	@Override
	public final void start() {
		content.clear();
	}

	@Override
	public final void startNode(T root) {
		content.add(root);
	}

	@Override
	public final void finish() {
	}

	@Override
	public final void finishNode(T node) {
	}

	@Override
	public final void inner(String data, String name) {
	}

}
