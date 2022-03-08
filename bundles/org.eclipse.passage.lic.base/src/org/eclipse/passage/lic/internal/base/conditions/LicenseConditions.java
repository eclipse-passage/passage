/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.conditions;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult.Sum;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.access.Libraries;

/**
 * <p>
 * Reads all licensing {@linkplain Condition}s from given license file.
 * </p>
 * <p>
 * A license file can belong either to a product under licensing, or to any
 * library it exploits. Only appropriate component can read it's licenses.
 * </p>
 * <p>
 * To be used strictly outside of Access Cycle.
 * </p>
 */
public final class LicenseConditions implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final Path file;
	private final Supplier<ServiceInvocationResult<LicenseReadingService>> owner;
	private final Libraries libraries;

	public LicenseConditions(//
			Path file, //
			Supplier<ServiceInvocationResult<LicenseReadingService>> provider, //
			Libraries libraries) {
		this.file = file;
		this.owner = provider;
		this.libraries = libraries;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> get() {
		return new CalmedDown(sum().apply(fromProduct(), fromLibraries())).get();
	}

	private ServiceInvocationResult<Collection<ConditionPack>> fromProduct() {
		ServiceInvocationResult<LicenseReadingService> reader = owner.get();
		if (!reader.data().isPresent()) {
			return new BaseServiceInvocationResult<>(reader.diagnostic());
		}
		return reader.data().get().read(file);
	}

	private ServiceInvocationResult<Collection<ConditionPack>> fromLibraries() {
		Optional<ServiceInvocationResult<List<LicenseReadingService>>> request = libraries.licenseReadingServices();
		if (!request.isPresent()) {
			return new BaseServiceInvocationResult<>(Collections.emptyList());
		}
		ServiceInvocationResult<List<LicenseReadingService>> services = request.get();
		if (!services.data().isPresent()) {
			return new BaseServiceInvocationResult<>(services.diagnostic());
		}
		return services.data().get().stream()//
				.map(service -> service.read(file))//
				.reduce(sum())//
				.get(); // no library case is checked
	}

	private Sum<Collection<ConditionPack>> sum() {
		return new Sum<Collection<ConditionPack>>(new SumOfCollections<>());
	}

}
