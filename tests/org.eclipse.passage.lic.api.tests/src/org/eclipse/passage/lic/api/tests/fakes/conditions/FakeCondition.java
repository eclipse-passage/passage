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
package org.eclipse.passage.lic.api.tests.fakes.conditions;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;

@SuppressWarnings("restriction")
public final class FakeCondition implements Condition {

	@Override
	public VersionMatch versionMatch() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ValidityPeriod validityPeriod() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String feature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EvaluationInstructions evaluationInstructions() {
		throw new UnsupportedOperationException();
	}

}
