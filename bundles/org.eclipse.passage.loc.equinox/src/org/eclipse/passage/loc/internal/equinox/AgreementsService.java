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
package org.eclipse.passage.loc.internal.equinox;

import java.util.Optional;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.loc.internal.api.workspace.Agreements;

@SuppressWarnings("restriction")
public final class AgreementsService {

	public Agreements get() throws LicensingException {
		Optional<Agreements> service = new OperatorGearAware()
				.withGear(gear -> Optional.of(gear.workspace().agreements()));
		if (!service.isPresent()) {
			throw new LicensingException("There is no Agreements service supplied by Operator Workspace"); //$NON-NLS-1$
		}
		return service.get();
	}

}
