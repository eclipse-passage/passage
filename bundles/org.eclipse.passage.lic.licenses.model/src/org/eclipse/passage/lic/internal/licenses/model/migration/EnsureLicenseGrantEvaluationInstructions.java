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
package org.eclipse.passage.lic.internal.licenses.model.migration;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EnsureLicenseGrantEvaluationInstructions implements Function<LicenseGrant, EvaluationInstructions> {

	@Override
	public EvaluationInstructions apply(LicenseGrant grant) {
		return Optional.ofNullable(grant.getUserAuthentication()).orElseGet(() -> reset(grant));
	}

	private EvaluationInstructions reset(LicenseGrant grant) {
		EvaluationInstructions instructions = LicensesFactory.eINSTANCE.createEvaluationInstructions();
		grant.setUserAuthentication(instructions);
		return instructions;
	}

}
