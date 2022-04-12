/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp

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
package org.eclipse.passage.lic.base.restrictions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.agreements.AgreementAssessmentService;
import org.eclipse.passage.lic.base.agreements.UnacceptedAgreementRestriction;
import org.eclipse.passage.lic.base.diagnostic.code.InsufficientLicenseCoverage;

/**
 * 
 * @since 2.1
 */
public final class BasePermissionsExaminationService implements PermissionsExaminationService {

	private final StringServiceId id = new StringServiceId("base-permissions-examination-service"); //$NON-NLS-1$
	private final AgreementAcceptanceService acceptance;
	private final LicensedProduct product;

	public BasePermissionsExaminationService(AgreementAcceptanceService acceptance, Supplier<LicensedProduct> product) {
		this.acceptance = acceptance;
		this.product = product.get();
	}

	@Override
	public StringServiceId id() {
		return id;
	}

	@Override
	public ExaminationCertificate examine(Collection<Requirement> requirements, Collection<Permission> permissions) {
		Objects.requireNonNull(requirements);
		Objects.requireNonNull(permissions);
		Objects.requireNonNull(product);
		Map<Requirement, Permission> active = new HashMap<>();
		Collection<AgreementToAccept> agreements = agreements(requirements);
		return new BaseExaminationCertificate(active, //
				assessFeature(requirements, permissions, agreements, active), //
				agreements);
	}

	private Collection<AgreementToAccept> agreements(Collection<Requirement> requirements) {
		return new AgreementAssessmentService(requirements, acceptance).assessment();
	}

	private List<Restriction> assessFeature(Collection<Requirement> requirements, Collection<Permission> permissions,
			Collection<AgreementToAccept> agreements, Map<Requirement, Permission> active) {
		List<Restriction> uncovered = insufficientCoverage(requirements, permissions, active);
		List<Restriction> unaccepted = unacceptedAgreements(agreements);
		return Stream.of(uncovered, unaccepted) //
				.flatMap(List::stream)//
				.collect(Collectors.toList());

	}

	private List<Restriction> insufficientCoverage(Collection<Requirement> requirements,
			Collection<Permission> permissions, Map<Requirement, Permission> active) {
		return requirements.stream() //
				.collect(Collectors.groupingBy(Requirement::feature)).values().stream()//
				.map(v -> insufficientLicenseCoverage(v, permissions, active))//
				.flatMap(Collection::stream)//
				.collect(Collectors.toList());
	}

	private Collection<Restriction> insufficientLicenseCoverage(Collection<Requirement> requirements,
			Collection<Permission> permissions, Map<Requirement, Permission> active) {
		return requirements.stream()//
				.filter(requirement -> notCovered(requirement, permissions, active))//
				.map(this::insufficientLicenseCoverage) //
				.collect(Collectors.toList());
	}

	private boolean notCovered(Requirement requirement, Collection<Permission> permissions,
			Map<Requirement, Permission> active) {
		Optional<Permission> satisfied = permissions.stream() //
				.filter(permission -> sameFeature(requirement, permission)) //
				.filter(permission -> versionMatches(requirement, permission)) //
				.findAny();
		if (satisfied.isPresent()) {
			active.put(requirement, satisfied.get());
		}
		return !satisfied.isPresent();
	}

	private boolean sameFeature(Requirement requirement, Permission permission) {
		return requirement.feature().identifier().equals(permission.condition().feature());
	}

	private boolean versionMatches(Requirement requirement, Permission permission) {
		return permission.condition().versionMatch().rule().match(//
				requirement.feature().version(), //
				permission.condition().versionMatch().version());
	}

	private Restriction insufficientLicenseCoverage(Requirement requirement) {
		return new BaseRestriction(product, requirement, new InsufficientLicenseCoverage());
	}

	private List<Restriction> unacceptedAgreements(Collection<AgreementToAccept> agreements) {
		return agreements.stream()// .
				.filter(agreement -> !agreement.acceptance().accepted())//
				.map(agreement -> new UnacceptedAgreementRestriction(product, agreement).get())//
				.collect(Collectors.toList());
	}

}
