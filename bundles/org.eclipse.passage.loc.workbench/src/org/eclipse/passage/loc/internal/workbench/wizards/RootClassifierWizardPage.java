package org.eclipse.passage.loc.internal.workbench.wizards;

import java.io.File;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.passage.moveto.lic.emf.edit.EObjectNameIdentifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Provides UI to to fulfill the field values for a root classifier to be
 * created, including path to store the new resource.
 * 
 * @see BaseClassifierWizardPage
 *
 */
public final class RootClassifierWizardPage extends BaseClassifierWizardPage {

	private final String extension;

	protected Text text;
	private Button button;

	protected RootClassifierWizardPage(EntityMetadata metadata, String extension) {
		super(RootClassifierWizardPage.class.getSimpleName(), metadata);
		this.extension = extension;
	}

	@Override
	protected void createFieldControls(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(WorkbenchMessages.CreateFileWizardPage_label_file);
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		text.addModifyListener(validator);
		button = new Button(composite, SWT.PUSH);
		button.setText(WorkbenchMessages.CreateFileWizardPage_button_browse);
		button.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> selectPath()));
		super.createFieldControls(composite);
	}

	protected void selectPath() {
		String selected = LocWokbench.selectSavePath(getShell(), extension);
		if (selected != null) {
			text.setText(selected);
		}
	}

	@Override
	protected void initControls() {
		super.initControls();
		text.setText(basePath() + File.separator + new EObjectNameIdentifier(eClass).apply('.') + '.' + extension);
	}

	protected String basePath() {
		return System.getProperty("user.home"); //$NON-NLS-1$
	}

	protected String path() {
		String path = text.getText();
		if (!path.endsWith('.' + extension)) {
			path = path + '.' + extension;
		}
		return path;
	}

	@Override
	protected boolean validatePage() {
		// FIXME: further improvements required for diagnostic (!exists, etc.)
		if (path().isEmpty()) {
			setErrorMessage(WorkbenchMessages.CreateFileWizardPage_e_specify_path);
			return false;
		}
		return super.validatePage();
	}
}
