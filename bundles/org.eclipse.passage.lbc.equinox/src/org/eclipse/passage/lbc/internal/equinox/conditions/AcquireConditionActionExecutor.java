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
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.internal.base.BaseLicensingRequest;
import org.eclipse.passage.lbc.internal.base.BaseServerConfiguration;
import org.eclipse.passage.lbc.internal.base.ParsedRequest;
import org.eclipse.passage.lbc.internal.base.chains.Mine;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.osgi.service.component.annotations.Component;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.api.access.AccessManager}
 */
@Component(property = "action" + '=' + "acquire")
public class AcquireConditionActionExecutor implements BackendActionExecutor {

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {
		try {
			send(response, loadConditions(request));
			return LicensingResults.createOK(EquinoxMessages.AcquireConditionActionExecutor_k_mined,
					getClass().getName());
		} catch (IOException e) {
			String error = EquinoxMessages.AcquireConditionActionExecutor_e_mining_failed;
			return LicensingResults.createError(error, getClass().getName(), e);
		}
	}

	private List<Resource> loadConditions(HttpServletRequest request) {
		return new Mine(BaseServerConfiguration.empty())
				.apply(new ParsedRequest().apply(new BaseLicensingRequest(request))).data().get();
	}

	private void send(HttpServletResponse response, List<Resource> conditions) throws IOException {
		for (Resource resource : conditions) {
			resource.save(response.getOutputStream(), new HashMap<>());
		}
	}

}
