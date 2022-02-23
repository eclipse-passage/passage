/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.jface;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.api.PassageUI;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.equinox.EquinoxPassageLicenseCoverage;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.LicenseStatusDialog;
import org.eclipse.swt.widgets.Shell;

public final class EquinoxPassageUI implements PassageUI {

	private final Supplier<Shell> shell;

	public EquinoxPassageUI(Supplier<Shell> shell) {
		this.shell = shell;
	}

	@Override
	public ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature) {
		return investigate(//
				() -> acquire(feature), //
				GrantLockAttempt::certificate, //
				new CertificateWorthAttention().negate());
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assessLicensingStatus() {
		return investigate(this::assess, Function.identity(), c -> false);
	}

	private <T> ServiceInvocationResult<T> investigate(//
			Supplier<ServiceInvocationResult<T>> service, //
			Function<T, ExaminationCertificate> certificate, //
			Predicate<Optional<ExaminationCertificate>> ok) {
		ServiceInvocationResult<T> result = service.get();
		try {
			while (exposeAndMayBeEvenFix(result, certificate, ok)) {
				result = service.get();
			}
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(new Trouble(//
					new TroubleCode.Of(-1, "Unexpected error"), //$NON-NLS-1$
					"One of supplied subservices failed", //$NON-NLS-1$
					e));
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
