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
import java.util.Collections;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.diagnostic.FailureDiagnostic;

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
 * <p>
 * Any implementation must follow the contract defined in
 * {@code EmissionContractTest}
 * </p>
 */
public interface Emission {

	/**
	 * Must be asked before any information ( permission or failure details) is
	 * asked for.
	 */
	boolean failed();

	Collection<Permission> permissions();

	FailureDiagnostic failureDiagnostic();

	public static final class Successful implements Emission {

		private final Collection<Permission> permissions;

		public Successful(Collection<Permission> permissions) {
			Objects.requireNonNull(permissions, "Emission.Successful::permissions"); //$NON-NLS-1$
			this.permissions = permissions;
		}

		public Successful(Permission permission) {
			this(Collections.singleton(permission));
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
		public FailureDiagnostic failureDiagnostic() {
			throw new UnsupportedOperationException();
		}

	}

	public static final class Failed implements Emission {

		private final FailureDiagnostic diagnose;

		public Failed(FailureDiagnostic diagnose) {
			Objects.requireNonNull(diagnose, "Emission.Failed::diagnose"); //$NON-NLS-1$
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
		public FailureDiagnostic failureDiagnostic() {
			return diagnose;
		}

	}

}
