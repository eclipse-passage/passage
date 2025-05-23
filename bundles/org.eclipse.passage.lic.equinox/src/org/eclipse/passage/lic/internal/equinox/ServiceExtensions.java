/*******************************************************************************
 * Copyright (c) 2022, 2025 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.RegistryFactory;

/**
 * <p>
 * Reads and instantiates executables for the most common service-supplying
 * extensions of sequence<service<class>> structure.
 * </p>
 * <p>
 * In case there is a trouble with any single extension, logs details and
 * proceeds with the rest of them.
 * </p>
 */
public final class ServiceExtensions<S> implements Supplier<List<S>> {

	private final String namespace;
	private final String point;
	private final Class<S> service;

	public ServiceExtensions(String namespace, String point, Class<S> service) {
		Objects.requireNonNull(namespace, "ServiceExtensions::namespace"); //$NON-NLS-1$
		Objects.requireNonNull(point, "ServiceExtensions::point"); //$NON-NLS-1$
		Objects.requireNonNull(service, "ServiceExtensions::service"); //$NON-NLS-1$
		this.namespace = namespace;
		this.point = point;
		this.service = service;
	}

	@Override
	public List<S> get() {
		return Arrays.stream(extensions())//
				.map(IExtension::getConfigurationElements)//
				.flatMap(Arrays::stream)//
				.map(this::service)//
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toList());
	}

	private IExtension[] extensions() {
		return RegistryFactory.getRegistry().getExtensionPoint(namespace, point).getExtensions();
	}

	private Optional<S> service(IConfigurationElement config) {
		try {
			Object executable = config.createExecutableExtension("class"); //$NON-NLS-1$
			return Optional.of(service.cast(executable));
		} catch (CoreException e) {
			System.err.println("failed to instanciate a service ");//$NON-NLS-1$
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
