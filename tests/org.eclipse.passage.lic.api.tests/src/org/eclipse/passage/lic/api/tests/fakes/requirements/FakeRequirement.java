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
package org.eclipse.passage.lic.api.tests.fakes.requirements;

import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;

public final class FakeRequirement implements Requirement {

	private final Feature feature;
	private final RestrictionLevel level;

	public FakeRequirement(RestrictionLevel level) {
		this(new FakeFeature(), level);
	}

	public FakeRequirement(String feature) {
		this(new FakeFeature(feature), new RestrictionLevel.Error());
	}

	public FakeRequirement() {
		this(new FakeFeature(), new RestrictionLevel.Error());
	}

	public FakeRequirement(Feature feature, RestrictionLevel level) {
		this.feature = feature;
		this.level = level;
	}

	@Override
	public Feature feature() {
		return feature;
	}

	@Override
	public RestrictionLevel restrictionLevel() {
		return level;
	}

	@Override
	public List<ResolvedAgreement> agreements() {
		return Collections.emptyList();
	}

	@Override
	public Object source() {
		return "API Tests"; //$NON-NLS-1$
	}

}
