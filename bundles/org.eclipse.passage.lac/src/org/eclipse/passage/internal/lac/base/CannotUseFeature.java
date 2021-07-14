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

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.internal.net.handle.Failure;

/**
 * Public only for test purposes
 */
public final class CannotUseFeature extends Failure {

	public CannotUseFeature(String feature, LicensedProduct product, String user) {
		super(620, String.format("Feature %s of product [%s] cannot be used by user [%s]", //$NON-NLS-1$
				feature, product, user));
	}

}