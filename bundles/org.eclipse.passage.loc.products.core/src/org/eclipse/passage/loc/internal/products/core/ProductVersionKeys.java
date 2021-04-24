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

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;

final class ProductVersionKeys {

	private final String plugin;
	private final BiConsumer<Path, Path> notify;

	ProductVersionKeys(String plugin, BiConsumer<Path, Path> notify) {
		this.notify = notify;
		Objects.requireNonNull(plugin, "ProductVersionPassword::plugin"); //$NON-NLS-1$
		this.plugin = plugin;
	}

	public IStatus createKeys(ProductVersionDescriptor target) {
		Optional<String> existing = keysArePresent(target);
		if (existing.isPresent()) {
			return error(existing.get());
		}
		Optional<String> invalid = validate(target);
		if (invalid.isPresent()) {
			return error(invalid.get());
		}
		LicensedProduct product = product(target);
		Optional<StreamCodec> codec = codec(product);
		if (codec.isEmpty()) {
			return noCodec(target, product);
		}
		try {
			return createKeyPair(target, product, codec.get());
		} catch (Exception e) {
			return failed(e);
		}
	}

	private IStatus createKeyPair(ProductVersionDescriptor target, LicensedProduct product, StreamCodec codec)
			throws LicensingException {
		Path destination = new UserHomeProductResidence(product).get();
		Path open = open(product, destination);
		Path secret = secret(product, destination);
		new StreamCodec.Smart(codec).createKeyPair(open, secret, product.identifier(),
				new ProductVersionPassword(target).get());
		notify.accept(open, secret);
		// TODO: store .keys_xmi under workspace
		return created(open, secret);
	}

	private Status error(String errors) {
		return new Status(IStatus.ERROR, plugin, errors);
	}

	private Status noCodec(ProductVersionDescriptor target, LicensedProduct product) {
		return error(String.format(ProductsCoreMessages.ProductOperatorServiceImpl_e_unable_to_create_keys,
				product.version(), target.getProduct().getName()));
	}

	private Status failed(Exception e) {
		return new Status(IStatus.ERROR, plugin, ProductsCoreMessages.ProductOperatorServiceImpl_e_export_error, e);
	}

	private Status created(Path open, Path secret) {
		return new Status(IStatus.OK, plugin,
				String.format(ProductsCoreMessages.ProductOperatorServiceImpl_ok_keys_exported, open, secret));
	}

	private BaseLicensedProduct product(ProductVersionDescriptor target) {
		return new BaseLicensedProduct(//
				target.getProduct().getIdentifier(), //
				target.getVersion());
	}

	private Path secret(LicensedProduct licensed, Path path) {
		return path.resolve(//
				new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PrivateKey()).get());
	}

	private Path open(LicensedProduct licensed, Path path) {
		return path.resolve(//
				new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PublicKey()).get());
	}

	@SuppressWarnings("restriction")
	private Optional<StreamCodec> codec(LicensedProduct product) {
		return new OperatorGearAware().withGear(gear -> gear.codec(product));
	}

	private Optional<String> validate(ProductVersionDescriptor target) {
		if (!(target instanceof ProductVersion)) {
			return Optional.empty();
		}
		return Optional.ofNullable(LicensingEcore.extractValidationError(((ProductVersion) target).getProduct()));
	}

	private Optional<String> keysArePresent(ProductVersionDescriptor target) {
		Optional<String> pub = keyIsPresent(target::getInstallationToken,
				ProductsCoreMessages.ProductOperatorServiceImpl_e_public_key_already_defined);
		if (pub.isPresent()) {
			return pub;
		}
		return keyIsPresent(target::getSecureToken,
				ProductsCoreMessages.ProductOperatorServiceImpl_e_private_key_already_defined);
	}

	// TODO: appeal to workspace here
	private Optional<String> keyIsPresent(Supplier<String> path, String error) {
		String existing = path.get();
		if (existing == null) { // model(emf)-driven null
			return Optional.empty();
		}
		File file = new File(existing);
		if (file.exists()) {
			return Optional.of(String.format(error, file.getAbsolutePath()));
		}
		return Optional.empty();
	}
}
