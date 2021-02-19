/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.equinox;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.loc.internal.api.OperatorGear;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public final class OperatorGearAware {

	public <T> Optional<T> withGear(Function<OperatorGear, Optional<T>> with) {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		Collection<ServiceReference<OperatorGearSupplier>> references = Collections.emptyList();
		try {
			references = context.getServiceReferences(OperatorGearSupplier.class, null);
		} catch (InvalidSyntaxException e) {
			return Optional.empty();
		}
		if (references.isEmpty()) {
			return Optional.empty();
		}
		ServiceReference<OperatorGearSupplier> any = references.iterator().next();
		try {
			return with.apply(context.getService(any).gear());
		} finally {
			context.ungetService(any);
		}
	}

}
