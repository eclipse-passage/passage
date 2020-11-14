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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.internal.base.i18n.ConditionMiningMessages;
import org.eclipse.passage.lic.internal.base.io.FileCollection;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;

/**
 * <p>
 * Scans the configured part of the local file system for encrypted license
 * files, read 'em and retrieve all the licensing {@linkplain Condition}s they
 * declare.
 * </p>
 */
public abstract class LocalConditions implements MinedConditions {

	private final ConditionMiningTarget id;
	private final MiningEquipment equipment;

	protected LocalConditions(ConditionMiningTarget id, MiningEquipment equipment) {
		Objects.requireNonNull(id, getClass().getSimpleName() + "::id"); //$NON-NLS-1$
		Objects.requireNonNull(equipment, getClass().getSimpleName() + "::equipment"); //$NON-NLS-1$
		this.id = id;
		this.equipment = equipment;
	}

	@Override
	public final ConditionMiningTarget id() {
		return id;
	}

	@Override
	public final ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product) {
		try {
			return equipment.tool(product).mine(licenses(product));
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<Collection<ConditionPack>>( //
					new Trouble(//
							new ServiceFailedOnInfrastructureDenial(), //
							ConditionMiningMessages.getString("LocalConditions.failed"), //$NON-NLS-1$
							e));
		}
	}

	private Collection<Path> licenses(LicensedProduct product) throws LicensingException {
		return new FileCollection(base(product), new PassageFileExtension.LicenseEncrypted()).get();
	}

	protected abstract Supplier<Path> base(LicensedProduct product);

}
