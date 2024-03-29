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
package org.eclipse.passage.lic.base;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * 
 * @since 2.1
 */
public abstract class BaseFramework implements Framework {

	private final LicensedProduct product;

	protected BaseFramework() {
		this.product = productRead();
	}

	@Override
	public final LicensedProduct product() {
		return product;
	}

	protected abstract LicensedProduct productRead();

}
