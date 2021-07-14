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
package org.eclipse.passage.lic.internal.jetty.interaction;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.equinox.LicensedApplication;

final class LicenseProtection {

	private final Logger log = LogManager.getLogger(getClass());
	private Optional<GrantLockAttempt> lock = Optional.empty();

	boolean check() {
		lock = acquireLicense();
		if (!lock.isPresent()) {
			return false;
		}
		if (!lock.get().successful()) {
			return false;
		}
		ExaminationCertificate certificate = lock.get().certificate();
		if (new CertificateWorthAttention().test(Optional.of(certificate))) {
			log.error(String.format(//
					"\n---\n%s\n---\n", //$NON-NLS-1$
					new RequirementsLicensingStatusExplained(certificate).get()));
		}
		return true;
	}

	void release() {
		lock.ifPresent(acq -> new EquinoxPassage().releaseLicense(acq));
	}

	private Optional<GrantLockAttempt> acquireLicense() {
		return product().flatMap(this::acquireLicense);
	}

	private Optional<LicensedProduct> product() {
		try {
			return Optional.of(new LicensedApplication().product());
		} catch (LicensingException e) {
			log.error("Failed to read product credentials", e); //$NON-NLS-1$
			return Optional.empty();
		}
	}

	private Optional<GrantLockAttempt> acquireLicense(LicensedProduct product) {
		ServiceInvocationResult<GrantLockAttempt> response = new EquinoxPassage().acquireLicense(product.identifier());
		if (!successful(response)) {
			log.error(String.format(//
					"Failed to acquire license \nfor product %s:\n%s\n", //$NON-NLS-1$
					product, //
					new DiagnosticExplained(response.diagnostic()).get()));
			return Optional.empty();
		}
		return Optional.of(response.data().get());
	}

	private boolean successful(ServiceInvocationResult<GrantLockAttempt> response) {
		return response.data().map(GrantLockAttempt::successful).orElse(Boolean.FALSE);
	}

}
