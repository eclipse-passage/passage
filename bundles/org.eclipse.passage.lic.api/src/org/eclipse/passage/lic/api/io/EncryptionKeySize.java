/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
public abstract class EncryptionKeySize {

	private final int size;

	public EncryptionKeySize(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("EncryptionKeySize::size must be positive"); //$NON-NLS-1$
		}
		this.size = size;
	}

	public final int size() {
		return size;
	}

	@Override
	public final boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		return size == ((EncryptionKeySize) object).size();
	}

	@Override
	public final int hashCode() {
		return Objects.hash(size);
	}

	@Override
	public final String toString() {
		return Integer.toString(size);
	}

	public static final class Default extends EncryptionKeySize {

		public Default() {
			super(1024);
		}

	}

	public static final class Of extends EncryptionKeySize {

		public Of(int size) {
			super(size);
		}

	}

}
