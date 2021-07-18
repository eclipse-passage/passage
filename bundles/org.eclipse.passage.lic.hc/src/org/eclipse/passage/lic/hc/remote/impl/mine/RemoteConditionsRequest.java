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
package org.eclipse.passage.lic.hc.remote.impl.mine;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.hc.remote.Configuration;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.impl.BaseConfiguration;
import org.eclipse.passage.lic.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.hc.remote.impl.RequestParameters;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

/**
 * 
 * @since 1.1
 */
public final class RemoteConditionsRequest<C extends Connection> extends RemoteRequest<C> {

	public RemoteConditionsRequest(LicensedProduct product, FloatingLicenseAccess access, HashesRegistry hashes) {
		super(product, access, hashes);
	}

	@Override
	public RequestParameters parameters() {
		return new MineRequestParameters(product, access, hash);
	}

	@Override
	public Configuration<C> config() {
		return new BaseConfiguration.Get<C>();
	}

}
