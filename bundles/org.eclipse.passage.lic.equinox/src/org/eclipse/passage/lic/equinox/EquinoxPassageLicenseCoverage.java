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
package org.eclipse.passage.lic.equinox;

import java.util.Optional;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.PassageLicenseCoverage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.access.Access;
import org.eclipse.passage.lic.base.access.SumOfCertificates;
import org.eclipse.passage.lic.base.diagnostic.code.NoDataOfType;
import org.eclipse.passage.lic.internal.base.access.Libraries;
import org.eclipse.passage.lic.internal.equinox.access.RegisteredLibraries;

/**
 * @since 2.1
 */
@SuppressWarnings("restriction")
public final class EquinoxPassageLicenseCoverage implements PassageLicenseCoverage {

	private final EquinoxFrameworkAware<?> delegate;

	public EquinoxPassageLicenseCoverage() {
		this(new SuppliedFrameworkAware());
	}

	public EquinoxPassageLicenseCoverage(EquinoxFrameworkAware<?> delegate) {
		this.delegate = delegate;
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assess() {
		return both(owner(), libraries());
	}

	private ServiceInvocationResult<ExaminationCertificate> both(//
			ServiceInvocationResult<ExaminationCertificate> owner, //
			Optional<ServiceInvocationResult<ExaminationCertificate>> libraries) {
		if (!libraries.isPresent()) {
			return owner;
		}
		return new BaseServiceInvocationResult.Sum<>(new SumOfCertificates())//
				.apply(owner, libraries.get());
	}

	private Optional<ServiceInvocationResult<ExaminationCertificate>> libraries() {
		Optional<LicensedProduct> product = delegate.withFramework(Framework::product);
		if (!product.isPresent()) {
			return noOwningProduct();
		}
		return new Libraries(new RegisteredLibraries(), product::get).assess();
	}

	private ServiceInvocationResult<ExaminationCertificate> owner() {
		return delegate.withFrameworkService(framework -> new Access(framework).assess());
	}

	private Optional<ServiceInvocationResult<ExaminationCertificate>> noOwningProduct() {
		return Optional.of(new BaseServiceInvocationResult<>(
				new Trouble(new NoDataOfType(), "License Product definition is absent"))); //$NON-NLS-1$
	}

}
