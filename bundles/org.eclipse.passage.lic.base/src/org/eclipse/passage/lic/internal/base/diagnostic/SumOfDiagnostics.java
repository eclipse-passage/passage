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

import java.util.function.BinaryOperator;

import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;

@SuppressWarnings("restriction")
public final class SumOfDiagnostics implements BinaryOperator<Diagnostic> {

	@Override
	public Diagnostic apply(Diagnostic first, Diagnostic second) {
		return new BaseDiagnostic(//
				new SumOfLists<Trouble>().apply(first.severe(), second.severe()), //
				new SumOfLists<Trouble>().apply(first.bearable(), second.bearable())//
		);
	}

}
