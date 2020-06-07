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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.report.internal.core.user.CustomerStorage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportCustomersWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.PageObserver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

final class PreviewPage extends WizardPage implements PageObserver {

	private final CustomerStorage customers;
	private List users;
	private Text path;
	private final DataForExport data;

	protected PreviewPage(CustomerStorage customers, DataForExport data) {
		super("preview"); //$NON-NLS-1$
		this.customers = customers;
		this.data = data;
		setTitle(ExportCustomersWizardMessages.PreviewPage_title);
		setMessage(ExportCustomersWizardMessages.PreviewPage_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(1, false));
		users = new List(content, SWT.BORDER | SWT.READ_ONLY);
		users.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		path = new Text(content, SWT.BORDER | SWT.READ_ONLY);
		path.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));
		setControl(content);
	}

	@Override
	public void update() {
		updateTargetPath();
		updateUsers();
		getWizard().getContainer().updateButtons();
	}

	private void updateTargetPath() {
		path.setText(data.target().toString());
	}

	private void updateUsers() {
		users.removeAll();
		customers.forProducts(data.products()).stream() //
				.map(this::userInfo) //
				.sorted() //
				.forEach(users::add);
	}

	private String userInfo(UserDescriptor user) {
		return NLS.bind("{0} ({1})", user.getFullName(), user.getEmail()); //$NON-NLS-1$
	}

}
