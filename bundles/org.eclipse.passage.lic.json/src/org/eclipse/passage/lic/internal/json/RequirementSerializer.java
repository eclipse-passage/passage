/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import org.eclipse.passage.lic.api.requirements.Requirement;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class RequirementSerializer extends StdSerializer<Requirement> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = -7005780859505972762L;

	protected RequirementSerializer(Class<Requirement> type) {
		super(type);
	}

	@Override
	public void serialize(Requirement value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("feature", value.feature()); //$NON-NLS-1$
		gen.writeObjectField("restriction", value.restrictionLevel()); //$NON-NLS-1$
		gen.writeStringField("source", value.source().toString()); //$NON-NLS-1$
		gen.writeEndObject();
	}

}
