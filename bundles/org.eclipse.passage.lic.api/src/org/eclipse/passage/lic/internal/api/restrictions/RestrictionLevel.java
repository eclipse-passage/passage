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

/**
 * Severity of a licensing requirement and, accordingly, restriction verdict.
 */
public interface RestrictionLevel {

	String META = "licensing.restriction.level"; //$NON-NLS-1$

	String name();

	final class Info implements RestrictionLevel {

		@Override
		public String name() {
			return "info"; //$NON-NLS-1$
		}

	}

	final class Warning implements RestrictionLevel {

		@Override
		public String name() {
			return "warn"; //$NON-NLS-1$
		}

	}

	final class Error implements RestrictionLevel {

		@Override
		public String name() {
			return "error"; //$NON-NLS-1$
		}

	}

	final class Fatal implements RestrictionLevel {

		@Override
		public String name() {
			return "fatal"; //$NON-NLS-1$
		}

	}

}
