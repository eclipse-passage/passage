/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.ComposedPage;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.PageFields;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.SwitchableFields;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;

public final class IssueUserConfigsRequestPage implements Supplier<IWizardPage> {

	private final ComposedPage page;
	private final Supplier<Optional<Boolean>> generate;
	private final Supplier<Optional<String>> ip;
	private final Supplier<Optional<Integer>> port;

	IssueUserConfigsRequestPage(IEclipseContext context) {
		page = new ComposedPage(//
				IssueUserConfigsRequestPage.class.getSimpleName(), //
				IssueLicensePageMessages.IssueUserConfigsRequestPage_page_description, //
				context);
		PageFields server = page.withBlock();
		port = server.withPort();
		ip = server.withIp();
		SwitchableFields switcher = page.withSwitchableBlock(
				IssueLicensePageMessages.IssueLicenseRequestPage_lbl_generate_floating_configs, //
				true, //
				server);
		generate = switcher.switcher();
	}

	ServerConfigsRequest request() {
		return new ServerConfigsRequest.Of(generate, ip, port).get();
	}

	@Override
	public IWizardPage get() {
		return page.get();
	}

}
