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
package org.eclipse.passage.loc.internal.licenses.core.issue;

import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;

@SuppressWarnings("restriction")
final class FeatureGrantCapacityReduction implements Reduction<FeatureGrant> {

	private final int capacity = 3;

	@Override
	public void accept(FeatureGrant grant) {
		grant.setCapacity(Math.min(capacity, grant.getCapacity()));
	}

}
