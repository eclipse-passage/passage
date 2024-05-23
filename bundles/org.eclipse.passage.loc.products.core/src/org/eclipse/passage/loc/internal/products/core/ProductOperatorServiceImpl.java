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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.internal.e4.events.OperatorProductEvents;
import org.eclipse.passage.loc.internal.equinox.OperatorProductService;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component
public final class ProductOperatorServiceImpl implements OperatorProductService {

	private final List<EventAdmin> events = new ArrayList<>();

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	public void bindEventAdmin(EventAdmin admin) {
		this.events.add(admin);
	}

	public void unbindEventAdmin(EventAdmin admin) {
		this.events.remove(admin);
	}

	@Override
	public String createPassword(ProductVersion descriptor) {
		return new ProductVersionPassword(descriptor).get();
	}

	@Override
	public IStatus createProductKeys(ProductVersion target) {
		return new ProductVersionKeys(FrameworkUtil.getBundle(getClass()).getSymbolicName(), this::broadcast)
				.createKeys(target);
	}

	private void broadcast(String info) {
		events.stream().findAny().get().postEvent(new OperatorProductEvents().keysCreated(info));
	}

}
