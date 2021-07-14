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
import java.util.Optional;

/**
 * 
 * @since 2.1
 */
public final class Trouble {

	private final TroubleCode code;
	private final String details;
	private final Optional<Exception> exception;

	private Trouble(TroubleCode code, String details, Optional<Exception> exception) {
		Objects.requireNonNull(code);
		Objects.requireNonNull(details);
		Objects.requireNonNull(exception);
		this.code = code;
		this.details = details;
		this.exception = exception;
	}

	public Trouble(TroubleCode code, String details, Exception exception) {
		this(code, details, Optional.of(exception));
	}

	public Trouble(TroubleCode code, String details) {
		this(code, details, Optional.empty());
	}

	public Optional<Exception> exception() {
		return exception;
	}

	public TroubleCode code() {
		return code;
	}

	public String details() {
		return details;
	}

}
