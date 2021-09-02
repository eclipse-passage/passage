/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.RequirementsCoverageExplained;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;
import org.eclipse.passage.lic.equinox.EquinoxPassageLicenseCoverage;

public final class LicenseStatusCheck {

	private final Interaction interaction;

	public LicenseStatusCheck(Interaction interaction) {
		this.interaction = interaction;
	}

	public Result run() {
		Option.Decision intention = Option.Decision.reassess;
		while (Option.Decision.reassess.equals(intention)) {
			ServiceInvocationResult<ExaminationCertificate> assessment = new EquinoxPassageLicenseCoverage().assess();
			reportAssessment(assessment.data());
			List<Option> options = options(assessment);
			Option option = promptOptions(options);
			intention = option.run();
		}
		return Option.Decision.proceed.equals(intention) ? Result.proceed : Result.exit;
	}

	private List<Option> options(ServiceInvocationResult<ExaminationCertificate> assessment) {
		List<Option> options = new ArrayList<>();
		if (new CertificateIsRestrictive().test(assessment.data())) {
			options.add(new OptionImport(interaction));
			options.add(new OptionRequest(interaction));
			agreements(assessment).ifPresent(agreements -> options.add(new OptionAccept(interaction, agreements)));
			options.add(new OptionDiagnostic(interaction, assessment.diagnostic()));
			options.add(new BaseOption.Quit());
		} else {
			options.add(new OptionImport(interaction));
			options.add(new OptionRequest(interaction));
			options.add(new OptionDiagnostic(interaction, assessment.diagnostic()));
			options.add(new BaseOption.Proceed());
		}
		return options;
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
		if (certificate.isPresent()) {
			interaction.prompt(String.format("\n%s", new RequirementsCoverageExplained(certificate.get()).get())); //$NON-NLS-1$
		} else {
			interaction.prompt("License status assessment failed"); //$NON-NLS-1$
		}
	}

	private Option promptOptions(List<Option> options) {
		while (true) {
			options.forEach(option -> interaction.prompt(option.documentation()));
			String key = interaction.input().trim();
			Optional<Option> option = findOption(options, key);
			if (option.isPresent()) {
				return option.get();
			}
			interaction.prompt(String.format("No option has been found for key %s", key)); //$NON-NLS-1$
		}
	}

	private Optional<Option> findOption(List<Option> options, String request) {
		if (request.length() != 1) {
			return Optional.empty();
		}
		char key = request.charAt(0);
		return options.stream().filter(op -> op.key() == key).findAny();
	}

	public static enum Result {
		proceed, exit;
	}

}
