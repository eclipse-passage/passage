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
package org.eclipse.passage.lic.internal.equinox.acquire;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.base.acquire.LocalLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.equinox.conditions.InstallationMiningTarget;

public final class InstallationLicenseAcquisitionService extends LocalLicenseAcquisitionService {

	@Override
	public ConditionMiningTarget id() {
		return new InstallationMiningTarget().get();
	}

}
