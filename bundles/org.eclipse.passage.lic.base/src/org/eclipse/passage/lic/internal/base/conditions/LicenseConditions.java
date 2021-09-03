/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;

public final class LicenseConditions implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final Path file;
	private final Supplier<ServiceInvocationResult<LicenseReadingService>> provider;

	public LicenseConditions(Path file, Supplier<ServiceInvocationResult<LicenseReadingService>> provider) {
		this.file = file;
		this.provider = provider;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> get() {
		ServiceInvocationResult<LicenseReadingService> reader = provider.get();
		if (!reader.data().isPresent()) {
			return new BaseServiceInvocationResult<>(reader.diagnostic());
		}
		return reader.data().get().read(file);
	}

}
