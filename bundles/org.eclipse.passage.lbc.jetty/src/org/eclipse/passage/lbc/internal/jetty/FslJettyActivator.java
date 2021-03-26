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

import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.FlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lic.internal.equinox.io.FileFromBundle;
import org.eclipse.passage.lic.internal.jetty.JettyHandler;
import org.eclipse.passage.lic.internal.jetty.ui.LicensedJettyActivator;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public final class FslJettyActivator extends LicensedJettyActivator {

	private final FloatingState state;

	public FslJettyActivator() {
		this.state = new EagerFloatingState();
	}

	@Override
	protected JettyHandler handler() {
		return new JettyHandler(request -> new FlotingRequestHandled(new StatedRequest(request, state)).get());
	}

	@Override
	protected Path logConfig() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		return new FileFromBundle(bundle, "config/log4j2.xml").get(); //$NON-NLS-1$
	}

}
