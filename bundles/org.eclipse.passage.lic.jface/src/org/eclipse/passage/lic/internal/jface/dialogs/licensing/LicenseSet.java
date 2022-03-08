/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.base.conditions.mining.BaseLicenseReadingService;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.io.ExternalLicense;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.internal.base.access.Libraries;
import org.eclipse.passage.lic.internal.jface.i18n.ImportLicenseDialogMessages;

@SuppressWarnings("restriction")
final class LicenseSet {
	private final LicensedProduct product;
	private final List<Path> licenses;
	private final Libraries libraries;
	private final Consumer<String> error;
	private final Optional<LicenseReadingService> service;

	LicenseSet(List<Path> licenses, LicensedProduct product, Libraries libraries, Consumer<String> error) {
		this.licenses = licenses;
		this.product = product;
		this.libraries = libraries;
		this.error = error;
		this.service = productLicenseReadingService();
	}

	private Optional<LicenseReadingService> productLicenseReadingService() {
		return miner().map(miner -> new BaseLicenseReadingService(product, miner));
	}

	private Optional<MiningEquipment> miner() {
		return new SuppliedFrameworkAware()
				.withFramework(framework -> framework.accessCycleConfiguration().miningEquipment());
	}

	void install() {
		licenses.forEach(file -> doLicenseImport(file));
	}

	private void doLicenseImport(Path license) {
		try {
			installLibraryLicense(license);
			installProductLicense(license);
		} catch (Exception e) {
			error.accept(
					String.format(ImportLicenseDialogMessages.ImportLicenseDialog_io_error, e.getLocalizedMessage()));
		}
	}

	private void installProductLicense(Path license) throws IOException {
		if (!productRelevantLicense(license)) {
			return;
		}
		new ExternalLicense(product).install(license);
	}

	private void installLibraryLicense(Path license) throws Exception {
		Optional<ServiceInvocationResult<Boolean>> result = libraries.installLicense(license);
		if (!result.isPresent()) {
			return; // no libraries
		}
		ServiceInvocationResult<Boolean> status = result.get();
		Diagnostic diagnostic = status.diagnostic();
		System.out.println("Import license license: " + license); //$NON-NLS-1$
		System.out.println(new DiagnosticExplained(diagnostic).get());
		if (!new NoSevereErrors().test(diagnostic)) {
			throw new LicensingException(String.format("License file [%s] failed to be imported", license)); //$NON-NLS-1$
		}
	}

	private boolean productRelevantLicense(Path license) {
		if (!service.isPresent()) {
			return false;
		}
		return service.get().read(license).data()//
				.map(conditions -> !conditions.isEmpty())//
				.orElse(Boolean.FALSE);
	}
}
