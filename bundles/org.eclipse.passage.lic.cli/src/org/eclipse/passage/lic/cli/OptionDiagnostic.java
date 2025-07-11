/*******************************************************************************
 * Copyright (c) 2021, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation; further evolution
 *******************************************************************************/
package org.eclipse.passage.lic.cli;

import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;

final class OptionDiagnostic extends BaseOption<LicenseCoverageCheckOption, CoverageCheckOptionDecision> {

	private final Diagnostic diagnostic;

	OptionDiagnostic(Interaction interaction, Diagnostic diagnostic) {
		super(new LicenseCoverageCheckOption.Choise().showDiagnostic(), //
				"Diagnostic", //$NON-NLS-1$
				"View diagnostic of the latest license status assessment", //$NON-NLS-1$
				interaction);
		this.diagnostic = diagnostic;
	}

	@Override
	public CoverageCheckOptionDecision run() {
		interaction.prompt(new DiagnosticExplained(diagnostic).get());
		return CoverageCheckOptionDecision.reassess;
	}

}
