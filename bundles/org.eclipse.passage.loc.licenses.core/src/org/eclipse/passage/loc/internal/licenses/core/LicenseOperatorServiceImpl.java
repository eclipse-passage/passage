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
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.loc.api.OperatorLicenseEvents;
import org.eclipse.passage.loc.api.OperatorLicenseService;
import org.eclipse.passage.loc.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@Component
public class LicenseOperatorServiceImpl implements OperatorLicenseService {

	private String pluginId;

	private EnvironmentInfo environmentInfo;
	private EventAdmin eventAdmin;
	private ProductRegistry productRegistry;
	private OperatorProductService productOperatorService;
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
	public void bindProductRegistry(ProductRegistry productRegistry) {
		this.productRegistry = productRegistry;
	}

	public void unbindProductRegistry(ProductRegistry productRegistry) {
		this.productRegistry = null;
	}

	@Reference
	public void bindProductOperatorService(OperatorProductService productOperatorService) {
		this.productOperatorService = productOperatorService;
	}

	public void unbindProductOperatorService(OperatorProductService productOperatorService) {
		this.productOperatorService = null;
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
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
			return new Status(IStatus.ERROR, pluginId,
					LicensesCoreMessages.LicenseOperatorServiceImpl_status_invalid_license_pack);
		}
		LicensePack license = EcoreUtil.copy(pack);
		String errors = LicensingEcore.extractValidationError(license);
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
			eventAdmin.postEvent(OperatorLicenseEvents.decodedIssued(licenseIn));
		} catch (IOException e) {
			return new Status(IStatus.ERROR, pluginId, LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e);
		}

		if (streamCodec == null) {
			String format = LicensesCoreMessages.LicenseOperatorServiceImpl_export_success;
			String message = String.format(format, licenseIn);
			return new Status(IStatus.OK, pluginId, message);
		}

		String keyFileName = productIdentifier + '_' + productVersion;
		String privateKeyPath = storageKeyFolder + File.separator + keyFileName
				+ OperatorProductService.EXTENSION_KEY_PRIVATE;
		File privateProductToken = new File(privateKeyPath);
		if (!privateProductToken.exists()) {
			String pattern = LicensesCoreMessages.LicenseOperatorServiceImpl_private_key_not_found;
			String message = String.format(pattern, privateProductToken.getAbsolutePath());
			return new Status(IStatus.ERROR, pluginId, message);
		}

		String licenseOut = storageKeyFolder + File.separator + uuid + LicensingPaths.EXTENSION_LICENSE_ENCRYPTED;
		File licenseEncoded = new File(licenseOut);
		try (FileInputStream licenseInput = new FileInputStream(licenseIn);
				FileOutputStream licenseOutput = new FileOutputStream(licenseEncoded);
				FileInputStream keyStream = new FileInputStream(privateProductToken)) {
			String username = productIdentifier;
			ProductVersionDescriptor pvd = productRegistry.getProductVersion(productIdentifier, productVersion);
			String password = productOperatorService.createPassword(pvd);
			streamCodec.encodeStream(licenseInput, licenseOutput, keyStream, username, password);
			eventAdmin.postEvent(OperatorLicenseEvents.encodedIssued(licenseOut));
			String format = LicensesCoreMessages.LicenseOperatorServiceImpl_export_success;
			String message = String.format(format, licenseOut);
			return new Status(IStatus.OK, pluginId, message);
		} catch (Exception e) {
			return new Status(IStatus.ERROR, pluginId, LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e);
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
