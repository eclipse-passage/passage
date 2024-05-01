/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.products.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.internal.e4.events.OperatorProductEvents;
import org.eclipse.passage.loc.internal.equinox.OperatorProductService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component
public class ProductOperatorServiceImpl implements OperatorProductService {

	private String plugin;
	@Reference
	private EnvironmentInfo environment;
	@Reference
	private EventAdmin events;

	@Activate
	public void activate(BundleContext context) {
		plugin = context.getBundle().getSymbolicName();
	}

	@Override
	public String createPassword(ProductVersion descriptor) {
		return new ProductVersionPassword(descriptor).get();
	}

	@Override
	public IStatus createProductKeys(ProductVersion target) {
		return new ProductVersionKeys(plugin, this::broadcast).createKeys(target);
	}

	private void broadcast(String info) {
		events.postEvent(new OperatorProductEvents().keysCreated(info));
	}

}
