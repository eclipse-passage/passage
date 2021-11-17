/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.base.io.MD5Hashes;
import org.eclipse.passage.lic.base.io.PathKeyKeeper;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.eclipse.passage.lic.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.licenses.model.transport.XmiConditionTransport;

final class TestEquipment implements Supplier<Equipment> {

	private final LicensedProduct product;
	private final Supplier<Path> source;

	public TestEquipment(LicensedProduct product, Supplier<Path> source) {
		this.product = product;
		this.source = source;
	}

	@Override
	public Equipment get() {
		return new Equipment(keys(), codecs(), transports(), hashes());
	}

	private StreamCodecRegistry codecs() {
		return () -> new ReadOnlyRegistry<>(new BcStreamCodec(() -> product));
	}

	private KeyKeeperRegistry keys() {
		return () -> new ReadOnlyRegistry<>(new PathKeyKeeper(product, source));
	}

	private HashesRegistry hashes() {
		return () -> new ReadOnlyRegistry<StringServiceId, Hashes>(new MD5Hashes());
	}

	private ConditionTransportRegistry transports() {
		return () -> new ReadOnlyRegistry<>(new XmiConditionTransport());
	}

}
