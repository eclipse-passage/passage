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
package org.eclipse.passage.lic.internal.api.restrictions;

import java.util.Collection;

import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;

/**
 * <p>
 * Let all the resolved {@linkplain Requirement}s meet all the lease
 * {@linkplain Permission}s and find out which of the former are not covered by
 * any of the latter.
 * <p>
 * <p>
 * Issue a {@linkplain Restriction} for each such unsatisfied requirement.
 * </p>
 */
public interface PermissionsExaminationService extends Service<StringServiceId> {

	ExaminationCertificate examine(Collection<Requirement> requirements, Collection<Permission> permissions,
			LicensedProduct product);

}
