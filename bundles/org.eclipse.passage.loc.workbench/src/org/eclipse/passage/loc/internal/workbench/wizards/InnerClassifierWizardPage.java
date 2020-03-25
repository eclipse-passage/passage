package org.eclipse.passage.loc.internal.workbench.wizards;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.internal.workbench.ClassifierMetadata;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SelectRoot;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Provides UI to to fulfill the field values for an inner classifier to be
 * created, including container object.
 * 
 * @since 0.6
 * 
 * @see BaseClassifierWizardPage
 *
 */
public final class InnerClassifierWizardPage<C> extends BaseClassifierWizardPage {

	private final SelectRequest<C> request;
	private final IEclipseContext context;
	private final String container = "Container"; //$NON-NLS-1$ //FIXME: we need to resolve EClass and get its label!!!

	private Text text;

	protected InnerClassifierWizardPage(ClassifierMetadata metadata, ClassifierInitializer initializer,
			SelectRequest<C> request, IEclipseContext context) {
		super(InnerClassifierWizardPage.class.getSimpleName(), metadata, initializer);
		this.request = request;
		this.context = context;
	}

	@Override
	protected void createFieldControls(Composite composite) {
		String label = container;
		text = createTextButtonBlock(composite, label, () -> selectContainer());
		super.createFieldControls(composite);
	}

	private Text createTextButtonBlock(Composite composite, String labelText, Supplier<Optional<C>> supplier) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(labelText);
		label.setLayoutData(GridDataFactory.fillDefaults().create());
		Text parent = new Text(composite, SWT.READ_ONLY);
		parent.addModifyListener(m -> setPageComplete(validatePage()));
		parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		Button select = new Button(composite, SWT.PUSH);
		select.setText(WorkbenchMessages.InnerClassifierWizardPage_text_select);
		select.addSelectionListener(widgetSelectedAdapter(event -> updateText(supplier.get())));
		select.setLayoutData(GridDataFactory.fillDefaults().create());
		return parent;
	}

	private void updateText(Optional<C> optional) {
		if (optional.isPresent()) {
			C present = optional.get();
			text.setData(present);
			text.setText(request.appearance().labelProvider().getText(present));
		} else {
			text.setData(null);
			text.setText(""); //$NON-NLS-1$
		}
		validatePage();
	}

	@Override
	protected void initControls(ClassifierInitializer initializer) {
		super.initControls(initializer);
		Optional.ofNullable(eObject.eContainingFeature()).ifPresent(f -> updateText(container(eObject.eGet(f))));
	}

	private Optional<C> selectContainer() {
		Collection<C> initial = new ArrayList<>();
		container().ifPresent(initial::add);
		return new SelectRoot<>(request, context).get();
	}

	@Override
	protected boolean validatePage() {
		if (!Optional.ofNullable(text.getData()).isPresent()) {
			setErrorMessage(NLS.bind(WorkbenchMessages.InnerClassifierWizardPage_e_specify_container, container));
			return false;
		}
		return super.validatePage();
	}

	protected Optional<C> container() {
		return container(text.getData());
	}

	protected Optional<C> container(Object nullable) {
		return Optional.ofNullable(nullable)//
				.filter(request.target()::isInstance)//
				.flatMap(d -> Optional.of(request.target().cast(d)));
	}
}
