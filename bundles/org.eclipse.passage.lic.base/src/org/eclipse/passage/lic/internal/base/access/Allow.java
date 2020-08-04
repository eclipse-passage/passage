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

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

@SuppressWarnings("restriction")
final class Allow extends Cycle<Boolean> {

	Allow(Framework framework, String feature) {
		super(framework, feature);
	}

	@Override
	protected Boolean stop(Diagnostic diagnostic) {
		return new NoSevereErrors().test(diagnostic);
	}

	@Override
	protected Boolean stop(ExaminationCertificate certificate, Diagnostic diagnostic) {
		return new NoSevereErrors().test(diagnostic) && //
				new NoSevereRestrictions().test(certificate);
	}

	@Override
	protected Boolean freeWayOut() {
		return true;
	}

}
