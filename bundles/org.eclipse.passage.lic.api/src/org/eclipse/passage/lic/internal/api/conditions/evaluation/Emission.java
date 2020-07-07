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

import java.util.Collection;

/**
 * Report {@linkplain Condition}s evaluation results.
 * 
 * <p>
 * Condition evaluation, in general, can end up
 * </p>
 * <ul>
 * <li>in a set successfully emitted permissions, one per each of the given
 * conditions</li>
 * <li>or, in case of any part of evaluation fails, in complete denial with lots
 * of details.</li>
 * </ul>
 * 
 * <p>
 * Thus, working with the emission result, first check if it has been successful
 * at all ({@code Emission.failed()}) and then request either permissions or
 * failure details.
 * </p>
 * 
 */
public interface Emission {

	/**
	 * Must be asked before any information ( permission or failure details) is
	 * asked for.
	 */
	boolean failed();

	Collection<Permission> permissions();

	EmissionFailureDiagnostic failureDiagnostic();

	public static final class Successful implements Emission {

		private final Collection<Permission> permissions;

		public Successful(Collection<Permission> permissions) {
			this.permissions = permissions;
		}

		@Override
		public boolean failed() {
			return false;
		}

		@Override
		public Collection<Permission> permissions() {
			return permissions;
		}

		@Override
		public EmissionFailureDiagnostic failureDiagnostic() {
			throw new UnsupportedOperationException();
		}

	}

	public static final class Failed implements Emission {

		private final EmissionFailureDiagnostic diagnose;

		public Failed(EmissionFailureDiagnostic diagnose) {
			this.diagnose = diagnose;
		}

		@Override
		public boolean failed() {
			return true;
		}

		@Override
		public Collection<Permission> permissions() {
			throw new UnsupportedOperationException();
		}

		@Override
		public EmissionFailureDiagnostic failureDiagnostic() {
			return diagnose;
		}

	}

}
