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
package org.eclipse.passage.lic.internal.api.restrictions;

import java.util.Objects;

/**
 * Severity of a licensing requirement and, accordingly, restriction verdict.
 */
public interface RestrictionLevel {

	String META = "licensing.restriction.level"; //$NON-NLS-1$

	String identifier();

	final class Info implements RestrictionLevel {

		@Override
		public String identifier() {
			return "info"; //$NON-NLS-1$
		}

	}

	final class Warning implements RestrictionLevel {

		@Override
		public String identifier() {
			return "warn"; //$NON-NLS-1$
		}

	}

	final class Error implements RestrictionLevel {

		@Override
		public String identifier() {
			return "error"; //$NON-NLS-1$
		}

	}

	final class Fatal implements RestrictionLevel {

		@Override
		public String identifier() {
			return "fatal"; //$NON-NLS-1$
		}

	}

	final class Of implements RestrictionLevel {

		private final String name;

		public Of(String name) {
			Objects.requireNonNull(name, "Name is mandatory for restriction level"); //$NON-NLS-1$
			this.name = name.trim().toLowerCase();
		}

		@Override
		public String identifier() {
			return name;
		}

	}

}
