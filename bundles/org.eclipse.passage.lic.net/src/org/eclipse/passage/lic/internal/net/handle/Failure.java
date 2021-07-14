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
package org.eclipse.passage.lic.internal.net.handle;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;

public abstract class Failure implements NetResponse {

	private final int code;
	private final String message;

	protected Failure(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public final boolean failed() {
		return true;
	}

	@Override
	public final boolean carriesPayload() {
		return false;
	}

	@Override
	public final Error error() {
		return new Err();
	}

	@Override
	public final byte[] payload() {
		throw new IllegalStateException("Is not intended to be called for failed response: no valid output"); //$NON-NLS-1$ dev
	}

	@Override
	public final ContentType contentType() {
		return new ContentType.Xml();
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

	public static final class ForeignServer extends Failure {

		public ForeignServer(String error) {
			super(600, String.format("Server authentication failed: %s", error)); //$NON-NLS-1$
		}

	}

	public static final class BadRequestInvalidServerAuthInstructions extends Failure {

		public BadRequestInvalidServerAuthInstructions() {
			super(602,
					"Bad Requets: floating server authentication instructions (evaluation type and exrpressions) are absent or incomplete"); //$NON-NLS-1$
		}

	}

	public static final class BadRequestUnknownAction extends Failure {

		public BadRequestUnknownAction(String actual) {
			super(603, String.format("Bad Requets: 'action' %s is not supported", actual)); //$NON-NLS-1$
		}

	}

	public static final class BadRequestInvalidProduct extends Failure {

		public BadRequestInvalidProduct() {
			super(604, "Bad Request: licensed product identifier and/or version information is absent"); //$NON-NLS-1$
		}

	}

	public static final class BadRequestNoUser extends Failure {

		public BadRequestNoUser() {
			super(605, "Bad Request: no user identifier"); //$NON-NLS-1$
		}

	}

	public static final class BadRequestNoAlgo extends Failure {

		public BadRequestNoAlgo() {
			super(606, "Bad Request: no encoding algorithm identifier"); //$NON-NLS-1$
		}

	}

	public static final class BadRequestNoFeature extends Failure {

		public BadRequestNoFeature() {
			super(607, "Bad Request: no feature identifier"); //$NON-NLS-1$
		}

	}

	public static final class BadRequestUnkonwnProduct extends Failure {

		public BadRequestUnkonwnProduct(LicensedProduct product) {
			super(608, String.format("Bad Request: product %s is not known to the server", product)); //$NON-NLS-1$
		}

	}

	public static final class OperationFailed extends Failure {

		public OperationFailed(String name, String details) {
			super(610, String.format("Operation %s failed: \n%s", name, details)); //$NON-NLS-1$
		}

	}

}
