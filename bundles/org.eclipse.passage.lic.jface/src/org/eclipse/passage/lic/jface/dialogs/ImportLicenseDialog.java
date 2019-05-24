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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.api.conditions.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.conditions.ConditionMiners;
import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.internal.jface.JFaceMessages;
import org.eclipse.passage.lic.internal.jface.viewers.LicensingConditionViewer;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class ImportLicenseDialog extends TitleAreaDialog {

	private final LicensingConfiguration configuration;
	private final ConditionMinerRegistry conditionMinerRegistry;
	private final KeyKeeper keyKeeper;
	private final StreamCodec streamCodec;
	private final ConditionTransport transport;
	private final LicensingReporter reporter;

	private final List<LicensingCondition> mined = new ArrayList<>();

	private Combo sourceText;
	private Button sourceButton;
	private String currentError;
	private TableViewer tableViewer;

	public ImportLicenseDialog(Shell shell, LicensingConfiguration configuration) {
		super(shell);
		this.configuration = configuration;
		this.conditionMinerRegistry = LicensingEquinox.getLicensingService(ConditionMinerRegistry.class);
		this.keyKeeper = LicensingEquinox.getLicensingService(KeyKeeperRegistry.class).getKeyKeeper(configuration);
		this.streamCodec = LicensingEquinox.getLicensingService(StreamCodecRegistry.class)
				.getStreamCodec(configuration);
		this.transport = LicensingEquinox.getLicensingService(ConditionTransportRegistry.class)
				.getConditionTransportForContentType(LicensingProperties.LICENSING_CONTENT_TYPE_XML);
		this.reporter = LicensingEquinox.getLicensingService(LicensingReporter.class);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(JFaceMessages.ImportLicenseDialog_shell);
		newShell.setImage(LicensingImages.getImage(LicensingImages.IMG_IMPORT));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(JFaceMessages.ImportLicenseDialog_title);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite contents = new Composite(area, SWT.NONE);
		contents.setLayout(new GridLayout(1, false));
		contents.setLayoutData(new GridData(GridData.FILL_BOTH));
		createSelector(contents);
		createViewer(contents);
		Dialog.applyDialogFont(area);
		return area;
	}

	protected void createSelector(Composite contents) {
		Composite composite = new Composite(contents, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		composite.setLayout(new GridLayout(3, false));
		Label label = new Label(composite, SWT.NONE);
		label.setText(JFaceMessages.ImportLicenseDialog_source_label);
		sourceText = new Combo(composite, SWT.NONE);
		sourceText.setLayoutData(new GridData(GridData.FILL_BOTH));
		sourceText.addListener(SWT.Modify, e -> handleEvent(e));
		sourceButton = new Button(composite, SWT.PUSH);
		sourceButton.setText(JFaceMessages.ImportLicenseDialog_source_button);
		sourceButton.addListener(SWT.Selection, e -> handleEvent(e));
		setButtonLayoutData(sourceButton);
	}

	protected void createViewer(Composite contents) {
		Table tableDetails = new Table(contents, SWT.BORDER);
		tableDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableViewer = LicensingConditionViewer.createTableViewer(tableDetails);
	}

	protected void handleBrowseButtonPressed() {
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SHEET);
		dialog.setText(JFaceMessages.ImportLicenseDialog_source_title);
		dialog.setFilterPath(sourceText.getText().trim());
		dialog.setFilterExtensions(new String[] { "*.licen", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
		String selectedFileName = dialog.open();
		if (selectedFileName != null) {
			sourceText.setText(selectedFileName);
		}
	}

	protected void handleEvent(Event e) {
		if (e.widget == sourceButton) {
			handleBrowseButtonPressed();
		}
		updateCompletion();
		tableViewer.setInput(mined);
	}

	protected void updateCompletion() {
		boolean pageComplete = determineCompletion();
		mined.clear();
		if (pageComplete) {
			LicensingResult mining = mine(sourceText.getText().trim());
			LicensingResultDialogs.updateTitleDialog(this, mining);
		}
		getButton(IDialogConstants.OK_ID).setEnabled(!mined.isEmpty());
	}

	protected LicensingResult mine(String pack) {
		String source = getClass().getName();
		Set<String> paths = Collections.singleton(pack);
		return ConditionMiners.mine(source, configuration, mined, keyKeeper, streamCodec, transport, paths);
	}

	protected boolean determineCompletion() {
		boolean complete = validateSourceGroup();
		if (complete) {
			setErrorMessage(null);
		} else {
			setErrorMessage(currentError);
		}
		return complete;
	}

	protected boolean validateSourceGroup() {
		if (!validSource()) {
			currentError = JFaceMessages.ImportLicenseDialog_source_invalid;
			return false;
		}
		return true;
	}

	protected boolean validSource() {
		String source = sourceText.getText().trim();
		File fromFile = new File(source);
		return fromFile.exists() && !fromFile.isDirectory();
	}

	@Override
	protected void okPressed() {
		String source = sourceText.getText().trim();
		LicensingResult result = conditionMinerRegistry.importConditions(source, configuration);
		LicensingResultDialogs.openMessageDialog(getShell(), JFaceMessages.ImportLicenseDialog_shell, result);
		int severity = result.getSeverity();
		if (severity <= LicensingResult.INFO) {
			super.okPressed();
		} else {
			reporter.logResult(result);
		}
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

}
