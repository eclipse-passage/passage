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
package org.eclipse.passage.lbc.internal.base.acquire;

import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;

final class ProtectedGrantCapacity implements Supplier<Integer> {

	private final Logger log = LogManager.getLogger(getClass());
	private final String feature = "org.eclipse.passage.lbc.acquire.concurrent.full"; //$NON-NLS-1$
	private final FeatureGrant grant;
	private final int unlicensed = 4;

	ProtectedGrantCapacity(FeatureGrant grant) {
		this.grant = grant;
	}

	@Override
	public Integer get() {
		if (grant.getCapacity() < unlicensed) {
			return grant.getCapacity();
		}
		if (new EquinoxPassage().canUse(feature)) {
			return grant.getCapacity();
		}
		reportDiminish();
		return unlicensed;
	}

	private void reportDiminish() {
		log.error(String.format("Due to insufficient license coverage capacity for grant [%s] decreased to %d", //$NON-NLS-1$
				grant(), //
				unlicensed));
	}

	private String grant() {
		return String.format("feature=%s, license=%s, capacity=%d", //$NON-NLS-1$
				grant.getFeature().getIdentifier(), //
				grant.getPack().getLicense().getIdentifier(), //
				grant.getCapacity());
	}

}
