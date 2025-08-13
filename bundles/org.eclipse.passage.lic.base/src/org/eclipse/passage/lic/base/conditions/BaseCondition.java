/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.base.conditions;

import java.util.Objects;

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.api.conditions.VersionMatch;

/**
 *
 * @since 2.1
 */
public final class BaseCondition implements Condition {

	private final String identifier;
	private final FeatureIdentifier feature;
	private final VersionMatch version;
	private final ValidityPeriod period;
	private final EvaluationInstructions instructions;

	/**
	 * @since 4.0
	 */
	public BaseCondition(String identifier, FeatureIdentifier feature, VersionMatch version, ValidityPeriod period,
			EvaluationInstructions instructions) {
		this.identifier = Objects.requireNonNull(identifier);
		this.feature = Objects.requireNonNull(feature);
		this.version = Objects.requireNonNull(version);
		this.period = Objects.requireNonNull(period);
		this.instructions = Objects.requireNonNull(instructions);
	}

	@Override
	public String identifier() {
		return identifier;
	}

	@Override
	public FeatureIdentifier feature() {
		return feature;
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
