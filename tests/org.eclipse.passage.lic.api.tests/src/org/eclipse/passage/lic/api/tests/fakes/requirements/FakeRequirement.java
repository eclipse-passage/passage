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
package org.eclipse.passage.lic.api.tests.fakes.requirements;

import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

@SuppressWarnings("restriction")
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
	public Object source() {
		return "API Tests"; //$NON-NLS-1$
	}

}
