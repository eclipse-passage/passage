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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.access.SumOfCertificates;

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

	public Optional<AgreementAcceptanceService> agreementsService(AgreementToAccept agreement) {
		if (empty()) {
			return Optional.empty();
		}
		return libraries.stream()//
				.map(library -> library.agreementsService(agreement))//
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.findAny();
	}

	public Collection<Condition> conditions(Path license) {
		// TODO Auto-generated method stub
		return null;
	}

	public void installLicense(Path license) throws IOException {
		// TODO Auto-generated method stub

	}

}
