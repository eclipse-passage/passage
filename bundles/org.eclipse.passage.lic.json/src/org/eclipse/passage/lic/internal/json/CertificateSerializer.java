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
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("restriction")
public final class CertificateSerializer extends StdSerializer<ExaminationCertificate> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = -8642464467092910399L;

	CertificateSerializer(Class<ExaminationCertificate> type) {
		super(type);
	}

	@Override
	public void serialize(ExaminationCertificate value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("stamp", value.stamp()); //$NON-NLS-1$
		writeCollection(value.satisfied(), gen, "requirements"); //$NON-NLS-1$
		writeCollection(value.restrictions(), gen, "restrictions"); //$NON-NLS-1$
		writeCollection(permissions(value), gen, "permissions"); //$NON-NLS-1$
		gen.writeEndObject();
	}

	private <T> void writeCollection(Collection<T> items, JsonGenerator gen, String name) throws IOException {
		gen.writeArrayFieldStart(name);
		for (T item : items) {
			gen.writeObject(item);
		}
		gen.writeEndArray();
	}

	private List<Permission> permissions(ExaminationCertificate certificate) {
		return certificate.satisfied().stream() //
				.map(requirement -> certificate.satisfaction(requirement)) //
				.collect(Collectors.toList());
	}

	private String date(ZonedDateTime time) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time);
	}

}
