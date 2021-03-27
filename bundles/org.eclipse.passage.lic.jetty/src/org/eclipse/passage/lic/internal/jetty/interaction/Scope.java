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

import java.util.Objects;

public abstract class Scope {

	private final String id;

	protected Scope(String id) {
		Objects.requireNonNull(id, "Scope::id"); //$NON-NLS-1$
		this.id = id;
	}

	public String id() {
		return id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Scope)) {
			return false;
		}
		return ((Scope) obj).id.equals(id);
	}

	@Override
	public String toString() {
		return id;
	}

	public static final class Self extends Scope {

		public Self() {
			super("self"); //$NON-NLS-1$
		}

	}

	public static final class Server extends Scope {

		public Server() {
			super("server"); //$NON-NLS-1$
		}

	}

	public static final class Of extends Scope {

		public Of(String id) {
			super(id);
		}

	}

}
