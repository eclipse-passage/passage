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
package org.eclipse.passage.lic.internal.base.diagnostic.code;

import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.base.i18n.DiagnosticCodeMessages;

public final class InsufficientLicenseCoverage extends TroubleCode {

	public InsufficientLicenseCoverage() {
		super(405, DiagnosticCodeMessages.getString("InsufficientLicenseCoverage.not_covered")); //$NON-NLS-1$
	}

}
