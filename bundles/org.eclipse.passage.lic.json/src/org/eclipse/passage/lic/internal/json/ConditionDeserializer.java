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

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleForIdentifier;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

final class ConditionDeserializer extends StdDeserializer<Condition> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = 4583912455528712124L;

	ConditionDeserializer(Class<Condition> type) {
		super(type);
	}

	@Override
	public Condition deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		// FIXME: redesign innards basing on {@code NamedData}
		JsonNode node = parser.getCodec().readTree(parser);
		return new BaseCondition(node.get("identifier").textValue(), //$NON-NLS-1$
				node.get("feature").textValue(), //$NON-NLS-1$ ,
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

	private ZonedDateTime date(String source) {
		return ZonedDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);
	}

}
