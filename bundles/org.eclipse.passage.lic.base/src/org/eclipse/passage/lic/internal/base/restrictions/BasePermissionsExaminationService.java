/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.diagnostic.code.InsufficientLicenseCoverage;

public final class BasePermissionsExaminationService implements PermissionsExaminationService {

	private final StringServiceId id = new StringServiceId("base-permissions-examination-service"); //$NON-NLS-1$

	@Override
	public StringServiceId id() {
		return id;
	}

	@Override
	public ExaminationCertificate examine(Collection<Requirement> requirements, Collection<Permission> permissions,
			LicensedProduct product) {
		Objects.requireNonNull(requirements);
		Objects.requireNonNull(permissions);
		Objects.requireNonNull(product);
		Map<Requirement, Permission> active = new HashMap<>();
		Collection<Restriction> restrictions = requirements.stream() //
				.collect(Collectors.groupingBy(Requirement::feature)).entrySet().stream()//
				.map(e -> examineFeature(e.getValue(), permissions, product, active))//
				.flatMap(Collection::stream)//
				.collect(Collectors.toList());
		return new BaseExaminationCertificate(active, restrictions);
	}

	private Collection<Restriction> examineFeature(Collection<Requirement> requirements,
			Collection<Permission> permissions, LicensedProduct product, Map<Requirement, Permission> active) {
		return requirements.stream()//
				.filter(requirement -> !covered(requirement, permissions, active))//
				.map(requirement -> restriction(requirement, product)) //
				.collect(Collectors.toList());
	}

	private boolean covered(Requirement requirement, Collection<Permission> permissions,
			Map<Requirement, Permission> active) {
		return permissions.stream() //
				.filter(permission -> sameFeature(requirement, permission)) //
				.filter(permission -> versionMatches(requirement, permission)) //
				// keep an eye on each permission that played active role in examination
				.peek(permission -> active.put(requirement, permission)) //
				.findAny()//
				.isPresent();
	}

	private boolean sameFeature(Requirement requirement, Permission permission) {
		return requirement.feature().identifier().equals(permission.condition().feature());
	}

	private boolean versionMatches(Requirement requirement, Permission permission) {
		return permission.condition().versionMatch().rule().match(//
				requirement.feature().version(), //
				permission.condition().versionMatch().version());
	}

	private Restriction restriction(Requirement requirement, LicensedProduct product) {
		return new BaseRestriction(product, requirement, new InsufficientLicenseCoverage());
	}

}
