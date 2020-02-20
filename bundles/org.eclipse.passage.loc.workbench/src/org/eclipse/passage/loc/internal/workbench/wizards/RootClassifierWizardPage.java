package org.eclipse.passage.loc.internal.workbench.wizards;

import java.io.File;

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.internal.workbench.ClassifierMetadata;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Provides UI to to fulfill the field values for a root classifier to be
 * created, including path to store the new resource.
 * 
 * @since 0.6
 * 
 * @see BaseClassifierWizardPage
 *
 */
public final class RootClassifierWizardPage extends BaseClassifierWizardPage {

	private final String extension;

	protected Text textPath;
	private Button buttonPath;

	protected RootClassifierWizardPage(ClassifierMetadata metadata, ClassifierInitializer initializer,
			String extension) {
		super(RootClassifierWizardPage.class.getSimpleName(), metadata, initializer);
		this.extension = extension;
	}

	@Override
	protected void createFieldControls(Composite composite) {
		super.createFieldControls(composite);
		textPath = new Text(composite, SWT.BORDER);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			data.horizontalSpan = 1;
			textPath.setLayoutData(data);
		}
		buttonPath = new Button(composite, SWT.PUSH);
		buttonPath.setText(WorkbenchMessages.CreateFileWizardPage_button_browse);
		textPath.addModifyListener(validator);
		buttonPath.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> selectPath()));
	}

	protected void selectPath() {
		String selected = LocWokbench.selectSavePath(getShell(), extension);
		if (selected != null) {
			textPath.setText(selected);
		}
	}

	@Override
	protected void initControls(ClassifierInitializer initializer) {
		super.initControls(initializer);
		String basePath = basePath();
		String fileName = initializer.newFileName();
		String resourceURI = basePath + File.separator + fileName + '.' + extension;
		textPath.setText(resourceURI);
	}

	protected String basePath() {
		return System.getProperty("user.home"); //$NON-NLS-1$
	}

	protected String path() {
		String text = textPath.getText();
		if (!text.endsWith('.' + extension)) {
			text = text + '.' + extension;
		}
		return text;
	}
}
