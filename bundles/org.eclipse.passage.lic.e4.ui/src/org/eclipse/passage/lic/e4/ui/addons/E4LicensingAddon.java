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
package org.eclipse.passage.lic.e4.ui.addons;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.restrictions.ExaminationExplained;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.equinox.LicensedProductFromContext;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.internal.e4.ui.restrictions.WorkbenchShutdown;
import org.eclipse.passage.lic.jface.EquinoxPassageUI;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.event.Event;

/**
 * @since 2.1
 */
public final class E4LicensingAddon {

	private final IApplicationContext application;
	private final IEclipseContext context;
	private java.util.Optional<GrantLockAttempt> grant = java.util.Optional.empty();

	@Inject
	public E4LicensingAddon(IApplicationContext application, IEclipseContext context) {
		this.application = application;
		this.context = context;
	}

	@Inject
	@Optional
	public void applicationStarted(//
			@SuppressWarnings("unused") //
			@UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) //
			Event event) {
		ServiceInvocationResult<GrantLockAttempt> response = //
				new EquinoxPassageUI(this::shell).acquireLicense(product().identifier());
		if (grantAcquired(response)) {
			grant = response.data();
		} else {
			System.err.printf("License grant has not been acquired on startup, shutdown initated: \n%s\n%s\n", //$NON-NLS-1$
					new DiagnosticExplained(response.diagnostic()).get(), explainExamination(response));
			new WorkbenchShutdown().run();
		}
	}

	private LicensedProduct product() {
		return new SuppliedFrameworkAware().withFramework(Framework::product)//
				.orElseGet(new LicensedProductFromContext(application));
	}

	@Inject
	@Optional
	public void applicationFainted(//
			@SuppressWarnings("unused") //
			@UIEventTopic(UIEvents.UILifeCycle.APP_SHUTDOWN_STARTED) //
			Event event) {
		releaseGrant();
	}

	private Shell shell() {
		return context.get(Shell.class);
	}

	private boolean grantAcquired(ServiceInvocationResult<GrantLockAttempt> response) {
		return response.data().isPresent() && response.data().get().successful();
	}

	private void releaseGrant() {
		if (!grant.isPresent()) {
			return;
		}
		ServiceInvocationResult<Boolean> released = new EquinoxPassage().releaseLicense(grant.get());
		if (released.data().isPresent() && released.data().get()) {
			grant = java.util.Optional.empty();
		}
	}

	private String explainExamination(ServiceInvocationResult<GrantLockAttempt> response) {
		if (!response.data().isPresent()) {
			return ""; //$NON-NLS-1$
		}
		return new ExaminationExplained(response.data().get().certificate()).get();
	}

}
