/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base.conditions;

import static org.eclipse.passage.lic.base.LicensingProperties.*;

import java.util.Date;

import org.eclipse.passage.lic.runtime.LicensingCondition;

public class BaseLicensingCondition implements LicensingCondition {

	private final String featureIdentifier;
	private final String matchVersion;
	private final String matchRule;
	
	private final Date validFrom;
	private final Date validUntil;

	private final String conditionType;
	private final String conditionExpression;

	BaseLicensingCondition(String identifier, String version, String rule, Date from, Date until, String conditionType,
			String conditionExpression) {
		this.featureIdentifier = identifier;
		this.matchVersion = version;
		this.matchRule = rule;
		this.validFrom = from;
		this.validUntil = until;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LICENSING_FEATURE_IDENTIFIER).append('=').append(featureIdentifier).append(';');
		sb.append("version").append('=').append(matchVersion).append(';'); //$NON-NLS-1$
		sb.append("rule").append('=').append(matchRule).append(';'); //$NON-NLS-1$
		sb.append("validFrom").append('=').append(validFrom).append(';'); //$NON-NLS-1$
		sb.append("validUntil").append('=').append(validUntil).append(';'); //$NON-NLS-1$
		sb.append(LICENSING_CONDITION_TYPE).append('=').append(conditionType).append(';');
		sb.append("expression").append('=').append(conditionExpression); //$NON-NLS-1$
		return sb.toString();
	}

}
