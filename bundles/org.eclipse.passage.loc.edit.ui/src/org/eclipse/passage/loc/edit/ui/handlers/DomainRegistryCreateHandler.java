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
package org.eclipse.passage.loc.edit.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.passage.loc.features.core.Features;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.eclipse.passage.loc.products.core.Products;
import org.eclipse.passage.loc.users.core.Users;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class DomainRegistryCreateHandler {

	private static final String PERSPECTIVE_DASHBOARD_ID = "org.eclipse.passage.loc.dashboard.perspective.main"; //$NON-NLS-1$
	private static final String REGISTRY_RESOURCE_CREATE = "org.eclipse.passage.loc.edit.ui.commandparameter.domain.resource.create"; //$NON-NLS-1$
	private static final String REGISTRY_RESOURCE_CREATE_FEATURE = REGISTRY_RESOURCE_CREATE + ".feature"; //$NON-NLS-1$
	private static final String REGISTRY_RESOURCE_CREATE_PRODUCT = REGISTRY_RESOURCE_CREATE + ".product"; //$NON-NLS-1$
	private static final String REGISTRY_RESOURCE_CREATE_USER = REGISTRY_RESOURCE_CREATE + ".user"; //$NON-NLS-1$
	private static final String REGISTRY_RESOURCE_CREATE_LICENSE = REGISTRY_RESOURCE_CREATE + ".license"; //$NON-NLS-1$

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) IEclipseContext context,
			@Named(REGISTRY_RESOURCE_CREATE) String domainRegistryId) {

		String domain = null;
		switch (domainRegistryId) {
		case REGISTRY_RESOURCE_CREATE_FEATURE:
			domain = Features.DOMAIN_NAME;
			break;
		case REGISTRY_RESOURCE_CREATE_PRODUCT:
			domain = Products.DOMAIN_NAME;
			break;
		case REGISTRY_RESOURCE_CREATE_USER:
			domain = Users.DOMAIN_NAME;
			break;
		case REGISTRY_RESOURCE_CREATE_LICENSE:
			domain = Licenses.DOMAIN_NAME;
			break;
		default:
		}

		if (domain != null) {
			LocWokbench.createDomainResource(context, domain, PERSPECTIVE_DASHBOARD_ID);
		}
	}
}
