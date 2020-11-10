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

import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;

final class AcquireRequestParameters extends RequestParameters {

	private final String feature;

	AcquireRequestParameters(LicensedProduct product, String feature, FloatingLicenseAccess access) {
		super(product, access);
		this.feature = feature;
	}

	@Override
	protected ConditionAction action() {
		return new ConditionAction.Acquire();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<NamedData> actionParameters() throws LicensingException {
		return Collections.singletonList(new ProductIdentifier(encode(feature)));
	}
}
