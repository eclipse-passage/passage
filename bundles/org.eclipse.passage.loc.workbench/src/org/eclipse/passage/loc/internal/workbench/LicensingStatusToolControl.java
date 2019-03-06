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
import org.eclipse.passage.lic.base.LicensingEvents;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.jface.RestrictionVerdictLabels;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class LicensingStatusToolControl {
	
	private Button button;

	@Inject
	@Optional
	public void restrictionsExecuted(@UIEventTopic(LicensingEvents.LicensingLifeCycle.RESTRICTIONS_EXECUTED) Iterable<RestrictionVerdict> actions) {
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
				String contacts = "Eclipse Passage \nhttps://www.eclipse.org/passage";
				LicensingStatusDialog.setDefaultContacts(contacts);
				Shell activeShell = button.getDisplay().getActiveShell();
				LicensingStatusDialog dialog = new LicensingStatusDialog(activeShell);
				dialog.open();
			}
		});
		button.setImage(LicensingImages.getImage(LicensingImages.IMG_LEVEL_OK));
		button.setText("Undefined");
	}

	protected void updateButton(RestrictionVerdict last) {
		String key = RestrictionVerdictLabels.resolveImageKey(last);
		button.setImage(LicensingImages.getImage(key));
		button.setText(RestrictionVerdictLabels.resolveLabel(last));
		button.setToolTipText(RestrictionVerdictLabels.resolveSummary(last));
	}

}