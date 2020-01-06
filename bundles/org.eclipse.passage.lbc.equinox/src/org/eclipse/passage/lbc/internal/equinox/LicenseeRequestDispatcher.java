/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lbc.internal.equinox;

import static org.eclipse.passage.lic.net.LicensingNet.ROLE;
import static org.eclipse.passage.lic.net.LicensingNet.ROLE_LICENSEE;

import java.util.Map;

import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.base.BaseRequestDispatcher;
import org.eclipse.passage.lic.api.LicensingReporter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(service = BackendRequestDispatcher.class, property = ROLE + '=' + ROLE_LICENSEE)
public class LicenseeRequestDispatcher extends BaseRequestDispatcher {

	@Activate
	@Override
	public void activate(Map<String, Object> context) {
		super.activate(context);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	@Override
	public void bindLicensingReporter(LicensingReporter reporter) {
		super.bindLicensingReporter(reporter);
	}

	@Override
	public void unbindLicensingReporter(LicensingReporter reporter) {
		super.unbindLicensingReporter(reporter);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void bindBackendActionExecutor(BackendActionExecutor executor, Map<String, Object> properties) {
		super.bindBackendActionExecutor(executor, properties);
	}

	@Override
	public void unbindBackendActionExecutor(BackendActionExecutor executor, Map<String, Object> properties) {
		super.unbindBackendActionExecutor(executor, properties);
	}

}
