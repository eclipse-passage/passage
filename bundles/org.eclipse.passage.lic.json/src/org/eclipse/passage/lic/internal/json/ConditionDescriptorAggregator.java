/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.json;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.base.BaseLicensingCondition;

public class ConditionDescriptorAggregator {

	private final List<BaseLicensingCondition> licensingConditions = new ArrayList<>();

	public List<BaseLicensingCondition> getLicensingConditions() {
		return licensingConditions;
	}

	public void addLicensingCondition(BaseLicensingCondition d) {
		licensingConditions.add(d);
	}

}
