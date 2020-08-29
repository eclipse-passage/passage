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
package org.eclipse.passage.lic.internal.jface;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.internal.api.PassageUI;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassageLicenseCoverage;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.LicenseStatusDialog;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class EquinoxPassageUI implements PassageUI {

	private final Supplier<Shell> shell;

	public EquinoxPassageUI(Supplier<Shell> shell) {
		this.shell = shell;
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> acquireLicense(String feature) {
		return investigate(//
				() -> acquire(feature), //
				certificate -> !new CertificateWorthAttention().test(certificate));
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assessLicensingStatus() {
		return investigate(this::assess, c -> false);
	}

	private ServiceInvocationResult<ExaminationCertificate> investigate(
			Supplier<ServiceInvocationResult<ExaminationCertificate>> gain, //
			Predicate<Optional<ExaminationCertificate>> ok) {
		ServiceInvocationResult<ExaminationCertificate> result = gain.get();
		while (exposeAndMayBeEvenFix(result, ok)) {
			result = gain.get();
		}
		return result;
	}

	private ServiceInvocationResult<ExaminationCertificate> acquire(String feature) {
		return new EquinoxPassage().acquireLicense(feature);
	}

	private ServiceInvocationResult<ExaminationCertificate> assess() {
		return new EquinoxPassageLicenseCoverage().assess();
	}

	/**
	 * @return {@code true} if licensing state has been changed and new assessment
	 *         have sense. {@code false} otherwise.
	 */
	private boolean exposeAndMayBeEvenFix(//
			ServiceInvocationResult<ExaminationCertificate> result, //
			Predicate<Optional<ExaminationCertificate>> ok) {
		if (!result.data().isPresent()) {
			new DiagnosticDialog(shell.get(), result.diagnostic()).open();
			return false;
		}
		if (ok.test(result.data())) {
			return false;
		}
		ExaminationCertificate certificate = result.data().get();
		LicenseStatusDialog dialog = new LicenseStatusDialog(shell.get(), certificate, result.diagnostic());
		if (Window.OK != dialog.open()) {
			return false;
		}
		return dialog.goodIntention().paveTheWay();
	}

}
