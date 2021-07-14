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

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.restrictions.NoSevereRestrictions;

final class Allow extends Cycle<Boolean> {

	Allow(Framework framework, String feature) {
		super(framework, feature);
	}

	@Override
	protected Boolean stopOnError(Diagnostic diagnostic) {
		return false;
	}

	@Override
	protected Boolean stopOnCertificate(ExaminationCertificate certificate, Diagnostic diagnostic) {
		return new NoSevereErrors().test(diagnostic) && //
				new NoSevereRestrictions().test(certificate);
	}

	@Override
	protected Boolean freeWayOut() {
		return true;
	}

}
