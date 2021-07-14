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
package org.eclipse.passage.lic.base.io;

import java.util.Objects;

import org.eclipse.passage.lic.api.io.DigestExpectation;

/**
 * @since 2.1
 */
public final class BaseDigestExpectation implements DigestExpectation {

	private final byte[] digest;

	public BaseDigestExpectation(byte[] digest) {
		Objects.requireNonNull(digest, "BaseDigestExpectation::digest. Use None if there is no actual expectations."); //$NON-NLS-1$
		this.digest = digest;
	}

	@Override
	public boolean expected() {
		return true;
	}

	@Override
	public byte[] value() {
		return digest;
	}

}
