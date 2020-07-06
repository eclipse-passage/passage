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
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.base.io.FileCollection;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;

/**
 * <p>
 * Scans the configured part of the local file system for encrypted license
 * files, read 'em and retrieve all the licensing {@linkplain Condition}s they
 * declare.
 * </p>
 */
@SuppressWarnings("restriction")
public abstract class LocalConditions implements MinedConditions {

	private final StringServiceId id;
	private final MiningEquipment equipment;
	private final Consumer<LicensingException> handler;

	protected LocalConditions(StringServiceId id, MiningEquipment equipment, Consumer<LicensingException> handler) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(equipment);
		Objects.requireNonNull(handler);
		this.id = id;
		this.equipment = equipment;
		this.handler = handler;
	}

	@Override
	public final StringServiceId id() {
		return id;
	}

	@Override
	public final Collection<Condition> all(LicensedProduct product) throws ConditionMiningException {
		return equipment.tool(product).mine(licenses(product), handler);
	}

	private Collection<Path> licenses(LicensedProduct product) throws ConditionMiningException {
		try {
			return new FileCollection(new PathFromLicensedProduct(this::base, product),
					new PassageFileExtension.LicenseEncrypted()).get();
		} catch (LicensingException e) {
			throw new ConditionMiningException(//
					String.format(BaseMessages.getString("LocalConditions.failure"), id, product), e); //$NON-NLS-1$
		}
	}

	protected abstract Path base();

}
