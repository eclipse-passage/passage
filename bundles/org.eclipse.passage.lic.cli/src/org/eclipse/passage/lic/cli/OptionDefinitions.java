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

import java.util.Collection;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;

public interface OptionDefinitions {

	Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> accept(Collection<AgreementToAccept> agreements);

	Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> diagnostic(Diagnostic diagnostic);

	Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> licenseImport();

	Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> licenseRequest();

	Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> proceed(String name);

	Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> quit();

	public static final class Default implements OptionDefinitions {

		private final TheOtherSide communication;

		public Default(TheOtherSide communication) {
			this.communication = communication;
		}

		@Override
		public Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> accept(
				Collection<AgreementToAccept> agreements) {
			return new OptionAccept(communication, agreements, new Product().get());
		}

		@Override
		public Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> diagnostic(Diagnostic diagnostic) {
			return new OptionDiagnostic(communication, diagnostic);
		}

		@Override
		public Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> licenseImport() {
			return new OptionImport(communication, new Product().get());
		}

		@Override
		public Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> licenseRequest() {
			return new OptionRequest(communication);
		}

		@Override
		public Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> proceed(String name) {
			return new OptionProceed(name, communication);
		}

		@Override
		public Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision> quit() {
			return new OptionQuit(communication);
		}

	}

}
