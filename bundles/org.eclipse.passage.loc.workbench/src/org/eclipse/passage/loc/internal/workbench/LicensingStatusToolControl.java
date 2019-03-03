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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.passage.lic.base.LicensingEvents;
import org.eclipse.passage.lic.jface.RestrictionVerdictLabels;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class LicensingStatusToolControl {
	
	private final HardwareInspector hardwareInspector;

	private final List<ConfigurationRequirement> requirements = new ArrayList<>();
	private final List<RestrictionVerdict> verdicts = new ArrayList<>();

	private Button button;

	@Inject
	public LicensingStatusToolControl(HardwareInspector hardwareInspector) {
		this.hardwareInspector = hardwareInspector;
	}
	
	@Inject
	@Optional
	public void requirementsResolved(@UIEventTopic(LicensingEvents.LicensingLifeCycle.REQUIREMENTS_RESOLVED) Iterable<ConfigurationRequirement> reqs) {
		requirements.clear();
		reqs.forEach(requirements::add);
	}

	@Inject
	@Optional
	public void restrictionsExecuted(@UIEventTopic(LicensingEvents.LicensingLifeCycle.RESTRICTIONS_EXECUTED) Iterable<RestrictionVerdict> actions) {
		verdicts.clear();
		actions.forEach(verdicts::add);
		RestrictionVerdict last = RestrictionVerdictLabels.resolveLastVerdict(verdicts);
		updateButton(last);
	}

	@PostConstruct
	public void createGui(Composite parent) {
		button = new Button(parent, SWT.NONE);
		button.setFont(JFaceResources.getDefaultFont());
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String contacts = "Eclipse Passage \nhttps://projects.eclipse.org/projects/technology.passage";
				LicensingStatusDialog.setDefaultContacts(contacts);
				Shell activeShell = button.getDisplay().getActiveShell();
				LicensingStatusDialog dialog = new LicensingStatusDialog(activeShell);
				dialog.setHardwareInspector(hardwareInspector);
				dialog.updateLicensingStatus(requirements, verdicts);
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