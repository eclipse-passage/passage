/*******************************************************************************
 * Copyright (c) 2020, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - evolved to grow configurable in the place of actual files supplying (#1482)
 *******************************************************************************/
package org.eclipse.passage.lic.base.conditions.mining;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.base.io.FileCollection;
import org.eclipse.passage.lic.base.io.PassageFileExtension;

/**
 * <p>
 * Scans the configured part of the local file system for encrypted license
 * files, reads them and retrieves all the licensing {@linkplain Condition}s
 * they declare.
 * </p>
 * 
 * @since 2.1
 */
public abstract class LocalConditions extends BaseLocalConditions {

	protected LocalConditions(ConditionMiningTarget id, MiningEquipment equipment, PassageFileExtension scope) {
		super(id, equipment, scope);
	}

	protected LocalConditions(ConditionMiningTarget id, MiningEquipment equipment) {
		this(id, equipment, new PassageFileExtension.LicenseEncrypted());
	}

	/**
	 * @since 4.1
	 */
	@Override
	protected final Collection<Path> licenses(LicensedProduct product) throws LicensingException {
		return new FileCollection(base(product), scope).get();
	}

}
