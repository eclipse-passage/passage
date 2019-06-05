/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.spi.model.VViewFactory;
import org.eclipse.emf.ecp.view.spi.model.VViewModelProperties;
import org.eclipse.emfforms.swt.core.EMFFormsSWTConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.api.access.LicensingRequest;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.loc.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.DashboardUiMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class IssueLicensePackPage extends WizardPage {

	private final IEclipseContext context;
	private LicensePack licensePack;
	private VViewModelProperties viewModelProperties;
	private Composite base;

	protected IssueLicensePackPage(String pageName, IEclipseContext context) {
		super(pageName);
		this.context = context;
		setTitle(DashboardUiMessages.IssueLicensePackPage_page_title);
		setDescription(DashboardUiMessages.IssueLicensePackPage_page_description);
	}

	public void init(LicensingRequest request) {
		if (licensePack != null) {
			licensePack.setPlanIdentifier(request.getPlanIdentifier());
			licensePack.setProductIdentifier(request.getProductIdentifier());
			licensePack.setProductVersion(request.getProductVersion());
			licensePack.setUserIdentifier(request.getUserIdentifier());
			return;
		}
		OperatorLicenseService operatorLicenseService = context.get(OperatorLicenseService.class);
		LicensePackDescriptor licensePackDescriptor = operatorLicenseService.createLicensePack(request);
		if (licensePackDescriptor instanceof LicensePack) {
			licensePack = (LicensePack) licensePackDescriptor;
			licensePack.eAdapters().add(new EContentAdapter() {
				@Override
				public void notifyChanged(Notification notification) {
					setPageComplete(validatePage());
				}
			});
		}
		updatePage();
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		base = new Composite(composite, SWT.NONE);
		base.setLayout(new GridLayout(1, false));
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		viewModelProperties = VViewFactory.eINSTANCE.createViewModelLoadingProperties();
		viewModelProperties.addInheritableProperty(EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_KEY,
				EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_VALUE);
		updatePage();
		setControl(composite);
	}

	private void updatePage() {
		if (base == null || base.isDisposed()) {
			setPageComplete(false);
			return;
		}
		if (licensePack != null) {
			try {
				ECPSWTViewRenderer.INSTANCE.render(base, licensePack, viewModelProperties);
				base.layout();
			} catch (ECPRendererException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setPageComplete(validatePage());
	}

	protected boolean validatePage() {
		String errors = LicensingEcore.extractValidationError(licensePack);
		setErrorMessage(errors);
		return errors == null;
	}

	public LicensePackDescriptor getLicensePack() {
		return licensePack;
	}

}
