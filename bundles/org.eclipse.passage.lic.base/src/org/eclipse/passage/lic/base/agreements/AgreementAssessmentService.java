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
package org.eclipse.passage.lic.base.agreements;

import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementState;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;

public final class AgreementAssessmentService {

	private final Collection<Requirement> requirements;
	private final BaseAgreementAcceptanceService.Smart acceptance;

	public AgreementAssessmentService(Collection<Requirement> requirements, AgreementAcceptanceService acceptance) {
		this.requirements = requirements;
		this.acceptance = new BaseAgreementAcceptanceService.Smart(acceptance);
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
		} catch (Exception e) {
			String name = origin(agreement, requirement);
			return new Assessment(name, cannotBeRead(e, name));
		}
	}

	private AgreementState contentAssessment(InputStream content, ResolvedAgreement agreement,
			Requirement requirement) {
		return acceptance.accepted(content, origin(agreement, requirement));
	}

	private String origin(ResolvedAgreement agreement, Requirement requirement) {
		return requirement.source().toString() + ':' + agreement.path();
	}

	private Trouble cannotBeRead(Exception e, String location) {
		return new Trouble(//
				new ServiceFailedOnMorsel(), //
				String.format("Failed to read content of agreement [%s]", location), //$NON-NLS-1$
				e);
	}

}
