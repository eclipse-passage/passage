/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.workbench.emfforms.parts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecp.common.spi.ChildrenDescriptorCollector;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfforms.internal.swt.treemasterdetail.defaultprovider.DefaultDeleteActionBuilder;
import org.eclipse.emfforms.spi.swt.treemasterdetail.MenuProvider;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeMasterDetailComposite;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeMasterDetailMenuListener;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeMasterDetailSWTFactory;
import org.eclipse.emfforms.spi.swt.treemasterdetail.actions.ActionCollector;
import org.eclipse.emfforms.spi.swt.treemasterdetail.actions.MasterDetailAction;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.CreateElementCallback;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.passage.lic.emf.resource.ExtractEObject;
import org.eclipse.passage.lic.emf.resource.ExtractResource;
import org.eclipse.passage.loc.internal.workbench.emfforms.i18n.WorkbenchEmfformsMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

public class DetailsView {

	private final MPart part;

	private Composite content;

	// TreeMasterDetailComposite implies the Resource has root EObject
	private final List<EObject> root;

	private final CommandStackListener dirtyStackListener;
	private final ISelectionChangedListener selectionChangedListener;
	private CommandStack commandStack;

	@Inject
	public DetailsView(MPart part, ESelectionService selectionService) {
		root = new ArrayList<>();
		this.part = part;
		this.dirtyStackListener = e -> {
			Object source = e.getSource();
			if (source instanceof BasicCommandStack) {
				BasicCommandStack stack = (BasicCommandStack) source;
				part.setDirty(stack.isSaveNeeded());
			}
		};
		this.selectionChangedListener = e -> {
			ISelection selection = e.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structured = (IStructuredSelection) selection;
				selectionService.setSelection(structured.getFirstElement());
			}
		};
	}

	@PostConstruct
	public void createComposite(Composite parent) {
		content = new Composite(parent, SWT.NONE);
		content.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		content.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
		content.setLayoutData(GridDataFactory.fillDefaults().create());
	}

	@Inject
	@Optional
	protected void subscribeTopicShow(@UIEventTopic(LocWokbench.TOPIC_SHOW) Notifier input, IEclipseContext context) {
		show(input, context);
	}

	protected void show(Notifier input, IEclipseContext context) {
		if (content == null || content.isDisposed()) {
			return;
		}
		if (input == null) {
			return;
		}
		root.clear();
		new ExtractEObject().apply(input).ifPresent(root::add);
		java.util.Optional<Resource> resource = new ExtractResource().apply(input);
		configurePart(resource, context);
		Control[] children = content.getChildren();
		for (Control control : children) {
			control.dispose();
		}
		if (!root.isEmpty() && resource.isPresent()) {
			try {
				TreeMasterDetailComposite rootView = createRootView(content, resource.get(), getCreateElementCallback(),
						context);
				TreeViewer viewer = rootView.getSelectionProvider();
				viewer.addSelectionChangedListener(selectionChangedListener);
				viewer.refresh();
				EObject reveal = root.get(0);
				while (reveal != null) {
					viewer.reveal(reveal);
					if (viewer.testFindItem(reveal) != null) {
						break;
					}
					reveal = reveal.eContainer();
				}
				if (reveal == null) {
					return;
				}

				rootView.setSelection(new StructuredSelection(reveal));
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		content.layout();
	}

	protected TreeMasterDetailComposite createRootView(Composite parent, Object editorInput,
			CreateElementCallback createElementCallback, IEclipseContext context) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		composite.setLayout(new FormLayout());

		final FormData treeMasterDetailLayoutData = new FormData();
		treeMasterDetailLayoutData.top = new FormAttachment(0);
		treeMasterDetailLayoutData.left = new FormAttachment(0);
		treeMasterDetailLayoutData.right = new FormAttachment(100);
		treeMasterDetailLayoutData.bottom = new FormAttachment(100);
		final TreeMasterDetailComposite treeMasterDetail = createTreeMasterDetail(composite, editorInput,
				createElementCallback);
		treeMasterDetail.setLayoutData(treeMasterDetailLayoutData);
		TreeViewer viewer = treeMasterDetail.getSelectionProvider();
		customizedLabelProvider().ifPresent(viewer::setLabelProvider);
		customizedContentProvider().ifPresent(viewer::setContentProvider);
		viewer.setLabelProvider(new DomainRegistryLabelProvider());

		return treeMasterDetail;
	}

	protected java.util.Optional<LabelProvider> customizedLabelProvider() {
		return java.util.Optional.of(new DomainRegistryLabelProvider());
	}

	protected java.util.Optional<IStructuredContentProvider> customizedContentProvider() {
		return java.util.Optional.empty();
	}

	protected TreeMasterDetailComposite createTreeMasterDetail(final Composite composite, Object editorInput,
			final CreateElementCallback createElementCallback) {
		ChildrenDescriptorCollector childrenDescriptorCollector = createChildrenDescriptorCollector();
		MenuProvider menuProvider = createMenuProvider(childrenDescriptorCollector, createElementCallback);
		final TreeMasterDetailComposite treeMasterDetail = TreeMasterDetailSWTFactory
				.fillDefaults(composite, SWT.NONE, editorInput).customizeCildCreation(createElementCallback)
				.customizeMenu(menuProvider).create();
		return treeMasterDetail;
	}

	protected MenuProvider createMenuProvider(ChildrenDescriptorCollector childrenDescriptorCollector,
			final CreateElementCallback createElementCallback) {
		MenuProvider menuProvider = new MenuProvider() {
			@Override
			public Menu getMenu(TreeViewer treeViewer, EditingDomain editingDomain) {
				final MenuManager menuMgr = new MenuManager();
				menuMgr.setRemoveAllWhenShown(true);
				final List<MasterDetailAction> masterDetailActions = ActionCollector.newList()
						.addCutAction(editingDomain).addCopyAction(editingDomain).addPasteAction(editingDomain)
						.getList();
				menuMgr.addMenuListener(new TreeMasterDetailMenuListener(childrenDescriptorCollector, menuMgr,
						treeViewer, editingDomain, masterDetailActions, createElementCallback,
						new DefaultDeleteActionBuilder()));
				final Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
				return menu;

			}
		};
		return menuProvider;
	}

	protected void configurePart(java.util.Optional<Resource> resource, IEclipseContext context) {
		resource.//
				flatMap(new ExtractEObject())//
				.map(AdapterFactoryEditingDomain::getEditingDomainFor)//
				.filter(AdapterFactoryEditingDomain.class::isInstance)//
				.map(AdapterFactoryEditingDomain.class::cast)//
				.ifPresent(d -> configureCommandStack(d, context));
		if (!resource.isEmpty()) {
			URI uri = resource.get().getURI();
			if (uri != null) {
				part.setLabel(uri.lastSegment());
				part.setTooltip(String.valueOf(uri));
			}
		} else {
			part.setLabel(WorkbenchEmfformsMessages.DetailsView_label_details);
		}
	}

	protected void configureCommandStack(AdapterFactoryEditingDomain domain, IEclipseContext context) {
		AdapterFactory adapters = domain.getAdapterFactory();
		context.set(EditingDomain.class, domain);
		context.set(AdapterFactory.class, adapters);
		if (commandStack == null) {
			commandStack = domain.getCommandStack();
			commandStack.addCommandStackListener(dirtyStackListener);
		}
		commandStack.flush();
	}

	@PreDestroy
	public void dispose() {
		if (commandStack != null) {
			commandStack.removeCommandStackListener(dirtyStackListener);
		}
	}

	@Persist
	public void save() {
		if (root.isEmpty()) {
			part.setDirty(false);
			return;
		}
		Resource eResource = root.get(0).eResource();
		if (eResource != null) {
			try {
				eResource.save(new HashMap<>());
				if (commandStack instanceof BasicCommandStack) {
					BasicCommandStack basicCommandStack = (BasicCommandStack) commandStack;
					basicCommandStack.saveIsDone();
					part.setDirty(basicCommandStack.isSaveNeeded());
				}
			} catch (IOException e) {
				String message = WorkbenchEmfformsMessages.DetailsView_e_save_title;
				IStatus error = new Status(IStatus.ERROR, getClass(), message, e);
				ErrorDialog.openError(content.getShell(), message, message, error);
			}
		}
	}

	/**
	 * 
	 * @return
	 * 
	 * @since 0.5.0
	 */
	protected ChildrenDescriptorCollector createChildrenDescriptorCollector() {
		return new ChildrenDescriptorCollector();
	}

	protected CreateElementCallback getCreateElementCallback() {
		return null;
	}

}
