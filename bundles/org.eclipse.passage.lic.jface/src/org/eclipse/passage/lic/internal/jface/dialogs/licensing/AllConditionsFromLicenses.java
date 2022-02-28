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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.equinox.LicenseReadingServiceRequest;
import org.eclipse.passage.lic.internal.base.access.Libraries;
import org.eclipse.passage.lic.internal.base.conditions.LicenseConditions;

@SuppressWarnings("restriction")
final class AllConditionsFromLicenses implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final List<Path> licenses;
	private final Libraries libraries;
	private final LicenseReadingServiceRequest product;

	AllConditionsFromLicenses(List<Path> licenses, Libraries libraries) {
		this.licenses = licenses;
		this.libraries = libraries;
		this.product = new LicenseReadingServiceRequest();
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> get() {
		return licenses.stream()//
				.map(this::fromLicense)//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<>()))//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<ConditionPack>> fromLicense(Path file) {
		return new LicenseConditions(file, product, libraries).get();
	}

}
