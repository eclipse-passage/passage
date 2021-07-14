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
import java.util.function.Supplier;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

/**
 * Supplies {@linkplain Bundle} {@code name} reading from headers. Does not
 * tolerate {@code null} bundle.
 */
final class BundleName implements Supplier<String> {

	private final Bundle bundle;

	protected BundleName(Bundle bundle) {
		Objects.requireNonNull(bundle, "Cannot get name of a bundle if there is no bundle"); //$NON-NLS-1$
		this.bundle = bundle;
	}

	@Override
	public String get() {
		return bundle.getHeaders().get(Constants.BUNDLE_NAME);
	}

}
