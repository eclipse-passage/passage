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
package org.eclipse.passage.lic.api.tests.fakes.restrictions;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;

@SuppressWarnings("restriction")
public final class FakePermissionsExaminationService implements PermissionsExaminationService {

	@Override
	public StringServiceId id() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Restriction> examine(Collection<Requirement> requirements, Collection<Permission> permissions,
			LicensedProduct product) {
		throw new UnsupportedOperationException();
	}

}
