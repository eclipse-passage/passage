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
package org.eclipse.passage.lic.api.diagnostic;

import java.util.Objects;

// FIXME: let it work on contract too
/**
 * 
 * @since 2.1
 */
public abstract class TroubleCode {

	private final int code;
	private final String explanation;

	public TroubleCode(int code, String explanation) {
		Objects.requireNonNull(explanation);
		this.code = code;
		this.explanation = explanation;
	}

	public final int code() {
		return code;
	}

	public final String explanation() {
		return explanation;
	}

	@Override
	public final boolean equals(Object object) {
		// explanation does not participate intentionally
		if (!getClass().isInstance(object)) {
			return false;
		}
		return code == ((TroubleCode) object).code();
	}

	@Override
	public final int hashCode() {
		// explanation does not participate intentionally
		return Objects.hash(code);
	}

	@Override
	public final String toString() {
		return String.format("%d: %s", code, explanation); //$NON-NLS-1$
	}

	public static final class Of extends TroubleCode {

		public Of(int code, String explanation) {
			super(code, explanation);
		}

	}
}
