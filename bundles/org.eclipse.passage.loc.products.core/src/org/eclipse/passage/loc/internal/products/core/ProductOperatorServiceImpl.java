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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.internal.api.LicensingPaths;
import org.eclipse.passage.loc.internal.api.OperatorProductEvents;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@Component
public class ProductOperatorServiceImpl implements OperatorProductService {

	private String pluginId;

	private EnvironmentInfo environmentInfo;
	private EventAdmin eventAdmin;
	private StreamCodec streamCodec;

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

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	public void bindStreamCodec(StreamCodec codec) {
		this.streamCodec = codec;
	}

	public void unbindStreamCodec(StreamCodec codec) {
		if (this.streamCodec == codec) {
			this.streamCodec = null;
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
			return new Status(IStatus.ERROR, pluginId, ProductsCoreMessages.ProductOperatorServiceImpl_e_invalid_product_version);
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
		String identifier = product.getIdentifier();
		String version = productVersion.getVersion();
		if (streamCodec == null) {
			String pattern = ProductsCoreMessages.ProductOperatorServiceImpl_e_unable_to_create_keys;
			String message = String.format(pattern, version, product.getName());
			return new Status(IStatus.ERROR, pluginId, message);
		}
		Path basePath = getBasePath();
		try {
			Path path = basePath.resolve(identifier).resolve(version);
			Files.createDirectories(path);
			String storageKeyFolder = path.toFile().getAbsolutePath();
			String keyFileName = identifier + '_' + version;
			String publicKeyPath = storageKeyFolder + File.separator + keyFileName
					+ LicensingPaths.EXTENSION_PRODUCT_PUBLIC;
			String privateKeyPath = storageKeyFolder + File.separator + keyFileName + EXTENSION_KEY_PRIVATE;
			streamCodec.createKeyPair(publicKeyPath, privateKeyPath, identifier, createPassword(productVersion));
			productVersion.setInstallationToken(publicKeyPath);
			productVersion.setSecureToken(privateKeyPath);
			eventAdmin.postEvent(OperatorProductEvents.publicCreated(publicKeyPath));
			eventAdmin.postEvent(OperatorProductEvents.privateCreated(privateKeyPath));
			String format = ProductsCoreMessages.ProductOperatorServiceImpl_ok_keys_exported;
			String message = String.format(format, publicKeyPath, privateKeyPath);
			return new Status(IStatus.OK, pluginId, message);
		} catch (Exception e) {
			return new Status(IStatus.ERROR, pluginId, ProductsCoreMessages.ProductOperatorServiceImpl_e_export_error, e);
		}
	}

	public Path getBasePath() {
		String areaValue = environmentInfo.getProperty("user.home"); //$NON-NLS-1$
		Path passagePath = Paths.get(areaValue, LicensingPaths.FOLDER_LICENSING_BASE);
		try {
			Files.createDirectories(passagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passagePath;
	}

}
