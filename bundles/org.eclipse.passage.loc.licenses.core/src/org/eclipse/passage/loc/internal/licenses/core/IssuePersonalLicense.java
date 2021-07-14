/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.emf.validation.ErrorMessages;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.OperatorLicenseEvents;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.licenses.core.issue.PersonalLicenseIssuingProtection;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseIssuingFailed;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseValidationFailed;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.osgi.service.event.EventAdmin;

final class IssuePersonalLicense {

	private final LicenseRegistry licenses;
	private final ProductRegistry products;
	private final OperatorProductService operator;
	private final EventAdmin events;

	IssuePersonalLicense(LicenseRegistry licenses, ProductRegistry products, OperatorProductService operator,
			EventAdmin events) {
		this.licenses = licenses;
		this.products = products;
		this.operator = operator;
		this.events = events;
	}

	ServiceInvocationResult<IssuedLicense> issue(Supplier<PersonalLicensePack> template) {
		PersonalLicensePack license = adjsut(signed(EcoreUtil.copy(template.get())));
		Optional<String> errors = new ErrorMessages().apply(license);
		if (errors.isPresent()) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseValidationFailed(), errors.get()));
		}
		try {
			new UpdateLicensePlan(licenses).withPersonal(EcoreUtil.copy(license));
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_error_io, e));
		}
		LicensedProduct product = new BaseLicensedProduct(//
				license.getLicense().getProduct().getIdentifier(), //
				license.getLicense().getProduct().getVersion());
		Path path = new UserHomeProductResidence(product).get();

		Path decrypted;
		try {
			decrypted = new PersistedDecoded(path, license)//
					.write(license.getLicense().getIdentifier() + new PassageFileExtension.LicenseDecrypted().get());
			events.postEvent(OperatorLicenseEvents.decodedIssued(decrypted.toString()));
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_save_decoded, e));
		}

		Path encrypted;
		try {
			encrypted = new PersistedEncoded(product, decrypted, new ProductPassword(products, operator))//
					.write(license.getLicense().getIdentifier() + new PassageFileExtension.LicenseEncrypted().get());
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e));
		}
		events.postEvent(OperatorLicenseEvents.encodedIssued(encrypted.toString()));
		return new BaseServiceInvocationResult<>(new BaseIssuedLicense(license, encrypted, decrypted));
	}

	private PersonalLicensePack adjsut(PersonalLicensePack license) {
		Date issueDate = new Date();
		license.getLicense().setIdentifier(UUID.randomUUID().toString());
		license.getLicense().setIssueDate(issueDate);
		new AssignGrantIdentifiers().accept(license);
		new PersonalLicenseIssuingProtection().accept(license);
		return license;
	}

	private PersonalLicensePack signed(PersonalLicensePack pack) {
		new LicenseSignature().accept(pack.getLicense());
		return pack;
	}

}
