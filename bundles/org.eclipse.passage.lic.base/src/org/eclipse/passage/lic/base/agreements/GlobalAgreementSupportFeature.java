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
package org.eclipse.passage.lic.base.agreements;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.base.requirements.BaseFeature;

public final class GlobalAgreementSupportFeature implements Supplier<Feature> {

	@Override
	public Feature get() {
		return new BaseFeature(//
				"passage.global-agreement-support.feature", //$NON-NLS-1$
				"0.0.0", //$NON-NLS-1$
				"Global Agreements Support", //$NON-NLS-1$
				"Eclipse Passage Runtime" //$NON-NLS-1$
		);
	}

}
