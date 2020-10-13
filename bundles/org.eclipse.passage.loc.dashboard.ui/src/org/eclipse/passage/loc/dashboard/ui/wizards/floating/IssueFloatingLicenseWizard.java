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
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.ComposedPage;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;

public final class IssueFloatingLicenseWizard extends Wizard {

	private final IEclipseContext context;
	private final FloatingDataPack initial;

	private Supplier<Optional<LicensePlanDescriptor>> plan;

	public IssueFloatingLicenseWizard(IEclipseContext context, FloatingDataPack initial) {
		this.context = context;
		this.initial = initial;
		setWindowTitle(IssueLicensePageMessages.IssueFloatingLicenseWizard_title);
	}

	@Override
	public void addPages() {
		ComposedPage page = new ComposedPage("data", context); //$NON-NLS-1$
		plan = page.withLicensePlan(initial.plan());
		addPage(page.get());
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
