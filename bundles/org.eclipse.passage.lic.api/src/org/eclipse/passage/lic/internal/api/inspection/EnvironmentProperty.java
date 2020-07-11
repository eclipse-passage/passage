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
package org.eclipse.passage.lic.internal.api.inspection;

import java.util.Objects;

public abstract class EnvironmentProperty {

	protected final String separator = ".";//$NON-NLS-1$
	private final String name;
	private final String family;

	protected EnvironmentProperty(String family, String name) {
		Objects.requireNonNull(family);
		Objects.requireNonNull(name);
		this.family = family;
		this.name = name;
	}

	protected EnvironmentProperty(FullyQualifiedName fqn) {
		this.family = fqn.family(separator);
		this.name = fqn.name(separator);
	}

	public final String family() {
		return family;
	}

	public final String name() {
		return name;
	}

	@Override
	public final boolean equals(Object object) {
		if (!EnvironmentProperty.class.isInstance(object)) {
			return false;
		}
		EnvironmentProperty os = (EnvironmentProperty) object;
		return family.equals(os.family) && name.equals(os.name);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(family, name);
	}

	@Override
	public final String toString() {
		return String.format("%s%s%s", family, separator, name); //$NON-NLS-1$
	}

	public static final class Of extends EnvironmentProperty {

		public Of(String fqn) {
			super(new FullyQualifiedName(fqn));
		}

	}

	private static final class FullyQualifiedName {

		private final String fqn;

		FullyQualifiedName(String fqn) {
			this.fqn = fqn;
		}

		String family(String separator) {
			return fqn.substring(0, bound(separator));
		}

		String name(String separator) {
			return fqn.substring(bound(separator) + separator.length());
		}

		private int bound(String separator) {
			int bound = fqn.indexOf(separator);
			if (bound < 1) {
				throw new IllegalArgumentException(String.format(//
						"Full qualifier name of a hardware property is expected to be <family>%s<name>", //$NON-NLS-1$
						separator));
			}
			return bound;
		}
	}

}
