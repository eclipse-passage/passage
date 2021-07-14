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

import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * On a {@code licensing configuration declaration} we expect
 * {@code product version} to be stored under a precise key, which is
 * encapsulated here.
 * 
 * @see NamedData
 * 
 * @since 2.1
 */
public final class ProductVersion extends StringNamedData {

	public ProductVersion(String value) {
		super(value);
	}

	public ProductVersion(Map<String, Object> values) {
		super(values);
	}

	public ProductVersion(Function<String, String> retriever) {
		super(retriever);
	}

	public ProductVersion(LicensedProduct product) {
		super(product.version());
	}

	@Override
	public String key() {
		return "licensing.product.version"; //$NON-NLS-1$
	}

}
