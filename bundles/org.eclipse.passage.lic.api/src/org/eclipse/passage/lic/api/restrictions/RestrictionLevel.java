/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api.restrictions;

import java.util.Objects;

import org.eclipse.passage.lic.api.registry.ServiceId;

/**
 * <p>
 * Severity of a licensing requirement and, accordingly, restriction verdict.
 * It's up to a product developer to define semantics of a restriction level. By
 * default we supply
 * </p>
 * <ul>
 * <li>{@code info} level. It a requirement of this level is not satisfied, then
 * a product user should be somehow notified of this fact without pausing
 * workflow.</li>
 * <li>{@code warn} level: unsatisfied requirement of this type should cause a
 * feature execution postponing</li>
 * <li>{@code error} level: feature usage should be stopped
 * <li>
 * <li>{@code fatal} level: the whole product usage must be stopped
 * <li>
 * </ul>
 * <p>
 * These treatment can be redesigned according to a product development demands.
 * </p>
 * <p>
 * Designed to be a <i>data-class</i>.
 * </p>
 * 
 * @since 2.1
 */
public abstract class RestrictionLevel implements ServiceId {

	private final String identifier;

	protected RestrictionLevel(String identifier) {
		Objects.requireNonNull(identifier, "Identifier is mandatory for restriction level"); //$NON-NLS-1$
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
		if (!RestrictionLevel.class.isInstance(object)) {
			return false;
		}
		return identifier.equals(((RestrictionLevel) object).identifier);
	}

	@Override
	public final String toString() {
		return identifier;
	}

	public static final class Info extends RestrictionLevel {

		public Info() {
			super("info"); //$NON-NLS-1$
		}

	}

	public static final class Warning extends RestrictionLevel {

		public Warning() {
			super("warn"); //$NON-NLS-1$
		}
	}

	public final static class Error extends RestrictionLevel {

		public Error() {
			super("error"); //$NON-NLS-1$
		}

	}

	public static final class Fatal extends RestrictionLevel {

		public Fatal() {
			super("fatal"); //$NON-NLS-1$
		}

	}

	public static final class Of extends RestrictionLevel {

		public Of(String identifier) {
			super(identifier);
		}

	}

}
