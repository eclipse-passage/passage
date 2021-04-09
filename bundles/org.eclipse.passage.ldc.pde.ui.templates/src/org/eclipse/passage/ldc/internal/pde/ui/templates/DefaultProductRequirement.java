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
package org.eclipse.passage.ldc.internal.pde.ui.templates;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;

@SuppressWarnings("restriction")
final class DefaultProductRequirement implements Supplier<Requirement> {

	private final Supplier<String> product;
	private final Supplier<String> version;
	private final Supplier<String> name;
	private final Supplier<String> provider;

	DefaultProductRequirement(Supplier<String> product, Supplier<String> name, Supplier<String> version,
			Supplier<String> provider) {
		this.product = product;
		this.version = version;
		this.name = name;
		this.provider = provider;
	}

	@Override
	public Requirement get() {
		return new BaseRequirement(//
				new BaseFeature(//
						product.get(), //
						version.get(), //
						name.get(), //
						provider.get()), //
				new RestrictionLevel.Warning(), //
				this);
	}

}
