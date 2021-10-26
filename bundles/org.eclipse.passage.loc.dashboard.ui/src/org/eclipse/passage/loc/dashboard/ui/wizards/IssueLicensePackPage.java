/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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

import java.util.Optional;
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
import org.eclipse.passage.lic.emf.validation.ErrorMessages;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

class IssueLicensePackPage extends WizardPage {

	private final IEclipseContext context;
	private final Supplier<PersonalLicenseRequest> data;
	private final ErrorMessages validate;
	private PersonalLicensePack license;
	private VViewModelProperties properties;
	private Composite base;

	protected IssueLicensePackPage(String name, Supplier<PersonalLicenseRequest> data, IEclipseContext context) {
		super(name);
		this.context = context;
		this.data = data;
		this.validate = new ErrorMessages();
		setTitle(IssueLicensePageMessages.IssueLicensePackPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicensePackPage_page_description);
	}

	void init() {
		PersonalLicenseRequest request = data.get();
		if (license != null) {
			refillFormRequest(request);
		} else {
			createFormRequest(request);
		}
		buildPage();
	}

	private void createFormRequest(PersonalLicenseRequest request) {
		OperatorLicenseService service = context.get(OperatorLicenseService.class);
		PersonalLicensePackDescriptor descriptor = service.createLicensePack(request);
		if (descriptor instanceof PersonalLicensePack) {
			license = (PersonalLicensePack) descriptor;
			license.eAdapters().add(new EContentAdapter() {
				@Override
				public void notifyChanged(Notification notification) {
					setPageComplete(validatePage());
				}
			});
		}
	}

	private void refillFormRequest(PersonalLicenseRequest request) {
		license.getLicense().setPlan(request.plan());
		license.getLicense().getProduct().setIdentifier(request.productIdentifier());
		license.getLicense().getProduct().setVersion(request.productVersion());
		license.getLicense().getUser().setIdentifier(request.user());
		EList<PersonalFeatureGrant> grants = license.getGrants();
		for (PersonalFeatureGrant grant : grants) {
			ValidityPeriodClosed valid = (ValidityPeriodClosed) grant.getValid();
			valid.setFrom(request.validFrom());
			valid.setUntil(request.validUntil());
		}
		ValidityPeriodClosed valid = (ValidityPeriodClosed) license.getLicense().getValid();
		valid.setFrom(request.validFrom());
		valid.setUntil(request.validUntil());
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
		buildPage();
		Dialog.applyDialogFont(composite);
	}

	private void buildPage() {
		if (base == null || base.isDisposed()) {
			setPageComplete(false);
			return;
		}
		if (license != null) {
			// FIXME: AF: rework this hotfix for
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=576904
			Control[] children = base.getChildren();
			for (Control control : children) {
				control.dispose();
			}
			try {
				ECPSWTViewRenderer.INSTANCE.render(base, license, properties);
				base.layout();
			} catch (ECPRendererException e) {
			}
		}
		setPageComplete(validatePage());
	}

	protected boolean validatePage() {
		Optional<String> errors = validate.apply(license);
		setErrorMessage(errors.orElse(null));// framework requires null
		return errors.isEmpty();
	}

	PersonalLicensePackDescriptor pack() {
		return license;
	}

}
