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
package org.eclipse.passage.loc.internal.workbench.registry;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

public final class UnregisterSelected {

	private final Consumer<URI> unregister;
	private final Predicate<List<URI>> accept;

	public UnregisterSelected(Consumer<URI> unregister, Predicate<List<URI>> accept) {
		Objects.requireNonNull(unregister, "Consumer<URI> unregister"); //$NON-NLS-1$
		Objects.requireNonNull(accept, "Predicate<List<URI>> accept"); //$NON-NLS-1$
		this.unregister = unregister;
		this.accept = accept;
	}

	public void unregister(Object selection) {
		List<URI> remove = Arrays.stream(//
				Optional.of(selection)//
						.filter(Object[].class::isInstance)//
						.map(Object[].class::cast)//
						.orElse(new Object[] { selection }))//
				.filter(Resource.class::isInstance)//
				.map(Resource.class::cast).map(Resource::getURI)//
				.filter(Objects::nonNull).collect(Collectors.toList());
		if (remove.isEmpty() || !accept.test(remove)) {
			return;
		}
		remove.forEach(unregister);
	}

}
