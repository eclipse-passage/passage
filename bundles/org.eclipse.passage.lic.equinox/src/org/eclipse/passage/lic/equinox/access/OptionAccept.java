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

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.equinox.access.Interaction.Smart;

final class OptionAccept extends BaseOption<CoverageCheckOptionDecision> {

	private final Collection<AgreementToAccept> agreements;
	private final Options<AcceptanceDecision> options;

	public OptionAccept(Interaction.Smart interaction, Collection<AgreementToAccept> agreements) {
		super('a', //
				"Accept", //$NON-NLS-1$
				"Read and accept license agreements", //$NON-NLS-1$
				interaction);
		this.agreements = agreements;
		this.options = new Options<>(interaction, //
				Arrays.asList(//
						new OptionAccepted(interaction), //
						new OptionDenied(interaction)));
	}

	@Override
	public CoverageCheckOptionDecision run() {
		interaction.head(String.format("accept license agreements: %d", agreements.size()), //$NON-NLS-1$
				"Please read the agreement(s) carefully prior pressing 'I agree'"); //$NON-NLS-1$
		Optional<AgreementAcceptanceService> acceptance = acceptanceService();
		if (!acceptance.isPresent()) {
			reportInsufficientConfiguration();
			return CoverageCheckOptionDecision.quit;
		}
		agreements.forEach(agreement -> exposeForAccept(agreement, acceptance.get()));
		return CoverageCheckOptionDecision.reassess;
	}

	private void reportInsufficientConfiguration() {
		interaction.prompt(
				"The product lacks configuration, thus license acceptance cannot be performed. \n Contact the vendor."); //$NON-NLS-1$

	}

	private void exposeForAccept(AgreementToAccept agreement, AgreementAcceptanceService service) {
		try {
			if (exposedAndAccepted(agreement)) {
				service.accept(() -> agreement.acceptance().content());
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

	private static final class OptionAccepted extends BaseOption<AcceptanceDecision> {

		OptionAccepted(Smart interaction) {
			super('a', //
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

	private static final class OptionDenied extends BaseOption<AcceptanceDecision> {

		OptionDenied(Smart interaction) {
			super('n', //
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

}
