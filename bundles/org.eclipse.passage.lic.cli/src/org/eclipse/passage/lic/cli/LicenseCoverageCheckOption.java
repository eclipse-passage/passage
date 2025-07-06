/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

package org.eclipse.passage.lic.cli;

public final class LicenseCoverageCheckOption extends Option.Key.Base {

	private LicenseCoverageCheckOption(char key) {
		super(key);
	}

	public static final class Choise {

		public LicenseCoverageCheckOption acceptAgreement() {
			return new LicenseCoverageCheckOption('a');
		}

		public LicenseCoverageCheckOption showDiagnostic() {
			return new LicenseCoverageCheckOption('d');
		}

		public LicenseCoverageCheckOption licenseImport() {
			return new LicenseCoverageCheckOption('i');
		}

		public LicenseCoverageCheckOption licenseRequest() {
			return new LicenseCoverageCheckOption('r');
		}

		public LicenseCoverageCheckOption proceed() {
			return new LicenseCoverageCheckOption('p');
		}

		public LicenseCoverageCheckOption quit() {
			return new LicenseCoverageCheckOption('q');
		}
	}

}
