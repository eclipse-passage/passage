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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.base.io.FloatingFileExtension;
import org.eclipse.passage.lic.emf.validation.ErrorMessages;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.licenses.core.issue.FloatingLicenseIssuingProtection;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.core.PublicKeyReplcated;
import org.eclipse.passage.loc.licenses.trouble.code.LicenseAgreementsAttachFailed;
import org.eclipse.passage.loc.licenses.trouble.code.LicenseIssuingFailed;
import org.eclipse.passage.loc.licenses.trouble.code.LicenseValidationFailed;

@SuppressWarnings("restriction")
final class IssueFloatingLicense {

	private final LicenseRegistry licenses;
	private final AgreementRegistry agreements;
	private final ProductRegistry products;
	private final OperatorProductService operator;

	IssueFloatingLicense(LicenseRegistry licenses, AgreementRegistry agreements, ProductRegistry products,
			OperatorProductService operator) {
		this.licenses = licenses;
		this.agreements = agreements;
		this.products = products;
		this.operator = operator;
	}

	ServiceInvocationResult<IssuedFloatingLicense> issue(FloatingLicensePack pack,
			Collection<FloatingLicenseAccess> configs) {
		FloatingLicensePack license;
		try {
			license = new Builder(pack, configs)//
					.signed()//
					.shielded()//
					.withAgreements()//
					.get();
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(
					new Trouble(new LicenseAgreementsAttachFailed(), e.getMessage(), e));
		}
		try {
			new UpdateLicensePlan(licenses).withFloating(license);
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_error_io, e));
		}
		return persistLicenseFiles(EcoreUtil.copy(license), configs);
	}

	private ServiceInvocationResult<IssuedFloatingLicense> persistLicenseFiles(FloatingLicensePack pack,
			Collection<FloatingLicenseAccess> configs) {
		LicensedProduct product = product(pack.getLicense().getProduct());
		Path residence = new LicensePackResidence(pack.getLicense()).get();
		ServiceInvocationResult<List<Path>> license = //
				persist(pack, product, residence, decryptedFile(pack), encryptedFile(pack));
		BinaryOperator<ServiceInvocationResult<List<Path>>> sum = new BaseServiceInvocationResult.Sum<>(
				new SumOfLists<Path>());
		ServiceInvocationResult<List<Path>> withConfigs = configs.stream()//
				.map(access -> persist(access, product, residence, decryptedFile(access), encryptedFile(access)))//
				.reduce(license, sum);
		ServiceInvocationResult<List<Path>> withKey = sum.apply(withConfigs, replicateKey(product, residence));
		if (!new NoSevereErrors().test(withKey.diagnostic())) {
			return new BaseServiceInvocationResult<>(withKey.diagnostic());
		}
		new ContributedLicensePackIssueListener().floating(pack, configs, residence);
		return new BaseServiceInvocationResult<>(new BaseIssuedFloatingLicense(residence, withKey.data().get()));
	}

	private ServiceInvocationResult<List<Path>> persist(EObject target, LicensedProduct product, //
			Path folder, String decrypted, String encrypted) {
		// validate
		Optional<String> errors = new ErrorMessages().apply(target);
		if (errors.isPresent()) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseValidationFailed(), errors.get()));
		}
		// persist decoded
		Path lic;
		try {
			lic = new PersistedDecoded(folder, target).write(decrypted);
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(), //
					LicensesCoreMessages.LicenseOperatorServiceImpl_floating_save_decoded_failed, e));
		}
		// persist encoded
		Path licen;
		try {
			licen = new PersistedEncoded(product, lic, new ProductPassword(products, operator)).write(encrypted);
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(), //
					LicensesCoreMessages.LicenseOperatorServiceImpl_floating_save_encoded_failed, e));
		}
		return new BaseServiceInvocationResult<>(Arrays.asList(lic, licen));
	}

	private ServiceInvocationResult<List<Path>> replicateKey(LicensedProduct product, Path folder) {
		// copy product public key
		Path key;
		try {
			key = new PublicKeyReplcated(product, folder).store();
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(), //
					LicensesCoreMessages.LicenseOperatorServiceImpl_floating_save_product_key, e));
		}
		return new BaseServiceInvocationResult<>(Collections.singletonList(key));
	}

	private LicensedProduct product(ProductRef ref) {
		return new BaseLicensedProduct(ref.getIdentifier(), ref.getVersion());
	}

	private String decryptedFile(FloatingLicensePack pack) {
		return pack.getLicense().getIdentifier() + new FloatingFileExtension.FloatingLicenseDecrypted().get();
	}

	private String encryptedFile(FloatingLicensePack pack) {
		return pack.getLicense().getIdentifier() + new FloatingFileExtension.FloatingLicenseEncrypted().get();
	}

	private String decryptedFile(FloatingLicenseAccess access) {
		return accessFile(access, new FloatingFileExtension.FloatingLicenseAccessDecrypted());
	}

	private String encryptedFile(FloatingLicenseAccess access) {
		return accessFile(access, new FloatingFileExtension.FloatingLicenseAccessEncrypted());
	}

	private String accessFile(FloatingLicenseAccess access, FloatingFileExtension ext) {
		return String.format("%s_%s%s", //$NON-NLS-1$
				access.getOriginLicensePack(), //
				access.getUser(), //
				ext.get());
	}

	private final class Builder implements Supplier<FloatingLicensePack> {

		private final FloatingLicensePack pack;
		private final Collection<FloatingLicenseAccess> configs;

		Builder(FloatingLicensePack template, Collection<FloatingLicenseAccess> configs) {
			this.pack = EcoreUtil.copy(template);
			this.configs = configs;
		}

		Builder shielded() {
			new FloatingLicenseIssuingProtection().accept(pack);
			Collection<String> users = pack.getUsers().stream()//
					.map(UserGrant::getUser)//
					.collect(Collectors.toSet());
			Collection<FloatingLicenseAccess> redundant = configs.stream()//
					.filter(c -> !users.contains(c.getUser())).collect(Collectors.toSet());
			configs.removeAll(redundant);
			return this;
		}

		Builder signed() {
			new LicenseSignature().accept(pack.getLicense());
			return this;
		}

		Builder withAgreements() throws LicensingException {
			new LicenseAgreements(agreements).install(plan(), pack.getLicense());
			return this;
		}

		@Override
		public FloatingLicensePack get() {
			return pack;
		}

		private LicensePlanDescriptor plan() {
			return licenses.getLicensePlan(pack.getLicense().getPlan());
		}

	}
}
