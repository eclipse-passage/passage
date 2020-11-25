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
package org.eclipse.passage.lbc.internal.base.tobemoved.mine;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.UserGrant;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleForIdentifier;

final class AssembledConditions {

	private final FloatingLicensePack license;

	AssembledConditions(FloatingLicensePack license) {
		this.license = license;
	}

	Collection<Condition> forUser(String identifier) throws LicensingException {
		Optional<UserGrant> user = license.getUsers().stream()//
				.filter(grant -> grant.getUser().equals(identifier))//
				.findFirst();
		if (!user.isPresent()) {
			return Collections.emptyList();
		}
		return forFeatures(user.get());
	}

	private Collection<Condition> forFeatures(UserGrant user) throws LicensingException {
		Collection<Condition> conditions = new ArrayList<>();
		for (FeatureGrant feature : license.getFeatures()) {
			conditions.add(condition(user, feature));
		}
		return conditions;
	}

	private Condition condition(UserGrant user, FeatureGrant feature) throws LicensingException {
		return new BaseCondition(//
				feature.getIdentifier(), //
				feature.getIdentifier(), //
				version(feature), //
				period(feature), //
				evaluation(user));
	}

	private EvaluationInstructions evaluation(UserGrant user) {
		return new BaseEvaluationInstructions(//
				new EvaluationType.Of(user.getAuthentication().getType()), //
				user.getAuthentication().getExpression());
	}

	private ValidityPeriod period(FeatureGrant feature) throws LicensingException {
		org.eclipse.passage.lic.floating.model.api.ValidityPeriod origin = feature.getValid();
		if (!ValidityPeriodClosed.class.isInstance(origin)) {
			throw new LicensingException(String.format(BaseMessages.AssembledConditions_validity_period_type_unknown,
					origin.eClass().getName()));
		}
		ValidityPeriodClosed closed = ValidityPeriodClosed.class.cast(origin);
		return new BaseValidityPeriodClosed(date(closed.getFrom()), date(closed.getUntil()));
	}

	private ZonedDateTime date(Date date) {
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private VersionMatch version(FeatureGrant feature) {
		return new BaseVersionMatch(//
				feature.getVersion().getVersion(), //
				new MatchingRuleForIdentifier(feature.getVersion().getRule()).get());
	}

}
