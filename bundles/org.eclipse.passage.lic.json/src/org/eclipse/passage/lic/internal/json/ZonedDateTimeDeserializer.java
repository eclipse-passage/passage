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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = -8647902276935159360L;

	protected ZonedDateTimeDeserializer(Class<ZonedDateTime> type) {
		super(type);
	}

	@Override
	public ZonedDateTime deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		return new Json.Date().apply(node);
	}

}
