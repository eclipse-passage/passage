/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.api.CodecSupplier;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.OperatorEvents;
import org.eclipse.passage.loc.internal.api.OperatorLicenseEvents;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseIssuingFailed;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseIssuingIsPartial;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseValidationFailed;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistryEvents;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
final class IssuePersonalLicense {

	private final UserRegistry users;
	private final ProductRegistry products;
	private final OperatorProductService operator;
	private final EventAdmin events;

	IssuePersonalLicense(UserRegistry users, ProductRegistry products, OperatorProductService operator,
			EventAdmin events) {
		this.users = users;
		this.products = products;
		this.operator = operator;
		this.events = events;
	}

	ServiceInvocationResult<IssuedLicense> issue(PersonalLicenseRequest request, Supplier<LicensePack> template) {
		LicensePack license = EcoreUtil.copy(template.get());
		String errors = LicensingEcore.extractValidationError(license);
		if (errors != null) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseValidationFailed(), errors));
		}
		LicensedProduct product = new BaseLicensedProduct(license.getProductIdentifier(), license.getProductVersion());
		Date issueDate = new Date();
		license.setIdentifier(UUID.randomUUID().toString());
		license.setIssueDate(issueDate);
		new AssignGrantIdentifiers().accept(license);
		String userIdentifier = license.getUserIdentifier();
		UserDescriptor userDescriptor = users.getUser(userIdentifier);
		Map<String, Object> attachments = new HashMap<String, Object>();
		UserLicense userLicense = null;
		if (userDescriptor instanceof User) {
			User user = (User) userDescriptor;
			String conditionType = userDescriptor.getPreferredConditionType();
			String expression = userDescriptor.getPreferredConditionExpression();
			userLicense = UsersFactory.eINSTANCE.createUserLicense();
			userLicense.setPackIdentifier(license.getIdentifier());
			userLicense.setIssueDate(issueDate);
			userLicense.setPlanIdentifier(request.plan());
			userLicense.setValidFrom(request.validFrom());
			userLicense.setValidUntil(request.validUntil());
			userLicense.setConditionExpression(expression);
			userLicense.setConditionType(conditionType);
			userLicense.setProductIdentifier(product.identifier());
			userLicense.setProductVersion(product.version());
			if (users instanceof IEditingDomainProvider) {
				IEditingDomainProvider edp = (IEditingDomainProvider) users;
				EditingDomain editingDomain = edp.getEditingDomain();
				EReference structured = UsersPackage.eINSTANCE.getUser_UserLicenses();
				CommandStack stack = editingDomain.getCommandStack();
				stack.execute(AddCommand.create(editingDomain, user, structured, userLicense));
			} else {
				user.getUserLicenses().add(userLicense);
			}
			events.postEvent(OperatorEvents.create(UserRegistryEvents.USER_LICENSE_CREATE, userLicense));
			try {
				// FIXME: define parameters
				user.eResource().save(null);
				attachments.put(userLicense.eClass().getName(), userLicense);
			} catch (IOException e) {
				return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
						LicensesCoreMessages.LicenseOperatorServiceImpl_error_io, e));
			}
		}
		Path path = new UserHomeProductResidence(product).get();
		Path decrypted;
		try {
			decrypted = new EmfObjectPersisted(path, license)//
					.write(license.getIdentifier() + new PassageFileExtension.LicenseDecrypted().get());
			events.postEvent(OperatorLicenseEvents.decodedIssued(decrypted.toString()));
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_save_decoded, e));
		}

		Optional<StreamCodec> codec = new CodecSupplier(product).get();
		if (!codec.isPresent()) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingIsPartial(), //
					String.format(LicensesCoreMessages.LicenseOperatorServiceImpl_w_no_encoding, decrypted)));
		}

		Path privateKeyPath = path.resolve(//
				new FileNameFromLicensedProduct(product, new PassageFileExtension.PrivateKey()).get());
		File privateProductToken = privateKeyPath.toFile();
		if (!privateProductToken.exists()) {
			String pattern = LicensesCoreMessages.LicenseOperatorServiceImpl_private_key_not_found;
			String message = String.format(pattern, privateProductToken.getAbsolutePath());
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(), message));
		}

		Path encrypted = path.resolve(license.getIdentifier() + new PassageFileExtension.LicenseEncrypted().get());
		File licenseEncoded = encrypted.toFile();
		try (FileInputStream licenseInput = new FileInputStream(decrypted.toFile());
				FileOutputStream licenseOutput = new FileOutputStream(licenseEncoded);
				FileInputStream keyStream = new FileInputStream(privateProductToken)) {
			String username = product.identifier();
			ProductVersionDescriptor pvd = products.getProductVersion(product.identifier(), product.version());
			String password = operator.createPassword(pvd);
			codec.get().encode(licenseInput, licenseOutput, keyStream, username, password);
			events.postEvent(OperatorLicenseEvents.encodedIssued(encrypted.toString()));
			attachments.put(LicensesPackage.eNAME, encrypted);
			return new BaseServiceInvocationResult<>(new BaseIssuedLicense(userLicense, encrypted, decrypted));
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(//
					new Trouble(//
							new LicenseIssuingFailed(), //
							LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e));
		}
	}

}
