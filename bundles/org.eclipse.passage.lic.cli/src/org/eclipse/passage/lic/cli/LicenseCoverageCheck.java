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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.RequirementsCoverageExplained;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;
import org.eclipse.passage.lic.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.equinox.EquinoxPassageLicenseCoverage;

@SuppressWarnings("restriction")
public final class LicenseCoverageCheck {

	private final TheOtherSide communication;
	private final String name;

	public LicenseCoverageCheck(String name, TheOtherSide communication) {
		this.name = name;
		this.communication = communication;
	}

	public LicenseCoverageCheck(Interaction interaction) {
		this("application", new TheOtherSide.Blind(interaction)); //$NON-NLS-1$
	}

	public Result run() {
		Optional<LicensedProduct> product = product();
		if (!product.isPresent()) {
			return Result.exit;
		}
		CoverageCheckOptionDecision intention = CoverageCheckOptionDecision.reassess;
		while (CoverageCheckOptionDecision.reassess.equals(intention)) {
			ServiceInvocationResult<ExaminationCertificate> assessment = new EquinoxPassageLicenseCoverage().assess();
			reportAssessment(assessment.data());
			Options<LicenseCoverageCheckOption, CoverageCheckOptionDecision> options = options(assessment,
					product.get());
			communication.withContext(assessment, keys(options.options()));
			intention = options.promptAndPick().run();
		}
		return CoverageCheckOptionDecision.proceed.equals(intention) ? Result.proceed : Result.exit;
	}

	private Optional<LicensedProduct> product() {
		ServiceInvocationResult<LicensedProduct> product = new EquinoxPassage().product();
		if (!product.data().isPresent()) {
			communication.prompt(
					"Product configuration lacks product under licenseing definition. Contact the product vendor."); //$NON-NLS-1$
		}
		return product.data();
	}

	private Options<LicenseCoverageCheckOption, CoverageCheckOptionDecision> options(
			ServiceInvocationResult<ExaminationCertificate> assessment, LicensedProduct product) {
		List<Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision>> options = new ArrayList<>();
		if (new CertificateIsRestrictive().test(assessment.data())) {
			options.add(new OptionImport(communication, product));
			options.add(new OptionRequest(communication));
			agreements(assessment)
					.ifPresent(agreements -> options.add(new OptionAccept(communication, agreements, product)));
			options.add(new OptionDiagnostic(communication, assessment.diagnostic()));
			options.add(new OptionQuit(communication));
		} else {
			options.add(new OptionImport(communication, product));
			options.add(new OptionRequest(communication));
			options.add(new OptionDiagnostic(communication, assessment.diagnostic()));
			options.add(new OptionProceed(name, communication));
			options.add(new OptionQuit(communication));
		}
		return new Options<>(options, communication);
	}

	private Optional<Collection<AgreementToAccept>> agreements(
			ServiceInvocationResult<ExaminationCertificate> assessment) {
		if (!assessment.data().isPresent()) {
			return Optional.empty();
		}
		Collection<AgreementToAccept> agreements = assessment.data().get().agreements();
		if (agreements.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(agreements);
	}

	private void reportAssessment(Optional<ExaminationCertificate> certificate) {
		new DecoratedPrompt(communication).head(name + " license coverage assessment"); //$NON-NLS-1$
		if (certificate.isPresent()) {
			if (new CertificateIsRestrictive().test(certificate)) {
				communication.prompt("License coverage for the product or its features is not sufficient"); //$NON-NLS-1$
			} else if (new CertificateWorthAttention().test(certificate)) {
				communication.prompt("License coverage for the product or its features worth your attention"); //$NON-NLS-1$
			} else {
				communication.prompt("License coverage for the product and all its features is sufficient"); //$NON-NLS-1$
			}
			communication.prompt(String.format("\n%s", new RequirementsCoverageExplained(certificate.get()).get())); //$NON-NLS-1$
		} else {
			communication.prompt(name + " license status assessment failed"); //$NON-NLS-1$
		}
	}

	private List<? extends Option.Key> keys(
			List<Option<LicenseCoverageCheckOption, CoverageCheckOptionDecision>> options) {
		return options.stream()//
				.map(Option::key)//
				.toList();
	}

	public static enum Result {
		proceed, exit;
	}

}
