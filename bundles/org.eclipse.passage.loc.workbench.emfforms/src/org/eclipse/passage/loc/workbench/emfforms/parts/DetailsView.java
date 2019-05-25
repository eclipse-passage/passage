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
package org.eclipse.passage.loc.workbench.emfforms.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
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
	private EObject root;

	private final CommandStackListener dirtyStackListener;
	private final ISelectionChangedListener selectionChangedListener;
	private CommandStack commandStack;

	@Inject
	public DetailsView(MPart part, ESelectionService selectionService) {
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
		this.root = LicensingEcore.extractEObject(input);
		Resource resource = LicensingEcore.extractResource(input);
		configurePart(resource, context);
		Control[] children = content.getChildren();
		for (Control control : children) {
			control.dispose();
		}
		if (this.root != null) {
			try {
				TreeMasterDetailComposite rootView = createRootView(content, resource, getCreateElementCallback(),
						context);
				TreeViewer selectionProvider = rootView.getSelectionProvider();
				selectionProvider.addSelectionChangedListener(selectionChangedListener);
				selectionProvider.refresh();
				EObject objectToReveal = this.root;
				while (objectToReveal != null) {
					selectionProvider.reveal(objectToReveal);
					if (selectionProvider.testFindItem(objectToReveal) != null) {
						break;
					}
					objectToReveal = objectToReveal.eContainer();
				}
				if (objectToReveal == null) {
					return;
				}

				rootView.setSelection(new StructuredSelection(objectToReveal));
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
		TreeViewer selectionProvider = treeMasterDetail.getSelectionProvider();
		AdapterFactory adapterFactory = context.get(ComposedAdapterFactoryProvider.class).getComposedAdapterFactory();
		selectionProvider.setLabelProvider(new DomainRegistryLabelProvider(adapterFactory));
		return treeMasterDetail;
	}

	protected TreeMasterDetailComposite createTreeMasterDetail(final Composite composite, Object editorInput,
			final CreateElementCallback createElementCallback) {
		MenuProvider menuProvider = createMenuProvider(createElementCallback);
		final TreeMasterDetailComposite treeMasterDetail = TreeMasterDetailSWTFactory
				.fillDefaults(composite, SWT.NONE, editorInput).customizeCildCreation(createElementCallback)
				.customizeMenu(menuProvider).create();
		return treeMasterDetail;
	}

	protected MenuProvider createMenuProvider(final CreateElementCallback createElementCallback) {
		MenuProvider menuProvider = new MenuProvider() {
			@Override
			public Menu getMenu(TreeViewer treeViewer, EditingDomain editingDomain) {
				final MenuManager menuMgr = new MenuManager();
				menuMgr.setRemoveAllWhenShown(true);
				final List<MasterDetailAction> masterDetailActions = ActionCollector.newList()
						.addCutAction(editingDomain).addCopyAction(editingDomain).addPasteAction(editingDomain)
						.getList();
				menuMgr.addMenuListener(new TreeMasterDetailMenuListener(new ChildrenDescriptorCollector(), menuMgr,
						treeViewer, editingDomain, masterDetailActions, createElementCallback,
						new DefaultDeleteActionBuilder()));
				final Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
				return menu;

			}
		};
		return menuProvider;
	}

	protected void configurePart(Resource resource, IEclipseContext context) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(LicensingEcore.extractEObject(resource));
		context.set(EditingDomain.class, editingDomain);
		if (editingDomain instanceof AdapterFactoryEditingDomain) {
			AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory();
			context.set(AdapterFactory.class, adapterFactory);
			if (commandStack == null) {
				commandStack = editingDomain.getCommandStack();
				commandStack.addCommandStackListener(dirtyStackListener);
			}
			commandStack.flush();
		}
		if (resource != null) {
			URI uri = resource.getURI();
			if (uri != null) {
				part.setLabel(uri.lastSegment());
				part.setTooltip(String.valueOf(uri));
			}
		} else {
			part.setLabel(WorkbenchEmfformsMessages.DetailsView_label_details);
		}
	}

	@PreDestroy
	public void dispose() {
		if (commandStack != null) {
			commandStack.removeCommandStackListener(dirtyStackListener);
		}
	}

	@Persist
	public void save() {
		if (root == null) {
			part.setDirty(false);
			return;
		}
		Resource eResource = root.eResource();
		if (eResource != null) {
			IStatus status = LocWokbench.save(eResource);
			if (status.isOK()) {
				if (commandStack instanceof BasicCommandStack) {
					BasicCommandStack basicCommandStack = (BasicCommandStack) commandStack;
					basicCommandStack.saveIsDone();
					part.setDirty(basicCommandStack.isSaveNeeded());
				}
			}
		}
	}

	protected CreateElementCallback getCreateElementCallback() {
		return null;
	}

}
