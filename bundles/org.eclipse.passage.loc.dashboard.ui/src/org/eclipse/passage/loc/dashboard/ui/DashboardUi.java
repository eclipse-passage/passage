/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.internal.e4.core.commands.ExecuteCommand;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.agreements.ui.AgreementsUi;
import org.eclipse.passage.loc.features.ui.FeaturesUi;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.eclipse.passage.loc.licenses.ui.LicensesUi;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.users.ui.UsersUi;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class DashboardUi {

	public static final String COMMAND_CREATE = "org.eclipse.passage.loc.dashboard.ui.command.create"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_CREATE_DOMAIN = "org.eclipse.passage.loc.dashboard.ui.commandparameter.create.domain"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_CREATE_PERSPECTIVE = "org.eclipse.passage.loc.dashboard.ui.commandparameter.create.perspective"; //$NON-NLS-1$

	public static final String COMMAND_LOAD = "org.eclipse.passage.loc.dashboard.ui.command.load"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_LOAD_DOMAIN = "org.eclipse.passage.loc.dashboard.ui.commandparameter.load.domain"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_LOAD_PERSPECTIVE = "org.eclipse.passage.loc.dashboard.ui.commandparameter.load.perspective"; //$NON-NLS-1$

	public static final String COMMAND_SHOW = "org.eclipse.passage.loc.dashboard.ui.command.show"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_SHOW_DOMAIN = "org.eclipse.passage.loc.dashboard.ui.commandparameter.show.domain"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_SHOW_CLASSIFIER = "org.eclipse.passage.loc.dashboard.ui.commandparameter.show.classifier"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_SHOW_PERSPECTIVE = "org.eclipse.passage.loc.dashboard.ui.commandparameter.show.perspective"; //$NON-NLS-1$

	public static final String COMMAND_ISSUE_LICENSE = "org.eclipse.passage.loc.dashboard.ui.command.issue.license"; //$NON-NLS-1$
	public static final String COMMAND_ISSUE_FLOATING_LICENSE = "org.eclipse.passage.loc.dashboard.ui.command.issue.floatinglicense"; //$NON-NLS-1$

	public static void showDetails(IEclipseContext context, String domain, String classifier, String perspectiveId) {
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
		EditingDomainRegistryAccess registryAccess = context.get(EditingDomainRegistryAccess.class);
		SelectionCommandAdvisor advisor = registryAccess.getSelectionCommandAdvisor(domain);
		if (advisor != null) {
			return advisor.getSelectionInput(classifier);
		}
		return Collections.emptyList();
	}

	private static String resolveTitle(IEclipseContext context, String domain, String classifier) {
		EditingDomainRegistryAccess registryAccess = context.get(EditingDomainRegistryAccess.class);
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
		case AgreementsPackage.eNAME:
			return AgreementsUi.PERSPECTIVE_MAIN;
		case FeaturesPackage.eNAME:
			return FeaturesUi.PERSPECTIVE_MAIN;
		case ProductsPackage.eNAME:
			return ProductsUi.PERSPECTIVE_MAIN;
		case UsersPackage.eNAME:
			return UsersUi.PERSPECTIVE_MAIN;
		case LicensesPackage.eNAME:
			return LicensesUi.PERSPECTIVE_MAIN;
		default:
			break;
		}
		return null;
	}

	public static void executeCreateCommand(IEclipseContext context, String domain) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(COMMANDPARAMETER_CREATE_DOMAIN, domain);
		String perspectiveId = resolvePerspectiveId(domain);
		if (perspectiveId != null) {
			parameters.put(COMMANDPARAMETER_CREATE_PERSPECTIVE, perspectiveId);
		}
		new ExecuteCommand(COMMAND_CREATE, context).apply(parameters);
	}

	public static void executeLoadCommand(IEclipseContext context, String domain) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(COMMANDPARAMETER_LOAD_DOMAIN, domain);
		String perspectiveId = resolvePerspectiveId(domain);
		if (perspectiveId != null) {
			parameters.put(COMMANDPARAMETER_LOAD_PERSPECTIVE, perspectiveId);
		}
		new ExecuteCommand(COMMAND_LOAD, context).apply(parameters);
	}

	public static void executeShowCommand(IEclipseContext context, String domain, String classifier) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(COMMANDPARAMETER_SHOW_DOMAIN, domain);
		parameters.put(COMMANDPARAMETER_SHOW_CLASSIFIER, classifier);
		String perspectiveId = resolvePerspectiveId(domain);
		if (perspectiveId != null) {
			parameters.put(COMMANDPARAMETER_SHOW_PERSPECTIVE, perspectiveId);
		}
		new ExecuteCommand(COMMAND_SHOW, context).apply(parameters);
	}

}
