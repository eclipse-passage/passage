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
package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.function.Supplier;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

final class BundleVendor implements Supplier<String> {

	private final Bundle bundle;

	protected BundleVendor(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public String get() {
		return bundle.getHeaders().get(Constants.BUNDLE_VENDOR);
	}

}
