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
package org.eclipse.passage.loc.internal.workbench;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.passage.lic.api.access.AccessEvents;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.jface.viewers.LicensingLabelProvider;
import org.eclipse.passage.lic.jface.viewers.RestrictionRepresenters;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class LicensingStatusToolControl {

	private Button button;
	private LicensingLabelProvider labelProvider;

	@Inject
	@Optional
	public void restrictionsExecuted(
			@UIEventTopic(AccessEvents.RESTRICTIONS_EXECUTED) Iterable<RestrictionVerdict> actions) {
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(actions);
		updateButton(last);
	}

	@PostConstruct
	public void createGui(Composite parent) {
		button = new Button(parent, SWT.NONE);
		button.setFont(JFaceResources.getDefaultFont());
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell activeShell = button.getDisplay().getActiveShell();
				LicensingStatusDialog dialog = new LicensingStatusDialog(activeShell);
				dialog.open();
			}
		});
		button.setImage(LicensingImages.getImage(LicensingImages.IMG_LEVEL_OK));
		button.setText(WorkbenchMessages.LicensingStatusToolControl_text_undefined);
		labelProvider = new LicensingLabelProvider();
	}

	protected void updateButton(RestrictionVerdict last) {
		button.setImage(labelProvider.getImage(last));
		button.setText(labelProvider.getText(last));
		button.setToolTipText(RestrictionRepresenters.resolveSummary(last));
	}

}