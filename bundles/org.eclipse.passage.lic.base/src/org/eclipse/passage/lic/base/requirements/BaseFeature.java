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
package org.eclipse.passage.lic.base.requirements;

import java.util.Objects;

import org.eclipse.passage.lic.api.requirements.Feature;

/**
 * Base <i>data driven</i> implementation of a {@linkplain Feature} descriptor.
 * 
 * @since 2.1
 */
public final class BaseFeature implements Feature {

	private final String id;
	private final String version;
	private final String name;
	private final String provider;

	public BaseFeature(String id, String version, String name, String provider) {
		Objects.requireNonNull(id, "Identifier is mandatory for a feature"); //$NON-NLS-1$
		Objects.requireNonNull(version, "Version is mandatory for a feature"); //$NON-NLS-1$
		Objects.requireNonNull(name, "Name is mandatory for a feature"); //$NON-NLS-1$
		Objects.requireNonNull(provider, "Provider is mandatory for a feature"); //$NON-NLS-1$
		this.id = id;
		this.version = version;
		this.name = name;
		this.provider = provider;
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
	public String identifier() {
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
		return "BaseFeature [id=" + id + // //$NON-NLS-1$
				", version=" + version + // //$NON-NLS-1$
				", name=" + name + // //$NON-NLS-1$
				", provider=" + provider + // //$NON-NLS-1$
				"]"; //$NON-NLS-1$
	}

}
