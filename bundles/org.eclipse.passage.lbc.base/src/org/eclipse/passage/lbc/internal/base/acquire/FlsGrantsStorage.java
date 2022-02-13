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
package org.eclipse.passage.lbc.internal.base.acquire;

import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;

final class FlsGrantsStorage extends DefaultGrantsStorage {

	private final String feature = "org.eclipse.passage.lbc.acquire.concurrent"; //$NON-NLS-1$

	@Override
	protected int capacity(FeatureGrant grant) {
		return new ProtectedGrantCapacity(grant).get();
	}

	@Override
	protected boolean beforeAcquire() {
		checkFlsLicense();
		return true; // restriction level is 'info': we just report license absence
	}

	@Override
	protected boolean afterAcquire() {
		return true;
	}

	@Override
	protected void beforeRelease() {
		// do nothing
	}

	@Override
	protected void afterRelease() {
		// do nothing
	}

	// TODO: evolve LicenseRunnable to return a result, re-implement with proper
	// grant acquisition
	private boolean checkFlsLicense() {
		boolean canUse = new EquinoxPassage().canUse(feature);
		if (!canUse) {
			log.error(String.format("FLS feature %s is not covered by a license", feature)); //$NON-NLS-1$
		}
		return canUse;
	}

}
