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
package org.eclipse.passage.lbc.internal.base;

import java.util.Collections;

import org.eclipse.passage.lbc.internal.api.BackendServerConfiguration;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;

/**
 * @since 1.0
 */
public class EmptyServerConfiguration implements BackendServerConfiguration {

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return () -> new ReadOnlyRegistry<>(Collections.emptyList());
	}

	@Override
	public StreamCodecRegistry codecs() {
		return () -> new ReadOnlyRegistry<>(Collections.emptyList());
	}

	@Override
	public KeyKeeperRegistry keyKeepers() {
		return () -> new ReadOnlyRegistry<>(Collections.emptyList());
	}

	@Override
	public ConditionTransportRegistry transports() {
		return () -> new ReadOnlyRegistry<>(Collections.emptyList());
	}

}
