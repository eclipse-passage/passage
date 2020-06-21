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
package org.eclipse.passage.lic.internal.json.tobemoved;

import java.io.IOException;
import java.util.Date;

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

@SuppressWarnings("restriction")
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
		JsonNode node = parser.getCodec().readTree(parser);
		return new BaseCondition(node.get("feature").textValue(), //$NON-NLS-1$ ,
				new BaseVersionMatch( //
						node.get("version").textValue(), //$NON-NLS-1$
						new MatchingRuleForIdentifier(node.get("rule").textValue()).get()), //$NON-NLS-1$
				new BaseValidityPeriodClosed(//
						new Date(node.get("period").get("from").longValue()), //$NON-NLS-1$ //$NON-NLS-2$
						new Date(node.get("period").get("to").longValue())), //$NON-NLS-1$ //$NON-NLS-2$
				new BaseEvaluationInstructions(//
						new EvaluationType.Of(node.get("type").textValue()), // //$NON-NLS-1$
						node.get("expression").textValue())); //$NON-NLS-1$
	}

}
