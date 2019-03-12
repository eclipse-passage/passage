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

import org.eclipse.passage.lic.base.access.BaseFeaturePermission;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.access.FeaturePermissionTransport;
import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component(property = { LICENSING_CONTENT_TYPE + '=' + LICENSING_CONTENT_TYPE_JSON })
public class JsonFeaturePermissionTransport implements FeaturePermissionTransport {

	@Override
	public Iterable<FeaturePermission> readFeaturePermissions(InputStream input) throws IOException {
		Collection<FeaturePermission> descriptors = new ArrayList<>();
		if (input != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			FeaturePermissionAggregator transferAgregatorObject = mapper.readValue(input,
					FeaturePermissionAggregator.class);
			descriptors.addAll(transferAgregatorObject.getFeaturePermissions());
		}
		return descriptors;
	}

	@Override
	public void writeFeaturePermissions(Iterable<FeaturePermission> permissions, OutputStream output)
			throws IOException {
		if (permissions == null) {
			return;
		}
		FeaturePermissionAggregator aggregator = new FeaturePermissionAggregator();
		for (FeaturePermission permission : permissions) {
			if (permission instanceof BaseFeaturePermission) {
				BaseFeaturePermission base = (BaseFeaturePermission) permission;
				aggregator.addFeaturePermission(base);
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
		mapper.addMixIn(BaseFeaturePermission.class, FeaturePermissionMixln.class);
		output.write(mapper.writeValueAsBytes(aggregator));

	}

}
