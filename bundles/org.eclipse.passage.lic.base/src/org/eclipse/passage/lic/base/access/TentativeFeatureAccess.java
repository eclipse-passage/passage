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

import java.util.Date;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.base.acquire.BaseGrantAcquisition;

final class TentativeFeatureAccess implements Supplier<GrantAcquisition>, Predicate<GrantAcquisition> {

	private final String feature;
	private final String tentative = "tentative"; //$NON-NLS-1$

	TentativeFeatureAccess(String feature) {
		this.feature = feature;
	}

	TentativeFeatureAccess() {
		this("any"); //$NON-NLS-1$
	}

	@Override
	public GrantAcquisition get() {
		return new BaseGrantAcquisition(//
				String.format("%s-id", tentative), //$NON-NLS-1$
				String.format("%s-grant", tentative), //$NON-NLS-1$
				feature, //
				String.format("%s-user", tentative), //$NON-NLS-1$
				new Date());
	}

	@Override
	public boolean test(GrantAcquisition grant) {
		return grant.identifier().startsWith(tentative) //
				&& grant.grant().startsWith(tentative) //
				&& grant.user().startsWith(tentative);
	}

}
