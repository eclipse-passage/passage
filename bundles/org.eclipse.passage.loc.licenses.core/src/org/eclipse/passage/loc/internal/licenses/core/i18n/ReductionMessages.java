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
package org.eclipse.passage.loc.internal.licenses.core.i18n;

import org.eclipse.osgi.util.NLS;

public class ReductionMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.licenses.core.i18n.ReductionMessages"; //$NON-NLS-1$
	public static String ClosedValidityPeriodReduction_reduction_validityperiod_allowed;
	public static String ClosedValidityPeriodReduction_reduction_validityperiod_length;
	public static String FeatureGrantCapacityReduction_reduction_featuregrant_capacity;
	public static String FeatureGrantCapacityReduction_reduction_featuregrant_feature;
	public static String UserGrantsAmountReduction_reduction_usergrant_amount;
	public static String UserGrantsAmountReduction_reduction_usergrant_user;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ReductionMessages.class);
	}

	private ReductionMessages() {
	}
}
