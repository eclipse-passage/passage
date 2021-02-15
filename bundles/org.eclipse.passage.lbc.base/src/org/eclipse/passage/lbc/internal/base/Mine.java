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

import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.mine.Conditions;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

final class Mine extends AuthentifiedChoreDraft {

	Mine(RawRequest data) {
		super(data);
	}

	@Override
	protected NetResponse withProductUser(ProductUserRequest<RawRequest> request) {
		return new Conditions(request).get();
	}

}
