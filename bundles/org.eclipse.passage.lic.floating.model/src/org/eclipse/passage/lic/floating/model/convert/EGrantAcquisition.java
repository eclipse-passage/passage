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
package org.eclipse.passage.lic.floating.model.convert;

import java.util.function.Supplier;

import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.floating.model.meta.FloatingFactory;

public final class EGrantAcquisition implements Supplier<GrantAcqisition> {

	private final org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition source;

	public EGrantAcquisition(org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition source) {
		this.source = source;
	}

	@Override
	public GrantAcqisition get() {
		GrantAcqisition grant = FloatingFactory.eINSTANCE.createGrantAcqisition();
		grant.setIdentifier(source.identifier());
		grant.setGrant(source.grant());
		grant.setFeature(source.feature());
		grant.setUser(source.user());
		grant.setCreated(source.created());
		return grant;
	}

}
