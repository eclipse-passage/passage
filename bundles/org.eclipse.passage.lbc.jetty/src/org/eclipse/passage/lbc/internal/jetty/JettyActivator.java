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
package org.eclipse.passage.lbc.internal.jetty;

import java.nio.file.Path;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.FlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.logging.Logging;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.equinox.LicensedApplication;
import org.eclipse.passage.lic.internal.equinox.io.FileFromBundle;
import org.eclipse.passage.lic.internal.jetty.JettyHandler;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.eclipse.passage.lic.internal.net.connect.Port;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public final class JettyActivator implements BundleActivator {
	private final Logger log;
	private final JettyServer jetty;
	private final FloatingState state;
	private Optional<GrantLockAttempt> lock = Optional.empty();

	public JettyActivator() {
		configureLogging();
		this.log = LogManager.getLogger(getClass());
		this.jetty = new JettyServer(this::handler);
		this.state = new EagerFloatingState();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		lock = acquireLicense();
		if (!lock.isPresent()) {
			return;
		}
		if (!lock.get().successful()) {
			return;
		}
		jetty.launch(new Port(Platform.getApplicationArgs(), 8090));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		lock.ifPresent(acq -> new EquinoxPassage().releaseLicense(acq));
		jetty.terminate();
	}

	private JettyHandler handler() {
		return new JettyHandler(request -> new FlotingRequestHandled(new StatedRequest(request, state)).get());
	}

	private void configureLogging() {
		new Logging(this::logConfig).configure();
	}

	private Path logConfig() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		return new FileFromBundle(bundle, "config/log4j2.xml").get(); //$NON-NLS-1$
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
