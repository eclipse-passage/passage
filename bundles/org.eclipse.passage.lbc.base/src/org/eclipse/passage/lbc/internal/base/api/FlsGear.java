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
package org.eclipse.passage.lbc.internal.base.api;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.Gear;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.StreamCodec;

public interface FlsGear extends Gear {

	HashesRegistry hashes();

	KeyKeeper keyKeper(LicensedProduct product, Supplier<Path> residense);

	StreamCodec codec(LicensedProduct product);

	ConditionTransport transport(ContentType contentType);

	FloatingState state(Supplier<Path> storage);

}
