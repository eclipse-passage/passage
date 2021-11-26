/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.io.UnsupportedEncodingException;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.internal.jface.i18n.AgreementsDialogMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

final class AgreementPage extends WizardPage {

	private final AgreementToAccept agreement;

	protected AgreementPage(AgreementToAccept agreement) {
		super(agreement.acceptance().name());
		this.agreement = agreement;
		setPageComplete(false);
		setMessage(
				AgreementsDialogMessages.AgreementPage_title);

	}

	@Override
	public void createControl(Composite parent) {
		Composite container = container(parent);
		installContentView(container);
		installOptions(container);
		setControl(container);
	}

	private Composite container(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, true));
		return container;
	}

	private void installContentView(Composite parent) {
		Text content = new Text(parent, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		try {
			// TODO: add 'encoding' control
			content.setText(new String(agreement.acceptance().content(), "UTF-8")); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			content.setText(e.getLocalizedMessage());
		}
	}

	private void installOptions(Composite parent) {
		Composite options = new Composite(parent, SWT.NONE);
		options.setLayout(new GridLayout(1, false));
		Button accept = option(parent, AgreementsDialogMessages.AgreementPage_accept);
		accept.addSelectionListener(SelectionListener.widgetSelectedAdapter(this::updatePageComplete));
	}

	private void updatePageComplete(SelectionEvent e) {
		boolean accepted = ((Button) e.getSource()).getSelection();
		setPageComplete(accepted);
	}

	private Button option(Composite parent, String text) {
		Button option = new Button(parent, SWT.CHECK | SWT.LEFT);
		option.setText(text);
		option.setSelection(false);
		option.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		return option;
	}

}
