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
package org.eclipse.passage.lbc.internal.base;

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
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.eclipse.passage.lic.internal.net.io.SafePayload;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;

public abstract class DecodedRequest<T extends EObject> {

	private final RawRequest request;
	private final Map<String, Object> options;

	protected DecodedRequest(RawRequest request, Map<String, Object> options) {
		this.request = request;
		this.options = options;
	}

	public final T get() throws IOException, LicensingException {
		// FIXME:AF: should be done via factory
		return new EObjectFromBytes<>(decoded(request.content()), target(), LicensesResourceImpl::new).get(options);
	}

	protected abstract Class<T> target();

	private byte[] decoded(byte[] raw) throws LicensingException {
		LicensedProduct product = new ProductUserRequest<RawRequest>(request).product().get();
		return new SafePayload(new PathKeyKeeper(product, request.state()::source), new MD5Hashes()).decode(raw);
	}

	public static final class GrantAck extends DecodedRequest<GrantAcqisition> {

		public GrantAck(RawRequest request) {
			super(request, Collections.singletonMap(LicensesPackage.eNS_URI, LicensesPackage.eINSTANCE));
		}

		@Override
		protected Class<GrantAcqisition> target() {
			return GrantAcqisition.class;
		}
	}

}
