/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.json;

import java.util.Date;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @deprecated use revised version of transport (package revision)
 */
@Deprecated
final class LicensingConditionMixIn implements LicensingCondition {

	static LicensingConditionMixIn create(LicensingCondition d) {
		String featureIdentifier = d.getFeatureIdentifier();
		String matchVersion = d.getMatchVersion();
		String matchRule = d.getMatchRule();
		Date validFrom = d.getValidFrom();
		Date validUntil = d.getValidUntil();
		String conditionType = d.getConditionType();
		String conditionExpression = d.getConditionExpression();
		return new LicensingConditionMixIn(featureIdentifier, matchVersion, matchRule, validFrom, validUntil,
				conditionType, conditionExpression);
	}

	private final String featureIdentifier;
	private final String matchVersion;
	private final String matchRule;
	private final Date validFrom;
	private final Date validUntil;
	private final String conditionType;
	private final String conditionExpression;

	@JsonCreator
	LicensingConditionMixIn(@JsonProperty("featureIdentifier") String featureIdentifier,
			@JsonProperty("matchVersion") String matchVersion, @JsonProperty("matchRule") String matchRule,
			@JsonProperty("validFrom") Date validFrom, @JsonProperty("validUntil") Date validUntil,
			@JsonProperty("conditionType") String conditionType,
			@JsonProperty("conditionExpression") String conditionExpression) {
		this.featureIdentifier = featureIdentifier;
		this.matchVersion = matchVersion;
		this.matchRule = matchRule;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.conditionType = conditionType;
		this.conditionExpression = conditionExpression;
	}

	@Override
	public String getFeatureIdentifier() {
		return featureIdentifier;
	}

	@Override
	public String getMatchVersion() {
		return matchVersion;
	}

	@Override
	public String getMatchRule() {
		return matchRule;
	}

	@Override
	public Date getValidFrom() {
		return validFrom;
	}

	@Override
	public Date getValidUntil() {
		return validUntil;
	}

	@Override
	public String getConditionType() {
		return conditionType;
	}

	@Override
	public String getConditionExpression() {
		return conditionExpression;
	}

}