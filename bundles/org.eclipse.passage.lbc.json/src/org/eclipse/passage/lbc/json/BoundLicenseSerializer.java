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

import org.eclipse.passage.lbc.internal.api.BoundLicense;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class BoundLicenseSerializer extends StdSerializer<BoundLicense> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -210107085093085286L;

	protected BoundLicenseSerializer(Class<BoundLicense> t) {
		super(t);
	}

	@Override
	public void serialize(BoundLicense license, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("identifier", license.identifier().get().get()); //$NON-NLS-1$
		gen.writeNumberField("capacity", license.capacity().get().get()); //$NON-NLS-1$
		gen.writeNumberField("taken", license.taken().get().get()); //$NON-NLS-1$
		gen.writeEndObject();
	}

}
