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

package org.eclipse.passage.lic.base.restrictions;

import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.requirements.BaseFeature;

final class MissingRequirement implements Requirement {

	@Override
	public Feature feature() {
		return new BaseFeature(//
				new BaseFeatureIdentifier("unknown-feature-id"), //$NON-NLS-1$
				"0.0.0", //$NON-NLS-1$
				"unknown-feature-name", //$NON-NLS-1$
				service());
	}

	@Override
	public RestrictionLevel restrictionLevel() {
		return new RestrictionLevel.Error();
	}

	@Override
	public List<ResolvedAgreement> agreements() {
		return Collections.emptyList();
	}

	@Override
	public Object source() {
		return service();
	}

	private String service() {
		return "Passage Access Cycle"; //$NON-NLS-1$
	}

}
