/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.jface.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.internal.jface.JFaceMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class LicensingConfigurationDialog extends TrayDialog {

	private static final String TAB_ID = "TAB_ID"; //$NON-NLS-1$
	private static String lastTabId = null;
	private Map<TabItem, LicensingPage> tab2page = new HashMap<TabItem, LicensingPage>();
	private TabFolder tabFolder;

	public LicensingConfigurationDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(JFaceMessages.LicensingConfigurationDialog_shell);
		newShell.setImage(Window.getDefaultImage());
	}

	@Override
	protected Control createContents(Composite parent) {
		Control control = super.createContents(parent);
		boolean selected = false;
		if (tabFolder.getItemCount() > 0) {
			if (lastTabId != null) {
				TabItem[] items = tabFolder.getItems();
				for (int i = 0; i < items.length; i++)
					if (items[i].getData(TAB_ID).equals(lastTabId)) {
						tabFolder.setSelection(i);
						tabSelected(items[i]);
						selected = true;
						break;
					}
			}
			if (!selected)
				tabSelected(tabFolder.getItem(0));
		}
		Dialog.applyDialogFont(tabFolder);
		return control;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		tabFolder = new TabFolder(area, SWT.NONE);
		createFolderItems(tabFolder);

		GridData folderData = new GridData(SWT.FILL, SWT.FILL, true, true);
		folderData.widthHint = convertHorizontalDLUsToPixels(440);
		folderData.heightHint = convertVerticalDLUsToPixels(220);
		tabFolder.setLayoutData(folderData);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabSelected((TabItem) e.item);
			}
		});
		return area;
	}

	protected void createFolderItems(TabFolder folder) {
		Iterable<LicensingPageContributor> contributors = LicensingPages.getPageContributors();
		for (LicensingPageContributor pageContributor : contributors) {
			TabItem item = new TabItem(folder, SWT.NONE);
			item.setText(pageContributor.getPageName());
			item.setData(pageContributor);
			item.setData(TAB_ID, pageContributor.getPageIdentifier());
			Composite control = new Composite(folder, SWT.NONE);
			control.setLayout(new GridLayout());
			item.setControl(control);
		}
	}

	protected void tabSelected(TabItem item) {
		Object data = item.getData();
		if (data instanceof LicensingPageContributor) {
			final LicensingPageContributor element = (LicensingPageContributor) data;
			Composite pageComposite = (Composite) item.getControl();
			try {
				final LicensingPage page = element.createPage();
				tab2page.put(item, page);
				page.createControl(pageComposite);
				Dialog.applyDialogFont(pageComposite);
				item.setData(page);
				item.addDisposeListener(e -> page.dispose());
			} catch (Exception e) {
				Label label = new Label(pageComposite, SWT.NONE);
				label.setText(e.getMessage());
				item.setData(null);
			}
			pageComposite.layout(true, true);
		}
		String id = (String) item.getData(TAB_ID);
		lastTabId = id;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		for (LicensingPage page : tab2page.values()) {
			page.accept();
		}
		super.okPressed();
	}

	@Override
	public boolean close() {
		tab2page.clear();
		return super.close();
	}

}
