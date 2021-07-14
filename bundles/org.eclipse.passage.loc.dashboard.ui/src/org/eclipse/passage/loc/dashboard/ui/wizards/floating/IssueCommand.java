/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;

final class IssueCommand {

	private final IEclipseContext context;
	private final FloatingLicensePack pack;
	private final ServerConfigsRequest config;

	IssueCommand(IEclipseContext context, FloatingLicensePack pack, ServerConfigsRequest config) {
		this.context = context;
		this.pack = pack;
		this.config = config;
	}

	ServiceInvocationResult<IssuedFloatingLicense> issue() {
		List<FloatingLicenseAccess> personals = personalAccessConfigs();
		return context.get(OperatorLicenseService.class).issueFloatingLicensePack(pack, personals);
	}

	private List<FloatingLicenseAccess> personalAccessConfigs() {
		if (!config.generate()) {
			return Collections.emptyList();
		}
		return pack.getUsers().stream()//
				.map(UserGrant::getUser) //
				.map(this::personalAccess)//
				.collect(Collectors.toList());
	}

	private FloatingLicenseAccess personalAccess(String user) {
		FloatingLicenseAccess access = LicensesFactory.eINSTANCE.createFloatingLicenseAccess();
		access.setUser(user);
		access.setOriginLicensePack(pack.getLicense().getIdentifier());
		access.setServer(server());
		return access;
	}

	private FloatingServerConnection server() {
		FloatingServerConnection connection = LicensesFactory.eINSTANCE.createFloatingServerConnection();
		connection.setIp(config.ip());
		connection.setPort(config.port());
		connection.setAuthentication(EcoreUtil.copy(pack.getHost().getAuthentication()));
		return connection;
	}

}
