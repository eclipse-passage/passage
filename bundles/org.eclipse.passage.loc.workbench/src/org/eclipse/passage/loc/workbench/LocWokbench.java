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
package org.eclipse.passage.loc.workbench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.jface.dialogs.FilteredSelectionDialog;
import org.eclipse.passage.loc.jface.dialogs.LabelSearchFilter;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.passage.loc.workbench.wizards.CreateFileWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class LocWokbench {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.workbench"; //$NON-NLS-1$

	public static final String COMMAND_VIEW_PERSPECTIVE = "org.eclipse.passage.loc.workbench.command.view.perspective"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_VIEW_PERSPECTIVE_ID = "org.eclipse.passage.loc.workbench.commandparameter.perspective.id"; //$NON-NLS-1$

	public static final String TOPIC_SHOW = "ru/arsysop/passage/loc/workbench/show"; //$NON-NLS-1$

	public static String selectSavePath(Shell shell, String extension) {
		String[] array = maskFilters(extension);
		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setFilterExtensions(array);
		return fileDialog.open();
	}

	public static String selectLoadPath(Shell shell, String extension, String... others) {
		String[] array = maskFilters(extension, others);
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		fileDialog.setFilterExtensions(array);
		return fileDialog.open();
	}

	private static String[] maskFilters(String extension, String... others) {
		List<String> filters = new ArrayList<>();
		filters.add(maskExtension(extension));
		for (String other : others) {
			filters.add(maskExtension(other));
		}
		String[] array = filters.toArray(new String[filters.size()]);
		return array;
	}

	private static String maskExtension(String extension) {
		return "*." + extension; //$NON-NLS-1$
	}

	public static void createDomainResource(IEclipseContext context, String domain, String perspectiveId) {
		EditingDomainRegistryAccess registryAccess = context.get(EditingDomainRegistryAccess.class);
		ClassifierInitializer initializer = registryAccess.getClassifierInitializer(domain);
		EditingDomainRegistry<?> registry = registryAccess.getDomainRegistry(domain);
		EClass eClass = registry.getContentClassifier();

		Wizard wizard = new CreateFileWizard(context, domain, perspectiveId);
		Shell shell = context.get(Shell.class);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.setTitle(initializer.newObjectTitle());
		dialog.setMessage(initializer.newFileMessage());

		Shell createdShell = dialog.getShell();
		createdShell.setText(initializer.newObjectMessage());
		createdShell.setImage(LicensingImages.getImage(eClass.getName()));

		dialog.open();
	}

	public static void loadDomainResource(IEclipseContext eclipseContext, String domain, String perspectiveId) {
		EditingDomainRegistryAccess access = eclipseContext.get(EditingDomainRegistryAccess.class);
		EditingDomainRegistry<?> registry = access.getDomainRegistry(domain);
		String fileExtension = domain + "_xmi";
		Shell shell = eclipseContext.get(Shell.class);
		String selected = selectLoadPath(shell, fileExtension, "lic_" + domain);
		if (selected == null) {
			return;
		}
		switchPerspective(eclipseContext, perspectiveId);
		registry.registerSource(selected);
	}

	public static void switchPerspective(IEclipseContext eclipseContext, String perspectiveId) {
		EPartService partService = eclipseContext.get(EPartService.class);
		Optional<MPerspective> switched = partService.switchPerspective(perspectiveId);
		if (switched.isPresent()) {
			MPerspective perspective = switched.get();
			String label = perspective.getLocalizedLabel();
			IApplicationContext applicationContext = eclipseContext.get(IApplicationContext.class);
			String brandingName = applicationContext.getBrandingName();
			String title = brandingName + ' ' + '-' + ' ' + label;
			MWindow window = eclipseContext.get(MWindow.class);
			window.setLabel(title);
		}
	}

	public static <C> C selectClassifier(IEclipseContext context, String classifier, String title, Iterable<C> input,
			C initial, Class<C> clazz) {
		Object selected = selectClassifier(context, classifier, title, input, initial);
		if (clazz.isInstance(selected)) {
			return clazz.cast(selected);
		}
		return null;
	}

	public static <C> Object selectClassifier(IEclipseContext context, String classifier, String title,
			Iterable<C> input, C initial) {
		Shell shell = context.get(Shell.class);
		ComposedAdapterFactoryProvider provider = context.get(ComposedAdapterFactoryProvider.class);
		ComposedAdapterFactory factory = provider.getComposedAdapterFactory();
		return selectClassifier(shell, factory, classifier, title, input, initial);
	}

	public static <C> C selectClassifier(Shell shell, ComposedAdapterFactory factory, String classifier, String title,
			Iterable<? extends C> input, C initial, Class<C> clazz) {
		Object selected = selectClassifier(shell, factory, classifier, title, input, initial);
		if (clazz.isInstance(selected)) {
			return clazz.cast(selected);
		}
		return null;
	}

	public static <C> Object selectClassifier(Shell shell, ComposedAdapterFactory factory, String classifier,
			String title, Iterable<? extends C> input, C initial) {
		if (input == null) {
			return null;
		}
		long count = StreamSupport.stream(input.spliterator(), false).count();
		if (count == 0) {
			return null;
		}
		if (count == 1) {
			return input.iterator().next();
		}
		LabelSearchFilter filter = new LabelSearchFilter();

		FilteredSelectionDialog<C> dialog = new FilteredSelectionDialog<C>(shell, false, filter);
		dialog.setTitle(title);
		dialog.setImage(LicensingImages.getImage(classifier));

		dialog.setLabelProvider(new DomainRegistryLabelProvider(factory));
		dialog.setInput(input);
		if (initial != null) {
			dialog.setInitialSelection(initial);
		}
		if (dialog.open() == Dialog.OK) {
			return dialog.getFirstResult().orElse(null);
		}
		return null;
	}

	public static IStatus save(Resource resource) {
		try {
			// FIXME: define parameters
			resource.save(null);
			return Status.OK_STATUS;
		} catch (IOException e) {
			return new Status(IStatus.ERROR, BUNDLE_SYMBOLIC_NAME, "Error saving resource", e);
		}
	}

	public static EditingDomain extractEditingDomain(IEclipseContext context) {
		EditingDomain editingDomain = context.get(EditingDomain.class);
		if (editingDomain != null) {
			return editingDomain;
		}
		IEditingDomainProvider provider = context.get(IEditingDomainProvider.class);
		if (provider != null) {
			return provider.getEditingDomain();
		}
		EditingDomainRegistry<?> registry = context.get(EditingDomainRegistry.class);
		if (registry instanceof IEditingDomainProvider) {
			provider = (IEditingDomainProvider) registry;
			return provider.getEditingDomain();

		}
		return null;
	}

}
