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
package org.eclipse.passage.lic.base.access;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementState;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.access.Permissions.AppliedLicenses;
import org.eclipse.passage.lic.base.agreements.AgreementAcceptanceDemand;
import org.eclipse.passage.lic.base.agreements.BaseAgreementToAccept;
import org.eclipse.passage.lic.base.agreements.MinedAgreement;
import org.eclipse.passage.lic.base.agreements.UnacceptedAgreementRestriction;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.base.restrictions.BaseExaminationCertificate;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

/**
 * @since 2.1
 */
public final class Restrictions implements Supplier<ServiceInvocationResult<ExaminationCertificate>> {

	private final Registry<StringServiceId, PermissionsExaminationService> registry;
	private final AgreementAcceptanceService acceptance;
	private final Collection<Requirement> requirements;
	private final AppliedLicenses permissions;
	private final LicensedProduct product;

	public Restrictions(LicensedProduct product, Registry<StringServiceId, PermissionsExaminationService> registry,
			AgreementAcceptanceService acceptance, Collection<Requirement> requirements, AppliedLicenses permissions) {
		this.product = product;
		this.registry = registry;
		this.acceptance = acceptance;
		this.requirements = requirements;
		this.permissions = permissions;
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> get() {
		if (registry.services().isEmpty()) {
			return new BaseServiceInvocationResult<ExaminationCertificate>( //
					new Trouble(//
							new NoServicesOfType(AccessCycleMessages.getString("Restrictions.type")), // //$NON-NLS-1$
							AccessCycleMessages.getString("Restrictions.no_services"))); //$NON-NLS-1$
		}
		return new BaseServiceInvocationResult<ExaminationCertificate>(//
				new SumOfCertificates().apply(//
						permissionBasedCertificate(), //
						agreementsBasedCertificate())//
		);
	}

	private ExaminationCertificate permissionBasedCertificate() {
		return registry.services().stream()//
				.map(service -> service.examine(requirements, permissions.permissions())) //
				.reduce(new SumOfCertificates())//
				.get(); // guaranteed to exist as there is at least one service
	}

	private ExaminationCertificate agreementsBasedCertificate() {
		List<AgreementToAccept> agreements = permissions.agreements().stream()//
				.map(this::agreementToAccept)//
				.collect(Collectors.toList());
		return new BaseExaminationCertificate(Collections.emptyMap(), restrictions(agreements), agreements);
	}

	private AgreementToAccept agreementToAccept(GlobalAgreement agreement) {
		ResolvedAgreement definition = new MinedAgreement(agreement);
		return new BaseAgreementToAccept(//
				new AgreementAcceptanceDemand(definition, product), //
				definition, //
				acceptanceState(agreement));
	}

	private AgreementState acceptanceState(GlobalAgreement agreement) {
		return acceptance.accepted(agreement.content(), agreement.name());
	}

	private Collection<Restriction> restrictions(List<AgreementToAccept> agreements) {
		return agreements.stream()//
				.filter(agreement -> !agreement.acceptance().accepted())//
				.map(agreement -> new UnacceptedAgreementRestriction(product, agreement).get())//
				.collect(Collectors.toList());
	}

}
