/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.internal.lac.base;

import java.util.Optional;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.FeatureIdentifier;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ChoreDraft;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.internal.net.handle.PlainSuceess;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

final class CanUse extends ChoreDraft<NetRequest> {

	protected CanUse(NetRequest request) {
		super(request);
	}

	@Override
	protected Optional<NetResponse> rawInvalid() {
		Optional<String> feature = feature();
		if (!feature.isPresent()) {
			return Optional.of(new Failure.BadRequestNoFeature());
		}
		return Optional.empty();
	}

	@Override
	protected Optional<NetResponse> productUserInvalid(ProductUserRequest<NetRequest> refined) {
		return Optional.empty();
	}

	private Optional<String> feature() {
		return new FeatureIdentifier(key -> data.parameter(key)).get();
	}

	@Override
	protected NetResponse withProductUser(ProductUserRequest<NetRequest> request) throws LicensingException {
		String feature = feature().get();
		return new PassageAgent(request).canUse(feature)//
				? new PlainSuceess() //
				: new CannotUseFeature(feature, request.product().get(), request.user().get());
	}

}
