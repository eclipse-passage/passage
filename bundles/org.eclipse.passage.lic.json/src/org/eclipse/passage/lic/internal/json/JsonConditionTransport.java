/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.json;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONTENT_TYPE;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONTENT_TYPE_JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(property = { LICENSING_CONTENT_TYPE + '=' + LICENSING_CONTENT_TYPE_JSON })
public class JsonConditionTransport implements ConditionTransport {

	@Override
	public Iterable<LicensingCondition> readConditions(InputStream input) throws IOException {
		Collection<LicensingCondition> descriptors = new ArrayList<>();
		ObjectMapper mapper = JsonTransport.createObjectMapper();
		LicensingConditionAggregator transfer = mapper.readValue(input, LicensingConditionAggregator.class);
		transfer.getLicensingConditions().forEach(descriptors::add);
		return descriptors;
	}

	@Override
	public void writeConditions(Iterable<LicensingCondition> conditions, OutputStream output) throws IOException {
		if (conditions == null) {
			return;
		}
		LicensingConditionAggregator aggregator = new LicensingConditionAggregator();
		for (LicensingCondition c : conditions) {
			aggregator.addLicensingCondition(c);
		}

		ObjectMapper mapper = JsonTransport.createObjectMapper();
		byte[] byteValues = mapper.writeValueAsBytes(aggregator);
		output.write(byteValues);
	}

}
