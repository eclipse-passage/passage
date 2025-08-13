/*******************************************************************************
 * Copyright (c) 2020, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.base.requirements;

import java.util.Objects;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.requirements.Feature;

/**
 * Base <i>data driven</i> implementation of a {@linkplain Feature} descriptor.
 *
 * @since 2.1
 */
public final class BaseFeature implements Feature {

	private final FeatureIdentifier id;
	private final String version;
	private final String name;
	private final String provider;

	/**
	 * @since 4.0
	 */
	public BaseFeature(FeatureIdentifier id, String version, String name, String provider) {
		this.id = Objects.requireNonNull(id);
		this.version = Objects.requireNonNull(version);
		this.name = Objects.requireNonNull(name);
		this.provider = Objects.requireNonNull(provider);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String version() {
		return version;
	}

	@Override
	public FeatureIdentifier identifier() {
		return id;
	}

	@Override
	public String provider() {
		return provider;
	}

	@Override
	public boolean equals(Object another) {
		if (!getClass().isInstance(another)) {
			return false;
		}
		Feature feature = (Feature) another;
		return id.equals(feature.identifier()) //
				&& name.equals(feature.name()) //
				&& version.equals(feature.version()) //
				&& provider.equals(feature.provider()); //
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, version, id, provider);
	}

	@Override
	public String toString() {
		return "BaseFeature [id=" + id.identifier() + // //$NON-NLS-1$
				", version=" + version + // //$NON-NLS-1$
				", name=" + name + // //$NON-NLS-1$
				", provider=" + provider + // //$NON-NLS-1$
				"]"; //$NON-NLS-1$
	}

}
