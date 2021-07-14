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
package org.eclipse.passage.lbc.internal.base.acquire;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.internal.net.handle.Failure;

/**
 * Public only for test purposes
 */
public final class NoGrantsAvailable extends Failure {

	public NoGrantsAvailable(LicensedProduct product, String feature) {
		super(611, String.format("No license grants available for feature %s of product %s", feature, product)); //$NON-NLS-1$
	}

}