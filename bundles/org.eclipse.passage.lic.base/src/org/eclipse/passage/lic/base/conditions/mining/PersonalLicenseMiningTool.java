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
package org.eclipse.passage.lic.base.conditions.mining;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport.Data;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.conditions.BaseConditionOrigin;
import org.eclipse.passage.lic.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.NoRelevantConditions;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

final class PersonalLicenseMiningTool extends ArmedMiningTool {

	public PersonalLicenseMiningTool(KeyKeeper key, StreamCodec codec, ConditionTransport transport,
			ConditionMiningTarget miner) {
		super(key, codec, transport, miner);
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> mine(Collection<Path> sources) {
		return sources.stream()//
				.map(path -> mine(path)) //
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<ConditionPack>())) //
				.orElse(new BaseServiceInvocationResult<Collection<ConditionPack>>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<ConditionPack>> mine(Path source) {
		try {
			Data data = from(decoded(source));
			return new BaseServiceInvocationResult<>(//
					diagnostic(data.conditions(), source), //
					Collections.singleton(//
							new BaseConditionPack(//
									new BaseConditionOrigin(miner, source(source), data.signature()), //
									data.conditions(), //
									data.agreements())//
					));
		} catch (IOException | LicensingException e) {
			return new BaseServiceInvocationResult<>(//
					new Trouble(//
							new ServiceFailedOnMorsel(), //
							String.format(BaseMessages.getString("MiningTool.error_mining_file"), //$NON-NLS-1$
									source.normalize().toAbsolutePath()), //
							e));
		}
	}

	private Data from(byte[] decoded) throws IOException {
		try (ByteArrayInputStream input = new ByteArrayInputStream(decoded)) {
			return transport.read(input);
		}
	}

	private Diagnostic diagnostic(Collection<Condition> conditions, Path source) {
		if (conditions.isEmpty()) {
			return new BaseDiagnostic(//
					Collections.emptyList(), //
					Collections.singletonList(//
							new Trouble(new NoRelevantConditions(), source.toAbsolutePath().toString()))//
			);
		}
		return new BaseDiagnostic();
	}

}
