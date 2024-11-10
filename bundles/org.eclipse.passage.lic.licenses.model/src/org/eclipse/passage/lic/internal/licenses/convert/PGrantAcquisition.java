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
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.internal.licenses.convert;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.acquire.BaseGrantAcquisition;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;

public final class PGrantAcquisition implements Supplier<org.eclipse.passage.lic.api.acquire.GrantAcquisition> {

	private final GrantAcqisition source;

	public PGrantAcquisition(GrantAcqisition source) {
		this.source = Objects.requireNonNull(source);
	}

	@Override
	public org.eclipse.passage.lic.api.acquire.GrantAcquisition get() {
		return new BaseGrantAcquisition(//
				source.getIdentifier(), //
				source.getGrant(), //
				new BaseFeatureIdentifier(source.getFeature()), //
				source.getUser(), //
				source.getCreated());
	}

}
