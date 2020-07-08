/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.api.conditions.evaluation;

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.registry.ServiceId;

public abstract class ExpressionProtocol implements ServiceId {

	private final String identifier;

	protected ExpressionProtocol(String identifier) {
		Objects.requireNonNull(identifier, "ExpressionProtocol::identifier"); //$NON-NLS-1$
		this.identifier = identifier.trim().toLowerCase();
	}

	public final String identifier() {
		return identifier;
	}

	@Override
	public final int hashCode() {
		return Objects.hash(identifier());
	}

	@Override
	public final boolean equals(Object object) {
		if (!ExpressionProtocol.class.isInstance(object)) {
			return false;
		}
		return identifier.equals(((ExpressionProtocol) object).identifier);
	}

	@Override
	public final String toString() {
		return identifier;
	}

	public static final class Ands extends ExpressionProtocol {

		public Ands() {
			super("ands"); //$NON-NLS-1$
		}

	}

	public static final class Default extends ExpressionProtocol {

		public Default() {
			super(new Ands().identifier());
		}

	}

	public static final class Of extends ExpressionProtocol {

		public Of(String identifier) {
			super(identifier);
		}

	}

}
