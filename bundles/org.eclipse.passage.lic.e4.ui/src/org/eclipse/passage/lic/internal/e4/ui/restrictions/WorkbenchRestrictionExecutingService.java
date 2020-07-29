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
package org.eclipse.passage.lic.internal.e4.ui.restrictions;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.api.restrictions.execution.RestrictionExecutingService;

@SuppressWarnings("restriction")
public final class WorkbenchRestrictionExecutingService implements RestrictionExecutingService {

	private final StringServiceId id = new StringServiceId("workbench-restriction-executor"); //$NON-NLS-1$

	@Override
	public StringServiceId id() {
		return id;
	}

	@Override
	public void execute(Collection<Restriction> restrictions) {

	}

}
