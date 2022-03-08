/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.base.access.Libraries;
import org.eclipse.passage.lic.internal.equinox.access.RegisteredLibraries;
import org.eclipse.passage.lic.internal.jface.i18n.ImportLicenseDialogMessages;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("restriction")
public final class ImportLicenseDialog extends NotificationDialog {

	private final DateTimeFormatter dates = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //$NON-NLS-1$
	private Libraries libraries = null;
	private ButtonConfig action;
	private Text path;

	public ImportLicenseDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(ImportLicenseDialogMessages.ImportLicenseDialog_title);
		shell.setImage(LicensingImages.getImage(LicensingImages.IMG_IMPORT));
		shell.setSize(850, 500);
	}

	@Override
	protected void buildUI(Composite parent) {
		buildSelector(parent);
		buildViewer(parent);
	}

	@Override
	protected void initMessage() {
		setMessage(ImportLicenseDialogMessages.ImportLicenseDialog_prelude);
	}

	private void buildSelector(Composite parent) {
		Composite composite = row(parent, 3);
		new Label(composite, SWT.NONE).setText(ImportLicenseDialogMessages.ImportLicenseDialog_path_label);
		path = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		path.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Button browse = new Button(composite, SWT.PUSH);
		browse.setText("B&rowse..."); //$NON-NLS-1$
		browse.addListener(SWT.Selection, e -> browseAndLoad());
		setButtonLayoutData(browse);
	}

	private Composite row(Composite parent, int columns) {
		Composite row = new Composite(parent, SWT.NONE);
		row.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		row.setLayout(new GridLayout(columns, false));
		return row;
	}

	private void buildViewer(Composite parent) {
		viewer = new HereTable<Condition>(parent, Condition.class) //
				.withColumn(ImportLicenseDialogMessages.ImportLicenseDialog_column_feature, 300, this::feature) //
				.withColumn(ImportLicenseDialogMessages.ImportLicenseDialog_column_period, 300, this::period) //
				.withColumn(ImportLicenseDialogMessages.ImportLicenseDialog_column_evaluation, 210, this::evaluation) //
				.viewer();
	}

	private String feature(Condition condition) {
		return String.format("%s version %s (%s)", //$NON-NLS-1$
				condition.feature(), //
				condition.versionMatch().version(), //
				condition.versionMatch().rule().identifier());
	}

	private String period(Condition condition) {
		ValidityPeriod period = condition.validityPeriod();
		if (!BaseValidityPeriodClosed.class.isInstance(period)) { // to be eliminated #566015
			return "unknown"; //$NON-NLS-1$
		}
		BaseValidityPeriodClosed closed = (BaseValidityPeriodClosed) condition.validityPeriod();
		return String.format("%s - %s", dates.format(closed.from()), dates.format(closed.to())); //$NON-NLS-1$
	}

	private String evaluation(Condition condition) {
		return String.format("%s (%s)", //$NON-NLS-1$
				condition.evaluationInstructions().expression(), //
				condition.evaluationInstructions().type().identifier());
	}

	private void browseAndLoad() {
		loadLicense(browse());
		updateButtonsEnablement();
	}

	private List<Path> browse() {
		DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN | SWT.SHEET);
		dialog.setText(ImportLicenseDialogMessages.ImportLicenseDialog_browse_dialog_title);
		String folder = dialog.open();
		if (folder == null) {
			return Collections.emptyList();
		}
		path.setText(folder);
		List<Path> licenses = new AllLicensesFromFolder(folder).get();
		path.setData(licenses);
		return licenses;
	}

	private void loadLicense(List<Path> files) {
		Optional<LicensedProduct> product = product();
		if (!product.isPresent()) {
			return;
		}
		ServiceInvocationResult<Collection<ConditionPack>> packs = //
				new AllConditionsFromLicenses(files, libraries()).get();
		if (!new NoSevereErrors().test(packs.diagnostic())) {
			reportError(packs.diagnostic());
			return;
		}
		List<Condition> conditions = packs.data().get().stream()//
				.flatMap(pack -> pack.conditions().stream())//
				.collect(Collectors.toList());
		viewer.setInput(conditions);
	}

	private void reportError(Diagnostic diagnostic) {
		setErrorMessage(ImportLicenseDialogMessages.ImportLicenseDialog_lic_read_failed);
		new DiagnosticDialog(getShell(), diagnostic).open();
	}

	@Override
	protected void initButtons() {
		action = new ButtonConfig(1, this::doLicenseImport, //
				ImportLicenseDialogMessages.ImportLicenseDialog_import_title, //
				ImportLicenseDialogMessages.ImportLicenseDialog_import_tooltip, //
				""); //$NON-NLS-1$
		action.reside(buttons);
	}

	@Override
	protected void inplaceData() {
		viewer.setInput(Collections.emptyList());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void updateButtonsEnablement() {
		getButton(action.id()).setEnabled(!((Collection) viewer.getInput()).isEmpty());
	}

	private Optional<LicensedProduct> product() {
		ServiceInvocationResult<LicensedProduct> product = new EquinoxPassage().product();
		if (!product.data().isPresent()) {
			reportError(product.diagnostic());
		}
		return product.data();
	}

	@SuppressWarnings("unchecked")
	private void doLicenseImport() {
		Optional<LicensedProduct> product = product();
		if (!product.isPresent()) {
			return;
		}
		Object licenses = path.getData();
		if (licenses == null) {
			return;
		}
		new LicenseSet((List<Path>) licenses, product.get(), libraries, this::setErrorMessage).install();
		okPressed();
	}

	private Libraries libraries() {
		if (libraries == null) {
			Optional<LicensedProduct> product = product();
			if (!product.isPresent()) {
				return null;
			}
			libraries = new Libraries(new RegisteredLibraries(), product::get);
		}
		return libraries;
	}

}
