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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

abstract class NotificationDialog extends TitleAreaDialog {

	protected final Map<Integer, ButtonConfig> buttons = new TreeMap<>();
	protected TableViewer viewer; // framework-driven mutability

	public NotificationDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected final Control createDialogArea(Composite parent) {
		Composite owner = owner(parent);
		buildUI(owner);
		installBehaviour();
		inplaceData();
		initMessage();
		return owner;
	}

	@Override
	protected final void buttonPressed(int id) {
		Optional.ofNullable(buttons.get(id))//
				.map(button -> button.action())//
				.orElse(this::okPressed) //
				.run();
	}

	@Override
	protected final void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
		initButtons();
		buttons.keySet().forEach(id -> createButton(parent, buttons.get(id)));
		createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, true);
		updateButtonsEnablement();
	}

	@Override
	protected final boolean isResizable() {
		return true;
	}

	private void createButton(Composite parent, ButtonConfig config) {
		Button button = createButton(parent, config.id(), config.name(), false);
		button.setToolTipText(config.tooltip());
		button.setImage(LicensingImages.getImage(config.image()));
	}

	private Composite owner(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1, false));
		return composite;
	}

	private void installBehaviour() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtonsEnablement();
			}
		});
	}

	protected abstract void buildUI(Composite parent);

	protected abstract void initButtons();

	protected abstract void inplaceData();

	protected abstract void updateButtonsEnablement();

	protected abstract void initMessage();

}
