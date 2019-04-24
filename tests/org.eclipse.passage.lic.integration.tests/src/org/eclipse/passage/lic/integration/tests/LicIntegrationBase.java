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
package org.eclipse.passage.lic.integration.tests;

import static org.eclipse.passage.lic.base.io.LicensingPaths.EXTENSION_LICENSE_DECRYPTED;
import static org.eclipse.passage.lic.base.io.LicensingPaths.EXTENSION_LICENSE_ENCRYPTED;
import static org.eclipse.passage.lic.base.io.LicensingPaths.EXTENSION_PRODUCT_PUBLIC;
import static org.eclipse.passage.lic.base.io.LicensingPaths.composeFileName;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public abstract class LicIntegrationBase {

	private static final String EXTENSION_SERVER_SETTINGS = ".settings"; //$NON-NLS-1$
	private static final String PASSAGE_SERVER_PORT_DEF = "passage.server.port=8080"; //$NON-NLS-1$
	private static final String PASSAGE_SERVER_HOST_DEF = "passage.server.host=localhost"; //$NON-NLS-1$
	static final String SOME_BUNDLE_ID = "some.licensed.bundle"; //$NON-NLS-1$
	static final String SOME_COMPONENT_ID = "some.licensed.component"; //$NON-NLS-1$
	static final String SOME_COMPONENT_VERSION = "1.2.0"; //$NON-NLS-1$

	static final String SOME_DECRYPTED_PRODUCT = "some.decrypted.product"; //$NON-NLS-1$
	static final String SOME_ENCRYPTED_PRODUCT = "some.encrypted.product"; //$NON-NLS-1$

	static final String EXECUTOR_1 = "executor.1"; //$NON-NLS-1$
	static final String EXECUTOR_2 = "executor.2"; //$NON-NLS-1$
	static final String EXECUTOR_3 = "executor.3"; //$NON-NLS-1$

	private static ServiceReference<AccessManager> accessManagerReference;
	protected static AccessManager accessManager;
	private static ServiceReference<EnvironmentInfo> environmentInfoReference;
	protected static EnvironmentInfo environmentInfo;

	@BeforeClass
	public static void startup() {
		Bundle bundle = FrameworkUtil.getBundle(LicIntegrationBase.class);
		BundleContext bundleContext = bundle.getBundleContext();
		accessManagerReference = bundleContext.getServiceReference(AccessManager.class);
		accessManager = bundleContext.getService(accessManagerReference);
		environmentInfoReference = bundleContext.getServiceReference(EnvironmentInfo.class);
		environmentInfo = bundleContext.getService(environmentInfoReference);
	}

	@AfterClass
	public static void shutdown() {
		accessManager = null;
		environmentInfo = null;
		Bundle bundle = FrameworkUtil.getBundle(LicIntegrationBase.class);
		BundleContext bundleContext = bundle.getBundleContext();
		bundleContext.ungetService(accessManagerReference);
		accessManagerReference = null;
		bundleContext.ungetService(environmentInfoReference);
		environmentInfoReference = null;
	}

	@Test
	public void testAccessManager() {
		assertNotNull(accessManager);
	}

	protected void createServerConfiguration(LicensingConfiguration configuration) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(PASSAGE_SERVER_HOST_DEF).append('\n');
		sb.append(PASSAGE_SERVER_PORT_DEF).append('\n');
		String content = sb.toString();

		Path path = EquinoxPaths.resolveInstallConfigurationPath(configuration);
		Files.createDirectories(path);
		Path settings = path.resolve(composeFileName(configuration, EXTENSION_SERVER_SETTINGS));
		Files.write(settings, content.getBytes());
	}

	protected void deleteServerConfiguration(LicensingConfiguration configuration) throws IOException {
		Path path = EquinoxPaths.resolveInstallConfigurationPath(configuration);
		Path settings = path.resolve(composeFileName(configuration, EXTENSION_SERVER_SETTINGS));
		Files.deleteIfExists(settings);
	}

	protected void createProductLicense(LicensingConfiguration configuration, LicensePack license, boolean encrypted) throws IOException {
		Path path = EquinoxPaths.resolveInstallConfigurationPath(configuration);
		Files.createDirectories(path);

		File licFile = path.resolve(composeFileName(configuration, EXTENSION_LICENSE_DECRYPTED)).toFile();
		LocOfflineEmulator.storeLicense(license, licFile);
		
		if (encrypted) {
			String publicFileName = composeFileName(configuration, EXTENSION_PRODUCT_PUBLIC);
			String privateFileName = composeFileName(configuration, ".scr"); //$NON-NLS-1$
			String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
			Path osgiInf = Paths.get(userDir, "OSGI-INF"); //$NON-NLS-1$
			File publicFile = osgiInf.resolve(publicFileName).toFile();
			File privateFile = osgiInf.resolve(privateFileName).toFile();
			File licenFile = path.resolve(composeFileName(configuration, EXTENSION_LICENSE_ENCRYPTED)).toFile();
			
			LocOfflineEmulator.encodeLicense(license, publicFile, privateFile, licFile, licenFile);
		}
	}

	protected void deleteProductLicense(LicensingConfiguration configuration, boolean encrypted) throws IOException {
		Path path = EquinoxPaths.resolveInstallConfigurationPath(configuration);
		FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				if (exc != null) {
					throw exc;
				}
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(path, visitor);
		if (encrypted) {
			String publicFileName = composeFileName(configuration, EXTENSION_PRODUCT_PUBLIC);
			String privateFileName = composeFileName(configuration, ".scr"); //$NON-NLS-1$
			String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
			Path osgiInf = Paths.get(userDir, "OSGI-INF"); //$NON-NLS-1$
			File publicFile = osgiInf.resolve(publicFileName).toFile();
			File privateFile = osgiInf.resolve(privateFileName).toFile();
			publicFile.delete();
			privateFile.delete();
		}
	}
}
