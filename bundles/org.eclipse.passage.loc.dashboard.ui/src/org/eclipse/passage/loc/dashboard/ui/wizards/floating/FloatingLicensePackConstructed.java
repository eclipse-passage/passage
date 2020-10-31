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
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import org.eclipse.passage.loc.internal.api.FloatingLicenseRequest;

final class LicenseFromRequest {

	private final FloatingLicenseRequest request;

	LicenseFromRequest(FloatingLicenseRequest request) {
		this.request = request;
	}

}
