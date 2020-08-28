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
package org.eclipse.passage.lbc.internal.base;

import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;

/**
 * @since 1.0
 */
public final class BoundGrant implements Function<LicenseGrant, BoundLicense> {

	@Override
	public BoundLicense apply(LicenseGrant grant) {
		return new BaseBoundLicense( //
				new ConditionIdentifier(grant.getIdentifier()), //
				new LicenseTaken(0), //
				new LicenseCapacity(grant.getCapacity()));
	}

}
