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

import org.eclipse.passage.lbc.internal.api.tobemoved.Chore;
import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lbc.internal.base.tobemoved.mine.Conditions;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.net.LicenseUser;

final class Mine implements Chore {

	private RawRequest data;

	Mine(RawRequest data) {
		this.data = data;
	}

	@Override
	public FloatingResponse getDone() {
		Optional<LicensedProduct> product = new ProductFromRequest(data).get();
		if (!product.isPresent()) {
			return new Failure.BadRequestInvalidProduct();
		}
		Optional<String> user = new LicenseUser(data::parameter).get();
		if (!user.isPresent()) {
			return new Failure.BadRequestNoUser();
		}
		return new Conditions(product.get(), user.get()).get();
	}

}
