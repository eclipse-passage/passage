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
package org.eclipse.passage.lic.base.diagnostic;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;

/**
 * 
 * @since 2.1
 */
public final class BaseDiagnostic implements Diagnostic {

	private final List<Trouble> severe;
	private final List<Trouble> bearable;

	public BaseDiagnostic() {
		this(Collections.emptyList(), Collections.emptyList());
	}

	public BaseDiagnostic(Trouble severe) {
		this(Collections.singletonList(severe), Collections.emptyList());
	}

	public BaseDiagnostic(Trouble severe, List<Trouble> bearable) {
		this(Collections.singletonList(severe), bearable);
	}

	public BaseDiagnostic(List<Trouble> bearable) {
		this(Collections.emptyList(), bearable);
		if (bearable.isEmpty()) {
			throw new IllegalArgumentException("Diagnostic is not useful without any information"); //$NON-NLS-1$
		}
	}

	public BaseDiagnostic(List<Trouble> severe, List<Trouble> bearable) {
		Objects.requireNonNull(severe, "BaseDiagnostic:severe"); //$NON-NLS-1$
		Objects.requireNonNull(bearable, "BaseDiagnostic:bearable"); //$NON-NLS-1$
		this.severe = severe;
		this.bearable = bearable;
	}

	@Override
	public List<Trouble> severe() {
		return severe;
	}

	@Override
	public List<Trouble> bearable() {
		return bearable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Trouble trouble : severe) {
			builder.append(trouble.details()).append('\n');
		}
		for (Trouble trouble : bearable) {
			builder.append(trouble.details()).append('\n');
		}
		return builder.toString();
	}

}
