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
package org.eclipse.passage.lic.equinox;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.base.ProductIdentifier;

/**
 * @since 2.1
 */
public final class ApplicationIdentifier implements Supplier<String> {

	private final IApplicationContext context;

	public ApplicationIdentifier(IApplicationContext context) {
		this.context = context;
	}

	@Override
	public String get() {
		Optional<String> property = new ProductIdentifier(context::getBrandingProperty).get();
		if (property.isPresent()) {
			return property.get();
		}
		String brand = context.getBrandingId();
		if (brand != null) {
			return brand;
		}
		String application = context.getBrandingApplication();
		if (application != null) {
			return application;
		}
		// OK, no more ideas
		return new InvalidLicensedProduct().identifier();
	}

}
