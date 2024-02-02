/*******************************************************************************
 * Copyright (c) 2019, 2022 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.function.Supplier;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.described.DescribedPersonalLicense;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("restriction")
public final class IssueLicenseDetailsPage extends TwoPhaseWizardPage {

	private final Supplier<PersonalLicensePackDescriptor> pack;
	private Text info;

	protected IssueLicenseDetailsPage(String name, Supplier<PersonalLicensePackDescriptor> pack) {
		super(name);
		this.pack = pack;
		setTitle(IssueLicensePageMessages.IssueLicenseDetailsPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicenseDetailsPage_page_description);
	}

	// TODO: into to Tab; + one Tab for each agreement
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		composite.setLayout(new GridLayout());
		this.info = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		this.info.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		setControl(composite);
		Dialog.applyDialogFont(composite);
	}

	@Override
	void init() {
		info.setText(new DescribedPersonalLicense(pack.get()).get());
	}

}
