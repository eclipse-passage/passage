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
package org.eclipse.passage.lbc.internal.base.chains;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lbc.internal.api.Chain;
import org.eclipse.passage.lbc.internal.api.ProductLicensesRequest;
import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lbc.internal.base.LicenseEObject;
import org.eclipse.passage.lbc.internal.base.LicensesResource;
import org.eclipse.passage.lbc.internal.base.MinedConditionPacks;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

public class MineConditionsChain implements Chain<List<Resource>, ProductLicensesRequest> {

	private List<Resource> resources(ProductLicensesRequest request) {
		return Stream.of(request) //
				.map(new MinedConditionPacks(new BaseLicenseVault())) //
				.flatMap(packs -> packs.stream()) //
				.map(new LicenseEObject()) //
				.map(new LicensesResource()) //
				.collect(Collectors.toList());
	}

	@Override
	public ServiceInvocationResult<List<Resource>> execute(ProductLicensesRequest request) {
		return new BaseServiceInvocationResult<List<Resource>>(resources(request));
	}

}
