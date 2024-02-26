/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further evolution
 *******************************************************************************/
package org.eclipse.passage.loc.internal.workbench;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;

public abstract class SupplySelectRequest<C> implements Supplier<SelectRequest<C>> {

	protected final MandatoryService context;
	protected final Collection<C> initial;

	public SupplySelectRequest(MandatoryService context) {
		this(context, Collections.emptyList());
	}

	public SupplySelectRequest(MandatoryService context, Optional<C> initial) {
		this(context, initial.map(Collections::singletonList).orElseGet(Collections::emptyList));
	}

	public SupplySelectRequest(MandatoryService context, Collection<C> initial) {
		this.context = context;
		this.initial = initial;
	}

	protected LabelProvider labels() {
		return new DomainRegistryLabelProvider();
	}
}
