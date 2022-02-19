/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;

public final class UnregisterUri implements Consumer<URI> {

	private final LocDomainRegistryAccess access;

	public UnregisterUri(LocDomainRegistryAccess access) {
		Objects.requireNonNull(access, "LocDomainRegistryAccess access"); //$NON-NLS-1$
		this.access = access;
	}

	@Override
	public void accept(URI uri) {
		Optional.ofNullable(uri)//
				.flatMap(u -> Optional.ofNullable(u.fileExtension()))//
				.flatMap(e -> access.domainRegistryForExtension(e))//
				.filter(EditingDomainRegistry.class::isInstance)//
				.map(EditingDomainRegistry.class::cast)//
				.ifPresent(r -> r.unregisterSource(uri));
	}

}
