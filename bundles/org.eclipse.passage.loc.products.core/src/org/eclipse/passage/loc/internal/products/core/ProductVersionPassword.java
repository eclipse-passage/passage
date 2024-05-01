/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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
package org.eclipse.passage.loc.internal.products.core;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.products.model.api.ProductVersion;

final class ProductVersionPassword implements Supplier<String> {

	private final ProductVersion source;

	ProductVersionPassword(ProductVersion source) {
		Objects.requireNonNull(source, "ProductVersionPassword::source"); //$NON-NLS-1$
		this.source = source;
	}

	@Override
	public String get() {
		return id() + "###" + version(); //$NON-NLS-1$
	}

	private String id() {
		return source.getProduct().getIdentifier();
	}

	private String version() {
		return source.getVersion();
	}

}
