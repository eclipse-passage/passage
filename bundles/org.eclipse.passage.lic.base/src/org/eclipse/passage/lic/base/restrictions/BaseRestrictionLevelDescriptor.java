/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import org.eclipse.passage.lic.api.restrictions.RestrictionLevelDescriptor;

public class BaseRestrictionLevelDescriptor implements RestrictionLevelDescriptor {

	private final String identifier;
	private final String name;
	private final String description;

	public BaseRestrictionLevelDescriptor(String identifier, String name, String description) {
		this.identifier = identifier;
		this.name = name;
		this.description = description;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
