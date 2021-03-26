/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.jetty.ui;

import java.nio.file.Path;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.logging.Logging;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.equinox.LicensedApplication;
import org.eclipse.passage.lic.internal.jetty.JettyHandler;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.eclipse.passage.lic.internal.net.connect.Port;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public abstract class LicensedJettyActivator implements BundleActivator {

	private final Logger log;
	private final JettyServer jetty;
	private Optional<GrantLockAttempt> lock = Optional.empty();

	public LicensedJettyActivator() {
		configureLogging();
		this.log = LogManager.getLogger(getClass());
		this.jetty = new JettyServer(this::handler);
	}

	@Override
	public final void start(BundleContext context) throws Exception {
		registerCommands(context);
		if (checkLicense()) {
			jetty.launch(new Port(Platform.getApplicationArgs(), 8090));
		}
	}

	@Override
	public final void stop(BundleContext context) throws Exception {
		lock.ifPresent(acq -> new EquinoxPassage().releaseLicense(acq));
		jetty.terminate();
	}

	private void configureLogging() {
		new Logging(this::logConfig).configure();
	}

	private boolean checkLicense() {
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

	private void registerCommands(BundleContext context) {
		// new Commands().register(context, jetty);
	}

	protected abstract JettyHandler handler();

	protected abstract Path logConfig() throws Exception;

}
