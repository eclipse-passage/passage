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
package org.eclipse.passage.loc.licenses.ui.handlers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.inject.Named;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.loc.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
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
		UserRegistry userRegistry = context.get(UserRegistry.class);
		ProductRegistry productRegistry = context.get(ProductRegistry.class);
		ComposedAdapterFactoryProvider provider = context.get(ComposedAdapterFactoryProvider.class);
		UserDescriptor userDescriptor = UsersUi.selectUserDescriptor(shell, provider, userRegistry, null);
		if (userDescriptor == null) {
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

		LicensePack licensePack = createLicensePack(licensePlan, userDescriptor, productVersion, from, until);

		if (userDescriptor instanceof User) {
			User user = (User) userDescriptor;
			String conditionType = userDescriptor.getPreferredConditionType();
			String expression = userDescriptor.getPreferredConditionExpression();
			String productIdentifier = productVersion.getProduct().getIdentifier();
			UserLicense userLicense = UsersFactory.eINSTANCE.createUserLicense();
			userLicense.setPlanIdentifier(licensePlan.getIdentifier());
			userLicense.setValidFrom(from);
			userLicense.setValidUntil(until);
			userLicense.setConditionExpression(expression);
			userLicense.setConditionType(conditionType);
			userLicense.setProductIdentifier(productIdentifier);
			userLicense.setProductVersion(productVersion.getVersion());
			if (userRegistry instanceof IEditingDomainProvider) {
				IEditingDomainProvider edp = (IEditingDomainProvider) userRegistry;
				EditingDomain editingDomain = edp.getEditingDomain();
				EReference structured = UsersPackage.eINSTANCE.getUser_UserLicenses();
				CommandStack stack = editingDomain.getCommandStack();
				stack.execute(AddCommand.create(editingDomain, user, structured, userLicense));
			} else {
				user.getUserLicenses().add(userLicense);
			}
			LocWokbench.save(user.eResource());
		}

		IStatus status = licenseService.issueLicensePack(licensePack);
		if (status.isOK()) {
			MessageDialog.openInformation(shell, LicensesUiMessages.LicenseExportHandler_success_title,
					status.getMessage());
		} else {
			ErrorDialog.openError(shell, LicensesUiMessages.LicenseExportHandler_error_title,
					LicensesUiMessages.LicenseExportHandler_error_message, status);
		}
	}

	private LicensePack createLicensePack(LicensePlanDescriptor licensePlan, UserDescriptor userDescriptor,
			ProductVersionDescriptor productVersion, Date from, Date until) {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicensePack licensePack = licenseFactory.createLicensePack();
		String productIdentifier = productVersion.getProduct().getIdentifier();
		licensePack.setProductIdentifier(productIdentifier);
		licensePack.setProductVersion(productVersion.getVersion());
		licensePack.setUserIdentifier(userDescriptor.getEmail());
		EList<LicenseGrant> grants = licensePack.getLicenseGrants();
		Iterable<? extends LicensePlanFeatureDescriptor> features = licensePlan.getLicensePlanFeatures();
		String conditionType = userDescriptor.getPreferredConditionType();
		String expression = userDescriptor.getPreferredConditionExpression();
		for (LicensePlanFeatureDescriptor planFeature : features) {
			LicenseGrant grant = createLicenseGrant(planFeature, from, until, conditionType, expression);
			grants.add(grant);
		}
		return licensePack;
	}

	private LicenseGrant createLicenseGrant(LicensePlanFeatureDescriptor planFeature, Date from, Date until,
			String conditionType, String expression) {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicenseGrant grant = licenseFactory.createLicenseGrant();
		grant.setFeatureIdentifier(planFeature.getFeatureIdentifier());
		grant.setMatchVersion(planFeature.getMatchVersion());
		grant.setMatchRule(planFeature.getMatchRule());
		grant.setCapacity(1);
		grant.setConditionExpression(expression);
		grant.setConditionType(conditionType);
		grant.setValidFrom(from);
		grant.setValidUntil(until);
		return grant;
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

}