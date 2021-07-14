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
package org.eclipse.passage.lbc.internal.base.mine;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.base.conditions.mining.LocalConditions;
import org.eclipse.passage.lic.base.io.FloatingFileExtension;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;

final class FloatingConditions extends LocalConditions {

	private final Supplier<Path> base;

	FloatingConditions(Supplier<Path> base, String user) {
		super(//
				new ConditionMiningTarget.Local().child("floating-server"), //$NON-NLS-1$
				new ReassemblingMiningEquipment(user, base), //
				new FloatingFileExtension.FloatingLicenseEncrypted());
		this.base = base;
	}

	@Override
	protected Supplier<Path> base(LicensedProduct product) {
		return new PathFromLicensedProduct(base, product);
	}

}