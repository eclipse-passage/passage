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
package org.eclipse.passage.lic.internal.api.io;

import java.util.Objects;

public abstract class EncryptionAlgorithm {

	private final String name;

	public EncryptionAlgorithm(String name) {
		Objects.requireNonNull(name, "EncryptionAlgorithm::name"); //$NON-NLS-1$
		this.name = name.toUpperCase();
	}

	@Override
	public boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		return name.equals(((EncryptionAlgorithm) object).name());
	}

	public String name() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
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
