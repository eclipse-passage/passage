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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.internal.workbench.wizards.BaseClassifierWizard;
import org.eclipse.passage.moveto.lic.emf.edit.EClassResources;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * Creates the resource for the given domain with {@link BaseClassifierWizard}.
 * Will return either root object of created resource or
 * {@link Optional#empty()}
 * 
 * @param <C> classifier to be created
 */
public abstract class CreateClassifier<C> implements Supplier<Optional<C>> {

	protected final MandatoryService context;
	private final String domain;
	private final Class<C> clazz;

	/**
	 * Constructs the new instance with given context, domain and classifier.
	 * Actually either domain or classifier should be enough - to be fixed later.
	 * 
	 * @param context    the {@link MandatoryService} to resolve services, must not
	 *                   be <code>null</code>
	 * @param domain     the licensing domain to create resource for, must not be
	 *                   <code>null</code>
	 * @param classifier the class of object to be created and stored in resource,
	 *                   must not be <code>null</code>
	 */
	public CreateClassifier(MandatoryService context, String domain, Class<C> classifier) {
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
		EditingDomainRegistry<?> registry = registryAccess.getDomainRegistry(domain);
		return showWizard(clazz, registry)//
				.filter(clazz::isInstance)//
				.flatMap(e -> Optional.of(clazz.cast(e)));
	}

	protected Optional<EObject> showWizard(Class<C> type, EditingDomainRegistry<?> registry) {
		Optional<EntityMetadata> found = context.get(ComposableClassMetadata.class).find(type);
		if (!found.isPresent()) {
			return Optional.empty();
		}
		EntityMetadata metadata = found.get();
		EClass eClass = metadata.eClass();
		ResourceLocator resourceLocator = new EClassResources(eClass).get();
		String typeName = resourceLocator.getString(NLS.bind("_UI_{0}_type", eClass.getName())); //$NON-NLS-1$
		BaseClassifierWizard<?> wizard = createWizard(type, metadata, registry);
		Shell parentShell = context.get(Shell.class);
		WizardDialog dialog = new WizardDialog(parentShell, wizard);
		dialog.create();
		dialog.setTitle(typeName);
		dialog.setMessage(dialogMessage(typeName));
		Shell createdShell = dialog.getShell();
		Point location = createdShell.getLocation();
		createdShell.setLocation(location.x + 40, location.y + 40);
		createdShell.setText(NLS.bind(WorkbenchMessages.CreateClassifier_text_new_type, typeName));
		createdShell.setImage(LicensingImages.getImage(eClass.getName()));
		dialog.open();
		return wizard.created();
	}

	protected abstract String dialogMessage(String typeName);

	protected abstract BaseClassifierWizard<?> createWizard(Class<C> type, EntityMetadata metadata,
			EditingDomainRegistry<?> registry);

}
