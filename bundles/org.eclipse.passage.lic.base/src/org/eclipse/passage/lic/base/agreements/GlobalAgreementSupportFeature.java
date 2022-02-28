/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.base.agreements;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.base.requirements.BaseFeature;

public final class GlobalAgreementSupportFeature implements Supplier<Feature> {

	private final LicensedProduct product;

	public GlobalAgreementSupportFeature(LicensedProduct product) {
		this.product = product;
	}

	@Override
	public Feature get() {
		return new BaseFeature(//
				product.identifier(), //
				product.version(), //
				"Global Agreements Support by Passage", //$NON-NLS-1$
				"Eclipse Passage Runtime" //$NON-NLS-1$
		);
	}

}
