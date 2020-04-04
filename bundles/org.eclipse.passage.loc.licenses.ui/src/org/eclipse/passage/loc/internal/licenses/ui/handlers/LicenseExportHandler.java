/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.inject.Named;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.access.LicensingRequest;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.internal.users.ui.SelectUser;
import org.eclipse.passage.loc.internal.users.ui.SelectUserOrigin;
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
		ProductRegistry productRegistry = context.get(ProductRegistry.class);
		ComposedAdapterFactoryProvider provider = context.get(ComposedAdapterFactoryProvider.class);
		java.util.Optional<UserDescriptor> user = new SelectInner<UserDescriptor, UserOriginDescriptor>(
				new SelectUser(context).get(), new SelectUserOrigin(context).get(), context).get();
		if (!user.isPresent()) {
			return;
		}
		ProductVersionDescriptor productVersion = ProductsUi.selectProductVersionDescriptor(shell, provider,
				productRegistry, null);
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
		Date from = Date.from(fromLocal.atZone(ZoneId.systemDefault()).toInstant());
		Date until = Date.from(untilLocal.atZone(ZoneId.systemDefault()).toInstant());

		LicensingRequest request = createLicensingRequest(user.get(), licensePlan, productVersion, from, until);

		LicensePackDescriptor licensePack = licenseService.createLicensePack(request);

		LicensingResult result = licenseService.issueLicensePack(request, licensePack);
		if (result.getSeverity() == LicensingResult.OK) {
			MessageDialog.openInformation(shell, LicensesUiMessages.LicenseExportHandler_success_title,
					result.getMessage());
			Object attached = result.getAttachment(UsersPackage.eINSTANCE.getUserLicense().getName());
			if (attached instanceof UserLicense) {
				UserLicense userLicense = (UserLicense) attached;
				String perspectiveId = UsersUi.PERSPECTIVE_MAIN;
				LocWokbench.switchPerspective(context, perspectiveId);
				IEventBroker broker = context.get(IEventBroker.class);
				broker.post(LocWokbench.TOPIC_SHOW, userLicense);
			}
		} else {
			IStatus status = LicensingEquinox.toStatus(result);
			ErrorDialog.openError(shell, LicensesUiMessages.LicenseExportHandler_error_title,
					LicensesUiMessages.LicenseExportHandler_error_message, status);
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

	private LicensingRequest createLicensingRequest(UserDescriptor userDescriptor, LicensePlanDescriptor licensePlan,
			ProductVersionDescriptor productVersion, Date from, Date until) {
		String uuid = UUID.randomUUID().toString();
		Date creationDate = new Date();
		return new LicensingRequest() {

			@Override
			public Date getValidUntil() {
				return until;
			}

			@Override
			public Date getValidFrom() {
				return from;
			}

			@Override
			public String getUserIdentifier() {
				return userDescriptor.getEmail();
			}

			@Override
			public String getUserFullName() {
				return userDescriptor.getFullName();
			}

			@Override
			public String getProductVersion() {
				return productVersion.getVersion();
			}

			@Override
			public String getProductIdentifier() {
				return productVersion.getProduct().getIdentifier();
			}

			@Override
			public String getPlanIdentifier() {
				return licensePlan.getIdentifier();
			}

			@Override
			public String getIdentifier() {
				return uuid;
			}

			@Override
			public Date getCreationDate() {
				return creationDate;
			}

			@Override
			public String getConditionType() {
				return userDescriptor.getPreferredConditionType();
			}

			@Override
			public String getConditionExpression() {
				return userDescriptor.getPreferredConditionExpression();
			}
		};
	}

}