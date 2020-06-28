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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;

public final class BaseCondition implements Condition {

	private final String identifier;
	private final VersionMatch version;
	private final ValidityPeriod period;
	private final EvaluationInstructions instructions;

	public BaseCondition(String identifier, VersionMatch version, ValidityPeriod period,
			EvaluationInstructions instructions) {
		Objects.requireNonNull(identifier, "BaseCondition::Identifier"); //$NON-NLS-1$
		Objects.requireNonNull(version, "BaseCondition::VersionMatch"); //$NON-NLS-1$
		Objects.requireNonNull(period, "BaseCondition::ValidityPeriod"); //$NON-NLS-1$
		Objects.requireNonNull(instructions, "BaseCondition::EvaluationInstructions"); //$NON-NLS-1$
		this.identifier = identifier;
		this.version = version;
		this.period = period;
		this.instructions = instructions;
	}

	@Override
	public String feature() {
		return identifier;
	}

	@Override
	public VersionMatch versionMatch() {
		return version;
	}

	@Override
	public ValidityPeriod validityPeriod() {
		return period;
	}

	@Override
	public EvaluationInstructions evaluationInstructions() {
		return instructions;
	}

}
