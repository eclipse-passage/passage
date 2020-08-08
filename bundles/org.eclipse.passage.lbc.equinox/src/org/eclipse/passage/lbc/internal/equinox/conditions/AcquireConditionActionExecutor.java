/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.internal.base.LicenseEObject;
import org.eclipse.passage.lbc.internal.base.LicensesResource;
import org.eclipse.passage.lbc.internal.base.MinedConditionPacks;
import org.eclipse.passage.lbc.internal.base.ParsedRequest;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.ConditionActions;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.net.LicensingNet;
import org.osgi.service.component.annotations.Component;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.api.access.AccessManager}
 */
@Component(property = LicensingNet.ACTION + '=' + ConditionActions.ACQUIRE)
public class AcquireConditionActionExecutor implements BackendActionExecutor {

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {
		try {
			sendResources(response, resources(request));
			return LicensingResults.createOK(EquinoxMessages.AcquireConditionActionExecutor_k_mined,
					getClass().getName());
		} catch (IOException e) {
			String error = EquinoxMessages.AcquireConditionActionExecutor_e_mining_failed;
			return LicensingResults.createError(error, getClass().getName(), e);
		}
	}

	private void sendResources(HttpServletResponse response, Collection<Resource> resources) throws IOException {
		for (Resource resource : resources) {
			resource.save(response.getOutputStream(), new HashMap<>());
		}
	}

	private List<Resource> resources(HttpServletRequest request) {
		return Stream.of(request) //
				.map(new ParsedRequest()) //
				.map(new MinedConditionPacks()) //
				.flatMap(packs -> packs.stream()) //
				.map(new LicenseEObject()) //
				.map(new LicensesResource()) //
				.collect(Collectors.toList());
	}

}
