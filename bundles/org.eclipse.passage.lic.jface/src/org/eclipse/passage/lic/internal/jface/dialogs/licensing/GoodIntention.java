/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.swt.widgets.Shell;

public abstract class GoodIntention {

	/**
	 * Do intended action and return {@code true} if the world has been changed
	 */
	public abstract boolean paveTheWay();

	public final static class Nope extends GoodIntention {

		@Override
		public boolean paveTheWay() {
			return false;
		}

	}

	public final static class ImportLicense extends GoodIntention {

		private final Supplier<Shell> shell;

		public ImportLicense(Supplier<Shell> shell) {
			this.shell = shell;
		}

		@Override
		public boolean paveTheWay() {
			return Window.OK == new ImportLicenseDialog(shell.get()).open();
		}
	}

	public final static class RequestLicense extends GoodIntention {

		private final Supplier<Shell> shell;

		public RequestLicense(Supplier<Shell> shell) {
			this.shell = shell;
		}

		@Override
		public boolean paveTheWay() {
			new EnvironmentStateDialog(shell.get()).open();
			return false;
		}
	}

	public final static class Diagnose extends GoodIntention {

		private final Supplier<Shell> shell;
		private final Diagnostic diagnostic;

		public Diagnose(Supplier<Shell> shell, Diagnostic diagnostic) {
			this.shell = shell;
			this.diagnostic = diagnostic;
		}

		@Override
		public boolean paveTheWay() {
			new DiagnosticDialog(shell.get(), diagnostic).open();
			return false;
		}
	}

	public final static class ExposeLicenseAgreements extends GoodIntention {

		private final Supplier<Shell> shell;
		private final Collection<AgreementToAccept> agreements;

		public ExposeLicenseAgreements(Supplier<Shell> shell, Collection<AgreementToAccept> agreements) {
			this.shell = shell;
			this.agreements = agreements;
		}

		@Override
		public boolean paveTheWay() {
			int result = new AgreementsWizardDialog(shell.get(), agreements).open();
			return result == Window.OK;
		}

	}

}
