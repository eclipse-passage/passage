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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;

@SuppressWarnings("restriction")
public final class LicensedApplicationFromContext implements Supplier<LicensedProduct> {

	private final Supplier<Optional<IApplicationContext>> context;

	public LicensedApplicationFromContext(IApplicationContext context) {
		this.context = () -> Optional.of(context);
	}

	@Override
	public LicensedProduct get() {
		Optional<IApplicationContext> supplied = context.get();
		if (!supplied.isPresent()) {
			throw new RuntimeException();
		}
		return new BaseLicensedProduct(//
				new ApplicationIdentifier(supplied.get()).get(), //
				new ApplicationVersion(supplied.get()).get());
	}

}
