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

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.base.conditions.mining.LicensingContentType;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationExpression;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationType;

final class AcquireRequestParameters extends RequestParameters {

	private final LicensedProduct product;
	private final String feature;
	private final FloatingLicenseAccess access;

	AcquireRequestParameters(LicensedProduct product, String feature, FloatingLicenseAccess access) {
		this.product = product;
		this.feature = feature;
		this.access = access;
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected NamedData[] parameters() throws LicensingException {
		return new NamedData[] { //
				new ProductIdentifier(encode(product.identifier())), //
				new ProductVersion(encode(product.version())), //
				new FeatureIdentifier(encode(feature)), //
				new LicensingAction(new ConditionAction.Acquire()), //
				new LicensingContentType(new ContentType.Xml()), //
				new LicenseUser(access.getUser()), //
				new ServerAuthenticationType(access.getServer()), //
				new ServerAuthenticationExpression(access.getServer()) };
	}

}
