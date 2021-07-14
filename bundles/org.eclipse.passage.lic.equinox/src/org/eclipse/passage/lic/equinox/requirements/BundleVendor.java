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
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

/**
 * Supplies {@linkplain Bundle} {@code vendor} reading from headers. Does not
 * tolerate {@code null} bundle.
 */
final class BundleVendor implements Supplier<String> {

	private final Bundle bundle;

	protected BundleVendor(Bundle bundle) {
		Objects.requireNonNull(bundle, "Cannot get vendor of a bundle if there is no bundle"); //$NON-NLS-1$
		this.bundle = bundle;
	}

	@Override
	public String get() {
		return Optional.ofNullable(bundle.getHeaders().get(Constants.BUNDLE_VENDOR))//
				.orElse(EquinoxMessages.BundleVendor_unknown_vendor);
	}

}
