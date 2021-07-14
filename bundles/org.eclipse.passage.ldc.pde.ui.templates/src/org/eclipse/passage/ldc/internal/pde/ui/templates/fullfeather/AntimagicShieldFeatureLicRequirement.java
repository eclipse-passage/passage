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
package org.eclipse.passage.ldc.internal.pde.ui.templates.fullfeather;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.base.requirements.BaseFeature;
import org.eclipse.passage.lic.base.requirements.BaseRequirement;

public final class AntimagicShieldFeatureLicRequirement implements Supplier<Requirement> {

	@Override
	public Requirement get() {
		return new BaseRequirement(//
				new BaseFeature(//
						"antimagic-shield", //$NON-NLS-1$
						"2.72", //$NON-NLS-1$
						"Antimagic protection", //$NON-NLS-1$
						"Passage E4FF Template"), //$NON-NLS-1$
				new RestrictionLevel.Warning(), //
				this //
		);
	}

}
