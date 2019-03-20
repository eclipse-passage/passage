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

import static org.eclipse.passage.lic.base.LicensingProperties.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.passage.lic.base.conditions.BaseLicensingCondition;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(property = {LICENSING_CONTENT_TYPE + '=' + LICENSING_CONTENT_TYPE_JSON})
public class JsonConditionTransport implements ConditionTransport {

	@Override
	public Iterable<LicensingCondition> readConditions(InputStream input) throws IOException {
		Collection<LicensingCondition> descriptors = new ArrayList<>();
		if (input != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
			mapper.addMixIn(BaseLicensingCondition.class, LicensingConditionMixIn.class);
			ConditionDescriptorAggregator transferAgregatorObject = mapper.readValue(input,
					ConditionDescriptorAggregator.class);
			descriptors.addAll(transferAgregatorObject.getLicensingConditions());
		}
		return descriptors;
	}

	@Override
	public void writeConditions(Iterable<LicensingCondition> conditions, OutputStream output)
			throws IOException {
		if (conditions == null) {
			return;
		}
		ConditionDescriptorAggregator aggregator = new ConditionDescriptorAggregator();
		for (LicensingCondition c : conditions) {
			if (c instanceof BaseLicensingCondition) {
				BaseLicensingCondition base = (BaseLicensingCondition) c;
				aggregator.addLicensingCondition(base);
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
		mapper.addMixIn(BaseLicensingCondition.class, LicensingConditionMixIn.class);
		byte[] byteValues = mapper.writeValueAsBytes(aggregator);
		output.write(byteValues);
	}

}
