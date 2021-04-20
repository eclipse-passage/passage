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
import java.util.UUID;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
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

@SuppressWarnings("restriction")
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

	ServiceInvocationResult<IssuedLicense> issue(Supplier<LicensePack> template) {
		LicensePack license = adjsut(EcoreUtil.copy(template.get()));
		String errors = LicensingEcore.extractValidationError(license);
		if (errors != null) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseValidationFailed(), errors));
		}
		try {
			new UpdateLicensePlan(licenses).withPersonal(EcoreUtil.copy(license));
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_error_io, e));
		}
		LicensedProduct product = new BaseLicensedProduct(license.getProduct().getProduct(),
				license.getProduct().getVersion());
		Path path = new UserHomeProductResidence(product).get();

		Path decrypted;
		try {
			decrypted = new PersistedDecoded(path, license)//
					.write(license.getIdentifier() + new PassageFileExtension.LicenseDecrypted().get());
			events.postEvent(OperatorLicenseEvents.decodedIssued(decrypted.toString()));
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_save_decoded, e));
		}

		Path encrypted;
		try {
			encrypted = new PersistedEncoded(product, decrypted, new ProductPassword(products, operator))//
					.write(license.getIdentifier() + new PassageFileExtension.LicenseEncrypted().get());
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e));
		}
		events.postEvent(OperatorLicenseEvents.encodedIssued(encrypted.toString()));
		return new BaseServiceInvocationResult<>(new BaseIssuedLicense(license, encrypted, decrypted));
	}

	private LicensePack adjsut(LicensePack license) {
		Date issueDate = new Date();
		license.setIdentifier(UUID.randomUUID().toString());
		license.setIssueDate(issueDate);
		new AssignGrantIdentifiers().accept(license);
		new PersonalLicenseIssuingProtection().accept(license);
		return license;
	}

}
