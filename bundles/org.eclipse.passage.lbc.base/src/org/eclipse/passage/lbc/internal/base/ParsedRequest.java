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
package org.eclipse.passage.lbc.internal.base;

import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.ProductLicensesRequest;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

/**
 * @since 1.0
 */
public final class ParsedRequest implements Function<BackendLicensingRequest, ProductLicensesRequest> {

	@Override
	public ProductLicensesRequest apply(BackendLicensingRequest request) {
		ProductIdentifier productId = new ProductIdentifier(key -> request.parameter(key));
		ProductVersion productVersion = new ProductVersion(key -> request.parameter(key));
		return new BaseMiningRequest(productId, productVersion, request.requester());
	}

}
