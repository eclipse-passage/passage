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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.equinox.EquinoxAccess;
import org.eclipse.passage.lic.jface.RestrictionVerdictLabels;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.jface.viewers.LicensingLabelProvider;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.passage.lic.runtime.inspector.FeatureCase;
import org.eclipse.passage.lic.runtime.inspector.FeatureInspector;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class LicensingStatusDialog extends TitleAreaDialog {

	public static final int HARDWARE_INSPECTOR_ID = IDialogConstants.CLIENT_ID + 1;

	public static final int IMPORT_LICENSE_ID = IDialogConstants.CLIENT_ID + 2;

	private static String defaultContacts = ""; //$NON-NLS-1$

	// FIXME: AF: implement https://bugs.eclipse.org/bugs/show_bug.cgi?id=544387
	public static String getDefaultContacts() {
		return defaultContacts;
	}

	public static void setDefaultContacts(String defaultContacts) {
		LicensingStatusDialog.defaultContacts = defaultContacts;
	}
	private final AccessManager accessManager;
	private final FeatureInspector featureInspector;
	private final HardwareInspector hardwareInspector;
	
	private final FeatureCase featureCase;

	private TableViewer tableViewer;


	public LicensingStatusDialog(Shell shell, String...features) {
		super(shell);
		accessManager = EquinoxAccess.getAccessManager();
		featureInspector = EquinoxAccess.getFeatureInspector();
		featureCase = featureInspector.inspectFeatures(features);
		hardwareInspector = EquinoxAccess.getHardwareInspector();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Licensing");
		newShell.setImage(LicensingImages.getImage(LicensingImages.IMG_DEFAULT));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Licensing status");
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(featureCase.getRestrictions());
		if (last == null) {
			setMessage(RestrictionVerdictLabels.resolveSummary(last));
		} else {
			setErrorMessage(RestrictionVerdictLabels.resolveSummary(last));
		}
		Composite area = (Composite) super.createDialogArea(parent);
		createAreaContent(area);
		Dialog.applyDialogFont(area);
		return area;
	}

	protected void createAreaContent(Composite area) {
		Composite contents = new Composite(area, SWT.NONE);
		contents.setLayout(new GridLayout(1, false));
		contents.setLayoutData(new GridData(GridData.FILL_BOTH));
		Table tableDetails = new Table(contents, SWT.BORDER);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableDetails.setLayoutData(layoutData);
		tableDetails.setHeaderVisible(true);
		tableDetails.setLinesVisible(true);

		tableViewer = new TableViewer(tableDetails);
		tableViewer.setContentProvider(new ArrayContentProvider());

		createColumnViewer(tableViewer, "", 20);
		createColumnViewer(tableViewer, "Provider", 150);
		createColumnViewer(tableViewer, "Name", 300);
		createColumnViewer(tableViewer, "Version", 80);
		createColumnViewer(tableViewer, "Idenitifier", 200);
		createColumnViewer(tableViewer, "Level", 50);

		tableViewer.setLabelProvider(new LicensingLabelProvider());

		tableViewer.setInput(featureCase.getRequirements());

		Group contactsGroup = new Group(area, SWT.NONE);
		contactsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		contactsGroup.setText("Please contact your Licensing Operator for details");
		contactsGroup.setFont(JFaceResources.getDialogFont());
		contactsGroup.setLayout(new GridLayout());
		StyledText contactsText = new StyledText(contactsGroup, SWT.READ_ONLY | SWT.MULTI);
		contactsText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		contactsText.setText(defaultContacts);
		contactsText.setFont(JFaceResources.getDialogFont());
	}

	protected TableViewerColumn createColumnViewer(TableViewer tableViewDetails, String columnName, int width) {
		TableViewerColumn columnViewer = new TableViewerColumn(tableViewDetails, SWT.NONE);
		TableColumn column = columnViewer.getColumn();
		column.setText(columnName);
		column.setWidth(width);
		column.setResizable(true);
		return columnViewer;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button importButton = createButton(parent, IMPORT_LICENSE_ID, "Import...", false);
		importButton.setImage(LicensingImages.getImage(LicensingImages.IMG_IMPORT));
		importButton.setEnabled(accessManager != null);
		Button inspector = createButton(parent, HARDWARE_INSPECTOR_ID, "Inspect...", false);
		inspector.setImage(LicensingImages.getImage(LicensingImages.IMG_INSPECTOR));
		inspector.setEnabled(hardwareInspector != null);
		createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, true);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		switch (buttonId) {
		case IMPORT_LICENSE_ID:
			importLicensePressed();
			break;
		case HARDWARE_INSPECTOR_ID:
			hardwareInspectorPressed();
			break;
		default:
			okPressed();
			break;
		}
	}

	protected void importLicensePressed() {
		ImportLicenseDialog dialog = new ImportLicenseDialog(getShell(), accessManager);
		dialog.open();
	}

	protected void hardwareInspectorPressed() {
		HardwareInspectorDialog dialog = new HardwareInspectorDialog(getShell(), hardwareInspector);
		dialog.open();
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

}
