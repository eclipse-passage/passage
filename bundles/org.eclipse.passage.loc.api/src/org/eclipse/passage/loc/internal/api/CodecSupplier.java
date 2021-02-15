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
package org.eclipse.passage.loc.internal.api;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.equinox.SuppliedFrameworkAware;

@SuppressWarnings("restriction")
public final class CodecSupplier extends SuppliedFrameworkAware implements Supplier<Optional<StreamCodec>> {

	private final LicensedProduct product;

	public CodecSupplier(LicensedProduct product) {
		this.product = product;
	}

	@Override
	public Optional<StreamCodec> get() {
		return withFramework(this::codec);
	}

	private StreamCodec codec(Framework framework) {
		return framework.unemployedCodecs().employFor(() -> product);
	}

}
