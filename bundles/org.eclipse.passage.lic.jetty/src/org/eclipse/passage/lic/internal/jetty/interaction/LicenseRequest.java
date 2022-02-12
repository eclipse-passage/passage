/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.jetty.interaction;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.equinox.Environments;

//TODO: remove in the favor of OptionRequest of LicStatus
final class LicenseRequest extends Command {

	public LicenseRequest() {
		super(new Scope.Self(), new LicRequest().get());
	}

	public void licrequest() {
		Collection<RuntimeEnvironment> envs = new Environments().get();
		reportEnvironmentsDiscovered(envs);
		for (RuntimeEnvironment env : envs) {
			try {
				reportAssessment(env);
			} catch (LicensingException e) {
				System.err.printf("%s environment assessment failed\n", env.id().identifier()); //$NON-NLS-1$
				e.printStackTrace();
			}
		}

	}

	private void reportEnvironmentsDiscovered(Collection<RuntimeEnvironment> envs) {
		System.out.printf(
				"\nTo request a license send demanded particles of these %d environments (%s) assessment to your licensing operator:\n", //$NON-NLS-1$
				envs.size(), //
				envs.stream().map(env -> env.id().identifier()).collect(Collectors.joining(", "))); //$NON-NLS-1$
	}

	private void reportAssessment(RuntimeEnvironment env) throws LicensingException {
		System.out.printf("\n==== %s ====\n%s\n", env.id().identifier(), env.state()); //$NON-NLS-1$
	}

	@Override
	protected List<String> commands() {
		return new LicRequest().get();
	}

	@Override // TODO l10n
	public String usage() {
		return "Start environment assessment to gather information demanded for license issuing"; //$NON-NLS-1$
	}

	private static final class LicRequest extends Name {

		protected LicRequest() {
			super("licrequest"); //$NON-NLS-1$
		}

	}

}
