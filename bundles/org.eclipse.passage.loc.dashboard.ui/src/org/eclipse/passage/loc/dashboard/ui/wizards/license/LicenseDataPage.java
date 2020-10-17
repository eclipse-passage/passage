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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.List;
import java.util.Optional;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public final class LicenseDataPage extends WizardPage {

	private final List<Field<?>> units;

	protected LicenseDataPage(String name, List<Field<?>> units) {
		super(name);
		this.units = units;
		setTitle(IssueLicensePageMessages.IssueLicenseRequestPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicenseRequestPage_page_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = container(parent);
		setControl(container);
		units.forEach(unit -> unit.installControll(container));
		Dialog.applyDialogFont(container);
	}

	private Composite container(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		container.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		return container;
	}

	void validate() {
		setPageComplete(validatePage());
	}

	private boolean validatePage() {
		setMessage("", WizardPage.NONE); //$NON-NLS-1$
		for (Field<?> unit : units) {
			Optional<String> error = unit.error();
			if (error.isPresent()) {
				setMessage(error.get(), WizardPage.ERROR);
				return false;
			}
		}
		return true;
	}

}
