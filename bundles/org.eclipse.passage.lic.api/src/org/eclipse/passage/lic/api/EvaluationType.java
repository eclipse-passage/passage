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
package org.eclipse.passage.lic.api;

import java.util.Objects;

import org.eclipse.passage.lic.api.registry.ServiceId;

/**
 * <p>
 * Defines the way the condition will be evaluated in a running environment. For
 * example, "hardware" means that specially dedicated module is going to access
 * a workstation hardware in order to supply enough information to verify if the
 * condition is met.
 * </p>
 * <p>
 * Designed to be a <i>data-class</i>.
 * </p>
 * 
 * @since 2.1
 */
public abstract class EvaluationType implements ServiceId {

	private final String identifier;

	protected EvaluationType(String identifier) {
		Objects.requireNonNull(identifier, "EvaluationType::identifier"); //$NON-NLS-1$
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
