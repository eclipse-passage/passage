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
import org.eclipse.passage.lbc.internal.base.BaseBoundLicense;
import org.eclipse.passage.lbc.internal.base.ConditionIdentifier;
import org.eclipse.passage.lbc.internal.base.LicenseCapacity;
import org.eclipse.passage.lbc.internal.base.LicenseTaken;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class BoundLicenseDeserializer extends StdDeserializer<BoundLicense> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8837623016361034983L;

	protected BoundLicenseDeserializer(Class<BoundLicense> vc) {
		super(vc);
	}

	@Override
	public BoundLicense deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode root = p.getCodec().readTree(p);
		return new BaseBoundLicense( //
				new ConditionIdentifier(key -> root.get(key).textValue()),
				new LicenseTaken(key -> root.get(key).intValue()),
				new LicenseCapacity(key -> root.get(key).intValue()));
	}

}
