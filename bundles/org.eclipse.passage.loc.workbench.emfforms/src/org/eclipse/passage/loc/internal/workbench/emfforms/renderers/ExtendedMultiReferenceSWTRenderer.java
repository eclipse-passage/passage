/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.workbench.emfforms.renderers;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.view.internal.control.multireference.MultiReferenceSWTRenderer;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.renderer.NoPropertyDescriptorFoundExeption;
import org.eclipse.emf.ecp.view.spi.renderer.NoRendererFoundException;
import org.eclipse.emf.ecp.view.spi.util.swt.ImageRegistryService;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.emfforms.spi.swt.core.layout.EMFFormsSWTLayoutUtil;
import org.eclipse.emfforms.spi.swt.core.layout.SWTGridCell;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

@SuppressWarnings("restriction")
public class ExtendedMultiReferenceSWTRenderer extends MultiReferenceSWTRenderer {

	private Composite compact;

	@Inject
	public ExtendedMultiReferenceSWTRenderer(VControl vElement, ViewModelContext viewContext,
			ReportService reportService, EMFFormsDatabinding emfFormsDatabinding,
			EMFFormsLabelProvider emfFormsLabelProvider, VTViewTemplateProvider vtViewTemplateProvider,
			ImageRegistryService imageRegistryService) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider,
				imageRegistryService);
	}

	@Override
	protected Composite createControlComposite(Composite composite) {
		compact = super.createControlComposite(composite);
		return compact;
	}

	protected void updateControlCompositeHeight() {
		if (getTableViewer().getTable().isDisposed()) {
			return;
		}
		int computed = getTableViewer().getTable().computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		int hint = computed > getTableHeightHint() ? getTableHeightHint() : computed;
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).hint(1, hint).applyTo(compact);
	}

	@Override
	protected Control renderControl(SWTGridCell gridCell, Composite parent)
			throws NoRendererFoundException, NoPropertyDescriptorFoundExeption {
		Control control = super.renderControl(gridCell, parent);
		TableViewer viewer = getTableViewer();
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (getVElement().isEffectivelyReadonly()) {
					handleDoubleClick(
							(EObject) IStructuredSelection.class.cast(event.getSelection()).getFirstElement());
				}
			}

		});
		addRelayoutListener();
		viewer.refresh(true);
		updateControlCompositeHeight();
		return control;
	}

	private void addRelayoutListener() {
		// FIXME: AF: who is removing these listeners?
		final IObservableList<?> list = IObservableList.class.cast(getTableViewer().getInput());
		list.addListChangeListener(new IListChangeListener<Object>() {
			@Override
			public void handleListChange(ListChangeEvent<?> event) {
				adjustSize();
			}
		});
	}

	private void adjustSize() {
		updateControlCompositeHeight();
		EMFFormsSWTLayoutUtil.adjustParentSize(compact);
	}

	@Override
	protected void rootDomainModelChanged() throws DatabindingFailedException {
		super.rootDomainModelChanged();
		updateButtonEnabling();
		addRelayoutListener();
		adjustSize();
	}

}
