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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.internal.api.PassageUI;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
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
	public ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature) {
		ServiceInvocationResult<GrantLockAttempt> lock = investigate(//
				() -> acquire(feature), //
				GrantLockAttempt::certificate, //
				cert -> !new CertificateWorthAttention().test(cert));
		return lock;
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assessLicensingStatus() {
		return investigate(this::assess, Function.identity(), c -> false);
	}

	private <T> ServiceInvocationResult<T> investigate(//
			Supplier<ServiceInvocationResult<T>> gain, //
			Function<T, ExaminationCertificate> get, //
			Predicate<Optional<ExaminationCertificate>> ok) {
		ServiceInvocationResult<T> result = gain.get();
		while (exposeAndMayBeEvenFix(result, get, ok)) {
			result = gain.get();
		}
		return result;
	}

	private ServiceInvocationResult<GrantLockAttempt> acquire(String feature) {
		return new EquinoxPassage().acquireLicense(feature);
	}

	private ServiceInvocationResult<ExaminationCertificate> assess() {
		return new EquinoxPassageLicenseCoverage().assess();
	}

	/**
	 * @return {@code true} if licensing state has been changed and new assessment
	 *         have sense, {@code false} otherwise.
	 */
	private <T> boolean exposeAndMayBeEvenFix(//
			ServiceInvocationResult<T> result, //
			Function<T, ExaminationCertificate> get, //
			Predicate<Optional<ExaminationCertificate>> ok) {
		if (!result.data().isPresent()) {
			new DiagnosticDialog(shell.get(), result.diagnostic()).open();
			return false;
		}
		Optional<ExaminationCertificate> certificate = Optional.of(get.apply(result.data().get()));
		if (ok.test(certificate)) {
			return false;
		}
		LicenseStatusDialog dialog = new LicenseStatusDialog(shell.get(), certificate.get(), result.diagnostic());
		if (Window.OK != dialog.open()) {
			return false;
		}
		return dialog.goodIntention().paveTheWay();
	}

}
