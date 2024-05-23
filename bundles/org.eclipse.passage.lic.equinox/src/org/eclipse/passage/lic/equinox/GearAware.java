/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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
package org.eclipse.passage.lic.equinox;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 2.1
 */
public abstract class GearAware<G, S extends Supplier<G>> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public final <T> Optional<T> withGear(Unsafe<G, T> with) throws LicensingException {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		Collection<ServiceReference<S>> references = Collections.emptyList();
		try {
			references = context.getServiceReferences(supplier(), null);
		} catch (InvalidSyntaxException e) {
			log.error("Failed to resolve service references", e); //$NON-NLS-1$
			return Optional.empty();
		}
		if (references.isEmpty()) {
			log.error("No reference of service " + supplier().getName()); //$NON-NLS-1$
			return Optional.empty();
		}
		ServiceReference<S> any = references.iterator().next();
		try {
			return with.apply(context.getService(any).get());
		} catch (Exception e) {
			throw new LicensingException("Error on service invocation", e); //$NON-NLS-1$
		} finally {
			context.ungetService(any);
		}
	}

	protected abstract Class<S> supplier();

	@FunctionalInterface
	public interface Unsafe<G, T> {

		Optional<T> apply(G gear) throws Exception;

	}
}
