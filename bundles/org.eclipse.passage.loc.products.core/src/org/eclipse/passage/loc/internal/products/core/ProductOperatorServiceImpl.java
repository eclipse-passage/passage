/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.products.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.loc.internal.api.OperatorProductEvents;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component
public class ProductOperatorServiceImpl implements OperatorProductService {

	private String plugin;
	private EnvironmentInfo environment;
	private EventAdmin events;

	@Activate
	public void activate(BundleContext context) {
		plugin = context.getBundle().getSymbolicName();
	}

	@Reference
	public void bindEnvironmentInfo(EnvironmentInfo info) {
		this.environment = info;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo info) {
		if (environment == info) {
			this.environment = null;
		}
	}

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.events = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		if (this.events == admin) {
			this.events = null;
		}
	}

	@Override
	public String createPassword(ProductVersionDescriptor descriptor) {
		return new ProductVersionPassword(descriptor).get();
	}

	@Override
	public IStatus createProductKeys(ProductVersionDescriptor target) {
		return new ProductVersionKeys(plugin, this::broadcast).createKeys(target);
	}

	private void broadcast(String info) {
		events.postEvent(OperatorProductEvents.keysCreated(info));
	}

}
