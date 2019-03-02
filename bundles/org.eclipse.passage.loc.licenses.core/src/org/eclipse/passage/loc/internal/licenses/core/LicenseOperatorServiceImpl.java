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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.eclipse.passage.lic.runtime.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductsRegistry;
import org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor;
import org.eclipse.passage.loc.edit.LocEdit;
import org.eclipse.passage.loc.runtime.LicenseOperatorEvents;
import org.eclipse.passage.loc.runtime.LicenseOperatorService;
import org.eclipse.passage.loc.runtime.ProductOperatorService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

public class LicenseOperatorServiceImpl implements LicenseOperatorService {

	private String pluginId;

	private EnvironmentInfo environmentInfo;
	private EventAdmin eventAdmin;
	private ProductsRegistry productRegistry;
	private ProductOperatorService productOperatorService;
	private StreamCodec streamCodec;
	
	@Activate
	public void activate(BundleContext context) {
		pluginId = context.getBundle().getSymbolicName();
	}

	@Reference
	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = null;
	}

	@Reference
	public void bindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}

	public void unbindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = null;
	}

	@Reference
	public void bindProductRegistry(ProductsRegistry productRegistry) {
		this.productRegistry = productRegistry;
	}

	public void unbindProductRegistry(ProductsRegistry productRegistry) {
		this.productRegistry = null;
	}

	@Reference
	public void bindProductOperatorService(ProductOperatorService productOperatorService) {
		this.productOperatorService = productOperatorService;
	}

	public void unbindProductOperatorService(ProductOperatorService productOperatorService) {
		this.productOperatorService = null;
	}

	@Reference(cardinality=ReferenceCardinality.OPTIONAL)
	public void bindStreamCodec(StreamCodec streamCodec) {
		this.streamCodec = streamCodec;
	}

	public void unbindStreamCodec(StreamCodec streamCodec) {
		this.streamCodec = null;
	}

	@Override
	public IStatus issueLicensePack(LicensePackDescriptor descriptor) {
		LicensePack pack = null;
		if (descriptor instanceof LicensePack) {
			pack = (LicensePack) descriptor;
		}
		if (pack == null) {
			return new Status(IStatus.ERROR, pluginId, "Invalid License Pack");
		}
		LicensePack license = EcoreUtil.copy(pack);
		String errors = LocEdit.extractValidationError(license);
		if (errors != null) {
			return new Status(IStatus.ERROR, pluginId, errors);
		}
		String productIdentifier = license.getProductIdentifier();
		String productVersion = license.getProductVersion();
		Path basePath = getBasePath();
		Path path = basePath.resolve(productIdentifier).resolve(productVersion);
		String storageKeyFolder = path.toFile().getAbsolutePath();

		String uuid = UUID.randomUUID().toString();
		Date value = new Date();
		license.setIdentifier(uuid);
		license.setIssueDate(value);
		String licenseIn = storageKeyFolder + File.separator + uuid + LicensingPaths.EXTENSION_LICENSE_DECRYPTED;
		
		URI uri = URI.createFileURI(licenseIn);
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(uri);
		resource.getContents().add(license);
		try {
			resource.save(null);
			eventAdmin.postEvent(LicenseOperatorEvents.decodedIssued(licenseIn));
		} catch (IOException e) {
			return new Status(IStatus.ERROR, pluginId, "License Pack export error", e);
		}

		if (streamCodec == null) {
			String format = "License pack exported succesfully: \n\n %s \n";
			String message = String.format(format, licenseIn);
			return new Status(IStatus.OK, pluginId, message);
		}

		String keyFileName = productIdentifier + '_' + productVersion;
		String privateKeyPath = storageKeyFolder + File.separator + keyFileName + LocEdit.EXTENSION_KEY_PRIVATE;
		File privateProductToken = new File(privateKeyPath);
		if (!privateProductToken.exists()) {
			String pattern = "Product private key not found: \n %s";
			String message = String.format(pattern, privateProductToken.getAbsolutePath());
			return new Status(IStatus.ERROR, pluginId, message);
		}

		String licenseOut = storageKeyFolder + File.separator + uuid + LicensingPaths.EXTENSION_LICENSE_ENCRYPTED;
		File licenseEncoded = new File(licenseOut);
		try (FileInputStream licenseInput = new FileInputStream(licenseIn);
				FileOutputStream licenseOutput = new FileOutputStream(licenseEncoded); FileInputStream keyStream = new FileInputStream(privateProductToken)) {
			String username = productIdentifier;
			ProductVersionDescriptor pvd = productRegistry.getProductVersion(productIdentifier, productVersion);
			String password = productOperatorService.createPassword(pvd);
			streamCodec.encodeStream(licenseInput, licenseOutput, keyStream, username, password);
			eventAdmin.postEvent(LicenseOperatorEvents.encodedIssued(licenseOut));
			String format = "License pack exported succesfully: \n\n %s \n";
			String message = String.format(format, licenseOut);
			return new Status(IStatus.OK, pluginId, message);
		} catch (Exception e) {
			return new Status(IStatus.ERROR, pluginId, "License Pack export error", e);
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
