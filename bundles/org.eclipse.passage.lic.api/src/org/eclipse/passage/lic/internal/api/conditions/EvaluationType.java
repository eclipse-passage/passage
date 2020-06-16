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
package org.eclipse.passage.lic.internal.api.conditions;

import java.util.Objects;

/**
 * <p>
 * Defines the way the condition will be evaluated in a running environment. The
 * type of condition like "time" or "hardware".
 * </p>
 * <p>
 * Designed to be a <i>data-class</i>.
 * </p>
 */
public abstract class EvaluationType {

	private final String identifier;

	protected EvaluationType(String identifier) {
		Objects.requireNonNull(identifier, "Identifier is mandatory for condition type"); //$NON-NLS-1$
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
		if (!EvaluationType.class.isInstance(object)) {
			return false;
		}
		return identifier.equals(((EvaluationType) object).identifier);
	}

	@Override
	public final String toString() {
		return identifier;
	}

	public static final class Time extends EvaluationType {

		public Time() {
			super("time"); //$NON-NLS-1$
		}

	}

	public static final class Hardware extends EvaluationType {

		public Hardware() {
			super("hardware"); //$NON-NLS-1$
		}

	}

	public static final class Of extends EvaluationType {

		public Of(String identifier) {
			super(identifier);
		}

	}

}
