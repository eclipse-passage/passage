/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
import org.eclipse.passage.lic.emf.meta.ClassMetadata;
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.emf.meta.ComposedClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * 
 * Registers an instance of {@link ComposableClassMetadata} to be available via
 * OSGi and to be used from service context
 * 
 * @see EclipseContextFactory#getServiceContext(org.osgi.framework.BundleContext)
 * @see IEclipseContext#get(Class)
 *
 */
@Component
public class LocClassMetadata implements ComposableClassMetadata {

	private final ComposableClassMetadata delegate;

	public LocClassMetadata() {
		delegate = new ComposedClassMetadata();
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return delegate.find(clazz);
	}

	@Override
	public void consider(ClassMetadata fragment) {
		delegate.consider(fragment);
	}

	@Override
	public void forget(ClassMetadata fragment) {
		delegate.forget(fragment);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	void bind(ClassMetadata metadata) {
		consider(metadata);
	}

	void unbind(ClassMetadata metadata) {
		forget(metadata);
	}

}
