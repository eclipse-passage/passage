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
package org.eclipse.passage.lic.internal.hc.remote.impl.acquire;

import java.util.function.BiFunction;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData.OfFeature;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;

final class OfFeatureRequest extends RemoteRequest {

	private final OfFeature data;
	private final BiFunction<RemoteServiceData.OfFeature, FloatingLicenseAccess, RequestParameters> parameters;

	OfFeatureRequest(OfFeature data, FloatingLicenseAccess access,
			BiFunction<RemoteServiceData.OfFeature, FloatingLicenseAccess, RequestParameters> parameters) {
		super(data.product(), access);
		this.data = data;
		this.parameters = parameters;
	}

	@Override
	protected RequestParameters parameters() {
		return parameters.apply(data, access);
	}

}