/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.dashboard.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.SelectionCommandAdvisor;
import org.eclipse.passage.lic.features.Features;
import org.eclipse.passage.lic.licenses.Licenses;
import org.eclipse.passage.lic.products.Products;
import org.eclipse.passage.lic.users.Users;
import org.eclipse.passage.loc.features.ui.FeaturesUi;
import org.eclipse.passage.loc.licenses.ui.LicensesUi;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.users.ui.UsersUi;
import org.eclipse.passage.loc.workbench.LocWokbench;

@SuppressWarnings("restriction")
public class DashboardUi {

	public static final String COMMAND_CREATE = "org.eclipse.passage.loc.dashboard.ui.command.create"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_CREATE_DOMAIN = "org.eclipse.passage.loc.dashboard.ui.commandparameter.create.domain"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_CREATE_PERSPECTIVE = "org.eclipse.passage.loc.dashboard.ui.commandparameter.create.perspective"; //$NON-NLS-1$

	public static final String COMMAND_LOAD = "org.eclipse.passage.loc.dashboard.ui.command.load"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_LOAD_DOMAIN = "org.eclipse.passage.loc.dashboard.ui.commandparameter.load.domain"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_LOAD_PERSPECTIVE = "org.eclipse.passage.loc.dashboard.ui.commandparameter.load.perspective"; //$NON-NLS-1$

	public static final String COMMAND_EDIT = "org.eclipse.passage.loc.dashboard.ui.command.edit"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_EDIT_DOMAIN = "org.eclipse.passage.loc.dashboard.ui.commandparameter.edit.domain"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_EDIT_CLASSIFIER = "org.eclipse.passage.loc.dashboard.ui.commandparameter.edit.classifier"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_EDIT_PERSPECTIVE = "org.eclipse.passage.loc.dashboard.ui.commandparameter.edit.perspective"; //$NON-NLS-1$

	public static Object executeCommand(IEclipseContext context, String commandId, String parameterId, String parameterValue) {
		Map<Object, Object> parameters = new HashMap<>();
		parameters.put(parameterId, parameterValue);
		return executeCommand(context, commandId, parameters);
	}

	public static Object executeCommand(IEclipseContext context, String commandId, Map<Object, Object> parameters) {
		ECommandService commandService = context.get(ECommandService.class);
		Command command = commandService.getCommand(commandId);
		ParameterizedCommand parametrizedCommand = ParameterizedCommand.generateCommand(command, parameters );
		EHandlerService eHandlerService = context.get(EHandlerService.class);
		return eHandlerService.executeHandler(parametrizedCommand);
	}

	public static void editDomainResource(IEclipseContext context, String domain, String classifier,
			String perspectiveId) {
		Iterable<?> input = resolveInput(context, domain, classifier);
		String title = resolveTitle(context, domain, classifier);
		Object selectedClassifier = LocWokbench.selectClassifier(context, classifier, title, input, null);
		if (selectedClassifier != null) {
			LocWokbench.switchPerspective(context, perspectiveId);
			IEventBroker broker = context.get(IEventBroker.class);
			broker.post(LocWokbench.TOPIC_SHOW, selectedClassifier);
		}
	}

	private static Iterable<?> resolveInput(IEclipseContext context, String domain, String classifier) {
		DomainRegistryAccess registryAccess = context.get(DomainRegistryAccess.class);
		SelectionCommandAdvisor advisor = registryAccess.getSelectionCommandAdvisor(domain);
		if (advisor != null) {
			return advisor.getSelectionInput(classifier);
		}
		return Collections.emptyList();
	}

	private static String resolveTitle(IEclipseContext context, String domain, String classifier) {
		DomainRegistryAccess registryAccess = context.get(DomainRegistryAccess.class);
		SelectionCommandAdvisor advisor = registryAccess.getSelectionCommandAdvisor(domain);
		if (advisor != null) {
			return advisor.getSelectionTitle(classifier);
		}
		return null;
	}

	public static String resolvePerspectiveId(String domain) {
		if (domain == null) {
			return null;
		}
		switch (domain) {
		case Features.DOMAIN_NAME:
			return FeaturesUi.PERSPECTIVE_MAIN;
		case Products.DOMAIN_NAME:
			return ProductsUi.PERSPECTIVE_MAIN;
		case Users.DOMAIN_NAME:
			return UsersUi.PERSPECTIVE_MAIN;
		case Licenses.DOMAIN_NAME:
			return LicensesUi.PERSPECTIVE_MAIN;
		default:
			break;
		}
		return null;
	}

	public static void executeCreateCommand(IEclipseContext context, String domain) {
		Map<Object, Object> parameters = new HashMap<>();
		parameters.put(COMMANDPARAMETER_CREATE_DOMAIN, domain);
		String perspectiveId = resolvePerspectiveId(domain);
		if (perspectiveId != null) {
			parameters.put(COMMANDPARAMETER_CREATE_PERSPECTIVE, perspectiveId);
		}
		executeCommand(context, COMMAND_CREATE, parameters);
	}

	public static void executeLoadCommand(IEclipseContext context, String domain) {
		Map<Object, Object> parameters = new HashMap<>();
		parameters.put(COMMANDPARAMETER_LOAD_DOMAIN, domain);
		String perspectiveId = resolvePerspectiveId(domain);
		if (perspectiveId != null) {
			parameters.put(COMMANDPARAMETER_LOAD_PERSPECTIVE, perspectiveId);
		}
		executeCommand(context, COMMAND_LOAD, parameters);
	}

	public static void executeEditCommand(IEclipseContext context, String domain, String classifier) {
		Map<Object, Object> parameters = new HashMap<>();
		parameters.put(COMMANDPARAMETER_EDIT_DOMAIN, domain);
		parameters.put(COMMANDPARAMETER_EDIT_CLASSIFIER, classifier);
		String perspectiveId = resolvePerspectiveId(domain);
		if (perspectiveId != null) {
			parameters.put(COMMANDPARAMETER_EDIT_PERSPECTIVE, perspectiveId);
		}
		executeCommand(context, COMMAND_EDIT, parameters);
	}

}
