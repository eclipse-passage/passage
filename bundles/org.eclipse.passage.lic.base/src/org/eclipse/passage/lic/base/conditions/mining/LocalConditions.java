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

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.NoLicenses;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.base.io.FileCollection;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.i18n.ConditionMiningMessages;

/**
 * <p>
 * Scans the configured part of the local file system for encrypted license
 * files, read 'em and retrieve all the licensing {@linkplain Condition}s they
 * declare.
 * </p>
 * 
 * @since 2.1
 */
public abstract class LocalConditions implements MinedConditions {

	private final ConditionMiningTarget id;
	private final MiningEquipment equipment;
	private final PassageFileExtension scope;

	protected LocalConditions(ConditionMiningTarget id, MiningEquipment equipment, PassageFileExtension scope) {
		String cls = getClass().getSimpleName();
		Objects.requireNonNull(id, cls + "::id"); //$NON-NLS-1$
		Objects.requireNonNull(equipment, cls + "::equipment"); //$NON-NLS-1$
		Objects.requireNonNull(scope, cls + "::scope"); //$NON-NLS-1$
		this.id = id;
		this.equipment = equipment;
		this.scope = scope;
	}

	protected LocalConditions(ConditionMiningTarget id, MiningEquipment equipment) {
		this(id, equipment, new PassageFileExtension.LicenseEncrypted());
	}

	@Override
	public final ConditionMiningTarget id() {
		return id;
	}

	@Override
	public final ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product) {
		try {
			Collection<Path> licenses = licenses(product);
			if (licenses.isEmpty()) {
				return noLicenses(product);
			}
			return equipment.tool(product, id).mine(licenses);
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<Collection<ConditionPack>>( //
					new Trouble(//
							new ServiceFailedOnInfrastructureDenial(), //
							ConditionMiningMessages.getString("LocalConditions.failed"), //$NON-NLS-1$
							e));
		}
	}

	private ServiceInvocationResult<Collection<ConditionPack>> noLicenses(LicensedProduct product) {
		return new BaseServiceInvocationResult<Collection<ConditionPack>>(//
				new BaseDiagnostic(//
						Collections.emptyList(), //
						Collections.singletonList(//
								new Trouble(//
										new NoLicenses(), //
										String.format(//
												ConditionMiningMessages.getString("LocalConditions.no_licenses"), //$NON-NLS-1$
												base(product).get().toAbsolutePath())))//
				), //
				Collections.emptyList()//
		);
	}

	private Collection<Path> licenses(LicensedProduct product) throws LicensingException {
		return new FileCollection(base(product), scope).get();
	}

	protected abstract Supplier<Path> base(LicensedProduct product);

}
