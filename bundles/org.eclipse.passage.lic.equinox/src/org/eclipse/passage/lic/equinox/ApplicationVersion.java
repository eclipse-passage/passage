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
import org.eclipse.passage.lic.base.ProductVersion;
import org.eclipse.passage.lic.base.version.BaseSemanticVersion;
import org.eclipse.passage.lic.base.version.DefaultVersion;
import org.eclipse.passage.lic.base.version.SafeVersion;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * @since 2.1
 */
public final class ApplicationVersion implements Supplier<String> {

	private final IApplicationContext context;

	public ApplicationVersion(IApplicationContext context) {
		this.context = context;
	}

	@Override
	public String get() {
		Optional<String> property = new ProductVersion(context::getBrandingProperty).get();
		if (property.isPresent()) {
			return new SafeVersion(property.get()).semantic().toString();
		}
		return Optional.ofNullable(context.getBrandingBundle()) //
				.map(Bundle::getVersion).map(this::stringify).orElse(new DefaultVersion().toString());
	}

	private String stringify(Version version) {
		return new BaseSemanticVersion(version.getMajor(), version.getMinor(), version.getMicro()).toString();
	}

}
