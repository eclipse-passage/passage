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

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.floating.FloatingFileExtensions;
import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseIssuingFailed;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseValidationFailed;
import org.eclipse.passage.loc.internal.products.ProductRegistry;

final class IssueFloatingLicense {

	private final ProductRegistry products;
	private final OperatorProductService operator;

	IssueFloatingLicense(ProductRegistry products, OperatorProductService operator) {
		this.products = products;
		this.operator = operator;
	}

	ServiceInvocationResult<IssuedFloatingLicense> issue(FloatingLicensePack pack,
			Collection<FloatingLicenseAccess> configs) {
		LicensedProduct product = product(pack.getLicense().getProduct());
		Path residence = residence(pack.getLicense());
		ServiceInvocationResult<List<Path>> license = //
				persist(pack, product, residence, decryptedFile(pack), encryptedFile(pack));
		ServiceInvocationResult<List<Path>> files = configs.stream()//
				.map(access -> persist(pack, product, residence, decryptedFile(access), encryptedFile(access)))//
				.reduce(license, new BaseServiceInvocationResult.Sum<>(new SumOfLists<Path>()));
		if (!new NoSevereErrors().test(files.diagnostic())) {
			return new BaseServiceInvocationResult<>(files.diagnostic());
		}
		return new BaseServiceInvocationResult<>(new BaseIssuedFloatingLicense(residence, files.data().get()));
	}

	private ServiceInvocationResult<List<Path>> persist(EObject target, LicensedProduct product, //
			Path folder, String decrypted, String encrypted) {
		// validate
		Optional<String> errors = Optional.ofNullable(LicensingEcore.extractValidationError(target));
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
					LicensesCoreMessages.LicenseOperatorServiceImpl_floating_save_decoded_failed, e));
		}
		return new BaseServiceInvocationResult<>(Arrays.asList(lic, licen));
	}

	private Path residence(LicenseRequisites license) {
		return new UserHomeProductResidence(//
				license.getProduct().getProduct(), //
				license.getProduct().getVersion())//
						.get()//
						.resolve(license.getIdentifier());
	}

	private LicensedProduct product(ProductRef ref) {
		return new BaseLicensedProduct(ref.getProduct(), ref.getVersion());

	}

	private String decryptedFile(FloatingLicensePack pack) {
		return pack.getLicense().getIdentifier() + new FloatingFileExtensions.LicenseDecrypted().get();
	}

	private String encryptedFile(FloatingLicensePack pack) {
		return pack.getLicense().getIdentifier() + new FloatingFileExtensions.LicenseEncrypted().get();
	}

	private String decryptedFile(FloatingLicenseAccess access) {
		return accessFile(access, new FloatingFileExtensions.LicenseAccessDecrypted());
	}

	private String encryptedFile(FloatingLicenseAccess access) {
		return accessFile(access, new FloatingFileExtensions.LicenseAccessEncrypted());
	}

	private String accessFile(FloatingLicenseAccess access, FloatingFileExtensions ext) {
		return String.format("%s_%s%s", //$NON-NLS-1$
				access.getOriginLicensePack(), //
				access.getUser(), //
				ext.get());
	}
}
