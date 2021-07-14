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
package org.eclipse.passage.lbc.base.tests;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.MD5Hashes;
import org.eclipse.passage.lic.base.io.PathKeyKeeper;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.eclipse.passage.lic.internal.net.io.SafePayload;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;

@SuppressWarnings("restriction")
abstract class DecodedResponse<T extends EObject> {

	private final NetResponse response;
	private final RawRequest request;
	private final Map<String, Object> options;

	DecodedResponse(NetResponse response, RawRequest request, Map<String, Object> options) {
		this.response = response;
		this.request = request;
		this.options = options;
	}

	final T get() throws IOException, LicensingException {
		// FIXME:AF: should be done via factory
		return new EObjectFromBytes<>(decoded(response.payload()), target(), LicensesResourceImpl::new).get(options);
	}

	protected abstract Class<T> target();

	private byte[] decoded(byte[] raw) throws LicensingException {
		LicensedProduct product = new ProductUserRequest<RawRequest>(request).product().get();
		return new SafePayload(new PathKeyKeeper(product, request.state()::source), new MD5Hashes()).decode(raw);
	}

	static final class License extends DecodedResponse<PersonalLicensePack> {

		License(NetResponse response, RawRequest request) {
			super(response, request, Collections.singletonMap(LicensesPackage.eNS_URI, LicensesPackage.eINSTANCE));
		}

		@Override
		protected Class<PersonalLicensePack> target() {
			return PersonalLicensePack.class;
		}
	}

	static final class GrantAck extends DecodedResponse<GrantAcqisition> {

		GrantAck(NetResponse response, RawRequest request) {
			super(response, request, Collections.singletonMap(LicensesPackage.eNS_URI, LicensesPackage.eINSTANCE));
		}

		@Override
		protected Class<GrantAcqisition> target() {
			return GrantAcqisition.class;
		}
	}

}
