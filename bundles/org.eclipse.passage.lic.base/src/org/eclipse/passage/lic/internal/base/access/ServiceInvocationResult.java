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
package org.eclipse.passage.lic.internal.base.access;

import java.util.function.BinaryOperator;

public abstract class ServiceInvocationResult<T> {

	private final boolean success;

	ServiceInvocationResult(boolean success) {
		this.success = success;
	}

	public final boolean successful() {
		return success;
	}

	public abstract T data();

	static final class Error<T> extends ServiceInvocationResult<T> {

		Error() {
			super(false);
		}

		@Override
		public T data() {
			throw new UnsupportedOperationException();
		}

	}

	static final class Success<T> extends ServiceInvocationResult<T> {

		private final T data;

		Success(T data) {
			super(true);
			this.data = data;
		}

		@Override
		public T data() {
			return data;
		}

	}

	public static final class Sum<T> implements BinaryOperator<ServiceInvocationResult<T>> {

		private final BinaryOperator<T> sum;

		public Sum(BinaryOperator<T> sum) {
			this.sum = sum;
		}

		@Override
		public ServiceInvocationResult<T> apply(ServiceInvocationResult<T> first, ServiceInvocationResult<T> second) {
			if (!first.success || !second.success) {
				return new Error<T>();
			}
			return new Success<T>(sum.apply(first.data(), second.data()));
		}

	}
}
