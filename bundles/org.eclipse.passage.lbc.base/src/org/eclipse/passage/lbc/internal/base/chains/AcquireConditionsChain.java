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

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lbc.internal.api.Chain;
import org.eclipse.passage.lbc.internal.api.LicensingRequest;
import org.eclipse.passage.lbc.internal.api.LicensingResponse;
import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lbc.internal.base.LicenseEObject;
import org.eclipse.passage.lbc.internal.base.LicensesResource;
import org.eclipse.passage.lbc.internal.base.MinedConditionPacks;
import org.eclipse.passage.lbc.internal.base.ParsedRequest;

public class AcquireConditionsChain implements Chain {

	@Override
	public void execute(LicensingRequest request, LicensingResponse response) throws IOException {
		sendResources(response, resources(request));
	}

	@SuppressWarnings("resource")
	private void sendResources(LicensingResponse response, Collection<Resource> resources) throws IOException {
		for (Resource resource : resources) {
			resource.save(response.outputStream(), new HashMap<>());
		}
	}

	private List<Resource> resources(LicensingRequest request) {
		return Stream.of(request) //
				.map(new ParsedRequest()) //
				.map(new MinedConditionPacks(new BaseLicenseVault())) //
				.flatMap(packs -> packs.stream()) //
				.map(new LicenseEObject()) //
				.map(new LicensesResource()) //
				.collect(Collectors.toList());
	}

}
