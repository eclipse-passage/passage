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
import org.eclipse.passage.lic.internal.base.InvalidLicensingConfiguration;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
public final class ApplicationVersion implements Supplier<String> {
	private final IApplicationContext context;

	public ApplicationVersion(IApplicationContext context) {
		this.context = context;
	}

	@Override
	public String get() {
		Optional<String> property = new ProductVersion(context::getBrandingProperty).get();
		if (property.isPresent()) {
			return property.get();
		}
		Bundle bundle = context.getBrandingBundle();
		if (bundle == null) {
			return new InvalidLicensingConfiguration().getProductVersion();
		}
		return stringify(bundle.getVersion());
	}

	private String stringify(Version version) {
		StringBuilder sb = new StringBuilder();
		sb.append(version.getMajor()).append('.');
		sb.append(version.getMinor()).append('.');
		sb.append(version.getMicro());
		return sb.toString();
	}

}
