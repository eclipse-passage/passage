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
package org.eclipse.passage.lbc.internal.base.tobemoved.acquire;

import java.util.Objects;

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lic.internal.api.LicensedProduct;

public final class Acquisition {

	private final LicensedProduct product;
	private final String user;
	private final String feature;

	public Acquisition(LicensedProduct product, String user, String feature) {
		Objects.requireNonNull(product, "Acquisition::product"); //$NON-NLS-1$
		Objects.requireNonNull(user, "Acquisition::user"); //$NON-NLS-1$
		Objects.requireNonNull(feature, "Acquisition::feature"); //$NON-NLS-1$
		this.product = product;
		this.user = user;
		this.feature = feature;
	}

	public FloatingResponse get() {
		return null;
	}

}
