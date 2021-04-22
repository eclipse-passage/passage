/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
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
import org.eclipse.passage.loc.internal.api.OperatorProductEvents;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component
public class ProductOperatorServiceImpl implements OperatorProductService {

	private String plugin;

	private EnvironmentInfo environment;
	private EventAdmin events;

	@Activate
	public void activate(BundleContext context) {
		plugin = context.getBundle().getSymbolicName();
	}

	@Reference
	public void bindEnvironmentInfo(EnvironmentInfo info) {
		this.environment = info;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo info) {
		if (environment == info) {
			this.environment = null;
		}
	}

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.events = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		if (this.events == admin) {
			this.events = null;
		}
	}

	@Override
	public String createPassword(ProductVersionDescriptor descriptor) {
		return new ProductVersionPassword(descriptor).get();
	}

	@Override
	public IStatus createProductKeys(ProductVersionDescriptor target) {
		Optional<String> existing = keysArePresent(target);
		if (existing.isPresent()) {
			return error(existing.get());
		}
		Optional<String> errors = validate(target);
		if (errors.isPresent()) {
			return error(errors.get());
		}
		LicensedProduct product = product(target);
		Optional<StreamCodec> codec = codec(product);
		if (codec.isEmpty()) {
			return noCodec(target, product);
		}
		Path destination = new UserHomeProductResidence(product).get();
		try {
			return createKeyPair(target, product, codec.get(), destination);
		} catch (Exception e) {
			return failed(e);
		}
	}

	private IStatus createKeyPair(ProductVersionDescriptor target, LicensedProduct product, StreamCodec codec,
			Path path) throws LicensingException {
		Path open = open(product, path);
		Path secret = secret(product, path);
		codec.createKeyPair(open, secret, product.identifier(), createPassword(target));
		notify(open, secret);
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

	private void notify(Path open, Path secret) {
		events.postEvent(OperatorProductEvents.publicCreated(open.toString()));
		events.postEvent(OperatorProductEvents.privateCreated(secret.toString()));
	}

	private Path secret(LicensedProduct licensed, Path path) {
		return path.resolve(//
				new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PrivateKey()).get());
	}

	private Path open(LicensedProduct licensed, Path path) {
		return path.resolve(//
				new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PublicKey()).get());
	}

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

	private Optional<String> keyIsPresent(Supplier<String> path, String error) {
		String existing = path.get();
		if (existing == null) {
			return Optional.empty();
		}
		File file = new File(existing);
		if (file.exists()) {
			return Optional.of(String.format(error, file.getAbsolutePath()));
		}
		return Optional.empty();
	}

}
