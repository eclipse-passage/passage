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

import org.eclipse.passage.lic.api.conditions.Condition;

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
		JsonNode node = parser.getCodec().readTree(parser);
		return new Json.LicensingCondition().apply(node);
	}

}
