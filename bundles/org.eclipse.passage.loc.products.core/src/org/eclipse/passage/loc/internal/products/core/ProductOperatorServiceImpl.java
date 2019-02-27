/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.products.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.model.api.Product;
import org.eclipse.passage.lic.model.api.ProductVersion;
import org.eclipse.passage.lic.registry.ProductVersionDescriptor;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.eclipse.passage.loc.edit.LocEdit;
import org.eclipse.passage.loc.edit.ProductDomainRegistry;
import org.eclipse.passage.loc.runtime.ProductOperatorEvents;
import org.eclipse.passage.loc.runtime.ProductOperatorService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@Component
public class ProductOperatorServiceImpl implements ProductOperatorService {
	
	private String pluginId;

	private EventAdmin eventAdmin;
	private ProductDomainRegistry productRegistry;
	private StreamCodec streamCodec;
	
	@Activate
	public void activate(BundleContext context) {
		pluginId = context.getBundle().getSymbolicName();
	}

	@Reference
	public void bindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}

	public void unbindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = null;
	}

	@Reference(cardinality=ReferenceCardinality.OPTIONAL)
	public void bindStreamCodec(StreamCodec streamCodec) {
		this.streamCodec = streamCodec;
	}

	public void unbindStreamCodec(StreamCodec streamCodec) {
		this.streamCodec = null;
	}

	@Override
	public IStatus createProductKeys(ProductVersionDescriptor descriptor) {
		ProductVersion productVersion = null;
		if (descriptor instanceof ProductVersion) {
			productVersion = (ProductVersion) descriptor;
		}
		if (productVersion == null) {
			return new Status(IStatus.ERROR, pluginId, "Invalid Product Version");
		}
		String installationToken = productVersion.getInstallationToken();
		if (installationToken != null) {
			File publicFile = new File(installationToken);
			if (publicFile.exists()) {
				String pattern = "Public key already defined: \n %s";
				String message = String.format(pattern, publicFile.getAbsolutePath());
				return new Status(IStatus.ERROR, pluginId, message);
			}
		}
		String secureToken = productVersion.getSecureToken();
		if (secureToken != null) {
			File privateFile = new File(secureToken);
			if (privateFile.exists()) {
				String pattern = "Private key already defined: \n %s";
				String message = String.format(pattern, privateFile.getAbsolutePath());
				return new Status(IStatus.ERROR, pluginId, message);
			}
		}

		Product product = productVersion.getProduct();
		String errors = LocEdit.extractValidationError(product);
		if (errors != null) {
			return new Status(IStatus.ERROR, pluginId, errors);
		}
		String identifier = product.getIdentifier();
		String version = productVersion.getVersion();
		if (streamCodec == null) {
			String pattern = "Unable to create keys for version %s of %s : \n codec not found";
			String message = String.format(pattern, version, product.getName());
			return new Status(IStatus.ERROR, pluginId, message);
		}
		Path basePath = productRegistry.getBasePath();
		try {
			Path path = basePath.resolve(identifier).resolve(version);
			Files.createDirectories(path);
			String storageKeyFolder = path.toFile().getAbsolutePath();
			String keyFileName = identifier + '_' + version;
			String publicKeyPath = storageKeyFolder + File.separator + keyFileName
					+ LicensingPaths.EXTENSION_PRODUCT_PUBLIC;
			String privateKeyPath = storageKeyFolder + File.separator + keyFileName + LocEdit.EXTENSION_KEY_PRIVATE;
			streamCodec.createKeyPair(publicKeyPath, privateKeyPath, identifier,
					productRegistry.createPassword(productVersion));
			productVersion.setInstallationToken(publicKeyPath);
			productVersion.setSecureToken(privateKeyPath);
			eventAdmin.postEvent(ProductOperatorEvents.publicCreated(publicKeyPath));
			eventAdmin.postEvent(ProductOperatorEvents.privateCreated(privateKeyPath));
			String format = "Product keys exported succesfully: \n\n %s \n %s \n";
			String message = String.format(format, publicKeyPath, privateKeyPath);
			return new Status(IStatus.OK, pluginId, message);
		} catch (Exception e) {
			return new Status(IStatus.ERROR, pluginId, "Product key export error", e);
		}
	}

}
