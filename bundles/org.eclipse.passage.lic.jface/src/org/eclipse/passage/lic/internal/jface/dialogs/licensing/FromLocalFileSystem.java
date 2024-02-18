package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.jface.i18n.ImportLicenseDialogMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class FromLocalFileSystem implements LicenseFilesControl {

	private Text path;

	@Override
	public void install(Composite parent, Consumer<List<Path>> onLicenses) {
		Composite composite = row(parent, 3);
		new Label(composite, SWT.NONE).setText(ImportLicenseDialogMessages.ImportLicenseDialog_path_label);
		path = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		path.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Button browse = new Button(composite, SWT.PUSH);
		browse.setText(ImportLicenseDialogMessages.ImportLicenseDialog_browse);
		browse.addListener(SWT.Selection, e -> browseAndLoad(onLicenses));
	}

	private void browseAndLoad(Consumer<List<Path>> onLicenses) {
		onLicenses.accept(browse());
	}

	private Composite row(Composite parent, int columns) {
		Composite row = new Composite(parent, SWT.NONE);
		row.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		row.setLayout(new GridLayout(columns, false));
		return row;
	}

	private List<Path> browse() {
		DirectoryDialog dialog = new DirectoryDialog(path.getShell(), SWT.OPEN | SWT.SHEET);
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

}
