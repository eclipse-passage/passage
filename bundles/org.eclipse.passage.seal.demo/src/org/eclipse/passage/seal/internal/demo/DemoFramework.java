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
package org.eclipse.passage.seal.internal.demo;

import java.util.Arrays;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.internal.equinox.requirements.ComponentRequirements;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteConditions;
import org.eclipse.passage.lic.internal.json.tobemoved.JsonConditionTransport;

@SuppressWarnings("restriction")
final class DemoFramework implements Framework {

	private final Registry<StringServiceId, ResolvedRequirements> requirements;
	private final Registry<StringServiceId, MinedConditions> conditions;
	private final Registry<ContentType, ConditionTransport> transports;
	static final Framework demo = new DemoFramework();

	private DemoFramework() {
		requirements = //
				new ReadOnlyRegistry<>(Arrays.asList(//
						new BundleRequirements(), //
						new ComponentRequirements()) //
				);
		conditions = //
				new ReadOnlyRegistry<>(Arrays.asList(//
						new RemoteConditions(transports())//
				)//
				);
		transports = new ReadOnlyRegistry<>(Arrays.asList(//
				new JsonConditionTransport()//
		));
	}

	@Override
	public ResolvedRequirementsRegistry requirementsRegistry() {
		return () -> requirements;
	}

	@Override
	public MinedConditionsRegistry conditionsRegistry() {
		return () -> conditions;
	}

	private ConditionTransportRegistry transports() {
		return () -> transports;
	}

}
