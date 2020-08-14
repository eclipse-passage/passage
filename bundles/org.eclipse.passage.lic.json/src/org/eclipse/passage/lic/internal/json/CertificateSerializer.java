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

import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CertificateSerializer extends StdSerializer<ExaminationCertificate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8642464467092910399L;

	CertificateSerializer(Class<ExaminationCertificate> t) {
		super(t);
	}

	@Override
	public void serialize(ExaminationCertificate value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeStringField("stamp", date(value.stamp())); //$NON-NLS-1$
		writeCollection(value.participants(), gen, "permissions"); //$NON-NLS-1$
		writeCollection(value.restrictions(), gen, "restrictions"); //$NON-NLS-1$
		gen.writeEndObject();
	}

	private <T> void writeCollection(Collection<T> items, JsonGenerator gen, String name) throws IOException {
		gen.writeArrayFieldStart(name);
		for (T item : items) {
			gen.writeObject(item);
		}
		gen.writeEndArray();
	}

	private String date(ZonedDateTime time) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time);
	}

}
