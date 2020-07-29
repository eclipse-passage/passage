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
package org.eclipse.passage.lic.api.tests.fakes.restrictions;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.api.restrictions.execution.RestrictionExecutingService;

@SuppressWarnings("restriction")
public class FakeRestrictionExecutingService implements RestrictionExecutingService {

	@Override
	public StringServiceId id() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute(Collection<Restriction> restrictions) {
		throw new UnsupportedOperationException();
	}

}
