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
package org.eclipse.passage.lic.api.io;

import java.util.Objects;

/**
 * @since 2.1
 */
public abstract class EncryptionAlgorithm {

	private final String name;

	public EncryptionAlgorithm(String name) {
		Objects.requireNonNull(name, "EncryptionAlgorithm::name"); //$NON-NLS-1$
		this.name = name.toUpperCase();
	}

	public final String name() {
		return name;
	}

	@Override
	public final boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		return name.equals(((EncryptionAlgorithm) object).name());
	}

	@Override
	public final int hashCode() {
		return name.hashCode();
	}

	@Override
	public final String toString() {
		return name;
	}

	public static final class RSA extends EncryptionAlgorithm {

		public RSA() {
			super("RSA"); //$NON-NLS-1$
		}

	}

	public static final class Default extends EncryptionAlgorithm {

		public Default() {
			super(new RSA().name());
		}

	}

	public static final class Of extends EncryptionAlgorithm {

		public Of(String name) {
			super(name);
		}

	}

}
