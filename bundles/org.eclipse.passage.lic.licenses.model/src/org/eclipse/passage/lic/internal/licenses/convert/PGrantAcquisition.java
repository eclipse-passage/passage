/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.convert;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.base.acquire.BaseGrantAcquisition;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;

public final class PGrantAcquisition
		implements Supplier<org.eclipse.passage.lic.api.acquire.GrantAcquisition> {

	private final GrantAcqisition source;

	public PGrantAcquisition(GrantAcqisition source) {
		Objects.requireNonNull(source, "PGrantAcquisition::source"); //$NON-NLS-1$
		this.source = source;
	}

	@Override
	public org.eclipse.passage.lic.api.acquire.GrantAcquisition get() {
		return new BaseGrantAcquisition(//
				source.getIdentifier(), //
				source.getGrant(), //
				source.getFeature(), //
				source.getUser(), //
				source.getCreated());
	}

}
