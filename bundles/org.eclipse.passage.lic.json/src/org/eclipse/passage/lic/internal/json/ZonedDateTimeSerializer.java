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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = 6125923493245016575L;

	protected ZonedDateTimeSerializer(Class<ZonedDateTime> type) {
		super(type);
	}

	@Override
	public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("formatted", date(value)); //$NON-NLS-1$
		gen.writeEndObject();
	}

	private String date(ZonedDateTime time) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time);
	}

}
