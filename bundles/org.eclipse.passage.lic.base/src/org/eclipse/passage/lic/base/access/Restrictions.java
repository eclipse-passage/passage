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
package org.eclipse.passage.lic.base.access;

import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

/**
 * @since 1.1
 */
public final class Restrictions implements Supplier<ServiceInvocationResult<ExaminationCertificate>> {

	private final Registry<StringServiceId, PermissionsExaminationService> registry;
	private final Collection<Requirement> requirements;
	private final Collection<Permission> permissions;
	private final LicensedProduct product;

	public Restrictions(Registry<StringServiceId, PermissionsExaminationService> registry,
			Collection<Requirement> requirements, Collection<Permission> permissions, LicensedProduct product) {
		this.registry = registry;
		this.requirements = requirements;
		this.permissions = permissions;
		this.product = product;
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
				registry.services().stream()//
						.map(service -> service.examine(requirements, permissions, product)) //
						.reduce(new SumOfCertificates())//
						.get() // guaranteed to exist as there is at least one service
		);
	}

}
