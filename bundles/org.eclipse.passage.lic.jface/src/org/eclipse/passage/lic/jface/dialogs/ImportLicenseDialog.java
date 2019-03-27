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
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.conditions.ConditionMiners;
import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.internal.jface.viewers.LicensingConditionViewer;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransportRegistry;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.eclipse.passage.lic.runtime.io.StreamCodecRegistry;
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

	private final ConditionMinerRegistry conditionMinerRegistry;
	private final LicensingConfiguration configuration;
	private final KeyKeeper keyKeeper;
	private final StreamCodec streamCodec;
	private final ConditionTransport transport;

	private final List<LicensingCondition> mined = new ArrayList<>();

	private Combo sourceText;
	private Button sourceButton;
	private String currentMessage;
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
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Import License");
		newShell.setImage(LicensingImages.getImage(LicensingImages.IMG_IMPORT));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Import license");
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
		label.setText("From licensing file");
		sourceText = new Combo(composite, SWT.NONE);
		sourceText.setLayoutData(new GridData(GridData.FILL_BOTH));
		sourceText.addListener(SWT.Modify, e -> handleEvent(e));
		sourceButton = new Button(composite, SWT.PUSH);
		sourceButton.setText("B&rowse...");
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
		dialog.setText("Import from File");
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
			setMessage(mining.getMessage());
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
			setErrorMessage(currentMessage);
		}

		return complete;
	}

	protected boolean validateSourceGroup() {
		if (!validSource()) {
			currentMessage = "Licensing file does not exist or is a directory.";
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
		conditionMinerRegistry.importConditions(source, configuration);
		super.okPressed();
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

}
