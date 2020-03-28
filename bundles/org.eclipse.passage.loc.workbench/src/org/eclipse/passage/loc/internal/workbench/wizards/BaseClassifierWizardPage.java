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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.workbench.wizards;

import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Provides UI to to fulfill the field values for an object to be created,
 * either root of resource or not. Can be asked for a reference to a candidate
 * instance.
 * 
 * @since 0.6
 *
 */
public abstract class BaseClassifierWizardPage extends WizardPage {

	protected final EClass eClass;
	protected final EStructuralFeature identification;
	protected final EStructuralFeature naming;
	protected final ClassifierInitializer classifierInitializer;
	protected final EObject eObject;

	protected final ModifyListener validator = e -> setPageComplete(validatePage());

	private Text textId;
	private Text textName;

	/**
	 * Creates a new wizard page with given metadata and initializer.
	 * 
	 * @param pageName    the name of the page
	 * @param metadata    describes EMF metadata for an object to be created, must
	 *                    not be <code>null</code>
	 * @param initializer describer initial values for an object to be created, must
	 *                    not be <code>null</code>
	 * 
	 * @see EntityMetadata
	 * @see ClassifierInitializer
	 */
	protected BaseClassifierWizardPage(String pageName, EntityMetadata metadata, ClassifierInitializer initializer) {
		super(pageName);
		Objects.requireNonNull(metadata, WorkbenchMessages.BaseClassifierWizardPage_e_null_metadata);
		Objects.requireNonNull(initializer, WorkbenchMessages.BaseClassifierWizardPage_e_null_initializer);
		this.eClass = metadata.eClass();
		this.eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		this.identification = metadata.identification();
		this.naming = metadata.naming();
		this.classifierInitializer = initializer;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		createFieldControls(composite);
		initControls(classifierInitializer);
		setPageComplete(validatePage());
		setControl(composite);
	}

	protected void createFieldControls(Composite composite) {
		createIdentifierGroup(composite);
		createNameGroup(composite);
	}

	private void createNameGroup(Composite composite) {
		Label labelName = new Label(composite, SWT.LEFT);
		labelName.setText(WorkbenchMessages.CreateFileWizardPage_label_name);
		labelName.setLayoutData(GridDataFactory.fillDefaults().create());
		textName = new Text(composite, SWT.BORDER);
		textName.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		textName.addModifyListener(validator);
	}

	private void createIdentifierGroup(Composite composite) {
		Label labelId = new Label(composite, SWT.LEFT);
		labelId.setText(WorkbenchMessages.CreateFileWizardPage_label_identifier);
		labelId.setLayoutData(GridDataFactory.fillDefaults().create());
		textId = new Text(composite, SWT.BORDER);
		textId.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		textId.addModifyListener(validator);
	}

	protected void initControls(ClassifierInitializer initializer) {
		textId.setText(initializer.newObjectIdentifier());
		textName.setText(initializer.newObjectName());
	}

	protected boolean validatePage() {
		boolean validationResult = true;
		String id = textId.getText();
		// FIXME: databinding
		eObject.eSet(identification, id);
		if (id.isEmpty()) {
			setErrorMessage(WorkbenchMessages.CreateFileWizardPage_e_specify_identifier);
			validationResult = false;
		}
		String name = textName.getText();
		// FIXME: databinding
		eObject.eSet(naming, name);
		if (name.isEmpty()) {
			setErrorMessage(WorkbenchMessages.CreateFileWizardPage_e_specify_name);
			validationResult = false;
		}
		if (validationResult) {
			setErrorMessage(null);
		}
		return validationResult;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		textId.setFocus();
	}

	/**
	 * 
	 * @see BaseClassifierWizard#created()
	 */
	protected EObject candidate() {
		return eObject;
	}
}
