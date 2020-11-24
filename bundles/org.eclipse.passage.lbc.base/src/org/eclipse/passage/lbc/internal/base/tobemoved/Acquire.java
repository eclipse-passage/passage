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
package org.eclipse.passage.lbc.internal.base.tobemoved;

import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lbc.internal.base.tobemoved.acquire.Acquisition;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;

final class Acquire extends ChoreDraft {

	Acquire(RawRequest data) {
		super(data);
	}

	@Override
	protected FloatingResponse withProductAndUser(LicensedProduct product, String user) {
		Optional<String> feature = new FeatureIdentifier(data::parameter).get();
		if (!feature.isPresent()) {
			return new Failure.BadRequestNoFeature();
		}
		String decoded;
		try {
			decoded = decode(feature.get());
		} catch (LicensingException e) {
			return failed(e.getMessage());
		}
		return new Acquisition(product, user, decoded).get();
	}

}
