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

package org.eclipse.passage.lbc.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;

public class ConditionsArbitr {

	/**
	 * Descriptors reserved on a minute
	 */
	private final List<LicensingCondition> reservedDescriptors = new ArrayList<>();

	/**
	 * Descriptors in lease on an hour
	 */
	private final List<LicensingCondition> leasedDescriptors = new ArrayList<>();

	public LicensingResult reserveCondition(LicensingCondition descriptor) {
		reservedDescriptors.add(descriptor);
		return LicensingResults.createOK();
	}

	public LicensingResult leaseCondition(LicensingCondition descriptor) {
		leasedDescriptors.add(descriptor);
		return LicensingResults.createOK();
	}

}
