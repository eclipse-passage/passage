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
package org.eclipse.passage.lbc.internal.base.tobemoved;

import java.io.OutputStream;

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;

abstract class Failure implements FloatingResponse {

	private final int code;
	private final String message;

	protected Failure(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public boolean failed() {
		return true;
	}

	@Override
	public Error error() {
		return new Err();
	}

	@Override
	public void write(OutputStream output) {
		throw new IllegalStateException("Is not intended to be called for failed response: no valid output"); //$NON-NLS-1$ dev
	}

	private final class Err implements Error {

		@Override
		public int code() {
			return code;
		}

		@Override
		public String message() {
			return message;
		}

	}

	static final class BadRequestNoAction extends Failure {

		BadRequestNoAction() {
			super(601, "Bad Requets: no 'action' is defined"); //$NON-NLS-1$
		}

	}

}
