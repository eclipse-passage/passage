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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.email.Mailing;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.core.EmailTemplate;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class IssueLicenseDetailsPage extends WizardPage {

	private final IEclipseContext context;
	private final Supplier<PersonalLicensePackDescriptor> data;
	private Text info;
	private Text from;
	private String mailFrom = ""; //$NON-NLS-1$

	protected IssueLicenseDetailsPage(String pageName, Supplier<PersonalLicensePackDescriptor> data, IEclipseContext context) {
		super(pageName);
		this.context = context;
		this.data = data;
		setTitle(IssueLicensePageMessages.IssueLicenseDetailsPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicenseDetailsPage_page_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		composite.setLayout(new GridLayout());
		info = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		info.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Optional.ofNullable(context//
				.get(Mailing.class))//
				.ifPresent(m -> createEmlButton(composite));
		setControl(composite);
		Dialog.applyDialogFont(composite);
	}

	private void createEmlButton(Composite parent) {
		Group prepareEml = new Group(parent, SWT.CHECK);
		prepareEml.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		prepareEml.setText(IssueLicensePageMessages.IssueLicenseDetailsPage_lbl_eml_text);
		prepareEml.setLayout(new GridLayout());
		from = new Text(prepareEml, SWT.NONE);
		from.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		from.addModifyListener(e -> mailFrom = from.getText().trim());
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			if (info == null || info.isDisposed()) {
				return;
			}
			Mailing mailing = context.get(Mailing.class);
			if (mailing == null) {
				return;
			}
			info.setText(new EmailTemplate(mailing).details(data.get()).stream()//
					.collect(Collectors.joining(System.lineSeparator())));
		}
	}

	public String mailFrom() {
		return mailFrom;
	}

}
