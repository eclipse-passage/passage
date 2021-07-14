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
package org.eclipse.passage.lic.api.tests.fakes.conditions;

import java.util.Optional;

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.api.conditions.VersionMatch;

public final class FakeCondition implements Condition {

	private Optional<String> identifier = Optional.empty();
	private Optional<String> feature = Optional.empty();
	private Optional<VersionMatch> version = Optional.empty();
	private Optional<ValidityPeriod> period = Optional.empty();
	private Optional<EvaluationInstructions> evaluation = Optional.empty();

	@Override
	public String identifier() {
		return getOrFail(identifier);
	}

	@Override
	public String feature() {
		return getOrFail(feature);
	}

	@Override
	public VersionMatch versionMatch() {
		return getOrFail(version);
	}

	@Override
	public ValidityPeriod validityPeriod() {
		return getOrFail(period);
	}

	@Override
	public EvaluationInstructions evaluationInstructions() {
		return getOrFail(evaluation);
	}

	private <T> T getOrFail(Optional<T> optional) {
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new UnsupportedOperationException();
	}

	public FakeCondition withVersionMatch(VersionMatch v) {
		this.version = Optional.ofNullable(v);
		return this;
	}

	public FakeCondition withValidityPeriod(ValidityPeriod p) {
		this.period = Optional.ofNullable(p);
		return this;
	}

	public FakeCondition withFeature(String f) {
		this.feature = Optional.ofNullable(f);
		return this;
	}

	public FakeCondition withEvaluationInstructions(EvaluationInstructions e) {
		this.evaluation = Optional.ofNullable(e);
		return this;
	}

}
