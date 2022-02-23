/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.emf.validation.ErrorMessages;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.OperatorLicenseEvents;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.licenses.core.issue.PersonalLicenseIssuingProtection;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.licenses.trouble.code.LicenseAgreementsAttachFailed;
import org.eclipse.passage.loc.licenses.trouble.code.LicenseIssuingFailed;
import org.eclipse.passage.loc.licenses.trouble.code.LicenseValidationFailed;
import org.osgi.service.event.EventAdmin;

final class IssuePersonalLicense {

	private final LicenseRegistry licenses;
	private final AgreementRegistry agreements;
	private final ProductRegistry products;
	private final OperatorProductService operator;
	private final EventAdmin events;

	IssuePersonalLicense(LicenseRegistry licenses, AgreementRegistry agreements, ProductRegistry products,
			OperatorProductService operator, EventAdmin events) {
		this.licenses = licenses;
		this.agreements = agreements;
		this.products = products;
		this.operator = operator;
		this.events = events;
	}

	ServiceInvocationResult<IssuedLicense> issue(Supplier<PersonalLicensePack> template) {
		PersonalLicensePack license;
		try {
			license = new Builder(template.get())//
					.adjusted()//
					.guarded()//
					.signed()//
					.withAgreements()//
					.get();
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(
					new Trouble(new LicenseAgreementsAttachFailed(), e.getMessage(), e));
		}
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
		Path path = new LicensePackResidence(license.getLicense()).get();

		Path decrypted;
		try {
			decrypted = decrypted(license, path);
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_save_decoded, e));
		}

		Path encrypted;
		try {
			encrypted = encrypted(license, product(license), decrypted);
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e));
		}
		new ContributedLicensePackIssueListener().personal(license, path);
		return result(license, decrypted, encrypted);
	}

	private BaseServiceInvocationResult<IssuedLicense> result(PersonalLicensePack license, Path decrypted,
			Path encrypted) {
		return new BaseServiceInvocationResult<>(new BaseIssuedLicense(license, encrypted, decrypted));
	}

	private Path encrypted(PersonalLicensePack license, LicensedProduct product, Path decrypted)
			throws LicensingException {
		Path encrypted = new PersistedEncoded(product, decrypted, new ProductPassword(products, operator))//
				.write(license.getLicense().getIdentifier() + new PassageFileExtension.LicenseEncrypted().get());
		events.postEvent(OperatorLicenseEvents.encodedIssued(encrypted.toString()));
		return encrypted;
	}

	private Path decrypted(PersonalLicensePack license, Path path) throws LicensingException {
		Path decrypted = new PersistedDecoded(path, license)//
				.write(license.getLicense().getIdentifier() + new PassageFileExtension.LicenseDecrypted().get());
		events.postEvent(OperatorLicenseEvents.decodedIssued(decrypted.toString()));
		return decrypted;
	}

	private BaseLicensedProduct product(PersonalLicensePack license) {
		return new BaseLicensedProduct(//
				license.getLicense().getProduct().getIdentifier(), //
				license.getLicense().getProduct().getVersion());
	}

	private final class Builder implements Supplier<PersonalLicensePack> {

		private final PersonalLicensePack pack;

		Builder(PersonalLicensePack template) {
			this.pack = EcoreUtil.copy(template);
		}

		Builder signed() {
			new LicenseSignature().accept(pack.getLicense());
			return this;
		}

		Builder withAgreements() throws LicensingException {
			new LicenseAgreements(agreements).install(plan(), pack.getLicense());
			return this;
		}

		Builder adjusted() {
			pack.getLicense().setIdentifier(UUID.randomUUID().toString());
			pack.getLicense().setIssueDate(new Date());
			new AssignGrantIdentifiers().accept(pack);
			return this;
		}

		Builder guarded() {
			new PersonalLicenseIssuingProtection().accept(pack);
			return this;
		}

		@Override
		public PersonalLicensePack get() {
			return pack;
		}

		private LicensePlanDescriptor plan() {
			return licenses.getLicensePlan(pack.getLicense().getPlan());
		}
	}
}
