/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
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

	private String pluginId;

	private EnvironmentInfo environmentInfo;
	private EventAdmin eventAdmin;

	@Activate
	public void activate(BundleContext context) {
		pluginId = context.getBundle().getSymbolicName();
	}

	@Reference
	public void bindEnvironmentInfo(EnvironmentInfo info) {
		this.environmentInfo = info;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo info) {
		if (environmentInfo == info) {
			this.environmentInfo = null;
		}
	}

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		if (this.eventAdmin == admin) {
			this.eventAdmin = null;
		}
	}

	@Override
	public String createPassword(ProductVersionDescriptor descriptor) {
		String id = null;
		String version = null;
		if (descriptor != null) {
			ProductDescriptor product = descriptor.getProduct();
			if (product != null) {
				id = product.getIdentifier();
			}
			version = descriptor.getVersion();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("###").append(version); //$NON-NLS-1$
		return sb.toString();
	}

	@Override
	public IStatus createProductKeys(ProductVersionDescriptor descriptor) {
		ProductVersion productVersion = null;
		if (descriptor instanceof ProductVersion) {
			productVersion = (ProductVersion) descriptor;
		}
		if (productVersion == null) {
			return new Status(IStatus.ERROR, pluginId,
					ProductsCoreMessages.ProductOperatorServiceImpl_e_invalid_product_version);
		}
		String installationToken = productVersion.getInstallationToken();
		if (installationToken != null) {
			File publicFile = new File(installationToken);
			if (publicFile.exists()) {
				String pattern = ProductsCoreMessages.ProductOperatorServiceImpl_e_public_key_already_defined;
				String message = String.format(pattern, publicFile.getAbsolutePath());
				return new Status(IStatus.ERROR, pluginId, message);
			}
		}
		String secureToken = productVersion.getSecureToken();
		if (secureToken != null) {
			File privateFile = new File(secureToken);
			if (privateFile.exists()) {
				String pattern = ProductsCoreMessages.ProductOperatorServiceImpl_e_private_key_already_defined;
				String message = String.format(pattern, privateFile.getAbsolutePath());
				return new Status(IStatus.ERROR, pluginId, message);
			}
		}

		Product product = productVersion.getProduct();
		String errors = LicensingEcore.extractValidationError(product);
		if (errors != null) {
			return new Status(IStatus.ERROR, pluginId, errors);
		}
		LicensedProduct licensed = new BaseLicensedProduct(product.getIdentifier(), productVersion.getVersion());
		Optional<StreamCodec> codec = codec(licensed);
		if (codec.isEmpty()) {
			String pattern = ProductsCoreMessages.ProductOperatorServiceImpl_e_unable_to_create_keys;
			String message = String.format(pattern, licensed.version(), product.getName());
			return new Status(IStatus.ERROR, pluginId, message);
		}
		Path path = new UserHomeProductResidence(licensed).get();
		try {
			new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PrivateKey());
			Path open = path.resolve(//
					new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PublicKey()).get());
			Path secret = path.resolve(//
					new FileNameFromLicensedProduct(licensed, new PassageFileExtension.PrivateKey()).get());
			codec.get().createKeyPair(open, secret, licensed.identifier(), createPassword(productVersion));
			productVersion.setInstallationToken(open.toString());
			productVersion.setSecureToken(secret.toString());
			eventAdmin.postEvent(OperatorProductEvents.publicCreated(open.toString()));
			eventAdmin.postEvent(OperatorProductEvents.privateCreated(secret.toString()));
			String format = ProductsCoreMessages.ProductOperatorServiceImpl_ok_keys_exported;
			String message = String.format(format, open, secret);
			return new Status(IStatus.OK, pluginId, message);
		} catch (Exception e) {
			return new Status(IStatus.ERROR, pluginId, ProductsCoreMessages.ProductOperatorServiceImpl_e_export_error,
					e);
		}
	}

	private Optional<StreamCodec> codec(LicensedProduct product) {
		return new OperatorGearAware().withGear(gear -> Optional.ofNullable(gear.codec(product)));
	}

}
