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
package org.eclipse.passage.lbc.internal.base.mine;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionOrigin;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.internal.base.conditions.mining.ArmedMiningTool;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

final class ReassemblingMiningTool extends ArmedMiningTool {

	private final String user;
	private final LicensedProduct product;
	private final Logger log = LogManager.getLogger(getClass());

	ReassemblingMiningTool(KeyKeeper key, StreamCodec codec, ConditionTransport transport, LicensedProduct product,
			String user, ConditionMiningTarget miner) {
		super(key, codec, transport, miner);
		this.product = product;
		this.user = user;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> mine(Collection<Path> sources) {
		Collection<ConditionPack> packs = new ArrayList<>();
		List<Trouble> failures = new ArrayList<>();
		for (Path license : sources) {
			try {
				packs.add(conditions(license));
			} catch (LicensingException e) {
				log.error("failed: ", e); //$NON-NLS-1$
				failures.add(new Trouble(//
						new ServiceFailedOnMorsel(), //
						String.format(BaseMessages.ReassemblingMiningTool_path_failed,
								license.toAbsolutePath().toString()),
						e));
			}
		}
		return new BaseServiceInvocationResult<>(//
				new BaseDiagnostic(Collections.emptyList(), failures), packs);
	}

	private ConditionPack conditions(Path license) throws LicensingException {
		FloatingLicensePack pack = pack(license);
		if (!productFits(pack.getLicense().getProduct())) {
			return noConditions(license);
		}
		Collection<Condition> conditions = new AssembledConditions(pack).forUser(user);
		return new BaseConditionPack(origin(license), conditions);
	}

	private boolean productFits(ProductRef ref) {
		return new BaseLicensedProduct(ref.getProduct(), ref.getVersion()).equals(product);
	}

	private FloatingLicensePack pack(Path source) throws LicensingException {
		return new EObjectFromBytes<>(//
				decoded(source), //
				FloatingLicensePack.class//
		).get(Collections.singletonMap(LicensesPackage.eNS_URI, LicensesPackage.eINSTANCE));
	}

	private BaseConditionPack noConditions(Path license) {
		return new BaseConditionPack(//
				origin(license), //
				Collections.emptyList());
	}

	private BaseConditionOrigin origin(Path license) {
		return new BaseConditionOrigin(miner, license.toAbsolutePath().toString());
	}

}
