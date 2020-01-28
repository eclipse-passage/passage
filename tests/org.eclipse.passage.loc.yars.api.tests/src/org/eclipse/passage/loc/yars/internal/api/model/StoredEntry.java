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
package org.eclipse.passage.loc.yars.internal.api.model;

public class StoredEntry {

	private final String name;
	private final String origin;

	public StoredEntry(String name, String origin) {
		this.name = name;
		this.origin = origin;
	}

	public String name() {
		return name;
	}

	public String origin() {
		return origin;
	}

}
