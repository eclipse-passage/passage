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
 *     ArSysOp - initial API and implementation, further support
 *******************************************************************************/
package org.eclipse.passage.lic.cli;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.equinox.Environments;

@SuppressWarnings("restriction")
final class OptionRequest extends BaseOption<LicenseCoverageCheckOption, CoverageCheckOptionDecision> {

	OptionRequest(Interaction interaction) {
		super(new LicenseCoverageCheckOption.Choise().licenseRequest(), //
				"Request License", //$NON-NLS-1$
				"Collect information necessary for a license issuing", //$NON-NLS-1$
				interaction);
	}

	@Override
	public CoverageCheckOptionDecision run() {
		new DecoratedPrompt(interaction).head("gather environment information"); //$NON-NLS-1$
		Collection<RuntimeEnvironment> envs = new Environments().get();
		reportEnvironmentsDiscovered(envs);
		for (RuntimeEnvironment env : envs) {
			try {
				reportAssessment(env);
			} catch (LicensingException e) {
				interaction.prompt(String.format("%s environment assessment failed", env.id().identifier())); //$NON-NLS-1$
				interaction.swear(e);
			}
		}
		return CoverageCheckOptionDecision.reassess;
	}

	private void reportEnvironmentsDiscovered(Collection<RuntimeEnvironment> envs) {
		interaction.prompt(String.format(
				"\nTo request a license send demanded particles of these %d environments (%s) assessment to your licensing operator:\n", //$NON-NLS-1$
				envs.size(), //
				envs.stream().map(env -> env.id().identifier()).collect(Collectors.joining(", ")))); //$NON-NLS-1$
	}

	private void reportAssessment(RuntimeEnvironment env) throws LicensingException {
		interaction.prompt(String.format("\n==== %s ====\n%s\n", env.id().identifier(), env.state())); //$NON-NLS-1$
	}

}
