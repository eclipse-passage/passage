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
package org.eclipse.passage.lic.base.diagnostic.code;

import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.base.i18n.DiagnosticCodeMessages;

/**
 *
 * @since 2.1
 */
public final class LicenseNotStarted extends TroubleCode {

	public LicenseNotStarted() {
		super(402, DiagnosticCodeMessages.getString("LicenseNotStarted.license_not_started")); //$NON-NLS-1$
	}

}
