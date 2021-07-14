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
package org.eclipse.passage.lic.internal.json;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel.Of;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.conditions.BaseCondition;
import org.eclipse.passage.lic.base.conditions.BaseConditionOrigin;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.base.conditions.BaseIssuerSignature;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.base.conditions.MatchingRuleForIdentifier;
import org.eclipse.passage.lic.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.base.requirements.BaseFeature;
import org.eclipse.passage.lic.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.base.restrictions.BaseRestriction;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Json<T> implements Function<JsonNode, T> {

	public static final class Date extends Json<ZonedDateTime> {

		@Override
		public ZonedDateTime apply(JsonNode node) {
			return date(node.get("formatted").textValue()); //$NON-NLS-1$
		}

		private ZonedDateTime date(String source) {
			return ZonedDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);
		}

	}

	public static final class LicensingCondition extends Json<Condition> {

		@Override
		public Condition apply(JsonNode node) {
			return new BaseCondition(node.get("identifier").textValue(), //$NON-NLS-1$
					node.get("feature").textValue(), //$NON-NLS-1$ ,
					new BaseVersionMatch( //
							node.get("version").textValue(), //$NON-NLS-1$
							new MatchingRuleForIdentifier(node.get("rule").textValue()).get()), //$NON-NLS-1$
					new BaseValidityPeriodClosed(//
							new Date().apply(node.get("period-closed-from")), //$NON-NLS-1$
							new Date().apply(node.get("period-closed-to"))), //$NON-NLS-1$
					new BaseEvaluationInstructions(//
							new EvaluationType.Of(node.get("type").textValue()), // //$NON-NLS-1$
							node.get("expression").textValue())); //$NON-NLS-1$
		}

	}

	public static final class LicensingPermission extends Json<Permission> {

		@Override
		public Permission apply(JsonNode node) {
			return new BasePermission(//
					new Json.Product().apply(node.get("product")), //$NON-NLS-1$
					new Json.LicensingCondition().apply(node.get("condition")), //$NON-NLS-1$
					new Json.Date().apply(node.get("lease")), //$NON-NLS-1$
					new Json.Date().apply(node.get("expiration")), //$NON-NLS-1$
					new Json.LicensingConditionOrigin().apply(node.get("origin"))); //$NON-NLS-1$
		}

	}

	public static final class LicensingConditionOrigin extends Json<ConditionOrigin> {

		@Override
		public ConditionOrigin apply(JsonNode node) {
			return new BaseConditionOrigin(//
					new ConditionMiningTarget.Of(node.get("miner").textValue()), //$NON-NLS-1$
					node.get("coordinates").textValue(), //$NON-NLS-1$
					new BaseIssuerSignature()); // TODO:
		}

	}

	public static final class LicensingRequirement extends Json<Requirement> {

		@Override
		public Requirement apply(JsonNode node) {
			return new BaseRequirement(new Json.ProductFeature().apply(node.get("feature")), //$NON-NLS-1$
					new RestrictionLevelOf().apply(node.get("restriction")), //$NON-NLS-1$
					node.get("source").textValue()); //$NON-NLS-1$
		}

	}

	public static final class Reason extends Json<TroubleCode> {

		@Override
		public TroubleCode apply(JsonNode node) {
			return new TroubleCode.Of(node.get("code").intValue(), node.get("explanation").textValue()); //$NON-NLS-1$ //$NON-NLS-2$
		}

	}

	public static final class LicensingRestriction extends Json<Restriction> {

		@Override
		public Restriction apply(JsonNode node) {
			return new BaseRestriction(new Json.Product().apply(node.get("product")), //$NON-NLS-1$
					new Json.LicensingRequirement().apply(node.get("requirement")), //$NON-NLS-1$
					new Json.Reason().apply(node.get("reason")));//$NON-NLS-1$
		}

	}

	public static final class RestrictionLevelOf extends Json<RestrictionLevel.Of> {

		@Override
		public Of apply(JsonNode node) {
			return new RestrictionLevel.Of(node.get("identifier").textValue()); //$NON-NLS-1$
		}

	}

	public static final class Product extends Json<LicensedProduct> {

		@Override
		public LicensedProduct apply(JsonNode node) {
			return new BaseLicensedProduct(node.get("identifier").textValue(), //$NON-NLS-1$
					node.get("version").textValue()); //$NON-NLS-1$
		}

	}

	public static final class ProductFeature extends Json<Feature> {

		@Override
		public Feature apply(JsonNode node) {
			return new BaseFeature(node.get("id").textValue(), node.get("version").textValue(), //$NON-NLS-1$ //$NON-NLS-2$
					node.get("name").textValue(), node.get("provider").textValue()); //$NON-NLS-1$//$NON-NLS-2$
		}

	}

}
