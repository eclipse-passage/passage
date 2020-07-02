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
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.internal.equinox.requirements.ComponentRequirements;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteConditions;
import org.eclipse.passage.lic.internal.json.tobemoved.JsonConditionTransport;

@SuppressWarnings("restriction")
final class SealedAccessCycleConfiguration implements AccessCycleConfiguration {

	private final Registry<StringServiceId, ResolvedRequirements> requirements;
	private final Registry<StringServiceId, MinedConditions> conditions;
	private final Registry<ContentType, ConditionTransport> transports;
	private final Registry<LicensedProduct, StreamCodec> codecs;

	SealedAccessCycleConfiguration(Supplier<LicensedProduct> product) {
		requirements = new ReadOnlyRegistry<>(Arrays.asList(//
				new BundleRequirements(), //
				new ComponentRequirements() //
		));
		conditions = new ReadOnlyRegistry<>(Arrays.asList(//
				new RemoteConditions(product, transports())//
		// FIXME: path condition minders require codecs
		));
		transports = new ReadOnlyRegistry<>(Arrays.asList(//
				new JsonConditionTransport()//
		));
		codecs = new ReadOnlyRegistry<>(Arrays.asList(//
				new BcStreamCodec(product) //
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

	private StreamCodecRegistry codecs() {
		return () -> codecs;
	}

}
