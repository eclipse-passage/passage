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
package org.eclipse.passage.ldc.internal.pde.ui.templates;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;

@SuppressWarnings("restriction")
final class DefaultProductRequirement implements Supplier<Requirement> {
	private final String product;

	DefaultProductRequirement(String product) {
		this.product = product;
	}

	@Override
	public Requirement get() {
		return new BaseRequirement(//
				new BaseFeature(//
						product, //
						"1.0.0", //$NON-NLS-1$
						product, //
						"Eclipse Passage Template"), //$NON-NLS-1$
				new RestrictionLevel.Warning(), //
				this);
	}

}
