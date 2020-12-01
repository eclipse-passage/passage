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
package org.eclipse.passage.lic.internal.base.access;

import java.util.Date;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.base.acquire.BaseGrantAcquisition;

final class TentativeFeatureAccess implements Supplier<GrantAcqisition>, Predicate<GrantAcqisition> {

	private final String feature;
	private final String tentative = "tentative"; //$NON-NLS-1$

	TentativeFeatureAccess(String feature) {
		this.feature = feature;
	}

	TentativeFeatureAccess() {
		this("any"); //$NON-NLS-1$
	}

	@Override
	public GrantAcqisition get() {
		return new BaseGrantAcquisition(//
				String.format("%s-id", tentative), //$NON-NLS-1$
				String.format("%s-grant", tentative), //$NON-NLS-1$
				feature, //
				String.format("%s-user", tentative), //$NON-NLS-1$
				new Date());
	}

	@Override
	public boolean test(GrantAcqisition grant) {
		return grant.identifier().startsWith(tentative) //
				&& grant.grant().startsWith(tentative) //
				&& grant.user().startsWith(tentative);
	}

}
