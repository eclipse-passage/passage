/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.Optional;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.net.mail.LicensingMail;
import org.eclipse.passage.lic.net.mail.api.LicensingMails;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.core.LicenseMailSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class IssueLicenseDetailsPage extends WizardPage {

	private Text text;
	private boolean createMail;
	private boolean createEml;
	private LicenseMailSupport licenseMailSupport;

	protected IssueLicenseDetailsPage(String pageName) {
		super(pageName);
		setTitle(IssueLicensePageMessages.IssueLicenseDetailsPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicenseDetailsPage_page_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		composite.setLayout(new GridLayout());
		text = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Group groupButtons = new Group(composite, SWT.NONE);
		groupButtons.setText(IssueLicensePageMessages.IssueLicenseDetailsPage_group_mail_text);
		groupButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		groupButtons.setLayout(new GridLayout(1, false));

		Button buttonPrepareMail = new Button(composite, SWT.PUSH);
		buttonPrepareMail.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		buttonPrepareMail.setText(IssueLicensePageMessages.IssueLicenseDetailsPage_btn_mail_text);
		buttonPrepareMail.setSelection(true);
		buttonPrepareMail.addSelectionListener(
				SelectionListener.widgetSelectedAdapter(c -> createMail = buttonPrepareMail.getSelection()));
		createMail = buttonPrepareMail.getSelection();
		Optional<LicensingMail> optLicensingEmlService = LicensingMails.getLicensingEmlService();
		if (optLicensingEmlService.isPresent()) {
			Button buttonPrepareEml = new Button(groupButtons, SWT.CHECK);
			buttonPrepareEml.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			buttonPrepareEml.setText(IssueLicensePageMessages.IssueLicenseDetailsPage_btn_eml_text);
			buttonPrepareEml.addSelectionListener(
					SelectionListener.widgetSelectedAdapter(c -> createEml = buttonPrepareEml.getSelection()));
			createEml = buttonPrepareEml.getSelection();
		}
		setControl(composite);
		Dialog.applyDialogFont(composite);
	}

	public void init(LicensePackDescriptor licensePack) {
		this.licenseMailSupport = new LicenseMailSupport(licensePack);
		if (text != null && !text.isDisposed()) {
			text.setText(licenseMailSupport.getDetails());
		}
	}

	public boolean isCreateMail() {
		return createMail;
	}

	public boolean isCreateEml() {
		return createEml;
	}

	public LicenseMailSupport getMailSupport() {
		return licenseMailSupport;
	}
}
