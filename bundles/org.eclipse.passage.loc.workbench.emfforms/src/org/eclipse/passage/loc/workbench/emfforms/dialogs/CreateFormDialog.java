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
package org.eclipse.passage.loc.workbench.emfforms.dialogs;

import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTView;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.spi.model.VDiagnostic;
import org.eclipse.emf.ecp.view.spi.model.VViewFactory;
import org.eclipse.emf.ecp.view.spi.model.VViewModelProperties;
import org.eclipse.emf.ecp.view.spi.validation.ValidationService;
import org.eclipse.emf.ecp.view.spi.validation.ViewValidationListener;
import org.eclipse.emfforms.swt.core.EMFFormsSWTConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CreateFormDialog extends TitleAreaDialog {

	private final String shellText;
	private final String dialogTitle;
	private final EObject createdObject;

	private ECPSWTView ecpswtView;

	public CreateFormDialog(Shell parent, String shellText, String dialogTitle, EObject created) {
		super(parent);
		this.shellText = shellText;
		this.dialogTitle = dialogTitle;
		createdObject = created;
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(shellText);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.
	 * Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(dialogTitle);

		final ScrolledComposite wrapper = new ScrolledComposite(parent, SWT.V_SCROLL);
		wrapper.setExpandHorizontal(true);
		wrapper.setExpandVertical(true);
		final FillLayout wrapperLayout = new FillLayout();
		wrapperLayout.marginHeight = 10;
		wrapperLayout.marginWidth = 10;
		wrapper.setLayout(wrapperLayout);
		wrapper.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final Composite emfFormsParent = new Composite(wrapper, SWT.NONE);
		wrapper.setContent(emfFormsParent);
		emfFormsParent.setLayout(new GridLayout());

		try {
			final VViewModelProperties properties = VViewFactory.eINSTANCE.createViewModelLoadingProperties();
			properties.addInheritableProperty(EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_KEY,
					EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_VALUE);
			ecpswtView = ECPSWTViewRenderer.INSTANCE.render(emfFormsParent, createdObject, properties);
			final ValidationService validationService = ecpswtView.getViewModelContext()
					.getService(ValidationService.class);
			validationService.registerValidationListener(new ViewValidationListener() {
				@Override
				public void onNewValidation(Set<Diagnostic> validationResults) {
					updateOKButtonEnablement();
				}
			});
		} catch (final ECPRendererException e) {
		}

		wrapper.setMinSize(wrapper.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		getParentShell().forceFocus();
		final Diagnostic result = Diagnostician.INSTANCE.validate(createdObject);
		if (result.getSeverity() == Diagnostic.OK) {
			super.okPressed();
		} else {
			// Get the error count and create an appropriate Error message:
			final int errorCount = result.getChildren().size();

			final StringBuilder sb = new StringBuilder();

			sb.append(errorCount);
			sb.append(" ");
			sb.append(errorCount == 1 ? "error" : "errors");
			sb.append(" occured while analyzing your inputs. The following errors were found:\r\n");

			int messageCount = 1;
			for (final Diagnostic d : result.getChildren()) {
				sb.append("\r\n");
				sb.append(messageCount++);
				sb.append(". ");
				sb.append(d.getMessage());
			}

			final String errorMessage = sb.toString();

			MessageDialog.open(MessageDialog.ERROR, getParentShell(), "Error", errorMessage, SWT.NONE);
		}
	}

	/**
	 * Gets the created instance or the updated one, if it was passed in the
	 * constructor. All fields are initialized with user inputs
	 *
	 * @return the created instance
	 */
	public EObject getCreatedInstance() {
		return createdObject;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		updateOKButtonEnablement();
	}

	private void updateOKButtonEnablement() {
		VDiagnostic diagnostic = ecpswtView.getViewModelContext().getViewModel().getDiagnostic();
		boolean isError = diagnostic.getHighestSeverity() >= Diagnostic.ERROR;
		final Button button = getButton(IDialogConstants.OK_ID);
		if (isError) {
			setErrorMessage(diagnostic.getMessage());
			if (button != null) {
				button.setEnabled(false);
			}
		} else {
			setErrorMessage(null);
			if (button != null) {
				button.setEnabled(true);
			}
		}
	}
}
