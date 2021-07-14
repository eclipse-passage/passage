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

/**
 * @since 2.1
 */
public interface DigestExpectation {

	/**
	 * @return {@code true} if there is any expectation at all, {@code false} if
	 *         digest is not intended to be used for a key verification
	 */
	boolean expected();

	/**
	 * <p>
	 * In the case we actually expect input digest to be verified, here actual
	 * digest expectation is supplied.
	 * </p>
	 * <p>
	 * Is not designed to be called if there are no expectations at all. Thus, is
	 * not designed to ever return {@code null}.
	 * </p>
	 */
	byte[] value();

	public static final class None implements DigestExpectation {

		@Override
		public boolean expected() {
			return false;
		}

		@Override
		public byte[] value() {
			throw new UnsupportedOperationException();
		}

	}
}
