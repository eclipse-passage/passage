/*******************************************************************************
 * Copyright (c) 2018, 2022 ArSysOp
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
package org.eclipse.passage.loc.workbench;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.CreateRoot;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.jface.dialogs.FilteredSelectionDialog;
import org.eclipse.passage.loc.jface.dialogs.LabelSearchFilter;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @since 1.0
 */
@SuppressWarnings("restriction")
public class LocWokbench {

	public static final String COMMAND_VIEW_PERSPECTIVE = "org.eclipse.passage.loc.workbench.command.view.perspective"; //$NON-NLS-1$
	public static final String COMMANDPARAMETER_VIEW_PERSPECTIVE_ID = "org.eclipse.passage.loc.workbench.commandparameter.perspective.id"; //$NON-NLS-1$

	public static final String TOPIC_SHOW = "org/eclipse/passage/loc/workbench/show"; //$NON-NLS-1$

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
		EditingDomainRegistry<?> registry = registryAccess.getDomainRegistry(domain);
		if (new CreateRoot<>(new MandatoryEclipseContext(context), domain, registry.getContentClass()).get()
				.isPresent()) {
			LocWokbench.switchPerspective(context, perspectiveId);
		}
	}

	public static void loadDomainResource(IEclipseContext eclipseContext, String domain, String perspectiveId) {
		EditingDomainRegistryAccess access = eclipseContext.get(EditingDomainRegistryAccess.class);
		EditingDomainRegistry<?> registry = access.getDomainRegistry(domain);
		// FIXME: rework to remove legacy extensions
		String fileExtension = domain + "_xmi"; //$NON-NLS-1$
		Shell shell = eclipseContext.get(Shell.class);
		String selected = selectLoadPath(shell, fileExtension, "lic_" + domain); //$NON-NLS-1$
		if (selected == null) {
			return;
		}
		switchPerspective(eclipseContext, perspectiveId);
		ServiceInvocationResult<Boolean> source = registry.registerSource(URI.createFileURI(selected));
		if (!new NoErrors().test(source.diagnostic())) {
			new DiagnosticDialog(shell, source.diagnostic()).open();
		}
	}

	public static void switchPerspective(IEclipseContext context, String perspectiveId) {
		EPartService partService = context.get(EPartService.class);
		if (partService == null) {
			return;
		}
		Optional<MPerspective> switched = partService.switchPerspective(perspectiveId);
		if (switched.isPresent()) {
			MPerspective perspective = switched.get();
			String label = perspective.getLocalizedLabel();
			IApplicationContext application = context.get(IApplicationContext.class);
			if (application == null) {
				return;
			}
			String branding = application.getBrandingName();
			String title = branding + ' ' + '-' + ' ' + label;
			MWindow window = context.get(MWindow.class);
			window.setLabel(title);
		}
	}

	public static <C> Object selectClassifier(IEclipseContext context, String classifier, String title,
			Iterable<C> input, C initial) {
		Shell shell = context.get(Shell.class);
		return selectClassifier(shell, classifier, title, input, initial);
	}

	/**
	 * @since 1.0
	 */
	public static <C> C selectClassifier(Shell shell, String classifier, String title, Iterable<? extends C> input,
			C initial, Class<C> clazz) {
		Object selected = selectClassifier(shell, classifier, title, input, initial);
		if (clazz.isInstance(selected)) {
			return clazz.cast(selected);
		}
		return null;
	}

	/**
	 * @since 1.0
	 */
	public static <C> Object selectClassifier(Shell shell, String classifier, String title, Iterable<? extends C> input,
			C initial) {
		if (input == null) {
			return null;
		}
		long count = StreamSupport.stream(input.spliterator(), false).count();
		if (count == 0) {
			MessageDialog.openInformation(shell, title, WorkbenchMessages.LocWokbench_e_nothing_to_select);
			return null;
		}
		if (count == 1) {
			return input.iterator().next();
		}
		LabelSearchFilter filter = new LabelSearchFilter();

		FilteredSelectionDialog<C> dialog = new FilteredSelectionDialog<C>(shell, false, filter);
		dialog.setTitle(title);
		dialog.setImage(LicensingImages.getImage(classifier));

		dialog.setLabelProvider(new DomainRegistryLabelProvider());
		dialog.setInput(input);
		if (initial != null) {
			dialog.setInitialSelection(Collections.singletonList(initial));
		}
		if (dialog.open() == Dialog.OK) {
			return dialog.getFirstResult().orElse(null);
		}
		return null;
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
