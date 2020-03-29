/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.meta.PlainEntityMetadata;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.internal.workbench.wizards.BaseClassifierWizard;
import org.eclipse.swt.widgets.Shell;

/**
 * Creates the resource for the given domain with {@link BaseClassifierWizard}.
 * Will return either root object of created resource or
 * {@link Optional#empty()}
 * 
 * @since 0.6
 *
 * @param <C> classifier to be created
 */
public abstract class CreateClassifier<C> implements Supplier<Optional<C>> {

	protected final IEclipseContext context;
	private final String domain;
	private final Class<C> clazz;

	/**
	 * Constructs the new instance with given context, domain and classifier.
	 * Actually either domain or classifier should be enough - to be fixed later.
	 * 
	 * @param context    the {@link IEclipseContext} to resolve services, must not
	 *                   be <code>null</code>
	 * @param domain     the licensing domain to create resource for, must not be
	 *                   <code>null</code>
	 * @param classifier the class of object to be created and stored in resource,
	 *                   must not be <code>null</code>
	 */
	public CreateClassifier(IEclipseContext context, String domain, Class<C> classifier) {
		Objects.requireNonNull(context, WorkbenchMessages.CreateDomainResource_e_null_context);
		Objects.requireNonNull(domain, WorkbenchMessages.CreateDomainResource_e_null_domain);
		Objects.requireNonNull(classifier, WorkbenchMessages.CreateDomainResource_e_null_classifier);
		this.context = context;
		this.domain = domain;
		this.clazz = classifier;
	}

	@Override
	public Optional<C> get() {
		EditingDomainRegistryAccess registryAccess = context.get(EditingDomainRegistryAccess.class);
		ClassifierInitializer initializer = registryAccess.getClassifierInitializer(domain);
		EditingDomainRegistry<?> registry = registryAccess.getDomainRegistry(domain);
		return showWizard(clazz, initializer, registry)//
				.filter(clazz::isInstance)//
				.flatMap(e -> Optional.of(clazz.cast(e)));
	}

	protected Optional<EObject> showWizard(Class<C> clazz, ClassifierInitializer initializer,
			EditingDomainRegistry<?> registry) {
		PlainEntityMetadata metadata = new PlainEntityMetadata(registry.getContentClassifier(),
				registry.getContentIdentifierAttribute(), registry.getContentNameAttribute());
		BaseClassifierWizard<?> wizard = createWizard(clazz, metadata, initializer, registry);
		WizardDialog dialog = new WizardDialog(context.get(Shell.class), wizard);
		dialog.create();
		dialog.setTitle(initializer.newObjectTitle());
		dialog.setMessage(initializer.newFileMessage());
		Shell createdShell = dialog.getShell();
		createdShell.setText(initializer.newObjectMessage());
		createdShell.setImage(LicensingImages.getImage(registry.getContentClassifier().getName()));
		dialog.open();
		return wizard.created();
	}

	protected abstract BaseClassifierWizard<?> createWizard(Class<C> clazz, EntityMetadata metadata,
			ClassifierInitializer initializer, EditingDomainRegistry<?> registry);

}
