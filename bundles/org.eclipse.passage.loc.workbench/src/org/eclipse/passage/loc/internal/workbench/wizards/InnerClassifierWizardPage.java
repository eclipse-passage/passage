/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.workbench.wizards;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.loc.internal.api.ComposableClassSupply;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.moveto.lic.emf.edit.EClassName;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.FrameworkUtil;

/**
 * Provides UI to to fulfill the field values for an inner classifier to be
 * created, including container object.
 * 
 * @param <R> root classifier to store created if not present
 * 
 * @see BaseClassifierWizardPage
 *
 */
public final class InnerClassifierWizardPage<R> extends BaseClassifierWizardPage {

	private final SelectRequest<R> request;
	private final MandatoryService context;
	private final MandatoryEclipseContext forward;

	private Text text;

	protected InnerClassifierWizardPage(EntityMetadata metadata, SelectRequest<R> request, MandatoryService context) {
		super(InnerClassifierWizardPage.class.getSimpleName(), metadata);
		this.request = request;
		this.context = context;
		this.forward = new MandatoryEclipseContext(EclipseContextFactory
				.createServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext()).createChild());
	}

	@Override
	protected void createFieldControls(Composite composite) {
		text = createTextButtonBlock(composite, //
				new EClassName(containerMetadata().get().eClass()).get(), //
				this::selectContainer);
		super.createFieldControls(composite);
	}

	private Optional<EntityMetadata> containerMetadata() {
		return context.get(ComposableClassMetadata.class).find(request.target());
	}

	private Text createTextButtonBlock(Composite composite, String labelText, Supplier<Optional<?>> supplier) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(labelText);
		label.setLayoutData(GridDataFactory.fillDefaults().create());
		Text parent = new Text(composite, SWT.READ_ONLY);
		parent.addModifyListener(m -> setPageComplete(validatePage()));
		parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		Button select = new Button(composite, SWT.PUSH);
		select.setText(WorkbenchMessages.InnerClassifierWizardPage_text_select);
		forward.get().set(Shell.class, getShell());
		select.addSelectionListener(widgetSelectedAdapter(event -> updateText(supplier.get())));
		select.setLayoutData(GridDataFactory.fillDefaults().create());
		return parent;
	}

	private void updateText(Optional<?> optional) {
		if (optional.isPresent()) {
			Object present = optional.get();
			text.setData(present);
			text.setText(request.appearance().labelProvider().getText(present));
		} else {
			text.setData(null);
			text.setText(""); //$NON-NLS-1$
		}
		validatePage();
	}

	@Override
	protected void initControls() {
		super.initControls();
		Optional.ofNullable(eObject.eContainingFeature()).ifPresent(f -> updateText(container(eObject.eGet(f))));
	}

	private Optional<?> selectContainer() {
		Collection<R> initial = new ArrayList<>();
		container().ifPresent(initial::add);
		Optional<InstanceSupply<?>> found = context.get(ComposableClassSupply.class).find(request.target(), forward);
		if (found.isPresent()) {
			return found.get().supply();
		}
		throw new NoSuchElementException(request.target().getName());
	}

	@Override
	protected boolean validatePage() {
		if (!Optional.ofNullable(text.getData()).isPresent()) {
			setErrorMessage(NLS.bind(WorkbenchMessages.InnerClassifierWizardPage_e_specify_container,
					new EClassName(containerMetadata().get().eClass()).get()));
			return false;
		}
		return super.validatePage();
	}

	protected Optional<R> container() {
		return container(text.getData());
	}

	protected Optional<R> container(Object nullable) {
		return Optional.ofNullable(nullable)//
				.filter(request.target()::isInstance)//
				.flatMap(d -> Optional.of(request.target().cast(d)));
	}
}
