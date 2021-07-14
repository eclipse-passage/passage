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
package org.eclipse.passage.loc.internal.licenses.ui.handlers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.licenses.core.request.PersonalLicenseData;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.ui.SelectUser;
import org.eclipse.passage.loc.internal.users.ui.SelectUserOrigin;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.internal.workbench.SelectInner;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.users.ui.UsersUi;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

//FIXME: should be moved to reduce dependencies
public class LicenseExportHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) LicensePlanDescriptor licensePlan,
			IEclipseContext context) {
		OperatorLicenseService licenseService = context.get(OperatorLicenseService.class);
		Shell shell = context.get(Shell.class);
		MandatoryEclipseContext resolution = new MandatoryEclipseContext(context);
		java.util.Optional<UserDescriptor> user = new SelectInner<UserDescriptor, UserOriginDescriptor>(
				new SelectUser(resolution).get(), new SelectUserOrigin(resolution).get(), resolution).get();
		if (!user.isPresent()) {
			return;
		}
		ProductRegistry productRegistry = context.get(ProductRegistry.class);
		ProductVersionDescriptor productVersion = ProductsUi.selectProductVersionDescriptor(shell, productRegistry,
				null);
		if (productVersion == null) {
			return;
		}
		long months = 12;
		InputDialog durationDialog = new InputDialog(shell, LicensesUiMessages.LicenseExportHandler_period_title,
				LicensesUiMessages.LicenseExportHandler_period_message, String.valueOf(months), new IInputValidator() {
					@Override
					public String isValid(String newText) {
						String invalidInput = LicensesUiMessages.LicenseExportHandler_e_period_invalid;
						try {
							long parsed = Long.parseLong(newText);
							if (parsed <= 0) {
								return invalidInput;
							}
						} catch (Exception e) {
							return invalidInput;
						}
						return null;
					}
				});
		if (durationDialog.open() != Window.OK) {
			return;
		}
		months = Long.parseLong(durationDialog.getValue());
		LocalDateTime fromLocal = LocalDateTime.now();
		LocalDateTime untilLocal = fromLocal.plusMonths(months);

		PersonalLicenseRequest request = createLicensingRequest(user.get(), licensePlan, productVersion,
				fromLocal.toLocalDate(), untilLocal.toLocalDate());

		PersonalLicensePackDescriptor licensePack = licenseService.createLicensePack(request);

		ServiceInvocationResult<IssuedLicense> result = licenseService.issueLicensePack(request, licensePack);
		if (new NoSevereErrors().test(result.diagnostic()) && result.data().isPresent()) {
			MessageDialog.openInformation(shell, LicensesUiMessages.LicenseExportHandler_success_title,
					String.format(LicensesUiMessages.LicenseExportHandler_success_description, //
							result.data().get().encrypted().toAbsolutePath().toString(), //
							result.data().get().decrypted().toAbsolutePath().toString()));
			PersonalLicensePack userLicense = result.data().get().license();
			String perspectiveId = UsersUi.PERSPECTIVE_MAIN;
			LocWokbench.switchPerspective(context, perspectiveId);
			IEventBroker broker = context.get(IEventBroker.class);
			broker.post(LocWokbench.TOPIC_SHOW, userLicense);
		} else {
			new DiagnosticDialog(shell, result.diagnostic()).open();
		}
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional LicensePlanDescriptor licensePlan,
			IEclipseContext context) {
		OperatorLicenseService licenseService = context.get(OperatorLicenseService.class);
		if (licenseService == null) {
			return false;
		}
		return licensePlan != null;
	}

	private PersonalLicenseRequest createLicensingRequest(UserDescriptor user, LicensePlanDescriptor plan,
			ProductVersionDescriptor product, LocalDate from, LocalDate until) {
		return new PersonalLicenseData(() -> user, () -> plan, () -> product, () -> from, () -> until);
	}

}