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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;

@SuppressWarnings("restriction")
public final class RemoteConditions implements MinedConditions {

	private final StringServiceId id = new StringServiceId("remote"); //$NON-NLS-1$
	private final ConditionTransportRegistry transports;

	public RemoteConditions(ConditionTransportRegistry transports) {
		this.transports = transports;
	}

	@Override
	public StringServiceId id() {
		return id;
	}

	// FIXME: consider caching (ttl-ed)
	@Override
	public Collection<Condition> all() throws ConditionMiningException {
		return new HttpClient().remoteConditions(//
				new LicensingServerRequest(), //
				new DecryptedConditions(transports));
	}

}
