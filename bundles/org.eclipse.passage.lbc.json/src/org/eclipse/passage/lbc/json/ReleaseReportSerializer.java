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
package org.eclipse.passage.lbc.json;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.passage.lbc.internal.base.ReleaseReport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public final class ReleaseReportSerializer extends StdSerializer<ReleaseReport> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = 2629460673927552326L;

	protected ReleaseReportSerializer(Class<ReleaseReport> type) {
		super(type);
	}

	@Override
	public void serialize(ReleaseReport value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		writeCollection(value.get(), gen, "verdicts"); //$NON-NLS-1$
		gen.writeEndObject();
	}

	private <T> void writeCollection(Collection<T> items, JsonGenerator gen, String name) throws IOException {
		gen.writeArrayFieldStart(name);
		for (T item : items) {
			gen.writeObject(item);
		}
		gen.writeEndArray();
	}

}
