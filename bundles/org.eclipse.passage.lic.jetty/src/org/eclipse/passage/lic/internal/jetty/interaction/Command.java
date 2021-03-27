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
package org.eclipse.passage.lic.internal.jetty.interaction;

public abstract class Command {

	private final Scope scope;
	private final String[] names;

	public Command(Scope scope, String[] names) {
		this.scope = scope;
		this.names = names;
	}

	public final Scope scope() {
		return scope;
	}

	public final String[] commands() {
		return names;
	}

}
