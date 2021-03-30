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
package org.eclipse.passage.lic.internal.hc.remote.impl.acquire;

import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.PassageAction;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;

final class ReleaseRequestParameters extends RequestParameters {

	private final String feature;

	ReleaseRequestParameters(LicensedProduct product, String feature, FloatingLicenseAccess access, String hash) {
		super(product, access, hash);
		this.feature = feature;
	}

	@Override
	protected PassageAction action() {
		return new PassageAction.Release();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<NamedData> actionParameters() throws LicensingException {
		return Collections.singletonList(new FeatureIdentifier(encode(feature)));
	}

}
