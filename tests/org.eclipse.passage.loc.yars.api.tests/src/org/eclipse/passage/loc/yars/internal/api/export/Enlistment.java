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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.loc.yars.internal.api.ListMedia;

@SuppressWarnings("restriction")
public class Enlistment<T> implements ListMedia<T, List<T>> {

	private final List<T> content = new ArrayList<>();

	@Override
	public Enlistment<T> start() {
		content.clear();
		return this;
	}

	@Override
	public Enlistment<T> nodeStart(T root) {
		content.add(root);
		return this;
	}

	@Override
	public List<T> content() {
		return content;
	}

}
