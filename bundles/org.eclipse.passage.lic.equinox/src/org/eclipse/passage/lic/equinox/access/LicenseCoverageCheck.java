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
package org.eclipse.passage.lic.equinox.access;

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

//TODO i18n
public final class LicenseCoverageCheck {

	private final Interaction.Smart interaction;

	public LicenseCoverageCheck(Interaction interaction) {
		this.interaction = new Interaction.Smart(interaction);
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
			intention = options(assessment, product.get()).promptAndPick().run();
		}
		return CoverageCheckOptionDecision.proceed.equals(intention) ? Result.proceed : Result.exit;
	}

	private Optional<LicensedProduct> product() {
		ServiceInvocationResult<LicensedProduct> product = new EquinoxPassage().product();
		if (!product.data().isPresent()) {
			interaction.prompt(
					"Product configuration lacks product under licenseing definition. Contact the product vendor."); //$NON-NLS-1$
		}
		return product.data();
	}

	private Options<CoverageCheckOptionDecision> options(ServiceInvocationResult<ExaminationCertificate> assessment,
			LicensedProduct product) {
		List<Option<CoverageCheckOptionDecision>> options = new ArrayList<>();
		if (new CertificateIsRestrictive().test(assessment.data())) {
			options.add(new OptionImport(interaction, product));
			options.add(new OptionRequest(interaction));
			agreements(assessment)
					.ifPresent(agreements -> options.add(new OptionAccept(interaction, agreements, product)));
			options.add(new OptionDiagnostic(interaction, assessment.diagnostic()));
			options.add(new OptionQuit(interaction));
			options.add(new OptionProceed(interaction));
		} else {
			options.add(new OptionImport(interaction, product));
			options.add(new OptionRequest(interaction));
			options.add(new OptionDiagnostic(interaction, assessment.diagnostic()));
			options.add(new OptionProceed(interaction));
		}
		return new Options<>(interaction, options);
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
		interaction.head("License coverage assessment"); //$NON-NLS-1$
		if (certificate.isPresent()) {
			if (new CertificateIsRestrictive().test(certificate)) {
				interaction.prompt("License coverage for the product or its features is not sufficient"); //$NON-NLS-1$
			} else if (new CertificateWorthAttention().test(certificate)) {
				interaction.prompt("License coverage for the product or its features worth your attention"); //$NON-NLS-1$
			} else {
				interaction.prompt("License coverage for the product and all its feature is sufficient"); //$NON-NLS-1$
			}
			interaction.prompt(String.format("\n%s", new RequirementsCoverageExplained(certificate.get()).get())); //$NON-NLS-1$
		} else {
			interaction.prompt("License status assessment failed"); //$NON-NLS-1$
		}
	}

	public static enum Result {
		proceed, exit;
	}

}
