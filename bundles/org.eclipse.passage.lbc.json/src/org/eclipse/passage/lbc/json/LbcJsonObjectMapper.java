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

import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.base.ReleaseReport;
import org.eclipse.passage.lic.internal.json.JsonObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@SuppressWarnings("restriction")
public final class LbcJsonObjectMapper implements Supplier<ObjectMapper> {

	@Override
	public ObjectMapper get() {
		return withSerializers(new JsonObjectMapper().get());
	}

	private ObjectMapper withSerializers(ObjectMapper mapper) {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new BoundLicenseSerializer(BoundLicense.class));
		module.addDeserializer(BoundLicense.class, new BoundLicenseDeserializer(BoundLicense.class));
		module.addSerializer(new ReleaseReportSerializer(ReleaseReport.class));
		module.addDeserializer(ReleaseReport.class, new ReleaseReportDeserializer(ReleaseReport.class));
		mapper.registerModule(module);
		return mapper;
	}

}
