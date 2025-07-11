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

final class OptionQuit extends BaseOption<LicenseCoverageCheckOption, CoverageCheckOptionDecision> {

	OptionQuit(Interaction interaction) {
		super(new LicenseCoverageCheckOption.Choise().quit(), //
				"Quit", //$NON-NLS-1$
				"Exit the application", //$NON-NLS-1$
				interaction);
	}

	@Override
	public CoverageCheckOptionDecision run() {
		interaction.prompt("Quitting server launching...\nRun 'fls:start' to repeat the attempt."); //$NON-NLS-1$
		return CoverageCheckOptionDecision.quit;
	}

}