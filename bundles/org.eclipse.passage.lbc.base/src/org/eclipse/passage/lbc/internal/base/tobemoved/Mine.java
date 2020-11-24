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

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lbc.internal.base.tobemoved.mine.Conditions;
import org.eclipse.passage.lic.internal.api.LicensedProduct;

final class Mine extends ChoreDraft {

	Mine(RawRequest data) {
		super(data);
	}

	@Override
	protected FloatingResponse withProductAndUser(LicensedProduct product, String user) {
		return new Conditions(product, user).get();
	}

}
