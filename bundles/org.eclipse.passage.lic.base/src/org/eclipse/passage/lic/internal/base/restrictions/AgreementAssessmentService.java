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
package org.eclipse.passage.lic.internal.base.restrictions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedAgreement;
import org.eclipse.passage.lic.api.restrictions.AgreementState;
import org.eclipse.passage.lic.api.restrictions.AgreementToAccept;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;

public final class AgreementAssessmentService {

	private final LicensedProduct product;
	private final Collection<Requirement> requirements;
	private final HashesRegistry hashes;

	public AgreementAssessmentService(LicensedProduct product, Collection<Requirement> requirements,
			HashesRegistry hashes) {
		this.product = product;
		this.requirements = requirements;
		this.hashes = hashes;
	}

	public Collection<AgreementToAccept> assessment() {
		return requirements.stream()//
				.map(this::assessment)//
				.flatMap(Collection::stream)//
				.collect(Collectors.toList());
	}

	private Collection<AgreementToAccept> assessment(Requirement requirement) {
		return requirement.agreements().stream()//
				.map(agreement -> new BaseAgreementToAccept(requirement, agreement, assessment(agreement, requirement)))//
				.collect(Collectors.toList());
	}

	private AgreementState assessment(ResolvedAgreement agreement, Requirement requirement) {
		try (InputStream content = agreement.content()) {
			return contentAssessment(content, agreement, requirement);
		} catch (IOException e) {
			String name = origin(agreement, requirement);
			return new Assessment(name, cannotBeRead(e, name));
		}
	}

	private AgreementState contentAssessment(InputStream content, ResolvedAgreement agreement,
			Requirement requirement) {
		@SuppressWarnings("hiding")
		Optional<Hashes> hashes = hashingService();
		String name = origin(agreement, requirement);
		if (!hashes.isPresent()) {
			return new Assessment(name, noHashingService());
		}
		return new AgreementAcceptanceService(hashes.get(), product).accepted(content, name);
	}

	private String origin(ResolvedAgreement agreement, Requirement requirement) {
		return requirement.source().toString() + ':' + agreement.path();
	}

	private Trouble cannotBeRead(IOException e, String location) {
		return new Trouble(//
				new ServiceFailedOnMorsel(), //
				String.format("Failed to read content of agreement [%s]", location), //$NON-NLS-1$
				e);
	}

	private Trouble noHashingService() {
		return new Trouble(//
				new NoServicesOfType("hash calculator"), //$NON-NLS-1$
				"Any agreement assessment is impossible."); //$NON-NLS-1$
	}

	private Optional<Hashes> hashingService() {
		Registry<StringServiceId, Hashes> registry = hashes.get();
		if (registry.services().isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(registry.services().iterator().next());
	}

}
