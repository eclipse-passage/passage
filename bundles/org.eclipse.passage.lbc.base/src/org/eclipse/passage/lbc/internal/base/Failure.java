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
package org.eclipse.passage.lbc.internal.base;

import java.io.OutputStream;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.LicensedProduct;

public abstract class Failure implements FloatingResponse {

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

	static final class BadRequestUnknownAction extends Failure {

		BadRequestUnknownAction(String actual) {
			super(601, String.format("Bad Requets: 'action' %s is not supported", actual)); //$NON-NLS-1$
		}

	}

	static final class BadRequestInvalidProduct extends Failure {

		BadRequestInvalidProduct() {
			super(602, "Bad Request: licensed product identifier and/or version information is absent"); //$NON-NLS-1$
		}

	}

	static final class BadRequestNoUser extends Failure {

		BadRequestNoUser() {
			super(603, "Bad Request: no user identifier"); //$NON-NLS-1$
		}

	}

	public static final class BadRequestNoFeature extends Failure {

		public BadRequestNoFeature() {
			super(604, "Bad Request: no feature identifier"); //$NON-NLS-1$
		}

	}

	public static final class OperationFailed extends Failure {

		public OperationFailed(String name, String details) {
			super(610, String.format("Operation %s failed: \n%s", name, details)); //$NON-NLS-1$
		}

	}

	public static final class NoGrantsAvailable extends Failure {

		public NoGrantsAvailable(LicensedProduct product, String feature) {
			super(611, String.format("No license grants available for feature %s of product %s", feature, product)); //$NON-NLS-1$
		}

	}

	public static final class NotReleased extends Failure {

		public NotReleased(LicensedProduct product, GrantAcqisition acqisition) {
			super(611, String.format("Failed to release grant %s acquisition for feature %s of product %s", //$NON-NLS-1$
					acqisition.getGrant(), acqisition.getFeature(), product));
		}

	}
}
