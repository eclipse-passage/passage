/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
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
		Set<String> products = data.products();
		addUsers("Users", storage -> storage.personsUsedProducts(products), this::userInfo); //$NON-NLS-1$
		addUsers("Companies", storage -> storage.companiesUsedProducts(products), this::companyInfo); //$NON-NLS-1$
	}

	private <T> void addUsers(String title, Function<CustomerStorage, Collection<T>> target, Function<T, String> info) {
		users.add(String.format("--- %s ---", title)); //$NON-NLS-1$
		target.apply(customers).stream() //
				.map(info) //
				.sorted() //
				.forEach(users::add);
	}

	private String userInfo(UserDescriptor user) {
		return NLS.bind("{0} ({1})", user.getContact().getName(), user.getContact().getEmail()); //$NON-NLS-1$
	}

	private String companyInfo(UserOriginDescriptor company) {
		if (company == null) {
			return ExportCustomersWizardMessages.PreviewPage_noinfo_company;
		}
		return NLS.bind("{0} ({1})", company.getName(), company.getIdentifier()); //$NON-NLS-1$
	}

}
