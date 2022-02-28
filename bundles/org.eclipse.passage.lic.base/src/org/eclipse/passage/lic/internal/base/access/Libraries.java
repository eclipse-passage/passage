/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult.Sum;
import org.eclipse.passage.lic.base.access.SumOfCertificates;
import org.eclipse.passage.lic.base.diagnostic.SumOfLists;

public final class Libraries {

	private final List<DelegatedLicensingService> libraries;
	private final Supplier<LicensedProduct> owner;

	public Libraries(Supplier<List<DelegatedLicensingService>> libraries, Supplier<LicensedProduct> owner) {
		this(libraries.get(), owner);
	}

	public Libraries(List<DelegatedLicensingService> libraries, Supplier<LicensedProduct> owner) {
		this.libraries = libraries;
		this.owner = owner;
	}

	public boolean empty() {
		return libraries.isEmpty();
	}

	public LicensedProduct product() {
		return owner.get();
	}

	public Optional<ServiceInvocationResult<ExaminationCertificate>> assess() {
		return libraries.stream()//
				.map(DelegatedLicensingService::assess)
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCertificates()));
	}

	public Optional<ServiceInvocationResult<List<AgreementAcceptanceService>>> agreementsServices(
			AgreementToAccept agreement) {
		return libraries.stream()//
				.map(library -> library.agreementsService(agreement))//
				.map(this::enlisted)//
				.reduce(sum());
	}

	public Optional<ServiceInvocationResult<List<LicenseReadingService>>> licenseReadingServices() {
		return libraries.stream()//
				.map(DelegatedLicensingService::licenseReadingService)//
				.map(this::enlisted)//
				.reduce(sum());
	}

	public void installLicense(Path license) throws IOException {
		// TODO Auto-generated method stub

	}

	private <T> ServiceInvocationResult<List<T>> enlisted(ServiceInvocationResult<T> origin) {
		List<T> data = origin.data().isPresent() ? Arrays.asList(origin.data().get()) : Collections.emptyList();
		return new BaseServiceInvocationResult<List<T>>(origin.diagnostic(), data);
	}

	private <T> Sum<List<T>> sum() {
		return new Sum<List<T>>(new SumOfLists<T>());
	}

}
