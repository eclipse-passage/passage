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
package org.eclipse.passage.lic.base.diagnostic;

import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;

/**
 * @since 2.1
 */
public final class RequirementsCoverageExplained {

	private final ExaminationCertificate certificate;

	public RequirementsCoverageExplained(ExaminationCertificate certificate) {
		this.certificate = certificate;
	}

	public String get() {
		StringBuilder out = new StringBuilder();
		new RequirementsCoverage(certificate).get().forEach(status -> append(status, out));
		return out.toString();
	}

	private void append(RequirementStatus status, StringBuilder out) {
		out.append(//
				String.format("%s: %s; rates as %s\n", //$NON-NLS-1$
						status.feature(), //
						status.status(), //
						status.level().identifier()//
				));
	}

}
