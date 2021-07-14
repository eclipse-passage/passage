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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Optional;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.api.ClassSupply;
import org.eclipse.passage.loc.internal.api.ComposableClassSupply;
import org.eclipse.passage.loc.internal.api.ComposedClassSupply;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * 
 * Registers an instance of {@link ComposableClassSupply} to be available via
 * OSGi and to be used from service context
 * 
 * @see EclipseContextFactory#getServiceContext(org.osgi.framework.BundleContext)
 * @see IEclipseContext#get(Class)
 *
 */
@Component
public class LocClassSupply implements ComposableClassSupply {

	private final ComposableClassSupply delegate;

	public LocClassSupply() {
		delegate = new ComposedClassSupply();
	}

	@Override
	public Optional<InstanceSupply<?>> find(Class<?> clazz, MandatoryService context) {
		return delegate.find(clazz, context);
	}

	@Override
	public void consider(ClassSupply fragment) {
		delegate.consider(fragment);
	}

	@Override
	public void forget(ClassSupply fragment) {
		delegate.forget(fragment);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	void bind(ClassSupply reference) {
		consider(reference);
	}

	void unbind(ClassSupply reference) {
		forget(reference);
	}

}
