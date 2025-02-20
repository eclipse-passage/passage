/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.base.io.LenientFileCollection;
import org.eclipse.passage.lic.base.io.PassageFileExtension;

/**
 * @since 4.1
 */
public abstract class LenientLocalConditions extends BaseLocalConditions {

	protected LenientLocalConditions(ConditionMiningTarget id, MiningEquipment equipment, PassageFileExtension scope) {
		super(id, equipment, scope);
	}

	protected LenientLocalConditions(ConditionMiningTarget id, MiningEquipment equipment) {
		this(id, equipment, new PassageFileExtension.LicenseEncrypted());
	}

	@Override
	protected final Collection<Path> licenses(LicensedProduct product) throws LicensingException {
		return new LenientFileCollection(base(product), scope).get();
	}

}
