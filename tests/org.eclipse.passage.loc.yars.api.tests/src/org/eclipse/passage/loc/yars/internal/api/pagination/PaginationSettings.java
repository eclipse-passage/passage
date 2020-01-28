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
package org.eclipse.passage.loc.yars.internal.api.pagination;

import org.eclipse.passage.loc.yars.internal.api.FetchParams;

@SuppressWarnings("restriction")
class PaginationSettings implements FetchParams {

	private final int no;
	private final int length;

	PaginationSettings(int no, int length) {
		this.no = no;
		this.length = length;
	}

	int from() {
		return no * length;
	}

	int to() {
		return (no + 1) * length;
	}

}
