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

import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.access.PermissionTransport;
import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(property = { LICENSING_CONTENT_TYPE + '=' + LICENSING_CONTENT_TYPE_JSON })
public class JsonPermissionTransport implements PermissionTransport {

	@Override
	public Iterable<FeaturePermission> readPermissions(InputStream input) throws IOException {
		Collection<FeaturePermission> descriptors = new ArrayList<>();
		ObjectMapper mapper = JsonTransport.createObjectMapper();
		FeaturePermissionAggregator transfer = mapper.readValue(input, FeaturePermissionAggregator.class);
		transfer.getFeaturePermissions().forEach(descriptors::add);
		return descriptors;
	}

	@Override
	public void writePermissions(Iterable<FeaturePermission> permissions, OutputStream output) throws IOException {
		if (permissions == null) {
			return;
		}
		FeaturePermissionAggregator aggregator = new FeaturePermissionAggregator();
		for (FeaturePermission permission : permissions) {
			aggregator.addFeaturePermission(permission);
		}

		ObjectMapper mapper = JsonTransport.createObjectMapper();
		output.write(mapper.writeValueAsBytes(aggregator));

	}

}
