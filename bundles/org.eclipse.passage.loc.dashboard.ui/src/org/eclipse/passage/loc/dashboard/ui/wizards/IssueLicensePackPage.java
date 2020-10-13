/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.spi.model.VViewFactory;
import org.eclipse.emf.ecp.view.spi.model.VViewModelProperties;
import org.eclipse.emfforms.swt.core.EMFFormsSWTConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.loc.internal.api.LicensingRequest;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class IssueLicensePackPage extends WizardPage {

	private final IEclipseContext context;
	private final Supplier<LicensingRequest> data;
	private LicensePack license;
	private VViewModelProperties properties;
	private Composite base;

	protected IssueLicensePackPage(String name, Supplier<LicensingRequest> data, IEclipseContext context) {
		super(name);
		this.context = context;
		this.data = data;
		setTitle(IssueLicensePageMessages.IssueLicensePackPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicensePackPage_page_description);
	}

	public void init() {
		LicensingRequest request = data.get();
		if (license != null) {
			license.setPlanIdentifier(request.getPlanIdentifier());
			license.setProductIdentifier(request.getProductIdentifier());
			license.setProductVersion(request.getProductVersion());
			license.setUserIdentifier(request.getUserIdentifier());
			EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
			for (LicenseGrant licenseGrant : licenseGrants) {
				licenseGrant.setValidFrom(request.getValidFrom());
				licenseGrant.setValidUntil(request.getValidUntil());
			}
			return;
		}
		OperatorLicenseService operatorLicenseService = context.get(OperatorLicenseService.class);
		LicensePackDescriptor licensePackDescriptor = operatorLicenseService.createLicensePack(request);
		if (licensePackDescriptor instanceof LicensePack) {
			license = (LicensePack) licensePackDescriptor;
			license.eAdapters().add(new EContentAdapter() {
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
		setControl(composite);
		base = new Composite(composite, SWT.NONE);
		base.setLayout(new GridLayout(1, false));
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		properties = VViewFactory.eINSTANCE.createViewModelLoadingProperties();
		properties.addInheritableProperty(EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_KEY,
				EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_VALUE);
		updatePage();
		Dialog.applyDialogFont(composite);
	}

	private void updatePage() {
		if (base == null || base.isDisposed()) {
			setPageComplete(false);
			return;
		}
		if (license != null) {
			try {
				ECPSWTViewRenderer.INSTANCE.render(base, license, properties);
				base.layout();
			} catch (ECPRendererException e) {
			}
		}
		setPageComplete(validatePage());
	}

	protected boolean validatePage() {
		String errors = LicensingEcore.extractValidationError(license);
		setErrorMessage(errors);
		return errors == null;
	}

	public LicensePackDescriptor pack() {
		return license;
	}

}
