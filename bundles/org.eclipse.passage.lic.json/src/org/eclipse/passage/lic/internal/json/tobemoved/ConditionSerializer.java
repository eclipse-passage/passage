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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;

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
		// FIXME: redesign innards basing on {@code NamedData}
		gen.writeStartObject();
		gen.writeStringField("feature", condition.feature()); //$NON-NLS-1$
		gen.writeStringField("version", condition.versionMatch().version()); //$NON-NLS-1$
		gen.writeStringField("rule", condition.versionMatch().rule().identifier()); //$NON-NLS-1$
		gen.writeStringField("period-closed-from", date(((ValidityPeriodClosed) condition.validityPeriod()).from())); //$NON-NLS-1$
		gen.writeStringField("period-closed-to", date(((ValidityPeriodClosed) condition.validityPeriod()).to())); //$NON-NLS-1$
		gen.writeStringField("type", condition.evaluationInstructions().type().identifier()); //$NON-NLS-1$
		gen.writeStringField("expression", condition.evaluationInstructions().expression()); //$NON-NLS-1$
		gen.writeEndObject();
	}

	private String date(ZonedDateTime time) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time);
	}

}
