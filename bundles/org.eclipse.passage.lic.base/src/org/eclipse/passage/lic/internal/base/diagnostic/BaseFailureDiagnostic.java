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
package org.eclipse.passage.lic.internal.base.diagnostic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.diagnostic.FailureDiagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;

@SuppressWarnings("restriction")
public final class BaseFailureDiagnostic implements FailureDiagnostic {

	private final List<Trouble> troubles;

	public BaseFailureDiagnostic(List<Trouble> troubles) {
		Objects.requireNonNull(troubles);
		if (troubles.isEmpty()) {
			throw new IllegalArgumentException("Diagnostic is not useful without any information"); //$NON-NLS-1$
		}
		this.troubles = new ArrayList<>(troubles);
	}

	public BaseFailureDiagnostic(Trouble trouble) {
		this(Collections.singletonList(trouble));
	}

	public BaseFailureDiagnostic(TroubleCode code, String details, Exception exception) {
		this(Collections.singletonList(new Trouble(code, details, exception)));
	}

	public BaseFailureDiagnostic(TroubleCode code, String details) {
		this(Collections.singletonList(new Trouble(code, details)));
	}

	public BaseFailureDiagnostic(Emission first, Emission second) {
		if (first.successful() || second.successful()) {
			throw new IllegalArgumentException("Diagnostic supposes failure"); //$NON-NLS-1$
		}
		troubles = new ArrayList<Trouble>();
		append(first);
		append(second);

	}

	private void append(Emission emission) {
		if (emission.successful()) {
			return;
		}
		troubles.addAll(emission.failureDiagnostic().troubles());
	}

	@Override
	public List<Trouble> troubles() {
		return troubles;
	}
}
