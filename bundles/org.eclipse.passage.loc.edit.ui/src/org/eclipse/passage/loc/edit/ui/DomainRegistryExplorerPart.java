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
package org.eclipse.passage.loc.edit.ui;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.loc.internal.emf.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.registry.UnregisterAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchActionConstants;

public class DomainRegistryExplorerPart {

	private final LocDomainRegistryAccess access;

	private TableViewer viewer;

	@Inject
	public DomainRegistryExplorerPart(IEclipseContext context) {
		access = (LocDomainRegistryAccess) context.get(EditingDomainRegistryAccess.class);
	}

	@PostConstruct
	public void postConstruct(Composite parent, IEclipseContext context) {

		Composite area = new Composite(parent, SWT.NONE);
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setLayout(new GridLayout(1, false));
		viewer = createRegistryTree(area);
		ESelectionService selectionService = context.get(ESelectionService.class);
		viewer.addSelectionChangedListener(e -> {
			ISelection selection = e.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structured = (IStructuredSelection) selection;
				if (structured.size() == 1) {
					selectionService.setSelection(structured.getFirstElement());
				} else {
					selectionService.setSelection(structured.toArray());
				}
			}
		});
		update(access.domainRegistryList());
	}

	private TableViewer createRegistryTree(Composite area) {
		TableViewer created = new TableViewer(area);
		created.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		created.setContentProvider(ArrayContentProvider.getInstance());
		created.setLabelProvider(new DomainRegistryLabelProvider(access));
		createContextMenu(created.getControl());
		return created;
	}

	@Inject
	@Optional
	public void changed(@SuppressWarnings("unused") @UIEventTopic("org/eclipse/passage/lic/*") Object unused) {
		update(access.domainRegistryList());
	}

	private void update(List<EditingDomainRegistry<?>> list) {
		if (viewer == null && viewer.getControl().isDisposed()) {
			return;
		}
		viewer.setInput(list.stream()//
				.filter(BaseDomainRegistry.class::isInstance)//
				.map(BaseDomainRegistry.class::cast).map(BaseDomainRegistry::getEditingDomain)//
				.map(EditingDomain::getResourceSet)//
				.flatMap(rs -> rs.getResources().stream())//
				.filter(r -> r.getURI() != null)//
				.sorted((r1, r2) -> r1.getURI().toString().compareTo(r2.getURI().toString()))//
				.collect(Collectors.toList()));
	}

	private void createContextMenu(Control control) {
		MenuManager contextMenu = new MenuManager("#ViewerMenu"); //$NON-NLS-1$
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this::fillContextMenu);
		control.setMenu(contextMenu.createContextMenu(control));
	}

	private void fillContextMenu(IMenuManager contextMenu) {
		contextMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		contextMenu.add(new UnregisterAction(access, //
				() -> viewer.getStructuredSelection().toArray(), //
				() -> viewer.getControl().getShell()));
	}

}
