/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.workbench.wizards;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CreateFileWizardPage extends WizardPage {

	protected final EObject eObject;

	protected Text txtResourceURI;
	protected Text txtId;
	protected Text txtName;
	private Button resourceURIBrowseFileSystemButton;

	protected ModifyListener validator = e -> setPageComplete(validatePage());

	private String extension;
	private ClassifierInitializer classifierInitializer;
	private boolean createName;
	private boolean createId;

	public CreateFileWizardPage(String pageName, EObject eObject, String extension, ClassifierInitializer initializer,
			boolean createId, boolean createName) {
		super(pageName);

		this.extension = extension;
		this.classifierInitializer = initializer;
		this.createId = createId;
		this.createName = createName;
		this.eObject = eObject;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		createFileControls(composite);
		initControls(classifierInitializer);
		setPageComplete(validatePage());
		setControl(composite);
	}

	protected void createFileControls(Composite composite) {
		if (createId) {
			Label idFieldLabel = new Label(composite, SWT.LEFT);
			idFieldLabel.setText(WorkbenchMessages.CreateFileWizardPage_label_identifier);
			idFieldLabel.setLayoutData(GridDataFactory.fillDefaults().create());

			txtId = new Text(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				data.horizontalSpan = 2;
				txtId.setLayoutData(data);
			}
		}

		if (createName) {
			Label nameFieldILabel = new Label(composite, SWT.LEFT);
			{
				nameFieldILabel.setText(WorkbenchMessages.CreateFileWizardPage_label_name);
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = false;
				data.horizontalSpan = 1;
				nameFieldILabel.setLayoutData(data);
			}

			txtName = new Text(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				data.horizontalSpan = 2;
				txtName.setLayoutData(data);
			}
		}
		Label resourceURILabel = new Label(composite, SWT.LEFT);
		{
			resourceURILabel.setText(WorkbenchMessages.CreateFileWizardPage_label_file);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = false;
			data.horizontalSpan = 1;
			resourceURILabel.setLayoutData(data);
		}

		txtResourceURI = new Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			data.horizontalSpan = 1;
			txtResourceURI.setLayoutData(data);
		}
		resourceURIBrowseFileSystemButton = new Button(composite, SWT.PUSH);
		resourceURIBrowseFileSystemButton.setText(WorkbenchMessages.CreateFileWizardPage_button_browse);
		txtResourceURI.addModifyListener(validator);
		resourceURIBrowseFileSystemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String selected = LocWokbench.selectSavePath(getShell(), extension);
				if (selected != null) {
					txtResourceURI.setText(selected);
				}
			}
		});
	}

	protected void initControls(ClassifierInitializer initializer) {
		String basePath = getBasePath();
		String fileName = initializer.newFileName();
		String resourceURI = basePath + File.separator + fileName + '.' + extension;
		txtResourceURI.setText(resourceURI);
		if (txtId != null) {
			txtId.setText(initializer.newObjectIdentifier());
		}
		if (txtName != null) {
			txtName.setText(initializer.newObjectName());
		}
	}

	protected String getBasePath() {
		return System.getProperty("user.home"); //$NON-NLS-1$
	}

	protected boolean validatePage() {
		URI fileURI = getFileURI();
		boolean validationResult = true;
		if (fileURI == null || fileURI.isEmpty()) {
			setMessage(WorkbenchMessages.CreateFileWizardPage_e_specify_path);
			validationResult = false;
		}
		if (createId) {
			String textId = getIdentifier();
			if (textId == null || textId.isEmpty()) {
				setMessage(WorkbenchMessages.CreateFileWizardPage_e_specify_identifier);
				validationResult = false;
			}
		}
		if (createName) {
			String textName = getName();
			if (textName == null || textName.isEmpty()) {
				setMessage(WorkbenchMessages.CreateFileWizardPage_e_specify_name);
				validationResult = false;
			}
		}
		return validationResult;
	}

	public String getIdentifier() {
		if (txtId == null) {
			return ""; //$NON-NLS-1$
		}
		return txtId.getText();
	}

	@Override
	public String getName() {
		if (txtName == null) {
			return ""; //$NON-NLS-1$
		}
		return txtName.getText();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			txtResourceURI.setFocus();
		}
	}

	public URI getFileURI() {
		try {
			String text = txtResourceURI.getText();
			if (text != null && !text.endsWith('.' + extension)) {
				text = text + '.' + extension;
			}
			return URI.createFileURI(text);
		} catch (Exception exception) {
			// Ignore
		}
		return null;
	}

	public void selectFileField() {
		txtResourceURI.selectAll();
		txtResourceURI.setFocus();
	}

}
