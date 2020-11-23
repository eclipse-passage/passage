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
package org.eclipse.passage.lbc.internal.base.tobemoved.mine;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.passage.lbc.internal.base.tobemoved.ServerKeyKeeper;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.MiningTool;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;

final class ReassemblingMiningTool implements MiningTool {

	private final KeyKeeper key;
	private final StreamCodec codec;
	private final ConditionTransport transport;

	ReassemblingMiningTool(LicensedProduct product, String user) {
		this.key = new ServerKeyKeeper(product);
		this.codec = new BcStreamCodec(() -> product);
		this.transport = new AssemblingTransport();
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> mine(Collection<Path> sources) {
		return null;
	}

}
