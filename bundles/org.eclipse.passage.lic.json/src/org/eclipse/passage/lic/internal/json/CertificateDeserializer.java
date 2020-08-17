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
package org.eclipse.passage.lic.internal.json;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel.Of;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleForIdentifier;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.base.restrictions.BaseRestriction;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class CertificateDeserializer extends StdDeserializer<ExaminationCertificate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4764283882751022188L;

	CertificateDeserializer(Class<ExaminationCertificate> vc) {
		super(vc);
	}

	@Override
	public ExaminationCertificate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode root = p.getCodec().readTree(p);
		ArrayNode permissions = (ArrayNode) root.get("permissions"); //$NON-NLS-1$
		ArrayNode restrictions = (ArrayNode) root.get("restrictions"); //$NON-NLS-1$
		return new AcquiredExaminationCertificate(permissions(permissions), restrictions(restrictions),
				root.get("stamp").textValue()); //$NON-NLS-1$
	}

	private Collection<Permission> permissions(ArrayNode permissions) {
		Collection<Permission> deserialized = new LinkedList<>();
		for (JsonNode node : permissions) {
			deserialized.add(new BasePermission(product(node.get("product")), condition(node.get("condition")), //$NON-NLS-1$ //$NON-NLS-2$
					ZonedDateTime.now(), ZonedDateTime.now().plusDays(2)));
		}
		return deserialized;
	}

	private Collection<Restriction> restrictions(ArrayNode restrictions) {
		Collection<Restriction> deserialized = new LinkedList<>();
		for (JsonNode node : restrictions) {
			deserialized.add(new BaseRestriction(product(node.get("product")), requirement(node.get("requirement")), //$NON-NLS-1$ //$NON-NLS-2$
					reason(node.get("reason")))); //$NON-NLS-1$
		}
		return deserialized;
	}

	private TroubleCode reason(JsonNode node) {
		return new TroubleCode.Of(node.get("code").intValue(), node.get("explanation").textValue()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private Requirement requirement(JsonNode node) {
		return new BaseRequirement(feature(node.get("feature")), restriction(node.get("restriction")), //$NON-NLS-1$ //$NON-NLS-2$
				node.get("source").textValue()); //$NON-NLS-1$
	}

	private Of restriction(JsonNode node) {
		return new RestrictionLevel.Of(node.get("identifier").textValue()); //$NON-NLS-1$
	}

	private Feature feature(JsonNode node) {
		return new BaseFeature(node.get("id").textValue(), node.get("version").textValue(), //$NON-NLS-1$ //$NON-NLS-2$
				node.get("name").textValue(), node.get("provider").textValue()); //$NON-NLS-1$//$NON-NLS-2$
	}

	private Condition condition(JsonNode node) {
		return new BaseCondition(node.get("feature").textValue(), //$NON-NLS-1$ ,
				new BaseVersionMatch( //
						node.get("version").textValue(), //$NON-NLS-1$
						new MatchingRuleForIdentifier(node.get("rule").textValue()).get()), //$NON-NLS-1$
				new BaseValidityPeriodClosed(//
						date(node.get("period-closed-from").textValue()), //$NON-NLS-1$
						date(node.get("period-closed-to").textValue())), //$NON-NLS-1$
				new BaseEvaluationInstructions(//
						new EvaluationType.Of(node.get("type").textValue()), // //$NON-NLS-1$
						node.get("expression").textValue())); //$NON-NLS-1$
	}

	private LicensedProduct product(JsonNode productNode) {
		return new BaseLicensedProduct(productNode.get("identifier").textValue(), //$NON-NLS-1$
				productNode.get("version").textValue()); //$NON-NLS-1$
	}

	private ZonedDateTime date(String source) {
		return ZonedDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);
	}

}
