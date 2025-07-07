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

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.internal.base.access.Libraries;
import org.eclipse.passage.lic.internal.equinox.access.AgreementAcceptanceDelegate;
import org.eclipse.passage.lic.internal.equinox.access.RegisteredLibraries;

@SuppressWarnings("restriction")
final class OptionAccept extends BaseOption<LicenseCoverageCheckOption, CoverageCheckOptionDecision> {

	private final Collection<AgreementToAccept> agreements;
	private final Options<AgreementAcceptanceOption, AcceptanceDecision> options;
	private final Libraries libraries;

	OptionAccept(TheOtherSide interaction, Collection<AgreementToAccept> agreements, LicensedProduct product) {
		super(new LicenseCoverageCheckOption.Choise().acceptAgreement(), //
				"Accept", //$NON-NLS-1$
				"Read and accept license agreements", //$NON-NLS-1$
				interaction);
		this.agreements = agreements;
		this.options = new Options<>(Arrays.asList(//
				new OptionAccepted(interaction), //
				new OptionDenied(interaction)), //
				interaction);
		this.libraries = new Libraries(new RegisteredLibraries(), () -> product);
	}

	@Override
	public CoverageCheckOptionDecision run() {
		new DecoratedPrompt(interaction).head(String.format("accept license agreements: %d", agreements.size()), //$NON-NLS-1$
				"Please read the agreement(s) carefully prior pressing 'I agree'"); //$NON-NLS-1$
		Optional<AgreementAcceptanceService> root = acceptanceService();
		if (!root.isPresent()) {
			reportInsufficientConfiguration();
			return CoverageCheckOptionDecision.quit;
		}
		AgreementAcceptanceDelegate acceptance = new AgreementAcceptanceDelegate(root.get(), libraries);
		agreements.forEach(agreement -> exposeForAccept(agreement, acceptance));
		return CoverageCheckOptionDecision.reassess;
	}

	private void reportInsufficientConfiguration() {
		interaction.prompt(
				"The product lacks configuration, thus license acceptance cannot be performed. \n Contact the vendor."); //$NON-NLS-1$

	}

	private void exposeForAccept(AgreementToAccept agreement, AgreementAcceptanceDelegate service) {
		try {
			if (exposedAndAccepted(agreement)) {
				service.accept(agreement);
			}
		} catch (Exception e) {
			interaction.swear(e);
		}
	}

	private boolean exposedAndAccepted(AgreementToAccept agreement) {
		interaction.prompt(String.format("  ---\n%s\n   ---", agreement.acceptance().name())); //$NON-NLS-1$
		interaction.prompt(new String(agreement.acceptance().content()));
		return AcceptanceDecision.accepted.equals(options.promptAndPick().run());
	}

	private Optional<AgreementAcceptanceService> acceptanceService() {
		return new SuppliedFrameworkAware()
				.withFramework(framework -> framework.accessCycleConfiguration().acceptance());
	}

	private enum AcceptanceDecision {
		accepted, denied
	}

	private static final class OptionAccepted extends BaseOption<AgreementAcceptanceOption, AcceptanceDecision> {

		OptionAccepted(Interaction interaction) {
			super(new AgreementAcceptanceOption.Choise().accept(), //
					"Accept", //$NON-NLS-1$
					"I have read the text of the agreement carefully and ACCEPT all its terms", //$NON-NLS-1$
					interaction);
		}

		@Override
		public AcceptanceDecision run() {
			interaction.prompt("--> Agreement has just been ACCEPTED"); //$NON-NLS-1$
			return AcceptanceDecision.accepted;
		}

	}

	private static final class OptionDenied extends BaseOption<AgreementAcceptanceOption, AcceptanceDecision> {

		OptionDenied(Interaction interaction) {
			super(new AgreementAcceptanceOption.Choise().decline(), //
					"Do not accept", //$NON-NLS-1$
					"I DO NOT ACCEPT the agreement", //$NON-NLS-1$
					interaction);
		}

		@Override
		public AcceptanceDecision run() {
			interaction.prompt("--> Agreementhas just been DENIED"); //$NON-NLS-1$
			return AcceptanceDecision.denied;
		}

	}

	private static final class AgreementAcceptanceOption extends Option.Key.Base {

		private AgreementAcceptanceOption(char key) {
			super(key);
		}

		private static final class Choise {

			AgreementAcceptanceOption accept() {
				return new AgreementAcceptanceOption('a');
			}

			AgreementAcceptanceOption decline() {
				return new AgreementAcceptanceOption('n');
			}

		}

	}
}
