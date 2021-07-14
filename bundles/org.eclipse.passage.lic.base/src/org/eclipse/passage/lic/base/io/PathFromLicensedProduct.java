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
package org.eclipse.passage.lic.base.io;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * @since 2.1
 */
public final class PathFromLicensedProduct implements Supplier<Path> {

	private final Supplier<Path> base;
	private final Supplier<String> identifier;
	private final Supplier<String> version;

	public PathFromLicensedProduct(Supplier<Path> base, LicensedProduct product) {
		this(base, product::identifier, product::version);
	}

	public PathFromLicensedProduct(Supplier<Path> base, String product, String version) {
		this(base, () -> product, () -> version);
	}

	public PathFromLicensedProduct(Supplier<Path> base, Supplier<String> identifier, Supplier<String> version) {
		this.base = base;
		this.identifier = identifier;
		this.version = version;
	}

	@Override
	public Path get() {
		return base.get()//
				.resolve(identifier.get())//
				.resolve(version.get());
	}

}
