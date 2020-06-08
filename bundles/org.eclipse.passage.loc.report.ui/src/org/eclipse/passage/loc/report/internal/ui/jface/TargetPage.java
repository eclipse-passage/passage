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
package org.eclipse.passage.loc.report.internal.ui.jface;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportWizardMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class TargetPage extends WizardPage {

	private final PageObserver observer;
	private Text path;
	private Button open;

	public TargetPage(PageObserver observer) {
		super("target"); //$NON-NLS-1$
		this.observer = observer;
		setTitle(ExportWizardMessages.TargetPage_title);
		setDescription(ExportWizardMessages.TargetPage_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
		createPath(content);
		createBrowseForPath(content);
		createOpen(content);
		new Label(content, SWT.NONE).setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		setControl(content);
	}

	public void installInitial() {
		path.setText(System.getProperty("user.home")); //$NON-NLS-1$
		open.setSelection(true);
	}

	public Path path() {
		return Paths.get(path.getText());
	}

	public boolean open() {
		return open.getSelection();
	}

	private void createPath(Composite content) {
		path = new Text(content, SWT.READ_ONLY | SWT.BORDER);
		path.setLayoutData(new GridData(GridData.FILL, SWT.TOP, true, false));
		path.addModifyListener(e -> updateControls());
	}

	private void updateControls() {
		observer.update();
	}

	private void createOpen(Composite content) {
		open = new Button(content, SWT.CHECK);
		open.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 2, 1));
		open.setText(ExportWizardMessages.TargetPage_open);
	}

	private void createBrowseForPath(Composite content) {
		Button browse = new Button(content, SWT.PUSH);
		browse.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		browse.setText(ExportWizardMessages.TargetPage_browse);
		browse.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
			DirectoryDialog dialog = new DirectoryDialog(getShell());
			dialog.setFilterPath(path.getText());
			Optional.ofNullable(dialog.open()).ifPresent(path::setText);
		}));
	}

}
