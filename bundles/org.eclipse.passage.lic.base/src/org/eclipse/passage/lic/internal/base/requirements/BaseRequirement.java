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
package org.eclipse.passage.lic.internal.base.requirements;

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

@SuppressWarnings("restriction")
public final class BaseRequirement implements Requirement {

	private final Feature feature;
	private final RestrictionLevel restriction;
	private final Object source;

	public BaseRequirement(Feature feature, RestrictionLevel restriction, Object source) {
		Objects.requireNonNull(feature);
		Objects.requireNonNull(restriction);
		Objects.requireNonNull(source);
		this.feature = feature;
		this.restriction = restriction;
		this.source = source;
	}

	@Override
	public Feature feature() {
		return feature;
	}

	@Override
	public RestrictionLevel restrictionLevel() {
		return restriction;
	}

	@Override
	public Object source() {
		return source;
	}

}
