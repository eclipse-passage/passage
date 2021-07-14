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
package org.eclipse.passage.lic.base;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.SumOfDiagnostics;

/**
 * 
 * @author afedorov3un
 *
 * @param <T>
 * 
 * @since 2.1
 */
public final class BaseServiceInvocationResult<T> implements ServiceInvocationResult<T> {

	private final Diagnostic diagnostic;
	private final Optional<T> data;

	public BaseServiceInvocationResult(Trouble severe) {
		this(new BaseDiagnostic(severe), Optional.empty());
	}

	public BaseServiceInvocationResult(T data) {
		this(new BaseDiagnostic(), Optional.of(data));
	}

	public BaseServiceInvocationResult(Diagnostic diagnostic) {
		this(diagnostic, Optional.empty());
	}

	public BaseServiceInvocationResult(Diagnostic diagnostic, T data) {
		this(diagnostic, Optional.of(data));
	}

	public BaseServiceInvocationResult(Diagnostic diagnostic, Optional<T> data) {
		Objects.requireNonNull(diagnostic, "BaseServiceInvocationResult:diagnostic"); //$NON-NLS-1$
		Objects.requireNonNull(data, "BaseServiceInvocationResult:data"); //$NON-NLS-1$
		this.diagnostic = diagnostic;
		this.data = data;
	}

	@Override
	public Diagnostic diagnostic() {
		return diagnostic;
	}

	@Override
	public Optional<T> data() {
		return data;
	}

	// FIXME: pull it up
	public static final class Sum<T> implements BinaryOperator<ServiceInvocationResult<T>> {

		private final BinaryOperator<T> sum;

		public Sum(BinaryOperator<T> sum) {
			this.sum = sum;
		}

		@Override
		public ServiceInvocationResult<T> apply(ServiceInvocationResult<T> first, ServiceInvocationResult<T> second) {
			return new BaseServiceInvocationResult<T>(//
					new SumOfDiagnostics().apply(first.diagnostic(), second.diagnostic()), //
					sum(first.data(), second.data())//
			);
		}

		private Optional<T> sum(Optional<T> first, Optional<T> second) {
			if (first.isPresent() && second.isPresent()) {
				return Optional.of(sum.apply(first.get(), second.get()));
			}
			if (first.isPresent()) {
				return first;
			}

			if (second.isPresent()) {
				return second;
			}
			return Optional.empty();
		}

	}
}
