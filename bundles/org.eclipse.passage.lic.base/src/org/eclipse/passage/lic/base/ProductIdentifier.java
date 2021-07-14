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
 * Supplies an {@code identifier} of a product under licensing, which is
 * expected to be stored in a variety of sources under a special key.
 * 
 * @see NamedData
 * 
 * @since 2.1
 */
public final class ProductIdentifier extends StringNamedData {

	public ProductIdentifier(String value) {
		super(value);
	}

	public ProductIdentifier(Map<String, Object> values) {
		super(values);
	}

	public ProductIdentifier(Function<String, String> retriever) {
		super(retriever);
	}

	public ProductIdentifier(LicensedProduct product) {
		super(product.identifier());
	}

	@Override
	public String key() {
		return "licensing.product.identifier"; //$NON-NLS-1$
	}

}
