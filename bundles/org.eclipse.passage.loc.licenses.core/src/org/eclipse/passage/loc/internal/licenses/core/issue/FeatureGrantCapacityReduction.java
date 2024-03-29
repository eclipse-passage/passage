/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
import org.eclipse.passage.loc.internal.licenses.core.i18n.ReductionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class FeatureGrantCapacityReduction implements Reduction<FeatureGrant> {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final int capacity = 3;

	@Override
	public void accept(FeatureGrant grant) {
		if (grant.getCapacity() <= capacity) {
			return;
		}
		log.warn(String.format(ReductionMessages.FeatureGrantCapacityReduction_reduction_featuregrant_capacity,
				capacity));
		log.warn(String.format(ReductionMessages.FeatureGrantCapacityReduction_reduction_featuregrant_feature,
				grant.getFeature(), capacity));
		grant.setCapacity(capacity);
	}

}
