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

import org.eclipse.passage.lic.internal.api.conditions.Condition;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("restriction")
final class ConditionSerializer extends StdSerializer<Condition> {

	ConditionSerializer(Class<Condition> type) {
		super(type);
	}

	/**
	 * generated
	 */
	private static final long serialVersionUID = -8694881423638827104L;

	@Override
	public void serialize(Condition condition, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("feature", condition.feature()); //$NON-NLS-1$
		gen.writeStringField("version", condition.versionMatch().version()); //$NON-NLS-1$
		gen.writeStringField("rule", condition.versionMatch().rule().identifier()); //$NON-NLS-1$
		gen.writeObjectField("period", condition.validityPeriod()); //$NON-NLS-1$
		gen.writeStringField("type", condition.evaluationInstructions().type().identifier()); //$NON-NLS-1$
		gen.writeStringField("expression", condition.evaluationInstructions().expression()); //$NON-NLS-1$
		gen.writeEndObject();
	}

}
