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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.licenses.model.api.LicensePlan;

public abstract class BaseLicensingData {

	private final LicensePlan plan;
	private final ZonedDateTime from;
	private final ZonedDateTime until;
	private final int capacity;

}
