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

import java.util.List;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.RequirementsCoverageExplained;
import org.eclipse.passage.lic.equinox.EquinoxPassageLicenseCoverage;

final class LicenseStatus extends Command {

	public LicenseStatus() {
		super(new Scope.Self(), new LicStatus().get());
	}

	public void licstatus() {
		ServiceInvocationResult<ExaminationCertificate> response = new EquinoxPassageLicenseCoverage().assess();
		if (response.data().isPresent()) {
			reportCertificate(response.data().get());
		}
		reportDiagnostic(response.diagnostic());
	}

	private void reportCertificate(ExaminationCertificate certificate) {
		System.out.printf("\n%s\n", new RequirementsCoverageExplained(certificate).get()); //$NON-NLS-1$
	}

	@Override
	protected List<String> commands() {
		return new LicStatus().get();
	}

	@Override // TODO: l10n
	public String usage() {
		return "Examine and report license coverage for the product"; //$NON-NLS-1$
	}

	private static final class LicStatus extends Name {

		protected LicStatus() {
			super("licstatus"); //$NON-NLS-1$
		}

	}
}
