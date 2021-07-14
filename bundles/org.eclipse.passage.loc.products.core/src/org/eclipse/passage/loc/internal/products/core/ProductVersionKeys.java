/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.products.core;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.emf.validation.ErrorMessages;
import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.internal.api.workspace.Keys;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;

@SuppressWarnings("restriction")
final class ProductVersionKeys {

	private final String plugin;
	private final Consumer<String> notify;
	private final Logger log = LogManager.getLogger(getClass());

	ProductVersionKeys(String plugin, Consumer<String> notify) {
		Objects.requireNonNull(plugin, "ProductVersionPassword::plugin"); //$NON-NLS-1$
		Objects.requireNonNull(notify, "ProductVersionPassword::notify"); //$NON-NLS-1$
		this.plugin = plugin;
		this.notify = notify;
	}

	ProductVersionKeys(String plugin) {
		this(plugin, p -> {
		});
	}

	public IStatus createKeys(ProductVersionDescriptor target) {
		LicensedProduct product = product(target);
		Optional<String> existing;
		try {
			existing = keyIsPresent(product);
		} catch (LicensingException e) {
			return failed(e);
		}
		if (existing.isPresent()) {
			return error(existing.get());
		}
		Optional<String> invalid = validate(target);
		if (invalid.isPresent()) {
			return error(invalid.get());
		}
		Optional<StreamCodec> codec;
		try {
			codec = codec(product);
		} catch (LicensingException e) {
			return failed(e);
		}
		if (codec.isEmpty()) {
			return noCodec(target, product);
		}
		try {
			return createKeyPair(target, product, codec.get());
		} catch (Exception e) {
			return failed(e);
		}
	}

	private BaseLicensedProduct product(ProductVersionDescriptor target) {
		return new BaseLicensedProduct(//
				target.getProduct().getIdentifier(), //
				target.getVersion());
	}

	private Optional<StreamCodec> codec(LicensedProduct product) throws LicensingException {
		return new OperatorGearAware().withGear(gear -> gear.codec(product));
	}

	private Optional<String> validate(ProductVersionDescriptor target) {
		if (!(target instanceof ProductVersion)) {
			return Optional.empty();
		}
		return new ErrorMessages().apply(((ProductVersion) target).getProduct());
	}

	private Optional<String> keyIsPresent(LicensedProduct target) throws LicensingException {
		Keys service = new OperatorGearAware().withGear(gear -> Optional.of(gear.workspace().keys())).get();
		Optional<String> exists = service.existing(target.identifier(), target.version());
		return exists.map(
				path -> String.format(ProductsCoreMessages.ProductOperatorServiceImpl_e_key_already_defined, path));
	}

	private IStatus createKeyPair(ProductVersionDescriptor target, LicensedProduct product, StreamCodec codec)
			throws LicensingException {
		Optional<String> stored = store(generate(target, product, codec));
		if (!stored.isPresent()) {
			notify.accept(stored.get());
		}
		return created(stored);
	}

	private Optional<String> store(KeyPair pair) throws LicensingException {
		return new KeyPairStored(pair).store();
	}

	private KeyPair generate(ProductVersionDescriptor target, LicensedProduct product, StreamCodec codec)
			throws LicensingException {
		try (ByteArrayOutputStream open = new ByteArrayOutputStream();
				ByteArrayOutputStream secret = new ByteArrayOutputStream()) {
			codec.createKeyPair(open, secret, product.identifier(), new ProductVersionPassword(target).get());
			return new KeyPairGeneraged(codec, open.toByteArray(), secret.toByteArray()).get();
		} catch (Exception e) {
			throw new LicensingException("failed to generate keys", e); //$NON-NLS-1$ // TODO: l10n
		}
	}

	private Status error(String errors) {
		return new Status(IStatus.ERROR, plugin, errors);
	}

	private Status noCodec(ProductVersionDescriptor target, LicensedProduct product) {
		return error(String.format(ProductsCoreMessages.ProductOperatorServiceImpl_e_unable_to_create_keys,
				product.version(), target.getProduct().getName()));
	}

	private Status failed(Exception e) {
		log.error("", e); //$NON-NLS-1$
		return new Status(IStatus.ERROR, plugin, ProductsCoreMessages.ProductOperatorServiceImpl_e_export_error, e);
	}

	private Status created(Optional<String> persisted) {
		return new Status(IStatus.OK, plugin, String
				.format(ProductsCoreMessages.ProductOperatorServiceImpl_ok_keys_exported, persisted.orElse("unknown"))); //$NON-NLS-1$
	}

}