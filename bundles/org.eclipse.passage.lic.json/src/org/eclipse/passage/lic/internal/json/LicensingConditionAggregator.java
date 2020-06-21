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
package org.eclipse.passage.lic.internal.json;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * @deprecated use revised version of transport (tobemoved package)
 */
@Deprecated
public class LicensingConditionAggregator {

	private final List<LicensingConditionMixIn> licensingConditions = new ArrayList<>();

	public Iterable<LicensingConditionMixIn> getLicensingConditions() {
		return licensingConditions;
	}

	public void addLicensingCondition(LicensingCondition d) {
		LicensingConditionMixIn mixIn = LicensingConditionMixIn.create(d);
		licensingConditions.add(mixIn);
	}

}
